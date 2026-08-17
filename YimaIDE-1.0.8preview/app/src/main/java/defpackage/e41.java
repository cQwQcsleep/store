package defpackage;

import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final /* synthetic */ class e41 {
    public static /* synthetic */ void a(Object obj, Object obj2) throws TimeoutException {
        StringBuilder sb = new StringBuilder();
        sb.append(obj);
        sb.append((Object) " for ");
        sb.append(obj2);
        throw new TimeoutException(sb.toString());
    }
}
