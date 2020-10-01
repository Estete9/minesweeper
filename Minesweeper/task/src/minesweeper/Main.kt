package minesweeper

import java.io.InputStream
import java.util.*
import kotlin.collections.HashSet
import kotlin.random.Random

// constant size for the grid
const val size = 9

class MineArray {
    //    fun that takes an input called inputStream and returns the number of mines
    fun getMineCount(numberOfMines: InputStream): Int {
        print("How many mines do you want on the field? ")
        return Scanner(numberOfMines).nextInt()
    }

    //    fun that takes the number of mines and creates an IntArray called mineArray using a random number and repeating until the random number isn't in the mineArray
    //    this random number is then added to the mineArray, this random is where the bomb will go
    fun makeMineArray(mineCount: Int): IntArray {
        val mineArray = IntArray(mineCount)
        var random: Int
        for (i in 0 until mineCount) {
            do {
                random = Random.nextInt(size * size)
            } while (random in mineArray)
            mineArray[i] = random
        }
        return mineArray
    }
}

//enum class that contains 3 different types of cells
enum class Cell(var content: String, var cellNum: Int, var isBomb: Boolean) {
    NUMBER_CELL("0", 0, false),
    BOMB_CELL(".", 0, true),
    SAFE_CELL(".", 0, false);
}

object Grid {
    //receives user input and calculate the cellNum

    private fun getCellNum(row: Int, column: Int) = column * size + row

    //    creates the game's grid with the size of the board squared and sets values default to "Cell.SAFE_CELL"
    val fullGrid: Array<Cell> = Array(size * size) { Cell.SAFE_CELL }

    //    makes and stores the grid into gridArray
    fun makeGrid(mineArray: IntArray): Array<Cell> {

//        iterates through mineArray, creates a bomb mine, updates it's cellNum and adds it to the fullGrid
//            then it checks if the cells around the mine index exists, if the cells around are not on the first column or last column
//            and if the cell is not a bomb
//                while it also checks if the surrounding cells are safe cells so it can change them to a number cells or
//                number cells so they can increase they're content
        for (i in mineArray) {
            val cell = Cell.BOMB_CELL
            cell.cellNum = i
            fullGrid[i] = cell
            if (fullGrid.getOrNull(i - 1) != null && i % 9 != 0 && !fullGrid[i - 1].isBomb) {

                if (fullGrid[i - 1].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i - 1
                    fullGrid[i - 1] = prevCell
                    fullGrid[i - 1].content = "1"
                } else {
                    fullGrid[i - 1].content = (fullGrid[i - 1].content.toInt() + 1).toString()
                }
            }

            if (fullGrid.getOrNull(i - 10) != null && i % 9 != 0 && !fullGrid[i - 10].isBomb) {
                if (fullGrid[i - 10].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i - 10
                    fullGrid[i - 10] = prevCell
                    fullGrid[i - 10].content = "1"
                } else {
                    fullGrid[i - 10].content = (fullGrid[i - 10].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i - 9) != null && !fullGrid[i - 9].isBomb) {
                if (fullGrid[i - 9].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i - 9
                    fullGrid[i - 9] = prevCell
                    fullGrid[i - 9].content = "1"
                } else {
                    fullGrid[i - 9].content = (fullGrid[i - 9].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i - 8) != null && (i + 1) % 9 != 0 && !fullGrid[i - 8].isBomb) {
                if (fullGrid[i - 8].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i - 8
                    fullGrid[i - 8] = prevCell
                    fullGrid[i - 8].content = "1"
                } else {
                    fullGrid[i - 8].content = (fullGrid[i - 8].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i + 1) != null && (i + 1) % 9 != 0 && !fullGrid[i + 1].isBomb) {
                if (fullGrid[i + 1].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i + 1
                    fullGrid[i + 1] = prevCell
                    fullGrid[i + 1].content = "1"
                } else {
                    fullGrid[i + 1].content = (fullGrid[i + 1].content.toInt() + 1).toString()
                }
            }
            if (fullGrid.getOrNull(i + 10) != null && (i + 1) % 9 != 0 && !fullGrid[i + 10].isBomb) {
                if (fullGrid[i + 10].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i + 10
                    fullGrid[i + 10] = prevCell
                    fullGrid[i + 10].content = "1"
                } else {
                    fullGrid[i + 10].content = (fullGrid[i + 10].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i + 9) != null && !fullGrid[i + 9].isBomb) {
                if (fullGrid[i + 9].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i + 9
                    fullGrid[i + 9] = prevCell
                    fullGrid[i + 9].content = "1"
                } else {
                    fullGrid[i + 9].content = (fullGrid[i + 9].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i + 8) != null && i % 9 != 0 && !fullGrid[i + 8].isBomb) {
                if (fullGrid[i + 8].name == "SAFE_CELL") {
                    val prevCell = Cell.NUMBER_CELL
                    prevCell.cellNum = i + 8
                    fullGrid[i + 8] = prevCell
                    fullGrid[i + 8].content = "1"
                } else {
                    fullGrid[i + 8].content = (fullGrid[i + 8].content.toInt() + 1).toString()
                }

            }
        }
        return fullGrid
    }

    //fun that for each row prints the grid's row
    fun printGrid(fullGrid: Array<Cell>) {
//        prints the column numbers border
        print(" |")
        for (i in 1..size) print(i)
        println("|")
//        prints the top separating border
        print("-|")
        for (i in 1..size) print("-")
        println("|")
//        prints the grid
        for (i in 0 until size) {
//            prints the row numbers
            print("${i + 1}|")
            for (j in 0 until size) {
                print(fullGrid[i * size + j].content)
            }
            println("|")
        }
//        prints the bottom separating border
        print("-|")
        for (i in 1..size) print("-")
        println("|")
    }

    private fun getMineSetting(): Int {
        println("Set/delete mines marks (x and y coordinates):")
        val scanner = Scanner(System.`in`)
        return getCellNum(scanner.nextInt() - 1, scanner.nextInt() - 1)
    }

    fun changeGrid(mineArray: IntArray) {
        val playerMines = mutableListOf<Int>()

        do {
            val playerChoice = getMineSetting()
            when {
                /*grid.*/fullGrid[playerChoice].content != "." && fullGrid[playerChoice].content != "*" -> {
                println("There is a number here!")
                continue
            }
                fullGrid[playerChoice].content == "." -> {
                fullGrid[playerChoice].content = fullGrid[playerChoice].content.replace(".", "*")
            }
                fullGrid[playerChoice].content == "*" -> {
                fullGrid[playerChoice].content = fullGrid[playerChoice].content.replace("*", ".")
            }
            }
            if (playerMines.contains(playerChoice)) {
                playerMines.remove(playerChoice)
            } else {
                playerMines.add(playerChoice)
            }
            printGrid(fullGrid)

        } while (HashSet(mineArray.asList()) != HashSet(playerMines))
        println("Congratulations! You found all the mines!")
    }
}

fun main() {
    val mineCount = MineArray().getMineCount(System.`in`)
    val mineArray = MineArray().makeMineArray(mineCount)
    Grid.makeGrid(mineArray)
    Grid.printGrid(Grid.fullGrid)
    Grid.changeGrid(mineArray)

}
