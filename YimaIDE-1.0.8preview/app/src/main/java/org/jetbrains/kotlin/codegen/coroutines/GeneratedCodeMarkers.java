package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J0\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/GeneratedCodeMarkers;", Argument.Delimiters.none, "checkContinuation", Argument.Delimiters.none, "lambdaArgumentsUnspilling", "tableswitch", "checkResult", "checkCOROUTINE_SUSPENDED", "unreachable", "<init>", "(IIIIII)V", "getCheckContinuation", "()I", "getLambdaArgumentsUnspilling", "getTableswitch", "getCheckResult", "getCheckCOROUTINE_SUSPENDED", "getUnreachable", "addFakeVariablesToLVTAndInitializeThem", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "isForNamedFunction", Argument.Delimiters.none, "addLocalVariableAndInitializeIt", "start", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "end", "suffix", Argument.Delimiters.none, "index", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GeneratedCodeMarkers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int checkCOROUTINE_SUSPENDED;
    private final int checkContinuation;
    private final int checkResult;
    private final int lambdaArgumentsUnspilling;
    private final int tableswitch;
    private final int unreachable;

    public GeneratedCodeMarkers(int i, int i2, int i3, int i4, int i5, int i6) {
        this.checkContinuation = i;
        this.lambdaArgumentsUnspilling = i2;
        this.tableswitch = i3;
        this.checkResult = i4;
        this.checkCOROUTINE_SUSPENDED = i5;
        this.unreachable = i6;
    }

    private final void addLocalVariableAndInitializeIt(MethodNode methodNode, LabelNode start, LabelNode end, String suffix, int index) {
        InsnList insnList = methodNode.instructions;
        MethodNode methodNode2 = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
        instructionAdapter.iconst(0);
        instructionAdapter.store(index, Type.INT_TYPE);
        Unit unit = Unit.INSTANCE;
        InsnList insnList2 = methodNode2.instructions;
        insnList2.getClass();
        insnList.insertBefore(start, insnList2);
        methodNode.localVariables.add(new LocalVariableNode("$ecd$" + suffix, "I", null, start, end, index));
    }

    public final void addFakeVariablesToLVTAndInitializeThem(MethodNode methodNode, boolean isForNamedFunction) {
        GeneratedCodeMarkers generatedCodeMarkers;
        MethodNode methodNode2;
        methodNode.getClass();
        LabelNode orCreateStartingLabel = CoroutineTransformerMethodVisitorKt.getOrCreateStartingLabel(methodNode);
        LabelNode orCreateEndingLabel = CoroutineTransformerMethodVisitorKt.getOrCreateEndingLabel(methodNode);
        if (isForNamedFunction) {
            String str = "checkContinuation$" + this.checkContinuation;
            int i = methodNode.maxLocals;
            methodNode.maxLocals = i + 1;
            generatedCodeMarkers = this;
            methodNode2 = methodNode;
            generatedCodeMarkers.addLocalVariableAndInitializeIt(methodNode2, orCreateStartingLabel, orCreateEndingLabel, str, i);
        } else {
            generatedCodeMarkers = this;
            methodNode2 = methodNode;
            String str2 = "lambdaArgumentsUnspilling$" + generatedCodeMarkers.lambdaArgumentsUnspilling;
            int i2 = methodNode2.maxLocals;
            methodNode2.maxLocals = i2 + 1;
            generatedCodeMarkers.addLocalVariableAndInitializeIt(methodNode2, orCreateStartingLabel, orCreateEndingLabel, str2, i2);
        }
        String str3 = "tableswitch$" + generatedCodeMarkers.tableswitch;
        int i3 = methodNode2.maxLocals;
        methodNode2.maxLocals = i3 + 1;
        generatedCodeMarkers.addLocalVariableAndInitializeIt(methodNode2, orCreateStartingLabel, orCreateEndingLabel, str3, i3);
        String str4 = "checkResult$" + generatedCodeMarkers.checkResult;
        int i4 = methodNode2.maxLocals;
        methodNode2.maxLocals = i4 + 1;
        generatedCodeMarkers.addLocalVariableAndInitializeIt(methodNode2, orCreateStartingLabel, orCreateEndingLabel, str4, i4);
        String str5 = "checkCOROUTINE_SUSPENDED$" + generatedCodeMarkers.checkCOROUTINE_SUSPENDED;
        int i5 = methodNode2.maxLocals;
        methodNode2.maxLocals = i5 + 1;
        generatedCodeMarkers.addLocalVariableAndInitializeIt(methodNode2, orCreateStartingLabel, orCreateEndingLabel, str5, i5);
        String str6 = "unreachable$" + generatedCodeMarkers.unreachable;
        int i6 = methodNode2.maxLocals;
        methodNode2.maxLocals = i6 + 1;
        generatedCodeMarkers.addLocalVariableAndInitializeIt(methodNode2, orCreateStartingLabel, orCreateEndingLabel, str6, i6);
    }

    public final int getCheckCOROUTINE_SUSPENDED() {
        return this.checkCOROUTINE_SUSPENDED;
    }

    public final int getCheckContinuation() {
        return this.checkContinuation;
    }

    public final int getCheckResult() {
        return this.checkResult;
    }

    public final int getLambdaArgumentsUnspilling() {
        return this.lambdaArgumentsUnspilling;
    }

    public final int getTableswitch() {
        return this.tableswitch;
    }

    public final int getUnreachable() {
        return this.unreachable;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/GeneratedCodeMarkers$Companion;", Argument.Delimiters.none, "<init>", "()V", "fillOutMarkersAndCleanUpMethodNode", "Lorg/jetbrains/kotlin/codegen/coroutines/GeneratedCodeMarkers;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "findLinenumberAndRemoveCodeAddedByInliner", Argument.Delimiters.none, "suffix", Argument.Delimiters.none, "markFakeLineNumber", Argument.Delimiters.none, "mv", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "line", "(Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;Ljava/lang/Integer;)V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int findLinenumberAndRemoveCodeAddedByInliner(MethodNode methodNode, String suffix) {
            Object next;
            AbstractInsnNode previous;
            String str;
            List list = methodNode.localVariables;
            list.getClass();
            Iterator it = list.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                str = ((LocalVariableNode) next).name;
                str.getClass();
            } while (!StringsKt.startsWith$default(str, "$i$f$" + suffix, false, 2, (Object) null));
            LocalVariableNode localVariableNode = (LocalVariableNode) next;
            int i = -1;
            if (localVariableNode != null) {
                AbstractInsnNode previous2 = localVariableNode.start;
                AbstractInsnNode next2 = previous2.getNext();
                LineNumberNode lineNumberNode = next2 instanceof LineNumberNode ? (LineNumberNode) next2 : null;
                i = lineNumberNode != null ? lineNumberNode.line : -1;
                AbstractInsnNode previous3 = previous2.getPrevious();
                if (previous3 != null && previous3.getOpcode() == 54 && (previous = previous2.getPrevious().getPrevious()) != null && previous.getOpcode() == 3) {
                    previous2 = previous2.getPrevious().getPrevious();
                    previous2.getClass();
                }
                AbstractInsnNode previous4 = previous2.getPrevious();
                if (previous4 != null && previous4.getOpcode() == 0) {
                    previous2 = previous2.getPrevious();
                    previous2.getClass();
                }
                InsnList insnList = methodNode.instructions;
                insnList.getClass();
                UtilKt.removeAll(insnList, SequencesKt.toList(new InsnSequence(previous2, localVariableNode.end)));
                methodNode.localVariables.remove(localVariableNode);
            }
            return i;
        }

        public final GeneratedCodeMarkers fillOutMarkersAndCleanUpMethodNode(MethodNode methodNode) {
            methodNode.getClass();
            return new GeneratedCodeMarkers(findLinenumberAndRemoveCodeAddedByInliner(methodNode, "checkContinuation"), findLinenumberAndRemoveCodeAddedByInliner(methodNode, "lambdaArgumentsUnspilling"), findLinenumberAndRemoveCodeAddedByInliner(methodNode, "tableswitch"), findLinenumberAndRemoveCodeAddedByInliner(methodNode, "checkResult"), findLinenumberAndRemoveCodeAddedByInliner(methodNode, "checkCOROUTINE_SUSPENDED"), findLinenumberAndRemoveCodeAddedByInliner(methodNode, "unreachable"));
        }

        public final void markFakeLineNumber(InstructionAdapter mv, Integer line) {
            mv.getClass();
            if (line == null) {
                return;
            }
            Label label = new Label();
            mv.mark(label);
            mv.visitLineNumber(line.intValue(), label);
        }

        private Companion() {
        }
    }
}
