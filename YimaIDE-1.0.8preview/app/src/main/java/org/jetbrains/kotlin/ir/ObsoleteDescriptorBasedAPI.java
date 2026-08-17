package org.jetbrains.kotlin.ir;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import org.eclipse.jdt.internal.compiler.util.SuffixConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u00012\b\u0003\u0012.\b\u0004\u0012*\b\fJ\u0006\b\n0\u00058\u0006J\u0006\b\n0\u00058\u0007J\u0006\b\n0\u00058\bJ\u0006\b\n0\u00058\tJ\u0006\b\n0\u00058\nÊ\u0001\u0018\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "", "org.jetbrains.kotlin:ir.tree", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", SuffixConstants.EXTENSION_CLASS, "PROPERTY", "VALUE_PARAMETER", "FUNCTION", "TYPEALIAS", "Lkotlin/RequiresOptIn;", "message", "Please use IR declaration properties and not its descriptor properties", "level", "Lkotlin/RequiresOptIn$Level;", "ERROR"}, k = 1, mv = {2, 4, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObsoleteDescriptorBasedAPI {
}
