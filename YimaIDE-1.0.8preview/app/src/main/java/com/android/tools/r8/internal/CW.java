package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.dex.C0155s;
import com.android.tools.r8.internal.CW;
import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.retrace.FinishedPartitionMappingCallback;
import com.android.tools.r8.retrace.InvalidMappingFileException;
import com.android.tools.r8.retrace.MappingPartitionFromKeySupplier;
import com.android.tools.r8.retrace.PartitionMappingSupplier;
import com.android.tools.r8.retrace.PrepareMappingPartitionsCallback;
import com.android.tools.r8.retrace.RegisterMappingPartitionCallback;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class CW<T extends CW<T>> implements com.android.tools.r8.I {
    private final RegisterMappingPartitionCallback a;
    private final PrepareMappingPartitionsCallback b;
    private final FinishedPartitionMappingCallback c;
    private final boolean d;
    private final byte[] e;
    private final MapVersion f;
    private C3313b g;
    private final LinkedHashSet h = new LinkedHashSet();
    private final HashSet i = new HashSet();
    private final C1975l7 j = new C1975l7();
    private final C1975l7 k = new C1975l7();

    public CW(RegisterMappingPartitionCallback registerMappingPartitionCallback, PrepareMappingPartitionsCallback prepareMappingPartitionsCallback, FinishedPartitionMappingCallback finishedPartitionMappingCallback, boolean z, byte[] bArr, MapVersion mapVersion) {
        this.a = registerMappingPartitionCallback;
        this.b = prepareMappingPartitionsCallback;
        this.c = finishedPartitionMappingCallback;
        this.d = z;
        this.e = bArr;
        this.f = mapVersion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(Set set, String str) {
        return set == null || set.contains(C0929Wj.r(str));
    }

    public C1667ha0 createRetracerFromPartitionSupplier(DiagnosticsHandler diagnosticsHandler, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
        if (!this.h.isEmpty()) {
            this.b.prepare();
        }
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            try {
                byte[] bArr = mappingPartitionFromKeySupplier.get((String) it.next());
                if (bArr != null) {
                    DiagnosticsHandler diagnosticsHandler2 = diagnosticsHandler;
                    this.g = C3313b.a(new C1156bZ(new ByteArrayInputStream(bArr), MX.b, true), getMetadata(diagnosticsHandler).b(), diagnosticsHandler2, true, this.d, new Consumer() { // from class: g81
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            CW.a((C3313b.a) obj);
                        }
                    }).a(this.g);
                    diagnosticsHandler = diagnosticsHandler2;
                }
            } catch (IOException e) {
                throw new InvalidMappingFileException(e);
            }
        }
        DiagnosticsHandler diagnosticsHandler3 = diagnosticsHandler;
        this.i.addAll(this.h);
        this.h.clear();
        if (this.g == null) {
            this.g = C3313b.a().a();
        }
        return C1667ha0.a(TM.a(this.g), diagnosticsHandler3);
    }

    @Override // com.android.tools.r8.I
    public void finished(DiagnosticsHandler diagnosticsHandler) {
        this.c.finished(diagnosticsHandler);
    }

    public Set<com.android.tools.r8.naming.mappinginformation.b> getMapVersions(DiagnosticsHandler diagnosticsHandler) {
        return Collections.singleton(getMetadata(diagnosticsHandler).b().toMapVersionMappingInformation());
    }

    public QM getMetadata(DiagnosticsHandler diagnosticsHandler) {
        boolean zB = this.j.b();
        C1975l7 c1975l7 = this.j;
        if (zB) {
            return (QM) c1975l7.a();
        }
        synchronized (c1975l7) {
            try {
                if (this.j.b()) {
                    return (QM) this.j.a();
                }
                byte[] bArr = this.e;
                QM qmA = QM.a(bArr == null ? null : C0155s.b(bArr), this.f, diagnosticsHandler);
                this.j.a(qmA);
                return qmA;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public PartitionMappingSupplier getPartitionMappingSupplier() {
        return null;
    }

    /* JADX INFO: renamed from: registerClassUse */
    public CW mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference) {
        Set setA;
        boolean zTest;
        String typeName = classReference.getTypeName();
        boolean zB = this.k.b();
        C1975l7 c1975l7 = this.k;
        if (zB) {
            zTest = ((Predicate) c1975l7.a()).test(typeName);
        } else {
            synchronized (c1975l7) {
                try {
                    if (this.k.b()) {
                        zTest = ((Predicate) this.k.a()).test(typeName);
                    } else {
                        QM metadata = getMetadata(diagnosticsHandler);
                        if (metadata != null && metadata.c()) {
                            C1397eO c1397eOE = metadata.e();
                            setA = !c1397eOE.c() ? null : c1397eOE.a();
                        }
                        Predicate predicateA = a(setA);
                        this.k.a(predicateA);
                        zTest = predicateA.test(typeName);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zTest ? registerKeyUse(typeName) : self();
    }

    public T registerKeyUse(String str) {
        if (!this.i.contains(str) && this.h.add(str)) {
            this.a.register(str);
        }
        return (T) self();
    }

    public abstract CW self();

    public void verifyMappingFileHash(DiagnosticsHandler diagnosticsHandler) {
        diagnosticsHandler.error(new StringDiagnostic("Cannot verify map file hash for partitions"));
        throw new RuntimeException("Cannot verify map file hash for partitions");
    }

    private static Predicate a(final Set set) {
        return new Predicate() { // from class: h81
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CW.a(set, (String) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(C3313b.a aVar) {
        aVar.a = true;
    }
}
