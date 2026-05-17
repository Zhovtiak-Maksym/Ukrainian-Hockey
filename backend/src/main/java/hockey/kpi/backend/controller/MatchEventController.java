package hockey.kpi.backend.controller;

import hockey.kpi.backend.model.MatchEvent;
import hockey.kpi.backend.service.MatchEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-events")
@CrossOrigin
public class MatchEventController {

    private final MatchEventService matchEventService;

    public MatchEventController(MatchEventService matchEventService) {
        this.matchEventService = matchEventService;
    }

    @PostMapping
    public ResponseEntity<MatchEvent> addEvent(@RequestBody MatchEvent event) {
        return ResponseEntity.ok(matchEventService.addEvent(event));
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<MatchEvent>> getEventsByMatchId(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchEventService.getEventsByMatchId(matchId));
    }
}
