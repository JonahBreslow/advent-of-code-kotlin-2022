import java.io.File

fun main() {

    fun  readFile(file_path: String) : List<String> {
        val text = File(file_path).readLines()
        return text
    }

    fun create_elves_calories(input: List<String>): List<Int>{
        var calories = mutableListOf<Int>()
        var counter:Int = 0
        for (food in input){
            when(food){
                "" -> {
                    calories.add(counter)
                    counter = 0
                }
                else -> {
                    counter += food.toInt()
                }
            }
        }
        return calories
    }

    fun get_max_calories(input: List<Int>, n_elves: Int): Int {
        val sorted_elves = input.sortedDescending()
        val res = sorted_elves.take(n_elves)
        return res.sum()
    }

    fun part1(input: String): Int {
        val text =  readFile(input)
        val elfList = create_elves_calories(text)
        return get_max_calories(elfList, n_elves=1)
    }

    fun part2(input: String): Int {
        val text =  readFile(input)
        val elfList = create_elves_calories(text)
        return get_max_calories(elfList, n_elves=3)
    }

    // test if implementation meets criteria from the description, like:
    check(part1("data/day1_test.txt") == 24000)

    println(part1("data/day1.txt"))
    println(part2("data/day1.txt"))
}
