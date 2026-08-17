package com.android.tools.r8.utils;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DexFilePerClassFileConsumer;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.q;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class n extends DexFilePerClassFileConsumer.ForwardingConsumer {
    public static final /* synthetic */ boolean f = true;
    public TreeMap c;
    public final /* synthetic */ DexFilePerClassFileConsumer d;
    public final /* synthetic */ r e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(r rVar, DexFilePerClassFileConsumer dexFilePerClassFileConsumer, DexFilePerClassFileConsumer dexFilePerClassFileConsumer2) {
        super(dexFilePerClassFileConsumer);
        this.e = rVar;
        this.d = dexFilePerClassFileConsumer2;
        this.c = new TreeMap();
    }

    public final void a(String str, q qVar) {
        i.a aVar = this.e.a;
        byte[] bArr = qVar.b;
        Set set = qVar.a;
        aVar.getClass();
        ProgramResource programResourceFromBytes = ProgramResource.fromBytes(Origin.unknown(), ProgramResource.Kind.DEX, bArr, set);
        aVar.b.add(programResourceFromBytes);
        aVar.d.put(programResourceFromBytes, str);
    }

    @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.DexFilePerClassFileConsumer
    public final void accept(String str, ByteDataView byteDataView, Set set, DiagnosticsHandler diagnosticsHandler) {
        super.accept(str, byteDataView, (Set<String>) set, diagnosticsHandler);
        byte[] bArrCopyByteData = byteDataView.copyByteData();
        synchronized (this) {
            this.c.put(str, new q(set, bArrCopyByteData));
        }
    }

    @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
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
            this.c.forEach(new BiConsumer() { // from class: gnh
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a((String) obj, (q) obj2);
                }
            });
            this.c = null;
        }
    }

    @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
    public final DataResourceConsumer getDataResourceConsumer() {
        DexFilePerClassFileConsumer dexFilePerClassFileConsumer = this.d;
        return new m(this, dexFilePerClassFileConsumer != null ? dexFilePerClassFileConsumer.getDataResourceConsumer() : null);
    }
}
