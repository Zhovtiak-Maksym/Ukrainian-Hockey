package hockey.kpi.backend.controller;

import hockey.kpi.backend.model.Match;
import hockey.kpi.backend.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.createMatch(match));
    }


    @PostMapping("/{id}/finish")
    public ResponseEntity<Void> finishMatch(@PathVariable Long id, @RequestParam boolean isOvertime) {

        matchService.finishMatch(id, isOvertime);

        return ResponseEntity.ok().build();
    }
}
