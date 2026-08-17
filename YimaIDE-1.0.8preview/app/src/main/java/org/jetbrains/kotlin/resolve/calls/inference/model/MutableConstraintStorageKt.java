package org.jetbrains.kotlin.resolve.calls.inference.model;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u001a.\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006*\f\b\u0002\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\b"}, d2 = {"Context", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemMarker;", "identityHashSetFromSum", "", "T", "first", "", "second", "org.jetbrains.kotlin:resolution.common"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class MutableConstraintStorageKt {
    public static final <T> Set<T> identityHashSetFromSum(List<? extends T> list, List<? extends T> list2) {
        list.getClass();
        list2.getClass();
        IdentityHashMap identityHashMap = new IdentityHashMap();
        Iterator<? extends T> it = list.iterator();
        while (it.hasNext()) {
            identityHashMap.put(it.next(), Boolean.TRUE);
        }
        Iterator<? extends T> it2 = list2.iterator();
        while (it2.hasNext()) {
            identityHashMap.put(it2.next(), Boolean.TRUE);
        }
        Set<T> setKeySet = identityHashMap.keySet();
        setKeySet.getClass();
        return setKeySet;
    }
}
