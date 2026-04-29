package dev.meluhdy.scovilleChat.core.player

import dev.meluhdy.melodia.manager.MelodiaItem
import org.bukkit.entity.Player
import java.util.UUID

class PlayerMessageSettings(uuid: UUID) : MelodiaItem(uuid) {

    enum class ConnectionState(val value: String) {
        JOIN    ("join"),
        LEAVE   ("leave")
    }

    enum class ConnectionMessages(val translationID: String) {
        DEFAULT     ("default"),
        MR_COOL     ("mr_cool"),
        SHAKESPEARE ("shakespeare"),
        SUPERHERO   ("superhero"),
        AVIATION    ("aviation"),
        MONARCH     ("monarch"),
        REVERSED    ("reversed"),
        STINKY      ("stinky");

        fun getTranslationString(connectionState: ConnectionState): String = "chat.connection.${this.translationID}.${connectionState.value}"
    }

    constructor(p: Player) : this(p.uniqueId)

    var connectionMessage: ConnectionMessages = ConnectionMessages.DEFAULT

}