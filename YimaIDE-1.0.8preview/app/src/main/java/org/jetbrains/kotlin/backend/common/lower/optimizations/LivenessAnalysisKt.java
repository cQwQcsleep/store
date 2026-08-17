package org.jetbrains.kotlin.backend.common.lower.optimizations;

import java.util.BitSet;
import kotlin.Metadata;
import org.jetbrains.kotlin.utils.BitSetUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0005"}, d2 = {"withBit", "Ljava/util/BitSet;", "bit", "", "withOutBit", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class LivenessAnalysisKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final BitSet withBit(BitSet bitSet, int i) {
        if (bitSet.get(i)) {
            return bitSet;
        }
        BitSet bitSetCopy = BitSetUtilKt.copy(bitSet);
        bitSetCopy.set(i);
        return bitSetCopy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BitSet withOutBit(BitSet bitSet, int i) {
        if (!bitSet.get(i)) {
            return bitSet;
        }
        BitSet bitSetCopy = BitSetUtilKt.copy(bitSet);
        bitSetCopy.clear(i);
        return bitSetCopy;
    }
}
