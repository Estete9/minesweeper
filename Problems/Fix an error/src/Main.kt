class Player(val id: Int, val name: String) {
    companion object {
        val role = "playable character"
        fun getInfo() = "$id, $name, $role"
    }
}

fun getPlayerInfo(player: Player) = // TODO