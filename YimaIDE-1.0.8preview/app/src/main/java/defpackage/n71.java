package defpackage;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class n71 {
    public static /* synthetic */ void a(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(59);
        sb.append((Object) "Ran off end of other: ");
        sb.append(i);
        sb.append((Object) ", ");
        sb.append(i2);
        sb.append((Object) ", ");
        sb.append(i3);
        throw new IllegalArgumentException(sb.toString());
    }
}
