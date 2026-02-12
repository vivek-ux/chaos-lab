package com.chaoslab.chaoslab;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ChaosState {

    @Id
    private Long id = 1L;

    private String mode;

    public ChaosState() {}

    public ChaosState(String mode) {
        this.mode = mode;
    }

    public Long getId() { return id; }
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
}
