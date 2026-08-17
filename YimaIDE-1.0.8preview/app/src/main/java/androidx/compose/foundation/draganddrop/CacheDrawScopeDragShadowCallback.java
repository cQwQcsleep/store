package androidx.compose.foundation.draganddrop;

import androidx.compose.foundation.draganddrop.CacheDrawScopeDragShadowCallback;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/draganddrop/CacheDrawScopeDragShadowCallback;", "", "<init>", "()V", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "drawDragShadow", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "cachePicture", "Landroidx/compose/ui/draw/DrawResult;", "scope", "Landroidx/compose/ui/draw/CacheDrawScope;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CacheDrawScopeDragShadowCallback {
    public static final int $stable = 8;
    private GraphicsLayer graphicsLayer;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cachePicture$lambda$0$0$0(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cachePicture$lambda$0$1(CacheDrawScopeDragShadowCallback cacheDrawScopeDragShadowCallback, ContentDrawScope contentDrawScope) {
        GraphicsLayer graphicsLayer = cacheDrawScopeDragShadowCallback.graphicsLayer;
        graphicsLayer.getClass();
        GraphicsLayerKt.drawLayer(contentDrawScope, graphicsLayer);
        return Unit.INSTANCE;
    }

    public final DrawResult cachePicture(CacheDrawScope scope) {
        GraphicsLayer graphicsLayerObtainGraphicsLayer = scope.obtainGraphicsLayer();
        CacheDrawScope.record-TdoYBX4$default(scope, graphicsLayerObtainGraphicsLayer, (Density) null, (LayoutDirection) null, 0L, new Function1() { // from class: z81
            public final Object invoke(Object obj) {
                return CacheDrawScopeDragShadowCallback.cachePicture$lambda$0$0$0((ContentDrawScope) obj);
            }
        }, 7, (Object) null);
        this.graphicsLayer = graphicsLayerObtainGraphicsLayer;
        return scope.onDrawWithContent(new Function1() { // from class: a91
            public final Object invoke(Object obj) {
                return CacheDrawScopeDragShadowCallback.cachePicture$lambda$0$1(this.b, (ContentDrawScope) obj);
            }
        });
    }

    public final void drawDragShadow(DrawScope drawScope) {
        GraphicsLayer graphicsLayer = this.graphicsLayer;
        if (graphicsLayer != null) {
            GraphicsLayerKt.drawLayer(drawScope, graphicsLayer);
        } else {
            w01.a("No cached drag shadow. Check if the drag source node was rendered first");
        }
    }
}
