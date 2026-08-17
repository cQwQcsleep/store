package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J%\u0010\f\u001a\u00020\u00002\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001H&¢\u0006\u0002\u0010\u000fJ%\u0010\u0010\u001a\u00020\u00002\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000e\"\u0004\u0018\u00010\u0001H&¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0000H&J\b\u0010\u0014\u001a\u00020\u0000H&J\n\u0010\u0015\u001a\u00020\u0003H¦\u0080\u0004R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/utils/IndentingPrinter;", "", "currentIndent", "", "getCurrentIndent", "()Ljava/lang/String;", "currentIndentLengthInUnits", "", "getCurrentIndentLengthInUnits", "()I", "indentUnitLength", "getIndentUnitLength", "println", "objects", "", "([Ljava/lang/Object;)Lorg/jetbrains/kotlin/utils/IndentingPrinter;", "print", "printlnMultiLine", "s", "pushIndent", "popIndent", "toString", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface IndentingPrinter {
    String getCurrentIndent();

    int getCurrentIndentLengthInUnits();

    int getIndentUnitLength();

    IndentingPrinter popIndent();

    IndentingPrinter print(Object... objects);

    IndentingPrinter println(Object... objects);

    IndentingPrinter printlnMultiLine(String s);

    IndentingPrinter pushIndent();

    String toString();
}
