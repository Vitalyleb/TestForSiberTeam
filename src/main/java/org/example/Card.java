package org.example;

public class Card implements Comparable<Card> {
    private Rank rank;
    private Suit suit;

    public Card(String cardString) {
        this.rank = Rank.fromSymbol(cardString.charAt(0));
        this.suit = Suit.fromSymbol(cardString.charAt(1));
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return rank.compareTo(other.rank);
    }
}

