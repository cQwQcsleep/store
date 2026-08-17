package kotlin.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Target({ElementType.METHOD})
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0002\bFÊ\u0001*\b\u0003\u0012&\b\u0004\u0012\"\b\fJ\u0006\b\n0\u00058\u0006J\u0006\b\n0\u00058\u0007J\u0006\b\n0\u00058\bJ\u0006\b\n0\u00058\tÊ\u0001\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r¨\u0006\u0002"}, d2 = {"Lkotlin/internal/InlineOnly;", "", "kotlin-stdlib", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "FUNCTION", "PROPERTY", "PROPERTY_GETTER", "PROPERTY_SETTER", "Lkotlin/annotation/Retention;", "value", "Lkotlin/annotation/AnnotationRetention;", "BINARY"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
public @interface InlineOnly {
}
