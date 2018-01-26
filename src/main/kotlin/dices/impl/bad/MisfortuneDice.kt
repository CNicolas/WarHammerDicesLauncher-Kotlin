package dices.impl.bad

import dices.Dice
import dices.Face
import dices.Face.*

class MisfortuneDice : Dice() {
    override val faces: List<Face> = listOf(
            FAILURE,
            FAILURE,
            BANE,
            VOID,
            VOID,
            VOID
    )
}