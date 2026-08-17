package org.jetbrains.kotlin.codegen.pseudoInsns;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/pseudoInsns/PseudoInsn;", Argument.Delimiters.none, "signature", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getSignature", "()Ljava/lang/String;", "FIX_STACK_BEFORE_JUMP", "FAKE_ALWAYS_TRUE_IFEQ", "FAKE_ALWAYS_FALSE_IFEQ", "SAVE_STACK_BEFORE_TRY", "RESTORE_STACK_IN_TRY_CATCH", "STORE_NOT_NULL", "AS_NOT_NULL", "emit", Argument.Delimiters.none, "iv", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "createInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "isa", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum PseudoInsn {
    FIX_STACK_BEFORE_JUMP(null, 1, null),
    FAKE_ALWAYS_TRUE_IFEQ("()I"),
    FAKE_ALWAYS_FALSE_IFEQ("()I"),
    SAVE_STACK_BEFORE_TRY(null, 1, null),
    RESTORE_STACK_IN_TRY_CATCH(null, 1, null),
    STORE_NOT_NULL(null, 1, null),
    AS_NOT_NULL("(Ljava/lang/Object;)Ljava/lang/Object;");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String signature;

    /* synthetic */ PseudoInsn(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "()V" : str);
    }

    public static EnumEntries<PseudoInsn> getEntries() {
        return $ENTRIES;
    }

    public final MethodInsnNode createInsnNode() {
        return new MethodInsnNode(184, PseudoInsnsKt.getPSEUDO_INSN_CALL_OWNER(), toString(), this.signature, false);
    }

    public final void emit(InstructionAdapter iv) {
        iv.getClass();
        iv.invokestatic(PseudoInsnsKt.getPSEUDO_INSN_CALL_OWNER(), toString(), this.signature, false);
    }

    public final String getSignature() {
        return this.signature;
    }

    public final boolean isa(AbstractInsnNode node) {
        node.getClass();
        return this == PseudoInsnsKt.parsePseudoInsnOrNull(node);
    }

    PseudoInsn(String str) {
        this.signature = str;
    }
}
