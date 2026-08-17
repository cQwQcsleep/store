package io.github.rosemoe.sora.event;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Unsubscribe {
    private boolean unsubscribeFlag = false;

    public boolean isUnsubscribed() {
        return this.unsubscribeFlag;
    }

    public void reset() {
        this.unsubscribeFlag = false;
    }

    public void unsubscribe() {
        this.unsubscribeFlag = true;
    }
}
