package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"render", "", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "renderer", "Lorg/jetbrains/kotlin/ir/util/IdSignatureRenderer;", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IdSignatureRendererKt {
    public static final String render(IdSignature idSignature, IdSignatureRenderer idSignatureRenderer) {
        idSignature.getClass();
        idSignatureRenderer.getClass();
        return idSignatureRenderer.render(idSignature);
    }

    public static /* synthetic */ String render$default(IdSignature idSignature, IdSignatureRenderer idSignatureRenderer, int i, Object obj) {
        if ((i & 1) != 0) {
            idSignatureRenderer = IdSignatureRenderer.INSTANCE.getDEFAULT();
        }
        return render(idSignature, idSignatureRenderer);
    }
}
