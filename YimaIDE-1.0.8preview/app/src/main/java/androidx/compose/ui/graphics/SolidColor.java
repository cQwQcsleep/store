package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001d\u001a\u00020\u0011H\u0016R\u0013\u0010\u0003\u001a\u00020\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/graphics/SolidColor;", "Landroidx/compose/ui/graphics/Brush;", "Landroidx/compose/ui/graphics/Interpolatable;", "value", "Landroidx/compose/ui/graphics/Color;", "<init>", "(JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getValue-0d7_KjU", "()J", "J", "applyTo", "", "size", "Landroidx/compose/ui/geometry/Size;", "p", "Landroidx/compose/ui/graphics/Paint;", "alpha", "", "applyTo-Pq9zytI", "(JLandroidx/compose/ui/graphics/Paint;F)V", "equals", "", "other", "", "hashCode", "", "toString", "", "lerp", "t", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SolidColor extends Brush implements Interpolatable {
    public static final int $stable = 0;
    private final long value;

    private SolidColor(long j) {
        super(null);
        this.value = j;
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: applyTo-Pq9zytI */
    public void mo3079applyToPq9zytI(long size, Paint p, float alpha) {
        p.setAlpha(1.0f);
        long jM3133copywmQWz5c$default = this.value;
        if (alpha != 1.0f) {
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(jM3133copywmQWz5c$default, Color.m3136getAlphaimpl(jM3133copywmQWz5c$default) * alpha, 0.0f, 0.0f, 0.0f, 14, null);
        }
        p.mo3010setColor8_81llA(jM3133copywmQWz5c$default);
        if (p.getShader() != null) {
            p.setShader(null);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SolidColor) && Color.m3135equalsimpl0(this.value, ((SolidColor) other).value);
    }

    /* JADX INFO: renamed from: getValue-0d7_KjU, reason: not valid java name and from getter */
    public final long getValue() {
        return this.value;
    }

    public int hashCode() {
        return Color.m3141hashCodeimpl(this.value);
    }

    @Override // androidx.compose.ui.graphics.Interpolatable
    public Object lerp(Object other, float t) {
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (other == null) {
            other = new SolidColor(Color.INSTANCE.m3169getTransparent0d7_KjU(), defaultConstructorMarker);
        }
        if (other instanceof SolidColor) {
            return new SolidColor(ColorKt.m3185lerpjxsXWHM(this.value, ((SolidColor) other).value, t), defaultConstructorMarker);
        }
        return null;
    }

    public String toString() {
        return "SolidColor(value=" + ((Object) Color.m3142toStringimpl(this.value)) + ')';
    }

    public /* synthetic */ SolidColor(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }
}
