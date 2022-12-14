import java.io.File

fun main(){

    fun read_file(file_path: String) : List<String> {
        val text = File(file_path).readLines()
        return text
    }

    val fix = mapOf("A" to "X", "B" to "Y", "C" to "Z")

    fun part1(x: String) : Int {
        val me: String = x[2].toString()
        val opp: String = fix.getValue(x[0].toString())

        var score: Int = 0
        // draw
        if (opp == me) score += 3

        // Win
        if (opp == "X" && me == "Y") score +=6
        if (opp == "Y" && me == "Z") score +=6
        if (opp == "Z" && me == "X") score +=6
        
        score += mapOf("X" to 1, "Y" to 2, "Z" to 3).getValue(me)
        return score
    }

    fun part2(x: String): Int {
        val result: String = x[2].toString()
        val opp: String = x[0].toString()
        var me: String = "A"
    
        var score: Int = 0
    
        // draw
        if (result == "Y"){
            score += 3 
            me = opp
        }
        
        // lose 
        if (result == "X"){
            if (opp == "A") me = "C"
            if (opp == "B") me = "A"
            if (opp == "C") me = "B"
        }
    
        // win 
        if (result == "Z"){
            score += 6
            if (opp == "A") me = "B"
            if (opp == "B") me = "C"
            if (opp == "C") me = "A"
        } 
    
        score += mapOf("A" to 1, "B" to 2, "C" to 3).getValue(me)
        
        return score
    }
    val testFile = read_file("data/day2_test.txt")
    check(testFile.map { part1(it)}.sum() == 15)
    check(testFile.map { part2(it)}.sum() == 12)

    val file = read_file("data/day2.txt")
    println(file.map { part1(it)} .sum())
    println(file.map { part2(it)} .sum())

}

