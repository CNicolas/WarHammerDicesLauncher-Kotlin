package warhammer.dicelauncher.launch

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import warhammer.database.entities.HandEntity
import warhammer.dicelauncher.dices.Face.*

class LaunchHelpersTest {
    @Test
    fun should_simplify_a_list_of_faces() {
        val faces = listOf(DELAY, SUCCESS, FAILURE, BOON, BANE, BOON)
        val finalFaces = simplifyFaces(faces)

        println(faces)
        println(finalFaces)

        assertThat(finalFaces).isNotEmpty
        assertThat(finalFaces.size).isEqualTo(2)
        assertThat(finalFaces).containsExactlyInAnyOrder(BOON, DELAY)
    }

    @Test
    fun should_launch_hand_and_be_successful() {
        val hand = HandEntity("SampleHand", characteristicDicesCount = 10)
        val launchResult = launchHand(hand)

        assertThat(launchResult.isSuccessful).isTrue()

        println(launchResult)
    }

    @Test
    fun should_launch_hand_and_be_unsuccessful() {
        val hand = HandEntity("SampleHand", challengeDicesCount = 10)
        val launchResult = launchHand(hand)

        assertThat(launchResult.isSuccessful).isFalse()

        println(launchResult)
    }
}