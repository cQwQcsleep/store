package com.android.tools.r8.internal;

import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ah, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1085ah implements InterfaceC1665hZ {
    public final InterfaceC1665hZ a;
    public final Function b;

    public AbstractC1085ah(AbstractC1751iZ abstractC1751iZ, Function function) {
        this.a = abstractC1751iZ;
        this.b = function;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a(String str, Consumer consumer, Object obj) {
        return this.a.a(str, a(consumer), obj);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1665hZ
    public final Object getValue() {
        return this.b.apply(this.a.getValue());
    }

    public final Consumer a(final Consumer consumer) {
        return new Consumer() { // from class: vcg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, obj);
            }
        };
    }

    @Override // com.android.tools.r8.internal.InterfaceC1665hZ
    public final void a(String str, Object obj) {
        this.a.a(str, obj);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a() {
        return this.a.a();
    }

    public final /* synthetic */ void a(Consumer consumer, Object obj) {
        consumer.accept(this.b.apply(obj));
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a(String str, String str2, String str3, Consumer consumer) {
        return this.a.a(str, str2, str3, a(consumer));
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final J2 a(Consumer consumer, String str) {
        return this.a.a(a(consumer), str);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final J2 a(String str, Consumer consumer, String str2) {
        return this.a.a(str, a(consumer), str2);
    }
}
