package org.jetbrains.kotlin.descriptors.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\nJ\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/SyntheticFieldDescriptor;", "Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor;", "propertyDescriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "accessorDescriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyAccessorDescriptor;", "sourceElement", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lorg/jetbrains/kotlin/descriptors/PropertyAccessorDescriptor;Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "(Lorg/jetbrains/kotlin/descriptors/PropertyAccessorDescriptor;Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "getPropertyDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getDispatchReceiverParameter", Argument.Delimiters.none, "getDispatchReceiverForBackend", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValue;", "getDispatchReceiverParameterForBackend", "Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SyntheticFieldDescriptor extends LocalVariableDescriptor {
    public static final Name NAME;
    private final PropertyDescriptor propertyDescriptor;

    static {
        Name nameIdentifier = Name.identifier("field");
        nameIdentifier.getClass();
        NAME = nameIdentifier;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SyntheticFieldDescriptor(PropertyDescriptor propertyDescriptor, PropertyAccessorDescriptor propertyAccessorDescriptor, SourceElement sourceElement) {
        super(propertyAccessorDescriptor, Annotations.INSTANCE.getEMPTY(), NAME, propertyDescriptor.getType(), propertyDescriptor.isVar(), false, false, sourceElement);
        this.propertyDescriptor = propertyDescriptor;
    }

    public final ReceiverValue getDispatchReceiverForBackend() {
        ReceiverParameterDescriptor dispatchReceiverParameterForBackend = getDispatchReceiverParameterForBackend();
        if (dispatchReceiverParameterForBackend != null) {
            return dispatchReceiverParameterForBackend.getValue();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    /* JADX INFO: renamed from: getDispatchReceiverParameter, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ReceiverParameterDescriptor mo160getDispatchReceiverParameter() {
        return (ReceiverParameterDescriptor) getDispatchReceiverParameter();
    }

    public final ReceiverParameterDescriptor getDispatchReceiverParameterForBackend() {
        return this.propertyDescriptor.getDispatchReceiverParameter();
    }

    public final PropertyDescriptor getPropertyDescriptor() {
        return this.propertyDescriptor;
    }

    public Void getDispatchReceiverParameter() {
        return null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SyntheticFieldDescriptor(PropertyAccessorDescriptor propertyAccessorDescriptor, SourceElement sourceElement) {
        propertyAccessorDescriptor.getClass();
        sourceElement.getClass();
        PropertyDescriptor correspondingProperty = propertyAccessorDescriptor.getCorrespondingProperty();
        correspondingProperty.getClass();
        this(correspondingProperty, propertyAccessorDescriptor, sourceElement);
    }
}
