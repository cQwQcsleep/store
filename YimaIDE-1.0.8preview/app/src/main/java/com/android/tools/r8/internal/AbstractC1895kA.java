package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1895kA {
    public static final C1726iA a = new C1726iA();

    public static int a(InterfaceC1640hA interfaceC1640hA, int[] iArr) {
        int i;
        int length = iArr.length;
        int i2 = 0;
        if (length < 0) {
            w01.a(AbstractC1784iv.a(length, "The maximum number of elements (", ") is negative"));
            return 0;
        }
        if (length > iArr.length) {
            j2d.a();
            return 0;
        }
        int i3 = length;
        while (true) {
            i = i3 - 1;
            if (i3 == 0 || !interfaceC1640hA.hasNext()) {
                break;
            }
            iArr[i2] = interfaceC1640hA.q();
            i2++;
            i3 = i;
        }
        return (length - i) - 1;
    }
}
