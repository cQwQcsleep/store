package androidx.compose.animation;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020BJ\u0010\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020MH\u0016J\b\u0010h\u001a\u00020KH\u0016J\b\u0010i\u001a\u00020KH\u0016J\b\u0010j\u001a\u00020KH\u0016R+\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00118V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010\r\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u001a\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u001a\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u001a\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R+\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u001a\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R+\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u001a\u001a\u0004\b3\u0010\u0016\"\u0004\b4\u0010\u0018R+\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\u001a\u001a\u0004\b6\u00107\"\u0004\b8\u00109R+\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010\u001a\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0011\u0010@\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b@\u0010\u0016R\u001c\u0010D\u001a\u0004\u0018\u00010EX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010N\u001a\u0004\u0018\u00010\u0000X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0011\u0010S\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bT\u0010\u0016R\u001c\u0010U\u001a\u0004\u0018\u00010VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR/\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010\u0014\u001a\u0004\u0018\u00010[8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\ba\u0010\u001a\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u0014\u0010b\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0016R\u0014\u0010d\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010\u0016R\u0011\u0010f\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bg\u0010\u0016¨\u0006k"}, d2 = {"Landroidx/compose/animation/SharedElementEntry;", "Landroidx/compose/animation/LayerRenderer;", "Landroidx/compose/runtime/RememberObserver;", "sharedElement", "Landroidx/compose/animation/SharedElement;", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "placeholderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "renderOnlyWhenVisible", "", "overlayClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "renderInOverlayDuringTransition", "userState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "zIndex", "", "<init>", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsAnimation;Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;ZLandroidx/compose/animation/SharedTransitionScope$OverlayClip;ZLandroidx/compose/animation/SharedTransitionScope$SharedContentState;F)V", "<set-?>", "isAttached", "()Z", "setAttached", "(Z)V", "isAttached$delegate", "Landroidx/compose/runtime/MutableState;", "getZIndex", "()F", "setZIndex", "(F)V", "zIndex$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getRenderInOverlayDuringTransition", "setRenderInOverlayDuringTransition", "renderInOverlayDuringTransition$delegate", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "setSharedElement", "(Landroidx/compose/animation/SharedElement;)V", "sharedElement$delegate", "getBoundsAnimation", "()Landroidx/compose/animation/BoundsAnimation;", "setBoundsAnimation", "(Landroidx/compose/animation/BoundsAnimation;)V", "boundsAnimation$delegate", "getPlaceholderSize", "()Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "setPlaceholderSize", "(Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;)V", "placeholderSize$delegate", "getRenderOnlyWhenVisible", "setRenderOnlyWhenVisible", "renderOnlyWhenVisible$delegate", "getOverlayClip", "()Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "setOverlayClip", "(Landroidx/compose/animation/SharedTransitionScope$OverlayClip;)V", "overlayClip$delegate", "getUserState", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "setUserState", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;)V", "userState$delegate", "isEnabled", "calculateTargetBounds", "Landroidx/compose/ui/geometry/Rect;", "previousTargetBoundsBeforeLosingTarget", "clipPathInOverlay", "Landroidx/compose/ui/graphics/Path;", "getClipPathInOverlay$animation", "()Landroidx/compose/ui/graphics/Path;", "setClipPathInOverlay$animation", "(Landroidx/compose/ui/graphics/Path;)V", "drawInOverlay", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "parentState", "getParentState", "()Landroidx/compose/animation/SharedElementEntry;", "setParentState", "(Landroidx/compose/animation/SharedElementEntry;)V", "target", "getTarget", "boundsProvider", "Landroidx/compose/animation/BoundsProvider;", "getBoundsProvider", "()Landroidx/compose/animation/BoundsProvider;", "setBoundsProvider", "(Landroidx/compose/animation/BoundsProvider;)V", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "setLayer", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "layer$delegate", "shouldRenderAtAll", "getShouldRenderAtAll", "shouldRenderInOverlay", "getShouldRenderInOverlay$animation", "shouldRenderInPlace", "getShouldRenderInPlace", "onRemembered", "onForgotten", "onAbandoned", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SharedElementEntry implements LayerRenderer, RememberObserver {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: boundsAnimation$delegate, reason: from kotlin metadata */
    private final MutableState boundsAnimation;
    private BoundsProvider boundsProvider;
    private Path clipPathInOverlay;

    /* JADX INFO: renamed from: isAttached$delegate, reason: from kotlin metadata */
    private final MutableState isAttached = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);

    /* JADX INFO: renamed from: layer$delegate, reason: from kotlin metadata */
    private final MutableState layer = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);

    /* JADX INFO: renamed from: overlayClip$delegate, reason: from kotlin metadata */
    private final MutableState overlayClip;
    private SharedElementEntry parentState;

    /* JADX INFO: renamed from: placeholderSize$delegate, reason: from kotlin metadata */
    private final MutableState placeholderSize;

    /* JADX INFO: renamed from: renderInOverlayDuringTransition$delegate, reason: from kotlin metadata */
    private final MutableState renderInOverlayDuringTransition;

    /* JADX INFO: renamed from: renderOnlyWhenVisible$delegate, reason: from kotlin metadata */
    private final MutableState renderOnlyWhenVisible;

    /* JADX INFO: renamed from: sharedElement$delegate, reason: from kotlin metadata */
    private final MutableState sharedElement;

    /* JADX INFO: renamed from: userState$delegate, reason: from kotlin metadata */
    private final MutableState userState;

    /* JADX INFO: renamed from: zIndex$delegate, reason: from kotlin metadata */
    private final MutableFloatState zIndex;

    public SharedElementEntry(SharedElement sharedElement, BoundsAnimation boundsAnimation, SharedTransitionScope.PlaceholderSize placeholderSize, boolean z, SharedTransitionScope.OverlayClip overlayClip, boolean z2, SharedTransitionScope.SharedContentState sharedContentState, float f) {
        this.zIndex = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.renderInOverlayDuringTransition = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z2), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.sharedElement = SnapshotStateKt.mutableStateOf$default(sharedElement, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.boundsAnimation = SnapshotStateKt.mutableStateOf$default(boundsAnimation, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.placeholderSize = SnapshotStateKt.mutableStateOf$default(placeholderSize, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.renderOnlyWhenVisible = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.overlayClip = SnapshotStateKt.mutableStateOf$default(overlayClip, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.userState = SnapshotStateKt.mutableStateOf$default(sharedContentState, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    private final boolean getShouldRenderAtAll() {
        if (getBoundsAnimation().getTarget()) {
            return true;
        }
        return (getSharedElement().getState$animation().getMatchIsOrHasBeenConfigured() && !getSharedElement().getState$animation().getActiveMatchFound()) || !getRenderOnlyWhenVisible();
    }

    public final Rect calculateTargetBounds(Rect previousTargetBoundsBeforeLosingTarget) {
        return getUserState().getConfig$animation().m147alternativeTargetBoundsInTransitionScopeAfterRemovalcSwnlzA(getUserState(), previousTargetBoundsBeforeLosingTarget, IntSizeKt.toSize-ozmzZPI(getSharedElement().getScope().getLookaheadRoot$animation().getSize-YbymL2g()));
    }

    @Override // androidx.compose.animation.LayerRenderer
    public void drawInOverlay(DrawScope drawScope) {
        Rect currentBounds;
        GraphicsLayer layer = getLayer();
        if (layer == null || (currentBounds = getSharedElement().getState$animation().getCurrentBounds()) == null || !getShouldRenderInOverlay$animation()) {
            return;
        }
        long j = currentBounds.getTopLeft-F1C5BW0();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        Path path = this.clipPathInOverlay;
        if (path == null) {
            drawScope.getDrawContext().getTransform().translate(fIntBitsToFloat, fIntBitsToFloat2);
            try {
                GraphicsLayerKt.drawLayer(drawScope, layer);
                return;
            } finally {
                drawScope.getDrawContext().getTransform().translate(-fIntBitsToFloat, -fIntBitsToFloat2);
            }
        }
        int i = ClipOp.Companion.getIntersect-rtfAjoo();
        DrawContext drawContext = drawScope.getDrawContext();
        long j2 = drawContext.getSize-NH-jbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().clipPath-mtrdD-E(path, i);
            drawScope.getDrawContext().getTransform().translate(fIntBitsToFloat, fIntBitsToFloat2);
            try {
                GraphicsLayerKt.drawLayer(drawScope, layer);
                drawScope.getDrawContext().getTransform().translate(-fIntBitsToFloat, -fIntBitsToFloat2);
                drawContext.getCanvas().restore();
                drawContext.setSize-uvyYCjk(j2);
            } catch (Throwable th) {
                drawScope.getDrawContext().getTransform().translate(-fIntBitsToFloat, -fIntBitsToFloat2);
                throw th;
            }
        } catch (Throwable th2) {
            drawContext.getCanvas().restore();
            drawContext.setSize-uvyYCjk(j2);
            throw th2;
        }
    }

    public final BoundsAnimation getBoundsAnimation() {
        return (BoundsAnimation) this.boundsAnimation.getValue();
    }

    public final BoundsProvider getBoundsProvider() {
        return this.boundsProvider;
    }

    /* JADX INFO: renamed from: getClipPathInOverlay$animation, reason: from getter */
    public final Path getClipPathInOverlay() {
        return this.clipPathInOverlay;
    }

    public final GraphicsLayer getLayer() {
        return (GraphicsLayer) this.layer.getValue();
    }

    public final SharedTransitionScope.OverlayClip getOverlayClip() {
        return (SharedTransitionScope.OverlayClip) this.overlayClip.getValue();
    }

    @Override // androidx.compose.animation.LayerRenderer
    public SharedElementEntry getParentState() {
        return this.parentState;
    }

    public final SharedTransitionScope.PlaceholderSize getPlaceholderSize() {
        return (SharedTransitionScope.PlaceholderSize) this.placeholderSize.getValue();
    }

    public final boolean getRenderInOverlayDuringTransition() {
        return ((Boolean) this.renderInOverlayDuringTransition.getValue()).booleanValue();
    }

    public final boolean getRenderOnlyWhenVisible() {
        return ((Boolean) this.renderOnlyWhenVisible.getValue()).booleanValue();
    }

    public final SharedElement getSharedElement() {
        return (SharedElement) this.sharedElement.getValue();
    }

    public final boolean getShouldRenderInOverlay$animation() {
        return getShouldRenderAtAll() && getSharedElement().getBoundsTransformIsActive() && isEnabled() && getRenderInOverlayDuringTransition() && getSharedElement().getScope().isTransitionActive();
    }

    public final boolean getShouldRenderInPlace() {
        if (getSharedElement().getBoundsTransformIsActive()) {
            return !getShouldRenderInOverlay$animation() && getShouldRenderAtAll();
        }
        return true;
    }

    public final boolean getTarget() {
        return getBoundsAnimation().getTarget();
    }

    public final SharedTransitionScope.SharedContentState getUserState() {
        return (SharedTransitionScope.SharedContentState) this.userState.getValue();
    }

    @Override // androidx.compose.animation.LayerRenderer
    public float getZIndex() {
        return this.zIndex.getFloatValue();
    }

    public final boolean isAttached() {
        return ((Boolean) this.isAttached.getValue()).booleanValue();
    }

    public final boolean isEnabled() {
        return isAttached() && getUserState().isEnabledByUser$animation();
    }

    public void onAbandoned() {
    }

    public void onForgotten() {
        getSharedElement().getScope().onEntryRemoved$animation(this);
        getSharedElement().invalidateTargetBoundsProvider();
    }

    public void onRemembered() {
        getSharedElement().getScope().onEntryAdded$animation(this);
        getSharedElement().invalidateTargetBoundsProvider();
    }

    public final void setAttached(boolean z) {
        this.isAttached.setValue(Boolean.valueOf(z));
    }

    public final void setBoundsAnimation(BoundsAnimation boundsAnimation) {
        this.boundsAnimation.setValue(boundsAnimation);
    }

    public final void setBoundsProvider(BoundsProvider boundsProvider) {
        this.boundsProvider = boundsProvider;
    }

    public final void setClipPathInOverlay$animation(Path path) {
        this.clipPathInOverlay = path;
    }

    public final void setLayer(GraphicsLayer graphicsLayer) {
        this.layer.setValue(graphicsLayer);
    }

    public final void setOverlayClip(SharedTransitionScope.OverlayClip overlayClip) {
        this.overlayClip.setValue(overlayClip);
    }

    public void setParentState(SharedElementEntry sharedElementEntry) {
        this.parentState = sharedElementEntry;
    }

    public final void setPlaceholderSize(SharedTransitionScope.PlaceholderSize placeholderSize) {
        this.placeholderSize.setValue(placeholderSize);
    }

    public final void setRenderInOverlayDuringTransition(boolean z) {
        this.renderInOverlayDuringTransition.setValue(Boolean.valueOf(z));
    }

    public final void setRenderOnlyWhenVisible(boolean z) {
        this.renderOnlyWhenVisible.setValue(Boolean.valueOf(z));
    }

    public final void setSharedElement(SharedElement sharedElement) {
        this.sharedElement.setValue(sharedElement);
    }

    public final void setUserState(SharedTransitionScope.SharedContentState sharedContentState) {
        this.userState.setValue(sharedContentState);
    }

    public void setZIndex(float f) {
        this.zIndex.setFloatValue(f);
    }
}
