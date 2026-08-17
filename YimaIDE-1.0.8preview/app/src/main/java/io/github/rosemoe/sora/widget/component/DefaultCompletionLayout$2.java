package io.github.rosemoe.sora.widget.component;

import android.graphics.Outline;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DefaultCompletionLayout$2 extends ViewOutlineProvider {
    final /* synthetic */ DefaultCompletionLayout this$0;

    public DefaultCompletionLayout$2(DefaultCompletionLayout defaultCompletionLayout) {
        this.this$0 = defaultCompletionLayout;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), TypedValue.applyDimension(1, 8.0f, view.getContext().getResources().getDisplayMetrics()));
    }
}
