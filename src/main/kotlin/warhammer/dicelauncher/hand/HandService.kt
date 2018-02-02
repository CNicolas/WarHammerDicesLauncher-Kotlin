package warhammer.dicelauncher.hand

import warhammer.database.services.HandsDatabaseService

class HandService(private val handsDatabaseService: HandsDatabaseService) {
    fun add(hand: Hand): Hand? {
        val entity = HandMapper.mapHandToEntity(hand)
        val saved = handsDatabaseService.add(entity)

        return HandMapper.mapEntityToHand(saved)
    }

    fun findByName(name: String): Hand? = HandMapper.mapEntityToHand(handsDatabaseService.findByName(name))

    fun update(hand: Hand): Hand? {
        val entity = HandMapper.mapHandToEntity(hand)
        val saved = handsDatabaseService.update(entity)

        return HandMapper.mapEntityToHand(saved)
    }

    fun delete(hand: Hand): Boolean = handsDatabaseService.delete(HandMapper.mapHandToEntity(hand))
}