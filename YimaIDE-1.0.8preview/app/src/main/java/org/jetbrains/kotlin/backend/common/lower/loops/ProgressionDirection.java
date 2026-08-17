package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\u0000H&j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/ProgressionDirection;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "DECREASING", "INCREASING", "UNKNOWN", "asReversed", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ProgressionDirection {
    DECREASING { // from class: org.jetbrains.kotlin.backend.common.lower.loops.ProgressionDirection.DECREASING
        @Override // org.jetbrains.kotlin.backend.common.lower.loops.ProgressionDirection
        public ProgressionDirection asReversed() {
            return ProgressionDirection.INCREASING;
        }
    },
    INCREASING { // from class: org.jetbrains.kotlin.backend.common.lower.loops.ProgressionDirection.INCREASING
        @Override // org.jetbrains.kotlin.backend.common.lower.loops.ProgressionDirection
        public ProgressionDirection asReversed() {
            return ProgressionDirection.DECREASING;
        }
    },
    UNKNOWN { // from class: org.jetbrains.kotlin.backend.common.lower.loops.ProgressionDirection.UNKNOWN
        @Override // org.jetbrains.kotlin.backend.common.lower.loops.ProgressionDirection
        public ProgressionDirection asReversed() {
            return this;
        }
    };

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* synthetic */ ProgressionDirection(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static EnumEntries<ProgressionDirection> getEntries() {
        return $ENTRIES;
    }

    public abstract ProgressionDirection asReversed();
}
