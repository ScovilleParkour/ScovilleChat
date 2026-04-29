package dev.meluhdy.scovilleChat.core.player

import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.melodia.misc.serialization.SerializerElement
import kotlinx.serialization.builtins.serializer
import java.util.UUID

object PlayerMessageSettingsSerializer : MelodiaSerializer<PlayerMessageSettings>() {

    class PlayerMessageSettingsBuilder : Builder<PlayerMessageSettings>() {

        var connectionMessage: PlayerMessageSettings.ConnectionMessages = PlayerMessageSettings.ConnectionMessages.DEFAULT

        override fun build(): PlayerMessageSettings {
            val out = PlayerMessageSettings(uuid)
            out.connectionMessage = connectionMessage
            return out
        }

    }

    override val builder: Builder<PlayerMessageSettings> = PlayerMessageSettingsBuilder()
    override val steps: Array<SerializerElement<*, PlayerMessageSettings>> = arrayOf(
        SerializerElement("connectionMessage", Int.serializer(), { settings -> settings.connectionMessage.ordinal }, { value, settings -> (settings as PlayerMessageSettingsBuilder).connectionMessage = PlayerMessageSettings.ConnectionMessages.entries[value] })
    )

}