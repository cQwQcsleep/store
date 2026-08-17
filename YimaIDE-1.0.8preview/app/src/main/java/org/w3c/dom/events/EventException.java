package org.w3c.dom.events;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EventException extends RuntimeException {
    public static final short UNSPECIFIED_EVENT_TYPE_ERR = 0;
    private static final long serialVersionUID = 242753408332692061L;
    public short code;

    public EventException(short s, String str) {
        super(str);
        this.code = s;
    }
}
