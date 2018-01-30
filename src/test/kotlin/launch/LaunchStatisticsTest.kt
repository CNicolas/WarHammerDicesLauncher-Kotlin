package launch

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import statistics.LaunchStatistics

class LaunchStatisticsTest {
    @Test
    fun should_calculate_total_report_of_expertise_dices() {
        val launchCount = 10
        val hand = Hand(expertiseDicesCount = 5)
        val launchResults = mutableListOf<LaunchResult>()

        (0 until launchCount).forEach { launchResults.add(launchHand(hand)) }

        val statistics = LaunchStatistics(launchResults)

        println(statistics.totalResultReport)

        assertThat(statistics.launchCount).isEqualTo(launchCount)
        assertThat(statistics.successfulLaunchCount).isLessThanOrEqualTo(launchCount)
        assertThat(statistics.totalFailure).isZero()
        assertThat(statistics.averageSuccess).isGreaterThanOrEqualTo(1.0)
    }

    @Test
    fun should_calculate_statistics() {
        val launchCount = 100
        val hand = Hand(characteristicDicesCount = 3, fortuneDicesCount = 1, challengeDicesCount = 2, misfortuneDicesCount = 1)
        val launchResults = mutableListOf<LaunchResult>()

        (0 until launchCount).forEach { launchResults.add(launchHand(hand)) }

        val statistics = LaunchStatistics(launchResults)

        println(statistics.totalResultReport)

        assertThat(statistics.launchCount).isEqualTo(launchCount)
        assertThat(statistics.successfulLaunchCount).isLessThanOrEqualTo(launchCount)
        assertThat(statistics.totalSigmar).isZero()
        assertThat(statistics.averageSuccess).isLessThanOrEqualTo(1.0)
    }

    @Test
    fun should_get_coherent_average_result() {
        val launchCount = 1000
        val hand = Hand(characteristicDicesCount = 3, fortuneDicesCount = 1, challengeDicesCount = 1)
        val launchResults = mutableListOf<LaunchResult>()

        (0 until launchCount).forEach { launchResults.add(launchHand(hand)) }

        val statistics = LaunchStatistics(launchResults)

        println(statistics.totalResultReport)
        println(statistics.averageResult)

        assertThat(statistics.launchCount).isEqualTo(launchCount)
        assertThat(statistics.successfulLaunchCount).isLessThanOrEqualTo(launchCount)
    }
}