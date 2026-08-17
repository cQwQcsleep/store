package com.intellij.codeInsight.multiverse;

import com.intellij.psi.impl.source.tree.ChildRole;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/intellij/codeInsight/multiverse/MultiverseTestEnabler;", "", "<init>", "()V", "value", "Ljava/util/concurrent/atomic/AtomicBoolean;", "enableSharedSourcesForTheNextProject", "", "getValueAndErase", "", "getValueAndErase$intellij_platform_core_impl", "intellij.platform.core.impl"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public final class MultiverseTestEnabler {
    public static final MultiverseTestEnabler INSTANCE = new MultiverseTestEnabler();
    private static final AtomicBoolean value = new AtomicBoolean();

    private MultiverseTestEnabler() {
    }

    public final boolean getValueAndErase$intellij_platform_core_impl() {
        return value.getAndSet(false);
    }
}
