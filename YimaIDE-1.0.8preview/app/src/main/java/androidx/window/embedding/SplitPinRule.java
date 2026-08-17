package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0017BW\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0003\u0010\b\u001a\u00020\t\u0012\b\b\u0003\u0010\n\u001a\u00020\t\u0012\b\b\u0003\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\tH\u0016J\b\u0010\u0016\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0011¨\u0006\u0018"}, d2 = {"Landroidx/window/embedding/SplitPinRule;", "Landroidx/window/embedding/SplitRule;", "tag", "", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "isSticky", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "<init>", "(Ljava/lang/String;Landroidx/window/embedding/SplitAttributes;ZIIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;)V", "()Z", "equals", "other", "", "hashCode", "toString", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SplitPinRule extends SplitRule {
    private final boolean isSticky;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\u00002\b\b\u0001\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u0012\u001a\u00020\u00002\b\b\u0001\u0010\b\u001a\u00020\u0007J\u0010\u0010\u0013\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u001a\u001a\u00020\u001bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/window/embedding/SplitPinRule$Builder;", "", "<init>", "()V", "tag", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "isSticky", "", "setMinWidthDp", "setMinHeightDp", "setMinSmallestWidthDp", "setMaxAspectRatioInPortrait", "aspectRatio", "setMaxAspectRatioInLandscape", "setDefaultSplitAttributes", "setTag", "setSticky", "build", "Landroidx/window/embedding/SplitPinRule;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Builder {
        private boolean isSticky;
        private String tag;
        private int minWidthDp = 600;
        private int minHeightDp = 600;
        private int minSmallestWidthDp = 600;
        private EmbeddingAspectRatio maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
        private EmbeddingAspectRatio maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
        private SplitAttributes defaultSplitAttributes = new SplitAttributes.Builder().build();

        public final SplitPinRule build() {
            return new SplitPinRule(this.tag, this.defaultSplitAttributes, this.isSticky, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape);
        }

        public final Builder setDefaultSplitAttributes(SplitAttributes defaultSplitAttributes) {
            defaultSplitAttributes.getClass();
            this.defaultSplitAttributes = defaultSplitAttributes;
            return this;
        }

        public final Builder setMaxAspectRatioInLandscape(EmbeddingAspectRatio aspectRatio) {
            aspectRatio.getClass();
            this.maxAspectRatioInLandscape = aspectRatio;
            return this;
        }

        public final Builder setMaxAspectRatioInPortrait(EmbeddingAspectRatio aspectRatio) {
            aspectRatio.getClass();
            this.maxAspectRatioInPortrait = aspectRatio;
            return this;
        }

        public final Builder setMinHeightDp(int minHeightDp) {
            this.minHeightDp = minHeightDp;
            return this;
        }

        public final Builder setMinSmallestWidthDp(int minSmallestWidthDp) {
            this.minSmallestWidthDp = minSmallestWidthDp;
            return this;
        }

        public final Builder setMinWidthDp(int minWidthDp) {
            this.minWidthDp = minWidthDp;
            return this;
        }

        public final Builder setSticky(boolean isSticky) {
            this.isSticky = isSticky;
            return this;
        }

        public final Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }
    }

    public /* synthetic */ SplitPinRule(String str, SplitAttributes splitAttributes, boolean z, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, splitAttributes, z, (i4 & 8) != 0 ? 600 : i, (i4 & 16) != 0 ? 600 : i2, (i4 & 32) != 0 ? 600 : i3, (i4 & 64) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 128) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2);
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SplitPinRule) && super.equals(other) && this.isSticky == ((SplitPinRule) other).isSticky;
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        return (super.hashCode() * 31) + Boolean.hashCode(this.isSticky);
    }

    /* JADX INFO: renamed from: isSticky, reason: from getter */
    public final boolean getIsSticky() {
        return this.isSticky;
    }

    @Override // androidx.window.embedding.SplitRule
    public String toString() {
        return SplitPinRule.class.getSimpleName() + "{tag=" + getTag() + ", defaultSplitAttributes=" + getDefaultSplitAttributes() + ", isSticky=" + this.isSticky + '}';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPinRule(String str, SplitAttributes splitAttributes, boolean z, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2) {
        super(str, i, i2, i3, embeddingAspectRatio, embeddingAspectRatio2, splitAttributes);
        splitAttributes.getClass();
        embeddingAspectRatio.getClass();
        embeddingAspectRatio2.getClass();
        this.isSticky = z;
    }
}
