package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Target({})
@MustBeDocumented
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\n\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004Ê\u0001\u0012\b\u0006\u0012\u000e\b\u0007\u0012\n\b\fJ\u0006\b\n0\b8\tÊ\u0001\u0002\b\nÊ\u0001\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r¨\u0006\u0005"}, d2 = {"Lkotlin/ContextFunctionTypeParams;", "", "count", "", "()I", "kotlin-stdlib", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "TYPE", "Lkotlin/annotation/MustBeDocumented;", "Lkotlin/SinceKotlin;", "version", "1.7"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextFunctionTypeParams {
    int count();
}
