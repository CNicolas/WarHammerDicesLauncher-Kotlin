package launch

import dices.Face
import dices.Face.*

private val opposingFace: Map<Face, Face> = hashMapOf(
        SUCCESS to FAILURE,
        FAILURE to SUCCESS,
        BOON to BANE,
        BANE to BOON)

internal fun simplifyFaces(faces: List<Face>): List<Face> {
    val report = facesToFacesReport(faces)

    return facesReportToFaces(removeOpposites(report))
}

fun facesToFacesReport(faces: List<Face>): Map<Face, Int> {
    val report = hashMapOf<Face, Int>()

    faces.distinct()
            .map { face -> report.put(face, faces.count { it == face }) }

    return report
}

fun facesReportToFaces(facesReport: Map<Face, Int>): List<Face> {
    return facesReport.flatMap { entry ->
        (0 until entry.value).map { entry.key }
    }
}

private fun removeOpposites(report: Map<Face, Int>): Map<Face, Int> {
    val mutableReport = HashMap(report)

    mutableReport.forEach { face, faceCount ->
        run {
            if (faceCount > 0) {
                adjustOpposingFaces(face, faceCount, mutableReport)
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
