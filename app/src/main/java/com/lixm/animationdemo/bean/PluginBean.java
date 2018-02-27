package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2018/2/26
 * @detail
 */

public class PluginBean implements Serializable {
    private String label,pkgName;
    public PluginBean() {
    }

    public PluginBean(String label, String pkgName) {
        this.label = label;
        this.pkgName = pkgName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
