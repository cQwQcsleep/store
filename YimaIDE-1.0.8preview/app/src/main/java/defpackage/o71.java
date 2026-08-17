package defpackage;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class o71 {
    public static /* synthetic */ void a(int i, int i2) {
        StringBuilder sb = new StringBuilder(40);
        sb.append((Object) "Length too large: ");
        sb.append(i);
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }
}
