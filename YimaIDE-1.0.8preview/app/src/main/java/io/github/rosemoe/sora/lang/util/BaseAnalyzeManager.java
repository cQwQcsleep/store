package io.github.rosemoe.sora.lang.util;

import android.os.Bundle;
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager;
import io.github.rosemoe.sora.lang.analysis.StyleReceiver;
import io.github.rosemoe.sora.text.ContentReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class BaseAnalyzeManager implements AnalyzeManager {
    private ContentReference contentRef;
    private Bundle extraArguments;
    private StyleReceiver receiver;

    @Override // io.github.rosemoe.sora.lang.analysis.AnalyzeManager
    public void destroy() {
        this.receiver = null;
        this.contentRef = null;
        this.extraArguments = null;
    }

    public ContentReference getContentRef() {
        return this.contentRef;
    }

    public Bundle getExtraArguments() {
        return this.extraArguments;
    }

    public StyleReceiver getReceiver() {
        return this.receiver;
    }

    @Override // io.github.rosemoe.sora.lang.analysis.AnalyzeManager
    public void reset(ContentReference contentReference, Bundle bundle) {
        this.extraArguments = bundle;
        this.contentRef = contentReference;
        rerun();
    }

    @Override // io.github.rosemoe.sora.lang.analysis.AnalyzeManager
    public void setReceiver(StyleReceiver styleReceiver) {
        this.receiver = styleReceiver;
    }
}
