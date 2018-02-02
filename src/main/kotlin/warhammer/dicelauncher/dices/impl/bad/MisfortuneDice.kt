package warhammer.dicelauncher.dices.impl.bad

import warhammer.dicelauncher.dices.AbstractDice
import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.Face.*

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