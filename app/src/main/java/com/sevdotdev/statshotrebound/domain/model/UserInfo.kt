package com.sevdotdev.statshotrebound.domain.model

data class UserInfo(
    val userDisplayName: String,
    val userGameId: String,
    val userPlayType: String? = "Default"
) {
    fun addPlayType(block: () -> String): UserInfo =
        this.copy(
            userPlayType = block()
        )
    companion object {
        val NO_USER_IDENTIFIED_YET = UserInfo(
            "Slapper" + System.currentTimeMillis()/1000 * Math.random(),
            ""
        )
    }
}
