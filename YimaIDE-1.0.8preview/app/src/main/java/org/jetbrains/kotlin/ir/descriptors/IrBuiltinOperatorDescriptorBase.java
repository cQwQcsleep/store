package org.jetbrains.kotlin.ir.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorNonRootImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0015H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\rH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\n\u0010\"\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010#\u001a\u00020$H\u0016J)\u0010%\u001a\u0004\u0018\u0001H&\"\b\b\u0000\u0010&*\u00020'2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u0002H&\u0018\u00010)H\u0016¢\u0006\u0002\u0010*J\b\u0010+\u001a\u00020$H\u0016J\b\u0010,\u001a\u00020$H\u0016J\b\u0010-\u001a\u00020$H\u0016J\b\u0010.\u001a\u00020$H\u0016J\b\u0010/\u001a\u00020$H\u0016J\b\u00100\u001a\u00020$H\u0016J\b\u00101\u001a\u00020$H\u0016J\b\u00102\u001a\u00020$H\u0016J\b\u00103\u001a\u00020$H\u0016J\b\u00104\u001a\u00020$H\u0016J\b\u00105\u001a\u00020$H\u0016J8\u00106\u001a\u00020\u00172\b\u00107\u001a\u0004\u0018\u00010\u00042\b\u00108\u001a\u0004\u0018\u00010\u001f2\b\u00109\u001a\u0004\u0018\u00010\u001d2\b\u0010:\u001a\u0004\u0018\u00010!2\u0006\u0010;\u001a\u00020$H\u0016J\b\u0010<\u001a\u00020\u0017H\u0016JA\u0010=\u001a\u0002H>\"\n\b\u0000\u0010>*\u0004\u0018\u00010'\"\n\b\u0001\u0010?*\u0004\u0018\u00010'2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u0002H>\u0012\u0004\u0012\u0002H?0A2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010C¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/ir/descriptors/IrBuiltinOperatorDescriptorBase;", "Lorg/jetbrains/kotlin/descriptors/impl/DeclarationDescriptorNonRootImpl;", "Lorg/jetbrains/kotlin/ir/descriptors/IrBuiltinOperatorDescriptor;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "name", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;Lorg/jetbrains/kotlin/name/Name;)V", "getDispatchReceiverParameter", "Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "getExtensionReceiverParameter", "getContextReceiverParameters", "", "getOriginal", "Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;", "substitute", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "getOverriddenDescriptors", "", "setOverriddenDescriptors", "", "overriddenDescriptors", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "getTypeParameters", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "getVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getKind", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "getInitialSignatureDescriptor", "isExternal", "", "getUserData", "V", "", "key", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor$UserDataKey;", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor$UserDataKey;)Ljava/lang/Object;", "isHiddenForResolutionEverywhereBesideSupercalls", "isHiddenToOvercomeSignatureClash", "isInfix", "isInline", "isOperator", "isSuspend", "isTailrec", "isExpect", "isActual", "hasStableParameterNames", "hasSynthesizedParameterNames", "copy", "newOwner", "modality", "visibility", "kind", "copyOverrides", "newCopyBuilder", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrBuiltinOperatorDescriptorBase extends DeclarationDescriptorNonRootImpl implements IrBuiltinOperatorDescriptor {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrBuiltinOperatorDescriptorBase(DeclarationDescriptor declarationDescriptor, Name name) {
        super(declarationDescriptor, Annotations.Companion.getEMPTY(), name, SourceElement.NO_SOURCE);
        declarationDescriptor.getClass();
        name.getClass();
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return visitor.visitFunctionDescriptor(this, data);
    }

    public List<ReceiverParameterDescriptor> getContextReceiverParameters() {
        return CollectionsKt.emptyList();
    }

    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return null;
    }

    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return null;
    }

    public FunctionDescriptor getInitialSignatureDescriptor() {
        return null;
    }

    public CallableMemberDescriptor.Kind getKind() {
        return CallableMemberDescriptor.Kind.SYNTHESIZED;
    }

    public Modality getModality() {
        return Modality.FINAL;
    }

    public Collection<FunctionDescriptor> getOverriddenDescriptors() {
        return CollectionsKt.emptyList();
    }

    public List<TypeParameterDescriptor> getTypeParameters() {
        return CollectionsKt.emptyList();
    }

    public <V> V getUserData(CallableDescriptor.UserDataKey<V> key) {
        return null;
    }

    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PUBLIC;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    public boolean hasStableParameterNames() {
        return true;
    }

    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isExternal() {
        return false;
    }

    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return false;
    }

    public boolean isHiddenToOvercomeSignatureClash() {
        return false;
    }

    public boolean isInfix() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isOperator() {
        return false;
    }

    public boolean isSuspend() {
        return false;
    }

    public boolean isTailrec() {
        return false;
    }

    public Void setOverriddenDescriptors(Collection<? extends CallableMemberDescriptor> overriddenDescriptors) {
        overriddenDescriptors.getClass();
        throw new UnsupportedOperationException();
    }

    public FunctionDescriptor substitute(TypeSubstitutor substitutor) {
        substitutor.getClass();
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SimpleFunctionDescriptor m366getOriginal() {
        return this;
    }

    /* JADX INFO: renamed from: newCopyBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Void m369newCopyBuilder() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: renamed from: copy, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Void m362copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: renamed from: setOverriddenDescriptors, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ void m370setOverriddenDescriptors(Collection collection) {
        setOverriddenDescriptors((Collection<? extends CallableMemberDescriptor>) collection);
    }
}
