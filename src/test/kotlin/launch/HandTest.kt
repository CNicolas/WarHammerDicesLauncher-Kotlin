package launch

import dices.impl.bad.ChallengeDice
import dices.impl.good.CharacteristicDice
import dices.impl.good.FortuneDice
import org.testng.annotations.Test

class HandTest {
    @Test
    fun should_have_all_rolled_faces() {
        val hand = Hand(listOf(ChallengeDice(), FortuneDice(), CharacteristicDice()))
        val launch = hand.launch()

        println(launch)
    }
}