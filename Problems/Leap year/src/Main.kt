import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    isLeapYear(scanner.nextInt())
}

fun isLeapYear(year: Int) {
    if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
        println("Leap")
    } else println("Regular")
}