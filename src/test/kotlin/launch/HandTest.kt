package launch

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class HandTest {
    @Test
    fun should_launch_simple_hand() {
        val hand = Hand(characteristicDicesCount = 2, misfortuneDicesCount = 1)
        val launch = launchHand(hand)

        assertThat(launch.faces.size).isEqualTo(3)

        println(launch)
    }

    @Test
    fun should_launch_rank_3_hand() {
        val hand = Hand(fortuneDicesCount = 2, characteristicDicesCount = 5, expertiseDicesCount = 2, challengeDicesCount = 1, misfortuneDicesCount = 2)

        println(launchHand(hand))
    }
}