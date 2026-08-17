package com.android.tools.r8.internal;

import com.android.tools.r8.TextInputStream;
import com.android.tools.r8.TextOutputStream;
import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2739u4;
import com.android.tools.r8.internal.AbstractC2824v4;
import com.android.tools.r8.internal.C1371e4;
import com.android.tools.r8.internal.C2055m4;
import com.android.tools.r8.internal.C2140n4;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.X3;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.naming.C3342p0;
import com.android.tools.r8.profile.art.ArtProfileBuilder;
import com.android.tools.r8.profile.art.ArtProfileConsumer;
import com.android.tools.r8.profile.art.ArtProfileProvider;
import com.android.tools.r8.profile.art.ArtProfileRuleConsumer;
import com.android.tools.r8.profile.art.diagnostic.HumanReadableArtProfileParserErrorDiagnostic;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.synthesis.S;
import defpackage.fwf;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class X3 implements InterfaceC1708i1 {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public X3(LinkedHashMap linkedHashMap) {
        this.a = linkedHashMap;
    }

    public static void a(C0907Vn c0907Vn, C0333y c0333y, final C2055m4 c2055m4, Consumer consumer, Function function) {
        C0322w2 c0322w2E = c2055m4.e();
        c0907Vn.getClass();
        C0322w2 c0322w2D = c0907Vn.d(AbstractC3148ys.g(), c0322w2E);
        if (c0322w2D.w0() != c2055m4.e().w0()) {
            if (!b && !c0333y.a.g().a(c0322w2D.w0(), new com.android.tools.r8.synthesis.I() { // from class: dwf
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.h;
                }
            })) {
                x1f.a();
                return;
            }
            consumer.accept(c0322w2D.w0());
        }
        ((C2055m4.a) function.apply(c0322w2D)).a(new Consumer() { // from class: owf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C2140n4.a) obj).a(c2055m4.f());
            }
        });
    }

    @Override // com.android.tools.r8.internal.InterfaceC1708i1
    public final InterfaceC2049m1 b(C0322w2 c0322w2) {
        return (C2055m4) this.a.get(c0322w2);
    }

    public static C2055m4.a c(C0322w2 c0322w2) {
        C2055m4.a aVarD = C2055m4.d();
        aVarD.b = c0322w2;
        return aVarD;
    }

    public static C1287d4 b(com.android.tools.r8.graph.I2 i2) {
        C1287d4 c1287d4 = new C1287d4();
        c1287d4.b = i2;
        return c1287d4;
    }

    public static class a implements ArtProfileBuilder, InterfaceC1622h1 {
        public final ArtProfileProvider a;
        public final com.android.tools.r8.graph.B1 b;
        public final C2742u50 c;
        public final LinkedHashMap d;

        public a(ArtProfileProvider artProfileProvider, C2752uB c2752uB) {
            this.d = new LinkedHashMap();
            this.a = artProfileProvider;
            this.b = c2752uB.s();
            this.c = c2752uB.i;
        }

        public static AbstractC2824v4 a(C2055m4 c2055m4, com.android.tools.r8.graph.F2 f2, AbstractC2824v4 abstractC2824v4) {
            if (abstractC2824v4 == null) {
                return c2055m4;
            }
            C2055m4.a aVarD = C2055m4.d();
            aVarD.b = c2055m4.e();
            C2140n4.a aVar = aVarD.c;
            C2140n4 c2140n4F = c2055m4.f();
            aVar.a = c2140n4F.a | aVar.a;
            C2140n4.a aVar2 = aVarD.c;
            C2140n4 c2140n4F2 = ((C2055m4) abstractC2824v4).f();
            aVar2.a = c2140n4F2.a | aVar2.a;
            return aVarD.build();
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
        public final ArtProfileBuilder addClassRule(Consumer consumer) {
            C1287d4 c1287d4 = new C1287d4(this.b);
            consumer.accept(c1287d4);
            com.android.tools.r8.graph.I2 i2 = c1287d4.b;
            this.d.put(i2, new C1371e4(i2));
            return this;
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
        public final a addHumanReadableArtProfile(TextInputStream textInputStream, Consumer consumer) {
            C2467qt.a aVarA = C2467qt.a();
            final C2742u50 c2742u50 = this.c;
            Objects.requireNonNull(c2742u50);
            aVarA.a = new Consumer() { // from class: axf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    c2742u50.info((HumanReadableArtProfileParserErrorDiagnostic) obj);
                }
            };
            C2467qt.a aVarA2 = aVarA.a(this.c).a(this);
            consumer.accept(aVarA2);
            aVarA2.a().a(textInputStream, this.a.getOrigin());
            return this;
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
        public final ArtProfileBuilder addMethodRule(Consumer consumer) {
            com.android.tools.r8.graph.B1 b1 = this.b;
            boolean z = C2055m4.d;
            C2055m4.a aVar = new C2055m4.a(b1);
            consumer.accept(aVar);
            return a(aVar.build());
        }

        public a() {
            this.d = new LinkedHashMap();
            this.a = null;
            this.b = null;
            this.c = null;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        public final InterfaceC1622h1 a(InterfaceC2134n1 interfaceC2134n1) {
            interfaceC2134n1.getClass();
            return a((AbstractC2824v4) interfaceC2134n1);
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        public final a a(C1371e4 c1371e4) {
            this.d.put(c1371e4.b, c1371e4);
            return this;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        public final a a(final C2055m4 c2055m4) {
            this.d.compute(c2055m4.e(), new BiFunction() { // from class: exf
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return X3.a.a(c2055m4, (F2) obj, (AbstractC2824v4) obj2);
                }
            });
            return this;
        }

        public final a a(AbstractC2824v4 abstractC2824v4) {
            return (a) abstractC2824v4.a(new InterfaceC2022lh0() { // from class: cxf
                @Override // com.android.tools.r8.internal.InterfaceC2022lh0
                public final Object apply(Object obj) {
                    return this.a.a((C1371e4) obj);
                }
            }, new InterfaceC2022lh0() { // from class: dxf
                @Override // com.android.tools.r8.internal.InterfaceC2022lh0
                public final Object apply(Object obj) {
                    return this.a.a((C2055m4) obj);
                }
            });
        }

        public final a a(Collection collection) {
            collection.forEach(new Consumer() { // from class: bxf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((AbstractC2739u4) obj);
                }
            });
            return this;
        }

        public final /* synthetic */ void a(AbstractC2739u4 abstractC2739u4) {
            a(abstractC2739u4.c());
        }

        @Override // com.android.tools.r8.internal.InterfaceC1622h1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public X3 build() {
            return new X3(this.d);
        }
    }

    public static a a(ArtProfileProvider artProfileProvider, C2752uB c2752uB) {
        return new a(artProfileProvider, c2752uB);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1708i1
    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        return this.a.containsKey(i2);
    }

    public static void a(ArtProfileRuleConsumer artProfileRuleConsumer, C2055m4 c2055m4) {
        artProfileRuleConsumer.acceptMethodRule(c2055m4.b.z0(), c2055m4.f());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1708i1
    public final boolean a(C0322w2 c0322w2) {
        return this.a.containsKey(c0322w2);
    }

    public static void a(AbstractC3148ys abstractC3148ys, C1371e4 c1371e4, Consumer consumer) {
        com.android.tools.r8.graph.I2 i2 = c1371e4.b;
        abstractC3148ys.getClass();
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(AbstractC3148ys.g(), i2);
        if (b || i2C.M0()) {
            consumer.accept(i2C);
        } else {
            x1f.a();
        }
    }

    public static void a(C0907Vn c0907Vn, C1371e4 c1371e4, Consumer consumer) {
        com.android.tools.r8.graph.I2 i2 = c1371e4.b;
        c0907Vn.getClass();
        com.android.tools.r8.graph.I2 i2C = c0907Vn.c(AbstractC3148ys.g(), i2);
        if (i2C.M0()) {
            consumer.accept(i2C);
        } else {
            if (b || i2C.P0()) {
                return;
            }
            x1f.a();
        }
    }

    public static void a(AbstractC3345r0 abstractC3345r0, com.android.tools.r8.graph.B1 b1, C1371e4 c1371e4, Consumer consumer) {
        consumer.accept(abstractC3345r0.a(b1, c1371e4.b));
    }

    public static void a(C0215h c0215h, C1371e4 c1371e4, Consumer consumer) {
        if (c0215h.e(c1371e4.b)) {
            consumer.accept(c1371e4.b);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1708i1
    public <E1 extends Exception, E2 extends Exception> void a(InterfaceC1936kh0<? super C1371e4, E1> interfaceC1936kh0, InterfaceC1936kh0<? super C2055m4, E2> interfaceC1936kh1) throws Exception {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            ((AbstractC2824v4) it.next()).a(interfaceC1936kh0, interfaceC1936kh1);
        }
    }

    public int a() {
        return this.a.size();
    }

    public final X3 a(C0333y c0333y, final AbstractC3148ys abstractC3148ys) {
        abstractC3148ys.getClass();
        if (abstractC3148ys instanceof C0907Vn) {
            return a(c0333y, abstractC3148ys.a());
        }
        return a(new BiConsumer() { // from class: ywf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                X3.a(abstractC3148ys, (C1371e4) obj, (Consumer) obj2);
            }
        }, new InterfaceC1853ji0() { // from class: zwf
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                X3.a(abstractC3148ys, (C2055m4) obj, (Consumer) obj2, (Function) obj3);
            }
        });
    }

    public static void a(AbstractC3148ys abstractC3148ys, final C2055m4 c2055m4, Consumer consumer, Function function) {
        C0322w2 c0322w2E = c2055m4.e();
        abstractC3148ys.getClass();
        ((C2055m4.a) function.apply(abstractC3148ys.d(AbstractC3148ys.g(), c0322w2E))).a(new Consumer() { // from class: swf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C2140n4.a) obj).a(c2055m4.f());
            }
        });
    }

    public final X3 a(final C0333y c0333y, final C0907Vn c0907Vn) {
        return a(new BiConsumer() { // from class: gwf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                X3.a(c0907Vn, (C1371e4) obj, (Consumer) obj2);
            }
        }, new InterfaceC1853ji0() { // from class: hwf
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                X3.a(c0907Vn, c0333y, (C2055m4) obj, (Consumer) obj2, (Function) obj3);
            }
        });
    }

    public static void a(ArtProfileRuleConsumer artProfileRuleConsumer, C1371e4 c1371e4) {
        artProfileRuleConsumer.acceptClassRule(Reference.classFromDescriptor(c1371e4.b.Z0()), C1457f4.a);
    }

    public final X3 a(C0333y c0333y, final AbstractC3345r0 abstractC3345r0) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        if (!b) {
            abstractC3345r0.getClass();
            if (abstractC3345r0 instanceof C3342p0) {
                x1f.a();
                return null;
            }
        }
        return a(new BiConsumer() { // from class: qwf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                X3.a(abstractC3345r0, b1A, (C1371e4) obj, (Consumer) obj2);
            }
        }, new InterfaceC1853ji0() { // from class: rwf
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                C2055m4 c2055m4 = (C2055m4) obj;
                ((C2055m4.a) ((Function) obj3).apply(abstractC3345r0.a(b1A, c2055m4.e()))).a(new Consumer() { // from class: kwf
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj4) {
                        ((C2140n4.a) obj4).a(c2055m4.f());
                    }
                });
            }
        });
    }

    public final X3 a(C0333y c0333y) {
        final C0215h c0215hG = c0333y.g();
        return a(new BiConsumer() { // from class: nwf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                X3.a(c0215hG, (C1371e4) obj, (Consumer) obj2);
            }
        }, new InterfaceC1853ji0() { // from class: pwf
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                X3.a(c0215hG, (C2055m4) obj, (Consumer) obj2, (Function) obj3);
            }
        });
    }

    public static void a(C0215h c0215h, final C2055m4 c2055m4, Consumer consumer, Function function) {
        if (c2055m4.e().c(c0215h.c(c2055m4.e().w0())) != null) {
            ((C2055m4.a) function.apply(c2055m4.e())).a(new Consumer() { // from class: ewf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((C2140n4.a) obj).a(c2055m4.f());
                }
            });
        }
    }

    public final void a(com.android.tools.r8.graph.I5 i5) {
        Set setKeySet = this.a.keySet();
        Objects.requireNonNull(i5);
        setKeySet.removeIf(new fwf(i5));
    }

    public final X3 a(final BiConsumer biConsumer, final InterfaceC1853ji0 interfaceC1853ji0) throws Exception {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final Consumer consumer = new Consumer() { // from class: twf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                I2 i2 = (I2) obj;
                ((AbstractC2739u4) linkedHashMap.computeIfAbsent(i2, IM.a(new Supplier() { // from class: iwf
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return X3.b(i2);
                    }
                }))).getClass();
            }
        };
        final Function function = new Function() { // from class: uwf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                C0322w2 c0322w2 = (C0322w2) obj;
                return ((AbstractC2739u4) linkedHashMap.computeIfAbsent(c0322w2, IM.a(new Supplier() { // from class: jwf
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return X3.c(c0322w2);
                    }
                }))).b();
            }
        };
        a(new InterfaceC1936kh0() { // from class: vwf
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                biConsumer.accept((C1371e4) obj, consumer);
            }
        }, new InterfaceC1936kh0() { // from class: wwf
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                interfaceC1853ji0.a((C2055m4) obj, consumer, function);
            }
        });
        return new a().a(linkedHashMap.values()).build();
    }

    public void a(ArtProfileConsumer artProfileConsumer, C2742u50 c2742u50) {
        if (artProfileConsumer != null) {
            TextOutputStream humanReadableArtProfileConsumer = artProfileConsumer.getHumanReadableArtProfileConsumer();
            if (humanReadableArtProfileConsumer != null) {
                a(humanReadableArtProfileConsumer);
            }
            ArtProfileRuleConsumer ruleConsumer = artProfileConsumer.getRuleConsumer();
            if (ruleConsumer != null) {
                a(ruleConsumer);
            }
            artProfileConsumer.finished(c2742u50);
        }
    }

    public final void a(TextOutputStream textOutputStream) {
        try {
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(textOutputStream.getOutputStream(), textOutputStream.getCharset());
            try {
                InterfaceC1936kh0 interfaceC1936kh0 = new InterfaceC1936kh0() { // from class: xwf
                    @Override // com.android.tools.r8.internal.InterfaceC1936kh0
                    public final void accept(Object obj) throws IOException {
                        X3.a(outputStreamWriter, (AbstractC2824v4) obj);
                    }
                };
                Iterator it = this.a.values().iterator();
                while (it.hasNext()) {
                    interfaceC1936kh0.accept((AbstractC2824v4) it.next());
                }
                outputStreamWriter.close();
            } catch (Throwable th) {
                try {
                    outputStreamWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    public static /* synthetic */ void a(OutputStreamWriter outputStreamWriter, AbstractC2824v4 abstractC2824v4) throws IOException {
        abstractC2824v4.a(outputStreamWriter);
        outputStreamWriter.write(10);
    }

    public final void a(final ArtProfileRuleConsumer artProfileRuleConsumer) throws Exception {
        a(new InterfaceC1936kh0() { // from class: lwf
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                X3.a(artProfileRuleConsumer, (C1371e4) obj);
            }
        }, new InterfaceC1936kh0() { // from class: mwf
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                X3.a(artProfileRuleConsumer, (C2055m4) obj);
            }
        });
    }
}
