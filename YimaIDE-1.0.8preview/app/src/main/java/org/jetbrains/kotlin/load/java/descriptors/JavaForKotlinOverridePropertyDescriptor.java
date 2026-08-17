package org.jetbrains.kotlin.load.java.descriptors;

import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/load/java/descriptors/JavaForKotlinOverridePropertyDescriptor;", "Lorg/jetbrains/kotlin/load/java/descriptors/JavaPropertyDescriptor;", "ownerDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getterMethod", "Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;", "setterMethod", "overriddenProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "getGetterMethod", "()Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;", "getSetterMethod", "getOverriddenProperty", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaForKotlinOverridePropertyDescriptor extends JavaPropertyDescriptor {
    private final SimpleFunctionDescriptor getterMethod;
    private final PropertyDescriptor overriddenProperty;
    private final SimpleFunctionDescriptor setterMethod;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaForKotlinOverridePropertyDescriptor(ClassDescriptor classDescriptor, SimpleFunctionDescriptor simpleFunctionDescriptor, SimpleFunctionDescriptor simpleFunctionDescriptor2, PropertyDescriptor propertyDescriptor) {
        super(classDescriptor, Annotations.Companion.getEMPTY(), simpleFunctionDescriptor.getModality(), simpleFunctionDescriptor.getVisibility(), simpleFunctionDescriptor2 != null, propertyDescriptor.getName(), simpleFunctionDescriptor.getSource(), (PropertyDescriptor) null, CallableMemberDescriptor.Kind.DECLARATION, false, (Pair) null);
        classDescriptor.getClass();
        simpleFunctionDescriptor.getClass();
        propertyDescriptor.getClass();
        this.getterMethod = simpleFunctionDescriptor;
        this.setterMethod = simpleFunctionDescriptor2;
        this.overriddenProperty = propertyDescriptor;
    }

    public final SimpleFunctionDescriptor getGetterMethod() {
        return this.getterMethod;
    }

    public final PropertyDescriptor getOverriddenProperty() {
        return this.overriddenProperty;
    }

    public final SimpleFunctionDescriptor getSetterMethod() {
        return this.setterMethod;
    }
}
