package io.vavr;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MatchError extends NoSuchElementException {
    private static final long serialVersionUID = 1;
    private final Object obj;

    /* JADX WARN: Illegal instructions before constructor call */
    public MatchError(Object obj) {
        String str;
        if (obj == null) {
            str = "null";
        } else {
            str = "type: " + obj.getClass().getName() + ", value: " + obj;
        }
        super(str);
        this.obj = obj;
    }

    public Object getObject() {
        return this.obj;
    }
}
