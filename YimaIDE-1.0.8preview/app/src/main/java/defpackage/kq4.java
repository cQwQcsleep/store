package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class kq4 {
    public static final int e = 8;
    public final String a;
    public final String b;
    public final boolean c;
    public final List d;

    public kq4(String str, String str2, boolean z, List list) {
        str.getClass();
        str2.getClass();
        list.getClass();
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = list;
    }

    public static Comparable a(kq4 kq4Var) {
        kq4Var.getClass();
        return Boolean.valueOf(!kq4Var.c);
    }

    public static Comparable b(kq4 kq4Var) {
        kq4Var.getClass();
        return kq4Var.a;
    }

    public final List c() {
        ArrayList arrayList = new ArrayList();
        if (!this.c) {
            arrayList.add(this.b);
            return arrayList;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((kq4) it.next()).c());
        }
        return arrayList;
    }

    public final kq4 d(String str) {
        str.getClass();
        if (Intrinsics.areEqual(this.b, str)) {
            return this;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            kq4 kq4VarD = ((kq4) it.next()).d(str);
            if (kq4VarD != null) {
                return kq4VarD;
            }
        }
        return null;
    }

    public final List e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof kq4)) {
            return false;
        }
        kq4 kq4Var = (kq4) obj;
        return Intrinsics.areEqual(this.a, kq4Var.a) && Intrinsics.areEqual(this.b, kq4Var.b) && this.c == kq4Var.c && Intrinsics.areEqual(this.d, kq4Var.d);
    }

    public final String f() {
        return this.a;
    }

    public final String g() {
        return this.b;
    }

    public final boolean h() {
        return this.c;
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Boolean.hashCode(this.c)) * 31) + this.d.hashCode();
    }

    public final List i() {
        return CollectionsKt.sortedWith(this.d, ComparisonsKt.compareBy(new Function1[]{new Function1() { // from class: iq4
            public final Object invoke(Object obj) {
                return kq4.a((kq4) obj);
            }
        }, new Function1() { // from class: jq4
            public final Object invoke(Object obj) {
                return kq4.b((kq4) obj);
            }
        }}));
    }

    public String toString() {
        return "FileNode(name=" + this.a + ", path=" + this.b + ", isDirectory=" + this.c + ", children=" + this.d + ")";
    }

    public /* synthetic */ kq4(String str, String str2, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, (i & 8) != 0 ? new ArrayList() : list);
    }
}
