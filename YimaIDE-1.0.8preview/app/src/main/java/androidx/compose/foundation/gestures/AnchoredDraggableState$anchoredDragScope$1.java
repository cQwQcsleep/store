package androidx.compose.foundation.gestures;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017R\u001e\u0010\u0002\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0007\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u001e\u0010\b\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0004\"\u0004\b\n\u0010\u0006R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"androidx/compose/foundation/gestures/AnchoredDraggableState$anchoredDragScope$1", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "leftBound", "getLeftBound", "()Ljava/lang/Object;", "setLeftBound", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "rightBound", "getRightBound", "setRightBound", "distance", "", "getDistance", "()F", "setDistance", "(F)V", "dragTo", "", "newOffset", "lastKnownVelocity", "updateIfNeeded", "isMovingForward", "", "updateBounds", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AnchoredDraggableState$anchoredDragScope$1 implements AnchoredDragScope {
    private float distance = Float.NaN;
    private T leftBound;
    private T rightBound;
    final /* synthetic */ AnchoredDraggableState<T> this$0;

    public AnchoredDraggableState$anchoredDragScope$1(AnchoredDraggableState<T> anchoredDraggableState) {
        this.this$0 = anchoredDraggableState;
    }

    @Override // androidx.compose.foundation.gestures.AnchoredDragScope
    public void dragTo(float newOffset, float lastKnownVelocity) {
        float offset = this.this$0.getOffset();
        this.this$0.setOffset(newOffset);
        this.this$0.setLastVelocity(lastKnownVelocity);
        if (Float.isNaN(offset)) {
            return;
        }
        updateIfNeeded(newOffset >= offset);
    }

    public final float getDistance() {
        return this.distance;
    }

    public final T getLeftBound() {
        return this.leftBound;
    }

    public final T getRightBound() {
        return this.rightBound;
    }

    public final void setDistance(float f) {
        this.distance = f;
    }

    public final void setLeftBound(T t) {
        this.leftBound = t;
    }

    public final void setRightBound(T t) {
        this.rightBound = t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v14, types: [T, java.lang.Object] */
    public final void updateBounds(boolean isMovingForward) {
        T currentValue;
        Object currentValue2;
        float fPositionOf = this.this$0.getAnchors().positionOf(this.this$0.getCurrentValue());
        float offset = this.this$0.getOffset();
        AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
        if (offset == fPositionOf) {
            Object objClosestAnchor = this.this$0.getAnchors().closestAnchor(anchoredDraggableState.getOffset() + (isMovingForward ? 1.0f : -1.0f), isMovingForward);
            T t = objClosestAnchor;
            if (objClosestAnchor == null) {
                currentValue2 = this.this$0.getCurrentValue();
            }
            if (isMovingForward) {
                t = currentValue2;
                this.leftBound = this.this$0.getCurrentValue();
                this.rightBound = t;
            } else {
                t = currentValue2;
                this.leftBound = t;
                this.rightBound = this.this$0.getCurrentValue();
            }
        } else {
            Object objClosestAnchor2 = anchoredDraggableState.getAnchors().closestAnchor(this.this$0.getOffset(), false);
            if (objClosestAnchor2 == null) {
                currentValue = objClosestAnchor2;
                currentValue = this.this$0.getCurrentValue();
            }
            currentValue = objClosestAnchor2;
            Object objClosestAnchor3 = this.this$0.getAnchors().closestAnchor(this.this$0.getOffset(), true);
            T currentValue3 = objClosestAnchor3;
            if (objClosestAnchor3 == null) {
                currentValue3 = this.this$0.getCurrentValue();
            }
            this.leftBound = currentValue;
            this.rightBound = currentValue3;
        }
        DraggableAnchors anchors = this.this$0.getAnchors();
        T t2 = this.leftBound;
        t2.getClass();
        float fPositionOf2 = anchors.positionOf(t2);
        DraggableAnchors anchors2 = this.this$0.getAnchors();
        T t3 = this.rightBound;
        t3.getClass();
        this.distance = Math.abs(fPositionOf2 - anchors2.positionOf(t3));
    }

    public final void updateIfNeeded(boolean isMovingForward) {
        updateBounds(isMovingForward);
        if (Math.abs(this.this$0.getOffset() - this.this$0.getAnchors().positionOf(this.this$0.getCurrentValue())) >= this.distance / 2.0f) {
            Object currentValue = isMovingForward ? this.rightBound : this.leftBound;
            if (currentValue == null) {
                currentValue = this.this$0.getCurrentValue();
            }
            if (((Boolean) this.this$0.getConfirmValueChange$foundation().invoke(currentValue)).booleanValue()) {
                this.this$0.setCurrentValue(currentValue);
            }
        }
    }
}
