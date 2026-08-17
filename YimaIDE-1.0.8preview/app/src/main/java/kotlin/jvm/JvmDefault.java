package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Target({ElementType.METHOD})
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0002\bFÊ\u0001\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005Ê\u0001\u001a\b\u0006\u0012\u0016\b\u0007\u0012\u0012\b\fJ\u0006\b\n0\b8\tJ\u0006\b\n0\b8\nÊ\u0001\u0018\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010¨\u0006\u0002"}, d2 = {"Lkotlin/jvm/JvmDefault;", HttpUrl.FRAGMENT_ENCODE_SET, "kotlin-stdlib", "Lkotlin/SinceKotlin;", "version", "1.2", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "FUNCTION", "PROPERTY", "Lkotlin/Deprecated;", "message", "Switch to new -jvm-default modes: `enable` or `no-compatibility`", "level", "Lkotlin/DeprecationLevel;", "HIDDEN"}, k = 1, mv = {2, 4, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "Switch to new -jvm-default modes: `enable` or `no-compatibility`")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.RUNTIME)
public @interface JvmDefault {
}
