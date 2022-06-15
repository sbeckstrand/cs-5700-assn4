class Puzzle(cells: MutableList<MutableList<Cell>>) {
    val cells = cells
    var solved = false

    fun checkSolution() {
        println("blah")
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