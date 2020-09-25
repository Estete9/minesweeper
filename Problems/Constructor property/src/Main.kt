fun main() {
    val timerValue = readLine()!!.toInt()
    val timer = ByteTimer(timerValue)
    println(timer.defaultTime)
}

data class ByteTimer(var time: Int) {
    var defaultTime = time
        get() = field.coerceIn(-128, 127)

}