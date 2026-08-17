package com.android.tools.r8.internal;

import com.android.tools.r8.internal.DE;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LW extends OW {
    public static final /* synthetic */ boolean f = true;
    public final Set e;

    public LW(C3097yF c3097yF, HashSet hashSet, C1902kH c1902kH) {
        super(c3097yF, null, c1902kH);
        if (f || !hashSet.isEmpty()) {
            this.e = hashSet;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.OW
    public final void b(StringBuilder sb) {
        C3097yF c3097yF = this.a;
        if (!C2756uF.a.equals(c3097yF.a)) {
            sb.append("# context: ");
            sb.append(c3097yF.a.a());
            sb.append('\n');
        }
        if (!C2841vF.b.equals(c3097yF.b)) {
            String strC = AbstractC3035xa0.c(c3097yF.b.a);
            sb.append("# description: ");
            sb.append(strC);
            sb.append('\n');
        }
        sb.append("-keepattributes");
        sb.append(" ");
        ArrayList arrayList = new ArrayList(this.e);
        arrayList.sort(new Comparator() { // from class: hm8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((DE) obj).compareTo((DE) obj2);
            }
        });
        sb.append(((DE) arrayList.get(0)).b);
        for (int i = 1; i < arrayList.size(); i++) {
            sb.append(',');
            sb.append(((DE) arrayList.get(i)).b);
        }
    }

    @Override // com.android.tools.r8.internal.OW
    public final List c() {
        throw new IllegalStateException();
    }

    @Override // com.android.tools.r8.internal.OW
    public final void c(StringBuilder sb) {
        throw new IllegalStateException();
    }

    @Override // com.android.tools.r8.internal.OW
    public final String b() {
        return "-keepattributes";
    }

    @Override // com.android.tools.r8.internal.OW
    public final void b(StringBuilder sb, HE he) {
        throw new IllegalStateException();
    }
}
