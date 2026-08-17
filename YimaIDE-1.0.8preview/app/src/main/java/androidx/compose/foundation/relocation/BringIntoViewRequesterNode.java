package androidx.compose.foundation.relocation;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewRequesterNode;", "Landroidx/compose/ui/Modifier$Node;", "requester", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "<init>", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;)V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "onAttach", "", "updateRequester", "disposeRequester", "onDetach", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BringIntoViewRequesterNode extends Modifier.Node {
    public static final int $stable = 8;
    private BringIntoViewRequester requester;
    private final boolean shouldAutoInvalidate;

    public BringIntoViewRequesterNode(BringIntoViewRequester bringIntoViewRequester) {
        this.requester = bringIntoViewRequester;
    }

    private final void disposeRequester() {
        BringIntoViewRequester bringIntoViewRequester = this.requester;
        if (bringIntoViewRequester instanceof BringIntoViewRequesterImpl) {
            bringIntoViewRequester.getClass();
            ((BringIntoViewRequesterImpl) bringIntoViewRequester).getNodes().remove(this);
        }
    }

    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    public void onAttach() {
        updateRequester(this.requester);
    }

    public void onDetach() {
        disposeRequester();
    }

    public final void updateRequester(BringIntoViewRequester requester) {
        disposeRequester();
        if (requester instanceof BringIntoViewRequesterImpl) {
            ((BringIntoViewRequesterImpl) requester).getNodes().add(this);
        }
        this.requester = requester;
    }
}
