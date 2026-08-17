package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0015\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u0092\u0002\u0002\b\f¢\u0006\u0002\n\u0000R\u0015\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\f¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\f¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\f¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MethodInliner$PointForExternalFinallyBlocks;", "", "beforeIns", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "returnType", "Lorg/jetbrains/org/objectweb/asm/Type;", "finallyIntervalEnd", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "jumpTarget", "Lorg/jetbrains/org/objectweb/asm/Label;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;Lorg/jetbrains/org/objectweb/asm/Label;)V", "Lkotlin/jvm/JvmField;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class MethodInliner$PointForExternalFinallyBlocks {
    public final AbstractInsnNode beforeIns;
    public final LabelNode finallyIntervalEnd;
    public final Label jumpTarget;
    public final Type returnType;

    public MethodInliner$PointForExternalFinallyBlocks(AbstractInsnNode abstractInsnNode, Type type, LabelNode labelNode, Label label) {
        abstractInsnNode.getClass();
        type.getClass();
        labelNode.getClass();
        this.beforeIns = abstractInsnNode;
        this.returnType = type;
        this.finallyIntervalEnd = labelNode;
        this.jumpTarget = label;
    }
}
