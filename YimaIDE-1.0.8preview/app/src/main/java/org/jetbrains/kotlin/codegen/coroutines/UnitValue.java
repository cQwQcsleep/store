package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\bJ\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/UnitValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insns", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "<init>", "(Ljava/util/Set;)V", "insn", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)V", "getInsns", "()Ljava/util/Set;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class UnitValue extends BasicValue {
    private final Set<AbstractInsnNode> insns;

    /* JADX WARN: Illegal instructions before constructor call */
    public UnitValue(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        Set setSingleton = Collections.singleton(abstractInsnNode);
        setSingleton.getClass();
        this((Set<? extends AbstractInsnNode>) setSingleton);
    }

    public boolean equals(Object other) {
        return (other instanceof UnitValue) && Intrinsics.areEqual(this.insns, ((UnitValue) other).insns);
    }

    public final Set<AbstractInsnNode> getInsns() {
        return this.insns;
    }

    public int hashCode() {
        return Objects.hash(this.insns);
    }

    public String toString() {
        return "U";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnitValue(Set<? extends AbstractInsnNode> set) {
        super(AsmTypes.OBJECT_TYPE);
        set.getClass();
        this.insns = set;
    }
}
