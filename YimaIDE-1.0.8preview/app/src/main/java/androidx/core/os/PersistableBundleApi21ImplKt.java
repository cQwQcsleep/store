package androidx.core.os;

import android.os.PersistableBundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0007¨\u0006\u000b"}, d2 = {"Landroidx/core/os/PersistableBundleApi21ImplKt;", "", "<init>", "()V", "putValue", "", "persistableBundle", "Landroid/os/PersistableBundle;", "key", "", "value", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class PersistableBundleApi21ImplKt {
    public static final PersistableBundleApi21ImplKt INSTANCE = new PersistableBundleApi21ImplKt();

    private PersistableBundleApi21ImplKt() {
    }

    @JvmStatic
    public static final void putValue(PersistableBundle persistableBundle, String key, Object value) {
        persistableBundle.getClass();
        if (value == null) {
            persistableBundle.putString(key, null);
            return;
        }
        if (value instanceof Boolean) {
            persistableBundle.putBoolean(key, ((Boolean) value).booleanValue());
            return;
        }
        if (value instanceof Double) {
            persistableBundle.putDouble(key, ((Number) value).doubleValue());
            return;
        }
        if (value instanceof Integer) {
            persistableBundle.putInt(key, ((Number) value).intValue());
            return;
        }
        if (value instanceof Long) {
            persistableBundle.putLong(key, ((Number) value).longValue());
            return;
        }
        if (value instanceof String) {
            persistableBundle.putString(key, (String) value);
            return;
        }
        if (value instanceof PersistableBundle) {
            persistableBundle.putPersistableBundle(key, (PersistableBundle) value);
            return;
        }
        if (value instanceof boolean[]) {
            persistableBundle.putBooleanArray(key, (boolean[]) value);
            return;
        }
        if (value instanceof double[]) {
            persistableBundle.putDoubleArray(key, (double[]) value);
            return;
        }
        if (value instanceof int[]) {
            persistableBundle.putIntArray(key, (int[]) value);
            return;
        }
        if (value instanceof long[]) {
            persistableBundle.putLongArray(key, (long[]) value);
            return;
        }
        if (!(value instanceof Object[])) {
            zba.a("Unsupported value type ", value.getClass().getCanonicalName(), " for key \"", key, "\"");
            return;
        }
        Class<?> componentType = value.getClass().getComponentType();
        componentType.getClass();
        if (String.class.isAssignableFrom(componentType)) {
            persistableBundle.putStringArray(key, (String[]) value);
        } else {
            zba.a("Unsupported value array type ", componentType.getCanonicalName(), " for key \"", key, "\"");
        }
    }
}
