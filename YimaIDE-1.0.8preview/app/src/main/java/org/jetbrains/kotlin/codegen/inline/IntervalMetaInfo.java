package org.jetbrains.kotlin.codegen.inline;

import com.google.common.collect.LinkedListMultimap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.SplittableInterval;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u0018\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nJ\u0013\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00028\u0000¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\n2\u0006\u0010)\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010*J\"\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000.0 2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201J\u0016\u00102\u001a\u00020$2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201J\u0016\u00103\u001a\u00020$2\u0006\u00104\u001a\u00020\n2\u0006\u00105\u001a\u000201J)\u00106\u001a\b\u0012\u0004\u0012\u00028\u00000.2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201¢\u0006\u0002\u00107J)\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000.2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201¢\u0006\u0002\u00107J<\u00109\u001a&\u0012\f\u0012\n \u000b*\u0004\u0018\u00018\u00008\u0000 \u000b*\u0012\u0012\f\u0012\n \u000b*\u0004\u0018\u00018\u00008\u0000\u0018\u00010 0:2\u0006\u00104\u001a\u00020\n2\u0006\u0010;\u001a\u000201H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000RQ\u0010\b\u001aB\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0012\f\u0012\n \u000b*\u0004\u0018\u00018\u00008\u0000 \u000b* \u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0012\f\u0012\n \u000b*\u0004\u0018\u00018\u00008\u0000\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rRQ\u0010\u000e\u001aB\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0012\f\u0012\n \u000b*\u0004\u0018\u00018\u00008\u0000 \u000b* \u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0012\f\u0012\n \u000b*\u0004\u0018\u00018\u00008\u0000\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/IntervalMetaInfo;", "T", "Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;", Argument.Delimiters.none, "processor", "Lorg/jetbrains/kotlin/codegen/inline/CoveringTryCatchNodeProcessor;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/CoveringTryCatchNodeProcessor;)V", "intervalStarts", "Lcom/google/common/collect/LinkedListMultimap;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "kotlin.jvm.PlatformType", "getIntervalStarts", "()Lcom/google/common/collect/LinkedListMultimap;", "intervalEnds", "getIntervalEnds", "allIntervals", "Ljava/util/ArrayList;", "getAllIntervals", "()Ljava/util/ArrayList;", "currentIntervals", Argument.Delimiters.none, "getCurrentIntervals", "()Ljava/util/Set;", "intersection", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "interval", "other", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "(Lorg/jetbrains/org/objectweb/asm/tree/InsnList;Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;Lorg/jetbrains/kotlin/codegen/inline/Interval;)Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;", "copyIntervalsForRange", Argument.Delimiters.none, "startLabel", "endLabel", "addNewInterval", Argument.Delimiters.none, "newInfo", "(Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;)V", "remapStartLabel", "oldStart", "remapped", "(Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;)V", "remapEndLabel", "oldEnd", "splitCurrentIntervals", "Lorg/jetbrains/kotlin/codegen/inline/SplitPair;", "by", "keepStart", Argument.Delimiters.none, "splitAndRemoveCurrentIntervals", "processCurrent", "curIns", "directOrder", "split", "(Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;Lorg/jetbrains/kotlin/codegen/inline/Interval;Z)Lorg/jetbrains/kotlin/codegen/inline/SplitPair;", "splitAndRemoveIntervalFromCurrents", "getInterval", Argument.Delimiters.none, "isOpen", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IntervalMetaInfo<T extends SplittableInterval<? extends T>> {
    private final ArrayList<T> allIntervals;
    private final Set<T> currentIntervals;
    private final LinkedListMultimap<LabelNode, T> intervalEnds;
    private final LinkedListMultimap<LabelNode, T> intervalStarts;
    private final CoveringTryCatchNodeProcessor processor;

    public IntervalMetaInfo(CoveringTryCatchNodeProcessor coveringTryCatchNodeProcessor) {
        coveringTryCatchNodeProcessor.getClass();
        this.processor = coveringTryCatchNodeProcessor;
        this.intervalStarts = LinkedListMultimap.create();
        this.intervalEnds = LinkedListMultimap.create();
        this.allIntervals = new ArrayList<>();
        this.currentIntervals = new LinkedHashSet();
    }

    private final List<T> getInterval(LabelNode curIns, boolean isOpen) {
        return (isOpen ? this.intervalStarts : this.intervalEnds).get(curIns);
    }

    private final void remapEndLabel(LabelNode oldEnd, T remapped) {
        remapped.verify(this.processor);
        this.intervalEnds.remove(oldEnd, remapped);
        this.intervalEnds.put(remapped.getEndLabel(), remapped);
    }

    private final void remapStartLabel(LabelNode oldStart, T remapped) {
        remapped.verify(this.processor);
        this.intervalStarts.remove(oldStart, remapped);
        this.intervalStarts.put(remapped.getStartLabel(), remapped);
    }

    public final void addNewInterval(T newInfo) {
        newInfo.getClass();
        newInfo.verify(this.processor);
        this.intervalStarts.put(newInfo.getStartLabel(), newInfo);
        this.intervalEnds.put(newInfo.getEndLabel(), newInfo);
        this.allIntervals.add(newInfo);
    }

    public final List<T> copyIntervalsForRange(InsnList instructions, LabelNode startLabel, LabelNode endLabel) {
        instructions.getClass();
        startLabel.getClass();
        endLabel.getClass();
        SimpleInterval simpleInterval = new SimpleInterval(startLabel, endLabel);
        ArrayList<T> arrayList = this.allIntervals;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            SplittableInterval splittableIntervalIntersection = intersection(instructions, (SplittableInterval) it.next(), simpleInterval);
            if (splittableIntervalIntersection != null) {
                arrayList2.add(splittableIntervalIntersection);
            }
        }
        return arrayList2;
    }

    public final ArrayList<T> getAllIntervals() {
        return this.allIntervals;
    }

    public final Set<T> getCurrentIntervals() {
        return this.currentIntervals;
    }

    public final LinkedListMultimap<LabelNode, T> getIntervalEnds() {
        return this.intervalEnds;
    }

    public final LinkedListMultimap<LabelNode, T> getIntervalStarts() {
        return this.intervalStarts;
    }

    public final T intersection(InsnList instructions, T interval, Interval other) {
        instructions.getClass();
        interval.getClass();
        other.getClass();
        int iIndexOf = instructions.indexOf(interval.getStartLabel());
        int iIndexOf2 = instructions.indexOf(interval.getEndLabel());
        int iIndexOf3 = instructions.indexOf(other.getStartLabel());
        int iIndexOf4 = instructions.indexOf(other.getEndLabel());
        int iMax = Math.max(iIndexOf, iIndexOf3);
        int iMin = Math.min(iIndexOf2, iIndexOf4);
        if (iMax >= iMin) {
            return null;
        }
        return (T) interval.copyWithNewBounds(new SimpleInterval(iIndexOf == iMax ? interval.getStartLabel() : other.getStartLabel(), iIndexOf2 == iMin ? interval.getEndLabel() : other.getEndLabel()));
    }

    public final void processCurrent(LabelNode curIns, boolean directOrder) {
        curIns.getClass();
        List<T> interval = getInterval(curIns, directOrder);
        interval.getClass();
        for (T t : interval) {
            Set<T> set = this.currentIntervals;
            t.getClass();
            set.add((T) t);
        }
        List<T> interval2 = getInterval(curIns, !directOrder);
        interval2.getClass();
        Iterator<T> it = interval2.iterator();
        while (it.hasNext()) {
            this.currentIntervals.remove((SplittableInterval) it.next());
        }
    }

    public final SplitPair<T> split(T interval, Interval by, boolean keepStart) {
        interval.getClass();
        by.getClass();
        SplitPair<T> splitPairSplit = interval.split(by, keepStart);
        if (keepStart) {
            remapEndLabel(splitPairSplit.getNewPart().getEndLabel(), (SplittableInterval) splitPairSplit.getPatchedPart());
        } else {
            remapStartLabel(splitPairSplit.getNewPart().getStartLabel(), (SplittableInterval) splitPairSplit.getPatchedPart());
        }
        addNewInterval((SplittableInterval) splitPairSplit.getNewPart());
        return splitPairSplit;
    }

    public final void splitAndRemoveCurrentIntervals(Interval by, boolean keepStart) {
        by.getClass();
        Iterator it = CollectionsKt.toList(this.currentIntervals).iterator();
        while (it.hasNext()) {
            splitAndRemoveIntervalFromCurrents((SplittableInterval) it.next(), by, keepStart);
        }
    }

    public final SplitPair<T> splitAndRemoveIntervalFromCurrents(T interval, Interval by, boolean keepStart) {
        interval.getClass();
        by.getClass();
        SplitPair<T> splitPairSplit = split(interval, by, keepStart);
        this.currentIntervals.remove(splitPairSplit.getPatchedPart());
        return splitPairSplit;
    }

    public final List<SplitPair<T>> splitCurrentIntervals(Interval by, boolean keepStart) {
        by.getClass();
        Set<T> set = this.currentIntervals;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(split((SplittableInterval) it.next(), by, keepStart));
        }
        return arrayList;
    }
}
