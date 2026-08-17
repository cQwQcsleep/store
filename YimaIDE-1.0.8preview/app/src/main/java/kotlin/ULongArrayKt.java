package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000bb\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a3\u0010\r\u001a\u00020\u00012\n\u0010\u000e\u001a\u00020\u0001\"\u00020\u0006H\u0087\u0088\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000bb\u0002\b\f¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0011"}, d2 = {"ULongArray", "Lkotlin/ULongArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/ULong;", "(ILkotlin/jvm/functions/Function1;)[J", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/ExperimentalUnsignedTypes;", "Lkotlin/internal/InlineOnly;", "ulongArrayOf", "elements", "ulongArrayOf-QwZRm1k", "([J)[J", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ULongArrayKt {
    private static final long[] ULongArray(int i, Function1<? super Integer, ULong> function1) {
        function1.getClass();
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = function1.invoke(Integer.valueOf(i2)).getData();
        }
        return ULongArray.m267constructorimpl(jArr);
    }

    /* JADX INFO: renamed from: ulongArrayOf-QwZRm1k, reason: not valid java name */
    private static final long[] m283ulongArrayOfQwZRm1k(long... jArr) {
        jArr.getClass();
        return jArr;
    }
}
