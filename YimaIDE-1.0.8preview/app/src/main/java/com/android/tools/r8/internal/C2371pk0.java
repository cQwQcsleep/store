package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pk0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2371pk0 {
    public C2456qk0 a = new C2456qk0();

    public final C2371pk0 a(C2456qk0 c2456qk0) {
        if (!c2456qk0.a.isEmpty()) {
            C2456qk0 c2456qk1 = this.a;
            if (c2456qk1.a == null) {
                c2456qk1.a = new ArrayList();
            }
            this.a.a.addAll(c2456qk0.a);
        }
        if (!c2456qk0.b.isEmpty()) {
            C2456qk0 c2456qk2 = this.a;
            if (c2456qk2.b == null) {
                c2456qk2.b = new ArrayList();
            }
            this.a.b.addAll(c2456qk0.b);
        }
        if (!c2456qk0.c.isEmpty()) {
            C2456qk0 c2456qk3 = this.a;
            if (c2456qk3.c == null) {
                c2456qk3.c = new ArrayList();
            }
            this.a.c.addAll(c2456qk0.c);
        }
        if (!c2456qk0.d.isEmpty()) {
            C2456qk0 c2456qk4 = this.a;
            if (c2456qk4.d == null) {
                c2456qk4.d = new ArrayList();
            }
            this.a.d.addAll(c2456qk0.d);
        }
        if (!c2456qk0.e.isEmpty()) {
            C2456qk0 c2456qk5 = this.a;
            if (c2456qk5.e == null) {
                c2456qk5.e = new ArrayList();
            }
            this.a.e.addAll(c2456qk0.e);
        }
        return this;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C2371pk0 m18clone() {
        C2456qk0 c2456qk0 = new C2456qk0();
        if (this.a.a == null) {
            c2456qk0.a = null;
        } else {
            c2456qk0.a = new ArrayList(this.a.a);
        }
        if (this.a.b == null) {
            c2456qk0.b = null;
        } else {
            c2456qk0.b = new ArrayList(this.a.b);
        }
        if (this.a.c == null) {
            c2456qk0.c = null;
        } else {
            c2456qk0.c = new ArrayList(this.a.c);
        }
        if (this.a.d == null) {
            c2456qk0.d = null;
        } else {
            c2456qk0.d = new ArrayList(this.a.d);
        }
        if (this.a.e == null) {
            c2456qk0.e = null;
        } else {
            c2456qk0.e = new ArrayList(this.a.e);
        }
        C2371pk0 c2371pk0 = new C2371pk0();
        c2371pk0.a = c2456qk0;
        return c2371pk0;
    }

    public final C2456qk0 a() {
        C2456qk0 c2456qk0 = new C2456qk0();
        if (this.a.a == null) {
            c2456qk0.a = Collections.EMPTY_LIST;
        } else {
            c2456qk0.a = Collections.unmodifiableList(new ArrayList(this.a.a));
        }
        if (this.a.b == null) {
            c2456qk0.b = Collections.EMPTY_LIST;
        } else {
            c2456qk0.b = Collections.unmodifiableList(new ArrayList(this.a.b));
        }
        if (this.a.c == null) {
            c2456qk0.c = Collections.EMPTY_LIST;
        } else {
            c2456qk0.c = Collections.unmodifiableList(new ArrayList(this.a.c));
        }
        if (this.a.d == null) {
            c2456qk0.d = Collections.EMPTY_LIST;
        } else {
            c2456qk0.d = Collections.unmodifiableList(new ArrayList(this.a.d));
        }
        if (this.a.e == null) {
            c2456qk0.e = Collections.EMPTY_LIST;
            return c2456qk0;
        }
        c2456qk0.e = Collections.unmodifiableList(new ArrayList(this.a.e));
        return c2456qk0;
    }
}
