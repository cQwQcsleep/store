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
public final class BottomSheetScaffoldKt$StandardBottomSheet$3$1$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ String $dismissActionLabel;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ String $expandActionLabel;
    final /* synthetic */ String $partialExpandActionLabel;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ SheetState $state;

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
    public BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(SheetState sheetState, CoroutineScope coroutineScope, boolean z, String str, String str2, String str3, Function2<? super Composer, ? super Integer, Unit> function2) {
        this.$state = sheetState;
        this.$scope = coroutineScope;
        this.$sheetSwipeEnabled = z;
        this.$expandActionLabel = str;
        this.$partialExpandActionLabel = str2;
        this.$dismissActionLabel = str3;
        this.$dragHandle = function2;
    }

    public static Unit a(final SheetState sheetState, boolean z, String str, String str2, String str3, final CoroutineScope coroutineScope, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (sheetState.getAnchoredDraggableState$material3().getAnchors().getSize() > 1 && z) {
            SheetValue currentValue = sheetState.getCurrentValue();
            SheetValue sheetValue = SheetValue.PartiallyExpanded;
            if (currentValue == sheetValue) {
                if (((Boolean) sheetState.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(SheetValue.Expanded)).booleanValue()) {
                    SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str, new Function0() { // from class: androidx.compose.material3.i
                        public final Object invoke() {
                            return Boolean.valueOf(BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.d(coroutineScope, sheetState));
                        }
                    });
                }
            } else if (((Boolean) sheetState.getAnchoredDraggableState$material3().getConfirmValueChange$material3().invoke(sheetValue)).booleanValue()) {
                SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str2, new Function0() { // from class: androidx.compose.material3.j
                    public final Object invoke() {
                        return Boolean.valueOf(BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.e(coroutineScope, sheetState));
                    }
                });
            }
            if (!sheetState.getSkipHiddenState()) {
                SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str3, new Function0() { // from class: androidx.compose.material3.k
                    public final Object invoke() {
                        return Boolean.valueOf(BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.c(coroutineScope, sheetState));
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit b(SheetState sheetState, CoroutineScope coroutineScope) {
        int i = WhenMappings.$EnumSwitchMapping$0[sheetState.getCurrentValue().ordinal()];
        if (i == 1) {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1(sheetState, null), 3, (Object) null);
        } else if (i != 2) {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$3(sheetState, null), 3, (Object) null);
        } else {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$2(sheetState, null), 3, (Object) null);
        }
        return Unit.INSTANCE;
    }

    public static boolean c(CoroutineScope coroutineScope, SheetState sheetState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$2$1$1$3$1(sheetState, null), 3, (Object) null);
        return true;
    }

    public static boolean d(CoroutineScope coroutineScope, SheetState sheetState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$2$1$1$1$1(sheetState, null), 3, (Object) null);
        return true;
    }

    public static boolean e(CoroutineScope coroutineScope, SheetState sheetState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$2$1$1$2$1(sheetState, null), 3, (Object) null);
        return true;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511691176, i, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:336)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean zChanged = composer.changed(this.$state) | composer.changedInstance(this.$scope);
        final SheetState sheetState = this.$state;
        final CoroutineScope coroutineScope = this.$scope;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: androidx.compose.material3.l
                public final Object invoke() {
                    return BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.b(sheetState, coroutineScope);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifier = ClickableKt.clickable-XHw0xAI$default(companion, false, (String) null, (Role) null, (Function0) objRememberedValue, 7, (Object) null);
        boolean zChanged2 = composer.changed(this.$state) | composer.changed(this.$sheetSwipeEnabled) | composer.changed(this.$expandActionLabel) | composer.changedInstance(this.$scope) | composer.changed(this.$partialExpandActionLabel) | composer.changed(this.$dismissActionLabel);
        final SheetState sheetState2 = this.$state;
        final boolean z = this.$sheetSwipeEnabled;
        final String str = this.$expandActionLabel;
        final String str2 = this.$partialExpandActionLabel;
        final String str3 = this.$dismissActionLabel;
        final CoroutineScope coroutineScope2 = this.$scope;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Function1 function1 = new Function1() { // from class: androidx.compose.material3.m
                public final Object invoke(Object obj) {
                    return BottomSheetScaffoldKt$StandardBottomSheet$3$1$1.a(sheetState2, z, str, str2, str3, coroutineScope2, (SemanticsPropertyReceiver) obj);
                }
            };
            composer.updateRememberedValue(function1);
            objRememberedValue2 = function1;
        }
        Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) objRememberedValue2);
        Function2<Composer, Integer, Unit> function2 = this.$dragHandle;
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
        function2.invoke(composer, 0);
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
