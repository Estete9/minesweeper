class IceCreamOrder {
    val price: Int

    constructor(popsicles: Int) {
        this.price = popsicles * 7
    }

    constructor(scoops: Int, toppings: Int) {
        this.price = scoops * 5 + toppings * 2
    }
}