package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003\u001a&\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u000e\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u0007*\b\u0012\u0004\u0012\u0002H\u00060\b¨\u0006\t"}, d2 = {"isMeaningless", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "getMeaningfulIntervals", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;", "Lorg/jetbrains/kotlin/codegen/inline/IntervalMetaInfo;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoveringTryCatchNodeProcessorKt {
    public static final <T extends SplittableInterval<? extends T>> List<T> getMeaningfulIntervals(IntervalMetaInfo<T> intervalMetaInfo) {
        intervalMetaInfo.getClass();
        ArrayList<T> allIntervals = intervalMetaInfo.getAllIntervals();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allIntervals) {
            if (!isMeaningless((SplittableInterval) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final boolean isMeaningless(Interval interval) {
        interval.getClass();
        LabelNode startLabel = interval.getStartLabel();
        AbstractInsnNode endLabel = interval.getEndLabel();
        while (!Intrinsics.areEqual(endLabel, startLabel) && !UtilKt.isMeaningful(endLabel)) {
            endLabel = endLabel.getPrevious();
            endLabel.getClass();
        }
        return Intrinsics.areEqual(startLabel, endLabel);
    }

    public static final boolean isMeaningless(TryCatchBlockNode tryCatchBlockNode) {
        tryCatchBlockNode.getClass();
        LabelNode labelNode = tryCatchBlockNode.start;
        labelNode.getClass();
        LabelNode labelNode2 = tryCatchBlockNode.end;
        labelNode2.getClass();
        return isMeaningless(new SimpleInterval(labelNode, labelNode2));
    }
}
