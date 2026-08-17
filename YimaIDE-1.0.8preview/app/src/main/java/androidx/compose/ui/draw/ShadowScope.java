package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\fX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0012X¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007R\u0018\u0010\u001a\u001a\u00020\u001bX¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u00020!X¦\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u000e\"\u0004\b#\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006$À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/ShadowScope;", "Landroidx/compose/ui/unit/Density;", "radius", "", "getRadius", "()F", "setRadius", "(F)V", "spread", "getSpread", "setSpread", "color", "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", "setColor-8_81llA", "(J)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "alpha", "getAlpha", "setAlpha", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "offset", "Landroidx/compose/ui/geometry/Offset;", "getOffset-F1C5BW0", "setOffset-k-4lQ0M", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface ShadowScope extends Density {
    float getAlpha();

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU */
    int mo2639getBlendMode0nO6VwU();

    Brush getBrush();

    /* JADX INFO: renamed from: getColor-0d7_KjU */
    long mo2640getColor0d7_KjU();

    /* JADX INFO: renamed from: getOffset-F1C5BW0 */
    long mo2641getOffsetF1C5BW0();

    float getRadius();

    float getSpread();

    void setAlpha(float f);

    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    void mo2642setBlendModes9anfk8(int i);

    void setBrush(Brush brush);

    /* JADX INFO: renamed from: setColor-8_81llA */
    void mo2643setColor8_81llA(long j);

    /* JADX INFO: renamed from: setOffset-k-4lQ0M */
    void mo2644setOffsetk4lQ0M(long j);

    void setRadius(float f);

    void setSpread(float f);

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m2742roundToPxR2X_6o(ShadowScope shadowScope, long j) {
            return ShadowScope.super.mo4550roundToPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m2743roundToPx0680j_4(ShadowScope shadowScope, float f) {
            return ShadowScope.super.mo4551roundToPx0680j_4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m2744toDpGaN1DYA(ShadowScope shadowScope, long j) {
            return ShadowScope.super.mo4552toDpGaN1DYA(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2745toDpu2uoSUM(ShadowScope shadowScope, float f) {
            return ShadowScope.super.mo4553toDpu2uoSUM(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m2747toDpSizekrfVVM(ShadowScope shadowScope, long j) {
            return ShadowScope.super.mo4555toDpSizekrfVVM(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m2748toPxR2X_6o(ShadowScope shadowScope, long j) {
            return ShadowScope.super.mo4556toPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m2749toPx0680j_4(ShadowScope shadowScope, float f) {
            return ShadowScope.super.mo4557toPx0680j_4(f);
        }

        @Deprecated
        public static Rect toRect(ShadowScope shadowScope, DpRect dpRect) {
            return ShadowScope.super.toRect(dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m2750toSizeXkaWNTQ(ShadowScope shadowScope, long j) {
            return ShadowScope.super.mo4558toSizeXkaWNTQ(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m2751toSp0xMU5do(ShadowScope shadowScope, float f) {
            return ShadowScope.super.mo4559toSp0xMU5do(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2752toSpkPz2Gy4(ShadowScope shadowScope, float f) {
            return ShadowScope.super.mo4560toSpkPz2Gy4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2746toDpu2uoSUM(ShadowScope shadowScope, int i) {
            return ShadowScope.super.mo4554toDpu2uoSUM(i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2753toSpkPz2Gy4(ShadowScope shadowScope, int i) {
            return ShadowScope.super.mo4561toSpkPz2Gy4(i);
        }
    }
}
