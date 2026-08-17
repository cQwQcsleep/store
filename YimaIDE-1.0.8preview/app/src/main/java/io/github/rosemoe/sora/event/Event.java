package io.github.rosemoe.sora.event;

import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class Event {
    private final CodeEditor editor;
    private final long eventTime;
    private int interceptTargets;

    public Event(CodeEditor codeEditor, long j) {
        Objects.requireNonNull(codeEditor);
        this.editor = codeEditor;
        this.eventTime = j;
        this.interceptTargets = 0;
    }

    public boolean canIntercept() {
        return false;
    }

    public CodeEditor getEditor() {
        return this.editor;
    }

    public long getEventTime() {
        return this.eventTime;
    }

    public int getInterceptTargets() {
        return this.interceptTargets;
    }

    public void intercept() {
        if (canIntercept()) {
            this.interceptTargets = 3;
        } else {
            c41.a("intercept() not supported");
        }
    }

    public boolean isIntercepted() {
        return this.interceptTargets != 0;
    }

    public Event(CodeEditor codeEditor) {
        this(codeEditor, System.currentTimeMillis());
    }

    public void intercept(int i) {
        if (canIntercept()) {
            this.interceptTargets = i;
        } else {
            c41.a("intercept() not supported");
        }
    }
}
