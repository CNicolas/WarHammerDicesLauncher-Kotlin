package launch

import dices.Face.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class FacesReportTest {
    @Test
    fun should_convert_list_of_faces_to_report() {
        val faces = listOf(SUCCESS, BOON)
        val report = facesToFacesReport(faces)

        assertThat(report.keys.size).isEqualTo(2)
        assertThat(report[SUCCESS]).isEqualTo(1)
        assertThat(report[BOON]).isEqualTo(1)
    }

    @Test
    fun should_convert_complex_list_of_faces_to_report() {
        val faces = listOf(SUCCESS, BOON, DELAY, DELAY, SUCCESS, SIGMAR)
        val report = facesToFacesReport(faces)

        assertThat(report.keys.size).isEqualTo(4)
        assertThat(report[SUCCESS]).isEqualTo(2)
        assertThat(report[BOON]).isEqualTo(1)
        assertThat(report[DELAY]).isEqualTo(2)
        assertThat(report[SIGMAR]).isEqualTo(1)
    }

    @Test
    fun should_merge_2_simple_reports() {
        val faces1 = listOf(SUCCESS)
        val faces2 = listOf(SUCCESS, SUCCESS)
        val report1 = facesToFacesReport(faces1)
        val report2 = facesToFacesReport(faces2)

        assertThat(report1.keys.size).isEqualTo(1)
        assertThat(report1[SUCCESS]).isEqualTo(1)
        assertThat(report2.keys.size).isEqualTo(1)
        assertThat(report2[SUCCESS]).isEqualTo(2)

        val report = mergeReports(report1, report2)
        assertThat(report.keys.size).isEqualTo(1)
        assertThat(report[SUCCESS]).isEqualTo(3)
    }
}