package warhammer.dicelauncher.dices.impl.good

import warhammer.dicelauncher.dices.AbstractDice
import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.Face.*

class ExpertiseDice : AbstractDice() {
    override val faces: List<Face> = listOf(
            SUCCESS,
            SUCCESS_PLUS,
            BOON,
            BOON,
            SIGMAR,
            VOID
    )

    override fun roll(): List<Face> {
        val face = takeRandomFace()

        return when (face) {
            SUCCESS_PLUS -> reroll(listOf(SUCCESS))
            else -> listOf(face)
        }
    }

    private fun reroll(currentFaces: List<Face>): List<Face> {
        val face = takeRandomFace()

        return when (face) {
            SUCCESS_PLUS -> reroll(currentFaces + SUCCESS)
            else -> currentFaces + face
        }
    }
}