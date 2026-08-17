package androidx.compose.ui.contentcapture;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.util.LongSparseArray;
import android.view.View;
import android.view.autofill.AutofillId;
import android.view.translation.TranslationRequestValue;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.SemanticsNodeCopy;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeWithAdjustedBounds;
import androidx.compose.ui.semantics.SemanticsNode_androidKt;
import androidx.compose.ui.semantics.SemanticsOwnerKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u0084\u00012\u00020\u00012\u00020\u0002:\u0006\u0082\u0001\u0083\u0001\u0084\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u00106\u001a\u00020\"2\u0006\u00107\u001a\u000208H\u0016J\u0010\u00109\u001a\u00020\"2\u0006\u00107\u001a\u000208H\u0016J\u0010\u0010=\u001a\u00020\"2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020\"2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010A\u001a\u00020\"H\u0080@¢\u0006\u0004\bB\u0010CJ\r\u0010D\u001a\u00020\"H\u0000¢\u0006\u0002\bEJ\r\u0010F\u001a\u00020\"H\u0000¢\u0006\u0002\bGJ\b\u0010H\u001a\u00020\"H\u0002J\u0018\u0010I\u001a\u00020\"2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u000201H\u0002J\u0016\u0010M\u001a\u00020\"2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020)0(H\u0002J\u0018\u0010O\u001a\u00020\"2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020SH\u0002J\b\u0010T\u001a\u00020\"H\u0002J\b\u0010U\u001a\u00020\"H\u0002J\u0016\u0010V\u001a\u0004\u0018\u00010W*\u00020K2\u0006\u0010X\u001a\u00020QH\u0002J&\u0010Y\u001a\u00020\"*\u00020K2\u0018\u0010Z\u001a\u0014\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020K\u0012\u0004\u0012\u00020\"0[H\u0002JG\u0010\\\u001a\u00020\"\"\u0004\b\u0000\u0010]*\b\u0012\u0004\u0012\u0002H]0^2\u0018\u0010Z\u001a\u0014\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u0002H]\u0012\u0004\u0012\u00020\"0[2\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u0002H]\u0012\u0004\u0012\u00020\u001f0`H\u0082\bJ\u001a\u0010a\u001a\u00020\"2\u0006\u0010b\u001a\u00020Q2\b\u0010c\u001a\u0004\u0018\u00010WH\u0002J\u0010\u0010d\u001a\u00020\"2\u0006\u0010b\u001a\u00020QH\u0002J\b\u0010e\u001a\u00020\"H\u0002J\u0018\u0010f\u001a\u00020\"2\u0006\u0010X\u001a\u00020Q2\u0006\u0010g\u001a\u00020KH\u0002J\u0010\u0010h\u001a\u00020\"2\u0006\u0010g\u001a\u00020KH\u0002J\u0010\u0010i\u001a\u00020\"2\u0006\u0010g\u001a\u00020KH\u0002J\r\u0010j\u001a\u00020\"H\u0000¢\u0006\u0002\bkJ\r\u0010l\u001a\u00020\"H\u0000¢\u0006\u0002\bmJ\r\u0010n\u001a\u00020\"H\u0000¢\u0006\u0002\boJ\b\u0010p\u001a\u00020\"H\u0002J\b\u0010q\u001a\u00020\"H\u0002J\b\u0010r\u001a\u00020\"H\u0002J-\u0010s\u001a\u00020\"2\u0006\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020w2\u000e\u0010x\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010z0yH\u0001¢\u0006\u0002\b{J'\u0010|\u001a\u00020\"2\u0006\u0010}\u001a\u00020\u00002\u000f\u0010~\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u00010\u007fH\u0001¢\u0006\u0003\b\u0081\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\"\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(8@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006\u0085\u0001"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/view/View$OnAttachStateChangeListener;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "onContentCaptureSession", "Lkotlin/Function0;", "Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;Lkotlin/jvm/functions/Function0;)V", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "getOnContentCaptureSession", "()Lkotlin/jvm/functions/Function0;", "setOnContentCaptureSession", "(Lkotlin/jvm/functions/Function0;)V", "contentCaptureSession", "getContentCaptureSession$ui$annotations", "()V", "getContentCaptureSession$ui", "()Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;", "setContentCaptureSession$ui", "(Landroidx/compose/ui/contentcapture/ContentCaptureSessionWrapper;)V", "bufferedEvents", "", "Landroidx/compose/ui/contentcapture/ContentCaptureEvent;", "SendRecurringContentCaptureEventsIntervalMillis", "", "translateStatus", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$TranslateStatus;", "currentSemanticsNodesInvalidated", "", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "", "handler", "Landroid/os/Handler;", "getHandler$ui", "()Landroid/os/Handler;", "currentSemanticsNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes$ui", "()Landroidx/collection/IntObjectMap;", "setCurrentSemanticsNodes$ui", "(Landroidx/collection/IntObjectMap;)V", "currentSemanticsNodesSnapshotTimestampMillis", "previousSemanticsNodes", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/platform/SemanticsNodeCopy;", "previousSemanticsRoot", "checkingForSemanticsChanges", "contentCaptureChangeChecker", "Ljava/lang/Runnable;", "onViewAttachedToWindow", "v", "Landroid/view/View;", "onViewDetachedFromWindow", "isEnabled", "isEnabled$ui", "()Z", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "boundsUpdatesEventLoop", "boundsUpdatesEventLoop$ui", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSemanticsChange", "onSemanticsChange$ui", "onLayoutChange", "onLayoutChange$ui", "sendContentCaptureDisappearEvents", "sendContentCaptureAppearEvents", "newNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "oldNode", "checkForContentCapturePropertyChanges", "newSemanticsNodes", "sendContentCaptureTextUpdateEvent", "id", "", "newText", "", "updateSemanticsCopy", "notifySubtreeStateChangeIfNeeded", "toViewStructure", "Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "index", "fastForEachReplacedVisibleChildren", "action", "Lkotlin/Function2;", "fastForEachIndexedWithFilter", "T", "", "predicate", "Lkotlin/Function1;", "bufferContentCaptureViewAppeared", "virtualId", "viewStructure", "bufferContentCaptureViewDisappeared", "notifyContentCaptureChanges", "updateBuffersOnAppeared", "node", "updateBuffersOnDisappeared", "updateTranslationOnAppeared", "onShowTranslation", "onShowTranslation$ui", "onHideTranslation", "onHideTranslation$ui", "onClearTranslation", "onClearTranslation$ui", "showTranslatedText", "hideTranslatedText", "clearTranslatedText", "onCreateVirtualViewTranslationRequests", "virtualIds", "", "supportedFormats", "", "requestsCollector", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "onCreateVirtualViewTranslationRequests$ui", "onVirtualViewTranslationResponses", "contentCaptureManager", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "onVirtualViewTranslationResponses$ui", "TranslateStatus", "ViewTranslationHelperMethods", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidContentCaptureManager implements DefaultLifecycleObserver, View.OnAttachStateChangeListener {
    public static final String VIEW_STRUCTURE_BUNDLE_KEY_ADDITIONAL_INDEX = "android.view.ViewStructure.extra.EXTRA_VIEW_NODE_INDEX";
    public static final String VIEW_STRUCTURE_BUNDLE_KEY_TIMESTAMP = "android.view.contentcapture.EventTimestamp";
    private boolean checkingForSemanticsChanges;
    private ContentCaptureSessionWrapper contentCaptureSession;
    private long currentSemanticsNodesSnapshotTimestampMillis;
    private Function0<? extends ContentCaptureSessionWrapper> onContentCaptureSession;
    private SemanticsNodeCopy previousSemanticsRoot;
    private final AndroidComposeView view;
    public static final int $stable = 8;
    private final List<ContentCaptureEvent> bufferedEvents = new ArrayList();
    private long SendRecurringContentCaptureEventsIntervalMillis = 100;
    private TranslateStatus translateStatus = TranslateStatus.SHOW_ORIGINAL;
    private boolean currentSemanticsNodesInvalidated = true;
    private final Channel<Unit> boundsUpdateChannel = ChannelKt.Channel$default(1, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    private final Handler handler = new Handler(Looper.getMainLooper());
    private IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes = IntObjectMapKt.intObjectMapOf();
    private MutableIntObjectMap<SemanticsNodeCopy> previousSemanticsNodes = IntObjectMapKt.mutableIntObjectMapOf();
    private final Runnable contentCaptureChangeChecker = new Runnable() { // from class: j50
        @Override // java.lang.Runnable
        public final void run() {
            AndroidContentCaptureManager.a(this.b);
        }
    };

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$TranslateStatus;", "", "<init>", "(Ljava/lang/String;I)V", "SHOW_ORIGINAL", "SHOW_TRANSLATED", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public enum TranslateStatus {
        SHOW_ORIGINAL,
        SHOW_TRANSLATED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<TranslateStatus> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0007J \u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0007J \u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$ViewTranslationHelperMethods;", "", "<init>", "()V", "onCreateVirtualViewTranslationRequests", "", "contentCaptureManager", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "virtualIds", "", "supportedFormats", "", "requestsCollector", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "onVirtualViewTranslationResponses", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "doTranslation", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ViewTranslationHelperMethods {
        public static final ViewTranslationHelperMethods INSTANCE = new ViewTranslationHelperMethods();

        private ViewTranslationHelperMethods() {
        }

        public static void a(AndroidContentCaptureManager androidContentCaptureManager, LongSparseArray longSparseArray) {
            INSTANCE.doTranslation(androidContentCaptureManager, longSparseArray);
        }

        private final void doTranslation(AndroidContentCaptureManager contentCaptureManager, LongSparseArray<ViewTranslationResponse> response) {
            TranslationResponseValue value;
            CharSequence text;
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
            SemanticsNode semanticsNode;
            AccessibilityAction accessibilityAction;
            Function1 action;
            int size = response.size();
            for (int i = 0; i < size; i++) {
                long jKeyAt = response.keyAt(i);
                ViewTranslationResponse viewTranslationResponse = response.get(jKeyAt);
                if (viewTranslationResponse != null && (value = viewTranslationResponse.getValue("android:text")) != null && (text = value.getText()) != null && (semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) contentCaptureManager.getCurrentSemanticsNodes$ui().get((int) jKeyAt)) != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetTextSubstitution())) != null && (action = accessibilityAction.getAction()) != null) {
                    List list = null;
                }
            }
        }

        public final void onCreateVirtualViewTranslationRequests(AndroidContentCaptureManager contentCaptureManager, long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
            SemanticsNode semanticsNode;
            String strFastJoinToString$default;
            for (long j : virtualIds) {
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) contentCaptureManager.getCurrentSemanticsNodes$ui().get((int) j);
                if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
                    ViewTranslationRequest.Builder builder = new ViewTranslationRequest.Builder(contentCaptureManager.getView().getAutofillId(), semanticsNode.getId());
                    List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                    if (list != null && (strFastJoinToString$default = ListUtilsKt.fastJoinToString$default(list, "\n", null, null, 0, null, null, 62, null)) != null) {
                        List list2 = null;
                        builder.setValue("android:text", TranslationRequestValue.forText(new AnnotatedString(strFastJoinToString$default, list2, 2, list2)));
                        requestsCollector.accept(builder.build());
                    }
                }
            }
        }

        public final void onVirtualViewTranslationResponses(final AndroidContentCaptureManager contentCaptureManager, final LongSparseArray<ViewTranslationResponse> response) {
            if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
                doTranslation(contentCaptureManager, response);
            } else {
                contentCaptureManager.getView().post(new Runnable() { // from class: androidx.compose.ui.contentcapture.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        AndroidContentCaptureManager.ViewTranslationHelperMethods.a(contentCaptureManager, response);
                    }
                });
            }
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentCaptureEventType.values().length];
            try {
                iArr[ContentCaptureEventType.VIEW_APPEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ContentCaptureEventType.VIEW_DISAPPEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AndroidContentCaptureManager(AndroidComposeView androidComposeView, Function0<? extends ContentCaptureSessionWrapper> function0) {
        this.view = androidComposeView;
        this.onContentCaptureSession = function0;
        this.previousSemanticsRoot = new SemanticsNodeCopy(androidComposeView.getSemanticsOwner().getUnmergedRootSemanticsNode(), IntObjectMapKt.intObjectMapOf());
    }

    public static void a(AndroidContentCaptureManager androidContentCaptureManager) {
        if (androidContentCaptureManager.isEnabled$ui()) {
            Trace.beginSection("ContentCapture:changeChecker");
            try {
                Owner.measureAndLayout$default(androidContentCaptureManager.view, false, 1, null);
                androidContentCaptureManager.sendContentCaptureDisappearEvents();
                Trace.beginSection("ContentCapture:sendAppearEvents");
                try {
                    androidContentCaptureManager.sendContentCaptureAppearEvents(androidContentCaptureManager.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), androidContentCaptureManager.previousSemanticsRoot);
                    Unit unit = Unit.INSTANCE;
                    Trace.endSection();
                    androidContentCaptureManager.checkForContentCapturePropertyChanges(androidContentCaptureManager.getCurrentSemanticsNodes$ui());
                    androidContentCaptureManager.updateSemanticsCopy();
                    androidContentCaptureManager.checkingForSemanticsChanges = false;
                } finally {
                    Trace.endSection();
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }

    private final void bufferContentCaptureViewAppeared(int virtualId, ViewStructureCompat viewStructure) {
        if (viewStructure == null) {
            return;
        }
        this.bufferedEvents.add(new ContentCaptureEvent(virtualId, this.currentSemanticsNodesSnapshotTimestampMillis, ContentCaptureEventType.VIEW_APPEAR, viewStructure));
    }

    private final void bufferContentCaptureViewDisappeared(int virtualId) {
        this.bufferedEvents.add(new ContentCaptureEvent(virtualId, this.currentSemanticsNodesSnapshotTimestampMillis, ContentCaptureEventType.VIEW_DISAPPEAR, null));
    }

    private final void checkForContentCapturePropertyChanges(IntObjectMap<SemanticsNodeWithAdjustedBounds> newSemanticsNodes) {
        int[] iArr;
        long[] jArr;
        int[] iArr2;
        long[] jArr2;
        long j;
        char c;
        long j2;
        int i;
        SemanticsNode semanticsNode;
        int i2;
        SemanticsNode semanticsNode2;
        long j3;
        int i3;
        long[] jArr3;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap = newSemanticsNodes;
        int[] iArr3 = intObjectMap.keys;
        long[] jArr4 = intObjectMap.metadata;
        int length = jArr4.length - 2;
        if (length < 0) {
            return;
        }
        int i4 = 0;
        while (true) {
            long j4 = jArr4[i4];
            char c2 = 7;
            long j5 = -9187201950435737472L;
            if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i5 = 8;
                int i6 = 8 - ((~(i4 - length)) >>> 31);
                int i7 = 0;
                while (i7 < i6) {
                    if ((j4 & 255) < 128) {
                        int i8 = iArr3[(i4 << 3) + i7];
                        c = c2;
                        SemanticsNodeCopy semanticsNodeCopy = (SemanticsNodeCopy) this.previousSemanticsNodes.get(i8);
                        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) intObjectMap.get(i8);
                        SemanticsNode semanticsNode3 = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
                        if (semanticsNode3 == null) {
                            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("no value for specified key");
                            wq6.a();
                            return;
                        }
                        if (semanticsNodeCopy == null) {
                            MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui = semanticsNode3.getUnmergedConfig().getProps$ui();
                            j2 = j5;
                            Object[] objArr = ((ScatterMap) props$ui).keys;
                            long[] jArr5 = ((ScatterMap) props$ui).metadata;
                            int length2 = jArr5.length - 2;
                            if (length2 >= 0) {
                                int i9 = 0;
                                int i10 = i5;
                                while (true) {
                                    long j6 = jArr5[i9];
                                    iArr2 = iArr3;
                                    if ((((~j6) << c) & j6 & j2) != j2) {
                                        int i11 = 8 - ((~(i9 - length2)) >>> 31);
                                        int i12 = 0;
                                        while (i12 < i11) {
                                            if ((j6 & 255) < 128) {
                                                i3 = i12;
                                                SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) objArr[(i9 << 3) + i12];
                                                SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
                                                jArr3 = jArr4;
                                                if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties.getText())) {
                                                    List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode3.getUnmergedConfig(), semanticsProperties.getText());
                                                    sendContentCaptureTextUpdateEvent(semanticsNode3.getId(), String.valueOf(list != null ? (AnnotatedString) CollectionsKt.firstOrNull(list) : null));
                                                }
                                            } else {
                                                i3 = i12;
                                                jArr3 = jArr4;
                                            }
                                            j6 >>= i10;
                                            i12 = i3 + 1;
                                            jArr4 = jArr3;
                                        }
                                        jArr2 = jArr4;
                                        if (i11 != i10) {
                                            break;
                                        }
                                    } else {
                                        jArr2 = jArr4;
                                    }
                                    if (i9 == length2) {
                                        break;
                                    }
                                    i9++;
                                    iArr3 = iArr2;
                                    jArr4 = jArr2;
                                    i10 = 8;
                                }
                            } else {
                                iArr2 = iArr3;
                                jArr2 = jArr4;
                            }
                        } else {
                            iArr2 = iArr3;
                            jArr2 = jArr4;
                            j2 = j5;
                            MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui2 = semanticsNode3.getUnmergedConfig().getProps$ui();
                            Object[] objArr2 = ((ScatterMap) props$ui2).keys;
                            long[] jArr6 = ((ScatterMap) props$ui2).metadata;
                            int length3 = jArr6.length - 2;
                            if (length3 >= 0) {
                                int i13 = 0;
                                while (true) {
                                    long j7 = jArr6[i13];
                                    long[] jArr7 = jArr6;
                                    Object[] objArr3 = objArr2;
                                    if ((((~j7) << c) & j7 & j2) != j2) {
                                        int i14 = 8 - ((~(i13 - length3)) >>> 31);
                                        int i15 = 0;
                                        while (i15 < i14) {
                                            if ((j7 & 255) < 128) {
                                                i2 = i15;
                                                SemanticsPropertyKey semanticsPropertyKey2 = (SemanticsPropertyKey) objArr3[(i13 << 3) + i15];
                                                SemanticsProperties semanticsProperties2 = SemanticsProperties.INSTANCE;
                                                semanticsNode2 = semanticsNode3;
                                                if (Intrinsics.areEqual(semanticsPropertyKey2, semanticsProperties2.getText())) {
                                                    List list2 = (List) SemanticsConfigurationKt.getOrNull(semanticsNodeCopy.getUnmergedConfig(), semanticsProperties2.getText());
                                                    AnnotatedString annotatedString = list2 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list2) : null;
                                                    j3 = j4;
                                                    List list3 = (List) SemanticsConfigurationKt.getOrNull(semanticsNode2.getUnmergedConfig(), semanticsProperties2.getText());
                                                    AnnotatedString annotatedString2 = list3 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list3) : null;
                                                    if (!Intrinsics.areEqual(annotatedString, annotatedString2)) {
                                                        sendContentCaptureTextUpdateEvent(semanticsNode2.getId(), String.valueOf(annotatedString2));
                                                    }
                                                }
                                                j7 >>= 8;
                                                i15 = i2 + 1;
                                                semanticsNode3 = semanticsNode2;
                                                j4 = j3;
                                            } else {
                                                i2 = i15;
                                                semanticsNode2 = semanticsNode3;
                                            }
                                            j3 = j4;
                                            j7 >>= 8;
                                            i15 = i2 + 1;
                                            semanticsNode3 = semanticsNode2;
                                            j4 = j3;
                                        }
                                        semanticsNode = semanticsNode3;
                                        j = j4;
                                        if (i14 != 8) {
                                            break;
                                        }
                                    } else {
                                        semanticsNode = semanticsNode3;
                                        j = j4;
                                    }
                                    if (i13 == length3) {
                                        break;
                                    }
                                    i13++;
                                    objArr2 = objArr3;
                                    jArr6 = jArr7;
                                    semanticsNode3 = semanticsNode;
                                    j4 = j;
                                }
                            }
                            i = 8;
                        }
                        j = j4;
                        i = 8;
                    } else {
                        iArr2 = iArr3;
                        jArr2 = jArr4;
                        j = j4;
                        c = c2;
                        j2 = j5;
                        i = i5;
                    }
                    j4 = j >> i;
                    i7++;
                    intObjectMap = newSemanticsNodes;
                    i5 = i;
                    c2 = c;
                    j5 = j2;
                    iArr3 = iArr2;
                    jArr4 = jArr2;
                }
                iArr = iArr3;
                jArr = jArr4;
                if (i6 != i5) {
                    return;
                }
            } else {
                iArr = iArr3;
                jArr = jArr4;
            }
            if (i4 == length) {
                return;
            }
            i4++;
            intObjectMap = newSemanticsNodes;
            iArr3 = iArr;
            jArr4 = jArr;
        }
    }

    private final void clearTranslatedText() {
        AccessibilityAction accessibilityAction;
        Function0 action;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        Object[] objArr = currentSemanticsNodes$ui.values;
        long[] jArr = currentSemanticsNodes$ui.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        SemanticsConfiguration unmergedConfig$ui = ((SemanticsNodeWithAdjustedBounds) objArr[(i << 3) + i3]).getSemanticsNode().getUnmergedConfig();
                        if (SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getClearTextSubstitution())) != null && (action = accessibilityAction.getAction()) != null) {
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final <T> void fastForEachIndexedWithFilter(List<? extends T> list, Function2<? super Integer, ? super T, Unit> function2, Function1<? super T, Boolean> function1) {
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            T t = list.get(i2);
            if (((Boolean) function1.invoke(t)).booleanValue()) {
                function2.invoke(Integer.valueOf(i), t);
                i++;
            }
        }
    }

    private final void fastForEachReplacedVisibleChildren(SemanticsNode semanticsNode, Function2<? super Integer, ? super SemanticsNode, Unit> function2) {
        List<SemanticsNode> replacedChildren$ui = semanticsNode.getReplacedChildren$ui();
        int size = replacedChildren$ui.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            SemanticsNode semanticsNode2 = replacedChildren$ui.get(i2);
            if (getCurrentSemanticsNodes$ui().containsKey(semanticsNode2.getId())) {
                function2.invoke(Integer.valueOf(i), semanticsNode2);
                i++;
            }
        }
    }

    public static /* synthetic */ void getContentCaptureSession$ui$annotations() {
    }

    private final void hideTranslatedText() {
        AccessibilityAction accessibilityAction;
        Function1 action;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        Object[] objArr = currentSemanticsNodes$ui.values;
        long[] jArr = currentSemanticsNodes$ui.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        SemanticsConfiguration unmergedConfig$ui = ((SemanticsNodeWithAdjustedBounds) objArr[(i << 3) + i3]).getSemanticsNode().getUnmergedConfig();
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), Boolean.TRUE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (action = accessibilityAction.getAction()) != null) {
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final void notifyContentCaptureChanges() {
        ContentCaptureSessionWrapper contentCaptureSessionWrapper = this.contentCaptureSession;
        if (contentCaptureSessionWrapper == null || this.bufferedEvents.isEmpty()) {
            return;
        }
        List<ContentCaptureEvent> list = this.bufferedEvents;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ContentCaptureEvent contentCaptureEvent = list.get(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[contentCaptureEvent.getType().ordinal()];
            if (i2 == 1) {
                ViewStructureCompat structureCompat = contentCaptureEvent.getStructureCompat();
                if (structureCompat != null) {
                    contentCaptureSessionWrapper.notifyViewAppeared(structureCompat.toViewStructure());
                }
            } else if (i2 != 2) {
                bu8.a();
                return;
            } else {
                AutofillId autofillIdNewAutofillId = contentCaptureSessionWrapper.newAutofillId(contentCaptureEvent.getId());
                if (autofillIdNewAutofillId != null) {
                    contentCaptureSessionWrapper.notifyViewDisappeared(autofillIdNewAutofillId);
                }
            }
        }
        contentCaptureSessionWrapper.flush();
        this.bufferedEvents.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySubtreeStateChangeIfNeeded() {
        this.boundsUpdateChannel.trySend-JP2dKIU(Unit.INSTANCE);
    }

    private final void sendContentCaptureAppearEvents(SemanticsNode newNode, final SemanticsNodeCopy oldNode) {
        fastForEachReplacedVisibleChildren(newNode, new Function2<Integer, SemanticsNode, Unit>() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.sendContentCaptureAppearEvents.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public final void invoke(int i, SemanticsNode semanticsNode) {
                if (oldNode.getChildren().contains(semanticsNode.getId())) {
                    return;
                }
                this.updateBuffersOnAppeared(i, semanticsNode);
                this.notifySubtreeStateChangeIfNeeded();
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).intValue(), (SemanticsNode) obj2);
                return Unit.INSTANCE;
            }
        });
        List<SemanticsNode> replacedChildren$ui = newNode.getReplacedChildren$ui();
        int size = replacedChildren$ui.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode = replacedChildren$ui.get(i);
            if (getCurrentSemanticsNodes$ui().containsKey(semanticsNode.getId()) && this.previousSemanticsNodes.containsKey(semanticsNode.getId())) {
                Object obj = this.previousSemanticsNodes.get(semanticsNode.getId());
                if (obj == null) {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("node not present in pruned tree before this change");
                    wq6.a();
                    return;
                }
                sendContentCaptureAppearEvents(semanticsNode, (SemanticsNodeCopy) obj);
            }
        }
    }

    private final void sendContentCaptureDisappearEvents() {
        MutableIntObjectMap<SemanticsNodeCopy> mutableIntObjectMap = this.previousSemanticsNodes;
        int[] iArr = ((IntObjectMap) mutableIntObjectMap).keys;
        long[] jArr = ((IntObjectMap) mutableIntObjectMap).metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = iArr[(i << 3) + i3];
                        if (!getCurrentSemanticsNodes$ui().containsKey(i4)) {
                            bufferContentCaptureViewDisappeared(i4);
                            notifySubtreeStateChangeIfNeeded();
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final void sendContentCaptureTextUpdateEvent(int id, String newText) {
        ContentCaptureSessionWrapper contentCaptureSessionWrapper = this.contentCaptureSession;
        if (contentCaptureSessionWrapper == null) {
            return;
        }
        AutofillId autofillIdNewAutofillId = contentCaptureSessionWrapper.newAutofillId(id);
        if (autofillIdNewAutofillId != null) {
            contentCaptureSessionWrapper.notifyViewTextChanged(autofillIdNewAutofillId, newText);
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid content capture ID");
            wq6.a();
        }
    }

    private final void showTranslatedText() {
        AccessibilityAction accessibilityAction;
        Function1 action;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        Object[] objArr = currentSemanticsNodes$ui.values;
        long[] jArr = currentSemanticsNodes$ui.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        SemanticsConfiguration unmergedConfig$ui = ((SemanticsNodeWithAdjustedBounds) objArr[(i << 3) + i3]).getSemanticsNode().getUnmergedConfig();
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), Boolean.FALSE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (action = accessibilityAction.getAction()) != null) {
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final ViewStructureCompat toViewStructure(SemanticsNode semanticsNode, int i) {
        AutofillIdCompat autofillId;
        AutofillId autofillId2;
        String strM5212toLegacyClassNameV4PA4sw;
        ContentCaptureSessionWrapper contentCaptureSessionWrapper = this.contentCaptureSession;
        if (contentCaptureSessionWrapper == null || (autofillId = ViewCompatShims.getAutofillId(this.view)) == null) {
            return null;
        }
        SemanticsNode parent = semanticsNode.getParent();
        if (parent != null) {
            autofillId2 = contentCaptureSessionWrapper.newAutofillId(parent.getId());
            if (autofillId2 == null) {
                return null;
            }
        } else {
            autofillId2 = autofillId.toAutofillId();
        }
        ViewStructureCompat viewStructureCompatNewVirtualViewStructure = contentCaptureSessionWrapper.newVirtualViewStructure(autofillId2, semanticsNode.getId());
        if (viewStructureCompatNewVirtualViewStructure == null) {
            return null;
        }
        SemanticsConfiguration unmergedConfig$ui = semanticsNode.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (unmergedConfig$ui.contains(semanticsProperties.getPassword())) {
            return null;
        }
        Bundle extras = viewStructureCompatNewVirtualViewStructure.getExtras();
        if (extras != null) {
            extras.putLong(VIEW_STRUCTURE_BUNDLE_KEY_TIMESTAMP, this.currentSemanticsNodesSnapshotTimestampMillis);
            extras.putInt(VIEW_STRUCTURE_BUNDLE_KEY_ADDITIONAL_INDEX, i);
        }
        String str = (String) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, semanticsProperties.getTestTag());
        if (str != null) {
            viewStructureCompatNewVirtualViewStructure.setId(semanticsNode.getId(), null, null, str);
        }
        if (((Boolean) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, semanticsProperties.getIsTraversalGroup())) != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName("android.widget.ViewGroup");
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, semanticsProperties.getText());
        if (list != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
            viewStructureCompatNewVirtualViewStructure.setText(ListUtilsKt.fastJoinToString$default(list, "\n", null, null, 0, null, null, 62, null));
        }
        AnnotatedString annotatedString = (AnnotatedString) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, semanticsProperties.getEditableText());
        if (annotatedString != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName(AndroidComposeViewAccessibilityDelegateCompat.TextFieldClassName);
            viewStructureCompatNewVirtualViewStructure.setText(annotatedString);
        }
        List list2 = (List) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, semanticsProperties.getContentDescription());
        if (list2 != null) {
            viewStructureCompatNewVirtualViewStructure.setContentDescription(ListUtilsKt.fastJoinToString$default(list2, "\n", null, null, 0, null, null, 62, null));
        }
        Role role = (Role) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, semanticsProperties.getRole());
        if (role != null && (strM5212toLegacyClassNameV4PA4sw = SemanticsUtils_androidKt.m5212toLegacyClassNameV4PA4sw(role.getValue())) != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName(strM5212toLegacyClassNameV4PA4sw);
        }
        TextLayoutResult textLayoutResult = SemanticsUtils_androidKt.getTextLayoutResult(unmergedConfig$ui);
        if (textLayoutResult != null) {
            TextLayoutInput layoutInput = textLayoutResult.getLayoutInput();
            viewStructureCompatNewVirtualViewStructure.setTextStyle(TextUnit.m6216getValueimpl(layoutInput.getStyle().m5510getFontSizeXSAIIZE()) * layoutInput.getDensity().getDensity() * layoutInput.getDensity().getFontScale(), 0, 0, 0);
        }
        Rect boundsInParent$ui = semanticsNode.getBoundsInParent$ui();
        viewStructureCompatNewVirtualViewStructure.setDimens((int) boundsInParent$ui.getLeft(), (int) boundsInParent$ui.getTop(), 0, 0, (int) (boundsInParent$ui.getRight() - boundsInParent$ui.getLeft()), (int) (boundsInParent$ui.getBottom() - boundsInParent$ui.getTop()));
        return viewStructureCompatNewVirtualViewStructure;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateBuffersOnAppeared(int index, SemanticsNode node) {
        if (isEnabled$ui()) {
            updateTranslationOnAppeared(node);
            bufferContentCaptureViewAppeared(node.getId(), toViewStructure(node, index));
            fastForEachReplacedVisibleChildren(node, new Function2<Integer, SemanticsNode, Unit>() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.updateBuffersOnAppeared.1
                {
                    super(2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke(((Number) obj).intValue(), (SemanticsNode) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(int i, SemanticsNode semanticsNode) {
                    AndroidContentCaptureManager.this.updateBuffersOnAppeared(i, semanticsNode);
                }
            });
        }
    }

    private final void updateBuffersOnDisappeared(SemanticsNode node) {
        if (isEnabled$ui()) {
            bufferContentCaptureViewDisappeared(node.getId());
            List<SemanticsNode> replacedChildren$ui = node.getReplacedChildren$ui();
            int size = replacedChildren$ui.size();
            for (int i = 0; i < size; i++) {
                updateBuffersOnDisappeared(replacedChildren$ui.get(i));
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x005d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x005f A[LOOP:0: B:5:0x0017->B:15:0x005f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:19:0x0062 A[EDGE_INSN: B:19:0x0062->B:16:0x0062 BREAK  A[LOOP:0: B:5:0x0017->B:15:0x005f], SYNTHETIC] */
    private final void updateSemanticsCopy() {
        this.previousSemanticsNodes.clear();
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes$ui = getCurrentSemanticsNodes$ui();
        int[] iArr = currentSemanticsNodes$ui.keys;
        Object[] objArr = currentSemanticsNodes$ui.values;
        long[] jArr = currentSemanticsNodes$ui.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i != length) {
                        break;
                        break;
                    }
                    i++;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            this.previousSemanticsNodes.set(iArr[i4], new SemanticsNodeCopy(((SemanticsNodeWithAdjustedBounds) objArr[i4]).getSemanticsNode(), getCurrentSemanticsNodes$ui()));
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    } else if (i != length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes$ui());
    }

    private final void updateTranslationOnAppeared(SemanticsNode node) {
        AccessibilityAction accessibilityAction;
        Function1 action;
        Function1 action2;
        SemanticsConfiguration unmergedConfig$ui = node.getUnmergedConfig();
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution());
        if (this.translateStatus == TranslateStatus.SHOW_ORIGINAL && Intrinsics.areEqual(bool, Boolean.TRUE)) {
            AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getShowTextSubstitution());
            if (accessibilityAction2 == null || (action2 = accessibilityAction2.getAction()) == null) {
                return;
            }
            return;
        }
        if (this.translateStatus != TranslateStatus.SHOW_TRANSLATED || !Intrinsics.areEqual(bool, Boolean.FALSE) || (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getShowTextSubstitution())) == null || (action = accessibilityAction.getAction()) == null) {
            return;
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0054  */
    /* JADX WARN: Code duplicated, block: B:24:0x005f  */
    /* JADX WARN: Code duplicated, block: B:26:0x0068  */
    /* JADX WARN: Code duplicated, block: B:29:0x006f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0085  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0082, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(r5, r0) == r1) goto L32;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0082 -> B:13:0x002f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object boundsUpdatesEventLoop$ui(Continuation<? super Unit> continuation) {
        AndroidContentCaptureManager$boundsUpdatesEventLoop$1 androidContentCaptureManager$boundsUpdatesEventLoop$1;
        ChannelIterator it;
        ChannelIterator channelIterator;
        Object objHasNext;
        if (continuation instanceof AndroidContentCaptureManager$boundsUpdatesEventLoop$1) {
            androidContentCaptureManager$boundsUpdatesEventLoop$1 = (AndroidContentCaptureManager$boundsUpdatesEventLoop$1) continuation;
            int i = androidContentCaptureManager$boundsUpdatesEventLoop$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                androidContentCaptureManager$boundsUpdatesEventLoop$1.label = i - Integer.MIN_VALUE;
            } else {
                androidContentCaptureManager$boundsUpdatesEventLoop$1 = new AndroidContentCaptureManager$boundsUpdatesEventLoop$1(this, continuation);
            }
        } else {
            androidContentCaptureManager$boundsUpdatesEventLoop$1 = new AndroidContentCaptureManager$boundsUpdatesEventLoop$1(this, continuation);
        }
        Object obj = androidContentCaptureManager$boundsUpdatesEventLoop$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = androidContentCaptureManager$boundsUpdatesEventLoop$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            it = this.boundsUpdateChannel.iterator();
            androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0 = it;
            androidContentCaptureManager$boundsUpdatesEventLoop$1.label = 1;
            objHasNext = it.hasNext(androidContentCaptureManager$boundsUpdatesEventLoop$1);
            if (objHasNext != coroutine_suspended) {
                channelIterator = it;
                obj = objHasNext;
                if (!((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                channelIterator.next();
                if (isEnabled$ui()) {
                    notifyContentCaptureChanges();
                }
                if (!this.checkingForSemanticsChanges) {
                    this.checkingForSemanticsChanges = true;
                    this.handler.post(this.contentCaptureChangeChecker);
                }
                long j = this.SendRecurringContentCaptureEventsIntervalMillis;
                androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0 = channelIterator;
                androidContentCaptureManager$boundsUpdatesEventLoop$1.label = 2;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            channelIterator = (ChannelIterator) androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0;
            ResultKt.throwOnFailure(obj);
            if (!((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            channelIterator.next();
            if (isEnabled$ui()) {
                notifyContentCaptureChanges();
            }
            if (!this.checkingForSemanticsChanges) {
                this.checkingForSemanticsChanges = true;
                this.handler.post(this.contentCaptureChangeChecker);
            }
            long j2 = this.SendRecurringContentCaptureEventsIntervalMillis;
            androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0 = channelIterator;
            androidContentCaptureManager$boundsUpdatesEventLoop$1.label = 2;
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            channelIterator = (ChannelIterator) androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        it = channelIterator;
        androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0 = it;
        androidContentCaptureManager$boundsUpdatesEventLoop$1.label = 1;
        objHasNext = it.hasNext(androidContentCaptureManager$boundsUpdatesEventLoop$1);
        if (objHasNext != coroutine_suspended) {
            channelIterator = it;
            obj = objHasNext;
            if (!((Boolean) obj).booleanValue()) {
                return Unit.INSTANCE;
            }
            channelIterator.next();
            if (isEnabled$ui()) {
                notifyContentCaptureChanges();
            }
            if (!this.checkingForSemanticsChanges) {
                this.checkingForSemanticsChanges = true;
                this.handler.post(this.contentCaptureChangeChecker);
            }
            long j3 = this.SendRecurringContentCaptureEventsIntervalMillis;
            androidContentCaptureManager$boundsUpdatesEventLoop$1.L$0 = channelIterator;
            androidContentCaptureManager$boundsUpdatesEventLoop$1.label = 2;
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: getContentCaptureSession$ui, reason: from getter */
    public final ContentCaptureSessionWrapper getContentCaptureSession() {
        return this.contentCaptureSession;
    }

    public final IntObjectMap<SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes$ui() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsOwnerKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.getSemanticsOwner(), -1, new Function1<SemanticsNode, Boolean>() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$currentSemanticsNodes$1
                public final Boolean invoke(SemanticsNode semanticsNode) {
                    return Boolean.valueOf(SemanticsNode_androidKt.isAccessibilityIgnoredLink(semanticsNode));
                }
            });
            this.currentSemanticsNodesSnapshotTimestampMillis = System.currentTimeMillis();
        }
        return this.currentSemanticsNodes;
    }

    /* JADX INFO: renamed from: getHandler$ui, reason: from getter */
    public final Handler getHandler() {
        return this.handler;
    }

    public final Function0<ContentCaptureSessionWrapper> getOnContentCaptureSession() {
        return this.onContentCaptureSession;
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public final boolean isEnabled$ui() {
        return ContentCaptureManager.INSTANCE.isEnabled() && this.contentCaptureSession != null;
    }

    public final void onClearTranslation$ui() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        clearTranslatedText();
    }

    public final void onCreateVirtualViewTranslationRequests$ui(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        ViewTranslationHelperMethods.INSTANCE.onCreateVirtualViewTranslationRequests(this, virtualIds, supportedFormats, requestsCollector);
    }

    public final void onHideTranslation$ui() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        hideTranslatedText();
    }

    public final void onLayoutChange$ui() {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui()) {
            notifySubtreeStateChangeIfNeeded();
        }
    }

    public final void onSemanticsChange$ui() {
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled$ui() || this.checkingForSemanticsChanges) {
            return;
        }
        this.checkingForSemanticsChanges = true;
        this.handler.post(this.contentCaptureChangeChecker);
    }

    public final void onShowTranslation$ui() {
        this.translateStatus = TranslateStatus.SHOW_TRANSLATED;
        showTranslatedText();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner owner) {
        this.contentCaptureSession = (ContentCaptureSessionWrapper) this.onContentCaptureSession.invoke();
        updateBuffersOnAppeared(-1, this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        updateBuffersOnDisappeared(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
        this.contentCaptureSession = null;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        this.handler.removeCallbacks(this.contentCaptureChangeChecker);
        this.contentCaptureSession = null;
    }

    public final void onVirtualViewTranslationResponses$ui(AndroidContentCaptureManager contentCaptureManager, LongSparseArray<ViewTranslationResponse> response) {
        ViewTranslationHelperMethods.INSTANCE.onVirtualViewTranslationResponses(contentCaptureManager, response);
    }

    public final void setContentCaptureSession$ui(ContentCaptureSessionWrapper contentCaptureSessionWrapper) {
        this.contentCaptureSession = contentCaptureSessionWrapper;
    }

    public final void setCurrentSemanticsNodes$ui(IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap) {
        this.currentSemanticsNodes = intObjectMap;
    }

    public final void setOnContentCaptureSession(Function0<? extends ContentCaptureSessionWrapper> function0) {
        this.onContentCaptureSession = function0;
    }
}
