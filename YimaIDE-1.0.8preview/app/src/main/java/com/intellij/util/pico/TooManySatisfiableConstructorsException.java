package com.intellij.util.pico;

import java.lang.reflect.Constructor;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class TooManySatisfiableConstructorsException extends PicoIntrospectionException {
    private final Collection<Constructor<?>> constructors;

    public TooManySatisfiableConstructorsException(Collection<Constructor<?>> collection) {
        super("Too many satisfiable constructors:" + collection.toString());
        this.constructors = collection;
    }
}
