package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G4 {
    public int a;
    public H4[] b = new H4[6];

    public final void a(H4 h4) {
        while (h4 != null) {
            int i = 0;
            while (true) {
                int i2 = this.a;
                H4[] h4Arr = this.b;
                if (i >= i2) {
                    if (i2 >= h4Arr.length) {
                        H4[] h4Arr2 = new H4[h4Arr.length + 6];
                        System.arraycopy(h4Arr, 0, h4Arr2, 0, i2);
                        this.b = h4Arr2;
                    }
                    H4[] h4Arr3 = this.b;
                    int i3 = this.a;
                    this.a = i3 + 1;
                    h4Arr3[i3] = h4;
                    break;
                }
                if (h4Arr[i].a.equals(h4.a)) {
                    break;
                } else {
                    i++;
                }
            }
            h4 = h4.c;
        }
    }
}
