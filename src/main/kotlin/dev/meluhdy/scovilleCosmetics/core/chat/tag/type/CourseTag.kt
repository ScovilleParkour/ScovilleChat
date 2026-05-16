package dev.meluhdy.scovilleCosmetics.core.chat.tag.type

import dev.meluhdy.melodia.misc.serialization.SerializerElement
import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.scoville.core.course.CourseManager
import dev.meluhdy.scovilleCosmetics.ScovilleCosmetics
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTagSerializer
import kotlinx.serialization.builtins.serializer
import org.bukkit.entity.Player
import java.util.UUID

class CourseTag(id: String, tag: String, val course: UUID, uuid: UUID = UUID.randomUUID()) : ChatTag(id, tag, TagType.RANK, uuid) {

    override fun getDesc(p: Player): String = TextUtils.translate(ScovilleCosmetics.plugin, "tag.course.desc", p.locale(),
        CourseManager.get(course).run { if (this == null) "UNKNOWN COURSE" else this.coloredName ?: "NO NAME" })

}

object CourseTagSerializer : ChatTagSerializer<CourseTag>() {

    class CourseTagBuilder : ChatTagBuilder<CourseTag>() {

        var course: UUID = UUID.nameUUIDFromBytes(ByteArray(0))

        override fun build(): CourseTag = CourseTag(id, tag, course, uuid)

    }

    override val builder: Builder<CourseTag> = CourseTagBuilder()

    override val steps: Array<SerializerElement<*, CourseTag>> = arrayOf(
        SerializerElement("course", String.serializer(), { it.uuid.toString() }, { element, builder -> (builder as CourseTagBuilder).course = UUID.fromString(element) })
    )

}