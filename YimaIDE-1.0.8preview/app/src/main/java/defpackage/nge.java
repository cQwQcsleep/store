package defpackage;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class nge {
    public static final long a(String str) {
        str.getClass();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (b(str.charAt(i3))) {
                i++;
            } else {
                i2++;
            }
        }
        return (long) ((((double) i) / 1.6d) + (((double) i2) / 3.5d));
    }

    public static final boolean b(char c) {
        if (19968 <= c && c < 40960) {
            return true;
        }
        if (12288 <= c && c < 12352) {
            return true;
        }
        if (12352 > c || c >= 12544) {
            return 65280 <= c && c < 65520;
        }
        return true;
    }
}
