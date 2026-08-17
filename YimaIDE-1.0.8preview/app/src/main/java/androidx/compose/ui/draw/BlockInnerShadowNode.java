package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.shadow.InnerShadowPainter;
import androidx.compose.ui.graphics.shadow.Shadow;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B(\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010@\u001a\u00020\tH\u0016J\b\u0010A\u001a\u00020\tH\u0016J\b\u0010B\u001a\u00020\tH\u0002J'\u0010C\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nJ\f\u0010D\u001a\u00020\t*\u00020EH\u0016J\b\u0010F\u001a\u00020\u0012H\u0002J\b\u0010G\u001a\u00020\tH\u0016J\b\u0010H\u001a\u00020\tH\u0002J\u0013\u0010I\u001a\u00020\u00142\b\u0010J\u001a\u0004\u0018\u00010KH\u0096\u0002J\b\u0010L\u001a\u00020MH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR$\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0019@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010!R$\u0010\"\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0019@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001b\"\u0004\b$\u0010!R&\u0010&\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020%@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R&\u0010-\u001a\u00020,2\u0006\u0010\u0015\u001a\u00020,@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b.\u0010(\"\u0004\b/\u0010*R(\u00101\u001a\u0004\u0018\u0001002\b\u0010\u0015\u001a\u0004\u0018\u000100@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R$\u00106\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0019@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001b\"\u0004\b8\u0010!R&\u0010:\u001a\u0002092\u0006\u0010\u0015\u001a\u000209@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010?\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>¨\u0006N"}, d2 = {"Landroidx/compose/ui/draw/BlockInnerShadowNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/draw/InnerShadowScope;", "shape", "Landroidx/compose/ui/graphics/Shape;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function1;)V", "densityObject", "Landroidx/compose/ui/unit/Density;", "targetShadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "shadowPainter", "Landroidx/compose/ui/graphics/shadow/InnerShadowPainter;", "blockRead", "", "value", "setBlock", "(Lkotlin/jvm/functions/Function1;)V", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "radius", "getRadius", "setRadius", "(F)V", "spread", "getSpread", "setSpread", "Landroidx/compose/ui/geometry/Offset;", "offset", "getOffset-F1C5BW0", "()J", "setOffset-k-4lQ0M", "(J)V", "J", "Landroidx/compose/ui/graphics/Color;", "color", "getColor-0d7_KjU", "setColor-8_81llA", "Landroidx/compose/ui/graphics/Brush;", "brush", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "alpha", "getAlpha", "setAlpha", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "I", "onAttach", "onDensityChange", "updateDensity", "update", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "obtainPainter", "onObservedReadsChanged", "invalidateShadow", "equals", "other", "", "hashCode", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BlockInnerShadowNode extends Modifier.Node implements DrawModifierNode, ObserverModifierNode, InnerShadowScope {
    public static final int $stable = 0;
    private Function1<? super InnerShadowScope, Unit> block;
    private boolean blockRead;
    private Brush brush;
    private Density densityObject;
    private float radius;
    private InnerShadowPainter shadowPainter;
    private Shape shape;
    private float spread;
    private Shadow targetShadow;
    private long offset = Offset.INSTANCE.m2905getZeroF1C5BW0();
    private long color = Color.INSTANCE.m3160getBlack0d7_KjU();
    private float alpha = 1.0f;
    private int blendMode = BlendMode.INSTANCE.m3074getSrcOver0nO6VwU();

    public BlockInnerShadowNode(Shape shape, Function1<? super InnerShadowScope, Unit> function1) {
        this.shape = shape;
        this.block = function1;
    }

    private final void invalidateShadow() {
        this.targetShadow = null;
        this.shadowPainter = null;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    private final InnerShadowPainter obtainPainter() {
        if (!this.blockRead) {
            this.blockRead = true;
            ShadowKt.resetShadow(this);
            ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.draw.BlockInnerShadowNode.obtainPainter.1
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m2646invoke() {
                    BlockInnerShadowNode.this.block.invoke(BlockInnerShadowNode.this);
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m2646invoke();
                    return Unit.INSTANCE;
                }
            });
        }
        Shadow shadow = this.targetShadow;
        InnerShadowPainter innerShadowPainter = this.shadowPainter;
        Brush brush = getBrush();
        float fMo4553toDpu2uoSUM = mo4553toDpu2uoSUM(getRadius());
        float fMo4553toDpu2uoSUM2 = mo4553toDpu2uoSUM(getSpread());
        float fMo4553toDpu2uoSUM3 = mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (getOffset() >> 32)));
        long jM6078constructorimpl = DpOffset.m6078constructorimpl((((long) Float.floatToRawIntBits(mo4553toDpu2uoSUM(Float.intBitsToFloat((int) (getOffset() & 4294967295L))))) & 4294967295L) | (((long) Float.floatToRawIntBits(fMo4553toDpu2uoSUM3)) << 32));
        if (innerShadowPainter != null && shadow != null && Dp.m6027equalsimpl0(shadow.getRadius(), fMo4553toDpu2uoSUM) && Dp.m6027equalsimpl0(shadow.getSpread(), fMo4553toDpu2uoSUM2) && Color.m3135equalsimpl0(shadow.getColor(), getColor()) && Intrinsics.areEqual(shadow.getBrush(), brush) && shadow.getAlpha() == getAlpha() && BlendMode.m3043equalsimpl0(shadow.getBlendMode(), getBlendMode()) && DpOffset.m6082equalsimpl0(shadow.getOffset(), jM6078constructorimpl)) {
            return innerShadowPainter;
        }
        Shadow shadow2 = brush != null ? new Shadow(fMo4553toDpu2uoSUM, brush, fMo4553toDpu2uoSUM2, jM6078constructorimpl, getAlpha(), getBlendMode(), (DefaultConstructorMarker) null) : new Shadow(fMo4553toDpu2uoSUM, getColor(), fMo4553toDpu2uoSUM2, jM6078constructorimpl, getAlpha(), getBlendMode(), (DefaultConstructorMarker) null);
        this.targetShadow = shadow2;
        InnerShadowPainter innerShadowPainterCreateInnerShadowPainter = DelegatableNodeKt.requireGraphicsContext(this).getShadowContext().createInnerShadowPainter(this.shape, shadow2);
        this.shadowPainter = innerShadowPainterCreateInnerShadowPainter;
        return innerShadowPainterCreateInnerShadowPainter;
    }

    private final void setBlock(Function1<? super InnerShadowScope, Unit> function1) {
        if (this.block != function1) {
            this.block = function1;
            this.blockRead = false;
            DrawModifierNodeKt.invalidateDraw(this);
        }
    }

    private final void updateDensity() {
        Density densityRequireDensity = DelegatableNodeKt.requireDensity(this);
        if (Intrinsics.areEqual(this.densityObject, densityRequireDensity)) {
            return;
        }
        this.densityObject = densityRequireDensity;
        this.blockRead = false;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        Painter.m3833drawx_KDEd0$default(obtainPainter(), contentDrawScope, contentDrawScope.mo3708getSizeNHjbRc(), 0.0f, null, 6, null);
        contentDrawScope.drawContent();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof BlockInnerShadowNode)) {
            BlockInnerShadowNode blockInnerShadowNode = (BlockInnerShadowNode) other;
            return getAlpha() == blockInnerShadowNode.getAlpha() && Intrinsics.areEqual(this.shape, blockInnerShadowNode.shape) && this.block == blockInnerShadowNode.block && getRadius() == blockInnerShadowNode.getRadius() && getSpread() == blockInnerShadowNode.getSpread() && Offset.m2886equalsimpl0(getOffset(), blockInnerShadowNode.getOffset()) && Color.m3135equalsimpl0(getColor(), blockInnerShadowNode.getColor()) && Intrinsics.areEqual(getBrush(), blockInnerShadowNode.getBrush()) && BlendMode.m3043equalsimpl0(getBlendMode(), blockInnerShadowNode.getBlendMode());
        }
        return false;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: from getter */
    public int getBlendMode() {
        return this.blendMode;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public Brush getBrush() {
        return this.brush;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: from getter */
    public long getColor() {
        return this.color;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        Density density = this.densityObject;
        if (density != null) {
            return density.getDensity();
        }
        return 1.0f;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        Density density = this.densityObject;
        if (density != null) {
            return density.getFontScale();
        }
        return 1.0f;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    /* JADX INFO: renamed from: getOffset-F1C5BW0, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public float getRadius() {
        return this.radius;
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public float getSpread() {
        return this.spread;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((Float.hashCode(getAlpha()) * 31) + this.shape.hashCode()) * 31) + this.block.hashCode()) * 31) + Float.hashCode(getRadius())) * 31) + Float.hashCode(getSpread())) * 31) + Offset.m2891hashCodeimpl(getOffset())) * 31) + Color.m3141hashCodeimpl(getColor())) * 31;
        Brush brush = getBrush();
        return ((iHashCode + (brush != null ? brush.hashCode() : 0)) * 31) + BlendMode.m3044hashCodeimpl(getBlendMode());
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        updateDensity();
    }

    @Override // androidx.compose.ui.node.DelegatableNode
    public void onDensityChange() {
        if (getIsAttached()) {
            updateDensity();
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        this.blockRead = false;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public void setAlpha(float f) {
        if (this.alpha == f) {
            return;
        }
        this.alpha = f;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    public void mo2642setBlendModes9anfk8(int i) {
        if (BlendMode.m3043equalsimpl0(this.blendMode, i)) {
            return;
        }
        this.blendMode = i;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public void setBrush(Brush brush) {
        if (Intrinsics.areEqual(this.brush, brush)) {
            return;
        }
        this.brush = brush;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    /* JADX INFO: renamed from: setColor-8_81llA */
    public void mo2643setColor8_81llA(long j) {
        if (j == 16) {
            j = Color.INSTANCE.m3160getBlack0d7_KjU();
        }
        if (Color.m3135equalsimpl0(this.color, j)) {
            return;
        }
        this.color = j;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    /* JADX INFO: renamed from: setOffset-k-4lQ0M */
    public void mo2644setOffsetk4lQ0M(long j) {
        if (Offset.m2886equalsimpl0(this.offset, j)) {
            return;
        }
        this.offset = j;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public void setRadius(float f) {
        if (this.radius == f) {
            return;
        }
        this.radius = f;
        invalidateShadow();
    }

    @Override // androidx.compose.ui.draw.ShadowScope
    public void setSpread(float f) {
        if (this.spread == f) {
            return;
        }
        this.spread = f;
        invalidateShadow();
    }

    public final void update(Shape shape, Function1<? super InnerShadowScope, Unit> block) {
        this.shape = shape;
        setBlock(block);
    }
}
