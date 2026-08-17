package com.android.tools.r8.tracereferences;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.Cc0;
import com.android.tools.r8.origin.PathOrigin;
import java.nio.file.Path;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e implements ProgramResourceProvider {
    public final /* synthetic */ Path a;
    public final /* synthetic */ byte[] b;
    public final /* synthetic */ String c;

    public e(Path path, byte[] bArr, String str) {
        this.a = path;
        this.b = bArr;
        this.c = str;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        PathOrigin pathOrigin = new PathOrigin(this.a);
        ProgramResource.Kind kind = ProgramResource.Kind.CF;
        byte[] bArr = this.b;
        String str = this.c;
        int i = AbstractC2554rv.c;
        ProgramResource programResourceFromBytes = ProgramResource.fromBytes(pathOrigin, kind, bArr, new Cc0(str));
        int i2 = AbstractC0551Hu.c;
        return new Bc0(programResourceFromBytes);
    }
}
