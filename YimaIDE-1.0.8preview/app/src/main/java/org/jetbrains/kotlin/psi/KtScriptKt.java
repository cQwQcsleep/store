package org.jetbrains.kotlin.psi;

import com.intellij.openapi.util.Key;
import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\"!\u0010\u0000\u001a\u0015\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001¢\u0006\u0002\b\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"REPL_SNIPPET_KEY", "Lcom/intellij/openapi/util/Key;", "", JvmProtoBufUtil.PLATFORM_TYPE_ID, "Lorg/jetbrains/annotations/NotNull;", "org.jetbrains.kotlin:psi-api"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KtScriptKt {
    private static final Key<Boolean> REPL_SNIPPET_KEY;

    static {
        Key<Boolean> keyCreate = Key.create("REPL_SNIPPET");
        keyCreate.getClass();
        REPL_SNIPPET_KEY = keyCreate;
    }
}
