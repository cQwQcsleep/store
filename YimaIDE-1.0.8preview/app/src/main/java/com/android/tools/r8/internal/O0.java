package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O0 implements WP {
    public transient Collection b;
    public transient Set c;
    public transient InterfaceC1231cQ d;
    public transient Map e;

    @Override // com.android.tools.r8.internal.WP
    public Map b() {
        Map map = this.e;
        if (map != null) {
            return map;
        }
        Map mapD = d();
        this.e = mapD;
        return mapD;
    }

    @Override // com.android.tools.r8.internal.WP
    public InterfaceC1231cQ c() {
        InterfaceC1231cQ interfaceC1231cQ = this.d;
        if (interfaceC1231cQ != null) {
            return interfaceC1231cQ;
        }
        InterfaceC1231cQ interfaceC1231cQF = f();
        this.d = interfaceC1231cQF;
        return interfaceC1231cQF;
    }

    public abstract Map d();

    public abstract Set e();

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WP) {
            return b().equals(((WP) obj).b());
        }
        return false;
    }

    public abstract InterfaceC1231cQ f();

    public abstract Iterator g();

    public Spliterator h() {
        return Spliterators.spliterator(g(), size(), 0);
    }

    public final int hashCode() {
        return b().hashCode();
    }

    @Override // com.android.tools.r8.internal.WP
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.android.tools.r8.internal.WP
    public Set keySet() {
        Set set = this.c;
        if (set != null) {
            return set;
        }
        Set setE = e();
        this.c = setE;
        return setE;
    }

    @Override // com.android.tools.r8.internal.WP
    public boolean remove(Object obj, Object obj2) {
        Collection collection = (Collection) b().get(obj);
        return collection != null && collection.remove(obj2);
    }

    public final String toString() {
        return b().toString();
    }
}
