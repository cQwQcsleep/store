package org.jetbrains.kotlin.backend.jvm;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor$CopyBuilder;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0001H\u0016J\u0092\u0001\u0010\u000b\u001a\n \r*\u0004\u0018\u0001H\fH\f\"\u0010\b\u0000\u0010\f*\n \r*\u0004\u0018\u00010\u000e0\u000e\"\u0010\b\u0001\u0010\u000f*\n \r*\u0004\u0018\u00010\u000e0\u000e2F\u0010\u0010\u001aB\u0012\f\u0012\n \r*\u0004\u0018\u0001H\fH\f\u0012\f\u0012\n \r*\u0004\u0018\u0001H\u000fH\u000f \r* \u0012\f\u0012\n \r*\u0004\u0018\u0001H\fH\f\u0012\f\u0012\n \r*\u0004\u0018\u0001H\u000fH\u000f\u0018\u00010\u00110\u00112\u000e\u0010\u0012\u001a\n \r*\u0004\u0018\u0001H\u000fH\u000fH\u0096\u0001¢\u0006\u0002\u0010\u0013JQ\u0010\u0014\u001a\u00020\u00152F\u0010\u0010\u001aB\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00160\u0016\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00160\u0016 \r* \u0012\f\u0012\n \r*\u0004\u0018\u00010\u00160\u0016\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00160\u0016\u0018\u00010\u00110\u0011H\u0096\u0001J1\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006H\u0096\u0001J\u0012\u0010!\u001a\u00070\"¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J\t\u0010%\u001a\u00020\"H\u0096\u0001J$\u0010&\u001a\u0015\u0012\f\u0012\n \r*\u0004\u0018\u00010(0(0'¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$b\u0002\b)J\u0014\u0010*\u001a\t\u0018\u00010(¢\u0006\u0002\b#H\u0097\u0001b\u0002\b+J\u0014\u0010,\u001a\t\u0018\u00010(¢\u0006\u0002\b#H\u0097\u0001b\u0002\b+J\u0014\u0010-\u001a\t\u0018\u00010.¢\u0006\u0002\b#H\u0097\u0001b\u0002\b+J\u0012\u0010/\u001a\u00070\u001f¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J\u0012\u00100\u001a\u00070\u001b¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J\u0012\u00101\u001a\u000702¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J=\u00103\u001a2\u0012\u000e\b\u0001\u0012\n \r*\u0004\u0018\u00010.0. \r*\u0017\u0012\u000e\b\u0001\u0012\n \r*\u0004\u0018\u00010.0.05¢\u0006\u0002\b#04¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J\u0012\u00106\u001a\u000707¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J \u00108\u001a\u0015\u0012\f\u0012\n \r*\u0004\u0018\u000109090'¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$JW\u0010:\u001a\t\u0018\u0001H;¢\u0006\u0002\b#\"\u0010\b\u0000\u0010;*\n \r*\u0004\u0018\u00010\u000e0\u000e2*\u0010\u0010\u001a&\u0012\f\u0012\n \r*\u0004\u0018\u0001H;H; \r*\u0012\u0012\f\u0012\n \r*\u0004\u0018\u0001H;H;\u0018\u00010<0<H\u0097\u0001b\u0002\b+¢\u0006\u0002\u0010=J9\u0010>\u001a.\u0012\f\u0012\n \r*\u0004\u0018\u00010@0@ \r*\u0015\u0012\f\u0012\n \r*\u0004\u0018\u00010@0@0'¢\u0006\u0002\b#0?¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J\u0012\u0010A\u001a\u00070\u001d¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$J\t\u0010B\u001a\u00020\u0006H\u0096\u0001J\t\u0010C\u001a\u00020\u0006H\u0096\u0001J\t\u0010D\u001a\u00020\u0006H\u0096\u0001J\t\u0010E\u001a\u00020\u0006H\u0096\u0001J\t\u0010F\u001a\u00020\u0006H\u0096\u0001J\t\u0010G\u001a\u00020\u0006H\u0096\u0001J\t\u0010H\u001a\u00020\u0006H\u0096\u0001J\t\u0010I\u001a\u00020\u0006H\u0096\u0001J\t\u0010J\u001a\u00020\u0006H\u0096\u0001J\t\u0010K\u001a\u00020\u0006H\u0096\u0001J\t\u0010L\u001a\u00020\u0006H\u0096\u0001J\"\u0010M\u001a\u0017\u0012\u000e\b\u0001\u0012\n \r*\u0004\u0018\u00010.0.0N¢\u0006\u0002\b#H\u0097\u0001b\u0002\b$JG\u0010O\u001a\u00020\u00152<\b\u0001\u0010\u0010\u001a2\u0012\u000e\b\u0001\u0012\n \r*\u0004\u0018\u00010P0P \r*\u0017\u0012\u000e\b\u0001\u0012\n \r*\u0004\u0018\u00010P0P05¢\u0006\u0002\b#04¢\u0006\u0002\b#:\u0002\b$H\u0096\u0001J\u0013\u0010Q\u001a\u0004\u0018\u00010\u00012\u0006\u0010R\u001a\u00020SH\u0096\u0001R\u0012\u0010T\u001a\u00020UX\u0096\u0005¢\u0006\u0006\u001a\u0004\bV\u0010W¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/FakeActualClassConstructorDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "original", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;)V", "isActual", Argument.Delimiters.none, "isExpect", "getSource", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "getOriginal", "accept", "R", "kotlin.jvm.PlatformType", Argument.Delimiters.none, "D", "p0", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "p1", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "acceptVoid", Argument.Delimiters.none, "Ljava/lang/Void;", "copy", "newOwner", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "kind", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "copyOverrides", "getConstructedClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/annotations/NotNull;", "getContainingDeclaration", "getContextReceiverParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "Lkotlin/annotations/jvm/ReadOnly;", "getDispatchReceiverParameter", "Lorg/jetbrains/annotations/Nullable;", "getExtensionReceiverParameter", "getInitialSignatureDescriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "getKind", "getModality", "getName", "Lorg/jetbrains/kotlin/name/Name;", "getOverriddenDescriptors", Argument.Delimiters.none, Argument.Delimiters.none, "getReturnType", "Lorg/jetbrains/kotlin/types/KotlinType;", "getTypeParameters", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "getUserData", "V", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor$UserDataKey;", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor$UserDataKey;)Ljava/lang/Object;", "getValueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;", "getVisibility", "hasStableParameterNames", "hasSynthesizedParameterNames", "isExternal", "isHiddenForResolutionEverywhereBesideSupercalls", "isHiddenToOvercomeSignatureClash", "isInfix", "isInline", "isOperator", "isPrimary", "isSuspend", "isTailrec", "newCopyBuilder", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor$CopyBuilder;", "setOverriddenDescriptors", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "substitute", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "getAnnotations", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "org.jetbrains.kotlin:backend.jvm.entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FakeActualClassConstructorDescriptor implements ClassConstructorDescriptor {
    private final /* synthetic */ ClassConstructorDescriptor $$delegate_0;

    public FakeActualClassConstructorDescriptor(ClassConstructorDescriptor classConstructorDescriptor) {
        classConstructorDescriptor.getClass();
        this.$$delegate_0 = classConstructorDescriptor;
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> p0, D p1) {
        return (R) this.$$delegate_0.accept(p0, p1);
    }

    public void acceptVoid(DeclarationDescriptorVisitor<Void, Void> p0) {
        this.$$delegate_0.acceptVoid(p0);
    }

    @Override // org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
    /* JADX INFO: renamed from: copy, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ClassConstructorDescriptor m4copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        newOwner.getClass();
        modality.getClass();
        visibility.getClass();
        kind.getClass();
        return this.$$delegate_0.m4copy(newOwner, modality, visibility, kind, copyOverrides);
    }

    public Annotations getAnnotations() {
        return this.$$delegate_0.getAnnotations();
    }

    public ClassDescriptor getConstructedClass() {
        ClassDescriptor constructedClass = this.$$delegate_0.getConstructedClass();
        constructedClass.getClass();
        return constructedClass;
    }

    @Override // org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
    /* JADX INFO: renamed from: getContainingDeclaration, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ClassDescriptor m6getContainingDeclaration() {
        return this.$$delegate_0.m6getContainingDeclaration();
    }

    public List<ReceiverParameterDescriptor> getContextReceiverParameters() {
        List<ReceiverParameterDescriptor> contextReceiverParameters = this.$$delegate_0.getContextReceiverParameters();
        contextReceiverParameters.getClass();
        return contextReceiverParameters;
    }

    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.$$delegate_0.getDispatchReceiverParameter();
    }

    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.$$delegate_0.getExtensionReceiverParameter();
    }

    public FunctionDescriptor getInitialSignatureDescriptor() {
        return this.$$delegate_0.getInitialSignatureDescriptor();
    }

    public CallableMemberDescriptor.Kind getKind() {
        CallableMemberDescriptor.Kind kind = this.$$delegate_0.getKind();
        kind.getClass();
        return kind;
    }

    public Modality getModality() {
        Modality modality = this.$$delegate_0.getModality();
        modality.getClass();
        return modality;
    }

    public Name getName() {
        Name name = this.$$delegate_0.getName();
        name.getClass();
        return name;
    }

    public Collection<? extends FunctionDescriptor> getOverriddenDescriptors() {
        Collection<? extends FunctionDescriptor> overriddenDescriptors = this.$$delegate_0.getOverriddenDescriptors();
        overriddenDescriptors.getClass();
        return overriddenDescriptors;
    }

    public KotlinType getReturnType() {
        KotlinType returnType = this.$$delegate_0.getReturnType();
        returnType.getClass();
        return returnType;
    }

    public SourceElement getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        sourceElement.getClass();
        return sourceElement;
    }

    public List<TypeParameterDescriptor> getTypeParameters() {
        List<TypeParameterDescriptor> typeParameters = this.$$delegate_0.getTypeParameters();
        typeParameters.getClass();
        return typeParameters;
    }

    public <V> V getUserData(CallableDescriptor.UserDataKey<V> p0) {
        return (V) this.$$delegate_0.getUserData(p0);
    }

    public List<ValueParameterDescriptor> getValueParameters() {
        List<ValueParameterDescriptor> valueParameters = this.$$delegate_0.getValueParameters();
        valueParameters.getClass();
        return valueParameters;
    }

    public DescriptorVisibility getVisibility() {
        DescriptorVisibility visibility = this.$$delegate_0.getVisibility();
        visibility.getClass();
        return visibility;
    }

    public boolean hasStableParameterNames() {
        return this.$$delegate_0.hasStableParameterNames();
    }

    public boolean hasSynthesizedParameterNames() {
        return this.$$delegate_0.hasSynthesizedParameterNames();
    }

    public boolean isActual() {
        return true;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isExternal() {
        return this.$$delegate_0.isExternal();
    }

    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return this.$$delegate_0.isHiddenForResolutionEverywhereBesideSupercalls();
    }

    public boolean isHiddenToOvercomeSignatureClash() {
        return this.$$delegate_0.isHiddenToOvercomeSignatureClash();
    }

    public boolean isInfix() {
        return this.$$delegate_0.isInfix();
    }

    public boolean isInline() {
        return this.$$delegate_0.isInline();
    }

    public boolean isOperator() {
        return this.$$delegate_0.isOperator();
    }

    public boolean isPrimary() {
        return this.$$delegate_0.isPrimary();
    }

    public boolean isSuspend() {
        return this.$$delegate_0.isSuspend();
    }

    public boolean isTailrec() {
        return this.$$delegate_0.isTailrec();
    }

    public FunctionDescriptor$CopyBuilder<? extends FunctionDescriptor> newCopyBuilder() {
        FunctionDescriptor$CopyBuilder<? extends FunctionDescriptor> functionDescriptor$CopyBuilderNewCopyBuilder = this.$$delegate_0.newCopyBuilder();
        functionDescriptor$CopyBuilderNewCopyBuilder.getClass();
        return functionDescriptor$CopyBuilderNewCopyBuilder;
    }

    public void setOverriddenDescriptors(Collection<? extends CallableMemberDescriptor> p0) {
        p0.getClass();
        this.$$delegate_0.setOverriddenDescriptors(p0);
    }

    @Override // org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
    /* JADX INFO: renamed from: substitute, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ClassConstructorDescriptor m16substitute(TypeSubstitutor substitutor) {
        substitutor.getClass();
        return this.$$delegate_0.m16substitute(substitutor);
    }

    @Override // org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ClassConstructorDescriptor m12getOriginal() {
        return this;
    }
}
