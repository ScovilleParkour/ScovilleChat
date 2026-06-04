package dev.meluhdy.scovilleCosmetics.core.chat.tag.type

import dev.meluhdy.melodia.misc.serialization.SerializerElement
import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.scovilleCosmetics.ScovilleCosmetics
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTagSerializer
import kotlinx.serialization.builtins.serializer
import org.bukkit.entity.Player
import java.util.UUID

class SpecialTag(id: String, tag: String, val descTransId: String, uuid: UUID = UUID.randomUUID(), timeCreated: Long = System.currentTimeMillis()) : ChatTag(id, tag, TagType.SPECIAL, uuid, timeCreated) {

    override fun getDesc(p: Player): String = TextUtils.translate(ScovilleCosmetics.plugin, descTransId, p.locale())

}

object SpecialTagSerializer : ChatTagSerializer<SpecialTag>() {

    class SpecialTagBuilder : ChatTagBuilder<SpecialTag>() {

        var descTransId: String = ""

        override fun build(): SpecialTag = SpecialTag(id, tag, descTransId, uuid, timeCreated)

    }

    override fun getBuilder(): Builder<SpecialTag> = SpecialTagBuilder()

    override val extraSteps: Array<SerializerElement<*, SpecialTag>> = arrayOf(
        SerializerElement("descTransId", String.serializer(), { it.descTransId }, { element, builder -> (builder as SpecialTagBuilder).descTransId = element })
    )

}