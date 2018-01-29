package launch

import dices.Face
import dices.Face.SUCCESS

data class LaunchResult(val faces: List<Face>) {
    val report: Map<Face, Int> = facesToFacesReport(faces)
    val isSuccessful: Boolean = faces.contains(SUCCESS)

    private val successfulString: String
        get() = if (isSuccessful) "OK" else "KO"

    override fun toString(): String {
        return "$successfulString $report"
    }

}