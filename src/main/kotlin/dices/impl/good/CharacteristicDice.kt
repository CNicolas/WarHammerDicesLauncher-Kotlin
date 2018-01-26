package dices.impl.good

import dices.Dice
import dices.Face
import dices.Face.*

class CharacteristicDice : Dice() {
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