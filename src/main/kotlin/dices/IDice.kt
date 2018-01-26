package dices

interface IDice {
    val faces: List<Face>

    fun roll(): List<Face>
}