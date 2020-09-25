data class City(val name: String, val defaultTemp: Int = 0) {
    var degrees = 0
        set(value) {
            field = if (value < -92 || value > 57) defaultTemp else value
        }

}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai", 30)
    firstCity.degrees = first
    val secondCity = City("Moscow", 5)
    secondCity.degrees = second
    val thirdCity = City("Hanoi", 20)
    thirdCity.degrees = third

    if (firstCity.degrees == secondCity.degrees ||
            secondCity.degrees == thirdCity.degrees ||
            firstCity.degrees == thirdCity.degrees) {
        print("neither")
    } else {
        when {
            firstCity.degrees < secondCity.degrees
                    && firstCity.degrees < thirdCity.degrees -> print(firstCity.name)
            secondCity.degrees < firstCity.degrees
                    && secondCity.degrees < thirdCity.degrees -> print(secondCity.name)
            thirdCity.degrees < secondCity.degrees
                    && thirdCity.degrees < firstCity.degrees -> print(thirdCity.name)
        }
    }
}
