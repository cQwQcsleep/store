package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.startup.StartupClassBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xd0 implements InterfaceC1792j1, StartupClassBuilder {
    public static final /* synthetic */ boolean c = true;
    public final com.android.tools.r8.graph.B1 a;
    public com.android.tools.r8.graph.I2 b;

    public Xd0() {
        this.a = null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1792j1
    public final InterfaceC1877k1 build() {
        return new Yd0(this.b);
    }

    @Override // com.android.tools.r8.startup.StartupClassBuilder
    public final StartupClassBuilder setClassReference(ClassReference classReference) {
        if (c || this.a != null) {
            this.b = C1758id.a(classReference, this.a);
            return this;
        }
        x1f.a();
        return null;
    }

    public Xd0(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
    }
}
