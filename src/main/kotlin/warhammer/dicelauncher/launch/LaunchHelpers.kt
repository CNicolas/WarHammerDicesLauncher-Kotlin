package warhammer.dicelauncher.launch

import warhammer.database.entities.HandEntity
import warhammer.dicelauncher.dices.Dice
import warhammer.dicelauncher.dices.Face
import warhammer.dicelauncher.dices.Face.*
import warhammer.dicelauncher.dices.impl.bad.ChallengeDice
import warhammer.dicelauncher.dices.impl.bad.MisfortuneDice
import warhammer.dicelauncher.dices.impl.good.*
import warhammer.dicelauncher.statistics.LaunchStatistics

fun launchHand(handEntity: HandEntity): LaunchResult {
    val faces = simplifyFaces(handEntity.launch())
    return LaunchResult(faces)
}

fun launchForStatistics(handEntity: HandEntity, count: Int): LaunchStatistics {
    val launchResults = mutableListOf<LaunchResult>()

    (0 until count).forEach { launchResults.add(launchHand(handEntity)) }

    return LaunchStatistics(launchResults)
}

internal fun simplifyFaces(faces: List<Face>): List<Face> {
    val report = facesToFacesReport(faces)

    return facesReportToFaces(removeOpposites(report))
}

private fun HandEntity.launch(): List<Face> {
    val pool = createPool(this)

    return pool.flatMap { it.roll() }
}

private fun createPool(handEntity: HandEntity): List<Dice> {
    val pool = mutableListOf<Dice>()

    (0 until handEntity.characteristicDicesCount!!).forEach { pool.add(CharacteristicDice()) }
    (0 until handEntity.expertiseDicesCount!!).forEach { pool.add(ExpertiseDice()) }
    (0 until handEntity.fortuneDicesCount!!).forEach { pool.add(FortuneDice()) }
    (0 until handEntity.conservativeDicesCount!!).forEach { pool.add(ConservativeDice()) }
    (0 until handEntity.recklessDicesCount!!).forEach { pool.add(RecklessDice()) }
    (0 until handEntity.challengeDicesCount!!).forEach { pool.add(ChallengeDice()) }
    (0 until handEntity.misfortuneDicesCount!!).forEach { pool.add(MisfortuneDice()) }

    return pool.toList()
}

private val opposingFace: Map<Face, Face> = hashMapOf(
        SUCCESS to FAILURE,
        FAILURE to SUCCESS,
        BOON to BANE,
        BANE to BOON)

private fun removeOpposites(report: FacesReport): FacesReport {
    val mutableReport = HashMap(report)

    mutableReport.forEach { face, faceCount ->
        run {
            if (faceCount > 0) {
                val opposite = opposingFace[face]
                if (opposite != null) {
                    when {
                        mutableReport[opposite] == null -> {
                        }
                        faceCount == mutableReport[opposite] -> {
                            mutableReport[face] = 0
                            mutableReport[opposite] = 0
                        }
                        faceCount > mutableReport[opposite]!! -> {
                            mutableReport[face] = report[face]!! - report[opposite]!!
                            mutableReport[opposite] = 0
                        }
                        faceCount < mutableReport[opposite]!! -> {
                            mutableReport[face] = 0
                            mutableReport[opposite] = report[opposite]!! - report[face]!!
                        }
                    }
                }
            }
        }
    }

    return mutableReport
}
