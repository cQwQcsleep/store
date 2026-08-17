package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\u0003*\u00020\tH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\b\u001a\u00020\u0003*\u00020\u0010H\u0017¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\f\u001a\u00020\r*\u00020\u0010H\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\t*\u00020\rH\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\rH\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u0015\u001a\u00020\t*\u00020\u0003H\u0017¢\u0006\u0004\b\u0016\u0010\u000bJ\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\u0003H\u0017¢\u0006\u0004\b\u0019\u0010\u001bJ\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0017J\u0013\u0010\u001f\u001a\u00020 *\u00020!H\u0017¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020!*\u00020 H\u0017¢\u0006\u0004\b%\u0010#R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "toPx", "Landroidx/compose/ui/unit/Dp;", "toPx-0680j_4", "(F)F", "roundToPx", "", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "toPx--R2X_6o", "(J)F", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toSize-XkaWNTQ", "(J)J", "toDpSize", "toDpSize-k-rfVVM", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    default int mo4550roundToPxR2X_6o(long j) {
        return Math.round(mo4556toPxR2X_6o(j));
    }

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    default int mo4551roundToPx0680j_4(float f) {
        float fMo4557toPx0680j_4 = mo4557toPx0680j_4(f);
        if (Float.isInfinite(fMo4557toPx0680j_4)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(fMo4557toPx0680j_4);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo4554toDpu2uoSUM(int i) {
        return Dp.m6022constructorimpl(i / getDensity());
    }

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    default long mo4555toDpSizekrfVVM(long j) {
        return j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? DpKt.m6044DpSizeYgX7TsA(mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (j >> 32))), mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (j & 4294967295L)))) : DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
    }

    /* JADX INFO: renamed from: toPx--R2X_6o */
    default float mo4556toPxR2X_6o(long j) {
        if (!TextUnitType.m6244equalsimpl0(TextUnit.m6215getTypeUIouoOA(j), TextUnitType.INSTANCE.m6249getSpUIouoOA())) {
            InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
        }
        return mo4557toPx0680j_4(mo4552toDpGaN1DYA(j));
    }

    /* JADX INFO: renamed from: toPx-0680j_4 */
    default float mo4557toPx0680j_4(float f) {
        return f * getDensity();
    }

    default Rect toRect(DpRect dpRect) {
        return new Rect(mo4557toPx0680j_4(dpRect.m6105getLeftD9Ej5fM()), mo4557toPx0680j_4(dpRect.m6107getTopD9Ej5fM()), mo4557toPx0680j_4(dpRect.m6106getRightD9Ej5fM()), mo4557toPx0680j_4(dpRect.m6104getBottomD9Ej5fM()));
    }

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    default long mo4558toSizeXkaWNTQ(long j) {
        if (j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            return Size.INSTANCE.m2966getUnspecifiedNHjbRc();
        }
        float fMo4557toPx0680j_4 = mo4557toPx0680j_4(DpSize.m6120getWidthD9Ej5fM(j));
        float fMo4557toPx0680j_5 = mo4557toPx0680j_4(DpSize.m6118getHeightD9Ej5fM(j));
        return Size.m2949constructorimpl((((long) Float.floatToRawIntBits(fMo4557toPx0680j_4)) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_5)) & 4294967295L));
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo4561toSpkPz2Gy4(int i) {
        return mo4559toSp0xMU5do(mo4554toDpu2uoSUM(i));
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m6008roundToPxR2X_6o(Density density, long j) {
            return Density.super.mo4550roundToPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m6009roundToPx0680j_4(Density density, float f) {
            return Density.super.mo4551roundToPx0680j_4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m6010toDpGaN1DYA(Density density, long j) {
            return Density.super.mo4552toDpGaN1DYA(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m6012toDpu2uoSUM(Density density, int i) {
            return Density.super.mo4554toDpu2uoSUM(i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m6013toDpSizekrfVVM(Density density, long j) {
            return Density.super.mo4555toDpSizekrfVVM(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m6014toPxR2X_6o(Density density, long j) {
            return Density.super.mo4556toPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m6015toPx0680j_4(Density density, float f) {
            return Density.super.mo4557toPx0680j_4(f);
        }

        @Deprecated
        public static Rect toRect(Density density, DpRect dpRect) {
            return Density.super.toRect(dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m6016toSizeXkaWNTQ(Density density, long j) {
            return Density.super.mo4558toSizeXkaWNTQ(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m6017toSp0xMU5do(Density density, float f) {
            return Density.super.mo4559toSp0xMU5do(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m6019toSpkPz2Gy4(Density density, int i) {
            return Density.super.mo4561toSpkPz2Gy4(i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m6011toDpu2uoSUM(Density density, float f) {
            return Density.super.mo4553toDpu2uoSUM(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m6018toSpkPz2Gy4(Density density, float f) {
            return Density.super.mo4560toSpkPz2Gy4(f);
        }
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo4560toSpkPz2Gy4(float f) {
        return mo4559toSp0xMU5do(mo4553toDpu2uoSUM(f));
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo4553toDpu2uoSUM(float f) {
        return Dp.m6022constructorimpl(f / getDensity());
    }
}
