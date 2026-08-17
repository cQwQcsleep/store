package io.github.rosemoe.sora.lang.analysis;

import android.os.Bundle;
import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.text.ContentReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface AnalyzeManager {
    void delete(CharPosition charPosition, CharPosition charPosition2, CharSequence charSequence);

    void destroy();

    void insert(CharPosition charPosition, CharPosition charPosition2, CharSequence charSequence);

    void rerun();

    void reset(ContentReference contentReference, Bundle bundle);

    void setReceiver(StyleReceiver styleReceiver);
}
