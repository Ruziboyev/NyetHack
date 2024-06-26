package com.bignerdranch.nyethack

import java.io.File

class Player(
    _name: String,
    var healthPoints: Int = 100,
    val isBlessed: Boolean,
    private val isImmortal: Boolean
) {
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy { selectHomeTown() }
    var currentPosition = Coordinate(0, 0)

    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero" })
        require(name.isNotBlank(), { "Player must have a name" })
    }

    constructor(name: String) : this(
        name,
        isBlessed = true,
        isImmortal = false
    ) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }




    /*
    * prints health status
     */
    fun formatHealthStatus() = when (healthPoints) {
        100 -> "is in excellent condition"
        in 90..99 -> "has some scratches"
        in 75..89 -> if (isBlessed) {
            "has some minor wounds, but is healing quite quickly!"
        } else {
            "has some minor wounds"
        }

        in 15..74 -> "looks pretty hurt"
        else -> "is in awful condition"
    }


    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "Green" else "NONE"
        return auraColor
    }


    fun castFireball(numberFireballs: Int = 2) {
        println("A glass of Fireball springs into existence. (x$numberFireballs)")
    }


    private fun selectHomeTown() = File("src/main/data/towns.txt").readText().split("\n").random()

}