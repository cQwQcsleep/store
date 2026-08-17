package com.android.tools.r8.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ng, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0692Ng {
    public final Map a;
    public final boolean b = true;
    public final List c;

    public C0692Ng(List list, Map map) {
        this.a = map;
        this.c = list;
    }

    public final AU a(Fj0 fj0) {
        AU c0615Kg;
        String str;
        AU c0641Lg;
        AU c0485Fg;
        Type type = fj0.b;
        Class cls = fj0.a;
        if (this.a.get(type) != null) {
            throw new ClassCastException();
        }
        if (this.a.get(cls) != null) {
            throw new ClassCastException();
        }
        String strConcat = null;
        if (EnumSet.class.isAssignableFrom(cls)) {
            c0615Kg = new C0589Jg(type);
        } else {
            c0615Kg = cls == EnumMap.class ? new C0615Kg(type) : null;
        }
        if (c0615Kg != null) {
            return c0615Kg;
        }
        Iterator it = this.c.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
        if (Modifier.isAbstract(cls.getModifiers())) {
            c0641Lg = null;
        } else {
            try {
                Constructor declaredConstructor = cls.getDeclaredConstructor(null);
                AbstractC3082y40 abstractC3082y40 = B40.a;
                try {
                    declaredConstructor.setAccessible(true);
                    str = null;
                } catch (Exception e) {
                    str = "Failed making constructor '" + B40.a(declaredConstructor) + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e.getMessage();
                }
                c0641Lg = str != null ? new C0641Lg(str) : new C0666Mg(declaredConstructor);
            } catch (NoSuchMethodException unused) {
                c0641Lg = null;
            }
        }
        if (c0641Lg != null) {
            return c0641Lg;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                c0485Fg = new C3045xg();
            } else if (Set.class.isAssignableFrom(cls)) {
                c0485Fg = new C3129yg();
            } else {
                c0485Fg = Queue.class.isAssignableFrom(cls) ? new C3215zg() : new C0355Ag();
            }
        } else if (!Map.class.isAssignableFrom(cls)) {
            c0485Fg = null;
        } else if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
            c0485Fg = new C0381Bg();
        } else if (ConcurrentMap.class.isAssignableFrom(cls)) {
            c0485Fg = new C0407Cg();
        } else if (SortedMap.class.isAssignableFrom(cls)) {
            c0485Fg = new C0433Dg();
        } else {
            c0485Fg = (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new Fj0(((ParameterizedType) type).getActualTypeArguments()[0]).a)) ? new C0485Fg() : new C0459Eg();
        }
        if (c0485Fg != null) {
            return c0485Fg;
        }
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            strConcat = "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(cls.getName());
        } else if (Modifier.isAbstract(modifiers)) {
            strConcat = "Abstract classes can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Class name: ".concat(cls.getName());
        }
        if (strConcat != null) {
            return new C0563Ig(strConcat);
        }
        if (this.b) {
            return new C0511Gg(cls);
        }
        return new C0537Hg("Unable to create instance of " + cls + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.");
    }

    public final String toString() {
        return this.a.toString();
    }
}
