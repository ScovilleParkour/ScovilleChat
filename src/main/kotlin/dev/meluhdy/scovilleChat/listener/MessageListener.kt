package dev.meluhdy.scovilleChat.listener

import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.melodia.utils.fromMiniMessage
import dev.meluhdy.scovilleChat.ScovilleChat
import dev.meluhdy.scovilleChat.core.player.PlayerMessageSettings
import dev.meluhdy.scovilleChat.core.player.PlayerMessageSettingsManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLocaleChangeEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.UUID

object MessageListener : Listener {

    fun sendConnectionMessage(p: Player, state: PlayerMessageSettings.ConnectionState) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(ScovilleChat.plugin, {
            val playerSettings = PlayerMessageSettingsManager.getOrCreate(p)
            TextUtils.broadcastChat(ScovilleChat.plugin, playerSettings.connectionMessage.getTranslationString(
                state), p.name)
        }, 5L)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage("".fromMiniMessage())
        this.sendConnectionMessage(event.player, PlayerMessageSettings.ConnectionState.JOIN)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage("".fromMiniMessage())
        this.sendConnectionMessage(event.player, PlayerMessageSettings.ConnectionState.LEAVE)
    }

}