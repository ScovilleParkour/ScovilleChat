package dev.meluhdy.scovilleChat.core.tag

import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.melodia.misc.serialization.SerializerElement
import kotlinx.serialization.builtins.serializer

object TagSerializer : MelodiaSerializer<ChatTag>() {

    class TagBuilder : Builder<ChatTag>() {

        lateinit var id: String
        lateinit var tag: String
        var type: ChatTag.TagType = ChatTag.TagType.SPECIAL

        override fun build(): ChatTag = ChatTag(id, tag, type, uuid)

    }

    override val builder: Builder<ChatTag> = TagBuilder()
    override val steps: Array<SerializerElement<*, ChatTag>> = arrayOf(
        SerializerElement("id", String.serializer(), { tag -> tag.id }, { value, builder -> (builder as TagBuilder).id = value }),
        SerializerElement("tag", String.serializer(), { tag -> tag.tag }, { value, builder -> (builder as TagBuilder).tag = value }),
        SerializerElement("type", Int.serializer(), { tag -> tag.type.ordinal }, { value, builder -> (builder as TagBuilder).type = ChatTag.TagType.entries[value] }),
    )
}