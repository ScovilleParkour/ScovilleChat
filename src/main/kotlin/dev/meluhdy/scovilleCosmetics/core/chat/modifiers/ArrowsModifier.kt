package dev.meluhdy.scovilleCosmetics.core.chat.modifiers

import dev.meluhdy.scovilleCosmetics.core.chat.ChatModifier
import org.bukkit.entity.Player

object ArrowsModifier : ChatModifier(ChatModifiers.ARROWS) {

    override fun getString(player: Player): String = "<dark_gray>></dark_gray><gray>></gray> "

}