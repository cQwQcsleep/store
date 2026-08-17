package org.jetbrains.kotlin.codegen.optimization.temporaryVals;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/StoreLoadFrame;", "V", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "maxLocals", Argument.Delimiters.none, "<init>", "(I)V", "getMaxLocals", "()I", "execute", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StoreLoadFrame<V extends Value> extends Frame<V> {
    private final int maxLocals;

    public StoreLoadFrame(int i) {
        super(i, 0);
        this.maxLocals = i;
    }

    public void execute(AbstractInsnNode insn, Interpreter<V> interpreter) {
        insn.getClass();
        interpreter.getClass();
        int opcode = insn.getOpcode();
        if (54 <= opcode && opcode < 59) {
            VarInsnNode varInsnNode = (VarInsnNode) insn;
            setLocal(varInsnNode.var, interpreter.copyOperation(varInsnNode, (Value) null));
        } else if (21 <= opcode && opcode < 26) {
            VarInsnNode varInsnNode2 = (VarInsnNode) insn;
            interpreter.copyOperation(varInsnNode2, getLocal(varInsnNode2.var));
        } else if (opcode == 132) {
            IincInsnNode iincInsnNode = (IincInsnNode) insn;
            interpreter.unaryOperation(iincInsnNode, getLocal(iincInsnNode.var));
        }
    }

    public final int getMaxLocals() {
        return this.maxLocals;
    }
}
