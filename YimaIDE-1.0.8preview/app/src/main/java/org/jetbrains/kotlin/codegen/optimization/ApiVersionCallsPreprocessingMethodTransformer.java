package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.IntInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/ApiVersionCallsPreprocessingMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "targetApiVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "<init>", "(Lorg/jetbrains/kotlin/config/ApiVersion;)V", "constantConditionElimination", "Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer;", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "isApiVersionIsAtLeastCall", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getIntConstValue", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/Integer;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ApiVersionCallsPreprocessingMethodTransformer extends MethodTransformer {
    private final ConstantConditionEliminationMethodTransformer constantConditionElimination;
    private final ApiVersion targetApiVersion;

    public ApiVersionCallsPreprocessingMethodTransformer(ApiVersion apiVersion) {
        apiVersion.getClass();
        this.targetApiVersion = apiVersion;
        this.constantConditionElimination = new ConstantConditionEliminationMethodTransformer();
    }

    private final Integer getIntConstValue(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode instanceof InsnNode) {
            InsnNode insnNode = (InsnNode) abstractInsnNode;
            int opcode = insnNode.getOpcode();
            if (2 > opcode || opcode >= 9) {
                return null;
            }
            return Integer.valueOf(insnNode.getOpcode() - 3);
        }
        if (!(abstractInsnNode instanceof IntInsnNode)) {
            if (abstractInsnNode instanceof LdcInsnNode) {
                Object obj = ((LdcInsnNode) abstractInsnNode).cst;
                if (obj instanceof Integer) {
                    return (Integer) obj;
                }
            }
            return null;
        }
        IntInsnNode intInsnNode = (IntInsnNode) abstractInsnNode;
        int opcode2 = intInsnNode.getOpcode();
        if (opcode2 == 16 || opcode2 == 17) {
            return Integer.valueOf(intInsnNode.operand);
        }
        return null;
    }

    private final boolean isApiVersionIsAtLeastCall(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode.getOpcode() == 184 && (abstractInsnNode instanceof MethodInsnNode)) {
            MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
            String str = methodInsnNode.owner;
            str.getClass();
            if (StringsKt.startsWith$default(str, "kotlin/internal", false, 2, (Object) null) && Intrinsics.areEqual(methodInsnNode.name, "apiVersionIsAtLeast") && Intrinsics.areEqual(methodInsnNode.desc, "(III)Z")) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        AbstractInsnNode previous;
        Integer intConstValue;
        Integer intConstValue2;
        Integer intConstValue3;
        internalClassName.getClass();
        methodNode.getClass();
        AbstractInsnNode[] array = methodNode.instructions.toArray();
        array.getClass();
        boolean z = false;
        for (AbstractInsnNode abstractInsnNode : array) {
            abstractInsnNode.getClass();
            if (isApiVersionIsAtLeastCall(abstractInsnNode) && (previous = abstractInsnNode.getPrevious()) != null && (intConstValue = getIntConstValue(previous)) != null) {
                int iIntValue = intConstValue.intValue();
                AbstractInsnNode previous2 = previous.getPrevious();
                if (previous2 != null && (intConstValue2 = getIntConstValue(previous2)) != null) {
                    int iIntValue2 = intConstValue2.intValue();
                    AbstractInsnNode previous3 = previous2.getPrevious();
                    if (previous3 != null && (intConstValue3 = getIntConstValue(previous3)) != null) {
                        int iIntValue3 = intConstValue3.intValue();
                        StringBuilder sb = new StringBuilder();
                        sb.append(iIntValue3);
                        sb.append('.');
                        sb.append(iIntValue2);
                        sb.append('.');
                        sb.append(iIntValue);
                        InsnNode insnNode = this.targetApiVersion.getVersion().compareTo(new MavenComparableVersion(sb.toString())) >= 0 ? new InsnNode(4) : new InsnNode(3);
                        InsnList insnList = methodNode.instructions;
                        insnList.remove(previous3);
                        insnList.remove(previous2);
                        insnList.remove(previous);
                        insnList.set(abstractInsnNode, insnNode);
                        z = true;
                    }
                }
            }
        }
        if (z) {
            this.constantConditionElimination.transform(internalClassName, methodNode);
        }
    }
}
