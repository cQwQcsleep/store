package org.jetbrains.kotlin.load.java.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.load.java.structure.JavaField;
import org.jetbrains.kotlin.resolve.constants.ConstantValue;
import org.jetbrains.kotlin.resolve.constants.ConstantValueFactory;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J#\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/load/java/components/JavaPropertyInitializerEvaluatorImpl;", "Lorg/jetbrains/kotlin/load/java/components/JavaPropertyInitializerEvaluator;", "<init>", "()V", "getInitializerConstant", "Lorg/jetbrains/kotlin/resolve/constants/ConstantValue;", "field", "Lorg/jetbrains/kotlin/load/java/structure/JavaField;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "convertLiteralValue", "value", "", "expectedType", "Lorg/jetbrains/kotlin/types/KotlinType;", "convertLiteralValue$org_jetbrains_kotlin_frontend_java", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaPropertyInitializerEvaluatorImpl implements JavaPropertyInitializerEvaluator {
    public static final JavaPropertyInitializerEvaluatorImpl INSTANCE = new JavaPropertyInitializerEvaluatorImpl();

    private JavaPropertyInitializerEvaluatorImpl() {
    }

    public final ConstantValue<?> convertLiteralValue$org_jetbrains_kotlin_frontend_java(Object value, KotlinType expectedType) {
        value.getClass();
        expectedType.getClass();
        return ((value instanceof Byte) || (value instanceof Short) || (value instanceof Integer) || (value instanceof Long)) ? ConstantValueFactory.INSTANCE.createIntegerConstantValue(((Number) value).longValue(), expectedType, false) : ConstantValueFactory.createConstantValue$default(ConstantValueFactory.INSTANCE, value, (ModuleDescriptor) null, 2, (Object) null);
    }

    @Override // org.jetbrains.kotlin.load.java.components.JavaPropertyInitializerEvaluator
    public ConstantValue<?> getInitializerConstant(JavaField field, PropertyDescriptor descriptor) {
        field.getClass();
        descriptor.getClass();
        Object initializerValue = field.getInitializerValue();
        if (initializerValue == null) {
            return null;
        }
        JavaPropertyInitializerEvaluatorImpl javaPropertyInitializerEvaluatorImpl = INSTANCE;
        KotlinType type = descriptor.getType();
        type.getClass();
        return javaPropertyInitializerEvaluatorImpl.convertLiteralValue$org_jetbrains_kotlin_frontend_java(initializerValue, type);
    }
}
