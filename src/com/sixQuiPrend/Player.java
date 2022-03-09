package com.sixQuiPrend;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private String name;
    private ArrayList<Card> hand;
    private int penalty;

    public Player(String name) {
        this.name = name;
        this.penalty = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
        this.penalty = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getCardIndex(int cardValue) {
        for (int i = 0; i < this.hand.size(); ++i) {
            if (this.hand.get(i).getValue() == cardValue) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getPenalty() {
        return this.penalty;
    }

    public void addPenalty(ArrayList<Card> receivedPenaltyCards) {
        for (Card receivedPenaltyCard : receivedPenaltyCards) {
            this.penalty += receivedPenaltyCard.getBullHeads();
        }
    }

    public void playCardInSerie(Series series, int cardIndex) {
        Card card = this.hand.get(cardIndex);
        this.penalty += series.addCardAndGetPenalty(card);
        this.hand.remove(cardIndex);
    }

    @Override
    public int compareTo(Player player2) {
        if (this.penalty - player2.getPenalty() != 0) {
            return this.penalty - player2.getPenalty();
        }
        return this.name.compareTo(player2.name);
    }
}
