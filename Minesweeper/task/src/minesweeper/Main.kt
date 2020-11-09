package minesweeper

import java.io.InputStream
import java.util.*
import kotlin.collections.HashSet
import kotlin.random.Random

//import kotlin.random.Random

// constant size for the grid
const val size = 9
var isThereMine = false

class MineArray {
    //    fun that takes an input called inputStream and returns the number of mines
    fun getMineCount(numberOfMines: InputStream): Int {
        print("How many mines do you want on the field? ")
        return Scanner(numberOfMines).nextInt()
    }

    //    fun that takes the number of mines and creates an IntArray called mineArray using a random number and repeating until the random number isn't in the mineArray and isn't in the vicinity
    //    of the player choice
    //    this random number is then added to the mineArray, this random is where the bomb will go

    fun makeMineArray(mineCount: Int, playerChoice: Int): IntArray {
        var random: Int
        val isThereSpace = mineCount < 72
        val preMineArray = IntArray(mineCount)
        for (i in 0 until mineCount) {
            do {
                random = Random.nextInt(size * size)

            } while (random in preMineArray || random == playerChoice || isThereSpace && (
                            random in playerChoice - 1..playerChoice + 1 ||
                                    random == playerChoice + 10 || random == playerChoice - 10 ||
                                    random == playerChoice + 9 || random == playerChoice - 9 ||
                                    random == playerChoice + 8 || random == playerChoice - 8))
            preMineArray[i] = random
        }

        val mineArray = preMineArray.sorted().toIntArray()
//       for loop to check each mine and checks if there are mines diagonally, then calls the startMineRelocation Fun.
        for (minePos in mineArray) {
            val minePosX = minePos / size
            val minePosY = minePos - (minePos / size) * size

            if (minePosY < 6 && mineArray.contains(minePos + 30)) {
                val direction = "downRight"
                startMineRelocation(minePosX, minePosY, minePos, mineArray, direction)
            }
            if (minePosY > 2 && mineArray.contains(minePos + 24)) {
                val direction = "downLeft"
                startMineRelocation(minePosX, minePosY, minePos, mineArray, direction)
            }
            if (minePosY < 6 && mineArray.contains(minePos - 24)) {
                val direction = "upRight"
                startMineRelocation(minePosX, minePosY, minePos, mineArray, direction)
            }
            if (minePosY > 2 && mineArray.contains(minePos - 30)) {
                val direction = "upLeft"
                startMineRelocation(minePosX, minePosY, minePos, mineArray, direction)
            }
        }
        return mineArray
    }

}
// this fun runs the corresponding fun according to the position of the other mine
fun startMineRelocation(currentMinePosX: Int, currentMinePosY: Int, originalMinePos: Int, mineArray: IntArray, direction: String) {
    val startCheck = currentMinePosX * size + currentMinePosY
    val checkedMines = mutableMapOf<Int, Boolean>()
    for (i in 0 until size * size) checkedMines[i] = true
    if (direction == "downRight") relocateMineDownRight(currentMinePosX, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
    isThereMine = false
    if (direction == "downLeft") relocateMineDownLeft(currentMinePosX, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
    isThereMine = false
    if (direction == "upRight") relocateMineUpRight(currentMinePosX, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
    isThereMine = false
    if (direction == "upLeft") relocateMineUpLeft(currentMinePosX, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
    isThereMine = false
}

fun relocateMineDownRight(currentMinePosX: Int, currentMinePosY: Int, originalMinePos: Int, startCheck: Int, mineArray: IntArray, checkedMines: MutableMap<Int, Boolean>) {
    val originalX = originalMinePos / size
    val originalY = originalMinePos - (originalMinePos / size) * size
    val currentPos = currentMinePosX * size + currentMinePosY

    if (mineArray.contains(originalMinePos + 30) && originalY < 6) {
        if (isThereMine) return
        if (checkedMines[currentPos] == false) return
        if (currentMinePosX < originalX || currentMinePosX >= originalX + 4 || currentMinePosY < originalY || currentMinePosY >= originalY + 4) return
        if (mineArray.contains(currentPos) && currentPos != originalMinePos && currentPos != originalMinePos + 30) {
            isThereMine = true
            checkedMines[currentPos] = false
            return
        }
        checkedMines[currentPos] = false

        if (!isThereMine) relocateMineDownRight(currentMinePosX + 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineDownRight(currentMinePosX - 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineDownRight(currentMinePosX, currentMinePosY + 1, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineDownRight(currentMinePosX, currentMinePosY - 1, originalMinePos, startCheck, mineArray, checkedMines)

        if (!isThereMine && mineArray.contains(originalMinePos) && currentPos != originalMinePos) {
            mineArray[mineArray.indexOf(originalMinePos)] = originalMinePos + 1
        }
    }
}

fun relocateMineDownLeft(currentMinePosX: Int, currentMinePosY: Int, originalMinePos: Int, startCheck: Int, mineArray: IntArray, checkedMines: MutableMap<Int, Boolean>) {

    val originalX = originalMinePos / size
    val originalY = originalMinePos - (originalMinePos / size) * size
    val currentPos = currentMinePosX * size + currentMinePosY

    if (mineArray.contains(originalMinePos + 24) && originalY > 2) {
        if (isThereMine) return
        if (checkedMines[currentPos] == false) return
        if (currentMinePosX < originalX || currentMinePosX >= originalX + 4 || currentMinePosY > originalY || currentMinePosY <= originalY - 4) return
        if (mineArray.contains(currentPos) && currentPos != originalMinePos && currentPos != originalMinePos + 24) {
            isThereMine = true
            checkedMines[currentPos] = false
            return
        }
        checkedMines[currentPos] = false

        if (!isThereMine) relocateMineDownLeft(currentMinePosX + 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineDownLeft(currentMinePosX - 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineDownLeft(currentMinePosX, currentMinePosY + 1, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineDownLeft(currentMinePosX, currentMinePosY - 1, originalMinePos, startCheck, mineArray, checkedMines)

        if (!isThereMine && mineArray.contains(originalMinePos) && currentPos != originalMinePos) {
            mineArray[mineArray.indexOf(originalMinePos)] = originalMinePos - 1

        }

    }
}

fun relocateMineUpRight(currentMinePosX: Int, currentMinePosY: Int, originalMinePos: Int, startCheck: Int, mineArray: IntArray, checkedMines: MutableMap<Int, Boolean>) {
    val originalX = originalMinePos / size
    val originalY = originalMinePos - (originalMinePos / size) * size
    val currentPos = currentMinePosX * size + currentMinePosY

    if (mineArray.contains(originalMinePos - 24) && originalY < 6) {
        if (isThereMine) return
        if (checkedMines[currentPos] == false) return
        if (currentMinePosX > originalX || currentMinePosX <= originalX - 4 || currentMinePosY < originalY || currentMinePosY >= originalY + 4) return
        if (mineArray.contains(currentPos) && currentPos != originalMinePos && currentPos != originalMinePos - 24) {
            isThereMine = true
            checkedMines[currentPos] = false
            return
        }
        checkedMines[currentPos] = false

        if (!isThereMine) relocateMineUpRight(currentMinePosX + 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineUpRight(currentMinePosX - 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineUpRight(currentMinePosX, currentMinePosY + 1, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineUpRight(currentMinePosX, currentMinePosY - 1, originalMinePos, startCheck, mineArray, checkedMines)

        if (!isThereMine && mineArray.contains(originalMinePos) && currentPos != originalMinePos) {
            mineArray[mineArray.indexOf(originalMinePos)] = originalMinePos + 1

        }
    }
}

fun relocateMineUpLeft(currentMinePosX: Int, currentMinePosY: Int, originalMinePos: Int, startCheck: Int, mineArray: IntArray, checkedMines: MutableMap<Int, Boolean>) {
    val originalX = originalMinePos / size
    val originalY = originalMinePos - (originalMinePos / size) * size
    val currentPos = currentMinePosX * size + currentMinePosY

    if (mineArray.contains(originalMinePos - 30) && originalY > 2) {
        if (isThereMine) return
        if (checkedMines[currentPos] == false) return
        if (currentMinePosX > originalX || currentMinePosX <= originalX - 4 || currentMinePosY > originalY || currentMinePosY <= originalY - 4) return
        if (mineArray.contains(currentPos) && currentPos != originalMinePos && currentPos != originalMinePos - 30) {
            isThereMine = true
            checkedMines[currentPos] = false
            return
        }

        checkedMines[currentPos] = false

        if (!isThereMine) relocateMineUpLeft(currentMinePosX + 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineUpLeft(currentMinePosX - 1, currentMinePosY, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineUpLeft(currentMinePosX, currentMinePosY + 1, originalMinePos, startCheck, mineArray, checkedMines)
        if (!isThereMine) relocateMineUpLeft(currentMinePosX, currentMinePosY - 1, originalMinePos, startCheck, mineArray, checkedMines)

        if (!isThereMine && mineArray.contains(originalMinePos) && currentPos != originalMinePos) {
            mineArray[mineArray.indexOf(originalMinePos)] = originalMinePos - 1

        }

    }
}

//enum class that contains 4 constants which will be used when creating the cells
enum class CellConstants(val content: String, var print: String, val cellNum: Int, var isBomb: Boolean, var isNumber: Boolean, var isUnexplored: Boolean) {
    NUMBER_CELL("0", ".", 0, false, true, false),
    UNEXPLORED_CELL(".", ".", 0, false, false, true),
    FREE_CELL("/", "/", 0, false, false, false),
    MINE_CELL("X", "X", 0, true, false, false);
}

//cell class that takes the values from the CellConstants
class Cell(cellConstants: CellConstants) {
    var content = cellConstants.content
    var print = cellConstants.print
    var cellNum = cellConstants.cellNum
    var isMine = cellConstants.isBomb
    var isNumber = cellConstants.isNumber
    var isUnexplored = cellConstants.isUnexplored


}


object Grid {

    val fullGrid: Array<Cell> = Array(size * size) { Cell(CellConstants.UNEXPLORED_CELL) }
    val safeCellsList = HashSet<Int>()

    //    val mineCells = HashSet<Int>()
    private var isGameOver = false

    //    creates the game's grid with the size of the board squared and sets values default to "Cell.SAFE_CELL"
    //    makes and stores the grid into gridArray
    fun makeGrid(mineArray: IntArray): Array<Cell> {

        for (i in 0 until size * size) {
            if (!mineArray.contains(i)) safeCellsList.add(i)
        }
//        iterates through mineArray, creates an cell with UNMARKED_CELL values and changes the isBomb to true,
//            updates it's cellNum and adds it to the fullGrid, then it checks: if the cells around the mine index exists,
//            if the cells around are not on the first column or last column and if the cell is not a bomb
//                while it also checks if the surrounding cells are safe cells so it can change them to number cells or
//                if they are number cells so they can increase they're content
        for (i in mineArray) {
            fullGrid[i].isMine = true
            fullGrid[i].cellNum = i
            if (fullGrid.getOrNull(i - 1) != null && i % 9 != 0 && !fullGrid[i - 1].isMine) {

                if (!fullGrid[i - 1].isNumber) {
                    fullGrid[i - 1].cellNum = i - 1
                    fullGrid[i - 1].content = "1"
                    fullGrid[i - 1].isNumber = true
                } else {
                    fullGrid[i - 1].content = (fullGrid[i - 1].content.toInt() + 1).toString()
                }
            }

            if (fullGrid.getOrNull(i - 10) != null && i % 9 != 0 && !fullGrid[i - 10].isMine) {
                if (!fullGrid[i - 10].isNumber) {
//                    val prevCell = Cell(CellConstants.UNEXPLORED_CELL)
                    fullGrid[i - 10].cellNum = i - 10
                    fullGrid[i - 10].content = "1"
                    fullGrid[i - 10].isNumber = true
                } else {
                    fullGrid[i - 10].content = (fullGrid[i - 10].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i - 9) != null && !fullGrid[i - 9].isMine) {
                if (!fullGrid[i - 9].isNumber) {
                    fullGrid[i - 9].cellNum = i - 9
                    fullGrid[i - 9].content = "1"
                    fullGrid[i - 9].isNumber = true
                } else {
                    fullGrid[i - 9].content = (fullGrid[i - 9].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i - 8) != null && (i + 1) % 9 != 0 && !fullGrid[i - 8].isMine) {
                if (!fullGrid[i - 8].isNumber) {
                    fullGrid[i - 8].cellNum = i - 8
                    fullGrid[i - 8].content = "1"
                    fullGrid[i - 8].isNumber = true
                } else {
                    fullGrid[i - 8].content = (fullGrid[i - 8].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i + 1) != null && (i + 1) % 9 != 0 && !fullGrid[i + 1].isMine) {
                if (!fullGrid[i + 1].isNumber) {
                    fullGrid[i + 1].cellNum = i + 1
                    fullGrid[i + 1].content = "1"
                    fullGrid[i + 1].isNumber = true
                } else {
                    fullGrid[i + 1].content = (fullGrid[i + 1].content.toInt() + 1).toString()
                }
            }
            if (fullGrid.getOrNull(i + 10) != null && (i + 1) % 9 != 0 && !fullGrid[i + 10].isMine) {
                if (!fullGrid[i + 10].isNumber) {
                    fullGrid[i + 10].cellNum = i + 10
                    fullGrid[i + 10].content = "1"
                    fullGrid[i + 10].isNumber = true
                } else {
                    fullGrid[i + 10].content = (fullGrid[i + 10].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i + 9) != null && !fullGrid[i + 9].isMine) {
                if (!fullGrid[i + 9].isNumber) {
                    fullGrid[i + 9].cellNum = i + 9
                    fullGrid[i + 9].content = "1"
                    fullGrid[i + 9].isNumber = true
                } else {
                    fullGrid[i + 9].content = (fullGrid[i + 9].content.toInt() + 1).toString()
                }

            }
            if (fullGrid.getOrNull(i + 8) != null && i % 9 != 0 && !fullGrid[i + 8].isMine) {
                if (!fullGrid[i + 8].isNumber) {
                    fullGrid[i + 8].cellNum = i + 8
                    fullGrid[i + 8].content = "1"
                    fullGrid[i + 8].isNumber = true
                } else {
                    fullGrid[i + 8].content = (fullGrid[i + 8].content.toInt() + 1).toString()
                }

            }
        }
        return fullGrid
    }
//main loop where it continues until the player clears all the empty spaces or flags all the mines
    fun gameLoop(mineArray: IntArray, firstChoice: Int) {

        var playerChoice: Pair<Int, String>
        val playerMines = mutableListOf(firstChoice)
        printGrid(fullGrid)
        do {
            if (safeCellsList.isEmpty()) break
            playerChoice = getMineSettingCoords()
            if (playerChoice.second == "free" && mineArray.contains(playerChoice.first)) {
                for (i in mineArray) {
                    val explodedMine = Cell(CellConstants.MINE_CELL)
                    explodedMine.cellNum = i
                    fullGrid[i] = explodedMine
                }
                isGameOver = true

                printGrid(fullGrid)
                println("You stepped on a mine and failed!")
                break
            }
            if (!updateGrid(playerChoice)) continue
            printGrid(fullGrid)
            if (playerMines.contains(playerChoice.first)) {
                playerMines.remove(playerChoice.first)

            } else {
                playerMines.add(playerChoice.first)
            }
            if (safeCellsList.isEmpty()) break
        } while (!isGameOver && safeCellsList.isNotEmpty())
        if (HashSet(mineArray.asList()) == HashSet(playerMines) || safeCellsList.isEmpty())
            println("Congratulations! You found all the mines!")

    }
//fun to uncover the cells according to if the surrounding cells are mines, numbers or are free
    private fun checkAroundCell(x: Int, y: Int, prevCell: Cell, newCell: Cell) {
        val position = getCellNum(y, x)
        if (x < 0 || x >= size || y < 0 || y >= size) return
        if (fullGrid[position].isMine) return
        if (!fullGrid[position].isUnexplored) return
//replace cell with explored cell
        if (fullGrid[position].isNumber) {
            val numberCell = Cell(CellConstants.NUMBER_CELL)
            numberCell.cellNum = fullGrid[position].cellNum
            numberCell.content = fullGrid[position].content
            numberCell.print = numberCell.content
            fullGrid[position] = numberCell
            safeCellsList.remove(position)
            return
        } else {
            val freeCell = Cell(CellConstants.FREE_CELL)
            freeCell.cellNum = fullGrid[position].cellNum
            fullGrid[position] = freeCell
            if (fullGrid.getOrNull(position + 1) != null &&
                    fullGrid.getOrNull(position + 9) != null &&
                    (position + 1) % 9 != 0 &&
                    fullGrid[position + 1].isNumber && fullGrid[position + 9].isNumber &&
                    !fullGrid[position + 10].isMine) {
                val numberCornerCell = Cell(CellConstants.NUMBER_CELL)
                numberCornerCell.cellNum = fullGrid[position + 10].cellNum
                numberCornerCell.content = fullGrid[position + 10].content
                numberCornerCell.print = numberCornerCell.content
                fullGrid[position + 10] = numberCornerCell
                safeCellsList.remove(position + 10)
            }
            if (fullGrid.getOrNull(position - 1) != null &&
                    fullGrid.getOrNull(position + 9) != null &&
                    position % 9 != 0 &&
                    fullGrid[position - 1].isNumber && fullGrid[position + 9].isNumber &&
                    !fullGrid[position + 8].isMine) {
                val numberCornerCell = Cell(CellConstants.NUMBER_CELL)
                numberCornerCell.cellNum = fullGrid[position + 8].cellNum
                numberCornerCell.content = fullGrid[position + 8].content
                numberCornerCell.print = numberCornerCell.content
                fullGrid[position + 8] = numberCornerCell
                safeCellsList.remove(position + 8)
            }
            if (fullGrid.getOrNull(position - 1) != null &&
                    fullGrid.getOrNull(position - 9) != null &&
                    position % 9 != 0 &&
                    fullGrid[position - 1].isNumber && fullGrid[position - 9].isNumber &&
                    !fullGrid[position - 10].isMine) {
                val numberCornerCell = Cell(CellConstants.NUMBER_CELL)
                numberCornerCell.cellNum = fullGrid[position - 10].cellNum
                numberCornerCell.content = fullGrid[position - 10].content
                numberCornerCell.print = numberCornerCell.content
                fullGrid[position - 10] = numberCornerCell
                safeCellsList.remove(position - 10)
            }
            if (fullGrid.getOrNull(position + 1) != null &&
                    fullGrid.getOrNull(position - 9) != null &&
                    (position + 1) % 9 != 0 &&
                    fullGrid[position + 1].isNumber && fullGrid[position - 9].isNumber &&
                    !fullGrid[position - 8].isMine) {
                val numberCornerCell = Cell(CellConstants.NUMBER_CELL)
                numberCornerCell.cellNum = fullGrid[position - 8].cellNum
                numberCornerCell.content = fullGrid[position - 8].content
                numberCornerCell.print = numberCornerCell.content
                fullGrid[position - 8] = numberCornerCell
                safeCellsList.remove(position - 8)
            }
        }
        safeCellsList.remove(position)
        checkAroundCell(x + 1, y, prevCell, newCell)
        checkAroundCell(x - 1, y, prevCell, newCell)
        checkAroundCell(x, y + 1, prevCell, newCell)
        checkAroundCell(x, y - 1, prevCell, newCell)
    }
    //receives user input and calculate the cellNum

    private fun getCellNum(column: Int, row: Int) = row * size + column

//    fun that take the info of the player choice and starts the updateGrid fun
    private fun floodFillCells(row: Int, column: Int, newCell: Cell) {
        val prevCell = fullGrid[getCellNum(column, row)]
        checkAroundCell(row, column, prevCell, newCell)
    }
//   fun that fills the grid and updates each cell according to the content of the surrounding cells starting with the player's choice
    fun updateGrid(playerChoice: Pair<Int, String>): Boolean {

        if (!fullGrid[playerChoice.first].isUnexplored) {
            println("That cell is already explored, try again.")
            return false
        }
//        do - while loop that turns the playerMines and the mineArray to a set and stops when they are equal
        return when (playerChoice.second) {
            "free" -> {
                if (fullGrid[playerChoice.first].isNumber) {
                    val numberCell = Cell(CellConstants.NUMBER_CELL)
                    numberCell.content = fullGrid[playerChoice.first].content
                    numberCell.print = numberCell.content
                    numberCell.cellNum = playerChoice.first
                    fullGrid[playerChoice.first] = numberCell
                    safeCellsList.remove(playerChoice.first)
                    return true
                } else {
                    floodFillCells(playerChoice.first / size, playerChoice.first - (playerChoice.first / size) * size, fullGrid[playerChoice.first])
                }
                true

            }
            "mine" -> {
                //                checks if the chosen cell is a number, then prints the warning and repeats the loop
                //                checks if the chosen cell was set before and depending on that changes the cell val to the correct constant
                when (fullGrid[playerChoice.first].print) {
                    "." -> {
                        fullGrid[playerChoice.first].print = "*"
                        true
                    }
                    "*" -> {
                        fullGrid[playerChoice.first].print = "."
                        true
                    }
                    else -> {
                        println("that is not a valid option, try again")
                        false
                    }
                }

            }
            else -> {
                println("that is not a valid option, try again")
                return false
            }
        }
    }

    //fun that for each row prints the grid's row
    fun printGrid(fullGrid: Array<Cell>) {
//updated de grid before printing
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
                print(fullGrid[i * size + j].print)
            }
            println("|")
        }
//        prints the bottom separating border
        print("-|")
        for (i in 1..size) print("-")
        println("|")
    }

    //   fun that uses a scanner to get the input and returns the result of the getCellNum fun
    fun getMineSettingCoords(): Pair<Int, String> {
        println("Set/unset mines marks or claim a cell as free (x and y coordinates):")
        val scanner = Scanner(System.`in`)
        val row = scanner.nextInt() - 1
        val column = scanner.nextInt() - 1
        val command = scanner.next()
        return Pair(getCellNum(row, column), command)
    }

    //   updates the grid according to the set/delete mark input
}

fun main() {
    val mineCount = MineArray().getMineCount(System.`in`)
    Grid.printGrid(Grid.fullGrid)

    var firstChoice = Grid.getMineSettingCoords()
    while (firstChoice.second == "mine") {
        Grid.updateGrid(firstChoice)
        Grid.printGrid(Grid.fullGrid)
        firstChoice = Grid.getMineSettingCoords()
    }

    if (firstChoice.second == "free") Grid.safeCellsList.remove(firstChoice.first)
    val mineArray = MineArray().makeMineArray(mineCount, firstChoice.first)
    println("modified")
    Grid.makeGrid(mineArray)
    Grid.updateGrid(firstChoice)
    Grid.gameLoop(mineArray, firstChoice.first)
}
