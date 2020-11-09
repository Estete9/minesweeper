class EspressoMachine {
    val costPerServing: Float

    constructor(coffeeCapsulesCount: Int, totalCost: Float) {
        this.costPerServing = totalCost / coffeeCapsulesCount.toFloat()
    }

    constructor(coffeeBeansWeight: Float, totalCost: Float) {
        val noOfCups = (coffeeBeansWeight / 10.6).toFloat()
        this.costPerServing = totalCost / noOfCups
    }
}