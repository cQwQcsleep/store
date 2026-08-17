package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bb\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/CalculateMatrixToWindow;", "", "calculateMatrixToWindow", "", "view", "Landroid/view/View;", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "calculateMatrixToWindow-EL8BTi8", "(Landroid/view/View;[F)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
interface CalculateMatrixToWindow {
    /* JADX INFO: renamed from: calculateMatrixToWindow-EL8BTi8, reason: not valid java name */
    void mo5109calculateMatrixToWindowEL8BTi8(View view, float[] matrix);
}
