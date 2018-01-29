package launch

import dices.Face.*
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class LaunchHelpersTest {
    @Test
    fun should_simplify_a_list_of_faces() {
        val faces = listOf(DELAY, SUCCESS, FAILURE, BOON, BANE, BOON)
        val finalFaces = simplifyFaces(faces)

        assertThat(finalFaces).isNotEmpty
        assertThat(finalFaces.size).isEqualTo(2)
        assertThat(finalFaces).containsExactly(BOON, DELAY)
    }
}