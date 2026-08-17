package org.jetbrains.kotlin.codegen.inline;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.MethodInliner$doInline$lambdaInliner$1;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformerKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.commons.LocalVariablesSorter;
import org.jetbrains.org.objectweb.asm.commons.MethodRemapper;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\u0007H\u0002J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J0\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"org/jetbrains/kotlin/codegen/inline/MethodInliner$doInline$lambdaInliner$1", "Lorg/jetbrains/kotlin/codegen/inline/InlineAdapter;", "transformationInfo", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "currentLabel", "Lorg/jetbrains/org/objectweb/asm/Label;", "visitLabel", Argument.Delimiters.none, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "visitLineNumber", "line", Argument.Delimiters.none, "start", "handleAnonymousObjectRegeneration", "anew", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "visitMethodInsn", "opcode", "owner", Argument.Delimiters.none, ModuleXmlParser.NAME, "desc", "itf", Argument.Delimiters.none, "visitFieldInsn", "visitMaxs", "stack", "locals", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MethodInliner$doInline$lambdaInliner$1 extends InlineAdapter {
    final /* synthetic */ LinkedList<InvokeCall> $currentInvokes;
    final /* synthetic */ Ref.IntRef $currentLineNumber;
    final /* synthetic */ String $fakeContinuationName;
    final /* synthetic */ Iterator<TransformationInfo> $iterator;
    final /* synthetic */ LocalVariablesSorter $localVariablesSorter;
    final /* synthetic */ int $markerShift;
    final /* synthetic */ MethodNode $node;
    final /* synthetic */ TypeRemapper $remapper;
    final /* synthetic */ MethodNode $resultNode;
    private Label currentLabel;
    final /* synthetic */ MethodInliner this$0;
    private TransformationInfo transformationInfo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MethodInliner$doInline$lambdaInliner$1(MethodRemapper methodRemapper, MethodInliner methodInliner, Ref.IntRef intRef, Iterator<? extends TransformationInfo> it, TypeRemapper typeRemapper, String str, LinkedList<InvokeCall> linkedList, int i, MethodNode methodNode, LocalVariablesSorter localVariablesSorter, MethodNode methodNode2, int i2, SourceMapCopier sourceMapCopier) {
        super(methodRemapper, i2, sourceMapCopier);
        this.this$0 = methodInliner;
        this.$currentLineNumber = intRef;
        this.$iterator = it;
        this.$remapper = typeRemapper;
        this.$fakeContinuationName = str;
        this.$currentInvokes = linkedList;
        this.$markerShift = i;
        this.$node = methodNode;
        this.$localVariablesSorter = localVariablesSorter;
        this.$resultNode = methodNode2;
    }

    public static String a(FunctionalArgument functionalArgument) {
        return "Lambda inlining " + ((LambdaInfo) functionalArgument).getLambdaClassType().getInternalName();
    }

    public static LineNumberNode b(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode instanceof LineNumberNode) {
            return (LineNumberNode) abstractInsnNode;
        }
        return null;
    }

    private final void handleAnonymousObjectRegeneration() {
        TransformationInfo transformationInfo;
        TransformationInfo next = this.$iterator.next();
        this.transformationInfo = next;
        next.getClass();
        String oldClassName = next.getOldClassName();
        InliningContext parent = this.this$0.inliningContext.getParent();
        if (Intrinsics.areEqual((parent == null || (transformationInfo = parent.getTransformationInfo()) == null) ? null : transformationInfo.getOldClassName(), oldClassName)) {
            return;
        }
        TransformationInfo transformationInfo2 = this.transformationInfo;
        transformationInfo2.getClass();
        if (!transformationInfo2.shouldRegenerate(this.this$0.isSameModule)) {
            this.this$0.result.addNotChangedClass(oldClassName);
            return;
        }
        TransformationInfo transformationInfo3 = this.transformationInfo;
        transformationInfo3.getClass();
        String newClassName = transformationInfo3.getNewClassName();
        this.$remapper.addMapping(oldClassName, newClassName);
        InliningContext inliningContext = this.this$0.inliningContext;
        NameGenerator nameGenerator = this.this$0.inliningContext.getNameGenerator();
        HashMap map = this.this$0.currentTypeMapping;
        InlineCallSiteInfo inlineCallSiteInfo = this.this$0.inlineCallSiteInfo;
        TransformationInfo transformationInfo4 = this.transformationInfo;
        transformationInfo4.getClass();
        RegeneratedClassContext regeneratedClassContextSubInlineWithClassRegeneration = inliningContext.subInlineWithClassRegeneration(nameGenerator, map, inlineCallSiteInfo, transformationInfo4);
        TransformationInfo transformationInfo5 = this.transformationInfo;
        transformationInfo5.getClass();
        InlineResult inlineResultDoTransform = transformationInfo5.createTransformer(regeneratedClassContextSubInlineWithClassRegeneration, this.this$0.isSameModule, this.$fakeContinuationName).doTransform(this.this$0.nodeRemapper);
        Map<String, String> changedTypes = inlineResultDoTransform.getChangedTypes();
        TypeRemapper typeRemapper = this.$remapper;
        for (Map.Entry<String, String> entry : changedTypes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringsKt.contains$default(value, InlineCodegenUtilsKt.INLINE_CALL_TRANSFORMATION_SUFFIX, false, 2, (Object) null) && !StringsKt.contains$default(key, InlineCodegenUtilsKt.INLINE_CALL_TRANSFORMATION_SUFFIX, false, 2, (Object) null) && InlineCodegenUtilsKt.isAnonymousClass(key) && !typeRemapper.hasNoAdditionalMapping(key)) {
                typeRemapper.addMapping(key, value);
            }
        }
        this.this$0.result.merge(inlineResultDoTransform);
        this.this$0.result.addChangedType(oldClassName, newClassName);
        if (this.this$0.inliningContext.isInliningLambda() && !(this.this$0.inliningContext.getLambdaInfo() instanceof DefaultLambda)) {
            TransformationInfo transformationInfo6 = this.transformationInfo;
            transformationInfo6.getClass();
            if (transformationInfo6.canRemoveAfterTransformation() && !this.this$0.inliningContext.getRoot().getState().getGlobalInlineContext().isTypeFromInlineFunction(oldClassName)) {
                this.this$0.result.addClassToRemove(oldClassName);
            }
        }
        if (inlineResultDoTransform.getReifiedTypeParametersUsages().wereUsedReifiedParameters()) {
            ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
            MethodVisitor methodVisitor = ((MethodVisitor) this).mv;
            methodVisitor.getClass();
            companion.putNeedClassReificationMarker(methodVisitor);
            this.this$0.result.getReifiedTypeParametersUsages().mergeAll(inlineResultDoTransform.getReifiedTypeParametersUsages());
        }
        Iterator<ClassBuilder> it = regeneratedClassContextSubInlineWithClassRegeneration.getContinuationBuilders().values().iterator();
        while (it.hasNext()) {
            it.next().done(this.this$0.inliningContext.getState().getConfig().getGenerateSmapCopyToAnnotation());
        }
    }

    /* JADX WARN: Code duplicated, block: B:6:0x001d  */
    public void anew(Type type) {
        type.getClass();
        String internalName = type.getInternalName();
        internalName.getClass();
        if (InlineCodegenUtilsKt.isSamWrapper(internalName)) {
            handleAnonymousObjectRegeneration();
        } else {
            String internalName2 = type.getInternalName();
            internalName2.getClass();
            if (InlineCodegenUtilsKt.isAnonymousClass(internalName2)) {
                handleAnonymousObjectRegeneration();
            }
        }
        super.anew(type);
    }

    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        owner.getClass();
        name.getClass();
        desc.getClass();
        if (opcode == 178 && (InlineCodegenUtilsKt.isAnonymousSingletonLoad(owner, name) || InlineCodegenUtilsKt.isWhenMappingAccess(owner, name))) {
            handleAnonymousObjectRegeneration();
        }
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    public void visitLabel(Label label) {
        this.currentLabel = label;
        super.visitLabel(label);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.InlineAdapter
    public void visitLineNumber(int line, Label start) {
        start.getClass();
        if (!this.this$0.isInlineOnlyMethod) {
            this.$currentLineNumber.element = line;
        }
        super.visitLineNumber(line, start);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.InlineAdapter
    public void visitMaxs(int stack, int locals) {
        this.this$0.lambdasFinallyBlocks = this.$resultNode.tryCatchBlocks.size();
        super.visitMaxs(stack, locals);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) throws AnalyzerException {
        TransformationInfo transformationInfo;
        int i;
        owner.getClass();
        name.getClass();
        desc.getClass();
        if (!InlineCodegenUtilsKt.isInvokeOnLambda(owner, name)) {
            if (!InlineCodegenUtilsKt.isAnonymousConstructorCall(owner, name)) {
                if (!ReifiedTypeInliner.INSTANCE.isNeedClassReificationMarker(new MethodInsnNode(opcode, owner, name, desc, false))) {
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                    return;
                } else {
                    if (this.this$0.inliningContext.getShouldReifyTypeParametersInObjects()) {
                        return;
                    }
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                    return;
                }
            }
            TransformationInfo transformationInfo2 = this.transformationInfo;
            AnonymousObjectTransformationInfo anonymousObjectTransformationInfo = transformationInfo2 instanceof AnonymousObjectTransformationInfo ? (AnonymousObjectTransformationInfo) transformationInfo2 : null;
            if (anonymousObjectTransformationInfo == null) {
                throw new AssertionError("<init> call doesn't correspond to object transformation info for '" + owner + '.' + name + "': " + this.transformationInfo);
            }
            InliningContext parent = this.this$0.inliningContext.getParent();
            if (parent == null || (transformationInfo = parent.getTransformationInfo()) == null || !Intrinsics.areEqual(transformationInfo.getOldClassName(), anonymousObjectTransformationInfo.getOldClassName())) {
                transformationInfo = null;
            }
            AnonymousObjectTransformationInfo anonymousObjectTransformationInfo2 = (AnonymousObjectTransformationInfo) transformationInfo;
            AnonymousObjectTransformationInfo anonymousObjectTransformationInfo3 = anonymousObjectTransformationInfo2 == null ? anonymousObjectTransformationInfo : anonymousObjectTransformationInfo2;
            if (!anonymousObjectTransformationInfo3.shouldRegenerate(this.this$0.isSameModule)) {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
                return;
            }
            for (CapturedParamDesc capturedParamDesc : anonymousObjectTransformationInfo3.getAllRecapturedParameters()) {
                if (anonymousObjectTransformationInfo2 != null && Intrinsics.areEqual(capturedParamDesc.getFieldName(), "this")) {
                    Type objectType = Type.getObjectType(owner);
                    objectType.getClass();
                    capturedParamDesc = new CapturedParamDesc(objectType, "this$0", capturedParamDesc.getType(), false, 8, null);
                }
                String containingLambdaName = capturedParamDesc.getContainingLambdaName();
                String strFoldName = FieldRemapper.INSTANCE.foldName(capturedParamDesc.getFieldName());
                String descriptor = capturedParamDesc.getType().getDescriptor();
                descriptor.getClass();
                visitFieldInsn(178, containingLambdaName, strFoldName, descriptor);
            }
            super.visitMethodInsn(opcode, anonymousObjectTransformationInfo3.getNewClassName(), name, anonymousObjectTransformationInfo3.getNewConstructorDescriptor(), itf);
            if (this.this$0.inliningContext.getParent() instanceof RegeneratedClassContext) {
                ((RegeneratedClassContext) this.this$0.inliningContext.getParent()).getTypeRemapper().addAdditionalMappings(anonymousObjectTransformationInfo3.getOldClassName(), anonymousObjectTransformationInfo3.getNewClassName());
            }
            this.transformationInfo = null;
            return;
        }
        this.$currentInvokes.isEmpty();
        InvokeCall invokeCallRemove = this.$currentInvokes.remove();
        final FunctionalArgument functionalArgument = invokeCallRemove.functionalArgument;
        if (!(functionalArgument instanceof LambdaInfo)) {
            MethodVisitor methodVisitor = ((MethodVisitor) this).mv;
            methodVisitor.getClass();
            CoroutineTransformerKt.markNoinlineLambdaIfSuspend(methodVisitor, functionalArgument);
            super.visitMethodInsn(opcode, owner, name, desc, itf);
            return;
        }
        SimpleTypeMarker simpleTypeMarkerNullableAnyType = this.this$0.inliningContext.getTypeMapper().getTypeSystem().nullableAnyType();
        LambdaInfo lambdaInfo = (LambdaInfo) functionalArgument;
        Type[] argumentTypes = lambdaInfo.getInvokeMethod().getArgumentTypes();
        List<KotlinTypeMarker> invokeMethodParameters = lambdaInfo.getInvokeMethodParameters();
        int length = Type.getArgumentTypes(desc).length;
        if (length == argumentTypes.length) {
            invokeMethodParameters.size();
        }
        int iMax = Math.max(getNextLocalIndex(), this.$markerShift);
        int size = 0;
        for (Type type : argumentTypes) {
            size += type.getSize();
        }
        int size2 = iMax + size;
        for (int i2 = length - 1; -1 < i2; i2--) {
            Type type2 = argumentTypes[i2];
            StackValue.coerce(AsmTypes.OBJECT_TYPE, simpleTypeMarkerNullableAnyType, type2, invokeMethodParameters.get(i2), this, this.this$0.inliningContext.getTypeMapper());
            size2 -= type2.getSize();
            store(size2, type2);
        }
        if (argumentTypes.length == 0) {
            nop();
        }
        InsnList insnList = lambdaInfo.getNode().getNode().instructions;
        insnList.getClass();
        LineNumberNode lineNumberNode = (LineNumberNode) SequencesKt.firstOrNull(SequencesKt.mapNotNull(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: p1a
            public final Object invoke(Object obj) {
                return MethodInliner$doInline$lambdaInliner$1.b((AbstractInsnNode) obj);
            }
        }));
        int i3 = lineNumberNode != null ? lineNumberNode.line : -1;
        boolean z = functionalArgument instanceof DefaultLambda;
        if (z != this.this$0.isInlineOnlyMethod && (i = this.$currentLineNumber.element) >= 0 && i3 == i) {
            Label label = new Label();
            int iMapSyntheticLineNumber = this.this$0.sourceMapper.getParent().mapSyntheticLineNumber(1);
            ((MethodVisitor) this).mv.visitLabel(label);
            ((MethodVisitor) this).mv.visitLineNumber(iMapSyntheticLineNumber, label);
        }
        InlineCodegenUtilsKt.addInlineMarker(this, true);
        Parameters parametersAddAllParameters = lambdaInfo.addAllParameters(this.this$0.nodeRemapper);
        String internalName = lambdaInfo.getLambdaClassType().getInternalName();
        internalName.getClass();
        InlinedLambdaRemapper inlinedLambdaRemapper = new InlinedLambdaRemapper(internalName, this.this$0.nodeRemapper, parametersAddAllParameters, z && ((DefaultLambda) functionalArgument).getIsBoundCallableReference());
        setLambdaInlining(true);
        MethodInliner methodInliner = new MethodInliner(lambdaInfo.getNode().getNode(), parametersAddAllParameters, this.this$0.inliningContext.subInlineLambda(lambdaInfo), inlinedLambdaRemapper, z ? this.this$0.isSameModule : true, new Function0() { // from class: q1a
            public final Object invoke() {
                return MethodInliner$doInline$lambdaInliner$1.a(functionalArgument);
            }
        }, new SourceMapCopier(this.this$0.sourceMapper.getParent(), lambdaInfo.getNode().getClassSMAP(), z ? this.this$0.sourceMapper.getCallSite() : null), this.this$0.inlineCallSiteInfo, false, false, 0, 0, 3584, null);
        LocalVarRemapper localVarRemapper = new LocalVarRemapper(parametersAddAllParameters, size2);
        InlineScopesGenerator inlineScopesGenerator = this.this$0.inliningContext.getInlineScopesGenerator();
        Label label2 = this.currentLabel;
        int iIncrementScopeNumbersOfVariables = (inlineScopesGenerator == null || label2 == null || !this.this$0.isRegeneratingAnonymousObject()) ? 0 : MethodInlinerKt.incrementScopeNumbersOfVariables(this.$node, label2);
        if (inlineScopesGenerator != null) {
            MethodInliner methodInliner2 = this.this$0;
            Ref.IntRef intRef = this.$currentLineNumber;
            inlineScopesGenerator.setInlinedScopes(inlineScopesGenerator.getInlinedScopes() + iIncrementScopeNumbersOfVariables);
            inlineScopesGenerator.setCurrentCallSiteLineNumber(methodInliner2.isInlineOnlyMethod ? intRef.element : methodInliner2.sourceMapper.mapLineNumber(intRef.element));
        }
        InlineResult inlineResultDoInline = methodInliner.doInline(this.$localVariablesSorter, localVarRemapper, true, lambdaInfo.getReturnLabels(), invokeCallRemove.finallyDepthShift);
        if (inlineScopesGenerator != null) {
            inlineScopesGenerator.setInlinedScopes(inlineScopesGenerator.getInlinedScopes() - iIncrementScopeNumbersOfVariables);
        }
        this.this$0.result.mergeWithNotChangeInfo(inlineResultDoInline);
        this.this$0.result.getReifiedTypeParametersUsages().mergeAll(inlineResultDoInline.getReifiedTypeParametersUsages());
        this.this$0.result.getReifiedTypeParametersUsages().mergeAll(lambdaInfo.getReifiedTypeParametersUsages());
        StackValue.coerce(lambdaInfo.getInvokeMethod().getReturnType(), lambdaInfo.getNullableAnyType(), InstructionAdapter.OBJECT_TYPE, simpleTypeMarkerNullableAnyType, this, this.this$0.inliningContext.getTypeMapper());
        setLambdaInlining(false);
        InlineCodegenUtilsKt.addInlineMarker(this, false);
        if (this.$currentLineNumber.element != -1) {
            Label label3 = new Label();
            ((MethodVisitor) this).mv.visitLabel(label3);
            if (this.this$0.isInlineOnlyMethod) {
                ((MethodVisitor) this).mv.visitLineNumber(this.$currentLineNumber.element, label3);
            } else {
                super.visitLineNumber(this.$currentLineNumber.element, label3);
            }
        }
    }
}
