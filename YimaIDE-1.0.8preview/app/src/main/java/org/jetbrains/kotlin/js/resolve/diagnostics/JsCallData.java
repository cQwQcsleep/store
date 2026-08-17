package org.jetbrains.kotlin.js.resolve.diagnostics;

import com.intellij.openapi.util.TextRange;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCallData;", "", "reportRange", "Lcom/intellij/openapi/util/TextRange;", "message", "", "<init>", "(Lcom/intellij/openapi/util/TextRange;Ljava/lang/String;)V", "getReportRange", "()Lcom/intellij/openapi/util/TextRange;", "getMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class JsCallData {
    private final String message;
    private final TextRange reportRange;

    public JsCallData(TextRange textRange, String str) {
        textRange.getClass();
        str.getClass();
        this.reportRange = textRange;
        this.message = str;
    }

    public final String getMessage() {
        return this.message;
    }

    public final TextRange getReportRange() {
        return this.reportRange;
    }
}
