package dev.meluhdy.scovilleCosmetics.misc

import dev.meluhdy.melodia.utils.BlockDataUtils.getFromTag
import dev.meluhdy.melodia.utils.BlockDataUtils.setTag
import dev.meluhdy.scoville.Scoville
import dev.meluhdy.scoville.misc.ScovilleConstants
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.TagManager
import org.bukkit.block.Block
import java.util.UUID

fun Block.getScovilleTagId(): String? = this.getFromTag(Scoville.plugin, ScovilleConstants.TAG_KEY)
fun Block.getScovilleTag(): ChatTag? = this.getScovilleTagId()?.let { TagManager.get(it) }
fun Block.setScovilleTag(tag: ChatTag) = this.setScovilleTag(tag.id)
fun Block.setScovilleTag(tagId: String) = this.setTag(Scoville.plugin, ScovilleConstants.TAG_KEY, tagId)