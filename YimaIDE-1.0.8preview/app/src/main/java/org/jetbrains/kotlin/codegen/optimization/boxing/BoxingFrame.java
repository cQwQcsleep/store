package org.jetbrains.kotlin.codegen.optimization.boxing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxingFrame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "nLocals", Argument.Delimiters.none, "nStack", "<init>", "(II)V", "merge", Argument.Delimiters.none, "frame", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BoxingFrame extends Frame<BasicValue> {
    public BoxingFrame(int i, int i2) {
        super(i, i2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public boolean merge(Frame<? extends BasicValue> frame, Interpreter<BasicValue> interpreter) throws AnalyzerException {
        frame.getClass();
        interpreter.getClass();
        if (getStackSize() != frame.getStackSize()) {
            throw new AnalyzerException((AbstractInsnNode) null, "Incompatible stack heights");
        }
        BoxingInterpreter boxingInterpreter = (BoxingInterpreter) interpreter;
        int locals = getLocals();
        boolean z = false;
        for (int i = 0; i < locals; i++) {
            BasicValue basicValue = (BasicValue) getLocal(i);
            basicValue.getClass();
            Value local = frame.getLocal(i);
            local.getClass();
            BasicValue basicValueMergeLocalVariableValues = boxingInterpreter.mergeLocalVariableValues(basicValue, (BasicValue) local);
            if (!Intrinsics.areEqual(basicValue, basicValueMergeLocalVariableValues)) {
                setLocal(i, basicValueMergeLocalVariableValues);
                z = true;
            }
        }
        int stackSize = getStackSize();
        for (int i2 = 0; i2 < stackSize; i2++) {
            BasicValue basicValue2 = (BasicValue) getStack(i2);
            basicValue2.getClass();
            Value stack = frame.getStack(i2);
            stack.getClass();
            BasicValue basicValueMergeStackValues = boxingInterpreter.mergeStackValues(basicValue2, (BasicValue) stack);
            if (!Intrinsics.areEqual(basicValue2, basicValueMergeStackValues)) {
                setStack(i2, basicValueMergeStackValues);
                z = true;
            }
        }
        return z;
    }
}
