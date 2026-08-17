package org.jetbrains.kotlin.codegen.pseudoInsns;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u000f\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u0010\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\n\u0010\u0011\u001a\u00020\u000b*\u00020\f\u001a\n\u0010\u0012\u001a\u00020\u000b*\u00020\f\u001a\u0012\u0010\u0013\u001a\u00020\u0005*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\t\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0015"}, d2 = {"PSEUDO_INSN_CALL_OWNER", Argument.Delimiters.none, "getPSEUDO_INSN_CALL_OWNER", "()Ljava/lang/String;", "isPseudoInsn", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "parsePseudoInsnOrNull", "Lorg/jetbrains/kotlin/codegen/pseudoInsns/PseudoInsn;", "fixStackAndJump", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/org/objectweb/asm/Label;", "fakeAlwaysTrueIfeq", "fakeAlwaysFalseIfeq", "storeNotNull", "asNotNull", "isPseudo", "pseudoInsn", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PseudoInsnsKt {
    private static final String PSEUDO_INSN_CALL_OWNER = "kotlin/jvm/internal/$PseudoInsn";

    public static final void asNotNull(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        PseudoInsn.AS_NOT_NULL.emit(instructionAdapter);
    }

    public static final void fakeAlwaysFalseIfeq(InstructionAdapter instructionAdapter, Label label) {
        instructionAdapter.getClass();
        label.getClass();
        PseudoInsn.FAKE_ALWAYS_FALSE_IFEQ.emit(instructionAdapter);
        instructionAdapter.ifeq(label);
    }

    public static final void fakeAlwaysTrueIfeq(InstructionAdapter instructionAdapter, Label label) {
        instructionAdapter.getClass();
        label.getClass();
        PseudoInsn.FAKE_ALWAYS_TRUE_IFEQ.emit(instructionAdapter);
        instructionAdapter.ifeq(label);
    }

    public static final void fixStackAndJump(InstructionAdapter instructionAdapter, Label label) {
        instructionAdapter.getClass();
        label.getClass();
        PseudoInsn.FIX_STACK_BEFORE_JUMP.emit(instructionAdapter);
        instructionAdapter.goTo(label);
    }

    public static final String getPSEUDO_INSN_CALL_OWNER() {
        return PSEUDO_INSN_CALL_OWNER;
    }

    public static final boolean isPseudo(AbstractInsnNode abstractInsnNode, PseudoInsn pseudoInsn) {
        abstractInsnNode.getClass();
        pseudoInsn.getClass();
        return pseudoInsn.isa(abstractInsnNode);
    }

    public static final boolean isPseudoInsn(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return (abstractInsnNode instanceof MethodInsnNode) && abstractInsnNode.getOpcode() == 184 && Intrinsics.areEqual(((MethodInsnNode) abstractInsnNode).owner, PSEUDO_INSN_CALL_OWNER);
    }

    public static final PseudoInsn parsePseudoInsnOrNull(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (!isPseudoInsn(abstractInsnNode)) {
            return null;
        }
        String str = ((MethodInsnNode) abstractInsnNode).name;
        str.getClass();
        return PseudoInsn.valueOf(str);
    }

    public static final void storeNotNull(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        PseudoInsn.STORE_NOT_NULL.emit(instructionAdapter);
    }
}
