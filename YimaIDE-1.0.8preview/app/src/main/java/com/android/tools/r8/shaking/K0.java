package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.nio.file.Path;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class K0 {
    public final Path a;
    public final AbstractC0551Hu b;
    public final Origin c;
    public final Position d;

    public K0(Path path, List<String> list, Origin origin, Position position) {
        this.a = path;
        this.b = AbstractC0551Hu.a(list);
        this.c = origin;
        this.d = position;
    }

    public static boolean a(int i, int i2, String str, String str2) {
        if (i2 >= str2.length()) {
            return i == str.length();
        }
        char cCharAt = str2.charAt(i2);
        if (cCharAt == '*') {
            int i3 = i2 + 1;
            boolean z = str2.length() > i3 && str2.charAt(i3) == '*';
            if (z) {
                i2 = i3;
            }
            int i4 = i2 + 1;
            String strSubstring = str2.substring(i4);
            if (strSubstring.indexOf(42) == -1) {
                int length = strSubstring.length();
                if (i + length > str.length()) {
                    return false;
                }
                if (z || str.substring(i, str.length() - length).indexOf(47) == -1) {
                    return a(str.length() - length, i4, str, str2);
                }
            } else {
                while (i < str.length()) {
                    if (!z && str.charAt(i) == '/') {
                        return false;
                    }
                    if (a(i, i4, str, str2)) {
                        return true;
                    }
                    i++;
                }
            }
        } else {
            if (i >= str.length()) {
                return false;
            }
            if (cCharAt == '?' || cCharAt == str.charAt(i)) {
                return a(i + 1, i2 + 1, str, str2);
            }
        }
        return false;
    }

    public final String toString() {
        boolean zIsEmpty = this.b.isEmpty();
        Path path = this.a;
        if (zIsEmpty) {
            return path.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append('(');
        Ck0 it = this.b.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!z) {
                sb.append(',');
            }
            sb.append(str);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }

    public final Path a() {
        return this.a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean a(String str) {
        if (this.b.isEmpty()) {
            return true;
        }
        Ck0 it = this.b.iterator();
        boolean z = 0;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            int i = str2.charAt(0) == '!' ? 1 : 0;
            if (a(0, i, str, str2)) {
                return i ^ 1;
            }
            z = i;
        }
        return z;
    }
}
