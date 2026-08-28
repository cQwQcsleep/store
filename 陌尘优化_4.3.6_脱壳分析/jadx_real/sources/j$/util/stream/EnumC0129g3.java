package j$.util.stream;

import j$.util.InterfaceC0089m;
import java.util.EnumMap;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DISTINCT' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* renamed from: j$.util.stream.g3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class EnumC0129g3 {
    public static final EnumC0129g3 DISTINCT;
    public static final EnumC0129g3 ORDERED;
    public static final EnumC0129g3 SHORT_CIRCUIT;
    public static final EnumC0129g3 SIZED;
    public static final EnumC0129g3 SORTED;
    static final int f;
    static final int g;
    static final int h;
    private static final int i;
    private static final int j;
    private static final int k;
    static final int l;
    static final int m;
    static final int n;
    static final int o;
    static final int p;
    static final int q;
    static final int r;
    static final int s;
    static final int t;
    static final int u;
    private static final /* synthetic */ EnumC0129g3[] v;
    private final EnumMap a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public static EnumC0129g3 valueOf(String str) {
        return (EnumC0129g3) Enum.valueOf(EnumC0129g3.class, str);
    }

    public static EnumC0129g3[] values() {
        return (EnumC0129g3[]) v.clone();
    }

    static {
        EnumC0124f3 enumC0124f3 = EnumC0124f3.SPLITERATOR;
        C0119e3 c0119e3V = v(enumC0124f3);
        EnumC0124f3 enumC0124f32 = EnumC0124f3.STREAM;
        c0119e3V.a(enumC0124f32);
        EnumC0124f3 enumC0124f33 = EnumC0124f3.OP;
        c0119e3V.a.put((EnumMap) enumC0124f33, (EnumC0124f3) 3);
        EnumC0129g3 enumC0129g3 = new EnumC0129g3("DISTINCT", 0, 0, c0119e3V);
        DISTINCT = enumC0129g3;
        C0119e3 c0119e3V2 = v(enumC0124f3);
        c0119e3V2.a(enumC0124f32);
        c0119e3V2.a.put((EnumMap) enumC0124f33, (EnumC0124f3) 3);
        EnumC0129g3 enumC0129g32 = new EnumC0129g3("SORTED", 1, 1, c0119e3V2);
        SORTED = enumC0129g32;
        C0119e3 c0119e3V3 = v(enumC0124f3);
        c0119e3V3.a(enumC0124f32);
        EnumMap enumMap = c0119e3V3.a;
        enumMap.put((EnumMap) enumC0124f33, (EnumC0124f3) 3);
        EnumC0124f3 enumC0124f34 = EnumC0124f3.TERMINAL_OP;
        enumMap.put((EnumMap) enumC0124f34, (EnumC0124f3) 2);
        EnumC0124f3 enumC0124f35 = EnumC0124f3.UPSTREAM_TERMINAL_OP;
        enumMap.put((EnumMap) enumC0124f35, (EnumC0124f3) 2);
        EnumC0129g3 enumC0129g33 = new EnumC0129g3("ORDERED", 2, 2, c0119e3V3);
        ORDERED = enumC0129g33;
        C0119e3 c0119e3V4 = v(enumC0124f3);
        c0119e3V4.a(enumC0124f32);
        c0119e3V4.a.put((EnumMap) enumC0124f33, (EnumC0124f3) 2);
        EnumC0129g3 enumC0129g34 = new EnumC0129g3("SIZED", 3, 3, c0119e3V4);
        SIZED = enumC0129g34;
        C0119e3 c0119e3V5 = v(enumC0124f33);
        c0119e3V5.a(enumC0124f34);
        EnumC0129g3 enumC0129g35 = new EnumC0129g3("SHORT_CIRCUIT", 4, 12, c0119e3V5);
        SHORT_CIRCUIT = enumC0129g35;
        v = new EnumC0129g3[]{enumC0129g3, enumC0129g32, enumC0129g33, enumC0129g34, enumC0129g35};
        f = k(enumC0124f3);
        g = k(enumC0124f32);
        h = k(enumC0124f33);
        k(enumC0124f34);
        k(enumC0124f35);
        int i2 = 0;
        for (EnumC0129g3 enumC0129g36 : values()) {
            i2 |= enumC0129g36.e;
        }
        i = i2;
        int i3 = g;
        j = i3;
        int i4 = i3 << 1;
        k = i4;
        l = i3 | i4;
        EnumC0129g3 enumC0129g37 = DISTINCT;
        m = enumC0129g37.c;
        n = enumC0129g37.d;
        EnumC0129g3 enumC0129g38 = SORTED;
        o = enumC0129g38.c;
        p = enumC0129g38.d;
        EnumC0129g3 enumC0129g39 = ORDERED;
        q = enumC0129g39.c;
        r = enumC0129g39.d;
        EnumC0129g3 enumC0129g310 = SIZED;
        s = enumC0129g310.c;
        t = enumC0129g310.d;
        u = SHORT_CIRCUIT.c;
    }

    private static C0119e3 v(EnumC0124f3 enumC0124f3) {
        C0119e3 c0119e3 = new C0119e3(new EnumMap(EnumC0124f3.class));
        c0119e3.a(enumC0124f3);
        return c0119e3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EnumC0129g3(String str, int i2, int i3, C0119e3 c0119e3) {
        EnumC0124f3[] enumC0124f3ArrValues = EnumC0124f3.values();
        int length = enumC0124f3ArrValues.length;
        int i4 = 0;
        while (true) {
            EnumMap enumMap = c0119e3.a;
            if (i4 >= length) {
                this.a = enumMap;
                int i5 = i3 * 2;
                this.b = i5;
                this.c = 1 << i5;
                this.d = 2 << i5;
                this.e = 3 << i5;
                return;
            }
            EnumC0124f3 enumC0124f3 = enumC0124f3ArrValues[i4];
            if (enumMap instanceof InterfaceC0089m) {
                ((InterfaceC0089m) enumMap).putIfAbsent(enumC0124f3, 0);
            } else if (enumMap.get(enumC0124f3) == null) {
                enumMap.put((EnumMap) enumC0124f3, (EnumC0124f3) 0);
            }
            i4++;
        }
    }

    final boolean n(int i2) {
        return (i2 & this.e) == this.c;
    }

    final boolean s(int i2) {
        int i3 = this.e;
        return (i2 & i3) == i3;
    }

    private static int k(EnumC0124f3 enumC0124f3) {
        int iIntValue = 0;
        for (EnumC0129g3 enumC0129g3 : values()) {
            iIntValue |= ((Integer) enumC0129g3.a.get(enumC0124f3)).intValue() << enumC0129g3.b;
        }
        return iIntValue;
    }

    static int j(int i2, int i3) {
        int i4;
        if (i2 == 0) {
            i4 = i;
        } else {
            i4 = ~(((j & i2) << 1) | i2 | ((k & i2) >> 1));
        }
        return i2 | (i3 & i4);
    }

    static int w(int i2) {
        return i2 & ((~i2) >> 1) & j;
    }

    static int m(j$.util.U u2) {
        int iCharacteristics = u2.characteristics();
        int i2 = iCharacteristics & 4;
        int i3 = f;
        return (i2 == 0 || u2.getComparator() == null) ? iCharacteristics & i3 : iCharacteristics & i3 & (-5);
    }
}
