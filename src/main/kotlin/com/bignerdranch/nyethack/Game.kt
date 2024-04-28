package com.bignerdranch.nyethack

fun main(args: Array<String>) {

    //starts game loop
    Game.play()

}

object Game {
    private val player =Player("Madrigal")
    private val currentRoom: Room = TownSquare()

    init {
        println("Welcome, adventurer")
        player.castFireball()
    }
    fun play() {
        while (true){
            println(currentRoom.description())
            println(currentRoom.load())

            //player Status
            printPlayerStatus(player)

            //command from player
            print("> enter command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val arguments = input.split(" ").getOrElse(1, { "" })

        private fun commandNotFound() = "I'm not quite sure what you mean by $command"
        fun processCommand() = when (command.toLowerCase()) {
            else -> commandNotFound()
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} " + "${player.formatHealthStatus()}")
    }

}