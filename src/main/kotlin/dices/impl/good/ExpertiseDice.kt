package dices.impl.good

import dices.Dice
import dices.Face
import dices.Face.*

class ExpertiseDice : Dice() {
    override val faces: List<Face> = listOf(
            SUCCESS,
            SUCCESS_PLUS,
            BOON,
            BOON,
            SIGMAR,
            VOID
    )
}