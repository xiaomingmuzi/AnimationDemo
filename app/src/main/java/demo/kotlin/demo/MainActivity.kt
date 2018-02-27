package com.lixm.kotlin.demo

import android.app.Activity
import android.os.Bundle
import com.lixm.animationdemo.R
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import java.io.File
import kotlin.properties.Delegates
import com.lixm.kotlin.demo.D as F

class MainActivity : Activity() {
    //    可变的（var）或只读的（val）
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
        var sum = sum(3, 5)
        sum_txt.text = "和为：$sum"
        printProduct("6", "7")
        printProduct("000", "1")
        getStringLength("10000")
        getStringLength(111)
        useFor()
        useWhile()
        print_null.text = useWhen("hello") + "\n" + useWhen(100L) + "\n" + useWhen("你好")
        useRange()
        useRange1()

        shuliediedai()
        diedai()
        useIn()
        useLambda()
        forList()
        transform("Red")
        tryAndCatch()
        ifNotNull()
        equalNum()
        decimalDigitValue('8')

        createArray()
        printyinhao()

        val x = OuterBar()
        println("OuterBar中x的值为：${x.x}")
        val y = x.Baz()
        y.g()

//        var allByDefault:Int? // 错误：需要显式初始化器，隐含默认 getter 和 setter
//        println(allByDefault)

        val l = mutableListOf<Int>(1, 2, 3)
        println("swap置换后：${l.swap(0, 2)}")

        E().caller(F())//D.foo in E
        E1().caller(F())//D.foo in E1
        E().caller(D1())//D.foo in E

        val user = User()
        user.name = "first"//<no name>->first
        user.name = "second"//first->second
        user.age=11
        println(user.age)
        user.age=14
        println(user.age)

