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
@Target({ElementType.PARAMETER})
@MustBeDocumented
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\n\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004Ê\u0001\u0012\b\u0006\u0012\u000e\b\u0007\u0012\n\b\fJ\u0006\b\n0\b8\tÊ\u0001\u0002\b\nÊ\u0001\f\b\u000b\u0012\b\b\u0002\u0012\u0004\b\b(\fÊ\u0001\u0002\b\r¨\u0006\u0005"}, d2 = {"Lkotlin/IntroducedAt;", "", "version", "", "()Ljava/lang/String;", "kotlin-stdlib", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "VALUE_PARAMETER", "Lkotlin/annotation/MustBeDocumented;", "Lkotlin/SinceKotlin;", "2.3", "Lkotlin/ExperimentalVersionOverloading;"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IntroducedAt {
    String version();
}
