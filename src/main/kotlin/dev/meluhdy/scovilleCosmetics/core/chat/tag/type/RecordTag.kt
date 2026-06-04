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

class RecordTag(id: String, tag: String, val course: UUID, uuid: UUID = UUID.randomUUID(), timeCreated: Long = System.currentTimeMillis()) : ChatTag(id, tag, TagType.RECORD, uuid, timeCreated) {

    override fun getDesc(p: Player): String = TextUtils.translate(ScovilleCosmetics.plugin, "tag.record.desc", p.locale(),
        CourseManager.get(course).run { if (this == null) "UNKNOWN COURSE" else this.coloredName ?: "NO NAME" })

}

object RecordTagSerializer : ChatTagSerializer<RecordTag>() {

    class RecordTagBuilder : ChatTagBuilder<RecordTag>() {

        var course: UUID = UUID.nameUUIDFromBytes(ByteArray(0))

        override fun build(): RecordTag = RecordTag(id, tag, course, uuid, timeCreated)

    }

    override fun getBuilder(): Builder<RecordTag> = RecordTagBuilder()

    override val extraSteps: Array<SerializerElement<*, RecordTag>> = arrayOf(
        SerializerElement("course", String.serializer(), { it.uuid.toString() }, { element, builder -> (builder as RecordTagBuilder).course = UUID.fromString(element) })
    )

}