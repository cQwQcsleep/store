package androidx.compose.animation;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a7\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\f\u0010\r\u001a\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002\u001a\u0019\u0010\u0016\u001a\u00020\t*\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000fH\u0000¢\u0006\u0002\u0010\u0018\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0013\u001a\u00020\u000f*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"SharedTransitionDebug", "", "updateTargetData", "", "targetData", "Landroidx/compose/animation/TargetData;", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "structuralOffset", "targetBoundsProviderChanged", "updateTargetData-BGTQxF0", "(Landroidx/compose/animation/TargetData;JJJZ)V", "obtainBoundsFromLastTarget", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/animation/SharedElement;", "lastTargetBoundsProvider", "Landroidx/compose/animation/BoundsProvider;", "targetBounds", "getTargetBounds", "(Landroidx/compose/animation/TargetData;)Landroidx/compose/ui/geometry/Rect;", "calculateOffsetFromDirectManipulation", "animatedBounds", "(Landroidx/compose/animation/TargetData;Landroidx/compose/ui/geometry/Rect;)J", "animation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SharedTransitionStateMachineKt {
    public static final boolean SharedTransitionDebug = false;

    public static final long calculateOffsetFromDirectManipulation(TargetData targetData, Rect rect) {
        return Offset.plus-MK-Hz9U(Offset.minus-MK-Hz9U(rect.getTopLeft-F1C5BW0(), targetData.m182getInitialMfrOffsetF1C5BW0()), targetData.m181getCurrentMfrOffsetF1C5BW0());
    }

    public static final Rect getTargetBounds(TargetData targetData) {
        return RectKt.Rect-tz77jQw(Offset.plus-MK-Hz9U(targetData.m182getInitialMfrOffsetF1C5BW0(), targetData.m184getTargetStructuralOffsetF1C5BW0()), targetData.m183getSizeNHjbRc());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect obtainBoundsFromLastTarget(SharedElement sharedElement, BoundsProvider boundsProvider) {
        if (boundsProvider == null) {
            return null;
        }
        List<SharedElementEntry> allEntries = sharedElement.getAllEntries();
        int size = allEntries.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(allEntries.get(i).getBoundsProvider(), boundsProvider)) {
                return boundsProvider.getLastBoundsInSharedTransitionScope();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateTargetData-BGTQxF0, reason: not valid java name */
    public static final void m163updateTargetDataBGTQxF0(TargetData targetData, long j, long j2, long j3, boolean z) {
        if (!Offset.equals-impl0(targetData.m184getTargetStructuralOffsetF1C5BW0(), j3) || !Size.equals-impl0(targetData.m183getSizeNHjbRc(), j) || z) {
            targetData.m187setSizeuvyYCjk(j);
            targetData.m188setTargetStructuralOffsetk4lQ0M(j3);
            if (z) {
                targetData.m186setInitialMfrOffsetk4lQ0M(Offset.minus-MK-Hz9U(Offset.minus-MK-Hz9U(j2, j3), Offset.minus-MK-Hz9U(targetData.m181getCurrentMfrOffsetF1C5BW0(), targetData.m182getInitialMfrOffsetF1C5BW0())));
            }
        }
        targetData.m185setCurrentMfrOffsetk4lQ0M(Offset.minus-MK-Hz9U(j2, j3));
    }
}
