package org.jetbrains.kotlin.ir.types;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "", "<init>", "(Ljava/lang/String;I)V", "MARKED_NULLABLE", "NOT_SPECIFIED", "DEFINITELY_NOT_NULL", "Companion", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum SimpleTypeNullability {
    MARKED_NULLABLE,
    NOT_SPECIFIED,
    DEFINITELY_NOT_NULL;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<SimpleTypeNullability> getEntries() {
        return $ENTRIES;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability$Companion;", "", "<init>", "()V", "fromHasQuestionMark", "Lorg/jetbrains/kotlin/ir/types/SimpleTypeNullability;", "hasQuestionMark", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SimpleTypeNullability fromHasQuestionMark(boolean hasQuestionMark) {
            return hasQuestionMark ? SimpleTypeNullability.MARKED_NULLABLE : SimpleTypeNullability.NOT_SPECIFIED;
        }

        private Companion() {
        }
    }
}
