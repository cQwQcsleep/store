package com.android.tools.r8.internal;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF2' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Nm0 {
    public static final Hm0 d;
    public static final Nm0 e;
    public static final /* synthetic */ Nm0[] f;
    public final Pm0 b;
    public final int c;

    /* JADX INFO: Fake field, exist only in values array */
    Nm0 EF0;

    /* JADX INFO: Fake field, exist only in values array */
    Nm0 EF1;

    /* JADX INFO: Fake field, exist only in values array */
    Nm0 EF2;

    static {
        Nm0 nm0 = new Nm0("DOUBLE", 0, Pm0.f, 1);
        Nm0 nm1 = new Nm0("FLOAT", 1, Pm0.e, 5);
        Pm0 pm0 = Pm0.d;
        Nm0 nm2 = new Nm0("INT64", 2, pm0, 0);
        Nm0 nm3 = new Nm0("UINT64", 3, pm0, 0);
        Pm0 pm1 = Pm0.c;
        Nm0 nm4 = new Nm0("INT32", 4, pm1, 0);
        Nm0 nm5 = new Nm0("FIXED64", 5, pm0, 1);
        Nm0 nm6 = new Nm0("FIXED32", 6, pm1, 5);
        Nm0 nm7 = new Nm0("BOOL", 7, Pm0.g, 0);
        Fm0 fm0 = new Fm0();
        final Pm0 pm2 = Pm0.k;
        Hm0 hm0 = new Hm0(pm2);
        d = hm0;
        Nm0 nm8 = new Nm0(pm2) { // from class: com.android.tools.r8.internal.Jm0
            @Override // com.android.tools.r8.internal.Nm0
            public final boolean a() {
                return false;
            }
        };
        final Pm0 pm3 = Pm0.i;
        Nm0 nm9 = new Nm0(pm3) { // from class: com.android.tools.r8.internal.Lm0
            @Override // com.android.tools.r8.internal.Nm0
            public final boolean a() {
                return false;
            }
        };
        Nm0 nm10 = new Nm0("UINT32", 12, pm1, 0);
        Nm0 nm11 = new Nm0("ENUM", 13, Pm0.j, 0);
        e = nm11;
        f = new Nm0[]{nm0, nm1, nm2, nm3, nm4, nm5, nm6, nm7, fm0, hm0, nm8, nm9, nm10, nm11, new Nm0("SFIXED32", 14, pm1, 5), new Nm0("SFIXED64", 15, pm0, 1), new Nm0("SINT32", 16, pm1, 0), new Nm0("SINT64", 17, pm0, 0)};
    }

    public Nm0(String str, int i, Pm0 pm0, int i2) {
        super(str, i);
        this.b = pm0;
        this.c = i2;
    }

    public boolean a() {
        return !(this instanceof Fm0);
    }

    public Nm0(String str, int i, Pm0 pm0, int i2, int i3) {
        super(str, i);
        this.b = pm0;
        this.c = i2;
    }
}
