package hockey.kpi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    private String city;

    @Column(name = "games_played")
    private Integer gamesPlayed = 0;

    private Integer wins = 0;
    private Integer losses = 0;

    @Column(name = "ot_wins")
    private Integer otWins = 0;

    @Column(name = "ot_losses")
    private Integer otLosses = 0;

    @Column(name = "goals_scored")
    private Integer goalsScored = 0;

    @Column(name = "goals_conceded")
    private Integer goalsConceded = 0;

    private Integer points = 0;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getOtLosses() {
        return otLosses;
    }

    public void setOtLosses(Integer otLosses) {
        this.otLosses = otLosses;
    }

    public Integer getOtWins() {
        return otWins;
    }

    public void setOtWins(Integer otWins) {
        this.otWins = otWins;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Integer getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(Integer goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}











