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

fun mergeReports(reports: List<FacesReport>): FacesReport {
    return reports.reduce { finalReport, launchResultReport -> mergeReports(finalReport, launchResultReport) }
}

fun mergeReports(report1: FacesReport, report2: FacesReport): FacesReport {
    return report1 + report2
}

operator fun FacesReport.plus(other: FacesReport): FacesReport {
    return this.mergeReduce(other) { a, b -> a + b }
}

fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V = { _, b -> b }): Map<K, V> =
        this.toMutableMap().apply { other.forEach { merge(it.key, it.value, reduce) } }
