package androidx.compose.foundation.contextmenu;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0001\u001a(\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a(\u0010\b\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a \u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a \u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a \u0010\f\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\r"}, d2 = {"alignPopupAxis", "", "position", "popupLength", "windowLength", "closeAffinity", "", "popupFitsBetweenPositionAndStartEdge", "popupFitsBetweenPositionAndEndEdge", "alignPopupStartEdgeToPosition", "alignPopupEndEdgeToPosition", "alignStartEdges", "alignEndEdges", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuPopupPositionProviderKt {
    private static final int alignEndEdges(int i, int i2, boolean z) {
        return alignStartEdges(i, i2, !z);
    }

    public static final int alignPopupAxis(int i, int i2, int i3, boolean z) {
        if (i2 >= i3) {
            return alignStartEdges(i2, i3, z);
        }
        if (popupFitsBetweenPositionAndEndEdge(i, i2, i3, z)) {
            return alignPopupStartEdgeToPosition(i, i2, z);
        }
        return popupFitsBetweenPositionAndStartEdge(i, i2, i3, z) ? alignPopupEndEdgeToPosition(i, i2, z) : alignEndEdges(i2, i3, z);
    }

    public static /* synthetic */ int alignPopupAxis$default(int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            z = true;
        }
        return alignPopupAxis(i, i2, i3, z);
    }

    private static final int alignPopupEndEdgeToPosition(int i, int i2, boolean z) {
        return alignPopupStartEdgeToPosition(i, i2, !z);
    }

    private static final int alignPopupStartEdgeToPosition(int i, int i2, boolean z) {
        return z ? i : i - i2;
    }

    private static final int alignStartEdges(int i, int i2, boolean z) {
        if (z) {
            return 0;
        }
        return i2 - i;
    }

    private static final boolean popupFitsBetweenPositionAndEndEdge(int i, int i2, int i3, boolean z) {
        return popupFitsBetweenPositionAndStartEdge(i, i2, i3, !z);
    }

    private static final boolean popupFitsBetweenPositionAndStartEdge(int i, int i2, int i3, boolean z) {
        if (z) {
            return i2 <= i;
        }
        return i3 - i2 > i;
    }
}
