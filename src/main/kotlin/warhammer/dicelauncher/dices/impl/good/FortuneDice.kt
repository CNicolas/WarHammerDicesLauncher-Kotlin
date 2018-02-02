package warhammer.dicelauncher.dices.impl.good

import warhammer.dicelauncher.dices.AbstractDice
import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.Face.*

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