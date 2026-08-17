package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2167nP {
    public final AbstractC2167nP a;

    public AbstractC2167nP(int i, AbstractC2167nP abstractC2167nP) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            w01.a(CX.a(i, "Unsupported api "));
            throw null;
        }
        if (i == 17432576) {
            ug.a(this);
        }
        this.a = abstractC2167nP;
    }

    public abstract void a();

    public abstract void a(int i, String str, String str2);

    public abstract void a(String str);

    public abstract void a(String str, int i, String... strArr);

    public abstract void a(String str, String... strArr);

    public abstract void b(String str);

    public abstract void b(String str, int i, String... strArr);

    public abstract void c(String str);
}
