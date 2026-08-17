package androidx.core.database;

import android.database.CursorWindow;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class CursorWindowCompat {

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static CursorWindow createCursorWindow(String str, long j) {
            return new CursorWindow(str, j);
        }
    }

    private CursorWindowCompat() {
    }

    public static CursorWindow create(String str, long j) {
        return Api28Impl.createCursorWindow(str, j);
    }
}
