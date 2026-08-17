package com.android.tools.r8;

import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.ExtractMarker;
import com.android.tools.r8.dex.C0011c;
import com.android.tools.r8.dex.W;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.C1908kN;
import com.android.tools.r8.internal.C1994lN;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.InterfaceC2718to;
import com.android.tools.r8.origin.Origin;
import defpackage.ik4;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ExtractMarker {
    private static void a(MarkerInfoConsumer markerInfoConsumer, C2742u50 c2742u50, Origin origin, final com.android.tools.r8.utils.i.a aVar) {
        final ArrayList arrayList = new ArrayList();
        try {
            AbstractC2632so.a(c2742u50, new AbstractC2632so.a() { // from class: hk4
                @Override // com.android.tools.r8.internal.AbstractC2632so.a
                public final void run() {
                    ExtractMarker.a(arrayList, aVar);
                }
            });
            arrayList.sort(Comparator.comparing(new ik4()));
            markerInfoConsumer.acceptMarkerInfo(new C1908kN(C2847vL.a((Collection) arrayList, new Function() { // from class: jk4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new C1994lN((W) obj);
                }
            }), origin));
        } catch (CompilationFailedException e) {
            throw new E(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(MarkerInfoConsumer markerInfoConsumer, C2742u50 c2742u50, byte[] bArr, Origin origin) {
        a(markerInfoConsumer, c2742u50, origin, com.android.tools.r8.utils.i.b().a(bArr, origin));
    }

    public static void main(final String[] strArr) throws Exception {
        AbstractC2632so.a(new InterfaceC2718to() { // from class: kk4
            @Override // com.android.tools.r8.internal.InterfaceC2718to
            public final void run() throws CompilationFailedException {
                ExtractMarker.a(strArr);
            }
        });
    }

    public static void run(ExtractMarkerCommand extractMarkerCommand) throws CompilationFailedException {
        final MarkerInfoConsumer markerInfoConsumer = extractMarkerCommand.getMarkerInfoConsumer();
        final C2742u50 c2742u50 = new C2742u50(extractMarkerCommand.getDiagnosticsHandler());
        try {
            extractMarkerCommand.forEachEntry(new BiConsumer() { // from class: ek4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ExtractMarker.a(markerInfoConsumer, c2742u50, (Path) obj, (Origin) obj2);
                }
            }, new BiConsumer() { // from class: fk4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ExtractMarker.a(markerInfoConsumer, c2742u50, (byte[]) obj, (Origin) obj2);
                }
            }, new BiConsumer() { // from class: gk4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ExtractMarker.b(markerInfoConsumer, c2742u50, (byte[]) obj, (Origin) obj2);
                }
            });
        } catch (E e) {
            throw e.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List list, com.android.tools.r8.utils.i.a aVar) {
        com.android.tools.r8.utils.i iVarA = aVar.a();
        C2752uB c2752uB = new C2752uB();
        c2752uB.D1 = true;
        c2752uB.c(EnumC3077y2.D);
        list.addAll(new C0011c(iVarA, c2752uB, new Ch0("ExtractMarker", false)).a().e.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(MarkerInfoConsumer markerInfoConsumer, C2742u50 c2742u50, Path path, Origin origin) {
        a(markerInfoConsumer, c2742u50, origin, com.android.tools.r8.utils.i.b().c(path));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(MarkerInfoConsumer markerInfoConsumer, C2742u50 c2742u50, byte[] bArr, Origin origin) {
        a(markerInfoConsumer, c2742u50, origin, com.android.tools.r8.utils.i.b().b(bArr, origin));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String[] strArr) throws CompilationFailedException {
        PrintStream printStream = System.out;
        ExtractMarkerCommand extractMarkerCommandBuild = ExtractMarkerCommand.parse(strArr).setMarkerInfoConsumer(new F(printStream)).build();
        if (extractMarkerCommandBuild.isPrintHelp()) {
            printStream.println(ExtractMarkerCommand.g);
        } else {
            run(extractMarkerCommandBuild);
        }
    }
}
