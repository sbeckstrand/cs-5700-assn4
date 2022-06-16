class OnlyPossibleSpot(solver: Solver) : CellSolverMethod(solver) {
    init {
        name = "Only Possible Spot"
    }

    override fun findSolution(): Boolean {

        val cells = puzzle?.cells
        if (cells != null) {
            repeat(cells.size) {
                repeat(cells[it].size) { cellIndex ->
                    val currCell = cells[it][cellIndex]
                    if (!currCell.solved) {
                        val possibleValues = currCell.possibleValues
                        for (value in possibleValues) {
                            // Check Row
                            var onlyInCurrent = true
                            for (cell in cells[it]) {
                                if (cell != currCell && cell.value != "-") {
                                    if (cell.possibleValues.contains(value)) {
                                        onlyInCurrent = false
                                    }
                                }
                            }

                            if (onlyInCurrent) {
                                currCell.value = value
                                currCell.solved = true
                                return true
                            }

                            // Check Column
                            onlyInCurrent = true
                            for (column in cells) {
                                val cell = column[currCell.x!!]
                                if (cell != currCell && cell.value != "-") {
                                    if (cell.possibleValues.contains(value)) {
                                        onlyInCurrent = false
                                    }
                                }
                            }

                            if (onlyInCurrent) {
                                currCell.value = value
                                currCell.solved = true
                                return true
                            }

                            // Check block
                            onlyInCurrent = true
                            val blockSize = kotlin.math.sqrt(cells[0].size.toDouble()).toInt()

                            // x checks
                            repeat(currCell.x?.rem(blockSize)!!) { xDiff ->
                                val cell = cells[currCell.y!!][currCell.x!! - (xDiff + 1)]
                                if (cell.possibleValues.contains(value)) {
                                    onlyInCurrent = false
                                }
                            }

                            repeat((blockSize - 1) - (currCell.x?.rem(blockSize)!!)) { xDiff ->
                                val cell = cells[currCell.y!!][currCell.x!! + (xDiff + 1)]
                                if (cell.possibleValues.contains(value)) {
                                    onlyInCurrent = false
                                }
                            }

                            if (onlyInCurrent) {
                                currCell.value = value
                                currCell.solved = true
                                return true
                            }
                        }
                    }
                }
            }
        }

        return false
    }

}