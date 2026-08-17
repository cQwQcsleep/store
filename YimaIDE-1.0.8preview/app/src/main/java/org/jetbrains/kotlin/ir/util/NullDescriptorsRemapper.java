package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0006\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0006\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0006\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u0014H\u0016¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/NullDescriptorsRemapper;", "Lorg/jetbrains/kotlin/ir/util/DescriptorsRemapper;", "<init>", "()V", "remapDeclaredClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "descriptor", "remapDeclaredConstructor", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "remapDeclaredEnumEntry", "remapDeclaredField", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "remapDeclaredSimpleFunction", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "remapDeclaredProperty", "remapDeclaredTypeParameter", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "remapDeclaredVariable", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "remapDeclaredValueParameter", "Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NullDescriptorsRemapper implements DescriptorsRemapper {
    public static final NullDescriptorsRemapper INSTANCE = new NullDescriptorsRemapper();

    private NullDescriptorsRemapper() {
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public ClassDescriptor remapDeclaredClass(ClassDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public ClassConstructorDescriptor remapDeclaredConstructor(ClassConstructorDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public ClassDescriptor remapDeclaredEnumEntry(ClassDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public PropertyDescriptor remapDeclaredField(PropertyDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public PropertyDescriptor remapDeclaredProperty(PropertyDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public FunctionDescriptor remapDeclaredSimpleFunction(FunctionDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public TypeParameterDescriptor remapDeclaredTypeParameter(TypeParameterDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public ParameterDescriptor remapDeclaredValueParameter(ParameterDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.util.DescriptorsRemapper
    public VariableDescriptor remapDeclaredVariable(VariableDescriptor descriptor) {
        descriptor.getClass();
        return null;
    }
}
