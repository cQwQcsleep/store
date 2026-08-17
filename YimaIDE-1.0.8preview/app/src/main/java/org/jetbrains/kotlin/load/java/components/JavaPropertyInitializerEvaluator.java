package org.jetbrains.kotlin.load.java.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.load.java.structure.JavaField;
import org.jetbrains.kotlin.resolve.constants.ConstantValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\bJ\u001e\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/java/components/JavaPropertyInitializerEvaluator;", "", "getInitializerConstant", "Lorg/jetbrains/kotlin/resolve/constants/ConstantValue;", "field", "Lorg/jetbrains/kotlin/load/java/structure/JavaField;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "DoNothing", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface JavaPropertyInitializerEvaluator {

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/load/java/components/JavaPropertyInitializerEvaluator$DoNothing;", "Lorg/jetbrains/kotlin/load/java/components/JavaPropertyInitializerEvaluator;", "<init>", "()V", "getInitializerConstant", "Lorg/jetbrains/kotlin/resolve/constants/ConstantValue;", "field", "Lorg/jetbrains/kotlin/load/java/structure/JavaField;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DoNothing implements JavaPropertyInitializerEvaluator {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
        }

        @Override // org.jetbrains.kotlin.load.java.components.JavaPropertyInitializerEvaluator
        public ConstantValue<?> getInitializerConstant(JavaField field, PropertyDescriptor descriptor) {
            field.getClass();
            descriptor.getClass();
            return null;
        }
    }

    ConstantValue<?> getInitializerConstant(JavaField field, PropertyDescriptor descriptor);
}
