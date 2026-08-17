package com.android.tools.r8.utils;

import com.android.tools.r8.BaseCompilerCommand;
import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.DexFilePerClassFileConsumer;
import com.android.tools.r8.DexIndexedConsumer;
import com.android.tools.r8.ProgramConsumer;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.CM;
import com.android.tools.r8.naming.I0;
import com.android.tools.r8.naming.Q;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class r {
    public static final /* synthetic */ boolean e = true;
    public final i.a a;
    public boolean b;
    public ProgramConsumer c;
    public final CM d;

    public r(C2752uB c2752uB) {
        this.a = i.b();
        this.b = false;
        this.c = null;
        this.d = null;
        c2752uB.j = a(c2752uB.j);
        Q q = c2752uB.F1;
        if (q != null) {
            this.d = new CM(q, new I0(new j(this)));
        }
        c2752uB.F1 = this.d;
    }

    public ProgramConsumer a(ProgramConsumer programConsumer) {
        boolean z = e;
        if (!z && this.c != null) {
            x1f.a();
            return null;
        }
        if (programConsumer instanceof ClassFileConsumer) {
            a((ClassFileConsumer) programConsumer);
        } else if (programConsumer instanceof DexIndexedConsumer) {
            a((DexIndexedConsumer) programConsumer);
        } else if (programConsumer instanceof DexFilePerClassFileConsumer) {
            DexFilePerClassFileConsumer dexFilePerClassFileConsumer = (DexFilePerClassFileConsumer) programConsumer;
            if (!z && this.c != null) {
                x1f.a();
                return null;
            }
            this.c = new n(this, dexFilePerClassFileConsumer, dexFilePerClassFileConsumer);
        } else {
            if (!z && programConsumer != null) {
                x1f.a();
                return null;
            }
            a((DexIndexedConsumer) null);
        }
        if (z || this.c != null) {
            return this.c;
        }
        x1f.a();
        return null;
    }

    public r(BaseCompilerCommand.Builder<?, ?> builder) {
        this.a = i.b();
        this.b = false;
        this.c = null;
        this.d = null;
        builder.setProgramConsumer(a(builder.getProgramConsumer()));
    }

    public r() {
        this.a = i.b();
        this.b = false;
        this.c = null;
        this.d = null;
    }

    public DexIndexedConsumer a(DexIndexedConsumer dexIndexedConsumer) {
        if (!e && this.c != null) {
            x1f.a();
            return null;
        }
        l lVar = new l(this, dexIndexedConsumer, dexIndexedConsumer);
        this.c = lVar;
        return lVar;
    }

    public ClassFileConsumer a(ClassFileConsumer classFileConsumer) {
        if (!e && this.c != null) {
            x1f.a();
            return null;
        }
        p pVar = new p(this, classFileConsumer, classFileConsumer);
        this.c = pVar;
        return pVar;
    }

    public i a() {
        if (e || this.b) {
            return this.a.a();
        }
        x1f.a();
        return null;
    }
}
