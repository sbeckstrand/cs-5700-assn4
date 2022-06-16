class Guess(solver: Solver) : CellSolverMethod(solver) {
    init {
        name = "Guess"
    }

    override fun findSolution(): Boolean {

        val cells = puzzle?.cells
        if (cells != null) {
            repeat(cells.size) {
                repeat(cells[it].size) { cellIndex ->
                    val currCell = cells[it][cellIndex]
                    if (!currCell.solved) {
                        val possibleValues = currCell.possibleValues
                        if (possibleValues.size > 0) {
                            currCell.value = possibleValues[0]
                            currCell.solved = true
                            return true
                        } else {

                            return false
                        }
                    }
                }
            }
        }

        return false
    }
}