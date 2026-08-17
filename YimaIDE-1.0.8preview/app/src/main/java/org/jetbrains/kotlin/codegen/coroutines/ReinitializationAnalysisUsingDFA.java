package org.jetbrains.kotlin.codegen.coroutines;

import defpackage.bhc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.BitSetUtilKt;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00030\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA;", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysis;", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "containingClassName", Argument.Delimiters.none, "variablesToSpillBySuspensionPointIndex", "Lorg/jetbrains/kotlin/codegen/coroutines/SpillableVariable;", "<init>", "(Ljava/util/List;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Ljava/lang/String;Ljava/util/List;)V", "calculate", Argument.Delimiters.none, Argument.Delimiters.none, "()[Ljava/util/List;", "ResumeDependentValue", "SuspensionPointsContext", "ResumeDependentFrame", "ResumeDependentInterpeter", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ReinitializationAnalysisUsingDFA extends ReinitializationAnalysis {

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J$\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00012\u0010\u0010\u0013\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0001H\u0016J,\u0010\u0014\u001a\u00020\u00152\u0010\u0010\u0013\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u00012\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0017H\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0017H\u0016J\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002J \u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000f¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentFrame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue;", "context", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;", "maxLocals", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;I)V", "getContext", "()Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;", "getMaxLocals", "()I", "isAfterSuspensionPoint", "Ljava/util/BitSet;", "()Ljava/util/BitSet;", "toString", Argument.Delimiters.none, "init", "frame", "merge", Argument.Delimiters.none, "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "execute", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "unused", "setAllLocalsState", "spIndex", "state", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue$VariableState;", "setLocalState", "varIndex", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ResumeDependentFrame extends Frame<ResumeDependentValue> {
        private final SuspensionPointsContext context;
        private final BitSet isAfterSuspensionPoint;
        private final int maxLocals;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ResumeDependentFrame(SuspensionPointsContext suspensionPointsContext, int i) {
            super(i, 0);
            suspensionPointsContext.getClass();
            this.context = suspensionPointsContext;
            this.maxLocals = i;
            this.isAfterSuspensionPoint = new BitSet(suspensionPointsContext.getSuspensionPointsCount());
            for (int i2 = 0; i2 < i; i2++) {
                setLocal(i2, new ResumeDependentValue(this.context.getSuspensionPointsCount()));
            }
        }

        private final void setAllLocalsState(int spIndex, ResumeDependentValue.VariableState state) {
            IntIterator it = RangesKt.until(0, this.maxLocals).iterator();
            while (it.hasNext()) {
                setLocalState(it.nextInt(), spIndex, state);
            }
        }

        private final void setLocalState(int varIndex, int spIndex, ResumeDependentValue.VariableState state) {
            setLocal(varIndex, ((ResumeDependentValue) getLocal(varIndex)).withState(spIndex, state));
        }

        public void execute(AbstractInsnNode insn, Interpreter<ResumeDependentValue> unused) {
            Integer num;
            insn.getClass();
            if (InlineCodegenUtilsKt.isAfterSuspendMarker(insn) && (num = this.context.getSuspensionPointIndexByEnd().get(insn)) != null) {
                int iIntValue = num.intValue();
                this.isAfterSuspensionPoint.set(iIntValue);
                setAllLocalsState(iIntValue, ResumeDependentValue.VariableState.UNINITIALIZED);
            }
            int opcode = insn.getOpcode();
            int iNextSetBit = -1;
            if (54 <= opcode && opcode < 59) {
                VarInsnNode varInsnNode = (VarInsnNode) insn;
                BitSet bitSet = this.isAfterSuspensionPoint;
                while (true) {
                    iNextSetBit = bitSet.nextSetBit(iNextSetBit + 1);
                    if (iNextSetBit < 0) {
                        return;
                    } else {
                        setLocalState(varInsnNode.var, iNextSetBit, ResumeDependentValue.VariableState.INITIALIZED);
                    }
                }
            } else {
                if (opcode != 132) {
                    return;
                }
                IincInsnNode iincInsnNode = (IincInsnNode) insn;
                BitSet bitSet2 = this.isAfterSuspensionPoint;
                while (true) {
                    iNextSetBit = bitSet2.nextSetBit(iNextSetBit + 1);
                    if (iNextSetBit < 0) {
                        return;
                    } else {
                        setLocalState(iincInsnNode.var, iNextSetBit, ResumeDependentValue.VariableState.INITIALIZED);
                    }
                }
            }
        }

        public final SuspensionPointsContext getContext() {
            return this.context;
        }

        public final int getMaxLocals() {
            return this.maxLocals;
        }

        public Frame<ResumeDependentValue> init(Frame<? extends ResumeDependentValue> frame) {
            frame.getClass();
            ResumeDependentFrame resumeDependentFrame = frame instanceof ResumeDependentFrame ? (ResumeDependentFrame) frame : null;
            if (resumeDependentFrame == null) {
                return super.init(frame);
            }
            this.isAfterSuspensionPoint.clear();
            this.isAfterSuspensionPoint.or(resumeDependentFrame.isAfterSuspensionPoint);
            return super.init(frame);
        }

        /* JADX INFO: renamed from: isAfterSuspensionPoint, reason: from getter */
        public final BitSet getIsAfterSuspensionPoint() {
            return this.isAfterSuspensionPoint;
        }

        /* JADX WARN: Code duplicated, block: B:16:0x003c  */
        public boolean merge(Frame<? extends ResumeDependentValue> frame, Interpreter<ResumeDependentValue> interpreter) {
            boolean z;
            frame.getClass();
            ResumeDependentFrame resumeDependentFrame = frame instanceof ResumeDependentFrame ? (ResumeDependentFrame) frame : null;
            if (resumeDependentFrame == null) {
                return super.merge(frame, interpreter);
            }
            if (this.maxLocals != resumeDependentFrame.maxLocals) {
                k2d.a("Check failed.");
                return false;
            }
            if (Intrinsics.areEqual(this.isAfterSuspensionPoint, resumeDependentFrame.isAfterSuspensionPoint)) {
                z = false;
            } else {
                BitSet bitSetCopy = BitSetUtilKt.copy(this.isAfterSuspensionPoint);
                this.isAfterSuspensionPoint.or(resumeDependentFrame.isAfterSuspensionPoint);
                if (Intrinsics.areEqual(this.isAfterSuspensionPoint, bitSetCopy)) {
                    z = false;
                } else {
                    z = true;
                }
            }
            return super.merge(frame, interpreter) || z;
        }

        public String toString() {
            return "After " + this.isAfterSuspensionPoint + ", " + super.toString();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;", Argument.Delimiters.none, "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "<init>", "(Ljava/util/List;)V", "suspensionPointIndexByEnd", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", Argument.Delimiters.none, "getSuspensionPointIndexByEnd", "()Ljava/util/Map;", "suspensionPointsCount", "getSuspensionPointsCount", "()I", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SuspensionPointsContext {
        private final Map<AbstractInsnNode, Integer> suspensionPointIndexByEnd;
        private final int suspensionPointsCount;

        public SuspensionPointsContext(List<SuspensionPoint> list) {
            list.getClass();
            if (list.isEmpty()) {
                k2d.a("Check failed.");
                throw null;
            }
            List<SuspensionPoint> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    if (!InlineCodegenUtilsKt.isAfterSuspendMarker(((SuspensionPoint) it.next()).getSuspensionCallEnd())) {
                        k2d.a("Check failed.");
                        throw null;
                    }
                }
            }
            Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(list2);
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
            for (IndexedValue indexedValue : iterableWithIndex) {
                Pair pair = TuplesKt.to(((SuspensionPoint) indexedValue.component2()).getSuspensionCallEnd(), Integer.valueOf(indexedValue.getIndex()));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            this.suspensionPointIndexByEnd = linkedHashMap;
            this.suspensionPointsCount = list.size();
        }

        public final Map<AbstractInsnNode, Integer> getSuspensionPointIndexByEnd() {
            return this.suspensionPointIndexByEnd;
        }

        public final int getSuspensionPointsCount() {
            return this.suspensionPointsCount;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReinitializationAnalysisUsingDFA(List<SuspensionPoint> list, MethodNode methodNode, String str, List<? extends List<SpillableVariable>> list2) {
        super(list, methodNode, str, list2);
        list.getClass();
        methodNode.getClass();
        str.getClass();
        list2.getClass();
    }

    public static Frame a(SuspensionPointsContext suspensionPointsContext, int i, int i2) {
        return new ResumeDependentFrame(suspensionPointsContext, i);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.coroutines.ReinitializationAnalysis
    public List<SpillableVariable>[] calculate() throws AnalyzerException {
        final SuspensionPointsContext suspensionPointsContext = new SuspensionPointsContext(getSuspensionPoints());
        Frame[] frameArrAnalyze = new FastMethodAnalyzer(getContainingClassName(), getMethodNode(), new ResumeDependentInterpeter(suspensionPointsContext), false, new Function2() { // from class: org.jetbrains.kotlin.codegen.coroutines.a
            public final Object invoke(Object obj, Object obj2) {
                return ReinitializationAnalysisUsingDFA.a(suspensionPointsContext, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        }).analyze();
        int size = getSuspensionPoints().size();
        List<SpillableVariable>[] listArr = new List[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            listArr[i2] = new ArrayList();
        }
        for (SuspensionPoint suspensionPoint : getSuspensionPoints()) {
            int i3 = i + 1;
            Frame frame = frameArrAnalyze[getMethodNode().instructions.indexOf(suspensionPoint.getSuspensionCallEnd())];
            if (frame == null) {
                StringBuilder sb = new StringBuilder("Missing 'after resume' analysis data for ");
                sb.append(suspensionPoint.getSuspensionCallEnd());
                bhc.a(sb, " at ", getContainingClassName(), "::", getMethodNode().name);
                return null;
            }
            for (SpillableVariable spillableVariable : getVariablesToSpillBySuspensionPointIndex().get(i)) {
                ResumeDependentValue resumeDependentValue = (ResumeDependentValue) frame.getLocal(spillableVariable.getSlot());
                if (resumeDependentValue == null) {
                    l6d.a("Missing 'after resume' analysis data for slot ", spillableVariable.getSlot());
                    return null;
                }
                Iterable iterableWithIndex = ArraysKt.withIndex(resumeDependentValue.getStates());
                ArrayList arrayList = new ArrayList();
                for (Object obj : iterableWithIndex) {
                    IndexedValue indexedValue = (IndexedValue) obj;
                    if (((ResumeDependentValue.VariableState) indexedValue.getValue()).isUnitialized() && indexedValue.getIndex() != i) {
                        arrayList.add(obj);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    int index = ((IndexedValue) it.next()).getIndex();
                    List<SpillableVariable> list = getVariablesToSpillBySuspensionPointIndex().get(index);
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it2 = list.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (((SpillableVariable) it2.next()).getSlot() != spillableVariable.getSlot()) {
                                }
                            }
                        }
                    }
                    List<SpillableVariable> list2 = listArr[index];
                    List<SpillableVariable> list3 = list2;
                    if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                        Iterator<T> it3 = list3.iterator();
                        do {
                            if (it3.hasNext()) {
                            }
                        } while (((SpillableVariable) it3.next()).getSlot() != spillableVariable.getSlot());
                    }
                    list2.add(spillableVariable);
                }
            }
            i = i3;
        }
        return listArr;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\t\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J(\u0010\u0015\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016J2\u0010\u0016\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016J&\u0010\u0018\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0010\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentInterpeter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue;", "context", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;", "<init>", "(Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;)V", "getContext", "()Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$SuspensionPointsContext;", "newValue", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "merge", "value1", "value2", "newOperation", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "copyOperation", "value", "unaryOperation", "binaryOperation", "ternaryOperation", "value3", "naryOperation", "values", Argument.Delimiters.none, "returnOperation", Argument.Delimiters.none, "expected", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ResumeDependentInterpeter extends Interpreter<ResumeDependentValue> {
        private final SuspensionPointsContext context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ResumeDependentInterpeter(SuspensionPointsContext suspensionPointsContext) {
            super(589824);
            suspensionPointsContext.getClass();
            this.context = suspensionPointsContext;
        }

        public final SuspensionPointsContext getContext() {
            return this.context;
        }

        public ResumeDependentValue merge(ResumeDependentValue value1, ResumeDependentValue value2) {
            value1.getClass();
            value2.getClass();
            if (Intrinsics.areEqual(value1, value2)) {
                return value1;
            }
            IntRange intRangeUntil = RangesKt.until(0, this.context.getSuspensionPointsCount());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
            IntIterator it = intRangeUntil.iterator();
            while (it.hasNext()) {
                int iNextInt = it.nextInt();
                arrayList.add(value1.getStates()[iNextInt].merge(value2.getStates()[iNextInt]));
            }
            return new ResumeDependentValue((ResumeDependentValue.VariableState[]) arrayList.toArray(new ResumeDependentValue.VariableState[0]));
        }

        /* JADX INFO: renamed from: naryOperation, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Value m54naryOperation(AbstractInsnNode abstractInsnNode, List list) {
            return naryOperation(abstractInsnNode, (List<ResumeDependentValue>) list);
        }

        /* JADX INFO: renamed from: newValue, reason: merged with bridge method [inline-methods] */
        public ResumeDependentValue m56newValue(Type type) {
            return new ResumeDependentValue(this.context.getSuspensionPointsCount());
        }

        public ResumeDependentValue naryOperation(AbstractInsnNode insn, List<ResumeDependentValue> values) {
            return null;
        }

        /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
        public ResumeDependentValue m55newOperation(AbstractInsnNode insn) {
            return null;
        }

        public ResumeDependentValue copyOperation(AbstractInsnNode insn, ResumeDependentValue value) {
            return null;
        }

        public ResumeDependentValue unaryOperation(AbstractInsnNode insn, ResumeDependentValue value) {
            return null;
        }

        public void returnOperation(AbstractInsnNode insn, ResumeDependentValue value, ResumeDependentValue expected) {
        }

        public ResumeDependentValue binaryOperation(AbstractInsnNode insn, ResumeDependentValue value1, ResumeDependentValue value2) {
            return null;
        }

        public ResumeDependentValue ternaryOperation(AbstractInsnNode insn, ResumeDependentValue value1, ResumeDependentValue value2, ResumeDependentValue value3) {
            return null;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0016\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004J\b\u0010\u0010\u001a\u00020\bH\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\bH\u0096\u0080\u0004R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "states", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue$VariableState;", "<init>", "([Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue$VariableState;)V", "suspensionPointsCount", Argument.Delimiters.none, "(I)V", "getStates", "()[Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue$VariableState;", "[Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue$VariableState;", "withState", "spIndex", "state", "getSize", "toString", Argument.Delimiters.none, "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "VariableState", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ResumeDependentValue implements Value {
        private final VariableState[] states;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFA$ResumeDependentValue$VariableState;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "UNKNOWN", "INITIALIZED", "UNINITIALIZED", "isUnitialized", Argument.Delimiters.none, "merge", "other", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public enum VariableState {
            UNKNOWN,
            INITIALIZED,
            UNINITIALIZED;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[VariableState.values().length];
                    try {
                        iArr[VariableState.UNKNOWN.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[VariableState.UNINITIALIZED.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[VariableState.INITIALIZED.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            public static EnumEntries<VariableState> getEntries() {
                return $ENTRIES;
            }

            public final boolean isUnitialized() {
                return this == UNINITIALIZED;
            }

            public final VariableState merge(VariableState other) {
                other.getClass();
                if (this == UNKNOWN) {
                    return other;
                }
                VariableState variableState = UNINITIALIZED;
                return (this == variableState || other == variableState) ? variableState : this;
            }

            @Override // java.lang.Enum
            public String toString() {
                int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
                if (i == 1) {
                    return "?";
                }
                if (i == 2) {
                    return "!";
                }
                if (i == 3) {
                    return "+";
                }
                bu8.a();
                return null;
            }
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public ResumeDependentValue(int i) {
            VariableState[] variableStateArr = new VariableState[i];
            for (int i2 = 0; i2 < i; i2++) {
                variableStateArr[i2] = VariableState.UNKNOWN;
            }
            this(variableStateArr);
        }

        public static CharSequence a(VariableState variableState) {
            variableState.getClass();
            return variableState.toString();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof ResumeDependentValue) {
                return Arrays.equals(this.states, ((ResumeDependentValue) other).states);
            }
            return false;
        }

        public int getSize() {
            return 1;
        }

        public final VariableState[] getStates() {
            return this.states;
        }

        public int hashCode() {
            return Arrays.hashCode(this.states);
        }

        public String toString() {
            return ArraysKt.joinToString$default(this.states, Argument.Delimiters.none, "[", "]", 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.codegen.coroutines.b
                public final Object invoke(Object obj) {
                    return ReinitializationAnalysisUsingDFA.ResumeDependentValue.a((ReinitializationAnalysisUsingDFA.ResumeDependentValue.VariableState) obj);
                }
            }, 24, (Object) null);
        }

        public final ResumeDependentValue withState(int spIndex, VariableState state) {
            state.getClass();
            VariableState[] variableStateArr = this.states;
            if (variableStateArr[spIndex] == state) {
                return this;
            }
            VariableState[] variableStateArr2 = (VariableState[]) Arrays.copyOf(variableStateArr, variableStateArr.length);
            variableStateArr2[spIndex] = state;
            return new ResumeDependentValue(variableStateArr2);
        }

        public ResumeDependentValue(VariableState[] variableStateArr) {
            variableStateArr.getClass();
            this.states = variableStateArr;
        }
    }
}
