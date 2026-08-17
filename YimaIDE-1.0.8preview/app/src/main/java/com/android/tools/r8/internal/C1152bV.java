package com.android.tools.r8.internal;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1152bV implements ProgramResource {
    public static final /* synthetic */ boolean e = true;
    public final Origin a;
    public final ProgramResource.Kind b;
    public byte[] c;
    public final Set d;

    public C1152bV(Origin origin, ProgramResource.Kind kind, byte[] bArr, Set set) {
        if (!e && bArr == null) {
            x1f.a();
            throw null;
        }
        this.a = origin;
        this.b = kind;
        this.c = bArr;
        this.d = set;
    }

    public static C1152bV a(ProgramResource.Kind kind, ArchiveEntryOrigin archiveEntryOrigin, byte[] bArr, Set set) {
        return new C1152bV(archiveEntryOrigin, kind, bArr, set);
    }

    @Override // com.android.tools.r8.ProgramResource
    public final InputStream getByteStream() {
        return new ByteArrayInputStream(getBytes());
    }

    @Override // com.android.tools.r8.ProgramResource
    public final byte[] getBytes() {
        if (!e && this.c == null) {
            x1f.a();
            return null;
        }
        byte[] bArr = this.c;
        this.c = null;
        return bArr;
    }

    @Override // com.android.tools.r8.ProgramResource
    public final Set getClassDescriptors() {
        return this.d;
    }

    @Override // com.android.tools.r8.ProgramResource
    public final ProgramResource.Kind getKind() {
        return this.b;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.a;
    }
}
