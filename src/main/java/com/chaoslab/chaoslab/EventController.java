package com.chaoslab.chaoslab;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private final ChaosEventRepository repository;
    private final LogService logService;

    public EventController(ChaosEventRepository repository, LogService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    // GET /events
    @GetMapping
    public List<ChaosEvent> getAllEvents() {
        return repository.findAll();
    }

    // GET /events/recent
    @GetMapping("/recent")
    public List<ChaosEvent> getRecentEvents() {
        return repository.findTop10ByOrderByIdDesc();
    }

    // GET /events/type/EXCEPTION
    @GetMapping("/type/{type}")
    public List<ChaosEvent> getEventsByType(@PathVariable String type) {
        return repository.findByType(type);
    }

    // GET /events/paged?page=0&size=5
    @GetMapping("/paged")
    public Page<ChaosEvent> getPagedEvents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // GET /events/status
    @GetMapping("/status")
    public ChaosMode status() {
        return logService.getChaosMode();
    }

    // DELETE /events/clear
    @DeleteMapping("/clear")
    public String clearEvents() {
        repository.deleteAll();
        return "All events deleted";
    }
}
