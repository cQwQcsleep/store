package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0376Bb extends J2 {
    public final boolean b;
    public boolean c;

    public C0376Bb(J2 j2) {
        super(j2);
        this.b = true;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (this.c) {
            k2d.a("Cannot call a visit method after visitEnd has been called");
            return;
        }
        if (this.b && str == null) {
            w01.a("Annotation value name must not be null");
            return;
        }
        if (!(obj instanceof Byte) && !(obj instanceof Boolean) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof C3050xi0) && !(obj instanceof byte[]) && !(obj instanceof boolean[]) && !(obj instanceof char[]) && !(obj instanceof short[]) && !(obj instanceof int[]) && !(obj instanceof long[]) && !(obj instanceof float[]) && !(obj instanceof double[])) {
            w01.a("Invalid annotation value");
        } else if ((obj instanceof C3050xi0) && ((C3050xi0) obj).c() == 11) {
            w01.a("Invalid annotation value");
        } else {
            super.a(obj, str);
        }
    }

    public C0376Bb(J2 j2, int i) {
        super(j2);
        this.b = false;
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        if (!this.c) {
            if (this.b && str == null) {
                w01.a("Annotation value name must not be null");
                return null;
            }
            C0636Lb.d(49, str2);
            return new C0376Bb(super.a(str, str2));
        }
        k2d.a("Cannot call a visit method after visitEnd has been called");
        return null;
    }

    @Override // com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        if (!this.c) {
            if (this.b && str == null) {
                w01.a("Annotation value name must not be null");
                return null;
            }
            return new C0376Bb(super.a(str), 0);
        }
        k2d.a("Cannot call a visit method after visitEnd has been called");
        return null;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        if (!this.c) {
            this.c = true;
            super.a();
        } else {
            k2d.a("Cannot call a visit method after visitEnd has been called");
        }
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        if (!this.c) {
            if (this.b && str == null) {
                w01.a("Annotation value name must not be null");
                return;
            }
            C0636Lb.d(49, str2);
            if (str3 != null) {
                super.a(str, str2, str3);
                return;
            } else {
                w01.a("Invalid enum value");
                return;
            }
        }
        k2d.a("Cannot call a visit method after visitEnd has been called");
    }
}
