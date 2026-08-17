package com.android.tools.r8.internal;

import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ac0 extends AbstractC2895vu {
    public final transient Object f;
    public final transient Object g;
    public final transient AbstractC2895vu h;
    public transient Ac0 i;

    public Ac0(Object obj, Object obj2) {
        AbstractC0871Ud.a(obj, obj2);
        this.f = obj;
        this.g = obj2;
        this.h = null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final boolean containsKey(Object obj) {
        return this.f.equals(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final boolean containsValue(Object obj) {
        return this.g.equals(obj);
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        biConsumer.accept(this.f, this.g);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        if (this.f.equals(obj)) {
            return this.g;
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        C3236zu c3236zu = new C3236zu(this.f, this.g);
        int i = AbstractC2554rv.c;
        return new Cc0(c3236zu);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv j() {
        Object obj = this.f;
        int i = AbstractC2554rv.c;
        return new Cc0(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2895vu
    /* JADX INFO: renamed from: s */
    public final AbstractC2895vu f() {
        AbstractC2895vu abstractC2895vu = this.h;
        if (abstractC2895vu != null) {
            return abstractC2895vu;
        }
        Ac0 ac0 = this.i;
        if (ac0 != null) {
            return ac0;
        }
        Ac0 ac1 = new Ac0(this.g, this.f, this);
        this.i = ac1;
        return ac1;
    }

    @Override // java.util.Map
    public final int size() {
        return 1;
    }

    public Ac0(Object obj, Object obj2, AbstractC2895vu abstractC2895vu) {
        this.f = obj;
        this.g = obj2;
        this.h = abstractC2895vu;
    }
}
