package kotlin.text;

import java.io.IOException;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000P\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0087\u0080\bb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fb\u0002\b\r¢\u0006\u0002\u0010\t\u001a=\u0010\u000e\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u000f\"\u0004\u0018\u00010\u0005H\u0087\u0080\bb\u0002\b\r¢\u0006\u0002\u0010\u0010\u001a1\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0087\u0088\bb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fb\u0002\b\u0013b\u0002\b\r¢\u0006\u0002\u0010\u0012\u001a;\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0087\u0088\bb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fb\u0002\b\u0013b\u0002\b\r¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u0015H\u0087\u0088\bb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\fb\u0002\b\u0013b\u0002\b\r¢\u0006\u0002\u0010\u0016\u001a;\u0010\u0017\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0019\u001a\u0002H\u00012\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001bH\u0080\u0080\u0004¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"appendRange", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", HttpUrl.FRAGMENT_ENCODE_SET, "startIndex", HttpUrl.FRAGMENT_ENCODE_SET, "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "Lkotlin/SinceKotlin;", "version", "1.4", "Lkotlin/IgnorableReturnValue;", "append", HttpUrl.FRAGMENT_ENCODE_SET, "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendLine", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "Lkotlin/internal/InlineOnly;", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;)Ljava/lang/Appendable;", HttpUrl.FRAGMENT_ENCODE_SET, "(Ljava/lang/Appendable;C)Ljava/lang/Appendable;", "appendElement", HttpUrl.FRAGMENT_ENCODE_SET, "element", "transform", "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt__AppendableKt {
    @IgnorableReturnValue
    public static final <T extends Appendable> T append(T t, CharSequence... charSequenceArr) throws IOException {
        t.getClass();
        charSequenceArr.getClass();
        for (CharSequence charSequence : charSequenceArr) {
            t.append(charSequence);
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void appendElement(Appendable appendable, T t, Function1<? super T, ? extends CharSequence> function1) throws IOException {
        appendable.getClass();
        if (function1 != null) {
            appendable.append((CharSequence) function1.invoke(t));
            return;
        }
        if (t == 0 ? true : t instanceof CharSequence) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(t.toString());
        }
    }

    @IgnorableReturnValue
    private static final Appendable appendLine(Appendable appendable, CharSequence charSequence) {
        appendable.getClass();
        return appendable.append(charSequence).append('\n');
    }

    @IgnorableReturnValue
    public static final <T extends Appendable> T appendRange(T t, CharSequence charSequence, int i, int i2) {
        t.getClass();
        charSequence.getClass();
        T t2 = (T) t.append(charSequence, i, i2);
        t2.getClass();
        return t2;
    }

    @IgnorableReturnValue
    private static final Appendable appendLine(Appendable appendable) {
        appendable.getClass();
        return appendable.append('\n');
    }

    @IgnorableReturnValue
    private static final Appendable appendLine(Appendable appendable, char c) {
        appendable.getClass();
        return appendable.append(c).append('\n');
    }
}
