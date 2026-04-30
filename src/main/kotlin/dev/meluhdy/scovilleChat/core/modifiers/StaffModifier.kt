package dev.meluhdy.scovilleChat.core.modifiers

import dev.meluhdy.scoville.misc.track.StaffTrack
import dev.meluhdy.scovilleChat.core.ChatModifier
import org.bukkit.entity.Player

object StaffModifier : ChatModifier(ChatModifiers.STAR) {

    override fun getString(player: Player): String {
        val rank = StaffTrack.fromPlayer(player)

        return when (rank) {
            StaffTrack.StaffRank.DEFAULT -> ""
            StaffTrack.StaffRank.JR_BUILDER -> ""
            StaffTrack.StaffRank.BUILDER -> "<gray>✫</gray> "
            StaffTrack.StaffRank.HELPER -> "<green>✫</green> "
            StaffTrack.StaffRank.MODERATOR -> "<yellow>✫</yellow> "
            StaffTrack.StaffRank.ADMIN -> "<red>✫</red> "
            StaffTrack.StaffRank.OWNER -> "<white>✫</white> "
        }
    }

}