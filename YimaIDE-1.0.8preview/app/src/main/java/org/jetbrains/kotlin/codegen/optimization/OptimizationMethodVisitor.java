package org.jetbrains.kotlin.codegen.optimization;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.TransformationMethodVisitor;
import org.jetbrains.kotlin.codegen.coroutines.UninitializedStoresProcessor;
import org.jetbrains.kotlin.codegen.inline.InplaceArgumentsMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.boxing.PopBackwardPropagationTransformer;
import org.jetbrains.kotlin.codegen.optimization.boxing.RedundantBoxingMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.boxing.StackPeepholeOptimizationsTransformer;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.nullCheck.RedundantNullCheckMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.temporaryVals.TemporaryVariablesEliminationTransformer;
import org.jetbrains.kotlin.codegen.optimization.transformer.CompositeMethodTransformer;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000b\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/OptimizationMethodVisitor;", "Lorg/jetbrains/kotlin/codegen/TransformationMethodVisitor;", "delegate", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "mandatoryTransformationsOnly", Argument.Delimiters.none, "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "access", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "signature", "exceptions", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/MethodVisitor;ZLorg/jetbrains/kotlin/codegen/state/GenerationState;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "normalizationMethodTransformer", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/CompositeMethodTransformer;", "getNormalizationMethodTransformer", "()Lorg/jetbrains/kotlin/codegen/optimization/transformer/CompositeMethodTransformer;", "optimizationTransformer", "getOptimizationTransformer", "performTransformations", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OptimizationMethodVisitor extends TransformationMethodVisitor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MEMORY_LIMIT_BY_METHOD_MB = 50;
    private final GenerationState generationState;
    private final boolean mandatoryTransformationsOnly;
    private final CompositeMethodTransformer normalizationMethodTransformer;
    private final CompositeMethodTransformer optimizationTransformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OptimizationMethodVisitor(MethodVisitor methodVisitor, boolean z, GenerationState generationState, int i, String str, String str2, String str3, String[] strArr) {
        super(methodVisitor, i, str, str2, str3, strArr, 0, 64, null);
        methodVisitor.getClass();
        generationState.getClass();
        str.getClass();
        str2.getClass();
        this.mandatoryTransformationsOnly = z;
        this.generationState = generationState;
        this.normalizationMethodTransformer = new CompositeMethodTransformer(new InplaceArgumentsMethodTransformer(), new FixStackWithLabelNormalizationMethodTransformer(), new MethodVerifier("AFTER mandatory stack transformations", generationState));
        this.optimizationTransformer = new CompositeMethodTransformer(new CapturedVarsOptimizationMethodTransformer(), new RedundantNullCheckMethodTransformer(generationState), new RedundantCheckCastEliminationMethodTransformer(), new ConstantConditionEliminationMethodTransformer(), new RedundantBoxingMethodTransformer(generationState), new TemporaryVariablesEliminationTransformer(), new StackPeepholeOptimizationsTransformer(), new PopBackwardPropagationTransformer(), new DeadCodeEliminationMethodTransformer(), new RedundantGotoMethodTransformer(), new RedundantNopsCleanupMethodTransformer(), new NegatedJumpsMethodTransformer(), RedundantCheckcastsBeforeAastoreMethodTransformer.INSTANCE, new MethodVerifier("AFTER optimizations", generationState));
    }

    public final CompositeMethodTransformer getNormalizationMethodTransformer() {
        return this.normalizationMethodTransformer;
    }

    public final CompositeMethodTransformer getOptimizationTransformer() {
        return this.optimizationTransformer;
    }

    @Override // org.jetbrains.kotlin.codegen.TransformationMethodVisitor
    public void performTransformations(MethodNode methodNode) {
        methodNode.getClass();
        this.normalizationMethodTransformer.transform("fake", methodNode);
        new UninitializedStoresProcessor(methodNode).run();
        if (!this.mandatoryTransformationsOnly && INSTANCE.canBeOptimized(methodNode) && !this.generationState.getConfig().getDisableOptimization()) {
            this.optimizationTransformer.transform("fake", methodNode);
        }
        new DeadCodeEliminationMethodTransformer().transform("fake", methodNode);
        UtilKt.prepareForEmitting(methodNode);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\f\u001a\u00020\u0005*\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/OptimizationMethodVisitor$Companion;", Argument.Delimiters.none, "<init>", "()V", "MEMORY_LIMIT_BY_METHOD_MB", Argument.Delimiters.none, "TRY_CATCH_BLOCKS_SOFT_LIMIT", "canBeOptimized", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "canBeOptimizedUsingSourceInterpreter", "countInsnsWithFramesUntil", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "end", "getTotalFramesWeight", Argument.Delimiters.none, "size", "getTotalTcbSize", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int countInsnsWithFramesUntil(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
            AbstractInsnNode next;
            int i = 0;
            while (!Intrinsics.areEqual(abstractInsnNode, abstractInsnNode2) && abstractInsnNode != null) {
                int nodeType = UtilKt.getNodeType(abstractInsnNode);
                if ((nodeType != 15 && nodeType != 14 && nodeType != 8 && abstractInsnNode.getOpcode() != 0) || ((next = abstractInsnNode.getNext()) != null && UtilKt.getNodeType(next) == 8)) {
                    i++;
                }
                abstractInsnNode = abstractInsnNode.getNext();
            }
            return i;
        }

        private final long getTotalFramesWeight(int size, MethodNode node) {
            return (((long) size) * ((long) (node.maxLocals + node.maxStack))) / 1048576;
        }

        private final int getTotalTcbSize(MethodNode node) {
            List<TryCatchBlockNode> list = node.tryCatchBlocks;
            list.getClass();
            int iCountInsnsWithFramesUntil = 0;
            for (TryCatchBlockNode tryCatchBlockNode : list) {
                iCountInsnsWithFramesUntil += OptimizationMethodVisitor.INSTANCE.countInsnsWithFramesUntil(tryCatchBlockNode.start, tryCatchBlockNode.end);
            }
            return iCountInsnsWithFramesUntil;
        }

        public final boolean canBeOptimized(MethodNode node) {
            node.getClass();
            return (node.tryCatchBlocks.size() <= 16 || getTotalFramesWeight(getTotalTcbSize(node), node) <= 50) && getTotalFramesWeight(countInsnsWithFramesUntil(node.instructions.getFirst(), null), node) < 50;
        }

        public final boolean canBeOptimizedUsingSourceInterpreter(MethodNode node) {
            node.getClass();
            int iCountInsnsWithFramesUntil = countInsnsWithFramesUntil(node.instructions.getFirst(), null);
            return (node.tryCatchBlocks.size() <= 16 || getTotalFramesWeight(getTotalTcbSize(node) * iCountInsnsWithFramesUntil, node) <= 50) && getTotalFramesWeight(iCountInsnsWithFramesUntil * iCountInsnsWithFramesUntil, node) < 50;
        }

        private Companion() {
        }
    }
}
