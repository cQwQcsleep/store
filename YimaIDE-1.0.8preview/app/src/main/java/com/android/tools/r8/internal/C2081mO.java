package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2081mO {
    public static final C2081mO c = new C2081mO(null, null);
    public static final C2081mO d = new C2081mO(null, null);
    public static final /* synthetic */ boolean e = true;
    public final C2628sl0 a;
    public final C2628sl0[] b;

    public C2081mO(C2628sl0 c2628sl0, C2628sl0[] c2628sl0Arr) {
        this.a = c2628sl0;
        this.b = c2628sl0Arr;
    }

    public final boolean a() {
        return this == c;
    }

    public final boolean b() {
        return this == d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MethodBoxingStatus[");
        if (b()) {
            sb.append("UNPROCESSED_CANDIDATE");
        } else if (a()) {
            sb.append("NONE_UNBOXABLE");
        } else {
            int i = 0;
            while (true) {
                C2628sl0[] c2628sl0Arr = this.b;
                if (i >= c2628sl0Arr.length) {
                    break;
                }
                if (!c2628sl0Arr[i].a()) {
                    sb.append(i);
                    sb.append(":");
                    sb.append(this.b[i]);
                    sb.append(";");
                }
                i++;
            }
            if (!this.a.a()) {
                sb.append("ret:");
                sb.append(this.a);
                sb.append(";");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
