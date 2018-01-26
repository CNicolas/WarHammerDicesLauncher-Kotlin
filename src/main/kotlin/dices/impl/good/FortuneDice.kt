package dices.impl.good

import dices.AbstractDice
import dices.Face
import dices.Face.*

class FortuneDice : AbstractDice() {
    override val faces: List<Face> = listOf(
            SUCCESS,
            SUCCESS,
            BOON,
            VOID,
            VOID,
            VOID
    )
}