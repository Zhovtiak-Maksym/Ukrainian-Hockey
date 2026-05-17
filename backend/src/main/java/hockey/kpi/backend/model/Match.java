package hockey.kpi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Column(name = "home_score")
    private Integer homeScore = 0;

    @Column(name = "away_score")
    private Integer awayScore = 0;

    @Column(name = "home_penalty_minutes")
    private Integer homePenaltyMinutes = 0;

    @Column(name = "away_penalty_minutes")
    private Integer awayPenaltyMinutes = 0;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Column(length = 15)
    private String status = "PLANNED";

    public Match() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public Integer getHomePenaltyMinutes() {
        return homePenaltyMinutes;
    }

    public void setHomePenaltyMinutes(Integer homePenaltyMinutes) {
        this.homePenaltyMinutes = homePenaltyMinutes;
    }

    public Integer getAwayPenaltyMinutes() {
        return awayPenaltyMinutes;
    }

    public void setAwayPenaltyMinutes(Integer awayPenaltyMinutes) {
        this.awayPenaltyMinutes = awayPenaltyMinutes;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}









