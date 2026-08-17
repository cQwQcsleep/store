package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2392q1 implements H20, Serializable {
    public boolean a(Object obj, boolean z) {
        throw new UnsupportedOperationException();
    }

    public boolean b(Object obj) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        if (containsKey(obj)) {
            return Boolean.valueOf(a(obj));
        }
        return null;
    }

    public final Object put(Object obj, Object obj2) {
        boolean zContainsKey = containsKey(obj);
        boolean zA = a(obj, ((Boolean) obj2).booleanValue());
        if (zContainsKey) {
            return Boolean.valueOf(zA);
        }
        return null;
    }

    public final Object remove(Object obj) {
        boolean zContainsKey = containsKey(obj);
        boolean zB = b(obj);
        if (zContainsKey) {
            return Boolean.valueOf(zB);
        }
        return null;
    }
}
