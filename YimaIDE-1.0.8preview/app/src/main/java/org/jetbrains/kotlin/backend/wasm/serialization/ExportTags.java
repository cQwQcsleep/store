package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;
import org.snakeyaml.engine.v2.tokens.DirectiveToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/ExportTags;", "", "<init>", "()V", "FUNCTION", "Lkotlin/UInt;", "I", "TABLE", "MEMORY", "GLOBAL", DirectiveToken.TAG_DIRECTIVE, "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ExportTags {
    public static final int FUNCTION = 0;
    public static final int GLOBAL = 3;
    public static final ExportTags INSTANCE = new ExportTags();
    public static final int MEMORY = 2;
    public static final int TABLE = 1;
    public static final int TAG = 4;

    private ExportTags() {
    }
}
