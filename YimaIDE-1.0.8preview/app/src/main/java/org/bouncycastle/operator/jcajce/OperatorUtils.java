package org.bouncycastle.operator.jcajce;

import defpackage.w01;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.operator.GenericKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class OperatorUtils {
    public static Key getJceKey(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return (Key) genericKey.getRepresentation();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return new SecretKeySpec((byte[]) genericKey.getRepresentation(), "ENC");
        }
        w01.a("unknown generic key type");
        return null;
    }
}
