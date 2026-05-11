package dev.meluhdy.scovilleCosmetics.core.chat.tag

import dev.meluhdy.melodia.manager.MelodiaItem
import dev.meluhdy.melodia.manager.MelodiaManager
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.rank.RankTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.rank.RankTagManager
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.special.SpecialTagManager
import java.util.UUID

abstract class ChatTag(val id: String, val tag: String, val type: TagType, uuid: UUID = UUID.randomUUID()) : MelodiaItem(uuid) {

    enum class TagType(val id: String) {
        RANK    ("rank"),
        COURSE  ("course"),
        SHINY   ("shiny"),
        HIDDEN  ("hidden"),
        RECORD  ("record"),
        SPECIAL ("special");

        fun getManager(): MelodiaManager<out ChatTag> {
            return when (this) {
                RANK     -> RankTagManager
                SPECIAL  -> SpecialTagManager
                else -> RankTagManager // TODO: Fix
            }
        }

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

}