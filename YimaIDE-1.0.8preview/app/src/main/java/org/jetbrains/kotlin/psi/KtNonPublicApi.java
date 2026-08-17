package org.jetbrains.kotlin.psi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u00012\b\u0003\u0012.\b\u0004\u0012*\b\fJ\u0006\b\n0\u00058\u0006J\u0006\b\n0\u00058\u0007J\u0006\b\n0\u00058\bJ\u0006\b\n0\u00058\tJ\u0006\b\n0\u00058\nÊ\u0001\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/psi/KtNonPublicApi;", "", "org.jetbrains.kotlin:psi-api", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CLASS", "PROPERTY", "CONSTRUCTOR", "FUNCTION", "TYPEALIAS", "Lkotlin/RequiresOptIn;", "message", "Internal API which is used in projects developed by JetBrains"}, k = 1, mv = {2, 4, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS})
@Retention(RetentionPolicy.RUNTIME)
public @interface KtNonPublicApi {
}
