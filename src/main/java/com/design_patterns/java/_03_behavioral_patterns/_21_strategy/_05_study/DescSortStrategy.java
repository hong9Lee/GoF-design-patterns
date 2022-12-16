package com.design_patterns.java._03_behavioral_patterns._21_strategy._05_study;

import java.util.Collections;
import java.util.List;

public class DescSortStrategy implements CardChange {

    @Override
    public List<Integer> change(List<Integer> cardList) {
        Collections.sort(cardList, Collections.reverseOrder());
        return cardList;
    }
}
