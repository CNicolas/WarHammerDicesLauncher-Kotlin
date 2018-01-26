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

    override fun roll(): List<Face> {
        val face = takeRandomFace()

        return when (face) {
            FAILURE_FAILURE -> listOf(FAILURE, FAILURE)
            BANE_BANE -> listOf(BANE, BANE)
            else -> listOf(face)
        }
    }
}