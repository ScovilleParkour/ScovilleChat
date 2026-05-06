package dev.meluhdy.scovilleChat.core.tag

import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.scovilleChat.ScovilleChat
import kotlinx.serialization.json.JsonElement
import java.io.File

object TagManager : MelodiaSavingManager<ChatTag>() {

    val baseFolder
        get() = "${ScovilleChat.plugin.dataFolder}${File.separator}tags"

    override fun getFile(obj: ChatTag): File = File(baseFolder, "${obj.uuid}.json")

    override fun loadSaves(): Array<File> = File(baseFolder).listFiles() ?: arrayOf()

    override fun serializeObject(obj: ChatTag): JsonElement = serializer.encodeToJsonElement(TagSerializer, obj)

    override fun deserializeObject(jsonElement: JsonElement): ChatTag = serializer.decodeFromJsonElement(TagSerializer, jsonElement)

}