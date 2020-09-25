object Math {
    fun abs(num: Int): Int {
        return if (num < 0) num * -1 else num
    }

    fun abs(num: Double): Double {
        return if (num < 0) num * -1.0 else num
    }
}
