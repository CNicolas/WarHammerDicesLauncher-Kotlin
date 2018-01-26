package dices.impl.good

import dices.AbstractDice
import dices.Face
import dices.Face.*

class ConservativeDice : AbstractDice() {
    override val faces: List<Face> = listOf(
            SUCCESS,
            SUCCESS,
            SUCCESS,
            SUCCESS,
            BOON,
            BOON,
            SUCCESS_BOON,
            SUCCESS_DELAY,
            SUCCESS_DELAY,
            VOID
    )

    override fun roll(): List<Face> {
        val face = takeRandomFace()

        return when (face) {
            SUCCESS_BOON -> listOf(SUCCESS, BOON)
            SUCCESS_DELAY -> listOf(SUCCESS, DELAY)
            else -> listOf(face)
        }
    }
}