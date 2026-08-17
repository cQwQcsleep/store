package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.contextmenu.ContextMenuAreaKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.IntOffsetKt;
import com.intellij.util.io.IOUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001as\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010\u0011\u001aF\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\tH\u0001¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"ContextMenuArea", "", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "onDismiss", "Lkotlin/Function0;", "contextMenuBuilderBlock", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "onOpenGesture", "content", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/contextmenu/ContextMenuState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ContextMenu", "(Landroidx/compose/foundation/contextmenu/ContextMenuState;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuAreaKt {
    public static final void ContextMenu(final ContextMenuState contextMenuState, final Function0<Unit> function0, Modifier modifier, final Function1<? super ContextMenuScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        Composer composer2;
        final Modifier modifier2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function2 function2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-195055274);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(contextMenuState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            modifier2 = modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-195055274, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenu (ContextMenuArea.kt:73)");
            }
            ContextMenuState.Status status = contextMenuState.getStatus();
            if (status instanceof ContextMenuState.Status.Open) {
                ContextMenuState.Status.Open open = (ContextMenuState.Status.Open) status;
                boolean zChanged = composerStartRestartGroup.changed(open);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                    ContextMenuPopupPositionProvider contextMenuPopupPositionProvider = new ContextMenuPopupPositionProvider(IntOffsetKt.round-k-4lQ0M(open.getOffset()), (Function2) null, 2, (DefaultConstructorMarker) null);
                    composerStartRestartGroup.updateRememberedValue(contextMenuPopupPositionProvider);
                    objRememberedValue = contextMenuPopupPositionProvider;
                }
                composer2 = composerStartRestartGroup;
                ContextMenuUiKt.ContextMenuPopup((ContextMenuPopupPositionProvider) objRememberedValue, function0, modifier2, function1, composer2, i3 & 8176, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                } else {
                    function2 = new Function2() { // from class: yu2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContextMenuAreaKt.d(contextMenuState, function0, modifier2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    };
                }
            }
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
        composer2 = composerStartRestartGroup;
        composer2.skipToGroupEnd();
        modifier2 = modifier;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            function2 = new Function2() { // from class: zu2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuAreaKt.b(contextMenuState, function0, modifier2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(function2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0160  */
    /* JADX WARN: Code duplicated, block: B:105:0x016c  */
    /* JADX WARN: Code duplicated, block: B:106:0x0170  */
    /* JADX WARN: Code duplicated, block: B:109:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:111:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:114:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006d  */
    /* JADX WARN: Code duplicated, block: B:43:0x0071  */
    /* JADX WARN: Code duplicated, block: B:45:0x0079  */
    /* JADX WARN: Code duplicated, block: B:46:0x007c  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x008c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x0097  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:77:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:79:0x00da  */
    /* JADX WARN: Code duplicated, block: B:83:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:87:0x0103  */
    /* JADX WARN: Code duplicated, block: B:88:0x0105  */
    /* JADX WARN: Code duplicated, block: B:91:0x010b  */
    /* JADX WARN: Code duplicated, block: B:92:0x010d  */
    /* JADX WARN: Code duplicated, block: B:95:0x0115  */
    /* JADX WARN: Code duplicated, block: B:97:0x011d  */
    /* JADX WARN: Code duplicated, block: B:99:0x012f  */
    public static final void ContextMenuArea(final ContextMenuState contextMenuState, final Function0<Unit> function0, final Function1<? super ContextMenuScope, Unit> function1, Modifier modifier, boolean z, Function0<Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function4;
        Modifier modifier2;
        int i4;
        boolean z2;
        int i5;
        int i6;
        final Function0<Unit> function5;
        int i7;
        boolean z3;
        final boolean z4;
        final Function0<Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifierContextMenuGestures;
        Function0 constructor;
        boolean z5;
        boolean z6;
        boolean z7;
        Object objRememberedValue;
        Object objRememberedValue2;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(1195420540);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(contextMenuState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            function4 = function0;
            i3 |= composerStartRestartGroup.changedInstance(function4) ? 32 : 16;
        } else {
            function4 = function0;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        int i9 = i2 & 8;
        if (i9 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        function5 = function2;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = IOUtil.MiB;
                        } else {
                            i8 = 524288;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 599187) != 599186) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        if (i9 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                                objRememberedValue2 = new Function0() { // from class: vu2
                                    public final Object invoke() {
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function5 = (Function0) objRememberedValue2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                        }
                        if (z2) {
                            composerStartRestartGroup.startReplaceGroup(-1095188022);
                            if ((458752 & i3) == 131072) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if ((i3 & 14) == 4) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            z7 = z5 | z6;
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z7 || objRememberedValue == Composer.Companion.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: wu2
                                    public final Object invoke(Object obj) {
                                        return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1095031162);
                            composerStartRestartGroup.endReplaceGroup();
                            modifierContextMenuGestures = modifier2;
                        }
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                        ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                        constructor = companion.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                        ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                    }
                    z4 = z2;
                    function6 = function5;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier3 = modifier2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                            public final Object invoke(Object obj, Object obj2) {
                                return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier3, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                function5 = function2;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = IOUtil.MiB;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((i3 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i9 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function0() { // from class: vu2
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function5 = (Function0) objRememberedValue2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                    }
                    if (z2) {
                        composerStartRestartGroup.startReplaceGroup(-1095188022);
                        if ((458752 & i3) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if ((i3 & 14) == 4) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        z7 = z5 | z6;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z7) {
                            objRememberedValue = new Function1() { // from class: wu2
                                public final Object invoke(Object obj) {
                                    return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: wu2
                                public final Object invoke(Object obj) {
                                    return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1095031162);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierContextMenuGestures = modifier2;
                    }
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                    constructor = companion2.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                    Updater.set-impl(composer3, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer3, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer3, companion2.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer3, modifierMaterializeModifier2, companion2.getSetModifier());
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                    ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                z4 = z2;
                function6 = function5;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier4, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z2 = z;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function2;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = IOUtil.MiB;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((i3 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i9 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function0() { // from class: vu2
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function5 = (Function0) objRememberedValue2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                    }
                    if (z2) {
                        composerStartRestartGroup.startReplaceGroup(-1095188022);
                        if ((458752 & i3) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if ((i3 & 14) == 4) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        z7 = z5 | z6;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z7) {
                            objRememberedValue = new Function1() { // from class: wu2
                                public final Object invoke(Object obj) {
                                    return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: wu2
                                public final Object invoke(Object obj) {
                                    return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1095031162);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierContextMenuGestures = modifier2;
                    }
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                    ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                    constructor = companion3.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer4, measurePolicyMaybeCachedBoxMeasurePolicy3, companion3.getSetMeasurePolicy());
                    Updater.set-impl(composer4, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer4, Integer.valueOf(iHashCode3), companion3.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer4, companion3.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer4, modifierMaterializeModifier3, companion3.getSetModifier());
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                    ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                z4 = z2;
                function6 = function5;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier5, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            function5 = function2;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = IOUtil.MiB;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((i3 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i9 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if (i6 != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: vu2
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function5 = (Function0) objRememberedValue2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                }
                if (z2) {
                    composerStartRestartGroup.startReplaceGroup(-1095188022);
                    if ((458752 & i3) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if ((i3 & 14) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    z7 = z5 | z6;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z7) {
                        objRememberedValue = new Function1() { // from class: wu2
                            public final Object invoke(Object obj) {
                                return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: wu2
                            public final Object invoke(Object obj) {
                                return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1095031162);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierContextMenuGestures = modifier2;
                }
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
                constructor = companion4.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer5, measurePolicyMaybeCachedBoxMeasurePolicy4, companion4.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
                Updater.init-impl(composer5, Integer.valueOf(iHashCode4), companion4.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer5, companion4.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer5, modifierMaterializeModifier4, companion4.getSetModifier());
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            z4 = z2;
            function6 = function5;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier6, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function2;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = IOUtil.MiB;
                    } else {
                        i8 = 524288;
                    }
                    i3 |= i8;
                }
                if ((i3 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    if (i9 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function0() { // from class: vu2
                                public final Object invoke() {
                                    return Unit.INSTANCE;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function5 = (Function0) objRememberedValue2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                    }
                    if (z2) {
                        composerStartRestartGroup.startReplaceGroup(-1095188022);
                        if ((458752 & i3) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if ((i3 & 14) == 4) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        z7 = z5 | z6;
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z7) {
                            objRememberedValue = new Function1() { // from class: wu2
                                public final Object invoke(Object obj) {
                                    return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new Function1() { // from class: wu2
                                public final Object invoke(Object obj) {
                                    return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1095031162);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierContextMenuGestures = modifier2;
                    }
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                    ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
                    constructor = companion5.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer6, measurePolicyMaybeCachedBoxMeasurePolicy5, companion5.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode5), companion5.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion5.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier5, companion5.getSetModifier());
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                    ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                z4 = z2;
                function6 = function5;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier7 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier7, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            function5 = function2;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = IOUtil.MiB;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((i3 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i9 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if (i6 != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: vu2
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function5 = (Function0) objRememberedValue2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                }
                if (z2) {
                    composerStartRestartGroup.startReplaceGroup(-1095188022);
                    if ((458752 & i3) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if ((i3 & 14) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    z7 = z5 | z6;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z7) {
                        objRememberedValue = new Function1() { // from class: wu2
                            public final Object invoke(Object obj) {
                                return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: wu2
                            public final Object invoke(Object obj) {
                                return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1095031162);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierContextMenuGestures = modifier2;
                }
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                ComposeUiNode.Companion companion6 = ComposeUiNode.Companion;
                constructor = companion6.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer7, measurePolicyMaybeCachedBoxMeasurePolicy6, companion6.getSetMeasurePolicy());
                Updater.set-impl(composer7, currentCompositionLocalMap6, companion6.getSetResolvedCompositionLocals());
                Updater.init-impl(composer7, Integer.valueOf(iHashCode6), companion6.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer7, companion6.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer7, modifierMaterializeModifier6, companion6.getSetModifier());
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            z4 = z2;
            function6 = function5;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier8 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier8, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z2 = z;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                function5 = function2;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = IOUtil.MiB;
                } else {
                    i8 = 524288;
                }
                i3 |= i8;
            }
            if ((i3 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i9 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if (i6 != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: vu2
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function5 = (Function0) objRememberedValue2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
                }
                if (z2) {
                    composerStartRestartGroup.startReplaceGroup(-1095188022);
                    if ((458752 & i3) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if ((i3 & 14) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    z7 = z5 | z6;
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z7) {
                        objRememberedValue = new Function1() { // from class: wu2
                            public final Object invoke(Object obj) {
                                return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: wu2
                            public final Object invoke(Object obj) {
                                return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1095031162);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierContextMenuGestures = modifier2;
                }
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
                ComposeUiNode.Companion companion7 = ComposeUiNode.Companion;
                constructor = companion7.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer8, measurePolicyMaybeCachedBoxMeasurePolicy7, companion7.getSetMeasurePolicy());
                Updater.set-impl(composer8, currentCompositionLocalMap7, companion7.getSetResolvedCompositionLocals());
                Updater.init-impl(composer8, Integer.valueOf(iHashCode7), companion7.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer8, companion7.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer8, modifierMaterializeModifier7, companion7.getSetModifier());
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
                ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            z4 = z2;
            function6 = function5;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier9, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        function5 = function2;
        if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i8 = IOUtil.MiB;
            } else {
                i8 = 524288;
            }
            i3 |= i8;
        }
        if ((i3 & 599187) != 599186) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            if (i9 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (i4 != 0) {
                z2 = true;
            }
            if (i6 != 0) {
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: vu2
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function5 = (Function0) objRememberedValue2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1195420540, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuArea (ContextMenuArea.kt:46)");
            }
            if (z2) {
                composerStartRestartGroup.startReplaceGroup(-1095188022);
                if ((458752 & i3) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if ((i3 & 14) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                z7 = z5 | z6;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z7) {
                    objRememberedValue = new Function1() { // from class: wu2
                        public final Object invoke(Object obj) {
                            return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: wu2
                        public final Object invoke(Object obj) {
                            return ContextMenuAreaKt.ContextMenuArea$lambda$1$0(function5, contextMenuState, (Offset) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                modifierContextMenuGestures = ContextMenuGesturesKt.contextMenuGestures(modifier2, (Function1<? super Offset, Unit>) objRememberedValue);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1095031162);
                composerStartRestartGroup.endReplaceGroup();
                modifierContextMenuGestures = modifier2;
            }
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
            int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierContextMenuGestures);
            ComposeUiNode.Companion companion8 = ComposeUiNode.Companion;
            constructor = companion8.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer9, measurePolicyMaybeCachedBoxMeasurePolicy8, companion8.getSetMeasurePolicy());
            Updater.set-impl(composer9, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
            Updater.init-impl(composer9, Integer.valueOf(iHashCode8), companion8.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer9, companion8.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer9, modifierMaterializeModifier8, companion8.getSetModifier());
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            function3.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
            ContextMenu(contextMenuState, function4, null, function1, composerStartRestartGroup, (i3 & 126) | ((i3 << 3) & 7168), 4);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        z4 = z2;
        function6 = function5;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier10 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuAreaKt.e(contextMenuState, function0, function1, modifier10, z4, function6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$1$0(Function0 function0, ContextMenuState contextMenuState, Offset offset) {
        function0.invoke();
        contextMenuState.setStatus(new ContextMenuState.Status.Open(offset.unbox-impl(), null));
        return Unit.INSTANCE;
    }

    public static Unit b(ContextMenuState contextMenuState, Function0 function0, Modifier modifier, Function1 function1, int i, int i2, Composer composer, int i3) {
        ContextMenu(contextMenuState, function0, modifier, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(ContextMenuState contextMenuState, Function0 function0, Modifier modifier, Function1 function1, int i, int i2, Composer composer, int i3) {
        ContextMenu(contextMenuState, function0, modifier, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit e(ContextMenuState contextMenuState, Function0 function0, Function1 function1, Modifier modifier, boolean z, Function0 function2, Function2 function3, int i, int i2, Composer composer, int i3) {
        ContextMenuArea(contextMenuState, function0, function1, modifier, z, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }
}
