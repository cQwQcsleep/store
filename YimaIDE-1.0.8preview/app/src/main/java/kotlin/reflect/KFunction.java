package kotlin.reflect;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Function;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R)\u0010\u0004\u001a\u00020\u00058&X§\u0084\br\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\bR)\u0010\f\u001a\u00020\u00058&X§\u0084\br\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b¢\u0006\f\u0012\u0004\b\r\u0010\u0007\u001a\u0004\b\f\u0010\bR)\u0010\u000e\u001a\u00020\u00058&X§\u0084\br\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b¢\u0006\f\u0012\u0004\b\u000f\u0010\u0007\u001a\u0004\b\u000e\u0010\bR)\u0010\u0010\u001a\u00020\u00058&X§\u0084\br\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b¢\u0006\f\u0012\u0004\b\u0011\u0010\u0007\u001a\u0004\b\u0010\u0010\bR)\u0010\u0012\u001a\u00020\u00058&X§\u0084\br\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b¢\u0006\f\u0012\u0004\b\u0013\u0010\u0007\u001a\u0004\b\u0012\u0010\b¨\u0006\u0014"}, d2 = {"Lkotlin/reflect/KFunction;", "R", "Lkotlin/reflect/KCallable;", "Lkotlin/Function;", "isInline", "", "isInline$annotations", "()V", "()Z", "Lkotlin/SinceKotlin;", "version", "1.1", "isExternal", "isExternal$annotations", "isOperator", "isOperator$annotations", "isInfix", "isInfix$annotations", "isSuspend", "isSuspend$annotations", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface KFunction<R> extends KCallable<R>, Function<R> {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class DefaultImpls {
        public static /* synthetic */ void isExternal$annotations() {
        }

        public static /* synthetic */ void isInfix$annotations() {
        }

        public static /* synthetic */ void isInline$annotations() {
        }

        public static /* synthetic */ void isOperator$annotations() {
        }

        public static /* synthetic */ void isSuspend$annotations() {
        }
    }

    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // kotlin.reflect.KCallable
    boolean isSuspend();
}
