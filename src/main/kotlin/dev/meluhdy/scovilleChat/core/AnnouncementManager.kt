package dev.meluhdy.scovilleChat.core

import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.melodia.utils.fromLegacyMessage
import dev.meluhdy.scovilleChat.ScovilleChat
import org.bukkit.Bukkit
import kotlin.random.Random

object AnnouncementManager {

    fun announce() {
        var random: Int = -1
        Bukkit.getOnlinePlayers().forEach {
            val messages = TextUtils.getTranslatedStringList(ScovilleChat.plugin, "chat.announcement", it.locale())
            if (random == -1) {
                random = Random.nextInt(0, messages.size)
            }
            val message = messages[random]
            it.sendMessage(TextUtils.translate(ScovilleChat.plugin, message.id, it.locale(), message.args).fromLegacyMessage())
        }
    }

    fun startTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ScovilleChat.plugin, Runnable { announce() }, 0L, 20 * 60 * 5)
    }

}