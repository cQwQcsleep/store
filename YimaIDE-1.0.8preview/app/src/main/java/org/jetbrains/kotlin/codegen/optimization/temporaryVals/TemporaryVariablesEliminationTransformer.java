package org.jetbrains.kotlin.codegen.optimization.temporaryVals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.nullCheck.RedundantNullCheckMethodTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\"B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u0016*\u00020\u00182\n\u0010\u001d\u001a\u00020\u001e\"\u00020\u001fH\u0002J\u0014\u0010 \u001a\u00020\u0016*\u00020\u00182\u0006\u0010!\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryVariablesEliminationTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "temporaryValsAnalyzer", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryValsAnalyzer;", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "simplifyTrivialInstructions", "processLabels", "cfg", "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryVariablesEliminationTransformer$ControlFlowGraph;", "optimizeTemporaryVals", "temporaryVals", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryVal;", "simplifyKnownSafeCallPatterns", "isRewritableSafeCallPart", Argument.Delimiters.none, "branchInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "rewriteSafeCallPart", "insnList", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "matchOpcodes", "opcodes", Argument.Delimiters.none, Argument.Delimiters.none, "isIntervening", "context", "ControlFlowGraph", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TemporaryVariablesEliminationTransformer extends MethodTransformer {
    private final TemporaryValsAnalyzer temporaryValsAnalyzer = new TemporaryValsAnalyzer();

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\fJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\nJ\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010\u0011\u001a\u00020\nJ\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R6\u0010\b\u001a*\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\tj\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryVariablesEliminationTransformer$ControlFlowGraph;", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "nonTrivialPredecessors", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lkotlin/collections/HashMap;", "reset", Argument.Delimiters.none, "addNonTrivialPredecessor", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "pred", "hasNonTrivialPredecessors", Argument.Delimiters.none, "getAllPredecessors", Argument.Delimiters.none, "hasSinglePredecessor", "expectedPredecessor", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ControlFlowGraph {
        private final MethodNode methodNode;
        private final HashMap<LabelNode, List<AbstractInsnNode>> nonTrivialPredecessors;

        public ControlFlowGraph(MethodNode methodNode) {
            methodNode.getClass();
            this.methodNode = methodNode;
            this.nonTrivialPredecessors = new HashMap<>();
        }

        public final void addNonTrivialPredecessor(LabelNode label, AbstractInsnNode pred) {
            label.getClass();
            pred.getClass();
            HashMap<LabelNode, List<AbstractInsnNode>> map = this.nonTrivialPredecessors;
            SmartList smartList = map.get(label);
            if (smartList == null) {
                smartList = new SmartList();
                map.put(label, smartList);
            }
            ((List) smartList).add(pred);
        }

        public final List<AbstractInsnNode> getAllPredecessors(LabelNode label) {
            int opcode;
            label.getClass();
            ArrayList arrayList = new ArrayList();
            AbstractInsnNode previous = label.getPrevious();
            if (previous.getOpcode() != 167 && ((172 > (opcode = previous.getOpcode()) || opcode >= 178) && previous.getOpcode() != 191)) {
                arrayList.add(previous);
            }
            List<AbstractInsnNode> listEmptyList = this.nonTrivialPredecessors.get(label);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            arrayList.addAll(listEmptyList);
            return arrayList;
        }

        public final MethodNode getMethodNode() {
            return this.methodNode;
        }

        public final boolean hasNonTrivialPredecessors(LabelNode label) {
            label.getClass();
            return this.nonTrivialPredecessors.containsKey(label);
        }

        public final boolean hasSinglePredecessor(LabelNode label, AbstractInsnNode expectedPredecessor) {
            int opcode;
            label.getClass();
            expectedPredecessor.getClass();
            AbstractInsnNode previous = label.getPrevious();
            if (previous.getOpcode() == 167 || ((172 <= (opcode = previous.getOpcode()) && opcode < 178) || previous.getOpcode() == 191)) {
                previous = null;
            } else if (!Intrinsics.areEqual(previous, expectedPredecessor)) {
                return false;
            }
            List<AbstractInsnNode> list = this.nonTrivialPredecessors.get(label);
            if (list == null) {
                return previous != null;
            }
            if (list.size() > 1) {
                return false;
            }
            if (list.size() == 0) {
                return Intrinsics.areEqual(previous, expectedPredecessor);
            }
            return previous == null && Intrinsics.areEqual(list.get(0), expectedPredecessor);
        }

        public final void reset() {
            this.nonTrivialPredecessors.clear();
        }
    }

    private final boolean isIntervening(AbstractInsnNode abstractInsnNode, ControlFlowGraph controlFlowGraph) {
        int nodeType = UtilKt.getNodeType(abstractInsnNode);
        if (nodeType == 0) {
            return abstractInsnNode.getOpcode() != 0;
        }
        if (nodeType != 8) {
            return (nodeType == 14 || nodeType == 15) ? false : true;
        }
        abstractInsnNode.getClass();
        return controlFlowGraph.hasNonTrivialPredecessors((LabelNode) abstractInsnNode);
    }

    private final boolean isRewritableSafeCallPart(AbstractInsnNode branchInsn) {
        VarInsnNode previous = branchInsn.getPrevious();
        if (previous != null && matchOpcodes(previous, 25, 198)) {
            VarInsnNode varInsnNode = previous;
            JumpInsnNode next = varInsnNode.getNext();
            next.getClass();
            VarInsnNode next2 = next.getNext();
            if (next2.getOpcode() == 25) {
                VarInsnNode varInsnNode2 = next2;
                if (varInsnNode2.var == varInsnNode.var) {
                    return true;
                }
                VarInsnNode next3 = varInsnNode2.getNext();
                if (next3.getOpcode() == 25 && next3.var == varInsnNode.var) {
                    return true;
                }
            } else if (matchOpcodes(next2, 178, 25)) {
                FieldInsnNode fieldInsnNode = (FieldInsnNode) next2;
                if (Type.getType(fieldInsnNode.desc).getSize() != 1) {
                    return false;
                }
                VarInsnNode next4 = fieldInsnNode.getNext();
                next4.getClass();
                if (next4.var == varInsnNode.var) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean matchOpcodes(AbstractInsnNode abstractInsnNode, int... iArr) {
        for (int i : iArr) {
            if (abstractInsnNode.getOpcode() != i || (abstractInsnNode = abstractInsnNode.getNext()) == null) {
                return false;
            }
        }
        return true;
    }

    private final void optimizeTemporaryVals(ControlFlowGraph cfg, List<TemporaryVal> temporaryVals) {
        InsnList insnList = cfg.getMethodNode().instructions;
        int iMax = 0;
        for (TemporaryVal temporaryVal : temporaryVals) {
            if (temporaryVal.getLoadInsns().isEmpty()) {
                int opcode = temporaryVal.getStoreInsn().getOpcode();
                insnList.insertBefore(temporaryVal.getStoreInsn(), new InsnNode((opcode == 54 || opcode == 56 || opcode == 58) ? 87 : 88));
                insnList.remove(temporaryVal.getStoreInsn());
            } else {
                if (temporaryVal.getLoadInsns().size() == 1) {
                    VarInsnNode storeInsn = temporaryVal.getStoreInsn();
                    VarInsnNode varInsnNode = temporaryVal.getLoadInsns().get(0);
                    if (!Intrinsics.areEqual(storeInsn.getNext(), varInsnNode)) {
                        AbstractInsnNode next = storeInsn.getNext();
                        next.getClass();
                        Iterator it = new InsnSequence(next, varInsnNode).iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (isIntervening((AbstractInsnNode) it.next(), cfg)) {
                                    if (matchOpcodes(storeInsn, 58, 25, 25)) {
                                        VarInsnNode next2 = storeInsn.getNext();
                                        next2.getClass();
                                        VarInsnNode varInsnNode2 = next2;
                                        VarInsnNode next3 = varInsnNode2.getNext();
                                        next3.getClass();
                                        if (Intrinsics.areEqual(next3, varInsnNode)) {
                                            insnList.remove(storeInsn);
                                            insnList.remove(varInsnNode);
                                            insnList.insert(varInsnNode2, new InsnNode(95));
                                            break;
                                        }
                                    }
                                    if (!matchOpcodes(storeInsn, 58, 178, 25)) {
                                        break;
                                    }
                                    FieldInsnNode next4 = storeInsn.getNext();
                                    next4.getClass();
                                    FieldInsnNode fieldInsnNode = next4;
                                    VarInsnNode next5 = fieldInsnNode.getNext();
                                    next5.getClass();
                                    if (!Intrinsics.areEqual(next5, varInsnNode) || Type.getType(fieldInsnNode.desc).getSize() != 1) {
                                        break;
                                    }
                                    insnList.remove(storeInsn);
                                    insnList.remove(varInsnNode);
                                    insnList.insert(fieldInsnNode, new InsnNode(95));
                                    break;
                                }
                            }
                        }
                    }
                    insnList.remove(storeInsn);
                    insnList.remove(varInsnNode);
                    break;
                }
                if (temporaryVal.getLoadInsns().size() == 2) {
                    VarInsnNode storeInsn2 = temporaryVal.getStoreInsn();
                    if (matchOpcodes(storeInsn2, 58, 25, 18, 184, 25)) {
                        VarInsnNode next6 = storeInsn2.getNext();
                        LdcInsnNode next7 = next6.getNext();
                        AbstractInsnNode next8 = next7.getNext();
                        VarInsnNode next9 = next8.getNext();
                        if (next6.var == temporaryVal.getIndex() && (next7.cst instanceof String) && (RedundantNullCheckMethodTransformerKt.isCheckExpressionValueIsNotNull(next8) || RedundantNullCheckMethodTransformerKt.isCheckNotNullWithMessage(next8))) {
                            next9.getClass();
                            if (next9.var == temporaryVal.getIndex()) {
                                insnList.remove(storeInsn2);
                                insnList.remove(next6);
                                insnList.insertBefore(next7, new InsnNode(89));
                                insnList.remove(next9);
                                iMax = Math.max(iMax, 1);
                            }
                        }
                    }
                }
            }
        }
        cfg.getMethodNode().maxStack += iMax;
    }

    private final void processLabels(ControlFlowGraph cfg) {
        cfg.reset();
        MethodNode methodNode = cfg.getMethodNode();
        InsnList insnList = methodNode.instructions;
        HashSet hashSet = new HashSet();
        AbstractInsnNode first = insnList.getFirst();
        if (first instanceof LabelNode) {
            hashSet.add(first);
        }
        AbstractInsnNode last = insnList.getLast();
        if (last instanceof LabelNode) {
            hashSet.add(last);
        }
        ListIterator it = insnList.iterator();
        it.getClass();
        while (it.hasNext()) {
            JumpInsnNode jumpInsnNode = (AbstractInsnNode) it.next();
            jumpInsnNode.getClass();
            int nodeType = UtilKt.getNodeType(jumpInsnNode);
            if (nodeType == 7) {
                LabelNode labelNode = jumpInsnNode.label;
                labelNode.getClass();
                processLabels$addCfgEdgeToLabel(hashSet, cfg, jumpInsnNode, labelNode);
            } else if (nodeType == 15) {
                hashSet.add(((LineNumberNode) jumpInsnNode).start);
            } else if (nodeType == 11) {
                TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode) jumpInsnNode;
                LabelNode labelNode2 = tableSwitchInsnNode.dflt;
                labelNode2.getClass();
                processLabels$addCfgEdgeToLabel(hashSet, cfg, jumpInsnNode, labelNode2);
                List list = tableSwitchInsnNode.labels;
                list.getClass();
                processLabels$addCfgEdgesToLabels(hashSet, cfg, jumpInsnNode, list);
            } else if (nodeType == 12) {
                LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode) jumpInsnNode;
                LabelNode labelNode3 = lookupSwitchInsnNode.dflt;
                labelNode3.getClass();
                processLabels$addCfgEdgeToLabel(hashSet, cfg, jumpInsnNode, labelNode3);
                List list2 = lookupSwitchInsnNode.labels;
                list2.getClass();
                processLabels$addCfgEdgesToLabels(hashSet, cfg, jumpInsnNode, list2);
            }
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            hashSet.add(localVariableNode.start);
            hashSet.add(localVariableNode.end);
        }
        for (TryCatchBlockNode tryCatchBlockNode : methodNode.tryCatchBlocks) {
            hashSet.add(tryCatchBlockNode.start);
            hashSet.add(tryCatchBlockNode.end);
            LabelNode labelNode4 = tryCatchBlockNode.start;
            labelNode4.getClass();
            LabelNode labelNode5 = tryCatchBlockNode.handler;
            labelNode5.getClass();
            processLabels$addCfgEdgeToLabel(hashSet, cfg, labelNode4, labelNode5);
        }
        AbstractInsnNode first2 = insnList.getFirst();
        while (first2 != null) {
            if (!(first2 instanceof LabelNode) || hashSet.contains(first2)) {
                first2 = first2.getNext();
            } else {
                AbstractInsnNode next = ((LabelNode) first2).getNext();
                insnList.remove(first2);
                first2 = next;
            }
        }
    }

    private static final void processLabels$addCfgEdgeToLabel(HashSet<LabelNode> hashSet, ControlFlowGraph controlFlowGraph, AbstractInsnNode abstractInsnNode, LabelNode labelNode) {
        hashSet.add(labelNode);
        controlFlowGraph.addNonTrivialPredecessor(labelNode, abstractInsnNode);
    }

    private static final void processLabels$addCfgEdgesToLabels(HashSet<LabelNode> hashSet, ControlFlowGraph controlFlowGraph, AbstractInsnNode abstractInsnNode, Collection<? extends LabelNode> collection) {
        hashSet.addAll(collection);
        Iterator<? extends LabelNode> it = collection.iterator();
        while (it.hasNext()) {
            controlFlowGraph.addNonTrivialPredecessor(it.next(), abstractInsnNode);
        }
    }

    private final void rewriteSafeCallPart(AbstractInsnNode branchInsn, InsnList insnList) {
        VarInsnNode previous = branchInsn.getPrevious();
        previous.getClass();
        VarInsnNode varInsnNode = previous;
        JumpInsnNode next = varInsnNode.getNext();
        next.getClass();
        JumpInsnNode jumpInsnNode = next;
        VarInsnNode next2 = jumpInsnNode.getNext();
        if (next2.getOpcode() != 25) {
            AbstractInsnNode next3 = next2.getNext();
            insnList.insertBefore(jumpInsnNode, new InsnNode(89));
            insnList.remove(next3);
            insnList.insert(next2, new InsnNode(95));
            return;
        }
        VarInsnNode varInsnNode2 = next2;
        if (varInsnNode2.var == varInsnNode.var) {
            insnList.insertBefore(jumpInsnNode, new InsnNode(89));
            insnList.remove(varInsnNode2);
            return;
        }
        VarInsnNode next4 = varInsnNode2.getNext();
        next4.getClass();
        insnList.insertBefore(jumpInsnNode, new InsnNode(89));
        insnList.remove(next4);
        insnList.insert(varInsnNode2, new InsnNode(95));
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0142 A[LOOP:1: B:42:0x013c->B:44:0x0142, LOOP_END] */
    private final void simplifyKnownSafeCallPatterns(ControlFlowGraph cfg) {
        Iterator<T> it;
        VarInsnNode next;
        InsnList insnList = cfg.getMethodNode().instructions;
        VarInsnNode[] array = insnList.toArray();
        array.getClass();
        int iMax = 0;
        for (VarInsnNode varInsnNode : array) {
            varInsnNode.getClass();
            if (matchOpcodes(varInsnNode, 25, 199)) {
                VarInsnNode varInsnNode2 = varInsnNode;
                JumpInsnNode next2 = varInsnNode2.getNext();
                next2.getClass();
                JumpInsnNode jumpInsnNode = next2;
                LabelNode labelNode = jumpInsnNode.label;
                labelNode.getClass();
                if (cfg.hasSinglePredecessor(labelNode, jumpInsnNode) && (next = labelNode.getNext()) != null) {
                    if (next.getOpcode() == 25) {
                        VarInsnNode varInsnNode3 = next;
                        if (varInsnNode3.var == varInsnNode2.var) {
                            insnList.insertBefore(jumpInsnNode, new InsnNode(89));
                            insnList.insert(jumpInsnNode, new InsnNode(87));
                            insnList.remove(varInsnNode3);
                            iMax = Math.max(iMax, 1);
                        } else {
                            VarInsnNode next3 = varInsnNode3.getNext();
                            if (next3.getOpcode() == 25) {
                                VarInsnNode varInsnNode4 = next3;
                                if (varInsnNode4.var == varInsnNode2.var) {
                                    insnList.insertBefore(jumpInsnNode, new InsnNode(89));
                                    insnList.insert(jumpInsnNode, new InsnNode(87));
                                    insnList.insert(varInsnNode3, new InsnNode(95));
                                    insnList.remove(varInsnNode4);
                                    iMax = Math.max(iMax, 1);
                                }
                            }
                        }
                    } else if (matchOpcodes(next, 178, 25)) {
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) next;
                        VarInsnNode next4 = fieldInsnNode.getNext();
                        next4.getClass();
                        VarInsnNode varInsnNode5 = next4;
                        if (Type.getType(fieldInsnNode.desc).getSize() == 1 && varInsnNode5.var == varInsnNode2.var) {
                            insnList.insertBefore(jumpInsnNode, new InsnNode(89));
                            insnList.insert(jumpInsnNode, new InsnNode(87));
                            insnList.insert(fieldInsnNode, new InsnNode(95));
                            insnList.remove(varInsnNode5);
                            iMax = Math.max(iMax, 1);
                        }
                    }
                }
            } else if (matchOpcodes(varInsnNode, 25, 198)) {
                JumpInsnNode next5 = varInsnNode.getNext();
                next5.getClass();
                LabelNode labelNode2 = next5.label;
                labelNode2.getClass();
                List<AbstractInsnNode> allPredecessors = cfg.getAllPredecessors(labelNode2);
                if ((allPredecessors instanceof Collection) && allPredecessors.isEmpty()) {
                    it = allPredecessors.iterator();
                    while (it.hasNext()) {
                        rewriteSafeCallPart((AbstractInsnNode) it.next(), insnList);
                    }
                    insnList.insert(labelNode2, new InsnNode(87));
                    iMax = Math.max(iMax, 1);
                } else {
                    Iterator<T> it2 = allPredecessors.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            it = allPredecessors.iterator();
                            while (it.hasNext()) {
                                rewriteSafeCallPart((AbstractInsnNode) it.next(), insnList);
                            }
                            insnList.insert(labelNode2, new InsnNode(87));
                            iMax = Math.max(iMax, 1);
                        } else if (!isRewritableSafeCallPart((AbstractInsnNode) it2.next())) {
                        }
                    }
                }
            }
        }
        cfg.getMethodNode().maxStack += iMax;
    }

    private final void simplifyTrivialInstructions(MethodNode methodNode) {
        AbstractInsnNode next;
        InsnList insnList = methodNode.instructions;
        AbstractInsnNode[] array = insnList.toArray();
        array.getClass();
        for (AbstractInsnNode abstractInsnNode : array) {
            abstractInsnNode.getClass();
            if (matchOpcodes(abstractInsnNode, 21, 87) || matchOpcodes(abstractInsnNode, 23, 87) || matchOpcodes(abstractInsnNode, 25, 87)) {
                AbstractInsnNode next2 = abstractInsnNode.getNext();
                insnList.insert(abstractInsnNode, new InsnNode(0));
                insnList.remove(abstractInsnNode);
                insnList.remove(next2);
            } else if (matchOpcodes(abstractInsnNode, 24, 88) || matchOpcodes(abstractInsnNode, 22, 88)) {
                AbstractInsnNode next3 = abstractInsnNode.getNext();
                insnList.insert(abstractInsnNode, new InsnNode(0));
                insnList.remove(abstractInsnNode);
                insnList.remove(next3);
            }
        }
        List list = methodNode.tryCatchBlocks;
        list.getClass();
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(((TryCatchBlockNode) it.next()).start);
        }
        AbstractInsnNode[] array2 = insnList.toArray();
        array2.getClass();
        for (AbstractInsnNode abstractInsnNode2 : array2) {
            if (abstractInsnNode2.getOpcode() == 0) {
                AbstractInsnNode previous = abstractInsnNode2.getPrevious();
                if (!CollectionsKt.contains(hashSet, previous) && (((next = abstractInsnNode2.getNext()) != null && UtilKt.isMeaningful(next)) || (previous != null && UtilKt.isMeaningful(previous)))) {
                    insnList.remove(abstractInsnNode2);
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        internalClassName.getClass();
        methodNode.getClass();
        Collection<AbstractInsnNode> collection = methodNode.instructions;
        collection.getClass();
        if (!(collection instanceof Collection) || !collection.isEmpty()) {
            for (AbstractInsnNode abstractInsnNode : collection) {
                abstractInsnNode.getClass();
                if (InlineCodegenUtilsKt.isSuspendInlineMarker(abstractInsnNode)) {
                    return;
                }
            }
        }
        simplifyTrivialInstructions(methodNode);
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(methodNode);
        processLabels(controlFlowGraph);
        simplifyKnownSafeCallPatterns(controlFlowGraph);
        List<TemporaryVal> listAnalyze = this.temporaryValsAnalyzer.analyze(internalClassName, methodNode);
        if (!listAnalyze.isEmpty()) {
            optimizeTemporaryVals(controlFlowGraph, listAnalyze);
        }
        UtilKt.removeUnusedLocalVariables(methodNode);
    }
}
