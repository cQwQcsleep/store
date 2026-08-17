package androidx.compose.animation.core;

import androidx.compose.ui.graphics.BezierKt;
import androidx.compose.ui.graphics.IntervalTree;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathIterator;
import androidx.compose.ui.graphics.PathSegment;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/animation/core/PathEasing;", "Landroidx/compose/animation/core/Easing;", PathTree.NAME_path, "Landroidx/compose/ui/graphics/Path;", "<init>", "(Landroidx/compose/ui/graphics/Path;)V", "intervals", "Landroidx/compose/ui/graphics/IntervalTree;", "Landroidx/compose/ui/graphics/PathSegment;", "transform", "", "fraction", "initializeEasing", "", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PathEasing implements Easing {
    public static final int $stable = 0;
    private IntervalTree<PathSegment> intervals;
    private final Path path;

    public PathEasing(Path path) {
        this.path = path;
    }

    private final void initializeEasing() {
        float[] fArr = new float[5];
        IntervalTree<PathSegment> intervalTree = new IntervalTree<>();
        PathIterator it = this.path.iterator(PathIterator.ConicEvaluation.AsQuadratics, 2.0E-4f);
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PathSegment next = it.next();
            if (!(next.getType() != PathSegment.Type.Close)) {
                PreconditionsKt.throwIllegalArgumentException("The path cannot contain a close() command.");
            }
            if (next.getType() != PathSegment.Type.Move && next.getType() != PathSegment.Type.Done) {
                long jComputeHorizontalBounds$default = BezierKt.computeHorizontalBounds$default(next, fArr, 0, 4, null);
                intervalTree.addInterval(Float.intBitsToFloat((int) (jComputeHorizontalBounds$default >> 32)), Float.intBitsToFloat((int) (jComputeHorizontalBounds$default & 4294967295L)), next);
            }
        }
        if (!(intervalTree.contains(0.0f) && intervalTree.contains(1.0f))) {
            PreconditionsKt.throwIllegalArgumentException("The easing path must start at 0.0f and end at 1.0f.");
        }
        this.intervals = intervalTree;
    }

    public float transform(float fraction) {
        if (fraction <= 0.0f) {
            return 0.0f;
        }
        if (fraction >= 1.0f) {
            return 1.0f;
        }
        if (this.intervals == null) {
            initializeEasing();
        }
        IntervalTree<PathSegment> intervalTree = this.intervals;
        if (intervalTree == null) {
            Intrinsics.throwUninitializedPropertyAccessException("intervals");
            intervalTree = null;
        }
        Object data = IntervalTree.findFirstOverlap$default(intervalTree, fraction, 0.0f, 2, null).getData();
        if (data == null) {
            PreconditionsKt.throwIllegalStateExceptionForNullCheck("The easing path is invalid. Make sure it is continuous on the x axis.");
            wq6.a();
            return 0.0f;
        }
        PathSegment pathSegment = (PathSegment) data;
        float fFindFirstRoot = BezierKt.findFirstRoot(pathSegment, fraction);
        if (Float.isNaN(fFindFirstRoot)) {
            PreconditionsKt.throwIllegalStateException("The easing path is invalid. Make sure it does not contain NaN/Infinity values.");
        }
        return BezierKt.evaluateY(pathSegment, fFindFirstRoot);
    }
}
