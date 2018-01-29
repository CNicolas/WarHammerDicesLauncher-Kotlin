package statistics

import dices.Face.*
import launch.LaunchResult
import launch.mergeReports

data class LaunchStatistics(val launchResults: List<LaunchResult>) {
    val launchCount = launchResults.size
    val successfulLaunchCount = launchResults.count { it.isSuccessful }
    val totalResultReport = mergeReports(launchResults.map { it.report })

    val totalVoid = totalResultReport[VOID] ?: 0
    val totalSuccess = totalResultReport[SUCCESS] ?: 0
    val totalBoon = totalResultReport[BOON] ?: 0
    val totalDelay = totalResultReport[DELAY] ?: 0
    val totalSigmar = totalResultReport[SIGMAR] ?: 0
    val totalFailure = totalResultReport[FAILURE] ?: 0
    val totalBane = totalResultReport[BANE] ?: 0
    val totalExhaustion = totalResultReport[EXHAUSTION] ?: 0
    val totalChaos = totalResultReport[CHAOS] ?: 0

    val averageVoid: Double = totalVoid.toDouble() / launchCount
    val averageSuccess: Double = totalSuccess.toDouble() / launchCount
    val averageBoon: Double = totalBoon.toDouble() / launchCount
    val averageDelay: Double = totalDelay.toDouble() / launchCount
    val averageSigmar: Double = totalSigmar.toDouble() / launchCount
    val averageFailure: Double = totalFailure.toDouble() / launchCount
    val averageBane: Double = totalBane.toDouble() / launchCount
    val averageExhaustion: Double = totalExhaustion.toDouble() / launchCount
    val averageChaos: Double = totalChaos.toDouble() / launchCount
}