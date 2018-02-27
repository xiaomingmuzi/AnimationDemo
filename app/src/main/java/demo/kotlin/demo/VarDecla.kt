package com.lixm.kotlin.demo

/**
 * @author Lixm
 * @date 2017/11/7
 * @detail
 */
open class VarDecla {
    //变量的声明和使用
    fun main(args: Array<String>) {//固定写法，函数的入口
        var name = "张三" //计算机给我一个存储空间，空间的名字叫name，里面存放的是张三
        name = "李四"    //计算机name空间里面，放李四
        println(name)
    }
}