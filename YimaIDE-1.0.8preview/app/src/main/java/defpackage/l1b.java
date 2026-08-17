package defpackage;

import java.util.ConcurrentModificationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final /* synthetic */ class l1b {
    public static /* synthetic */ void a(Object obj) {
        throw new ConcurrentModificationException("Hash code of a key (" + obj + ((Object) ") has changed after it was added to the persistent map."));
    }
}
