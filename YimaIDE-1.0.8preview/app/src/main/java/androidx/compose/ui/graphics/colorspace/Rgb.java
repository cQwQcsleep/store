package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 _2\u00020\u0001:\u0001_B]\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014BE\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0005\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0004\b\u0013\u0010\u0018B]\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u0019B%\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u001bB-\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u001a\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u001cB1\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u001a\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u001dB%\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u001e\u001a\u00020\u0017¢\u0006\u0004\b\u0013\u0010\u001fB-\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u001e\u001a\u00020\u0017¢\u0006\u0004\b\u0013\u0010 BA\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u001e\u001a\u00020\u0017\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010!B!\b\u0010\u0012\u0006\u0010\"\u001a\u00020\u0000\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0013\u0010#J\b\u0010=\u001a\u00020\u0005H\u0007J\b\u0010>\u001a\u00020\u0005H\u0007J\b\u0010?\u001a\u00020\u0005H\u0007J\u0012\u0010=\u001a\u00020\u00052\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u0007J\u0012\u0010>\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\u0005H\u0007J\u0012\u0010?\u001a\u00020\u00052\b\b\u0001\u0010+\u001a\u00020\u0005H\u0007J\u0010\u0010@\u001a\u00020\r2\u0006\u0010A\u001a\u00020\u0012H\u0016J\u0010\u0010B\u001a\u00020\r2\u0006\u0010A\u001a\u00020\u0012H\u0016J \u0010C\u001a\u00020\u00052\u0006\u0010D\u001a\u00020\r2\u0006\u0010E\u001a\u00020\r2\u0006\u0010F\u001a\u00020\rH\u0007J\u0012\u0010C\u001a\u00020\u00052\b\b\u0001\u0010G\u001a\u00020\u0005H\u0007J \u0010H\u001a\u00020\u00052\u0006\u0010D\u001a\u00020\r2\u0006\u0010E\u001a\u00020\r2\u0006\u0010F\u001a\u00020\rH\u0007J\u0012\u0010H\u001a\u00020\u00052\b\b\u0001\u0010G\u001a\u00020\u0005H\u0007J\u0010\u0010I\u001a\u00020\u00052\u0006\u0010G\u001a\u00020\u0005H\u0016J%\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rH\u0010¢\u0006\u0002\bOJ%\u0010P\u001a\u00020\r2\u0006\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rH\u0010¢\u0006\u0002\bQJ7\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\r2\u0006\u0010U\u001a\u00020\r2\u0006\u0010V\u001a\u00020\r2\u0006\u0010W\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u0001H\u0010¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020\u00052\u0006\u0010G\u001a\u00020\u0005H\u0016J\u0013\u0010[\u001a\u00020:2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0096\u0002J\b\u0010^\u001a\u00020\u0012H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010\b\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010)R\u0014\u0010-\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010/R\u0014\u00104\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010/R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b6\u00101R\u0014\u00107\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010/R\u0014\u00109\u001a\u00020:X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010;R\u0014\u0010<\u001a\u00020:X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010;¨\u0006`"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Rgb;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "name", "", "primaries", "", "whitePoint", "Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "transform", "oetf", "Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "eotf", "min", "", "max", "transferParameters", "Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "id", "", "<init>", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;[FLandroidx/compose/ui/graphics/colorspace/DoubleFunction;Landroidx/compose/ui/graphics/colorspace/DoubleFunction;FFLandroidx/compose/ui/graphics/colorspace/TransferParameters;I)V", "toXYZ", "Lkotlin/Function1;", "", "(Ljava/lang/String;[FLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;FF)V", "function", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/TransferParameters;)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;Landroidx/compose/ui/graphics/colorspace/TransferParameters;)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;Landroidx/compose/ui/graphics/colorspace/TransferParameters;I)V", "gamma", "(Ljava/lang/String;[FD)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;D)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;DFFI)V", "colorSpace", "(Landroidx/compose/ui/graphics/colorspace/Rgb;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;)V", "getWhitePoint", "()Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "getTransferParameters", "()Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "getPrimaries$ui_graphics", "()[F", "getTransform$ui_graphics", "inverseTransform", "getInverseTransform$ui_graphics", "oetfOrig", "getOetfOrig$ui_graphics", "()Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "getOetf", "()Lkotlin/jvm/functions/Function1;", "oetfFunc", "getOetfFunc$ui_graphics", "eotfOrig", "getEotfOrig$ui_graphics", "getEotf", "eotfFunc", "getEotfFunc$ui_graphics", "isWideGamut", "", "()Z", "isSrgb", "getPrimaries", "getTransform", "getInverseTransform", "getMinValue", "component", "getMaxValue", "toLinear", "r", "g", "b", "v", "fromLinear", "toXyz", "toXy", "", "v0", "v1", "v2", "toXy$ui_graphics", "toZ", "toZ$ui_graphics", "xyzaToColor", "Landroidx/compose/ui/graphics/Color;", "x", "y", "z", "a", "xyzaToColor-JlNiLsg$ui_graphics", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "fromXyz", "equals", "other", "", "hashCode", "Companion", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Rgb extends ColorSpace {
    private final Function1<Double, Double> eotf;
    private final DoubleFunction eotfFunc;
    private final DoubleFunction eotfOrig;
    private final float[] inverseTransform;
    private final boolean isSrgb;
    private final boolean isWideGamut;
    private final float max;
    private final float min;
    private final Function1<Double, Double> oetf;
    private final DoubleFunction oetfFunc;
    private final DoubleFunction oetfOrig;
    private final float[] primaries;
    private final TransferParameters transferParameters;
    private final float[] transform;
    private final WhitePoint whitePoint;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final DoubleFunction DoubleIdentity = new DoubleFunction() { // from class: wjc
        @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
        public final double invoke(double d) {
            return Rgb.f(d);
        }
    };

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, float[] fArr2, DoubleFunction doubleFunction, DoubleFunction doubleFunction2, float f, float f2, TransferParameters transferParameters, int i) {
        super(str, ColorModel.INSTANCE.m3571getRgbxdoWZVw(), i, null);
        this.whitePoint = whitePoint;
        this.min = f;
        this.max = f2;
        this.transferParameters = transferParameters;
        this.oetfOrig = doubleFunction;
        this.oetf = new Function1<Double, Double>() { // from class: androidx.compose.ui.graphics.colorspace.Rgb$oetf$1
            {
                super(1);
            }

            public final Double invoke(double d) {
                return Double.valueOf(RangesKt.coerceIn(this.this$0.getOetfOrig().invoke(d), this.this$0.min, this.this$0.max));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).doubleValue());
            }
        };
        this.oetfFunc = new DoubleFunction() { // from class: ujc
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return Rgb.a(this.a, d);
            }
        };
        this.eotfOrig = doubleFunction2;
        this.eotf = new Function1<Double, Double>() { // from class: androidx.compose.ui.graphics.colorspace.Rgb$eotf$1
            {
                super(1);
            }

            public final Double invoke(double d) {
                return Double.valueOf(this.this$0.getEotfOrig().invoke(RangesKt.coerceIn(d, this.this$0.min, this.this$0.max)));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).doubleValue());
            }
        };
        this.eotfFunc = new DoubleFunction() { // from class: vjc
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return Rgb.d(this.a, d);
            }
        };
        if (fArr.length != 6 && fArr.length != 9) {
            w01.a("The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ");
            throw null;
        }
        if (f >= f2) {
            throw new IllegalArgumentException("Invalid range: min=" + f + ", max=" + f2 + "; min must be strictly < max");
        }
        Companion companion = INSTANCE;
        float[] fArrXyPrimaries = companion.xyPrimaries(fArr);
        this.primaries = fArrXyPrimaries;
        if (fArr2 == null) {
            this.transform = companion.computeXYZMatrix(fArrXyPrimaries, whitePoint);
        } else {
            if (fArr2.length != 9) {
                jt6.a("Transform must have 9 entries! Has ", fArr2.length);
                throw null;
            }
            this.transform = fArr2;
        }
        this.inverseTransform = ColorSpaceKt.inverse3x3(this.transform);
        this.isWideGamut = companion.isWideGamut(fArrXyPrimaries, f, f2);
        this.isSrgb = companion.isSrgb(fArrXyPrimaries, whitePoint, doubleFunction, doubleFunction2, f, f2, i);
    }

    public static double a(Rgb rgb, double d) {
        return RangesKt.coerceIn(rgb.oetfOrig.invoke(d), rgb.min, rgb.max);
    }

    public static double b(Function1 function1, double d) {
        return ((Number) function1.invoke(Double.valueOf(d))).doubleValue();
    }

    public static double c(double d, double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        }
        return Math.pow(d2, 1.0d / d);
    }

    public static double d(Rgb rgb, double d) {
        return rgb.eotfOrig.invoke(RangesKt.coerceIn(d, rgb.min, rgb.max));
    }

    public static double e(Function1 function1, double d) {
        return ((Number) function1.invoke(Double.valueOf(d))).doubleValue();
    }

    public static double f(double d) {
        return d;
    }

    public static double g(Function1 function1, double d) {
        return ((Number) function1.invoke(Double.valueOf(d))).doubleValue();
    }

    public static double h(Function1 function1, double d) {
        return ((Number) function1.invoke(Double.valueOf(d))).doubleValue();
    }

    public static double i(double d, double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        }
        return Math.pow(d2, d);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || Rgb.class != other.getClass() || !super.equals(other)) {
            return false;
        }
        Rgb rgb = (Rgb) other;
        if (Float.compare(rgb.min, this.min) != 0 || Float.compare(rgb.max, this.max) != 0 || !Intrinsics.areEqual(this.whitePoint, rgb.whitePoint) || !Arrays.equals(this.primaries, rgb.primaries)) {
            return false;
        }
        TransferParameters transferParameters = this.transferParameters;
        TransferParameters transferParameters2 = rgb.transferParameters;
        if (transferParameters != null) {
            return Intrinsics.areEqual(transferParameters, transferParameters2);
        }
        if (transferParameters2 == null) {
            return true;
        }
        if (Intrinsics.areEqual(this.oetfOrig, rgb.oetfOrig)) {
            return Intrinsics.areEqual(this.eotfOrig, rgb.eotfOrig);
        }
        return false;
    }

    public final float[] fromLinear(float[] v) {
        if (v.length < 3) {
            return v;
        }
        v[0] = (float) this.oetfFunc.invoke(v[0]);
        v[1] = (float) this.oetfFunc.invoke(v[1]);
        v[2] = (float) this.oetfFunc.invoke(v[2]);
        return v;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] fromXyz(float[] v) {
        ColorSpaceKt.mul3x3Float3(this.inverseTransform, v);
        if (v.length < 3) {
            return v;
        }
        v[0] = (float) this.oetfFunc.invoke(v[0]);
        v[1] = (float) this.oetfFunc.invoke(v[1]);
        v[2] = (float) this.oetfFunc.invoke(v[2]);
        return v;
    }

    public final Function1<Double, Double> getEotf() {
        return this.eotf;
    }

    /* JADX INFO: renamed from: getEotfFunc$ui_graphics, reason: from getter */
    public final DoubleFunction getEotfFunc() {
        return this.eotfFunc;
    }

    /* JADX INFO: renamed from: getEotfOrig$ui_graphics, reason: from getter */
    public final DoubleFunction getEotfOrig() {
        return this.eotfOrig;
    }

    public final float[] getInverseTransform(float[] inverseTransform) {
        return ArraysKt.copyInto$default(this.inverseTransform, inverseTransform, 0, 0, 0, 14, (Object) null);
    }

    /* JADX INFO: renamed from: getInverseTransform$ui_graphics, reason: from getter */
    public final float[] getInverseTransform() {
        return this.inverseTransform;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMaxValue(int component) {
        return this.max;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMinValue(int component) {
        return this.min;
    }

    public final Function1<Double, Double> getOetf() {
        return this.oetf;
    }

    /* JADX INFO: renamed from: getOetfFunc$ui_graphics, reason: from getter */
    public final DoubleFunction getOetfFunc() {
        return this.oetfFunc;
    }

    /* JADX INFO: renamed from: getOetfOrig$ui_graphics, reason: from getter */
    public final DoubleFunction getOetfOrig() {
        return this.oetfOrig;
    }

    public final float[] getPrimaries(float[] primaries) {
        return ArraysKt.copyInto$default(this.primaries, primaries, 0, 0, 0, 14, (Object) null);
    }

    /* JADX INFO: renamed from: getPrimaries$ui_graphics, reason: from getter */
    public final float[] getPrimaries() {
        return this.primaries;
    }

    public final TransferParameters getTransferParameters() {
        return this.transferParameters;
    }

    public final float[] getTransform(float[] transform) {
        return ArraysKt.copyInto$default(this.transform, transform, 0, 0, 0, 14, (Object) null);
    }

    /* JADX INFO: renamed from: getTransform$ui_graphics, reason: from getter */
    public final float[] getTransform() {
        return this.transform;
    }

    public final WhitePoint getWhitePoint() {
        return this.whitePoint;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public int hashCode() {
        int iHashCode = ((((super.hashCode() * 31) + this.whitePoint.hashCode()) * 31) + Arrays.hashCode(this.primaries)) * 31;
        float f = this.min;
        int iFloatToIntBits = (iHashCode + (f == 0.0f ? 0 : Float.floatToIntBits(f))) * 31;
        float f2 = this.max;
        int iFloatToIntBits2 = (iFloatToIntBits + (f2 == 0.0f ? 0 : Float.floatToIntBits(f2))) * 31;
        TransferParameters transferParameters = this.transferParameters;
        int iHashCode2 = iFloatToIntBits2 + (transferParameters != null ? transferParameters.hashCode() : 0);
        return this.transferParameters == null ? (((iHashCode2 * 31) + this.oetfOrig.hashCode()) * 31) + this.eotfOrig.hashCode() : iHashCode2;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isSrgb, reason: from getter */
    public boolean getIsSrgb() {
        return this.isSrgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isWideGamut, reason: from getter */
    public boolean getIsWideGamut() {
        return this.isWideGamut;
    }

    public final float[] toLinear(float[] v) {
        if (v.length < 3) {
            return v;
        }
        v[0] = (float) this.eotfFunc.invoke(v[0]);
        v[1] = (float) this.eotfFunc.invoke(v[1]);
        v[2] = (float) this.eotfFunc.invoke(v[2]);
        return v;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public long toXy$ui_graphics(float v0, float v1, float v2) {
        float fInvoke = (float) this.eotfFunc.invoke(v0);
        float fInvoke2 = (float) this.eotfFunc.invoke(v1);
        float fInvoke3 = (float) this.eotfFunc.invoke(v2);
        float[] fArr = this.transform;
        if (fArr.length < 9) {
            return 0L;
        }
        return (((long) Float.floatToRawIntBits(((fArr[0] * fInvoke) + (fArr[3] * fInvoke2)) + (fArr[6] * fInvoke3))) << 32) | (((long) Float.floatToRawIntBits((fArr[1] * fInvoke) + (fArr[4] * fInvoke2) + (fArr[7] * fInvoke3))) & 4294967295L);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] toXyz(float[] v) {
        if (v.length < 3) {
            return v;
        }
        v[0] = (float) this.eotfFunc.invoke(v[0]);
        v[1] = (float) this.eotfFunc.invoke(v[1]);
        v[2] = (float) this.eotfFunc.invoke(v[2]);
        return ColorSpaceKt.mul3x3Float3(this.transform, v);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float toZ$ui_graphics(float v0, float v1, float v2) {
        float fInvoke = (float) this.eotfFunc.invoke(v0);
        float fInvoke2 = (float) this.eotfFunc.invoke(v1);
        float fInvoke3 = (float) this.eotfFunc.invoke(v2);
        float[] fArr = this.transform;
        return (fArr[2] * fInvoke) + (fArr[5] * fInvoke2) + (fArr[8] * fInvoke3);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: xyzaToColor-JlNiLsg$ui_graphics */
    public long mo3574xyzaToColorJlNiLsg$ui_graphics(float x, float y, float z, float a, ColorSpace colorSpace) {
        float[] fArr = this.inverseTransform;
        return ColorKt.Color((float) this.oetfFunc.invoke((fArr[0] * x) + (fArr[3] * y) + (fArr[6] * z)), (float) this.oetfFunc.invoke((fArr[1] * x) + (fArr[4] * y) + (fArr[7] * z)), (float) this.oetfFunc.invoke((fArr[2] * x) + (fArr[5] * y) + (fArr[8] * z)), a, colorSpace);
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J \u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002J)\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000fH\u0082\bJ\u0018\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002J\u0015\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\tH\u0000¢\u0006\u0002\b$J\u0010\u0010%\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\tH\u0002J\u0010\u0010&\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010'\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Rgb$Companion;", "", "<init>", "()V", "DoubleIdentity", "Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "isSrgb", "", "primaries", "", "whitePoint", "Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "OETF", "EOTF", "min", "", "max", "id", "", "compare", "point", "", "a", "b", "isWideGamut", "area", "cross", "ax", "ay", "bx", "by", "contains", "p1", "p2", "computePrimaries", "toXYZ", "computePrimaries$ui_graphics", "computeWhitePoint", "xyPrimaries", "computeXYZMatrix", "generateOetf", "function", "Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "generateEotf", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static double a(TransferParameters transferParameters, double d) {
            return ColorSpaces.INSTANCE.transferHlgEotf$ui_graphics(transferParameters, d);
        }

        private final float area(float[] primaries) {
            if (primaries.length < 6) {
                return 0.0f;
            }
            float f = primaries[0];
            float f2 = primaries[1];
            float f3 = primaries[2];
            float f4 = primaries[3];
            float f5 = primaries[4];
            float f6 = primaries[5];
            float f7 = ((((((f * f4) + (f2 * f5)) + (f3 * f6)) - (f4 * f5)) - (f2 * f3)) - (f * f6)) * 0.5f;
            return f7 < 0.0f ? -f7 : f7;
        }

        public static double b(TransferParameters transferParameters, double d) {
            return ColorSpaceKt.response(d, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getGamma());
        }

        public static double c(TransferParameters transferParameters, double d) {
            return ColorSpaceKt.rcpResponse(d, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getE(), transferParameters.getF(), transferParameters.getGamma());
        }

        private final boolean compare(double point, DoubleFunction a, DoubleFunction b) {
            return Math.abs(a.invoke(point) - b.invoke(point)) <= 0.001d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final WhitePoint computeWhitePoint(float[] toXYZ) {
            float[] fArrMul3x3Float3 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{1.0f, 1.0f, 1.0f});
            float f = fArrMul3x3Float3[0] + fArrMul3x3Float3[1] + fArrMul3x3Float3[2];
            return new WhitePoint(fArrMul3x3Float3[0] / f, fArrMul3x3Float3[1] / f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float[] computeXYZMatrix(float[] primaries, WhitePoint whitePoint) {
            float f = primaries[0];
            float f2 = primaries[1];
            float f3 = primaries[2];
            float f4 = primaries[3];
            float f5 = primaries[4];
            float f6 = primaries[5];
            float x = whitePoint.getX();
            float y = whitePoint.getY();
            float f7 = (1.0f - f) / f2;
            float f8 = (1.0f - x) / y;
            float f9 = f / f2;
            float f10 = (f3 / f4) - f9;
            float f11 = (x / y) - f9;
            float f12 = ((1.0f - f3) / f4) - f7;
            float f13 = (f5 / f6) - f9;
            float f14 = (((f8 - f7) * f10) - (f11 * f12)) / (((((1.0f - f5) / f6) - f7) * f10) - (f12 * f13));
            float f15 = (f11 - (f13 * f14)) / f10;
            float f16 = (1.0f - f15) - f14;
            float f17 = f16 / f2;
            float f18 = f15 / f4;
            float f19 = f14 / f6;
            return new float[]{f17 * f, f16, f17 * ((1.0f - f) - f2), f18 * f3, f15, f18 * ((1.0f - f3) - f4), f19 * f5, f14, f19 * ((1.0f - f5) - f6)};
        }

        private final boolean contains(float[] p1, float[] p2) {
            float f = p1[0];
            float f2 = p2[0];
            float f3 = p1[1];
            float f4 = p2[1];
            float f5 = p1[2];
            float f6 = p2[2];
            float f7 = p1[3];
            float f8 = p2[3];
            float f9 = p1[4];
            float f10 = p2[4];
            float f11 = p1[5];
            float f12 = p2[5];
            float[] fArr = {f - f2, f3 - f4, f5 - f6, f7 - f8, f9 - f10, f11 - f12};
            float f13 = fArr[0];
            float f14 = fArr[1];
            if (((f4 - f12) * f13) - ((f2 - f10) * f14) >= 0.0f && ((f2 - f6) * f14) - ((f4 - f8) * f13) >= 0.0f) {
                float f15 = fArr[2];
                float f16 = fArr[3];
                if (((f8 - f4) * f15) - ((f6 - f2) * f16) >= 0.0f && ((f6 - f10) * f16) - ((f8 - f12) * f15) >= 0.0f) {
                    float f17 = fArr[4];
                    float f18 = fArr[5];
                    if (((f12 - f8) * f17) - ((f10 - f6) * f18) >= 0.0f && ((f10 - f2) * f18) - ((f12 - f4) * f17) >= 0.0f) {
                        return true;
                    }
                }
            }
            return false;
        }

        private final float cross(float ax, float ay, float bx, float by) {
            return (ax * by) - (ay * bx);
        }

        public static double d(TransferParameters transferParameters, double d) {
            return ColorSpaces.INSTANCE.transferSt2048Eotf$ui_graphics(transferParameters, d);
        }

        public static double e(TransferParameters transferParameters, double d) {
            return ColorSpaces.INSTANCE.transferSt2048Oetf$ui_graphics(transferParameters, d);
        }

        public static double f(TransferParameters transferParameters, double d) {
            return ColorSpaces.INSTANCE.transferHlgOetf$ui_graphics(transferParameters, d);
        }

        public static double g(TransferParameters transferParameters, double d) {
            return ColorSpaceKt.response(d, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getE(), transferParameters.getF(), transferParameters.getGamma());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DoubleFunction generateEotf(final TransferParameters function) {
            if (function.isHLGish$ui_graphics()) {
                return new DoubleFunction() { // from class: dkc
                    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                    public final double invoke(double d) {
                        return Rgb.Companion.a(function, d);
                    }
                };
            }
            if (function.isPQish$ui_graphics()) {
                return new DoubleFunction() { // from class: ekc
                    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                    public final double invoke(double d) {
                        return Rgb.Companion.d(function, d);
                    }
                };
            }
            return (function.getE() == 0.0d && function.getF() == 0.0d) ? new DoubleFunction() { // from class: fkc
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return Rgb.Companion.b(function, d);
                }
            } : new DoubleFunction() { // from class: gkc
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return Rgb.Companion.g(function, d);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DoubleFunction generateOetf(final TransferParameters function) {
            if (function.isHLGish$ui_graphics()) {
                return new DoubleFunction() { // from class: hkc
                    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                    public final double invoke(double d) {
                        return Rgb.Companion.f(function, d);
                    }
                };
            }
            if (function.isPQish$ui_graphics()) {
                return new DoubleFunction() { // from class: ikc
                    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                    public final double invoke(double d) {
                        return Rgb.Companion.e(function, d);
                    }
                };
            }
            return (function.getE() == 0.0d && function.getF() == 0.0d) ? new DoubleFunction() { // from class: jkc
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return Rgb.Companion.h(function, d);
                }
            } : new DoubleFunction() { // from class: kkc
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return Rgb.Companion.c(function, d);
                }
            };
        }

        public static double h(TransferParameters transferParameters, double d) {
            return ColorSpaceKt.rcpResponse(d, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getGamma());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSrgb(float[] primaries, WhitePoint whitePoint, DoubleFunction OETF, DoubleFunction EOTF, float min, float max, int id) {
            if (id == 0) {
                return true;
            }
            ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
            if (!ColorSpaceKt.compare(primaries, colorSpaces.getSrgbPrimaries$ui_graphics()) || !ColorSpaceKt.compare(whitePoint, Illuminant.INSTANCE.getD65()) || min != 0.0f || max != 1.0f) {
                return false;
            }
            Rgb srgb = colorSpaces.getSrgb();
            for (double d = 0.0d; d <= 1.0d; d += 0.00392156862745098d) {
                if (!compare(d, OETF, srgb.getOetfOrig()) || !compare(d, EOTF, srgb.getEotfOrig())) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isWideGamut(float[] primaries, float min, float max) {
            float fArea = area(primaries);
            ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
            if (fArea / area(colorSpaces.getNtsc1953Primaries$ui_graphics()) <= 0.9f || !contains(primaries, colorSpaces.getSrgbPrimaries$ui_graphics())) {
                return min < 0.0f && max > 1.0f;
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float[] xyPrimaries(float[] primaries) {
            float[] fArr = new float[6];
            if (primaries.length != 9) {
                ArraysKt.copyInto$default(primaries, fArr, 0, 0, 6, 6, (Object) null);
                return fArr;
            }
            float f = primaries[0];
            float f2 = primaries[1];
            float f3 = f + f2 + primaries[2];
            fArr[0] = f / f3;
            fArr[1] = f2 / f3;
            float f4 = primaries[3];
            float f5 = primaries[4];
            float f6 = f4 + f5 + primaries[5];
            fArr[2] = f4 / f6;
            fArr[3] = f5 / f6;
            float f7 = primaries[6];
            float f8 = primaries[7];
            float f9 = f7 + f8 + primaries[8];
            fArr[4] = f7 / f9;
            fArr[5] = f8 / f9;
            return fArr;
        }

        public final float[] computePrimaries$ui_graphics(float[] toXYZ) {
            float[] fArrMul3x3Float3 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{1.0f, 0.0f, 0.0f});
            float[] fArrMul3x3Float4 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{0.0f, 1.0f, 0.0f});
            float[] fArrMul3x3Float5 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{0.0f, 0.0f, 1.0f});
            float f = fArrMul3x3Float3[0];
            float f2 = fArrMul3x3Float3[1];
            float f3 = f + f2 + fArrMul3x3Float3[2];
            float f4 = fArrMul3x3Float4[0];
            float f5 = fArrMul3x3Float4[1];
            float f6 = f4 + f5 + fArrMul3x3Float4[2];
            float f7 = fArrMul3x3Float5[0];
            float f8 = fArrMul3x3Float5[1];
            float f9 = f7 + f8 + fArrMul3x3Float5[2];
            return new float[]{f / f3, f2 / f3, f4 / f6, f5 / f6, f7 / f9, f8 / f9};
        }

        private Companion() {
        }
    }

    public final float[] getInverseTransform() {
        float[] fArr = this.inverseTransform;
        return Arrays.copyOf(fArr, fArr.length);
    }

    public final float[] getPrimaries() {
        float[] fArr = this.primaries;
        return Arrays.copyOf(fArr, fArr.length);
    }

    public final float[] getTransform() {
        float[] fArr = this.transform;
        return Arrays.copyOf(fArr, fArr.length);
    }

    public final float[] fromLinear(float r, float g, float b) {
        return fromLinear(new float[]{r, g, b});
    }

    public final float[] toLinear(float r, float g, float b) {
        return toLinear(new float[]{r, g, b});
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, final Function1<? super Double, Double> function1, final Function1<? super Double, Double> function2) {
        Companion companion = INSTANCE;
        this(str, companion.computePrimaries$ui_graphics(fArr), companion.computeWhitePoint(fArr), null, new DoubleFunction() { // from class: xjc
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return Rgb.h(function1, d);
            }
        }, new DoubleFunction() { // from class: yjc
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return Rgb.e(function2, d);
            }
        }, 0.0f, 1.0f, null, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, final Function1<? super Double, Double> function1, final Function1<? super Double, Double> function2, float f, float f2) {
        this(str, fArr, whitePoint, null, new DoubleFunction() { // from class: bkc
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return Rgb.b(function1, d);
            }
        }, new DoubleFunction() { // from class: ckc
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return Rgb.g(function2, d);
            }
        }, f, f2, null, -1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, TransferParameters transferParameters) {
        Companion companion = INSTANCE;
        this(str, companion.computePrimaries$ui_graphics(fArr), companion.computeWhitePoint(fArr), transferParameters, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, TransferParameters transferParameters) {
        this(str, fArr, whitePoint, transferParameters, -1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, WhitePoint whitePoint, TransferParameters transferParameters, int i) {
        Companion companion = INSTANCE;
        this(str, fArr, whitePoint, null, companion.generateOetf(transferParameters), companion.generateEotf(transferParameters), 0.0f, 1.0f, transferParameters, i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, double d) {
        Companion companion = INSTANCE;
        this(str, companion.computePrimaries$ui_graphics(fArr), companion.computeWhitePoint(fArr), d, 0.0f, 1.0f, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, double d) {
        this(str, fArr, whitePoint, d, 0.0f, 1.0f, -1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, WhitePoint whitePoint, final double d, float f, float f2, int i) {
        DoubleFunction doubleFunction;
        DoubleFunction doubleFunction2;
        if (d == 1.0d) {
            doubleFunction = DoubleIdentity;
        } else {
            doubleFunction = new DoubleFunction() { // from class: zjc
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d2) {
                    return Rgb.c(d, d2);
                }
            };
        }
        DoubleFunction doubleFunction3 = doubleFunction;
        if (d == 1.0d) {
            doubleFunction2 = DoubleIdentity;
        } else {
            doubleFunction2 = new DoubleFunction() { // from class: akc
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d2) {
                    return Rgb.i(d, d2);
                }
            };
        }
        this(str, fArr, whitePoint, null, doubleFunction3, doubleFunction2, f, f2, new TransferParameters(d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 96, null), i);
    }

    public Rgb(Rgb rgb, float[] fArr, WhitePoint whitePoint) {
        this(rgb.getName(), rgb.primaries, whitePoint, fArr, rgb.oetfOrig, rgb.eotfOrig, rgb.min, rgb.max, rgb.transferParameters, -1);
    }
}
