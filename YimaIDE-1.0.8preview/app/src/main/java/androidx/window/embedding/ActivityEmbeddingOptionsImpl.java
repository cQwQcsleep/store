package androidx.window.embedding;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.WindowSdkExtensions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0002\b\u0016J\u0014\u0010\u0017\u001a\u00020\u0011*\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0019\u0010\u001a\u001a\u00020\u0011*\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001bJ\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u0013H\u0000¢\u0006\u0002\b\u001eJ\u000e\u0010\u001f\u001a\u0004\u0018\u00010\u0019*\u00020\u0013H\u0002J\u0014\u0010 \u001a\u00020!*\u00020\u00132\u0006\u0010\"\u001a\u00020\u0005H\u0002J\u001c\u0010#\u001a\u00020\u0011*\u00020\u00132\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010$\u001a\u00020!H\u0002J\u001d\u0010%\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010&\u001a\u00020'H\u0001¢\u0006\u0002\b(R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Landroidx/window/embedding/ActivityEmbeddingOptionsImpl;", "", "<init>", "()V", "KEY_EMBEDDING_BOUNDS", "", "KEY_EMBEDDING_BOUNDS_ALIGNMENT", "KEY_EMBEDDING_BOUNDS_WIDTH", "KEY_EMBEDDING_BOUNDS_HEIGHT", "DIMENSION_TYPE_EXPANDED", "DIMENSION_TYPE_HINGE", "DIMENSION_TYPE_RATIO", "DIMENSION_TYPE_PIXEL", "KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE", "KEY_EMBEDDING_BOUNDS_DIMENSION_VALUE", "KEY_ACTIVITY_STACK_ALIGNMENT", "setOverlayCreateParams", "", "options", "Landroid/os/Bundle;", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "setOverlayCreateParams$window_release", "putEmbeddingBounds", "embeddingBounds", "Landroidx/window/embedding/EmbeddingBounds;", "putActivityStackAlignment", "putActivityStackAlignment$window_release", "getOverlayAttributes", "Landroidx/window/embedding/OverlayAttributes;", "getOverlayAttributes$window_release", "getEmbeddingBounds", "getDimension", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "key", "putDimension", "dimension", "setActivityStackToken", "activityStackToken", "Landroidx/window/extensions/embedding/ActivityStack$Token;", "setActivityStackToken$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ActivityEmbeddingOptionsImpl {
    private static final String DIMENSION_TYPE_EXPANDED = "expanded";
    private static final String DIMENSION_TYPE_HINGE = "hinge";
    private static final String DIMENSION_TYPE_PIXEL = "pixel";
    private static final String DIMENSION_TYPE_RATIO = "ratio";
    public static final ActivityEmbeddingOptionsImpl INSTANCE = new ActivityEmbeddingOptionsImpl();
    private static final String KEY_ACTIVITY_STACK_ALIGNMENT = "androidx.window.embedding.ActivityStackAlignment";
    private static final String KEY_EMBEDDING_BOUNDS = "androidx.window.embedding.EmbeddingBounds";
    private static final String KEY_EMBEDDING_BOUNDS_ALIGNMENT = "androidx.window.embedding.EmbeddingBounds.alignment";
    private static final String KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE = "androidx.window.embedding.EmbeddingBounds.dimension_type";
    private static final String KEY_EMBEDDING_BOUNDS_DIMENSION_VALUE = "androidx.window.embedding.EmbeddingBounds.dimension_value";
    private static final String KEY_EMBEDDING_BOUNDS_HEIGHT = "androidx.window.embedding.EmbeddingBounds.height";
    private static final String KEY_EMBEDDING_BOUNDS_WIDTH = "androidx.window.embedding.EmbeddingBounds.width";

    private ActivityEmbeddingOptionsImpl() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final EmbeddingBounds.Dimension getDimension(Bundle bundle, String str) {
        Bundle bundle2 = bundle.getBundle(str);
        bundle2.getClass();
        String string = bundle2.getString(KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE);
        if (string != null) {
            switch (string.hashCode()) {
                case -1939100487:
                    if (string.equals(DIMENSION_TYPE_EXPANDED)) {
                        return EmbeddingBounds.Dimension.DIMENSION_EXPANDED;
                    }
                    break;
                case 99283243:
                    if (string.equals(DIMENSION_TYPE_HINGE)) {
                        return EmbeddingBounds.Dimension.DIMENSION_HINGE;
                    }
                    break;
                case 106680966:
                    if (string.equals(DIMENSION_TYPE_PIXEL)) {
                        return EmbeddingBounds.Dimension.INSTANCE.pixel(bundle2.getInt(KEY_EMBEDDING_BOUNDS_DIMENSION_VALUE));
                    }
                    break;
                case 108285963:
                    if (string.equals(DIMENSION_TYPE_RATIO)) {
                        return EmbeddingBounds.Dimension.INSTANCE.ratio(bundle2.getFloat(KEY_EMBEDDING_BOUNDS_DIMENSION_VALUE));
                    }
                    break;
            }
        }
        aca.a("Illegal type ", string);
        return null;
    }

    private final EmbeddingBounds getEmbeddingBounds(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(KEY_EMBEDDING_BOUNDS);
        if (bundle2 == null) {
            return null;
        }
        return new EmbeddingBounds(new EmbeddingBounds.Alignment(bundle2.getInt(KEY_EMBEDDING_BOUNDS_ALIGNMENT)), getDimension(bundle2, KEY_EMBEDDING_BOUNDS_WIDTH), getDimension(bundle2, KEY_EMBEDDING_BOUNDS_HEIGHT));
    }

    private final void putDimension(Bundle bundle, String str, EmbeddingBounds.Dimension dimension) {
        Bundle bundle2 = new Bundle();
        if (Intrinsics.areEqual(dimension, EmbeddingBounds.Dimension.DIMENSION_EXPANDED)) {
            bundle2.putString(KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE, DIMENSION_TYPE_EXPANDED);
        } else if (Intrinsics.areEqual(dimension, EmbeddingBounds.Dimension.DIMENSION_HINGE)) {
            bundle2.putString(KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE, DIMENSION_TYPE_HINGE);
        } else if (dimension instanceof EmbeddingBounds.Dimension.Ratio) {
            bundle2.putString(KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE, DIMENSION_TYPE_RATIO);
            bundle2.putFloat(KEY_EMBEDDING_BOUNDS_DIMENSION_VALUE, ((EmbeddingBounds.Dimension.Ratio) dimension).getValue());
        } else if (dimension instanceof EmbeddingBounds.Dimension.Pixel) {
            bundle2.putString(KEY_EMBEDDING_BOUNDS_DIMENSION_TYPE, DIMENSION_TYPE_PIXEL);
            bundle2.putInt(KEY_EMBEDDING_BOUNDS_DIMENSION_VALUE, ((EmbeddingBounds.Dimension.Pixel) dimension).getValue());
        }
        Unit unit = Unit.INSTANCE;
        bundle.putBundle(str, bundle2);
    }

    private final void putEmbeddingBounds(Bundle bundle, EmbeddingBounds embeddingBounds) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt(KEY_EMBEDDING_BOUNDS_ALIGNMENT, embeddingBounds.getAlignment().getValue());
        ActivityEmbeddingOptionsImpl activityEmbeddingOptionsImpl = INSTANCE;
        activityEmbeddingOptionsImpl.putDimension(bundle2, KEY_EMBEDDING_BOUNDS_WIDTH, embeddingBounds.getWidth());
        activityEmbeddingOptionsImpl.putDimension(bundle2, KEY_EMBEDDING_BOUNDS_HEIGHT, embeddingBounds.getHeight());
        Unit unit = Unit.INSTANCE;
        bundle.putBundle(KEY_EMBEDDING_BOUNDS, bundle2);
    }

    public final OverlayAttributes getOverlayAttributes$window_release(Bundle bundle) {
        bundle.getClass();
        EmbeddingBounds embeddingBounds = getEmbeddingBounds(bundle);
        if (embeddingBounds == null) {
            return null;
        }
        return new OverlayAttributes(embeddingBounds);
    }

    public final void putActivityStackAlignment$window_release(Bundle bundle, EmbeddingBounds embeddingBounds) {
        bundle.getClass();
        embeddingBounds.getClass();
        bundle.putInt(KEY_ACTIVITY_STACK_ALIGNMENT, embeddingBounds.getAlignment().getValue());
    }

    public final void setActivityStackToken$window_release(Bundle options, androidx.window.extensions.embedding.ActivityStack.Token activityStackToken) {
        options.getClass();
        activityStackToken.getClass();
        options.putBundle("androidx.window.extensions.embedding.ActivityStackToken", activityStackToken.toBundle());
    }

    public final void setOverlayCreateParams$window_release(Bundle options, OverlayCreateParams overlayCreateParams) {
        options.getClass();
        overlayCreateParams.getClass();
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(8);
        options.putString("androidx.window.extensions.embedding.OverlayTag", overlayCreateParams.getTag());
        putEmbeddingBounds(options, overlayCreateParams.getAttrs().getBounds());
    }
}
