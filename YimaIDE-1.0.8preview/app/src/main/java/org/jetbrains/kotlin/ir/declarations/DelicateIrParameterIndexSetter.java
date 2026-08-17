package org.jetbrains.kotlin.ir.declarations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Target({ElementType.METHOD})
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\u0018\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\bÊ\u0001\u0012\b\t\u0012\u000e\b\n\u0012\n\b\fJ\u0006\b\n0\u000b8\f¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DelicateIrParameterIndexSetter;", "", "org.jetbrains.kotlin:ir.tree", "Lkotlin/RequiresOptIn;", "message", "Index of a parameter is tracked automatically when adding/removing it to/from IrFunction. Only a few selected places should need to modify it manually. One example is IrScript, whose parameters have non-obvious and not-automatic indices.", "level", "Lkotlin/RequiresOptIn$Level;", "ERROR", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "PROPERTY_SETTER"}, k = 1, mv = {2, 4, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY_SETTER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DelicateIrParameterIndexSetter {
}
