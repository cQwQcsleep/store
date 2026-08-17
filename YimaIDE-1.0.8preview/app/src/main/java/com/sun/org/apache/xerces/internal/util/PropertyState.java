package com.sun.org.apache.xerces.internal.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PropertyState {
    public final Object state;
    public final Status status;
    public static final PropertyState UNKNOWN = new PropertyState(Status.UNKNOWN, null);
    public static final PropertyState RECOGNIZED = new PropertyState(Status.RECOGNIZED, null);
    public static final PropertyState NOT_SUPPORTED = new PropertyState(Status.NOT_SUPPORTED, null);
    public static final PropertyState NOT_RECOGNIZED = new PropertyState(Status.NOT_RECOGNIZED, null);
    public static final PropertyState NOT_ALLOWED = new PropertyState(Status.NOT_ALLOWED, null);

    public PropertyState(Status status, Object obj) {
        this.status = status;
        this.state = obj;
    }

    public static PropertyState is(Object obj) {
        return new PropertyState(Status.SET, obj);
    }

    public static PropertyState of(Status status) {
        return new PropertyState(status, null);
    }

    public boolean isExceptional() {
        return this.status.isExceptional();
    }
}
