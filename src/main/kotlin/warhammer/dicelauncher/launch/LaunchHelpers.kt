package warhammer.dicelauncher.launch

import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.Face.*
import warhammer.dicelauncher.hand.Hand
import warhammer.dicelauncher.statistics.LaunchStatistics

fun launchForStatistics(hand: Hand, count: Int): LaunchStatistics {
    val launchResults = mutableListOf<LaunchResult>()

    (0 until count).forEach { launchResults.add(launchHand(hand)) }

    return LaunchStatistics(launchResults)
}

fun launchHand(hand: Hand): LaunchResult {
    val faces = simplifyFaces(hand.launch())
    return LaunchResult(faces)
}

internal fun simplifyFaces(faces: List<Face>): List<Face> {
    val report = facesToFacesReport(faces)

    return facesReportToFaces(removeOpposites(report))
}

private val opposingFace: Map<Face, Face> = hashMapOf(
        SUCCESS to FAILURE,
        FAILURE to SUCCESS,
        BOON to BANE,
        BANE to BOON)

private fun removeOpposites(report: FacesReport): FacesReport {
    val mutableReport = HashMap(report)

    mutableReport.forEach { face, faceCount ->
        run {
            if (faceCount > 0) {
                val opposite = opposingFace[face]
                if (opposite != null) {
                    when {
                        mutableReport[opposite] == null -> {
                        }
                        faceCount == mutableReport[opposite] -> {
                            mutableReport[face] = 0
                            mutableReport[opposite] = 0
                        }
                        faceCount > mutableReport[opposite]!! -> {
                            mutableReport[face] = report[face]!! - report[opposite]!!
                            mutableReport[opposite] = 0
                        }
                        faceCount < mutableReport[opposite]!! -> {
                            mutableReport[face] = 0
                            mutableReport[opposite] = report[opposite]!! - report[face]!!
                        }
                    }
                }
            }
        }
    }

    return mutableReport
}
