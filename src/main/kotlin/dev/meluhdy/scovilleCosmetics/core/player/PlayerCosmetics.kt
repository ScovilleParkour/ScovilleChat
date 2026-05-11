package dev.meluhdy.scovilleCosmetics.core.player

import dev.meluhdy.melodia.manager.MelodiaItem
import dev.meluhdy.scovilleCosmetics.core.chat.modifiers.ChatColorModifier
import dev.meluhdy.scovilleCosmetics.core.chat.modifiers.ConnectionMessages
import dev.meluhdy.scovilleCosmetics.core.chat.modifiers.NicknameModifier
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import org.bukkit.entity.Player
import java.util.UUID

class PlayerCosmetics(uuid: UUID) : MelodiaItem(uuid) {

    data class TagSelector(val tagId: String, val type: ChatTag.TagType)

    constructor(p: Player) : this(p.uniqueId)

    var tag: TagSelector? = null
    var nickname: NicknameModifier = NicknameModifier(null)
    var connectionMessage: ConnectionMessages = ConnectionMessages.DEFAULT
    var chatColor: ChatColorModifier = ChatColorModifier(ChatColorModifier.ChatColor.WHITE)

    fun getTag(): ChatTag? = tag?.let { it.type.getManager().get { t -> t.id == it.tagId } }

}