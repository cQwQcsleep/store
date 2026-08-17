package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1966l20 {
    public AbstractC1966l20 a;

    public AbstractC1966l20(int i, AbstractC1966l20 abstractC1966l20) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            w01.a(CX.a(i, "Unsupported api "));
            throw null;
        }
        if (i == 17432576) {
            ug.a(this);
        }
        this.a = abstractC1966l20;
    }

    public abstract J2 a(int i, C3052xj0 c3052xj0, String str, boolean z);

    public abstract J2 a(String str, boolean z);

    public abstract void a();

    public void a(H4 h4) {
        AbstractC1966l20 abstractC1966l20 = this.a;
        if (abstractC1966l20 != null) {
            abstractC1966l20.a(h4);
        }
    }
}
