package org.example;

public enum Suit {
    SPADES('S'), HEARTS('H'), DIAMONDS('D'), CLUBS('C');

    private final char symbol;

    Suit(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Suit fromSymbol(char symbol) {
        for (Suit suit : Suit.values()) {
            if (suit.symbol == symbol) {
                return suit;
            }
        }
        throw new IllegalArgumentException("Invalid suit symbol: " + symbol);
    }
}
