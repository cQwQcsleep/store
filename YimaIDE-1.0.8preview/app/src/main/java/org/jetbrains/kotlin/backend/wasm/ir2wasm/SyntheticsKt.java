package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"syntheticFqName", "", "toSyntheticSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CommonSignature;", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SyntheticsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final IdSignature.CommonSignature toSyntheticSignature(String str) {
        return new IdSignature.CommonSignature("__SYNTHETIC__", str, (Long) null, 0L, (String) null);
    }
}
