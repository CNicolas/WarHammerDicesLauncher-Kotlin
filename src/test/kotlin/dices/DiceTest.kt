package dices

import dices.Face.*
import dices.impl.good.ConservativeDice
import dices.impl.good.ExpertiseDice
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class DiceTest {
    @Test
    fun should_return_random_face() {
        val dice = ConservativeDice()
        val allRandomFaces = mutableListOf<Face>()

        (0..1000).forEach { allRandomFaces.add(dice.takeRandomFace()) }

        val resFaces = allRandomFaces.distinct()
                .sorted()

        assertThat(resFaces.size).isEqualTo(dice.faces.distinct().size)
        assertThat(resFaces).isEqualTo(listOf(VOID, SUCCESS, SUCCESS_BOON, SUCCESS_DELAY, BOON))
    }

    @Test
    fun should_return_sometimes_more_than_one_face_on_expertise_dice() {
        val dice = ExpertiseDice()
        val allRolls = mutableListOf(listOf<Face>())

        (0..1000).forEach { allRolls.add(dice.roll()) }

        assertThat(allRolls.filter { it.size > 1 }.size).isGreaterThanOrEqualTo(1)
    }
}