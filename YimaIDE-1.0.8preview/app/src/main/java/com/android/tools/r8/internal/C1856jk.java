package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.AbstractC0007c;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.f63;
import defpackage.wdh;
import java.util.Arrays;
import java.util.Collections;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1856jk extends AbstractC2027lk implements Comparable, InterfaceC0468Ep {
    public static final C1600gk n = new C1600gk();
    public static final Nm0[] o = (Nm0[]) Nm0.f.clone();
    public final int b;
    public final C1258cj c;
    public final String d;
    public final C1941kk e;
    public final C0955Xj f;
    public final boolean g;
    public EnumC1771ik h;
    public C0955Xj i;
    public C0955Xj j;
    public final C2198nk k;
    public C1260ck l;
    public Object m;

    static {
        if (EnumC1771ik.h.length == AbstractC0007c.c(18).length) {
            return;
        }
        f63.a("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
    }

    public C1856jk(C1258cj c1258cj, C1941kk c1941kk, C0955Xj c0955Xj, int i, boolean z) throws C1091ak {
        this.b = i;
        this.c = c1258cj;
        this.d = AbstractC2370pk.a(c1941kk, c0955Xj, c1258cj.m());
        this.e = c1941kk;
        if (c1258cj.t()) {
            int iA = AbstractC1175bj.a(c1258cj.i);
            this.h = EnumC1771ik.h[AbstractC1175bj.b(iA == 0 ? 1 : iA) - 1];
        }
        this.g = c1258cj.p;
        if (c1258cj.g <= 0) {
            throw new C1091ak(this, "Field numbers must be positive integers.");
        }
        if (z) {
            if (!c1258cj.q()) {
                throw new C1091ak(this, "FieldDescriptorProto.extendee not set for extension field.");
            }
            this.i = null;
            if (c0955Xj != null) {
                this.f = c0955Xj;
            } else {
                this.f = null;
            }
            if (c1258cj.r()) {
                throw new C1091ak(this, "FieldDescriptorProto.oneof_index set for extension field.");
            }
            this.k = null;
        } else {
            if (c1258cj.q()) {
                throw new C1091ak(this, "FieldDescriptorProto.extendee set for non-extension field.");
            }
            this.i = c0955Xj;
            if (c1258cj.r()) {
                int i2 = c1258cj.m;
                if (i2 < 0 || i2 >= c0955Xj.b.l.size()) {
                    throw new C1091ak(this, "FieldDescriptorProto.oneof_index is out of range for type " + c0955Xj.b.k());
                }
                C2198nk c2198nk = (C2198nk) Collections.unmodifiableList(Arrays.asList(c0955Xj.j)).get(c1258cj.m);
                this.k = c2198nk;
                c2198nk.g++;
            } else {
                this.k = null;
            }
            this.f = null;
        }
        c1941kk.h.a(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void a(C1856jk c1856jk) {
        if (c1856jk.c.q()) {
            AbstractC2027lk abstractC2027lkA = c1856jk.e.h.a(c1856jk, c1856jk.c.l());
            if (!(abstractC2027lkA instanceof C0955Xj)) {
                throw new C1091ak(c1856jk, "\"" + c1856jk.c.l() + "\" is not a message type.");
            }
            C0955Xj c0955Xj = (C0955Xj) abstractC2027lkA;
            c1856jk.i = c0955Xj;
            int i = c1856jk.c.g;
            int iBinarySearch = Arrays.binarySearch(c0955Xj.k, i);
            if (iBinarySearch < 0) {
                iBinarySearch = (~iBinarySearch) - 1;
            }
            if (iBinarySearch < 0 || i >= c0955Xj.l[iBinarySearch]) {
                StringBuilder sb = new StringBuilder("\"");
                sb.append(c1856jk.i.c);
                sb.append("\" does not declare ");
                throw new C1091ak(c1856jk, AbstractC2181nb0.a(c1856jk.c.g, " as an extension number.", sb));
            }
        }
        if (c1856jk.c.u()) {
            AbstractC2027lk abstractC2027lkA2 = c1856jk.e.h.a(c1856jk, c1856jk.c.o());
            if (!c1856jk.c.t()) {
                if (abstractC2027lkA2 instanceof C0955Xj) {
                    c1856jk.h = EnumC1771ik.e;
                } else {
                    if (!(abstractC2027lkA2 instanceof C1260ck)) {
                        throw new C1091ak(c1856jk, "\"" + c1856jk.c.o() + "\" is not a type.");
                    }
                    c1856jk.h = EnumC1771ik.g;
                }
            }
            EnumC1686hk enumC1686hk = c1856jk.h.b;
            if (enumC1686hk == EnumC1686hk.k) {
                if (!(abstractC2027lkA2 instanceof C0955Xj)) {
                    throw new C1091ak(c1856jk, "\"" + c1856jk.c.o() + "\" is not a message type.");
                }
                c1856jk.j = (C0955Xj) abstractC2027lkA2;
                if (c1856jk.c.p()) {
                    throw new C1091ak(c1856jk, "Messages can't have default values.");
                }
            } else {
                if (enumC1686hk != EnumC1686hk.j) {
                    throw new C1091ak(c1856jk, "Field with primitive type has type_name.");
                }
                if (!(abstractC2027lkA2 instanceof C1260ck)) {
                    throw new C1091ak(c1856jk, "\"" + c1856jk.c.o() + "\" is not an enum type.");
                }
                c1856jk.l = (C1260ck) abstractC2027lkA2;
            }
        } else {
            EnumC1686hk enumC1686hk2 = c1856jk.h.b;
            if (enumC1686hk2 == EnumC1686hk.k || enumC1686hk2 == EnumC1686hk.j) {
                throw new C1091ak(c1856jk, "Field with message or enum type missing type_name.");
            }
        }
        if (c1856jk.c.n().h && !c1856jk.k()) {
            throw new C1091ak(c1856jk, "[packed = true] can only be specified for repeated primitive fields.");
        }
        char c = 3;
        if (c1856jk.c.p()) {
            if (c1856jk.m()) {
                throw new C1091ak(c1856jk, "Repeated fields cannot have default values.");
            }
            try {
                switch (c1856jk.h.ordinal()) {
                    case 0:
                        if (c1856jk.c.k().equals("inf")) {
                            c1856jk.m = Double.valueOf(Double.POSITIVE_INFINITY);
                        } else if (c1856jk.c.k().equals("-inf")) {
                            c1856jk.m = Double.valueOf(Double.NEGATIVE_INFINITY);
                        } else if (!c1856jk.c.k().equals("nan")) {
                            c1856jk.m = Double.valueOf(c1856jk.c.k());
                        } else {
                            c1856jk.m = Double.valueOf(Double.NaN);
                        }
                        break;
                    case 1:
                        if (c1856jk.c.k().equals("inf")) {
                            c1856jk.m = Float.valueOf(Float.POSITIVE_INFINITY);
                        } else if (c1856jk.c.k().equals("-inf")) {
                            c1856jk.m = Float.valueOf(Float.NEGATIVE_INFINITY);
                        } else if (!c1856jk.c.k().equals("nan")) {
                            c1856jk.m = Float.valueOf(c1856jk.c.k());
                        } else {
                            c1856jk.m = Float.valueOf(Float.NaN);
                        }
                        break;
                    case 2:
                    case 15:
                    case 17:
                        c1856jk.m = Long.valueOf(Sg0.a(c1856jk.c.k(), true, true));
                        break;
                    case XmlPullParser.END_TAG /* 3 */:
                    case XmlPullParser.CDSECT /* 5 */:
                        c1856jk.m = Long.valueOf(Sg0.a(c1856jk.c.k(), false, true));
                        break;
                    case 4:
                    case 14:
                    case Fcntl.S_IWGRP /* 16 */:
                        c1856jk.m = Integer.valueOf((int) Sg0.a(c1856jk.c.k(), true, false));
                        break;
                    case XmlPullParser.ENTITY_REF /* 6 */:
                    case 12:
                        c1856jk.m = Integer.valueOf((int) Sg0.a(c1856jk.c.k(), false, false));
                        break;
                    case 7:
                        c1856jk.m = Boolean.valueOf(c1856jk.c.k());
                        break;
                    case 8:
                        c1856jk.m = c1856jk.c.k();
                        break;
                    case 9:
                    case XmlPullParser.DOCDECL /* 10 */:
                        throw new C1091ak(c1856jk, "Message type had default value.");
                    case AndroidSdkVersion.HONEYCOMB /* 11 */:
                        try {
                            c1856jk.m = Sg0.a(c1856jk.c.k());
                        } catch (Og0 e) {
                            C1091ak c1091ak = new C1091ak(c1856jk, "Couldn't parse default value: " + e.getMessage());
                            c1091ak.initCause(e);
                            throw c1091ak;
                        }
                        break;
                    case 13:
                        C1260ck c1260ck = c1856jk.l;
                        String strK = c1856jk.c.k();
                        AbstractC2027lk abstractC2027lkA3 = c1260ck.d.h.a(3, c1260ck.c + '.' + strK);
                        C1515fk c1515fk = abstractC2027lkA3 instanceof C1515fk ? (C1515fk) abstractC2027lkA3 : null;
                        c1856jk.m = c1515fk;
                        if (c1515fk == null) {
                            throw new C1091ak(c1856jk, "Unknown enum default value: \"" + c1856jk.c.k() + '\"');
                        }
                        break;
                        break;
                }
            } catch (NumberFormatException e2) {
                C1091ak c1091ak2 = new C1091ak(c1856jk, "Could not parse default value: \"" + c1856jk.c.k() + '\"');
                c1091ak2.initCause(e2);
                throw c1091ak2;
            }
        } else if (c1856jk.m()) {
            c1856jk.m = Collections.EMPTY_LIST;
        } else {
            int iOrdinal = c1856jk.h.b.ordinal();
            if (iOrdinal == 7) {
                c1856jk.m = Collections.unmodifiableList(Arrays.asList(c1856jk.l.e)).get(0);
            } else if (iOrdinal != 8) {
                c1856jk.m = c1856jk.h.b.b;
            } else {
                c1856jk.m = null;
            }
        }
        C0955Xj c0955Xj2 = c1856jk.i;
        if (c0955Xj2 == null || !c0955Xj2.b.l().g) {
            return;
        }
        if (!c1856jk.c.q()) {
            throw new C1091ak(c1856jk, "MessageSets cannot have fields, only extensions.");
        }
        int i2 = c1856jk.c.h;
        if (i2 == 1) {
            c = 1;
        } else if (i2 == 2) {
            c = 2;
        } else if (i2 != 3) {
            c = 0;
        }
        if (c == 0) {
            c = 1;
        }
        if (c != 1 || c1856jk.h != EnumC1771ik.e) {
            throw new C1091ak(c1856jk, "Extensions of MessageSets must be optional messages.");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.d;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        C1856jk c1856jk = (C1856jk) obj;
        if (c1856jk.i == this.i) {
            return this.c.g - c1856jk.c.g;
        }
        w01.a("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.c.m();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.c;
    }

    public final Object f() {
        if (this.h.b != EnumC1686hk.k) {
            return this.m;
        }
        c41.a("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        return null;
    }

    public final C1260ck g() {
        if (this.h.b == EnumC1686hk.j) {
            return this.l;
        }
        wdh.a("This field is not of enum type. (", this.d, ")");
        return null;
    }

    public final Pm0 h() {
        return o[this.h.ordinal()].b;
    }

    public final C0955Xj i() {
        if (this.h.b == EnumC1686hk.k) {
            return this.j;
        }
        wdh.a("This field is not of message type. (", this.d, ")");
        return null;
    }

    public final boolean j() {
        return this.h == EnumC1771ik.e && m() && i().b.l().j;
    }

    public final boolean k() {
        return m() && o[this.h.ordinal()].a();
    }

    public final boolean l() {
        if (!k()) {
            return false;
        }
        int iF = this.e.f();
        C1258cj c1258cj = this.c;
        if (iF == 2) {
            return c1258cj.n().h;
        }
        return !c1258cj.n().k() || this.c.n().h;
    }

    public final boolean m() {
        char c;
        int i = this.c.h;
        if (i != 1) {
            c = 2;
            if (i != 2) {
                c = i != 3 ? (char) 0 : (char) 3;
            }
        } else {
            c = 1;
        }
        if (c == 0) {
            c = 1;
        }
        return c == 3;
    }

    public final boolean n() {
        if (this.h != EnumC1771ik.c) {
            return false;
        }
        if (this.i.b.l().j || this.e.f() == 3) {
            return true;
        }
        return this.e.b.l().k;
    }

    public final String toString() {
        return this.d;
    }
}
