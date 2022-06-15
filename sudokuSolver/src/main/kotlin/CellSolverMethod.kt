abstract class CellSolverMethod {
    var puzzle: Puzzle? = null

    fun run(currentPuzzle: Puzzle) {
        puzzle = currentPuzzle
    }

    fun startTimer() {

    }

    fun stopTimer() {

    }


    abstract fun findSolution(): Boolean
}