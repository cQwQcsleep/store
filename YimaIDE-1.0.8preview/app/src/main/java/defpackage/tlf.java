package defpackage;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class tlf {
    public static final a c = new a(null);
    public static final int d = 8;
    public int a;
    public int b;

    public final synchronized boolean a() {
        int i = this.b;
        if (i >= 3) {
            return false;
        }
        this.b = i + 1;
        return true;
    }

    public final synchronized boolean b() {
        int i = this.a;
        if (i >= 3) {
            return false;
        }
        this.a = i + 1;
        return true;
    }

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }
}
