package com.android.tools.r8.naming;

import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.naming.C3327i;
import com.android.tools.r8.naming.V;
import com.android.tools.r8.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3327i extends AbstractC3323g {
    public final String a;
    public final String b;
    public final Position c;
    public final C2742u50 d;
    public final HashMap e = new HashMap();
    public final HashMap f = new HashMap();
    public final HashMap g = new HashMap();

    public C3327i(String str, String str2, F0 f0, C2742u50 c2742u50) {
        this.a = str2;
        this.b = str;
        this.c = f0;
        this.d = c2742u50;
    }

    public static /* synthetic */ List b(V.b bVar) {
        return new ArrayList(2);
    }

    @Override // com.android.tools.r8.naming.AbstractC3323g
    public final AbstractC3323g a(V v) {
        if (v.b.f() == 1) {
            V.b bVarB = v.b().b();
            if (bVarB.e()) {
                ((List) this.e.computeIfAbsent(bVarB, new Function() { // from class: u4h
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return C3327i.b((V.b) obj);
                    }
                })).add(v);
                return this;
            }
            this.f.put(bVarB, v);
            return this;
        }
        V.a aVarA = v.b().a();
        if (!aVarA.e() && this.g.put(aVarA, v) != null) {
            C2742u50 c2742u50 = this.d;
            String string = aVarA.toString();
            String str = this.a;
            Position position = v.d;
            int i = z0.f;
            c2742u50.error(new z0("'" + string + "' in '" + C0929Wj.b(str) + "' already has a mapping", position));
        }
        return this;
    }

    @Override // com.android.tools.r8.naming.AbstractC3323g
    public final C3331k.b a(N0 n0, V.b bVar, N0 n1, String str) {
        return null;
    }

    @Override // com.android.tools.r8.naming.AbstractC3323g
    public final void a(Consumer consumer, com.android.tools.r8.naming.mappinginformation.e eVar) {
    }

    @Override // com.android.tools.r8.naming.AbstractC3323g
    public final boolean a(V.b bVar) {
        return true;
    }

    @Override // com.android.tools.r8.naming.AbstractC3323g
    public final V a(V.c cVar) {
        return null;
    }
}
