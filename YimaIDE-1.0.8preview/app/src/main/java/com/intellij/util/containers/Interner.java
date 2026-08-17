package com.intellij.util.containers;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class Interner<T> {
    public static <T> Interner<T> createInterner() {
        return new HashSetInterner();
    }

    public static Interner<String> createStringInterner() {
        return createInterner();
    }

    public static <T> Interner<T> createWeakInterner() {
        return new WeakInterner();
    }

    public abstract void clear();

    public abstract Set<T> getValues();

    public abstract T intern(T t);
}
