package org.jetbrains.kotlin.codegen.optimization.boxing;

import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010\u001c\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010*\u001a\n +*\u0004\u0018\u00010\u00030\u00032\b\u0010,\u001a\u0004\u0018\u00010-J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050'J\u001a\u00100\u001a\u0002012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001b03J\u000e\u00104\u001a\u0002012\u0006\u00105\u001a\u00020\u0005J\u000e\u00106\u001a\u0002012\u0006\u00107\u001a\u00020\u001bJ\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001b0'J\u000e\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020\u0000J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00000<J\u0006\u0010=\u001a\u000201J\u0006\u0010>\u001a\u00020\u001bJ\u0006\u0010?\u001a\u00020\u001fJ\u0016\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u0003J\u0018\u0010C\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00180DJ\u001a\u0010E\u001a\u0002012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001b03R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0015j\b\u0012\u0004\u0012\u00020\u0005`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0017\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00180\u0015j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u0018`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00000\u001aj\b\u0012\u0004\u0012\u00020\u0000`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010.\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedValueDescriptor;", Argument.Delimiters.none, "boxedType", "Lorg/jetbrains/org/objectweb/asm/Type;", "boxingInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "progressionIterator", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "getBoxedType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getBoxingInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getProgressionIterator", "()Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;", "getGenerationState", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "associatedInsns", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "unboxingWithCastInsns", "Lcom/intellij/openapi/util/Pair;", "associatedVariables", "Ljava/util/HashSet;", Argument.Delimiters.none, "Lkotlin/collections/HashSet;", "mergedWith", "value", Argument.Delimiters.none, "isSafeToRemove", "()Z", "multiFieldValueClassUnboxInfo", "Lorg/jetbrains/kotlin/codegen/state/GenerationState$MultiFieldValueClassUnboxInfo;", "getMultiFieldValueClassUnboxInfo", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState$MultiFieldValueClassUnboxInfo;", "unboxedTypes", Argument.Delimiters.none, "getUnboxedTypes", "()Ljava/util/List;", "getUnboxTypeOrOtherwiseMethodReturnType", "kotlin.jvm.PlatformType", "methodInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "isValueClassValue", "getAssociatedInsns", "sortAssociatedInsns", Argument.Delimiters.none, "indexes", Argument.Delimiters.none, "addInsn", "insnNode", "addVariableIndex", "index", "getVariablesIndexes", "addMergedWith", "descriptor", "getMergedWith", Argument.Delimiters.none, "markAsUnsafeToRemove", "getTotalUnboxSize", "isFromProgressionIterator", "addUnboxingWithCastTo", "insn", ModuleXmlParser.TYPE, "getUnboxingWithCastInsns", Argument.Delimiters.none, "sortUnboxingWithCastInsns", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BoxedValueDescriptor {
    private final LinkedHashSet<AbstractInsnNode> associatedInsns;
    private final HashSet<Integer> associatedVariables;
    private final Type boxedType;
    private final AbstractInsnNode boxingInsn;
    private final GenerationState generationState;
    private boolean isSafeToRemove;
    private final boolean isValueClassValue;
    private final HashSet<BoxedValueDescriptor> mergedWith;
    private final GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo;
    private final ProgressionIteratorBasicValue progressionIterator;
    private final List<Type> unboxedTypes;
    private final LinkedHashSet<Pair<AbstractInsnNode, Type>> unboxingWithCastInsns;

    public BoxedValueDescriptor(Type type, AbstractInsnNode abstractInsnNode, ProgressionIteratorBasicValue progressionIteratorBasicValue, GenerationState generationState) {
        type.getClass();
        abstractInsnNode.getClass();
        generationState.getClass();
        this.boxedType = type;
        this.boxingInsn = abstractInsnNode;
        this.progressionIterator = progressionIteratorBasicValue;
        this.generationState = generationState;
        this.associatedInsns = new LinkedHashSet<>();
        this.unboxingWithCastInsns = new LinkedHashSet<>();
        this.associatedVariables = new HashSet<>();
        this.mergedWith = new HashSet<>();
        this.isSafeToRemove = true;
        GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo = BoxedBasicValueKt.getMultiFieldValueClassUnboxInfo(type, generationState);
        this.multiFieldValueClassUnboxInfo = multiFieldValueClassUnboxInfo;
        this.unboxedTypes = BoxedBasicValueKt.getUnboxedTypes(type, generationState, multiFieldValueClassUnboxInfo);
        this.isValueClassValue = !AsmUtil.isBoxedPrimitiveType(type);
    }

    public final void addInsn(AbstractInsnNode insnNode) {
        insnNode.getClass();
        this.associatedInsns.add(insnNode);
    }

    public final void addMergedWith(BoxedValueDescriptor descriptor) {
        descriptor.getClass();
        this.mergedWith.add(descriptor);
    }

    public final void addUnboxingWithCastTo(AbstractInsnNode insn, Type type) {
        insn.getClass();
        type.getClass();
        this.unboxingWithCastInsns.add(Pair.create(insn, type));
    }

    public final void addVariableIndex(int index) {
        this.associatedVariables.add(Integer.valueOf(index));
    }

    public final List<AbstractInsnNode> getAssociatedInsns() {
        return CollectionsKt.toList(this.associatedInsns);
    }

    public final Type getBoxedType() {
        return this.boxedType;
    }

    public final AbstractInsnNode getBoxingInsn() {
        return this.boxingInsn;
    }

    public final GenerationState getGenerationState() {
        return this.generationState;
    }

    public final Iterable<BoxedValueDescriptor> getMergedWith() {
        return this.mergedWith;
    }

    public final GenerationState.MultiFieldValueClassUnboxInfo getMultiFieldValueClassUnboxInfo() {
        return this.multiFieldValueClassUnboxInfo;
    }

    public final ProgressionIteratorBasicValue getProgressionIterator() {
        return this.progressionIterator;
    }

    public final int getTotalUnboxSize() {
        Iterator<T> it = this.unboxedTypes.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((Type) it.next()).getSize();
        }
        return size;
    }

    public final Type getUnboxTypeOrOtherwiseMethodReturnType(MethodInsnNode methodInsnNode) {
        Type type = (Type) CollectionsKt.singleOrNull(this.unboxedTypes);
        if (type != null) {
            return type;
        }
        methodInsnNode.getClass();
        return Type.getReturnType(methodInsnNode.desc);
    }

    public final List<Type> getUnboxedTypes() {
        return this.unboxedTypes;
    }

    public final Set<Pair<AbstractInsnNode, Type>> getUnboxingWithCastInsns() {
        return this.unboxingWithCastInsns;
    }

    public final List<Integer> getVariablesIndexes() {
        return new ArrayList(this.associatedVariables);
    }

    public final boolean isFromProgressionIterator() {
        return this.progressionIterator != null;
    }

    /* JADX INFO: renamed from: isSafeToRemove, reason: from getter */
    public final boolean getIsSafeToRemove() {
        return this.isSafeToRemove;
    }

    /* JADX INFO: renamed from: isValueClassValue, reason: from getter */
    public final boolean getIsValueClassValue() {
        return this.isValueClassValue;
    }

    public final void markAsUnsafeToRemove() {
        this.isSafeToRemove = false;
    }

    public final void sortAssociatedInsns(final Map<AbstractInsnNode, Integer> indexes) {
        indexes.getClass();
        List listSortedWith = CollectionsKt.sortedWith(this.associatedInsns, new Comparator() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.BoxedValueDescriptor$sortAssociatedInsns$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Object obj = indexes.get((AbstractInsnNode) t);
                obj.getClass();
                Object obj2 = indexes.get((AbstractInsnNode) t2);
                obj2.getClass();
                return ComparisonsKt.compareValues((Integer) obj, (Integer) obj2);
            }
        });
        this.associatedInsns.clear();
        this.associatedInsns.addAll(listSortedWith);
    }

    public final void sortUnboxingWithCastInsns(final Map<AbstractInsnNode, Integer> indexes) {
        indexes.getClass();
        List listSortedWith = CollectionsKt.sortedWith(this.unboxingWithCastInsns, new Comparator() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.BoxedValueDescriptor$sortUnboxingWithCastInsns$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Object obj = indexes.get(((Pair) t).first);
                obj.getClass();
                Object obj2 = indexes.get(((Pair) t2).first);
                obj2.getClass();
                return ComparisonsKt.compareValues((Integer) obj, (Integer) obj2);
            }
        });
        this.unboxingWithCastInsns.clear();
        this.unboxingWithCastInsns.addAll(listSortedWith);
    }
}
