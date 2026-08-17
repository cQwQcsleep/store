package androidx.compose.material3.carousel;

import androidx.compose.material3.carousel.KeylineListScope;
import androidx.compose.material3.carousel.KeylinesKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0000\u001a(\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0000\u001aU\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0002\u0010\u0012\u001a0\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\u001a0\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\u001a \u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0002¨\u0006\u001d"}, d2 = {"multiBrowseKeylineList", "Landroidx/compose/material3/carousel/KeylineList;", "density", "Landroidx/compose/ui/unit/Density;", "carouselMainAxisSize", "", "preferredItemSize", "itemSpacing", "itemCount", "", "minSmallItemSize", "maxSmallItemSize", "uncontainedKeylineList", "itemSize", "heroKeylineList", "maxItemSize", "isCentered", "", "(Landroidx/compose/ui/unit/Density;FLjava/lang/Float;FIZFF)Landroidx/compose/material3/carousel/KeylineList;", "createLeftAlignedKeylineList", "leftAnchorSize", "rightAnchorSize", "arrangement", "Landroidx/compose/material3/carousel/Arrangement;", "createCenterAlignedKeylineList", "calculateMediumChildSize", "minimumMediumSize", "largeItemSize", "remainingSpace", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class KeylinesKt {
    public static Unit a(float f, Arrangement arrangement, float f2, KeylineListScope keylineListScope) {
        keylineListScope.add(f, true);
        int largeCount = arrangement.getLargeCount();
        for (int i = 0; i < largeCount; i++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getLargeSize(), false, 2, null);
        }
        int mediumCount = arrangement.getMediumCount();
        for (int i2 = 0; i2 < mediumCount; i2++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getMediumSize(), false, 2, null);
        }
        int smallCount = arrangement.getSmallCount();
        for (int i3 = 0; i3 < smallCount; i3++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getSmallSize(), false, 2, null);
        }
        keylineListScope.add(f2, true);
        return Unit.INSTANCE;
    }

    public static Unit b(float f, Arrangement arrangement, float f2, KeylineListScope keylineListScope) {
        keylineListScope.add(f, true);
        int smallCount = arrangement.getSmallCount() / 2;
        for (int i = 0; i < smallCount; i++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getSmallSize(), false, 2, null);
        }
        int mediumCount = arrangement.getMediumCount() / 2;
        for (int i2 = 0; i2 < mediumCount; i2++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getMediumSize(), false, 2, null);
        }
        int largeCount = arrangement.getLargeCount();
        for (int i3 = 0; i3 < largeCount; i3++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getLargeSize(), false, 2, null);
        }
        int mediumCount2 = arrangement.getMediumCount() / 2;
        for (int i4 = 0; i4 < mediumCount2; i4++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getMediumSize(), false, 2, null);
        }
        int smallCount2 = arrangement.getSmallCount() / 2;
        for (int i5 = 0; i5 < smallCount2; i5++) {
            KeylineListScope.add$default(keylineListScope, arrangement.getSmallSize(), false, 2, null);
        }
        keylineListScope.add(f2, true);
        return Unit.INSTANCE;
    }

    private static final float calculateMediumChildSize(float f, float f2, float f3) {
        float fMax = Math.max(1.5f * f3, f);
        float f4 = 0.85f * f2;
        return fMax > f4 ? Math.min(Math.max(f4, f3 * 1.2f), f2) : fMax;
    }

    public static final KeylineList createCenterAlignedKeylineList(float f, float f2, final float f3, final float f4, final Arrangement arrangement) {
        return KeylineListKt.m1365keylineListOfWNYm7Xg(f, f2, CarouselAlignment.INSTANCE.m1349getCenterNUL3oTo(), new Function1() { // from class: q78
            public final Object invoke(Object obj) {
                return KeylinesKt.b(f3, arrangement, f4, (KeylineListScope) obj);
            }
        });
    }

    public static final KeylineList createLeftAlignedKeylineList(float f, float f2, final float f3, final float f4, final Arrangement arrangement) {
        return KeylineListKt.m1365keylineListOfWNYm7Xg(f, f2, CarouselAlignment.INSTANCE.m1351getStartNUL3oTo(), new Function1() { // from class: p78
            public final Object invoke(Object obj) {
                return KeylinesKt.a(f3, arrangement, f4, (KeylineListScope) obj);
            }
        });
    }

    public static final KeylineList heroKeylineList(Density density, float f, Float f2, float f3, int i, boolean z, float f4, float f5) {
        if (f == 0.0f) {
            return KeylineListKt.emptyKeylineList();
        }
        boolean z2 = z && i >= 3;
        int[] iArr = new int[1];
        if (z2) {
            iArr[0] = 2;
        } else {
            iArr[0] = 1;
        }
        float fMin = Math.min(f2 != null ? f2.floatValue() : f, f);
        float fCoerceIn = RangesKt.coerceIn(fMin / 3.0f, f4, f5);
        if (f < (ArraysKt.maxOrThrow(iArr) * f4) + (1.25f * f4)) {
            iArr = new int[]{0};
        }
        int iMax = Math.max(1, (int) Math.floor((f - (ArraysKt.maxOrThrow(iArr) * f4)) / fMin));
        int iCeil = (int) Math.ceil(f / fMin);
        int i2 = (iCeil - iMax) + 1;
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr2[i3] = iCeil - i3;
        }
        float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1352getAnchorSizeD9Ej5fM$material3());
        Arrangement arrangementFindLowestCostArrangement = Arrangement.INSTANCE.findLowestCostArrangement(f, f3, fCoerceIn, f4, f5, iArr, 0.0f, new int[]{0}, fMin, iArr2);
        if (arrangementFindLowestCostArrangement == null) {
            return KeylineListKt.emptyKeylineList();
        }
        return (!z2 || i < arrangementFindLowestCostArrangement.itemCount()) ? createLeftAlignedKeylineList(f, f3, fMo4557toPx0680j_4, fMo4557toPx0680j_4, arrangementFindLowestCostArrangement) : createCenterAlignedKeylineList(f, f3, fMo4557toPx0680j_4, fMo4557toPx0680j_4, arrangementFindLowestCostArrangement);
    }

    public static /* synthetic */ KeylineList heroKeylineList$default(Density density, float f, Float f2, float f3, int i, boolean z, float f4, float f5, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i2 & 64) != 0) {
            f4 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1354getMinSmallItemSizeD9Ej5fM());
        }
        float f6 = f4;
        if ((i2 & 128) != 0) {
            f5 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1353getMaxSmallItemSizeD9Ej5fM());
        }
        return heroKeylineList(density, f, f2, f3, i, z2, f6, f5);
    }

    public static final KeylineList multiBrowseKeylineList(Density density, float f, float f2, float f3, int i, float f4, float f5) {
        float f6;
        if (f == 0.0f || f2 == 0.0f) {
            return KeylineListKt.emptyKeylineList();
        }
        int[] iArr = {1};
        int[] iArr2 = {1, 0};
        float fMin = Math.min(f2, f);
        float fCoerceIn = RangesKt.coerceIn(fMin / 3.0f, f4, f5);
        float f7 = (fMin + fCoerceIn) / 2.0f;
        if (f < 2.0f * f4) {
            iArr = new int[]{0};
        }
        int iMax = Math.max(1, (int) Math.floor(((f - (ArraysKt.maxOrThrow(iArr2) * f7)) - (ArraysKt.maxOrThrow(iArr) * f5)) / fMin));
        int iCeil = (int) Math.ceil(f / fMin);
        int i2 = (iCeil - iMax) + 1;
        int[] iArr3 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr3[i3] = iCeil - i3;
        }
        float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1352getAnchorSizeD9Ej5fM$material3());
        Arrangement arrangementFindLowestCostArrangement = Arrangement.INSTANCE.findLowestCostArrangement(f, f3, fCoerceIn, f4, f5, iArr, f7, iArr2, fMin, iArr3);
        if (arrangementFindLowestCostArrangement == null || arrangementFindLowestCostArrangement.itemCount() <= i) {
            f6 = f;
        } else {
            int smallCount = arrangementFindLowestCostArrangement.getSmallCount();
            int mediumCount = arrangementFindLowestCostArrangement.getMediumCount();
            for (int iItemCount = arrangementFindLowestCostArrangement.itemCount() - i; iItemCount > 0; iItemCount--) {
                if (smallCount > 0) {
                    smallCount--;
                } else if (mediumCount > 1) {
                    mediumCount--;
                }
            }
            f6 = f;
            arrangementFindLowestCostArrangement = Arrangement.INSTANCE.findLowestCostArrangement(f6, f3, fCoerceIn, f4, f5, new int[]{smallCount}, f7, new int[]{mediumCount}, fMin, iArr3);
        }
        return arrangementFindLowestCostArrangement == null ? KeylineListKt.emptyKeylineList() : createLeftAlignedKeylineList(f6, f3, fMo4557toPx0680j_4, fMo4557toPx0680j_4, arrangementFindLowestCostArrangement);
    }

    public static /* synthetic */ KeylineList multiBrowseKeylineList$default(Density density, float f, float f2, float f3, int i, float f4, float f5, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            f4 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1354getMinSmallItemSizeD9Ej5fM());
        }
        float f6 = f4;
        if ((i2 & 64) != 0) {
            f5 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1353getMaxSmallItemSizeD9Ej5fM());
        }
        return multiBrowseKeylineList(density, f, f2, f3, i, f6, f5);
    }

    public static final KeylineList uncontainedKeylineList(Density density, float f, float f2, float f3) {
        if (f == 0.0f || f2 == 0.0f) {
            return KeylineListKt.emptyKeylineList();
        }
        float fMin = Math.min(f2 + f3, f);
        int iMax = Math.max(1, (int) Math.floor(f / fMin));
        float f4 = f - (iMax * fMin);
        int i = f4 <= 0.0f ? 0 : 1;
        float fMo4557toPx0680j_4 = density.mo4557toPx0680j_4(CarouselDefaults.INSTANCE.m1352getAnchorSizeD9Ej5fM$material3());
        float fCalculateMediumChildSize = calculateMediumChildSize(fMo4557toPx0680j_4, fMin, f4);
        return createLeftAlignedKeylineList(f, f3, Math.max(Math.min(fMo4557toPx0680j_4, f2), fCalculateMediumChildSize * 0.5f), fMo4557toPx0680j_4, new Arrangement(0, 0.0f, 0, fCalculateMediumChildSize, i, fMin, iMax));
    }
}
