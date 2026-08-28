package j$.time.zone;

import j$.time.Duration;
import j$.time.chrono.AbstractC0073i;
import j$.time.y;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class b implements Comparable, Serializable {
    private static final long serialVersionUID = -6946044323557704546L;
    private final long a;
    private final j$.time.h b;
    private final y c;
    private final y d;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.a, ((b) obj).a);
    }

    b(j$.time.h hVar, y yVar, y yVar2) {
        hVar.getClass();
        this.a = AbstractC0073i.n(hVar, yVar);
        this.b = hVar;
        this.c = yVar;
        this.d = yVar2;
    }

    b(long j, y yVar, y yVar2) {
        this.a = j;
        this.b = j$.time.h.L(j, 0, yVar);
        this.c = yVar;
        this.d = yVar2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 2, this);
    }

    final void writeExternal(ObjectOutput objectOutput) {
        a.c(this.a, objectOutput);
        a.d(this.c, objectOutput);
        a.d(this.d, objectOutput);
    }

    public final long B() {
        return this.a;
    }

    public final j$.time.h k() {
        return this.b;
    }

    public final y s() {
        return this.c;
    }

    public final y n() {
        return this.d;
    }

    public final j$.time.h j() {
        return this.b.N(this.d.I() - this.c.I());
    }

    public final Duration m() {
        return Duration.m(this.d.I() - this.c.I());
    }

    public final boolean w() {
        return this.d.I() > this.c.I();
    }

    final List v() {
        return w() ? Collections.emptyList() : j$.com.android.tools.r8.a.h(new Object[]{this.c, this.d});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.c.equals(bVar.c) && this.d.equals(bVar.d);
    }

    public final int hashCode() {
        return (this.b.hashCode() ^ this.c.hashCode()) ^ Integer.rotateLeft(this.d.hashCode(), 16);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Transition[");
        sb.append(w() ? "Gap" : "Overlap");
        sb.append(" at ");
        sb.append(this.b);
        sb.append(this.c);
        sb.append(" to ");
        sb.append(this.d);
        sb.append(']');
        return sb.toString();
    }
}
