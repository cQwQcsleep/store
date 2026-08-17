package io.github.rosemoe.sora.event;

import io.github.rosemoe.sora.widget.CodeEditor;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class ResultedEvent<T> extends Event {
    private T result;

    public ResultedEvent(CodeEditor codeEditor) {
        super(codeEditor);
    }

    public T getResult() {
        return this.result;
    }

    public void interceptAndSetResult(T t) {
        setResult(t);
        intercept();
    }

    public boolean isResultSet() {
        return this.result != null;
    }

    public void setResult(T t) {
        this.result = t;
    }
}
