package minesweeper

import java.io.InputStream
import java.util.*
import kotlin.random.Random

// constant size for the grid
const val size = 9

class MineArray() {
    //    fun that takes an input called inputStream and returns the number of mines
    fun getMineCount(inputStream: InputStream): Int {
        print("How many mines do you want on the field? ")
        return Scanner(inputStream).nextInt()
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

class Cell() {
    var cellNum = 0
    var isBomb = false

}

class Grid() {
    //    creates the game's grid with the size of the board squared and sets values default to "."
    val fullGrid = Array(size * size) { "." }
    fun makeGrid(mineArray: IntArray): Array<String> {
//    makes and stores the grid into gridArray

        for (i in 0 until size) {
            for (j in 0 until size) {
//                creates a cell
                val cell = Cell()
//                calculates the cellNum
                cell.cellNum = i * size + j
//                checks if the cellNum is in the mine array to change the cell from the default "." to "X"
                if (cell.cellNum in mineArray) {
                    fullGrid[cell.cellNum] = "X"
                }
//                in the loop goes by every mine "X" and modifies the cells around it
                if (fullGrid[cell.cellNum] == "X") {
//                    checks if the surrounding cell exists using getOrNull
//                    checks that when the column is 0 the cell modification doesn't take place because the bomb won't modify the cell 1 row up
                    if (fullGrid.getOrNull(cell.cellNum - 1) != null && j != 0) {
//                        checks that the cell to be modified isn't a mine
                        if (fullGrid[cell.cellNum - 1] != "X") {
//                            checks that if the cell to be modified is "." changes to 1, otherwise changes to Int and then increases by 1 then returns to String
                            if (fullGrid[cell.cellNum - 1] == ".") {
                                fullGrid[cell.cellNum - 1] = "1"
                            } else {
                                fullGrid[cell.cellNum - 1] =
                                        (fullGrid[cell.cellNum - 1].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
//                    checks that when the column is 0 the cell modification doesn't take place because the bomb won't modify the cell two rows up
                    if (fullGrid.getOrNull(cell.cellNum - 10) != null && j != 0) {
                        if (fullGrid[cell.cellNum - 10] != "X") {
                            if (fullGrid[cell.cellNum - 10] == ".") {
                                fullGrid[cell.cellNum - 10] = "1"
                            } else {
                                fullGrid[cell.cellNum - 10] =
                                        (fullGrid[cell.cellNum - 10].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
                    if (fullGrid.getOrNull(cell.cellNum - 9) != null) {
                        if (fullGrid[cell.cellNum - 9] != "X") {
                            if (fullGrid[cell.cellNum - 9] == ".") {
                                fullGrid[cell.cellNum - 9] = "1"
                            } else {
                                fullGrid[cell.cellNum - 9] =
                                        (fullGrid[cell.cellNum - 9].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
//                    checks that when the column is size - 1 (8 in this case) the cell modification doesn't take place because the bomb won't modify the first cell of the same row
                    if (fullGrid.getOrNull(cell.cellNum - 8) != null && j != size - 1) {
                        if (fullGrid[cell.cellNum - 8] != "X") {
                            if (fullGrid[cell.cellNum - 8] == ".") {
                                fullGrid[cell.cellNum - 8] = "1"
                            } else {
                                fullGrid[cell.cellNum - 8] =
                                        (fullGrid[cell.cellNum - 8].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
//                    checks that when the column is size - 1 (8) the cell modification doesn't take place because the bomb won't modify the cell one row below
                    if (fullGrid.getOrNull(cell.cellNum + 1) != null && j != size - 1) {
                        if (fullGrid[cell.cellNum + 1] != "X") {
                            if (fullGrid[cell.cellNum + 1] == ".") {
                                fullGrid[cell.cellNum + 1] = "1"
                            } else {
                                fullGrid[cell.cellNum + 1] =
                                        (fullGrid[cell.cellNum + 1].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
//                    checks that when the column is 0 the cell modification doesn't take place because the bomb won't modify the last cell of the same row
                    if (fullGrid.getOrNull(cell.cellNum + 8) != null && j != 0) {
                        if (fullGrid[cell.cellNum + 8] != "X") {
                            if (fullGrid[cell.cellNum + 8] == ".") {
                                fullGrid[cell.cellNum + 8] = "1"
                            } else {
                                fullGrid[cell.cellNum + 8] =
                                        (fullGrid[cell.cellNum + 8].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
                    if (fullGrid.getOrNull(cell.cellNum + 9) != null) {
                        if (fullGrid[cell.cellNum + 9] != "X") {
                            if (fullGrid[cell.cellNum + 9] == ".") {
                                fullGrid[cell.cellNum + 9] = "1"
                            } else {
                                fullGrid[cell.cellNum + 9] =
                                        (fullGrid[cell.cellNum + 9].toInt() + 1).toString()
                            }
                        }
                    }
//                    checks if the surrounding cell exists using getOrNull
//                    checks that when the column is size - 1 the cell modification doesn't take place because the bomb won't modify the cell two rows bellow
                    if (fullGrid.getOrNull(cell.cellNum + 10) != null && j != size - 1) {
                        if (fullGrid[cell.cellNum + 10] != "X") {
                            if (fullGrid[cell.cellNum + 10] == ".") {
                                fullGrid[cell.cellNum + 10] = "1"
                            } else {
                                fullGrid[cell.cellNum + 10] =
                                        (fullGrid[cell.cellNum + 10].toInt() + 1).toString()
                            }
                        }
                    }
                }
            }
        }

        return fullGrid
    }

    //fun that for each row prints the grid's row
    fun printGrid(fullGrid: Array<String>) {
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
                print(fullGrid[i * size + j])
            }
            println("|")
        }
//        prints the bottom separating border
        print("-|")
        for (i in 1..size) print("-")
        println("|")
    }
}

fun main() {

    val mineCount = MineArray().getMineCount(System.`in`)
    val mineArray = MineArray().makeMineArray(mineCount)
    val grid = Grid()
    grid.makeGrid(mineArray)
    grid.printGrid(grid.fullGrid)
}

