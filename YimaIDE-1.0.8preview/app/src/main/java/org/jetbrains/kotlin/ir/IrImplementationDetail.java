package org.jetbrains.kotlin.ir;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Target({ElementType.CONSTRUCTOR})
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\u0018\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\bÊ\u0001\u0012\b\t\u0012\u000e\b\n\u0012\n\b\fJ\u0006\b\n0\u000b8\f¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/ir/IrImplementationDetail;", "", "org.jetbrains.kotlin:ir.tree", "Lkotlin/RequiresOptIn;", "message", "Use IrFactory instead of creating IR nodes directly", "level", "Lkotlin/RequiresOptIn$Level;", "ERROR", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CONSTRUCTOR"}, k = 1, mv = {2, 4, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface IrImplementationDetail {
}
