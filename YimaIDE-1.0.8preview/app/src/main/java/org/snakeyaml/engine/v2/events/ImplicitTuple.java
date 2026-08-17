package org.snakeyaml.engine.v2.events;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class ImplicitTuple {
    private final boolean nonPlain;
    private final boolean plain;

    public ImplicitTuple(boolean z, boolean z2) {
        this.plain = z;
        this.nonPlain = z2;
    }

    public boolean bothFalse() {
        return (this.plain || this.nonPlain) ? false : true;
    }

    public boolean canOmitTagInNonPlainScalar() {
        return this.nonPlain;
    }

    public boolean canOmitTagInPlainScalar() {
        return this.plain;
    }

    public String toString() {
        return "implicit=[" + this.plain + ", " + this.nonPlain + "]";
    }
}
