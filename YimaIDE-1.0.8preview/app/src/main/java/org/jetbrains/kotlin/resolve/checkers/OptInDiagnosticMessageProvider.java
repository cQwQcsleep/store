package org.jetbrains.kotlin.resolve.checkers;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/OptInDiagnosticMessageProvider;", "", "<init>", "()V", "buildDefaultDiagnosticMessage", "", "markerName", "verb", "buildCustomDiagnosticMessage", "message", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public abstract class OptInDiagnosticMessageProvider {
    public static /* synthetic */ String buildDefaultDiagnosticMessage$default(OptInDiagnosticMessageProvider optInDiagnosticMessageProvider, String str, String str2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: buildDefaultDiagnosticMessage");
            return null;
        }
        if ((i & 2) != 0) {
            str2 = "";
        }
        return optInDiagnosticMessageProvider.buildDefaultDiagnosticMessage(str, str2);
    }

    public String buildCustomDiagnosticMessage(String message) {
        message.getClass();
        return message;
    }

    public abstract String buildDefaultDiagnosticMessage(String markerName, String verb);
}
