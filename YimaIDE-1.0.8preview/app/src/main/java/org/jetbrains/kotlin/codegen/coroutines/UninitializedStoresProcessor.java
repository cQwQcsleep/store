package org.jetbrains.kotlin.codegen.coroutines;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.UninitializedStoresProcessor;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0011\u0012\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\n\u001a\u0004\u0018\u00010\u000b*\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\f\u0010\u0010\u001a\u00020\u0007*\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/UninitializedStoresProcessor;", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "isInSpecialMethod", Argument.Delimiters.none, "run", Argument.Delimiters.none, "getUninitializedValueForConstructorCall", "Lorg/jetbrains/kotlin/codegen/coroutines/UninitializedStoresProcessor$UninitializedNewValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "isConstructorCall", "UninitializedNewValueFrame", "UninitializedNewValue", "UninitializedNewValueMarkerInterpreter", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UninitializedStoresProcessor {
    private final boolean isInSpecialMethod;
    private final MethodNode methodNode;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0005H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/UninitializedStoresProcessor$UninitializedNewValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "newInsn", "Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "internalName", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;Ljava/lang/String;)V", "getNewInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "getInternalName", "()Ljava/lang/String;", "toString", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UninitializedNewValue extends StrictBasicValue {
        private final String internalName;
        private final TypeInsnNode newInsn;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UninitializedNewValue(TypeInsnNode typeInsnNode, String str) {
            super(Type.getObjectType(str));
            typeInsnNode.getClass();
            str.getClass();
            this.newInsn = typeInsnNode;
            this.internalName = str;
        }

        public final String getInternalName() {
            return this.internalName;
        }

        public final TypeInsnNode getNewInsn() {
            return this.newInsn;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
        public String toString() {
            return "UninitializedNewValue(internalName='" + this.internalName + "')";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/UninitializedStoresProcessor$UninitializedNewValueFrame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "nLocals", Argument.Delimiters.none, "nStack", "<init>", "(Lorg/jetbrains/kotlin/codegen/coroutines/UninitializedStoresProcessor;II)V", "execute", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class UninitializedNewValueFrame extends Frame<BasicValue> {
        public UninitializedNewValueFrame(int i, int i2) {
            super(i, i2);
        }

        public void execute(AbstractInsnNode insn, Interpreter<BasicValue> interpreter) {
            insn.getClass();
            boolean z = UninitializedStoresProcessor.this.getUninitializedValueForConstructorCall(this, insn) != null;
            super.execute(insn, interpreter);
            if (z) {
                UninitializedNewValue uninitializedNewValuePop = pop();
                uninitializedNewValuePop.getClass();
                push(new StrictBasicValue(uninitializedNewValuePop.getType()));
            }
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bH\u0002J!\u0010\u001e\u001a\u00020\u00162\u0014\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010!0 ¢\u0006\u0002\u0010\"J\u001e\u0010#\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0!H\u0002J\u001e\u0010%\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0!H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\u0006\u001a*\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0007j\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t`\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u001a\u001a\u00020\u001b*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/UninitializedStoresProcessor$UninitializedNewValueMarkerInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter;", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/InsnList;)V", "uninitializedValuesToRemovableUsages", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", Argument.Delimiters.none, "Lkotlin/collections/HashMap;", "getUninitializedValuesToRemovableUsages", "()Ljava/util/HashMap;", "newOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "copyOperation", "value", "merge", "v", "w", "checkUninitializedObjectCopy", Argument.Delimiters.none, "newInsn", "Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "usageInsn", "debugText", Argument.Delimiters.none, "getDebugText", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/String;", "analyzePopInstructions", "frames", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "([Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "analyzePop", "frame", "analyzePop2", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UninitializedNewValueMarkerInterpreter extends OptimizationBasicInterpreter {
        private final InsnList instructions;
        private final HashMap<AbstractInsnNode, Set<AbstractInsnNode>> uninitializedValuesToRemovableUsages;

        public UninitializedNewValueMarkerInterpreter(InsnList insnList) {
            insnList.getClass();
            this.instructions = insnList;
            this.uninitializedValuesToRemovableUsages = new HashMap<>();
        }

        private final void analyzePop(AbstractInsnNode insn, Frame<BasicValue> frame) {
            BasicValue pVar = StackTransformationUtilsKt.top(frame);
            if (pVar == null) {
                f2f.a("Stack underflow on POP: ", getDebugText(insn));
            } else if (pVar instanceof UninitializedNewValue) {
                Set<AbstractInsnNode> set = this.uninitializedValuesToRemovableUsages.get(((UninitializedNewValue) pVar).getNewInsn());
                set.getClass();
                set.add(insn);
            }
        }

        private final void analyzePop2(AbstractInsnNode insn, Frame<BasicValue> frame) {
            List<BasicValue> listPeekWords = StackTransformationUtilsKt.peekWords(frame, 2);
            if (listPeekWords == null) {
                f2f.a("Stack underflow on POP2: ", getDebugText(insn));
                return;
            }
            for (BasicValue basicValue : listPeekWords) {
                if (basicValue instanceof UninitializedNewValue) {
                    s0g.a("Unexpected POP2 instruction for ", getDebugText(((UninitializedNewValue) basicValue).getNewInsn()), ": ", getDebugText(insn));
                    return;
                }
            }
        }

        private final void checkUninitializedObjectCopy(TypeInsnNode newInsn, AbstractInsnNode usageInsn) {
            int opcode = usageInsn.getOpcode();
            if (opcode == 25 || opcode == 58 || opcode == 89) {
                return;
            }
            s0g.a("Unexpected copy instruction for ", getDebugText(newInsn), ": ", getDebugText(usageInsn));
        }

        private final String getDebugText(AbstractInsnNode abstractInsnNode) {
            return this.instructions.indexOf(abstractInsnNode) + ": " + InlineCodegenUtilsKt.getInsnText(abstractInsnNode);
        }

        public final void analyzePopInstructions(Frame<BasicValue>[] frames) {
            frames.getClass();
            int i = 0;
            for (AbstractInsnNode abstractInsnNode : this.instructions) {
                int i2 = i + 1;
                Frame<BasicValue> frame = frames[i];
                if (frame != null) {
                    int opcode = abstractInsnNode.getOpcode();
                    if (opcode == 87) {
                        analyzePop(abstractInsnNode, frame);
                    } else if (opcode == 88) {
                        analyzePop2(abstractInsnNode, frame);
                    }
                }
                i = i2;
            }
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
        public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) {
            insn.getClass();
            if (!(value instanceof UninitializedNewValue)) {
                return super.copyOperation(insn, value);
            }
            UninitializedNewValue uninitializedNewValue = (UninitializedNewValue) value;
            checkUninitializedObjectCopy(uninitializedNewValue.getNewInsn(), insn);
            Set<AbstractInsnNode> set = this.uninitializedValuesToRemovableUsages.get(uninitializedNewValue.getNewInsn());
            set.getClass();
            set.add(insn);
            return value;
        }

        public final HashMap<AbstractInsnNode, Set<AbstractInsnNode>> getUninitializedValuesToRemovableUsages() {
            return this.uninitializedValuesToRemovableUsages;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
        public BasicValue merge(BasicValue v, BasicValue w) {
            v.getClass();
            w.getClass();
            if (v != w) {
                StrictBasicValue strictBasicValue = StrictBasicValue.UNINITIALIZED_VALUE;
                if (v != strictBasicValue && w != strictBasicValue) {
                    boolean z = v instanceof UninitializedNewValue;
                    if (!z && !(w instanceof UninitializedNewValue)) {
                        BasicValue basicValueMerge = super.merge(v, w);
                        basicValueMerge.getClass();
                        return basicValueMerge;
                    }
                    UninitializedNewValue uninitializedNewValue = z ? (UninitializedNewValue) v : null;
                    TypeInsnNode newInsn = uninitializedNewValue != null ? uninitializedNewValue.getNewInsn() : null;
                    UninitializedNewValue uninitializedNewValue2 = w instanceof UninitializedNewValue ? (UninitializedNewValue) w : null;
                    if (newInsn != (uninitializedNewValue2 != null ? uninitializedNewValue2.getNewInsn() : null)) {
                    }
                }
                return strictBasicValue;
            }
            return v;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
        /* JADX INFO: renamed from: newOperation */
        public BasicValue mo59newOperation(AbstractInsnNode insn) {
            insn.getClass();
            if (insn.getOpcode() != 187) {
                return super.mo59newOperation(insn);
            }
            HashMap<AbstractInsnNode, Set<AbstractInsnNode>> map = this.uninitializedValuesToRemovableUsages;
            if (map.get(insn) == null) {
                map.put(insn, new LinkedHashSet());
            }
            TypeInsnNode typeInsnNode = (TypeInsnNode) insn;
            String str = typeInsnNode.desc;
            str.getClass();
            return new UninitializedNewValue(typeInsnNode, str);
        }
    }

    public UninitializedStoresProcessor(MethodNode methodNode) {
        methodNode.getClass();
        this.methodNode = methodNode;
        this.isInSpecialMethod = Intrinsics.areEqual(methodNode.name, "<init>") || Intrinsics.areEqual(methodNode.name, "<clinit>");
    }

    public static Frame a(UninitializedStoresProcessor uninitializedStoresProcessor, int i, int i2) {
        return uninitializedStoresProcessor.new UninitializedNewValueFrame(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UninitializedNewValue getUninitializedValueForConstructorCall(Frame<BasicValue> frame, AbstractInsnNode abstractInsnNode) {
        if (!isConstructorCall(abstractInsnNode)) {
            return null;
        }
        abstractInsnNode.getOpcode();
        int length = Type.getArgumentTypes(((MethodInsnNode) abstractInsnNode).desc).length;
        UninitializedNewValue uninitializedNewValuePeek = StackTransformationUtilsKt.peek(frame, length + 1);
        UninitializedNewValue uninitializedNewValue = uninitializedNewValuePeek instanceof UninitializedNewValue ? uninitializedNewValuePeek : null;
        if (uninitializedNewValue != null) {
            StackTransformationUtilsKt.peek(frame, length);
            return uninitializedNewValue;
        }
        if (this.isInSpecialMethod) {
            return null;
        }
        k2d.a("Expected value generated with NEW");
        return null;
    }

    private final boolean isConstructorCall(AbstractInsnNode abstractInsnNode) {
        return (abstractInsnNode instanceof MethodInsnNode) && Intrinsics.areEqual(((MethodInsnNode) abstractInsnNode).name, "<init>");
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0066  */
    public final void run() {
        Frame<BasicValue>[] frameArr;
        char c;
        Collection collection = this.methodNode.instructions;
        collection.getClass();
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            char c2 = 187;
            if (((AbstractInsnNode) it.next()).getOpcode() == 187) {
                InsnList insnList = this.methodNode.instructions;
                insnList.getClass();
                UninitializedNewValueMarkerInterpreter uninitializedNewValueMarkerInterpreter = new UninitializedNewValueMarkerInterpreter(insnList);
                Frame<BasicValue>[] frameArrAnalyze = new FastMethodAnalyzer("fake", this.methodNode, uninitializedNewValueMarkerInterpreter, true, new Function2() { // from class: g0f
                    public final Object invoke(Object obj, Object obj2) {
                        return UninitializedStoresProcessor.a(this.b, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                    }
                }).analyze();
                uninitializedNewValueMarkerInterpreter.analyzePopInstructions(frameArrAnalyze);
                MethodInsnNode[] array = this.methodNode.instructions.toArray();
                array.getClass();
                int length = array.length;
                int i = 0;
                while (i < length) {
                    MethodInsnNode methodInsnNode = array[i];
                    Frame<BasicValue> frame = frameArrAnalyze[i];
                    if (frame == null) {
                        frameArr = frameArrAnalyze;
                        c = c2;
                    } else {
                        methodInsnNode.getClass();
                        UninitializedNewValue uninitializedValueForConstructorCall = getUninitializedValueForConstructorCall(frame, methodInsnNode);
                        if (uninitializedValueForConstructorCall == null) {
                            frameArr = frameArrAnalyze;
                            c = c2;
                        } else {
                            TypeInsnNode newInsn = uninitializedValueForConstructorCall.getNewInsn();
                            Set<AbstractInsnNode> set = uninitializedNewValueMarkerInterpreter.getUninitializedValuesToRemovableUsages().get(newInsn);
                            set.getClass();
                            Set<AbstractInsnNode> set2 = set;
                            set2.isEmpty();
                            boolean z = true;
                            if (set2.size() != 1) {
                                InsnList insnList2 = this.methodNode.instructions;
                                insnList2.getClass();
                                UtilKt.removeAll(insnList2, set2);
                                insnList2.remove(newInsn);
                                int length2 = Type.getArgumentTypes(methodInsnNode.desc).length;
                                ArrayList arrayList = new ArrayList();
                                int size = this.methodNode.maxLocals;
                                int i2 = 0;
                                while (i2 < length2) {
                                    Type type = frame.getStack((frame.getStackSize() - 1) - i2).getType();
                                    this.methodNode.instructions.insertBefore(methodInsnNode, new VarInsnNode(type.getOpcode(54), size));
                                    size += type.getSize();
                                    arrayList.add(type);
                                    i2++;
                                    z = z;
                                    frameArrAnalyze = frameArrAnalyze;
                                }
                                frameArr = frameArrAnalyze;
                                MethodNode methodNode = this.methodNode;
                                methodNode.maxLocals = Math.max(methodNode.maxLocals, size);
                                InsnList insnList3 = this.methodNode.instructions;
                                c = 187;
                                AbstractInsnNode typeInsnNode = new TypeInsnNode(187, newInsn.desc);
                                AbstractInsnNode insnNode = new InsnNode(89);
                                AbstractInsnNode[] abstractInsnNodeArr = new AbstractInsnNode[2];
                                abstractInsnNodeArr[0] = typeInsnNode;
                                abstractInsnNodeArr[z ? 1 : 0] = insnNode;
                                insnList3.insertBefore(methodInsnNode, UtilKt.insnListOf(abstractInsnNodeArr));
                                for (Type type2 : CollectionsKt.reversed(arrayList)) {
                                    size -= type2.getSize();
                                    this.methodNode.instructions.insertBefore(methodInsnNode, new VarInsnNode(type2.getOpcode(21), size));
                                }
                            } else {
                                frameArr = frameArrAnalyze;
                                c = c2;
                            }
                        }
                    }
                    i++;
                    c2 = c;
                    frameArrAnalyze = frameArr;
                }
                return;
            }
        }
    }
}
