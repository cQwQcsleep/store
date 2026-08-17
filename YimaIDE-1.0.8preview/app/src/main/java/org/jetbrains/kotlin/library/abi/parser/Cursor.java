package org.jetbrains.kotlin.library.abi.parser;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B+\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\u000bJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J$\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u0016J*\u0010\u0019\u001a\u0004\u0018\u00010\u00042\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u001e2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u0016J$\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u0016J\u0006\u0010\"\u001a\u00020\u0000J\r\u0010\u001c\u001a\u00020\u0018H\u0000¢\u0006\u0002\b#R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/parser/Cursor;", "", "lines", "", "", "rowIndex", "", "columnIndex", "<init>", "(Ljava/util/List;II)V", "text", "(Ljava/lang/String;)V", "value", "getRowIndex", "()I", "getColumnIndex", "currentLine", "getCurrentLine", "()Ljava/lang/String;", "offset", "getOffset", "isFinished", "", "nextLine", "", "parseSymbol", "symbol", "peek", "skipInlineWhitespace", "symbols", "", "pattern", "Lkotlin/text/Regex;", "parseValidIdentifier", "copy", "skipInlineWhitespace$org_jetbrains_kotlin_kotlin_util_klib_abi", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Cursor {
    private int columnIndex;
    private final List<String> lines;
    private final int offset;
    private int rowIndex;

    private Cursor(List<String> list, int i, int i2) {
        this.lines = list;
        this.rowIndex = i;
        this.columnIndex = i2;
        int length = 0;
        Iterator<T> it = list.subList(0, i).iterator();
        while (it.hasNext()) {
            length += ((String) it.next()).length();
        }
        this.offset = length + i2;
    }

    public static /* synthetic */ String parseSymbol$default(Cursor cursor, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return cursor.parseSymbol(str, z, z2);
    }

    public static /* synthetic */ String parseValidIdentifier$default(Cursor cursor, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return cursor.parseValidIdentifier(z);
    }

    public final Cursor copy() {
        return new Cursor(this.lines, this.rowIndex, this.columnIndex);
    }

    public final int getColumnIndex() {
        return this.columnIndex;
    }

    public final String getCurrentLine() {
        return this.lines.get(this.rowIndex).substring(this.columnIndex);
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getRowIndex() {
        return this.rowIndex;
    }

    public final boolean isFinished() {
        return this.rowIndex >= this.lines.size();
    }

    public final void nextLine() {
        this.rowIndex++;
        this.columnIndex = 0;
        if (isFinished()) {
            return;
        }
        skipInlineWhitespace$org_jetbrains_kotlin_kotlin_util_klib_abi();
    }

    public final String parseSymbol(Collection<String> symbols, boolean peek, boolean skipInlineWhitespace) {
        Object next;
        symbols.getClass();
        String currentLine = getCurrentLine();
        Iterator<T> it = symbols.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!StringsKt.startsWith$default(currentLine, (String) next, false, 2, (Object) null));
        String str = (String) next;
        if (str == null) {
            return null;
        }
        if (!peek) {
            this.columnIndex += str.length();
            if (skipInlineWhitespace) {
                skipInlineWhitespace$org_jetbrains_kotlin_kotlin_util_klib_abi();
            }
        }
        return str;
    }

    public final String parseValidIdentifier(boolean peek) {
        return parseSymbol$default(this, CursorKt.validIdentifierRegex, peek, false, 4, (Object) null);
    }

    public final void skipInlineWhitespace$org_jetbrains_kotlin_kotlin_util_klib_abi() {
        String currentLine = getCurrentLine();
        int length = currentLine.length();
        int length2 = 0;
        while (true) {
            if (length2 >= length) {
                length2 = -1;
                break;
            } else if (!CharsKt.isWhitespace(currentLine.charAt(length2))) {
                break;
            } else {
                length2++;
            }
        }
        if (length2 == -1) {
            length2 = currentLine.length();
        }
        if (length2 != 0) {
            this.columnIndex += length2;
        }
    }

    public static /* synthetic */ String parseSymbol$default(Cursor cursor, Collection collection, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return cursor.parseSymbol((Collection<String>) collection, z, z2);
    }

    public static /* synthetic */ String parseSymbol$default(Cursor cursor, Regex regex, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return cursor.parseSymbol(regex, z, z2);
    }

    public /* synthetic */ Cursor(List list, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Cursor(String str) {
        this(StringsKt.lines(str), 0, 0, 6, null);
        str.getClass();
    }

    public final String parseSymbol(String symbol, boolean peek, boolean skipInlineWhitespace) {
        symbol.getClass();
        if (!StringsKt.startsWith$default(getCurrentLine(), symbol, false, 2, (Object) null)) {
            return null;
        }
        if (!peek) {
            this.columnIndex += symbol.length();
            if (skipInlineWhitespace) {
                skipInlineWhitespace$org_jetbrains_kotlin_kotlin_util_klib_abi();
            }
        }
        return symbol;
    }

    public final String parseSymbol(Regex pattern, boolean peek, boolean skipInlineWhitespace) {
        String value;
        pattern.getClass();
        MatchResult matchResultFind$default = Regex.find$default(pattern, getCurrentLine(), 0, 2, (Object) null);
        if (matchResultFind$default == null || (value = matchResultFind$default.getValue()) == null) {
            return null;
        }
        if (!peek) {
            this.columnIndex += matchResultFind$default.getRange().getLast() + 1;
            if (skipInlineWhitespace) {
                skipInlineWhitespace$org_jetbrains_kotlin_kotlin_util_klib_abi();
            }
        }
        return value;
    }
}
