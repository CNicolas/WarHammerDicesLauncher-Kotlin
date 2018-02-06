package warhammer.dicelauncher.statistics

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import warhammer.database.entities.HandEntity
import warhammer.dicelauncher.launch.launchForStatistics

class LaunchStatisticsTest {
    @Test
    fun should_calculate_total_report_of_expertise_dices() {
        val launchCount = 10
        val hand = HandEntity("SampleHand", expertiseDicesCount = 5)
        val statistics = launchForStatistics(hand, launchCount)

        println(statistics.totalResultReport)

        assertThat(statistics.launchCount).isEqualTo(launchCount)
        assertThat(statistics.successfulLaunchCount).isLessThanOrEqualTo(launchCount)
        assertThat(statistics.totalFailure).isZero()
        assertThat(statistics.averageSuccess).isGreaterThanOrEqualTo(1.0)
    }

    @Test
    fun should_calculate_statistics() {
        val launchCount = 100
        val hand = HandEntity("SampleHand", characteristicDicesCount = 3, fortuneDicesCount = 1, challengeDicesCount = 2, misfortuneDicesCount = 1)

        val statistics = launchForStatistics(hand, launchCount)

        println(statistics.totalResultReport)

        assertThat(statistics.launchCount).isEqualTo(launchCount)
        assertThat(statistics.successfulLaunchCount).isLessThanOrEqualTo(launchCount)
        assertThat(statistics.totalSigmar).isZero()
        assertThat(statistics.averageSuccess).isLessThanOrEqualTo(1.0)
    }

    @Test
    fun should_get_coherent_average_result() {
        val launchCount = 1000
        val hand = HandEntity("SampleHand", characteristicDicesCount = 3, fortuneDicesCount = 1, challengeDicesCount = 1)
        val statistics = launchForStatistics(hand, launchCount)

        println(statistics.totalResultReport)
        println(statistics.averageResult)

        assertThat(statistics.launchCount).isEqualTo(launchCount)
        assertThat(statistics.successfulLaunchCount).isLessThanOrEqualTo(launchCount)
    }
}