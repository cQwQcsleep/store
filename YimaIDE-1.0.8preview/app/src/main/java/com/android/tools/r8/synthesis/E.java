package com.android.tools.r8.synthesis;

import com.android.tools.r8.synthesis.A;
import com.android.tools.r8.synthesis.E;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface E {
    static /* synthetic */ A a(A a) {
        return a;
    }

    static /* synthetic */ A a(C c) {
        return c;
    }

    static E b() {
        return new E() { // from class: l14
            @Override // com.android.tools.r8.synthesis.E
            public final A a() {
                return E.c();
            }
        };
    }

    static /* synthetic */ A c() {
        return new B();
    }

    static E d() {
        final D d = new D();
        return new E() { // from class: n14
            @Override // com.android.tools.r8.synthesis.E
            public final A a() {
                return E.a(d);
            }
        };
    }

    static E e() {
        final C c = new C();
        return new E() { // from class: i14
            @Override // com.android.tools.r8.synthesis.E
            public final A a() {
                return E.a(c);
            }
        };
    }

    A a();
}
