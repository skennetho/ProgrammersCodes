package Sort

import kotlin.reflect.typeOf

fun main(args: Array<String>){
    var arr =  intArrayOf(34, 5, 9, 3,30)
    val sl = Solution()
    var answer = sl.solution(arr)
    print("answer : $answer")
}
class Solution {
    fun solution(numbers: IntArray): String {
        var strArr = numbers.map{it.toString() }
//        println("before: $strArr ")
        strArr = strArr.sortedWith(Comparator(){ s: String, s1: String ->
            var c = s1+s
            c.compareTo(s+s1)
        })
//        println("after: $strArr")

        var answer = ""

        strArr.forEach{str -> answer+= str}
        return if(strArr[0].get(0) =='0'){
            "0"
        }
        else {
            answer
        }
    }
}