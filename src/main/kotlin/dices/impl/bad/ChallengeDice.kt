package dices.impl.bad

import dices.Dice
import dices.Face
import dices.Face.*

class ChallengeDice : Dice() {
    override val faces: List<Face> = listOf(
            FAILURE,
            FAILURE,
            FAILURE_FAILURE,
            FAILURE_FAILURE,
            BANE,
            BANE_BANE,
            CHAOS,
            VOID
    )
}