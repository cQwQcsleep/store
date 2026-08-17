package com.android.tools.r8.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1960l extends E {
    public final ByteBuffer a = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    @Override // com.android.tools.r8.internal.E
    public final InterfaceC0990Ys a(int i, byte[] bArr) {
        DX.a(0, i, bArr.length);
        QN qn = (QN) this;
        qn.b();
        qn.b.update(bArr, 0, i);
        return this;
    }

    public final AbstractC1960l b(int i) {
        try {
            byte[] bArrArray = this.a.array();
            QN qn = (QN) this;
            qn.b();
            qn.b.update(bArrArray, 0, i);
            return this;
        } finally {
            this.a.clear();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(byte b) {
        QN qn = (QN) this;
        qn.b();
        qn.b.update(b);
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(int i) {
        this.a.putInt(i);
        return b(4);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(long j) {
        this.a.putLong(j);
        return b(8);
    }

    @Override // com.android.tools.r8.internal.E, com.android.tools.r8.internal.InterfaceC0990Ys
    public final InterfaceC0990Ys a(byte[] bArr) {
        bArr.getClass();
        int length = bArr.length;
        QN qn = (QN) this;
        qn.b();
        qn.b.update(bArr, 0, length);
        return this;
    }
}
