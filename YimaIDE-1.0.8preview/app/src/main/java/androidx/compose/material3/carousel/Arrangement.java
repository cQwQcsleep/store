package androidx.compose.material3.carousel;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0001\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0006\u0010\u0019\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/carousel/Arrangement;", "", "priority", "", "smallSize", "", "smallCount", "mediumSize", "mediumCount", "largeSize", "largeCount", "<init>", "(IFIFIFI)V", "getSmallSize", "()F", "getSmallCount", "()I", "getMediumSize", "getMediumCount", "getLargeSize", "getLargeCount", "isValid", "", "cost", "targetLargeSize", "itemCount", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Arrangement {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float MediumItemFlexPercentage = 0.1f;
    private final int largeCount;
    private final float largeSize;
    private final int mediumCount;
    private final float mediumSize;
    private final int priority;
    private final int smallCount;
    private final float smallSize;

    public Arrangement(int i, float f, int i2, float f2, int i3, float f3, int i4) {
        this.priority = i;
        this.smallSize = f;
        this.smallCount = i2;
        this.mediumSize = f2;
        this.mediumCount = i3;
        this.largeSize = f3;
        this.largeCount = i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float cost(float targetLargeSize) {
        if (isValid()) {
            return Math.abs(targetLargeSize - this.largeSize) * this.priority;
        }
        return Float.MAX_VALUE;
    }

    private final boolean isValid() {
        int i = this.largeCount;
        if (i <= 0 || this.smallCount <= 0 || this.mediumCount <= 0) {
            return i <= 0 || this.smallCount <= 0 || this.largeSize > this.smallSize;
        }
        float f = this.largeSize;
        float f2 = this.mediumSize;
        return f > f2 && f2 > this.smallSize;
    }

    public final int getLargeCount() {
        return this.largeCount;
    }

    public final float getLargeSize() {
        return this.largeSize;
    }

    public final int getMediumCount() {
        return this.mediumCount;
    }

    public final float getMediumSize() {
        return this.mediumSize;
    }

    public final int getSmallCount() {
        return this.smallCount;
    }

    public final float getSmallSize() {
        return this.smallSize;
    }

    public final int itemCount() {
        return this.largeCount + this.mediumCount + this.smallCount;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000eJ`\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J0\u0010\u001c\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/carousel/Arrangement$Companion;", "", "<init>", "()V", "MediumItemFlexPercentage", "", "findLowestCostArrangement", "Landroidx/compose/material3/carousel/Arrangement;", "availableSpace", "itemSpacing", "targetSmallSize", "minSmallSize", "maxSmallSize", "smallCounts", "", "targetMediumSize", "mediumCounts", "targetLargeSize", "largeCounts", "fit", "priority", "", "smallCount", "smallSize", "mediumCount", "mediumSize", "largeCount", "largeSize", "calculateLargeSize", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final float calculateLargeSize(float availableSpace, int smallCount, float smallSize, int mediumCount, int largeCount) {
            float f = smallCount;
            float f2 = mediumCount / 2.0f;
            return (availableSpace - ((f + f2) * smallSize)) / (largeCount + f2);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x003e  */
        /* JADX WARN: Code duplicated, block: B:15:0x0042  */
        /* JADX WARN: Code duplicated, block: B:23:0x0069  */
        /* JADX WARN: Code duplicated, block: B:25:0x0071  */
        private final Arrangement fit(int priority, float availableSpace, float itemSpacing, int smallCount, float smallSize, float minSmallSize, float maxSmallSize, int mediumCount, float mediumSize, int largeCount, float largeSize) {
            float fMax;
            float f;
            float fCalculateLargeSize;
            float f2;
            float f3;
            float fMin;
            float f4 = availableSpace - ((((largeCount + mediumCount) + smallCount) - 1) * itemSpacing);
            float fCoerceIn = RangesKt.coerceIn(smallSize, minSmallSize, maxSmallSize);
            float f5 = largeCount;
            float f6 = mediumCount;
            float f7 = smallCount;
            float f8 = f4 - (((largeSize * f5) + (mediumSize * f6)) + (fCoerceIn * f7));
            if (smallCount <= 0 || f8 <= 0.0f) {
                if (smallCount > 0 && f8 < 0.0f) {
                    fMax = Math.max(f8 / f7, minSmallSize - fCoerceIn);
                }
                if (smallCount > 0) {
                    f = fCoerceIn;
                } else {
                    f = 0.0f;
                }
                fCalculateLargeSize = calculateLargeSize(f4, smallCount, f, mediumCount, largeCount);
                float f9 = f;
                f2 = (fCalculateLargeSize + f9) / 2.0f;
                if (mediumCount > 0 && fCalculateLargeSize != largeSize) {
                    f3 = (largeSize - fCalculateLargeSize) * f5;
                    fMin = Math.min(Math.abs(f3), 0.1f * f2 * f6);
                    if (f3 > 0.0f) {
                        f2 -= fMin / f6;
                        fCalculateLargeSize += fMin / f5;
                    } else {
                        f2 += fMin / f6;
                        fCalculateLargeSize -= fMin / f5;
                    }
                }
                return new Arrangement(priority, f9, smallCount, f2, mediumCount, fCalculateLargeSize, largeCount);
            }
            fMax = Math.min(f8 / f7, maxSmallSize - fCoerceIn);
            fCoerceIn += fMax;
            if (smallCount > 0) {
                f = fCoerceIn;
            } else {
                f = 0.0f;
            }
            fCalculateLargeSize = calculateLargeSize(f4, smallCount, f, mediumCount, largeCount);
            float f10 = f;
            f2 = (fCalculateLargeSize + f10) / 2.0f;
            if (mediumCount > 0) {
                f3 = (largeSize - fCalculateLargeSize) * f5;
                fMin = Math.min(Math.abs(f3), 0.1f * f2 * f6);
                if (f3 > 0.0f) {
                    f2 -= fMin / f6;
                    fCalculateLargeSize += fMin / f5;
                } else {
                    f2 += fMin / f6;
                    fCalculateLargeSize -= fMin / f5;
                }
            }
            return new Arrangement(priority, f10, smallCount, f2, mediumCount, fCalculateLargeSize, largeCount);
        }

        public final Arrangement findLowestCostArrangement(float availableSpace, float itemSpacing, float targetSmallSize, float minSmallSize, float maxSmallSize, int[] smallCounts, float targetMediumSize, int[] mediumCounts, float targetLargeSize, int[] largeCounts) {
            int length = largeCounts.length;
            Arrangement arrangement = null;
            int i = 1;
            int i2 = 0;
            while (i2 < length) {
                int i3 = largeCounts[i2];
                int length2 = mediumCounts.length;
                int i4 = 0;
                while (i4 < length2) {
                    int i5 = mediumCounts[i4];
                    int length3 = smallCounts.length;
                    int i6 = 0;
                    while (i6 < length3) {
                        int i7 = i4;
                        int i8 = smallCounts[i6];
                        Arrangement arrangement2 = arrangement;
                        int i9 = i2;
                        int i10 = length2;
                        int i11 = length3;
                        int i12 = i6;
                        Arrangement arrangementFit = fit(i, availableSpace, itemSpacing, i8, targetSmallSize, minSmallSize, maxSmallSize, i5, targetMediumSize, i3, targetLargeSize);
                        if (arrangement2 != null && arrangementFit.cost(targetLargeSize) >= arrangement2.cost(targetLargeSize)) {
                            arrangement = arrangement2;
                        } else {
                            if (arrangementFit.cost(targetLargeSize) == 0.0f) {
                                return arrangementFit;
                            }
                            arrangement = arrangementFit;
                        }
                        i++;
                        i6 = i12 + 1;
                        smallCounts = smallCounts;
                        i2 = i9;
                        length2 = i10;
                        i4 = i7;
                        length3 = i11;
                    }
                    i4++;
                    smallCounts = smallCounts;
                }
                i2++;
            }
            return arrangement;
        }

        private Companion() {
        }
    }
}
