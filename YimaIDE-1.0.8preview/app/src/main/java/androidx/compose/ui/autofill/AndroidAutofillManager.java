package androidx.compose.ui.autofill;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.collection.ObjectListKt;
import androidx.compose.ui.focus.FocusListener;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.semantics.SemanticsListener;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u001c\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u000e\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&J\u0014\u0010'\u001a\u00020\u00192\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u0015\u0010-\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b0J\u001d\u00101\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\b4J\u0015\u00105\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b6J\u0015\u00107\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b8J\r\u0010;\u001a\u00020\u0019H\u0000¢\u0006\u0002\b<R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Landroidx/compose/ui/autofill/AndroidAutofillManager;", "Landroidx/compose/ui/autofill/AutofillManager;", "Landroidx/compose/ui/semantics/SemanticsListener;", "Landroidx/compose/ui/focus/FocusListener;", "platformAutofillManager", "Landroidx/compose/ui/autofill/PlatformAutofillManager;", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "view", "Landroid/view/View;", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "packageName", "", "<init>", "(Landroidx/compose/ui/autofill/PlatformAutofillManager;Landroidx/compose/ui/semantics/SemanticsOwner;Landroid/view/View;Landroidx/compose/ui/spatial/RectManager;Ljava/lang/String;)V", "getPlatformAutofillManager", "()Landroidx/compose/ui/autofill/PlatformAutofillManager;", "setPlatformAutofillManager", "(Landroidx/compose/ui/autofill/PlatformAutofillManager;)V", "reusableRect", "Landroid/graphics/Rect;", "rootAutofillId", "Landroid/view/autofill/AutofillId;", "commit", "", "cancel", "onFocusChanged", "previous", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "current", "onSemanticsChanged", "semanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "previousSemanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "populateViewStructure", "rootViewStructure", "Landroid/view/ViewStructure;", "performAutofill", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "currentlyDisplayedIDs", "Landroidx/collection/MutableIntSet;", "requestAutofill", "requestAutofill$ui", "onPostAttach", "onPostAttach$ui", "onPostLayoutNodeReused", "previousSemanticsId", "", "onPostLayoutNodeReused$ui", "onLayoutNodeDeactivated", "onLayoutNodeDeactivated$ui", "onDetach", "onDetach$ui", "pendingAutofillCommit", "", "onEndApplyChanges", "onEndApplyChanges$ui", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidAutofillManager extends AutofillManager implements SemanticsListener, FocusListener {
    public static final int $stable = 8;
    private MutableIntSet currentlyDisplayedIDs;
    private final String packageName;
    private boolean pendingAutofillCommit;
    private PlatformAutofillManager platformAutofillManager;
    private final RectManager rectManager;
    private Rect reusableRect = new Rect();
    private AutofillId rootAutofillId;
    private final SemanticsOwner semanticsOwner;
    private final View view;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AndroidAutofillManager(PlatformAutofillManager platformAutofillManager, SemanticsOwner semanticsOwner, View view, RectManager rectManager, String str) {
        this.platformAutofillManager = platformAutofillManager;
        this.semanticsOwner = semanticsOwner;
        this.view = view;
        this.rectManager = rectManager;
        this.packageName = str;
        view.setImportantForAutofill(1);
        AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(view);
        AutofillId autofillId2 = autofillId != null ? autofillId.toAutofillId() : null;
        if (autofillId2 != null) {
            this.rootAutofillId = autofillId2;
            this.currentlyDisplayedIDs = new MutableIntSet(0, 1, (DefaultConstructorMarker) null);
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
            wq6.a();
            throw null;
        }
    }

    @Override // androidx.compose.ui.autofill.AutofillManager
    public void cancel() {
        this.platformAutofillManager.cancel();
    }

    @Override // androidx.compose.ui.autofill.AutofillManager
    public void commit() {
        this.platformAutofillManager.commit();
    }

    public final PlatformAutofillManager getPlatformAutofillManager() {
        return this.platformAutofillManager;
    }

    public final void onDetach$ui(SemanticsInfo semanticsInfo) {
        if (this.currentlyDisplayedIDs.remove(semanticsInfo.getSemanticsId())) {
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), false);
        }
    }

    public final void onEndApplyChanges$ui() {
        if (this.currentlyDisplayedIDs.isEmpty() && this.pendingAutofillCommit) {
            this.platformAutofillManager.commit();
            this.pendingAutofillCommit = false;
        }
        if (this.currentlyDisplayedIDs.isNotEmpty()) {
            this.pendingAutofillCommit = true;
        }
    }

    @Override // androidx.compose.ui.focus.FocusListener
    public void onFocusChanged(FocusTargetModifierNode previous, FocusTargetModifierNode current) {
        SemanticsInfo semanticsInfoRequireSemanticsInfo;
        SemanticsConfiguration semanticsConfiguration;
        SemanticsInfo semanticsInfoRequireSemanticsInfo2;
        SemanticsConfiguration semanticsConfiguration2;
        if (previous != null && (semanticsInfoRequireSemanticsInfo2 = DelegatableNodeKt.requireSemanticsInfo(previous)) != null && (semanticsConfiguration2 = semanticsInfoRequireSemanticsInfo2.getSemanticsConfiguration()) != null && AndroidAutofillManager_androidKt.isAutofillable(semanticsConfiguration2)) {
            this.platformAutofillManager.notifyViewExited(this.view, semanticsInfoRequireSemanticsInfo2.getSemanticsId());
        }
        if (current == null || (semanticsInfoRequireSemanticsInfo = DelegatableNodeKt.requireSemanticsInfo(current)) == null || (semanticsConfiguration = semanticsInfoRequireSemanticsInfo.getSemanticsConfiguration()) == null || !AndroidAutofillManager_androidKt.isAutofillable(semanticsConfiguration)) {
            return;
        }
        final int semanticsId = semanticsInfoRequireSemanticsInfo.getSemanticsId();
        this.rectManager.getRects().withRect(semanticsId, new Function4<Integer, Integer, Integer, Integer, Unit>() { // from class: androidx.compose.ui.autofill.AndroidAutofillManager$onFocusChanged$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                invoke(((Number) obj).intValue(), ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int i2, int i3, int i4) {
                this.this$0.getPlatformAutofillManager().notifyViewEntered(this.this$0.view, semanticsId, new Rect(i, i2, i3, i4));
            }
        });
    }

    public final void onLayoutNodeDeactivated$ui(SemanticsInfo semanticsInfo) {
        if (this.currentlyDisplayedIDs.remove(semanticsInfo.getSemanticsId())) {
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), false);
        }
    }

    public final void onPostAttach$ui(SemanticsInfo semanticsInfo) {
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        if (semanticsConfiguration == null || !AndroidAutofillManager_androidKt.isRelatedToAutoCommit(semanticsConfiguration)) {
            return;
        }
        this.currentlyDisplayedIDs.add(semanticsInfo.getSemanticsId());
        this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), true);
    }

    public final void onPostLayoutNodeReused$ui(SemanticsInfo semanticsInfo, int previousSemanticsId) {
        if (this.currentlyDisplayedIDs.remove(previousSemanticsId)) {
            this.platformAutofillManager.notifyViewVisibilityChanged(this.view, previousSemanticsId, false);
        }
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        if (semanticsConfiguration == null || !AndroidAutofillManager_androidKt.isRelatedToAutoCommit(semanticsConfiguration)) {
            return;
        }
        this.currentlyDisplayedIDs.add(semanticsInfo.getSemanticsId());
        this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsInfo.getSemanticsId(), true);
    }

    @Override // androidx.compose.ui.semantics.SemanticsListener
    public void onSemanticsChanged(SemanticsInfo semanticsInfo, SemanticsConfiguration previousSemanticsConfiguration) {
        Boolean bool;
        AnnotatedString annotatedString;
        AnnotatedString annotatedString2;
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        int semanticsId = semanticsInfo.getSemanticsId();
        String text = (previousSemanticsConfiguration == null || (annotatedString2 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(previousSemanticsConfiguration, SemanticsProperties.INSTANCE.getInputText())) == null) ? null : annotatedString2.getText();
        String text2 = (semanticsConfiguration == null || (annotatedString = (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getInputText())) == null) ? null : annotatedString.getText();
        boolean z = false;
        if (text != text2) {
            if (text == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, true);
            } else if (text2 == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, false);
            } else if (Intrinsics.areEqual((ContentDataType) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getContentDataType()), ContentDataType.INSTANCE.getText())) {
                this.platformAutofillManager.notifyValueChanged(this.view, semanticsId, AutofillApi26Helper.INSTANCE.getAutofillTextValue(text2));
            }
        }
        ToggleableState toggleableState = previousSemanticsConfiguration != null ? (ToggleableState) SemanticsConfigurationKt.getOrNull(previousSemanticsConfiguration, SemanticsProperties.INSTANCE.getToggleableState()) : null;
        ToggleableState toggleableState2 = semanticsConfiguration != null ? (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getToggleableState()) : null;
        if (toggleableState != toggleableState2) {
            if (toggleableState == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, true);
            } else if (toggleableState2 == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, false);
            } else if (Intrinsics.areEqual((ContentDataType) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getContentDataType()), ContentDataType.INSTANCE.getToggle())) {
                int i = WhenMappings.$EnumSwitchMapping$0[toggleableState2.ordinal()];
                if (i != 1) {
                    bool = i != 2 ? null : Boolean.FALSE;
                } else {
                    bool = Boolean.TRUE;
                }
                if (bool != null) {
                    this.platformAutofillManager.notifyValueChanged(this.view, semanticsId, AutofillApi26Helper.INSTANCE.getAutofillToggleValue(bool.booleanValue()));
                }
            }
        }
        FillableData fillableData = previousSemanticsConfiguration != null ? (FillableData) SemanticsConfigurationKt.getOrNull(previousSemanticsConfiguration, SemanticsProperties.INSTANCE.getFillableData()) : null;
        FillableData fillableData2 = semanticsConfiguration != null ? (FillableData) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getFillableData()) : null;
        if (!Intrinsics.areEqual(fillableData, fillableData2)) {
            if (fillableData == null) {
                this.platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, true);
            } else {
                PlatformAutofillManager platformAutofillManager = this.platformAutofillManager;
                if (fillableData2 == null) {
                    platformAutofillManager.notifyViewVisibilityChanged(this.view, semanticsId, false);
                } else {
                    platformAutofillManager.notifyValueChanged(this.view, semanticsId, ((AndroidFillableData) fillableData2).getAutofillValue());
                }
            }
        }
        boolean z2 = previousSemanticsConfiguration != null && AndroidAutofillManager_androidKt.isRelatedToAutoCommit(previousSemanticsConfiguration);
        if (semanticsConfiguration != null && AndroidAutofillManager_androidKt.isRelatedToAutoCommit(semanticsConfiguration)) {
            z = true;
        }
        if (z2 != z) {
            MutableIntSet mutableIntSet = this.currentlyDisplayedIDs;
            if (z) {
                mutableIntSet.add(semanticsId);
            } else {
                mutableIntSet.remove(semanticsId);
            }
        }
    }

    public final void performAutofill(SparseArray<AutofillValue> values) {
        SemanticsConfiguration semanticsConfiguration;
        Function1 action;
        Function1 action2;
        int size = values.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = values.keyAt(i);
            AutofillValue autofillValue = values.get(iKeyAt);
            SemanticsInfo semanticsInfo = this.semanticsOwner.get$ui(iKeyAt);
            if (semanticsInfo != null && (semanticsConfiguration = semanticsInfo.getSemanticsConfiguration()) != null) {
                SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
                AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsActions.getOnAutofillText());
                if (accessibilityAction != null && (action2 = accessibilityAction.getAction()) != null) {
                }
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsActions.getOnFillData());
                if (accessibilityAction2 != null && (action = accessibilityAction2.getAction()) != null) {
                }
            }
        }
    }

    public final void populateViewStructure(ViewStructure rootViewStructure) {
        AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
        SemanticsInfo rootInfo$ui = this.semanticsOwner.getRootInfo$ui();
        PopulateViewStructure_androidKt.populate(rootViewStructure, rootInfo$ui, this.rootAutofillId, this.packageName, this.rectManager);
        MutableObjectList mutableObjectListMutableObjectListOf = ObjectListKt.mutableObjectListOf(rootInfo$ui, rootViewStructure);
        while (mutableObjectListMutableObjectListOf.isNotEmpty()) {
            Object objRemoveAt = mutableObjectListMutableObjectListOf.removeAt(((ObjectList) mutableObjectListMutableObjectListOf)._size - 1);
            objRemoveAt.getClass();
            ViewStructure viewStructure = (ViewStructure) objRemoveAt;
            Object objRemoveAt2 = mutableObjectListMutableObjectListOf.removeAt(((ObjectList) mutableObjectListMutableObjectListOf)._size - 1);
            objRemoveAt2.getClass();
            List<SemanticsInfo> childrenInfo = ((SemanticsInfo) objRemoveAt2).getChildrenInfo();
            int size = childrenInfo.size();
            for (int i = 0; i < size; i++) {
                SemanticsInfo semanticsInfo = childrenInfo.get(i);
                if (!semanticsInfo.getIsDeactivated() && semanticsInfo.isAttached() && semanticsInfo.isPlaced()) {
                    SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
                    if (semanticsConfiguration == null || !AndroidAutofillManager_androidKt.isRelatedToAutofill(semanticsConfiguration)) {
                        mutableObjectListMutableObjectListOf.add(semanticsInfo);
                        mutableObjectListMutableObjectListOf.add(viewStructure);
                    } else {
                        ViewStructure viewStructureNewChild = autofillApi26Helper.newChild(viewStructure, autofillApi26Helper.addChildCount(viewStructure, 1));
                        PopulateViewStructure_androidKt.populate(viewStructureNewChild, semanticsInfo, this.rootAutofillId, this.packageName, this.rectManager);
                        mutableObjectListMutableObjectListOf.add(semanticsInfo);
                        mutableObjectListMutableObjectListOf.add(viewStructureNewChild);
                    }
                }
            }
        }
    }

    public final void requestAutofill$ui(final SemanticsInfo semanticsInfo) {
        this.rectManager.getRects().withRect(semanticsInfo.getSemanticsId(), new Function4<Integer, Integer, Integer, Integer, Unit>() { // from class: androidx.compose.ui.autofill.AndroidAutofillManager$requestAutofill$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public final void invoke(int i, int i2, int i3, int i4) {
                this.this$0.reusableRect.set(i, i2, i3, i4);
                this.this$0.getPlatformAutofillManager().requestAutofill(this.this$0.view, semanticsInfo.getSemanticsId(), this.this$0.reusableRect);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                invoke(((Number) obj).intValue(), ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
                return Unit.INSTANCE;
            }
        });
    }

    public final void setPlatformAutofillManager(PlatformAutofillManager platformAutofillManager) {
        this.platformAutofillManager = platformAutofillManager;
    }
}
