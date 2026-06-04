package dev.meluhdy.scovilleCosmetics.core.player

import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.melodia.utils.FileUtils
import dev.meluhdy.melodia.utils.FileUtils.requireString
import dev.meluhdy.scoville.Scoville
import dev.meluhdy.scovilleCosmetics.ScovilleCosmetics
import kotlinx.serialization.json.JsonElement
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

object PlayerCosmeticsManager : MelodiaSavingManager<PlayerCosmetics>() {

    val baseFolder: String
        get() = FileUtils.getFile(ScovilleCosmetics.plugin, (Scoville.plugin.config as YamlConfiguration).requireString("player_folder")).absolutePath

    fun get(p: Player): PlayerCosmetics? = get(p.uniqueId)

    fun getOrCreate(p: Player, factory: () -> PlayerCosmetics): PlayerCosmetics = getOrCreate(p.uniqueId, factory)

    fun getOrCreate(p: Player): PlayerCosmetics = getOrCreate(p) { PlayerCosmetics(p) }

    override fun getFile(obj: PlayerCosmetics): File = File(baseFolder, "${obj.uuid}.json")

    override fun loadSaves(): Array<File> = File(baseFolder).listFiles() ?: arrayOf()

    override fun serializeObject(obj: PlayerCosmetics): JsonElement = serializer.encodeToJsonElement(PlayerCosmeticsSerializer, obj)

    override fun deserializeObject(jsonElement: JsonElement): PlayerCosmetics = serializer.decodeFromJsonElement(PlayerCosmeticsSerializer, jsonElement)

}