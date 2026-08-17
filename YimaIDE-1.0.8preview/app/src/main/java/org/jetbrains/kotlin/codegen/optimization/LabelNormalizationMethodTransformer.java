package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartIdentityTable;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FrameNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/LabelNormalizationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "TransformerForMethod", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LabelNormalizationMethodTransformer extends MethodTransformer {
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        new TransformerForMethod(methodNode).transform();
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001f\u001a\u00020\"H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\u00182\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\u0013H\u0002J\b\u0010'\u001a\u00020\u0013H\u0002J\f\u0010(\u001a\u00020\u0018*\u00020\u001aH\u0002J\f\u0010(\u001a\u00020\u0018*\u00020\u001dH\u0002J\f\u0010(\u001a\u00020\u0018*\u00020 H\u0002J\f\u0010(\u001a\u00020\u0018*\u00020\"H\u0002J\f\u0010(\u001a\u00020\u0018*\u00020%H\u0002J\u0010\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000fH\u0002J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/LabelNormalizationMethodTransformer$TransformerForMethod;", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "kotlin.jvm.PlatformType", "getInstructions", "()Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "newLabelNodes", "Lorg/jetbrains/kotlin/utils/SmartIdentityTable;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getNewLabelNodes", "()Lorg/jetbrains/kotlin/utils/SmartIdentityTable;", "transform", Argument.Delimiters.none, "rewriteLabelInstructions", Argument.Delimiters.none, "rewriteNonLabelInstructions", "rewriteLineNumberNode", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "oldLineNode", "Lorg/jetbrains/org/objectweb/asm/tree/LineNumberNode;", "rewriteJumpInsn", "oldJumpNode", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "rewriteLookupSwitchInsn", "oldSwitchNode", "Lorg/jetbrains/org/objectweb/asm/tree/LookupSwitchInsnNode;", "rewriteTableSwitchInsn", "Lorg/jetbrains/org/objectweb/asm/tree/TableSwitchInsnNode;", "rewriteFrameNode", "oldFrameNode", "Lorg/jetbrains/org/objectweb/asm/tree/FrameNode;", "rewriteTryCatchBlocks", "rewriteLocalVars", "rewriteLabels", "getNew", "oldLabelNode", "getNewOrOld", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TransformerForMethod {
        private final InsnList instructions;
        private final MethodNode methodNode;
        private final SmartIdentityTable<LabelNode, LabelNode> newLabelNodes;

        public TransformerForMethod(MethodNode methodNode) {
            methodNode.getClass();
            this.methodNode = methodNode;
            this.instructions = methodNode.instructions;
            this.newLabelNodes = new SmartIdentityTable<>();
        }

        private final LabelNode getNew(LabelNode oldLabelNode) {
            LabelNode labelNode = (LabelNode) this.newLabelNodes.get(oldLabelNode);
            if (labelNode != null) {
                return labelNode;
            }
            k2d.a("Label wasn't found during iterating through instructions");
            return null;
        }

        private final LabelNode getNewOrOld(LabelNode oldLabelNode) {
            LabelNode labelNode = (LabelNode) this.newLabelNodes.get(oldLabelNode);
            return labelNode == null ? oldLabelNode : labelNode;
        }

        private final AbstractInsnNode rewriteFrameNode(FrameNode oldFrameNode) {
            InsnList insnList = this.instructions;
            insnList.getClass();
            return LabelNormalizationMethodTransformerKt.replaceNodeGetNext(insnList, oldFrameNode, rewriteLabels(oldFrameNode));
        }

        private final AbstractInsnNode rewriteJumpInsn(JumpInsnNode oldJumpNode) {
            InsnList insnList = this.instructions;
            insnList.getClass();
            return LabelNormalizationMethodTransformerKt.replaceNodeGetNext(insnList, oldJumpNode, rewriteLabels(oldJumpNode));
        }

        private final boolean rewriteLabelInstructions() {
            AbstractInsnNode first = this.instructions.getFirst();
            boolean z = false;
            while (first != null) {
                if (first instanceof LabelNode) {
                    LabelNode labelNode = (LabelNode) first;
                    AbstractInsnNode previous = labelNode.getPrevious();
                    boolean z2 = previous instanceof LabelNode;
                    SmartIdentityTable<LabelNode, LabelNode> smartIdentityTable = this.newLabelNodes;
                    if (z2) {
                        smartIdentityTable.set(first, previous);
                        InsnList insnList = this.instructions;
                        insnList.getClass();
                        first = LabelNormalizationMethodTransformerKt.removeNodeGetNext(insnList, first);
                        z = true;
                    } else {
                        smartIdentityTable.set(first, first);
                        first = labelNode.getNext();
                    }
                } else {
                    first = first.getNext();
                }
            }
            return z;
        }

        private final AbstractInsnNode rewriteLabels(FrameNode frameNode) {
            FrameNode frameNode2 = new FrameNode(frameNode.type, 0, new Object[0], 0, new Object[0]);
            List list = frameNode.local;
            list.getClass();
            List list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (Object newOrOld : list2) {
                if (newOrOld instanceof LabelNode) {
                    newOrOld = getNewOrOld((LabelNode) newOrOld);
                }
                arrayList.add(newOrOld);
            }
            frameNode2.local = arrayList;
            List list3 = frameNode.stack;
            list3.getClass();
            List list4 = list3;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            for (Object newOrOld2 : list4) {
                if (newOrOld2 instanceof LabelNode) {
                    newOrOld2 = getNewOrOld((LabelNode) newOrOld2);
                }
                arrayList2.add(newOrOld2);
            }
            frameNode2.stack = arrayList2;
            return frameNode2;
        }

        private final AbstractInsnNode rewriteLineNumberNode(LineNumberNode oldLineNode) {
            InsnList insnList = this.instructions;
            insnList.getClass();
            return LabelNormalizationMethodTransformerKt.replaceNodeGetNext(insnList, oldLineNode, rewriteLabels(oldLineNode));
        }

        private final void rewriteLocalVars() {
            MethodNode methodNode = this.methodNode;
            List list = methodNode.localVariables;
            list.getClass();
            List<LocalVariableNode> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (LocalVariableNode localVariableNode : list2) {
                String str = localVariableNode.name;
                String str2 = localVariableNode.desc;
                String str3 = localVariableNode.signature;
                LabelNode labelNode = localVariableNode.start;
                labelNode.getClass();
                LabelNode labelNode2 = getNew(labelNode);
                LabelNode labelNode3 = localVariableNode.end;
                labelNode3.getClass();
                arrayList.add(new LocalVariableNode(str, str2, str3, labelNode2, getNew(labelNode3), localVariableNode.index));
            }
            methodNode.localVariables = arrayList;
        }

        private final AbstractInsnNode rewriteLookupSwitchInsn(LookupSwitchInsnNode oldSwitchNode) {
            InsnList insnList = this.instructions;
            insnList.getClass();
            return LabelNormalizationMethodTransformerKt.replaceNodeGetNext(insnList, oldSwitchNode, rewriteLabels(oldSwitchNode));
        }

        private final void rewriteNonLabelInstructions() {
            AbstractInsnNode first = this.instructions.getFirst();
            while (first != null) {
                if (first instanceof JumpInsnNode) {
                    first = rewriteJumpInsn((JumpInsnNode) first);
                } else if (first instanceof LineNumberNode) {
                    first = rewriteLineNumberNode((LineNumberNode) first);
                } else if (first instanceof LookupSwitchInsnNode) {
                    first = rewriteLookupSwitchInsn((LookupSwitchInsnNode) first);
                } else if (first instanceof TableSwitchInsnNode) {
                    first = rewriteTableSwitchInsn((TableSwitchInsnNode) first);
                } else {
                    first = first instanceof FrameNode ? rewriteFrameNode((FrameNode) first) : first.getNext();
                }
            }
        }

        private final AbstractInsnNode rewriteTableSwitchInsn(TableSwitchInsnNode oldSwitchNode) {
            InsnList insnList = this.instructions;
            insnList.getClass();
            return LabelNormalizationMethodTransformerKt.replaceNodeGetNext(insnList, oldSwitchNode, rewriteLabels(oldSwitchNode));
        }

        private final void rewriteTryCatchBlocks() {
            MethodNode methodNode = this.methodNode;
            List list = methodNode.tryCatchBlocks;
            list.getClass();
            List<TryCatchBlockNode> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (TryCatchBlockNode tryCatchBlockNode : list2) {
                LabelNode labelNode = tryCatchBlockNode.start;
                labelNode.getClass();
                LabelNode labelNode2 = getNew(labelNode);
                LabelNode labelNode3 = tryCatchBlockNode.end;
                labelNode3.getClass();
                LabelNode labelNode4 = getNew(labelNode3);
                LabelNode labelNode5 = tryCatchBlockNode.handler;
                labelNode5.getClass();
                TryCatchBlockNode tryCatchBlockNode2 = new TryCatchBlockNode(labelNode2, labelNode4, getNew(labelNode5), tryCatchBlockNode.type);
                tryCatchBlockNode2.visibleTypeAnnotations = tryCatchBlockNode.visibleTypeAnnotations;
                tryCatchBlockNode2.invisibleTypeAnnotations = tryCatchBlockNode.invisibleTypeAnnotations;
                arrayList.add(tryCatchBlockNode2);
            }
            methodNode.tryCatchBlocks = arrayList;
        }

        public final InsnList getInstructions() {
            return this.instructions;
        }

        public final MethodNode getMethodNode() {
            return this.methodNode;
        }

        public final SmartIdentityTable<LabelNode, LabelNode> getNewLabelNodes() {
            return this.newLabelNodes;
        }

        public final void transform() {
            if (rewriteLabelInstructions()) {
                rewriteNonLabelInstructions();
                rewriteTryCatchBlocks();
                rewriteLocalVars();
                UtilKt.removeEmptyCatchBlocks(this.methodNode);
            }
        }

        private final AbstractInsnNode rewriteLabels(JumpInsnNode jumpInsnNode) {
            int opcode = jumpInsnNode.getOpcode();
            LabelNode labelNode = jumpInsnNode.label;
            labelNode.getClass();
            return new JumpInsnNode(opcode, getNew(labelNode));
        }

        private final AbstractInsnNode rewriteLabels(LookupSwitchInsnNode lookupSwitchInsnNode) {
            LabelNode labelNode = lookupSwitchInsnNode.dflt;
            labelNode.getClass();
            LabelNode labelNode2 = getNew(labelNode);
            List list = lookupSwitchInsnNode.keys;
            list.getClass();
            LookupSwitchInsnNode lookupSwitchInsnNode2 = new LookupSwitchInsnNode(labelNode2, CollectionsKt.toIntArray(list), new LabelNode[0]);
            List list2 = lookupSwitchInsnNode.labels;
            list2.getClass();
            List<LabelNode> list3 = list2;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for (LabelNode labelNode3 : list3) {
                labelNode3.getClass();
                arrayList.add(getNew(labelNode3));
            }
            lookupSwitchInsnNode2.labels = arrayList;
            return lookupSwitchInsnNode2;
        }

        private final AbstractInsnNode rewriteLabels(TableSwitchInsnNode tableSwitchInsnNode) {
            int i = tableSwitchInsnNode.min;
            int i2 = tableSwitchInsnNode.max;
            LabelNode labelNode = tableSwitchInsnNode.dflt;
            labelNode.getClass();
            TableSwitchInsnNode tableSwitchInsnNode2 = new TableSwitchInsnNode(i, i2, getNew(labelNode), new LabelNode[0]);
            List list = tableSwitchInsnNode.labels;
            list.getClass();
            List<LabelNode> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (LabelNode labelNode2 : list2) {
                labelNode2.getClass();
                arrayList.add(getNew(labelNode2));
            }
            tableSwitchInsnNode2.labels = arrayList;
            return tableSwitchInsnNode2;
        }

        private final AbstractInsnNode rewriteLabels(LineNumberNode lineNumberNode) {
            int i = lineNumberNode.line;
            LabelNode labelNode = lineNumberNode.start;
            labelNode.getClass();
            return new LineNumberNode(i, getNewOrOld(labelNode));
        }
    }
}
