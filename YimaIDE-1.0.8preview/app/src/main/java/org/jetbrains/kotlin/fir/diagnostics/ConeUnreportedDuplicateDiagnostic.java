package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeUnreportedDuplicateDiagnostic;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "original", "<init>", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getOriginal", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeUnreportedDuplicateDiagnostic implements ConeDiagnostic {
    private final ConeDiagnostic original;

    public ConeUnreportedDuplicateDiagnostic(ConeDiagnostic coneDiagnostic) {
        coneDiagnostic.getClass();
        this.original = coneDiagnostic;
    }

    public final ConeDiagnostic getOriginal() {
        return this.original;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return this.original.getReason();
    }
}
