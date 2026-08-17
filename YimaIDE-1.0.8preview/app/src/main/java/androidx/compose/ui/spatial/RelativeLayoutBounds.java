package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001f0'J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0000J&\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019J\u0006\u00100\u001a\u00020)J\u001d\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006¢\u0006\u0004\b4\u00105J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00109\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0011\u0010$\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b%\u0010!¨\u0006:"}, d2 = {"Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "", "topLeft", "", "bottomRight", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "windowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "<init>", "(JJJJJ[FLandroidx/compose/ui/node/DelegatableNode;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "[F", "positionInRoot", "getPositionInRoot-nOcc-ac", "()J", "positionInWindow", "getPositionInWindow-nOcc-ac", "positionInScreen", "getPositionInScreen-nOcc-ac", "width", "", "getWidth", "()I", "height", "getHeight", "boundsInRoot", "Landroidx/compose/ui/unit/IntRect;", "getBoundsInRoot", "()Landroidx/compose/ui/unit/IntRect;", "boundsInWindow", "getBoundsInWindow", "boundsInScreen", "getBoundsInScreen", "calculateOcclusions", "", "fractionVisibleIn", "", "viewport", "fractionVisibleInRect", "left", "top", "right", "bottom", "fractionVisibleInWindow", "fractionVisibleInWindowWithInsets", "topLeftInset", "bottomRightInset", "fractionVisibleInWindowWithInsets-E1MhUcY", "(JJ)F", "equals", "", "other", "hashCode", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RelativeLayoutBounds {
    public static final int $stable = 8;
    private final long bottomRight;
    private final DelegatableNode node;
    private final long screenOffset;
    private final long topLeft;
    private final float[] viewToWindowMatrix;
    private final long windowOffset;
    private final long windowSize;

    private RelativeLayoutBounds(long j, long j2, long j3, long j4, long j5, float[] fArr, DelegatableNode delegatableNode) {
        this.topLeft = j;
        this.bottomRight = j2;
        this.windowOffset = j3;
        this.screenOffset = j4;
        this.windowSize = j5;
        this.viewToWindowMatrix = fArr;
        this.node = delegatableNode;
    }

    public final List<IntRect> calculateOcclusions() {
        int i;
        int i2;
        RectManager rectManager = DelegatableNodeKt.requireOwner(this.node).getRectManager();
        int semanticsId = DelegatableNodeKt.requireLayoutNode(this.node).getSemanticsId();
        RectList rects = rectManager.getRects();
        int iIndexOf = rects.indexOf(semanticsId);
        if (iIndexOf < 0) {
            return CollectionsKt.emptyList();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        long[] jArr = rects.items;
        int i3 = rects.itemsSize;
        long j = jArr[iIndexOf];
        long j2 = jArr[iIndexOf + 1];
        int i4 = 0;
        while (i4 < jArr.length - 2 && i4 < i3) {
            if (i4 == iIndexOf) {
                i4 += 3;
            } else {
                long j3 = jArr[i4];
                long j4 = jArr[i4 + 1];
                if (((((j2 - j3) - InlineClassHelperKt.Uint64Low32) | ((j4 - j) - InlineClassHelperKt.Uint64Low32)) & (-9223372034707292160L)) == 0) {
                    i = i3;
                    i2 = iIndexOf;
                    int i5 = (int) (j3 >> 32);
                    int i6 = (int) j3;
                    int i7 = (int) (j4 >> 32);
                    int i8 = (int) j4;
                    if (rectManager.isTargetDrawnFirst$ui(semanticsId, ((int) jArr[i4 + 2]) & 33554431)) {
                        listCreateListBuilder.add(new IntRect(i5, i6, i7, i8));
                    }
                } else {
                    i = i3;
                    i2 = iIndexOf;
                }
                i4 += 3;
                iIndexOf = i2;
                i3 = i;
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x004d  */
    public boolean equals(Object other) {
        boolean zM3380equalsimpl0;
        if (this == other) {
            return true;
        }
        if (other == null || RelativeLayoutBounds.class != other.getClass()) {
            return false;
        }
        RelativeLayoutBounds relativeLayoutBounds = (RelativeLayoutBounds) other;
        if (this.topLeft != relativeLayoutBounds.topLeft || this.bottomRight != relativeLayoutBounds.bottomRight || this.windowSize != relativeLayoutBounds.windowSize || !IntOffset.m6149equalsimpl0(this.windowOffset, relativeLayoutBounds.windowOffset) || !IntOffset.m6149equalsimpl0(this.screenOffset, relativeLayoutBounds.screenOffset)) {
            return false;
        }
        float[] fArr = this.viewToWindowMatrix;
        float[] fArr2 = relativeLayoutBounds.viewToWindowMatrix;
        if (fArr == null) {
            if (fArr2 == null) {
                zM3380equalsimpl0 = true;
            } else {
                zM3380equalsimpl0 = false;
            }
        } else if (fArr2 == null) {
            zM3380equalsimpl0 = false;
        } else {
            zM3380equalsimpl0 = Matrix.m3380equalsimpl0(fArr, fArr2);
        }
        return zM3380equalsimpl0 && Intrinsics.areEqual(this.node, relativeLayoutBounds.node);
    }

    public final float fractionVisibleIn(RelativeLayoutBounds viewport) {
        long j = viewport.topLeft;
        long j2 = viewport.bottomRight;
        return fractionVisibleInRect((int) (j >> 32), (int) j, (int) (j2 >> 32), (int) j2);
    }

    public final float fractionVisibleInRect(int left, int top, int right, int bottom) {
        int i = (int) (this.topLeft >> 32);
        int iMin = Math.min(Math.max(i, left), right);
        int i2 = (int) this.topLeft;
        int iMin2 = Math.min(Math.max(i2, top), bottom);
        int i3 = (int) (this.bottomRight >> 32);
        int iMax = Math.max(Math.min(i3, right), left);
        int i4 = (int) this.bottomRight;
        return Math.max((iMax - iMin) * (Math.max(Math.min(i4, bottom), top) - iMin2), 0) / Math.min((right - left) * (bottom - top), (i3 - i) * (i4 - i2));
    }

    public final float fractionVisibleInWindow() {
        long j = this.windowSize;
        return fractionVisibleInRect(0, 0, (int) (j >> 32), (int) j);
    }

    /* JADX INFO: renamed from: fractionVisibleInWindowWithInsets-E1MhUcY, reason: not valid java name */
    public final float m5278fractionVisibleInWindowWithInsetsE1MhUcY(long topLeftInset, long bottomRightInset) {
        long j = this.windowSize;
        int iM6150getXimpl = IntOffset.m6150getXimpl(this.windowOffset);
        int iM6151getYimpl = IntOffset.m6151getYimpl(this.windowOffset);
        return fractionVisibleInRect(IntOffset.m6150getXimpl(topLeftInset) + iM6150getXimpl, IntOffset.m6151getYimpl(topLeftInset) + iM6151getYimpl, (iM6150getXimpl + ((int) (j >> 32))) - IntOffset.m6150getXimpl(bottomRightInset), (iM6151getYimpl + ((int) j)) - IntOffset.m6151getYimpl(bottomRightInset));
    }

    public final IntRect getBoundsInRoot() {
        long j = this.topLeft;
        long j2 = this.bottomRight;
        return new IntRect((int) (j >> 32), (int) j, (int) (j2 >> 32), (int) j2);
    }

    public final IntRect getBoundsInScreen() {
        if (this.viewToWindowMatrix != null) {
            IntRect boundsInWindow = getBoundsInWindow();
            long j = this.windowOffset;
            return new IntRect(boundsInWindow.getLeft() + IntOffset.m6150getXimpl(j), boundsInWindow.getTop() + IntOffset.m6151getYimpl(j), boundsInWindow.getRight() + IntOffset.m6150getXimpl(j), boundsInWindow.getBottom() + IntOffset.m6151getYimpl(j));
        }
        long j2 = this.topLeft;
        int i = (int) (j2 >> 32);
        long j3 = this.bottomRight;
        int i2 = (int) (j3 >> 32);
        int i3 = (int) j3;
        int iM6150getXimpl = IntOffset.m6150getXimpl(this.screenOffset);
        int iM6151getYimpl = IntOffset.m6151getYimpl(this.screenOffset);
        return new IntRect(i + iM6150getXimpl, ((int) j2) + iM6151getYimpl, i2 + iM6150getXimpl, i3 + iM6151getYimpl);
    }

    public final IntRect getBoundsInWindow() {
        long j = this.topLeft;
        int i = (int) (j >> 32);
        int i2 = (int) j;
        long j2 = this.bottomRight;
        int i3 = (int) (j2 >> 32);
        int i4 = (int) j2;
        float[] fArr = this.viewToWindowMatrix;
        if (fArr != null) {
            return IntRectKt.roundToIntRect(Matrix.m3385mapimpl(fArr, new Rect(i, i2, i3, i4)));
        }
        int iM6150getXimpl = IntOffset.m6150getXimpl(this.screenOffset) - IntOffset.m6150getXimpl(this.windowOffset);
        int iM6151getYimpl = IntOffset.m6151getYimpl(this.screenOffset) - IntOffset.m6151getYimpl(this.windowOffset);
        return new IntRect(i + iM6150getXimpl, i2 + iM6151getYimpl, i3 + iM6150getXimpl, i4 + iM6151getYimpl);
    }

    public final int getHeight() {
        return ((int) this.bottomRight) - ((int) this.topLeft);
    }

    /* JADX INFO: renamed from: getPositionInRoot-nOcc-ac, reason: not valid java name */
    public final long m5279getPositionInRootnOccac() {
        return IntOffset.m6144constructorimpl(this.topLeft);
    }

    /* JADX INFO: renamed from: getPositionInScreen-nOcc-ac, reason: not valid java name */
    public final long m5280getPositionInScreennOccac() {
        int iM6150getXimpl = IntOffset.m6150getXimpl(this.screenOffset);
        int iM6151getYimpl = IntOffset.m6151getYimpl(this.screenOffset);
        long j = this.topLeft;
        return IntOffset.m6144constructorimpl((((long) (((int) (j >> 32)) + iM6150getXimpl)) << 32) | (((long) (((int) j) + iM6151getYimpl)) & 4294967295L));
    }

    /* JADX INFO: renamed from: getPositionInWindow-nOcc-ac, reason: not valid java name */
    public final long m5281getPositionInWindownOccac() {
        int iM6150getXimpl = IntOffset.m6150getXimpl(this.screenOffset) - IntOffset.m6150getXimpl(this.windowOffset);
        int iM6151getYimpl = IntOffset.m6151getYimpl(this.screenOffset) - IntOffset.m6151getYimpl(this.windowOffset);
        long j = this.topLeft;
        return IntOffset.m6144constructorimpl((((long) (((int) (j >> 32)) + iM6150getXimpl)) << 32) | (((long) (((int) j) + iM6151getYimpl)) & 4294967295L));
    }

    public final int getWidth() {
        return ((int) (this.bottomRight >> 32)) - ((int) (this.topLeft >> 32));
    }

    public int hashCode() {
        int iHashCode = ((((((((Long.hashCode(this.topLeft) * 31) + Long.hashCode(this.bottomRight)) * 31) + Long.hashCode(this.windowSize)) * 31) + IntOffset.m6152hashCodeimpl(this.windowOffset)) * 31) + IntOffset.m6152hashCodeimpl(this.screenOffset)) * 31;
        float[] fArr = this.viewToWindowMatrix;
        return ((iHashCode + (fArr != null ? Matrix.m3382hashCodeimpl(fArr) : 0)) * 31) + this.node.hashCode();
    }

    public /* synthetic */ RelativeLayoutBounds(long j, long j2, long j3, long j4, long j5, float[] fArr, DelegatableNode delegatableNode, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, fArr, delegatableNode);
    }
}
