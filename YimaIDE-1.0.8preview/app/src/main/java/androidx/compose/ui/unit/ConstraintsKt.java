package androidx.compose.ui.unit;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0013\u001a\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a-\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010 \u001a\u0010\u0010!\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a\u0011\u0010\"\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0082\b\u001a5\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010 \u001a\u0019\u0010$\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b¢\u0006\u0004\b&\u0010'\u001a\u001b\u0010$\u001a\u00020(*\u00020\u001b2\u0006\u0010\u0019\u001a\u00020(H\u0007¢\u0006\u0004\b)\u0010'\u001a\u001b\u0010*\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010+\u001a\u00020\u0001H\u0007¢\u0006\u0004\b,\u0010-\u001a\u001b\u0010.\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010/\u001a\u00020\u0001H\u0007¢\u0006\u0004\b0\u0010-\u001a\u001b\u00101\u001a\u000202*\u00020\u001b2\u0006\u0010\u0019\u001a\u00020(H\u0007¢\u0006\u0004\b3\u00104\u001a'\u00105\u001a\u00020\u001b*\u00020\u001b2\b\b\u0002\u00106\u001a\u00020\u00012\b\b\u0002\u00107\u001a\u00020\u0001H\u0007¢\u0006\u0004\b8\u00109\u001a\u0019\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010?\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010C\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010D\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0010\u001a\u00020\u00038\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0012¨\u0006E"}, d2 = {"Infinity", "", "FocusMask", "", "MinFocusBits", "MaxAllowedForMinFocusBits", "MinFocusMask", "MinNonFocusBits", "MaxAllowedForMinNonFocusBits", "MinNonFocusMask", "MaxFocusBits", "MaxAllowedForMaxFocusBits", "MaxFocusMask", "MaxNonFocusBits", "MaxAllowedForMaxNonFocusBits", "MaxNonFocusMask", "MaxDimensionsAndFocusMask", "getMaxDimensionsAndFocusMask$annotations", "()V", "throwInvalidConstraintException", "", "widthVal", "heightVal", "throwInvalidConstraintsSizeException", "", "size", "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "minWidth", "maxWidth", "minHeight", "maxHeight", "(IIII)J", "bitsNeedForSizeUnchecked", "maxAllowedForSize", "Constraints", "constrain", "otherConstraints", "constrain-N9IONVI", "(JJ)J", "Landroidx/compose/ui/unit/IntSize;", "constrain-4WqzIAM", "constrainWidth", "width", "constrainWidth-K40F9xA", "(JI)I", "constrainHeight", "height", "constrainHeight-K40F9xA", "isSatisfiedBy", "", "isSatisfiedBy-4WqzIAM", "(JJ)Z", "offset", "horizontal", "vertical", "offset-NN6Ew-U", "(JII)J", "addMaxWithMinimum", "max", "value", "indexToBitOffset", "index", "bitOffsetToIndex", "bits", "minHeightOffsets", "bitOffset", "widthMask", "heightMask", "ui-unit"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ConstraintsKt {
    private static final long FocusMask = 3;
    private static final int Infinity = Integer.MAX_VALUE;
    private static final int MaxAllowedForMaxFocusBits = 8190;
    private static final int MaxAllowedForMaxNonFocusBits = 262142;
    private static final int MaxAllowedForMinFocusBits = 32766;
    private static final int MaxAllowedForMinNonFocusBits = 65534;
    public static final long MaxDimensionsAndFocusMask = -8589934589L;
    private static final int MaxFocusBits = 18;
    private static final int MaxFocusMask = 262143;
    private static final int MaxNonFocusBits = 13;
    private static final int MaxNonFocusMask = 8191;
    private static final int MinFocusBits = 16;
    private static final int MinFocusMask = 65535;
    private static final int MinNonFocusBits = 15;
    private static final int MinNonFocusMask = 32767;

    public static final long Constraints(int i, int i2, int i3, int i4) {
        if (!((i3 >= 0) & (i2 >= i) & (i4 >= i3) & (i >= 0))) {
            InlineClassHelperKt.throwIllegalArgumentException("maxWidth must be >= than minWidth,\nmaxHeight must be >= than minHeight,\nminWidth and minHeight must be >= 0");
        }
        return createConstraints(i, i2, i3, i4);
    }

    public static /* synthetic */ long Constraints$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return Constraints(i, i2, i3, i4);
    }

    private static final int addMaxWithMinimum(int i, int i2) {
        if (i == Integer.MAX_VALUE) {
            return i;
        }
        int i3 = i + i2;
        if (i3 < 0) {
            return 0;
        }
        return i3;
    }

    private static final int bitOffsetToIndex(int i) {
        return (i >> 1) + (i & 1);
    }

    public static final int bitsNeedForSizeUnchecked(int i) {
        if (i < MaxNonFocusMask) {
            return 13;
        }
        if (i < 32767) {
            return 15;
        }
        if (i < 65535) {
            return 16;
        }
        return i < MaxFocusMask ? 18 : 255;
    }

    /* JADX INFO: renamed from: constrain-4WqzIAM, reason: not valid java name */
    public static final long m5989constrain4WqzIAM(long j, long j2) {
        int i = (int) (j2 >> 32);
        int iM5977getMinWidthimpl = Constraints.m5977getMinWidthimpl(j);
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        if (i < iM5977getMinWidthimpl) {
            i = iM5977getMinWidthimpl;
        }
        if (i <= iM5975getMaxWidthimpl) {
            iM5975getMaxWidthimpl = i;
        }
        int i2 = (int) (j2 & 4294967295L);
        int iM5976getMinHeightimpl = Constraints.m5976getMinHeightimpl(j);
        int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
        if (i2 < iM5976getMinHeightimpl) {
            i2 = iM5976getMinHeightimpl;
        }
        if (i2 <= iM5974getMaxHeightimpl) {
            iM5974getMaxHeightimpl = i2;
        }
        return IntSize.m6188constructorimpl((((long) iM5975getMaxWidthimpl) << 32) | (((long) iM5974getMaxHeightimpl) & 4294967295L));
    }

    /* JADX INFO: renamed from: constrain-N9IONVI, reason: not valid java name */
    public static final long m5990constrainN9IONVI(long j, long j2) {
        int iM5977getMinWidthimpl = Constraints.m5977getMinWidthimpl(j);
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        int iM5976getMinHeightimpl = Constraints.m5976getMinHeightimpl(j);
        int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
        int iM5977getMinWidthimpl2 = Constraints.m5977getMinWidthimpl(j2);
        if (iM5977getMinWidthimpl2 < iM5977getMinWidthimpl) {
            iM5977getMinWidthimpl2 = iM5977getMinWidthimpl;
        }
        if (iM5977getMinWidthimpl2 > iM5975getMaxWidthimpl) {
            iM5977getMinWidthimpl2 = iM5975getMaxWidthimpl;
        }
        int iM5975getMaxWidthimpl2 = Constraints.m5975getMaxWidthimpl(j2);
        if (iM5975getMaxWidthimpl2 >= iM5977getMinWidthimpl) {
            iM5977getMinWidthimpl = iM5975getMaxWidthimpl2;
        }
        if (iM5977getMinWidthimpl <= iM5975getMaxWidthimpl) {
            iM5975getMaxWidthimpl = iM5977getMinWidthimpl;
        }
        int iM5976getMinHeightimpl2 = Constraints.m5976getMinHeightimpl(j2);
        if (iM5976getMinHeightimpl2 < iM5976getMinHeightimpl) {
            iM5976getMinHeightimpl2 = iM5976getMinHeightimpl;
        }
        if (iM5976getMinHeightimpl2 > iM5974getMaxHeightimpl) {
            iM5976getMinHeightimpl2 = iM5974getMaxHeightimpl;
        }
        int iM5974getMaxHeightimpl2 = Constraints.m5974getMaxHeightimpl(j2);
        if (iM5974getMaxHeightimpl2 >= iM5976getMinHeightimpl) {
            iM5976getMinHeightimpl = iM5974getMaxHeightimpl2;
        }
        if (iM5976getMinHeightimpl <= iM5974getMaxHeightimpl) {
            iM5974getMaxHeightimpl = iM5976getMinHeightimpl;
        }
        return Constraints(iM5977getMinWidthimpl2, iM5975getMaxWidthimpl, iM5976getMinHeightimpl2, iM5974getMaxHeightimpl);
    }

    /* JADX INFO: renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final int m5991constrainHeightK40F9xA(long j, int i) {
        int iM5976getMinHeightimpl = Constraints.m5976getMinHeightimpl(j);
        int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
        if (i < iM5976getMinHeightimpl) {
            i = iM5976getMinHeightimpl;
        }
        return i > iM5974getMaxHeightimpl ? iM5974getMaxHeightimpl : i;
    }

    /* JADX INFO: renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final int m5992constrainWidthK40F9xA(long j, int i) {
        int iM5977getMinWidthimpl = Constraints.m5977getMinWidthimpl(j);
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        if (i < iM5977getMinWidthimpl) {
            i = iM5977getMinWidthimpl;
        }
        return i > iM5975getMaxWidthimpl ? iM5975getMaxWidthimpl : i;
    }

    public static final long createConstraints(int i, int i2, int i3, int i4) {
        int i5 = i4 == Integer.MAX_VALUE ? i3 : i4;
        int iBitsNeedForSizeUnchecked = bitsNeedForSizeUnchecked(i5);
        int i6 = i2 == Integer.MAX_VALUE ? i : i2;
        int iBitsNeedForSizeUnchecked2 = bitsNeedForSizeUnchecked(i6);
        if (iBitsNeedForSizeUnchecked + iBitsNeedForSizeUnchecked2 > 31) {
            throwInvalidConstraintException(i6, i5);
        }
        int i7 = i2 + 1;
        int i8 = i4 + 1;
        int i9 = iBitsNeedForSizeUnchecked2 - 13;
        return Constraints.m5963constructorimpl((((long) (i7 & (~(i7 >> 31)))) << 33) | ((long) ((i9 >> 1) + (i9 & 1))) | (((long) i) << 2) | (((long) i3) << (iBitsNeedForSizeUnchecked2 + 2)) | (((long) (i8 & (~(i8 >> 31)))) << (iBitsNeedForSizeUnchecked2 + 33)));
    }

    public static /* synthetic */ void getMaxDimensionsAndFocusMask$annotations() {
    }

    private static final int heightMask(int i) {
        return (1 << (18 - i)) - 1;
    }

    private static final int indexToBitOffset(int i) {
        return ((i & 1) << 1) + (((i & 2) >> 1) * 3);
    }

    /* JADX INFO: renamed from: isSatisfiedBy-4WqzIAM, reason: not valid java name */
    public static final boolean m5993isSatisfiedBy4WqzIAM(long j, long j2) {
        int iM5977getMinWidthimpl = Constraints.m5977getMinWidthimpl(j);
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        int i = (int) (j2 >> 32);
        if (iM5977getMinWidthimpl > i || i > iM5975getMaxWidthimpl) {
            return false;
        }
        int i2 = (int) (j2 & 4294967295L);
        return Constraints.m5976getMinHeightimpl(j) <= i2 && i2 <= Constraints.m5974getMaxHeightimpl(j);
    }

    private static final int maxAllowedForSize(int i) {
        if (i < MaxNonFocusMask) {
            return MaxAllowedForMaxNonFocusBits;
        }
        if (i < 32767) {
            return MaxAllowedForMinNonFocusBits;
        }
        if (i < 65535) {
            return MaxAllowedForMinFocusBits;
        }
        if (i < MaxFocusMask) {
            return MaxAllowedForMaxFocusBits;
        }
        throwInvalidConstraintsSizeException(i);
        wq6.a();
        return 0;
    }

    private static final int minHeightOffsets(int i) {
        return i + 15;
    }

    /* JADX INFO: renamed from: offset-NN6Ew-U, reason: not valid java name */
    public static final long m5994offsetNN6EwU(long j, int i, int i2) {
        int iM5977getMinWidthimpl = Constraints.m5977getMinWidthimpl(j) + i;
        if (iM5977getMinWidthimpl < 0) {
            iM5977getMinWidthimpl = 0;
        }
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        if (iM5975getMaxWidthimpl != Integer.MAX_VALUE && (iM5975getMaxWidthimpl = iM5975getMaxWidthimpl + i) < 0) {
            iM5975getMaxWidthimpl = 0;
        }
        int iM5976getMinHeightimpl = Constraints.m5976getMinHeightimpl(j) + i2;
        if (iM5976getMinHeightimpl < 0) {
            iM5976getMinHeightimpl = 0;
        }
        int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
        return Constraints(iM5977getMinWidthimpl, iM5975getMaxWidthimpl, iM5976getMinHeightimpl, (iM5974getMaxHeightimpl == Integer.MAX_VALUE || (iM5974getMaxHeightimpl = iM5974getMaxHeightimpl + i2) >= 0) ? iM5974getMaxHeightimpl : 0);
    }

    /* JADX INFO: renamed from: offset-NN6Ew-U$default, reason: not valid java name */
    public static /* synthetic */ long m5995offsetNN6EwU$default(long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m5994offsetNN6EwU(j, i, i2);
    }

    public static final void throwInvalidConstraintException(int i, int i2) {
        throw new IllegalArgumentException("Can't represent a width of " + i + " and height of " + i2 + " in Constraints");
    }

    public static final Void throwInvalidConstraintsSizeException(int i) {
        throw new IllegalArgumentException("Can't represent a size of " + i + " in Constraints");
    }

    private static final int widthMask(int i) {
        return (1 << (i + 13)) - 1;
    }
}
