package com.android.tools.r8;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.AbstractC2972wm;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.C1969l4;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2996x4;
import com.android.tools.r8.internal.C3057xm;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.IY;
import com.android.tools.r8.internal.InterfaceC0852Tk;
import com.android.tools.r8.origin.Origin;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L8Command extends BaseCompilerCommand {
    static final /* synthetic */ boolean D = true;
    private final R8Command A;
    private final InterfaceC0852Tk B;
    private final B1 C;
    private final D8Command z;

    /* JADX WARN: Illegal instructions before constructor call */
    private L8Command(R8Command r8Command, D8Command d8Command, com.android.tools.r8.utils.i iVar, CompilationMode compilationMode, ProgramConsumer programConsumer, StringConsumer stringConsumer, int i, C2742u50 c2742u50, boolean z, BiPredicate biPredicate, InterfaceC0852Tk interfaceC0852Tk, List list, List list2, int i2, AbstractC2972wm abstractC2972wm, MapIdProvider mapIdProvider, ClassConflictResolver classConflictResolver, CancelCompilationChecker cancelCompilationChecker, B1 b1) {
        C2752uB.g gVar = C2752uB.g.c;
        List list3 = Collections.EMPTY_LIST;
        super(iVar, compilationMode, programConsumer, stringConsumer, i, c2742u50, gVar, false, z, biPredicate, list, list2, i2, abstractC2972wm, mapIdProvider, null, false, list3, list3, classConflictResolver, cancelCompilationChecker);
        this.z = d8Command;
        this.A = r8Command;
        this.B = interfaceC0852Tk;
        this.C = b1;
    }

    public static Builder builder() {
        return new Builder(new V());
    }

    public static Builder parse(String[] strArr, Origin origin) {
        return new X().a(strArr, origin, builder());
    }

    @Override // com.android.tools.r8.BaseCommand
    public C2752uB b() {
        C2752uB c2752uB = new C2752uB(this.C, g());
        boolean z = D;
        if (!z && c2752uB.Z0) {
            x1f.a();
            return null;
        }
        c2752uB.Z0 = getMode() == CompilationMode.DEBUG;
        if (!z && c2752uB.E1 != null) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.w1) {
            x1f.a();
            return null;
        }
        c2752uB.c(EnumC3077y2.b(getMinApiLevel()));
        if (!z && c2752uB.y0) {
            x1f.a();
            return null;
        }
        if (!z && !c2752uB.A0) {
            x1f.a();
            return null;
        }
        ProgramConsumer programConsumer = getProgramConsumer();
        c2752uB.j = programConsumer;
        if (!z && !(programConsumer instanceof ClassFileConsumer)) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.g0()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.c0()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.t) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.T().b) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.E) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.X) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.Z.a) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.M) {
            x1f.a();
            return null;
        }
        c2752uB.S().a();
        if (!z && c2752uB.E0 != C2752uB.g.c) {
            x1f.a();
            return null;
        }
        if (!z && !c2752uB.x1) {
            x1f.a();
            return null;
        }
        c2752uB.x1 = false;
        InterfaceC0852Tk interfaceC0852Tk = this.B;
        c2752uB.a(interfaceC0852Tk, interfaceC0852Tk.f());
        if (!z && c2752uB.W0 != null) {
            x1f.a();
            return null;
        }
        c2752uB.W0 = new C2996x4(getAssertionsConfiguration(), AssertionsConfiguration.a(g()).setCompileTimeDisable().setScopeAll().build());
        c2752uB.k = IY.a(d(), c2752uB.i);
        c2752uB.c = getCancelCompilationChecker();
        if (!C2752uB.V1) {
            if (!z && c2752uB.g0 != -1) {
                x1f.a();
                return null;
            }
            c2752uB.g0 = getThreadCount();
        }
        c2752uB.t();
        c2752uB.a().a();
        c2752uB.a().c();
        c2752uB.a(f());
        C3057xm.a aVarC = C3057xm.a(com.android.tools.r8.dex.W.b.d).c();
        a(aVarC);
        R8Command r8Command = this.A;
        if (r8Command != null) {
            aVarC.p = r8Command.b().H();
        }
        aVarC.n = this.B;
        c2752uB.q0 = aVarC.a();
        return c2752uB;
    }

    @Override // com.android.tools.r8.BaseCompilerCommand
    public final List c() {
        if (i() != null) {
            return i().c();
        }
        return j() != null ? j().c() : Collections.EMPTY_LIST;
    }

    public D8Command i() {
        return this.z;
    }

    public R8Command j() {
        return this.A;
    }

    public boolean k() {
        return this.A != null;
    }

    public static class Builder extends BaseCompilerCommand.Builder<L8Command, Builder> {
        static final /* synthetic */ boolean D = true;
        private final ArrayList B;
        private final ArrayList C;

        private Builder(DiagnosticsHandler diagnosticsHandler) {
            super(diagnosticsHandler);
            this.B = new ArrayList();
            this.C = new ArrayList();
        }

        public Builder addProguardConfiguration(List<String> list, Origin origin) {
            this.B.add(new C1405eW(list, origin));
            return this;
        }

        public Builder addProguardConfigurationFiles(Path... pathArr) {
            Collections.addAll(this.C, pathArr);
            return this;
        }

        @Override // com.android.tools.r8.BaseCommand.Builder
        public final BaseCommand.Builder c() {
            return this;
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder, com.android.tools.r8.BaseCommand.Builder
        public final void d() {
            if (isPrintHelp()) {
                return;
            }
            C2742u50 c2742u50B = b();
            boolean z = getProgramConsumer() instanceof ClassFileConsumer;
            if (!m()) {
                c2742u50B.a("L8 requires a desugared library configuration");
            }
            if (getProgramConsumer() instanceof DexFilePerClassFileConsumer) {
                c2742u50B.a("L8 does not support compiling to dex per class");
            }
            if (a().c()) {
                c2742u50B.a("L8 does not support a main dex list");
            } else if (getMainDexListConsumer() != null) {
                c2742u50B.a("L8 does not support generating a main dex list");
            }
            if (!isShrinking()) {
                if (this.proguardMapConsumer != null || this.partitionMapConsumer != null) {
                    c2742u50B.a("L8 does not support defining a map consumer when not shrinking");
                }
                if (!f().isEmpty()) {
                    if (z) {
                        c2742u50B.a("L8 does not support rewriting of ART profiles when generating class files");
                    } else {
                        c2742u50B.a("L8 does not impact ART profiles when generating DEX and not shrinking");
                    }
                }
            } else if (z) {
                c2742u50B.a("L8 does not support shrinking when generating class files");
            }
            super.d();
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public final CompilationMode e() {
            return CompilationMode.DEBUG;
        }

        public boolean isShrinking() {
            return (this.B.isEmpty() && this.C.isEmpty()) ? false : true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.tools.r8.BaseCommand.Builder
        public final BaseCommand makeCommand() {
            D8Command d8CommandMakeCommand;
            ProgramConsumer programConsumer;
            R8Command r8Command;
            if (isPrintHelp() || isPrintVersion()) {
                return new L8Command(isPrintHelp(), isPrintVersion());
            }
            if (getMode() == null) {
                setMode(CompilationMode.DEBUG);
            }
            B1 b1 = new B1();
            InterfaceC0852Tk interfaceC0852TkA = a(b1, true);
            com.android.tools.r8.utils.i iVarA = a().a();
            if (isShrinking()) {
                W w = new W();
                R8Command.Builder programConsumer2 = R8Command.builder(b()).addProgramResourceProvider((ProgramResourceProvider) w).a(interfaceC0852TkA.f()).setMinApiLevel(getMinApiLevel()).setMode(getMode()).setIncludeClassesChecksum(getIncludeClassesChecksum()).setDexClassChecksumFilter(getDexClassChecksumFilter()).setProgramConsumer(getProgramConsumer());
                Iterator it = ((ArrayList) f()).iterator();
                while (it.hasNext()) {
                    programConsumer2.a((C1969l4) it.next());
                }
                Iterator<ClassFileResourceProvider> it2 = iVarA.h().iterator();
                while (it2.hasNext()) {
                    programConsumer2.addLibraryResourceProvider(it2.next());
                }
                for (C1405eW c1405eW : this.B) {
                    programConsumer2.addProguardConfiguration((List) c1405eW.a(), (Origin) c1405eW.b());
                }
                StringConsumer stringConsumer = this.proguardMapConsumer;
                if (stringConsumer != null) {
                    programConsumer2.setProguardMapConsumer(stringConsumer);
                }
                PartitionMapConsumer partitionMapConsumer = this.partitionMapConsumer;
                if (partitionMapConsumer != null) {
                    programConsumer2.setPartitionMapConsumer(partitionMapConsumer);
                }
                programConsumer2.addProguardConfiguration(interfaceC0852TkA.e(), Origin.unknown());
                programConsumer2.addProguardConfiguration(new Bc0("-dontwarn sun.misc.Unsafe"), Origin.unknown());
                programConsumer2.addProguardConfigurationFiles(this.C);
                programConsumer2.setDisableDesugaring(true);
                programConsumer2.o();
                R8Command r8CommandMakeCommand = programConsumer2.makeCommand();
                d8CommandMakeCommand = null;
                programConsumer = w;
                r8Command = r8CommandMakeCommand;
            } else if (!(getProgramConsumer() instanceof ClassFileConsumer)) {
                W w2 = new W();
                D8Command.Builder builderQ = ((D8Command.Builder) D8Command.builder(b()).addProgramResourceProvider(w2)).a(interfaceC0852TkA.f()).setMinApiLevel(getMinApiLevel()).setMode(getMode()).setIncludeClassesChecksum(getIncludeClassesChecksum()).setDexClassChecksumFilter(getDexClassChecksumFilter()).setProgramConsumer(getProgramConsumer()).q();
                Iterator<ClassFileResourceProvider> it3 = iVarA.h().iterator();
                while (it3.hasNext()) {
                    builderQ.addLibraryResourceProvider(it3.next());
                }
                builderQ.setDisableDesugaring(true);
                builderQ.o();
                d8CommandMakeCommand = builderQ.makeCommand();
                programConsumer = w2;
                r8Command = null;
            } else {
                if (!D && !(getProgramConsumer() instanceof ClassFileConsumer)) {
                    x1f.a();
                    return null;
                }
                d8CommandMakeCommand = null;
                programConsumer = getProgramConsumer();
                r8Command = null;
            }
            return new L8Command(r8Command, d8CommandMakeCommand, iVarA, getMode(), programConsumer, getMainDexListConsumer(), getMinApiLevel(), b(), getIncludeClassesChecksum(), getDexClassChecksumFilter(), interfaceC0852TkA, g(), j(), l(), i(), getMapIdProvider(), h(), getCancelCompilationChecker(), b1);
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public Builder setAndroidPlatformBuild(boolean z) {
            throw b().b("L8 does not support configuring Android platform builds.");
        }

        public Builder setProguardMapConsumer(StringConsumer stringConsumer) {
            this.proguardMapConsumer = stringConsumer;
            return (Builder) ((BaseCompilerCommand.Builder) c());
        }

        public Builder setProguardMapOutputPath(Path path) {
            if (BaseCompilerCommand.Builder.A || path != null) {
                return (Builder) setProguardMapConsumer((StringConsumer) new StringConsumer.FileConsumer(path));
            }
            x1f.a();
            return null;
        }

        public Builder addProguardConfigurationFiles(List<Path> list) {
            this.C.addAll(list);
            return this;
        }
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    public static Builder parse(String[] strArr, Origin origin, DiagnosticsHandler diagnosticsHandler) {
        return new X().a(strArr, origin, builder(diagnosticsHandler));
    }

    private L8Command(boolean z, boolean z2) {
        super(z, z2);
        this.A = null;
        this.z = null;
        this.B = null;
        this.C = null;
    }
}
