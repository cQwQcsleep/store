package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsnsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J \u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0002J4\u0010\u0010\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\f2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J4\u0010\u0017\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J<\u0010\u0018\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J4\u0010\u001d\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J<\u0010\u001e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J<\u0010 \u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "analyzeAndTransformBreakContinueGotos", "context", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;", "analyzeAndTransformSaveRestoreStack", "removeAlwaysFalseIfeqMarkers", "removeAlwaysTrueIfeqMarkers", "transformBreakContinueGotos", "fixStackContext", "actions", Argument.Delimiters.none, "Lkotlin/Function0;", "analyzer", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackAnalyzer;", "transformSaveRestoreStackMarkers", "transformSaveStackMarker", "marker", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "localVariablesManager", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/LocalVariablesManager;", "transformRestoreStackMarker", "transformAfterInlineCallMarker", "inlineMarker", "transformBeforeInlineCallMarker", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FixStackMethodTransformer extends MethodTransformer {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void analyzeAndTransformBreakContinueGotos(FixStackContext context, String internalClassName, MethodNode methodNode) throws AnalyzerException {
        FixStackAnalyzer fixStackAnalyzer = new FixStackAnalyzer(internalClassName, methodNode, context, true);
        fixStackAnalyzer.analyze();
        methodNode.maxStack += fixStackAnalyzer.getMaxExtraStackSize();
        ArrayList arrayList = new ArrayList();
        transformBreakContinueGotos(methodNode, context, arrayList, fixStackAnalyzer);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void analyzeAndTransformSaveRestoreStack(FixStackContext context, String internalClassName, MethodNode methodNode) throws AnalyzerException {
        FixStackAnalyzer fixStackAnalyzer = new FixStackAnalyzer(internalClassName, methodNode, context, false);
        fixStackAnalyzer.analyze();
        ArrayList arrayList = new ArrayList();
        transformSaveRestoreStackMarkers(methodNode, context, arrayList, fixStackAnalyzer);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }

    public static Unit c(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        methodNode.instructions.remove(abstractInsnNode);
        return Unit.INSTANCE;
    }

    public static Unit d(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        StackTransformationUtilsKt.saveStack(methodNode, abstractInsnNode, savedStackDescriptor);
        return Unit.INSTANCE;
    }

    public static Unit e(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        StackTransformationUtilsKt.restoreStack(methodNode, abstractInsnNode, savedStackDescriptor);
        return Unit.INSTANCE;
    }

    public static Unit f(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        StackTransformationUtilsKt.saveStack(methodNode, abstractInsnNode, savedStackDescriptor);
        return Unit.INSTANCE;
    }

    public static Unit g(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor, FixStackValue fixStackValue, int i) {
        StackTransformationUtilsKt.restoreStackWithReturnValue(methodNode, abstractInsnNode, savedStackDescriptor, fixStackValue, i);
        return Unit.INSTANCE;
    }

    public static Unit h(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        StackTransformationUtilsKt.restoreStack(methodNode, abstractInsnNode, savedStackDescriptor);
        return Unit.INSTANCE;
    }

    public static Unit i(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        methodNode.instructions.remove(abstractInsnNode);
        return Unit.INSTANCE;
    }

    public static Unit j(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        methodNode.instructions.remove(abstractInsnNode);
        return Unit.INSTANCE;
    }

    private final void removeAlwaysFalseIfeqMarkers(FixStackContext context, MethodNode methodNode) {
        Iterator<T> it = context.getFakeAlwaysFalseIfeqMarkers().iterator();
        while (it.hasNext()) {
            StackTransformationUtilsKt.removeAlwaysFalseIfeq(methodNode, (AbstractInsnNode) it.next());
        }
        context.getFakeAlwaysFalseIfeqMarkers().clear();
    }

    private final void removeAlwaysTrueIfeqMarkers(FixStackContext context, MethodNode methodNode) {
        Iterator<T> it = context.getFakeAlwaysTrueIfeqMarkers().iterator();
        while (it.hasNext()) {
            StackTransformationUtilsKt.replaceAlwaysTrueIfeqWithGoto(methodNode, (AbstractInsnNode) it.next());
        }
        context.getFakeAlwaysTrueIfeqMarkers().clear();
    }

    private final void transformAfterInlineCallMarker(final MethodNode methodNode, List<Function0<Unit>> actions, FixStackAnalyzer analyzer, AbstractInsnNode inlineMarker, LocalVariablesManager localVariablesManager) {
        final AbstractInsnNode abstractInsnNode;
        final SavedStackDescriptor beforeInlineDescriptor = localVariablesManager.getBeforeInlineDescriptor(inlineMarker);
        List<FixStackValue> actualStack = analyzer.getActualStack(inlineMarker);
        if (actualStack == null || !beforeInlineDescriptor.isNotEmpty()) {
            abstractInsnNode = inlineMarker;
            actions.add(new Function0() { // from class: yg5
                public final Object invoke() {
                    return FixStackMethodTransformer.i(methodNode, abstractInsnNode);
                }
            });
        } else {
            int size = actualStack.size();
            if (size == 0) {
                abstractInsnNode = inlineMarker;
                actions.add(new Function0() { // from class: xg5
                    public final Object invoke() {
                        return FixStackMethodTransformer.e(methodNode, abstractInsnNode, beforeInlineDescriptor);
                    }
                });
            } else {
                if (size != 1) {
                    x01.a("Inline method should not leave more than 1 value on stack");
                    return;
                }
                final FixStackValue fixStackValue = (FixStackValue) CollectionsKt.last(actualStack);
                final int iCreateReturnValueVariable = localVariablesManager.createReturnValueVariable(fixStackValue);
                abstractInsnNode = inlineMarker;
                actions.add(new Function0() { // from class: wg5
                    public final Object invoke() {
                        return FixStackMethodTransformer.g(methodNode, abstractInsnNode, beforeInlineDescriptor, fixStackValue, iCreateReturnValueVariable);
                    }
                });
            }
        }
        localVariablesManager.markAfterInlineMarkerEmitted(abstractInsnNode);
    }

    private final void transformBeforeInlineCallMarker(final MethodNode methodNode, List<Function0<Unit>> actions, FixStackAnalyzer analyzer, final AbstractInsnNode inlineMarker, LocalVariablesManager localVariablesManager) {
        List<FixStackValue> stackToSpill = analyzer.getStackToSpill(inlineMarker);
        if (stackToSpill != null) {
            final SavedStackDescriptor savedStackDescriptorAllocateVariablesForBeforeInlineMarker = localVariablesManager.allocateVariablesForBeforeInlineMarker(inlineMarker, stackToSpill);
            actions.add(new Function0() { // from class: bh5
                public final Object invoke() {
                    return FixStackMethodTransformer.f(methodNode, inlineMarker, savedStackDescriptorAllocateVariablesForBeforeInlineMarker);
                }
            });
        } else {
            localVariablesManager.allocateVariablesForBeforeInlineMarker(inlineMarker, CollectionsKt.emptyList());
            actions.add(new Function0() { // from class: ch5
                public final Object invoke() {
                    return FixStackMethodTransformer.j(methodNode, inlineMarker);
                }
            });
        }
    }

    private final void transformBreakContinueGotos(final MethodNode methodNode, FixStackContext fixStackContext, List<Function0<Unit>> actions, FixStackAnalyzer analyzer) {
        for (final JumpInsnNode jumpInsnNode : fixStackContext.getBreakContinueGotoNodes()) {
            int iIndexOf = methodNode.instructions.indexOf(jumpInsnNode);
            int iIndexOf2 = methodNode.instructions.indexOf(jumpInsnNode.label);
            int actualStackSize = analyzer.getActualStackSize(jumpInsnNode);
            LabelNode labelNode = jumpInsnNode.label;
            labelNode.getClass();
            final int expectedStackSize = analyzer.getExpectedStackSize(labelNode);
            if (actualStackSize >= 0 && expectedStackSize >= 0) {
                final List<FixStackValue> actualStack = analyzer.getActualStack(jumpInsnNode);
                if (actualStack == null) {
                    rwa.a("Jump at ", iIndexOf, " should be alive");
                    return;
                }
                actions.add(new Function0() { // from class: ug5
                    public final Object invoke() {
                        return FixStackMethodTransformer.transformBreakContinueGotos$lambda$0$1(methodNode, jumpInsnNode, expectedStackSize, actualStack);
                    }
                });
            } else {
                if (actualStackSize >= 0 && expectedStackSize < 0) {
                    throw new AssertionError("Live jump " + iIndexOf + " to dead label " + iIndexOf2);
                }
                final AbstractInsnNode previous = jumpInsnNode.getPrevious();
                actions.add(new Function0() { // from class: vg5
                    public final Object invoke() {
                        return FixStackMethodTransformer.transformBreakContinueGotos$lambda$0$2(methodNode, previous);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit transformBreakContinueGotos$lambda$0$1(MethodNode methodNode, JumpInsnNode jumpInsnNode, int i, List list) {
        AbstractInsnNode previous = jumpInsnNode.getPrevious();
        previous.getClass();
        StackTransformationUtilsKt.replaceMarkerWithPops(methodNode, previous, i, list);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit transformBreakContinueGotos$lambda$0$2(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        methodNode.instructions.remove(abstractInsnNode);
        return Unit.INSTANCE;
    }

    private final void transformRestoreStackMarker(final MethodNode methodNode, List<Function0<Unit>> actions, final AbstractInsnNode marker, LocalVariablesManager localVariablesManager) {
        final SavedStackDescriptor savedStackDescriptor = localVariablesManager.getSavedStackDescriptor(marker);
        actions.add(new Function0() { // from class: tg5
            public final Object invoke() {
                return FixStackMethodTransformer.h(methodNode, marker, savedStackDescriptor);
            }
        });
        localVariablesManager.markRestoreStackMarkerEmitted(marker);
    }

    private final void transformSaveRestoreStackMarkers(MethodNode methodNode, FixStackContext context, List<Function0<Unit>> actions, FixStackAnalyzer analyzer) {
        FixStackMethodTransformer fixStackMethodTransformer;
        MethodNode methodNode2;
        List<Function0<Unit>> list;
        FixStackAnalyzer fixStackAnalyzer;
        LocalVariablesManager localVariablesManager = new LocalVariablesManager(context, methodNode);
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : new InsnSequence(insnList)) {
            if (PseudoInsnsKt.parsePseudoInsnOrNull(abstractInsnNode) == PseudoInsn.SAVE_STACK_BEFORE_TRY) {
                fixStackMethodTransformer = this;
                methodNode2 = methodNode;
                list = actions;
                fixStackAnalyzer = analyzer;
                fixStackMethodTransformer.transformSaveStackMarker(methodNode2, list, fixStackAnalyzer, abstractInsnNode, localVariablesManager);
            } else {
                fixStackMethodTransformer = this;
                methodNode2 = methodNode;
                list = actions;
                fixStackAnalyzer = analyzer;
                if (InlineCodegenUtilsKt.isBeforeInlineMarker(abstractInsnNode)) {
                    fixStackMethodTransformer.transformBeforeInlineCallMarker(methodNode2, list, fixStackAnalyzer, abstractInsnNode, localVariablesManager);
                } else if (InlineCodegenUtilsKt.isAfterInlineMarker(abstractInsnNode)) {
                    fixStackMethodTransformer.transformAfterInlineCallMarker(methodNode2, list, fixStackAnalyzer, abstractInsnNode, localVariablesManager);
                }
            }
            this = fixStackMethodTransformer;
            methodNode = methodNode2;
            actions = list;
            analyzer = fixStackAnalyzer;
        }
        FixStackMethodTransformer fixStackMethodTransformer2 = this;
        MethodNode methodNode3 = methodNode;
        List<Function0<Unit>> list2 = actions;
        InsnList insnList2 = methodNode3.instructions;
        insnList2.getClass();
        for (AbstractInsnNode abstractInsnNode2 : new InsnSequence(insnList2)) {
            if (PseudoInsnsKt.parsePseudoInsnOrNull(abstractInsnNode2) == PseudoInsn.RESTORE_STACK_IN_TRY_CATCH) {
                fixStackMethodTransformer2.transformRestoreStackMarker(methodNode3, list2, abstractInsnNode2, localVariablesManager);
            }
        }
    }

    private final void transformSaveStackMarker(final MethodNode methodNode, List<Function0<Unit>> actions, FixStackAnalyzer analyzer, final AbstractInsnNode marker, LocalVariablesManager localVariablesManager) {
        List<FixStackValue> stackToSpill = analyzer.getStackToSpill(marker);
        if (stackToSpill != null) {
            final SavedStackDescriptor savedStackDescriptorAllocateVariablesForSaveStackMarker = localVariablesManager.allocateVariablesForSaveStackMarker(marker, stackToSpill);
            actions.add(new Function0() { // from class: zg5
                public final Object invoke() {
                    return FixStackMethodTransformer.d(methodNode, marker, savedStackDescriptorAllocateVariablesForSaveStackMarker);
                }
            });
        } else {
            localVariablesManager.allocateVariablesForSaveStackMarker(marker, CollectionsKt.emptyList());
            actions.add(new Function0() { // from class: ah5
                public final Object invoke() {
                    return FixStackMethodTransformer.c(methodNode, marker);
                }
            });
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        internalClassName.getClass();
        methodNode.getClass();
        FixStackContext fixStackContext = new FixStackContext(methodNode);
        if (fixStackContext.hasAnyMarkers()) {
            if (!fixStackContext.getConsistentInlineMarkers()) {
                InsnList insnList = methodNode.instructions;
                insnList.getClass();
                for (AbstractInsnNode abstractInsnNode : new InsnSequence(insnList)) {
                    if (InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode)) {
                        methodNode.instructions.remove(abstractInsnNode);
                    }
                }
            }
            if (fixStackContext.isAnalysisRequired()) {
                analyzeAndTransformBreakContinueGotos(fixStackContext, internalClassName, methodNode);
                removeAlwaysFalseIfeqMarkers(fixStackContext, methodNode);
                analyzeAndTransformSaveRestoreStack(fixStackContext, internalClassName, methodNode);
            }
            removeAlwaysTrueIfeqMarkers(fixStackContext, methodNode);
            removeAlwaysFalseIfeqMarkers(fixStackContext, methodNode);
        }
    }
}
