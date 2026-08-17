package androidx.window.embedding;

import android.graphics.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.Bounds;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import defpackage.wa4;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 '2\u00020\u0001:\u0003%&'B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0015\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001aJ\f\u0010\u001b\u001a\u00020\u0013*\u00020\u0017H\u0002J\f\u0010\u001c\u001a\u00020\u0013*\u00020\u0017H\u0002J\u000e\u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020\u0017H\u0002J\u001d\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\"J\u001d\u0010#\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006("}, d2 = {"Landroidx/window/embedding/EmbeddingBounds;", "", "alignment", "Landroidx/window/embedding/EmbeddingBounds$Alignment;", "width", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "height", "<init>", "(Landroidx/window/embedding/EmbeddingBounds$Alignment;Landroidx/window/embedding/EmbeddingBounds$Dimension;Landroidx/window/embedding/EmbeddingBounds$Dimension;)V", "getAlignment", "()Landroidx/window/embedding/EmbeddingBounds$Alignment;", "getWidth", "()Landroidx/window/embedding/EmbeddingBounds$Dimension;", "getHeight", "toString", "", "hashCode", "", "equals", "", "other", "shouldUseFallbackDimensionForWidth", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "shouldUseFallbackDimensionForWidth$window_release", "shouldUseFallbackDimensionForHeight", "shouldUseFallbackDimensionForHeight$window_release", "isHorizontal", "isVertical", "getOnlyFoldingFeatureOrNull", "Landroidx/window/layout/FoldingFeature;", "getWidthInPixel", "parentContainerBounds", "Landroidx/window/core/Bounds;", "getWidthInPixel$window_release", "getHeightInPixel", "getHeightInPixel$window_release", "Alignment", "Dimension", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EmbeddingBounds {
    public static final EmbeddingBounds BOUNDS_EXPANDED;
    public static final EmbeddingBounds BOUNDS_HINGE_BOTTOM;
    public static final EmbeddingBounds BOUNDS_HINGE_LEFT;
    public static final EmbeddingBounds BOUNDS_HINGE_RIGHT;
    public static final EmbeddingBounds BOUNDS_HINGE_TOP;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Alignment alignment;
    private final Dimension height;
    private final Dimension width;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0013\b\u0000\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Alignment;", "", "value", "", "<init>", "(I)V", "getValue$window_release", "()I", "equals", "", "other", "hashCode", "toString", "", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Alignment {
        private final int value;
        public static final Alignment ALIGN_LEFT = new Alignment(0);
        public static final Alignment ALIGN_TOP = new Alignment(1);
        public static final Alignment ALIGN_RIGHT = new Alignment(2);
        public static final Alignment ALIGN_BOTTOM = new Alignment(3);

        public Alignment(int i) {
            this.value = i;
            if (i < 0 || i >= 4) {
                w01.a("Failed requirement.");
                throw null;
            }
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Alignment) && this.value == ((Alignment) other).value;
        }

        /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value;
        }

        public String toString() {
            int i = this.value;
            if (i == 0) {
                return "left";
            }
            if (i == 1) {
                return "top";
            }
            if (i == 2) {
                return "right";
            }
            if (i == 3) {
                return "bottom";
            }
            return "unknown position:" + this.value;
        }
    }

    static {
        Alignment alignment = Alignment.ALIGN_TOP;
        Dimension dimension = Dimension.DIMENSION_EXPANDED;
        BOUNDS_EXPANDED = new EmbeddingBounds(alignment, dimension, dimension);
        Dimension dimension2 = Dimension.DIMENSION_HINGE;
        BOUNDS_HINGE_TOP = new EmbeddingBounds(alignment, dimension, dimension2);
        BOUNDS_HINGE_LEFT = new EmbeddingBounds(Alignment.ALIGN_LEFT, dimension2, dimension);
        BOUNDS_HINGE_BOTTOM = new EmbeddingBounds(Alignment.ALIGN_BOTTOM, dimension, dimension2);
        BOUNDS_HINGE_RIGHT = new EmbeddingBounds(Alignment.ALIGN_RIGHT, dimension2, dimension);
    }

    public EmbeddingBounds(Alignment alignment, Dimension dimension, Dimension dimension2) {
        alignment.getClass();
        dimension.getClass();
        dimension2.getClass();
        this.alignment = alignment;
        this.width = dimension;
        this.height = dimension2;
    }

    private final FoldingFeature getOnlyFoldingFeatureOrNull(WindowLayoutInfo windowLayoutInfo) {
        List<DisplayFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
        ArrayList arrayList = new ArrayList();
        for (Object obj : displayFeatures) {
            if (obj instanceof FoldingFeature) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() == 1) {
            return (FoldingFeature) arrayList.get(0);
        }
        return null;
    }

    private final boolean isHorizontal(WindowLayoutInfo windowLayoutInfo) {
        FoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        if (onlyFoldingFeatureOrNull == null) {
            return false;
        }
        return Intrinsics.areEqual(onlyFoldingFeatureOrNull.getOrientation(), FoldingFeature.Orientation.HORIZONTAL);
    }

    private final boolean isVertical(WindowLayoutInfo windowLayoutInfo) {
        FoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        if (onlyFoldingFeatureOrNull == null) {
            return false;
        }
        return Intrinsics.areEqual(onlyFoldingFeatureOrNull.getOrientation(), FoldingFeature.Orientation.VERTICAL);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmbeddingBounds)) {
            return false;
        }
        EmbeddingBounds embeddingBounds = (EmbeddingBounds) other;
        return Intrinsics.areEqual(this.alignment, embeddingBounds.alignment) && Intrinsics.areEqual(this.width, embeddingBounds.width) && Intrinsics.areEqual(this.height, embeddingBounds.height);
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final Dimension getHeight() {
        return this.height;
    }

    public final int getHeightInPixel$window_release(Bounds parentContainerBounds, WindowLayoutInfo windowLayoutInfo) {
        parentContainerBounds.getClass();
        windowLayoutInfo.getClass();
        int height = parentContainerBounds.getHeight();
        Dimension dimensionRatio = shouldUseFallbackDimensionForHeight$window_release(windowLayoutInfo) ? Dimension.INSTANCE.ratio(0.5f) : this.height;
        if (dimensionRatio instanceof Dimension.Ratio) {
            return ((Dimension.Ratio) dimensionRatio).times$window_release(height);
        }
        if (dimensionRatio instanceof Dimension.Pixel) {
            return Math.min(height, ((Dimension.Pixel) dimensionRatio).getValue());
        }
        if (!Intrinsics.areEqual(dimensionRatio, Dimension.DIMENSION_HINGE)) {
            z01.a("Unhandled width dimension=", this.width);
            return 0;
        }
        FoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        onlyFoldingFeatureOrNull.getClass();
        Rect bounds = onlyFoldingFeatureOrNull.getBounds();
        Alignment alignment = this.alignment;
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_TOP)) {
            return bounds.top - parentContainerBounds.getTop();
        }
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_BOTTOM)) {
            return parentContainerBounds.getBottom() - bounds.bottom;
        }
        wa4.a("Unhandled condition to get height in pixel! embeddingBounds=", this, " taskBounds=", parentContainerBounds, " windowLayoutInfo=", windowLayoutInfo);
        return 0;
    }

    public final Dimension getWidth() {
        return this.width;
    }

    public final int getWidthInPixel$window_release(Bounds parentContainerBounds, WindowLayoutInfo windowLayoutInfo) {
        parentContainerBounds.getClass();
        windowLayoutInfo.getClass();
        int width = parentContainerBounds.getWidth();
        Dimension dimensionRatio = shouldUseFallbackDimensionForWidth$window_release(windowLayoutInfo) ? Dimension.INSTANCE.ratio(0.5f) : this.width;
        if (dimensionRatio instanceof Dimension.Ratio) {
            return ((Dimension.Ratio) dimensionRatio).times$window_release(width);
        }
        if (dimensionRatio instanceof Dimension.Pixel) {
            return Math.min(width, ((Dimension.Pixel) dimensionRatio).getValue());
        }
        if (!Intrinsics.areEqual(dimensionRatio, Dimension.DIMENSION_HINGE)) {
            z01.a("Unhandled width dimension=", this.width);
            return 0;
        }
        FoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        onlyFoldingFeatureOrNull.getClass();
        Rect bounds = onlyFoldingFeatureOrNull.getBounds();
        Alignment alignment = this.alignment;
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_LEFT)) {
            return bounds.left - parentContainerBounds.getLeft();
        }
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_RIGHT)) {
            return parentContainerBounds.getRight() - bounds.right;
        }
        wa4.a("Unhandled condition to get height in pixel! embeddingBounds=", this, " taskBounds=", parentContainerBounds, " windowLayoutInfo=", windowLayoutInfo);
        return 0;
    }

    public int hashCode() {
        return (((this.alignment.hashCode() * 31) + this.width.hashCode()) * 31) + this.height.hashCode();
    }

    public final boolean shouldUseFallbackDimensionForHeight$window_release(WindowLayoutInfo windowLayoutInfo) {
        windowLayoutInfo.getClass();
        if (Intrinsics.areEqual(this.height, Dimension.DIMENSION_HINGE)) {
            return !isHorizontal(windowLayoutInfo) || CollectionsKt.listOf(new Alignment[]{Alignment.ALIGN_LEFT, Alignment.ALIGN_RIGHT}).contains(this.alignment);
        }
        return false;
    }

    public final boolean shouldUseFallbackDimensionForWidth$window_release(WindowLayoutInfo windowLayoutInfo) {
        windowLayoutInfo.getClass();
        if (Intrinsics.areEqual(this.width, Dimension.DIMENSION_HINGE)) {
            return !isVertical(windowLayoutInfo) || CollectionsKt.listOf(new Alignment[]{Alignment.ALIGN_TOP, Alignment.ALIGN_BOTTOM}).contains(this.alignment);
        }
        return false;
    }

    public String toString() {
        return "Bounds:{alignment=" + this.alignment + ", width=" + this.width + ", height=" + this.height + '}';
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u0000 \u00102\u00020\u0001:\u0003\u000e\u000f\u0010B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension;", "", "description", "", "<init>", "(Ljava/lang/String;)V", "getDescription$window_release", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "Pixel", "Ratio", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static abstract class Dimension {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Dimension DIMENSION_EXPANDED = new Ratio(1.0f);
        public static final Dimension DIMENSION_HINGE = new Dimension() { // from class: androidx.window.embedding.EmbeddingBounds$Dimension$Companion$DIMENSION_HINGE$1
        };
        private final String description;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0080\u0002¢\u0006\u0002\b\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension$Pixel;", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "value", "", "<init>", "(I)V", "getValue$window_release", "()I", "compareTo", "dimen", "compareTo$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Pixel extends Dimension {
            private final int value;

            public Pixel(int i) {
                super("dimension in pixel:" + i);
                this.value = i;
                if (i >= 1) {
                    return;
                }
                w01.a("Pixel value must be a positive integer.");
                throw null;
            }

            public final int compareTo$window_release(int dimen) {
                return this.value - dimen;
            }

            /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
            public final int getValue() {
                return this.value;
            }
        }

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0080\u0002¢\u0006\u0002\b\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension$Ratio;", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "value", "", "<init>", "(F)V", "getValue$window_release", "()F", "times", "", "dimen", "times$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Ratio extends Dimension {
            private final float value;

            public Ratio(float f) {
                super("dimension in ratio:" + f);
                this.value = f;
                if (f <= 0.0d || f > 1.0d) {
                    w01.a("Ratio must be in range (0.0, 1.0]");
                    throw null;
                }
            }

            /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
            public final float getValue() {
                return this.value;
            }

            public final int times$window_release(int dimen) {
                return (int) (this.value * dimen);
            }
        }

        public Dimension(String str) {
            str.getClass();
            this.description = str;
        }

        @JvmStatic
        public static final Dimension pixel(int i) {
            return INSTANCE.pixel(i);
        }

        @JvmStatic
        public static final Dimension ratio(float f) {
            return INSTANCE.ratio(f);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Dimension) {
                return Intrinsics.areEqual(this.description, ((Dimension) other).description);
            }
            return false;
        }

        /* JADX INFO: renamed from: getDescription$window_release, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public int hashCode() {
            return this.description.hashCode();
        }

        public String toString() {
            return this.description;
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0012\u0010\n\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension$Companion;", "", "<init>", "()V", "DIMENSION_EXPANDED", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "DIMENSION_HINGE", "pixel", "value", "", "ratio", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final Dimension pixel(int value) {
                return new Pixel(value);
            }

            @JvmStatic
            public final Dimension ratio(float ratio) {
                return new Ratio(ratio);
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0010J\u001c\u0010\u0011\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0010R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Companion;", "", "<init>", "()V", "BOUNDS_EXPANDED", "Landroidx/window/embedding/EmbeddingBounds;", "BOUNDS_HINGE_TOP", "BOUNDS_HINGE_LEFT", "BOUNDS_HINGE_BOTTOM", "BOUNDS_HINGE_RIGHT", "translateEmbeddingBounds", "Landroidx/window/core/Bounds;", "embeddingBounds", "parentContainerBounds", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "translateEmbeddingBounds$window_release", "offset", "dx", "", "dy", "parentContainerInfo", "Landroidx/window/embedding/ParentContainerInfo;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Bounds offset(Bounds bounds, int i, int i2) {
            return new Bounds(bounds.getLeft() + i, bounds.getTop() + i2, bounds.getRight() + i, bounds.getBottom() + i2);
        }

        public final Bounds translateEmbeddingBounds$window_release(EmbeddingBounds embeddingBounds, Bounds parentContainerBounds, WindowLayoutInfo windowLayoutInfo) {
            embeddingBounds.getClass();
            parentContainerBounds.getClass();
            windowLayoutInfo.getClass();
            Dimension width = embeddingBounds.getWidth();
            Dimension dimension = Dimension.DIMENSION_EXPANDED;
            if (Intrinsics.areEqual(width, dimension) && Intrinsics.areEqual(embeddingBounds.getHeight(), dimension)) {
                return Bounds.INSTANCE.getEMPTY_BOUNDS();
            }
            EmbeddingBounds embeddingBounds2 = new EmbeddingBounds(embeddingBounds.getAlignment(), embeddingBounds.shouldUseFallbackDimensionForWidth$window_release(windowLayoutInfo) ? Dimension.INSTANCE.ratio(0.5f) : embeddingBounds.getWidth(), embeddingBounds.shouldUseFallbackDimensionForHeight$window_release(windowLayoutInfo) ? Dimension.INSTANCE.ratio(0.5f) : embeddingBounds.getHeight());
            int widthInPixel$window_release = embeddingBounds2.getWidthInPixel$window_release(parentContainerBounds, windowLayoutInfo);
            int heightInPixel$window_release = embeddingBounds2.getHeightInPixel$window_release(parentContainerBounds, windowLayoutInfo);
            int width2 = parentContainerBounds.getWidth();
            int height = parentContainerBounds.getHeight();
            if (widthInPixel$window_release == width2 && heightInPixel$window_release == height) {
                return Bounds.INSTANCE.getEMPTY_BOUNDS();
            }
            Bounds bounds = new Bounds(0, 0, widthInPixel$window_release, heightInPixel$window_release);
            Alignment alignment = embeddingBounds.getAlignment();
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_TOP)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, (width2 - widthInPixel$window_release) / 2, 0);
            }
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_LEFT)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, 0, (height - heightInPixel$window_release) / 2);
            }
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_BOTTOM)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, (width2 - widthInPixel$window_release) / 2, height - heightInPixel$window_release);
            }
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_RIGHT)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, width2 - widthInPixel$window_release, (height - heightInPixel$window_release) / 2);
            }
            z01.a("Unknown alignment: ", embeddingBounds.getAlignment());
            return null;
        }

        private Companion() {
        }

        public final Bounds translateEmbeddingBounds$window_release(EmbeddingBounds embeddingBounds, ParentContainerInfo parentContainerInfo) {
            embeddingBounds.getClass();
            parentContainerInfo.getClass();
            return translateEmbeddingBounds$window_release(embeddingBounds, parentContainerInfo.getWindowBounds(), parentContainerInfo.getWindowLayoutInfo());
        }
    }
}
