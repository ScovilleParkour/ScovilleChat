package dev.meluhdy.scovilleChat.core.tag

import dev.meluhdy.melodia.manager.MelodiaItem
import java.util.UUID

class ChatTag(val id: String, val tag: String, val type: TagType, uuid: UUID = UUID.randomUUID()) : MelodiaItem(uuid) {

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

    override fun equals(other: Any?): Boolean {
        return other is ChatTag && other.id == id
    }

}