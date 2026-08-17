package kotlin;

import defpackage.qf1;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\"6\u0010\u0002\u001a\u00020\u0003*\u00020\u00018Æ\u0002X\u0087\u0084\br\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006r\u0002\b\u0007r\u0002\b\f¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Char", "", "code", "", "Lkotlin/SinceKotlin;", "version", "1.5", "Lkotlin/internal/InlineOnly;", "getCode$annotations", "(C)V", "getCode", "(C)I", "Lkotlin/internal/IntrinsicConstEvaluation;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class CharCodeKt {
    private static final char Char(int i) {
        if (i >= 0 && i <= 65535) {
            return (char) i;
        }
        qf1.a("Invalid Char code: ", i);
        return (char) 0;
    }

    private static final int getCode(char c) {
        return c;
    }

    public static /* synthetic */ void getCode$annotations(char c) {
    }
}
