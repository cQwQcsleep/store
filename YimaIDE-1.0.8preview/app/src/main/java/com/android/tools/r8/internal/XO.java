package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class XO {
    public final int a;
    public XO b;

    public XO(int i, XO xo) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            w01.a(CX.a(i, "Unsupported api "));
            throw null;
        }
        if (i == 17432576) {
            ug.a(this);
        }
        this.a = i;
        this.b = xo;
    }

    public void a(int i, String str, String str2, String str3, boolean z) {
        int i2 = this.a;
        if (i2 >= 327680 || (i & Fcntl.S_IRUSR) != 0) {
            XO xo = this.b;
            if (xo != null) {
                xo.a(i & (-257), str, str2, str3, z);
                return;
            }
            return;
        }
        if (z == (i == 185)) {
            a(i | (i2 < 327680 ? Fcntl.S_IRUSR : 0), str, str2, str3, i == 185);
        } else {
            c41.a("INVOKESPECIAL/STATIC on interfaces requires ASM5");
        }
    }

    public J2 b(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.a < 327680) {
            c41.a("This feature requires ASM5");
            return null;
        }
        XO xo = this.b;
        if (xo != null) {
            return xo.b(i, c3052xj0, str, z);
        }
        return null;
    }

    public J2 c(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.a < 327680) {
            c41.a("This feature requires ASM5");
            return null;
        }
        XO xo = this.b;
        if (xo != null) {
            return xo.c(i, c3052xj0, str, z);
        }
        return null;
    }

    public void d(int i, int i2) {
        XO xo = this.b;
        if (xo != null) {
            xo.d(i, i2);
        }
    }

    public void b() {
        XO xo = this.b;
        if (xo != null) {
            xo.b();
        }
    }

    public void c(int i, String str) {
        XO xo = this.b;
        if (xo != null) {
            xo.c(i, str);
        }
    }

    public void b(int i, int i2) {
        XO xo = this.b;
        if (xo != null) {
            xo.b(i, i2);
        }
    }

    public void c(int i, int i2) {
        XO xo = this.b;
        if (xo != null) {
            xo.c(i, i2);
        }
    }

    public void b(int i, String str) {
        if (this.a >= 327680) {
            XO xo = this.b;
            if (xo != null) {
                xo.b(i, str);
                return;
            }
            return;
        }
        c41.a("This feature requires ASM5");
    }

    public void c() {
        XO xo = this.b;
        if (xo != null) {
            xo.c();
        }
    }

    public void b(int i, WI wi) {
        XO xo = this.b;
        if (xo != null) {
            xo.b(i, wi);
        }
    }

    public J2 a(String str, boolean z) {
        XO xo = this.b;
        if (xo != null) {
            return xo.a(str, z);
        }
        return null;
    }

    public void a(int i, boolean z) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, z);
        }
    }

    public J2 a(int i, String str, boolean z) {
        XO xo = this.b;
        if (xo != null) {
            return xo.a(i, str, z);
        }
        return null;
    }

    public void a(H4 h4) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(h4);
        }
    }

    public void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, i2, objArr, i3, objArr2);
        }
    }

    public void a(int i) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i);
        }
    }

    public void a(int i, String str, String str2, String str3) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, str, str2, str3);
        }
    }

    public J2 a() {
        XO xo = this.b;
        if (xo != null) {
            return xo.a();
        }
        return null;
    }

    public void a(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        if (this.a >= 327680) {
            XO xo = this.b;
            if (xo != null) {
                xo.a(str, str2, c0497Fs, objArr);
                return;
            }
            return;
        }
        c41.a("This feature requires ASM5");
    }

    public void a(int i, WI wi) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, wi);
        }
    }

    public void a(WI wi) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(wi);
        }
    }

    public void a(Object obj) {
        if (this.a < 327680 && ((obj instanceof C0497Fs) || ((obj instanceof C3050xi0) && ((C3050xi0) obj).c() == 11))) {
            c41.a("This feature requires ASM5");
            return;
        }
        if (this.a < 458752 && (obj instanceof C2190ng)) {
            c41.a("This feature requires ASM7");
            return;
        }
        XO xo = this.b;
        if (xo != null) {
            xo.a(obj);
        }
    }

    public void a(int i, int i2) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, i2);
        }
    }

    public void a(int i, int i2, WI wi, WI... wiArr) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, i2, wi, wiArr);
        }
    }

    public void a(WI wi, int[] iArr, WI[] wiArr) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(wi, iArr, wiArr);
        }
    }

    public void a(int i, String str) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(i, str);
        }
    }

    public J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.a >= 327680) {
            XO xo = this.b;
            if (xo != null) {
                return xo.a(i, c3052xj0, str, z);
            }
            return null;
        }
        c41.a("This feature requires ASM5");
        return null;
    }

    public void a(WI wi, WI wi2, WI wi3, String str) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(wi, wi2, wi3, str);
        }
    }

    public void a(String str, String str2, String str3, WI wi, WI wi2, int i) {
        XO xo = this.b;
        if (xo != null) {
            xo.a(str, str2, str3, wi, wi2, i);
        }
    }

    public J2 a(int i, C3052xj0 c3052xj0, WI[] wiArr, WI[] wiArr2, int[] iArr, String str, boolean z) {
        if (this.a >= 327680) {
            XO xo = this.b;
            if (xo != null) {
                return xo.a(i, c3052xj0, wiArr, wiArr2, iArr, str, z);
            }
            return null;
        }
        c41.a("This feature requires ASM5");
        return null;
    }
}