        VarDecla()

    }



    class User {
        var name: String by Delegates.observable("<no name>") { prop, old, new ->
            println("$old->$new")
        }
        var age:Int by Delegates.vetoable(10){
            prop, old, new -> true
        }
    }


    class D {
        fun bar() {
            println("类D中的bar()方法")
        }
    }

    class C {
        fun baz() {
            println("类C中的baz()方法")
            val x1 = foo().x //正确，没问题
            //匿名对象可以用作只在本地和私有作用域中声明的类型。
            // 如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，
            // 那么该函数或属性的实际类型会是匿名对象声明的超类型，
            // 如果你没有声明任何超类型，就会是 Any。在匿名对象中添加的成员将无法访问
            //val x2=publicFoo().x//错误，未能解析的引用“x”
        }

        fun D.foo() {
            bar()//调用D.bar()方法
            baz()//调用C.baz()方法
            //对于分发接收者和扩展接收者的成员名字冲突的情况，扩展接收者优先。
            // 要引用分发接收者的成员你可以使用 限定的 this 语法。
            toString()        //调用D.toString()
            this@C.toString() //调用C.toString()
        }

        /**
         * 私有函数，所以其返回类型是匿名对象类型
         */
        private fun foo() = object {
            val x: String = "x"
        }

        fun publicFoo() = object {
            val x: String = "x"
        }
    }

    fun <T> MutableList<T>.swap(index1: Int, index2: Int): T {
        val tmp = this[index1]//this 对应该列表   this[0]对应集合中下标为0的值，为1
        this[index1] = this[index2]
        this[index2] = tmp
        return this[index1]
    }

    /**
     * 原生字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行和任何其他字符:
     */
    private fun printyinhao() {
        val text = """
                 for (c in "foo")
                 print(c)
            """
        println(text)

        val text1 = """
            |Tell me and I forget
            |Teach me and I remember
            |Involve me and I learn
            |(Benjamin Franklin)
        """.trimMargin("|")//字符串分割
        println(text1)
        val price = """${'$'}9.99"""
        println(price)
    }


    /**
     * 创建数组
     *
     */
    fun createArray() {
        //创建一个Array<String> 初始化为["0","1","4","9","16"]
        val asc = Array(5, { i -> (i * i).toString() })
        print("初始化的数组为：")
        for (item in asc)
            print("$item ")
        println()
    }

    /**
     * fun check(c: Char) {
     *if (c == 1) { // 错误：类型不兼容
     * // ……
     * }
     * }
     * 但是可以显示的把字符转换为Int类型的数字
     *
     */
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt()//显式转换为数字
    }

    /**
     * 注意数字装箱不必保留同一性
     * 另一方面，它保留了相等性
     *
     */
    fun equalNum() {
        val a: Int = 10000
        println(a === a)//输出true
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println("boxedA：$boxedA  anotherBoxedA：$anotherBoxedA  " +
                (boxedA == anotherBoxedA) + "  " +//true 它保留了相等性
                (boxedA === anotherBoxedA))// false 数字装箱不必保留同一性
    }

    /**
     * try-catch结构体
     */
    fun tryAndCatch() {
        val result = try {
            var a = 5
            var b = "99"
            val c = a * Integer.parseInt(b)
//            print("输出结果为：$a * $b= $c")
        } catch (e: Exception) {
            e.printStackTrace()
//            throw Exception(e)
        }
        println("计算结果为：$result")
    }

    /**
     * If Not Null and Else 的缩写
     */
    fun ifNotNull() {
        val files = File("Test").listFiles()
        println(files?.size ?: "empty")
    }

    abstract class Shape(val sides: List<Double>) {
        val perimeter: Double get() = sides.sum()
        abstract fun calculateArea(): Double
    }

    interface RectangleProperties {
        val isSquare: Boolean
    }

    fun forList() {
        println("闭区间：")
        for (i in 1..10) {//闭区间，包含100
            print("$i ")//1 2 3 4 5 6 7 8 9 10
        }
        println()
        println("半开区间，until：")
        for (i in 1 until 10) {//半开区间，不包含100
            print("$i ")//1 2 3 4 5 6 7 8 9
        }
        println()
        println("step：")
        for (i in 2..10 step 2) {
            print("$i ")// 2 4 6 8 10
        }
        println()
        println("downTo：")
        for (i in 10 downTo 1) {// 10 9 8 7 6 5 4 3 2 1
            print("$i ")
        }
        println()
    }

    /**
     * 以下类的创建方法为这种的简洁语法
     *   class Rectangle(height :Double,length:Double){
     *    val  customHeight=height
     *     val customLength=length
    }*/
    class Rectangle(
            var height: Double,
            var length: Double
    ) : Shape(listOf(height, length, height, length)), RectangleProperties {
        override val isSquare: Boolean get() = length == height
        override fun calculateArea(): Double = height * length
    }

    class Triangle(
            var sideA: Double,
            var sideB: Double,
            var sideC: Double
    ) : Shape(listOf(sideA, sideB, sideC)) {
        override fun calculateArea(): Double {
            val s = perimeter / 2
            return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
        }
    }

    /**
     * 使用lambda表达式来过滤（filter）和映射（map）集合
     */
    fun useLambda() {
        val fruits = listOf<String>("banana", "avocado", "apple", "kiwi")
        fruits.filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
    }

    /**
     * 使用in运算符来判断集合内是否包含某实例
     */
    fun useIn() {
        val items = setOf<String>("apple", "banana", "kiwi")
        when {
            "orange" in items -> println("juicy")
            "apple" in items -> println("apple is fine too")
        }
    }

    /**
     * 数列迭代
     */
    fun shuliediedai() {
        for (x in 1..10 step 2) {
            print("$x ")
        }
        for (x in 9 downTo 0 step 3) {
            print("$x ")
        }
        println()
    }

    /**
     * 使用区间迭代
     */
    fun diedai() {
        for (x in 1..5) {
            print(x)
        }
        println()
    }

    /**
     * 使用区间range
     * 使用in运算符来检测某个数字是否在指定的区间内
     */
    fun useRange() {
        val x = 10
        val y = 9
        if (x in 1..y + 1)
            println("first in range")

    }

    /**
     * 检测某个数字是否在指定区间外
     */
    fun useRange1() {
        val list = listOf<String>("a", "b", "c")
        if (-1 !in 0..list.lastIndex)
            println("-1 is out of range")
        if (list.size !in list.indices)
            println("list size is out of valid list indices range too")

    }

    /**
     * 返回When表达式
     *
     */
    fun transform(color: String): Int {
        return when (color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    /**
     * 使用when表达式
     *  如果 when 作为一个表达式使用，则必须有 else 分支，
     *  除非编译器能够检测出所有的可能情况都已经覆盖了。
     */
    fun useWhen(obj: Any): String =
            when (obj) {
                in 4..6 -> "Three"
                1, 2 -> "one"
                "hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a String"
                else -> "Unknow"
            }

    /**
     * 使用while表达式
     */
    fun useWhile() {
        val items = listOf<String>("小丽", "Lee", "098")
        var index = 0
        while (index < items.size) {
            println("useWhile item at $index is ${items[index]}")
            index++
        }
    }


    fun useFor() {
        val items = listOf<String>("apple", "banana", "kiwi")
        println("for 使用 in：")
        for (item in items)
            print("$item ")
        println()
        println("for 使用indices：")
        for (index in items.indices)
            println("item at $index is ${items[index]}")
        println("for withIndex：")
        for ((index, value) in items.withIndex())
            println("the element at $index is $value")
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * 可以输出为空的函数
     */
    fun printProduct(arg1: String, arg2: String) {
        val x = Integer.parseInt(arg1)
        val y = Integer.parseInt(arg2)
        if (x != null && y != null) {
            println("arg1*arg2=" + x * y)
        } else {
            println("either '$arg1' or '$arg2' is not a number")
        }
    }

    /**
     * is 运算符检测一个表达式是否某类型的一个实例。
     * 如果一个不可变的局部变量或属性已经判断出为某类型，
     * 那么检测后的分支中可以直接当作该类型使用，无需显式转换
     */
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            //'obj'在该条件分之内自动转换成‘String’
            return obj.length
        }
        //在离开类型检测分支后，‘obj’仍然是‘Any’类型
        return null
    }

    fun getStringLength1(obj: Any): Int? {
        if (obj !is String) return null
        //‘obj’在这一分支自动转换为‘String’
        else return obj.length
    }

    fun getStringLength2(obj: Any): Int? {
        //'obj'在‘&&’右边自动转换成‘String’类型
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

}
