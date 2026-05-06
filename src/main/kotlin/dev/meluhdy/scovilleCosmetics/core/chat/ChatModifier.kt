package dev.meluhdy.scovilleCosmetics.core.chat

import org.bukkit.entity.Player
import java.util.SortedMap

abstract class ChatModifier(val modifiers: ChatModifiers) {

    enum class ChatModifiers {
        TAG,
        RANKUP,
        NICKNAME,
        SIDE_RANKUP,
        STAR,
        ARROWS,
        CHAT_COLOR
    }

    abstract fun getString(player: Player): String

    fun apply(player: Player, message: SortedMap<ChatModifiers, String>) {
        message[this.modifiers] = this.getString(player)
    }

}