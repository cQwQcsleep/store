package defpackage;

import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract /* synthetic */ class hv3 {
    public static /* synthetic */ void a(Object obj) throws Exception {
        if (obj instanceof AutoCloseable) {
            ((AutoCloseable) obj).close();
        } else if (obj instanceof ExecutorService) {
            iv3.a((ExecutorService) obj);
        } else {
            jv3.a(obj);
        }
    }
}
