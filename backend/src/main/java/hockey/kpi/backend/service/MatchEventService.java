package hockey.kpi.backend.service;

import hockey.kpi.backend.model.Match;
import hockey.kpi.backend.repository.MatchRepository;
import hockey.kpi.backend.model.MatchEvent;
import hockey.kpi.backend.repository.MatchEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchEventService {

    private final MatchEventRepository matchEventRepository;
    private final MatchRepository matchRepository;

    public MatchEventService(MatchEventRepository matchEventRepository, MatchRepository matchRepository) {
        this.matchEventRepository = matchEventRepository;
        this.matchRepository = matchRepository;
    }

    public List<MatchEvent> getEventsByMatchId(Long matchId) {
        return matchEventRepository.findByMatchId(matchId);
    }

    public MatchEvent addEvent(MatchEvent event) {

        Match match = matchRepository.findById(event.getMatch().getId())
                .orElseThrow(() -> new IllegalArgumentException("Матч не знайдено"));

        if (!"IN_PROGRESS".equals(match.getStatus())) {
            throw new IllegalArgumentException("Події можна додавати тільки в матч, який є активним");
        }

        MatchEvent savedEvent = matchEventRepository.save(event);

        if ("GOAL".equals(event.getEventType())) {

            if (match.getHomeTeam().getId().equals(event.getTeam().getId())) {

                int currentHomeScore = match.getHomeScore();
                match.setHomeScore(currentHomeScore + 1);
            } else if (match.getAwayTeam().getId().equals(event.getTeam().getId())) {

                int currentAwayScore = match.getAwayScore();
                match.setAwayScore(currentAwayScore + 1);
            } else {
                throw new IllegalArgumentException("Команда з таким ID не грає у такому матчі");
            }

            matchRepository.save(match);
        }
        else if ("PENALTY".equals(event.getEventType())) {

            if (event.getPenaltyDuration() == null || event.getPenaltyDuration() <= 0) {
                throw new IllegalArgumentException("Недопустима тривалість штрафу");
            }

            if (match.getHomeTeam().getId().equals(event.getTeam().getId())) {

                int currentHomePenalty = match.getHomePenaltyMinutes();
                match.setHomePenaltyMinutes(currentHomePenalty + event.getPenaltyDuration());
            } else if (match.getAwayTeam().getId().equals(event.getTeam().getId())) {

                int currentAwayPenalty = match.getAwayPenaltyMinutes();
                match.setAwayPenaltyMinutes(currentAwayPenalty + event.getPenaltyDuration());
            } else {
                throw new IllegalArgumentException("Команда з таким ID не грає у такому матчі");
            }

            matchRepository.save(match);
        }

        return savedEvent;
    }
}
