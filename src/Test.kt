import Sort.Solution

fun main(args: Array<String>) {
    println("kotlin test")
    var k = KotlinVO(1, "txt", true);
    var array = arrayOf(
            KotlinVO(1, "3", true),
            KotlinVO(2, "30", true),
            KotlinVO(4, "34", false),
            KotlinVO(19, "19", false),
            KotlinVO(7, "7", true)
    )
    var str = ""
    array.forEach { data -> print(data.toString()) }
    array.sortBy { data -> data.txt }
    println()
    array.forEach { data -> print(data.toString()) }
    println()

    array.sortWith(Comparator<KotlinVO>() { kotlinVO: KotlinVO, kotlinVO1: KotlinVO ->
        var c = kotlinVO1.txt + kotlinVO.txt
        c.compareTo(kotlinVO.txt + kotlinVO1.txt)
    })
    array.forEach { data -> print(data.toString()) }
    println()

    str = "start"
    str.forEach { c -> println(c) }
}

data class KotlinVO(val num : Int,val txt : String,val boo : Boolean){
    override fun toString():String{
        return "($num:$txt:$boo)"
    }
}
