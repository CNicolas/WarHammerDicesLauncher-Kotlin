package dices.impl.good

import dices.AbstractDice
import dices.Face
import dices.Face.*

class CharacteristicDice : AbstractDice() {
    override val faces: List<Face> = listOf(
            SUCCESS,
            SUCCESS,
            SUCCESS,
            SUCCESS,
            BOON,
            BOON,
            VOID,
            VOID
    )
}