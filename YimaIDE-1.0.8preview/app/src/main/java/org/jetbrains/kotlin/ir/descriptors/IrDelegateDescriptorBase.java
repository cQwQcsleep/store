package org.jetbrains.kotlin.ir.descriptors;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.constants.ConstantValue;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J5\u0010\u001a\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b\"\u0004\b\u0001\u0010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001c0\u001e2\u0006\u0010\u001f\u001a\u0002H\u001cH\u0016¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/ir/descriptors/IrDelegateDescriptorBase;", "Lorg/jetbrains/kotlin/descriptors/impl/PropertyDescriptorImpl;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "name", "Lorg/jetbrains/kotlin/name/Name;", "delegateType", "Lorg/jetbrains/kotlin/types/KotlinType;", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;)V", "setOutType", "", "outType", "getCompileTimeInitializer", "Lorg/jetbrains/kotlin/resolve/constants/ConstantValue;", "cleanCompileTimeInitializerCache", "getVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "substitute", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "isVar", "", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrDelegateDescriptorBase extends PropertyDescriptorImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrDelegateDescriptorBase(DeclarationDescriptor declarationDescriptor, Name name, KotlinType kotlinType, Annotations annotations) {
        super(declarationDescriptor, (PropertyDescriptor) null, annotations, Modality.FINAL, DescriptorVisibilities.PRIVATE, false, name, CallableMemberDescriptor.Kind.SYNTHESIZED, SourceElement.NO_SOURCE, false, false, false, false, false, true);
        declarationDescriptor.getClass();
        name.getClass();
        kotlinType.getClass();
        annotations.getClass();
        List listEmptyList = CollectionsKt.emptyList();
        ClassDescriptor classDescriptor = declarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) declarationDescriptor : null;
        setType(kotlinType, listEmptyList, classDescriptor != null ? classDescriptor.getThisAsReceiverParameter() : null, (ReceiverParameterDescriptor) null, CollectionsKt.emptyList());
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return visitor.visitPropertyDescriptor(this, data);
    }

    public void cleanCompileTimeInitializerCache() {
    }

    public ConstantValue<?> getCompileTimeInitializer() {
        return null;
    }

    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    public boolean isVar() {
        return false;
    }

    public final void setOutType(KotlinType outType) {
        super/*org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl*/.setOutType(outType);
    }

    /* JADX INFO: renamed from: substitute, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public PropertyDescriptor m381substitute(TypeSubstitutor substitutor) {
        substitutor.getClass();
        throw new UnsupportedOperationException("Property delegate descriptor shouldn't be substituted: " + this);
    }

    public /* synthetic */ IrDelegateDescriptorBase(DeclarationDescriptor declarationDescriptor, Name name, KotlinType kotlinType, Annotations annotations, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(declarationDescriptor, name, kotlinType, (i & 8) != 0 ? Annotations.Companion.getEMPTY() : annotations);
    }
}
