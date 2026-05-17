package hockey.kpi.backend.service;

import hockey.kpi.backend.model.Match;
import hockey.kpi.backend.model.Team;
import hockey.kpi.backend.repository.MatchEventRepository;
import hockey.kpi.backend.repository.MatchRepository;
import hockey.kpi.backend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchEventRepository matchEventRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, MatchEventRepository matchEventRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.matchEventRepository = matchEventRepository;
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public Match updateMatchStatus(Long matchId, String newStatus) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Матч не знайдено"));

        match.setStatus(newStatus);

        return matchRepository.save(match);
    }

    public Match finishMatch(Long matchId, boolean isOvertime) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Матч не знайдено"));

        if ("FINISHED".equals(match.getStatus())) {
            throw new IllegalArgumentException("Цей матч вже було завершено");
        }

        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();

        int homeScore = match.getHomeScore();
        int awayScore = match.getAwayScore();

        if (homeScore == awayScore) {
            throw new IllegalArgumentException("Не можливо мати рівний рахунок");
        }

        Team winner = (homeScore > awayScore) ? homeTeam : awayTeam;
        Team loser = (homeScore > awayScore) ? awayTeam : homeTeam;

        int winnerCurrentGames = winner.getGamesPlayed();
        int loserCurrentGames = loser.getGamesPlayed();

        int winnerNewGames = winnerCurrentGames + 1;
        int loserNewGames = loserCurrentGames + 1;

        winner.setGamesPlayed(winnerNewGames);
        loser.setGamesPlayed(loserNewGames);


        if (isOvertime) {

            int currentWinnerOtWins = winner.getOtWins();
            winner.setOtWins(currentWinnerOtWins + 1);

            int currentWinnerPoints = winner.getPoints();
            winner.setPoints(currentWinnerPoints + 2);

            int currentLoserOtLosses = loser.getOtLosses();
            loser.setOtLosses(currentLoserOtLosses + 1);

            int currentLoserPoints = loser.getPoints();
            loser.setPoints(currentLoserPoints + 1);

        } else {

            int currentWinnerWins = winner.getWins();
            winner.setWins(currentWinnerWins + 1);

            int currentWinnerPoints = winner.getPoints();
            winner.setPoints(currentWinnerPoints + 3);

            int currentLoserLosses = loser.getLosses();
            loser.setLosses(currentLoserLosses + 1);
        }

        match.setStatus("FINISHED");

        teamRepository.save(homeTeam);
        teamRepository.save(awayTeam);

        return matchRepository.save(match);
    }
}