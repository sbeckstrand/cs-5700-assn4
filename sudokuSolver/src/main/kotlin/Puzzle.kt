import kotlin.math.sqrt

class Puzzle(cells: MutableList<MutableList<Cell>>) {
    val cells = cells
    var solved = false
    var bad = false
    var badReason = ""

    init {
        val sqrRoot = sqrt(cells[0].size.toDouble())
        if (sqrRoot * sqrRoot != cells[0].size.toDouble()) {
            bad = true
            badReason = "Puzzle is in the wrong format. Not a perfect square"
        }

        if (cells.size != cells[0].size) {
            bad = true
            badReason = "Puzzle is in the wrong format. Uneven number of columns and rows"
        }

        var evenColumns = true
        val firstRow = cells[0].size
        for (row in cells) {
            if (row.size != firstRow) {
                evenColumns = false
            }
        }

        if (!evenColumns) {
            bad = true
            badReason = "At least one column is has a different number of values than other columns"
        }
    }
    fun checkSolution() {
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