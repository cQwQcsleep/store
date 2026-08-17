package org.jetbrains.kotlin.cfg;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.psi.KtClassOrObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0004\u0006\u0007\b\tR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor;", "", "classOrObject", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "getClassOrObject", "()Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "PropertyIsNull", "NonFinalClass", "NonFinalProperty", "NonFinalFunction", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface LeakingThisDescriptor {

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor$NonFinalClass;", "Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor;", "klass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "classOrObject", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;Lorg/jetbrains/kotlin/psi/KtClassOrObject;)V", "getKlass", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getClassOrObject", "()Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class NonFinalClass implements LeakingThisDescriptor {
        private final KtClassOrObject classOrObject;
        private final ClassDescriptor klass;

        public NonFinalClass(ClassDescriptor classDescriptor, KtClassOrObject ktClassOrObject) {
            classDescriptor.getClass();
            ktClassOrObject.getClass();
            this.klass = classDescriptor;
            this.classOrObject = ktClassOrObject;
        }

        public static /* synthetic */ NonFinalClass copy$default(NonFinalClass nonFinalClass, ClassDescriptor classDescriptor, KtClassOrObject ktClassOrObject, int i, Object obj) {
            if ((i & 1) != 0) {
                classDescriptor = nonFinalClass.klass;
            }
            if ((i & 2) != 0) {
                ktClassOrObject = nonFinalClass.classOrObject;
            }
            return nonFinalClass.copy(classDescriptor, ktClassOrObject);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ClassDescriptor getKlass() {
            return this.klass;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final NonFinalClass copy(ClassDescriptor klass, KtClassOrObject classOrObject) {
            klass.getClass();
            classOrObject.getClass();
            return new NonFinalClass(klass, classOrObject);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NonFinalClass)) {
                return false;
            }
            NonFinalClass nonFinalClass = (NonFinalClass) other;
            return Intrinsics.areEqual(this.klass, nonFinalClass.klass) && Intrinsics.areEqual(this.classOrObject, nonFinalClass.classOrObject);
        }

        @Override // org.jetbrains.kotlin.cfg.LeakingThisDescriptor
        public KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final ClassDescriptor getKlass() {
            return this.klass;
        }

        public int hashCode() {
            return (this.klass.hashCode() * 31) + this.classOrObject.hashCode();
        }

        public String toString() {
            return "NonFinalClass(klass=" + this.klass + ", classOrObject=" + this.classOrObject + Util.C_PARAM_END;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor$NonFinalFunction;", "Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor;", "function", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "classOrObject", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Lorg/jetbrains/kotlin/psi/KtClassOrObject;)V", "getFunction", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "getClassOrObject", "()Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class NonFinalFunction implements LeakingThisDescriptor {
        private final KtClassOrObject classOrObject;
        private final FunctionDescriptor function;

        public NonFinalFunction(FunctionDescriptor functionDescriptor, KtClassOrObject ktClassOrObject) {
            functionDescriptor.getClass();
            ktClassOrObject.getClass();
            this.function = functionDescriptor;
            this.classOrObject = ktClassOrObject;
        }

        public static /* synthetic */ NonFinalFunction copy$default(NonFinalFunction nonFinalFunction, FunctionDescriptor functionDescriptor, KtClassOrObject ktClassOrObject, int i, Object obj) {
            if ((i & 1) != 0) {
                functionDescriptor = nonFinalFunction.function;
            }
            if ((i & 2) != 0) {
                ktClassOrObject = nonFinalFunction.classOrObject;
            }
            return nonFinalFunction.copy(functionDescriptor, ktClassOrObject);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FunctionDescriptor getFunction() {
            return this.function;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final NonFinalFunction copy(FunctionDescriptor function, KtClassOrObject classOrObject) {
            function.getClass();
            classOrObject.getClass();
            return new NonFinalFunction(function, classOrObject);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NonFinalFunction)) {
                return false;
            }
            NonFinalFunction nonFinalFunction = (NonFinalFunction) other;
            return Intrinsics.areEqual(this.function, nonFinalFunction.function) && Intrinsics.areEqual(this.classOrObject, nonFinalFunction.classOrObject);
        }

        @Override // org.jetbrains.kotlin.cfg.LeakingThisDescriptor
        public KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final FunctionDescriptor getFunction() {
            return this.function;
        }

        public int hashCode() {
            return (this.function.hashCode() * 31) + this.classOrObject.hashCode();
        }

        public String toString() {
            return "NonFinalFunction(function=" + this.function + ", classOrObject=" + this.classOrObject + Util.C_PARAM_END;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor$NonFinalProperty;", "Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor;", "property", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "classOrObject", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lorg/jetbrains/kotlin/psi/KtClassOrObject;)V", "getProperty", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getClassOrObject", "()Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class NonFinalProperty implements LeakingThisDescriptor {
        private final KtClassOrObject classOrObject;
        private final PropertyDescriptor property;

        public NonFinalProperty(PropertyDescriptor propertyDescriptor, KtClassOrObject ktClassOrObject) {
            propertyDescriptor.getClass();
            ktClassOrObject.getClass();
            this.property = propertyDescriptor;
            this.classOrObject = ktClassOrObject;
        }

        public static /* synthetic */ NonFinalProperty copy$default(NonFinalProperty nonFinalProperty, PropertyDescriptor propertyDescriptor, KtClassOrObject ktClassOrObject, int i, Object obj) {
            if ((i & 1) != 0) {
                propertyDescriptor = nonFinalProperty.property;
            }
            if ((i & 2) != 0) {
                ktClassOrObject = nonFinalProperty.classOrObject;
            }
            return nonFinalProperty.copy(propertyDescriptor, ktClassOrObject);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final PropertyDescriptor getProperty() {
            return this.property;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final NonFinalProperty copy(PropertyDescriptor property, KtClassOrObject classOrObject) {
            property.getClass();
            classOrObject.getClass();
            return new NonFinalProperty(property, classOrObject);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NonFinalProperty)) {
                return false;
            }
            NonFinalProperty nonFinalProperty = (NonFinalProperty) other;
            return Intrinsics.areEqual(this.property, nonFinalProperty.property) && Intrinsics.areEqual(this.classOrObject, nonFinalProperty.classOrObject);
        }

        @Override // org.jetbrains.kotlin.cfg.LeakingThisDescriptor
        public KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final PropertyDescriptor getProperty() {
            return this.property;
        }

        public int hashCode() {
            return (this.property.hashCode() * 31) + this.classOrObject.hashCode();
        }

        public String toString() {
            return "NonFinalProperty(property=" + this.property + ", classOrObject=" + this.classOrObject + Util.C_PARAM_END;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor$PropertyIsNull;", "Lorg/jetbrains/kotlin/cfg/LeakingThisDescriptor;", "property", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "classOrObject", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lorg/jetbrains/kotlin/psi/KtClassOrObject;)V", "getProperty", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getClassOrObject", "()Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class PropertyIsNull implements LeakingThisDescriptor {
        private final KtClassOrObject classOrObject;
        private final PropertyDescriptor property;

        public PropertyIsNull(PropertyDescriptor propertyDescriptor, KtClassOrObject ktClassOrObject) {
            propertyDescriptor.getClass();
            ktClassOrObject.getClass();
            this.property = propertyDescriptor;
            this.classOrObject = ktClassOrObject;
        }

        public static /* synthetic */ PropertyIsNull copy$default(PropertyIsNull propertyIsNull, PropertyDescriptor propertyDescriptor, KtClassOrObject ktClassOrObject, int i, Object obj) {
            if ((i & 1) != 0) {
                propertyDescriptor = propertyIsNull.property;
            }
            if ((i & 2) != 0) {
                ktClassOrObject = propertyIsNull.classOrObject;
            }
            return propertyIsNull.copy(propertyDescriptor, ktClassOrObject);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final PropertyDescriptor getProperty() {
            return this.property;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final PropertyIsNull copy(PropertyDescriptor property, KtClassOrObject classOrObject) {
            property.getClass();
            classOrObject.getClass();
            return new PropertyIsNull(property, classOrObject);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PropertyIsNull)) {
                return false;
            }
            PropertyIsNull propertyIsNull = (PropertyIsNull) other;
            return Intrinsics.areEqual(this.property, propertyIsNull.property) && Intrinsics.areEqual(this.classOrObject, propertyIsNull.classOrObject);
        }

        @Override // org.jetbrains.kotlin.cfg.LeakingThisDescriptor
        public KtClassOrObject getClassOrObject() {
            return this.classOrObject;
        }

        public final PropertyDescriptor getProperty() {
            return this.property;
        }

        public int hashCode() {
            return (this.property.hashCode() * 31) + this.classOrObject.hashCode();
        }

        public String toString() {
            return "PropertyIsNull(property=" + this.property + ", classOrObject=" + this.classOrObject + Util.C_PARAM_END;
        }
    }

    KtClassOrObject getClassOrObject();
}
