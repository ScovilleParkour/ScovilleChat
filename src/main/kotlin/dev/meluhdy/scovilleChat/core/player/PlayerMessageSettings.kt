package dev.meluhdy.scovilleChat.core.player

import dev.meluhdy.melodia.manager.MelodiaItem
import dev.meluhdy.scovilleChat.core.modifiers.ChatColorModifier
import dev.meluhdy.scovilleChat.core.modifiers.ConnectionMessages
import dev.meluhdy.scovilleChat.core.modifiers.NicknameModifier
import dev.meluhdy.scovilleChat.core.tag.ChatTag
import dev.meluhdy.scovilleChat.core.tag.TagManager
import org.bukkit.entity.Player
import java.util.UUID

class PlayerMessageSettings(uuid: UUID) : MelodiaItem(uuid) {

    constructor(p: Player) : this(p.uniqueId)

    var tag: UUID? = null
    var nickname: NicknameModifier = NicknameModifier(null)
    var connectionMessage: ConnectionMessages = ConnectionMessages.DEFAULT
    var chatColor: ChatColorModifier = ChatColorModifier(ChatColorModifier.ChatColor.WHITE)

    fun getTag(): ChatTag? = tag?.let { TagManager.get(it) }

}