package launch

import dices.Face
import dices.Face.*

fun launchHand(hand: Hand): LaunchResult {
    val faces = simplifyFaces(hand.launch())
    return LaunchResult(faces)
}

internal fun simplifyFaces(faces: List<Face>): List<Face> {
    val report = facesToFacesReport(faces)

    return facesReportToFaces(removeOpposites(report))
}

internal fun facesToFacesReport(faces: List<Face>): Map<Face, Int> {
    val report = hashMapOf<Face, Int>()

    faces.distinct()
            .map { face -> report.put(face, faces.count { it == face }) }

    return report
}

internal fun facesReportToFaces(facesReport: Map<Face, Int>): List<Face> {
    return facesReport.flatMap { entry ->
        (0 until entry.value).map { entry.key }
    }
}

private val opposingFace: Map<Face, Face> = hashMapOf(
        SUCCESS to FAILURE,
        FAILURE to SUCCESS,
        BOON to BANE,
        BANE to BOON)

private fun removeOpposites(report: Map<Face, Int>): Map<Face, Int> {
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

private fun adjustOpposingFaces(face: Face, faceCount: Int, mutableReport: HashMap<Face, Int>) {
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
                mutableReport[face] = mutableReport[face]!! - mutableReport[opposite]!!
                mutableReport[opposite] = 0
            }
            faceCount < mutableReport[opposite]!! -> {
                mutableReport[face] = 0
                mutableReport[opposite] = mutableReport[opposite]!! - mutableReport[face]!!
            }
        }
    }
}
