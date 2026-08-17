package androidx.compose.ui.layout;

import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001f\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ&\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0016J\u0017\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "", "dispose", "", "placeablesCount", "", "getPlaceablesCount", "()I", "premeasure", "index", "constraints", "Landroidx/compose/ui/unit/Constraints;", "premeasure-0kLqBqw", "(IJ)V", "traverseDescendants", "key", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "getSize", "Landroidx/compose/ui/unit/IntSize;", "getSize-YEO4UFw", "(I)J", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SubcomposeLayoutState$PrecomposedSlotHandle {
    void dispose();

    default int getPlaceablesCount() {
        return 0;
    }

    /* JADX INFO: renamed from: getSize-YEO4UFw, reason: not valid java name */
    default long m1858getSizeYEO4UFw(int index) {
        return IntSize.Companion.getZero-YbymL2g();
    }

    /* JADX INFO: renamed from: premeasure-0kLqBqw, reason: not valid java name */
    default void m1859premeasure0kLqBqw(int index, long constraints) {
    }

    default void traverseDescendants(Object key, Function1<? super TraversableNode, ? extends TraversableNode.Companion.TraverseDescendantsAction> block) {
    }
}
