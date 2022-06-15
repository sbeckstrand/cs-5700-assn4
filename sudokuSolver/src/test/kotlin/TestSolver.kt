import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TestSolver {

    @Test
    fun testSolverCreateWithValid() {
        assertDoesNotThrow {
            val solver = Solver("puzzles/Puzzle-4x4-0001.txt", "puzzles/output/output.txt")
        }
    }

    @Test
    fun testSolverRunWithValid() {
        assertDoesNotThrow {
            val solver = Solver("puzzles/Puzzle-4x4-0001.txt", "puzzles/output/output.txt")
            solver.run()
        }
    }

    @Test
    fun testSovlerRunWithInvalidFilePath() {
        assertThrows<Exception> {
            val solver = Solver("puzzles/testing.txt", "puzzles/output/output.txt")
            solver.run()
        }
    }
}