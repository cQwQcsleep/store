package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002J\u0011\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0003H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", Argument.Delimiters.none, "suspensionCallBegin", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "suspensionCallEnd", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)V", "getSuspensionCallBegin", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getSuspensionCallEnd", "tryCatchBlocksContinuationLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getTryCatchBlocksContinuationLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "setTryCatchBlocksContinuationLabel", "(Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;)V", "stateLabel", "getStateLabel", "unboxInlineClassInstructions", Argument.Delimiters.none, "getUnboxInlineClassInstructions", "()Ljava/util/List;", "findUnboxInlineClassInstructions", "contains", Argument.Delimiters.none, "insn", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SuspensionPoint {
    private final LabelNode stateLabel;
    private final AbstractInsnNode suspensionCallBegin;
    private final AbstractInsnNode suspensionCallEnd;
    public LabelNode tryCatchBlocksContinuationLabel;
    private final List<AbstractInsnNode> unboxInlineClassInstructions;

    public SuspensionPoint(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        abstractInsnNode.getClass();
        abstractInsnNode2.getClass();
        this.suspensionCallBegin = abstractInsnNode;
        this.suspensionCallEnd = abstractInsnNode2;
        this.stateLabel = CodegenUtilKt.linkWithLabel(new LabelNode());
        this.unboxInlineClassInstructions = findUnboxInlineClassInstructions();
    }

    private final List<AbstractInsnNode> findUnboxInlineClassInstructions() {
        AbstractInsnNode next;
        AbstractInsnNode next2 = this.suspensionCallEnd.getNext();
        if (next2 == null || (next = next2.getNext()) == null) {
            return CollectionsKt.emptyList();
        }
        if (!InlineCodegenUtilsKt.isBeforeUnboxInlineClassMarker(next)) {
            return CollectionsKt.emptyList();
        }
        AbstractInsnNode next3 = next.getNext();
        while (next3 != null && !InlineCodegenUtilsKt.isAfterUnboxInlineClassMarker(next3)) {
            next3 = next3.getNext();
        }
        if (next3 == null) {
            k2d.a("Before unbox inline class marker without after unbox inline class marker");
            return null;
        }
        AbstractInsnNode next4 = next.getNext();
        next4.getClass();
        return SequencesKt.toList(new InsnSequence(next4, next3.getPrevious().getPrevious()));
    }

    public final boolean contains(AbstractInsnNode insn) {
        insn.getClass();
        Iterator<AbstractInsnNode> it = new InsnSequence(this.suspensionCallBegin, this.suspensionCallEnd.getNext()).iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next(), insn)) {
                return true;
            }
        }
        return false;
    }

    public final LabelNode getStateLabel() {
        return this.stateLabel;
    }

    public final AbstractInsnNode getSuspensionCallBegin() {
        return this.suspensionCallBegin;
    }

    public final AbstractInsnNode getSuspensionCallEnd() {
        return this.suspensionCallEnd;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final LabelNode getTryCatchBlocksContinuationLabel() throws UninitializedPropertyAccessException {
        LabelNode labelNode = this.tryCatchBlocksContinuationLabel;
        if (labelNode != null) {
            return labelNode;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tryCatchBlocksContinuationLabel");
        return null;
    }

    public final List<AbstractInsnNode> getUnboxInlineClassInstructions() {
        return this.unboxInlineClassInstructions;
    }

    public final void setTryCatchBlocksContinuationLabel(LabelNode labelNode) {
        labelNode.getClass();
        this.tryCatchBlocksContinuationLabel = labelNode;
    }
}
