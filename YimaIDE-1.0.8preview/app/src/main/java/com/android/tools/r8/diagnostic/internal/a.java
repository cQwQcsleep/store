package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionClassContext;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class a extends c implements DefinitionClassContext {
    public final ClassReference b;

    public a(ClassReference classReference, Origin origin) {
        super(origin);
        this.b = classReference;
    }

    public static C0002a a() {
        return new C0002a();
    }

    @Override // com.android.tools.r8.diagnostic.DefinitionClassContext
    public final ClassReference getClassReference() {
        return this.b;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.diagnostic.internal.a$a, reason: collision with other inner class name */
    public static class C0002a extends b<C0002a> {
        public static final /* synthetic */ boolean d = true;
        public ClassReference c;

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
        public a a() {
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
            return new a(this.c, this.a);
        }

        public C0002a a(ClassReference classReference) {
            this.c = classReference;
            return this;
        }
    }
}
