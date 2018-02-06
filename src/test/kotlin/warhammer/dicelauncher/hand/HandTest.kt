package warhammer.dicelauncher.hand

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import warhammer.database.entities.HandEntity
import warhammer.database.services.HandsDatabaseService
import warhammer.dicelauncher.launch.launchHand

class HandTest {
    @Test
    fun should_launch_simple_hand() {
        val hand = HandEntity("SampleHand", characteristicDicesCount = 2, fortuneDicesCount = 1)
        val launch = launchHand(hand)

        assertThat(launch.faces.size).isEqualTo(3)

        println(launch)
    }

    @Test
    fun should_launch_rank_3_hand() {
        val hand = HandEntity("SampleHand", fortuneDicesCount = 2, characteristicDicesCount = 5, expertiseDicesCount = 2, challengeDicesCount = 1, misfortuneDicesCount = 2)

        println(launchHand(hand))
    }

    @Test
    fun should_use_hand_and_database() {
        val hand = HandEntity("SampleHand", characteristicDicesCount = 2, misfortuneDicesCount = 1)

        val handsDatabaseService = HandsDatabaseService(databaseUrl = "jdbc:sqlite:testSqlite:?mode=memory&cache=shared", driver = "org.sqlite.JDBC")
        handsDatabaseService.deleteAll()

        val savedHand = handsDatabaseService.add(hand)
        assertThat(savedHand).isNotNull()
        assertThat(savedHand?.name).isEqualTo("SampleHand")
        assertThat(savedHand?.characteristicDicesCount).isEqualTo(2)
        assertThat(savedHand?.misfortuneDicesCount).isEqualTo(1)
        assertThat(savedHand?.expertiseDicesCount).isEqualTo(0)
        assertThat(savedHand?.challengeDicesCount).isEqualTo(0)

        val modifiedHand = savedHand?.copy(name = "NewName", expertiseDicesCount = 1, challengeDicesCount = 1)
        val updatedHand = handsDatabaseService.update(modifiedHand!!)
        assertThat(updatedHand).isNotNull()
        assertThat(updatedHand?.name).isEqualTo("NewName")
        assertThat(updatedHand?.characteristicDicesCount).isEqualTo(2)
        assertThat(updatedHand?.misfortuneDicesCount).isEqualTo(1)
        assertThat(updatedHand?.expertiseDicesCount).isEqualTo(1)
        assertThat(updatedHand?.challengeDicesCount).isEqualTo(1)

        val resOfDelete = handsDatabaseService.delete(modifiedHand)
        assertThat(resOfDelete).isTrue()
        val nullHand = handsDatabaseService.findByName("NewName")
        assertThat(nullHand).isNull()
    }
}