package org.jetbrains.kotlin.backend.konan.ir.annotations;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.jetbrains.kotlin.backend.konan.ir.annotations.PointsTo;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001f\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\u0007HÖ\u0081\u0004¢\u0006\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003Ê\u0001\u0002\b!¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/ir/annotations/PointsTo;", "", "elements", "", "constructor-impl", "([I)[I", "", "", "signatureSize", "(Ljava/util/List;I)[I", "assertIsValidFor", "", "assertIsValidFor-impl", "([II)V", "kind", "Lorg/jetbrains/kotlin/backend/konan/ir/annotations/PointsToKind;", "indexFrom", "indexTo", "kind-s_dE6CA", "([III)Lorg/jetbrains/kotlin/backend/konan/ir/annotations/PointsToKind;", "toString", "", "toString-impl", "([I)Ljava/lang/String;", "equals", "", "other", "equals-impl", "([ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "([I)I", "org.jetbrains.kotlin:ir.backend.native", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class PointsTo {
    private final int[] elements;

    private /* synthetic */ PointsTo(int[] iArr) {
        this.elements = iArr;
    }

    public static CharSequence a(int i) {
        String string = Integer.toString(i, CharsKt.checkRadix(16));
        string.getClass();
        return "0x".concat(string);
    }

    /* JADX INFO: renamed from: assertIsValidFor-impl, reason: not valid java name */
    public static final void m2010assertIsValidForimpl(int[] iArr, int i) {
        if (iArr.length != i) {
            throw new IllegalArgumentException((((Object) m2018toStringimpl(iArr)) + " must have exactly " + i + " elements").toString());
        }
        for (int i2 : iArr) {
            if (i2 < 0 || (i2 >> (i * 4)) != 0) {
                String string = Integer.toString(i2, CharsKt.checkRadix(16));
                string.getClass();
                t2f.a("0x", string, " must not be negative and not have nibbles higher than ", i);
                return;
            }
        }
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PointsTo m2011boximpl(int[] iArr) {
        return new PointsTo(iArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m2012constructorimpl(List<Integer> list, int i) {
        list.getClass();
        int[] iArrM2013constructorimpl = m2013constructorimpl(CollectionsKt.toIntArray(list));
        m2010assertIsValidForimpl(iArrM2013constructorimpl, i);
        return iArrM2013constructorimpl;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2014equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof PointsTo) && Intrinsics.areEqual(iArr, ((PointsTo) obj).getElements());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2015equalsimpl0(int[] iArr, int[] iArr2) {
        return Intrinsics.areEqual(iArr, iArr2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2016hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    /* JADX INFO: renamed from: kind-s_dE6CA, reason: not valid java name */
    public static final PointsToKind m2017kinds_dE6CA(int[] iArr, int i, int i2) {
        return PointsToKind.INSTANCE.m2030fromMask5EATRwE((iArr[i] >> (i2 * 4)) & 15);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2018toStringimpl(int[] iArr) {
        return ArraysKt.joinToString$default(iArr, ", ", "(", ")", 0, (CharSequence) null, new Function1() { // from class: o3b
            public final Object invoke(Object obj) {
                return PointsTo.a(((Integer) obj).intValue());
            }
        }, 24, (Object) null);
    }

    public boolean equals(Object obj) {
        return m2014equalsimpl(this.elements, obj);
    }

    public int hashCode() {
        return m2016hashCodeimpl(this.elements);
    }

    public String toString() {
        return m2018toStringimpl(this.elements);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int[] getElements() {
        return this.elements;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int[] m2013constructorimpl(int[] iArr) {
        return iArr;
    }
}
