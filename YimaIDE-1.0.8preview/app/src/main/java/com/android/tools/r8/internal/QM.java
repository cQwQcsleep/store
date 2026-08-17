package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.dex.C0155s;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.retrace.MappingPartitionMetadata;
import com.android.tools.r8.retrace.RetracePartitionException;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface QM extends MappingPartitionMetadata {
    public static final byte[] a = {-86, -88};

    static QM a(C0155s c0155s, MapVersion mapVersion, DiagnosticsHandler diagnosticsHandler) {
        if (c0155s == null) {
            return a.a(mapVersion);
        }
        if (c0155s.n() <= 2) {
            RetracePartitionException retracePartitionException = new RetracePartitionException("Unknown map partition strategy for metadata");
            diagnosticsHandler.error(new ExceptionDiagnostic(retracePartitionException));
            throw retracePartitionException;
        }
        int iH = c0155s.h();
        byte[] bArr = a;
        if (iH == MB.a((byte) 0, (byte) 0, bArr[0], bArr[1])) {
            iH = c0155s.g();
        }
        int i = PM.a[OM.b(iH).ordinal()];
        if (i == 1) {
            return a.a(c0155s);
        }
        if (i == 2) {
            return RM.a(c0155s);
        }
        throw new RetracePartitionException("Could not find partition key strategy from serialized key: " + iH);
    }

    MapVersion b();

    default boolean c() {
        return false;
    }

    default boolean d() {
        return false;
    }

    default C1397eO e() {
        return new C1397eO(null, null);
    }

    public static class a implements QM {
        public final MapVersion b;

        public a(MapVersion mapVersion) {
            this.b = mapVersion;
        }

        public static a a(C0155s c0155s) {
            byte[] bArrArray = c0155s.c().array();
            return a(MapVersion.fromName(new String(bArrArray, 2, bArrArray.length - 2)));
        }

        @Override // com.android.tools.r8.internal.QM
        public final MapVersion b() {
            return this.b;
        }

        @Override // com.android.tools.r8.retrace.MappingPartitionMetadata
        public byte[] getBytes() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeBytes(this.b.getName());
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                rc6.a(e);
                return null;
            }
        }

        public static a a(MapVersion mapVersion) {
            return new a(mapVersion);
        }
    }

    default Collection<String> a() {
        return null;
    }
}
