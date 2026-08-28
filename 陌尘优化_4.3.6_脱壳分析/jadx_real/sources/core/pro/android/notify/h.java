package core.pro.android.notify;

import com.shadow.okio.Path;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract /* synthetic */ class h {
    public static String a(int i, String str) {
        return str + i;
    }

    public static String b(Path path, String str) {
        return str + path;
    }

    public static String c(String str, long j) {
        return str + j;
    }

    public static String d(String str, String str2, StringBuilder sb, Exception exc) {
        sb.append(l2.decrypt(str, str2));
        sb.append(exc.getMessage());
        return sb.toString();
    }

    public static String e(String str, String str2, StringBuilder sb, String str3) {
        sb.append(l2.decrypt(str, str2));
        sb.append(str3);
        return sb.toString();
    }

    public static void f(String str, String str2, StringBuilder sb, String str3) {
        sb.append(l2.decrypt(str, str2));
        sb.append(str3);
    }
}
