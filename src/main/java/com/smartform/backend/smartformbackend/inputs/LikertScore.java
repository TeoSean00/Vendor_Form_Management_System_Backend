package com.smartform.backend.smartformbackend.inputs;

import com.smartform.backend.smartformbackend.inputs.LikertOption;
import java.util.ArrayList;

public class LikertScore extends Base {
    
    ArrayList<LikertOption> options = new ArrayList<LikertOption>();

    public LikertScore(String plainText, boolean required, String headerType) {
        super(plainText, required, headerType);
    }
    
    public void addOption(int score, String name){
        LikertOption likertOption = new LikertOption(score, name);
        options.add(likertOption);
    }

    public void removeOption(LikertOption option){
        // Not sure about this
        options.remove(option);
    }

    public int getTotalScore(){
        int totalScore = 0;
        for (LikertOption option : options){
            totalScore += option.getScore();
        }
        return totalScore;
    }
}
