package org.jetbrains.kotlin.analysis.utils.printer;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"prettyPrint", "", "body", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/analysis/utils/printer/PrettyPrinter;", "", "Lkotlin/ExtensionFunctionType;", "prettyPrintWithSettingsFrom", "other", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PrettyPrinterKt {
    public static final String prettyPrint(Function1<? super PrettyPrinter, Unit> function1) {
        function1.getClass();
        PrettyPrinter prettyPrinter = new PrettyPrinter(0, 1, null);
        function1.invoke(prettyPrinter);
        return prettyPrinter.toString();
    }

    public static final String prettyPrintWithSettingsFrom(PrettyPrinter prettyPrinter, Function1<? super PrettyPrinter, Unit> function1) {
        prettyPrinter.getClass();
        function1.getClass();
        PrettyPrinter prettyPrinter2 = new PrettyPrinter(prettyPrinter.getIndentSize());
        function1.invoke(prettyPrinter2);
        return prettyPrinter2.toString();
    }
}
