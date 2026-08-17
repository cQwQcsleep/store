package androidx.window.reflection;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/reflection/WindowExtensionsConstants;", "", "<init>", "()V", "WINDOW_EXTENSIONS_PACKAGE_NAME", "", "WINDOW_EXTENSIONS_PROVIDER_CLASS", "WINDOW_EXTENSIONS_CLASS", "LAYOUT_PACKAGE", "FOLDING_FEATURE_CLASS", "SUPPORTED_WINDOW_FEATURES_CLASS", "DISPLAY_FOLD_FEATURE_CLASS", "WINDOW_LAYOUT_COMPONENT_CLASS", "WINDOW_AREA_COMPONENT_CLASS", "EXTENSION_WINDOW_AREA_STATUS_CLASS", "EXTENSION_WINDOW_AREA_PRESENTATION_CLASS", "ACTIVITY_EMBEDDING_COMPONENT_CLASS", "WINDOW_CONSUMER", "JAVA_CONSUMER", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowExtensionsConstants {
    public static final String ACTIVITY_EMBEDDING_COMPONENT_CLASS = "androidx.window.extensions.embedding.ActivityEmbeddingComponent";
    public static final String DISPLAY_FOLD_FEATURE_CLASS = "androidx.window.extensions.layout.DisplayFoldFeature";
    public static final String EXTENSION_WINDOW_AREA_PRESENTATION_CLASS = "androidx.window.extensions.area.ExtensionWindowAreaPresentation";
    public static final String EXTENSION_WINDOW_AREA_STATUS_CLASS = "androidx.window.extensions.area.ExtensionWindowAreaStatus";
    public static final String FOLDING_FEATURE_CLASS = "androidx.window.extensions.layout.FoldingFeature";
    public static final WindowExtensionsConstants INSTANCE = new WindowExtensionsConstants();
    public static final String JAVA_CONSUMER = "java.util.function.Consumer";
    public static final String LAYOUT_PACKAGE = "layout";
    public static final String SUPPORTED_WINDOW_FEATURES_CLASS = "androidx.window.extensions.layout.SupportedWindowFeatures";
    public static final String WINDOW_AREA_COMPONENT_CLASS = "androidx.window.extensions.area.WindowAreaComponent";
    public static final String WINDOW_CONSUMER = "androidx.window.extensions.core.util.function.Consumer";
    public static final String WINDOW_EXTENSIONS_CLASS = "androidx.window.extensions.WindowExtensions";
    private static final String WINDOW_EXTENSIONS_PACKAGE_NAME = "androidx.window.extensions";
    public static final String WINDOW_EXTENSIONS_PROVIDER_CLASS = "androidx.window.extensions.WindowExtensionsProvider";
    public static final String WINDOW_LAYOUT_COMPONENT_CLASS = "androidx.window.extensions.layout.WindowLayoutComponent";

    private WindowExtensionsConstants() {
    }
}
