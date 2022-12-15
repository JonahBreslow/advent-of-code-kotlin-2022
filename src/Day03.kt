import java.io.File

val Char.priority: Int
        get(): Int {
            return when (this){
                in 'a'..'z' -> this -'a' + 1
                in 'A'..'Z' -> this - 'A' + 27
                else -> error("Check your input! $this")
            }
        }


fun main () {

    fun parseInput(input: String): ArrayList<Pair<String, String>> {
        val data = File(input).readLines()
        var parsed: ArrayList<Pair<String, String>> = arrayListOf()
        for (pack in data){
            val size = pack.length / 2 
            val compartments = pack.chunked(size)
            val pair = Pair(compartments[0], compartments[1])
            parsed.add(pair)
        }
        return parsed
    }

    fun part1(input: String): Int {
        val knapsacks = parseInput(input)
        var res: Int = 0
        for (pack in knapsacks){
            var f = pack.first.toSet()
            var b = pack.second.toSet()
            var item = (f intersect b).first()
            res += item.priority
        }
        
        return res
    }

    fun part2(input: String) : Int {
        val knapsacks = parseInput(input)
        var res: Int = 0
        var allItems: MutableList<Set<Char>> = mutableListOf()
        var iter = 0
        for (pack in knapsacks){
            var bagContents = (pack.first.toSet() union pack.second.toSet())
            allItems += bagContents

            if (iter % 3 == 2){
                val common = (allItems[0] intersect allItems[1] intersect allItems[2]).first()
                res += common.priority
                allItems = mutableListOf()
            }
            ++iter
        }
        return res
    }

    val testInput: String = "data/day3_test.txt"
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)
    
    val input: String = "data/day3.txt"
    println(part1(input))
    println(part2(input))
}