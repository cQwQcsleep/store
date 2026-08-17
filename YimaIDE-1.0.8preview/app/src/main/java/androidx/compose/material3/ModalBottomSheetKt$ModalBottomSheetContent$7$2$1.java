package androidx.compose.material3;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ModalBottomSheetKt$ModalBottomSheetContent$7$2$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function0<Unit> $animateToDismiss;
    final /* synthetic */ String $collapseActionLabel;
    final /* synthetic */ String $dismissActionLabel;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ String $expandActionLabel;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetGesturesEnabled;
    final /* synthetic */ SheetState $sheetState;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.Expanded.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ModalBottomSheetKt$ModalBottomSheetContent$7$2$1(SheetState sheetState, Function0<Unit> function0, CoroutineScope coroutineScope, boolean z, String str, String str2, String str3, Function2<? super Composer, ? super Integer, Unit> function2) {
        this.$sheetState = sheetState;
        this.$animateToDismiss = function0;
        this.$scope = coroutineScope;
        this.$sheetGesturesEnabled = z;
        this.$dismissActionLabel = str;
        this.$expandActionLabel = str2;
        this.$collapseActionLabel = str3;
        this.$dragHandle = function2;
    }

    public static Unit a(SheetState sheetState, Function0 function0, CoroutineScope coroutineScope) {
        int i = WhenMappings.$EnumSwitchMapping$0[sheetState.getCurrentValue().ordinal()];
        if (i == 1) {
            function0.invoke();
            Unit unit = Unit.INSTANCE;
        } else if (i != 2) {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2(sheetState, null), 3, (Object) null);
        } else {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1(sheetState, null), 3, (Object) null);
        }
        return Unit.INSTANCE;
    }

    public static boolean b(Function0 function0) {
        function0.invoke();
        return true;
    }

    public static boolean c(SheetState sheetState, CoroutineScope coroutineScope) {
        if (!((Boolean) sheetState.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.PartiallyExpanded)).booleanValue()) {
            return true;
        }
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$2$1$1$3$1(sheetState, null), 3, (Object) null);
        return true;
    }

    public static Unit d(boolean z, final SheetState sheetState, String str, String str2, String str3, final Function0 function0, final CoroutineScope coroutineScope, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (z) {
            SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str, new Function0() { // from class: androidx.compose.material3.h2
                public final Object invoke() {
                    return Boolean.valueOf(ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.b(function0));
                }
            });
            if (sheetState.getCurrentValue() == SheetValue.PartiallyExpanded) {
                SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str2, new Function0() { // from class: androidx.compose.material3.i2
                    public final Object invoke() {
                        return Boolean.valueOf(ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.e(sheetState, coroutineScope, sheetState));
                    }
                });
            } else if (sheetState.getHasPartiallyExpandedState()) {
                SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str3, new Function0() { // from class: androidx.compose.material3.j2
                    public final Object invoke() {
                        return Boolean.valueOf(ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.c(sheetState, coroutineScope));
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    public static boolean e(SheetState sheetState, CoroutineScope coroutineScope, SheetState sheetState2) {
        if (!((Boolean) sheetState.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.Expanded)).booleanValue()) {
            return true;
        }
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$2$1$1$2$1(sheetState2, null), 3, (Object) null);
        return true;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2000500644, i, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.kt:383)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean zChanged = composer.changed(this.$sheetState) | composer.changed(this.$animateToDismiss) | composer.changedInstance(this.$scope);
        final SheetState sheetState = this.$sheetState;
        final Function0<Unit> function0 = this.$animateToDismiss;
        final CoroutineScope coroutineScope = this.$scope;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: androidx.compose.material3.f2
                public final Object invoke() {
                    return ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.a(sheetState, function0, coroutineScope);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifier = ClickableKt.clickable-XHw0xAI$default(companion, false, (String) null, (Role) null, (Function0) objRememberedValue, 7, (Object) null);
        boolean zChanged2 = composer.changed(this.$sheetGesturesEnabled) | composer.changed(this.$sheetState) | composer.changed(this.$dismissActionLabel) | composer.changed(this.$animateToDismiss) | composer.changed(this.$expandActionLabel) | composer.changedInstance(this.$scope) | composer.changed(this.$collapseActionLabel);
        final boolean z = this.$sheetGesturesEnabled;
        final SheetState sheetState2 = this.$sheetState;
        final String str = this.$dismissActionLabel;
        final String str2 = this.$expandActionLabel;
        final String str3 = this.$collapseActionLabel;
        final Function0<Unit> function1 = this.$animateToDismiss;
        final CoroutineScope coroutineScope2 = this.$scope;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Function1 function2 = new Function1() { // from class: androidx.compose.material3.g2
                public final Object invoke(Object obj) {
                    return ModalBottomSheetKt$ModalBottomSheetContent$7$2$1.d(z, sheetState2, str, str2, str3, function1, coroutineScope2, (SemanticsPropertyReceiver) obj);
                }
            };
            composer.updateRememberedValue(function2);
            objRememberedValue2 = function2;
        }
        Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) objRememberedValue2);
        Function2<Composer, Integer, Unit> function3 = this.$dragHandle;
        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierSemantics);
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        Function0<ComposeUiNode> constructor = companion2.getConstructor();
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        function3.invoke(composer, 0);
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
