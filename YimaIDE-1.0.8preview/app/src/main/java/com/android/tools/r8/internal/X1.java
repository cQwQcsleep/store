package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Set;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.android.tools.r8.internal.X1[], still in use, count: 1, list:
  (r0v1 com.android.tools.r8.internal.X1[]) from 0x002e: INVOKE (r0v1 com.android.tools.r8.internal.X1[]) VIRTUAL call: java.lang.Object.clone():java.lang.Object A[MD:():java.lang.Object throws java.lang.CloneNotSupportedException (c), WRAPPED]
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X1 {
    b,
    c,
    d,
    e;

    public static final X40 f;

    static {
        X40 x40;
        X1[] x1Arr = (X1[]) x1Arr.clone();
        int i = AbstractC3067xv.g;
        C2767uQ c2767uQ = C2767uQ.b;
        int length = x1Arr.length;
        Comparable[] comparableArr = (Comparable[]) x1Arr.clone();
        if (length == 0) {
            x40 = AbstractC3067xv.a(c2767uQ);
        } else {
            AbstractC2856vU.a(length, comparableArr);
            Arrays.sort(comparableArr, 0, length, c2767uQ);
            int i2 = 1;
            for (int i3 = 1; i3 < length; i3++) {
                Comparable comparable = comparableArr[i3];
                if (c2767uQ.compare(comparable, comparableArr[i2 - 1]) != 0) {
                    comparableArr[i2] = comparable;
                    i2++;
                }
            }
            Arrays.fill(comparableArr, i2, length, (Object) null);
            x40 = new X40(AbstractC0551Hu.b(i2, comparableArr), c2767uQ);
        }
        f = x40;
    }

    public X1() {
        super(str, i);
    }

    public final String a() {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return "public";
        }
        if (iOrdinal == 1) {
            return "protected";
        }
        if (iOrdinal == 2) {
            defpackage.l0.a("No source syntax for package-private visibility.");
            return null;
        }
        if (iOrdinal == 3) {
            return "private";
        }
        throw new C2499rF("Unexpected access visibility: " + this);
    }

    public static boolean a(Set set) {
        return set.size() == ((X1[]) g.clone()).length;
    }
}
