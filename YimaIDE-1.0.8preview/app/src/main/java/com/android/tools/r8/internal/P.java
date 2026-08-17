package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class P implements InterfaceC1109az, Serializable {
    public Object b;

    @Override // java.util.Map
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Object put(Integer num, Object obj) {
        int iIntValue = num.intValue();
        boolean zA = a(iIntValue);
        Object objA = a(iIntValue, obj);
        if (zA) {
            return objA;
        }
        return null;
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
    public Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (a(iIntValue)) {
            return get(iIntValue);
        }
        return null;
    }

    public Object remove(Object obj) {
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

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public Object a(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public Object remove(int i) {
        throw new UnsupportedOperationException();
    }
}
