package com.lixm.animationdemo.bean;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/6/27
 */
public class Value {
    private String id;

    public Value(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    public void finalize() {
        System.out.println("Finalizing Value " + id);
    }
}
