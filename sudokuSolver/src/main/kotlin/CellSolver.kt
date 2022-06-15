class CellSolver(solver: Solver) {
    val solver = solver
    val strategies = mutableListOf<CellSolverMethod>()

    init {
        val onlyPossibleNumber = OnlyPossibleValue()

        strategies.add(onlyPossibleNumber)
    }

    fun run() {
        var finished = false
        val currentPuzzle = solver.puzzleStates.last()
        while (!currentPuzzle.solved and !finished) {
            for (strategy in strategies) {
                strategy.run(currentPuzzle)
            }
        }
    }

}