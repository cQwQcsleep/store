package io.github.rosemoe.sora;

import android.content.Context;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class I18nConfig {
    private static final SparseIntArray mapping = new SparseIntArray();

    public static int getResourceId(int i) {
        int i2 = mapping.get(i);
        return i2 == 0 ? i : i2;
    }

    public static String getString(Context context, int i) {
        return context.getString(getResourceId(i));
    }

    public static void mapTo(int i, int i2) {
        mapping.put(i, i2);
    }
}
