package androidx.compose.runtime.collection;

import androidx.collection.ScatterSet;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0000\u001a1\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0002*\u00020\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\bH\u0080\b\u001a'\u0010\t\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u00060\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\bH\u0080\b¨\u0006\u000b"}, d2 = {"wrapIntoSet", "", "T", "Landroidx/collection/ScatterSet;", "fastForEach", "", "", "block", "Lkotlin/Function1;", "fastAny", "", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ScatterSetWrapperKt {
    /* JADX WARN: Code duplicated, block: B:18:0x0053 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x0055 A[LOOP:0: B:7:0x0016->B:19:0x0055, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x0058 A[SYNTHETIC] */
    public static final boolean fastAny(Set<? extends Object> set, Function1<Object, Boolean> function1) {
        if (!(set instanceof ScatterSetWrapper)) {
            Set<? extends Object> set2 = set;
            if ((set2 instanceof Collection) && set2.isEmpty()) {
                return false;
            }
            Iterator<T> it = set2.iterator();
            while (it.hasNext()) {
                if (((Boolean) function1.invoke(it.next())).booleanValue()) {
                    return true;
                }
            }
            return false;
        }
        ScatterSet set$runtime = ((ScatterSetWrapper) set).getSet$runtime();
        Object[] objArr = set$runtime.elements;
        long[] jArr = set$runtime.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128 && ((Boolean) function1.invoke(objArr[(i << 3) + i3])).booleanValue()) {
                            return true;
                        }
                        j >>= 8;
                    }
                    if (i2 == 8) {
                        if (i != length) {
                            i++;
                        }
                    }
                } else if (i != length) {
                    i++;
                }
            }
        }
        return false;
    }

    public static final <T> void fastForEach(Set<? extends T> set, Function1<? super T, Unit> function1) {
        if (!(set instanceof ScatterSetWrapper)) {
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                function1.invoke(it.next());
            }
            return;
        }
        ScatterSet<T> set$runtime = ((ScatterSetWrapper) set).getSet$runtime();
        Object[] objArr = set$runtime.elements;
        long[] jArr = set$runtime.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        function1.invoke(objArr[(i << 3) + i3]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final <T> Set<T> wrapIntoSet(ScatterSet<T> scatterSet) {
        return new ScatterSetWrapper(scatterSet);
    }
}
