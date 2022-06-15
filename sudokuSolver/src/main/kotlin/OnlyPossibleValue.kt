import java.lang.Math.sqrt

class OnlyPossibleValue: CellSolverMethod() {

    override fun findSolution(): Boolean {

        val cells = puzzle?.cells

        if (cells != null) {
            repeat(cells.size) {
                repeat(cells[it].size) {cellIndex ->
                    val currCell = cells[it][cellIndex]

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
                        return true
                    }

                    // Check Column
                    for (column in cells) {
                        val cell = column[currCell.x!!]
                        if (cell != currCell && cell.value != "-") {
                            if (currCell.possibleValues.contains(cell.value)) {
                                currCell.possibleValues.remove(cell.value)
                            }
                        }
                    }

                    if (oneSolution(currCell)) {
                        currCell.value = currCell.possibleValues[0]
                        return true
                    }

                    // Check block
                    val blockSize = kotlin.math.sqrt(cells[0].size.toDouble()).toInt()
                    val xDifference = (blockSize - 1) - (currCell.x?.rem(blockSize)!!)
                    val yDifference = (blockSize - 1) - (currCell.y?.rem(blockSize)!!)


                }
            }
        }
        // Check row


        // check column

        // check block
        return false
    }

    fun oneSolution(cell: Cell): Boolean {
        if (cell.possibleValues.size == 1) {
            return true
        }
        return false
    }

}