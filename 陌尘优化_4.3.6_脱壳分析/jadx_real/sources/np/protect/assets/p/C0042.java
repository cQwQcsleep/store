package np.protect.assets.p;

import obfuse.NPStringFog;

/* renamed from: np.protect.assets.p.۟۟۟ۡۥ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public final class C0042<A, B, C> {

    /* renamed from: ۣ۟۟۟ۥ, reason: not valid java name and contains not printable characters */
    public A f70;

    /* renamed from: ۣ۟۟۟ۦ, reason: not valid java name and contains not printable characters */
    public B f71;

    /* renamed from: ۣ۟۟۟ۧ, reason: not valid java name and contains not printable characters */
    public C f72;

    public C0042() {
    }

    public C0042(A a, B b, C c) {
        this.f70 = a;
        this.f71 = b;
        this.f72 = c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0042)) {
            return false;
        }
        C0042 c0042 = (C0042) obj;
        return $$ExternalSyntheticBackport0.m(this.f70, c0042.f70) && $$ExternalSyntheticBackport0.m(this.f71, c0042.f71) && $$ExternalSyntheticBackport0.m(this.f72, c0042.f72);
    }

    public int hashCode() {
        return ($$ExternalSyntheticBackport0.m(this.f70) ^ $$ExternalSyntheticBackport0.m(this.f71)) ^ $$ExternalSyntheticBackport0.m(this.f72);
    }

    public String toString() {
        return NPStringFog.decode("3A181F040B3512151E0B0B2C5B4E") + this.f70 + NPStringFog.decode("55500F5B4E") + this.f71 + NPStringFog.decode("55500E5B4E") + this.f72 + NPStringFog.decode("13");
    }
}
