package dev.meluhdy.scovilleChat.core.modifiers

import dev.meluhdy.scovilleChat.core.ChatModifier
import org.bukkit.entity.Player

object ArrowsModifier : ChatModifier(ChatModifiers.ARROWS) {

    override fun getString(player: Player): String = "<dark_gray>></dark_gray><gray>></gray> "

}