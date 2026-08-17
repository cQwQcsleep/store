package org.jetbrains.kotlin.ir.backend.js.utils;

import java.util.HashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/utils/JsSignaturesPool;", "", "<init>", "()V", "signaturesToId", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getSignatureId", "signature", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsSignaturesPool {
    private final HashMap<String, Integer> signaturesToId = new HashMap<>();

    public final int getSignatureId(String signature) {
        signature.getClass();
        HashMap<String, Integer> map = this.signaturesToId;
        Integer numValueOf = map.get(signature);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(this.signaturesToId.size());
            map.put(signature, numValueOf);
        }
        return numValueOf.intValue();
    }
}
