package androidx.compose.material3.carousel;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006!"}, d2 = {"Landroidx/compose/material3/carousel/Keyline;", "", "size", "", "offset", "unadjustedOffset", "isFocal", "", "isAnchor", "isPivot", "cutoff", "<init>", "(FFFZZZF)V", "getSize", "()F", "getOffset", "getUnadjustedOffset", "()Z", "getCutoff", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final /* data */ class Keyline {
    public static final int $stable = 0;
    private final float cutoff;
    private final boolean isAnchor;
    private final boolean isFocal;
    private final boolean isPivot;
    private final float offset;
    private final float size;
    private final float unadjustedOffset;

    public Keyline(float f, float f2, float f3, boolean z, boolean z2, boolean z3, float f4) {
        this.size = f;
        this.offset = f2;
        this.unadjustedOffset = f3;
        this.isFocal = z;
        this.isAnchor = z2;
        this.isPivot = z3;
        this.cutoff = f4;
    }

    public static /* synthetic */ Keyline copy$default(Keyline keyline, float f, float f2, float f3, boolean z, boolean z2, boolean z3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = keyline.size;
        }
        if ((i & 2) != 0) {
            f2 = keyline.offset;
        }
        if ((i & 4) != 0) {
            f3 = keyline.unadjustedOffset;
        }
        if ((i & 8) != 0) {
            z = keyline.isFocal;
        }
        if ((i & 16) != 0) {
            z2 = keyline.isAnchor;
        }
        if ((i & 32) != 0) {
            z3 = keyline.isPivot;
        }
        if ((i & 64) != 0) {
            f4 = keyline.cutoff;
        }
        boolean z4 = z3;
        float f5 = f4;
        boolean z5 = z2;
        float f6 = f3;
        return keyline.copy(f, f2, f6, z, z5, z4, f5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getUnadjustedOffset() {
        return this.unadjustedOffset;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsFocal() {
        return this.isFocal;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsAnchor() {
        return this.isAnchor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsPivot() {
        return this.isPivot;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getCutoff() {
        return this.cutoff;
    }

    public final Keyline copy(float size, float offset, float unadjustedOffset, boolean isFocal, boolean isAnchor, boolean isPivot, float cutoff) {
        return new Keyline(size, offset, unadjustedOffset, isFocal, isAnchor, isPivot, cutoff);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Keyline)) {
            return false;
        }
        Keyline keyline = (Keyline) other;
        return Float.compare(this.size, keyline.size) == 0 && Float.compare(this.offset, keyline.offset) == 0 && Float.compare(this.unadjustedOffset, keyline.unadjustedOffset) == 0 && this.isFocal == keyline.isFocal && this.isAnchor == keyline.isAnchor && this.isPivot == keyline.isPivot && Float.compare(this.cutoff, keyline.cutoff) == 0;
    }

    public final float getCutoff() {
        return this.cutoff;
    }

    public final float getOffset() {
        return this.offset;
    }

    public final float getSize() {
        return this.size;
    }

    public final float getUnadjustedOffset() {
        return this.unadjustedOffset;
    }

    public int hashCode() {
        return (((((((((((Float.hashCode(this.size) * 31) + Float.hashCode(this.offset)) * 31) + Float.hashCode(this.unadjustedOffset)) * 31) + Boolean.hashCode(this.isFocal)) * 31) + Boolean.hashCode(this.isAnchor)) * 31) + Boolean.hashCode(this.isPivot)) * 31) + Float.hashCode(this.cutoff);
    }

    public final boolean isAnchor() {
        return this.isAnchor;
    }

    public final boolean isFocal() {
        return this.isFocal;
    }

    public final boolean isPivot() {
        return this.isPivot;
    }

    public String toString() {
        return "Keyline(size=" + this.size + ", offset=" + this.offset + ", unadjustedOffset=" + this.unadjustedOffset + ", isFocal=" + this.isFocal + ", isAnchor=" + this.isAnchor + ", isPivot=" + this.isPivot + ", cutoff=" + this.cutoff + ')';
    }
}
