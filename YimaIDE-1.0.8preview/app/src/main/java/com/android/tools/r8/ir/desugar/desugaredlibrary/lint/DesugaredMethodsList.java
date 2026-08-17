package com.android.tools.r8.ir.desugar.desugaredlibrary.lint;

import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.Version;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.InterfaceC2718to;
import com.android.tools.r8.ir.desugar.desugaredlibrary.lint.DesugaredMethodsList;
import com.android.tools.r8.t0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class DesugaredMethodsList extends e {
    private final EnumC3077y2 j;
    private final boolean k;
    private final StringConsumer l;

    public DesugaredMethodsList(int i, boolean z, C2742u50 c2742u50, t0 t0Var, Collection collection, StringConsumer stringConsumer, Collection collection2) {
        super(c2742u50, t0Var, collection, null, collection2);
        this.j = EnumC3077y2.b(i);
        this.k = z;
        this.l = stringConsumer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(DesugaredMethodsListCommand desugaredMethodsListCommand) throws IOException {
        new DesugaredMethodsList(desugaredMethodsListCommand.getMinApi(), desugaredMethodsListCommand.isAndroidPlatformBuild(), desugaredMethodsListCommand.getReporter(), desugaredMethodsListCommand.getDesugarLibrarySpecification(), desugaredMethodsListCommand.getDesugarLibraryImplementation(), desugaredMethodsListCommand.getOutputConsumer(), desugaredMethodsListCommand.getLibrary()).run();
    }

    public static void main(final String[] strArr) {
        AbstractC2632so.a(new InterfaceC2718to() { // from class: fp3
            @Override // com.android.tools.r8.internal.InterfaceC2718to
            public final void run() throws CompilationFailedException {
                DesugaredMethodsList.a(strArr);
            }
        });
    }

    public static void run(final DesugaredMethodsListCommand desugaredMethodsListCommand) throws CompilationFailedException {
        if (desugaredMethodsListCommand.isHelp()) {
            System.out.println(DesugaredMethodsListCommand.getUsageMessage());
            return;
        }
        if (!desugaredMethodsListCommand.isVersion()) {
            AbstractC2632so.b(desugaredMethodsListCommand.getReporter(), new AbstractC2632so.a() { // from class: ep3
                @Override // com.android.tools.r8.internal.AbstractC2632so.a
                public final void run() throws IOException {
                    DesugaredMethodsList.a(desugaredMethodsListCommand);
                }
            });
            return;
        }
        System.out.println("DesugaredMethodsList " + Version.getVersionString());
    }

    @Override // com.android.tools.r8.ir.desugar.desugaredlibrary.lint.e
    public final void a(EnumC3077y2 enumC3077y2, EnumC3077y2 enumC3077y3, ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.l.accept((String) it.next(), this.a.i);
        }
        this.l.finished(this.a.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String[] strArr) throws CompilationFailedException {
        try {
            run(strArr);
        } catch (IOException e) {
            throw new C0613Ke(e.getMessage(), e);
        }
    }

    @Override // com.android.tools.r8.ir.desugar.desugaredlibrary.lint.e
    public EnumC3077y2 run() throws IOException {
        EnumC3077y2 enumC3077y2C = this.b.c();
        a(enumC3077y2C, this.j, new o(this.a, this.f, this.j, this.k, true).b(this.d, this.c));
        return enumC3077y2C;
    }

    public static void run(String[] strArr) throws IOException, CompilationFailedException {
        run(DesugaredMethodsListCommand.parse(strArr));
    }
}
