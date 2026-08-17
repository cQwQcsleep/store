package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.JvmKotlinType;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\f\u0010\u000b\u001a\u00020\u0005*\u00020\fH\u0002J\u0014\u0010\r\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\r\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/IrInlineCodegen$Companion;", Argument.Delimiters.none, "<init>", "()V", "isLocalWithNoBoxing", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/StackValue;", "expected", "Lorg/jetbrains/kotlin/codegen/JvmKotlinType;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "requiresEmptyStackOnEntry", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "isBackwardsJump", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "from", "to", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrInlineCodegen$Companion {
    public /* synthetic */ IrInlineCodegen$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final boolean isBackwardsJump(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode instanceof JumpInsnNode) {
            LabelNode labelNode = ((JumpInsnNode) abstractInsnNode).label;
            labelNode.getClass();
            return isBackwardsJump(methodNode, abstractInsnNode, labelNode);
        }
        if (abstractInsnNode instanceof LookupSwitchInsnNode) {
            LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode) abstractInsnNode;
            LabelNode labelNode2 = lookupSwitchInsnNode.dflt;
            if (labelNode2 == null || !IrInlineCodegen.Companion.isBackwardsJump(methodNode, abstractInsnNode, labelNode2)) {
                List list = lookupSwitchInsnNode.labels;
                list.getClass();
                List<LabelNode> list2 = list;
                if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                    for (LabelNode labelNode3 : list2) {
                        IrInlineCodegen$Companion irInlineCodegen$Companion = IrInlineCodegen.Companion;
                        labelNode3.getClass();
                        if (irInlineCodegen$Companion.isBackwardsJump(methodNode, abstractInsnNode, labelNode3)) {
                        }
                    }
                }
                return false;
            }
            return true;
        }
        if (abstractInsnNode instanceof TableSwitchInsnNode) {
            TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode) abstractInsnNode;
            LabelNode labelNode4 = tableSwitchInsnNode.dflt;
            if (labelNode4 == null || !IrInlineCodegen.Companion.isBackwardsJump(methodNode, abstractInsnNode, labelNode4)) {
                List list3 = tableSwitchInsnNode.labels;
                list3.getClass();
                List<LabelNode> list4 = list3;
                if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                    for (LabelNode labelNode5 : list4) {
                        IrInlineCodegen$Companion irInlineCodegen$Companion2 = IrInlineCodegen.Companion;
                        labelNode5.getClass();
                        if (irInlineCodegen$Companion2.isBackwardsJump(methodNode, abstractInsnNode, labelNode5)) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLocalWithNoBoxing(StackValue stackValue, JvmKotlinType jvmKotlinType, KotlinTypeMapperBase kotlinTypeMapperBase) {
        return (stackValue instanceof StackValue.Local) && AsmUtil.isPrimitive(jvmKotlinType.getType()) == AsmUtil.isPrimitive(stackValue.type) && !StackValue.requiresInlineClassBoxingOrUnboxing(stackValue.type, stackValue.kotlinType, jvmKotlinType.getType(), jvmKotlinType.getKotlinType(), kotlinTypeMapperBase);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean requiresEmptyStackOnEntry(MethodNode methodNode) {
        List list = methodNode.tryCatchBlocks;
        list.getClass();
        if (!list.isEmpty()) {
            return true;
        }
        Collection<AbstractInsnNode> collection = methodNode.instructions;
        collection.getClass();
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return false;
        }
        for (AbstractInsnNode abstractInsnNode : collection) {
            abstractInsnNode.getClass();
            if (InlineCodegenUtilsKt.isBeforeSuspendMarker(abstractInsnNode) || InlineCodegenUtilsKt.isBeforeInlineSuspendMarker(abstractInsnNode) || IrInlineCodegen.Companion.isBackwardsJump(methodNode, abstractInsnNode)) {
                return true;
            }
        }
        return false;
    }

    private IrInlineCodegen$Companion() {
    }

    private final boolean isBackwardsJump(MethodNode methodNode, AbstractInsnNode abstractInsnNode, LabelNode labelNode) {
        return methodNode.instructions.indexOf(labelNode) < methodNode.instructions.indexOf(abstractInsnNode);
    }
}
