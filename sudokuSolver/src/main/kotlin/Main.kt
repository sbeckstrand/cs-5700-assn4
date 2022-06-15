fun main (args: Array<String>) {
    if (args.size != 2) {
        throw Exception("Please provide the path to a valid puzzle file and the output path")
    }

    val solver = Solver(args[0], args[1])
    solver.run()
}