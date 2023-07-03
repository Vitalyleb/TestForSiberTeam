package org.example;


import java.util.*;

public class PokerHand implements Comparable<PokerHand> {
    private final List<Card> cards;

    public PokerHand(String handString) {
        cards = new ArrayList<>();
        String[] cardStrings = handString.split(" ");
        for (String cardString : cardStrings) {
            cards.add(new Card(cardString));
        }
    }

    @Override
    public int compareTo(PokerHand other) {
        HandRank thisRank = evaluateRank();
        HandRank otherRank = other.evaluateRank();

        if (thisRank != otherRank) {
            return thisRank.getValue() - otherRank.getValue();
        } else {
            // If both hands have the same rank, compare the highest card
            // in each hand to determine the winner
            Collections.sort(cards);
            Collections.sort(other.cards);
            int size = cards.size();
            for (int i = size - 1; i >= 0; i--) {
                int result = cards.get(i).compareTo(other.cards.get(i));
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }
    }

    private HandRank evaluateRank() {
        Collections.sort(cards);
        boolean isFlush = isFlush();
        boolean isStraight = isStraight();

        if (isFlush && isStraight) {
            return HandRank.STRAIGHT_FLUSH;
        } else if (isFlush) {
            return HandRank.FLUSH;
        } else if (isStraight) {
            return HandRank.STRAIGHT;
        } else if (hasFourOfAKind()) {
            return HandRank.FOUR_OF_A_KIND;
        } else if (hasFullHouse()) {
            return HandRank.FULL_HOUSE;
        } else if (hasThreeOfAKind()) {
            return HandRank.THREE_OF_A_KIND;
        } else if (hasTwoPairs()) {
            return HandRank.TWO_PAIRS;
        } else if (hasOnePair()) {
            return HandRank.ONE_PAIR;
        } else {
            return HandRank.HIGH_CARD;
        }
    }

    private boolean isFlush() {
        Suit firstSuit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        int size = cards.size();
        if (cards.get(size - 1).getRank() == Rank.ACE) {
            boolean lowStraight = true;
            for (int i = 1; i < size - 1; i++) {
                if (cards.get(i).getRank().getValue() != i + 1) {
                    lowStraight = false;
                    break;
                }
            }
            if (lowStraight) {
                return true;
            }
        }

        for (int i = 0; i < size - 1; i++) {
            if (cards.get(i + 1).getRank().getValue() != cards.get(i).getRank().getValue() + 1) {
                return false;
            }
        }
        return true;
    }

    private boolean hasFourOfAKind() {
        Map<Rank, Integer> countMap = createRankCountMap();
        return countMap.containsValue(4);
    }

    private boolean hasFullHouse() {
        Map<Rank, Integer> countMap = createRankCountMap();
        return countMap.containsValue(3) && countMap.containsValue(2);
    }

    private boolean hasThreeOfAKind() {
        Map<Rank, Integer> countMap = createRankCountMap();
        return countMap.containsValue(3);
    }

    private boolean hasTwoPairs() {
        Map<Rank, Integer> countMap = createRankCountMap();
        int pairCount = 0;
        for (int count : countMap.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    private boolean hasOnePair() {
        Map<Rank, Integer> countMap = createRankCountMap();
        return countMap.containsValue(2);
    }

    private Map<Rank, Integer> createRankCountMap() {
        Map<Rank, Integer> countMap = new HashMap<>();
        for (Card card : cards) {
            Rank rank = card.getRank();
            countMap.put(rank, countMap.getOrDefault(rank, 0) + 1);
        }
        return countMap;
    }
}