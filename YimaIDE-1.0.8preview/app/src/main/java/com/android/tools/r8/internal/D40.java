package com.android.tools.r8.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D40 {
    public final String a;
    public final Field b;
    public final boolean c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ Method e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ AbstractC3220zi0 g;
    public final /* synthetic */ C0471Es h;
    public final /* synthetic */ Fj0 i;

    public D40(String str, Field field, boolean z, boolean z2, boolean z3, Method method, boolean z4, AbstractC3220zi0 abstractC3220zi0, C0471Es c0471Es, Fj0 fj0, boolean z5, boolean z6) {
        this.d = z3;
        this.e = method;
        this.f = z4;
        this.g = abstractC3220zi0;
        this.h = c0471Es;
        this.i = fj0;
        this.a = str;
        this.b = field;
        field.getName();
        this.c = z;
    }

    public final void a(C2754uD c2754uD, Object obj) throws IllegalAccessException {
        Object objInvoke;
        if (this.c) {
            if (this.d) {
                Method method = this.e;
                if (method == null) {
                    I40.a(obj, this.b);
                } else {
                    I40.a(obj, method);
                }
            }
            Method method2 = this.e;
            if (method2 != null) {
                try {
                    objInvoke = method2.invoke(obj, null);
                } catch (InvocationTargetException e) {
                    throw new C1729iD(C40.a("Accessor ", B40.a((AccessibleObject) this.e, false), " threw exception"), e.getCause());
                }
            } else {
                objInvoke = this.b.get(obj);
            }
            if (objInvoke == obj) {
                return;
            }
            c2754uD.b(this.a);
            (this.f ? this.g : new Bi0(this.h, this.g, this.i.b)).a(c2754uD, objInvoke);
        }
    }
}
