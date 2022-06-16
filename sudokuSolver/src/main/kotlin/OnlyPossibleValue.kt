import java.lang.Math.sqrt

class OnlyPossibleValue(solver: Solver) : CellSolverMethod(solver) {
    init {
        name = "Only Possible Value"
    }
    override fun findSolution(): Boolean {

        val cells = puzzle?.cells
//        println("Doing Value")
        if (cells != null) {
            repeat(cells.size) {
                repeat(cells[it].size) {cellIndex ->
                    val currCell = cells[it][cellIndex]
                    if (!currCell.solved) {
                        // Check Row
                        for (cell in cells[it]) {
                            if (cell != currCell && cell.value != "-") {
                                if (currCell.possibleValues.contains(cell.value)) {
                                    currCell.possibleValues.remove(cell.value)
                                }
                            }
                        }

                        if (oneSolution(currCell)) {
                            currCell.value = currCell.possibleValues[0]
                            currCell.solved = true
                            return true
                        }

                        // Check Column
                        for (column in cells) {
                            val cell = column[currCell.y!!]
                            if (cell != currCell && cell.value != "-") {
                                if (currCell.possibleValues.contains(cell.value)) {
                                    currCell.possibleValues.remove(cell.value)
                                }
                            }
                        }

                        if (oneSolution(currCell)) {
                            currCell.value = currCell.possibleValues[0]
                            currCell.solved = true
                            return true
                        }

                        // Check block
                        val blockSize = kotlin.math.sqrt(cells[0].size.toDouble()).toInt()

                        // x checks
                        repeat(currCell.x?.rem(blockSize)!!) { xDiff ->
                            val cell = cells[currCell.y!!][currCell.x!! - (xDiff + 1)]
                            if (currCell.possibleValues.contains(cell.value)) {
                                currCell.possibleValues.remove(cell.value)
                            }
                        }

                        repeat((blockSize - 1) - (currCell.x?.rem(blockSize)!!)) { xDiff ->
                            val cell = cells[currCell.y!!][currCell.x!! + (xDiff + 1)]
                            if (currCell.possibleValues.contains(cell.value)) {
                                currCell.possibleValues.remove(cell.value)
                            }
                        }

                        // y checks
                        repeat(currCell.y?.rem(blockSize)!!) { xDiff ->
                            val cell = cells[currCell.y!! - (xDiff + 1)][currCell.x!!]
                            if (currCell.possibleValues.contains(cell.value)) {
                                currCell.possibleValues.remove(cell.value)
                            }
                        }

                        repeat((blockSize - 1) - (currCell.y?.rem(blockSize)!!)) { xDiff ->
                            val cell = cells[currCell.y!! + (xDiff + 1)][currCell.x!!]
                            if (currCell.possibleValues.contains(cell.value)) {
                                currCell.possibleValues.remove(cell.value)
                            }
                        }

                        if (oneSolution(currCell)) {
                            currCell.value = currCell.possibleValues[0]
                            currCell.solved = true
                            return true
                        }
                    }

                }
            }
        }

        return false
    }

    private fun oneSolution(cell: Cell): Boolean {
        if (cell.possibleValues.size == 1) {
            return true
        }
        return false
    }

}