package com.android.tools.r8.errors;

import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.shaking.N0;
import com.android.tools.r8.shaking.x4;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class c {
    public final C0473Eu a = AbstractC0551Hu.g();

    public final c a(ArrayList arrayList, N0 n0, x4 x4Var) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            InterfaceC0332x5 interfaceC0332x5 = (InterfaceC0332x5) it.next();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            x4Var.a(n0.a(interfaceC0332x5.getReference()), new PrintStream(byteArrayOutputStream));
            this.a.a("Item " + interfaceC0332x5.getReference().m0() + " was not discarded.\n" + byteArrayOutputStream.toString());
        }
        return this;
    }

    public final CheckDiscardDiagnostic a() {
        return new CheckDiscardDiagnostic(this.a.a());
    }
}
