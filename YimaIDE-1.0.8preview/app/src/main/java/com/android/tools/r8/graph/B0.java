package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.naming.C3313b;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class B0 {
    public final AbstractC0327x0 a;
    public final C2752uB b;

    public B0(AbstractC0327x0 abstractC0327x0, C2752uB c2752uB) {
        this.a = abstractC0327x0;
        this.b = c2752uB;
    }

    public static PrintStream a(C3313b c3313b, Path path, String str, E0 e0) throws IOException {
        Path pathResolve = path.resolve(C0929Wj.a(e0.e.Z0(), c3313b).replace('.', File.separatorChar) + str);
        Path parent = pathResolve.getParent();
        if (parent != null) {
            Files.createDirectories(parent, new FileAttribute[0]);
        }
        return new PrintStream(Files.newOutputStream(pathResolve, new OpenOption[0]));
    }

    public abstract void b(D2 d2, PrintStream printStream);

    public void b(final PrintStream printStream) throws IOException {
        e(printStream);
        a(new A0() { // from class: ek0
            @Override // com.android.tools.r8.graph.A0
            public final PrintStream a(E0 e0) {
                return B0.a(printStream, e0);
            }
        }, new Consumer() { // from class: fk0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                B0.a((PrintStream) obj);
            }
        });
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract void a(PrintStream printStream, B5 b5);

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract void a(PrintStream printStream, C0210g1 c0210g1);

    public abstract void c(D2 d2, PrintStream printStream);

    public void c(PrintStream printStream) {
    }

    public void d(PrintStream printStream) {
    }

    public final void e(PrintStream printStream) {
        Collection<com.android.tools.r8.dex.W> collectionF = this.a.e.f();
        System.out.println("Number of markers: " + collectionF.size());
        Iterator<com.android.tools.r8.dex.W> it = collectionF.iterator();
        while (it.hasNext()) {
            printStream.println(it.next().toString());
        }
    }

    public static /* synthetic */ void a(PrintStream printStream) {
    }

    public static A0 a(final C3313b c3313b, final Path path, final String str) {
        return new A0() { // from class: dk0
            @Override // com.android.tools.r8.graph.A0
            public final PrintStream a(E0 e0) {
                return B0.a(c3313b, path, str, e0);
            }
        };
    }

    public static /* synthetic */ PrintStream a(PrintStream printStream, E0 e0) {
        return printStream;
    }

    public final void a(A0 a0, Consumer consumer) {
        for (D2 d2 : this.a.e()) {
            if (a(d2)) {
                PrintStream printStreamA = a0.a(d2);
                try {
                    a(d2, printStreamA);
                    consumer.accept(printStreamA);
                } catch (Throwable th) {
                    consumer.accept(printStreamA);
                    throw th;
                }
            }
        }
    }

    public final boolean a(D2 d2) {
        if (!this.b.R()) {
            return true;
        }
        H4 h4V = d2.V();
        final C2752uB c2752uB = this.b;
        Objects.requireNonNull(c2752uB);
        Predicate predicate = new Predicate() { // from class: ck0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return c2752uB.a((C0231j1) obj);
            }
        };
        C0231j1 c0231j1A = h4V.b.a(predicate);
        if (c0231j1A == null) {
            c0231j1A = h4V.b.b(predicate);
        }
        return c0231j1A != null;
    }

    public final void a(D2 d2, final PrintStream printStream) {
        c(d2, printStream);
        d(printStream);
        d2.d(new Consumer() { // from class: ak0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(printStream, (C0210g1) obj);
            }
        });
        c(printStream);
        d2.n(new Consumer() { // from class: bk0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(printStream, (B5) obj);
            }
        });
        b(d2, printStream);
    }
}
