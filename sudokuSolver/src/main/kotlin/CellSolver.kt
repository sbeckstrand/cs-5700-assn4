class CellSolver(solver: Solver) {
    val solver = solver
    val strategies = mutableListOf<CellSolverMethod>()

    init {
        val onlyPossibleNumber = OnlyPossibleValue(solver)
        val onlyPossibleSpot = OnlyPossibleSpot(solver)
        val guess = Guess(solver)
        strategies.add(onlyPossibleNumber)
        strategies.add(onlyPossibleSpot)
        strategies.add(guess)

    }

    fun run() {

        var revert = false
        var attempts = 0
        while (!revert && attempts < 1000) {
            val result = findNextSolution()
            if (!result) {
                revert = true
            }
            attempts += 1
        }

        if (attempts >= 1000) {
            solver.puzzleStates.last().bad = true
            solver.puzzleStates.last().badReason = "Could Not Be Solved in reasonable amount of attempts"
        }
    }

    private fun findNextSolution(): Boolean {
        var revert = false
        val currentPuzzle = solver.puzzleStates.last()
        while (!currentPuzzle.solved and !revert) {
            for (strategy in strategies) {
                val result = strategy.run(currentPuzzle)
                if (result) {
                    currentPuzzle.checkSolution()
                    return true
                }  else {
                    revert = true
                }
            }
        }

        return revert
    }
}