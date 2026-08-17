package com.intellij.openapi.client;

import com.intellij.psi.impl.source.tree.ChildRole;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/intellij/openapi/client/ClientKind;", "", "<init>", "(Ljava/lang/String;I)V", "LOCAL", "FRONTEND", "CONTROLLER", "GUEST", "OWNER", "REMOTE", "ALL", "intellij.platform.extensions"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public enum ClientKind {
    LOCAL,
    FRONTEND,
    CONTROLLER,
    GUEST,
    OWNER,
    REMOTE,
    ALL;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
}
