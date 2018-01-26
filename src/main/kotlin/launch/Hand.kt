package launch

import dices.Face
import dices.IDice

class Hand(val dices: List<IDice>) {
    fun launch(): List<Face> = dices.flatMap { it.roll() }
}