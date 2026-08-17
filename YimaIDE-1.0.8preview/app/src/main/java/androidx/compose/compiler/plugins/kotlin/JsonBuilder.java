package androidx.compose.compiler.plugins.kotlin;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nJ\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006J'\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b\u001aJ\u001f\u0010\u001b\u001a\u00020\u00132\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\b\u001aR\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/JsonBuilder;", "", "sb", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "indent", "", "<init>", "(Ljava/lang/Appendable;I)V", "hasEntry", "", "getHasEntry", "()Z", "setHasEntry", "(Z)V", "spacesForIndent", "nonWordCharRegex", "Lkotlin/text/Regex;", "entryLiteral", "", "key", "", "value", "entry", "fn", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "with", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsonBuilder {
    private boolean hasEntry;
    private final int indent;
    private final Regex nonWordCharRegex;
    private final Appendable sb;
    private final int spacesForIndent;

    public JsonBuilder(Appendable appendable, int i) {
        appendable.getClass();
        this.sb = appendable;
        this.indent = i;
        this.spacesForIndent = 2;
        this.nonWordCharRegex = new Regex("\\W");
    }

    private final void entryLiteral(String key, String value) throws IOException {
        Appendable appendable = this.sb;
        if (this.hasEntry) {
            appendable.append(",").append('\n');
        }
        appendable.append(StringsKt.repeat(" ", this.indent * this.spacesForIndent));
        appendable.append("\"" + this.nonWordCharRegex.replace(key, "") + '\"');
        appendable.append(": ");
        appendable.append(value);
        this.hasEntry = true;
    }

    public final void entry(String key, Function1<? super JsonBuilder, Unit> fn) throws IOException {
        key.getClass();
        fn.getClass();
        StringBuilder sb = new StringBuilder();
        new JsonBuilder(sb, this.indent + 1).with(fn);
        Unit unit = Unit.INSTANCE;
        entryLiteral(key, sb.toString());
    }

    public final boolean getHasEntry() {
        return this.hasEntry;
    }

    public final void setHasEntry(boolean z) {
        this.hasEntry = z;
    }

    public final void with(Function1<? super JsonBuilder, Unit> fn) throws IOException {
        fn.getClass();
        Appendable appendable = this.sb;
        appendable.append("{").append('\n');
        fn.invoke(this);
        if (this.hasEntry) {
            appendable.append('\n');
        }
        appendable.append(StringsKt.repeat(" ", (this.indent - 1) * this.spacesForIndent));
        appendable.append("}");
    }

    public /* synthetic */ JsonBuilder(Appendable appendable, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(appendable, (i2 & 2) != 0 ? 0 : i);
    }

    public final void entry(String key, int value) throws IOException {
        key.getClass();
        entryLiteral(key, String.valueOf(value));
    }

    public final void entry(String key, boolean value) throws IOException {
        key.getClass();
        entryLiteral(key, String.valueOf(value));
    }
}
