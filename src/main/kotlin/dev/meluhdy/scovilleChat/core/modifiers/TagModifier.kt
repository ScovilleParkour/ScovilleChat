package dev.meluhdy.scovilleChat.core.modifiers

import dev.meluhdy.melodia.utils.fromLegacyMessage
import dev.meluhdy.melodia.utils.legacyToMiniMessage
import dev.meluhdy.scovilleChat.core.ChatModifier
import dev.meluhdy.scovilleChat.core.ChatModifier.ChatModifiers
import dev.meluhdy.scovilleChat.core.tag.TagManager
import org.bukkit.entity.Player
import java.util.UUID

data class TagModifier(val tagId: UUID?) : ChatModifier(ChatModifiers.TAG) {

    override fun getString(player: Player): String {
        if (tagId == null) {
            return ""
        }

        val tag = TagManager.get(tagId) ?: return ""
        return "${tag.tag} ".legacyToMiniMessage()
    }
}
