package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1476fH;
import com.android.tools.r8.internal.OW;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class OW {
    public static final /* synthetic */ boolean d = true;
    public final C3097yF a;
    public final TG b;
    public final C1902kH c;

    public OW(C3097yF c3097yF, TG tg, C1902kH c1902kH) {
        this.a = c3097yF;
        this.b = tg;
        this.c = c1902kH;
    }

    public static void a(List list) {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        list.forEach(new Consumer() { // from class: ana
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                IdentityHashMap identityHashMap2 = identityHashMap;
                identityHashMap2.put((OW) obj, Integer.valueOf(identityHashMap2.size()));
            }
        });
        list.sort(Comparator.comparingInt(new ToIntFunction() { // from class: bna
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return OW.a((OW) obj);
            }
        }).thenComparingInt(new ToIntFunction() { // from class: cna
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return OW.b((OW) obj);
            }
        }).thenComparingInt(new ToIntFunction() { // from class: dna
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) identityHashMap.get((OW) obj)).intValue();
            }
        }));
    }

    public abstract String b();

    public void b(StringBuilder sb) {
        int iOrdinal;
        String str;
        C3097yF c3097yF = this.a;
        if (!C2756uF.a.equals(c3097yF.a)) {
            sb.append("# context: ");
            sb.append(c3097yF.a.a());
            sb.append('\n');
        }
        if (!C2841vF.b.equals(c3097yF.b)) {
            String strC = AbstractC3035xa0.c(c3097yF.b.a);
            sb.append("# description: ");
            sb.append(strC);
            sb.append('\n');
        }
        if (d()) {
            sb.append("-if");
            sb.append(' ');
            a(sb);
            List<HE> listA = a();
            if (!listA.isEmpty()) {
                sb.append(" {");
                for (HE he : listA) {
                    sb.append(' ');
                    a(sb, he);
                }
                sb.append(" }");
            }
            sb.append(' ');
        }
        sb.append(b());
        TG tg = this.b;
        for (SG sg : (SG[]) SG.g.clone()) {
            if (tg.a.contains(sg) && ((iOrdinal = sg.ordinal()) == 3 || iOrdinal != 4)) {
                sb.append(",allow");
                int iOrdinal2 = sg.ordinal();
                if (iOrdinal2 == 0) {
                    str = "shrinking";
                } else if (iOrdinal2 == 1) {
                    str = "optimization";
                } else if (iOrdinal2 == 2) {
                    str = "obfuscation";
                } else if (iOrdinal2 == 3) {
                    str = "accessmodification";
                } else {
                    if (iOrdinal2 != 4) {
                        throw new C1261ck0();
                    }
                    str = "annotationremoval";
                }
                sb.append(str);
            }
        }
        sb.append(' ');
        c(sb);
        List<HE> listC = c();
        if (listC.isEmpty()) {
            return;
        }
        sb.append(" {");
        for (HE he2 : listC) {
            sb.append(' ');
            b(sb, he2);
        }
        sb.append(" }");
    }

    public abstract void b(StringBuilder sb, HE he);

    public abstract List c();

    public abstract void c(StringBuilder sb);

    public boolean d() {
        return this instanceof JW;
    }

    public static /* synthetic */ int a(OW ow) {
        return ow.d() ? 1 : 0;
    }

    public static BiConsumer a(final C1476fH c1476fH) {
        return new BiConsumer() { // from class: zma
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                OW.a(c1476fH, (StringBuilder) obj, (C1476fH) obj2);
            }
        };
    }

    public static void a(C1476fH c1476fH, StringBuilder sb, C1476fH c1476fH2) {
        if (d || c1476fH2.equals(c1476fH)) {
            AbstractC3035xa0.b(new C2949wa0(sb), c1476fH);
        } else {
            x1f.a();
        }
    }

    public List a() {
        throw new C2499rF("Unreachable");
    }

    public void a(StringBuilder sb) {
        throw new C2499rF("Unreachable");
    }

    public void a(StringBuilder sb, HE he) {
        throw new C2499rF("Unreachable");
    }

    public static /* synthetic */ int b(OW ow) {
        String strB = ow.b();
        strB.getClass();
        switch (strB) {
            case "-keepclasseswithmembers":
                return 2;
            case "-keepattributes":
                return 0;
            case "-checkdiscard":
                return 4;
            case "-keep":
                return 1;
            case "-keepclassmembers":
                return 3;
            default:
                throw new C2499rF("Unexpected consequence keep type: " + ow.b());
        }
    }
}
