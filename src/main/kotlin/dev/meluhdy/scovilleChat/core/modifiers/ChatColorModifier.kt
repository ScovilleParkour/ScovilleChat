package dev.meluhdy.scovilleChat.core.modifiers

import dev.meluhdy.scovilleChat.core.ChatModifier
import org.bukkit.entity.Player

class ChatColorModifier(val chatColor: ChatColor) : ChatModifier(ChatModifiers.CHAT_COLOR) {

    enum class ChatColor(val color: String) {
        DARK_GREEN  ("dark_green"),
        GREEN       ("green"),
        YELLOW      ("yellow"),
        GOLD        ("gold"),
        RED         ("red"),
        DARK_RED    ("dark_red"),
        DARK_GRAY   ("dark_gray"),
        LIGHT_PURPLE("light_purple"),
        DARK_PURPLE ("dark_purple"),
        WHITE       ("white")
    }

    override fun getString(player: Player): String = "<${chatColor.color}>"

}