package dev.meluhdy.scovilleCosmetics.core.chat.modifiers

import dev.meluhdy.scovilleCosmetics.core.chat.ChatModifier
import org.bukkit.entity.Player

data class NicknameModifier(val nickname: String?) : ChatModifier(ChatModifiers.NICKNAME) {

    override fun getString(player: Player): String = "${nickname ?: player.name} "
}
