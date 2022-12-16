package com.design_patterns.java._03_behavioral_patterns._21_strategy._05_study;

import java.util.List;

public class Dealer extends CardDeckFactory {
    public Dealer(List<Integer> cardList) {
        super(cardList);
    }
}
