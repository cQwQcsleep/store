package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1858jl {
    public final InterfaceC1688hl a;

    public C1858jl(C1431el c1431el) {
        this.a = c1431el;
    }

    public static void a(InterfaceC1517fl interfaceC1517fl, com.android.tools.r8.graph.D2 d2) {
        interfaceC1517fl.a(a(d2));
        for (C0231j1 c0231j1 : d2.C1()) {
            interfaceC1517fl.a(c0231j1.getReference().m0());
            if (c0231j1.i1()) {
                Iterator<String> it = Wf0.f(c0231j1.U0().toString()).iterator();
                while (it.hasNext()) {
                    interfaceC1517fl.a(it.next());
                }
            } else {
                interfaceC1517fl.a("<nocode>");
            }
        }
    }

    public static String a(com.android.tools.r8.graph.D2 d2) {
        return d2.getType().m0() + " " + d2.V().d();
    }

    public final void a(C0333y c0333y) {
        try {
            InterfaceC1517fl interfaceC1517flA = ((C1431el) this.a).a();
            try {
                ArrayList arrayList = new ArrayList(c0333y.g().d());
                arrayList.sort(Comparator.comparing(new Function() { // from class: xdh
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((D2) obj).getType();
                    }
                }));
                for (int i = 0; i < arrayList.size(); i++) {
                    a(interfaceC1517flA, (com.android.tools.r8.graph.D2) arrayList.get(i));
                }
                interfaceC1517flA.close();
            } catch (Throwable th) {
                try {
                    interfaceC1517flA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    public static C1858jl a(Path path) {
        return new C1858jl(new C1431el(path));
    }

    public static String a(String str) {
        return str.replace("\r", "<CR>");
    }
}
