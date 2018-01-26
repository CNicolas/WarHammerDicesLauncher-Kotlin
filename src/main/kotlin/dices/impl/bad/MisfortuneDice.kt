package dices.impl.bad

import dices.AbstractDice
import dices.Face
import dices.Face.*

class MisfortuneDice : AbstractDice() {
    override val faces: List<Face> = listOf(
            FAILURE,
            FAILURE,
            BANE,
            VOID,
            VOID,
            VOID
    )
}