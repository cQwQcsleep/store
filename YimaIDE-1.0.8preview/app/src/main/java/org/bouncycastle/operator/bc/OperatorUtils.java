package org.bouncycastle.operator.bc;

import defpackage.w01;
import java.security.Key;
import org.bouncycastle.operator.GenericKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class OperatorUtils {
    public static byte[] getKeyBytes(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return ((Key) genericKey.getRepresentation()).getEncoded();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return (byte[]) genericKey.getRepresentation();
        }
        w01.a("unknown generic key type");
        return null;
    }
}
