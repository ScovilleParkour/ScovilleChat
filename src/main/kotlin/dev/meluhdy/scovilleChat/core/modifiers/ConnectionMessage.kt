package dev.meluhdy.scovilleChat.core.modifiers

enum class ConnectionState(val value: String) {
    JOIN    ("join"),
    LEAVE   ("leave")
}

enum class ConnectionMessages(val translationID: String) {
    DEFAULT     ("default"),
    MR_COOL     ("mr_cool"),
    SHAKESPEARE ("shakespeare"),
    SUPERHERO   ("superhero"),
    AVIATION    ("aviation"),
    MONARCH     ("monarch"),
    REVERSED    ("reversed"),
    STINKY      ("stinky");

    fun getTranslationString(connectionState: ConnectionState): String = "chat.connection.${this.translationID}.${connectionState.value}"
}