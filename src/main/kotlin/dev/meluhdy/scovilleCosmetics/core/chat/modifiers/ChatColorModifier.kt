package dev.meluhdy.scovilleCosmetics.core.chat.modifiers

import dev.meluhdy.scovilleCosmetics.core.chat.ChatModifier
import org.bukkit.entity.Player

class ChatColorModifier(val chatColor: ChatColor) : ChatModifier(ChatModifiers.CHAT_COLOR) {

    enum class ChatColor(val color: String, val char: Char) {
        DARK_GREEN  ("dark_green", '2'),
        GREEN       ("green", 'a'),
        YELLOW      ("yellow", 'e'),
        GOLD        ("gold", '6'),
        RED         ("red", 'c'),
        DARK_RED    ("dark_red", '4'),
        DARK_GRAY   ("dark_gray", '8'),
        LIGHT_PURPLE("light_purple", 'd'),
        DARK_PURPLE ("dark_purple", '5'),
        WHITE       ("white", 'f')
    }

    override fun getString(player: Player): String = "<${chatColor.color}>"

}