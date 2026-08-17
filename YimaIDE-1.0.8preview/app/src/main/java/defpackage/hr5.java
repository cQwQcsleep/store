package defpackage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public final /* synthetic */ class hr5 {
    public static /* synthetic */ void a(StringBuilder sb, Object obj, int i, int i2) {
        sb.append(obj);
        sb.append(i);
        sb.append(" but position is ");
        sb.append(i2);
        throw new IllegalStateException(sb.toString());
    }
}
