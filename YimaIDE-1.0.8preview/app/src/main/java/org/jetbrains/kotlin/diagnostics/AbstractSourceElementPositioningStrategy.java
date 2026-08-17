package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", Argument.Delimiters.none, "<init>", "()V", "markDiagnostic", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "isValid", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractSourceElementPositioningStrategy {
    public abstract boolean isValid(AbstractKtSourceElement element);

    public abstract List<TextRange> markDiagnostic(KtDiagnosticWithSource diagnostic);
}
