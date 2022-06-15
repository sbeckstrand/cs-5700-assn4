class Cell() {
    var value: String = "-"
    var possibleValues = mutableListOf<String>()
    var solved = false
    var x: Int? = null
    var y: Int? = null

    fun clone(): Cell {
        val cellCopy = Cell()
        cellCopy.value = value
        val possibleValuesCopy = mutableListOf<String>()
        for (value in possibleValues) {
            possibleValuesCopy.add(value)
        }
        cellCopy.possibleValues = possibleValuesCopy
        cellCopy.solved = solved
        cellCopy.x = x
        cellCopy.y = y
        return cellCopy
    }
}