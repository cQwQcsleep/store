package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0081\u0080\u0004b\u0002\b\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u001a)\u0010\b\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\nH\u0081\u0080\u0004b\u0002\b\u0004b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\u0010\u000b\u001a>\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001aS\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\r*\u0002H\u00122\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\r0\u0013¢\u0006\u0002\b\u0014H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a1\u0010\u0016\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\nH\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\u0010\u0017\u001an\u0010\u0018\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\b\b\u0001\u0010\u0012*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\r0\u0013H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0015\u001aC\u0010\u001c\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\b\b\u0001\u0010\u0012*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00120\n2\u0006\u0010\u001d\u001a\u0002H\rH\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\u0010\u001e\u001a\u0097\u0001\u0010\u001f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010 \u001a\u001d\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(!\u0012\u0004\u0012\u0002H\r0\u00132!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\r0\u0013H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0002\u0010\"\u001ap\u0010#\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010$\u001a\u001d\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(!\u0012\u0004\u0012\u0002H\r0\u0013H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0015\u001ac\u0010%\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010$\u001a\u001d\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(!\u0012\u0004\u0012\u0002H\r0\u0013H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001at\u0010&\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\u0004\b\u0000\u0010\r\"\b\b\u0001\u0010\u0012*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\r0\u0013H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0015\u001ag\u0010'\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\u0004\b\u0000\u0010\r\"\b\b\u0001\u0010\u0012*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\r0\u0013H\u0087\u0088\u0004b\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001an\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00120\n\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010(\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\t0\u0013H\u0087\u0088\bb\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007b\u0002\b)ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0015\u001an\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00120\n\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\n2!\u0010(\u001a\u001d\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\t0\u0013H\u0087\u0088\bb\u0002\b\u0011b\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007b\u0002\b)ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"createFailure", "", "exception", "", "Lkotlin/PublishedApi;", "Lkotlin/SinceKotlin;", "version", "1.3", "throwOnFailure", "", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "runCatching", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lkotlin/internal/InlineOnly;", "T", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "onFailure", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "fold", "onSuccess", "value", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "map", "transform", "mapCatching", "recover", "recoverCatching", "action", "Lkotlin/IgnorableReturnValue;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ResultKt {
    public static final Object createFailure(Throwable th) {
        th.getClass();
        return new Result.Failure(th);
    }

    private static final <R, T> R fold(Object obj, Function1<? super T, ? extends R> function1, Function1<? super Throwable, ? extends R> function2) {
        function1.getClass();
        function2.getClass();
        Throwable thM41exceptionOrNullimpl = Result.m41exceptionOrNullimpl(obj);
        return thM41exceptionOrNullimpl == null ? function1.invoke(obj) : function2.invoke(thM41exceptionOrNullimpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <R, T extends R> R getOrDefault(Object obj, R r) {
        return Result.m44isFailureimpl(obj) ? r : obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <R, T extends R> R getOrElse(Object obj, Function1<? super Throwable, ? extends R> function1) {
        function1.getClass();
        Throwable thM41exceptionOrNullimpl = Result.m41exceptionOrNullimpl(obj);
        return thM41exceptionOrNullimpl == null ? obj : function1.invoke(thM41exceptionOrNullimpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> T getOrThrow(Object obj) throws Throwable {
        throwOnFailure(obj);
        return obj;
    }

    private static final <R, T> Object map(Object obj, Function1<? super T, ? extends R> function1) {
        function1.getClass();
        return Result.m45isSuccessimpl(obj) ? Result.m38constructorimpl(function1.invoke(obj)) : Result.m38constructorimpl(obj);
    }

    private static final <R, T> Object mapCatching(Object obj, Function1<? super T, ? extends R> function1) {
        function1.getClass();
        if (!Result.m45isSuccessimpl(obj)) {
            return Result.m38constructorimpl(obj);
        }
        try {
            return Result.m38constructorimpl(function1.invoke(obj));
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m38constructorimpl(createFailure(th));
        }
    }

    @IgnorableReturnValue
    private static final <T> Object onFailure(Object obj, Function1<? super Throwable, Unit> function1) {
        function1.getClass();
        Throwable thM41exceptionOrNullimpl = Result.m41exceptionOrNullimpl(obj);
        if (thM41exceptionOrNullimpl != null) {
            function1.invoke(thM41exceptionOrNullimpl);
        }
        return obj;
    }

    @IgnorableReturnValue
    private static final <T> Object onSuccess(Object obj, Function1<? super T, Unit> function1) {
        function1.getClass();
        if (Result.m45isSuccessimpl(obj)) {
            function1.invoke(obj);
        }
        return obj;
    }

    private static final <R, T extends R> Object recover(Object obj, Function1<? super Throwable, ? extends R> function1) {
        function1.getClass();
        Throwable thM41exceptionOrNullimpl = Result.m41exceptionOrNullimpl(obj);
        return thM41exceptionOrNullimpl == null ? obj : Result.m38constructorimpl(function1.invoke(thM41exceptionOrNullimpl));
    }

    private static final <R, T extends R> Object recoverCatching(Object obj, Function1<? super Throwable, ? extends R> function1) {
        function1.getClass();
        Throwable thM41exceptionOrNullimpl = Result.m41exceptionOrNullimpl(obj);
        if (thM41exceptionOrNullimpl == null) {
            return obj;
        }
        try {
            return Result.m38constructorimpl(function1.invoke(thM41exceptionOrNullimpl));
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m38constructorimpl(createFailure(th));
        }
    }

    private static final <R> Object runCatching(Function0<? extends R> function0) {
        function0.getClass();
        try {
            Result.Companion companion = Result.INSTANCE;
            return Result.m38constructorimpl(function0.invoke());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m38constructorimpl(createFailure(th));
        }
    }

    public static final void throwOnFailure(Object obj) throws Throwable {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }

    private static final <T, R> Object runCatching(T t, Function1<? super T, ? extends R> function1) {
        function1.getClass();
        try {
            Result.Companion companion = Result.INSTANCE;
            return Result.m38constructorimpl(function1.invoke(t));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m38constructorimpl(createFailure(th));
        }
    }
}
