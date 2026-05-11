package dev.meluhdy.scovilleCosmetics.core.chat.tag.type.rank

import dev.meluhdy.melodia.manager.MelodiaManager
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import java.util.UUID

class RankTag(id: String, tag: String, val descTransId: String, uuid: UUID = UUID.randomUUID()) : ChatTag(id, tag, TagType.RANK, uuid)

object RankTagManager : MelodiaManager<RankTag>() {

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
    }

    fun get(tagId: String) = get { it.id == tagId }

}