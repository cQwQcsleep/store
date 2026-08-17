package org.jetbrains.kotlin.resolve.calls.results;

import java.util.EnumSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ResolutionStatus {
    UNKNOWN_STATUS,
    UNSAFE_CALL_ERROR,
    WRONG_NUMBER_OF_TYPE_ARGUMENTS_ERROR,
    UNSTABLE_SMARTCAST_FOR_RECEIVER_ERROR,
    INVISIBLE_MEMBER_ERROR,
    NULLABLE_ARGUMENT_TYPE_MISMATCH,
    OTHER_ERROR,
    ARGUMENTS_MAPPING_ERROR,
    RECEIVER_TYPE_ERROR,
    RECEIVER_PRESENCE_ERROR,
    INCOMPLETE_TYPE_INFERENCE,
    SUCCESS(true);

    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int severityIndex;
    private final boolean success;
    public static final EnumSet<ResolutionStatus>[] SEVERITY_LEVELS = {EnumSet.of(UNSAFE_CALL_ERROR), EnumSet.of(WRONG_NUMBER_OF_TYPE_ARGUMENTS_ERROR), EnumSet.of(UNSTABLE_SMARTCAST_FOR_RECEIVER_ERROR), EnumSet.of(INVISIBLE_MEMBER_ERROR), EnumSet.of(NULLABLE_ARGUMENT_TYPE_MISMATCH), EnumSet.of(OTHER_ERROR), EnumSet.of(ARGUMENTS_MAPPING_ERROR), EnumSet.of(RECEIVER_TYPE_ERROR), EnumSet.of(RECEIVER_PRESENCE_ERROR)};

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/resolve/calls/results/ResolutionStatus", "combine"));
    }

    ResolutionStatus(boolean z) {
        this.severityIndex = -1;
        this.success = z;
    }

    private boolean among(ResolutionStatus resolutionStatus, ResolutionStatus resolutionStatus2) {
        return this == resolutionStatus || this == resolutionStatus2;
    }

    private ResolutionStatus chooseDifferent(ResolutionStatus resolutionStatus, ResolutionStatus resolutionStatus2) {
        return this == resolutionStatus ? resolutionStatus2 : resolutionStatus;
    }

    private int getSeverityIndex() {
        if (this.severityIndex == -1) {
            int i = 0;
            while (true) {
                EnumSet<ResolutionStatus>[] enumSetArr = SEVERITY_LEVELS;
                if (i >= enumSetArr.length) {
                    break;
                }
                if (enumSetArr[i].contains(this)) {
                    this.severityIndex = i;
                    break;
                }
                i++;
            }
        }
        return this.severityIndex;
    }

    public ResolutionStatus combine(ResolutionStatus resolutionStatus) {
        if (this == UNKNOWN_STATUS) {
            if (resolutionStatus == null) {
                $$$reportNull$$$0(0);
            }
            return resolutionStatus;
        }
        ResolutionStatus resolutionStatus2 = SUCCESS;
        if (resolutionStatus2.among(this, resolutionStatus)) {
            ResolutionStatus resolutionStatusChooseDifferent = resolutionStatus2.chooseDifferent(this, resolutionStatus);
            if (resolutionStatusChooseDifferent == null) {
                $$$reportNull$$$0(1);
            }
            return resolutionStatusChooseDifferent;
        }
        ResolutionStatus resolutionStatus3 = INCOMPLETE_TYPE_INFERENCE;
        if (!resolutionStatus3.among(this, resolutionStatus)) {
            return getSeverityIndex() < resolutionStatus.getSeverityIndex() ? resolutionStatus : this;
        }
        ResolutionStatus resolutionStatusChooseDifferent2 = resolutionStatus3.chooseDifferent(this, resolutionStatus);
        if (resolutionStatusChooseDifferent2 == null) {
            $$$reportNull$$$0(2);
        }
        return resolutionStatusChooseDifferent2;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean possibleTransformToSuccess() {
        return this == UNKNOWN_STATUS || this == INCOMPLETE_TYPE_INFERENCE || this == SUCCESS;
    }

    ResolutionStatus() {
        this(false);
    }
}
