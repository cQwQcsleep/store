package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u0000  2\u00020\u0001:\u0002\u001f B;\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fB!\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\u000eJ \u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0019\u001a\u00020\nH\u0017J\u0017\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0010¢\u0006\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector;", "", "source", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "destination", "transformSource", "transformDestination", "renderIntent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "transform", "", "<init>", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;I[FLkotlin/jvm/internal/DefaultConstructorMarker;)V", AccessibilityNodeInfoCompat.MathInfoCompat.MATH_ATTRIBUTE_INTENT, "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getDestination", "getRenderIntent-uksYyKA", "()I", "I", "r", "", "g", "b", "v", "transformToColor", "Landroidx/compose/ui/graphics/Color;", "color", "transformToColor-l2rxGTc$ui_graphics", "(J)J", "RgbConnector", "Companion", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class Connector {
    private final ColorSpace destination;
    private final int renderIntent;
    private final ColorSpace source;
    private final float[] transform;
    private final ColorSpace transformDestination;
    private final ColorSpace transformSource;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Illegal instructions before constructor call */
    private Connector(ColorSpace colorSpace, ColorSpace colorSpace2, int i) {
        long model = colorSpace.getModel();
        ColorModel.Companion companion = ColorModel.INSTANCE;
        this(colorSpace, colorSpace2, ColorModel.m3564equalsimpl0(model, companion.m3571getRgbxdoWZVw()) ? ColorSpaceKt.adapt$default(colorSpace, Illuminant.INSTANCE.getD50(), null, 2, null) : colorSpace, ColorModel.m3564equalsimpl0(colorSpace2.getModel(), companion.m3571getRgbxdoWZVw()) ? ColorSpaceKt.adapt$default(colorSpace2, Illuminant.INSTANCE.getD50(), null, 2, null) : colorSpace2, i, INSTANCE.m3581computeTransformYBCOT_4(colorSpace, colorSpace2, i), null);
    }

    public final ColorSpace getDestination() {
        return this.destination;
    }

    /* JADX INFO: renamed from: getRenderIntent-uksYyKA, reason: not valid java name and from getter */
    public final int getRenderIntent() {
        return this.renderIntent;
    }

    public final ColorSpace getSource() {
        return this.source;
    }

    public float[] transform(float[] v) {
        float[] xyz = this.transformSource.toXyz(v);
        float[] fArr = this.transform;
        if (fArr != null) {
            xyz[0] = xyz[0] * fArr[0];
            xyz[1] = xyz[1] * fArr[1];
            xyz[2] = xyz[2] * fArr[2];
        }
        return this.transformDestination.fromXyz(xyz);
    }

    /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics, reason: not valid java name */
    public long mo3579transformToColorl2rxGTc$ui_graphics(long color) {
        float fM3140getRedimpl = Color.m3140getRedimpl(color);
        float fM3139getGreenimpl = Color.m3139getGreenimpl(color);
        float fM3137getBlueimpl = Color.m3137getBlueimpl(color);
        float fM3136getAlphaimpl = Color.m3136getAlphaimpl(color);
        long xy$ui_graphics = this.transformSource.toXy$ui_graphics(fM3140getRedimpl, fM3139getGreenimpl, fM3137getBlueimpl);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (xy$ui_graphics >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (xy$ui_graphics & 4294967295L));
        float z$ui_graphics = this.transformSource.toZ$ui_graphics(fM3140getRedimpl, fM3139getGreenimpl, fM3137getBlueimpl);
        float[] fArr = this.transform;
        if (fArr != null) {
            fIntBitsToFloat *= fArr[0];
            fIntBitsToFloat2 *= fArr[1];
            z$ui_graphics *= fArr[2];
        }
        float f = fIntBitsToFloat;
        return this.transformDestination.mo3574xyzaToColorJlNiLsg$ui_graphics(f, fIntBitsToFloat2, z$ui_graphics, fM3136getAlphaimpl, this.destination);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector$Companion;", "", "<init>", "()V", "computeTransform", "", "source", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "destination", AccessibilityNodeInfoCompat.MathInfoCompat.MATH_ATTRIBUTE_INTENT, "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "computeTransform-YBCOT_4", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;I)[F", "identity", "Landroidx/compose/ui/graphics/colorspace/Connector;", "identity$ui_graphics", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        public final float[] m3581computeTransformYBCOT_4(ColorSpace source, ColorSpace destination, int intent) {
            if (!RenderIntent.m3587equalsimpl0(intent, RenderIntent.INSTANCE.m3591getAbsoluteuksYyKA())) {
                return null;
            }
            long model = source.getModel();
            ColorModel.Companion companion = ColorModel.INSTANCE;
            boolean zM3564equalsimpl0 = ColorModel.m3564equalsimpl0(model, companion.m3571getRgbxdoWZVw());
            boolean zM3564equalsimpl1 = ColorModel.m3564equalsimpl0(destination.getModel(), companion.m3571getRgbxdoWZVw());
            if (zM3564equalsimpl0 && zM3564equalsimpl1) {
                return null;
            }
            if (!zM3564equalsimpl0 && !zM3564equalsimpl1) {
                return null;
            }
            if (!zM3564equalsimpl0) {
                source = destination;
            }
            Rgb rgb = (Rgb) source;
            float[] xyz$ui_graphics = zM3564equalsimpl0 ? rgb.getWhitePoint().toXyz$ui_graphics() : Illuminant.INSTANCE.getD50Xyz$ui_graphics();
            float[] xyz$ui_graphics2 = zM3564equalsimpl1 ? rgb.getWhitePoint().toXyz$ui_graphics() : Illuminant.INSTANCE.getD50Xyz$ui_graphics();
            return new float[]{xyz$ui_graphics[0] / xyz$ui_graphics2[0], xyz$ui_graphics[1] / xyz$ui_graphics2[1], xyz$ui_graphics[2] / xyz$ui_graphics2[2]};
        }

        public final Connector identity$ui_graphics(final ColorSpace source) {
            final int iM3593getRelativeuksYyKA = RenderIntent.INSTANCE.m3593getRelativeuksYyKA();
            return new Connector(source, iM3593getRelativeuksYyKA) { // from class: androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1
                {
                    super(source, source, iM3593getRelativeuksYyKA, null);
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                public float[] transform(float[] v) {
                    return v;
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics */
                public long mo3579transformToColorl2rxGTc$ui_graphics(long color) {
                    return color;
                }
            };
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0017\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0010¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector$RgbConnector;", "Landroidx/compose/ui/graphics/colorspace/Connector;", "mSource", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "mDestination", AccessibilityNodeInfoCompat.MathInfoCompat.MATH_ATTRIBUTE_INTENT, "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "<init>", "(Landroidx/compose/ui/graphics/colorspace/Rgb;Landroidx/compose/ui/graphics/colorspace/Rgb;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "mTransform", "", "transform", "v", "transformToColor", "Landroidx/compose/ui/graphics/Color;", "color", "transformToColor-l2rxGTc$ui_graphics", "(J)J", "computeTransform", "source", "destination", "computeTransform-YBCOT_4", "(Landroidx/compose/ui/graphics/colorspace/Rgb;Landroidx/compose/ui/graphics/colorspace/Rgb;I)[F", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class RgbConnector extends Connector {
        public static final int $stable = 8;
        private final Rgb mDestination;
        private final Rgb mSource;
        private final float[] mTransform;

        private RgbConnector(Rgb rgb, Rgb rgb2, int i) {
            super(rgb, rgb2, rgb, rgb2, i, null, null);
            this.mSource = rgb;
            this.mDestination = rgb2;
            this.mTransform = m3582computeTransformYBCOT_4(rgb, rgb2, i);
        }

        /* JADX INFO: renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        private final float[] m3582computeTransformYBCOT_4(Rgb source, Rgb destination, int intent) {
            if (ColorSpaceKt.compare(source.getWhitePoint(), destination.getWhitePoint())) {
                return ColorSpaceKt.mul3x3(destination.getInverseTransform(), source.getTransform());
            }
            float[] transform = source.getTransform();
            float[] inverseTransform = destination.getInverseTransform();
            float[] xyz$ui_graphics = source.getWhitePoint().toXyz$ui_graphics();
            float[] xyz$ui_graphics2 = destination.getWhitePoint().toXyz$ui_graphics();
            WhitePoint whitePoint = source.getWhitePoint();
            Illuminant illuminant = Illuminant.INSTANCE;
            if (!ColorSpaceKt.compare(whitePoint, illuminant.getD50())) {
                transform = ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(Adaptation.INSTANCE.getBradford().getTransform(), xyz$ui_graphics, illuminant.newD50Xyz$ui_graphics()), source.getTransform());
            }
            if (!ColorSpaceKt.compare(destination.getWhitePoint(), illuminant.getD50())) {
                inverseTransform = ColorSpaceKt.inverse3x3(ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(Adaptation.INSTANCE.getBradford().getTransform(), xyz$ui_graphics2, illuminant.newD50Xyz$ui_graphics()), destination.getTransform()));
            }
            if (RenderIntent.m3587equalsimpl0(intent, RenderIntent.INSTANCE.m3591getAbsoluteuksYyKA())) {
                transform = ColorSpaceKt.mul3x3Diag(new float[]{xyz$ui_graphics[0] / xyz$ui_graphics2[0], xyz$ui_graphics[1] / xyz$ui_graphics2[1], xyz$ui_graphics[2] / xyz$ui_graphics2[2]}, transform);
            }
            return ColorSpaceKt.mul3x3(inverseTransform, transform);
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        public float[] transform(float[] v) {
            v[0] = (float) this.mSource.getEotfFunc().invoke(v[0]);
            v[1] = (float) this.mSource.getEotfFunc().invoke(v[1]);
            v[2] = (float) this.mSource.getEotfFunc().invoke(v[2]);
            ColorSpaceKt.mul3x3Float3(this.mTransform, v);
            v[0] = (float) this.mDestination.getOetfFunc().invoke(v[0]);
            v[1] = (float) this.mDestination.getOetfFunc().invoke(v[1]);
            v[2] = (float) this.mDestination.getOetfFunc().invoke(v[2]);
            return v;
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics */
        public long mo3579transformToColorl2rxGTc$ui_graphics(long color) {
            float fM3140getRedimpl = Color.m3140getRedimpl(color);
            float fM3139getGreenimpl = Color.m3139getGreenimpl(color);
            float fM3137getBlueimpl = Color.m3137getBlueimpl(color);
            float fM3136getAlphaimpl = Color.m3136getAlphaimpl(color);
            float fInvoke = (float) this.mSource.getEotfFunc().invoke(fM3140getRedimpl);
            float fInvoke2 = (float) this.mSource.getEotfFunc().invoke(fM3139getGreenimpl);
            float fInvoke3 = (float) this.mSource.getEotfFunc().invoke(fM3137getBlueimpl);
            float[] fArr = this.mTransform;
            return ColorKt.Color((float) this.mDestination.getOetfFunc().invoke((fArr[0] * fInvoke) + (fArr[3] * fInvoke2) + (fArr[6] * fInvoke3)), (float) this.mDestination.getOetfFunc().invoke((fArr[1] * fInvoke) + (fArr[4] * fInvoke2) + (fArr[7] * fInvoke3)), (float) this.mDestination.getOetfFunc().invoke((fArr[2] * fInvoke) + (fArr[5] * fInvoke2) + (fArr[8] * fInvoke3)), fM3136getAlphaimpl, this.mDestination);
        }

        public /* synthetic */ RgbConnector(Rgb rgb, Rgb rgb2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rgb, rgb2, i);
        }
    }

    public final float[] transform(float r, float g, float b) {
        return transform(new float[]{r, g, b});
    }

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, int i, float[] fArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, colorSpace3, colorSpace4, i, fArr);
    }

    private Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, int i, float[] fArr) {
        this.source = colorSpace;
        this.destination = colorSpace2;
        this.transformSource = colorSpace3;
        this.transformDestination = colorSpace4;
        this.renderIntent = i;
        this.transform = fArr;
    }

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, i);
    }
}
