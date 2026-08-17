package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeSimpleDiagnostic;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "reason", Argument.Delimiters.none, "kind", "Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;)V", "getReason", "()Ljava/lang/String;", "getKind", "()Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSimpleDiagnostic implements ConeDiagnostic {
    private final DiagnosticKind kind;
    private final String reason;

    public ConeSimpleDiagnostic(String str, DiagnosticKind diagnosticKind) {
        str.getClass();
        diagnosticKind.getClass();
        this.reason = str;
        this.kind = diagnosticKind;
    }

    public final DiagnosticKind getKind() {
        return this.kind;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return this.reason;
    }

    public /* synthetic */ ConeSimpleDiagnostic(String str, DiagnosticKind diagnosticKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? DiagnosticKind.Other : diagnosticKind);
    }
}
