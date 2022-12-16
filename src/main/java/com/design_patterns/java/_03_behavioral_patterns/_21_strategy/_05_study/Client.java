package com.design_patterns.java._03_behavioral_patterns._21_strategy._05_study;

import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        // 카드 섞기
        CardDeckFactory cardDeckFactory = new Dealer(new ArrayList<>(Arrays.asList(1, 10, 3, 4)));

        cardDeckFactory.setChange(new ShuffleStrategy());
        cardDeckFactory.doChange();
        cardDeckFactory.doChange();

        cardDeckFactory.setChange(new DescSortStrategy());
        cardDeckFactory.doChange();
    }
}
