package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u001c\u0010!\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\"\u001a\u00020\tH\u0016R\u0016\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006#"}, d2 = {"Landroidx/compose/ui/graphics/SweepGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Interpolatable;", "center", "Landroidx/compose/ui/geometry/Offset;", "colors", "", "Landroidx/compose/ui/graphics/Color;", "stops", "", "<init>", "(JLjava/util/List;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCenter-F1C5BW0$ui_graphics", "()J", "J", "getColors$ui_graphics", "()Ljava/util/List;", "getStops$ui_graphics", "createShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "size", "Landroidx/compose/ui/geometry/Size;", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "equals", "", "other", "", "hashCode", "", "toString", "", "lerp", "t", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SweepGradient extends ShaderBrush implements Interpolatable {
    public static final int $stable = 0;
    private final long center;
    private final List<Color> colors;
    private final List<Float> stops;

    public /* synthetic */ SweepGradient(long j, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, list, (i & 4) != 0 ? null : list2, null);
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public Shader mo3103createShaderuvyYCjk(long size) {
        long jM2881constructorimpl;
        long j = this.center;
        if ((androidx.compose.ui.geometry.InlineClassHelperKt.DualUnsignedFloatMask & j) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            jM2881constructorimpl = SizeKt.m2968getCenteruvyYCjk(size);
        } else {
            float fIntBitsToFloat = Float.intBitsToFloat((int) (Float.intBitsToFloat((int) (j >> 32)) == Float.POSITIVE_INFINITY ? size >> 32 : this.center >> 32));
            if (Float.intBitsToFloat((int) (this.center & 4294967295L)) != Float.POSITIVE_INFINITY) {
                size = this.center;
            }
            float fIntBitsToFloat2 = Float.intBitsToFloat((int) (size & 4294967295L));
            jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32));
        }
        return ShaderKt.m3472SweepGradientShader9KIMszo(jM2881constructorimpl, this.colors, this.stops);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SweepGradient)) {
            return false;
        }
        SweepGradient sweepGradient = (SweepGradient) other;
        return Offset.m2886equalsimpl0(this.center, sweepGradient.center) && Intrinsics.areEqual(this.colors, sweepGradient.colors) && Intrinsics.areEqual(this.stops, sweepGradient.stops);
    }

    /* JADX INFO: renamed from: getCenter-F1C5BW0$ui_graphics, reason: not valid java name and from getter */
    public final long getCenter() {
        return this.center;
    }

    public final List<Color> getColors$ui_graphics() {
        return this.colors;
    }

    public final List<Float> getStops$ui_graphics() {
        return this.stops;
    }

    public int hashCode() {
        int iM2891hashCodeimpl = ((Offset.m2891hashCodeimpl(this.center) * 31) + this.colors.hashCode()) * 31;
        List<Float> list = this.stops;
        return iM2891hashCodeimpl + (list != null ? list.hashCode() : 0);
    }

    @Override // androidx.compose.ui.graphics.Interpolatable
    public Object lerp(Object other, float t) {
        if (other == null) {
            other = new SolidColor(Color.INSTANCE.m3169getTransparent0d7_KjU(), null);
        }
        if (other instanceof SolidColor) {
            long j = this.center;
            List<Color> list = this.colors;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).m3144unboximpl();
                arrayList.add(Color.m3124boximpl(((SolidColor) other).getValue()));
            }
            other = new SweepGradient(j, arrayList, this.stops, null);
        }
        if (!(other instanceof SweepGradient)) {
            return null;
        }
        SweepGradient sweepGradient = (SweepGradient) other;
        return new SweepGradient(OffsetKt.m2912lerpWko1d7g(this.center, sweepGradient.center, t), BrushKt.lerpColorList(this.colors, sweepGradient.colors, t), BrushKt.lerpNullableFloatList(this.stops, sweepGradient.stops, t), null);
    }

    public String toString() {
        String str;
        if ((this.center & androidx.compose.ui.geometry.InlineClassHelperKt.DualUnsignedFloatMask) != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            str = "center=" + ((Object) Offset.m2897toStringimpl(this.center)) + ", ";
        } else {
            str = "";
        }
        return "SweepGradient(" + str + "colors=" + this.colors + ", stops=" + this.stops + ')';
    }

    private SweepGradient(long j, List<Color> list, List<Float> list2) {
        this.center = j;
        this.colors = list;
        this.stops = list2;
    }

    public /* synthetic */ SweepGradient(long j, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, list, list2);
    }
}
