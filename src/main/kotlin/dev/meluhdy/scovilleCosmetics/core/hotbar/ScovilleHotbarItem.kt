package dev.meluhdy.scovilleCosmetics.core.hotbar

import dev.meluhdy.melodia.misc.hotbar.HotbarItem
import dev.meluhdy.melodia.utils.ItemUtils
import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.melodia.utils.fromLegacyMessage
import dev.meluhdy.scovilleCosmetics.ScovilleCosmetics
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class ScovilleHotbarItem : HotbarItem {

    constructor(p: Player, item: ItemStack, color: Char, title: String) : super(ItemUtils.modifyItem(
        item, "&$color${TextUtils.translate(ScovilleCosmetics.plugin, title, p.locale())}".fromLegacyMessage()
    ))

}