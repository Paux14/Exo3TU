package org.exampleTest;

import org.example.DiceScore;
import org.example.Ide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiceScoreTest {

    @Mock
    private Ide de;

    private DiceScore diceScore;

    @BeforeEach
    void setUp() {
        diceScore = new DiceScore(de);
    }

    @Test
    void getScore_identicalDice_returnsValueTimesTwoPlusTen() {
        when(de.getRoll()).thenReturn(4, 4);

        assertEquals(18, diceScore.getScore());
    }

    @Test
    void getScore_identicalDiceEqualToSix_returnsThirty() {
        when(de.getRoll()).thenReturn(6, 6);

        assertEquals(30, diceScore.getScore());
    }

    @Test
    void getScore_differentDice_returnsHighestValue() {
        when(de.getRoll()).thenReturn(2, 5);

        assertEquals(5, diceScore.getScore());
    }

    @Test
    void getScore_firstDiceHigherThanSecond_returnsFirstValue() {
        when(de.getRoll()).thenReturn(5, 2);

        assertEquals(5, diceScore.getScore());
    }
}
