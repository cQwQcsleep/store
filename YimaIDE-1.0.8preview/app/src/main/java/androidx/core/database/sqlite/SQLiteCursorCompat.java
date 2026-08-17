package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class SQLiteCursorCompat {

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor, boolean z) {
            sQLiteCursor.setFillWindowForwardOnly(z);
        }
    }

    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor, boolean z) {
        Api28Impl.setFillWindowForwardOnly(sQLiteCursor, z);
    }
}
