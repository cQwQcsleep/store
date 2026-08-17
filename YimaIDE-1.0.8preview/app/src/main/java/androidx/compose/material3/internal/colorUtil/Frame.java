package androidx.compose.material3.internal.colorUtil;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0013\b\u0001\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBY\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0005\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\t\u001a\u00020\n8G¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0013\u0010\f\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/internal/colorUtil/Frame;", "", "n", "", "aw", "nbb", "ncb", "c", "nc", "rgbD", "", "fl", "flRoot", "z", "<init>", "(FFFFFF[FFFF)V", "getN", "()F", "getAw", "getNbb", "getNcb", "getC", "getNc", "getRgbD", "()[F", "getFl", "getFlRoot", "getZ", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Frame {
    public static final int $stable;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final Frame Default;
    private final float aw;
    private final float c;
    private final float fl;
    private final float flRoot;
    private final float n;
    private final float nbb;
    private final float nc;
    private final float ncb;
    private final float[] rgbD;
    private final float z;

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        $stable = 8;
        CamUtils camUtils = CamUtils.INSTANCE;
        Default = companion.make(camUtils.getWHITE_POINT_D65(), (float) ((camUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);
    }

    private Frame(float f, float f2, float f3, float f4, float f5, float f6, float[] fArr, float f7, float f8, float f9) {
        this.n = f;
        this.aw = f2;
        this.nbb = f3;
        this.ncb = f4;
        this.c = f5;
        this.nc = f6;
        this.rgbD = fArr;
        this.fl = f7;
        this.flRoot = f8;
        this.z = f9;
    }

    public final float getAw() {
        return this.aw;
    }

    public final float getC() {
        return this.c;
    }

    public final float getFl() {
        return this.fl;
    }

    public final float getFlRoot() {
        return this.flRoot;
    }

    public final float getN() {
        return this.n;
    }

    public final float getNbb() {
        return this.nbb;
    }

    public final float getNc() {
        return this.nc;
    }

    public final float getNcb() {
        return this.ncb;
    }

    public final float[] getRgbD() {
        return this.rgbD;
    }

    public final float getZ() {
        return this.z;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Landroidx/compose/material3/internal/colorUtil/Frame$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/material3/internal/colorUtil/Frame;", "getDefault", "()Landroidx/compose/material3/internal/colorUtil/Frame;", "make", "whitepoint", "", "adaptingLuminance", "", "backgroundLstar", "surround", "discountingIlluminant", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Frame getDefault() {
            return Frame.Default;
        }

        public final Frame make(float[] whitepoint, float adaptingLuminance, float backgroundLstar, float surround, boolean discountingIlluminant) {
            CamUtils camUtils = CamUtils.INSTANCE;
            float[][] xyz_to_cam16rgb = camUtils.getXYZ_TO_CAM16RGB();
            float f = whitepoint[0];
            float[] fArr = xyz_to_cam16rgb[0];
            float f2 = fArr[0] * f;
            float f3 = whitepoint[1];
            float f4 = f2 + (fArr[1] * f3);
            float f5 = whitepoint[2];
            float f6 = f4 + (fArr[2] * f5);
            float[] fArr2 = xyz_to_cam16rgb[1];
            float f7 = (fArr2[0] * f) + (fArr2[1] * f3) + (fArr2[2] * f5);
            float[] fArr3 = xyz_to_cam16rgb[2];
            float f8 = (f * fArr3[0]) + (f3 * fArr3[1]) + (f5 * fArr3[2]);
            float f9 = (surround / 10.0f) + 0.8f;
            float fLerp = ((double) f9) >= 0.9d ? Frame_androidKt.lerp(0.59f, 0.69f, (f9 - 0.9f) * 10.0f) : Frame_androidKt.lerp(0.525f, 0.59f, (f9 - 0.8f) * 10.0f);
            float fExp = discountingIlluminant ? 1.0f : (1.0f - (((float) Math.exp(((-adaptingLuminance) - 42.0f) / 92.0f)) * 0.2777778f)) * f9;
            double d = fExp;
            if (d > 1.0d) {
                fExp = 1.0f;
            } else if (d < 0.0d) {
                fExp = 0.0f;
            }
            float[] fArr4 = {(((100.0f / f6) * fExp) + 1.0f) - fExp, (((100.0f / f7) * fExp) + 1.0f) - fExp, (((100.0f / f8) * fExp) + 1.0f) - fExp};
            float f10 = 1.0f / ((5.0f * adaptingLuminance) + 1.0f);
            float f11 = f10 * f10 * f10 * f10;
            float f12 = 1.0f - f11;
            float fCbrt = (f11 * adaptingLuminance) + (0.1f * f12 * f12 * ((float) Math.cbrt(((double) adaptingLuminance) * 5.0d)));
            float fYFromLstar = ((float) camUtils.yFromLstar(backgroundLstar)) / whitepoint[1];
            double d2 = fYFromLstar;
            float fSqrt = ((float) Math.sqrt(d2)) + 1.48f;
            float fPow = 0.725f / ((float) Math.pow(d2, 0.20000000298023224d));
            float[] fArr5 = {(float) Math.pow(((fArr4[0] * fCbrt) * f6) / 100.0f, 0.41999998688697815d), (float) Math.pow(((fArr4[1] * fCbrt) * f7) / 100.0f, 0.41999998688697815d), (float) Math.pow(((fArr4[2] * fCbrt) * f8) / 100.0f, 0.41999998688697815d)};
            float f13 = fArr5[0];
            float f14 = (f13 * 400.0f) / (f13 + 27.13f);
            float f15 = fArr5[1];
            float f16 = (f15 * 400.0f) / (f15 + 27.13f);
            float f17 = fArr5[2];
            float[] fArr6 = {f14, f16, (400.0f * f17) / (f17 + 27.13f)};
            return new Frame(fYFromLstar, ((fArr6[0] * 2.0f) + fArr6[1] + (fArr6[2] * 0.05f)) * fPow, fPow, fPow, fLerp, f9, fArr4, fCbrt, (float) Math.pow(fCbrt, 0.25d), fSqrt, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ Frame(float f, float f2, float f3, float f4, float f5, float f6, float[] fArr, float f7, float f8, float f9, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, fArr, f7, f8, f9);
    }
}
