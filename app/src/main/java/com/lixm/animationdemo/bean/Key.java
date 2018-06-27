package com.lixm.animationdemo.bean;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/6/27
 */
public class Key {
    private String id;

    public Key(String id) {
        this.id = id;
    }

    public String toString (){
        return id;
    }

    public int hashCode(){
        return id.hashCode();
    }

    public boolean equals(Object r){
        return (r instanceof Key) && id.equals(((Key)r).id);
    }
}
