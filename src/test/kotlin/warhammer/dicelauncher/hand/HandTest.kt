package warhammer.dicelauncher.hand

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import warhammer.database.entities.hand.Hand
import warhammer.dicelauncher.launch.launchHand

class HandTest {
    @Test
    fun should_launch_simple_hand() {
        val hand = Hand(name = "SampleHand", characteristicDicesCount = 2, fortuneDicesCount = 1)
        val launch = launchHand(hand)

        assertThat(launch.faces.size).isEqualTo(3)

        println(launch)
    }

    @Test
    fun should_launch_rank_3_hand() {
        val hand = Hand(name = "SampleHand", fortuneDicesCount = 2, characteristicDicesCount = 5, expertiseDicesCount = 2, challengeDicesCount = 1, misfortuneDicesCount = 2)

        println(launchHand(hand))
    }
}