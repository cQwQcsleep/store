package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\u000f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\u000f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0002\u001a\f\u0010\b\u001a\u00020\u0007*\u00020\u0002H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n\u001a\u000f\u0010\u000b\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\n\u0010\f\u001a\u00020\u0007*\u00020\u0002\u001a\n\u0010\r\u001a\u00020\u0002*\u00020\u0002¨\u0006\u000e"}, d2 = {"positionInRoot", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInWindow", "positionOnScreen", "boundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "boundsInWindow", "clipBounds", "", "positionInParent", "boundsInParent", "findRootCoordinates", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutCoordinatesKt {
    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect rectLocalBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, (int) (layoutCoordinates.mo4613getSizeYbymL2g() >> 32), (int) (layoutCoordinates.mo4613getSizeYbymL2g() & 4294967295L)) : rectLocalBoundingBoxOf$default;
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        return LayoutCoordinates.localBoundingBoxOf$default(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates, boolean z) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = findRootCoordinates(layoutCoordinates);
        float fMo4613getSizeYbymL2g = (int) (layoutCoordinatesFindRootCoordinates.mo4613getSizeYbymL2g() >> 32);
        float fMo4613getSizeYbymL2g2 = (int) (layoutCoordinatesFindRootCoordinates.mo4613getSizeYbymL2g() & 4294967295L);
        Rect rectLocalBoundingBoxOf = layoutCoordinatesFindRootCoordinates.localBoundingBoxOf(layoutCoordinates, z);
        float left = rectLocalBoundingBoxOf.getLeft();
        if (z) {
            if (left < 0.0f) {
                left = 0.0f;
            }
            if (left > fMo4613getSizeYbymL2g) {
                left = fMo4613getSizeYbymL2g;
            }
        }
        float top = rectLocalBoundingBoxOf.getTop();
        if (z) {
            if (top < 0.0f) {
                top = 0.0f;
            }
            if (top > fMo4613getSizeYbymL2g2) {
                top = fMo4613getSizeYbymL2g2;
            }
        }
        if (z) {
            float right = rectLocalBoundingBoxOf.getRight();
            if (right < 0.0f) {
                right = 0.0f;
            }
            if (right <= fMo4613getSizeYbymL2g) {
                fMo4613getSizeYbymL2g = right;
            }
        } else {
            fMo4613getSizeYbymL2g = rectLocalBoundingBoxOf.getRight();
        }
        if (z) {
            float bottom = rectLocalBoundingBoxOf.getBottom();
            float f = bottom >= 0.0f ? bottom : 0.0f;
            if (f <= fMo4613getSizeYbymL2g2) {
                fMo4613getSizeYbymL2g2 = f;
            }
        } else {
            fMo4613getSizeYbymL2g2 = rectLocalBoundingBoxOf.getBottom();
        }
        if (left == fMo4613getSizeYbymL2g || top == fMo4613getSizeYbymL2g2) {
            return Rect.INSTANCE.getZero();
        }
        long jMo4618localToWindowMKHz9U = layoutCoordinatesFindRootCoordinates.mo4618localToWindowMKHz9U(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(left)) << 32) | (((long) Float.floatToRawIntBits(top)) & 4294967295L)));
        long jMo4618localToWindowMKHz9U2 = layoutCoordinatesFindRootCoordinates.mo4618localToWindowMKHz9U(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMo4613getSizeYbymL2g)) << 32) | (((long) Float.floatToRawIntBits(top)) & 4294967295L)));
        long jMo4618localToWindowMKHz9U3 = layoutCoordinatesFindRootCoordinates.mo4618localToWindowMKHz9U(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMo4613getSizeYbymL2g)) << 32) | (((long) Float.floatToRawIntBits(fMo4613getSizeYbymL2g2)) & 4294967295L)));
        long jMo4618localToWindowMKHz9U4 = layoutCoordinatesFindRootCoordinates.mo4618localToWindowMKHz9U(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMo4613getSizeYbymL2g2)) & 4294967295L) | (((long) Float.floatToRawIntBits(left)) << 32)));
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U2 >> 32));
        float fIntBitsToFloat3 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U4 >> 32));
        float fIntBitsToFloat4 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U3 >> 32));
        float fMin = Math.min(fIntBitsToFloat, Math.min(fIntBitsToFloat2, Math.min(fIntBitsToFloat3, fIntBitsToFloat4)));
        float fMax = Math.max(fIntBitsToFloat, Math.max(fIntBitsToFloat2, Math.max(fIntBitsToFloat3, fIntBitsToFloat4)));
        float fIntBitsToFloat5 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U & 4294967295L));
        float fIntBitsToFloat6 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U2 & 4294967295L));
        float fIntBitsToFloat7 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U4 & 4294967295L));
        float fIntBitsToFloat8 = Float.intBitsToFloat((int) (jMo4618localToWindowMKHz9U3 & 4294967295L));
        return new Rect(fMin, Math.min(fIntBitsToFloat5, Math.min(fIntBitsToFloat6, Math.min(fIntBitsToFloat7, fIntBitsToFloat8))), fMax, Math.max(fIntBitsToFloat5, Math.max(fIntBitsToFloat6, Math.max(fIntBitsToFloat7, fIntBitsToFloat8))));
    }

    public static /* synthetic */ Rect boundsInWindow$default(LayoutCoordinates layoutCoordinates, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return boundsInWindow(layoutCoordinates, z);
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy = nodeCoordinator.getWrappedBy();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator == null) {
                return nodeCoordinator3;
            }
            wrappedBy = nodeCoordinator.getWrappedBy();
        }
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo4614localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m2905getZeroF1C5BW0()) : Offset.INSTANCE.m2905getZeroF1C5BW0();
    }

    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4616localToRootMKHz9U(Offset.INSTANCE.m2905getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4618localToWindowMKHz9U(Offset.INSTANCE.m2905getZeroF1C5BW0());
    }

    public static final long positionOnScreen(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4617localToScreenMKHz9U(Offset.INSTANCE.m2905getZeroF1C5BW0());
    }
}
