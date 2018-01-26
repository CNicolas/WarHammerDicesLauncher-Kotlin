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