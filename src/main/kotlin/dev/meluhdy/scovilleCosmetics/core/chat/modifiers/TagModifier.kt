package dev.meluhdy.scovilleCosmetics.core.chat.modifiers

import dev.meluhdy.melodia.utils.legacyToMiniMessage
import dev.meluhdy.scovilleCosmetics.core.chat.ChatModifier
import dev.meluhdy.scovilleCosmetics.core.player.PlayerCosmeticsManager
import org.bukkit.entity.Player
import java.util.UUID

data class TagModifier(val tagId: UUID?) : ChatModifier(ChatModifiers.TAG) {

    override fun getString(player: Player): String {
        if (tagId == null) {
            return ""
        }

        val settings = PlayerCosmeticsManager.get(player) ?: return ""

        val tag = settings.getTag() ?: return ""
        return "${tag.tag.trim()}&r ".legacyToMiniMessage()
    }
}
