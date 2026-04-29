package dev.meluhdy.scovilleChat.core.player

import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.scovilleChat.ScovilleChat
import kotlinx.serialization.json.JsonElement
import org.bukkit.entity.Player
import java.io.File

object PlayerMessageSettingsManager : MelodiaSavingManager<PlayerMessageSettings>() {

    val baseFolder
        get() = "${ScovilleChat.plugin.dataFolder}${File.separator}players"

    fun get(p: Player): PlayerMessageSettings? = get(p.uniqueId)

    fun getOrCreate(p: Player, factory: () -> PlayerMessageSettings): PlayerMessageSettings = getOrCreate(p.uniqueId, factory)

    fun getOrCreate(p: Player): PlayerMessageSettings = getOrCreate(p) { PlayerMessageSettings(p) }

    override fun getFile(obj: PlayerMessageSettings): File = File(baseFolder, "${obj.uuid}.json")

    override fun loadSaves(): Array<File> = File(baseFolder).listFiles() ?: arrayOf()

    override fun serializeObject(obj: PlayerMessageSettings): JsonElement = serializer.encodeToJsonElement(PlayerMessageSettingsSerializer, obj)

    override fun deserializeObject(jsonElement: JsonElement): PlayerMessageSettings = serializer.decodeFromJsonElement(PlayerMessageSettingsSerializer, jsonElement)

}