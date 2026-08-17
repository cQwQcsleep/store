package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.optimization.common.ProperTrackedReferenceValue;
import org.jetbrains.kotlin.codegen.optimization.common.ReferenceTrackingInterpreter;
import org.jetbrains.kotlin.codegen.optimization.common.ReferenceValueDescriptor;
import org.jetbrains.kotlin.codegen.optimization.common.TrackedReferenceValue;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\n\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/CapturedVarsOptimizationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "CapturedVarDescriptor", "Transformer", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CapturedVarsOptimizationMethodTransformer extends MethodTransformer {

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u00100\u001a\u000201H\u0016J\u0006\u00102\u001a\u00020\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020,0'¢\u0006\b\n\u0000\u001a\u0004\b-\u0010*R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020,0'¢\u0006\b\n\u0000\u001a\u0004\b/\u0010*¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/CapturedVarsOptimizationMethodTransformer$CapturedVarDescriptor;", "Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;", "newInsn", "Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "refType", "Lorg/jetbrains/org/objectweb/asm/Type;", "valueType", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/Type;)V", "getNewInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "getRefType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getValueType", "hazard", Argument.Delimiters.none, "getHazard", "()Z", "setHazard", "(Z)V", "initCallInsn", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "getInitCallInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "setInitCallInsn", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;)V", "localVar", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "getLocalVar", "()Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "setLocalVar", "(Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;)V", "localVarIndex", Argument.Delimiters.none, "getLocalVarIndex", "()I", "setLocalVarIndex", "(I)V", "wrapperInsns", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getWrapperInsns", "()Ljava/util/Collection;", "getFieldInsns", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "getGetFieldInsns", "putFieldInsns", "getPutFieldInsns", "onUseAsTainted", Argument.Delimiters.none, "canRewrite", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CapturedVarDescriptor implements ReferenceValueDescriptor {
        private final Collection<FieldInsnNode> getFieldInsns;
        private boolean hazard;
        private MethodInsnNode initCallInsn;
        private LocalVariableNode localVar;
        private int localVarIndex;
        private final TypeInsnNode newInsn;
        private final Collection<FieldInsnNode> putFieldInsns;
        private final Type refType;
        private final Type valueType;
        private final Collection<AbstractInsnNode> wrapperInsns;

        public CapturedVarDescriptor(TypeInsnNode typeInsnNode, Type type, Type type2) {
            typeInsnNode.getClass();
            type.getClass();
            type2.getClass();
            this.newInsn = typeInsnNode;
            this.refType = type;
            this.valueType = type2;
            this.localVarIndex = -1;
            this.wrapperInsns = new LinkedHashSet();
            this.getFieldInsns = new LinkedHashSet();
            this.putFieldInsns = new LinkedHashSet();
        }

        public final boolean canRewrite() {
            return (this.hazard || this.initCallInsn == null) ? false : true;
        }

        public final Collection<FieldInsnNode> getGetFieldInsns() {
            return this.getFieldInsns;
        }

        public final boolean getHazard() {
            return this.hazard;
        }

        public final MethodInsnNode getInitCallInsn() {
            return this.initCallInsn;
        }

        public final LocalVariableNode getLocalVar() {
            return this.localVar;
        }

        public final int getLocalVarIndex() {
            return this.localVarIndex;
        }

        public final TypeInsnNode getNewInsn() {
            return this.newInsn;
        }

        public final Collection<FieldInsnNode> getPutFieldInsns() {
            return this.putFieldInsns;
        }

        public final Type getRefType() {
            return this.refType;
        }

        public final Type getValueType() {
            return this.valueType;
        }

        public final Collection<AbstractInsnNode> getWrapperInsns() {
            return this.wrapperInsns;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.ReferenceValueDescriptor
        public void onUseAsTainted() {
            this.hazard = true;
        }

        public final void setHazard(boolean z) {
            this.hazard = z;
        }

        public final void setInitCallInsn(MethodInsnNode methodInsnNode) {
            this.initCallInsn = methodInsnNode;
        }

        public final void setLocalVar(LocalVariableNode localVariableNode) {
            this.localVar = localVariableNode;
        }

        public final void setLocalVarIndex(int i) {
            this.localVarIndex = i;
        }
    }

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001:\u0001&B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0010\u001a\u00020\u0011J\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002J%\u0010\u0016\u001a\u00020\u00112\u0016\u0010\u0017\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u0004\u0018\u00010\n*\u00020\u001aH\u0002J%\u0010\u001d\u001a\u00020\u00112\u0016\u0010\u0017\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010\u001bJ\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001f*\u00020 H\u0002J\u0014\u0010!\u001a\u00020\u0011*\u00020\"2\u0006\u0010#\u001a\u00020\u0014H\u0002J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/CapturedVarsOptimizationMethodTransformer$Transformer;", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "refValues", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/codegen/optimization/CapturedVarsOptimizationMethodTransformer$CapturedVarDescriptor;", "Lkotlin/collections/ArrayList;", "refValuesByNewInsn", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "Lkotlin/collections/LinkedHashMap;", "run", Argument.Delimiters.none, "getIndex", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "createRefValues", "trackPops", "frames", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "([Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "getCapturedVarOrNull", "assignLocalVars", "findCleanInstructions", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "removeOrReplaceByNop", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "insn", "rewriteRefValue", "capturedVar", "Interpreter", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Transformer {
        private final String internalClassName;
        private final MethodNode methodNode;
        private final ArrayList<CapturedVarDescriptor> refValues;
        private final LinkedHashMap<TypeInsnNode, CapturedVarDescriptor> refValuesByNewInsn;

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0014¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/CapturedVarsOptimizationMethodTransformer$Transformer$Interpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceTrackingInterpreter;", "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/CapturedVarsOptimizationMethodTransformer$Transformer;)V", "newOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "processRefValueUsage", Argument.Delimiters.none, "value", "Lorg/jetbrains/kotlin/codegen/optimization/common/TrackedReferenceValue;", "position", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public final class Interpreter extends ReferenceTrackingInterpreter {
            public Interpreter() {
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
            @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
            /* JADX INFO: renamed from: newOperation */
            public BasicValue mo59newOperation(AbstractInsnNode insn) throws AnalyzerException {
                insn.getClass();
                CapturedVarDescriptor capturedVarDescriptor = (CapturedVarDescriptor) Transformer.this.refValuesByNewInsn.get(insn);
                if (capturedVarDescriptor != null) {
                    return new ProperTrackedReferenceValue(capturedVarDescriptor.getRefType(), capturedVarDescriptor);
                }
                BasicValue basicValueMo59newOperation = super.mo59newOperation(insn);
                basicValueMo59newOperation.getClass();
                return basicValueMo59newOperation;
            }

            @Override // org.jetbrains.kotlin.codegen.optimization.common.ReferenceTrackingInterpreter
            public void processRefValueUsage(TrackedReferenceValue value, AbstractInsnNode insn, int position) {
                value.getClass();
                insn.getClass();
                for (ReferenceValueDescriptor referenceValueDescriptor : value.getDescriptors()) {
                    if (!(referenceValueDescriptor instanceof CapturedVarDescriptor)) {
                        s22.a("Unexpected descriptor: ", referenceValueDescriptor);
                        return;
                    }
                    if (insn.getOpcode() == 89) {
                        ((CapturedVarDescriptor) referenceValueDescriptor).getWrapperInsns().add(insn);
                    } else if (insn.getOpcode() == 25) {
                        ((CapturedVarDescriptor) referenceValueDescriptor).getWrapperInsns().add(insn);
                    } else if (insn.getOpcode() == 58) {
                        ((CapturedVarDescriptor) referenceValueDescriptor).getWrapperInsns().add(insn);
                    } else if (insn.getOpcode() == 180 && (insn instanceof FieldInsnNode) && Intrinsics.areEqual(((FieldInsnNode) insn).name, "element") && position == 0) {
                        ((CapturedVarDescriptor) referenceValueDescriptor).getGetFieldInsns().add(insn);
                    } else if (insn.getOpcode() == 181 && (insn instanceof FieldInsnNode) && Intrinsics.areEqual(((FieldInsnNode) insn).name, "element") && position == 0) {
                        ((CapturedVarDescriptor) referenceValueDescriptor).getPutFieldInsns().add(insn);
                    } else {
                        if (insn.getOpcode() == 183 && (insn instanceof MethodInsnNode)) {
                            MethodInsnNode methodInsnNode = (MethodInsnNode) insn;
                            if (Intrinsics.areEqual(methodInsnNode.name, "<init>") && position == 0) {
                                CapturedVarDescriptor capturedVarDescriptor = (CapturedVarDescriptor) referenceValueDescriptor;
                                if (capturedVarDescriptor.getInitCallInsn() == null || Intrinsics.areEqual(capturedVarDescriptor.getInitCallInsn(), insn)) {
                                    capturedVarDescriptor.setInitCallInsn(methodInsnNode);
                                } else {
                                    capturedVarDescriptor.setHazard(true);
                                }
                            }
                        }
                        ((CapturedVarDescriptor) referenceValueDescriptor).setHazard(true);
                    }
                }
            }
        }

        public Transformer(String str, MethodNode methodNode) {
            str.getClass();
            methodNode.getClass();
            this.internalClassName = str;
            this.methodNode = methodNode;
            this.refValues = new ArrayList<>();
            this.refValuesByNewInsn = new LinkedHashMap<>();
        }

        public static boolean a(LocalVariableNode localVariableNode, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            return !Intrinsics.areEqual(abstractInsnNode, localVariableNode.end);
        }

        private final void assignLocalVars(Frame<BasicValue>[] frames) {
            for (LocalVariableNode localVariableNode : this.methodNode.localVariables) {
                if (AsmTypes.isSharedVarType(Type.getType(localVariableNode.desc))) {
                    LabelNode labelNode = localVariableNode.start;
                    labelNode.getClass();
                    Frame<BasicValue> frame = frames[getIndex(labelNode)];
                    if (frame != null) {
                        ProperTrackedReferenceValue local = frame.getLocal(localVariableNode.index);
                        ProperTrackedReferenceValue properTrackedReferenceValue = local instanceof ProperTrackedReferenceValue ? local : null;
                        if (properTrackedReferenceValue != null) {
                            ReferenceValueDescriptor descriptor = properTrackedReferenceValue.getDescriptor();
                            CapturedVarDescriptor capturedVarDescriptor = descriptor instanceof CapturedVarDescriptor ? (CapturedVarDescriptor) descriptor : null;
                            if (capturedVarDescriptor != null && !capturedVarDescriptor.getHazard()) {
                                if (capturedVarDescriptor.getLocalVar() == null) {
                                    capturedVarDescriptor.setLocalVar(localVariableNode);
                                } else {
                                    capturedVarDescriptor.setHazard(true);
                                }
                            }
                        }
                    }
                }
            }
            Iterator<CapturedVarDescriptor> it = this.refValues.iterator();
            it.getClass();
            while (it.hasNext()) {
                CapturedVarDescriptor next = it.next();
                next.getClass();
                CapturedVarDescriptor capturedVarDescriptor2 = next;
                if (!capturedVarDescriptor2.getHazard()) {
                    if (capturedVarDescriptor2.getLocalVar() == null || capturedVarDescriptor2.getValueType().getSize() != 1) {
                        capturedVarDescriptor2.setLocalVarIndex(this.methodNode.maxLocals);
                        this.methodNode.maxLocals += capturedVarDescriptor2.getValueType().getSize();
                    } else {
                        LocalVariableNode localVar = capturedVarDescriptor2.getLocalVar();
                        localVar.getClass();
                        capturedVarDescriptor2.setLocalVarIndex(localVar.index);
                    }
                }
            }
        }

        public static boolean b(LocalVariableNode localVariableNode, AbstractInsnNode abstractInsnNode) {
            AbstractInsnNode previous;
            abstractInsnNode.getClass();
            if (!(abstractInsnNode instanceof VarInsnNode)) {
                return false;
            }
            VarInsnNode varInsnNode = (VarInsnNode) abstractInsnNode;
            return varInsnNode.getOpcode() == 58 && varInsnNode.var == localVariableNode.index && (previous = varInsnNode.getPrevious()) != null && previous.getOpcode() == 1;
        }

        public static boolean c(LocalVariableNode localVariableNode, AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            return !Intrinsics.areEqual(abstractInsnNode, localVariableNode.start);
        }

        private final void createRefValues() {
            Type type;
            InsnList insnList = this.methodNode.instructions;
            insnList.getClass();
            Iterator it = InsnSequenceKt.asSequence(insnList).iterator();
            while (it.hasNext()) {
                TypeInsnNode typeInsnNode = (AbstractInsnNode) it.next();
                if (typeInsnNode.getOpcode() == 187 && (typeInsnNode instanceof TypeInsnNode)) {
                    TypeInsnNode typeInsnNode2 = typeInsnNode;
                    Type objectType = Type.getObjectType(typeInsnNode2.desc);
                    if (AsmTypes.isSharedVarType(objectType) && (type = (Type) CapturedVarsOptimizationMethodTransformerKt.getREF_TYPE_TO_ELEMENT_TYPE().get(objectType.getInternalName())) != null) {
                        CapturedVarDescriptor capturedVarDescriptor = new CapturedVarDescriptor(typeInsnNode2, objectType, type);
                        this.refValues.add(capturedVarDescriptor);
                        this.refValuesByNewInsn.put(typeInsnNode, capturedVarDescriptor);
                    }
                }
            }
        }

        private final Sequence<AbstractInsnNode> findCleanInstructions(final LocalVariableNode localVariableNode) {
            InsnList insnList = this.methodNode.instructions;
            insnList.getClass();
            return SequencesKt.filter(SequencesKt.takeWhile(SequencesKt.dropWhile(new InsnSequence(insnList), new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.a
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CapturedVarsOptimizationMethodTransformer.Transformer.c(localVariableNode, (AbstractInsnNode) obj));
                }
            }), new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.b
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CapturedVarsOptimizationMethodTransformer.Transformer.a(localVariableNode, (AbstractInsnNode) obj));
                }
            }), new Function1() { // from class: org.jetbrains.kotlin.codegen.optimization.c
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CapturedVarsOptimizationMethodTransformer.Transformer.b(localVariableNode, (AbstractInsnNode) obj));
                }
            });
        }

        private final CapturedVarDescriptor getCapturedVarOrNull(BasicValue basicValue) {
            ProperTrackedReferenceValue properTrackedReferenceValue = basicValue instanceof ProperTrackedReferenceValue ? (ProperTrackedReferenceValue) basicValue : null;
            ReferenceValueDescriptor descriptor = properTrackedReferenceValue != null ? properTrackedReferenceValue.getDescriptor() : null;
            if (descriptor instanceof CapturedVarDescriptor) {
                return (CapturedVarDescriptor) descriptor;
            }
            return null;
        }

        private final int getIndex(AbstractInsnNode abstractInsnNode) {
            return this.methodNode.instructions.indexOf(abstractInsnNode);
        }

        private final void removeOrReplaceByNop(InsnList insnList, AbstractInsnNode abstractInsnNode) {
            if ((abstractInsnNode.getPrevious() instanceof LineNumberNode) && (abstractInsnNode.getNext() instanceof LabelNode) && (abstractInsnNode.getNext().getNext() instanceof LineNumberNode)) {
                insnList.set(abstractInsnNode, new InsnNode(0));
            } else {
                insnList.remove(abstractInsnNode);
            }
        }

        private final void rewriteRefValue(CapturedVarDescriptor capturedVar) {
            int index;
            LabelNode labelNode;
            InsnList insnList = this.methodNode.instructions;
            int opcode = capturedVar.getValueType().getOpcode(21);
            int opcode2 = capturedVar.getValueType().getOpcode(54);
            LocalVariableNode localVar = capturedVar.getLocalVar();
            if (localVar != null) {
                Collection<FieldInsnNode> putFieldInsns = capturedVar.getPutFieldInsns();
                if ((putFieldInsns instanceof Collection) && putFieldInsns.isEmpty()) {
                    insnList.insertBefore(capturedVar.getNewInsn(), new InsnNode(AsmUtil.defaultValueOpcode(capturedVar.getValueType())));
                    insnList.insertBefore(capturedVar.getNewInsn(), new VarInsnNode(opcode2, capturedVar.getLocalVarIndex()));
                    break;
                }
                Iterator<T> it = putFieldInsns.iterator();
                do {
                    if (!it.hasNext()) {
                        insnList.insertBefore(capturedVar.getNewInsn(), new InsnNode(AsmUtil.defaultValueOpcode(capturedVar.getValueType())));
                        insnList.insertBefore(capturedVar.getNewInsn(), new VarInsnNode(opcode2, capturedVar.getLocalVarIndex()));
                        break;
                    } else {
                        index = getIndex((FieldInsnNode) it.next());
                        labelNode = localVar.start;
                        labelNode.getClass();
                    }
                } while (index >= getIndex(labelNode));
                for (AbstractInsnNode abstractInsnNode : findCleanInstructions(localVar)) {
                    if (opcode2 == 58) {
                        insnList.set(abstractInsnNode.getPrevious(), new InsnNode(AsmUtil.defaultValueOpcode(capturedVar.getValueType())));
                    } else {
                        insnList.remove(abstractInsnNode.getPrevious());
                        insnList.remove(abstractInsnNode);
                    }
                }
                localVar.index = capturedVar.getLocalVarIndex();
                localVar.desc = capturedVar.getValueType().getDescriptor();
                localVar.signature = null;
            }
            insnList.remove(capturedVar.getNewInsn());
            MethodInsnNode initCallInsn = capturedVar.getInitCallInsn();
            initCallInsn.getClass();
            insnList.remove(initCallInsn);
            Iterator<T> it2 = capturedVar.getWrapperInsns().iterator();
            while (it2.hasNext()) {
                removeOrReplaceByNop(insnList, (AbstractInsnNode) it2.next());
            }
            Iterator<T> it3 = capturedVar.getGetFieldInsns().iterator();
            while (it3.hasNext()) {
                insnList.set((FieldInsnNode) it3.next(), new VarInsnNode(opcode, capturedVar.getLocalVarIndex()));
            }
            Iterator<T> it4 = capturedVar.getPutFieldInsns().iterator();
            while (it4.hasNext()) {
                insnList.set((FieldInsnNode) it4.next(), new VarInsnNode(opcode2, capturedVar.getLocalVarIndex()));
            }
        }

        private final void trackPops(Frame<BasicValue>[] frames) {
            CapturedVarDescriptor capturedVarOrNull;
            BasicValue basicValue;
            CapturedVarDescriptor capturedVarOrNull2;
            InsnList insnList = this.methodNode.instructions;
            insnList.getClass();
            int i = 0;
            for (AbstractInsnNode abstractInsnNode : InsnSequenceKt.asSequence(insnList)) {
                int i2 = i + 1;
                Frame<BasicValue> frame = frames[i];
                if (frame != null) {
                    int opcode = abstractInsnNode.getOpcode();
                    if (opcode == 87) {
                        BasicValue basicValue2 = (BasicValue) StackTransformationUtilsKt.top(frame);
                        if (basicValue2 != null && (capturedVarOrNull = getCapturedVarOrNull(basicValue2)) != null) {
                            capturedVarOrNull.getWrapperInsns().add(abstractInsnNode);
                        }
                    } else if (opcode == 88 && (basicValue = (BasicValue) StackTransformationUtilsKt.top(frame)) != null && basicValue.getSize() == 1) {
                        CapturedVarDescriptor capturedVarOrNull3 = getCapturedVarOrNull(basicValue);
                        if (capturedVarOrNull3 != null) {
                            capturedVarOrNull3.setHazard(true);
                        }
                        BasicValue basicValue3 = (BasicValue) StackTransformationUtilsKt.peek(frame, 1);
                        if (basicValue3 != null && (capturedVarOrNull2 = getCapturedVarOrNull(basicValue3)) != null) {
                            capturedVarOrNull2.setHazard(true);
                        }
                    }
                }
                i = i2;
            }
        }

        public final void run() {
            createRefValues();
            if (this.refValues.isEmpty()) {
                return;
            }
            Frame<BasicValue>[] frameArrAnalyze = MethodTransformer.analyze(this.internalClassName, this.methodNode, new Interpreter());
            frameArrAnalyze.getClass();
            trackPops(frameArrAnalyze);
            assignLocalVars(frameArrAnalyze);
            Iterator<CapturedVarDescriptor> it = this.refValues.iterator();
            it.getClass();
            while (it.hasNext()) {
                CapturedVarDescriptor next = it.next();
                next.getClass();
                CapturedVarDescriptor capturedVarDescriptor = next;
                if (capturedVarDescriptor.canRewrite()) {
                    rewriteRefValue(capturedVarDescriptor);
                }
            }
            UtilKt.removeEmptyCatchBlocks(this.methodNode);
            UtilKt.removeUnusedLocalVariables(this.methodNode);
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        new Transformer(internalClassName, methodNode).run();
    }
}
