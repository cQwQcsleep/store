package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@MustBeDocumented
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B2\bF\u0012\u000e\b\u0002\u0010\u0002\u001a\u00020\u0003B\u0004\b\b(\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\u00020\u0003B\u0004\b\b(\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\u00020\u0003B\u0004\b\b(\u0004R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0007R\u0013\u0010\u0005\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0013\u0010\u0006\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007Ê\u0001J\b\t\u0012F\b\n\u0012B\b\fJ\u0006\b\n0\u000b8\fJ\u0006\b\n0\u000b8\rJ\u0006\b\n0\u000b8\u000eJ\u0006\b\n0\u000b8\u000fJ\u0006\b\n0\u000b8\u0010J\u0006\b\n0\u000b8\u0011J\u0006\b\n0\u000b8\u0012J\u0006\b\n0\u000b8\u0013Ê\u0001\u0002\b\u0014Ê\u0001\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017¨\u0006\b"}, d2 = {"Lkotlin/DeprecatedSinceKotlin;", "", "warningSince", "", "", "errorSince", "hiddenSince", "()Ljava/lang/String;", "kotlin-stdlib", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CLASS", "FUNCTION", "PROPERTY", "ANNOTATION_CLASS", "CONSTRUCTOR", "PROPERTY_SETTER", "PROPERTY_GETTER", "TYPEALIAS", "Lkotlin/annotation/MustBeDocumented;", "Lkotlin/SinceKotlin;", "version", "1.4"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.TYPEALIAS})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedSinceKotlin {
    String errorSince() default "";

    String hiddenSince() default "";

    String warningSince() default "";
}
