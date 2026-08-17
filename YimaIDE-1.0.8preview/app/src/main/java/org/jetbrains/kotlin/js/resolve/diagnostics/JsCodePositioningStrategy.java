package org.jetbrains.kotlin.js.resolve.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticMarker;
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1;
import org.jetbrains.kotlin.diagnostics.PositioningStrategy;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCodePositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lcom/intellij/psi/PsiElement;", "<init>", "()V", "markDiagnostic", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsCodePositioningStrategy extends PositioningStrategy<PsiElement> {
    public static final JsCodePositioningStrategy INSTANCE = new JsCodePositioningStrategy();

    private JsCodePositioningStrategy() {
    }

    @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
    public List<TextRange> markDiagnostic(DiagnosticMarker diagnostic) {
        diagnostic.getClass();
        return CollectionsKt.listOf(((JsCallData) ((DiagnosticWithParameters1) diagnostic).getA()).getReportRange());
    }
}
