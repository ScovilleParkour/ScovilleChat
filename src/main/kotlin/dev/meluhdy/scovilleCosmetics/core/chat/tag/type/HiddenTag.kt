package dev.meluhdy.scovilleCosmetics.core.chat.tag.type

import dev.meluhdy.melodia.misc.serialization.SerializerElement
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTagSerializer
import org.bukkit.entity.Player
import java.util.UUID

class HiddenTag(id: String, tag: String, uuid: UUID = UUID.randomUUID()) : ChatTag(id, tag, TagType.HIDDEN, uuid) {

    override fun getDesc(p: Player): String = "&7???"

}

object HiddenTagSerializer : ChatTagSerializer<HiddenTag>() {

    class HiddenTagBuilder : ChatTagBuilder<HiddenTag>() {

        override fun build(): HiddenTag = HiddenTag(id, tag, uuid)

    }

    override fun getBuilder(): Builder<HiddenTag> = HiddenTagBuilder()

    override val steps: Array<SerializerElement<*, HiddenTag>> = arrayOf()

}