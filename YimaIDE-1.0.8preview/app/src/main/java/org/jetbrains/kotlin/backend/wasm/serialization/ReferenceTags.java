package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/ReferenceTags;", "", "<init>", "()V", "IN_PLACE", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ReferenceTags {
    public static final ReferenceTags INSTANCE = new ReferenceTags();
    public static final int IN_PLACE = -1;

    private ReferenceTags() {
    }
}
