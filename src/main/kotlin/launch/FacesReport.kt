package launch

import dices.Face

typealias FacesReport = Map<Face, Int>

internal fun facesToFacesReport(faces: List<Face>): FacesReport {
    val report = hashMapOf<Face, Int>()

    faces.distinct()
            .map { face -> report.put(face, faces.count { it == face }) }

    return report
}

internal fun facesReportToFaces(facesReport: FacesReport): List<Face> {
    return facesReport.flatMap { entry ->
        (0 until entry.value).map { entry.key }
    }
}