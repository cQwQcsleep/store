package com.android.tools.r8.internal;

import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.ExtractMarker;
import com.android.tools.r8.ExtractMarkerCommand;
import com.android.tools.r8.origin.Origin;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Oo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0726Oo {
    public static Collection<com.android.tools.r8.dex.W> a(Path path) throws CompilationFailedException {
        ExtractMarkerCommand.Builder builderAddProgramFiles = ExtractMarkerCommand.builder().addProgramFiles(path);
        ArrayList arrayList = new ArrayList();
        ExtractMarker.run(builderAddProgramFiles.setMarkerInfoConsumer(new C2080mN(arrayList)).build());
        return arrayList;
    }

    public static Collection<com.android.tools.r8.dex.W> b(byte[] bArr) throws CompilationFailedException {
        ExtractMarkerCommand.Builder builderAddDexProgramData = ExtractMarkerCommand.builder().addDexProgramData(bArr, Origin.unknown());
        ArrayList arrayList = new ArrayList();
        ExtractMarker.run(builderAddDexProgramData.setMarkerInfoConsumer(new C2080mN(arrayList)).build());
        return arrayList;
    }

    public static Collection<com.android.tools.r8.dex.W> a(byte[] bArr) throws CompilationFailedException {
        ExtractMarkerCommand.Builder builderAddClassProgramData = ExtractMarkerCommand.builder().addClassProgramData(bArr, Origin.unknown());
        ArrayList arrayList = new ArrayList();
        ExtractMarker.run(builderAddClassProgramData.setMarkerInfoConsumer(new C2080mN(arrayList)).build());
        return arrayList;
    }
}
