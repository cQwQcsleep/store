package org.jetbrains.kotlin.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0016\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "", "<init>", "(Ljava/lang/String;I)V", "K1_RESOLVED_TO_SAM_WITH_VARARG", "HIDDEN", "K2_UNSUPPORTED", "INAPPLICABLE_WRONG_RECEIVER", "INAPPLICABLE_ARGUMENTS_MAPPING_ERROR", "INAPPLICABLE", "K2_NO_COMPANION_OBJECT", "K1_IMPOSSIBLE_TO_GENERATE", "K1_RUNTIME_ERROR", "K2_VISIBILITY_ERROR", "UNSAFE_CALL", "UNSTABLE_SMARTCAST", "CONVENTION_ERROR", "RESOLVED_LOW_PRIORITY", "K2_NOT_FUNCTION_AS_OPERATOR", "RESOLVED_NEED_PRESERVE_COMPATIBILITY", "K2_SYNTHETIC_RESOLVED", "RESOLVED_WITH_ERROR", "RESOLVED", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum CandidateApplicability {
    K1_RESOLVED_TO_SAM_WITH_VARARG,
    HIDDEN,
    K2_UNSUPPORTED,
    INAPPLICABLE_WRONG_RECEIVER,
    INAPPLICABLE_ARGUMENTS_MAPPING_ERROR,
    INAPPLICABLE,
    K2_NO_COMPANION_OBJECT,
    K1_IMPOSSIBLE_TO_GENERATE,
    K1_RUNTIME_ERROR,
    K2_VISIBILITY_ERROR,
    UNSAFE_CALL,
    UNSTABLE_SMARTCAST,
    CONVENTION_ERROR,
    RESOLVED_LOW_PRIORITY,
    K2_NOT_FUNCTION_AS_OPERATOR,
    RESOLVED_NEED_PRESERVE_COMPATIBILITY,
    K2_SYNTHETIC_RESOLVED,
    RESOLVED_WITH_ERROR,
    RESOLVED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<CandidateApplicability> getEntries() {
        return $ENTRIES;
    }
}
