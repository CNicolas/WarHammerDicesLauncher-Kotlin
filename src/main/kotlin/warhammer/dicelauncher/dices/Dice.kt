package warhammer.dicelauncher.dices

interface Dice {
    val faces: List<Face>

    fun roll(): List<Face>
}