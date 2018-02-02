package warhammer.dicelauncher.hand

import warhammer.dicelauncher.dices.Dice
import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.impl.bad.ChallengeDice
import warhammer.dicelauncher.dices.impl.bad.MisfortuneDice
import warhammer.dicelauncher.dices.impl.good.*

data class Hand(val name: String,
                val id: Int = -1,
                val characteristicDicesCount: Int = 0,
                val expertiseDicesCount: Int = 0,
                val fortuneDicesCount: Int = 0,
                val conservativeDicesCount: Int = 0,
                val recklessDicesCount: Int = 0,
                val challengeDicesCount: Int = 0,
                val misfortuneDicesCount: Int = 0) {

    fun launch(): List<Face> {
        val pool = createPool()

        return pool.flatMap { it.roll() }
    }

    private fun createPool(): List<Dice> {
        val pool = mutableListOf<Dice>()

        (0 until characteristicDicesCount).forEach { pool.add(CharacteristicDice()) }
        (0 until expertiseDicesCount).forEach { pool.add(ExpertiseDice()) }
        (0 until fortuneDicesCount).forEach { pool.add(FortuneDice()) }
        (0 until conservativeDicesCount).forEach { pool.add(ConservativeDice()) }
        (0 until recklessDicesCount).forEach { pool.add(RecklessDice()) }
        (0 until challengeDicesCount).forEach { pool.add(ChallengeDice()) }
        (0 until misfortuneDicesCount).forEach { pool.add(MisfortuneDice()) }

        return pool.toList()
    }
}