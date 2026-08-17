package org.jetbrains.kotlin.codegen.optimization.temporaryVals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer;", Argument.Delimiters.none, "<init>", "()V", "analyze", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryVal;", "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "StoreData", "StoredValue", "StoreTrackingInterpreter", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TemporaryValsAnalyzer {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R!\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0012j\b\u0012\u0004\u0012\u00020\u0003`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreData;", Argument.Delimiters.none, "storeInsn", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;)V", "getStoreInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "isDirty", Argument.Delimiters.none, "()Z", "setDirty", "(Z)V", "value", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$Store;", "getValue", "()Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$Store;", "loads", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "getLoads", "()Ljava/util/LinkedHashSet;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class StoreData {
        private boolean isDirty;
        private final LinkedHashSet<VarInsnNode> loads;
        private final VarInsnNode storeInsn;
        private final StoredValue.Store value;

        public StoreData(VarInsnNode varInsnNode) {
            varInsnNode.getClass();
            this.storeInsn = varInsnNode;
            this.value = new StoredValue.Store(this);
            this.loads = new LinkedHashSet<>();
        }

        public final LinkedHashSet<VarInsnNode> getLoads() {
            return this.loads;
        }

        public final VarInsnNode getStoreInsn() {
            return this.storeInsn;
        }

        public final StoredValue.Store getValue() {
            return this.value;
        }

        /* JADX INFO: renamed from: isDirty, reason: from getter */
        public final boolean getIsDirty() {
            return this.isDirty;
        }

        public final void setDirty(boolean z) {
            this.isDirty = z;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public final List<TemporaryVal> analyze(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        int opcode;
        internalClassName.getClass();
        methodNode.getClass();
        final InsnList insnList = methodNode.instructions;
        VarInsnNode[] array = insnList.toArray();
        ArrayList arrayList = new ArrayList();
        for (Object obj : insnList) {
            if (obj instanceof VarInsnNode) {
                arrayList.add(obj);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj2 : arrayList) {
            if (UtilKt.isStoreOperation((VarInsnNode) obj2)) {
                linkedHashSet.add(obj2);
            }
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            int iIndexOf = insnList.indexOf(localVariableNode.end);
            for (int iIndexOf2 = insnList.indexOf(localVariableNode.start); iIndexOf2 < iIndexOf; iIndexOf2++) {
                VarInsnNode varInsnNode = array[iIndexOf2];
                varInsnNode.getClass();
                if (UtilKt.isStoreOperation(varInsnNode) && varInsnNode.var == localVariableNode.index) {
                    linkedHashSet.remove(varInsnNode);
                }
            }
            for (VarInsnNode previous = localVariableNode.start.getPrevious(); previous != null; previous = previous.getPrevious()) {
                if (UtilKt.isStoreOperation(previous) && previous.var == localVariableNode.index) {
                    linkedHashSet.remove(previous);
                    break;
                }
                if (UtilKt.getNodeType(previous) == 8 || ((172 <= (opcode = previous.getOpcode()) && opcode < 178) || previous.getOpcode() == 167 || previous.getOpcode() == 191)) {
                    break;
                }
            }
        }
        for (TryCatchBlockNode tryCatchBlockNode : methodNode.tryCatchBlocks) {
            AbstractInsnNode abstractInsnNodeResolveCatchStoreInstruction = tryCatchBlockNode.handler;
            while (abstractInsnNodeResolveCatchStoreInstruction != null && !UtilKt.isMeaningful(abstractInsnNodeResolveCatchStoreInstruction)) {
                abstractInsnNodeResolveCatchStoreInstruction = abstractInsnNodeResolveCatchStoreInstruction.getNext();
            }
            if (abstractInsnNodeResolveCatchStoreInstruction != null) {
                abstractInsnNodeResolveCatchStoreInstruction = InlineCodegenUtilsKt.resolveCatchStoreInstruction(abstractInsnNodeResolveCatchStoreInstruction);
            }
            if (abstractInsnNodeResolveCatchStoreInstruction != null) {
                TypeIntrinsics.asMutableCollection(linkedHashSet).remove(abstractInsnNodeResolveCatchStoreInstruction);
            }
            for (AbstractInsnNode previous2 = tryCatchBlockNode.start.getPrevious(); previous2 != null && UtilKt.isStoreOperation(previous2); previous2 = previous2.getPrevious()) {
                TypeIntrinsics.asMutableCollection(linkedHashSet).remove(previous2);
            }
        }
        if (!linkedHashSet.isEmpty() && ((((long) methodNode.instructions.size()) * ((long) methodNode.localVariables.size())) * ((long) linkedHashSet.size())) / 1048576 <= 50) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(linkedHashSet, 10)), 16));
            for (Object obj3 : linkedHashSet) {
                linkedHashMap.put(obj3, new StoreData((VarInsnNode) obj3));
            }
            Frame[] frameArrAnalyze = new FastStoreLoadAnalyzer(internalClassName, methodNode, new StoreTrackingInterpreter(linkedHashMap), null, 8, null).analyze();
            for (LocalVariableNode localVariableNode2 : methodNode.localVariables) {
                Frame frame = frameArrAnalyze[insnList.indexOf(localVariableNode2.start)];
                if (frame != null) {
                    StoredValue storedValue = (StoredValue) frame.getLocal(localVariableNode2.index);
                    if (storedValue instanceof StoredValue.Store) {
                        ((StoredValue.Store) storedValue).getTemporaryVal().setDirty(true);
                    } else if (storedValue instanceof StoredValue.DirtyStore) {
                        Iterator<T> it = ((StoredValue.DirtyStore) storedValue).getTemporaryVals().iterator();
                        while (it.hasNext()) {
                            ((StoreData) it.next()).setDirty(true);
                        }
                    } else if (!Intrinsics.areEqual(storedValue, StoredValue.Unknown.INSTANCE)) {
                        bu8.a();
                        return null;
                    }
                }
            }
            Collection collectionValues = linkedHashMap.values();
            ArrayList<StoreData> arrayList2 = new ArrayList();
            for (Object obj4 : collectionValues) {
                if (!((StoreData) obj4).getIsDirty()) {
                    arrayList2.add(obj4);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (StoreData storeData : arrayList2) {
                arrayList3.add(new TemporaryVal(storeData.getStoreInsn().var, storeData.getStoreInsn(), CollectionsKt.toList(storeData.getLoads())));
            }
            return CollectionsKt.sortedWith(arrayList3, new Comparator() { // from class: org.jetbrains.kotlin.codegen.optimization.temporaryVals.TemporaryValsAnalyzer$analyze$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(insnList.indexOf(((TemporaryVal) t).getStoreInsn())), Integer.valueOf(insnList.indexOf(((TemporaryVal) t2).getStoreInsn())));
                }
            });
        }
        return CollectionsKt.emptyList();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "<init>", "()V", "getSize", Argument.Delimiters.none, "Unknown", "Store", "DirtyStore", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$DirtyStore;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$Store;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$Unknown;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class StoredValue implements Value {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0082\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$DirtyStore;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue;", "temporaryVals", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreData;", "<init>", "(Ljava/util/Collection;)V", "getTemporaryVals", "()Ljava/util/Collection;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class DirtyStore extends StoredValue {
            private final Collection<StoreData> temporaryVals;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public DirtyStore(Collection<StoreData> collection) {
                super(null);
                collection.getClass();
                this.temporaryVals = collection;
            }

            public boolean equals(Object other) {
                return (other instanceof DirtyStore) && Intrinsics.areEqual(((DirtyStore) other).temporaryVals, this.temporaryVals);
            }

            public final Collection<StoreData> getTemporaryVals() {
                return this.temporaryVals;
            }

            public int hashCode() {
                return this.temporaryVals.hashCode();
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$Store;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue;", "temporaryVal", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreData;", "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreData;)V", "getTemporaryVal", "()Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreData;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Store extends StoredValue {
            private final StoreData temporaryVal;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Store(StoreData storeData) {
                super(null);
                storeData.getClass();
                this.temporaryVal = storeData;
            }

            public boolean equals(Object other) {
                return (other instanceof Store) && ((Store) other).temporaryVal == this.temporaryVal;
            }

            public final StoreData getTemporaryVal() {
                return this.temporaryVal;
            }

            public int hashCode() {
                return this.temporaryVal.hashCode();
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue$Unknown;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue;", "<init>", "()V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Unknown extends StoredValue {
            public static final Unknown INSTANCE = new Unknown();

            private Unknown() {
                super(null);
            }
        }

        public /* synthetic */ StoredValue(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public int getSize() {
            return 1;
        }

        private StoredValue() {
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0012\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018*\u00020\u0002H\u0002J\u0012\u0010\u0019\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J&\u0010\u001a\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0016J0\u0010\u001d\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0016J$\u0010\u001f\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0010\u0010 \u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010!H\u0016J&\u0010\"\u001a\u00020#2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00022\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreTrackingInterpreter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoredValue;", "storeInsnToStoreData", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer$StoreData;", "<init>", "(Ljava/util/Map;)V", "newEmptyValue", "local", Argument.Delimiters.none, "newValue", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "copyOperation", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "value", "unaryOperation", "merge", "a", "b", "temporaryVals", Argument.Delimiters.none, "newOperation", "binaryOperation", "value1", "value2", "ternaryOperation", "value3", "naryOperation", "values", Argument.Delimiters.none, "returnOperation", Argument.Delimiters.none, "expected", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class StoreTrackingInterpreter extends Interpreter<StoredValue> {
        private final Map<VarInsnNode, StoreData> storeInsnToStoreData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StoreTrackingInterpreter(Map<VarInsnNode, StoreData> map) {
            super(589824);
            map.getClass();
            this.storeInsnToStoreData = map;
        }

        private final Collection<StoreData> temporaryVals(StoredValue storedValue) {
            if (storedValue instanceof StoredValue.Store) {
                return SetsKt.setOf(((StoredValue.Store) storedValue).getTemporaryVal());
            }
            return storedValue instanceof StoredValue.DirtyStore ? ((StoredValue.DirtyStore) storedValue).getTemporaryVals() : SetsKt.emptySet();
        }

        public StoredValue copyOperation(AbstractInsnNode insn, StoredValue value) {
            insn.getClass();
            if (value == null) {
                StoreData storeData = this.storeInsnToStoreData.get(insn);
                if (storeData != null) {
                    return storeData.getValue();
                }
            } else if (value instanceof StoredValue.DirtyStore) {
                Iterator<T> it = ((StoredValue.DirtyStore) value).getTemporaryVals().iterator();
                while (it.hasNext()) {
                    ((StoreData) it.next()).setDirty(true);
                }
            } else if (value instanceof StoredValue.Store) {
                ((StoredValue.Store) value).getTemporaryVal().getLoads().add((VarInsnNode) insn);
            }
            return StoredValue.Unknown.INSTANCE;
        }

        public StoredValue merge(StoredValue a, StoredValue b) {
            a.getClass();
            b.getClass();
            if (a == b) {
                return a;
            }
            if (!(a instanceof StoredValue.Store) && !(a instanceof StoredValue.DirtyStore) && !(b instanceof StoredValue.Store) && !(b instanceof StoredValue.DirtyStore)) {
                return StoredValue.Unknown.INSTANCE;
            }
            SmartSet smartSetCreate = SmartSet.Companion.create(temporaryVals(a));
            smartSetCreate.addAll(temporaryVals(b));
            return new StoredValue.DirtyStore(smartSetCreate);
        }

        public StoredValue naryOperation(AbstractInsnNode insn, List<? extends StoredValue> values) {
            throw new IllegalStateException("Should not be called");
        }

        /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
        public StoredValue m73newOperation(AbstractInsnNode insn) {
            throw new IllegalStateException("Should not be called");
        }

        public StoredValue unaryOperation(AbstractInsnNode insn, StoredValue value) {
            insn.getClass();
            value.getClass();
            if (insn.getOpcode() == 132) {
                if (value instanceof StoredValue.Store) {
                    ((StoredValue.Store) value).getTemporaryVal().setDirty(true);
                    return value;
                }
                if (value instanceof StoredValue.DirtyStore) {
                    Iterator<T> it = ((StoredValue.DirtyStore) value).getTemporaryVals().iterator();
                    while (it.hasNext()) {
                        ((StoreData) it.next()).setDirty(true);
                    }
                }
            }
            return value;
        }

        /* JADX INFO: renamed from: newEmptyValue, reason: merged with bridge method [inline-methods] */
        public StoredValue m72newEmptyValue(int local) {
            return StoredValue.Unknown.INSTANCE;
        }

        /* JADX INFO: renamed from: newValue, reason: merged with bridge method [inline-methods] */
        public StoredValue m74newValue(Type type) {
            return StoredValue.Unknown.INSTANCE;
        }

        /* JADX INFO: renamed from: naryOperation, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Value m71naryOperation(AbstractInsnNode abstractInsnNode, List list) {
            return naryOperation(abstractInsnNode, (List<? extends StoredValue>) list);
        }

        public void returnOperation(AbstractInsnNode insn, StoredValue value, StoredValue expected) {
            throw new IllegalStateException("Should not be called");
        }

        public StoredValue binaryOperation(AbstractInsnNode insn, StoredValue value1, StoredValue value2) {
            throw new IllegalStateException("Should not be called");
        }

        public StoredValue ternaryOperation(AbstractInsnNode insn, StoredValue value1, StoredValue value2, StoredValue value3) {
            throw new IllegalStateException("Should not be called");
        }
    }
}
