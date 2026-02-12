package com.chaoslab.chaoslab;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger logger =
            LoggerFactory.getLogger(LogService.class);

    private final Random random = new Random();
    private final ChaosEventRepository repository;

    private ChaosMode chaosMode = ChaosMode.OFF;

    public LogService(ChaosEventRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 5000)
    public void heartbeat() {

        try {
            repository.save(new ChaosEvent("HEARTBEAT", "Service alive"));
            logger.info("ChaosLab heartbeat");
        } catch (Exception e) {
            logger.warn("Database unavailable -- skipping save");
        }

        switch (chaosMode) {

            case EXCEPTION:
                if (random.nextInt(5) == 0) {
                    repository.save(new ChaosEvent("EXCEPTION", "Chaos exception triggered"));
                    logger.error("Simulated exception chaos");
                    throw new RuntimeException("Chaos exception triggered");
                }
                break;

            case DELAY:
                logger.warn("Simulated latency chaos");
                try {
                    repository.save(new ChaosEvent("DELAY", "Latency injected"));
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
                break;

            case CPU_SPIKE:
                repository.save(new ChaosEvent("CPU_SPIKE", "CPU spike simulated"));
                logger.warn("Simulated CPU spike chaos");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 3000) {
                    Math.sqrt(random.nextDouble());
                }
                break;

            case OFF:
            default:
        }
    }

    public void setChaosMode(ChaosMode mode) {
        this.chaosMode = mode;
    }

    public ChaosMode getChaosMode() {
        return this.chaosMode;
    }
}
