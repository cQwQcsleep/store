package com.android.tools.r8.internal;

import java.io.Serializable;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2063m8 implements InterfaceC2156nE, Serializable {
    public transient InterfaceC2156nE b;
    public final Object c = C1977l8.b;
    public final Class d;
    public final String e;
    public final String f;
    public final boolean g;

    public AbstractC2063m8(Class cls, String str, String str2, boolean z) {
        this.d = cls;
        this.e = str;
        this.f = str2;
        this.g = z;
    }

    public abstract InterfaceC2156nE c();

    public String d() {
        return this.e;
    }

    public InterfaceC2328pE e() {
        Class cls = this.d;
        if (cls == null) {
            return null;
        }
        if (!this.g) {
            return AbstractC2654t40.a(cls);
        }
        AbstractC2654t40.a.getClass();
        return new C1237cW(cls, XmlPullParser.NO_NAMESPACE);
    }

    public String f() {
        return this.f;
    }
}
