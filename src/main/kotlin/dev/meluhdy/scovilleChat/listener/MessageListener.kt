package dev.meluhdy.scovilleChat.listener

import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.melodia.utils.fromMiniMessage
import dev.meluhdy.scovilleChat.ScovilleChat
import dev.meluhdy.scovilleChat.core.ChatModifier
import dev.meluhdy.scovilleChat.core.modifiers.ArrowsModifier
import dev.meluhdy.scovilleChat.core.modifiers.ConnectionState
import dev.meluhdy.scovilleChat.core.modifiers.RankModifier
import dev.meluhdy.scovilleChat.core.modifiers.StaffModifier
import dev.meluhdy.scovilleChat.core.player.PlayerMessageSettingsManager
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object MessageListener : Listener {

    fun sendConnectionMessage(p: Player, state: ConnectionState) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(ScovilleChat.plugin, {
            val playerSettings = PlayerMessageSettingsManager.getOrCreate(p)
            TextUtils.broadcastChat(ScovilleChat.plugin, playerSettings.connectionMessage.getTranslationString(
                state), p.name)
        }, 5L)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage("".fromMiniMessage())
        this.sendConnectionMessage(event.player, ConnectionState.JOIN)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage("".fromMiniMessage())
        this.sendConnectionMessage(event.player, ConnectionState.LEAVE)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun handleMessage(event: AsyncChatEvent) {
        val message = PlainTextComponentSerializer.plainText().serialize(event.originalMessage())
        val player = event.player
        val settings = PlayerMessageSettingsManager.getOrCreate(player)

        // Message Order: Tag, Rankup, Nickname, Side Rankup, Star, Arrows, Chat Color, Message

        val messageArray = sortedMapOf<ChatModifier.ChatModifiers, String>()

        val modifiers: List<ChatModifier?> = listOf(
            settings.nickname,
            ArrowsModifier,
            RankModifier,
            StaffModifier,
            settings.chatColor
        )

        modifiers.forEach { modifier: ChatModifier? ->
            modifier?.apply(player, messageArray)
        }

        val preamble = messageArray.values.joinToString("")
        TextUtils.broadcastChat(preamble + message)
        event.isCancelled = true
    }

}