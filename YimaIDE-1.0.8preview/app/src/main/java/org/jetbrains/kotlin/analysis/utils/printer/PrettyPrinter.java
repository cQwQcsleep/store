package org.jetbrains.kotlin.analysis.utils.printer;

import java.io.IOException;
import java.util.Iterator;
import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u001d\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J&\u0010\u001d\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0016J\u0014\u0010\u001d\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020%H\u0002J%\u0010&\u001a\u00020%2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020%0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J-\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020\u00042\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020%0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J%\u0010,\u001a\u00020%2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020%0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J%\u0010-\u001a\u00020%2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020%0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J5\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00132\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020%0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J]\u00101\u001a\u00020%\"\u0004\b\u0000\u001022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H2042\b\b\u0002\u00105\u001a\u00020\u00132\b\b\u0002\u00106\u001a\u00020\u00132\b\b\u0002\u00107\u001a\u00020\u00132\u001d\u00108\u001a\u0019\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H2\u0012\u0004\u0012\u00020%09¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J]\u0010:\u001a\u00020%\"\u0004\b\u0000\u001022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H2042\b\b\u0002\u00105\u001a\u00020\u00132\b\b\u0002\u00106\u001a\u00020\u00132\b\b\u0002\u00107\u001a\u00020\u00132\u001d\u00108\u001a\u0019\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H2\u0012\u0004\u0012\u00020%09¢\u0006\u0002\b)H\u0086\bø\u0001\u0000J\u000e\u0010;\u001a\u00020%2\u0006\u0010<\u001a\u00020#J\b\u0010=\u001a\u00020%H\u0002J\n\u0010>\u001a\u00020\u0013H\u0096\u0080\u0004J'\u0010?\u001a\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001J%\u0010C\u001a\u00020%2\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020%0(¢\u0006\u0002\b)H\u0086\nø\u0001\u0000JC\u0010E\u001a\u00020%*\u00020\u00132\f\u0010F\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001J[\u0010E\u001a\u00020%*\u00020\u00132\f\u0010F\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000\u0082\u0002\u001e\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001Js\u0010E\u001a\u00020%*\u00020\u00132\f\u0010F\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000\u0082\u0002(\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0004 \u0001J\u0081\u0001\u0010E\u001a\u00020%*\u00020\u00132\f\u0010F\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020%0B2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000\u0082\u0002(\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\n\b\b\u0001\u0012\u0002\u0010\u0005 \u0001J/\u0010K\u001a\u00020%2\u0006\u00106\u001a\u00020\u00132\f\u0010D\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001J\"\u0010L\u001a\u00020%2\u0006\u0010M\u001a\u00020\u00132\f\u0010F\u001a\b\u0012\u0004\u0012\u00020%0BH\u0086\bø\u0001\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00060\nj\u0002`\u000b8\u0000X\u0081\u0004r\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR.\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0000@\u0000X\u0081\u000er\u0002\b\u0010¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\r\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u0019\u001a\u00020\u00048\u0000@\u0000X\u0081\u000er\u0002\b\u0010¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\r\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/printer/PrettyPrinter;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "indentSize", "", "<init>", "(I)V", "getIndentSize", "()I", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getBuilder$annotations", "()V", "getBuilder", "()Ljava/lang/StringBuilder;", "Lkotlin/PublishedApi;", "prefixesToPrint", "Lkotlinx/collections/immutable/PersistentList;", "", "getPrefixesToPrint$annotations", "getPrefixesToPrint", "()Lkotlinx/collections/immutable/PersistentList;", "setPrefixesToPrint", "(Lkotlinx/collections/immutable/PersistentList;)V", OutputKeys.INDENT, "getIndent$annotations", "getIndent", "setIndent", "append", "nullableSeq", "", "start", "end", "c", "", "printPrefixes", "", "withIndent", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "withIndents", "indentCount", "withIndentInBraces", "withIndentInSquareBrackets", "withIndentWrapped", "before", "after", "printCollection", "T", "collection", "", "separator", "prefix", "postfix", "renderItem", "Lkotlin/Function2;", "printCollectionIfNotEmpty", "printCharIfNotThere", "char", "appendIndentIfNeeded", "toString", "checkIfPrinted", "", "render", "Lkotlin/Function0;", "invoke", "print", "separated", "p1", "p2", "p3", "p4", "p5", "withPrefix", "withSuffix", "suffix", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PrettyPrinter implements Appendable {
    private final StringBuilder builder;
    private int indent;
    private final int indentSize;
    private PersistentList<String> prefixesToPrint;

    public PrettyPrinter(int i) {
        this.indentSize = i;
        this.builder = new StringBuilder();
        this.prefixesToPrint = ExtensionsKt.persistentListOf();
    }

    private final void appendIndentIfNeeded() {
        if (this.builder.length() != 0) {
            StringBuilder sb = this.builder;
            if (sb.charAt(StringsKt.getLastIndex(sb)) != '\n') {
                return;
            }
        }
        this.builder.append(StringsKt.repeat(" ", this.indentSize * this.indent));
    }

    public static /* synthetic */ void getBuilder$annotations() {
    }

    public static /* synthetic */ void getIndent$annotations() {
    }

    public static /* synthetic */ void getPrefixesToPrint$annotations() {
    }

    public static /* synthetic */ void printCollection$default(PrettyPrinter prettyPrinter, Iterable iterable, String str, String str2, String str3, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = ", ";
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        if ((i & 8) != 0) {
            str3 = "";
        }
        iterable.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        function2.getClass();
        prettyPrinter.append(str2);
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            function2.invoke(prettyPrinter, it.next());
            if (it.hasNext()) {
                prettyPrinter.append(str);
            }
        }
        prettyPrinter.append(str3);
    }

    public static /* synthetic */ void printCollectionIfNotEmpty$default(PrettyPrinter prettyPrinter, Iterable iterable, String str, String str2, String str3, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = ", ";
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        if ((i & 8) != 0) {
            str3 = "";
        }
        iterable.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        function2.getClass();
        if (iterable.iterator().hasNext()) {
            prettyPrinter.append(str2);
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                function2.invoke(prettyPrinter, it.next());
                if (it.hasNext()) {
                    prettyPrinter.append(str);
                }
            }
            prettyPrinter.append(str3);
        }
    }

    private final void printPrefixes() {
        if (this.prefixesToPrint.isEmpty()) {
            return;
        }
        appendIndentIfNeeded();
        Iterator it = this.prefixesToPrint.iterator();
        while (it.hasNext()) {
            this.builder.append((String) it.next());
        }
        this.prefixesToPrint = ExtensionsKt.persistentListOf();
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence nullableSeq) {
        if (nullableSeq == null) {
            nullableSeq = "null";
        }
        CharSequence charSequence = nullableSeq;
        if (charSequence.length() == 0) {
            return this;
        }
        printPrefixes();
        int i = 0;
        for (Object obj : StringsKt.split$default(charSequence, new char[]{'\n'}, false, 0, 6, (Object) null)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (i > 0) {
                this.builder.append('\n');
            }
            if (str.length() > 0) {
                appendIndentIfNeeded();
                this.builder.append(str);
            }
            i = i2;
        }
        return this;
    }

    public final boolean checkIfPrinted(Function0<Unit> render) {
        render.getClass();
        int length = getBuilder().length();
        render.invoke();
        return length != getBuilder().length();
    }

    public final StringBuilder getBuilder() {
        return this.builder;
    }

    public final int getIndent() {
        return this.indent;
    }

    public final int getIndentSize() {
        return this.indentSize;
    }

    public final PersistentList<String> getPrefixesToPrint() {
        return this.prefixesToPrint;
    }

    public final void invoke(Function1<? super PrettyPrinter, Unit> print) {
        print.getClass();
        print.invoke(this);
    }

    public final void printCharIfNotThere(char c) {
        Character chLastOrNull = StringsKt.lastOrNull(this.builder);
        if (chLastOrNull != null && chLastOrNull.charValue() == c) {
            return;
        }
        append(c);
    }

    public final <T> void printCollection(Iterable<? extends T> collection, String separator, String prefix, String postfix, Function2<? super PrettyPrinter, ? super T, Unit> renderItem) {
        collection.getClass();
        separator.getClass();
        prefix.getClass();
        postfix.getClass();
        renderItem.getClass();
        append(prefix);
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            renderItem.invoke(this, it.next());
            if (it.hasNext()) {
                append(separator);
            }
        }
        append(postfix);
    }

    public final <T> void printCollectionIfNotEmpty(Iterable<? extends T> collection, String separator, String prefix, String postfix, Function2<? super PrettyPrinter, ? super T, Unit> renderItem) {
        collection.getClass();
        separator.getClass();
        prefix.getClass();
        postfix.getClass();
        renderItem.getClass();
        if (collection.iterator().hasNext()) {
            append(prefix);
            Iterator<? extends T> it = collection.iterator();
            while (it.hasNext()) {
                renderItem.invoke(this, it.next());
                if (it.hasNext()) {
                    append(separator);
                }
            }
            append(postfix);
        }
    }

    public final void separated(String str, Function0<Unit> function0, Function0<Unit> function1, Function0<Unit> function2, Function0<Unit> function3, Function0<Unit> function4) {
        str.getClass();
        function0.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        function4.getClass();
        int length = getBuilder().length();
        int length2 = getBuilder().length();
        int length3 = getBuilder().length();
        int length4 = getBuilder().length();
        function0.invoke();
        if (length4 != getBuilder().length()) {
            PersistentList<String> prefixesToPrint = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function1.invoke();
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } else {
            function1.invoke();
        }
        if (length3 != getBuilder().length()) {
            PersistentList<String> prefixesToPrint2 = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function2.invoke();
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint2);
                }
                InlineMarker.finallyEnd(1);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint2);
                }
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        } else {
            function2.invoke();
        }
        if (length2 != getBuilder().length()) {
            PersistentList<String> prefixesToPrint3 = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function3.invoke();
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint3);
                }
                InlineMarker.finallyEnd(1);
            } catch (Throwable th3) {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint3);
                }
                InlineMarker.finallyEnd(1);
                throw th3;
            }
        } else {
            function3.invoke();
        }
        if (length == getBuilder().length()) {
            function4.invoke();
            return;
        }
        PersistentList<String> prefixesToPrint4 = getPrefixesToPrint();
        setPrefixesToPrint(getPrefixesToPrint().add(str));
        try {
            function4.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (!getPrefixesToPrint().isEmpty()) {
                setPrefixesToPrint(prefixesToPrint4);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public final void setIndent(int i) {
        this.indent = i;
    }

    public final void setPrefixesToPrint(PersistentList<String> persistentList) {
        persistentList.getClass();
        this.prefixesToPrint = persistentList;
    }

    public String toString() {
        return this.builder.toString();
    }

    public final void withIndent(Function1<? super PrettyPrinter, Unit> block) {
        block.getClass();
        setIndent(getIndent() + 1);
        block.invoke(this);
        setIndent(getIndent() - 1);
    }

    public final void withIndentInBraces(Function1<? super PrettyPrinter, Unit> block) throws IOException {
        block.getClass();
        append("{");
        append('\n');
        setIndent(getIndent() + 1);
        block.invoke(this);
        setIndent(getIndent() - 1);
        append('\n');
        append("}");
    }

    public final void withIndentInSquareBrackets(Function1<? super PrettyPrinter, Unit> block) throws IOException {
        block.getClass();
        append("[");
        append('\n');
        setIndent(getIndent() + 1);
        block.invoke(this);
        setIndent(getIndent() - 1);
        append('\n');
        append("]");
    }

    public final void withIndentWrapped(String before, String after, Function1<? super PrettyPrinter, Unit> block) throws IOException {
        before.getClass();
        after.getClass();
        block.getClass();
        append(before);
        append('\n');
        setIndent(getIndent() + 1);
        block.invoke(this);
        setIndent(getIndent() - 1);
        append('\n');
        append(after);
    }

    public final void withIndents(int indentCount, Function1<? super PrettyPrinter, Unit> block) {
        block.getClass();
        if (indentCount < 0) {
            w01.a("Number of indents should be non-negative");
            return;
        }
        setIndent(getIndent() + indentCount);
        block.invoke(this);
        setIndent(getIndent() - indentCount);
    }

    public final void withPrefix(String prefix, Function0<Unit> print) {
        prefix.getClass();
        print.getClass();
        PersistentList<String> prefixesToPrint = getPrefixesToPrint();
        setPrefixesToPrint(getPrefixesToPrint().add(prefix));
        try {
            print.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (!getPrefixesToPrint().isEmpty()) {
                setPrefixesToPrint(prefixesToPrint);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public final void withSuffix(String suffix, Function0<Unit> p1) {
        suffix.getClass();
        p1.getClass();
        int length = getBuilder().length();
        p1.invoke();
        if (length != getBuilder().length()) {
            append(suffix);
        }
    }

    public PrettyPrinter() {
        this(0, 1, null);
    }

    public /* synthetic */ PrettyPrinter(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2 : i);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence nullableSeq, int start, int end) {
        if (nullableSeq == null) {
            nullableSeq = "null";
        }
        append(nullableSeq.subSequence(start, end));
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char c) {
        printPrefixes();
        if (c != '\n') {
            appendIndentIfNeeded();
        }
        this.builder.append(c);
        return this;
    }

    public final void separated(String str, Function0<Unit> function0, Function0<Unit> function1, Function0<Unit> function2) {
        str.getClass();
        function0.getClass();
        function1.getClass();
        function2.getClass();
        int length = getBuilder().length();
        int length2 = getBuilder().length();
        function0.invoke();
        if (length2 != getBuilder().length()) {
            PersistentList<String> prefixesToPrint = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function1.invoke();
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } else {
            function1.invoke();
        }
        if (length != getBuilder().length()) {
            PersistentList<String> prefixesToPrint2 = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function2.invoke();
                return;
            } finally {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint2);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        function2.invoke();
    }

    public final void separated(String str, Function0<Unit> function0, Function0<Unit> function1, Function0<Unit> function2, Function0<Unit> function3) {
        str.getClass();
        function0.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        int length = getBuilder().length();
        int length2 = getBuilder().length();
        int length3 = getBuilder().length();
        function0.invoke();
        if (length3 != getBuilder().length()) {
            PersistentList<String> prefixesToPrint = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function1.invoke();
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } else {
            function1.invoke();
        }
        if (length2 != getBuilder().length()) {
            PersistentList<String> prefixesToPrint2 = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function2.invoke();
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint2);
                }
                InlineMarker.finallyEnd(1);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint2);
                }
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        } else {
            function2.invoke();
        }
        if (length != getBuilder().length()) {
            PersistentList<String> prefixesToPrint3 = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function3.invoke();
                return;
            } finally {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint3);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        function3.invoke();
    }

    public final void separated(String str, Function0<Unit> function0, Function0<Unit> function1) {
        str.getClass();
        function0.getClass();
        function1.getClass();
        int length = getBuilder().length();
        function0.invoke();
        if (length != getBuilder().length()) {
            PersistentList<String> prefixesToPrint = getPrefixesToPrint();
            setPrefixesToPrint(getPrefixesToPrint().add(str));
            try {
                function1.invoke();
                return;
            } finally {
                InlineMarker.finallyStart(1);
                if (!getPrefixesToPrint().isEmpty()) {
                    setPrefixesToPrint(prefixesToPrint);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        function1.invoke();
    }
}
