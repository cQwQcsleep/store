package androidx.compose.ui.text.platform;

import android.graphics.Shader;
import android.text.TextPaint;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.platform.extensions.TextPaintExtensions_androidKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0013\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u00104\u001a\u0002052\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u00106\u001a\u0002052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0015\u00107\u001a\u0002052\u0006\u00108\u001a\u00020\u001b¢\u0006\u0004\b9\u0010:J)\u0010;\u001a\u0002052\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010<\u001a\u00020,2\b\b\u0002\u0010=\u001a\u00020\u0005¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u0002052\b\u00102\u001a\u0004\u0018\u000103J\b\u0010G\u001a\u000205H\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R$\u0010\u0012\u001a\u00020\u00138\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u001c\u001a\u0004\u0018\u00010\u001d8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R*\u0010#\u001a\u0012\u0012\f\u0012\n\u0018\u00010%j\u0004\u0018\u0001`&\u0018\u00010$X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R&\u0010+\u001a\u0004\u0018\u00010,8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u0015\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010B\u001a\u00020\u00102\u0006\u0010A\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bC\u0010D\"\u0004\bE\u0010F¨\u0006H"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidTextPaint;", "Landroid/text/TextPaint;", "flags", "", "density", "", "<init>", "(IF)V", "backingComposePaint", "Landroidx/compose/ui/graphics/Paint;", "composePaint", "getComposePaint", "()Landroidx/compose/ui/graphics/Paint;", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "backingBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "I", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "getShadow$ui_text$annotations", "()V", "getShadow$ui_text", "()Landroidx/compose/ui/graphics/Shadow;", "setShadow$ui_text", "(Landroidx/compose/ui/graphics/Shadow;)V", "lastColor", "Landroidx/compose/ui/graphics/Color;", "brush", "Landroidx/compose/ui/graphics/Brush;", "getBrush$ui_text$annotations", "getBrush$ui_text", "()Landroidx/compose/ui/graphics/Brush;", "setBrush$ui_text", "(Landroidx/compose/ui/graphics/Brush;)V", "shaderState", "Landroidx/compose/runtime/State;", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "getShaderState$ui_text", "()Landroidx/compose/runtime/State;", "setShaderState$ui_text", "(Landroidx/compose/runtime/State;)V", "brushSize", "Landroidx/compose/ui/geometry/Size;", "getBrushSize-VsRJwc0$ui_text$annotations", "getBrushSize-VsRJwc0$ui_text", "()Landroidx/compose/ui/geometry/Size;", "setBrushSize-iaC8Vc4$ui_text", "(Landroidx/compose/ui/geometry/Size;)V", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "setTextDecoration", "", "setShadow", "setColor", "color", "setColor-8_81llA", "(J)V", "setBrush", "size", "alpha", "setBrush-12SF9DM", "(Landroidx/compose/ui/graphics/Brush;JF)V", "setDrawStyle", "value", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "clearShader", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidTextPaint extends TextPaint {
    public static final int $stable = 8;
    private int backingBlendMode;
    private Paint backingComposePaint;
    private Brush brush;
    private Size brushSize;
    private DrawStyle drawStyle;
    private Color lastColor;
    private State<? extends Shader> shaderState;
    private Shadow shadow;
    private TextDecoration textDecoration;

    public AndroidTextPaint(int i, float f) {
        super(i);
        ((TextPaint) this).density = f;
        this.textDecoration = TextDecoration.INSTANCE.getNone();
        this.backingBlendMode = DrawScope.INSTANCE.m3710getDefaultBlendMode0nO6VwU();
        this.shadow = Shadow.INSTANCE.getNone();
    }

    public static Shader a(Brush brush, long j) {
        return ((ShaderBrush) brush).mo3103createShaderuvyYCjk(j);
    }

    private final void clearShader() {
        this.shaderState = null;
        this.brush = null;
        this.brushSize = null;
        setShader(null);
    }

    public static /* synthetic */ void getBrush$ui_text$annotations() {
    }

    /* JADX INFO: renamed from: getBrushSize-VsRJwc0$ui_text$annotations, reason: not valid java name */
    public static /* synthetic */ void m5733getBrushSizeVsRJwc0$ui_text$annotations() {
    }

    private final Paint getComposePaint() {
        Paint paint = this.backingComposePaint;
        if (paint != null) {
            return paint;
        }
        Paint paintAsComposePaint = AndroidPaint_androidKt.asComposePaint(this);
        this.backingComposePaint = paintAsComposePaint;
        return paintAsComposePaint;
    }

    public static /* synthetic */ void getShadow$ui_text$annotations() {
    }

    /* JADX INFO: renamed from: setBrush-12SF9DM$default, reason: not valid java name */
    public static /* synthetic */ void m5734setBrush12SF9DM$default(AndroidTextPaint androidTextPaint, Brush brush, long j, float f, int i, Object obj) {
        if ((i & 4) != 0) {
            f = Float.NaN;
        }
        androidTextPaint.m5738setBrush12SF9DM(brush, j, f);
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name and from getter */
    public final int getBackingBlendMode() {
        return this.backingBlendMode;
    }

    /* JADX INFO: renamed from: getBrush$ui_text, reason: from getter */
    public final Brush getBrush() {
        return this.brush;
    }

    /* JADX INFO: renamed from: getBrushSize-VsRJwc0$ui_text, reason: not valid java name and from getter */
    public final Size getBrushSize() {
        return this.brushSize;
    }

    public final State<Shader> getShaderState$ui_text() {
        return this.shaderState;
    }

    /* JADX INFO: renamed from: getShadow$ui_text, reason: from getter */
    public final Shadow getShadow() {
        return this.shadow;
    }

    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m5737setBlendModes9anfk8(int i) {
        if (BlendMode.m3043equalsimpl0(i, this.backingBlendMode)) {
            return;
        }
        getComposePaint().mo3009setBlendModes9anfk8(i);
        this.backingBlendMode = i;
    }

    public final void setBrush$ui_text(Brush brush) {
        this.brush = brush;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0034  */
    /* JADX WARN: Code duplicated, block: B:20:0x003d  */
    /* JADX INFO: renamed from: setBrush-12SF9DM, reason: not valid java name */
    public final void m5738setBrush12SF9DM(final Brush brush, final long size, float alpha) {
        if (brush == null) {
            clearShader();
            return;
        }
        if (brush instanceof SolidColor) {
            m5740setColor8_81llA(TextDrawStyleKt.m5926modulateDxMtmZc(((SolidColor) brush).getValue(), alpha));
            return;
        }
        if (!(brush instanceof ShaderBrush)) {
            bu8.a();
            return;
        }
        if (Intrinsics.areEqual(this.brush, brush)) {
            Size size2 = this.brushSize;
            if (!(size2 == null ? false : Size.m2954equalsimpl0(size2.m2963unboximpl(), size))) {
                if (size != InlineClassHelperKt.UnspecifiedPackedFloats) {
                    this.brush = brush;
                    this.brushSize = Size.m2946boximpl(size);
                    this.shaderState = SnapshotStateKt.derivedStateOf(new Function0() { // from class: q70
                        public final Object invoke() {
                            return AndroidTextPaint.a(brush, size);
                        }
                    });
                }
            }
        } else if (size != InlineClassHelperKt.UnspecifiedPackedFloats) {
            this.brush = brush;
            this.brushSize = Size.m2946boximpl(size);
            this.shaderState = SnapshotStateKt.derivedStateOf(new Function0() { // from class: q70
                public final Object invoke() {
                    return AndroidTextPaint.a(brush, size);
                }
            });
        }
        Paint composePaint = getComposePaint();
        State<? extends Shader> state = this.shaderState;
        composePaint.setShader(state != null ? state.getValue() : null);
        this.lastColor = null;
        AndroidTextPaint_androidKt.setAlpha(this, alpha);
    }

    /* JADX INFO: renamed from: setBrushSize-iaC8Vc4$ui_text, reason: not valid java name */
    public final void m5739setBrushSizeiaC8Vc4$ui_text(Size size) {
        this.brushSize = size;
    }

    /* JADX INFO: renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m5740setColor8_81llA(long color) {
        Color color2 = this.lastColor;
        if ((color2 == null ? false : Color.m3135equalsimpl0(color2.m3144unboximpl(), color)) || color == 16) {
            return;
        }
        this.lastColor = Color.m3124boximpl(color);
        setColor(ColorKt.m3188toArgb8_81llA(color));
        clearShader();
    }

    public final void setDrawStyle(DrawStyle drawStyle) {
        if (drawStyle == null || Intrinsics.areEqual(this.drawStyle, drawStyle)) {
            return;
        }
        this.drawStyle = drawStyle;
        if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
            setStyle(android.graphics.Paint.Style.FILL);
            return;
        }
        if (!(drawStyle instanceof Stroke)) {
            bu8.a();
            return;
        }
        getComposePaint().mo3014setStylek9PVt8s(PaintingStyle.INSTANCE.m3416getStrokeTiuSbCo());
        Stroke stroke = (Stroke) drawStyle;
        getComposePaint().setStrokeWidth(stroke.getWidth());
        getComposePaint().setStrokeMiterLimit(stroke.getMiter());
        getComposePaint().mo3013setStrokeJoinWw9F2mQ(stroke.getJoin());
        getComposePaint().mo3012setStrokeCapBeK7IIE(stroke.getCap());
        getComposePaint().setPathEffect(stroke.getPathEffect());
    }

    public final void setShaderState$ui_text(State<? extends Shader> state) {
        this.shaderState = state;
    }

    public final void setShadow(Shadow shadow) {
        if (shadow == null || Intrinsics.areEqual(this.shadow, shadow)) {
            return;
        }
        this.shadow = shadow;
        if (Intrinsics.areEqual(shadow, Shadow.INSTANCE.getNone())) {
            clearShadowLayer();
        } else {
            setShadowLayer(TextPaintExtensions_androidKt.correctBlurRadius(this.shadow.getBlurRadius()), Float.intBitsToFloat((int) (this.shadow.getOffset() >> 32)), Float.intBitsToFloat((int) (this.shadow.getOffset() & 4294967295L)), ColorKt.m3188toArgb8_81llA(this.shadow.getColor()));
        }
    }

    public final void setShadow$ui_text(Shadow shadow) {
        this.shadow = shadow;
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        if (textDecoration == null || Intrinsics.areEqual(this.textDecoration, textDecoration)) {
            return;
        }
        this.textDecoration = textDecoration;
        TextDecoration.Companion companion = TextDecoration.INSTANCE;
        setUnderlineText(textDecoration.contains(companion.getUnderline()));
        setStrikeThruText(this.textDecoration.contains(companion.getLineThrough()));
    }
}
