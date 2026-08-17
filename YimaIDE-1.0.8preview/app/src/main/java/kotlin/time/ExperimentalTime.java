package kotlin.time;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.TYPEALIAS})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0002\bFÊ\u0001\u000e\b\u0003\u0012\n\b\u0004\u0012\u0006\b\n0\u00058\u0006Ê\u0001\u0002\b\u0007Ê\u0001\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u000bÊ\u0001b\b\f\u0012^\b\r\u0012Z\b\fJ\u0006\b\n0\u000e8\u000fJ\u0006\b\n0\u000e8\u0010J\u0006\b\n0\u000e8\u0011J\u0006\b\n0\u000e8\u0012J\u0006\b\n0\u000e8\u0013J\u0006\b\n0\u000e8\u0014J\u0006\b\n0\u000e8\u0015J\u0006\b\n0\u000e8\u0016J\u0006\b\n0\u000e8\u0017J\u0006\b\n0\u000e8\u0018J\u0006\b\n0\u000e8\u0019Ê\u0001\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c¨\u0006\u0002"}, d2 = {"Lkotlin/time/ExperimentalTime;", HttpUrl.FRAGMENT_ENCODE_SET, "kotlin-stdlib", "Lkotlin/RequiresOptIn;", "level", "Lkotlin/RequiresOptIn$Level;", "ERROR", "Lkotlin/annotation/MustBeDocumented;", "Lkotlin/annotation/Retention;", "value", "Lkotlin/annotation/AnnotationRetention;", "BINARY", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CLASS", "ANNOTATION_CLASS", "PROPERTY", "FIELD", "LOCAL_VARIABLE", "VALUE_PARAMETER", "CONSTRUCTOR", "FUNCTION", "PROPERTY_GETTER", "PROPERTY_SETTER", "TYPEALIAS", "Lkotlin/SinceKotlin;", "version", "1.3"}, k = 1, mv = {2, 4, 0}, xi = 48)
@Documented
public @interface ExperimentalTime {
}
