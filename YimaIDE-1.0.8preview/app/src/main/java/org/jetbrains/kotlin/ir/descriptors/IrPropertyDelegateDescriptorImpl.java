package org.jetbrains.kotlin.ir.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FieldDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameUtils;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/ir/descriptors/IrPropertyDelegateDescriptorImpl;", "Lorg/jetbrains/kotlin/ir/descriptors/IrDelegateDescriptorBase;", "Lorg/jetbrains/kotlin/ir/descriptors/IrPropertyDelegateDescriptor;", "correspondingProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "delegateType", "Lorg/jetbrains/kotlin/types/KotlinType;", "kPropertyType", "<init>", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)V", "getCorrespondingProperty", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getKPropertyType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrPropertyDelegateDescriptorImpl extends IrDelegateDescriptorBase implements IrPropertyDelegateDescriptor {
    private final PropertyDescriptor correspondingProperty;
    private final KotlinType kPropertyType;

    /* JADX WARN: Illegal instructions before constructor call */
    public IrPropertyDelegateDescriptorImpl(PropertyDescriptor propertyDescriptor, KotlinType kotlinType, KotlinType kotlinType2) {
        Annotations annotations;
        propertyDescriptor.getClass();
        kotlinType.getClass();
        kotlinType2.getClass();
        DeclarationDescriptor containingDeclaration = propertyDescriptor.getContainingDeclaration();
        containingDeclaration.getClass();
        Name name = propertyDescriptor.getName();
        name.getClass();
        Name namePropertyDelegateName = NameUtils.propertyDelegateName(name);
        FieldDescriptor delegateField = propertyDescriptor.getDelegateField();
        super(containingDeclaration, namePropertyDelegateName, kotlinType, (delegateField == null || (annotations = delegateField.getAnnotations()) == null) ? Annotations.Companion.getEMPTY() : annotations);
        this.correspondingProperty = propertyDescriptor;
        this.kPropertyType = kotlinType2;
    }

    @Override // org.jetbrains.kotlin.ir.descriptors.IrPropertyDelegateDescriptor
    public PropertyDescriptor getCorrespondingProperty() {
        return this.correspondingProperty;
    }

    @Override // org.jetbrains.kotlin.ir.descriptors.IrPropertyDelegateDescriptor
    public KotlinType getKPropertyType() {
        return this.kPropertyType;
    }
}
