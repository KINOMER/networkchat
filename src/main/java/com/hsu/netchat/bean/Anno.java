package com.hsu.netchat.bean;

public class Anno {
    private String anno;

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno == null ? null : anno.trim();
    }
}