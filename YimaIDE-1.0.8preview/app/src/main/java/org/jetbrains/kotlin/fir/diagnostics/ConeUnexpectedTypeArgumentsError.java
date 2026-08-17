package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeUnexpectedTypeArgumentsError;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "reason", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getReason", "()Ljava/lang/String;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeUnexpectedTypeArgumentsError implements ConeDiagnostic {
    private final String reason;
    private final KtSourceElement source;

    public ConeUnexpectedTypeArgumentsError(String str, KtSourceElement ktSourceElement) {
        str.getClass();
        this.reason = str;
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return this.reason;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public /* synthetic */ ConeUnexpectedTypeArgumentsError(String str, KtSourceElement ktSourceElement, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : ktSourceElement);
    }
}
