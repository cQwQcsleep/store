package io.github.rosemoe.sora.widget.component;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.github.rosemoe.sora.R;
import io.github.rosemoe.sora.lang.completion.CompletionItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class DefaultCompletionItemAdapter extends EditorCompletionAdapter {
    @Override // io.github.rosemoe.sora.widget.component.EditorCompletionAdapter
    public int getItemHeight() {
        return (int) TypedValue.applyDimension(1, 45.0f, getContext().getResources().getDisplayMetrics());
    }

    @Override // io.github.rosemoe.sora.widget.component.EditorCompletionAdapter
    public View getView(int i, View view, ViewGroup viewGroup, boolean z) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.default_completion_result_item, viewGroup, false);
        }
        CompletionItem item = getItem(i);
        TextView textView = (TextView) view.findViewById(R.id.result_item_label);
        textView.setText(item.label);
        textView.setTextColor(getThemeColor(42));
        TextView textView2 = (TextView) view.findViewById(R.id.result_item_desc);
        textView2.setText(item.desc);
        textView2.setTextColor(getThemeColor(43));
        view.setTag(Integer.valueOf(i));
        if (z) {
            view.setBackgroundColor(getThemeColor(44));
        } else {
            view.setBackgroundColor(0);
        }
        ((ImageView) view.findViewById(R.id.result_item_image)).setImageDrawable(item.icon);
        return view;
    }
}
