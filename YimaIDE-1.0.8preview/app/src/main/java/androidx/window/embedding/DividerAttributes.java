package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 \u000f2\u00020\u0001:\u0004\f\r\u000e\u000fB\u001d\b\u0002\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/window/embedding/DividerAttributes;", "", "widthDp", "", "color", "<init>", "(II)V", "getWidthDp", "()I", "getColor", "toString", "", "FixedDividerAttributes", "DraggableDividerAttributes", "DragRange", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class DividerAttributes {
    public static final int COLOR_SYSTEM_DEFAULT = -16777216;
    public static final float DRAG_RANGE_VALUE_UNSPECIFIED = -1.0f;
    public static final int TYPE_VALUE_DRAGGABLE = 1;
    public static final int TYPE_VALUE_FIXED = 0;
    public static final int WIDTH_SYSTEM_DEFAULT = -1;
    private final int color;
    private final int widthDp;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final DividerAttributes NO_DIVIDER = new DividerAttributes() { // from class: androidx.window.embedding.DividerAttributes$Companion$NO_DIVIDER$1
        public int hashCode() {
            return toString().hashCode();
        }

        @Override // androidx.window.embedding.DividerAttributes
        public String toString() {
            return "NO_DIVIDER";
        }
    };

    public /* synthetic */ DividerAttributes(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -16777216 : i2);
    }

    public final int getColor() {
        return this.color;
    }

    public final int getWidthDp() {
        return this.widthDp;
    }

    public String toString() {
        return DividerAttributes.class.getSimpleName() + "{width=" + this.widthDp + ", color=" + this.color + '}';
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J-\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u001bJ\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0012\u0010\u001d\u001a\u00020\u00172\b\b\u0001\u0010\u0010\u001a\u00020\u0005H\u0002J\f\u0010\u001e\u001a\u00020\u0005*\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/window/embedding/DividerAttributes$Companion;", "", "<init>", "()V", "WIDTH_SYSTEM_DEFAULT", "", "NO_DIVIDER", "Landroidx/window/embedding/DividerAttributes;", "TYPE_VALUE_FIXED", "TYPE_VALUE_DRAGGABLE", "DRAG_RANGE_VALUE_UNSPECIFIED", "", "COLOR_SYSTEM_DEFAULT", "createDividerAttributes", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "widthDp", "color", "dragRangeMinRatio", "dragRangeMaxRatio", "isDraggingToFullscreenAllowed", "", "createDividerAttributes$window_release", "validateXmlDividerAttributes", "", "hasDragRangeMinRatio", "hasDragRangeMaxRatio", "hasIsDraggingToFullscreenAllowed", "validateXmlDividerAttributes$window_release", "validateWidth", "validateColor", "alpha", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int alpha(int i) {
            return i >>> 24;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateColor(int color) {
            if (alpha(color) == 255) {
                return;
            }
            dt1.a("Divider color must be opaque. Got: ", Integer.toHexString(color));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateWidth(int widthDp) {
            if (widthDp == -1 || widthDp >= 0) {
                return;
            }
            v1f.a("widthDp must be greater than or equal to 0 or WIDTH_SYSTEM_DEFAULT. Got: ", widthDp);
        }

        public final DividerAttributes createDividerAttributes$window_release(int type, int widthDp, int color, float dragRangeMinRatio, float dragRangeMaxRatio, boolean isDraggingToFullscreenAllowed) {
            if (type == 0) {
                return new FixedDividerAttributes.Builder().setWidthDp(widthDp).setColor(color).build();
            }
            if (type != 1) {
                throw new IllegalArgumentException("Got unknown divider type " + type + '!');
            }
            DraggableDividerAttributes.Builder draggingToFullscreenAllowed = new DraggableDividerAttributes.Builder().setWidthDp(widthDp).setColor(color).setDraggingToFullscreenAllowed(isDraggingToFullscreenAllowed);
            if (dragRangeMinRatio == -1.0f || dragRangeMaxRatio == -1.0f) {
                draggingToFullscreenAllowed.setDragRange(DragRange.DRAG_RANGE_SYSTEM_DEFAULT);
            } else {
                draggingToFullscreenAllowed.setDragRange(new DragRange.SplitRatioDragRange(dragRangeMinRatio, dragRangeMaxRatio));
            }
            return draggingToFullscreenAllowed.build();
        }

        public final void validateXmlDividerAttributes$window_release(int type, boolean hasDragRangeMinRatio, boolean hasDragRangeMaxRatio, boolean hasIsDraggingToFullscreenAllowed) {
            if (type == 1) {
                return;
            }
            if (hasDragRangeMinRatio) {
                w01.a("Fixed divider does not allow attribute dragRangeMinRatio!");
            } else if (hasDragRangeMaxRatio) {
                w01.a("Fixed divider does not allow attribute dragRangeMaxRatio!");
            } else if (hasIsDraggingToFullscreenAllowed) {
                w01.a("Fixed divider does not allow attribute isDraggingToFullscreenAllowed!");
            }
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b&\u0018\u0000 \u00052\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DragRange;", "", "<init>", "()V", "SplitRatioDragRange", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static abstract class DragRange {
        public static final DragRange DRAG_RANGE_SYSTEM_DEFAULT = new DragRange() { // from class: androidx.window.embedding.DividerAttributes$DragRange$Companion$DRAG_RANGE_SYSTEM_DEFAULT$1
            public String toString() {
                return "DRAG_RANGE_SYSTEM_DEFAULT";
            }
        };

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DragRange$SplitRatioDragRange;", "Landroidx/window/embedding/DividerAttributes$DragRange;", "minRatio", "", "maxRatio", "<init>", "(FF)V", "getMinRatio", "()F", "getMaxRatio", "toString", "", "equals", "", "other", "", "hashCode", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class SplitRatioDragRange extends DragRange {
            private final float maxRatio;
            private final float minRatio;

            public SplitRatioDragRange(float f, float f2) {
                super(null);
                this.minRatio = f;
                this.maxRatio = f2;
                if (f <= 0.0d || f >= 1.0d) {
                    w01.a("minRatio must be in the interval (0.0, 1.0)");
                    throw null;
                }
                if (f2 <= 0.0d || f2 >= 1.0d) {
                    w01.a("maxRatio must be in the interval (0.0, 1.0)");
                    throw null;
                }
                if (f <= f2) {
                    return;
                }
                w01.a("minRatio must be less than or equal to maxRatio");
                throw null;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SplitRatioDragRange)) {
                    return false;
                }
                SplitRatioDragRange splitRatioDragRange = (SplitRatioDragRange) other;
                return this.minRatio == splitRatioDragRange.minRatio && this.maxRatio == splitRatioDragRange.maxRatio;
            }

            public final float getMaxRatio() {
                return this.maxRatio;
            }

            public final float getMinRatio() {
                return this.minRatio;
            }

            public int hashCode() {
                return (Float.hashCode(this.minRatio) * 31) + Float.hashCode(this.maxRatio);
            }

            public String toString() {
                return "SplitRatioDragRange[" + this.minRatio + ", " + this.maxRatio + ']';
            }
        }

        public /* synthetic */ DragRange(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private DragRange() {
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\fB\u001d\b\u0003\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes;", "Landroidx/window/embedding/DividerAttributes;", "widthDp", "", "color", "<init>", "(II)V", "equals", "", "other", "", "hashCode", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class FixedDividerAttributes extends DividerAttributes {
        public /* synthetic */ FixedDividerAttributes(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -16777216 : i2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FixedDividerAttributes)) {
                return false;
            }
            FixedDividerAttributes fixedDividerAttributes = (FixedDividerAttributes) other;
            return getWidthDp() == fixedDividerAttributes.getWidthDp() && getColor() == fixedDividerAttributes.getColor();
        }

        public int hashCode() {
            return (getWidthDp() * 31) + getColor();
        }

        public /* synthetic */ FixedDividerAttributes(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2);
        }

        private FixedDividerAttributes(int i, int i2) {
            super(i, i2, null);
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0012\u0010\n\u001a\u00020\u00002\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\u000b\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\bH\u0007J\b\u0010\f\u001a\u00020\u0005H\u0007R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes$Builder;", "", "<init>", "()V", "original", "Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes;", "(Landroidx/window/embedding/DividerAttributes$FixedDividerAttributes;)V", "widthDp", "", "color", "setWidthDp", "setColor", "build", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Builder {
            private int color;
            private int widthDp;

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public Builder(FixedDividerAttributes fixedDividerAttributes) {
                this();
                fixedDividerAttributes.getClass();
                this.widthDp = fixedDividerAttributes.getWidthDp();
                this.color = fixedDividerAttributes.getColor();
            }

            public final FixedDividerAttributes build() {
                return new FixedDividerAttributes(this.widthDp, this.color, null);
            }

            public final Builder setColor(int color) {
                DividerAttributes.INSTANCE.validateColor(color);
                this.color = color;
                return this;
            }

            public final Builder setWidthDp(int widthDp) {
                DividerAttributes.INSTANCE.validateWidth(widthDp);
                this.widthDp = widthDp;
                return this;
            }

            public Builder() {
                this.widthDp = -1;
                this.color = -16777216;
            }
        }
    }

    private DividerAttributes(int i, int i2) {
        this.widthDp = i;
        this.color = i2;
    }

    public /* synthetic */ DividerAttributes(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2);
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B1\b\u0003\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\r¨\u0006\u0015"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes;", "Landroidx/window/embedding/DividerAttributes;", "widthDp", "", "color", "dragRange", "Landroidx/window/embedding/DividerAttributes$DragRange;", "isDraggingToFullscreenAllowed", "", "<init>", "(IILandroidx/window/embedding/DividerAttributes$DragRange;Z)V", "getDragRange", "()Landroidx/window/embedding/DividerAttributes$DragRange;", "()Z", "equals", "other", "", "hashCode", "toString", "", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class DraggableDividerAttributes extends DividerAttributes {

        /* JADX INFO: renamed from: dragRange, reason: from kotlin metadata and from toString */
        private final DragRange primaryContainerDragRange;
        private final boolean isDraggingToFullscreenAllowed;

        public /* synthetic */ DraggableDividerAttributes(int i, int i2, DragRange dragRange, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -16777216 : i2, (i3 & 4) != 0 ? DragRange.DRAG_RANGE_SYSTEM_DEFAULT : dragRange, (i3 & 8) != 0 ? false : z);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DraggableDividerAttributes)) {
                return false;
            }
            DraggableDividerAttributes draggableDividerAttributes = (DraggableDividerAttributes) other;
            return getWidthDp() == draggableDividerAttributes.getWidthDp() && getColor() == draggableDividerAttributes.getColor() && Intrinsics.areEqual(this.primaryContainerDragRange, draggableDividerAttributes.primaryContainerDragRange) && this.isDraggingToFullscreenAllowed == draggableDividerAttributes.isDraggingToFullscreenAllowed;
        }

        /* JADX INFO: renamed from: getDragRange, reason: from getter */
        public final DragRange getPrimaryContainerDragRange() {
            return this.primaryContainerDragRange;
        }

        public int hashCode() {
            return (((((getWidthDp() * 31) + getColor()) * 31) + this.primaryContainerDragRange.hashCode()) * 31) + Boolean.hashCode(this.isDraggingToFullscreenAllowed);
        }

        /* JADX INFO: renamed from: isDraggingToFullscreenAllowed, reason: from getter */
        public final boolean getIsDraggingToFullscreenAllowed() {
            return this.isDraggingToFullscreenAllowed;
        }

        @Override // androidx.window.embedding.DividerAttributes
        public String toString() {
            return DraggableDividerAttributes.class.getSimpleName() + "{width=" + getWidthDp() + ", color=" + getColor() + ", primaryContainerDragRange=" + this.primaryContainerDragRange + ", isDraggingToFullscreenAllowed=" + this.isDraggingToFullscreenAllowed + '}';
        }

        public /* synthetic */ DraggableDividerAttributes(int i, int i2, DragRange dragRange, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, dragRange, z);
        }

        private DraggableDividerAttributes(int i, int i2, DragRange dragRange, boolean z) {
            super(i, i2, null);
            this.primaryContainerDragRange = dragRange;
            this.isDraggingToFullscreenAllowed = z;
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0012\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\u000f\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\bH\u0007J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\rH\u0007J\b\u0010\u0013\u001a\u00020\u0005H\u0007R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes$Builder;", "", "<init>", "()V", "original", "Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes;", "(Landroidx/window/embedding/DividerAttributes$DraggableDividerAttributes;)V", "widthDp", "", "color", "dragRange", "Landroidx/window/embedding/DividerAttributes$DragRange;", "isDraggingToFullscreenAllowed", "", "setWidthDp", "setColor", "setDragRange", "setDraggingToFullscreenAllowed", "allowed", "build", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Builder {
            private int color;
            private DragRange dragRange;
            private boolean isDraggingToFullscreenAllowed;
            private int widthDp;

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public Builder(DraggableDividerAttributes draggableDividerAttributes) {
                this();
                draggableDividerAttributes.getClass();
                this.widthDp = draggableDividerAttributes.getWidthDp();
                this.dragRange = draggableDividerAttributes.getPrimaryContainerDragRange();
                this.color = draggableDividerAttributes.getColor();
                this.isDraggingToFullscreenAllowed = draggableDividerAttributes.getIsDraggingToFullscreenAllowed();
            }

            public final DraggableDividerAttributes build() {
                return new DraggableDividerAttributes(this.widthDp, this.color, this.dragRange, this.isDraggingToFullscreenAllowed, null);
            }

            public final Builder setColor(int color) {
                DividerAttributes.INSTANCE.validateColor(color);
                this.color = color;
                return this;
            }

            public final Builder setDragRange(DragRange dragRange) {
                dragRange.getClass();
                this.dragRange = dragRange;
                return this;
            }

            public final Builder setDraggingToFullscreenAllowed(boolean allowed) {
                this.isDraggingToFullscreenAllowed = allowed;
                return this;
            }

            public final Builder setWidthDp(int widthDp) {
                DividerAttributes.INSTANCE.validateWidth(widthDp);
                this.widthDp = widthDp;
                return this;
            }

            public Builder() {
                this.widthDp = -1;
                this.color = -16777216;
                this.dragRange = DragRange.DRAG_RANGE_SYSTEM_DEFAULT;
            }
        }
    }
}
