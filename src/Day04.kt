import java.io.File
import kotlin.math.min

fun main () {
    
    fun parseInput(input: String) : List<List<Set<Int>>>{
        return File(input)
        .readLines()
        .map { 
            it.split(",")
        }
        .map{
            val set1 = (it[0].split("-")[0].toInt() until it[0].split("-")[1].toInt()+1).map{it}.toSet()
            val set2 = (it[1].split("-")[0].toInt() until it[1].split("-")[1].toInt()+1).map{it}.toSet()
            listOf(set1, set2)
        }
    }

    fun part1(input: String) : Int{
        
        val overlaps = parseInput(input)
        .map{
            if((it[0].intersect(it[1]).size) == min(it[0].size, it[1].size)){
                1
            } else {
                0
            }
        }
        return overlaps.sum()
    }

    fun part2(input: String) : Int{
        
        val overlaps = parseInput(input)
        .map{
            if((it[0].intersect(it[1]).size) > 0 ){
                1
            } else {
                0
            }
        }
        return overlaps.sum()
    }

    val testFile = "data/day4_test.txt"
    check(part1(testFile) == 2)
    check(part2(testFile) == 4)

    val inputFile = "data/day4.txt"
    println(part1(inputFile))
    println(part2(inputFile))
}