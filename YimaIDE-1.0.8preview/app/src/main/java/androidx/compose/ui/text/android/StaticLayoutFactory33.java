package androidx.compose.ui.text.android;

import android.graphics.text.LineBreakConfig;
import android.text.StaticLayout;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.reflection.WindowExtensionsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory33;", "", "<init>", "()V", "isFallbackLineSpacingEnabled", "", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroid/text/StaticLayout;", "setLineBreakConfig", "", "builder", "Landroid/text/StaticLayout$Builder;", "lineBreakStyle", "", "lineBreakWordStyle", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class StaticLayoutFactory33 {
    public static final StaticLayoutFactory33 INSTANCE = new StaticLayoutFactory33();

    private StaticLayoutFactory33() {
    }

    @JvmStatic
    public static final boolean isFallbackLineSpacingEnabled(StaticLayout layout) {
        return layout.isFallbackLineSpacingEnabled();
    }

    @JvmStatic
    public static final void setLineBreakConfig(StaticLayout.Builder builder, int lineBreakStyle, int lineBreakWordStyle) {
        builder.setLineBreakConfig(new LineBreakConfig.Builder().setLineBreakStyle(lineBreakStyle).setLineBreakWordStyle(lineBreakWordStyle).build());
    }
}
