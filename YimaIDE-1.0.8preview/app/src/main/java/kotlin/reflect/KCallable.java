package kotlin.reflect;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J'\u0010\u001a\u001a\u00028\u00002\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001d0\u001c\"\u0004\u0018\u00010\u001dH¦\u0080\u0004¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u00028\u00002\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u001d0 H¦\u0080\u0004¢\u0006\u0002\u0010!R\u001f\u0010\u0003\u001a\u00020\u00048&X§\u0084\br\u0002\b\t¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u00020\u0010X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R/\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b8&X§\u0084\br\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019¢\u0006\f\u0012\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\u000eR+\u0010\"\u001a\u0004\u0018\u00010#8&X§\u0084\br\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019¢\u0006\f\u0012\u0004\b$\u0010\u0006\u001a\u0004\b%\u0010&R)\u0010'\u001a\u00020(8&X§\u0084\br\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019¢\u0006\f\u0012\u0004\b)\u0010\u0006\u001a\u0004\b'\u0010*R)\u0010+\u001a\u00020(8&X§\u0084\br\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019¢\u0006\f\u0012\u0004\b,\u0010\u0006\u001a\u0004\b+\u0010*R)\u0010-\u001a\u00020(8&X§\u0084\br\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019¢\u0006\f\u0012\u0004\b.\u0010\u0006\u001a\u0004\b-\u0010*R)\u0010/\u001a\u00020(8&X§\u0084\br\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(1¢\u0006\f\u0012\u0004\b0\u0010\u0006\u001a\u0004\b/\u0010*¨\u00062"}, d2 = {"Lkotlin/reflect/KCallable;", "R", "Lkotlin/reflect/KAnnotatedElement;", "name", "", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "Lkotlin/internal/IntrinsicConstEvaluation;", "parameters", "", "Lkotlin/reflect/KParameter;", "getParameters", "()Ljava/util/List;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "Lkotlin/SinceKotlin;", "version", "1.1", "call", "args", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", "", "(Ljava/util/Map;)Ljava/lang/Object;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", "", "isFinal$annotations", "()Z", "isOpen", "isOpen$annotations", "isAbstract", "isAbstract$annotations", "isSuspend", "isSuspend$annotations", "1.3", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface KCallable<R> extends KAnnotatedElement {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class DefaultImpls {
        public static /* synthetic */ void getName$annotations() {
        }

        public static /* synthetic */ void getTypeParameters$annotations() {
        }

        public static /* synthetic */ void getVisibility$annotations() {
        }

        public static /* synthetic */ void isAbstract$annotations() {
        }

        public static /* synthetic */ void isFinal$annotations() {
        }

        public static /* synthetic */ void isOpen$annotations() {
        }

        public static /* synthetic */ void isSuspend$annotations() {
        }
    }

    R call(Object... args);

    R callBy(Map<KParameter, ? extends Object> args);

    String getName();

    List<KParameter> getParameters();

    KType getReturnType();

    List<KTypeParameter> getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
