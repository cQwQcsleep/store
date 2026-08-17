package org.jetbrains.kotlin.codegen.optimization.fixStack;

import com.intellij.util.SmartList;
import com.intellij.util.containers.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsnsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0010H\u0002J\u0010\u0010+\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0010H\u0002J\u0010\u0010,\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0010H\u0002J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0010H\u0002J\u0006\u00100\u001a\u00020\u0017J\u0006\u00101\u001a\u00020\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R!\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR!\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R!\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0018R\u001d\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR9\u0010\u001d\u001a*\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001f0\u001ej\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001f` ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R-\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u001ej\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010` ¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u001e\u0010&\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0017@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018¨\u00062"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "breakContinueGotoNodes", "Ljava/util/LinkedHashSet;", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "Lkotlin/collections/LinkedHashSet;", "getBreakContinueGotoNodes", "()Ljava/util/LinkedHashSet;", "fakeAlwaysTrueIfeqMarkers", "Ljava/util/ArrayList;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lkotlin/collections/ArrayList;", "getFakeAlwaysTrueIfeqMarkers", "()Ljava/util/ArrayList;", "fakeAlwaysFalseIfeqMarkers", "getFakeAlwaysFalseIfeqMarkers", "isThereAnyTryCatch", Argument.Delimiters.none, "()Z", "saveStackMarkerForRestoreMarker", Argument.Delimiters.none, "getSaveStackMarkerForRestoreMarker", "()Ljava/util/Map;", "restoreStackMarkersForSaveMarker", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lkotlin/collections/HashMap;", "getRestoreStackMarkersForSaveMarker", "()Ljava/util/HashMap;", "openingInlineMethodMarker", "getOpeningInlineMethodMarker", "value", "consistentInlineMarkers", "getConsistentInlineMarkers", "visitFixStackBeforeJump", Argument.Delimiters.none, "insnNode", "visitFakeAlwaysTrueIfeq", "visitFakeAlwaysFalseIfeq", "indexOf", Argument.Delimiters.none, "node", "hasAnyMarkers", "isAnalysisRequired", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FixStackContext {
    private final LinkedHashSet<JumpInsnNode> breakContinueGotoNodes;
    private boolean consistentInlineMarkers;
    private final ArrayList<AbstractInsnNode> fakeAlwaysFalseIfeqMarkers;
    private final ArrayList<AbstractInsnNode> fakeAlwaysTrueIfeqMarkers;
    private final boolean isThereAnyTryCatch;
    private final MethodNode methodNode;
    private final HashMap<AbstractInsnNode, AbstractInsnNode> openingInlineMethodMarker;
    private final HashMap<AbstractInsnNode, List<AbstractInsnNode>> restoreStackMarkersForSaveMarker;
    private final Map<AbstractInsnNode, AbstractInsnNode> saveStackMarkerForRestoreMarker;

    public FixStackContext(MethodNode methodNode) {
        methodNode.getClass();
        this.methodNode = methodNode;
        this.breakContinueGotoNodes = new LinkedHashSet<>();
        this.fakeAlwaysTrueIfeqMarkers = new ArrayList<>();
        this.fakeAlwaysFalseIfeqMarkers = new ArrayList<>();
        Map<AbstractInsnNode, AbstractInsnNode> mapInsertTryCatchBlocksMarkers = AnalyzeTryCatchBlocksKt.insertTryCatchBlocksMarkers(methodNode);
        this.saveStackMarkerForRestoreMarker = mapInsertTryCatchBlocksMarkers;
        this.restoreStackMarkersForSaveMarker = new HashMap<>();
        this.openingInlineMethodMarker = new HashMap<>();
        this.consistentInlineMarkers = true;
        this.isThereAnyTryCatch = true ^ mapInsertTryCatchBlocksMarkers.isEmpty();
        for (Map.Entry<AbstractInsnNode, AbstractInsnNode> entry : mapInsertTryCatchBlocksMarkers.entrySet()) {
            AbstractInsnNode key = entry.getKey();
            AbstractInsnNode value = entry.getValue();
            HashMap<AbstractInsnNode, List<AbstractInsnNode>> map = this.restoreStackMarkersForSaveMarker;
            SmartList smartList = map.get(value);
            if (smartList == null) {
                smartList = new SmartList();
                map.put(value, smartList);
            }
            ((List) smartList).add(key);
        }
        Stack stack = new Stack();
        InsnList insnList = this.methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : new InsnSequence(insnList)) {
            PseudoInsn pseudoInsnOrNull = PseudoInsnsKt.parsePseudoInsnOrNull(abstractInsnNode);
            if (pseudoInsnOrNull == PseudoInsn.FIX_STACK_BEFORE_JUMP) {
                visitFixStackBeforeJump(abstractInsnNode);
            } else if (pseudoInsnOrNull == PseudoInsn.FAKE_ALWAYS_TRUE_IFEQ) {
                visitFakeAlwaysTrueIfeq(abstractInsnNode);
            } else if (pseudoInsnOrNull == PseudoInsn.FAKE_ALWAYS_FALSE_IFEQ) {
                visitFakeAlwaysFalseIfeq(abstractInsnNode);
            } else if (InlineCodegenUtilsKt.isBeforeInlineMarker(abstractInsnNode)) {
                stack.push(abstractInsnNode);
            } else if (InlineCodegenUtilsKt.isAfterInlineMarker(abstractInsnNode)) {
                stack.isEmpty();
                this.openingInlineMethodMarker.put(abstractInsnNode, (AbstractInsnNode) stack.pop());
            }
        }
        if (stack.isEmpty()) {
            return;
        }
        this.consistentInlineMarkers = false;
    }

    private final int indexOf(AbstractInsnNode node) {
        return this.methodNode.instructions.indexOf(node);
    }

    private final void visitFakeAlwaysFalseIfeq(AbstractInsnNode insnNode) {
        insnNode.getNext().getOpcode();
        this.fakeAlwaysFalseIfeqMarkers.add(insnNode);
    }

    private final void visitFakeAlwaysTrueIfeq(AbstractInsnNode insnNode) {
        insnNode.getNext().getOpcode();
        this.fakeAlwaysTrueIfeqMarkers.add(insnNode);
    }

    private final void visitFixStackBeforeJump(AbstractInsnNode insnNode) {
        JumpInsnNode next = insnNode.getNext();
        next.getOpcode();
        this.breakContinueGotoNodes.add(next);
    }

    public final LinkedHashSet<JumpInsnNode> getBreakContinueGotoNodes() {
        return this.breakContinueGotoNodes;
    }

    public final boolean getConsistentInlineMarkers() {
        return this.consistentInlineMarkers;
    }

    public final ArrayList<AbstractInsnNode> getFakeAlwaysFalseIfeqMarkers() {
        return this.fakeAlwaysFalseIfeqMarkers;
    }

    public final ArrayList<AbstractInsnNode> getFakeAlwaysTrueIfeqMarkers() {
        return this.fakeAlwaysTrueIfeqMarkers;
    }

    public final MethodNode getMethodNode() {
        return this.methodNode;
    }

    public final HashMap<AbstractInsnNode, AbstractInsnNode> getOpeningInlineMethodMarker() {
        return this.openingInlineMethodMarker;
    }

    public final HashMap<AbstractInsnNode, List<AbstractInsnNode>> getRestoreStackMarkersForSaveMarker() {
        return this.restoreStackMarkersForSaveMarker;
    }

    public final Map<AbstractInsnNode, AbstractInsnNode> getSaveStackMarkerForRestoreMarker() {
        return this.saveStackMarkerForRestoreMarker;
    }

    public final boolean hasAnyMarkers() {
        return (this.breakContinueGotoNodes.isEmpty() && this.fakeAlwaysTrueIfeqMarkers.isEmpty() && this.fakeAlwaysFalseIfeqMarkers.isEmpty() && !this.isThereAnyTryCatch && this.openingInlineMethodMarker.isEmpty()) ? false : true;
    }

    public final boolean isAnalysisRequired() {
        return (this.breakContinueGotoNodes.isEmpty() && !this.isThereAnyTryCatch && this.openingInlineMethodMarker.isEmpty()) ? false : true;
    }

    /* JADX INFO: renamed from: isThereAnyTryCatch, reason: from getter */
    public final boolean getIsThereAnyTryCatch() {
        return this.isThereAnyTryCatch;
    }
}
