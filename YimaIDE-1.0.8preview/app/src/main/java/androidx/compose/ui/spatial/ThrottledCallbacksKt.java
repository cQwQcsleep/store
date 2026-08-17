package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"rectInfoFor", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "topLeft", "", "bottomRight", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "windowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "rectInfoFor-Dg36KO4", "(Landroidx/compose/ui/node/DelegatableNode;JJJJJ[F)Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ThrottledCallbacksKt {
    /* JADX INFO: renamed from: rectInfoFor-Dg36KO4, reason: not valid java name */
    public static final RelativeLayoutBounds m5292rectInfoForDg36KO4(DelegatableNode delegatableNode, long j, long j2, long j3, long j4, long j5, float[] fArr) {
        NodeCoordinator nodeCoordinatorM4787requireCoordinator64DMado = DelegatableNodeKt.m4787requireCoordinator64DMado(delegatableNode, NodeKind.m4949constructorimpl(2));
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        if (!layoutNodeRequireLayoutNode.isPlaced()) {
            return null;
        }
        if (layoutNodeRequireLayoutNode.getOuterCoordinator$ui() == nodeCoordinatorM4787requireCoordinator64DMado) {
            return new RelativeLayoutBounds(j, j2, j3, j4, j5, fArr, delegatableNode, null);
        }
        long jM6144constructorimpl = IntOffset.m6144constructorimpl(j);
        long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m6150getXimpl(jM6144constructorimpl))) << 32) | (((long) Float.floatToRawIntBits(IntOffset.m6151getYimpl(jM6144constructorimpl))) & 4294967295L));
        long jMo4613getSizeYbymL2g = nodeCoordinatorM4787requireCoordinator64DMado.getCoordinates().mo4613getSizeYbymL2g();
        long jM6167roundk4lQ0M = IntOffsetKt.m6167roundk4lQ0M(layoutNodeRequireLayoutNode.getOuterCoordinator$ui().getCoordinates().mo4614localPositionOfR5De75A(nodeCoordinatorM4787requireCoordinator64DMado, jM2881constructorimpl));
        return new RelativeLayoutBounds(jM6167roundk4lQ0M, IntOffset.m6144constructorimpl((((long) (IntOffset.m6150getXimpl(jM6167roundk4lQ0M) + ((int) (jMo4613getSizeYbymL2g >> 32)))) << 32) | (((long) (IntOffset.m6151getYimpl(jM6167roundk4lQ0M) + ((int) (jMo4613getSizeYbymL2g & 4294967295L)))) & 4294967295L)), j3, j4, j5, fArr, delegatableNode, null);
    }
}
