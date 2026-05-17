package hockey.kpi.backend.service;

import hockey.kpi.backend.model.Team;
import hockey.kpi.backend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team createTeam(Team team) {
        Optional<Team> existingTeam = teamRepository.findByName(team.getName());

        if (existingTeam.isPresent()) {
            throw new IllegalArgumentException("Команда з назвою '" + team.getName() + "' вже була додана");
        }

        return teamRepository.save(team);
    }
}

