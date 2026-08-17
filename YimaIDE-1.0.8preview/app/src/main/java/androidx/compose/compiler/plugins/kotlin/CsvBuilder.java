package androidx.compose.compiler.plugins.kotlin;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b\u000bJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000fJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0010R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/CsvBuilder;", "", "writer", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "<init>", "(Ljava/lang/Appendable;)V", "row", "", "fn", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "col", "value", "", "", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CsvBuilder {
    private final Appendable writer;

    public CsvBuilder(Appendable appendable) {
        appendable.getClass();
        this.writer = appendable;
    }

    public final void col(String value) throws IOException {
        value.getClass();
        Appendable appendable = this.writer;
        if (StringsKt.contains$default(value, ',', false, 2, (Object) null)) {
            dt1.a("Illegal character ',' found: ", value);
        } else {
            appendable.append(value);
            appendable.append(",");
        }
    }

    public final void row(Function1<? super CsvBuilder, Unit> fn) throws IOException {
        fn.getClass();
        Appendable appendable = this.writer;
        fn.invoke(this);
        appendable.append('\n');
    }

    public final void col(int value) throws IOException {
        Appendable appendable = this.writer;
        appendable.append(String.valueOf(value));
        appendable.append(",");
    }

    public final void col(boolean value) throws IOException {
        Appendable appendable = this.writer;
        appendable.append(value ? "1" : "0");
        appendable.append(",");
    }
}
