package org.jetbrains.kotlin.diagnostics.rendering;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J/\u0010\u0012\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/AnnotationAndMethods;", Argument.Delimiters.none, "annotationClass", "Ljava/lang/Class;", Argument.Delimiters.none, "featureMethod", "Ljava/lang/reflect/Method;", "ifValueIsMethod", "<init>", "(Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getAnnotationClass", "()Ljava/lang/Class;", "getFeatureMethod", "()Ljava/lang/reflect/Method;", "getIfValueIsMethod", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class AnnotationAndMethods {
    private final Class<? extends Annotation> annotationClass;
    private final Method featureMethod;
    private final Method ifValueIsMethod;

    public AnnotationAndMethods(Class<? extends Annotation> cls, Method method, Method method2) {
        cls.getClass();
        method.getClass();
        method2.getClass();
        this.annotationClass = cls;
        this.featureMethod = method;
        this.ifValueIsMethod = method2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnnotationAndMethods copy$default(AnnotationAndMethods annotationAndMethods, Class cls, Method method, Method method2, int i, Object obj) {
        if ((i & 1) != 0) {
            cls = annotationAndMethods.annotationClass;
        }
        if ((i & 2) != 0) {
            method = annotationAndMethods.featureMethod;
        }
        if ((i & 4) != 0) {
            method2 = annotationAndMethods.ifValueIsMethod;
        }
        return annotationAndMethods.copy(cls, method, method2);
    }

    public final Class<? extends Annotation> component1() {
        return this.annotationClass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Method getFeatureMethod() {
        return this.featureMethod;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Method getIfValueIsMethod() {
        return this.ifValueIsMethod;
    }

    public final AnnotationAndMethods copy(Class<? extends Annotation> annotationClass, Method featureMethod, Method ifValueIsMethod) {
        annotationClass.getClass();
        featureMethod.getClass();
        ifValueIsMethod.getClass();
        return new AnnotationAndMethods(annotationClass, featureMethod, ifValueIsMethod);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotationAndMethods)) {
            return false;
        }
        AnnotationAndMethods annotationAndMethods = (AnnotationAndMethods) other;
        return Intrinsics.areEqual(this.annotationClass, annotationAndMethods.annotationClass) && Intrinsics.areEqual(this.featureMethod, annotationAndMethods.featureMethod) && Intrinsics.areEqual(this.ifValueIsMethod, annotationAndMethods.ifValueIsMethod);
    }

    public final Class<? extends Annotation> getAnnotationClass() {
        return this.annotationClass;
    }

    public final Method getFeatureMethod() {
        return this.featureMethod;
    }

    public final Method getIfValueIsMethod() {
        return this.ifValueIsMethod;
    }

    public int hashCode() {
        return (((this.annotationClass.hashCode() * 31) + this.featureMethod.hashCode()) * 31) + this.ifValueIsMethod.hashCode();
    }

    public String toString() {
        return "AnnotationAndMethods(annotationClass=" + this.annotationClass + ", featureMethod=" + this.featureMethod + ", ifValueIsMethod=" + this.ifValueIsMethod + ')';
    }
}
