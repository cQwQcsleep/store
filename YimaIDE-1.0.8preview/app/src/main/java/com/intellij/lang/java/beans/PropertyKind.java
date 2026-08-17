package com.intellij.lang.java.beans;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public enum PropertyKind {
    GETTER("get"),
    BOOLEAN_GETTER("is"),
    SETTER("set");

    public final String prefix;

    PropertyKind(String str) {
        this.prefix = str;
    }
}
