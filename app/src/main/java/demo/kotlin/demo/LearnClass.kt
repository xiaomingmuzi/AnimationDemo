package com.lixm.kotlin.demo

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @author Lixm
 * @date 2017/11/6
 * @detail
 */
class MyView : View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}


/**
 * 覆盖方法
 */
open class Base {
    open fun v() {}
    fun nv() {}
}

/**
 * Derived.v() 函数上必须加上 override标注。如果没写，编译器将会报错。
 * 如果函数没有标注 open 如 Base.nv()，则子类中不允许定义相同签名的函数， 不论加不加 override。
 * 在一个 final 类中（没有用 open 标注的类），开放成员是禁止的。
 */
class Derived() : Base() {
    override fun v() {}
}

/**
 * 标记为 override 的成员本身是开放的，
 * 也就是说，它可以在子类中覆盖。
 * 如果你想禁止再次覆盖，使用 final 关键字：
 */
open class AnotherDerived() : Base() {
    final override fun v() {}
}


/**
 * 在超类中声明然后在派生类中重新声明的属性必须以 override 开头，并且它们必须具有兼容的类型。
 * 每个声明的属性可以由具有初始化器的属性或者具有 getter 方法的属性覆盖。
 */
open class Foo {
    open val x: Int
        get() {
            val a = 3
            return a
        }
}

/**
 * 你也可以用一个 var 属性覆盖一个 val 属性，但反之则不行。
 * 这是允许的，因为一个 val 属性本质上声明了一个 getter 方法，
 * 而将其覆盖为 var 只是在子类中额外声明一个 setter 方法。
 */
class Bar1 : Foo() {
    override val x: Int = super.x
}

interface FooInterface {
    val count: Int
}

class BarInterface(override val count: Int) : FooInterface {}
class Bar2Interface : FooInterface {
    override val count: Int = 0
}

/**
 * 派生类中的代码可以使用 super 关键字调用其超类的函数与属性访问器的实现：
 */
open class Super {
    open fun f() {
        println("Super.f()")
    }

    open val x: Int get() = 1
}

class SuperBar : Super() {
    override fun f() {
        super.f()
        println("SuperBar.f()")
    }

    override val x: Int
        get() = super.x + 1
}

/**
 * 在一个内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer
 */
class OuterBar : Super() {
    override fun f() {
        super.f()
        println("OuterBar.f()")
    }

    override val x: Int
        get() = super.x * 2

    inner class Baz {
        fun g() {
            super@OuterBar.f()//调用Super实现的f()
            println("内部类Baz中调用super@OuterBar.x的值为：${super@OuterBar.x}")//使用Super实现的x的getter
        }
    }

    /**
     * 在 Kotlin 中，实现继承由下述规则规定：
     * 如果一个类从它的直接超类继承相同成员的多个实现， 它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。
     * 为了表示采用从哪个超类型继承的实现，我们使用由尖括号中超类型名限定的 super，如 super<Base>：
     */
    open class A {
        open fun f() {
            print("A中可继承的f()方法")
        }

        fun a() {
            print("A中不可继承的a()方法")
        }
    }

    interface B {
        fun f() {
            print("B中f()方法")//接口成员默认就是"open"的
        }

        fun b() {
            print("B中b()方法")
        }
    }

    /**
     * 同时继承 A 和 B 没问题，并且 a() 和 b() 也没问题因为 C 只继承了每个函数的一个实现。
     * 但是 f() 由 C 继承了两个实现，所以我们必须在 C 中覆盖 f() 并且提供我们自己的实现来消除歧义
     */
    class C() : A(), B {
        //编译器要求覆盖f()
        override fun f() {
            super<A>.f()//调用A.f()
            super<B>.f()//调用B.f()
        }
    }


    /**
     * 用一个抽象成员覆盖一个非抽象的开放成员
     */
    open class BaseOpen {
        open fun f() {}
    }

    abstract class DerivedBase : BaseOpen() {
        override abstract fun f()
    }
}

open class D {}
class D1 : D() {}
open class E {
    open fun D.foo() {
        println("D.foo in E")
    }

    open fun D1.foo() {
        println("D1.foo in E")
    }

    fun caller(d: D) {
        d.foo()   // 调用扩展函数
    }
}

class E1 : E() {
    override fun D.foo() {
        println("D.foo in E1")
    }

    override fun D1.foo() {
        println("D1.foo in E1")
    }
}