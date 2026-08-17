package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import org.jetbrains.kotlin.js.backend.JsToStringGenerationVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\r\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toJsStringLiteral", "", "", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JsHelpersKt {
    public static final CharSequence toJsStringLiteral(String str) {
        str.getClass();
        CharSequence charSequenceJavaScriptString = JsToStringGenerationVisitor.javaScriptString(str);
        charSequenceJavaScriptString.getClass();
        return charSequenceJavaScriptString;
    }
}
