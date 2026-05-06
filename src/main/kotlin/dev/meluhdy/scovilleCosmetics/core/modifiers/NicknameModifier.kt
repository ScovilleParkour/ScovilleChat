package dev.meluhdy.scovilleCosmetics.core.modifiers

import dev.meluhdy.scovilleCosmetics.core.ChatModifier
import org.bukkit.entity.Player

data class NicknameModifier(val nickname: String?) : ChatModifier(ChatModifiers.NICKNAME) {

    override fun getString(player: Player): String = "${nickname ?: player.name} "
}
