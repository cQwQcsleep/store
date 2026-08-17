package org.jetbrains.kotlin.descriptors.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.VariableAccessorDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0011\u0012B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0002\u0013\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableAccessorDescriptor;", "Lorg/jetbrains/kotlin/descriptors/impl/SimpleFunctionDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/VariableAccessorDescriptor;", "correspondingVariable", "Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor;", "isGetter", "", "<init>", "(Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor;Z)V", "getCorrespondingVariable", "()Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor;", "createValueParameter", "Lorg/jetbrains/kotlin/descriptors/impl/ValueParameterDescriptorImpl;", "name", "Lorg/jetbrains/kotlin/name/Name;", "type", "Lorg/jetbrains/kotlin/types/KotlinType;", "Getter", "Setter", "Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableAccessorDescriptor$Getter;", "Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableAccessorDescriptor$Setter;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class LocalVariableAccessorDescriptor extends SimpleFunctionDescriptorImpl implements VariableAccessorDescriptor {
    private final LocalVariableDescriptor correspondingVariable;

    private LocalVariableAccessorDescriptor(LocalVariableDescriptor localVariableDescriptor, boolean z) {
        List listListOf;
        DeclarationDescriptor containingDeclaration = localVariableDescriptor.getContainingDeclaration();
        Annotations empty = Annotations.Companion.getEMPTY();
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "<get-" : "<set-");
        sb.append(localVariableDescriptor.getName());
        sb.append('>');
        super(containingDeclaration, (SimpleFunctionDescriptor) null, empty, Name.special(sb.toString()), CallableMemberDescriptor.Kind.SYNTHESIZED, SourceElement.NO_SOURCE);
        this.correspondingVariable = localVariableDescriptor;
        if (z) {
            listListOf = CollectionsKt.emptyList();
        } else {
            Name nameIdentifier = Name.identifier("value");
            nameIdentifier.getClass();
            KotlinType type = localVariableDescriptor.getType();
            type.getClass();
            listListOf = CollectionsKt.listOf(createValueParameter(nameIdentifier, type));
        }
        List list = listListOf;
        KotlinType type2 = z ? localVariableDescriptor.getType() : DescriptorUtilsKt.getBuiltIns(localVariableDescriptor).getUnitType();
        type2.getClass();
        initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), list, type2, Modality.FINAL, DescriptorVisibilities.LOCAL);
    }

    private final ValueParameterDescriptorImpl createValueParameter(Name name, KotlinType type) {
        Annotations empty = Annotations.Companion.getEMPTY();
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        sourceElement.getClass();
        return new ValueParameterDescriptorImpl(this, (ValueParameterDescriptor) null, 0, empty, name, type, false, false, false, (KotlinType) null, sourceElement);
    }

    public /* bridge */ /* synthetic */ VariableDescriptorWithAccessors getCorrespondingVariable() {
        return this.correspondingVariable;
    }

    public final LocalVariableDescriptor getCorrespondingVariable() {
        return this.correspondingVariable;
    }

    public /* synthetic */ LocalVariableAccessorDescriptor(LocalVariableDescriptor localVariableDescriptor, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(localVariableDescriptor, z);
    }
}
