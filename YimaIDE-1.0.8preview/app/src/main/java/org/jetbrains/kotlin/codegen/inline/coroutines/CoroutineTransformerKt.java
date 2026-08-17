package org.jetbrains.kotlin.codegen.inline.coroutines;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.inline.FunctionalArgument;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.inline.NonInlineArgumentForInlineSuspendParameter;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u001c\u0010\t\u001a\u0004\u0018\u00010\n*\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"FOR_INLINE_SUFFIX", Argument.Delimiters.none, "NOINLINE_CALL_MARKER", "markNoinlineLambdaIfSuspend", Argument.Delimiters.none, "mv", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "info", "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "getSource", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "offset", Argument.Delimiters.none, "surroundInvokesWithSuspendMarkersIfNeeded", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoroutineTransformerKt {
    public static final String FOR_INLINE_SUFFIX = "$$forInline";

    public static boolean a(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getOpcode() == 184 && Intrinsics.areEqual(((MethodInsnNode) abstractInsnNode).owner, "$$$$$NOINLINE_CALL_MARKER$$$$$");
    }

    private static final AbstractInsnNode getSource(Frame<BasicValue> frame, int i) {
        PossibleLambdaLoad stack = frame.getStack((frame.getStackSize() - i) - 1);
        PossibleLambdaLoad possibleLambdaLoad = stack instanceof PossibleLambdaLoad ? stack : null;
        if (possibleLambdaLoad != null) {
            return possibleLambdaLoad.getInsn();
        }
        return null;
    }

    public static final void markNoinlineLambdaIfSuspend(MethodVisitor methodVisitor, FunctionalArgument functionalArgument) {
        methodVisitor.getClass();
        if (functionalArgument == NonInlineArgumentForInlineSuspendParameter.OTHER) {
            methodVisitor.visitMethodInsn(184, "$$$$$NOINLINE_CALL_MARKER$$$$$", K2JsArgumentConstants.SOURCE_MAP_SOURCE_CONTENT_ALWAYS, "()V", false);
        } else if (functionalArgument == NonInlineArgumentForInlineSuspendParameter.INLINE_LAMBDA_AS_VARIABLE) {
            methodVisitor.visitMethodInsn(184, "$$$$$NOINLINE_CALL_MARKER$$$$$", "conditional", "()V", false);
        }
    }

    public static final void surroundInvokesWithSuspendMarkersIfNeeded(MethodNode methodNode) {
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        List list = SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: m13
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoroutineTransformerKt.a((AbstractInsnNode) obj));
            }
        }));
        if (list.isEmpty()) {
            return;
        }
        Frame[] frameArrAnalyze = MethodTransformer.analyze("fake", methodNode, new CapturedLambdaInterpreter());
        frameArrAnalyze.getClass();
        List<AbstractInsnNode> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (AbstractInsnNode abstractInsnNode : list2) {
            MethodInsnNode next = abstractInsnNode.getNext();
            next.getClass();
            String str = next.owner;
            str.getClass();
            int i = Integer.parseInt(StringsKt.removePrefix(str, InlineCodegenUtilsKt.NUMBERED_FUNCTION_PREFIX));
            Frame frame = frameArrAnalyze[methodNode.instructions.indexOf(abstractInsnNode) + 1];
            AbstractInsnNode source = frame != null ? getSource(frame, i) : null;
            while (source != null && source.getOpcode() == 180) {
                source = source.getPrevious();
            }
            arrayList.add(source);
        }
        for (Pair pair : CollectionsKt.zip(list2, arrayList)) {
            MethodInsnNode methodInsnNode = (AbstractInsnNode) pair.component1();
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) pair.component2();
            methodInsnNode.getClass();
            MethodInsnNode methodInsnNode2 = methodInsnNode;
            boolean zAreEqual = Intrinsics.areEqual(methodInsnNode2.name, "conditional");
            MethodInsnNode next2 = methodInsnNode2.getNext();
            next2.getClass();
            MethodInsnNode methodInsnNode3 = next2;
            methodNode.instructions.remove(methodInsnNode);
            if (abstractInsnNode2 != null) {
                InsnList insnList2 = methodNode.instructions;
                MethodNode methodNode2 = new MethodNode();
                InlineCodegenUtilsKt.addInlineMarker(new InstructionAdapter(methodNode2), true);
                Unit unit = Unit.INSTANCE;
                InsnList insnList3 = methodNode2.instructions;
                insnList3.getClass();
                insnList2.insertBefore(abstractInsnNode2, insnList3);
                InsnList insnList4 = methodNode.instructions;
                MethodNode methodNode3 = new MethodNode();
                InlineCodegenUtilsKt.addSuspendMarker(new InstructionAdapter(methodNode3), true, zAreEqual);
                InsnList insnList5 = methodNode3.instructions;
                insnList5.getClass();
                insnList4.insertBefore(methodInsnNode3, insnList5);
                InsnList insnList6 = methodNode.instructions;
                MethodNode methodNode4 = new MethodNode();
                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode4);
                InlineCodegenUtilsKt.addSuspendMarker(instructionAdapter, false, zAreEqual);
                InlineCodegenUtilsKt.addInlineMarker(instructionAdapter, false);
                InsnList insnList7 = methodNode4.instructions;
                insnList7.getClass();
                insnList6.insert(methodInsnNode3, insnList7);
            }
        }
    }
}
