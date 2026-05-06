package dev.meluhdy.scovilleCosmetics.core.player

import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.melodia.misc.serialization.SerializerElement
import dev.meluhdy.scovilleCosmetics.core.chat.modifiers.ChatColorModifier
import dev.meluhdy.scovilleCosmetics.core.chat.modifiers.ConnectionMessages
import dev.meluhdy.scovilleCosmetics.core.chat.modifiers.NicknameModifier
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import java.util.UUID

object PlayerCosmeticsSerializer : MelodiaSerializer<PlayerCosmetics>() {

    class PlayerCosmeticsBuilder : Builder<PlayerCosmetics>() {

        var connectionMessage: ConnectionMessages = ConnectionMessages.DEFAULT

        var nickname: NicknameModifier = NicknameModifier(null)
        var chatColor: ChatColorModifier = ChatColorModifier(ChatColorModifier.ChatColor.WHITE)
        var tag: UUID? = null

        override fun build(): PlayerCosmetics {
            val out = PlayerCosmetics(uuid)
            out.nickname = nickname
            out.connectionMessage = connectionMessage
            out.tag = tag
            return out
        }

    }

    override val builder: Builder<PlayerCosmetics> = PlayerCosmeticsBuilder()
    override val steps: Array<SerializerElement<*, PlayerCosmetics>> = arrayOf(
        SerializerElement("nickname", String.serializer().nullable, { settings -> settings.nickname.nickname }, { value, settings -> (settings as PlayerCosmeticsBuilder).nickname = NicknameModifier(value) }),
        SerializerElement("connectionMessage", Int.serializer(), { settings -> settings.connectionMessage.ordinal }, { value, settings -> (settings as PlayerCosmeticsBuilder).connectionMessage = ConnectionMessages.entries[value] }),
        SerializerElement("chatColor", Int.serializer(), { settings -> settings.chatColor.chatColor.ordinal }, { value, settings -> (settings as PlayerCosmeticsBuilder).chatColor = ChatColorModifier(ChatColorModifier.ChatColor.entries[value]) }),
        SerializerElement("tag", String.serializer().nullable, { settings -> settings.tag?.toString() }, { value, settings -> (settings as PlayerCosmeticsBuilder).tag = value?.let { UUID.fromString(it) } })
    )

}