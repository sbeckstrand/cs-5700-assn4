import java.io.File


class Solver(inputPath: String, outputPath: String) {
    private val puzzlePath = inputPath
    private val outputPath = outputPath
    var originalPuzzle: Puzzle? = null
    val puzzleStates = mutableListOf<Puzzle>()
    var cells = mutableListOf<MutableList<Cell>>()
    private set;
    private val solutions = mutableListOf<Puzzle>()
    private var cellSolver: CellSolver? = null


    fun run() {
        readCells()
        buildPuzzle()
        solvePuzzle()
        exportResult()
    }

    private fun readCells() {
        val reader = CellReader(puzzlePath)
        cells = reader.buildCells()
    }

    private fun buildPuzzle() {
        originalPuzzle = Puzzle(cells)
        puzzleStates.add(originalPuzzle!!.clone())
    }

    private fun solvePuzzle() {
        if (originalPuzzle != null) {
            if (!originalPuzzle!!.bad) {
                cellSolver = CellSolver(this)
                cellSolver!!.run()
            }
        }
    }

    private fun exportResult() {
        try {
            File(outputPath).printWriter().use { out ->
                out.println("Original Puzzle: \n${originalPuzzle}\n---\n")
                if (puzzleStates.last().solved) {
                    out.println("Result: Solved")
                    out.println("${puzzleStates.last()}\n---\n")
                    var totalTime = 0.0
                    for (strategy in cellSolver?.strategies!!) {
                        totalTime += strategy.time
                    }
                    val timeString = "%.4f".format(totalTime)
                    out.println("Total Runtime: ${timeString}s\n")

                    out.println("${"Strategy".padEnd(20)} ${"Uses".padEnd(10)} ${"Time".padEnd(10)}")
                    for (strategy in cellSolver?.strategies!!) {
                        val time = "%.4f".format(strategy.time)
                        val occurrences = strategy.useCount.toString()
                        val name = strategy.name

                        out.println("${name.padEnd(20)} ${occurrences.padEnd(10)} ${"${time}s".padEnd(10)}")
                    }
                } else if (puzzleStates.last().bad) {
                    out.println("Result: Bad Puzzle")
                    out.println("Reason: ${puzzleStates.last().badReason}")
                }
            }
        } catch (e: Exception) {
            throw Exception(e.toString())
        }



    }
}