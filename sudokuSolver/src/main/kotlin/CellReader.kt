import java.io.File
import java.io.BufferedReader
import java.io.InputStream


class CellReader(filePath: String) {
    private val path = filePath
    private val cells = mutableListOf<MutableList<Cell>>()

    init {
        var puzzleFile = File(path)

        if (!puzzleFile.exists()) {
            throw Exception("Provided puzzle file does not exist!")
        }
    }

    fun buildCells(): MutableList<MutableList<Cell>> {
        val reader: InputStream = File(path).inputStream()
        val lines = mutableListOf<String>()
        reader.bufferedReader().forEachLine {
            lines.add(it);
        }

        var possibleValues: List<String> = listOf<String>()
        for (line in lines) {
            val index = lines.indexOf(line)
            if (index == 0) {
                val size = line.toInt()
                repeat(size) {
                    cells.add(mutableListOf<Cell>())
                }
            }
            if (index == 1) {
                possibleValues = line.split(" ")
            } else if (index > 1 && line != "") {
                val cellList = line.split(" ")
                var column = 0
                for (value in cellList) {
                    val cell = Cell()
                    if (value != "-") {
                        cell.value = value
                        cell.solved = true
                    } else {
                        cell.possibleValues = possibleValues as MutableList<String>;
                    }
                    cell.x = index - 2
                    cell.y = column
                    cells[index - 2].add(cell)
                    column += 1
                }
            }
        }

        return cells
    }
}