package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.position.TextPosition;
import com.android.tools.r8.position.TextRange;
import com.android.tools.r8.shaking.C3461t3;
import com.android.tools.r8.shaking.I2;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class I2 {
    public static final /* synthetic */ boolean n = true;
    public final Origin a;
    public final Position b;
    public final String c;
    public final List d;
    public final C3470v2 e;
    public final C3470v2 f;
    public final boolean g;
    public final O2 h;
    public final F2 i;
    public final List j;
    public final K3 k;
    public final boolean l;
    public final List m;

    public I2(Origin origin, Position position, String str, List list, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, List list2, K3 k3, boolean z2, List list3) {
        boolean z3 = n;
        if (!z3 && o2 == null) {
            x1f.a();
            throw null;
        }
        if (!z3 && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z3 && position == null) {
            x1f.a();
            throw null;
        }
        if (!z3 && str == null && origin == Origin.unknown()) {
            x1f.a();
            throw null;
        }
        this.a = origin;
        this.b = position;
        this.c = str;
        this.d = list;
        this.e = c3470v2;
        this.f = c3470v3;
        this.g = z;
        this.h = o2;
        this.i = f2;
        this.j = list2;
        this.k = k3;
        this.l = z2;
        this.m = list3;
    }

    public StringBuilder a(final StringBuilder sb) {
        List list = this.d;
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            sb.append('@');
            sb.append(it.next());
            while (it.hasNext()) {
                sb.append(" @");
                sb.append(it.next());
            }
            sb.append(' ');
        }
        boolean zA = Wf0.a(sb, (String) null, this.e);
        boolean zA2 = Wf0.a(sb, "!", this.f.toString().replace(" ", " !"));
        if (zA || zA2) {
            sb.append(' ');
        }
        if (this.g) {
            sb.append('!');
        }
        sb.append(this.h);
        sb.append(' ');
        this.i.a(sb);
        if (k()) {
            sb.append(' ');
            sb.append(this.l ? "extends" : "implements");
            sb.append(' ');
            List list2 = this.j;
            if (!list2.isEmpty()) {
                Iterator it2 = list2.iterator();
                sb.append('@');
                sb.append(it2.next());
                while (it2.hasNext()) {
                    sb.append(" @");
                    sb.append(it2.next());
                }
                sb.append(' ');
            }
            sb.append(this.k);
        }
        if (!this.m.isEmpty()) {
            sb.append(" {");
            sb.append(System.lineSeparator());
            this.m.forEach(new Consumer() { // from class: me6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    I2.a(sb, (C3461t3) obj);
                }
            });
            sb.append("}");
        }
        return sb;
    }

    public List<K3> b() {
        return this.d;
    }

    public F2 c() {
        return this.i;
    }

    public O2 d() {
        return this.h;
    }

    public K3 e() {
        return this.k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof I2)) {
            return false;
        }
        I2 i2 = (I2) obj;
        if (this.g == i2.g && this.l == i2.l && Objects.equals(this.d, i2.d) && this.e.equals(i2.e) && this.f.equals(i2.f) && this.h == i2.h && this.i.equals(i2.i) && Objects.equals(this.j, i2.j) && Objects.equals(this.k, i2.k)) {
            return this.m.equals(i2.m);
        }
        return false;
    }

    public boolean f() {
        return this.l;
    }

    public List<C3461t3> g() {
        return this.m;
    }

    public C3470v2 h() {
        return this.f;
    }

    public int hashCode() {
        int iHashCode = ((((((this.d.hashCode() * 3) + this.e.a) * 3) + this.f.a) * 3) + (this.g ? 1 : 0)) * 3;
        O2 o2 = this.h;
        int iHashCode2 = (this.j.hashCode() + ((this.i.hashCode() + ((iHashCode + (o2 != null ? o2.hashCode() : 0)) * 3)) * 3)) * 3;
        K3 k3 = this.k;
        return this.m.hashCode() + ((((iHashCode2 + (k3 != null ? k3.hashCode() : 0)) * 3) + (this.l ? 1 : 0)) * 3);
    }

    public Origin i() {
        return this.a;
    }

    public String j() {
        return this.c;
    }

    public final boolean k() {
        return this.k != null;
    }

    public String toString() {
        return a(new StringBuilder()).toString();
    }

    public static abstract class a<C extends I2, B extends a<C, B>> {
        public static final /* synthetic */ boolean o = true;
        public Origin a;
        public Position b;
        public Position c;
        public String d;
        public final C0473Eu e;
        public final C3470v2 f;
        public C3470v2 g;
        public boolean h;
        public O2 i;
        public F2 j;
        public final C0473Eu k;
        public K3 l;
        public boolean m;
        public List n;

        public a() {
            Origin originUnknown = Origin.unknown();
            Position position = Position.UNKNOWN;
            this.e = AbstractC0551Hu.g();
            this.f = new C3470v2();
            this.g = new C3470v2();
            this.h = false;
            this.k = AbstractC0551Hu.g();
            this.m = false;
            this.n = new LinkedList();
            this.a = originUnknown;
            this.b = position;
        }

        public B a(String str) {
            this.d = str;
            return (B) c();
        }

        public abstract I2 a();

        public final Position b() {
            Position position = this.b;
            if (position == null) {
                return Position.UNKNOWN;
            }
            Position position2 = this.c;
            return (position2 != null && (position instanceof TextPosition) && (position2 instanceof TextPosition)) ? new TextRange((TextPosition) position, (TextPosition) position2) : position;
        }

        public abstract a c();

        public B a(List<C3461t3> list) {
            this.n = list;
            return (B) c();
        }

        public B a(F2 f2) {
            this.j = f2;
            return (B) c();
        }

        public B a(O2 o2) {
            this.i = o2;
            return (B) c();
        }
    }

    public C3470v2 a() {
        return this.e;
    }

    public static /* synthetic */ void a(StringBuilder sb, C3461t3 c3461t3) {
        sb.append("  ");
        sb.append(c3461t3);
        sb.append(";");
        sb.append(System.lineSeparator());
    }
}
