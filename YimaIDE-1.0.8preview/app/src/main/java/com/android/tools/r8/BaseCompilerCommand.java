package com.android.tools.r8;

import com.android.tools.r8.BaseCompilerCommand;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.inspector.Inspector;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2972wm;
import com.android.tools.r8.internal.C0394Bt;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0878Uk;
import com.android.tools.r8.internal.C1969l4;
import com.android.tools.r8.internal.C2124mt;
import com.android.tools.r8.internal.C2552rt;
import com.android.tools.r8.internal.C2629sm;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.C3057xm;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.InterfaceC0852Tk;
import com.android.tools.r8.internal.KY;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.T40;
import com.android.tools.r8.internal.W40;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.profile.art.ArtProfileConsumer;
import com.android.tools.r8.profile.art.ArtProfileProvider;
import com.android.tools.r8.startup.StartupProfileProvider;
import defpackage.gk0;
import defpackage.n33;
import defpackage.vn0;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class BaseCompilerCommand extends BaseCommand {
    static final /* synthetic */ boolean y = true;
    private final CompilationMode e;
    private final ProgramConsumer f;
    private final StringConsumer g;
    private final int h;
    private final C2742u50 i;
    private final C2752uB.g j;
    private final boolean k;
    private final boolean l;
    private final BiPredicate m;
    private final List n;
    private final List o;
    private final int p;
    private final AbstractC2972wm q;
    private final MapIdProvider r;
    private final SourceFileProvider s;
    private final boolean t;
    private final List u;
    private final List v;
    private final ClassConflictResolver w;
    private final CancelCompilationChecker x;

    public BaseCompilerCommand(com.android.tools.r8.utils.i iVar, CompilationMode compilationMode, ProgramConsumer programConsumer, StringConsumer stringConsumer, int i, C2742u50 c2742u50, C2752uB.g gVar, boolean z, boolean z2, BiPredicate biPredicate, List list, List list2, int i2, AbstractC2972wm abstractC2972wm, MapIdProvider mapIdProvider, SourceFileProvider sourceFileProvider, boolean z3, List list3, List list4, ClassConflictResolver classConflictResolver, CancelCompilationChecker cancelCompilationChecker) {
        super(iVar);
        boolean z4 = y;
        if (!z4 && i <= 0) {
            x1f.a();
            throw null;
        }
        if (!z4 && compilationMode == null) {
            x1f.a();
            throw null;
        }
        this.e = compilationMode;
        this.f = programConsumer;
        this.g = stringConsumer;
        this.h = i;
        this.i = c2742u50;
        this.j = gVar;
        this.l = z;
        this.k = z2;
        this.m = biPredicate;
        this.n = list;
        this.o = list2;
        this.p = i2;
        this.q = abstractC2972wm;
        this.r = mapIdProvider;
        this.s = sourceFileProvider;
        this.t = z3;
        this.u = list3;
        this.v = list4;
        this.w = classConflictResolver;
        this.x = cancelCompilationChecker;
    }

    public final void a(C3057xm.a aVar) {
        com.android.tools.r8.dex.W.a aVar2;
        ProgramConsumer programConsumer = this.f;
        if (programConsumer instanceof ClassFileConsumer) {
            aVar2 = com.android.tools.r8.dex.W.a.b;
        } else {
            if (!KY.a && !(programConsumer instanceof DexIndexedConsumer) && !(programConsumer instanceof DexFilePerClassFileConsumer)) {
                x1f.a();
                return;
            }
            aVar2 = com.android.tools.r8.dex.W.a.c;
        }
        C3057xm.a aVarA = aVar.a(aVar2);
        aVarA.c = getMode();
        aVarA.d = getMinApiLevel();
        aVarA.e = isOptimizeMultidexForLinearAlloc();
        aVarA.f = getThreadCount();
        aVarA.g = e();
        aVarA.r = C2847vL.a((Collection) c(), (Function) new vn0());
        aVarA.s = h();
        if (getAndroidPlatformBuild()) {
            aVar.v = true;
        }
    }

    public final ClassConflictResolver d() {
        return this.w;
    }

    public C2752uB.g e() {
        return this.j;
    }

    public final AbstractC2972wm f() {
        return this.q;
    }

    public C2742u50 g() {
        return this.i;
    }

    public boolean getAndroidPlatformBuild() {
        return this.t;
    }

    public List<AssertionsConfiguration> getAssertionsConfiguration() {
        return Collections.unmodifiableList(this.n);
    }

    public CancelCompilationChecker getCancelCompilationChecker() {
        return this.x;
    }

    public BiPredicate<String, Long> getDexClassChecksumFilter() {
        return this.m;
    }

    public boolean getEnableDesugaring() {
        return this.j == C2752uB.g.c;
    }

    public boolean getIncludeClassesChecksum() {
        return this.k;
    }

    public StringConsumer getMainDexListConsumer() {
        return this.g;
    }

    public MapIdProvider getMapIdProvider() {
        return this.r;
    }

    public int getMinApiLevel() {
        return this.h;
    }

    public CompilationMode getMode() {
        return this.e;
    }

    public Collection<Consumer<Inspector>> getOutputInspections() {
        return Collections.unmodifiableList(this.o);
    }

    public ProgramConsumer getProgramConsumer() {
        return this.f;
    }

    public SourceFileProvider getSourceFileProvider() {
        return this.s;
    }

    public int getThreadCount() {
        return this.p;
    }

    public List<StartupProfileProvider> h() {
        return this.v;
    }

    public boolean isOptimizeMultidexForLinearAlloc() {
        return this.l;
    }

    public List<C1969l4> c() {
        return this.u;
    }

    public static abstract class Builder<C extends BaseCompilerCommand, B extends Builder<C, B>> extends BaseCommand.Builder<C, B> {
        static final /* synthetic */ boolean A = true;
        protected C2752uB.g desugarState;
        private ProgramConsumer f;
        private StringConsumer g;
        private Path h;
        private OutputMode i;
        private CompilationMode j;
        private int k;
        private int l;
        private final ArrayList m;
        private boolean n;
        private boolean o;
        private BiPredicate p;
        protected PartitionMapConsumer partitionMapConsumer;
        protected StringConsumer proguardMapConsumer;
        private final ArrayList q;
        private final ArrayList r;
        private AbstractC2972wm s;
        private MapIdProvider t;
        private SourceFileProvider u;
        private boolean v;
        private final ArrayList w;
        private final ArrayList x;
        private ClassConflictResolver y;
        private CancelCompilationChecker z;

        public Builder(com.android.tools.r8.utils.i iVar, DiagnosticsHandler diagnosticsHandler) {
            C2742u50 c2742u50 = new C2742u50(diagnosticsHandler);
            boolean z = com.android.tools.r8.utils.i.j;
            com.android.tools.r8.utils.i.a aVar = new com.android.tools.r8.utils.i.a(c2742u50);
            aVar.a.addAll(iVar.a);
            aVar.e.addAll(iVar.c);
            aVar.f.addAll(iVar.d);
            aVar.g.addAll(iVar.e);
            aVar.h = iVar.h;
            aVar.i = iVar.i;
            aVar.l = iVar.g;
            super(aVar);
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = OutputMode.DexIndexed;
            this.k = 0;
            this.l = -1;
            this.desugarState = C2752uB.g.c;
            this.m = new ArrayList();
            this.n = false;
            this.o = false;
            this.p = new BiPredicate() { // from class: xn0
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return BaseCompilerCommand.Builder.a((String) obj, (Long) obj2);
                }
            };
            this.q = new ArrayList();
            this.r = new ArrayList();
            this.proguardMapConsumer = null;
            this.partitionMapConsumer = null;
            this.s = AbstractC2972wm.a();
            this.t = null;
            this.u = null;
            this.v = false;
            this.w = new ArrayList();
            this.x = new ArrayList();
            this.y = null;
            this.z = null;
            this.j = CompilationMode.RELEASE;
        }

        public final InterfaceC0852Tk a(B1 b1, boolean z) {
            if (!this.m.isEmpty()) {
                if (this.m.size() <= 1) {
                    return C0878Uk.a((t0) this.m.get(0), b1, b(), z, getMinApiLevel());
                }
                n33.a("Only one desugared library configuration is supported.");
                return null;
            }
            EnumC3077y2 enumC3077y2 = EnumC3077y2.c;
            int i = AbstractC0551Hu.c;
            C0394Bt c0394Bt = new C0394Bt(enumC3077y2, "unused", null, null, true, P40.e);
            T40 t40 = T40.i;
            int i2 = AbstractC2554rv.c;
            W40 w40 = W40.j;
            return new C2124mt(c0394Bt, new C2552rt(t40, w40, w40, t40, t40, t40, t40, t40, t40, t40, t40, t40, t40, t40, w40, t40, w40, t40, t40), false);
        }

        public B addArtProfileForRewriting(ArtProfileProvider artProfileProvider, ArtProfileConsumer artProfileConsumer) {
            this.w.add(new C1969l4(artProfileProvider, artProfileConsumer));
            return (B) c();
        }

        public B addAssertionsConfiguration(Function<AssertionsConfiguration.Builder, AssertionsConfiguration> function) {
            this.q.add(function.apply(AssertionsConfiguration.a(b())));
            return (B) c();
        }

        public B addDesugaredLibraryConfiguration(String str) {
            this.m.add(t0.a(str, Origin.unknown()));
            return (B) c();
        }

        public B addOutputInspection(Consumer<Inspector> consumer) {
            this.r.add(consumer);
            return (B) c();
        }

        public B addSpecialLibraryConfiguration(String str) {
            return (B) addDesugaredLibraryConfiguration(str);
        }

        public Builder addStartupProfileProviders(Collection collection) {
            this.x.addAll(collection);
            return (Builder) c();
        }

        public final void c(Path path) {
            this.s = AbstractC2972wm.a(path);
        }

        public U createProgramOutputConsumer(Path path, OutputMode outputMode, boolean z) {
            if (outputMode == OutputMode.DexIndexed) {
                return C0831Sp.a(path) ? new DexIndexedConsumer.ArchiveConsumer(path, z) : new DexIndexedConsumer.DirectoryConsumer(path, z);
            }
            if (outputMode == OutputMode.DexFilePerClass) {
                return C0831Sp.a(path) ? new C3281j(path, z) : new C3282k(path, z);
            }
            if (outputMode == OutputMode.DexFilePerClassFile) {
                return C0831Sp.a(path) ? new DexFilePerClassFileConsumer.ArchiveConsumer(path, z) : new DexFilePerClassFileConsumer.DirectoryConsumer(path, z);
            }
            if (outputMode == OutputMode.ClassFile) {
                return C0831Sp.a(path) ? new ClassFileConsumer.ArchiveConsumer(path, z) : new ClassFileConsumer.DirectoryConsumer(path, z);
            }
            gk0.a("Unexpected output mode: ", outputMode);
            return null;
        }

        @Override // com.android.tools.r8.BaseCommand.Builder
        public void d() {
            C2742u50 c2742u50B = b();
            if (this.j == null) {
                c2742u50B.a("Expected valid compilation mode, was null");
            }
            C0831Sp.a(c2742u50B, this.h);
            if (getProgramConsumer() == null) {
                c2742u50B.a("A ProgramConsumer or Output is required for compilation");
            }
            ArrayList<Class> arrayList = new ArrayList(3);
            if (this.f instanceof DexIndexedConsumer) {
                arrayList.add(DexIndexedConsumer.class);
            }
            if (this.f instanceof DexFilePerClassFileConsumer) {
                arrayList.add(DexFilePerClassFileConsumer.class);
            }
            if (this.f instanceof ClassFileConsumer) {
                arrayList.add(ClassFileConsumer.class);
            }
            if (arrayList.size() > 1) {
                StringBuilder sb = new StringBuilder("Invalid program consumer. A program consumer can implement at most one consumer type but ");
                sb.append(this.f.getClass().getName());
                sb.append(" implements types:");
                for (Class cls : arrayList) {
                    sb.append(" ");
                    sb.append(cls.getName());
                }
                c2742u50B.a(sb.toString());
            }
            int minApiLevel = getMinApiLevel();
            EnumC3077y2 enumC3077y2 = EnumC3077y2.L;
            if (minApiLevel > enumC3077y2.d() && getMinApiLevel() != 10000) {
                c2742u50B.c("An API level of " + getMinApiLevel() + " is not supported by this compiler. Please use an API level of " + enumC3077y2.d() + " or earlier");
            }
            if (m() && getAndroidPlatformBuild()) {
                c2742u50B.a("Android platform builds cannot use desugared library");
            }
        }

        public abstract CompilationMode e();

        public final List f() {
            return this.w;
        }

        public final List g() {
            return this.q;
        }

        public boolean getAndroidPlatformBuild() {
            return this.v;
        }

        public CancelCompilationChecker getCancelCompilationChecker() {
            return this.z;
        }

        public BiPredicate<String, Long> getDexClassChecksumFilter() {
            return this.p;
        }

        public boolean getDisableDesugaring() {
            return this.desugarState == C2752uB.g.b;
        }

        public boolean getIncludeClassesChecksum() {
            return this.n;
        }

        public StringConsumer getMainDexListConsumer() {
            return this.g;
        }

        public MapIdProvider getMapIdProvider() {
            return this.t;
        }

        public int getMinApiLevel() {
            return n() ? this.k : EnumC3077y2.b().d();
        }

        public CompilationMode getMode() {
            return this.j;
        }

        public OutputMode getOutputMode() {
            return this.i;
        }

        public Path getOutputPath() {
            return this.h;
        }

        public ProgramConsumer getProgramConsumer() {
            return this.f;
        }

        public StringConsumer getProguardMapConsumer() {
            return this.proguardMapConsumer;
        }

        public SourceFileProvider getSourceFileProvider() {
            return this.u;
        }

        public final ClassConflictResolver h() {
            return this.y;
        }

        public final AbstractC2972wm i() {
            return this.s;
        }

        public boolean isOptimizeMultidexForLinearAlloc() {
            return this.o;
        }

        public final List j() {
            return this.r;
        }

        public final List k() {
            return this.x;
        }

        public final int l() {
            return this.l;
        }

        public final boolean m() {
            return !this.m.isEmpty();
        }

        public boolean n() {
            return this.k != 0;
        }

        public final void o() {
            this.s = new C2629sm();
        }

        public B setAndroidPlatformBuild(boolean z) {
            this.v = z;
            return (B) c();
        }

        public B setCancelCompilationChecker(CancelCompilationChecker cancelCompilationChecker) {
            this.z = cancelCompilationChecker;
            return (B) c();
        }

        public B setClassConflictResolver(ClassConflictResolver classConflictResolver) {
            this.y = classConflictResolver;
            return (B) c();
        }

        public B setDexClassChecksumFilter(BiPredicate<String, Long> biPredicate) {
            if (A || biPredicate != null) {
                this.p = biPredicate;
                return (B) c();
            }
            x1f.a();
            return null;
        }

        public B setDisableDesugaring(boolean z) {
            this.desugarState = z ? C2752uB.g.b : C2752uB.g.c;
            return (B) c();
        }

        public B setEnableDesugaring(boolean z) {
            this.desugarState = z ? C2752uB.g.c : C2752uB.g.b;
            return (B) c();
        }

        public B setIncludeClassesChecksum(boolean z) {
            this.n = z;
            return (B) c();
        }

        public B setMainDexListConsumer(StringConsumer stringConsumer) {
            this.g = stringConsumer;
            return (B) c();
        }

        public B setMainDexListOutputPath(Path path) {
            this.g = new StringConsumer.FileConsumer(path);
            return (B) c();
        }

        public B setMapIdProvider(MapIdProvider mapIdProvider) {
            this.t = mapIdProvider;
            return (B) c();
        }

        public B setMinApiLevel(int i) {
            if (i <= 0) {
                b().a("Invalid minApiLevel: " + i);
            } else {
                this.k = i;
            }
            return (B) c();
        }

        public B setMode(CompilationMode compilationMode) {
            if (A || compilationMode != null) {
                this.j = compilationMode;
                return (B) c();
            }
            x1f.a();
            return null;
        }

        public B setOptimizeMultidexForLinearAlloc(boolean z) {
            this.o = z;
            return (B) c();
        }

        public B setOutput(Path path, OutputMode outputMode, boolean z) {
            boolean z2 = A;
            if (!z2 && path == null) {
                x1f.a();
                return null;
            }
            if (!z2 && outputMode == null) {
                x1f.a();
                return null;
            }
            this.h = path;
            this.i = outputMode;
            this.f = createProgramOutputConsumer(path, outputMode, z);
            return (B) c();
        }

        public B setPartitionMapConsumer(PartitionMapConsumer partitionMapConsumer) {
            this.partitionMapConsumer = partitionMapConsumer;
            return (B) c();
        }

        public B setPartitionMapOutputPath(Path path) {
            if (A || path != null) {
                return (B) setPartitionMapConsumer(new com.android.tools.r8.utils.t(path));
            }
            x1f.a();
            return null;
        }

        public B setProgramConsumer(ProgramConsumer programConsumer) {
            this.h = null;
            this.i = null;
            this.f = programConsumer;
            return (B) c();
        }

        public B setSourceFileProvider(SourceFileProvider sourceFileProvider) {
            this.u = sourceFileProvider;
            return (B) c();
        }

        public B addDesugaredLibraryConfiguration(t0 t0Var) {
            this.m.add(t0Var);
            return (B) c();
        }

        public B setOutput(Path path, OutputMode outputMode) {
            return (B) setOutput(path, outputMode, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean a(String str, Long l) {
            return true;
        }

        public final Builder a(int i) {
            if (i <= 0) {
                b().a("Invalid threadCount: " + i);
            } else {
                this.l = i;
            }
            return (Builder) c();
        }

        public final void a(C1969l4 c1969l4) {
            this.w.add(c1969l4);
        }

        public Builder(com.android.tools.r8.utils.i iVar) {
            super(com.android.tools.r8.utils.i.a(iVar));
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = OutputMode.DexIndexed;
            this.k = 0;
            this.l = -1;
            this.desugarState = C2752uB.g.c;
            this.m = new ArrayList();
            this.n = false;
            this.o = false;
            this.p = new BiPredicate() { // from class: xn0
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return BaseCompilerCommand.Builder.a((String) obj, (Long) obj2);
                }
            };
            this.q = new ArrayList();
            this.r = new ArrayList();
            this.proguardMapConsumer = null;
            this.partitionMapConsumer = null;
            this.s = AbstractC2972wm.a();
            this.t = null;
            this.u = null;
            this.v = false;
            this.w = new ArrayList();
            this.x = new ArrayList();
            this.y = null;
            this.z = null;
            this.j = e();
        }

        public Builder(DiagnosticsHandler diagnosticsHandler) {
            super(com.android.tools.r8.utils.i.a(new C2742u50(diagnosticsHandler)));
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = OutputMode.DexIndexed;
            this.k = 0;
            this.l = -1;
            this.desugarState = C2752uB.g.c;
            this.m = new ArrayList();
            this.n = false;
            this.o = false;
            this.p = new BiPredicate() { // from class: xn0
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return BaseCompilerCommand.Builder.a((String) obj, (Long) obj2);
                }
            };
            this.q = new ArrayList();
            this.r = new ArrayList();
            this.proguardMapConsumer = null;
            this.partitionMapConsumer = null;
            this.s = AbstractC2972wm.a();
            this.t = null;
            this.u = null;
            this.v = false;
            this.w = new ArrayList();
            this.x = new ArrayList();
            this.y = null;
            this.z = null;
            this.j = e();
        }

        public final void d(Path path) {
            this.s = AbstractC2972wm.b(path);
        }
    }

    public BaseCompilerCommand(boolean z, boolean z2) {
        super(z, z2);
        this.f = null;
        this.g = null;
        this.e = null;
        this.h = 0;
        this.i = new C2742u50();
        this.j = C2752uB.g.c;
        this.k = false;
        this.l = false;
        this.m = new BiPredicate() { // from class: wn0
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return BaseCompilerCommand.a((String) obj, (Long) obj2);
            }
        };
        this.n = new ArrayList();
        this.o = null;
        this.p = -1;
        this.q = new C2629sm();
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(String str, Long l) {
        return true;
    }
}
