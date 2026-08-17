package org.jetbrains.kotlin.resolve.annotations;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"JVM_STATIC_ANNOTATION_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "getJVM_STATIC_ANNOTATION_FQ_NAME", "()Lorg/jetbrains/kotlin/name/FqName;", "hasJvmStaticAnnotation", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class AnnotationUtilKt {
    private static final FqName JVM_STATIC_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmStatic");

    public static final FqName getJVM_STATIC_ANNOTATION_FQ_NAME() {
        return JVM_STATIC_ANNOTATION_FQ_NAME;
    }

    public static final boolean hasJvmStaticAnnotation(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return declarationDescriptor.getAnnotations().findAnnotation(JVM_STATIC_ANNOTATION_FQ_NAME) != null;
    }
}
