package androidx.compose.foundation.text;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.contextmenu.ContextMenuAreaKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuStateKt;
import androidx.compose.foundation.text.CommonContextMenuAreaKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.internal.PlatformDefaultTextContextMenuProviders_androidKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGesturesModifierKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt;
import androidx.compose.foundation.text.selection.SelectionManager;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u0007\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\f\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\r2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000e\u001a5\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u000e\b\u0004\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\b\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\tH\u0080@¢\u0006\u0002\u0010\u0018\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\u0003H\u0080@¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"CommonContextMenuArea", "", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "selectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "enabled", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/selection/SelectionManager;", "(Landroidx/compose/foundation/text/selection/SelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextItem", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "label", "Landroidx/compose/foundation/text/TextContextMenuItems;", "operation", "getContextMenuItemsAvailability", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CommonContextMenuAreaKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt", f = "CommonContextMenuArea.kt", i = {0}, l = {200}, m = "getContextMenuItemsAvailability", n = {"$this$getContextMenuItemsAvailability"}, s = {"L$0"}, v = 1)
    public static final class C01781 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01781(Continuation<? super C01781> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommonContextMenuAreaKt.getContextMenuItemsAvailability((TextFieldSelectionState) null, (Continuation<? super MenuItemsAvailability>) this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$getContextMenuItemsAvailability$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt", f = "CommonContextMenuArea.kt", i = {0}, l = {212}, m = "getContextMenuItemsAvailability", n = {"$this$getContextMenuItemsAvailability"}, s = {"L$0"}, v = 1)
    public static final class C01792 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01792(Continuation<? super C01792> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommonContextMenuAreaKt.getContextMenuItemsAvailability((TextFieldSelectionManager) null, (Continuation<? super MenuItemsAvailability>) this);
        }
    }

    public static final void CommonContextMenuArea(final TextFieldSelectionState textFieldSelectionState, boolean z, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final boolean z2;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        Modifier modifierShowTextContextMenuOnSecondaryClick;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1442752422);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(textFieldSelectionState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1442752422, i2, -1, "androidx.compose.foundation.text.CommonContextMenuArea (CommonContextMenuArea.kt:75)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                composerStartRestartGroup.startReplaceGroup(-1299459355);
                if (z) {
                    composerStartRestartGroup.startReplaceGroup(-1299415211);
                    Modifier.Companion companion = Modifier.Companion;
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(textFieldSelectionState);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1(textFieldSelectionState, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    modifierShowTextContextMenuOnSecondaryClick = TextContextMenuGesturesModifierKt.showTextContextMenuOnSecondaryClick(companion, (Function2) objRememberedValue);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1298836224);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierShowTextContextMenuOnSecondaryClick = Modifier.Companion;
                }
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(modifierShowTextContextMenuOnSecondaryClick, function2, composerStartRestartGroup, (i2 >> 3) & 112, 0);
                composerStartRestartGroup.endReplaceGroup();
                z2 = z;
                function3 = function2;
            } else {
                composerStartRestartGroup.startReplaceGroup(-1298667367);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                Composer.Companion companion2 = Composer.Companion;
                if (objRememberedValue2 == companion2.getEmpty()) {
                    objRememberedValue2 = new ContextMenuState(null, 1, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final ContextMenuState contextMenuState = (ContextMenuState) objRememberedValue2;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == companion2.getEmpty()) {
                    objRememberedValue3 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue3;
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == companion2.getEmpty()) {
                    objRememberedValue4 = SnapshotStateKt.mutableStateOf$default(MenuItemsAvailability.m1382boximpl(MenuItemsAvailability.INSTANCE.m1395getNoneJKCFgKw()), (SnapshotMutationPolicy) null, 2, (Object) null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                final MutableState mutableState = (MutableState) objRememberedValue4;
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope);
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2 || objRememberedValue5 == companion2.getEmpty()) {
                    objRememberedValue5 = new Function2() { // from class: u72
                        public final Object invoke(Object obj, Object obj2) {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$8$0(coroutineScope, (TextFieldSelectionState) obj, (TextContextMenuItems) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                Function1<ContextMenuScope, Unit> function1ContextMenuBuilder = TextFieldSelectionStateKt.contextMenuBuilder(textFieldSelectionState, contextMenuState, mutableState, (Function2) objRememberedValue5);
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == companion2.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: v72
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$9$0(contextMenuState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                Function0 function0 = (Function0) objRememberedValue6;
                boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(textFieldSelectionState);
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3 || objRememberedValue7 == companion2.getEmpty()) {
                    objRememberedValue7 = new Function0() { // from class: w72
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$10$0(coroutineScope, mutableState, textFieldSelectionState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                z2 = z;
                function3 = function2;
                ContextMenuAreaKt.ContextMenuArea(contextMenuState, function0, function1ContextMenuBuilder, null, z2, (Function0) objRememberedValue7, function3, composerStartRestartGroup, ((i2 << 9) & 57344) | 54 | ((i2 << 12) & 3670016), 8);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            z2 = z;
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: x72
                public final Object invoke(Object obj, Object obj2) {
                    return CommonContextMenuAreaKt.d(textFieldSelectionState, z2, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$10$0(CoroutineScope coroutineScope, MutableState mutableState, TextFieldSelectionState textFieldSelectionState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1(mutableState, textFieldSelectionState, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$13$0(ContextMenuState contextMenuState) {
        ContextMenuStateKt.close(contextMenuState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$2$0(ContextMenuState contextMenuState) {
        ContextMenuStateKt.close(contextMenuState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$3$0(CoroutineScope coroutineScope, MutableState mutableState, TextFieldSelectionManager textFieldSelectionManager) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new CommonContextMenuAreaKt$CommonContextMenuArea$2$1$1(mutableState, textFieldSelectionManager, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$8$0(CoroutineScope coroutineScope, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1(textContextMenuItems, textFieldSelectionState, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CommonContextMenuArea$lambda$9$0(ContextMenuState contextMenuState) {
        ContextMenuStateKt.close(contextMenuState);
        return Unit.INSTANCE;
    }

    public static final void TextItem(ContextMenuScope contextMenuScope, ContextMenuState contextMenuState, TextContextMenuItems textContextMenuItems, boolean z, Function0<Unit> function0) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new AnonymousClass1(textContextMenuItems), null, false, null, new AnonymousClass2(function0, contextMenuState), 14, null);
        }
    }

    public static Unit d(TextFieldSelectionState textFieldSelectionState, boolean z, Function2 function2, int i, Composer composer, int i2) {
        CommonContextMenuArea(textFieldSelectionState, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit e(TextFieldSelectionManager textFieldSelectionManager, Function2 function2, int i, Composer composer, int i2) {
        CommonContextMenuArea(textFieldSelectionManager, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit g(SelectionManager selectionManager, Function2 function2, int i, Composer composer, int i2) {
        CommonContextMenuArea(selectionManager, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object getContextMenuItemsAvailability(TextFieldSelectionState textFieldSelectionState, Continuation<? super MenuItemsAvailability> continuation) {
        C01781 c01781;
        if (continuation instanceof C01781) {
            c01781 = (C01781) continuation;
            int i = c01781.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01781.label = i - Integer.MIN_VALUE;
            } else {
                c01781 = new C01781(continuation);
            }
        } else {
            c01781 = new C01781(continuation);
        }
        Object obj = c01781.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01781.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            c01781.L$0 = textFieldSelectionState;
            c01781.label = 1;
            if (textFieldSelectionState.updateClipboardEntry(c01781) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            textFieldSelectionState = (TextFieldSelectionState) c01781.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return MenuItemsAvailability.m1382boximpl(MenuItemsAvailability.m1384constructorimpl(textFieldSelectionState.canShowCopyMenuItem(), textFieldSelectionState.canShowPasteMenuItem(), textFieldSelectionState.canShowCutMenuItem(), textFieldSelectionState.canShowSelectAllMenuItem(), textFieldSelectionState.canShowAutofillMenuItem()));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$TextItem$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass2 implements Function0<Unit> {
        final /* synthetic */ Function0<Unit> $operation;
        final /* synthetic */ ContextMenuState $state;

        public AnonymousClass2(Function0<Unit> function0, ContextMenuState contextMenuState) {
            this.$operation = function0;
            this.$state = contextMenuState;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1321invoke() {
            this.$operation.invoke();
            ContextMenuStateKt.close(this.$state);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m1321invoke();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CommonContextMenuAreaKt$TextItem$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function2<Composer, Integer, String> {
        final /* synthetic */ TextContextMenuItems $label;

        public AnonymousClass1(TextContextMenuItems textContextMenuItems) {
            this.$label = textContextMenuItems;
        }

        public final String invoke(Composer composer, int i) {
            composer.startReplaceGroup(-35972707);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-35972707, i, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (CommonContextMenuArea.kt:190)");
            }
            String strResolvedString = this.$label.resolvedString(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return strResolvedString;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((Composer) obj, ((Number) obj2).intValue());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object getContextMenuItemsAvailability(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super MenuItemsAvailability> continuation) {
        C01792 c01792;
        if (continuation instanceof C01792) {
            c01792 = (C01792) continuation;
            int i = c01792.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01792.label = i - Integer.MIN_VALUE;
            } else {
                c01792 = new C01792(continuation);
            }
        } else {
            c01792 = new C01792(continuation);
        }
        Object obj = c01792.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01792.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            c01792.L$0 = textFieldSelectionManager;
            c01792.label = 1;
            if (textFieldSelectionManager.updateClipboardEntry$foundation(c01792) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            textFieldSelectionManager = (TextFieldSelectionManager) c01792.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return MenuItemsAvailability.m1382boximpl(MenuItemsAvailability.m1384constructorimpl(textFieldSelectionManager.canShowCopyMenuItem$foundation(), textFieldSelectionManager.canShowPasteMenuItem$foundation(), textFieldSelectionManager.canShowCutMenuItem$foundation(), textFieldSelectionManager.canShowSelectAllMenuItem$foundation(), textFieldSelectionManager.canShowAutofillMenuItem$foundation()));
    }

    public static final void CommonContextMenuArea(final TextFieldSelectionManager textFieldSelectionManager, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1533506138);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1533506138, i2, -1, "androidx.compose.foundation.text.CommonContextMenuArea (CommonContextMenuArea.kt:46)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                composerStartRestartGroup.startReplaceGroup(-885604480);
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(textFieldSelectionManager.getContextMenuAreaModifier(), function2, composerStartRestartGroup, i2 & 112, 0);
                composerStartRestartGroup.endReplaceGroup();
                function3 = function2;
            } else {
                composerStartRestartGroup.startReplaceGroup(-885475365);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                Composer.Companion companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new ContextMenuState(null, 1, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final ContextMenuState contextMenuState = (ContextMenuState) objRememberedValue;
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = SnapshotStateKt.mutableStateOf$default(MenuItemsAvailability.m1382boximpl(MenuItemsAvailability.INSTANCE.m1395getNoneJKCFgKw()), (SnapshotMutationPolicy) null, 2, (Object) null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MutableState mutableState = (MutableState) objRememberedValue3;
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: p72
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$2$0(contextMenuState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function0 function0 = (Function0) objRememberedValue4;
                Function1<ContextMenuScope, Unit> function1ContextMenuBuilder = TextFieldSelectionManagerKt.contextMenuBuilder(textFieldSelectionManager, contextMenuState, mutableState);
                boolean enabled = textFieldSelectionManager.getEnabled();
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(textFieldSelectionManager);
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = new Function0() { // from class: q72
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$3$0(coroutineScope, mutableState, textFieldSelectionManager);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                function3 = function2;
                ContextMenuAreaKt.ContextMenuArea(contextMenuState, function0, function1ContextMenuBuilder, null, enabled, (Function0) objRememberedValue5, function3, composerStartRestartGroup, ((i2 << 15) & 3670016) | 54, 8);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r72
                public final Object invoke(Object obj, Object obj2) {
                    return CommonContextMenuAreaKt.e(textFieldSelectionManager, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void CommonContextMenuArea(final SelectionManager selectionManager, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-614342087);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(selectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-614342087, i2, -1, "androidx.compose.foundation.text.CommonContextMenuArea (CommonContextMenuArea.kt:131)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                composerStartRestartGroup.startReplaceGroup(-1009319487);
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(selectionManager.getContextMenuAreaModifier(), function2, composerStartRestartGroup, i2 & 112, 0);
                composerStartRestartGroup.endReplaceGroup();
                function3 = function2;
            } else {
                composerStartRestartGroup.startReplaceGroup(-1009204043);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                Composer.Companion companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new ContextMenuState(null, 1, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final ContextMenuState contextMenuState = (ContextMenuState) objRememberedValue;
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: s72
                        public final Object invoke() {
                            return CommonContextMenuAreaKt.CommonContextMenuArea$lambda$13$0(contextMenuState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function3 = function2;
                ContextMenuAreaKt.ContextMenuArea(contextMenuState, (Function0) objRememberedValue2, SelectionManagerKt.contextMenuBuilder(selectionManager, contextMenuState), null, false, null, function3, composerStartRestartGroup, ((i2 << 15) & 3670016) | 54, 56);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t72
                public final Object invoke(Object obj, Object obj2) {
                    return CommonContextMenuAreaKt.g(selectionManager, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
