package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionMethodContext;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class f extends c implements DefinitionMethodContext {
    public final MethodReference b;

    public f(MethodReference methodReference, Origin origin) {
        super(origin);
        this.b = methodReference;
    }

    public static a a() {
        return new a();
    }

    @Override // com.android.tools.r8.diagnostic.DefinitionMethodContext
    public final MethodReference getMethodReference() {
        return this.b;
    }

    public static class a extends b<a> {
        public static final /* synthetic */ boolean d = true;
        public MethodReference c;

        @Override // com.android.tools.r8.diagnostic.internal.b
        public b a(Origin origin) {
            this.a = origin;
            return b();
        }

        @Override // com.android.tools.r8.diagnostic.internal.b
        public final b b() {
            return this;
        }

        @Override // com.android.tools.r8.diagnostic.internal.b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public f a() {
            boolean z = d;
            if (!z) {
                if (!b.b && this.a == null) {
                    x1f.a();
                    return null;
                }
                if (!z && this.c == null) {
                    x1f.a();
                    return null;
                }
            }
            return new f(this.c, this.a);
        }

        public a a(MethodReference methodReference) {
            this.c = methodReference;
            return this;
        }
    }
}
