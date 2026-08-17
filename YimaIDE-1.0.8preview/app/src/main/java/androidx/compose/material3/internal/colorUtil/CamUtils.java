package androidx.compose.material3.internal.colorUtil;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.graphics.ColorUtils;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u0007\n\u0002\b\b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0016J \u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u001e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0016J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u0016H\u0002J \u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0014H\u0002J \u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0014H\u0002J\u000e\u0010+\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020,J\u000e\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u0014J\u0010\u0010/\u001a\u00020,2\u0006\u0010\u001b\u001a\u00020,H\u0002J\u0010\u00100\u001a\u00020,2\u0006\u0010.\u001a\u00020\u0014H\u0002J\u000e\u00101\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0014J\u000e\u00102\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J\u0010\u00103\u001a\u00020,2\u0006\u0010\"\u001a\u00020\u0014H\u0002R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u00064"}, d2 = {"Landroidx/compose/material3/internal/colorUtil/CamUtils;", "", "<init>", "()V", "XYZ_TO_CAM16RGB", "", "", "getXYZ_TO_CAM16RGB", "()[[F", "[[F", "CAM16RGB_TO_XYZ", "getCAM16RGB_TO_XYZ", "WHITE_POINT_D65", "getWHITE_POINT_D65", "()[F", "SRGB_TO_XYZ", "", "[[D", "XYZ_TO_SRGB", "signum", "", "num", "", "argbFromLstar", "lstar", "argbFromXyz", "x", "y", "z", "argbFromLinrgbComponents", "r", "g", "b", "delinearized", "rgbComponent", "clampInt", "min", "max", "input", "argbFromRgb", "red", "green", "blue", "intFromLstar", "", "lstarFromInt", "argb", "lstarFromY", "yFromInt", "xyzFromInt", "yFromLstar", "linearized", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CamUtils {
    public static final CamUtils INSTANCE = new CamUtils();
    private static final float[][] XYZ_TO_CAM16RGB = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    private static final float[][] CAM16RGB_TO_XYZ = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    private static final float[] WHITE_POINT_D65 = {95.047f, 100.0f, 108.883f};
    private static final double[][] SRGB_TO_XYZ = {new double[]{0.41233895d, 0.35762064d, 0.18051042d}, new double[]{0.2126d, 0.7152d, 0.0722d}, new double[]{0.01932141d, 0.11916382d, 0.95034478d}};
    private static final double[][] XYZ_TO_SRGB = {new double[]{3.2413774792388685d, -1.5376652402851851d, -0.49885366846268053d}, new double[]{-0.9691452513005321d, 1.8758853451067872d, 0.04156585616912061d}, new double[]{0.05562093689691305d, -0.20395524564742123d, 1.0571799111220335d}};
    public static final int $stable = 8;

    private CamUtils() {
    }

    private final int argbFromRgb(int red, int green, int blue) {
        return ((red & 255) << 16) | (-16777216) | ((green & 255) << 8) | (blue & 255);
    }

    private final int argbFromXyz(double x, double y, double z) {
        double[][] dArr = XYZ_TO_SRGB;
        double[] dArr2 = dArr[0];
        double d = (dArr2[0] * x) + (dArr2[1] * y) + (dArr2[2] * z);
        double[] dArr3 = dArr[1];
        double d2 = (dArr3[0] * x) + (dArr3[1] * y) + (dArr3[2] * z);
        double[] dArr4 = dArr[2];
        return argbFromRgb(delinearized(d), delinearized(d2), delinearized((dArr4[0] * x) + (dArr4[1] * y) + (dArr4[2] * z)));
    }

    private final int clampInt(int min, int max, int input) {
        if (input < min) {
            return min;
        }
        return input > max ? max : input;
    }

    private final int delinearized(double rgbComponent) {
        double d = rgbComponent / 100.0d;
        return clampInt(0, 255, (int) Math.round((d <= 0.0031308d ? d * 12.92d : (Math.pow(d, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d));
    }

    private final float linearized(int rgbComponent) {
        float f = rgbComponent / 255.0f;
        return (f <= 0.04045f ? f / 12.92f : (float) Math.pow((f + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    private final float lstarFromY(float y) {
        float f = y / 100.0f;
        return f <= 0.008856452f ? f * 903.2963f : (((float) Math.cbrt(f)) * 116.0f) - 16.0f;
    }

    private final float yFromInt(int argb) {
        float fLinearized = linearized((argb >> 16) & 255);
        float fLinearized2 = linearized((argb >> 8) & 255);
        float fLinearized3 = linearized(argb & 255);
        double[] dArr = SRGB_TO_XYZ[1];
        return (float) ((((double) fLinearized) * dArr[0]) + (((double) fLinearized2) * dArr[1]) + (((double) fLinearized3) * dArr[2]));
    }

    public final int argbFromLinrgbComponents(double r, double g, double b) {
        return argbFromRgb(delinearized(r), delinearized(g), delinearized(b));
    }

    public final int argbFromLstar(double lstar) {
        double d = (lstar + 16.0d) / 116.0d;
        double d2 = lstar > 8.0d ? d * d * d : lstar / 903.2962962962963d;
        double d3 = d * d * d;
        boolean z = d3 > 0.008856451679035631d;
        double d4 = z ? d3 : lstar / 903.2962962962963d;
        if (!z) {
            d3 = lstar / 903.2962962962963d;
        }
        float[] fArr = WHITE_POINT_D65;
        return argbFromXyz(d4 * ((double) fArr[0]), d2 * ((double) fArr[1]), d3 * ((double) fArr[2]));
    }

    public final float[][] getCAM16RGB_TO_XYZ() {
        return CAM16RGB_TO_XYZ;
    }

    public final float[] getWHITE_POINT_D65() {
        return WHITE_POINT_D65;
    }

    public final float[][] getXYZ_TO_CAM16RGB() {
        return XYZ_TO_CAM16RGB;
    }

    public final int intFromLstar(float lstar) {
        if (lstar < 1.0f) {
            return -16777216;
        }
        if (lstar > 99.0f) {
            return -1;
        }
        float f = (lstar + 16.0f) / 116.0f;
        float f2 = lstar > 8.0f ? f * f * f : lstar / 903.2963f;
        float f3 = f * f * f;
        boolean z = f3 > 0.008856452f;
        float f4 = z ? f3 : ((f * 116.0f) - 16.0f) / 903.2963f;
        if (!z) {
            f3 = ((f * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = WHITE_POINT_D65;
        return ColorUtils.XYZToColor(f4 * fArr[0], f2 * fArr[1], f3 * fArr[2]);
    }

    public final float lstarFromInt(int argb) {
        return lstarFromY(yFromInt(argb));
    }

    public final int signum(double num) {
        if (num < 0.0d) {
            return -1;
        }
        return num == 0.0d ? 0 : 1;
    }

    public final float[] xyzFromInt(int argb) {
        float fLinearized = linearized((argb >> 16) & 255);
        float fLinearized2 = linearized((argb >> 8) & 255);
        float fLinearized3 = linearized(argb & 255);
        double[][] dArr = SRGB_TO_XYZ;
        double d = fLinearized;
        double[] dArr2 = dArr[0];
        double d2 = fLinearized2;
        double d3 = fLinearized3;
        double d4 = (dArr2[0] * d) + (dArr2[1] * d2) + (dArr2[2] * d3);
        double[] dArr3 = dArr[1];
        double d5 = (dArr3[0] * d) + (dArr3[1] * d2) + (dArr3[2] * d3);
        double[] dArr4 = dArr[2];
        return new float[]{(float) d4, (float) d5, (float) ((d * dArr4[0]) + (d2 * dArr4[1]) + (d3 * dArr4[2]))};
    }

    public final double yFromLstar(double lstar) {
        return lstar > 8.0d ? Math.pow((lstar + 16.0d) / 116.0d, 3.0d) * 100.0d : (lstar / 903.2962962962963d) * 100.0d;
    }
}
