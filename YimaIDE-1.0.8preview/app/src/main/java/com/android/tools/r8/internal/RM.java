package com.android.tools.r8.internal;

import com.android.tools.r8.dex.C0155s;
import com.android.tools.r8.naming.MapVersion;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RM implements QM {
    public final MapVersion b;
    public final C1824jO c;
    public final C1397eO d;

    public RM(MapVersion mapVersion, C1824jO c1824jO, C1397eO c1397eO) {
        this.b = mapVersion;
        this.c = c1824jO;
        this.d = c1397eO;
    }

    public static RM a(C0155s c0155s) {
        MapVersion mapVersionFromName = MapVersion.fromName(c0155s.j());
        byte[] bArr = new byte[c0155s.a.getInt()];
        c0155s.a(bArr);
        C1740iO c1740iO = new C1740iO(bArr);
        byte[] bArr2 = new byte[c0155s.a.getInt()];
        c0155s.a(bArr2);
        return new RM(mapVersionFromName, c1740iO, new C1312dO(bArr2));
    }

    @Override // com.android.tools.r8.internal.QM
    public final MapVersion b() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.QM
    public final boolean c() {
        return true;
    }

    @Override // com.android.tools.r8.internal.QM
    public final boolean d() {
        return true;
    }

    @Override // com.android.tools.r8.internal.QM
    public final C1397eO e() {
        return this.d;
    }

    @Override // com.android.tools.r8.retrace.MappingPartitionMetadata
    public final byte[] getBytes() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.write(QM.a);
            dataOutputStream.writeShort(OM.e.b);
            dataOutputStream.writeUTF(this.b.getName());
            AbstractC1583gb0.a(dataOutputStream, Wf0.a(";", this.c.a));
            this.d.a(dataOutputStream);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            rc6.a(e);
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.QM
    public final Collection a() {
        return this.c.a();
    }
}
