package dev.meluhdy.scovilleChat.core.player

import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.melodia.misc.serialization.SerializerElement
import dev.meluhdy.scovilleChat.core.modifiers.ChatColorModifier
import dev.meluhdy.scovilleChat.core.modifiers.ConnectionMessages
import dev.meluhdy.scovilleChat.core.modifiers.NicknameModifier
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import java.util.UUID

object PlayerMessageSettingsSerializer : MelodiaSerializer<PlayerMessageSettings>() {

    class PlayerMessageSettingsBuilder : Builder<PlayerMessageSettings>() {

        var connectionMessage: ConnectionMessages = ConnectionMessages.DEFAULT

        var nickname: NicknameModifier = NicknameModifier(null)
        var chatColor: ChatColorModifier = ChatColorModifier(ChatColorModifier.ChatColor.WHITE)
        var tag: UUID? = null

        override fun build(): PlayerMessageSettings {
            val out = PlayerMessageSettings(uuid)
            out.nickname = nickname
            out.connectionMessage = connectionMessage
            out.tag = tag
            return out
        }

    }

    override val builder: Builder<PlayerMessageSettings> = PlayerMessageSettingsBuilder()
    override val steps: Array<SerializerElement<*, PlayerMessageSettings>> = arrayOf(
        SerializerElement("nickname", String.serializer().nullable, { settings -> settings.nickname.nickname }, { value, settings -> (settings as PlayerMessageSettingsBuilder).nickname = NicknameModifier(value) }),
        SerializerElement("connectionMessage", Int.serializer(), { settings -> settings.connectionMessage.ordinal }, { value, settings -> (settings as PlayerMessageSettingsBuilder).connectionMessage = ConnectionMessages.entries[value] }),
        SerializerElement("chatColor", Int.serializer(), { settings -> settings.chatColor.chatColor.ordinal }, { value, settings -> (settings as PlayerMessageSettingsBuilder).chatColor = ChatColorModifier(ChatColorModifier.ChatColor.entries[value]) }),
        SerializerElement("tag", String.serializer().nullable, { settings -> settings.tag?.toString() }, { value, settings -> (settings as PlayerMessageSettingsBuilder).tag = value?.let { UUID.fromString(it) } })
    )

}