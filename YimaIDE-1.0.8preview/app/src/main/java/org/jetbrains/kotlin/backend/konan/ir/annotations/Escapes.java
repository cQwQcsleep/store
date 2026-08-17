package org.jetbrains.kotlin.backend.konan.ir.annotations;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.text.CharsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u001a\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003Ê\u0001\u0002\b\u001c¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/ir/annotations/Escapes;", "", "mask", "", "constructor-impl", "(I)I", "signatureSize", "(II)I", "assertIsValidFor", "", "assertIsValidFor-impl", "(II)V", "escapesAt", "", "index", "escapesAt-impl", "(II)Z", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "org.jetbrains.kotlin:ir.backend.native", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class Escapes {
    private final int mask;

    private /* synthetic */ Escapes(int i) {
        this.mask = i;
    }

    /* JADX INFO: renamed from: assertIsValidFor-impl, reason: not valid java name */
    public static final void m2000assertIsValidForimpl(int i, int i2) {
        if (i < 0 || (i >> i2) != 0) {
            throw new IllegalArgumentException((((Object) m2008toStringimpl(i)) + " must not be negative and not have bits higher than " + i2).toString());
        }
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Escapes m2001boximpl(int i) {
        return new Escapes(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2003constructorimpl(int i, int i2) {
        int iM2002constructorimpl = m2002constructorimpl(i);
        m2000assertIsValidForimpl(iM2002constructorimpl, i2);
        return iM2002constructorimpl;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2004equalsimpl(int i, Object obj) {
        return (obj instanceof Escapes) && i == ((Escapes) obj).getMask();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2005equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: escapesAt-impl, reason: not valid java name */
    public static final boolean m2006escapesAtimpl(int i, int i2) {
        return ((i >> i2) & 1) == 1;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2007hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2008toStringimpl(int i) {
        StringBuilder sb = new StringBuilder("0b");
        String string = Integer.toString(i, CharsKt.checkRadix(2));
        string.getClass();
        sb.append(string);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return m2004equalsimpl(this.mask, obj);
    }

    public int hashCode() {
        return m2007hashCodeimpl(this.mask);
    }

    public String toString() {
        return m2008toStringimpl(this.mask);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getMask() {
        return this.mask;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2002constructorimpl(int i) {
        return i;
    }
}
