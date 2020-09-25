import java.util.Scanner

enum class Rainbow(val colorName: String) {
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    INDIGO("indigo"),
    VIOLET("violet");

    companion object {
        fun isInRainbow(colorInput: String): Boolean {
            for (enum in values()) {
                if (enum.colorName == colorInput) return true
            }
            return false
        }
    }
}

fun main() {
    val input = Scanner(System.`in`)
    println(Rainbow.isInRainbow(input.next()))
}
