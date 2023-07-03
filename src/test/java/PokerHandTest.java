import org.example.PokerHand;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokerHandTest {

    @Test
    public void testCompareTo() {
        PokerHand highCard1 = new PokerHand("KS 2H 5C JD TD");
        PokerHand highCard2 = new PokerHand("QS 2H 5C JD TD");
        assertTrue(highCard1.compareTo(highCard2) < 0);

        PokerHand onePair1 = new PokerHand("2S 2H 5C JD TD");
        PokerHand onePair2 = new PokerHand("2S 2H 6C JD TD");
        assertTrue(onePair1.compareTo(onePair2) < 0);

        PokerHand twoPair1 = new PokerHand("2S 2H 5C 5D TD");
        PokerHand twoPair2 = new PokerHand("2S 2H 5C 5D AD");
        assertTrue(twoPair1.compareTo(twoPair2) < 0);

        PokerHand threeOfAKind1 = new PokerHand("2S 2H 2C JD TD");
        PokerHand threeOfAKind2 = new PokerHand("3S 3H 3C JD TD");
        assertTrue(threeOfAKind1.compareTo(threeOfAKind2) < 0);

        PokerHand straight1 = new PokerHand("2S 3H 4C 5D 6D");
        PokerHand straight2 = new PokerHand("3S 4H 5C 6D 7D");
        assertTrue(straight1.compareTo(straight2) < 0);

        PokerHand flush1 = new PokerHand("2S 7S KS JS TS");
        PokerHand flush2 = new PokerHand("3S 6S 9S JS TS");
        assertTrue(flush1.compareTo(flush2) > 0);

        PokerHand fullHouse1 = new PokerHand("2S 2H 2C KD KC");
        PokerHand fullHouse2 = new PokerHand("3S 3H 3C AD AC");
        assertTrue(fullHouse1.compareTo(fullHouse2) < 0);

        PokerHand fourOfAKind1 = new PokerHand("2S 2H 2C 2D KD");
        PokerHand fourOfAKind2 = new PokerHand("3S 3H 3C 3D AD");
        assertTrue(fourOfAKind1.compareTo(fourOfAKind2) < 0);

        PokerHand straightFlush1 = new PokerHand("2S 3S 4S 5S 6S");
        PokerHand straightFlush2 = new PokerHand("3S 4S 5S 6S 7S");
        assertTrue(straightFlush1.compareTo(straightFlush2) < 0);
    }
}