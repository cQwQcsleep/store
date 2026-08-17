package org.jetbrains.kotlin.ir.backend.js.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.js.util.TextOutputImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class JsCodeOutlineTransformer$printJsCodeWithDebugInfo$sourceMapBuilder$1 extends FunctionReferenceImpl implements Function0<Integer> {
    public JsCodeOutlineTransformer$printJsCodeWithDebugInfo$sourceMapBuilder$1(Object obj) {
        super(0, obj, TextOutputImpl.class, "getColumn", "getColumn()I", 0);
    }

    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
    public final Integer m234invoke() {
        return Integer.valueOf(((TextOutputImpl) ((CallableReference) this).receiver).getColumn());
    }
}
