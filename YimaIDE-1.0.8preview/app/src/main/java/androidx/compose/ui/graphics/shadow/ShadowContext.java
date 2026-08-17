package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016\u0082\u0001\u0001\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/graphics/shadow/ShadowContext;", "", "createInnerShadowPainter", "Landroidx/compose/ui/graphics/shadow/InnerShadowPainter;", "shape", "Landroidx/compose/ui/graphics/Shape;", "shadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "createDropShadowPainter", "Landroidx/compose/ui/graphics/shadow/DropShadowPainter;", "clearCache", "", "Landroidx/compose/ui/graphics/shadow/PlatformShadowContext;", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface ShadowContext {
    default void clearCache() {
    }

    default DropShadowPainter createDropShadowPainter(Shape shape, Shadow shadow) {
        return new DropShadowPainter(shape, shadow);
    }

    default InnerShadowPainter createInnerShadowPainter(Shape shape, Shadow shadow) {
        return new InnerShadowPainter(shape, shadow);
    }
}
