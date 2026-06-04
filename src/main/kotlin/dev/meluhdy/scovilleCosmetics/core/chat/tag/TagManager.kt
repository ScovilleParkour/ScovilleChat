package dev.meluhdy.scovilleCosmetics.core.chat.tag

import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.melodia.utils.FileUtils
import dev.meluhdy.melodia.utils.FileUtils.requireString
import dev.meluhdy.scoville.Scoville
import dev.meluhdy.scoville.core.course.CourseManager
import dev.meluhdy.scovilleCosmetics.ScovilleCosmetics
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.CourseTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.CourseTagSerializer
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.HiddenTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.HiddenTagSerializer
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.RankTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.RankTagSerializer
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.RecordTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.RecordTagSerializer
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.ShinyTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.ShinyTagSerializer
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.SpecialTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.SpecialTagSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import kotlin.io.path.Path

object TagManager : MelodiaSavingManager<ChatTag>() {

    private val COURSE_REGEX = Regex("[^a-z _]")

    init {
        add(RankTag("rank_owner", "&7«&f&lOwner&7»", "tag.rank.owner.desc"))
        add(RankTag("rank_admin", "&4«&c&lAdmin&4»", "tag.rank.admin.desc"))
        add(RankTag("rank_mod", "&6«&e&lMod&6»", "tag.rank.mod.desc"))
        add(RankTag("rank_helper", "&2«&a&lHelper&2»", "tag.rank.helper.desc"))
        add(RankTag("rank_builder", "&8«&7&lBuilder&8»", "tag.rank.builder.desc"))
        add(RankTag("rank_beta", "&8«&7&lBeta&8»", "tag.rank.beta.desc"))
        add(RankTag("rank_buffoon", "&4«&c&lBuffoon&4»", "tag.rank.buffoon.desc"))
        add(RankTag("rank_moron", "&6«&e&lMoron&6»", "tag.rank.moron.desc"))
        add(RankTag("rank_idiot", "&2«&a&lIdiot&2»", "tag.rank.idiot.desc"))

        add(SpecialTag("special_???_1", "&2«&a???&2»", "tag.special.???.desc"))
        add(SpecialTag("special_???_2", "&6«&e???&6»", "tag.special.???.desc"))
        add(SpecialTag("special_???_3", "&4«&c???&4»", "tag.special.???.desc"))
        add(SpecialTag("special_stone", "&7«&8⚝&7Stone&8⚝&7»", "tag.special.stone.desc"))
        add(SpecialTag("special_grass", "&a«&2⚝&aGrass&2⚝&a»", "tag.special.grass.desc"))
        add(SpecialTag("special_dirt", "&6«&e⚝&6Dirt&e⚝&6»", "tag.special.dirt.desc"))
        add(SpecialTag("special_cobble", "&7«&8⚝&7Cobble&8⚝&7»", "tag.special.cobble.desc"))
        add(SpecialTag("special_wood", "&6«&e⚝&6Wood&e⚝&6»", "tag.special.wood.desc"))
        add(SpecialTag("special_sapling", "&a«&2⚝&aSapling&2⚝&a»", "tag.special.sapling.desc"))
        add(SpecialTag("special_bedrock", "&8«&7⚝&8Bedrock&7⚝&8»", "tag.special.bedrock.desc"))
        add(SpecialTag("special_water", "&9«&1⚝&9Water&1⚝&9»", "tag.special.water.desc"))
    }

    fun get(tagId: String) = get { it.id == tagId }

    val baseFolder: String
        get() = FileUtils.getFile(ScovilleCosmetics.plugin, (ScovilleCosmetics.plugin.config as YamlConfiguration).requireString("tag_folder")).absolutePath

    override fun load() {
        super.load()

        ScovilleCosmetics.plugin.logger.debug("Adding Shiny + WR Tags")

        CourseManager.getAll().forEach {
            ScovilleCosmetics.plugin.logger.debug("Adding tag for course ${it.uuid}")
            var name = (it.name ?: it.uuid.toString()).lowercase()
            name = COURSE_REGEX.replace(name, "").replace(" ", "_")

            add(ShinyTag("s_$name", "&6«&e⁕${it.coloredName ?: "UNKNOWN"}&e⁕&6»", it.uuid))
            add(RecordTag("wr_$name", "&8«${it.coloredName} &6♚&8»", it.uuid))
        }
    }

    override val savingObjects: MutableSet<ChatTag>
        get() = getAll().filter { it is CourseTag || it is HiddenTag }.toMutableSet()

    override fun getFile(obj: ChatTag): File = Path(baseFolder, obj.type.name, "${obj.id}.json").toFile()

    override fun loadSaves(): Array<File> = File(baseFolder).walkTopDown().toCollection(ArrayList()).toTypedArray()

    override fun serializeObject(obj: ChatTag): JsonElement = when (obj.type) {
        ChatTag.TagType.RANK -> serializer.encodeToJsonElement(RankTagSerializer, obj as RankTag)
        ChatTag.TagType.SPECIAL -> serializer.encodeToJsonElement(SpecialTagSerializer, obj as SpecialTag)
        ChatTag.TagType.COURSE -> serializer.encodeToJsonElement(CourseTagSerializer, obj as CourseTag)
        ChatTag.TagType.SHINY -> serializer.encodeToJsonElement(ShinyTagSerializer, obj as ShinyTag)
        ChatTag.TagType.HIDDEN -> serializer.encodeToJsonElement(HiddenTagSerializer, obj as HiddenTag)
        ChatTag.TagType.RECORD -> serializer.encodeToJsonElement(RecordTagSerializer, obj as RecordTag)
    }

    override fun deserializeObject(jsonElement: JsonElement): ChatTag = when (ChatTag.TagType.entries[jsonElement.jsonObject["type"]!!.jsonPrimitive.int]) {
        ChatTag.TagType.RANK -> serializer.decodeFromJsonElement(RankTagSerializer, jsonElement.jsonObject)
        ChatTag.TagType.SPECIAL -> serializer.decodeFromJsonElement(SpecialTagSerializer, jsonElement.jsonObject)
        ChatTag.TagType.COURSE -> serializer.decodeFromJsonElement(CourseTagSerializer, jsonElement.jsonObject)
        ChatTag.TagType.SHINY -> serializer.decodeFromJsonElement(ShinyTagSerializer, jsonElement.jsonObject)
        ChatTag.TagType.HIDDEN -> serializer.decodeFromJsonElement(HiddenTagSerializer, jsonElement.jsonObject)
        ChatTag.TagType.RECORD -> serializer.decodeFromJsonElement(RecordTagSerializer, jsonElement.jsonObject)
    }

}