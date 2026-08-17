package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0004 !\"#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J&\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0002J&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\rH\u0002J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0018\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\rH\u0002J\u0014\u0010\u001c\u001a\u00020\u001d*\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0018\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\rH\u0002J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "parseMethodOrNull", "Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$MethodContext;", "parseCall", "Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$CallContext;", "start", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "iter", Argument.Delimiters.none, "parseArg", "Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$ArgContext;", "collectStartToEnd", "methodContext", "callContext", "argContext", "collectLvtEntryInstructions", "collectSuspensionPoints", "transformMethod", "transformCall", "isUnsafeToMove", Argument.Delimiters.none, "moveInplaceArgumentsFromStoresToLoads", "stripMarkers", "MethodContext", "CallContext", "ArgContext", "ParseErrorException", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InplaceArgumentsMethodTransformer extends MethodTransformer {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$ArgContext;", Argument.Delimiters.none, "argStartMarker", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "argEndMarker", "calls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$CallContext;", "storeInsn", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Ljava/util/List;Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;)V", "getArgStartMarker", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getArgEndMarker", "getCalls", "()Ljava/util/List;", "getStoreInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "loadOpcode", Argument.Delimiters.none, "getLoadOpcode", "()I", "varIndex", "getVarIndex", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ArgContext {
        private final AbstractInsnNode argEndMarker;
        private final AbstractInsnNode argStartMarker;
        private final List<CallContext> calls;
        private final int loadOpcode;
        private final VarInsnNode storeInsn;
        private final int varIndex;

        public ArgContext(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2, List<CallContext> list, VarInsnNode varInsnNode) {
            abstractInsnNode.getClass();
            abstractInsnNode2.getClass();
            list.getClass();
            this.argStartMarker = abstractInsnNode;
            this.argEndMarker = abstractInsnNode2;
            this.calls = list;
            this.storeInsn = varInsnNode;
            this.loadOpcode = varInsnNode != null ? varInsnNode.getOpcode() - 33 : -1;
            this.varIndex = varInsnNode != null ? varInsnNode.var : -1;
        }

        public final AbstractInsnNode getArgEndMarker() {
            return this.argEndMarker;
        }

        public final AbstractInsnNode getArgStartMarker() {
            return this.argStartMarker;
        }

        public final List<CallContext> getCalls() {
            return this.calls;
        }

        public final int getLoadOpcode() {
            return this.loadOpcode;
        }

        public final VarInsnNode getStoreInsn() {
            return this.storeInsn;
        }

        public final int getVarIndex() {
            return this.varIndex;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$CallContext;", Argument.Delimiters.none, "callStartMarker", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "callEndMarker", "args", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$ArgContext;", "calls", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Ljava/util/List;Ljava/util/List;)V", "getCallStartMarker", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getCallEndMarker", "getArgs", "()Ljava/util/List;", "getCalls", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CallContext {
        private final List<ArgContext> args;
        private final AbstractInsnNode callEndMarker;
        private final AbstractInsnNode callStartMarker;
        private final List<CallContext> calls;

        public CallContext(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2, List<ArgContext> list, List<CallContext> list2) {
            abstractInsnNode.getClass();
            abstractInsnNode2.getClass();
            list.getClass();
            list2.getClass();
            this.callStartMarker = abstractInsnNode;
            this.callEndMarker = abstractInsnNode2;
            this.args = list;
            this.calls = list2;
        }

        public final List<ArgContext> getArgs() {
            return this.args;
        }

        public final AbstractInsnNode getCallEndMarker() {
            return this.callEndMarker;
        }

        public final AbstractInsnNode getCallStartMarker() {
            return this.callStartMarker;
        }

        public final List<CallContext> getCalls() {
            return this.calls;
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR-\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R-\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00140\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0014`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R-\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00060\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0006`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R!\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u001a0\u0019j\b\u0012\u0004\u0012\u00020\u001a`\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$MethodContext;", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "calls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$CallContext;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Ljava/util/List;)V", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getCalls", "()Ljava/util/List;", "startArgToEndArg", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lkotlin/collections/HashMap;", "getStartArgToEndArg", "()Ljava/util/HashMap;", "lvtEntryForInstruction", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "getLvtEntryForInstruction", "varInstructionMoved", "getVarInstructionMoved", "suspensionJumpLabels", "Ljava/util/HashSet;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "Lkotlin/collections/HashSet;", "getSuspensionJumpLabels", "()Ljava/util/HashSet;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MethodContext {
        private final List<CallContext> calls;
        private final HashMap<AbstractInsnNode, LocalVariableNode> lvtEntryForInstruction;
        private final MethodNode methodNode;
        private final HashMap<AbstractInsnNode, AbstractInsnNode> startArgToEndArg;
        private final HashSet<LabelNode> suspensionJumpLabels;
        private final HashMap<AbstractInsnNode, CallContext> varInstructionMoved;

        public MethodContext(MethodNode methodNode, List<CallContext> list) {
            methodNode.getClass();
            list.getClass();
            this.methodNode = methodNode;
            this.calls = list;
            this.startArgToEndArg = new HashMap<>();
            this.lvtEntryForInstruction = new HashMap<>();
            this.varInstructionMoved = new HashMap<>();
            this.suspensionJumpLabels = new HashSet<>();
        }

        public final List<CallContext> getCalls() {
            return this.calls;
        }

        public final HashMap<AbstractInsnNode, LocalVariableNode> getLvtEntryForInstruction() {
            return this.lvtEntryForInstruction;
        }

        public final MethodNode getMethodNode() {
            return this.methodNode;
        }

        public final HashMap<AbstractInsnNode, AbstractInsnNode> getStartArgToEndArg() {
            return this.startArgToEndArg;
        }

        public final HashSet<LabelNode> getSuspensionJumpLabels() {
            return this.suspensionJumpLabels;
        }

        public final HashMap<AbstractInsnNode, CallContext> getVarInstructionMoved() {
            return this.varInstructionMoved;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InplaceArgumentsMethodTransformer$ParseErrorException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "<init>", "()V", "fillInStackTrace", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ParseErrorException extends RuntimeException {
        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }
    }

    private final void collectLvtEntryInstructions(MethodContext methodContext) {
        int opcode;
        InsnList insnList = methodContext.getMethodNode().instructions;
        VarInsnNode[] array = insnList.toArray();
        for (LocalVariableNode localVariableNode : methodContext.getMethodNode().localVariables) {
            int iIndexOf = insnList.indexOf(localVariableNode.end);
            for (int iIndexOf2 = insnList.indexOf(localVariableNode.start); iIndexOf2 < iIndexOf; iIndexOf2++) {
                VarInsnNode varInsnNode = array[iIndexOf2];
                int opcode2 = varInsnNode.getOpcode();
                if ((21 > opcode2 || opcode2 >= 26) && (54 > (opcode = varInsnNode.getOpcode()) || opcode >= 59)) {
                    if (varInsnNode.getOpcode() == 132 && ((IincInsnNode) varInsnNode).var == localVariableNode.index) {
                        methodContext.getLvtEntryForInstruction().put(varInsnNode, localVariableNode);
                    }
                } else if (varInsnNode.var == localVariableNode.index) {
                    methodContext.getLvtEntryForInstruction().put(varInsnNode, localVariableNode);
                }
            }
        }
    }

    private final void collectStartToEnd(MethodContext methodContext, CallContext callContext) {
        Iterator<ArgContext> it = callContext.getArgs().iterator();
        while (it.hasNext()) {
            collectStartToEnd(methodContext, it.next());
        }
        Iterator<CallContext> it2 = callContext.getCalls().iterator();
        while (it2.hasNext()) {
            collectStartToEnd(methodContext, it2.next());
        }
    }

    private final void collectSuspensionPoints(MethodContext methodContext) {
        TableSwitchInsnNode first = methodContext.getMethodNode().instructions.getFirst();
        do {
            first.getClass();
            if (first.getOpcode() == 184 && (first instanceof MethodInsnNode)) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) first;
                if (Intrinsics.areEqual(methodInsnNode.owner, "kotlin/coroutines/intrinsics/IntrinsicsKt") && Intrinsics.areEqual(methodInsnNode.name, "getCOROUTINE_SUSPENDED") && Intrinsics.areEqual(methodInsnNode.desc, "()Ljava/lang/Object;")) {
                    while (first != null) {
                        if (first.getOpcode() == 170 && first.getPrevious().getOpcode() == 180) {
                            FieldInsnNode previous = first.getPrevious();
                            previous.getClass();
                            FieldInsnNode fieldInsnNode = previous;
                            if (Intrinsics.areEqual(fieldInsnNode.name, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME) && Intrinsics.areEqual(fieldInsnNode.desc, "I")) {
                                TableSwitchInsnNode tableSwitchInsnNode = first;
                                methodContext.getSuspensionJumpLabels().addAll(tableSwitchInsnNode.labels);
                                methodContext.getSuspensionJumpLabels().add(tableSwitchInsnNode.dflt);
                                return;
                            }
                            first = first.getNext();
                        } else {
                            first = first.getNext();
                        }
                    }
                    return;
                }
            }
            first = first.getNext();
        } while (first != null);
    }

    private final boolean isUnsafeToMove(ArgContext argContext, MethodContext methodContext) {
        InsnSequence<JumpInsnNode> insnSequence = new InsnSequence(argContext.getArgStartMarker(), argContext.getArgEndMarker());
        HashSet hashSet = new HashSet();
        for (Object obj : insnSequence) {
            if (((AbstractInsnNode) obj) instanceof LabelNode) {
                hashSet.add(obj);
            }
        }
        for (JumpInsnNode jumpInsnNode : insnSequence) {
            if (UtilKt.isStoreOperation(jumpInsnNode) || CollectionsKt.contains(methodContext.getSuspensionJumpLabels(), jumpInsnNode)) {
                return true;
            }
            if (jumpInsnNode.getOpcode() == 167 && !hashSet.contains(jumpInsnNode.label)) {
                return true;
            }
        }
        return false;
    }

    private final void moveInplaceArgumentsFromStoresToLoads(MethodContext methodContext, CallContext callContext) {
        int opcode;
        InsnList insnList = methodContext.getMethodNode().instructions;
        List<ArgContext> args = callContext.getArgs();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(args, 10)), 16));
        for (Object obj : args) {
            linkedHashMap.put(Integer.valueOf(((ArgContext) obj).getVarIndex()), obj);
        }
        AbstractInsnNode callStartMarker = callContext.getCallStartMarker();
        int i = 0;
        while (!Intrinsics.areEqual(callStartMarker, callContext.getCallEndMarker())) {
            if (InlineArgumentsInPlaceKt.isInplaceArgumentStartMarker(callStartMarker)) {
                AbstractInsnNode abstractInsnNode = methodContext.getStartArgToEndArg().get(callStartMarker);
                abstractInsnNode.getClass();
                callStartMarker = abstractInsnNode;
            } else {
                int opcode2 = callStartMarker.getOpcode();
                if (21 > opcode2 || opcode2 >= 26) {
                    callStartMarker = callStartMarker.getNext();
                    callStartMarker.getClass();
                } else {
                    VarInsnNode varInsnNode = (VarInsnNode) callStartMarker;
                    int i2 = varInsnNode.var;
                    ArgContext argContext = (ArgContext) linkedHashMap.get(Integer.valueOf(i2));
                    if (argContext == null || argContext.getLoadOpcode() != varInsnNode.getOpcode()) {
                        callStartMarker = varInsnNode.getNext();
                        callStartMarker.getClass();
                    } else {
                        AbstractInsnNode next = argContext.getArgStartMarker().getNext();
                        while (!Intrinsics.areEqual(next, argContext.getArgEndMarker())) {
                            int opcode3 = next.getOpcode();
                            if ((21 <= opcode3 && opcode3 < 26) || ((54 <= (opcode = next.getOpcode()) && opcode < 59) || next.getOpcode() == 132)) {
                                methodContext.getVarInstructionMoved().put(next, callContext);
                            }
                            AbstractInsnNode next2 = next.getNext();
                            insnList.remove(next);
                            insnList.insertBefore(varInsnNode, next);
                            next = next2;
                        }
                        insnList.remove(argContext.getStoreInsn());
                        AbstractInsnNode next3 = varInsnNode.getNext();
                        next3.getClass();
                        insnList.remove(varInsnNode);
                        while (next3.getOpcode() == varInsnNode.getOpcode()) {
                            VarInsnNode varInsnNode2 = (VarInsnNode) next3;
                            if (varInsnNode2.var != i2) {
                                break;
                            }
                            if (varInsnNode2.getOpcode() == 22 || varInsnNode2.getOpcode() == 24) {
                                insnList.insertBefore(next3, new InsnNode(92));
                            } else {
                                insnList.insertBefore(next3, new InsnNode(89));
                            }
                            AbstractInsnNode next4 = varInsnNode2.getNext();
                            insnList.remove(next3);
                            next4.getClass();
                            next3 = next4;
                        }
                        insnList.remove(argContext.getArgStartMarker());
                        insnList.remove(argContext.getArgEndMarker());
                        i++;
                        if (i >= callContext.getArgs().size()) {
                            break;
                        } else {
                            callStartMarker = next3;
                        }
                    }
                }
            }
        }
        insnList.remove(callContext.getCallStartMarker());
        insnList.remove(callContext.getCallEndMarker());
    }

    private final ArgContext parseArg(MethodNode methodNode, AbstractInsnNode start, ListIterator<? extends AbstractInsnNode> iter) {
        VarInsnNode varInsnNode;
        int opcode;
        ArrayList arrayList = new ArrayList();
        while (iter.hasNext()) {
            AbstractInsnNode next = iter.next();
            if (InlineArgumentsInPlaceKt.isInplaceCallStartMarker(next)) {
                arrayList.add(parseCall(methodNode, next, iter));
            } else {
                if (InlineArgumentsInPlaceKt.isInplaceArgumentEndMarker(next)) {
                    VarInsnNode next2 = next.getNext();
                    if ((next2 instanceof VarInsnNode) && 54 <= (opcode = (varInsnNode = next2).getOpcode()) && opcode < 59) {
                        iter.next();
                        return new ArgContext(start, next, arrayList, varInsnNode);
                    }
                    AbstractInsnNode previous = next.getPrevious();
                    previous.getClass();
                    if (InlineArgumentsInPlaceKt.isInplaceArgumentStartMarker(previous)) {
                        return new ArgContext(start, next, arrayList, null);
                    }
                    throw new ParseErrorException();
                }
                if (InlineArgumentsInPlaceKt.isInplaceCallEndMarker(next) || InlineArgumentsInPlaceKt.isInplaceArgumentStartMarker(next)) {
                    throw new ParseErrorException();
                }
            }
        }
        throw new ParseErrorException();
    }

    private final CallContext parseCall(MethodNode methodNode, AbstractInsnNode start, ListIterator<? extends AbstractInsnNode> iter) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (iter.hasNext()) {
            AbstractInsnNode next = iter.next();
            if (InlineArgumentsInPlaceKt.isInplaceCallStartMarker(next)) {
                arrayList2.add(parseCall(methodNode, next, iter));
            } else {
                if (InlineArgumentsInPlaceKt.isInplaceCallEndMarker(next)) {
                    return new CallContext(start, next, arrayList, arrayList2);
                }
                if (InlineArgumentsInPlaceKt.isInplaceArgumentStartMarker(next)) {
                    arrayList.add(parseArg(methodNode, next, iter));
                } else if (InlineArgumentsInPlaceKt.isInplaceArgumentEndMarker(next)) {
                    throw new ParseErrorException();
                }
            }
        }
        throw new ParseErrorException();
    }

    private final MethodContext parseMethodOrNull(MethodNode methodNode) {
        ListIterator<? extends AbstractInsnNode> it = methodNode.instructions.iterator();
        it.getClass();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            try {
                AbstractInsnNode next = it.next();
                next.getClass();
                if (InlineArgumentsInPlaceKt.isInplaceCallStartMarker(next)) {
                    arrayList.add(parseCall(methodNode, next, it));
                } else if (InlineArgumentsInPlaceKt.isInplaceCallEndMarker(next) || InlineArgumentsInPlaceKt.isInplaceArgumentStartMarker(next) || InlineArgumentsInPlaceKt.isInplaceArgumentEndMarker(next)) {
                    throw new ParseErrorException();
                }
            } catch (ParseErrorException unused) {
                return null;
            }
        }
        return new MethodContext(methodNode, arrayList);
    }

    private final void stripMarkers(MethodNode methodNode) {
        AbstractInsnNode first = methodNode.instructions.getFirst();
        while (first != null) {
            if (InlineArgumentsInPlaceKt.isInplaceCallStartMarker(first) || InlineArgumentsInPlaceKt.isInplaceCallEndMarker(first) || InlineArgumentsInPlaceKt.isInplaceArgumentStartMarker(first) || InlineArgumentsInPlaceKt.isInplaceArgumentEndMarker(first)) {
                AbstractInsnNode next = first.getNext();
                methodNode.instructions.remove(first);
                first = next;
            } else {
                first = first.getNext();
            }
        }
    }

    private final void transformCall(MethodContext methodContext, CallContext callContext) {
        Iterator<ArgContext> it = callContext.getArgs().iterator();
        while (it.hasNext()) {
            Iterator<CallContext> it2 = it.next().getCalls().iterator();
            while (it2.hasNext()) {
                transformCall(methodContext, it2.next());
            }
        }
        Iterator<CallContext> it3 = callContext.getCalls().iterator();
        while (it3.hasNext()) {
            transformCall(methodContext, it3.next());
        }
        List<ArgContext> args = callContext.getArgs();
        if (!(args instanceof Collection) || !args.isEmpty()) {
            Iterator<T> it4 = args.iterator();
            while (it4.hasNext()) {
                if (isUnsafeToMove((ArgContext) it4.next(), methodContext)) {
                    InsnList insnList = methodContext.getMethodNode().instructions;
                    for (ArgContext argContext : callContext.getArgs()) {
                        insnList.remove(argContext.getArgStartMarker());
                        insnList.remove(argContext.getArgEndMarker());
                    }
                    insnList.remove(callContext.getCallStartMarker());
                    insnList.remove(callContext.getCallEndMarker());
                    return;
                }
            }
        }
        moveInplaceArgumentsFromStoresToLoads(methodContext, callContext);
    }

    private final void transformMethod(MethodContext methodContext) {
        Iterator<CallContext> it = methodContext.getCalls().iterator();
        while (it.hasNext()) {
            transformCall(methodContext, it.next());
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        MethodContext methodOrNull = parseMethodOrNull(methodNode);
        if (methodOrNull != null) {
            if (methodOrNull.getCalls().isEmpty()) {
                return;
            }
            collectStartToEnd(methodOrNull);
            collectLvtEntryInstructions(methodOrNull);
            collectSuspensionPoints(methodOrNull);
            transformMethod(methodOrNull);
            InplaceArgumentsMethodTransformerKt.fixupLVT(methodNode);
            UtilKt.removeUnusedLocalVariables(methodNode);
            UtilKt.updateMaxStack(methodNode);
        }
        stripMarkers(methodNode);
    }

    private final void collectStartToEnd(MethodContext methodContext) {
        Iterator<CallContext> it = methodContext.getCalls().iterator();
        while (it.hasNext()) {
            collectStartToEnd(methodContext, it.next());
        }
    }

    private final void collectStartToEnd(MethodContext methodContext, ArgContext argContext) {
        methodContext.getStartArgToEndArg().put(argContext.getArgStartMarker(), argContext.getArgEndMarker());
        Iterator<CallContext> it = argContext.getCalls().iterator();
        while (it.hasNext()) {
            collectStartToEnd(methodContext, it.next());
        }
    }
}
