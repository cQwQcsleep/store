package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/lazy/layout/NearestRangeKeyIndexMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "nearestRange", "Lkotlin/ranges/IntRange;", "intervalContent", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "<init>", "(Lkotlin/ranges/IntRange;Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;)V", "map", "Landroidx/collection/ObjectIntMap;", "", "keys", "", "[Ljava/lang/Object;", "keysStartIndex", "", "getIndex", "key", "getKey", "index", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NearestRangeKeyIndexMap implements LazyLayoutKeyIndexMap {
    public static final int $stable = 8;
    private final Object[] keys;
    private final int keysStartIndex;
    private final ObjectIntMap<Object> map;

    public NearestRangeKeyIndexMap(IntRange intRange, LazyLayoutIntervalContent<?> lazyLayoutIntervalContent) {
        IntervalList<Interval> intervals = lazyLayoutIntervalContent.getIntervals();
        final int first = intRange.getFirst();
        if (!(first >= 0)) {
            InlineClassHelperKt.throwIllegalStateException("negative nearestRange.first");
        }
        final int iMin = Math.min(intRange.getLast(), intervals.getSize() - 1);
        if (iMin < first) {
            this.map = ObjectIntMapKt.emptyObjectIntMap();
            this.keys = new Object[0];
            this.keysStartIndex = 0;
        } else {
            int i = (iMin - first) + 1;
            this.keys = new Object[i];
            this.keysStartIndex = first;
            final MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(i);
            intervals.forEach(first, iMin, new Function1() { // from class: sga
                public final Object invoke(Object obj) {
                    return NearestRangeKeyIndexMap.a(first, iMin, mutableObjectIntMap, this, (IntervalList.Interval) obj);
                }
            });
            this.map = mutableObjectIntMap;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0035  */
    public static Unit a(int i, int i2, MutableObjectIntMap mutableObjectIntMap, NearestRangeKeyIndexMap nearestRangeKeyIndexMap, IntervalList.Interval interval) {
        Object defaultLazyLayoutKey;
        Function1<Integer, Object> key = ((LazyLayoutIntervalContent.Interval) interval.getValue()).getKey();
        int iMax = Math.max(i, interval.getStartIndex());
        int iMin = Math.min(i2, (interval.getStartIndex() + interval.getSize()) - 1);
        if (iMax <= iMin) {
            while (true) {
                if (key == null) {
                    defaultLazyLayoutKey = Lazy_androidKt.getDefaultLazyLayoutKey(iMax);
                } else {
                    defaultLazyLayoutKey = key.invoke(Integer.valueOf(iMax - interval.getStartIndex()));
                    if (defaultLazyLayoutKey == null) {
                        defaultLazyLayoutKey = Lazy_androidKt.getDefaultLazyLayoutKey(iMax);
                    }
                }
                mutableObjectIntMap.set(defaultLazyLayoutKey, iMax);
                nearestRangeKeyIndexMap.keys[iMax - nearestRangeKeyIndexMap.keysStartIndex] = defaultLazyLayoutKey;
                if (iMax == iMin) {
                    break;
                }
                iMax++;
            }
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public int getIndex(Object key) {
        ObjectIntMap<Object> objectIntMap = this.map;
        int iFindKeyIndex = objectIntMap.findKeyIndex(key);
        if (iFindKeyIndex >= 0) {
            return objectIntMap.values[iFindKeyIndex];
        }
        return -1;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public Object getKey(int index) {
        Object[] objArr = this.keys;
        int i = index - this.keysStartIndex;
        if (i < 0 || i >= objArr.length) {
            return null;
        }
        return objArr[i];
    }
}
