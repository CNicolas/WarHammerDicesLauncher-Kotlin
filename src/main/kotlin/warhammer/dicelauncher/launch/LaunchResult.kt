package warhammer.dicelauncher.launch

import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.Face.SUCCESS

data class LaunchResult(val faces: List<Face>) {
    val report: FacesReport = facesToFacesReport(faces)
    val isSuccessful: Boolean = faces.contains(SUCCESS)

    private val successfulString: String
        get() = if (isSuccessful) "OK" else "KO"

    override fun toString(): String {
        return "$successfulString $report"
    }

}