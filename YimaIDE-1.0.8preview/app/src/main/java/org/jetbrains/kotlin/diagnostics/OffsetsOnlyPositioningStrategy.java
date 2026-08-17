package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/OffsetsOnlyPositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "<init>", "()V", "markKtDiagnostic", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "mark", "startOffset", Argument.Delimiters.none, "endOffset", "markDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "isValid", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class OffsetsOnlyPositioningStrategy extends AbstractSourceElementPositioningStrategy {
    @Override // org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy
    public boolean isValid(AbstractKtSourceElement element) {
        element.getClass();
        return true;
    }

    public List<TextRange> mark(int startOffset, int endOffset) {
        return OffsetsOnlyPositioningStrategyKt.markElement(startOffset, endOffset);
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy
    public List<TextRange> markDiagnostic(KtDiagnosticWithSource diagnostic) {
        diagnostic.getClass();
        return markKtDiagnostic(diagnostic.getElement(), diagnostic);
    }

    public List<TextRange> markKtDiagnostic(AbstractKtSourceElement element, KtDiagnostic diagnostic) {
        element.getClass();
        diagnostic.getClass();
        return mark(element.getStartOffset(), element.getEndOffset());
    }
}
