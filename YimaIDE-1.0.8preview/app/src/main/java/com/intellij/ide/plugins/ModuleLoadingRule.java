package com.intellij.ide.plugins;

import com.intellij.psi.impl.source.tree.ChildRole;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/intellij/ide/plugins/ModuleLoadingRule;", "", "required", "", "<init>", "(Ljava/lang/String;IZ)V", "getRequired", "()Z", "REQUIRED", "EMBEDDED", "OPTIONAL", "ON_DEMAND", "intellij.platform.core.impl"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public enum ModuleLoadingRule {
    REQUIRED(true),
    EMBEDDED(true),
    OPTIONAL(false),
    ON_DEMAND(false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean required;

    ModuleLoadingRule(boolean z) {
        this.required = z;
    }
}
