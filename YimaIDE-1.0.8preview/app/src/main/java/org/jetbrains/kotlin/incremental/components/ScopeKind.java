package org.jetbrains.kotlin.incremental.components;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/ScopeKind;", "", "<init>", "(Ljava/lang/String;I)V", "PACKAGE", "CLASSIFIER", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ScopeKind {
    PACKAGE,
    CLASSIFIER;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ScopeKind> getEntries() {
        return $ENTRIES;
    }
}
