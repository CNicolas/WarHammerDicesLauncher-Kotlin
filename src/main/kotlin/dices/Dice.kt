package dices

import java.util.*

abstract class Dice : IDice {
    val facesCount
        get() = faces.size

    override fun roll(): List<Face> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun takeRandomFace() = faces[Random().nextInt(facesCount)]
}