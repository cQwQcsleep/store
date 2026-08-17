package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class K implements InterfaceC0917Vx, Serializable {
    public Object b;

    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public Object a(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    public abstract void clear();

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return a(((Integer) obj).intValue());
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (a(iIntValue)) {
            return get(iIntValue);
        }
        return null;
    }

    public final Object put(Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        boolean zA = a(iIntValue);
        Object objA = a(iIntValue, obj2);
        if (zA) {
            return objA;
        }
        return null;
    }

    public final Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        boolean zA = a(iIntValue);
        Object objRemove = remove(iIntValue);
        if (zA) {
            return objRemove;
        }
        return null;
    }

    public Object remove(int i) {
        throw new UnsupportedOperationException();
    }
}
