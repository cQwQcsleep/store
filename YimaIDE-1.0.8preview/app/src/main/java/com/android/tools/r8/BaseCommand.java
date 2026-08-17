package com.android.tools.r8;

import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C1534g;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.gn0;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class BaseCommand {
    static final /* synthetic */ boolean d = true;
    private final boolean a;
    private final boolean b;
    private final com.android.tools.r8.utils.i c;

    public BaseCommand(com.android.tools.r8.utils.i iVar) {
        if (!d && iVar == null) {
            x1f.a();
            throw null;
        }
        this.c = iVar;
        this.a = false;
        this.b = false;
    }

    public com.android.tools.r8.utils.i a() {
        return this.c;
    }

    public abstract C2752uB b();

    public boolean isPrintHelp() {
        return this.a;
    }

    public boolean isPrintVersion() {
        return this.b;
    }

    public static abstract class Builder<C extends BaseCommand, B extends Builder<C, B>> {
        private final C2742u50 a;
        private final com.android.tools.r8.utils.i.a d;
        private boolean b = false;
        private boolean c = false;
        final ArrayList e = new ArrayList();

        public Builder(com.android.tools.r8.utils.i.a aVar) {
            this.d = aVar;
            this.a = aVar.m;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Path[] pathArr) {
            try {
                com.android.tools.r8.utils.i.a aVar = this.d;
                aVar.getClass();
                for (Path path : Arrays.asList(pathArr)) {
                    if (!Files.exists(path, new LinkOption[0])) {
                        throw new NoSuchFileException(path.toString());
                    }
                    aVar.h.add(t0.a(path));
                }
            } catch (NoSuchFileException e) {
                this.a.error(new StringDiagnostic("Main-dex-list file does not exist", new PathOrigin(Paths.get(e.getFile(), new String[0]))));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(Collection collection) {
            this.d.i.addAll(collection);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(Collection collection) {
            try {
                com.android.tools.r8.utils.i.a aVar = this.d;
                aVar.getClass();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Path path = (Path) it.next();
                    if (!Files.exists(path, new LinkOption[0])) {
                        throw new NoSuchFileException(path.toString());
                    }
                    aVar.h.add(t0.a(path));
                }
            } catch (NoSuchFileException e) {
                this.a.error(new StringDiagnostic("Main-dex-list file does not exist", new PathOrigin(Paths.get(e.getFile(), new String[0]))));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Path path = (Path) it.next();
                try {
                    this.d.c(path);
                    this.e.add(path);
                } catch (C0613Ke e) {
                    this.a.error(new ExceptionDiagnostic(e, new C0348i(path)));
                }
            }
        }

        public final void a(Runnable runnable) {
            try {
                runnable.run();
            } catch (C0613Ke e) {
                this.a.error(new StringDiagnostic(e.getMessage(), e.b, e.c));
            } catch (C1534g unused) {
            }
        }

        public B addClassProgramData(final byte[] bArr, final Origin origin) {
            a(new Runnable() { // from class: un0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(bArr, origin);
                }
            });
            return (B) c();
        }

        public B addClasspathFiles(final Path... pathArr) {
            a(new Runnable() { // from class: ln0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(pathArr);
                }
            });
            return (B) c();
        }

        public B addClasspathResourceProvider(final ClassFileResourceProvider classFileResourceProvider) {
            a(new Runnable() { // from class: in0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(classFileResourceProvider);
                }
            });
            return (B) c();
        }

        public B addDexProgramData(final byte[] bArr, final Origin origin) {
            a(new Runnable() { // from class: on0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.b(bArr, origin);
                }
            });
            return (B) c();
        }

        public B addLibraryFiles(final Collection<Path> collection) {
            a(new Runnable() { // from class: jn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.b(collection);
                }
            });
            return (B) c();
        }

        public B addLibraryResourceProvider(final ClassFileResourceProvider classFileResourceProvider) {
            a(new Runnable() { // from class: sn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.b(classFileResourceProvider);
                }
            });
            return (B) c();
        }

        public B addMainDexClasses(final String... strArr) {
            a(new Runnable() { // from class: mn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(strArr);
                }
            });
            return (B) c();
        }

        public B addMainDexListFiles(final Path... pathArr) {
            a(new Runnable() { // from class: tn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.b(pathArr);
                }
            });
            return (B) c();
        }

        public B addProgramFiles(final Collection<Path> collection) {
            a(new Runnable() { // from class: fn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.e(collection);
                }
            });
            return (B) c();
        }

        public B addProgramResourceProvider(ProgramResourceProvider programResourceProvider) {
            this.d.a(programResourceProvider);
            return (B) c();
        }

        public final C build() throws CompilationFailedException {
            final C1975l7 c1975l7 = new C1975l7(null);
            AbstractC2632so.a(this.a, new AbstractC2632so.a() { // from class: kn0
                @Override // com.android.tools.r8.internal.AbstractC2632so.a
                public final void run() {
                    this.a.a(c1975l7);
                }
            });
            return (C) c1975l7.a();
        }

        public abstract Builder c();

        public void error(Diagnostic diagnostic) {
            this.a.error(diagnostic);
        }

        public RuntimeException fatalError(Diagnostic diagnostic) {
            C2742u50 c2742u50 = this.a;
            c2742u50.a(null, diagnostic);
            throw c2742u50.c;
        }

        public boolean isPrintHelp() {
            return this.b;
        }

        public boolean isPrintVersion() {
            return this.c;
        }

        public abstract BaseCommand makeCommand();

        public B setPrintHelp(boolean z) {
            this.b = z;
            return (B) c();
        }

        public B setPrintVersion(boolean z) {
            this.c = z;
            return (B) c();
        }

        public B addClasspathFiles(final Collection<Path> collection) {
            a(new Runnable() { // from class: rn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(collection);
                }
            });
            return (B) c();
        }

        public B addLibraryFiles(Path... pathArr) {
            addLibraryFiles(Arrays.asList(pathArr));
            return (B) c();
        }

        public B addMainDexClasses(final Collection<String> collection) {
            a(new Runnable() { // from class: qn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.c(collection);
                }
            });
            return (B) c();
        }

        public B addMainDexListFiles(final Collection<Path> collection) {
            a(new Runnable() { // from class: nn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.d(collection);
                }
            });
            return (B) c();
        }

        public B addProgramFiles(Path... pathArr) {
            addProgramFiles(Arrays.asList(pathArr));
            return (B) c();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(C1975l7 c1975l7) {
            d();
            c1975l7.a(makeCommand());
            this.a.a();
        }

        public com.android.tools.r8.utils.i.a a() {
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(Path[] pathArr) {
            Arrays.stream(pathArr).forEach(new gn0(this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(Collection collection) {
            collection.forEach(new gn0(this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(final Path path) {
            a(new Runnable() { // from class: pn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.b(path);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(ClassFileResourceProvider classFileResourceProvider) {
            a().a(classFileResourceProvider);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(byte[] bArr, Origin origin) {
            this.d.a(bArr, origin);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String[] strArr) {
            com.android.tools.r8.utils.i.a aVar = this.d;
            aVar.getClass();
            aVar.i.addAll(Arrays.asList(strArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(boolean z) {
            this.d.j = z;
        }

        public final void a(PathOrigin pathOrigin, IOException iOException) {
            this.a.error(new ExceptionDiagnostic(iOException, pathOrigin));
        }

        public void d() {
        }

        public C2742u50 b() {
            return this.a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(ClassFileResourceProvider classFileResourceProvider) {
            a().b(classFileResourceProvider);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Path path = (Path) it.next();
                try {
                    this.d.b(path);
                } catch (C0613Ke e) {
                    this.a.error(new ExceptionDiagnostic(e, new C0347h(path)));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Path path) {
            com.android.tools.r8.utils.i.a aVarA = a();
            aVarA.a(path, aVarA.e);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(byte[] bArr, Origin origin) {
            this.d.b(bArr, origin);
        }

        public final void b(final boolean z) {
            a(new Runnable() { // from class: hn0
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(z);
                }
            });
        }
    }

    public BaseCommand(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
        this.c = null;
    }
}
