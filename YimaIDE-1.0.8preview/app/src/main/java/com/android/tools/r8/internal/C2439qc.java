package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.inspector.ClassInspector;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2439qc implements ClassInspector {
    public final com.android.tools.r8.graph.E0 a;
    public ClassReference b = null;

    public C2439qc(com.android.tools.r8.graph.D2 d2) {
        this.a = d2;
    }

    public final /* synthetic */ void a(Consumer consumer, C0210g1 c0210g1) {
        consumer.accept(new C1864jp(this, c0210g1));
    }

    @Override // com.android.tools.r8.inspector.ClassInspector
    public final void forEachField(final Consumer consumer) {
        this.a.d(new Consumer() { // from class: h3i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, (C0210g1) obj);
            }
        });
    }

    @Override // com.android.tools.r8.inspector.ClassInspector
    public final void forEachMethod(final Consumer consumer) {
        this.a.h(new Consumer() { // from class: g3i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, (C0231j1) obj);
            }
        });
    }

    @Override // com.android.tools.r8.inspector.ClassInspector
    public final ClassReference getClassReference() {
        if (this.b == null) {
            this.b = Reference.classFromDescriptor(this.a.e.Z0());
        }
        return this.b;
    }

    @Override // com.android.tools.r8.inspector.ClassInspector
    public final String getSourceFile() {
        com.android.tools.r8.graph.H2 h2C1 = this.a.c1();
        if (h2C1 == null) {
            return null;
        }
        return h2C1.toString();
    }

    public final /* synthetic */ void a(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(new C2679tO(this, c0231j1));
    }
}
