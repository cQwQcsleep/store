package com.android.tools.r8.dex;

import com.android.tools.r8.ByteBufferProvider;
import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.C3310n;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.DexFilePerClassFileConsumer;
import com.android.tools.r8.DexIndexedConsumer;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.GlobalSyntheticsConsumer;
import com.android.tools.r8.ProgramConsumer;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.SourceFileEnvironment;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.dex.C0148k;
import com.android.tools.r8.dex.W;
import com.android.tools.r8.dex.t0;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.B3;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0191d3;
import com.android.tools.r8.graph.C0196e1;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0228i5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0235j5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0299t0;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.C0311u5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.J5;
import com.android.tools.r8.graph.N2;
import com.android.tools.r8.graph.O2;
import com.android.tools.r8.graph.S2;
import com.android.tools.r8.graph.V2;
import com.android.tools.r8.graph.Y3;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC1565gK;
import com.android.tools.r8.internal.AbstractC2325pB;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.Ah0;
import com.android.tools.r8.internal.B30;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0856To;
import com.android.tools.r8.internal.C0882Uo;
import com.android.tools.r8.internal.C0901Vh;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1086ah0;
import com.android.tools.r8.internal.C1350dn;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.C2068mB;
import com.android.tools.r8.internal.C2153nB;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.C3076y10;
import com.android.tools.r8.internal.CV;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.DV;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.GX;
import com.android.tools.r8.internal.InterfaceC2022lh0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.internal.Qd0;
import com.android.tools.r8.internal.R3;
import com.android.tools.r8.internal.Vd0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.naming.J0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.shaking.R1;
import com.reandroid.arsc.chunk.TypeBlock;
import defpackage.dfh;
import defpackage.hfh;
import defpackage.ifh;
import defpackage.ik4;
import defpackage.jfh;
import defpackage.ngh;
import defpackage.qfh;
import defpackage.rfh;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.dex.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0148k {
    public static final /* synthetic */ boolean k = true;
    public final C0333y a;
    public final C2752uB b;
    public final r c;
    public final Predicate d;
    public final Optional e;
    public final Collection f;
    public List g;
    public HashSet h;
    public final DexIndexedConsumer i;
    public AbstractC2325pB j;

    public C0148k(C0333y c0333y, W w, C3310n c3310n) {
        r c0153p;
        this.a = c0333y;
        this.b = c0333y.M();
        C2752uB c2752uBM = c0333y.M();
        if ((!c0333y.w().b() && c2752uBM.K1.l().isEmpty() && c2752uBM.K1.c.k.isEmpty()) || c2752uBM.K1.a) {
            c0153p = new C0154q();
        } else {
            c2752uBM.u1.getClass();
            c0153p = new C0153p(c0333y);
        }
        this.c = c0153p;
        this.e = Optional.ofNullable(w);
        this.i = c3310n;
        C0215h c0215hG = c0333y.g();
        Objects.requireNonNull(c0215hG);
        this.d = GX.a(new hfh(c0215hG));
        this.f = c0333y.a().f();
    }

    /* JADX WARN: Code duplicated, block: B:90:0x0296  */
    public final void a() {
        Iterator<D2> it;
        Iterator<D2> it2 = this.a.g().d().iterator();
        while (it2.hasNext()) {
            D2 next = it2.next();
            C0191d3 c0191d3R0 = next.R0();
            List<Y3> listT0 = next.T0();
            if (c0191d3R0 == null && listT0.isEmpty() && next.Q0().a() && !next.t1() && !next.A1()) {
                it = it2;
            } else {
                ArrayList arrayList = new ArrayList(listT0.size() + 2);
                if (c0191d3R0 != null) {
                    if (c0191d3R0.b() != null) {
                        C0322w2 c0322w2B = c0191d3R0.b();
                        B1 b1 = this.b.a;
                        C0285r0[] c0285r0Arr = C0285r0.d;
                        arrayList.add(C0285r0.a(b1.e5, b1, new S2(c0322w2B)));
                    } else {
                        I2 i2 = c0191d3R0.a;
                        B1 b2 = this.b.a;
                        C0285r0[] c0285r0Arr2 = C0285r0.d;
                        arrayList.add(C0285r0.a(b2.d5, b2, new O2.k(i2)));
                    }
                }
                if (!listT0.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList(listT0.size());
                    for (Y3 y3 : listT0) {
                        if (next.e == y3.b()) {
                            if (c0191d3R0 == null && (y3.d() == null || y3.d == null)) {
                                this.b.a(next.e, next.d, next.K1());
                            } else {
                                H2 h2A = this.a.w().a(y3, this.b);
                                int iA = y3.a();
                                B1 b3 = this.b.a;
                                arrayList.add(new C0285r0(2, new C0196e1(b3.f5, new C0299t0[]{new C0299t0(b3.c("accessFlags"), O2.g.j(iA)), new C0299t0(b3.c(TypeBlock.NAME_name), h2A == null ? V2.c : new O2.j(h2A))})));
                                if (y3.d() != null && y3.e()) {
                                    I2 i2D = y3.d();
                                    B1 b4 = this.b.a;
                                    arrayList.add(C0285r0.a(b4.d5, b4, new O2.k(i2D)));
                                }
                            }
                        } else if (next.e == y3.d() && y3.e()) {
                            arrayList2.add(y3.b());
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        B1 b5 = this.b.a;
                        C0285r0[] c0285r0Arr3 = C0285r0.d;
                        O2[] o2Arr = new O2[arrayList2.size()];
                        for (int i = 0; i < arrayList2.size(); i++) {
                            o2Arr[i] = new O2.k((I2) arrayList2.get(i));
                        }
                        arrayList.add(C0285r0.a(b5.g5, b5, new O2.a(o2Arr)));
                    }
                }
                if (next.Q0().b()) {
                    arrayList.add(C0285r0.a(next.Q0().a(this.a.w(), this.d), this.b.a));
                }
                if (this.b.l()) {
                    if (next.w1()) {
                        List<C0235j5> listY0 = next.Y0();
                        B1 b6 = this.b.a;
                        C0285r0[] c0285r0Arr4 = C0285r0.d;
                        ArrayList arrayList3 = new ArrayList(listY0.size());
                        Iterator<C0235j5> it3 = listY0.iterator();
                        while (it3.hasNext()) {
                            arrayList3.add(new O2.k(it3.next().a()));
                        }
                        arrayList.add(C0285r0.a(b6.k5, b6, new O2.a((O2[]) arrayList3.toArray(O2.b))));
                    }
                    if (next.x1()) {
                        C0228i5 c0228i5X0 = next.X0();
                        B1 b7 = this.b.a;
                        C0285r0[] c0285r0Arr5 = C0285r0.d;
                        arrayList.add(C0285r0.a(b7.j5, b7, new O2.k(c0228i5X0.a())));
                    }
                }
                if (!next.q.isEmpty()) {
                    C2752uB c2752uB = this.b;
                    c2752uB.getClass();
                    if (c2752uB.b(EnumC3077y2.J) || c2752uB.o0) {
                        List<C0311u5> listZ0 = next.Z0();
                        B1 b8 = this.b.a;
                        C0285r0[] c0285r0Arr6 = C0285r0.d;
                        ArrayList arrayList4 = new ArrayList(listZ0.size());
                        Iterator<C0311u5> it4 = listZ0.iterator();
                        while (it4.hasNext()) {
                            arrayList4.add(new O2.k(it4.next().a()));
                        }
                        arrayList.add(C0285r0.a(b8.l5, b8, new O2.a((O2[]) arrayList4.toArray(O2.b))));
                    }
                }
                if (next.A1()) {
                    C2752uB c2752uB2 = this.b;
                    if (c2752uB2.b((EnumC3077y2) null) || c2752uB2.l0) {
                        C0333y c0333y = this.a;
                        C0285r0[] c0285r0Arr7 = C0285r0.d;
                        B1 b1A = c0333y.a();
                        int size = next.a1().size();
                        O2.j[] jVarArr = new O2.j[size];
                        O2.k[] kVarArr = new O2.k[size];
                        O2[] o2Arr2 = new O2[size];
                        O2.a[] aVarArr = new O2.a[size];
                        O2.a[] aVarArr2 = new O2.a[size];
                        int i3 = 0;
                        while (i3 < size) {
                            J5 j5 = next.a1().get(i3);
                            Iterator<D2> it5 = it2;
                            jVarArr[i3] = new O2.j(c0333y.w().a(j5.b));
                            kVarArr[i3] = new O2.k(j5.d());
                            if (j5.c().a()) {
                                o2Arr2[i3] = V2.c;
                            } else {
                                o2Arr2[i3] = new N2(C0285r0.a(j5.c().toString(), b1A).c);
                            }
                            int size2 = j5.a().size();
                            O2.c[] cVarArr = new O2.c[size2];
                            N2[] n2Arr = new N2[size2];
                            C0333y c0333y2 = c0333y;
                            aVarArr[i3] = new O2.a(cVarArr);
                            aVarArr2[i3] = new O2.a(n2Arr);
                            int i4 = 0;
                            while (i4 < size2) {
                                int i5 = size2;
                                C0285r0 c0285r0 = j5.a().get(i4);
                                int i6 = i4;
                                cVarArr[i6] = O2.c.a((byte) c0285r0.p0());
                                n2Arr[i6] = new N2(c0285r0.c);
                                i4 = i6 + 1;
                                size2 = i5;
                            }
                            i3++;
                            it2 = it5;
                            c0333y = c0333y2;
                        }
                        it = it2;
                        arrayList.add(new C0285r0(2, new C0196e1(b1A.m5, new C0299t0[]{new C0299t0(b1A.n5, new O2.a(jVarArr)), new C0299t0(b1A.o5, new O2.a(kVarArr)), new C0299t0(b1A.p5, new O2.a(o2Arr2)), new C0299t0(b1A.q5, new O2.a(aVarArr)), new C0299t0(b1A.r5, new O2.a(aVarArr2))})));
                    } else {
                        it = it2;
                    }
                } else {
                    it = it2;
                }
                if (!arrayList.isEmpty()) {
                    C0285r0[] c0285r0Arr8 = next.n0().d;
                    C0285r0[] c0285r0Arr9 = (C0285r0[]) arrayList.toArray(C0285r0.d);
                    Object[] objArr = (Object[]) Array.newInstance((Class<?>) C0285r0.class, c0285r0Arr8.length + c0285r0Arr9.length);
                    System.arraycopy(c0285r0Arr8, 0, objArr, 0, c0285r0Arr8.length);
                    System.arraycopy(c0285r0Arr9, 0, objArr, c0285r0Arr8.length, c0285r0Arr9.length);
                    next.b = C0306u0.a((C0285r0[]) objArr);
                }
                next.m = null;
                next.n.clear();
                next.G0();
                next.q.clear();
                next.r.clear();
            }
            next.M0().forEach(new Consumer() { // from class: ffh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((C0210g1) obj);
                }
            });
            next.C1().forEach(new Consumer() { // from class: gfh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((C0231j1) obj);
                }
            });
            it2 = it;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:31:0x00b9  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.tools.r8.ByteBufferProvider] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.android.tools.r8.dex.k] */
    public final Ch0 b(List list, t0 t0Var) {
        ProgramConsumer programConsumer;
        ?? r2;
        ByteDataView byteDataView;
        Ch0 ch0A = Ch0.a(this.b, "VirtualFile " + t0Var.e());
        if (!t0Var.b.c.isEmpty()) {
            a(t0Var);
            HashSet hashSet = this.h;
            if (hashSet == null || !hashSet.contains(t0Var)) {
                programConsumer = this.i;
                if (programConsumer == null) {
                    if (t0Var.g() != null) {
                        programConsumer = (DexFilePerClassFileConsumer) this.b.j;
                    } else {
                        FeatureSplit featureSplit = t0Var.d;
                        if (featureSplit != null) {
                            programConsumer = featureSplit.getProgramConsumer();
                            if (!k && !(programConsumer instanceof DexIndexedConsumer)) {
                                x1f.a();
                                return null;
                            }
                            r2 = (DexIndexedConsumer) programConsumer;
                        } else {
                            programConsumer = (DexIndexedConsumer) this.b.j;
                        }
                    }
                }
                ch0A.a("Reindex for lazy strings");
                C0284q5 c0284q5F = t0Var.f();
                c0284q5F.a(list);
                ch0A.b();
                ch0A.a("Write bytes");
                F fA = a(c0284q5F, r2, t0Var, ch0A);
                byteDataView = new ByteDataView(fA.a.c().array(), fA.a.c().arrayOffset(), fA.b);
                ch0A.b();
                ch0A.a("Pass bytes to consumer");
                if (programConsumer instanceof DexFilePerClassFileConsumer) {
                    ((DexFilePerClassFileConsumer) programConsumer).accept(t0Var.g(), byteDataView, t0Var.d(), this.b.i);
                } else {
                    ((DexIndexedConsumer) programConsumer).accept(t0Var.e(), byteDataView, t0Var.d(), this.b.i);
                }
                ch0A.b();
                byteDataView.invalidate();
                r2.releaseByteBuffer(fA.a.c());
            } else {
                programConsumer = this.j;
            }
            r2 = programConsumer;
            ch0A.a("Reindex for lazy strings");
            C0284q5 c0284q5F2 = t0Var.f();
            c0284q5F2.a(list);
            ch0A.b();
            ch0A.a("Write bytes");
            F fA2 = a(c0284q5F2, r2, t0Var, ch0A);
            byteDataView = new ByteDataView(fA2.a.c().array(), fA2.a.c().arrayOffset(), fA2.b);
            ch0A.b();
            ch0A.a("Pass bytes to consumer");
            if (programConsumer instanceof DexFilePerClassFileConsumer) {
                ((DexFilePerClassFileConsumer) programConsumer).accept(t0Var.g(), byteDataView, t0Var.d(), this.b.i);
            } else {
                ((DexIndexedConsumer) programConsumer).accept(t0Var.e(), byteDataView, t0Var.d(), this.b.i);
            }
            ch0A.b();
            byteDataView.invalidate();
            r2.releaseByteBuffer(fA2.a.c());
        }
        ch0A.b();
        return ch0A;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Ch0 a(List list, t0 t0Var) {
        Ch0 ch0A = Ch0.a(this.b, "VirtualFile " + t0Var.e());
        if (!t0Var.b.c.isEmpty()) {
            ch0A.a("Compute object offset mapping");
            t0Var.a(this.a, list.size(), ch0A, (C0284q5) null);
            ch0A.b();
            ch0A.a("Rewrite jumbo strings");
            C0284q5 c0284q5F = t0Var.f();
            Set set = t0Var.b.c;
            this.a.g().b();
            a(c0284q5F, set);
            ch0A.b();
        }
        C0901Vh.a(this.a, t0Var);
        ch0A.b();
        return ch0A;
    }

    public void c(ExecutorService executorService) throws ExecutionException, IOException {
        if (!k && this.b.Q()) {
            x1f.a();
        } else {
            a((com.android.tools.r8.utils.i) null, executorService);
        }
    }

    public final void b(ExecutorService executorService) {
        com.android.tools.r8.K.a(this.a, this.a.g().d(), new Consumer() { // from class: dgh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((D2) obj);
            }
        }, executorService);
    }

    public static String b(final C0333y c0333y) {
        R1 r1F = c0333y.g().f();
        final StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(r1F.d.size() + r1F.b.size() + r1F.a.size());
        r1F.a(new ifh(arrayList));
        arrayList.sort(new jfh());
        arrayList.forEach(new Consumer() { // from class: kfh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0148k.a(sb, c0333y, (I2) obj);
            }
        });
        return sb.toString();
    }

    public static F a(L l) {
        G gA = l.a(0, 1);
        C0161y c0161y = gA.b;
        C0155s c0155s = c0161y.b;
        c0161y.b = null;
        return new F(c0155s, gA.c.w);
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0121  */
    /* JADX WARN: Code duplicated, block: B:65:0x013d  */
    /* JADX WARN: Code duplicated, block: B:66:0x0147  */
    public final List a(ExecutorService executorService) {
        Collection collection;
        Vd0 c1350dn;
        C0148k c0148k;
        i0 l0Var;
        ArrayList arrayListA;
        C2752uB c2752uB;
        AbstractC2325pB c2068mB;
        Collection<D2> collectionD = this.a.g().d();
        ArrayList arrayList = new ArrayList();
        if (!this.a.M().y0 || this.a.M().l == null) {
            collection = collectionD;
        } else {
            ArrayList arrayList2 = new ArrayList(collectionD.size());
            for (D2 d2 : collectionD) {
                if (this.a.a.g().a(d2)) {
                    Consumer<D2> consumer = this.a.M().u1.J;
                    if (consumer != null) {
                        consumer.accept(d2);
                    }
                    arrayList.add(d2);
                } else {
                    arrayList2.add(d2);
                }
            }
            collection = arrayList2;
        }
        C2752uB c2752uB2 = this.b;
        ProgramConsumer programConsumer = c2752uB2.j;
        if (programConsumer instanceof DexFilePerClassFileConsumer) {
            l0Var = new k0(this, collection, ((DexFilePerClassFileConsumer) programConsumer).combineSyntheticClassesWithPrimaryClass());
        } else {
            if (!C2752uB.Y1 && !c2752uB2.Z()) {
                x1f.a();
                return null;
            }
            if (!c2752uB2.y0) {
                if (!c2752uB2.F().a(EnumC3077y2.w) && this.b.v1.isEmpty()) {
                    R1 r1F = this.a.g().f();
                    if (!R1.g && r1F.b.isEmpty() && !r1F.d.isEmpty()) {
                        x1f.a();
                        return null;
                    }
                    if (r1F.b.isEmpty() && r1F.a.isEmpty() && this.b.N0) {
                        l0Var = new o0(this, collection, this.b);
                    }
                    arrayListA = l0Var.a();
                    if (!arrayList.isEmpty()) {
                        HashSet hashSet = new HashSet(new k0(c0148k, arrayList, false).a());
                        c0148k.h = hashSet;
                        arrayListA.addAll(hashSet);
                        c2752uB = c0148k.b;
                        if (c2752uB.j instanceof DexFilePerClassFileConsumer) {
                            c2068mB = new C2153nB(c2752uB.l, c0148k.a);
                        } else {
                            c2068mB = new C2068mB(c2752uB.l);
                        }
                        c0148k.j = c2068mB;
                    }
                    return arrayListA;
                }
            }
            if (this.b.L().d) {
                boolean zH = this.a.g().h();
                C0333y c0333y = this.a;
                if (zH) {
                    c1350dn = c0333y.q;
                } else {
                    c1350dn = Vd0.a(c0333y);
                }
            } else {
                c1350dn = new C1350dn();
            }
            c0148k = this;
            l0Var = new l0(c0148k, collection, this.b, executorService, c1350dn);
            arrayListA = l0Var.a();
            if (!arrayList.isEmpty()) {
                HashSet hashSet2 = new HashSet(new k0(c0148k, arrayList, false).a());
                c0148k.h = hashSet2;
                arrayListA.addAll(hashSet2);
                c2752uB = c0148k.b;
                if (c2752uB.j instanceof DexFilePerClassFileConsumer) {
                    c2068mB = new C2153nB(c2752uB.l, c0148k.a);
                } else {
                    c2068mB = new C2068mB(c2752uB.l);
                }
                c0148k.j = c2068mB;
            }
            return arrayListA;
        }
        c0148k = this;
        arrayListA = l0Var.a();
        if (!arrayList.isEmpty()) {
            HashSet hashSet3 = new HashSet(new k0(c0148k, arrayList, false).a());
            c0148k.h = hashSet3;
            arrayListA.addAll(hashSet3);
            c2752uB = c0148k.b;
            if (c2752uB.j instanceof DexFilePerClassFileConsumer) {
                c2068mB = new C2153nB(c2752uB.l, c0148k.a);
            } else {
                c2068mB = new C2068mB(c2752uB.l);
            }
            c0148k.j = c2068mB;
        }
        return arrayListA;
    }

    public static void a(H2 h2, D2 d2) {
        d2.i = h2;
    }

    public static H2 a(B1 b1, W w) {
        return b1.c(w.toString());
    }

    public final void a(List list) {
        Collection<D2> collectionD = this.a.g().d();
        B30 b30 = new B30(collectionD.size());
        for (D2 d2 : collectionD) {
            b30.a(this.a.w().c(d2.getType()), d2.J1());
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            t0 t0Var = (t0) it.next();
            C0151n c0151n = new C0151n();
            Iterator it2 = t0Var.b.c.iterator();
            while (it2.hasNext()) {
                H2 h2C = this.a.w().c(((D2) it2.next()).e);
                c0151n.a.a(h2C.toString(), b30.b(h2C));
            }
            t0Var.c.a(this.a.a().c(c0151n.a()));
            t0Var.c.a();
        }
    }

    public final void a(List list, C1975l7 c1975l7, B1 b1, List list2, W w) {
        if (this.b.Q()) {
            list.add(new C0141d(w, c1975l7, b1));
        } else {
            list2.add(w);
        }
    }

    public ArrayList a(ExecutorService executorService, List list, final ArrayList arrayList) {
        return C1086ah0.a(list, new InterfaceC2022lh0() { // from class: ueh
            @Override // com.android.tools.r8.internal.InterfaceC2022lh0
            public final Object apply(Object obj) {
                return this.a.a(arrayList, (t0) obj);
            }
        }, this.a.M().N(), executorService);
    }

    public void a(ExecutorService executorService, List list, final ArrayList arrayList, Ch0 ch0) {
        ch0.getClass();
        Ah0 ah0A = ch0.a(C1086ah0.a(executorService), "Write files");
        ah0A.a(C1086ah0.a(list, new InterfaceC2022lh0() { // from class: wgh
            @Override // com.android.tools.r8.internal.InterfaceC2022lh0
            public final Object apply(Object obj) {
                return this.a.b(arrayList, (t0) obj);
            }
        }, this.a.M().N(), executorService));
        ah0A.a();
        AbstractC2325pB abstractC2325pB = this.j;
        if (abstractC2325pB != null) {
            abstractC2325pB.a(this.a);
            return;
        }
        GlobalSyntheticsConsumer globalSyntheticsConsumer = this.b.l;
        if (globalSyntheticsConsumer != null) {
            globalSyntheticsConsumer.finished(this.a.M().i);
        }
    }

    public final void a(com.android.tools.r8.utils.i iVar, ExecutorService executorService) {
        DV cv;
        Collection collection;
        Ch0 ch0 = this.a.g().b().c;
        ch0.a("DexApplication.write");
        C1975l7 c1975l7 = new C1975l7();
        ArrayList<AbstractC0146i> arrayList = new ArrayList();
        a(c1975l7, arrayList);
        C2752uB c2752uB = this.b;
        if (c2752uB.S1 == null) {
            cv = DV.b;
        } else if (!c2752uB.Q()) {
            a((J0) null);
            cv = DV.a;
        } else {
            Collection<D2> collectionD = this.a.g().d();
            HashMap map = new HashMap(collectionD.size());
            for (D2 d2 : collectionD) {
                H2 h2C1 = d2.c1();
                if (h2C1 != null) {
                    map.put(d2.getType(), h2C1);
                    d2.i = null;
                }
            }
            arrayList.add(new C0142e(this, c1975l7));
            cv = new CV(map);
        }
        try {
            ch0.a("Insert Attribute Annotations");
            a();
            ch0.b();
            if (this.b.Z()) {
                ch0.a("Set call-site contexts");
                b(executorService);
                ch0.b();
            }
            C0333y c0333y = this.a;
            if (c0333y.M().L().c) {
                Qd0 qd0 = new Qd0(c0333y);
                Set setA = qd0.a();
                Iterator<D2> it = c0333y.g().d().iterator();
                while (it.hasNext()) {
                    qd0.a(it.next(), setA);
                }
            }
            ch0.a("Distribute");
            final List listA = a(executorService);
            ch0.b();
            if (this.b.N) {
                ch0.a("Encode checksums");
                a(listA);
                ch0.b();
            }
            boolean z = k;
            if (!z && (collection = this.f) != null && !collection.isEmpty() && this.a.a().f() == null) {
                throw new AssertionError();
            }
            if (!z) {
                C0333y c0333y2 = this.a;
                Function function = new Function() { // from class: mfh
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return C0148k.a(listA, (C3076y10) obj);
                    }
                };
                Object objApply = Boolean.TRUE;
                C3076y10 c3076y10 = c0333y2.D;
                if (c3076y10 != null) {
                    objApply = function.apply(c3076y10);
                }
                if (!((Boolean) objApply).booleanValue()) {
                    throw new AssertionError();
                }
            }
            ch0.a("Sort Annotations");
            final C0147j c0147j = new C0147j(this.a.w());
            this.a.g().d().forEach(new Consumer() { // from class: yfh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((D2) obj).b(c0147j);
                }
            });
            ch0.b();
            Ah0 ah0A = ch0.a(C1086ah0.a(executorService), "Pre-write phase");
            ah0A.a(a(executorService, listA, arrayList));
            ah0A.a();
            if (this.b.Q()) {
                c1975l7.a(AbstractC1565gK.a(iVar, this.a, ch0, cv, C0901Vh.a(listA, this.b)));
            }
            ch0.a("Compute lazy strings");
            ArrayList arrayList2 = new ArrayList();
            for (AbstractC0146i abstractC0146i : arrayList) {
                if (!AbstractC0146i.b && abstractC0146i.a) {
                    throw new AssertionError();
                }
                H2 h2A = abstractC0146i.a();
                abstractC0146i.a = true;
                arrayList2.add(h2A);
            }
            ch0.b();
            a(executorService, listA, arrayList2, ch0);
            if (this.b.M1 != null && !this.c.a()) {
                if (!k && this.b.K1.a) {
                    throw new AssertionError();
                }
                this.c.a(this.b);
            }
            this.b.i.a();
            if (!(this.i instanceof C3310n)) {
                a(this.a);
            }
            ch0.b();
        } catch (Throwable th) {
            ch0.b();
            throw th;
        }
    }

    public static /* synthetic */ Boolean a(List list, final C3076y10 c3076y10) {
        Stream stream = list.stream();
        Objects.requireNonNull(c3076y10);
        return Boolean.valueOf(stream.allMatch(new Predicate() { // from class: teh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return c3076y10.a((t0) obj);
            }
        }));
    }

    public final void a(final C1975l7 c1975l7, final ArrayList arrayList) {
        final ArrayList arrayList2 = new ArrayList();
        Collection collection = this.f;
        if (collection != null) {
            arrayList2.addAll(collection);
        }
        final B1 b1A = this.a.a();
        this.e.ifPresent(new Consumer() { // from class: lfh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(arrayList, c1975l7, b1A, arrayList2, (W) obj);
            }
        });
        arrayList2.sort(Comparator.comparing(new ik4()));
        this.g = C2847vL.a((Collection) arrayList2, new Function() { // from class: nfh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0148k.a(b1A, (W) obj);
            }
        });
    }

    public final H2 a(J0 j0) {
        SourceFileEnvironment c0144g;
        if (!k && this.b.S1 == null) {
            x1f.a();
            return null;
        }
        if (j0 == null) {
            c0144g = new C0143f();
        } else {
            c0144g = new C0144g(j0);
        }
        String str = this.b.S1.get(c0144g);
        final H2 h2C = str != null ? this.b.a.c(str) : null;
        this.a.g().d().forEach(new Consumer() { // from class: igh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0148k.a(h2C, (D2) obj);
            }
        });
        return h2C;
    }

    public static void a(C0333y c0333y) {
        C2752uB c2752uBM = c0333y.M();
        final C2742u50 c2742u50 = c2752uBM.i;
        c0333y.d.a(c0333y);
        StringConsumer stringConsumer = c2752uBM.I1;
        if (stringConsumer != null) {
            AbstractC2632so.a(c2742u50, stringConsumer, c2752uBM.H().a);
            AbstractC2632so.a(c2742u50, c2752uBM.I1);
        }
        StringConsumer stringConsumer2 = c2752uBM.E1;
        if (stringConsumer2 != null) {
            AbstractC2632so.a(c2742u50, stringConsumer2, b(c0333y));
            AbstractC2632so.a(c2742u50, c2752uBM.E1);
        }
        com.android.tools.r8.naming.N n = new com.android.tools.r8.naming.N(c0333y);
        final DataResourceConsumer dataResourceConsumer = c2752uBM.n;
        if (dataResourceConsumer != null) {
            AbstractC0551Hu abstractC0551Hu = c0333y.f().a;
            f0 f0Var = new f0(c0333y);
            HashSet hashSet = new HashSet();
            Iterator<E> it = abstractC0551Hu.iterator();
            while (it.hasNext()) {
                try {
                    ((DataResourceProvider) it.next()).accept(new C0145h(f0Var, dataResourceConsumer, c2752uBM, n, hashSet));
                } catch (ResourceException e) {
                    throw new C0613Ke(e.getMessage(), e);
                }
            }
            if (!c0333y.c.c.isEmpty()) {
                final AbstractC3345r0 abstractC3345r0W = c0333y.w();
                c0333y.c.a(new BiConsumer() { // from class: tgh
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        C0148k.a(abstractC3345r0W, dataResourceConsumer, c2742u50, (I2) obj, (List) obj2);
                    }
                });
            }
            n.a().forEach(new Consumer() { // from class: vgh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    dataResourceConsumer.accept((DataEntryResource) obj, c2742u50);
                }
            });
        }
        C0882Uo c0882Uo = c2752uBM.o;
        if (c0882Uo != null) {
            ArrayList<C0856To> arrayList = new ArrayList();
            for (FeatureSplit featureSplit : c0882Uo.a) {
                DataResourceConsumer dataResourceConsumer2 = featureSplit.getProgramConsumer().getDataResourceConsumer();
                if (dataResourceConsumer2 != null) {
                    HashSet hashSet2 = new HashSet();
                    Iterator<ProgramResourceProvider> it2 = featureSplit.getProgramResourceProviders().iterator();
                    while (it2.hasNext()) {
                        DataResourceProvider dataResourceProvider = it2.next().getDataResourceProvider();
                        if (dataResourceProvider != null) {
                            hashSet2.add(dataResourceProvider);
                        }
                    }
                    if (!hashSet2.isEmpty()) {
                        arrayList.add(new C0856To(hashSet2, dataResourceConsumer2));
                    }
                }
            }
            for (C0856To c0856To : arrayList) {
                f0 f0Var2 = new f0(c0333y);
                DataResourceConsumer dataResourceConsumer3 = c0856To.b;
                Set set = c0856To.a;
                HashSet hashSet3 = new HashSet();
                Iterator it3 = set.iterator();
                while (it3.hasNext()) {
                    try {
                        ((DataResourceProvider) it3.next()).accept(new C0145h(f0Var2, dataResourceConsumer3, c2752uBM, n, hashSet3));
                    } catch (ResourceException e2) {
                        throw new C0613Ke(e2.getMessage(), e2);
                    }
                }
            }
        }
    }

    public static /* synthetic */ void a(final AbstractC3345r0 abstractC3345r0, DataResourceConsumer dataResourceConsumer, C2742u50 c2742u50, I2 i2, List list) {
        String strB = C0929Wj.b(abstractC3345r0.c(i2).toString());
        dataResourceConsumer.accept(DataEntryResource.fromBytes(Wf0.a((List<String>) list.stream().map(new Function() { // from class: pfh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3345r0.c((I2) obj);
            }
        }).map(new qfh()).map(new rfh()).collect(Collectors.toList())).getBytes(), "META-INF/services/" + strB, Origin.unknown()), c2742u50);
    }

    public static C0148k a(C0333y<?> c0333y, W w) {
        if (c0333y.M().u1.o) {
            return new C0149l(c0333y, w, null);
        }
        return new C0148k(c0333y, w, null);
    }

    public final void a(C0210g1 c0210g1) {
        if (c0210g1.N0().a()) {
            return;
        }
        C0285r0[] c0285r0Arr = (C0285r0[]) R3.b(c0210g1.n0().d, C0285r0.a(c0210g1.N0().a(this.a.w(), this.d), this.b.a));
        c0210g1.b = R3.a(c0285r0Arr) ? C0306u0.o0() : new C0306u0(c0285r0Arr);
        c0210g1.k = B3.e.p();
    }

    public final void a(C0231j1 c0231j1) {
        if (c0231j1.W0().a()) {
            return;
        }
        C0285r0[] c0285r0Arr = (C0285r0[]) R3.b(c0231j1.n0().d, C0285r0.a(c0231j1.W0().a(this.a.w(), this.d), this.b.a));
        c0231j1.b = R3.a(c0285r0Arr) ? C0306u0.o0() : new C0306u0(c0285r0Arr);
        c0231j1.q = B3.g.d();
    }

    public final void a(D2 d2) {
        d2.h(new Consumer() { // from class: sfh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                B5 b5 = (B5) obj;
                b5.e().U0().p0().c(b5);
            }
        }, new ngh());
    }

    public final void a(final C0284q5 c0284q5, Collection collection) {
        if (this.b.u1.h0 || c0284q5.n != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                ((D2) it.next()).h(new Consumer() { // from class: rgh
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.a(c0284q5, (B5) obj);
                    }
                }, new ngh());
            }
        }
    }

    public final /* synthetic */ void a(C0284q5 c0284q5, B5 b5) {
        b5.a(b5.e().U0().p0().a(b5, c0284q5, this.a, this.b.u1.h0).s(), this.a);
    }

    public final F a(C0284q5 c0284q5, ByteBufferProvider byteBufferProvider, t0 t0Var, Ch0 ch0) {
        final L l = new L(this.a, new C0161y(byteBufferProvider), c0284q5, this.c, t0Var, true);
        ch0.a("collect", new dfh(l));
        return (F) ch0.a("generate", new InterfaceC2706th0() { // from class: efh
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return C0148k.a(l);
            }
        });
    }

    public static String a(I2 i2, AbstractC3345r0 abstractC3345r0) {
        return C0929Wj.b(abstractC3345r0.c(i2).toString()).replace('.', DataResource.SEPARATOR) + ".class";
    }

    public static /* synthetic */ void a(StringBuilder sb, C0333y c0333y, I2 i2) {
        sb.append(a(i2, c0333y.w()));
        sb.append('\n');
    }

    public final void a(t0 t0Var) {
        this.b.u1.getClass();
        boolean z = k;
        if (!z && !t0Var.b.k.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !t0Var.b.l.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !t0Var.b.m.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !t0Var.b.n.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !t0Var.b.o.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !t0Var.b.p.isEmpty()) {
            x1f.a();
        } else {
            if (z || t0Var.b.q.isEmpty()) {
                return;
            }
            x1f.a();
        }
    }
}
