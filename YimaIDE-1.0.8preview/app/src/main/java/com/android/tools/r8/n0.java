package com.android.tools.r8;

import com.android.tools.r8.n0;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class n0 {
    public final ArrayList a = new ArrayList();
    public final HashMap b = new HashMap();

    public final void a(String str, String str2) {
        m0 m0Var;
        p0 p0VarA = p0.a(str);
        p0 p0VarA2 = p0.a(str2);
        Path path = p0VarA2.a;
        if (path != null) {
            m0Var = (m0) this.b.computeIfAbsent(path, new Function() { // from class: xnh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return n0.a((Path) obj);
                }
            });
            m0Var.d = p0VarA2.a;
            Path path2 = p0VarA.a;
            if (path2 != null) {
                m0Var.a.add(path2);
            }
        } else {
            m0 m0Var2 = new m0();
            this.a.add(m0Var2);
            m0Var = m0Var2;
        }
        if (Objects.isNull(p0VarA.b) != Objects.isNull(p0VarA2.b)) {
            w01.a("Both input and output for feature resources must be provided");
        } else {
            m0Var.b = p0VarA.b;
            m0Var.c = p0VarA2.b;
        }
    }

    public static /* synthetic */ m0 a(Path path) {
        return new m0();
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList(this.a);
        arrayList.addAll(this.b.values());
        return arrayList;
    }
}
