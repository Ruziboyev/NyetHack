package com.bignerdranch.nyethack

import java.io.File

const val TAVERN_NAME = "Temur's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniqePatrons = mutableSetOf<String>()
val menuList = File("src/main/data/tavern-menu-items.txt")
    .readText()
    .split("\n")
val patronGold = mutableMapOf<String, Double>()

fun main(args: Array<String>) {

    generatePatrons()
    generateOrder()
    displayPatronBalance()

} //main yopuvchi

private fun generateOrder() {
    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniqePatrons.shuffled().first(),
            menuList.shuffled().first()
        )
        orderCount++
    }
}

private fun generatePatrons() {
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniqePatrons += name
    }
    uniqePatrons.forEach {
        patronGold[it] = 6.0
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "u" -> "|_|"
            else -> it.value
        }
    }


private fun placeOrder(patronName: String, menuData: String) {
    val indexOfAppostrophe = TAVERN_NAME.indexOf("\'")
    val tavernMaster = TAVERN_NAME.substring(0..indexOfAppostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(",")
    val message = "$patronName buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicios $name")}"
    } else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

private fun displayPatronBalance() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}