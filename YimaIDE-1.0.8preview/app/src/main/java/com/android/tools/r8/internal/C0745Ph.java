package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ph, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0745Ph {
    public static final /* synthetic */ boolean c = true;
    public final int a;
    public final InterfaceC0763Pz b;

    public C0745Ph(int i, C0944Wy c0944Wy) {
        if (!c && i < 0) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = c0944Wy;
    }

    public final String a(boolean z) {
        StringBuilder sb = new StringBuilder("params:");
        sb.append(this.a);
        sb.append('\n');
        InterfaceC0763Pz interfaceC0763Pz = this.b;
        if (interfaceC0763Pz != null) {
            BU it = interfaceC0763Pz.values().iterator();
            while (it.hasNext()) {
                sb.append((C0849Th) it.next());
                sb.append('\n');
            }
        } else {
            sb.append(" no conversions\n");
        }
        return sb.toString();
    }

    public final String toString() {
        return a(false);
    }
}
