package org.jetbrains.kotlin.js.resolve.diagnostics;

import com.intellij.openapi.util.TextRange;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCallDataWithCode;", "Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCallData;", "reportRange", "Lcom/intellij/openapi/util/TextRange;", "message", "", "code", "codeRange", "<init>", "(Lcom/intellij/openapi/util/TextRange;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/openapi/util/TextRange;)V", "getCode", "()Ljava/lang/String;", "getCodeRange", "()Lcom/intellij/openapi/util/TextRange;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsCallDataWithCode extends JsCallData {
    private final String code;
    private final TextRange codeRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsCallDataWithCode(TextRange textRange, String str, String str2, TextRange textRange2) {
        super(textRange, str);
        textRange.getClass();
        str.getClass();
        str2.getClass();
        textRange2.getClass();
        this.code = str2;
        this.codeRange = textRange2;
    }

    public final String getCode() {
        return this.code;
    }

    public final TextRange getCodeRange() {
        return this.codeRange;
    }
}
