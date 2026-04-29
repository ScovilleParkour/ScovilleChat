package dev.meluhdy.scovilleChat.core

import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.Player

data class ChatModifier(val priority: ChatPriority, val modifier: (Player, TextComponent) -> Unit) {

    enum class ChatPriority(val priority: Int) {
        LOWEST  (0),
        LOW     (1),
        NORMAL  (2),
        HIGH    (3),
        HIGHEST (4)
    }

}