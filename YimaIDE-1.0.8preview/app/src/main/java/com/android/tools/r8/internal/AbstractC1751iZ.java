package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1751iZ implements InterfaceC1665hZ {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC3114yW a;
    public final HashMap b = new HashMap();
    public String c = null;
    public Object d = null;
    public BiConsumer e = null;

    public AbstractC1751iZ(AbstractC3114yW abstractC3114yW) {
        this.a = abstractC3114yW;
    }

    public final void a(String str) {
        this.a.a("Multiple properties: '" + this.c + "' and '" + str + "'");
        throw null;
    }

    public final /* synthetic */ void b(String str, Consumer consumer, Object obj) {
        boolean z = f;
        if (!z && obj == null) {
            x1f.a();
            return;
        }
        BiConsumer biConsumer = this.e;
        if (biConsumer != null) {
            biConsumer.accept(obj, this.a.b(str));
        }
        if (this.c == null) {
            this.c = str;
            this.d = obj;
            consumer.accept(obj);
        } else {
            if (z || this.d != null) {
                a(str);
                throw null;
            }
            x1f.a();
        }
    }

    public J2 c(String str, Consumer consumer, Object obj) {
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1665hZ
    public Object getValue() {
        if (!f) {
            if ((this.c != null) != (this.d != null)) {
                x1f.a();
                return null;
            }
        }
        return this.d;
    }

    public boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        return false;
    }

    public J2 a(Object obj, String str, String str2, Consumer consumer) {
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a() {
        if (!f) {
            if ((this.c != null) != (this.d != null)) {
                x1f.a();
                return false;
            }
        }
        return this.c != null;
    }

    public final Object a(Object obj) {
        if (!f) {
            if ((this.c != null) != (this.d != null)) {
                x1f.a();
                return null;
            }
        }
        return a() ? this.d : obj;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1665hZ
    public final void a(String str, Object obj) {
        if (this.b.put(str, obj) == null) {
            return;
        }
        aca.a("Unexpected attempt to redefine property ", str);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a(String str, Consumer consumer, Object obj) {
        Object obj2 = this.b.get(str);
        if (obj2 == null) {
            return false;
        }
        try {
            return a(obj2, str, obj, b(consumer, str));
        } catch (RuntimeException e) {
            AbstractC3114yW abstractC3114yW = this.a;
            abstractC3114yW.getClass();
            if (e instanceof C3096yE) {
                throw e;
            }
            throw new C3096yE(abstractC3114yW, e);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a(String str, String str2, String str3, Consumer consumer) {
        Object obj = this.b.get(str);
        if (obj == null) {
            return false;
        }
        try {
            return b(obj, str2, str3, b(consumer, str));
        } catch (RuntimeException e) {
            AbstractC3114yW abstractC3114yW = this.a;
            abstractC3114yW.getClass();
            if (e instanceof C3096yE) {
                throw e;
            }
            throw new C3096yE(abstractC3114yW, e);
        }
    }

    public final Consumer b(final Consumer consumer, final String str) {
        return new Consumer() { // from class: r8h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(str, consumer, obj);
            }
        };
    }

    public boolean b(Object obj, String str, String str2, Consumer consumer) {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final J2 a(Consumer consumer, String str) {
        Object obj = this.b.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return c(str, b(consumer, str), obj);
        } catch (RuntimeException e) {
            AbstractC3114yW abstractC3114yW = this.a;
            abstractC3114yW.getClass();
            if (e instanceof C3096yE) {
                throw e;
            }
            throw new C3096yE(abstractC3114yW, e);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final J2 a(String str, Consumer consumer, String str2) {
        Object obj = this.b.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return a(obj, str, str2, b(consumer, str));
        } catch (RuntimeException e) {
            AbstractC3114yW abstractC3114yW = this.a;
            abstractC3114yW.getClass();
            if (e instanceof C3096yE) {
                throw e;
            }
            throw new C3096yE(abstractC3114yW, e);
        }
    }
}
