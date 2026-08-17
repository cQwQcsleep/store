package defpackage;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ax {
    public final String a;
    public final String b;
    public final dx c;

    public ax(String str, String str2, dx dxVar) {
        str.getClass();
        str2.getClass();
        dxVar.getClass();
        this.a = str;
        this.b = str2;
        this.c = dxVar;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }

    public final dx c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ax)) {
            return false;
        }
        ax axVar = (ax) obj;
        return Intrinsics.areEqual(this.a, axVar.a) && Intrinsics.areEqual(this.b, axVar.b) && this.c == axVar.c;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "AgentTodoItem(id=" + this.a + ", content=" + this.b + ", status=" + this.c + ")";
    }
}
