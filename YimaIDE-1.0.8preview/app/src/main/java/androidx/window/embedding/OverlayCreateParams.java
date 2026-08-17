package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0002\r\u000eB\u001d\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/OverlayCreateParams;", "", "tag", "", "overlayAttributes", "Landroidx/window/embedding/OverlayAttributes;", "<init>", "(Ljava/lang/String;Landroidx/window/embedding/OverlayAttributes;)V", "getTag", "()Ljava/lang/String;", "getOverlayAttributes", "()Landroidx/window/embedding/OverlayAttributes;", "toString", "Builder", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OverlayCreateParams {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: overlayAttributes, reason: from kotlin metadata and from toString */
    private final OverlayAttributes attrs;
    private final String tag;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/embedding/OverlayCreateParams$Builder;", "", "<init>", "()V", "tag", "", "launchAttrs", "Landroidx/window/embedding/OverlayAttributes;", "setTag", "setOverlayAttributes", "attrs", "build", "Landroidx/window/embedding/OverlayCreateParams;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Builder {
        private OverlayAttributes launchAttrs;
        private String tag;

        public final OverlayCreateParams build() {
            String strGenerateOverlayTag = this.tag;
            if (strGenerateOverlayTag == null) {
                strGenerateOverlayTag = OverlayCreateParams.INSTANCE.generateOverlayTag();
            }
            OverlayAttributes overlayAttributesBuild = this.launchAttrs;
            if (overlayAttributesBuild == null) {
                overlayAttributesBuild = new OverlayAttributes.Builder().build();
            }
            return new OverlayCreateParams(strGenerateOverlayTag, overlayAttributesBuild);
        }

        public final Builder setOverlayAttributes(OverlayAttributes attrs) {
            attrs.getClass();
            this.launchAttrs = attrs;
            return this;
        }

        public final Builder setTag(String tag) {
            tag.getClass();
            this.tag = tag;
            return this;
        }
    }

    public /* synthetic */ OverlayCreateParams(String str, OverlayAttributes overlayAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? INSTANCE.generateOverlayTag() : str, (i & 2) != 0 ? new OverlayAttributes.Builder().build() : overlayAttributes);
    }

    @JvmStatic
    public static final String generateOverlayTag() {
        return INSTANCE.generateOverlayTag();
    }

    /* JADX INFO: renamed from: getOverlayAttributes, reason: from getter */
    public final OverlayAttributes getAttrs() {
        return this.attrs;
    }

    public final String getTag() {
        return this.tag;
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(OverlayCreateParams.class).getSimpleName() + ":{ , tag=" + this.tag + ", attrs=" + this.attrs + '}';
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Landroidx/window/embedding/OverlayCreateParams$Companion;", "", "<init>", "()V", "generateOverlayTag", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final String generateOverlayTag() {
            String string = UUID.randomUUID().toString();
            string.getClass();
            return StringsKt.substring(string, new IntRange(0, 32));
        }

        private Companion() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OverlayCreateParams(String str) {
        str.getClass();
        OverlayAttributes overlayAttributes = null;
        this(str, overlayAttributes, 2, overlayAttributes);
    }

    public OverlayCreateParams(String str, OverlayAttributes overlayAttributes) {
        str.getClass();
        overlayAttributes.getClass();
        this.tag = str;
        this.attrs = overlayAttributes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OverlayCreateParams() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }
}
