package com.sun.tools.javap;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Context {
    Map<Class<?>, Object> map = new HashMap();

    public <T> T get(Class<T> cls) {
        return (T) this.map.get(cls);
    }

    public <T> T put(Class<T> cls, T t) {
        return (T) this.map.put(cls, t);
    }
}
