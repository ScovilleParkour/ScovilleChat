package dev.meluhdy.scovilleChat.core.player

import dev.meluhdy.melodia.manager.MelodiaItem
import dev.meluhdy.scovilleChat.core.modifiers.ChatColorModifier
import dev.meluhdy.scovilleChat.core.modifiers.ConnectionMessages
import dev.meluhdy.scovilleChat.core.modifiers.NicknameModifier
import org.bukkit.entity.Player
import java.util.UUID

class PlayerMessageSettings(uuid: UUID) : MelodiaItem(uuid) {

    constructor(p: Player) : this(p.uniqueId)

    var nickname: NicknameModifier = NicknameModifier(null)
    var connectionMessage: ConnectionMessages = ConnectionMessages.DEFAULT
    var chatColor: ChatColorModifier = ChatColorModifier(ChatColorModifier.ChatColor.WHITE)

}