package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a-\u0010\u0000\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\b\u001a\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\fH\u0002¨\u0006\u0011"}, d2 = {"performSpilledVariableFieldTypesAnalysis", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "thisName", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Ljava/lang/String;)[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "coerceInt", Argument.Delimiters.none, "to", "Lorg/jetbrains/org/objectweb/asm/Type;", "v", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "isIntLike", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SpilledVariableFieldTypesAnalysisKt {
    private static final void coerceInt(Type type, InstructionAdapter instructionAdapter) {
        if (!Intrinsics.areEqual(type, Type.BOOLEAN_TYPE)) {
            StackValue.coerce(Type.INT_TYPE, type, instructionAdapter);
            return;
        }
        Label label = new Label();
        Label label2 = new Label();
        instructionAdapter.ifeq(label);
        instructionAdapter.iconst(1);
        instructionAdapter.goTo(label2);
        instructionAdapter.mark(label);
        instructionAdapter.iconst(0);
        instructionAdapter.mark(label2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isIntLike(Type type) {
        int sort = type.getSort();
        return sort == 1 || sort == 2 || sort == 3 || sort == 4;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Type inference failed for: r0v4, types: [org.jetbrains.org.objectweb.asm.tree.analysis.Frame<org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue>[], org.jetbrains.org.objectweb.asm.tree.analysis.Frame[]] */
    public static final Frame<BasicValue>[] performSpilledVariableFieldTypesAnalysis(MethodNode methodNode, String str) throws AnalyzerException {
        methodNode.getClass();
        str.getClass();
        IntLikeCoerceInterpreter intLikeCoerceInterpreter = new IntLikeCoerceInterpreter();
        new FastMethodAnalyzer(str, methodNode, intLikeCoerceInterpreter, false, null, 24, null).analyze();
        for (Map.Entry<VarInsnNode, Type> entry : intLikeCoerceInterpreter.getNeedsToBeCoerced().entrySet()) {
            VarInsnNode key = entry.getKey();
            Type value = entry.getValue();
            InsnList insnList = methodNode.instructions;
            MethodNode methodNode2 = new MethodNode();
            coerceInt(value, new InstructionAdapter(methodNode2));
            Unit unit = Unit.INSTANCE;
            InsnList insnList2 = methodNode2.instructions;
            insnList2.getClass();
            insnList.insert(key, insnList2);
        }
        return new FastMethodAnalyzer(str, methodNode, new OptimizationBasicInterpreter(), false, null, 24, null).analyze();
    }
}
