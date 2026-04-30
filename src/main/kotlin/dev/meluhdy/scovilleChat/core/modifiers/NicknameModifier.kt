package dev.meluhdy.scovilleChat.core.modifiers

import dev.meluhdy.scovilleChat.core.ChatModifier
import org.bukkit.entity.Player

data class NicknameModifier(val nickname: String?) : ChatModifier(ChatModifiers.NICKNAME) {

    override fun getString(player: Player): String = "${nickname ?: player.name} "
}
