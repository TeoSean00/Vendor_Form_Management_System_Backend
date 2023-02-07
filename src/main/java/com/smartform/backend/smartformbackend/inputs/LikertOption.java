package com.smartform.backend.smartformbackend.inputs;

public class LikertOption {
    private int score;
    private String name;

    public LikertOption(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    
    
}
