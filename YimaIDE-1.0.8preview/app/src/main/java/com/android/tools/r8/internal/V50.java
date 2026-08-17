package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.DataResource;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V50 implements Serializable {
    public final U50 b;
    public final String c;
    public final String d;
    public final int e;
    public final boolean f;

    public V50(U50 u50, String str, String str2, int i, boolean z) {
        this.b = u50;
        this.c = str;
        this.d = str2;
        this.e = i;
        this.f = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ea, code lost:
    
        if (r3 == null) goto L79;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static V50 a(String str) {
        int i;
        int i2;
        boolean z;
        U50 u50;
        int length = str.length();
        if (length != 0) {
            boolean z2 = false;
            char cCharAt = str.charAt(0);
            char cCharAt2 = "?".charAt(0);
            char cCharAt3 = "@".charAt(0);
            if (cCharAt2 == cCharAt) {
                i2 = 3;
                i = 1;
                z2 = true;
            } else if (cCharAt3 != cCharAt) {
                i = 0;
                z2 = false;
                i2 = 1;
            } else if (1 != length) {
                i = str.charAt(1) == '+' ? 2 : 1;
                i2 = i;
            }
            if (i != 0 && i != length) {
                if (str.charAt(i) == '*') {
                    i++;
                    z = true;
                } else {
                    z = false;
                }
                int i3 = -1;
                int i4 = -1;
                int i5 = -1;
                int i6 = -1;
                int i7 = i;
                while (true) {
                    if ((i3 != -1 && i4 != -1) || i7 >= length) {
                        break;
                    }
                    char cCharAt4 = str.charAt(i7);
                    if (cCharAt4 != '/') {
                        if (cCharAt4 != ':') {
                            if (cCharAt4 == '[') {
                                while (']' != cCharAt4 && i7 < length - 1) {
                                    i7++;
                                    cCharAt4 = str.charAt(i7);
                                }
                            }
                        } else if (i4 != -1) {
                            continue;
                        } else if (i != i7) {
                            i4 = i;
                            i = i7 + 1;
                            i6 = i7;
                        }
                    } else if (i3 == -1) {
                        i3 = i;
                        i = i7 + 1;
                        i5 = i7;
                    }
                    i7++;
                }
                if (length > i) {
                    String strSubstring = str.substring(i, length);
                    if (i5 > i3) {
                        String strSubstring2 = str.substring(i3, i5);
                        U50 u51 = U50.ANIM;
                        if (strSubstring2.equals("declare-styleable") || strSubstring2.equals("styleable")) {
                            u50 = null;
                        } else {
                            u50 = U50.G;
                            if (!strSubstring2.equals("sample")) {
                                u50 = U50.D;
                                if (!strSubstring2.equals("_aapt")) {
                                    u50 = U50.E;
                                    if (!strSubstring2.equals("overlayable")) {
                                        u50 = U50.H;
                                        if (!strSubstring2.equals("macro")) {
                                            u50 = (U50) U50.I.get(strSubstring2);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (z2) {
                        u50 = U50.ATTR;
                        return new V50(u50, strSubstring, i4 < i6 ? str.substring(i4, i6) : null, i2, z);
                    }
                }
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && V50.class == obj.getClass()) {
            V50 v50 = (V50) obj;
            if (this.e == v50.e && this.b == v50.b && Objects.equals(this.c, v50.c) && Objects.equals(this.d, v50.d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.e;
        return Objects.hash(AbstractC0007c.a(i), this.b, this.c, this.d);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int iB = AbstractC0007c.b(this.e);
        if (iB == 0) {
            sb.append("@");
        } else if (iB == 1) {
            sb.append("@+");
        } else if (iB == 2) {
            sb.append("?");
        }
        if (this.f) {
            sb.append('*');
        }
        String str = this.d;
        if (str != null) {
            sb.append(str);
            sb.append(':');
        }
        if (this.e != 4) {
            sb.append(this.b.b);
            sb.append(DataResource.SEPARATOR);
        }
        sb.append(this.c);
        return sb.toString();
    }
}
