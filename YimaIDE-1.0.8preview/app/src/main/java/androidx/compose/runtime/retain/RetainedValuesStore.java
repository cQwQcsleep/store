package androidx.compose.runtime.retain;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H&J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H&J\b\u0010\b\u001a\u00020\u0006H&J\b\u0010\t\u001a\u00020\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/retain/RetainedValuesStore;", "", "consumeExitedValueOrDefault", "key", "defaultValue", "saveExitingValue", "", "value", "onContentEnteredComposition", "onContentExitComposition", "runtime-retain"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface RetainedValuesStore {
    Object consumeExitedValueOrDefault(Object key, Object defaultValue);

    void onContentEnteredComposition();

    void onContentExitComposition();

    void saveExitingValue(Object key, Object value);
}
