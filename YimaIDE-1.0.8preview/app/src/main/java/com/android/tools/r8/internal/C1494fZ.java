package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.C1494fZ;
import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.retrace.InvalidMappingFileException;
import com.android.tools.r8.retrace.MappingSupplierBase;
import com.android.tools.r8.retrace.ProguardMapProducer;
import com.android.tools.r8.retrace.ProguardMappingSupplier;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.ulg;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1494fZ extends ProguardMappingSupplier {
    public static final /* synthetic */ boolean g = true;
    public ProguardMapProducer a;
    public final boolean b;
    public final boolean c;
    public C3313b d;
    public final HashSet e = new HashSet();
    public final HashSet f = new HashSet();

    public C1494fZ(ProguardMapProducer proguardMapProducer, boolean z, boolean z2) {
        this.a = proguardMapProducer;
        this.b = z;
        this.c = z2;
    }

    public final C1494fZ a(ClassReference classReference) {
        String typeName = classReference.getTypeName();
        if (!this.c && !this.f.contains(typeName)) {
            this.e.add(classReference.getTypeName());
        }
        return this;
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier
    public final C1667ha0 createRetracer(DiagnosticsHandler diagnosticsHandler) {
        ulg ulgVar;
        MapVersion mapVersionS;
        DiagnosticsHandler diagnosticsHandler2;
        if (this.a == null) {
            if (g || this.d != null) {
                return C1667ha0.a(TM.a(this.d), diagnosticsHandler);
            }
            x1f.a();
            return null;
        }
        if (this.d == null || !this.e.isEmpty()) {
            try {
                if (this.c) {
                    ulgVar = null;
                } else {
                    HashSet hashSet = this.e;
                    Objects.requireNonNull(hashSet);
                    ulgVar = new ulg(hashSet);
                }
                boolean z = this.d == null;
                cZ cZVar = this.a.isFileBacked() ? new cZ(this.a.getPath(), ulgVar, z) : new C1156bZ(this.a.get(), ulgVar, z);
                C3313b c3313b = this.d;
                if (c3313b == null) {
                    mapVersionS = MapVersion.MAP_VERSION_NONE;
                } else {
                    com.android.tools.r8.naming.mappinginformation.b bVarC = c3313b.c();
                    mapVersionS = bVarC == null ? MapVersion.MAP_VERSION_UNKNOWN : bVarC.s();
                }
                diagnosticsHandler2 = diagnosticsHandler;
                this.d = C3313b.a(cZVar, mapVersionS, diagnosticsHandler2, true, this.b, new Consumer() { // from class: pvg
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        C1494fZ.a((C3313b.a) obj);
                    }
                }).a(this.d);
                this.f.addAll(this.e);
                this.e.clear();
            } catch (Exception e) {
                throw new InvalidMappingFileException(e);
            }
        } else {
            diagnosticsHandler2 = diagnosticsHandler;
        }
        if (this.c) {
            this.a = null;
        }
        return C1667ha0.a(TM.a(this.d), diagnosticsHandler2);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.Z
    public final Set getMapVersions(DiagnosticsHandler diagnosticsHandler) {
        if (this.d == null) {
            createRetracer(diagnosticsHandler);
        }
        if (g || this.d != null) {
            return this.d.d();
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.retrace.MappingSupplierBase
    /* JADX INFO: renamed from: registerClassUse */
    public final /* bridge */ /* synthetic */ MappingSupplierBase mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference) {
        return a(classReference);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.retrace.MappingSupplierBase
    public final MappingSupplierBase registerFieldUse(DiagnosticsHandler diagnosticsHandler, FieldReference fieldReference) {
        return a(fieldReference.getHolderClass());
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.retrace.MappingSupplierBase
    public final MappingSupplierBase registerMethodUse(DiagnosticsHandler diagnosticsHandler, MethodReference methodReference) {
        return a(methodReference.getHolderClass());
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.Z
    public final void verifyMappingFileHash(DiagnosticsHandler diagnosticsHandler) {
        try {
            InputStream inputStream = this.a.get();
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                StringBuilder sb = new StringBuilder();
                char[] cArr = new char[Fcntl.S_ISUID];
                while (true) {
                    int i = inputStreamReader.read(cArr);
                    if (i == -1) {
                        break;
                    } else {
                        sb.append(cArr, 0, i);
                    }
                }
                com.android.tools.r8.naming.y0.a aVarA = com.android.tools.r8.naming.y0.a(sb.toString());
                if (aVarA.a) {
                    boolean z = com.android.tools.r8.naming.y0.a.c;
                    if (!z && aVarA.b == null) {
                        throw new AssertionError();
                    }
                    diagnosticsHandler.error(new StringDiagnostic(aVarA.b));
                    if (!z && aVarA.b == null) {
                        throw new AssertionError();
                    }
                    throw new RuntimeException(aVarA.b);
                }
                if (!aVarA.b()) {
                    if (!com.android.tools.r8.naming.y0.a.c && aVarA.b == null) {
                        throw new AssertionError();
                    }
                    diagnosticsHandler.warning(new StringDiagnostic(aVarA.b));
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            diagnosticsHandler.error(new ExceptionDiagnostic(e));
            rc6.a(e);
        }
    }

    public static void a(C3313b.a aVar) {
        aVar.a = true;
    }
}
