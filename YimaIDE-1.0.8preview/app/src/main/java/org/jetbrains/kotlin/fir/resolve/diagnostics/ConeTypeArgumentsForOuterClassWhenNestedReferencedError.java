package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnosticWithSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeTypeArgumentsForOuterClassWhenNestedReferencedError;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnosticWithSource;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeArgumentsForOuterClassWhenNestedReferencedError extends ConeDiagnosticWithSource {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeArgumentsForOuterClassWhenNestedReferencedError(KtSourceElement ktSourceElement) {
        super(ktSourceElement);
        ktSourceElement.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Type arguments for outer class are redundant when nested class is referenced";
    }
}
