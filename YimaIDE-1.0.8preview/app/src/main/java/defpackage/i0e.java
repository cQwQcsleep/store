package defpackage;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class i0e {
    public static /* synthetic */ void a(int i, Object obj, int i2, int i3) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(obj);
        sb.append(i2);
        sb.append((Object) "+");
        sb.append(i3);
        throw new IllegalArgumentException(sb.toString());
    }
}
