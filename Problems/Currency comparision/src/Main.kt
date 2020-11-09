import java.util.Scanner

enum class Currencies(val country: String, val currency: String) {
    GERMANY("Germany", "Euro"),
    MALI("Mali", "CFA franc"),
    DOMINICA("Dominica", "Eastern Caribbean dollar"),
    CANADA("Canada", "Canadian dollar"),
    SPAIN("Spain", "Euro"),
    AUSTRALIA("Australia", "Australian dollar"),
    BRAZIL("Brazil", "Brazilian real"),
    SENEGAL("Senegal", "CFA franc"),
    FRANCE("France", "Euro"),
    GRENADA("Grenada", "Eastern Caribbean dollar"),
    KIRIBATI("Kiribati", "Australian dollar"),
    NULL("false", "");

    companion object {

        fun containsCountry(country: String): Boolean {
            return values().map { it.name }.contains(country.toUpperCase())
        }

        fun compareCountries(country1: String, country2: String): Boolean {
            return containsCountry(country1) && containsCountry(country2) &&
                    valueOf(country1.toUpperCase()).currency ==
                    valueOf(country2.toUpperCase()).currency
        }

        inline fun <reified T : Enum<T>> enumValueOfOrNull(country: String): T? {
            return enumValues<T>().find { it.name == country.toUpperCase() }
        }
//
//        fun compare(country1: String, country2: String): Boolean {
//
//
////            for (enum in values()) {
//
//            return if (enumValueOfOrNull<Currencies>(country1)?.currency == enumValueOfOrNull<Currencies>(country2)?.currency) {
//                valueOf(country1.toUpperCase()).currency == valueOf(country2.toUpperCase()).currency
//            } else false
//
////                if (enum.country == country1) {
////                    for (enum2 in values()) {
////                        if (enum2.country == country2) {
////                            return enum.currency == enum2.currency
////                        }
////                    }
////                }
////            }
////            return false
//        }
    }
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
//    println(Currencies.compare(input.next(), input.next()))
    println(Currencies.compareCountries(input.next(), input.next()))
//    println(Currencies.valueOf("GERMANY").name)
}
