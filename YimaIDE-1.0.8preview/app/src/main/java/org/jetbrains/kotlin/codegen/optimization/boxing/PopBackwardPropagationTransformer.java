package org.jetbrains.kotlin.codegen.optimization.boxing;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.OptimizationMethodVisitor;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.SourceInterpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.SourceValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/PopBackwardPropagationTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "Transformer", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PopBackwardPropagationTransformer extends MethodTransformer {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        internalClassName.getClass();
        methodNode.getClass();
        if (OptimizationMethodVisitor.INSTANCE.canBeOptimizedUsingSourceInterpreter(methodNode)) {
            Collection<AbstractInsnNode> collection = methodNode.instructions;
            collection.getClass();
            if ((collection instanceof Collection) && collection.isEmpty()) {
                return;
            }
            for (AbstractInsnNode abstractInsnNode : collection) {
                abstractInsnNode.getClass();
                if (PopBackwardPropagationTransformerKt.isPop(abstractInsnNode) || PopBackwardPropagationTransformerKt.isPurePush(abstractInsnNode)) {
                    new Transformer(methodNode).transform();
                    return;
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0019\u001a\u00020\u000bJ\u0012\u0010\u001a\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\n0\u001bH\u0002J\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0002JA\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u0002`\f*\u00020\n2\u0016\u0010 \u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010!0\u00152\u0006\u0010\"\u001a\u00020#H\u0002¢\u0006\u0002\u0010$J\f\u0010%\u001a\u00020\u001d*\u00020\nH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u0002`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u0002`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u0002`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u0002`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u0002`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0014\u001a(\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\n0\n \u0013*\u0014\u0012\u000e\b\u0001\u0012\n \u0013*\u0004\u0018\u00010\n0\n\u0018\u00010\u00150\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/PopBackwardPropagationTransformer$Transformer;", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "REPLACE_WITH_NOP", "Lkotlin/Function1;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/boxing/Transformation;", "REPLACE_WITH_POP1", "REPLACE_WITH_POP2", "INSERT_POP1_AFTER", "INSERT_POP2_AFTER", "insnList", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "kotlin.jvm.PlatformType", "insns", Argument.Delimiters.none, "[Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "dontTouchInsnIndices", "Ljava/util/BitSet;", "transform", "markAsDontTouch", Argument.Delimiters.none, "longerWhenFusedWithPop", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/SourceValue;", "combineWithPop", "frames", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "resultSize", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;I)Lkotlin/jvm/functions/Function1;", "shouldKeep", "HazardsTrackingInterpreter", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Transformer {
        private final Function1<AbstractInsnNode, Unit> INSERT_POP1_AFTER;
        private final Function1<AbstractInsnNode, Unit> INSERT_POP2_AFTER;
        private final Function1<AbstractInsnNode, Unit> REPLACE_WITH_NOP;
        private final Function1<AbstractInsnNode, Unit> REPLACE_WITH_POP1;
        private final Function1<AbstractInsnNode, Unit> REPLACE_WITH_POP2;
        private final BitSet dontTouchInsnIndices;
        private final InsnList insnList;
        private final AbstractInsnNode[] insns;
        private final MethodNode methodNode;

        public Transformer(MethodNode methodNode) {
            methodNode.getClass();
            this.methodNode = methodNode;
            this.REPLACE_WITH_NOP = new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.a
                public final Object invoke(Object obj) {
                    return PopBackwardPropagationTransformer.Transformer.a(this.b, (AbstractInsnNode) obj);
                }
            };
            this.REPLACE_WITH_POP1 = new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.b
                public final Object invoke(Object obj) {
                    return PopBackwardPropagationTransformer.Transformer.e(this.b, (AbstractInsnNode) obj);
                }
            };
            this.REPLACE_WITH_POP2 = new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.c
                public final Object invoke(Object obj) {
                    return PopBackwardPropagationTransformer.Transformer.c(this.b, (AbstractInsnNode) obj);
                }
            };
            this.INSERT_POP1_AFTER = new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.d
                public final Object invoke(Object obj) {
                    return PopBackwardPropagationTransformer.Transformer.d(this.b, (AbstractInsnNode) obj);
                }
            };
            this.INSERT_POP2_AFTER = new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.boxing.e
                public final Object invoke(Object obj) {
                    return PopBackwardPropagationTransformer.Transformer.b(this.b, (AbstractInsnNode) obj);
                }
            };
            InsnList insnList = methodNode.instructions;
            this.insnList = insnList;
            AbstractInsnNode[] array = insnList.toArray();
            this.insns = array;
            this.dontTouchInsnIndices = new BitSet(array.length);
        }

        public static Unit a(Transformer transformer, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            transformer.insnList.set(abstractInsnNode, new InsnNode(0));
            return Unit.INSTANCE;
        }

        public static Unit b(Transformer transformer, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            transformer.insnList.insert(abstractInsnNode, new InsnNode(88));
            return Unit.INSTANCE;
        }

        public static Unit c(Transformer transformer, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            transformer.insnList.set(abstractInsnNode, new InsnNode(88));
            return Unit.INSTANCE;
        }

        private final Function1<AbstractInsnNode, Unit> combineWithPop(AbstractInsnNode abstractInsnNode, Frame<SourceValue>[] frameArr, int i) {
            if (PopBackwardPropagationTransformerKt.isPurePush(abstractInsnNode)) {
                return this.REPLACE_WITH_NOP;
            }
            if (!BoxingInterpreterKt.isPrimitiveBoxing(abstractInsnNode) && !PopBackwardPropagationTransformerKt.isPrimitiveTypeConversion(abstractInsnNode)) {
                if (i == 1) {
                    return this.INSERT_POP1_AFTER;
                }
                if (i == 2) {
                    return this.INSERT_POP2_AFTER;
                }
                ru7.a("Unexpected pop value size: ", i);
                return null;
            }
            int iIndexOf = this.insnList.indexOf(abstractInsnNode);
            Frame<SourceValue> frame = frameArr[iIndexOf];
            if (frame == null) {
                rwa.a("dead instruction #", iIndexOf, " used by non-dead instruction");
                return null;
            }
            SourceValue pVar = StackTransformationUtilsKt.top(frame);
            if (pVar == null) {
                rwa.a("coercion instruction at #", iIndexOf, " has no input");
                return null;
            }
            int i2 = pVar.size;
            if (i2 == 1) {
                return this.REPLACE_WITH_POP1;
            }
            if (i2 == 2) {
                return this.REPLACE_WITH_POP2;
            }
            throw new AssertionError("Unexpected pop value size: " + pVar.size);
        }

        public static Unit d(Transformer transformer, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            transformer.insnList.insert(abstractInsnNode, new InsnNode(87));
            return Unit.INSTANCE;
        }

        public static Unit e(Transformer transformer, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            transformer.insnList.set(abstractInsnNode, new InsnNode(87));
            return Unit.INSTANCE;
        }

        private final boolean longerWhenFusedWithPop(SourceValue sourceValue) {
            Set<AbstractInsnNode> set = sourceValue.insns;
            set.getClass();
            int i = 0;
            for (AbstractInsnNode abstractInsnNode : set) {
                abstractInsnNode.getClass();
                if (PopBackwardPropagationTransformerKt.isPurePush(abstractInsnNode)) {
                    i--;
                } else if (!BoxingInterpreterKt.isPrimitiveBoxing(abstractInsnNode) && !PopBackwardPropagationTransformerKt.isPrimitiveTypeConversion(abstractInsnNode)) {
                    i++;
                }
            }
            return i > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markAsDontTouch(Collection<? extends AbstractInsnNode> collection) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                this.dontTouchInsnIndices.set(this.insnList.indexOf((AbstractInsnNode) it.next()), true);
            }
        }

        private final boolean shouldKeep(AbstractInsnNode abstractInsnNode) {
            return this.dontTouchInsnIndices.get(this.insnList.indexOf(abstractInsnNode));
        }

        public final MethodNode getMethodNode() {
            return this.methodNode;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
        /* JADX WARN: Code duplicated, block: B:103:0x012a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:115:0x01a5 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:117:0x0193 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:81:0x0199  */
        public final void transform() throws AnalyzerException {
            SourceValue pVar;
            Frame<SourceValue>[] frameArrAnalyze = new FastMethodAnalyzer("fake", this.methodNode, new HazardsTrackingInterpreter(), false, null, 24, null).analyze();
            AbstractInsnNode[] abstractInsnNodeArr = this.insns;
            abstractInsnNodeArr.getClass();
            int length = abstractInsnNodeArr.length;
            for (int i = 0; i < length; i++) {
                AbstractInsnNode abstractInsnNode = abstractInsnNodeArr[i];
                Frame<SourceValue> frame = frameArrAnalyze[i];
                if (frame != null) {
                    switch (abstractInsnNode.getOpcode()) {
                        case 87:
                            SourceValue sourceValue = (SourceValue) StackTransformationUtilsKt.top(frame);
                            if (sourceValue != null) {
                                Set set = sourceValue.insns;
                                set.getClass();
                                Set set2 = set;
                                if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                                    Iterator it = set2.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) it.next();
                                            abstractInsnNode2.getClass();
                                            if (shouldKeep(abstractInsnNode2)) {
                                            }
                                        } else if (!longerWhenFusedWithPop(sourceValue)) {
                                            break;
                                        }
                                        Set set3 = sourceValue.insns;
                                        set3.getClass();
                                        markAsDontTouch(set3);
                                    }
                                } else if (!longerWhenFusedWithPop(sourceValue)) {
                                    Set set4 = sourceValue.insns;
                                    set4.getClass();
                                    markAsDontTouch(set4);
                                }
                            }
                            break;
                        case 88:
                            List listPeekWords = StackTransformationUtilsKt.peekWords(frame, 2);
                            if (listPeekWords != null) {
                                Iterator it2 = listPeekWords.iterator();
                                while (it2.hasNext()) {
                                    Set set5 = ((SourceValue) it2.next()).insns;
                                    set5.getClass();
                                    markAsDontTouch(set5);
                                }
                            }
                            break;
                        case 90:
                            List listPeekWords2 = StackTransformationUtilsKt.peekWords(frame, 1, 1);
                            if (listPeekWords2 != null) {
                                Iterator it3 = listPeekWords2.iterator();
                                while (it3.hasNext()) {
                                    Set set6 = ((SourceValue) it3.next()).insns;
                                    set6.getClass();
                                    markAsDontTouch(set6);
                                }
                            }
                            break;
                        case 91:
                            List listPeekWords3 = StackTransformationUtilsKt.peekWords(frame, 1, 2);
                            if (listPeekWords3 != null) {
                                Iterator it4 = listPeekWords3.iterator();
                                while (it4.hasNext()) {
                                    Set set7 = ((SourceValue) it4.next()).insns;
                                    set7.getClass();
                                    markAsDontTouch(set7);
                                }
                            }
                            break;
                        case 93:
                            List listPeekWords4 = StackTransformationUtilsKt.peekWords(frame, 2, 1);
                            if (listPeekWords4 != null) {
                                Iterator it5 = listPeekWords4.iterator();
                                while (it5.hasNext()) {
                                    Set set8 = ((SourceValue) it5.next()).insns;
                                    set8.getClass();
                                    markAsDontTouch(set8);
                                }
                            }
                            break;
                        case 94:
                            List listPeekWords5 = StackTransformationUtilsKt.peekWords(frame, 2, 2);
                            if (listPeekWords5 != null) {
                                Iterator it6 = listPeekWords5.iterator();
                                while (it6.hasNext()) {
                                    Set set9 = ((SourceValue) it6.next()).insns;
                                    set9.getClass();
                                    markAsDontTouch(set9);
                                }
                            }
                            break;
                    }
                }
            }
            HashMap map = new HashMap();
            AbstractInsnNode[] abstractInsnNodeArr2 = this.insns;
            abstractInsnNodeArr2.getClass();
            int length2 = abstractInsnNodeArr2.length;
            for (int i2 = 0; i2 < length2; i2++) {
                AbstractInsnNode abstractInsnNode3 = abstractInsnNodeArr2[i2];
                Frame<SourceValue> frame2 = frameArrAnalyze[i2];
                if (frame2 != null && abstractInsnNode3.getOpcode() == 87 && (pVar = StackTransformationUtilsKt.top(frame2)) != null) {
                    Set set10 = pVar.insns;
                    set10.getClass();
                    Set set11 = set10;
                    if ((set11 instanceof Collection) && set11.isEmpty()) {
                        map.put(abstractInsnNode3, this.REPLACE_WITH_NOP);
                        Set<AbstractInsnNode> set12 = pVar.insns;
                        set12.getClass();
                        for (AbstractInsnNode abstractInsnNode4 : set12) {
                            if (!map.containsKey(abstractInsnNode4)) {
                                abstractInsnNode4.getClass();
                                map.put(abstractInsnNode4, combineWithPop(abstractInsnNode4, frameArrAnalyze, pVar.size));
                            }
                        }
                    } else {
                        Iterator it7 = set11.iterator();
                        while (true) {
                            if (it7.hasNext()) {
                                AbstractInsnNode abstractInsnNode5 = (AbstractInsnNode) it7.next();
                                abstractInsnNode5.getClass();
                                if (shouldKeep(abstractInsnNode5)) {
                                }
                            } else {
                                map.put(abstractInsnNode3, this.REPLACE_WITH_NOP);
                                Set<AbstractInsnNode> set13 = pVar.insns;
                                set13.getClass();
                                while (r5.hasNext()) {
                                    if (!map.containsKey(abstractInsnNode4)) {
                                        abstractInsnNode4.getClass();
                                        map.put(abstractInsnNode4, combineWithPop(abstractInsnNode4, frameArrAnalyze, pVar.size));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (Object obj : map.entrySet()) {
                obj.getClass();
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                key.getClass();
                Object value = entry.getValue();
                value.getClass();
                ((Function1) value).invoke((AbstractInsnNode) key);
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\t\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J \u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J(\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/PopBackwardPropagationTransformer$Transformer$HazardsTrackingInterpreter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/SourceInterpreter;", "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/boxing/PopBackwardPropagationTransformer$Transformer;)V", "naryOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/SourceValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "values", Argument.Delimiters.none, "copyOperation", "value", "unaryOperation", "binaryOperation", "value1", "value2", "ternaryOperation", "value3", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public final class HazardsTrackingInterpreter extends SourceInterpreter {
            public HazardsTrackingInterpreter() {
                super(589824);
            }

            public SourceValue binaryOperation(AbstractInsnNode insn, SourceValue value1, SourceValue value2) {
                insn.getClass();
                value1.getClass();
                value2.getClass();
                Transformer transformer = Transformer.this;
                Set set = value1.insns;
                set.getClass();
                transformer.markAsDontTouch(set);
                Transformer transformer2 = Transformer.this;
                Set set2 = value2.insns;
                set2.getClass();
                transformer2.markAsDontTouch(set2);
                SourceValue sourceValueBinaryOperation = super.binaryOperation(insn, value1, value2);
                sourceValueBinaryOperation.getClass();
                return sourceValueBinaryOperation;
            }

            public SourceValue copyOperation(AbstractInsnNode insn, SourceValue value) {
                insn.getClass();
                value.getClass();
                Transformer transformer = Transformer.this;
                Set set = value.insns;
                set.getClass();
                transformer.markAsDontTouch(set);
                SourceValue sourceValueCopyOperation = super.copyOperation(insn, value);
                sourceValueCopyOperation.getClass();
                return sourceValueCopyOperation;
            }

            public SourceValue naryOperation(AbstractInsnNode insn, List<? extends SourceValue> values) {
                insn.getClass();
                values.getClass();
                for (SourceValue sourceValue : values) {
                    Transformer transformer = Transformer.this;
                    Set set = sourceValue.insns;
                    set.getClass();
                    transformer.markAsDontTouch(set);
                }
                SourceValue sourceValueNaryOperation = super.naryOperation(insn, values);
                sourceValueNaryOperation.getClass();
                return sourceValueNaryOperation;
            }

            public SourceValue ternaryOperation(AbstractInsnNode insn, SourceValue value1, SourceValue value2, SourceValue value3) {
                insn.getClass();
                value1.getClass();
                value2.getClass();
                value3.getClass();
                Transformer transformer = Transformer.this;
                Set set = value1.insns;
                set.getClass();
                transformer.markAsDontTouch(set);
                Transformer transformer2 = Transformer.this;
                Set set2 = value2.insns;
                set2.getClass();
                transformer2.markAsDontTouch(set2);
                Transformer transformer3 = Transformer.this;
                Set set3 = value3.insns;
                set3.getClass();
                transformer3.markAsDontTouch(set3);
                SourceValue sourceValueTernaryOperation = super.ternaryOperation(insn, value1, value2, value3);
                sourceValueTernaryOperation.getClass();
                return sourceValueTernaryOperation;
            }

            public SourceValue unaryOperation(AbstractInsnNode insn, SourceValue value) {
                insn.getClass();
                value.getClass();
                Transformer transformer = Transformer.this;
                Set set = value.insns;
                set.getClass();
                transformer.markAsDontTouch(set);
                SourceValue sourceValueUnaryOperation = super.unaryOperation(insn, value);
                sourceValueUnaryOperation.getClass();
                return sourceValueUnaryOperation;
            }

            /* JADX INFO: renamed from: naryOperation, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Value m68naryOperation(AbstractInsnNode abstractInsnNode, List list) {
                return naryOperation(abstractInsnNode, (List<? extends SourceValue>) list);
            }
        }
    }
}
