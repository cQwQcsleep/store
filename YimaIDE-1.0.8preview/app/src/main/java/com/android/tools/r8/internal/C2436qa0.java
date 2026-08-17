package com.android.tools.r8.internal;

import defpackage.m71;
import java.util.Arrays;
import java.util.Stack;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2436qa0 {
    public final Stack a = new Stack();

    public final void a(T7 t7) {
        if (!t7.b()) {
            if (!(t7 instanceof C2692ta0)) {
                String strValueOf = String.valueOf(t7.getClass());
                m71.a(strValueOf.length() + 49, strValueOf);
                return;
            } else {
                C2692ta0 c2692ta0 = (C2692ta0) t7;
                a(c2692ta0.e);
                a(c2692ta0.f);
                return;
            }
        }
        int size = t7.size();
        int[] iArr = C2692ta0.j;
        int iBinarySearch = Arrays.binarySearch(iArr, size);
        if (iBinarySearch < 0) {
            iBinarySearch = (-(iBinarySearch + 1)) - 1;
        }
        int i = iArr[iBinarySearch + 1];
        if (this.a.isEmpty() || ((T7) this.a.peek()).size() >= i) {
            this.a.push(t7);
            return;
        }
        int i2 = iArr[iBinarySearch];
        T7 c2692ta1 = (T7) this.a.pop();
        while (!this.a.isEmpty() && ((T7) this.a.peek()).size() < i2) {
            c2692ta1 = new C2692ta0((T7) this.a.pop(), c2692ta1);
        }
        C2692ta0 c2692ta2 = new C2692ta0(c2692ta1, t7);
        while (!this.a.isEmpty()) {
            int i3 = c2692ta2.d;
            int[] iArr2 = C2692ta0.j;
            int iBinarySearch2 = Arrays.binarySearch(iArr2, i3);
            if (iBinarySearch2 < 0) {
                iBinarySearch2 = (-(iBinarySearch2 + 1)) - 1;
            }
            if (((T7) this.a.peek()).size() >= iArr2[iBinarySearch2 + 1]) {
                break;
            } else {
                c2692ta2 = new C2692ta0((T7) this.a.pop(), c2692ta2);
            }
        }
        this.a.push(c2692ta2);
    }
}
