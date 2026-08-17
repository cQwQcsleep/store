package org.jetbrains.kotlin.codegen.optimization.nullCheck;

import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\n\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\f\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0000\"\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"isInstanceOfOrNullCheck", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "isCheckNotNull", "isCheckNotNullWithMessage", "usesLocalExceptParameterNullCheck", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "index", Argument.Delimiters.none, "isParameterCheckedForNull", "isCheckParameterIsNotNull", "isCheckExpressionValueIsNotNull", "isThrowIntrinsic", "THROW_INTRINSIC_METHOD_NAMES", Argument.Delimiters.none, Argument.Delimiters.none, "getTHROW_INTRINSIC_METHOD_NAMES", "()Ljava/util/Set;", "popReferenceValueBefore", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "insn", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantNullCheckMethodTransformerKt {
    private static final Set<String> THROW_INTRINSIC_METHOD_NAMES = SetsKt.setOf(new String[]{"throwNpe", "throwUninitializedProperty", "throwUninitializedPropertyAccessException", "throwAssert", "throwIllegalArgument", "throwIllegalState", "throwParameterIsNullException", "throwUndefinedForReified"});

    public static final Set<String> getTHROW_INTRINSIC_METHOD_NAMES() {
        return THROW_INTRINSIC_METHOD_NAMES;
    }

    public static final boolean isCheckExpressionValueIsNotNull(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        MethodInsnNode methodInsnNode = null;
        if (abstractInsnNode.getOpcode() != 184) {
            abstractInsnNode = null;
        }
        if (!(abstractInsnNode instanceof MethodInsnNode)) {
            abstractInsnNode = null;
        }
        MethodInsnNode methodInsnNode2 = (MethodInsnNode) abstractInsnNode;
        if (methodInsnNode2 != null && Intrinsics.areEqual(methodInsnNode2.owner, "kotlin/jvm/internal/Intrinsics") && ((Intrinsics.areEqual(methodInsnNode2.name, "checkExpressionValueIsNotNull") || Intrinsics.areEqual(methodInsnNode2.name, "checkNotNullExpressionValue")) && Intrinsics.areEqual(methodInsnNode2.desc, "(Ljava/lang/Object;Ljava/lang/String;)V"))) {
            methodInsnNode = methodInsnNode2;
        }
        return methodInsnNode != null;
    }

    public static final boolean isCheckNotNull(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        MethodInsnNode methodInsnNode = null;
        if (abstractInsnNode.getOpcode() != 184) {
            abstractInsnNode = null;
        }
        if (!(abstractInsnNode instanceof MethodInsnNode)) {
            abstractInsnNode = null;
        }
        MethodInsnNode methodInsnNode2 = (MethodInsnNode) abstractInsnNode;
        if (methodInsnNode2 != null && Intrinsics.areEqual(methodInsnNode2.owner, "kotlin/jvm/internal/Intrinsics") && Intrinsics.areEqual(methodInsnNode2.name, "checkNotNull") && Intrinsics.areEqual(methodInsnNode2.desc, ReifiedTypeInliner.pluginIntrinsicsMarkerSignature)) {
            methodInsnNode = methodInsnNode2;
        }
        return methodInsnNode != null;
    }

    public static final boolean isCheckNotNullWithMessage(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        MethodInsnNode methodInsnNode = null;
        if (abstractInsnNode.getOpcode() != 184) {
            abstractInsnNode = null;
        }
        if (!(abstractInsnNode instanceof MethodInsnNode)) {
            abstractInsnNode = null;
        }
        MethodInsnNode methodInsnNode2 = (MethodInsnNode) abstractInsnNode;
        if (methodInsnNode2 != null && Intrinsics.areEqual(methodInsnNode2.owner, "kotlin/jvm/internal/Intrinsics") && Intrinsics.areEqual(methodInsnNode2.name, "checkNotNull") && Intrinsics.areEqual(methodInsnNode2.desc, "(Ljava/lang/Object;Ljava/lang/String;)V")) {
            methodInsnNode = methodInsnNode2;
        }
        return methodInsnNode != null;
    }

    public static final boolean isCheckParameterIsNotNull(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        MethodInsnNode methodInsnNode = null;
        if (abstractInsnNode.getOpcode() != 184) {
            abstractInsnNode = null;
        }
        if (!(abstractInsnNode instanceof MethodInsnNode)) {
            abstractInsnNode = null;
        }
        MethodInsnNode methodInsnNode2 = (MethodInsnNode) abstractInsnNode;
        if (methodInsnNode2 != null && Intrinsics.areEqual(methodInsnNode2.owner, "kotlin/jvm/internal/Intrinsics") && ((Intrinsics.areEqual(methodInsnNode2.name, "checkParameterIsNotNull") || Intrinsics.areEqual(methodInsnNode2.name, "checkNotNullParameter")) && Intrinsics.areEqual(methodInsnNode2.desc, "(Ljava/lang/Object;Ljava/lang/String;)V"))) {
            methodInsnNode = methodInsnNode2;
        }
        return methodInsnNode != null;
    }

    public static final boolean isInstanceOfOrNullCheck(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getOpcode() == 193 || abstractInsnNode.getOpcode() == 198 || abstractInsnNode.getOpcode() == 199;
    }

    public static final boolean isParameterCheckedForNull(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode next;
        abstractInsnNode.getClass();
        AbstractInsnNode next2 = abstractInsnNode.getNext();
        if (next2 != null) {
            if (next2.getOpcode() != 18) {
                next2 = null;
            }
            if (next2 != null && (next = next2.getNext()) != null && isCheckParameterIsNotNull(next)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isThrowIntrinsic(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        MethodInsnNode methodInsnNode = null;
        if (abstractInsnNode.getOpcode() != 184) {
            abstractInsnNode = null;
        }
        if (!(abstractInsnNode instanceof MethodInsnNode)) {
            abstractInsnNode = null;
        }
        MethodInsnNode methodInsnNode2 = (MethodInsnNode) abstractInsnNode;
        if (methodInsnNode2 != null && Intrinsics.areEqual(methodInsnNode2.owner, "kotlin/jvm/internal/Intrinsics") && THROW_INTRINSIC_METHOD_NAMES.contains(methodInsnNode2.name)) {
            methodInsnNode = methodInsnNode2;
        }
        return methodInsnNode != null;
    }

    public static final void popReferenceValueBefore(InsnList insnList, AbstractInsnNode abstractInsnNode) {
        insnList.getClass();
        abstractInsnNode.getClass();
        AbstractInsnNode previous = abstractInsnNode.getPrevious();
        Integer numValueOf = previous != null ? Integer.valueOf(previous.getOpcode()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 1) || ((numValueOf != null && numValueOf.intValue() == 89) || (numValueOf != null && numValueOf.intValue() == 25))) {
            insnList.remove(previous);
        } else {
            insnList.insertBefore(abstractInsnNode, new InsnNode(87));
        }
    }

    public static final boolean usesLocalExceptParameterNullCheck(MethodNode methodNode, int i) {
        methodNode.getClass();
        Collection<VarInsnNode> collection = methodNode.instructions;
        collection.getClass();
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return false;
        }
        for (VarInsnNode varInsnNode : collection) {
            if (varInsnNode instanceof VarInsnNode) {
                VarInsnNode varInsnNode2 = varInsnNode;
                if (varInsnNode2.getOpcode() == 25 && varInsnNode2.var == i && !isParameterCheckedForNull(varInsnNode)) {
                    return true;
                }
            }
        }
        return false;
    }
}
