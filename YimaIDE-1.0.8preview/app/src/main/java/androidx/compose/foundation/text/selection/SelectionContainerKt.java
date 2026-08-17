package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.internal.ClipboardUtils_androidKt;
import androidx.compose.foundation.text.ClipboardEventsHandler_jvmKt;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.LongPressTextDragObserverKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionContainerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a \u0010\b\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\t\u001aJ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\f\u0010\n\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002"}, d2 = {"SelectionContainer", "", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DisableSelection", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "selection", "Landroidx/compose/foundation/text/selection/Selection;", "onSelectionChange", "Lkotlin/Function1;", "children", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/Selection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionContainerKt {
    public static final void DisableSelection(final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1162635549);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162635549, i2, -1, "androidx.compose.foundation.text.selection.DisableSelection (SelectionContainer.kt:73)");
            }
            CompositionLocalKt.CompositionLocalProvider(SelectionRegistrarKt.getLocalSelectionRegistrar().provides((Object) null), function2, composerStartRestartGroup, ((i2 << 3) & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: k3d
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.k(function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void SelectionContainer(Modifier modifier, final Selection selection, final Function1<? super Selection, Unit> function1, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-917932944);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(selection) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            modifier3 = i4 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-917932944, i3, -1, "androidx.compose.foundation.text.selection.SelectionContainer (SelectionContainer.kt:93)");
            }
            Object[] objArr = new Object[0];
            Saver<SelectionRegistrarImpl, Long> saver = SelectionRegistrarImpl.INSTANCE.getSaver();
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: o3d
                    public final Object invoke() {
                        return SelectionContainerKt.SelectionContainer$lambda$5$0();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final SelectionRegistrarImpl selectionRegistrarImpl = (SelectionRegistrarImpl) RememberSaveableKt.rememberSaveable(objArr, saver, (Function0) objRememberedValue, composerStartRestartGroup, 384);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new SelectionManager(selectionRegistrarImpl);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final SelectionManager selectionManager = (SelectionManager) objRememberedValue2;
            final Clipboard clipboard = (Clipboard) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalClipboard());
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue3;
            selectionManager.setHapticFeedBack((HapticFeedback) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalHapticFeedback()));
            boolean zChanged = composerStartRestartGroup.changed(coroutineScope) | composerStartRestartGroup.changed(clipboard);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = ClipboardUtils_androidKt.isWriteSupported(clipboard) ? new Function1() { // from class: p3d
                    public final Object invoke(Object obj) {
                        return SelectionContainerKt.SelectionContainer$lambda$7$0(coroutineScope, clipboard, (AnnotatedString) obj);
                    }
                } : null;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            selectionManager.setOnCopyHandler((Function1) objRememberedValue4);
            selectionManager.setTextToolbar((TextToolbar) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalTextToolbar()));
            selectionManager.setOnSelectionChange(function1);
            selectionManager.setSelection(selection);
            if (ComposeFoundationFlags.isSmartSelectionEnabled) {
                composerStartRestartGroup.startReplaceGroup(-82280708);
                selectionManager.setPlatformSelectionBehaviors$foundation(PlatformSelectionBehaviors_androidKt.rememberPlatformSelectionBehaviors(SelectedTextType.StaticText, null, composerStartRestartGroup, 54));
                selectionManager.setCoroutineScope$foundation(coroutineScope);
            } else {
                composerStartRestartGroup.startReplaceGroup(-86967598);
            }
            composerStartRestartGroup.endReplaceGroup();
            new Function0() { // from class: q3d
                public final Object invoke() {
                    return SelectionContainerKt.f(selectionManager);
                }
            };
            selectionManager.isNonEmptySelection$foundation();
            ClipboardEventsHandler_jvmKt.AnonymousClass1 anonymousClass1 = ClipboardEventsHandler_jvmKt.AnonymousClass1.INSTANCE;
            ClipboardEventsHandler_jvmKt.AnonymousClass3 anonymousClass3 = ClipboardEventsHandler_jvmKt.AnonymousClass3.INSTANCE;
            SimpleLayoutKt.SimpleLayout(modifier3.then(selectionManager.getModifier()), ComposableLambdaKt.rememberComposableLambda(-1799563674, true, new Function2() { // from class: r3d
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.l(selectionManager, selectionRegistrarImpl, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 48, 0);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(selectionManager);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: s3d
                    public final Object invoke(Object obj) {
                        return SelectionContainerKt.SelectionContainer$lambda$10$0(selectionManager, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.DisposableEffect(selectionManager, (Function1) objRememberedValue5, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t3d
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.j(modifier4, selection, function1, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Selection SelectionContainer$lambda$1(MutableState<Selection> mutableState) {
        return (Selection) mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult SelectionContainer$lambda$10$0(final SelectionManager selectionManager, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$lambda$10$0$$inlined$onDispose$1
            public void dispose() {
                selectionManager.onRelease();
                selectionManager.setHasFocus(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$3$0(MutableState mutableState, Selection selection) {
        mutableState.setValue(selection);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SelectionRegistrarImpl SelectionContainer$lambda$5$0() {
        return new SelectionRegistrarImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$7$0(CoroutineScope coroutineScope, Clipboard clipboard, AnnotatedString annotatedString) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new SelectionContainerKt$SelectionContainer$3$1$1(clipboard, annotatedString, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$9$0(SelectionRegistrarImpl selectionRegistrarImpl, final Function2 function2, final SelectionManager selectionManager, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-284825865, i, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous> (SelectionContainer.kt:136)");
            }
            CompositionLocalKt.CompositionLocalProvider(SelectionRegistrarKt.getLocalSelectionRegistrar().provides(selectionRegistrarImpl), ComposableLambdaKt.rememberComposableLambda(610483127, true, new Function2() { // from class: n3d
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.SelectionContainer$lambda$9$0$0(function2, selectionManager, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$9$0$0(Function2 function2, final SelectionManager selectionManager, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(610483127, i, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous>.<anonymous> (SelectionContainer.kt:137)");
            }
            function2.invoke(composer, 0);
            if (selectionManager.isInTouchMode() && selectionManager.getHasFocus() && !selectionManager.isTriviallyCollapsedSelection$foundation()) {
                composer.startReplaceGroup(-1736224054);
                Selection selection = selectionManager.getSelection();
                if (selection == null) {
                    composer.startReplaceGroup(2011629175);
                } else {
                    composer.startReplaceGroup(2011629176);
                    composer.startReplaceGroup(-1736222526);
                    List listListOf = CollectionsKt.listOf(new Boolean[]{Boolean.TRUE, Boolean.FALSE});
                    int size = listListOf.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        boolean zBooleanValue = ((Boolean) listListOf.get(i2)).booleanValue();
                        boolean zChanged = composer.changed(zBooleanValue);
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                            objRememberedValue = selectionManager.handleDragObserver(zBooleanValue);
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        final TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
                        boolean zChanged2 = composer.changed(zBooleanValue);
                        Object objRememberedValue2 = composer.rememberedValue();
                        if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = zBooleanValue ? new Function0() { // from class: l3d
                                public final Object invoke() {
                                    return SelectionContainerKt.SelectionContainer$lambda$9$0$0$0$0$1$0(selectionManager);
                                }
                            } : new Function0() { // from class: m3d
                                public final Object invoke() {
                                    return SelectionContainerKt.SelectionContainer$lambda$9$0$0$0$0$1$1(selectionManager);
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue2);
                        }
                        Function0 function0 = (Function0) objRememberedValue2;
                        ResolvedTextDirection direction = zBooleanValue ? selection.getStart().getDirection() : selection.getEnd().getDirection();
                        float startHandleLineHeight = zBooleanValue ? selectionManager.getStartHandleLineHeight() : selectionManager.getEndHandleLineHeight();
                        ResolvedTextDirection resolvedTextDirection = direction;
                        SelectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0 selectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0 = new SelectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0(function0);
                        boolean handlesCrossed = selection.getHandlesCrossed();
                        Modifier.Companion companion = Modifier.Companion;
                        boolean zChangedInstance = composer.changedInstance(textDragObserver);
                        Object objRememberedValue3 = composer.rememberedValue();
                        if (zChangedInstance || objRememberedValue3 == Composer.Companion.getEmpty()) {
                            objRememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$5$1$1$1$1$1$1
                                public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                                    Object objDetectDownAndDragGesturesWithObserver = LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(pointerInputScope, textDragObserver, continuation);
                                    return objDetectDownAndDragGesturesWithObserver == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDownAndDragGesturesWithObserver : Unit.INSTANCE;
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue3);
                        }
                        AndroidSelectionHandles_androidKt.m1735SelectionHandlewLIcFTc(selectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0, zBooleanValue, resolvedTextDirection, handlesCrossed, 0L, startHandleLineHeight, SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (PointerInputEventHandler) objRememberedValue3), composer, 0, 16);
                    }
                    composer.endReplaceGroup();
                }
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(2005806539);
            }
            composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset SelectionContainer$lambda$9$0$0$0$0$1$0(SelectionManager selectionManager) {
        Offset offsetM1784getStartHandlePosition_m7T9E = selectionManager.m1784getStartHandlePosition_m7T9E();
        return Offset.box-impl(offsetM1784getStartHandlePosition_m7T9E != null ? offsetM1784getStartHandlePosition_m7T9E.unbox-impl() : Offset.Companion.getUnspecified-F1C5BW0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset SelectionContainer$lambda$9$0$0$0$0$1$1(SelectionManager selectionManager) {
        Offset offsetM1783getEndHandlePosition_m7T9E = selectionManager.m1783getEndHandlePosition_m7T9E();
        return Offset.box-impl(offsetM1783getEndHandlePosition_m7T9E != null ? offsetM1783getEndHandlePosition_m7T9E.unbox-impl() : Offset.Companion.getUnspecified-F1C5BW0());
    }

    public static Unit b(Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        SelectionContainer(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static AnnotatedString f(SelectionManager selectionManager) {
        return selectionManager.getSelectedText$foundation();
    }

    public static Unit j(Modifier modifier, Selection selection, Function1 function1, Function2 function2, int i, int i2, Composer composer, int i3) {
        SelectionContainer(modifier, selection, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit k(Function2 function2, int i, Composer composer, int i2) {
        DisableSelection(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit l(final SelectionManager selectionManager, final SelectionRegistrarImpl selectionRegistrarImpl, final Function2 function2, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1799563674, i, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous> (SelectionContainer.kt:135)");
            }
            ContextMenu_androidKt.ContextMenuArea(selectionManager, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-284825865, true, new Function2() { // from class: w3d
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.SelectionContainer$lambda$9$0(selectionRegistrarImpl, function2, selectionManager, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void SelectionContainer(final Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1949207773);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1949207773, i3, -1, "androidx.compose.foundation.text.selection.SelectionContainer (SelectionContainer.kt:56)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            int i5 = i3;
            Selection selectionSelectionContainer$lambda$1 = SelectionContainer$lambda$1(mutableState);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: u3d
                    public final Object invoke(Object obj) {
                        return SelectionContainerKt.SelectionContainer$lambda$3$0(mutableState, (Selection) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            int i6 = (i5 & 14) | 384 | ((i5 << 6) & 7168);
            Modifier modifier2 = modifier;
            function3 = function2;
            SelectionContainer(modifier2, selectionSelectionContainer$lambda$1, (Function1) objRememberedValue2, function3, composerStartRestartGroup, i6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        } else {
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: v3d
                public final Object invoke(Object obj, Object obj2) {
                    return SelectionContainerKt.b(modifier, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
