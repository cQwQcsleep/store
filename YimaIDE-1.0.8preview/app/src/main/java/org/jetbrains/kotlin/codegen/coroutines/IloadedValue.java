package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/IloadedValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insns", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "<init>", "(Ljava/util/Set;)V", "getInsns", "()Ljava/util/Set;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IloadedValue extends BasicValue {
    private final Set<VarInsnNode> insns;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IloadedValue(Set<? extends VarInsnNode> set) {
        super(Type.INT_TYPE);
        set.getClass();
        this.insns = set;
    }

    public final Set<VarInsnNode> getInsns() {
        return this.insns;
    }
}
