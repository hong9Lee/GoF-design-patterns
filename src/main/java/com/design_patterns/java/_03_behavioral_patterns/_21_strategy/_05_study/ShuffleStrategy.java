package com.design_patterns.java._03_behavioral_patterns._21_strategy._05_study;

import java.util.Collections;
import java.util.List;

public class ShuffleStrategy implements CardChange {

    @Override
    public List<Integer> change(List<Integer> cardList) {
        Collections.shuffle(cardList);
        return cardList;
    }
}
