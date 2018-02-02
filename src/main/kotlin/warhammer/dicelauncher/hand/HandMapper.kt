package warhammer.dicelauncher.hand

import warhammer.database.entities.HandEntity

object HandMapper {
    fun mapHandToEntity(hand: Hand): HandEntity {
        return HandEntity(name = hand.name,
                id = hand.id,
                characteristicDicesCount = hand.characteristicDicesCount,
                expertiseDicesCount = hand.expertiseDicesCount,
                fortuneDicesCount = hand.fortuneDicesCount,
                conservativeDicesCount = hand.conservativeDicesCount,
                recklessDicesCount = hand.recklessDicesCount,
                challengeDicesCount = hand.challengeDicesCount,
                misfortuneDicesCount = hand.misfortuneDicesCount)
    }

    fun mapEntityToHand(entity: HandEntity?): Hand? {
        if (entity == null) {
            return null
        }

        return Hand(name = entity.name,
                id = entity.id,
                characteristicDicesCount = entity.characteristicDicesCount ?: 0,
                expertiseDicesCount = entity.expertiseDicesCount ?: 0,
                fortuneDicesCount = entity.fortuneDicesCount ?: 0,
                conservativeDicesCount = entity.conservativeDicesCount ?: 0,
                recklessDicesCount = entity.recklessDicesCount ?: 0,
                challengeDicesCount = entity.challengeDicesCount ?: 0,
                misfortuneDicesCount = entity.misfortuneDicesCount ?: 0)
    }
}