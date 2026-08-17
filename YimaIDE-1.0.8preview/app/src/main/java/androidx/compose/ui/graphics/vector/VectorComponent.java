package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u00108\u001a\u00020\u000f*\u0002062\u0006\u00109\u001a\u0002022\b\u0010:\u001a\u0004\u0018\u00010\u001fJ\f\u00108\u001a\u00020\u000f*\u000206H\u0016J\b\u0010;\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR/\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0010\u0010'\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010)\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020(8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010&\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0010\u0010/\u001a\u00020(X\u0082\u000e¢\u0006\u0004\n\u0002\u00100R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u00104\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u000f05¢\u0006\u0002\b7X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorComponent;", "Landroidx/compose/ui/graphics/vector/VNode;", "root", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "<init>", "(Landroidx/compose/ui/graphics/vector/GroupComponent;)V", "getRoot", "()Landroidx/compose/ui/graphics/vector/GroupComponent;", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "doInvalidate", "", "isDirty", "", "cacheDrawScope", "Landroidx/compose/ui/graphics/vector/DrawCache;", "cacheBitmapConfig", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "getCacheBitmapConfig-_sVssgQ$ui", "()I", "invalidateCallback", "Lkotlin/Function0;", "getInvalidateCallback$ui", "()Lkotlin/jvm/functions/Function0;", "setInvalidateCallback$ui", "(Lkotlin/jvm/functions/Function0;)V", "<set-?>", "Landroidx/compose/ui/graphics/ColorFilter;", "intrinsicColorFilter", "getIntrinsicColorFilter$ui", "()Landroidx/compose/ui/graphics/ColorFilter;", "setIntrinsicColorFilter$ui", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "intrinsicColorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "tintFilter", "Landroidx/compose/ui/geometry/Size;", "viewportSize", "getViewportSize-NH-jbRc$ui", "()J", "setViewportSize-uvyYCjk$ui", "(J)V", "viewportSize$delegate", "previousDrawSize", "J", "rootScaleX", "", "rootScaleY", "drawVectorBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "draw", "alpha", "colorFilter", "toString", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class VectorComponent extends VNode {
    public static final int $stable = 8;
    private final DrawCache cacheDrawScope;
    private final Function1<DrawScope, Unit> drawVectorBlock;

    /* JADX INFO: renamed from: intrinsicColorFilter$delegate, reason: from kotlin metadata */
    private final MutableState intrinsicColorFilter;
    private Function0<Unit> invalidateCallback;
    private boolean isDirty;
    private String name;
    private long previousDrawSize;
    private final GroupComponent root;
    private float rootScaleX;
    private float rootScaleY;
    private ColorFilter tintFilter;

    /* JADX INFO: renamed from: viewportSize$delegate, reason: from kotlin metadata */
    private final MutableState viewportSize;

    public VectorComponent(GroupComponent groupComponent) {
        super(null);
        this.root = groupComponent;
        groupComponent.setInvalidateListener$ui(new Function1<VNode, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComponent.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((VNode) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(VNode vNode) {
                VectorComponent.this.doInvalidate();
            }
        });
        this.name = "";
        this.isDirty = true;
        this.cacheDrawScope = new DrawCache();
        this.invalidateCallback = new Function0<Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComponent$invalidateCallback$1
            public /* bridge */ /* synthetic */ Object invoke() {
                m3879invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m3879invoke() {
            }
        };
        this.intrinsicColorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        Size.Companion companion = Size.INSTANCE;
        this.viewportSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m2946boximpl(companion.m2967getZeroNHjbRc()), null, 2, null);
        this.previousDrawSize = companion.m2966getUnspecifiedNHjbRc();
        this.rootScaleX = 1.0f;
        this.rootScaleY = 1.0f;
        this.drawVectorBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComponent$drawVectorBlock$1
            {
                super(1);
            }

            public final void invoke(DrawScope drawScope) {
                GroupComponent root = this.this$0.getRoot();
                VectorComponent vectorComponent = this.this$0;
                float f = vectorComponent.rootScaleX;
                float f2 = vectorComponent.rootScaleY;
                long jM2905getZeroF1C5BW0 = Offset.INSTANCE.m2905getZeroF1C5BW0();
                DrawContext drawContext = drawScope.getDrawContext();
                long jMo3629getSizeNHjbRc = drawContext.mo3629getSizeNHjbRc();
                drawContext.getCanvas().save();
                try {
                    drawContext.getTransform().mo3636scale0AR0LA0(f, f2, jM2905getZeroF1C5BW0);
                    root.draw(drawScope);
                } finally {
                    drawContext.getCanvas().restore();
                    drawContext.mo3630setSizeuvyYCjk(jMo3629getSizeNHjbRc);
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((DrawScope) obj);
                return Unit.INSTANCE;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doInvalidate() {
        this.isDirty = true;
        this.invalidateCallback.invoke();
    }

    public final void draw(DrawScope drawScope, float f, ColorFilter colorFilter) {
        DrawScope drawScope2;
        int iM3364getAlpha8_sVssgQ = (this.root.getIsTintable() && this.root.getTintColor() != 16 && VectorKt.tintableWithAlphaMask(getIntrinsicColorFilter$ui()) && VectorKt.tintableWithAlphaMask(colorFilter)) ? ImageBitmapConfig.INSTANCE.m3364getAlpha8_sVssgQ() : ImageBitmapConfig.INSTANCE.m3365getArgb8888_sVssgQ();
        if (!this.isDirty && Size.m2954equalsimpl0(this.previousDrawSize, drawScope.mo3708getSizeNHjbRc()) && ImageBitmapConfig.m3360equalsimpl0(iM3364getAlpha8_sVssgQ, m3876getCacheBitmapConfig_sVssgQ$ui())) {
            drawScope2 = drawScope;
        } else {
            this.tintFilter = ImageBitmapConfig.m3360equalsimpl0(iM3364getAlpha8_sVssgQ, ImageBitmapConfig.INSTANCE.m3364getAlpha8_sVssgQ()) ? ColorFilter.Companion.m3175tintxETnrds$default(ColorFilter.INSTANCE, VectorKt.m3887toOpaque8_81llA(this.root.getTintColor()), 0, 2, null) : null;
            this.rootScaleX = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) / Float.intBitsToFloat((int) (m3877getViewportSizeNHjbRc$ui() >> 32));
            this.rootScaleY = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)) / Float.intBitsToFloat((int) (m3877getViewportSizeNHjbRc$ui() & 4294967295L));
            drawScope2 = drawScope;
            this.cacheDrawScope.m3858drawCachedImageFqjB98A(iM3364getAlpha8_sVssgQ, IntSize.m6188constructorimpl((((long) ((int) Math.ceil(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L))))) & 4294967295L) | (((long) ((int) Math.ceil(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32))))) << 32)), drawScope2, drawScope.getLayoutDirection(), this.drawVectorBlock);
            this.isDirty = false;
            this.previousDrawSize = drawScope2.mo3708getSizeNHjbRc();
        }
        if (colorFilter == null) {
            colorFilter = getIntrinsicColorFilter$ui() != null ? getIntrinsicColorFilter$ui() : this.tintFilter;
        }
        this.cacheDrawScope.drawInto(drawScope2, f, colorFilter);
    }

    /* JADX INFO: renamed from: getCacheBitmapConfig-_sVssgQ$ui, reason: not valid java name */
    public final int m3876getCacheBitmapConfig_sVssgQ$ui() {
        ImageBitmap mCachedImage = this.cacheDrawScope.getMCachedImage();
        return mCachedImage != null ? mCachedImage.mo2998getConfig_sVssgQ() : ImageBitmapConfig.INSTANCE.m3365getArgb8888_sVssgQ();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ColorFilter getIntrinsicColorFilter$ui() {
        return (ColorFilter) this.intrinsicColorFilter.getValue();
    }

    public final Function0<Unit> getInvalidateCallback$ui() {
        return this.invalidateCallback;
    }

    public final String getName() {
        return this.name;
    }

    public final GroupComponent getRoot() {
        return this.root;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getViewportSize-NH-jbRc$ui, reason: not valid java name */
    public final long m3877getViewportSizeNHjbRc$ui() {
        return ((Size) this.viewportSize.getValue()).m2963unboximpl();
    }

    public final void setIntrinsicColorFilter$ui(ColorFilter colorFilter) {
        this.intrinsicColorFilter.setValue(colorFilter);
    }

    public final void setInvalidateCallback$ui(Function0<Unit> function0) {
        this.invalidateCallback = function0;
    }

    public final void setName(String str) {
        this.name = str;
    }

    /* JADX INFO: renamed from: setViewportSize-uvyYCjk$ui, reason: not valid java name */
    public final void m3878setViewportSizeuvyYCjk$ui(long j) {
        this.viewportSize.setValue(Size.m2946boximpl(j));
    }

    public String toString() {
        return "Params: \tname: " + this.name + "\n\tviewportWidth: " + Float.intBitsToFloat((int) (m3877getViewportSizeNHjbRc$ui() >> 32)) + "\n\tviewportHeight: " + Float.intBitsToFloat((int) (m3877getViewportSizeNHjbRc$ui() & 4294967295L)) + "\n";
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public void draw(DrawScope drawScope) {
        draw(drawScope, 1.0f, null);
    }
}
