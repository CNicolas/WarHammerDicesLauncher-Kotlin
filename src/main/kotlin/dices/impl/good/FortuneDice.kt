package dices.impl.good

import dices.Dice
import dices.Face
import dices.Face.*

class FortuneDice : Dice() {
    override val faces: List<Face> = listOf(
            SUCCESS,
            SUCCESS,
            BOON,
            VOID,
            VOID,
            VOID
    )
}