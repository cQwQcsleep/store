package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/resolve/TopDownAnalysisMode;", "", "isLocalDeclarations", "", "<init>", "(Ljava/lang/String;IZ)V", "()Z", "LocalDeclarations", "TopLevelDeclarations", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum TopDownAnalysisMode {
    LocalDeclarations(true),
    TopLevelDeclarations(false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean isLocalDeclarations;

    TopDownAnalysisMode(boolean z) {
        this.isLocalDeclarations = z;
    }

    public static EnumEntries<TopDownAnalysisMode> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: isLocalDeclarations, reason: from getter */
    public final boolean getIsLocalDeclarations() {
        return this.isLocalDeclarations;
    }
}
