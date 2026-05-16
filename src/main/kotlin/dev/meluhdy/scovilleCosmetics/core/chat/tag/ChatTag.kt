package dev.meluhdy.scovilleCosmetics.core.chat.tag

import dev.meluhdy.melodia.manager.MelodiaItem
import dev.meluhdy.melodia.misc.serialization.MelodiaSerializer
import dev.meluhdy.melodia.misc.serialization.SerializerElement
import kotlinx.serialization.builtins.serializer
import org.bukkit.entity.Player
import java.util.UUID

abstract class ChatTag(val id: String, val tag: String, val type: TagType, uuid: UUID = UUID.randomUUID()) : MelodiaItem(uuid) {

    enum class TagType(val id: String) {
        RANK    ("rank"),
        COURSE  ("course"),
        SHINY   ("shiny"),
        HIDDEN  ("hidden"),
        RECORD  ("record"),
        SPECIAL ("special");

        companion object {
            fun fromId(id: String): TagType? {
                return when (id) {
                    "rank" -> RANK
                    "course" -> COURSE
                    "shiny" -> SHINY
                    "hidden" -> HIDDEN
                    "record" -> RECORD
                    "special" -> SPECIAL
                    else -> null
                }
            }
        }
    }

    val permission: String
        get() = "tag.${type.id}.$id"

    abstract fun getDesc(p: Player): String

}

abstract class ChatTagSerializer<T : ChatTag> : MelodiaSerializer<T>() {

    abstract class ChatTagBuilder<T : ChatTag> : Builder<T>() {

        var id: String = ""
        var tag: String = ""
        var type: ChatTag.TagType = ChatTag.TagType.RANK

    }

    override val steps: Array<SerializerElement<*, T>> = arrayOf(
        SerializerElement("id", String.serializer(), { it.id }, { value, builder -> (builder as ChatTagBuilder).id = value }),
        SerializerElement("tag", String.serializer(), { it.tag }, { value, builder -> (builder as ChatTagBuilder).tag = value }),
        SerializerElement("type", Int.serializer(), { it.type.ordinal }, { value, builder -> (builder as ChatTagBuilder).type = ChatTag.TagType.entries[value] }),
    )

}