package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u0017\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001e\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/graphics/AndroidPathMeasure;", "Landroidx/compose/ui/graphics/PathMeasure;", "internalPathMeasure", "Landroid/graphics/PathMeasure;", "<init>", "(Landroid/graphics/PathMeasure;)V", "length", "", "getLength", "()F", "positionArray", "", "tangentArray", "getSegment", "", "startDistance", "stopDistance", "destination", "Landroidx/compose/ui/graphics/Path;", "startWithMoveTo", "setPath", "", PathTree.NAME_path, "forceClosed", "getPosition", "Landroidx/compose/ui/geometry/Offset;", "distance", "getPosition-tuRUvjQ", "(F)J", "getTangent", "getTangent-tuRUvjQ", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidPathMeasure implements PathMeasure {
    public static final int $stable = 8;
    private final android.graphics.PathMeasure internalPathMeasure;
    private float[] positionArray;
    private float[] tangentArray;

    public AndroidPathMeasure(android.graphics.PathMeasure pathMeasure) {
        this.internalPathMeasure = pathMeasure;
    }

    @Override // androidx.compose.ui.graphics.PathMeasure
    public float getLength() {
        return this.internalPathMeasure.getLength();
    }

    @Override // androidx.compose.ui.graphics.PathMeasure
    /* JADX INFO: renamed from: getPosition-tuRUvjQ, reason: not valid java name */
    public long mo3029getPositiontuRUvjQ(float distance) {
        if (this.positionArray == null) {
            this.positionArray = new float[2];
        }
        if (this.tangentArray == null) {
            this.tangentArray = new float[2];
        }
        if (!this.internalPathMeasure.getPosTan(distance, this.positionArray, this.tangentArray)) {
            return Offset.INSTANCE.m2904getUnspecifiedF1C5BW0();
        }
        float[] fArr = this.positionArray;
        fArr.getClass();
        float f = fArr[0];
        float[] fArr2 = this.positionArray;
        fArr2.getClass();
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fArr2[1])) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
    }

    @Override // androidx.compose.ui.graphics.PathMeasure
    public boolean getSegment(float startDistance, float stopDistance, Path destination, boolean startWithMoveTo) {
        android.graphics.PathMeasure pathMeasure = this.internalPathMeasure;
        if (destination instanceof AndroidPath) {
            return pathMeasure.getSegment(startDistance, stopDistance, ((AndroidPath) destination).getInternalPath(), startWithMoveTo);
        }
        c41.a("Unable to obtain android.graphics.Path");
        return false;
    }

    @Override // androidx.compose.ui.graphics.PathMeasure
    /* JADX INFO: renamed from: getTangent-tuRUvjQ, reason: not valid java name */
    public long mo3030getTangenttuRUvjQ(float distance) {
        if (this.positionArray == null) {
            this.positionArray = new float[2];
        }
        if (this.tangentArray == null) {
            this.tangentArray = new float[2];
        }
        if (!this.internalPathMeasure.getPosTan(distance, this.positionArray, this.tangentArray)) {
            return Offset.INSTANCE.m2904getUnspecifiedF1C5BW0();
        }
        float[] fArr = this.tangentArray;
        fArr.getClass();
        float f = fArr[0];
        float[] fArr2 = this.tangentArray;
        fArr2.getClass();
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fArr2[1])) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
    }

    @Override // androidx.compose.ui.graphics.PathMeasure
    public void setPath(Path path, boolean forceClosed) {
        android.graphics.Path internalPath;
        android.graphics.PathMeasure pathMeasure = this.internalPathMeasure;
        if (path == null) {
            internalPath = null;
        } else {
            if (!(path instanceof AndroidPath)) {
                c41.a("Unable to obtain android.graphics.Path");
                return;
            }
            internalPath = ((AndroidPath) path).getInternalPath();
        }
        pathMeasure.setPath(internalPath, forceClosed);
    }
}
