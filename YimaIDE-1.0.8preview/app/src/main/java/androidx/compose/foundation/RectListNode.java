package androidx.compose.foundation;

import android.graphics.Rect;
import android.view.View;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b!\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0017H&J\u0016\u0010\u0018\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0017H&J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0006H\u0002R(\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/RectListNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "rect", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getRect", "()Lkotlin/jvm/functions/Function1;", "setRect", "androidRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onGloballyPositioned", "", "coordinates", "onDetach", "currentRects", "Landroidx/compose/runtime/collection/MutableVector;", "updateRects", "rects", "replaceRect", "newRect", "calcBounds", "layoutCoordinates", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class RectListNode extends Modifier.Node implements GlobalPositionAwareModifierNode {
    public static final int $stable = 8;
    private Rect androidRect;
    private Function1<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> rect;

    public RectListNode(Function1<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> function1) {
        this.rect = function1;
    }

    private final Rect calcBounds(LayoutCoordinates layoutCoordinates, androidx.compose.ui.geometry.Rect rect) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates);
        long j = layoutCoordinatesFindRootCoordinates.localPositionOf-R5De75A(layoutCoordinates, rect.getTopLeft-F1C5BW0());
        long j2 = layoutCoordinatesFindRootCoordinates.localPositionOf-R5De75A(layoutCoordinates, rect.getTopRight-F1C5BW0());
        long j3 = layoutCoordinatesFindRootCoordinates.localPositionOf-R5De75A(layoutCoordinates, rect.getBottomLeft-F1C5BW0());
        long j4 = layoutCoordinatesFindRootCoordinates.localPositionOf-R5De75A(layoutCoordinates, rect.getBottomRight-F1C5BW0());
        int i = (int) (j >> 32);
        int i2 = (int) (j2 >> 32);
        int i3 = (int) (j3 >> 32);
        int i4 = (int) (j4 >> 32);
        int i5 = (int) (j & 4294967295L);
        int i6 = (int) (j2 & 4294967295L);
        int i7 = (int) (j3 & 4294967295L);
        int i8 = (int) (j4 & 4294967295L);
        return new Rect(MathKt.roundToInt(ComparisonsKt.minOf(Float.intBitsToFloat(i), new float[]{Float.intBitsToFloat(i2), Float.intBitsToFloat(i3), Float.intBitsToFloat(i4)})), MathKt.roundToInt(ComparisonsKt.minOf(Float.intBitsToFloat(i5), new float[]{Float.intBitsToFloat(i6), Float.intBitsToFloat(i7), Float.intBitsToFloat(i8)})), MathKt.roundToInt(ComparisonsKt.maxOf(Float.intBitsToFloat(i), new float[]{Float.intBitsToFloat(i2), Float.intBitsToFloat(i3), Float.intBitsToFloat(i4)})), MathKt.roundToInt(ComparisonsKt.maxOf(Float.intBitsToFloat(i5), new float[]{Float.intBitsToFloat(i6), Float.intBitsToFloat(i7), Float.intBitsToFloat(i8)})));
    }

    private final void replaceRect(Rect newRect) {
        MutableVector<Rect> mutableVectorCurrentRects = currentRects();
        Rect rect = this.androidRect;
        if (rect != null) {
            mutableVectorCurrentRects.remove(rect);
        }
        if (newRect != null && !newRect.isEmpty()) {
            mutableVectorCurrentRects.add(newRect);
        }
        updateRects(mutableVectorCurrentRects);
        this.androidRect = newRect;
    }

    public abstract MutableVector<Rect> currentRects();

    public Function1<LayoutCoordinates, androidx.compose.ui.geometry.Rect> getRect() {
        return this.rect;
    }

    public final View getView() {
        return DelegatableNode_androidKt.requireView(this);
    }

    public void onDetach() {
        super.onDetach();
        replaceRect(null);
    }

    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        Rect rectCalcBounds;
        if (getRect() == null) {
            androidx.compose.ui.geometry.Rect rectBoundsInRoot = LayoutCoordinatesKt.boundsInRoot(coordinates);
            rectCalcBounds = new Rect(MathKt.roundToInt(rectBoundsInRoot.getLeft()), MathKt.roundToInt(rectBoundsInRoot.getTop()), MathKt.roundToInt(rectBoundsInRoot.getRight()), MathKt.roundToInt(rectBoundsInRoot.getBottom()));
        } else {
            Function1<LayoutCoordinates, androidx.compose.ui.geometry.Rect> rect = getRect();
            rect.getClass();
            rectCalcBounds = calcBounds(coordinates, (androidx.compose.ui.geometry.Rect) rect.invoke(coordinates));
        }
        replaceRect(rectCalcBounds);
    }

    public void setRect(Function1<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> function1) {
        this.rect = function1;
    }

    public abstract void updateRects(MutableVector<Rect> rects);
}
