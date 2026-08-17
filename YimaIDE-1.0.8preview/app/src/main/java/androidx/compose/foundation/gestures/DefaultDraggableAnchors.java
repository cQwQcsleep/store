package androidx.compose.foundation.gestures;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u0012J\u001f\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0016\u001a\u00020\nH\u0016J\u0017\u0010\u001b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001c\u001a\u00020\u0018H\u0016¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0013\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0096\u0002J\b\u0010\"\u001a\u00020\u0018H\u0016J\b\u0010#\u001a\u00020$H\u0016J\f\u0010%\u001a\u00020\n*\u00020\u0006H\u0002J\f\u0010&\u001a\u00020\n*\u00020\u0006H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006'"}, d2 = {"Landroidx/compose/foundation/gestures/DefaultDraggableAnchors;", "T", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "keys", "", "anchors", "", "<init>", "(Ljava/util/List;[F)V", "positionOf", "", "anchor", "(Ljava/lang/Object;)F", "hasPositionFor", "", "(Ljava/lang/Object;)Z", "closestAnchor", "position", "(F)Ljava/lang/Object;", "searchUpwards", "(FZ)Ljava/lang/Object;", "minPosition", "maxPosition", "size", "", "getSize", "()I", "anchorAt", "index", "(I)Ljava/lang/Object;", "positionAt", "equals", "other", "", "hashCode", "toString", "", "minOrNaN", "maxOrNaN", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DefaultDraggableAnchors<T> implements DraggableAnchors<T> {
    private final float[] anchors;
    private final List<T> keys;
    private final int size;

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultDraggableAnchors(List<? extends T> list, float[] fArr) {
        this.keys = list;
        this.anchors = fArr;
        if (!(list.size() == fArr.length)) {
            InlineClassHelperKt.throwIllegalArgumentException("DraggableAnchors were constructed with inconsistent key-value sizes. Keys: " + list + " | Anchors: " + ArraysKt.toList(fArr));
        }
        this.size = fArr.length;
    }

    private final float maxOrNaN(float[] fArr) {
        if (fArr.length == 0) {
            return Float.NaN;
        }
        float fMax = fArr[0];
        int lastIndex = ArraysKt.getLastIndex(fArr);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                fMax = Math.max(fMax, fArr[i]);
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return fMax;
    }

    private final float minOrNaN(float[] fArr) {
        if (fArr.length == 0) {
            return Float.NaN;
        }
        float fMin = fArr[0];
        int lastIndex = ArraysKt.getLastIndex(fArr);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                fMin = Math.min(fMin, fArr[i]);
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return fMin;
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public T anchorAt(int index) {
        return (T) CollectionsKt.getOrNull(this.keys, index);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public T closestAnchor(float position, boolean searchUpwards) {
        float[] fArr = this.anchors;
        int length = fArr.length;
        int i = 0;
        int i2 = -1;
        float f = Float.POSITIVE_INFINITY;
        int i3 = 0;
        while (i < length) {
            float f2 = fArr[i];
            int i4 = i3 + 1;
            float f3 = searchUpwards ? f2 - position : position - f2;
            if (f3 < 0.0f) {
                f3 = Float.POSITIVE_INFINITY;
            }
            if (f3 <= f) {
                i2 = i3;
                f = f3;
            }
            i++;
            i3 = i4;
        }
        if (i2 == -1) {
            return null;
        }
        return this.keys.get(i2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultDraggableAnchors)) {
            return false;
        }
        DefaultDraggableAnchors defaultDraggableAnchors = (DefaultDraggableAnchors) other;
        return Intrinsics.areEqual(this.keys, defaultDraggableAnchors.keys) && Arrays.equals(this.anchors, defaultDraggableAnchors.anchors) && getSize() == defaultDraggableAnchors.getSize();
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public int getSize() {
        return this.size;
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public boolean hasPositionFor(T anchor) {
        return this.keys.indexOf(anchor) != -1;
    }

    public int hashCode() {
        return (((this.keys.hashCode() * 31) + Arrays.hashCode(this.anchors)) * 31) + getSize();
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float maxPosition() {
        return maxOrNaN(this.anchors);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float minPosition() {
        return minOrNaN(this.anchors);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float positionAt(int index) {
        float[] fArr = this.anchors;
        return (index < 0 || index >= fArr.length) ? ((Number) AnchoredDraggableKt.GetOrNan.invoke(Integer.valueOf(index))).floatValue() : fArr[index];
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float positionOf(T anchor) {
        int iIndexOf = this.keys.indexOf(anchor);
        float[] fArr = this.anchors;
        return (iIndexOf < 0 || iIndexOf >= fArr.length) ? ((Number) AnchoredDraggableKt.GetOrNan.invoke(Integer.valueOf(iIndexOf))).floatValue() : fArr[iIndexOf];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DraggableAnchors(anchors={");
        int size = getSize();
        for (int i = 0; i < size; i++) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(anchorAt(i));
            sb2.append('=');
            sb2.append(positionAt(i));
            sb.append(sb2.toString());
            if (i < getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("})");
        return sb.toString();
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public T closestAnchor(float position) {
        float[] fArr = this.anchors;
        int length = fArr.length;
        float f = Float.POSITIVE_INFINITY;
        int i = 0;
        int i2 = -1;
        int i3 = 0;
        while (i < length) {
            int i4 = i3 + 1;
            float fAbs = Math.abs(position - fArr[i]);
            if (fAbs <= f) {
                i2 = i3;
                f = fAbs;
            }
            i++;
            i3 = i4;
        }
        if (i2 == -1) {
            return null;
        }
        return this.keys.get(i2);
    }
}
