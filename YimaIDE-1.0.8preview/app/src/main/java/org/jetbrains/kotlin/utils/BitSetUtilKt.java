package org.jetbrains.kotlin.utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a$\u0010\u0002\u001a\u00020\u0003*\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005H\u0086\bø\u0001\u0000\u001a0\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t*\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\t0\u0005H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"copy", "Ljava/util/BitSet;", "forEachBit", "", "block", "Lkotlin/Function1;", "", "mapEachBit", "", "R", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class BitSetUtilKt {
    public static final BitSet copy(BitSet bitSet) {
        bitSet.getClass();
        BitSet bitSet2 = new BitSet(bitSet.size());
        bitSet2.or(bitSet);
        return bitSet2;
    }

    public static final void forEachBit(BitSet bitSet, Function1<? super Integer, Unit> function1) {
        bitSet.getClass();
        function1.getClass();
        int iNextSetBit = -1;
        while (true) {
            iNextSetBit = bitSet.nextSetBit(iNextSetBit + 1);
            if (iNextSetBit < 0) {
                return;
            } else {
                function1.invoke(Integer.valueOf(iNextSetBit));
            }
        }
    }

    public static final <R> List<R> mapEachBit(BitSet bitSet, Function1<? super Integer, ? extends R> function1) {
        bitSet.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        int iNextSetBit = -1;
        while (true) {
            iNextSetBit = bitSet.nextSetBit(iNextSetBit + 1);
            if (iNextSetBit < 0) {
                return arrayList;
            }
            arrayList.add(function1.invoke(Integer.valueOf(iNextSetBit)));
        }
    }
}
