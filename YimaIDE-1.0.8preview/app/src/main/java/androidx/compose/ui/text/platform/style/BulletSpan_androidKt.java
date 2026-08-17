package androidx.compose.ui.text.platform.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPathEffect_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a4\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a;\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0002¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"setDrawStyle", "", "Landroid/graphics/Paint;", "value", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "draw", "Landroidx/compose/ui/graphics/Outline;", "canvas", "Landroid/graphics/Canvas;", "paint", "xStart", "", "yCenter", "dir", "", "setBrushAndDraw", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "size", "Landroidx/compose/ui/geometry/Size;", "Lkotlin/Function0;", "setBrushAndDraw-yzxVdVo", "(Landroid/graphics/Paint;Landroidx/compose/ui/graphics/Brush;FJLkotlin/jvm/functions/Function0;)V", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BulletSpan_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void draw(Outline outline, Canvas canvas, Paint paint, float f, float f2, int i) {
        if (outline instanceof Outline.Generic) {
            canvas.save();
            Outline.Generic generic = (Outline.Generic) outline;
            Rect rect = generic.getRect();
            canvas.translate(f, f2 - ((rect.getBottom() - rect.getTop()) / 2.0f));
            Path path = generic.getPath();
            if (!(path instanceof AndroidPath)) {
                c41.a("Unable to obtain android.graphics.Path");
                return;
            } else {
                canvas.drawPath(((AndroidPath) path).getInternalPath(), paint);
                canvas.restore();
                return;
            }
        }
        if (!(outline instanceof Outline.Rounded)) {
            if (!(outline instanceof Outline.Rectangle)) {
                bu8.a();
                return;
            }
            Outline.Rectangle rectangle = (Outline.Rectangle) outline;
            Rect rect2 = rectangle.getRect();
            float bottom = f2 - ((rect2.getBottom() - rect2.getTop()) / 2.0f);
            Rect rect3 = rectangle.getRect();
            float right = f + (i * (rect3.getRight() - rect3.getLeft()));
            Rect rect4 = rectangle.getRect();
            canvas.drawRect(f, bottom, right, f2 + ((rect4.getBottom() - rect4.getTop()) / 2.0f), paint);
            return;
        }
        Outline.Rounded rounded = (Outline.Rounded) outline;
        if (RoundRectKt.isSimple(rounded.getRoundRect())) {
            float fIntBitsToFloat = Float.intBitsToFloat((int) (rounded.getRoundRect().m2939getTopLeftCornerRadiuskKHJgLs() >> 32));
            canvas.drawRoundRect(f, f2 - (rounded.getRoundRect().getHeight() / 2.0f), (i * rounded.getRoundRect().getWidth()) + f, (rounded.getRoundRect().getHeight() / 2.0f) + f2, fIntBitsToFloat, fIntBitsToFloat, paint);
            return;
        }
        Path Path = AndroidPath_androidKt.Path();
        Path.addRoundRect$default(Path, rounded.getRoundRect(), null, 2, null);
        canvas.save();
        canvas.translate(f, f2 - (rounded.getRoundRect().getHeight() / 2.0f));
        if (!(Path instanceof AndroidPath)) {
            c41.a("Unable to obtain android.graphics.Path");
        } else {
            canvas.drawPath(((AndroidPath) Path).getInternalPath(), paint);
            canvas.restore();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setBrushAndDraw-yzxVdVo, reason: not valid java name */
    public static final void m5757setBrushAndDrawyzxVdVo(Paint paint, Brush brush, float f, long j, Function0<Unit> function0) {
        Integer numValueOf = null;
        if (brush == null) {
            if (!Float.isNaN(f)) {
                numValueOf = Integer.valueOf(paint.getAlpha());
                paint.setAlpha((int) Math.rint(f * 255.0f));
            }
            function0.invoke();
            if (numValueOf != null) {
                paint.setAlpha(numValueOf.intValue());
                return;
            }
            return;
        }
        if (brush instanceof SolidColor) {
            int color = paint.getColor();
            if (!Float.isNaN(f)) {
                numValueOf = Integer.valueOf(paint.getAlpha());
                paint.setAlpha((int) Math.rint(f * 255.0f));
            }
            paint.setColor(ColorKt.m3188toArgb8_81llA(((SolidColor) brush).getValue()));
            function0.invoke();
            paint.setColor(color);
            if (numValueOf != null) {
                paint.setAlpha(numValueOf.intValue());
                return;
            }
            return;
        }
        if (!(brush instanceof ShaderBrush)) {
            bu8.a();
            return;
        }
        Shader shader = paint.getShader();
        if (!Float.isNaN(f)) {
            numValueOf = Integer.valueOf(paint.getAlpha());
            paint.setAlpha((int) Math.rint(f * 255.0f));
        }
        paint.setShader(((ShaderBrush) brush).mo3103createShaderuvyYCjk(j));
        function0.invoke();
        paint.setShader(shader);
        if (numValueOf != null) {
            paint.setAlpha(numValueOf.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDrawStyle(Paint paint, DrawStyle drawStyle) {
        if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
            paint.setStyle(Paint.Style.FILL);
            return;
        }
        if (!(drawStyle instanceof Stroke)) {
            bu8.a();
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        Stroke stroke = (Stroke) drawStyle;
        paint.setStrokeWidth(stroke.getWidth());
        paint.setStrokeMiter(stroke.getMiter());
        paint.setStrokeCap(DrawStyleSpan_androidKt.m5758toAndroidCapBeK7IIE(stroke.getCap()));
        paint.setStrokeJoin(DrawStyleSpan_androidKt.m5759toAndroidJoinWw9F2mQ(stroke.getJoin()));
        PathEffect pathEffect = stroke.getPathEffect();
        paint.setPathEffect(pathEffect != null ? AndroidPathEffect_androidKt.asAndroidPathEffect(pathEffect) : null);
    }
}
