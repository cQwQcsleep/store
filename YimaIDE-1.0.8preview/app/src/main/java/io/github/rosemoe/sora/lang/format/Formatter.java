package io.github.rosemoe.sora.lang.format;

import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.TextRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Formatter {

    public interface FormatResultReceiver {
        void onFormatFail(Throwable th);

        void onFormatSucceed(CharSequence charSequence, TextRange textRange);
    }

    default void cancel() {
    }

    void destroy();

    void format(Content content, TextRange textRange);

    void formatRegion(Content content, TextRange textRange, TextRange textRange2);

    boolean isRunning();

    void setReceiver(FormatResultReceiver formatResultReceiver);
}
