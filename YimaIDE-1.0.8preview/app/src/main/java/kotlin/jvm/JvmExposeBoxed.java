package kotlin.jvm;

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
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0002\u0018\u00002\u00020\u0001B\f\bF\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004Ê\u0001\u000e\b\u0006\u0012\n\b\u0007\u0012\u0006\b\n0\b8\tÊ\u0001\u0002\b\nÊ\u0001\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\rÊ\u0001\u0002\b\u000eÊ\u00012\b\u000f\u0012.\b\u0010\u0012*\b\fJ\u0006\b\n0\u00118\u0012J\u0006\b\n0\u00118\u0013J\u0006\b\n0\u00118\u0014J\u0006\b\n0\u00118\u0015J\u0006\b\n0\u00118\u0016¨\u0006\u0005"}, d2 = {"Lkotlin/jvm/JvmExposeBoxed;", HttpUrl.FRAGMENT_ENCODE_SET, "jvmName", HttpUrl.FRAGMENT_ENCODE_SET, "()Ljava/lang/String;", "kotlin-stdlib", "Lkotlin/annotation/Retention;", "value", "Lkotlin/annotation/AnnotationRetention;", "BINARY", "Lkotlin/annotation/MustBeDocumented;", "Lkotlin/SinceKotlin;", "version", "2.2", "Lkotlin/ExperimentalStdlibApi;", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "FUNCTION", "CONSTRUCTOR", "PROPERTY_GETTER", "PROPERTY_SETTER", "CLASS"}, k = 1, mv = {2, 4, 0}, xi = 48)
@Documented
public @interface JvmExposeBoxed {
    String jvmName() default "";
}
