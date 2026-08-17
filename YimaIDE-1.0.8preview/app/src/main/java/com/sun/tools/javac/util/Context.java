package com.sun.tools.javac.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Context {
    protected final Map<Key<?>, Object> ht = new HashMap();
    private final Map<Key<?>, Factory<?>> ft = new HashMap();
    private final Map<Class<?>, Key<?>> kt = new HashMap();

    public interface Factory<T> {
        T make(Context context);
    }

    public static class Key<T> {
    }

    private static void checkState(Map<?, ?> map) {
        if (map != null) {
            return;
        }
        g33.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T uncheckedCast(Object obj) {
        return obj;
    }

    public void dump() {
        Iterator<Object> it = this.ht.values().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            System.err.println(next == null ? null : next.getClass());
        }
    }

    public <T> T get(Key<T> key) {
        checkState(this.ht);
        Object objMake = this.ht.get(key);
        if (objMake instanceof Factory) {
            objMake = ((Factory) objMake).make(this);
            if (objMake instanceof Factory) {
                x01.a("T extends Context.Factory");
                return null;
            }
            Assert.check(this.ht.get(key) == objMake);
        }
        return (T) uncheckedCast(objMake);
    }

    public <T> Key<T> key(Class<T> cls) {
        checkState(this.kt);
        Key<T> key = (Key) uncheckedCast(this.kt.get(cls));
        if (key != null) {
            return key;
        }
        Key<T> key2 = new Key<>();
        this.kt.put(cls, key2);
        return key2;
    }

    public <T> void put(Key<T> key, T t) {
        if (t instanceof Factory) {
            x01.a("T extends Context.Factory");
            return;
        }
        checkState(this.ht);
        Object objPut = this.ht.put(key, t);
        if (objPut == null || (objPut instanceof Factory) || objPut == t || t == null) {
            return;
        }
        x01.a("duplicate context value");
    }

    public <T> void put(Key<T> key, Factory<T> factory) {
        checkState(this.ht);
        if (this.ht.put(key, factory) == null) {
            checkState(this.ft);
            this.ft.put(key, factory);
        } else {
            x01.a("duplicate context value");
        }
    }

    public <T> void put(Class<T> cls, T t) {
        put(key(cls), t);
    }

    public <T> void put(Class<T> cls, Factory<T> factory) {
        put((Key) key(cls), (Factory) factory);
    }

    public <T> T get(Class<T> cls) {
        return (T) get(key(cls));
    }
}
