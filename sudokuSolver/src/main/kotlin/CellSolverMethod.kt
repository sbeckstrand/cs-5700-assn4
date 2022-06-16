import java.util.*

abstract class CellSolverMethod(puzzleSolver: Solver) {
    var puzzle: Puzzle? = null
    var time: Double = 0.0
        private set;
    private var startTime: Long? = null
    var useCount = 0
    val solver = puzzleSolver
    var name = ""




    fun run(currentPuzzle: Puzzle): Boolean {
        puzzle = currentPuzzle
        startTimer()
        val result = findSolution()
        if (result) {
            stopTimer()
            useCount += 1
            solver.puzzleStates.add(puzzle!!)
        } else {
            startTime = null
        }

        return result
    }

    private fun startTimer() {
        if (startTime != null) {
            throw Exception("Timer already running");
        } else {
            startTime = System.nanoTime()
        }
    }

    private fun stopTimer() {
        if (startTime == null) {
            throw Exception("Timer is not running")
        } else {
            val stopTime = System.nanoTime()
            val diff = (stopTime - startTime!!) / 1e6
            time += diff
            startTime = null
        }


    }


    abstract fun findSolution(): Boolean
}