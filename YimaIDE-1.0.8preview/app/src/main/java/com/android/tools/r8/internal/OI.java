package com.android.tools.r8.internal;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.android.tools.r8.internal.OI[], still in use, count: 1, list:
  (r0v1 com.android.tools.r8.internal.OI[]) from 0x0030: CONSTRUCTOR (r1v2 com.android.tools.r8.internal.xn) = (r0v1 com.android.tools.r8.internal.OI[]) A[MD:(java.lang.Enum[]):void (m)] call: com.android.tools.r8.internal.xn.<init>(java.lang.Enum[]):void type: CONSTRUCTOR
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
public final class OI {
    b,
    c,
    d,
    e;

    static {
        new C3059xn(oiArr);
    }

    public OI() {
        super(str, i);
    }
}
