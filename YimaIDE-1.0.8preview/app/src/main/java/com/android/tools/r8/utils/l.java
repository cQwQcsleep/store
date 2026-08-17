package com.android.tools.r8.utils;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DexIndexedConsumer;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.internal.C0944Wy;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.q;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l extends DexIndexedConsumer.ForwardingConsumer {
    public static final /* synthetic */ boolean f = true;
    public C0944Wy c;
    public final /* synthetic */ DexIndexedConsumer d;
    public final /* synthetic */ r e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(r rVar, DexIndexedConsumer dexIndexedConsumer, DexIndexedConsumer dexIndexedConsumer2) {
        super(dexIndexedConsumer);
        this.e = rVar;
        this.d = dexIndexedConsumer2;
        this.c = new C0944Wy();
    }

    public final void a(Integer num, q qVar) {
        i.a aVar = this.e.a;
        byte[] bArr = qVar.b;
        Set set = qVar.a;
        aVar.getClass();
        aVar.a(ProgramResource.fromBytes(Origin.unknown(), ProgramResource.Kind.DEX, bArr, set));
    }

    @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.DexIndexedConsumer
    public final void accept(int i, ByteDataView byteDataView, Set set, DiagnosticsHandler diagnosticsHandler) {
        super.accept(i, byteDataView, (Set<String>) set, diagnosticsHandler);
        byte[] bArrCopyByteData = byteDataView.copyByteData();
        synchronized (this) {
            this.c.a(i, new q(set, bArrCopyByteData));
        }
    }

    @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        super.finished(diagnosticsHandler);
        r rVar = this.e;
        if (rVar.b) {
            if (f) {
                return;
            }
            getDataResourceConsumer();
        } else {
            rVar.b = true;
            this.c.forEach(new BiConsumer() { // from class: lih
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a((Integer) obj, (q) obj2);
                }
            });
            this.c = null;
        }
    }

    @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
    public final DataResourceConsumer getDataResourceConsumer() {
        DexIndexedConsumer dexIndexedConsumer = this.d;
        return new k(this, dexIndexedConsumer != null ? dexIndexedConsumer.getDataResourceConsumer() : null);
    }
}
