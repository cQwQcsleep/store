package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IR extends JR<IR> implements Comparable<IR>, InterfaceC2705th {
    public static final /* synthetic */ boolean h = true;
    public int c;
    public final TreeSet d;
    public final TreeSet e;
    public final TreeSet f;
    public final TreeSet g;

    public IR(com.android.tools.r8.graph.B5 b5) {
        super(b5);
        this.c = 0;
        this.d = new TreeSet();
        this.e = new TreeSet();
        this.f = new TreeSet();
        this.g = new TreeSet();
    }

    @Override // com.android.tools.r8.internal.JR
    public final void a(IR ir, boolean z) {
        boolean zAdd;
        if (ir == this || z) {
            synchronized (this.e) {
                this.c++;
            }
            return;
        }
        synchronized (this.e) {
            zAdd = this.e.add(ir);
            this.c++;
        }
        if (zAdd) {
            synchronized (ir.d) {
                ir.d.add(this);
            }
            d(ir);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final void c(InterfaceC2705th interfaceC2705th) {
        IR ir = (IR) interfaceC2705th;
        boolean zRemove = this.f.remove(ir);
        boolean z = h;
        if (!z && !zRemove) {
            x1f.a();
            return;
        }
        boolean zRemove2 = ir.g.remove(this);
        if (!z && !zRemove2) {
            x1f.a();
        } else {
            if (z || !this.e.contains(ir)) {
                return;
            }
            x1f.a();
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(IR ir) {
        return a().getReference().compareTo(ir.a().getReference());
    }

    public final void d(IR ir) {
        synchronized (this.f) {
            this.f.remove(ir);
        }
        synchronized (ir.g) {
            ir.g.remove(this);
        }
    }

    public final void e() {
        boolean z = h;
        if (!z && !this.e.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !this.f.isEmpty()) {
            x1f.a();
            return;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            boolean zRemove = ((IR) it.next()).e.remove(this);
            if (!h && !zRemove) {
                x1f.a();
                return;
            }
        }
        Iterator it2 = this.g.iterator();
        while (it2.hasNext()) {
            boolean zRemove2 = ((IR) it2.next()).f.remove(this);
            if (!h && !zRemove2) {
                x1f.a();
                return;
            }
        }
    }

    public final void f() {
        boolean z = h;
        if (!z && !this.d.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !this.g.isEmpty()) {
            x1f.a();
            return;
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            boolean zRemove = ((IR) it.next()).d.remove(this);
            if (!h && !zRemove) {
                x1f.a();
                return;
            }
        }
        Iterator it2 = this.f.iterator();
        while (it2.hasNext()) {
            boolean zRemove2 = ((IR) it2.next()).g.remove(this);
            if (!h && !zRemove2) {
                x1f.a();
                return;
            }
        }
    }

    public boolean g() {
        return this.d.isEmpty() && this.g.isEmpty();
    }

    public final boolean h() {
        return this.e.isEmpty() && this.f.isEmpty();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MethodNode for: ");
        sb.append(a().v());
        sb.append(" (");
        sb.append(this.d.size());
        sb.append(" callees, ");
        sb.append(this.e.size());
        sb.append(" callers, invoke count ");
        sb.append(this.c);
        sb.append(").");
        sb.append(System.lineSeparator());
        if (this.d.size() > 0) {
            sb.append("Callees:");
            sb.append(System.lineSeparator());
            for (IR ir : this.d) {
                sb.append("  ");
                sb.append(ir.a().v());
                sb.append(System.lineSeparator());
            }
        }
        if (this.e.size() > 0) {
            sb.append("Callers:");
            sb.append(System.lineSeparator());
            for (IR ir2 : this.e) {
                sb.append("  ");
                sb.append(ir2.a().v());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final Set b() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public boolean b(IR ir) {
        return this.d.contains(ir);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final boolean d(InterfaceC2705th interfaceC2705th) {
        return this.g.contains((IR) interfaceC2705th);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void e(IR ir) {
        boolean zRemove = this.e.remove(ir);
        boolean z = h;
        if (!z && !zRemove) {
            x1f.a();
            return;
        }
        boolean zRemove2 = ir.d.remove(this);
        if (!z && !zRemove2) {
            x1f.a();
        } else {
            if (z || !this.f.contains(ir)) {
                return;
            }
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final TreeSet c() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.JR
    public final void a(JR jr) {
        boolean zAdd;
        IR ir = (IR) jr;
        if (ir != this) {
            synchronized (this.e) {
                try {
                    if (this.e.contains(ir)) {
                        return;
                    }
                    synchronized (this.f) {
                        zAdd = this.f.add(ir);
                    }
                    if (zAdd) {
                        synchronized (ir.g) {
                            ir.g.add(this);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final boolean a(InterfaceC2705th interfaceC2705th) {
        return this.e.contains((IR) interfaceC2705th);
    }

    public void a(IR ir) {
        a(ir, false);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2705th
    public final boolean f(InterfaceC2705th interfaceC2705th) {
        return this.f.contains((IR) interfaceC2705th);
    }
}
