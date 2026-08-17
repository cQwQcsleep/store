package androidx.compose.ui.platform.coreshims;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ContentCaptureSessionCompat implements ContentCaptureSessionWrapper {
    private static final String KEY_VIEW_TREE_APPEARED = "TREAT_AS_VIEW_TREE_APPEARED";
    private static final String KEY_VIEW_TREE_APPEARING = "TREAT_AS_VIEW_TREE_APPEARING";
    private final View mView;
    private final Object mWrappedObj;

    public static class Api23Impl {
        private Api23Impl() {
        }

        public static Bundle getExtras(ViewStructure viewStructure) {
            return viewStructure.getExtras();
        }
    }

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static AutofillId newAutofillId(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
            return contentCaptureSession.newAutofillId(autofillId, j);
        }

        public static ViewStructure newViewStructure(ContentCaptureSession contentCaptureSession, View view) {
            return contentCaptureSession.newViewStructure(view);
        }

        public static ViewStructure newVirtualViewStructure(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
            return contentCaptureSession.newVirtualViewStructure(autofillId, j);
        }

        public static void notifyViewAppeared(ContentCaptureSession contentCaptureSession, ViewStructure viewStructure) {
            contentCaptureSession.notifyViewAppeared(viewStructure);
        }

        public static void notifyViewDisappeared(ContentCaptureSession contentCaptureSession, AutofillId autofillId) {
            contentCaptureSession.notifyViewDisappeared(autofillId);
        }

        public static void notifyViewTextChanged(ContentCaptureSession contentCaptureSession, AutofillId autofillId, CharSequence charSequence) {
            contentCaptureSession.notifyViewTextChanged(autofillId, charSequence);
        }

        public static void notifyViewsDisappeared(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long[] jArr) {
            contentCaptureSession.notifyViewsDisappeared(autofillId, jArr);
        }
    }

    public static class Api34Impl {
        private Api34Impl() {
        }

        public static void notifyViewsAppeared(ContentCaptureSession contentCaptureSession, List<ViewStructure> list) {
            contentCaptureSession.notifyViewsAppeared(list);
        }
    }

    private ContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View view) {
        this.mWrappedObj = contentCaptureSession;
        this.mView = view;
    }

    public static ContentCaptureSessionCompat toContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View view) {
        return new ContentCaptureSessionCompat(contentCaptureSession, view);
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public void flush() {
        ContentCaptureSession contentCaptureSession = (ContentCaptureSession) this.mWrappedObj;
        AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(this.mView);
        Objects.requireNonNull(autofillId);
        Api29Impl.notifyViewsDisappeared(contentCaptureSession, autofillId.toAutofillId(), new long[]{Long.MIN_VALUE});
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public AutofillId newAutofillId(long j) {
        ContentCaptureSession contentCaptureSession = (ContentCaptureSession) this.mWrappedObj;
        AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(this.mView);
        Objects.requireNonNull(autofillId);
        return Api29Impl.newAutofillId(contentCaptureSession, autofillId.toAutofillId(), j);
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public ViewStructureCompat newVirtualViewStructure(AutofillId autofillId, long j) {
        return ViewStructureCompat.toViewStructureCompat(Api29Impl.newVirtualViewStructure((ContentCaptureSession) this.mWrappedObj, autofillId, j));
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public void notifyViewAppeared(ViewStructure viewStructure) {
        Api29Impl.notifyViewAppeared((ContentCaptureSession) this.mWrappedObj, viewStructure);
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public void notifyViewDisappeared(AutofillId autofillId) {
        Api29Impl.notifyViewDisappeared((ContentCaptureSession) this.mWrappedObj, autofillId);
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public void notifyViewTextChanged(AutofillId autofillId, CharSequence charSequence) {
        Api29Impl.notifyViewTextChanged((ContentCaptureSession) this.mWrappedObj, autofillId, charSequence);
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public void notifyViewsAppeared(List<ViewStructure> list) {
        int i = Build.VERSION.SDK_INT;
        Object obj = this.mWrappedObj;
        if (i >= 34) {
            Api34Impl.notifyViewsAppeared((ContentCaptureSession) obj, list);
            return;
        }
        ViewStructure viewStructureNewViewStructure = Api29Impl.newViewStructure((ContentCaptureSession) obj, this.mView);
        Api23Impl.getExtras(viewStructureNewViewStructure).putBoolean(KEY_VIEW_TREE_APPEARING, true);
        Api29Impl.notifyViewAppeared((ContentCaptureSession) this.mWrappedObj, viewStructureNewViewStructure);
        int i2 = 0;
        while (true) {
            int size = list.size();
            Object obj2 = this.mWrappedObj;
            if (i2 >= size) {
                ViewStructure viewStructureNewViewStructure2 = Api29Impl.newViewStructure((ContentCaptureSession) obj2, this.mView);
                Api23Impl.getExtras(viewStructureNewViewStructure2).putBoolean(KEY_VIEW_TREE_APPEARED, true);
                Api29Impl.notifyViewAppeared((ContentCaptureSession) this.mWrappedObj, viewStructureNewViewStructure2);
                return;
            }
            Api29Impl.notifyViewAppeared((ContentCaptureSession) obj2, list.get(i2));
            i2++;
        }
    }

    @Override // androidx.compose.ui.contentcapture.ContentCaptureSessionWrapper
    public void notifyViewsDisappeared(long[] jArr) {
        int i = Build.VERSION.SDK_INT;
        Object obj = this.mWrappedObj;
        if (i >= 34) {
            AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(this.mView);
            Objects.requireNonNull(autofillId);
            Api29Impl.notifyViewsDisappeared((ContentCaptureSession) obj, autofillId.toAutofillId(), jArr);
            return;
        }
        ViewStructure viewStructureNewViewStructure = Api29Impl.newViewStructure((ContentCaptureSession) obj, this.mView);
        Api23Impl.getExtras(viewStructureNewViewStructure).putBoolean(KEY_VIEW_TREE_APPEARING, true);
        Api29Impl.notifyViewAppeared((ContentCaptureSession) this.mWrappedObj, viewStructureNewViewStructure);
        ContentCaptureSession contentCaptureSession = (ContentCaptureSession) this.mWrappedObj;
        AutofillIdCompat autofillId2 = ViewCompatShims.getAutofillId(this.mView);
        Objects.requireNonNull(autofillId2);
        Api29Impl.notifyViewsDisappeared(contentCaptureSession, autofillId2.toAutofillId(), jArr);
        ViewStructure viewStructureNewViewStructure2 = Api29Impl.newViewStructure((ContentCaptureSession) this.mWrappedObj, this.mView);
        Api23Impl.getExtras(viewStructureNewViewStructure2).putBoolean(KEY_VIEW_TREE_APPEARED, true);
        Api29Impl.notifyViewAppeared((ContentCaptureSession) this.mWrappedObj, viewStructureNewViewStructure2);
    }

    public ContentCaptureSession toContentCaptureSession() {
        return (ContentCaptureSession) this.mWrappedObj;
    }
}
