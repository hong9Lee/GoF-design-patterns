package com.design_patterns.java._03_behavioral_patterns._21_strategy._05_study;

import java.util.ArrayList;
import java.util.List;

public class CardDeckFactory {
    private List<Integer> cardList = new ArrayList<>();
    private CardChange change;

    public CardDeckFactory(List<Integer> cardList) {
        this.cardList = cardList;
    }

    public void doChange() {
        change.change(cardList);
    }

    public void setChange(CardChange change) {
        this.change = change;
    }
}
