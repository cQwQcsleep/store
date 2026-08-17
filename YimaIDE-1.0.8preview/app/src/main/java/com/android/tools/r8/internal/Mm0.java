package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Mm0 {
    public static final Mm0 d = new Mm0("INT32", 4, Om0.c, 0);
    public static final Mm0 e = new Mm0("BOOL", 7, Om0.g, 0);
    public static final Gm0 f;
    public static final Im0 g;
    public static final Mm0 h;
    public final Om0 b;
    public final int c;

    static {
        new Em0();
        f = new Gm0();
        g = new Im0();
        new Mm0() { // from class: com.android.tools.r8.internal.Km0
            {
                Om0 om0 = Om0.i;
            }

            @Override // com.android.tools.r8.internal.Mm0
            public final boolean a() {
                return false;
            }
        };
        h = new Mm0("ENUM", 13, Om0.j, 0);
    }

    public Mm0(String str, int i, Om0 om0, int i2) {
        super(str, i);
        this.b = om0;
        this.c = i2;
    }

    public boolean a() {
        return !(this instanceof Em0);
    }

    public Mm0(String str, int i, Om0 om0, int i2, int i3) {
        super(str, i);
        this.b = om0;
        this.c = i2;
    }
}
