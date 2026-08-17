package com.android.tools.r8.internal;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.internal.N9;
import com.android.tools.r8.utils.ArchiveResourceProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N9 {
    public static final /* synthetic */ boolean c = true;
    public HashMap a = null;
    public final com.android.tools.r8.utils.i b;

    public N9(com.android.tools.r8.utils.i iVar) {
        this.b = iVar;
    }

    public final void a() throws ResourceException {
        final L9 l9 = new L9(this);
        for (ProgramResourceProvider programResourceProvider : this.b.i()) {
            if (programResourceProvider instanceof ArchiveResourceProvider) {
                ((ArchiveResourceProvider) programResourceProvider).accept(new Consumer() { // from class: cba
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        N9.a(l9, (ProgramResource) obj);
                    }
                });
            } else {
                for (ProgramResource programResource : programResourceProvider.getProgramResources()) {
                    if (programResource.getKind() == ProgramResource.Kind.CF) {
                        try {
                            new C1586gd(C3043xe0.a(programResource.getByteStream())).a(l9, new H4[0], 4);
                        } catch (IOException unused) {
                        }
                    }
                }
            }
        }
    }

    public static void a(L9 l9, ProgramResource programResource) {
        if (programResource.getKind() != ProgramResource.Kind.CF) {
            return;
        }
        try {
            new C1586gd(C3043xe0.a(programResource.getByteStream())).a(l9, new H4[0], 4);
        } catch (ResourceException | IOException unused) {
        }
    }
}
