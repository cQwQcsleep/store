package com.android.tools.r8.relocator;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.dex.C0011c;
import com.android.tools.r8.dex.W;
import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0243l;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.C1086ah0;
import com.android.tools.r8.internal.C2466qs;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2832v8;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.relocator.Relocator;
import com.android.tools.r8.synthesis.E;
import com.android.tools.r8.utils.i;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Relocator {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(RelocatorCommand relocatorCommand, ExecutorService executorService, i iVar, C2752uB c2752uB) {
        try {
            c(relocatorCommand, executorService, iVar, c2752uB);
        } finally {
            executorService.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(RelocatorCommand relocatorCommand, ExecutorService executorService, i iVar, C2752uB c2752uB) throws IOException {
        Ch0 ch0A = Ch0.a(c2752uB, "Relocator");
        try {
            try {
                C0215h c0215hA = C0215h.a(new C0011c(iVar, c2752uB, ch0A).a(executorService), E.d());
                C0333y c0333yB = C0333y.b(c0215hA);
                c0333yB.a(C0243l.a((C0333y<?>) c0333yB).a());
                c0333yB.l = relocatorCommand.getMapping().a(c0333yB);
                new C2466qs(c0333yB).a(c0215hA.d(), executorService);
                C2832v8 c2832v8 = new C2832v8(c0333yB, new W(W.b.f));
                ClassFileConsumer consumer = relocatorCommand.getConsumer();
                if (!C2832v8.g && c2832v8.c.Q()) {
                    throw new AssertionError();
                }
                c2832v8.a(consumer, (i) null);
                c2752uB.l0();
                iVar.b(c2752uB.i);
                c2752uB.r0();
                if (c2752uB.r) {
                    ch0A.c();
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            iVar.b(c2752uB.i);
            c2752uB.r0();
            if (c2752uB.r) {
                ch0A.c();
            }
            throw th;
        }
    }

    public static void run(final RelocatorCommand relocatorCommand) throws CompilationFailedException {
        final i app = relocatorCommand.getApp();
        final C2752uB internalOptions = relocatorCommand.getInternalOptions();
        final ExecutorService executorServiceA = C1086ah0.a(internalOptions);
        AbstractC2632so.a(relocatorCommand.getReporter(), new AbstractC2632so.a() { // from class: wbc
            @Override // com.android.tools.r8.internal.AbstractC2632so.a
            public final void run() {
                Relocator.a(relocatorCommand, executorServiceA, app, internalOptions);
            }
        });
    }

    public static void run(final RelocatorCommand relocatorCommand, final ExecutorService executorService) throws CompilationFailedException {
        final i app = relocatorCommand.getApp();
        final C2752uB internalOptions = relocatorCommand.getInternalOptions();
        AbstractC2632so.a(relocatorCommand.getReporter(), new AbstractC2632so.a() { // from class: vbc
            @Override // com.android.tools.r8.internal.AbstractC2632so.a
            public final void run() throws IOException {
                Relocator.c(relocatorCommand, executorService, app, internalOptions);
            }
        });
    }
}
