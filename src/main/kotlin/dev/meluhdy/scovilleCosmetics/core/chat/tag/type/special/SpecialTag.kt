package dev.meluhdy.scovilleCosmetics.core.chat.tag.type.special

import dev.meluhdy.melodia.manager.MelodiaManager
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.type.rank.RankTagManager
import java.util.UUID

class SpecialTag(id: String, tag: String, val descTransId: String, uuid: UUID = UUID.randomUUID()) : ChatTag(id, tag, TagType.SPECIAL, uuid)

object SpecialTagManager : MelodiaManager<SpecialTag>() {

    init {
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

    fun get(tagId: String) = RankTagManager.get { it.id == tagId }

}