package com.android.tools.r8.dex;

import com.android.tools.r8.DataDirectoryResource;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.dex.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0145h implements DataResourceProvider.Visitor {
    public final /* synthetic */ f0 a;
    public final /* synthetic */ DataResourceConsumer b;
    public final /* synthetic */ C2752uB c;
    public final /* synthetic */ com.android.tools.r8.naming.N d;
    public final /* synthetic */ Set e;

    public C0145h(f0 f0Var, DataResourceConsumer dataResourceConsumer, C2752uB c2752uB, com.android.tools.r8.naming.N n, HashSet hashSet) {
        this.a = f0Var;
        this.b = dataResourceConsumer;
        this.c = c2752uB;
        this.d = n;
        this.e = hashSet;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0053  */
    /* JADX WARN: Code duplicated, block: B:22:? A[RETURN, SYNTHETIC] */
    @Override // com.android.tools.r8.DataResourceProvider.Visitor
    public final void visit(DataDirectoryResource dataDirectoryResource) {
        DataDirectoryResource dataDirectoryResourceFromName;
        f0 f0Var = this.a;
        if (f0Var.e.H() != null) {
            if (f0Var.e.H().g().a(dataDirectoryResource.getName())) {
                a0 a0Var = new a0(f0Var, dataDirectoryResource.getName());
                dataDirectoryResourceFromName = DataDirectoryResource.fromName(a0Var.d() ? a0Var.c() : dataDirectoryResource.getName(), dataDirectoryResource.getOrigin());
            }
            if (dataDirectoryResourceFromName != null) {
                this.b.accept(dataDirectoryResourceFromName, this.c.i);
                this.c.i.a();
            }
        }
        if (!f0.f && !f0Var.e.u1.J0) {
            x1f.a();
            return;
        }
        dataDirectoryResourceFromName = null;
        if (dataDirectoryResourceFromName != null) {
            this.b.accept(dataDirectoryResourceFromName, this.c.i);
            this.c.i.a();
        }
    }

    @Override // com.android.tools.r8.DataResourceProvider.Visitor
    public final void visit(DataEntryResource dataEntryResource) {
        this.a.getClass();
        if (f0.c(dataEntryResource)) {
            return;
        }
        this.d.getClass();
        if (com.android.tools.r8.naming.N.a(dataEntryResource)) {
            return;
        }
        DataEntryResource dataEntryResourceB = this.a.b(dataEntryResource);
        if (this.e.add(dataEntryResourceB.getName())) {
            this.b.accept(dataEntryResourceB, this.c.i);
        } else {
            this.c.i.warning(new StringDiagnostic("Resource '" + dataEntryResource.getName() + "' already exists."));
        }
        this.c.i.a();
    }
}
