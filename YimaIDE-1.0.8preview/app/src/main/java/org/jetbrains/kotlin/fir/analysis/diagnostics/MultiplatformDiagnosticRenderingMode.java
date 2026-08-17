package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J(\u0010\t\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\u0016J(\u0010\r\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/MultiplatformDiagnosticRenderingMode;", Argument.Delimiters.none, "<init>", "()V", "newLine", Argument.Delimiters.none, "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "renderList", "elements", Argument.Delimiters.none, "Lkotlin/Function0;", "renderSymbol", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "indent", Argument.Delimiters.none, "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class MultiplatformDiagnosticRenderingMode {
    public void newLine(StringBuilder sb) {
        sb.getClass();
        sb.append('\n');
    }

    public void renderList(StringBuilder sb, List<? extends Function0<Unit>> elements) {
        sb.getClass();
        elements.getClass();
        sb.append('\n');
        Iterator<? extends Function0<Unit>> it = elements.iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
    }

    public void renderSymbol(StringBuilder sb, FirBasedSymbol<?> symbol, String indent) {
        sb.getClass();
        symbol.getClass();
        indent.getClass();
        sb.append(indent);
        sb.append(FirPlatformIncompatibilityDiagnosticRendererKt.INDENTATION_UNIT);
        sb.append(FirDiagnosticRenderers.INSTANCE.getSYMBOL().render(symbol));
        sb.append('\n');
    }
}
