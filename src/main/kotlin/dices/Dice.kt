package dices

import java.util.*

abstract class Dice : IDice {
    private val facesCount: Int
        get() = faces.size

    override fun roll(): List<Face> = listOf(takeRandomFace())

    fun takeRandomFace() = faces[Random().nextInt(facesCount)]
}