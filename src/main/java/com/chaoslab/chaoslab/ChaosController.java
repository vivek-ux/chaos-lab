package com.chaoslab.chaoslab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chaos")
public class ChaosController {

    private final LogService logService;

    public ChaosController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/off")
    public String off() {
        logService.setChaosMode(ChaosMode.OFF);
        return "Chaos OFF";
    }

    @PostMapping("/delay")
    public String delay() {
        logService.setChaosMode(ChaosMode.DELAY);
        return "Chaos DELAY enabled";
    }

    @PostMapping("/CPU_SPIKE")
    public String cpuSpike() {
        logService.setChaosMode(ChaosMode.CPU_SPIKE);
        return "Chaos CPU_SPIKE enabled";
    }

    @GetMapping("/status")
    public ChaosMode status() {
        return logService.getChaosMode();
    }

    @PostMapping("/mode/{mode}")
    public String setChaosMode(@PathVariable String mode) {
        ChaosMode chaosMode = ChaosMode.valueOf(mode.toUpperCase());
        logService.setChaosMode(chaosMode);
        return "Chaos mode set to " + chaosMode;
    }
}
