package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/types/EmptyIntersectionTypeKind;", "", "description", "", "isDefinitelyEmpty", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getDescription", "()Ljava/lang/String;", "()Z", "MULTIPLE_CLASSES", "FINAL_CLASS_AND_INTERFACE", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum EmptyIntersectionTypeKind {
    MULTIPLE_CLASSES("multiple incompatible classes", true),
    FINAL_CLASS_AND_INTERFACE("final class and interface", false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String description;
    private final boolean isDefinitelyEmpty;

    EmptyIntersectionTypeKind(String str, boolean z) {
        this.description = str;
        this.isDefinitelyEmpty = z;
    }

    public static EnumEntries<EmptyIntersectionTypeKind> getEntries() {
        return $ENTRIES;
    }

    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: isDefinitelyEmpty, reason: from getter */
    public final boolean getIsDefinitelyEmpty() {
        return this.isDefinitelyEmpty;
    }
}
