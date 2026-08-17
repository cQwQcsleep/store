package com.reandroid.dex.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegisterType {
    private final String name;
    public static final RegisterType READ = new RegisterType("READ");
    public static final RegisterType WRITE = new RegisterType("WRITE");
    public static final RegisterType RW = new RegisterType("RW");

    private RegisterType(String str) {
        this.name = str;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean is(RegisterType registerType) {
        RegisterType registerType2;
        if (registerType == null) {
            return false;
        }
        return registerType == this || registerType == (registerType2 = RW) || this == registerType2;
    }

    public String toString() {
        return this.name;
    }
}
