package dev.meluhdy.scovilleCosmetics.core.hotbar.item

import dev.meluhdy.melodia.utils.sendMessage
import dev.meluhdy.scoville.Scoville
import dev.meluhdy.scoville.core.parkourer.ParkourerManager
import dev.meluhdy.scoville.event.event.GotoCheckpointEvent
import dev.meluhdy.scovilleCosmetics.core.hotbar.ScovilleHotbarItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CheckpointItem(p: Player, item: ItemStack, color: Char) : ScovilleHotbarItem(p, item, color, "item.hotbar.checkpoint.title") {

    override fun onClick(p: Player) {
        val parkourer = ParkourerManager.get(p) ?: return
        val course = parkourer.getPlayingCourse()
        if (course == null) {
            p.sendMessage(Scoville.plugin, "chat.checkpoint.not_playing")
            return
        }
        val checkpoint = parkourer.getCheckpoint(course)
        if (checkpoint == null) {
            p.sendMessage(Scoville.plugin, "chat.checkpoint.no_cp")
            return
        }
        GotoCheckpointEvent(p, checkpoint).callEvent()
    }

}