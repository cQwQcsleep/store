package androidx.compose.ui.contentcapture;

import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000bH&J\u001b\u0010\u000f\u001a\u00020\u000b2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\r0\u0011¢\u0006\u0002\b\u0012H&J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0003H&J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;", "", "newAutofillId", "Landroid/view/autofill/AutofillId;", "virtualChildId", "", "newVirtualViewStructure", "Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "parentId", "virtualId", "notifyViewAppeared", "", "node", "Landroid/view/ViewStructure;", "flush", "notifyViewsAppeared", "appearedNodes", "", "Lkotlin/jvm/JvmSuppressWildcards;", "notifyViewsDisappeared", "virtualIds", "", "notifyViewDisappeared", "id", "notifyViewTextChanged", "text", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface ContentCaptureSessionWrapper {
    void flush();

    AutofillId newAutofillId(long virtualChildId);

    ViewStructureCompat newVirtualViewStructure(AutofillId parentId, long virtualId);

    void notifyViewAppeared(ViewStructure node);

    void notifyViewDisappeared(AutofillId id);

    void notifyViewTextChanged(AutofillId id, CharSequence text);

    void notifyViewsAppeared(List<ViewStructure> appearedNodes);

    void notifyViewsDisappeared(long[] virtualIds);
}
