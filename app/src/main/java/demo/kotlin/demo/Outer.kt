package com.lixm.kotlin.demo

/**
 * @author Lixm
 * @date 2017/11/7
 * @detail
 * private 意味着只在这个类内部（包含其所有成员）可见；
 * protected—— 和 private一样 + 在子类中可见。
 * internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
 * public —— 能见到类声明的任何客户端都可见其 public 成员。
 * 注意 对于Java用户：Kotlin 中外部类不能访问内部类的 private 成员。
 * 如果你覆盖一个 protected 成员并且没有显式指定其可见性，该成员还会是 protected 可见性。
 */
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  //默认public

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    //a不可见
    //b、c、d可见
    override val b: Int = 5
}

class Unrelated(o: Outer) {
    // o.a 、o.b不可见
    // o.c和o.d可见  （相同模块）
    //Outer.Nested不可见，Nested::e也不可见
}

/**
 * 要指定一个类的的主构造函数的可见性，使用以下语法（注意你需要添加一个显式 constructor 关键字
 * 这里的构造函数是私有的。默认情况下，所有构造函数都是 public，
 * 这实际上等于类可见的地方它就可见（即 一个 internal 类的构造函数只能在相同模块内可见).
 */
class C private constructor(a:Int){}