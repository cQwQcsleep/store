package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.InstructionLivenessAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J/\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\rH\u0002¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/RedundantCheckCastEliminationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getTypeAdjustmentForALoadInstructions", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lorg/jetbrains/org/objectweb/asm/Type;", "insns", Argument.Delimiters.none, "([Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)Ljava/util/Map;", "isTrivialSubtype", Argument.Delimiters.none, "superType", "subType", "isMultiArrayType", ModuleXmlParser.TYPE, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantCheckCastEliminationMethodTransformer extends MethodTransformer {
    private final Map<AbstractInsnNode, Type> getTypeAdjustmentForALoadInstructions(AbstractInsnNode[] insns, MethodNode methodNode) {
        boolean[] zArrAnalyze = new InstructionLivenessAnalyzer(methodNode, false).analyze();
        HashMap map = new HashMap();
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            int iIndexOf = methodNode.instructions.indexOf(localVariableNode.end);
            for (int iIndexOf2 = methodNode.instructions.indexOf(localVariableNode.start); iIndexOf2 < iIndexOf; iIndexOf2++) {
                AbstractInsnNode abstractInsnNode = insns[iIndexOf2];
                if (abstractInsnNode.getOpcode() == 25 && ((VarInsnNode) abstractInsnNode).var == localVariableNode.index && !zArrAnalyze[iIndexOf2]) {
                    map.put(abstractInsnNode, Type.getType(localVariableNode.desc));
                }
            }
        }
        return map;
    }

    private final boolean isMultiArrayType(Type type) {
        return type.getSort() == 9 && type.getDimensions() != 1;
    }

    private final boolean isTrivialSubtype(Type superType, Type subType) {
        return Intrinsics.areEqual(superType, subType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Code duplicated, block: B:25:0x0085  */
    /* JADX WARN: Code duplicated, block: B:27:0x008e  */
    /* JADX WARN: Code duplicated, block: B:45:0x0091 A[SYNTHETIC] */
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        Frame frame;
        BasicValue pVar;
        internalClassName.getClass();
        methodNode.getClass();
        AbstractInsnNode[] array = methodNode.instructions.toArray();
        array.getClass();
        for (AbstractInsnNode abstractInsnNode : array) {
            if (abstractInsnNode.getOpcode() == 192) {
                for (AbstractInsnNode abstractInsnNode2 : array) {
                    ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
                    abstractInsnNode2.getClass();
                    if (companion.isOperationReifiedMarker(abstractInsnNode2)) {
                        return;
                    }
                }
                final Map<AbstractInsnNode, Type> typeAdjustmentForALoadInstructions = getTypeAdjustmentForALoadInstructions(array, methodNode);
                OptimizationBasicInterpreter optimizationBasicInterpreter = new OptimizationBasicInterpreter() { // from class: org.jetbrains.kotlin.codegen.optimization.RedundantCheckCastEliminationMethodTransformer$transform$interpreter$1
                    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
                    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
                    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
                        insn.getClass();
                        Type type = typeAdjustmentForALoadInstructions.get(insn);
                        if (type == null) {
                            BasicValue basicValueCopyOperation = super.copyOperation(insn, value);
                            basicValueCopyOperation.getClass();
                            return basicValueCopyOperation;
                        }
                        StrictBasicValue strictBasicValueM69newValue = m69newValue(type);
                        if (strictBasicValueM69newValue != null) {
                            return strictBasicValueM69newValue;
                        }
                        s22.a("Local variable type can't be VOID: ", type);
                        return null;
                    }
                };
                ArrayList arrayList = new ArrayList();
                Frame[] frameArrAnalyze = new FastMethodAnalyzer(internalClassName, methodNode, optimizationBasicInterpreter, true, null, 16, null).analyze();
                int length = array.length;
                for (int i = 0; i < length; i++) {
                    AbstractInsnNode abstractInsnNode3 = array[i];
                    if (abstractInsnNode3.getOpcode() == 192 && (frame = frameArrAnalyze[i]) != null && (pVar = StackTransformationUtilsKt.top(frame)) != null) {
                        TypeInsnNode typeInsnNode = (TypeInsnNode) abstractInsnNode3;
                        Type objectType = Type.getObjectType(typeInsnNode.desc);
                        if (pVar != StrictBasicValue.NULL_VALUE) {
                            objectType.getClass();
                            Type type = pVar.getType();
                            type.getClass();
                            if (isTrivialSubtype(objectType, type)) {
                                objectType.getClass();
                                if (!isMultiArrayType(objectType)) {
                                    arrayList.add(typeInsnNode);
                                }
                            }
                        } else {
                            objectType.getClass();
                            if (!isMultiArrayType(objectType)) {
                                arrayList.add(typeInsnNode);
                            }
                        }
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    methodNode.instructions.remove((TypeInsnNode) it.next());
                }
                return;
            }
        }
    }
}
