package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ScriptDescriptor;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u001cJ\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\rH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0004\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0004\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0004\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0004\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0004\u001a\u00020\u001bH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/DescriptorsRemapper;", "", "remapDeclaredClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "descriptor", "remapDeclaredScript", "Lorg/jetbrains/kotlin/descriptors/ScriptDescriptor;", "remapDeclaredConstructor", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "remapDeclaredEnumEntry", "remapDeclaredExternalPackageFragment", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "remapDeclaredField", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "remapDeclaredFilePackageFragment", "remapDeclaredProperty", "remapDeclaredSimpleFunction", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "remapDeclaredTypeParameter", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "remapDeclaredValueParameter", "Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;", "remapDeclaredVariable", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "remapDeclaredLocalDelegatedProperty", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "remapDeclaredTypeAlias", "Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;", "Default", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface DescriptorsRemapper {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/DescriptorsRemapper$Default;", "Lorg/jetbrains/kotlin/ir/util/DescriptorsRemapper;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Default implements DescriptorsRemapper {
        public static final Default INSTANCE = new Default();

        private Default() {
        }
    }

    default ClassDescriptor remapDeclaredClass(ClassDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default ClassConstructorDescriptor remapDeclaredConstructor(ClassConstructorDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default ClassDescriptor remapDeclaredEnumEntry(ClassDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default PackageFragmentDescriptor remapDeclaredExternalPackageFragment(PackageFragmentDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default PropertyDescriptor remapDeclaredField(PropertyDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default PackageFragmentDescriptor remapDeclaredFilePackageFragment(PackageFragmentDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default VariableDescriptorWithAccessors remapDeclaredLocalDelegatedProperty(VariableDescriptorWithAccessors descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default PropertyDescriptor remapDeclaredProperty(PropertyDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default ScriptDescriptor remapDeclaredScript(ScriptDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default FunctionDescriptor remapDeclaredSimpleFunction(FunctionDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default TypeAliasDescriptor remapDeclaredTypeAlias(TypeAliasDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default TypeParameterDescriptor remapDeclaredTypeParameter(TypeParameterDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default ParameterDescriptor remapDeclaredValueParameter(ParameterDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }

    default VariableDescriptor remapDeclaredVariable(VariableDescriptor descriptor) {
        descriptor.getClass();
        return descriptor;
    }
}
