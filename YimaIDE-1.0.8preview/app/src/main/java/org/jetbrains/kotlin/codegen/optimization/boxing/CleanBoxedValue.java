package org.jetbrains.kotlin.codegen.optimization.boxing;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0014\u001a\u00020\u0001H\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/CleanBoxedValue;", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedBasicValue;", "boxedType", "Lorg/jetbrains/org/objectweb/asm/Type;", "boxingInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "progressionIterator", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "getGenerationState", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "descriptor", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedValueDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedValueDescriptor;", "tainted", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/TaintedBoxedValue;", "taint", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CleanBoxedValue extends BoxedBasicValue {
    private final BoxedValueDescriptor descriptor;
    private final GenerationState generationState;
    private TaintedBoxedValue tainted;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CleanBoxedValue(Type type, AbstractInsnNode abstractInsnNode, ProgressionIteratorBasicValue progressionIteratorBasicValue, GenerationState generationState) {
        super(type);
        type.getClass();
        abstractInsnNode.getClass();
        generationState.getClass();
        this.generationState = generationState;
        this.descriptor = new BoxedValueDescriptor(type, abstractInsnNode, progressionIteratorBasicValue, generationState);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxedBasicValue
    public BoxedValueDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final GenerationState getGenerationState() {
        return this.generationState;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxedBasicValue
    public BoxedBasicValue taint() {
        TaintedBoxedValue taintedBoxedValue = this.tainted;
        if (taintedBoxedValue != null) {
            return taintedBoxedValue;
        }
        TaintedBoxedValue taintedBoxedValue2 = new TaintedBoxedValue(this);
        this.tainted = taintedBoxedValue2;
        return taintedBoxedValue2;
    }
}
