package warhammer.dicelauncher.dices

import warhammer.dicelauncher.dices.Face.*
import warhammer.dicelauncher.dices.impl.good.ConservativeDice
import warhammer.dicelauncher.dices.impl.good.ExpertiseDice
import warhammer.dicelauncher.dices.impl.good.RecklessDice
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class DiceTest {
    @Test
    fun should_return_random_face() {
        val dice = ConservativeDice()
        val allRandomFaces = mutableListOf<Face>()

        (0 until 1000).forEach { allRandomFaces.add(dice.takeRandomFace()) }

        val resFaces = allRandomFaces.distinct()
                .sorted()

        assertThat(resFaces.size).isEqualTo(dice.faces.distinct().size)
        assertThat(resFaces).isEqualTo(listOf(VOID, SUCCESS, SUCCESS_BOON, SUCCESS_DELAY, BOON))
    }

    @Test
    fun should_sometimes_return_more_than_one_face_on_expertise_dice() {
        val dice = ExpertiseDice()
        val allRolls = mutableListOf(listOf<Face>())

        (0 until 1000).forEach { allRolls.add(dice.roll()) }

        assertThat(allRolls.filter { it.size > 1 }.size).isGreaterThanOrEqualTo(1)
    }

    @Test
    fun should_sometimes_return_two_faces_on_conservative_dice() {
        val dice = ConservativeDice()
        val allRolls = mutableListOf(listOf<Face>())

        (0 until 1000).forEach { allRolls.add(dice.roll()) }

        assertThat(allRolls.filter { it.size == 2 }.size).isGreaterThanOrEqualTo(1)
    }

    @Test
    fun should_sometimes_return_two_faces_on_reckless_dice() {
        val dice = RecklessDice()
        val allRolls = mutableListOf(listOf<Face>())

        (0 until 1000).forEach { allRolls.add(dice.roll()) }

        assertThat(allRolls.filter { it.size == 2 }.size).isGreaterThanOrEqualTo(1)
    }
}