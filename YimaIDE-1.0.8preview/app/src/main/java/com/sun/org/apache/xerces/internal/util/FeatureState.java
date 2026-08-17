package com.sun.org.apache.xerces.internal.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FeatureState {
    public static final FeatureState NOT_ALLOWED;
    public static final FeatureState NOT_RECOGNIZED;
    public static final FeatureState NOT_SUPPORTED;
    public static final FeatureState RECOGNIZED;
    public static final FeatureState SET_DISABLED;
    public static final FeatureState SET_ENABLED;
    public static final FeatureState UNKNOWN;
    public final boolean state;
    public final Status status;

    static {
        Status status = Status.SET;
        SET_ENABLED = new FeatureState(status, true);
        SET_DISABLED = new FeatureState(status, false);
        UNKNOWN = new FeatureState(Status.UNKNOWN, false);
        RECOGNIZED = new FeatureState(Status.RECOGNIZED, false);
        NOT_SUPPORTED = new FeatureState(Status.NOT_SUPPORTED, false);
        NOT_RECOGNIZED = new FeatureState(Status.NOT_RECOGNIZED, false);
        NOT_ALLOWED = new FeatureState(Status.NOT_ALLOWED, false);
    }

    public FeatureState(Status status, boolean z) {
        this.status = status;
        this.state = z;
    }

    public static FeatureState is(boolean z) {
        return new FeatureState(Status.SET, z);
    }

    public static FeatureState of(Status status) {
        return new FeatureState(status, false);
    }

    public boolean isExceptional() {
        return this.status.isExceptional();
    }
}
