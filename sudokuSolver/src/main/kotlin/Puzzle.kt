import kotlin.math.sqrt

class Puzzle(cells: MutableList<MutableList<Cell>>) {
    val cells = cells
    var solved = false

    init {
        val sqrRoot = sqrt(cells[0].size.toDouble())
        if (sqrRoot * sqrRoot != cells[0].size.toDouble()) {
            throw Exception("Provided puzzle is not a perfect square")
        }
    }
    fun checkSolution(): Boolean {
        var solution = true
        repeat(cells.size) {
            repeat(cells[it].size) { cellIndex ->
                if (!cells[it][cellIndex].solved) {
                    solution = false
                }
            }
        }

        if (solution) {
            solved = true
        }

        return solved
    }

    fun clone(): Puzzle {
        val cellCopies = mutableListOf<MutableList<Cell>>()
        for (row in cells) {
            val newRow = mutableListOf<Cell>()
            for (cell in row) {
                val copy = cell.clone()
                newRow.add(copy)
            }
            cellCopies.add(newRow)
        }
        val newPuzzle = Puzzle(cellCopies)
        newPuzzle.solved = solved

        return newPuzzle
    }

    override fun toString(): String {
        var stringBuilder = ""
        repeat(cells.size) {
            repeat (cells[it].size) { cellIndex ->
                stringBuilder += "${cells[it][cellIndex].value} "
            }
            stringBuilder += "\n"
        }

        return stringBuilder
    }

}