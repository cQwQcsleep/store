package defpackage;

import it.unimi.dsi.fastutil.bytes.ByteComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class s31 implements ByteComparator, Serializable {
    public final /* synthetic */ ByteComparator b;
    public final /* synthetic */ ByteComparator c;

    public /* synthetic */ s31(ByteComparator byteComparator, ByteComparator byteComparator2) {
        this.b = byteComparator;
        this.c = byteComparator2;
    }

    @Override // it.unimi.dsi.fastutil.bytes.ByteComparator
    public final int compare(byte b, byte b2) {
        return ByteComparator.R(this.b, this.c, b, b2);
    }
}
