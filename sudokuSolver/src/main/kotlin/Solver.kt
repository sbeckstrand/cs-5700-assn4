import java.io.File

class Solver(inputPath: String, outputPath: String) {
    private val puzzlePath = inputPath
    private val outputPath = outputPath
    var originalPuzzle: Puzzle? = null
    val puzzleStates = mutableListOf<Puzzle>()
    private var cells = mutableListOf<MutableList<Cell>>()
    private val solutions = mutableListOf<Puzzle>()


    fun run() {
        readCells()
        buildPuzzle()

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
        
    }
}