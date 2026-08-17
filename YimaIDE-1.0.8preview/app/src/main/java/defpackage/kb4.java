package defpackage;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final /* synthetic */ class kb4 {
    public static /* synthetic */ void a(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(obj);
        sb.append((Object) " is a reserved key for the encryption keyset.");
        throw new SecurityException(sb.toString());
    }
}
