package com.android.tools.r8.utils;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class p extends ClassFileConsumer.ForwardingConsumer {
    public static final /* synthetic */ boolean f = true;
    public ArrayList c;
    public final /* synthetic */ ClassFileConsumer d;
    public final /* synthetic */ r e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(r rVar, ClassFileConsumer classFileConsumer, ClassFileConsumer classFileConsumer2) {
        super(classFileConsumer);
        this.e = rVar;
        this.d = classFileConsumer2;
        this.c = new ArrayList();
    }

    public final /* synthetic */ void a(q qVar) {
        this.e.a.a(qVar.b, Origin.unknown(), qVar.a);
    }

    @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ClassFileConsumer
    public final void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
        super.accept(byteDataView, str, diagnosticsHandler);
        byte[] bArrCopyByteData = byteDataView.copyByteData();
        synchronized (this) {
            this.c.add(new q(Collections.singleton(str), bArrCopyByteData));
        }
    }

    @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
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
            this.c.forEach(new Consumer() { // from class: nzh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((q) obj);
                }
            });
            this.c = null;
        }
    }

    @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
    public final DataResourceConsumer getDataResourceConsumer() {
        ClassFileConsumer classFileConsumer = this.d;
        return new o(this, classFileConsumer != null ? classFileConsumer.getDataResourceConsumer() : null);
    }
}
