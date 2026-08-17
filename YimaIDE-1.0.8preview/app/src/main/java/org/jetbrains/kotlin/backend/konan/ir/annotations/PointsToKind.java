package org.jetbrains.kotlin.backend.konan.ir.annotations;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0087@\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u0011\u0010\u0005J\u0011\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\u0088\u0001\u0002\u0092\u0001\u00020\u0003Ê\u0001\u0002\b\u0018¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/ir/annotations/PointsToKind;", "", "value", "", "constructor-impl", "(I)I", "sourceIsDirect", "", "getSourceIsDirect-impl", "(I)Z", "destinationIsDirect", "getDestinationIsDirect-impl", "equals", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.backend.native", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class PointsToKind {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    private /* synthetic */ PointsToKind(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PointsToKind m2021boximpl(int i) {
        return new PointsToKind(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2022constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2023equalsimpl(int i, Object obj) {
        return (obj instanceof PointsToKind) && i == ((PointsToKind) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2024equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: getDestinationIsDirect-impl, reason: not valid java name */
    public static final boolean m2025getDestinationIsDirectimpl(int i) {
        return i % 2 == 1;
    }

    /* JADX INFO: renamed from: getSourceIsDirect-impl, reason: not valid java name */
    public static final boolean m2026getSourceIsDirectimpl(int i) {
        return i < 3;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2027hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2028toStringimpl(int i) {
        return "PointsToKind(value=" + i + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m2023equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2027hashCodeimpl(this.value);
    }

    public String toString() {
        return m2028toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/ir/annotations/PointsToKind$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "fromMask", "Lorg/jetbrains/kotlin/backend/konan/ir/annotations/PointsToKind;", "mask", "", "fromMask-5EATRwE", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: fromMask-5EATRwE, reason: not valid java name */
        public final PointsToKind m2030fromMask5EATRwE(int mask) {
            if (mask >= 0 && mask <= 4) {
                if (mask == 0) {
                    return null;
                }
                return PointsToKind.m2021boximpl(PointsToKind.m2022constructorimpl(mask));
            }
            throw new IllegalArgumentException((mask + " must be 0..4").toString());
        }

        private Companion() {
        }
    }
}
