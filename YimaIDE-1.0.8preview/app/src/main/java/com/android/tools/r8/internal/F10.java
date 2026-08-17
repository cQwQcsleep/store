package com.android.tools.r8.internal;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ResourceShrinker;
import com.android.tools.r8.origin.PathOrigin;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class F10 {
    public static final void a(byte[] bArr, Path path, C0620Kl c0620Kl) throws ExecutionException, IOException {
        KB.c(bArr, "bytes");
        ResourceShrinker.run(new ResourceShrinker.Builder().addProgramResourceProvider(new E10(ProgramResource.fromBytes(new PathOrigin(path), ProgramResource.Kind.DEX, bArr, null))).build(), new C2051m2(c0620Kl));
    }
}
