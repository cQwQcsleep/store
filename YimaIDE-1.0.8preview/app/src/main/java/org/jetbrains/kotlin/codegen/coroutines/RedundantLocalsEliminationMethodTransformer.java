package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/RedundantLocalsEliminationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "<init>", "(Ljava/util/List;)V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantLocalsEliminationMethodTransformer extends MethodTransformer {
    private final List<SuspensionPoint> suspensionPoints;

    public RedundantLocalsEliminationMethodTransformer(List<SuspensionPoint> list) {
        list.getClass();
        this.suspensionPoints = list;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        Set setEmptySet;
        internalClassName.getClass();
        methodNode.getClass();
        List list = methodNode.localVariables;
        if (list != null) {
            setEmptySet = new LinkedHashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                setEmptySet.add(Integer.valueOf(((LocalVariableNode) it.next()).index));
            }
        } else {
            setEmptySet = SetsKt.emptySet();
        }
        UnitSourceInterpreter unitSourceInterpreter = new UnitSourceInterpreter(setEmptySet);
        Frame<BasicValue>[] frameArrRun = unitSourceInterpreter.run(internalClassName, methodNode);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (Pair pair : SequencesKt.zip(InsnSequenceKt.asSequence(insnList), ArraysKt.asSequence(frameArrRun))) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) pair.component1();
            if (((Frame) pair.component2()) != null || (abstractInsnNode instanceof LabelNode)) {
                abstractInsnNode = null;
            }
            if (abstractInsnNode != null) {
                linkedHashSet.add(abstractInsnNode);
            }
        }
        for (Map.Entry<AbstractInsnNode, Set<AbstractInsnNode>> entry : unitSourceInterpreter.getUnitUsageInformation().entrySet()) {
            AbstractInsnNode key = entry.getKey();
            Set<AbstractInsnNode> value = entry.getValue();
            if (!unitSourceInterpreter.getUnspillableUnitValues().contains(key) && !CoroutineTransformerMethodVisitorKt.contains(this.suspensionPoints, key)) {
                linkedHashSet.add(key);
                CollectionsKt.addAll(linkedHashSet, value);
            }
        }
        InsnList insnList2 = methodNode.instructions;
        insnList2.getClass();
        UtilKt.removeAll(insnList2, linkedHashSet);
    }
}
