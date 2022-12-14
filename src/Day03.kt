import java.io.File

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

    fun buildMap () : Map<Char, Int> {
        var map: Map<Char, Int> = mapOf()
        var key: Char = 'a'
        var value: Int = 1
        while ( key <= 'z'){
            map += Pair(key, value)
            ++key
            ++value
        }
        key = 'A'
        while ( key <= 'Z'){
            map += Pair(key, value)
            ++key
            ++value
        }
        return map
    }

    val map = buildMap()

    fun part1(input: String): Int {
        val knapsacks = parseInput(input)
        var res: Int = 0
        for (pack in knapsacks){
            var f = pack.first.toSet()
            var b = pack.second.toSet()
            var item = (f intersect b).first()
            res += map.getValue(item)
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
                res += map.getValue(common)
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