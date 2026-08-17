package io.github.rosemoe.sora.util;

import android.content.ClipData;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ClipDataUtils {
    public static String clipDataToString(ClipData clipData) {
        if (clipData == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < clipData.getItemCount(); i++) {
            if (i > 0) {
                sb.append('\n');
            }
            ClipData.Item itemAt = clipData.getItemAt(i);
            if (itemAt.getText() != null) {
                sb.append(itemAt.getText());
            } else if (itemAt.getUri() != null) {
                sb.append(itemAt.getUri().toString());
            } else if (itemAt.getIntent() != null) {
                sb.append(itemAt.getIntent().toUri(1));
            }
        }
        return sb.toString();
    }
}
