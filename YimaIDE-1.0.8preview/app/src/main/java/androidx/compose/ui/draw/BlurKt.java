package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RenderEffectKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a-\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"blur", "Landroidx/compose/ui/Modifier;", "radiusX", "Landroidx/compose/ui/unit/Dp;", "radiusY", "edgeTreatment", "Landroidx/compose/ui/draw/BlurredEdgeTreatment;", "blur-1fqS-gw", "(Landroidx/compose/ui/Modifier;FFLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "radius", "blur-F8QBwvs", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BlurKt {
    /* JADX INFO: renamed from: blur-1fqS-gw, reason: not valid java name */
    public static final Modifier m2647blur1fqSgw(Modifier modifier, final float f, final float f2, final Shape shape) {
        int iM3530getDecal3opZhB0;
        boolean z;
        if (shape != null) {
            iM3530getDecal3opZhB0 = TileMode.INSTANCE.m3529getClamp3opZhB0();
            z = true;
        } else {
            iM3530getDecal3opZhB0 = TileMode.INSTANCE.m3530getDecal3opZhB0();
            z = false;
        }
        final int i = iM3530getDecal3opZhB0;
        final boolean z2 = z;
        return ((Dp.m6021compareTo0680j_4(f, Dp.m6022constructorimpl(0.0f)) <= 0 || Dp.m6021compareTo0680j_4(f2, Dp.m6022constructorimpl(0.0f)) <= 0) && !z2) ? modifier : GraphicsLayerModifierKt.graphicsLayer(modifier, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.ui.draw.BlurKt$blur$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                float fMo4557toPx0680j_4 = graphicsLayerScope.mo4557toPx0680j_4(f);
                float fMo4557toPx0680j_5 = graphicsLayerScope.mo4557toPx0680j_4(f2);
                graphicsLayerScope.setRenderEffect((fMo4557toPx0680j_4 <= 0.0f || fMo4557toPx0680j_5 <= 0.0f) ? null : RenderEffectKt.m3458BlurEffect3YTHUZs(fMo4557toPx0680j_4, fMo4557toPx0680j_5, i));
                Shape rectangleShape = shape;
                if (rectangleShape == null) {
                    rectangleShape = RectangleShapeKt.getRectangleShape();
                }
                graphicsLayerScope.setShape(rectangleShape);
                graphicsLayerScope.setClip(z2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((GraphicsLayerScope) obj);
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: renamed from: blur-1fqS-gw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2648blur1fqSgw$default(Modifier modifier, float f, float f2, BlurredEdgeTreatment blurredEdgeTreatment, int i, Object obj) {
        if ((i & 4) != 0) {
            blurredEdgeTreatment = BlurredEdgeTreatment.m2651boximpl(BlurredEdgeTreatment.INSTANCE.m2658getRectangleGoahg());
        }
        return m2647blur1fqSgw(modifier, f, f2, blurredEdgeTreatment.m2657unboximpl());
    }

    /* JADX INFO: renamed from: blur-F8QBwvs, reason: not valid java name */
    public static final Modifier m2649blurF8QBwvs(Modifier modifier, float f, Shape shape) {
        return m2647blur1fqSgw(modifier, f, f, shape);
    }

    /* JADX INFO: renamed from: blur-F8QBwvs$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2650blurF8QBwvs$default(Modifier modifier, float f, BlurredEdgeTreatment blurredEdgeTreatment, int i, Object obj) {
        if ((i & 2) != 0) {
            blurredEdgeTreatment = BlurredEdgeTreatment.m2651boximpl(BlurredEdgeTreatment.INSTANCE.m2658getRectangleGoahg());
        }
        return m2649blurF8QBwvs(modifier, f, blurredEdgeTreatment.m2657unboximpl());
    }
}
