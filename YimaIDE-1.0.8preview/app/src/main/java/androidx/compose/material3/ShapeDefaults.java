package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSize;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.material3.tokens.ShapeTokens;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0015\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0014\u0010\u0016\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0014\u0010 \u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0014\u0010\"\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0014\u0010$\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0014\u0010&\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0014\u0010(\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019R\u0014\u0010*\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019¨\u0006,"}, d2 = {"Landroidx/compose/material3/ShapeDefaults;", "", "<init>", "()V", "ExtraSmall", "Landroidx/compose/foundation/shape/CornerBasedShape;", "getExtraSmall", "()Landroidx/compose/foundation/shape/CornerBasedShape;", "Small", "getSmall", "Medium", "getMedium", "Large", "getLarge", "LargeIncreased", "getLargeIncreased$material3", "ExtraLarge", "getExtraLarge", "ExtraLargeIncreased", "getExtraLargeIncreased$material3", "ExtraExtraLarge", "getExtraExtraLarge$material3", "CornerNone", "Landroidx/compose/foundation/shape/CornerSize;", "getCornerNone$material3", "()Landroidx/compose/foundation/shape/CornerSize;", "CornerExtraSmall", "getCornerExtraSmall$material3", "CornerSmall", "getCornerSmall$material3", "CornerMedium", "getCornerMedium$material3", "CornerLarge", "getCornerLarge$material3", "CornerLargeIncreased", "getCornerLargeIncreased$material3", "CornerExtraLarge", "getCornerExtraLarge$material3", "CornerExtraLargeIncreased", "getCornerExtraLargeIncreased$material3", "CornerExtraExtraLarge", "getCornerExtraExtraLarge$material3", "CornerFull", "getCornerFull$material3", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ShapeDefaults {
    public static final int $stable = 0;
    private static final CornerSize CornerExtraExtraLarge;
    private static final CornerSize CornerExtraLarge;
    private static final CornerSize CornerExtraLargeIncreased;
    private static final CornerSize CornerExtraSmall;
    private static final CornerSize CornerFull;
    private static final CornerSize CornerLarge;
    private static final CornerSize CornerLargeIncreased;
    private static final CornerSize CornerMedium;
    private static final CornerSize CornerNone;
    private static final CornerSize CornerSmall;
    private static final CornerBasedShape ExtraExtraLarge;
    private static final CornerBasedShape ExtraLarge;
    private static final CornerBasedShape ExtraLargeIncreased;
    private static final CornerBasedShape ExtraSmall;
    public static final ShapeDefaults INSTANCE = new ShapeDefaults();
    private static final CornerBasedShape Large;
    private static final CornerBasedShape LargeIncreased;
    private static final CornerBasedShape Medium;
    private static final CornerBasedShape Small;

    static {
        ShapeTokens shapeTokens = ShapeTokens.INSTANCE;
        ExtraSmall = shapeTokens.getCornerExtraSmall();
        Small = shapeTokens.getCornerSmall();
        Medium = shapeTokens.getCornerMedium();
        Large = shapeTokens.getCornerLarge();
        LargeIncreased = shapeTokens.getCornerLargeIncreased();
        ExtraLarge = shapeTokens.getCornerExtraLarge();
        ExtraLargeIncreased = shapeTokens.getCornerExtraLargeIncreased();
        ExtraExtraLarge = shapeTokens.getCornerExtraExtraLarge();
        CornerNone = shapeTokens.getCornerValueNone();
        CornerExtraSmall = shapeTokens.getCornerValueExtraSmall();
        CornerSmall = shapeTokens.getCornerValueSmall();
        CornerMedium = shapeTokens.getCornerValueMedium();
        CornerLarge = shapeTokens.getCornerValueLarge();
        CornerLargeIncreased = shapeTokens.getCornerValueLargeIncreased();
        CornerExtraLarge = shapeTokens.getCornerValueExtraLarge();
        CornerExtraLargeIncreased = shapeTokens.getCornerValueExtraLargeIncreased();
        CornerExtraExtraLarge = shapeTokens.getCornerValueExtraExtraLarge();
        CornerFull = CornerSizeKt.CornerSize(100);
    }

    private ShapeDefaults() {
    }

    public final CornerSize getCornerExtraExtraLarge$material3() {
        return CornerExtraExtraLarge;
    }

    public final CornerSize getCornerExtraLarge$material3() {
        return CornerExtraLarge;
    }

    public final CornerSize getCornerExtraLargeIncreased$material3() {
        return CornerExtraLargeIncreased;
    }

    public final CornerSize getCornerExtraSmall$material3() {
        return CornerExtraSmall;
    }

    public final CornerSize getCornerFull$material3() {
        return CornerFull;
    }

    public final CornerSize getCornerLarge$material3() {
        return CornerLarge;
    }

    public final CornerSize getCornerLargeIncreased$material3() {
        return CornerLargeIncreased;
    }

    public final CornerSize getCornerMedium$material3() {
        return CornerMedium;
    }

    public final CornerSize getCornerNone$material3() {
        return CornerNone;
    }

    public final CornerSize getCornerSmall$material3() {
        return CornerSmall;
    }

    public final CornerBasedShape getExtraExtraLarge$material3() {
        return ExtraExtraLarge;
    }

    public final CornerBasedShape getExtraLarge() {
        return ExtraLarge;
    }

    public final CornerBasedShape getExtraLargeIncreased$material3() {
        return ExtraLargeIncreased;
    }

    public final CornerBasedShape getExtraSmall() {
        return ExtraSmall;
    }

    public final CornerBasedShape getLarge() {
        return Large;
    }

    public final CornerBasedShape getLargeIncreased$material3() {
        return LargeIncreased;
    }

    public final CornerBasedShape getMedium() {
        return Medium;
    }

    public final CornerBasedShape getSmall() {
        return Small;
    }
}
