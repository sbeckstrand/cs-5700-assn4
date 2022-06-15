import java.io.File

class Solver(inputPath: String, outputPath: String) {
    private val puzzlePath = inputPath
    private val outputPath = outputPath
    val puzzle: Puzzle? = null
    private var cells = mutableListOf<MutableList<Cell>>()
    private val solutions = mutableListOf<Puzzle>()

    fun run() {
        readCells()
    }

    private fun readCells() {
        val reader = CellReader(puzzlePath)
        cells = reader.buildCells()
    }

    private fun buildPuzzle() {
        val puzzle = Puzzle
    }
}