package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.UiApplier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.compose.LocalSavedStateRegistryOwnerKt;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\n\u001ay\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00052\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\r\u001a1\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00020\u0005H\u0003¢\u0006\u0002\u0010\u0011\u001a[\u0010\u0012\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002¢\u0006\u0004\b \u0010!\u001a\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00020#\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0010H\u0002\"\"\u0010$\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"AndroidView", "", "T", "Landroid/view/View;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "modifier", "Landroidx/compose/ui/Modifier;", "update", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "onReset", "onRelease", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "createAndroidViewNodeFactory", "Lkotlin/Function0;", "Landroidx/compose/ui/node/LayoutNode;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "updateViewHolderParams", "Landroidx/compose/runtime/Updater;", "compositeKeyHash", "", "density", "Landroidx/compose/ui/unit/Density;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "savedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "compositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "updateViewHolderParams-6NefGtU", "(Landroidx/compose/runtime/Composer;Landroidx/compose/ui/Modifier;ILandroidx/compose/ui/unit/Density;Landroidx/lifecycle/LifecycleOwner;Landroidx/savedstate/SavedStateRegistryOwner;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/runtime/CompositionLocalMap;)V", "requireViewFactoryHolder", "Landroidx/compose/ui/viewinterop/ViewFactoryHolder;", "NoOpUpdate", "Lkotlin/ExtensionFunctionType;", "getNoOpUpdate", "()Lkotlin/jvm/functions/Function1;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidView_androidKt {
    private static final Function1<View, Unit> NoOpUpdate = new Function1<View, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$NoOpUpdate$1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((View) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(View view) {
        }
    };

    /* JADX WARN: Code duplicated, block: B:23:0x003c  */
    /* JADX WARN: Code duplicated, block: B:25:0x0041  */
    /* JADX WARN: Code duplicated, block: B:27:0x0045  */
    /* JADX WARN: Code duplicated, block: B:29:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0050  */
    /* JADX WARN: Code duplicated, block: B:34:0x0057  */
    /* JADX WARN: Code duplicated, block: B:36:0x005c  */
    /* JADX WARN: Code duplicated, block: B:38:0x0060  */
    /* JADX WARN: Code duplicated, block: B:40:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0072  */
    /* JADX WARN: Code duplicated, block: B:47:0x0077  */
    /* JADX WARN: Code duplicated, block: B:49:0x007b  */
    /* JADX WARN: Code duplicated, block: B:51:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0086  */
    /* JADX WARN: Code duplicated, block: B:56:0x0090  */
    /* JADX WARN: Code duplicated, block: B:57:0x0092  */
    /* JADX WARN: Code duplicated, block: B:60:0x009b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x009d  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:74:0x0101  */
    /* JADX WARN: Code duplicated, block: B:76:0x0115  */
    /* JADX WARN: Code duplicated, block: B:79:0x0121  */
    /* JADX WARN: Code duplicated, block: B:80:0x0125  */
    /* JADX WARN: Code duplicated, block: B:82:0x0145  */
    /* JADX WARN: Code duplicated, block: B:84:0x0159  */
    /* JADX WARN: Code duplicated, block: B:87:0x0165  */
    /* JADX WARN: Code duplicated, block: B:88:0x0169  */
    /* JADX WARN: Code duplicated, block: B:92:0x0189  */
    /* JADX WARN: Code duplicated, block: B:94:0x018f  */
    /* JADX WARN: Code duplicated, block: B:97:0x019a  */
    /* JADX WARN: Code duplicated, block: B:99:? A[RETURN, SYNTHETIC] */
    public static final <T extends View> void AndroidView(final Function1<? super Context, ? extends T> function1, Modifier modifier, Function1<? super T, Unit> function2, Function1<? super T, Unit> function3, Function1<? super T, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        Function1<? super T, Unit> function5;
        int i5;
        int i6;
        Function1<? super T, Unit> function6;
        int i7;
        int i8;
        Function1<? super T, Unit> function7;
        int i9;
        boolean z;
        Modifier modifier3;
        final Function1<? super T, Unit> function8;
        final Function1<? super T, Unit> function9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int iHashCode;
        Modifier modifierMaterializeModifier;
        Density density;
        LayoutDirection layoutDirection;
        CompositionLocalMap currentCompositionLocalMap;
        LifecycleOwner lifecycleOwner;
        SavedStateRegistryOwner savedStateRegistryOwner;
        Function0<LayoutNode> function0CreateAndroidViewNodeFactory;
        Function0<LayoutNode> function0CreateAndroidViewNodeFactory2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-180024211);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function5 = function2;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        function6 = function3;
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 16;
                    if (i8 != 0) {
                        if ((i & 24576) == 0) {
                            function7 = function4;
                            if (composerStartRestartGroup.changedInstance(function7)) {
                                i9 = 16384;
                            } else {
                                i9 = 8192;
                            }
                            i3 |= i9;
                        }
                        if ((i3 & 9363) != 9362) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            if (i10 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = NoOpUpdate;
                            }
                            if (i8 != 0) {
                                function7 = NoOpUpdate;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                            }
                            iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                            modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                            density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                            layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                            currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                            savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                            if (function5 != null) {
                                composerStartRestartGroup.startReplaceGroup(1313917368);
                                function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                                if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composerStartRestartGroup.startReusableNode();
                                if (composerStartRestartGroup.getInserting()) {
                                    composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                                } else {
                                    composerStartRestartGroup.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                                m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                                Updater.m2396setimpl(composerM2388constructorimpl, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((LayoutNode) obj, (Function1) obj2);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function10) {
                                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function10);
                                    }
                                });
                                Updater.m2396setimpl(composerM2388constructorimpl, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((LayoutNode) obj, (Function1) obj2);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function10) {
                                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function10);
                                    }
                                });
                                Updater.m2396setimpl(composerM2388constructorimpl, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((LayoutNode) obj, (Function1) obj2);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function10) {
                                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function10);
                                    }
                                });
                                composerStartRestartGroup.endNode();
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1314774735);
                                function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                                if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composerStartRestartGroup.startNode();
                                if (composerStartRestartGroup.getInserting()) {
                                    composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                                } else {
                                    composerStartRestartGroup.useNode();
                                }
                                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                                m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl2, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                                Updater.m2396setimpl(composerM2388constructorimpl2, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((LayoutNode) obj, (Function1) obj2);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function10) {
                                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function10);
                                    }
                                });
                                Updater.m2396setimpl(composerM2388constructorimpl2, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((LayoutNode) obj, (Function1) obj2);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function10) {
                                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function10);
                                    }
                                });
                                composerStartRestartGroup.endNode();
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier3 = modifier2;
                        }
                        function8 = function5;
                        function9 = function7;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            final Modifier modifier4 = modifier3;
                            final Function1<? super T, Unit> function10 = function6;
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                public final void invoke(Composer composer2, int i11) {
                                    AndroidView_androidKt.AndroidView(function1, modifier4, function8, function10, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            });
                        }
                    }
                    i3 |= 24576;
                    function7 = function4;
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i10 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = NoOpUpdate;
                        }
                        if (i8 != 0) {
                            function7 = NoOpUpdate;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                        }
                        iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                        density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                        currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                        savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                        if (function5 != null) {
                            composerStartRestartGroup.startReplaceGroup(1313917368);
                            function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl3, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl3, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function11) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function11);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl3, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function11) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function11);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl3, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function11) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function11);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1314774735);
                            function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl4, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl4, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function11) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function11);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl4, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function11) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function11);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                    }
                    function8 = function5;
                    function9 = function7;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier5 = modifier3;
                        final Function1<? super T, Unit> function11 = function6;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i11) {
                                AndroidView_androidKt.AndroidView(function1, modifier5, function8, function11, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i3 |= 3072;
                function6 = function3;
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        function7 = function4;
                        if (composerStartRestartGroup.changedInstance(function7)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i10 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = NoOpUpdate;
                        }
                        if (i8 != 0) {
                            function7 = NoOpUpdate;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                        }
                        iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                        density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                        currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                        savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                        if (function5 != null) {
                            composerStartRestartGroup.startReplaceGroup(1313917368);
                            function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl5 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl5, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl5, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function12) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function12);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl5, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function12) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function12);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl5, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function12) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function12);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1314774735);
                            function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl6 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl6, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl6, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function12) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function12);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl6, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function12) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function12);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                    }
                    function8 = function5;
                    function9 = function7;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier6 = modifier3;
                        final Function1<? super T, Unit> function12 = function6;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i11) {
                                AndroidView_androidKt.AndroidView(function1, modifier6, function8, function12, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i3 |= 24576;
                function7 = function4;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = NoOpUpdate;
                    }
                    if (i8 != 0) {
                        function7 = NoOpUpdate;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                    }
                    iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                    savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                    if (function5 != null) {
                        composerStartRestartGroup.startReplaceGroup(1313917368);
                        function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl7 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl7, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl7, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function13) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function13);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl7, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function13) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function13);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl7, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function13) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function13);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1314774735);
                        function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl8 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl8, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl8, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function13) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function13);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl8, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function13) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function13);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                function8 = function5;
                function9 = function7;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier7 = modifier3;
                    final Function1<? super T, Unit> function13 = function6;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidView_androidKt.AndroidView(function1, modifier7, function8, function13, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function5 = function2;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        function7 = function4;
                        if (composerStartRestartGroup.changedInstance(function7)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i10 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = NoOpUpdate;
                        }
                        if (i8 != 0) {
                            function7 = NoOpUpdate;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                        }
                        iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                        density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                        currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                        savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                        if (function5 != null) {
                            composerStartRestartGroup.startReplaceGroup(1313917368);
                            function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl9 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl9, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl9, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function14) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function14);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl9, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function14) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function14);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl9, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function14) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function14);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1314774735);
                            function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl10 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl10, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl10, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function14) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function14);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl10, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function14) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function14);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                    }
                    function8 = function5;
                    function9 = function7;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier8 = modifier3;
                        final Function1<? super T, Unit> function14 = function6;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i11) {
                                AndroidView_androidKt.AndroidView(function1, modifier8, function8, function14, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i3 |= 24576;
                function7 = function4;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = NoOpUpdate;
                    }
                    if (i8 != 0) {
                        function7 = NoOpUpdate;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                    }
                    iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                    savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                    if (function5 != null) {
                        composerStartRestartGroup.startReplaceGroup(1313917368);
                        function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl11 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl11, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl11, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function15) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function15);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl11, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function15) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function15);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl11, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function15) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function15);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1314774735);
                        function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl12 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl12, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl12, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function15) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function15);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl12, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function15) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function15);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                function8 = function5;
                function9 = function7;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier9 = modifier3;
                    final Function1<? super T, Unit> function15 = function6;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidView_androidKt.AndroidView(function1, modifier9, function8, function15, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 3072;
            function6 = function3;
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    function7 = function4;
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = NoOpUpdate;
                    }
                    if (i8 != 0) {
                        function7 = NoOpUpdate;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                    }
                    iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                    savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                    if (function5 != null) {
                        composerStartRestartGroup.startReplaceGroup(1313917368);
                        function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl13 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl13, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl13, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function16) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function16);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl13, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function16) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function16);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl13, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function16) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function16);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1314774735);
                        function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl14 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl14, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl14, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function16) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function16);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl14, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function16) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function16);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                function8 = function5;
                function9 = function7;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier10 = modifier3;
                    final Function1<? super T, Unit> function16 = function6;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidView_androidKt.AndroidView(function1, modifier10, function8, function16, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 24576;
            function7 = function4;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if (i6 != 0) {
                    function6 = NoOpUpdate;
                }
                if (i8 != 0) {
                    function7 = NoOpUpdate;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                }
                iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                if (function5 != null) {
                    composerStartRestartGroup.startReplaceGroup(1313917368);
                    function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl15 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl15, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl15, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function17) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function17);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl15, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function17) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function17);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl15, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function17) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function17);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1314774735);
                    function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl16 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl16, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl16, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function17) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function17);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl16, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function17) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function17);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            function8 = function5;
            function9 = function7;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier11 = modifier3;
                final Function1<? super T, Unit> function17 = function6;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidView_androidKt.AndroidView(function1, modifier11, function8, function17, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function5 = function2;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 16;
                if (i8 != 0) {
                    if ((i & 24576) == 0) {
                        function7 = function4;
                        if (composerStartRestartGroup.changedInstance(function7)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i10 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = NoOpUpdate;
                        }
                        if (i8 != 0) {
                            function7 = NoOpUpdate;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                        }
                        iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                        density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                        layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                        currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                        savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                        if (function5 != null) {
                            composerStartRestartGroup.startReplaceGroup(1313917368);
                            function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl17 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl17, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl17, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function18) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function18);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl17, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function18) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function18);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl17, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function18) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function18);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1314774735);
                            function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                            if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl18 = Updater.m2388constructorimpl(composerStartRestartGroup);
                            m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl18, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                            Updater.m2396setimpl(composerM2388constructorimpl18, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function18) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function18);
                                }
                            });
                            Updater.m2396setimpl(composerM2388constructorimpl18, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((LayoutNode) obj, (Function1) obj2);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function18) {
                                    AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function18);
                                }
                            });
                            composerStartRestartGroup.endNode();
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                    }
                    function8 = function5;
                    function9 = function7;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier12 = modifier3;
                        final Function1<? super T, Unit> function18 = function6;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i11) {
                                AndroidView_androidKt.AndroidView(function1, modifier12, function8, function18, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i3 |= 24576;
                function7 = function4;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = NoOpUpdate;
                    }
                    if (i8 != 0) {
                        function7 = NoOpUpdate;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                    }
                    iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                    savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                    if (function5 != null) {
                        composerStartRestartGroup.startReplaceGroup(1313917368);
                        function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl19 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl19, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl19, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function19) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function19);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl19, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function19) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function19);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl19, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function19) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function19);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1314774735);
                        function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl110 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl110, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl110, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function19) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function19);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl110, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function19) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function19);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                function8 = function5;
                function9 = function7;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier13 = modifier3;
                    final Function1<? super T, Unit> function19 = function6;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidView_androidKt.AndroidView(function1, modifier13, function8, function19, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 3072;
            function6 = function3;
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    function7 = function4;
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = NoOpUpdate;
                    }
                    if (i8 != 0) {
                        function7 = NoOpUpdate;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                    }
                    iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                    savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                    if (function5 != null) {
                        composerStartRestartGroup.startReplaceGroup(1313917368);
                        function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl111 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl111, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl111, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function110) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function110);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl111, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function110) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function110);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl111, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function110) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function110);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1314774735);
                        function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl112 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl112, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl112, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function110) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function110);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl112, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function110) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function110);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                function8 = function5;
                function9 = function7;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier14 = modifier3;
                    final Function1<? super T, Unit> function110 = function6;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidView_androidKt.AndroidView(function1, modifier14, function8, function110, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 24576;
            function7 = function4;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if (i6 != 0) {
                    function6 = NoOpUpdate;
                }
                if (i8 != 0) {
                    function7 = NoOpUpdate;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                }
                iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                if (function5 != null) {
                    composerStartRestartGroup.startReplaceGroup(1313917368);
                    function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl113 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl113, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl113, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function111) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function111);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl113, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function111) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function111);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl113, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function111) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function111);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1314774735);
                    function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl114 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl114, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl114, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function111) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function111);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl114, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function111) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function111);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            function8 = function5;
            function9 = function7;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier15 = modifier3;
                final Function1<? super T, Unit> function111 = function6;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidView_androidKt.AndroidView(function1, modifier15, function8, function111, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function5 = function2;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                function6 = function3;
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            i8 = i2 & 16;
            if (i8 != 0) {
                if ((i & 24576) == 0) {
                    function7 = function4;
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = NoOpUpdate;
                    }
                    if (i8 != 0) {
                        function7 = NoOpUpdate;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                    }
                    iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                    density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                    layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                    savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                    if (function5 != null) {
                        composerStartRestartGroup.startReplaceGroup(1313917368);
                        function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl115 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl115, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl115, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function112) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function112);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl115, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function112) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function112);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl115, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function112) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function112);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1314774735);
                        function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                        if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl116 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl116, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                        Updater.m2396setimpl(composerM2388constructorimpl116, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function112) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function112);
                            }
                        });
                        Updater.m2396setimpl(composerM2388constructorimpl116, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((LayoutNode) obj, (Function1) obj2);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function112) {
                                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function112);
                            }
                        });
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                function8 = function5;
                function9 = function7;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier16 = modifier3;
                    final Function1<? super T, Unit> function112 = function6;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            AndroidView_androidKt.AndroidView(function1, modifier16, function8, function112, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 24576;
            function7 = function4;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if (i6 != 0) {
                    function6 = NoOpUpdate;
                }
                if (i8 != 0) {
                    function7 = NoOpUpdate;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                }
                iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                if (function5 != null) {
                    composerStartRestartGroup.startReplaceGroup(1313917368);
                    function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl117 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl117, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl117, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function113) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function113);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl117, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function113) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function113);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl117, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function113) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function113);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1314774735);
                    function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl118 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl118, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl118, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function113) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function113);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl118, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function113) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function113);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            function8 = function5;
            function9 = function7;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier17 = modifier3;
                final Function1<? super T, Unit> function113 = function6;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidView_androidKt.AndroidView(function1, modifier17, function8, function113, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 3072;
        function6 = function3;
        i8 = i2 & 16;
        if (i8 != 0) {
            if ((i & 24576) == 0) {
                function7 = function4;
                if (composerStartRestartGroup.changedInstance(function7)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i3 |= i9;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if (i6 != 0) {
                    function6 = NoOpUpdate;
                }
                if (i8 != 0) {
                    function7 = NoOpUpdate;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
                }
                iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
                density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
                layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
                currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
                savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
                if (function5 != null) {
                    composerStartRestartGroup.startReplaceGroup(1313917368);
                    function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl119 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl119, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl119, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function114) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function114);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl119, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function114) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function114);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl119, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function114) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function114);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1314774735);
                    function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                    if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl1110 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl1110, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                    Updater.m2396setimpl(composerM2388constructorimpl1110, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function114) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function114);
                        }
                    });
                    Updater.m2396setimpl(composerM2388constructorimpl1110, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((LayoutNode) obj, (Function1) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function114) {
                            AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function114);
                        }
                    });
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            function8 = function5;
            function9 = function7;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier18 = modifier3;
                final Function1<? super T, Unit> function114 = function6;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        AndroidView_androidKt.AndroidView(function1, modifier18, function8, function114, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 24576;
        function7 = function4;
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i10 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                function5 = null;
            }
            if (i6 != 0) {
                function6 = NoOpUpdate;
            }
            if (i8 != 0) {
                function7 = NoOpUpdate;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-180024211, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:199)");
            }
            iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
            density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            lifecycleOwner = (LifecycleOwner) composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner());
            savedStateRegistryOwner = (SavedStateRegistryOwner) composerStartRestartGroup.consume(LocalSavedStateRegistryOwnerKt.getLocalSavedStateRegistryOwner());
            if (function5 != null) {
                composerStartRestartGroup.startReplaceGroup(1313917368);
                function0CreateAndroidViewNodeFactory2 = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl1111 = Updater.m2388constructorimpl(composerStartRestartGroup);
                m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl1111, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                Updater.m2396setimpl(composerM2388constructorimpl1111, function5, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((LayoutNode) obj, (Function1) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function115) {
                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(function115);
                    }
                });
                Updater.m2396setimpl(composerM2388constructorimpl1111, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((LayoutNode) obj, (Function1) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function115) {
                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function115);
                    }
                });
                Updater.m2396setimpl(composerM2388constructorimpl1111, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((LayoutNode) obj, (Function1) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function115) {
                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function115);
                    }
                });
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1314774735);
                function0CreateAndroidViewNodeFactory = createAndroidViewNodeFactory(function1, composerStartRestartGroup, i3 & 14);
                if (!(composerStartRestartGroup.getApplier() instanceof UiApplier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(function0CreateAndroidViewNodeFactory);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl1112 = Updater.m2388constructorimpl(composerStartRestartGroup);
                m6277updateViewHolderParams6NefGtU(composerM2388constructorimpl1112, modifierMaterializeModifier, iHashCode, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, currentCompositionLocalMap);
                Updater.m2396setimpl(composerM2388constructorimpl1112, function7, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((LayoutNode) obj, (Function1) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function115) {
                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setUpdateBlock(function115);
                    }
                });
                Updater.m2396setimpl(composerM2388constructorimpl1112, function6, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((LayoutNode) obj, (Function1) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode layoutNode, Function1<? super T, Unit> function115) {
                        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setReleaseBlock(function115);
                    }
                });
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        function8 = function5;
        function9 = function7;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier19 = modifier3;
            final Function1<? super T, Unit> function115 = function6;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i11) {
                    AndroidView_androidKt.AndroidView(function1, modifier19, function8, function115, function9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    private static final <T extends View> Function0<LayoutNode> createAndroidViewNodeFactory(final Function1<? super Context, ? extends T> function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2030558801, i, -1, "androidx.compose.ui.viewinterop.createAndroidViewNodeFactory (AndroidView.android.kt:252)");
        }
        final int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
        final Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        final CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composer, 0);
        final SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) composer.consume(SaveableStateRegistryKt.getLocalSaveableStateRegistry());
        final View view = (View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView());
        boolean zChangedInstance = composer.changedInstance(context) | ((((i & 14) ^ 6) > 4 && composer.changed(function1)) || (i & 6) == 4) | composer.changedInstance(compositionContextRememberCompositionContext) | composer.changedInstance(saveableStateRegistry) | composer.changed(iHashCode) | composer.changedInstance(view);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0<LayoutNode>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$createAndroidViewNodeFactory$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final LayoutNode m6278invoke() {
                    Context context2 = context;
                    Function1<Context, T> function2 = function1;
                    CompositionContext compositionContext = compositionContextRememberCompositionContext;
                    SaveableStateRegistry saveableStateRegistry2 = saveableStateRegistry;
                    int i2 = iHashCode;
                    KeyEvent.Callback callback = view;
                    callback.getClass();
                    return new ViewFactoryHolder(context2, function2, compositionContext, saveableStateRegistry2, i2, (Owner) callback).getLayoutNode();
                }
            };
            composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        Function0<LayoutNode> function0 = (Function0) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return function0;
    }

    public static final Function1<View, Unit> getNoOpUpdate() {
        return NoOpUpdate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends View> ViewFactoryHolder<T> requireViewFactoryHolder(LayoutNode layoutNode) {
        AndroidViewHolder interopViewFactoryHolder = layoutNode.getInteropViewFactoryHolder();
        if (interopViewFactoryHolder != null) {
            return (ViewFactoryHolder) interopViewFactoryHolder;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: updateViewHolderParams-6NefGtU, reason: not valid java name */
    private static final <T extends View> void m6277updateViewHolderParams6NefGtU(Composer composer, Modifier modifier, int i, Density density, LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner, LayoutDirection layoutDirection, CompositionLocalMap compositionLocalMap) {
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        Updater.m2396setimpl(composer, compositionLocalMap, companion.getSetResolvedCompositionLocals());
        Updater.m2396setimpl(composer, modifier, new Function2<LayoutNode, Modifier, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (Modifier) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(LayoutNode layoutNode, Modifier modifier2) {
                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setModifier(modifier2);
            }
        });
        Updater.m2396setimpl(composer, density, new Function2<LayoutNode, Density, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (Density) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(LayoutNode layoutNode, Density density2) {
                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setDensity(density2);
            }
        });
        Updater.m2396setimpl(composer, lifecycleOwner, new Function2<LayoutNode, LifecycleOwner, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (LifecycleOwner) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(LayoutNode layoutNode, LifecycleOwner lifecycleOwner2) {
                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setLifecycleOwner(lifecycleOwner2);
            }
        });
        Updater.m2396setimpl(composer, savedStateRegistryOwner, new Function2<LayoutNode, SavedStateRegistryOwner, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$4
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (SavedStateRegistryOwner) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(LayoutNode layoutNode, SavedStateRegistryOwner savedStateRegistryOwner2) {
                AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setSavedStateRegistryOwner(savedStateRegistryOwner2);
            }
        });
        Updater.m2396setimpl(composer, layoutDirection, new Function2<LayoutNode, LayoutDirection, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$5

            @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LayoutDirection.values().length];
                    try {
                        iArr[LayoutDirection.Ltr.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[LayoutDirection.Rtl.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            public final void invoke(LayoutNode layoutNode, LayoutDirection layoutDirection2) {
                ViewFactoryHolder viewFactoryHolderRequireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder(layoutNode);
                int i2 = WhenMappings.$EnumSwitchMapping$0[layoutDirection2.ordinal()];
                int i3 = 1;
                if (i2 == 1) {
                    i3 = 0;
                } else if (i2 != 2) {
                    bu8.a();
                    return;
                }
                viewFactoryHolderRequireViewFactoryHolder.setLayoutDirection(i3);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (LayoutDirection) obj2);
                return Unit.INSTANCE;
            }
        });
        Updater.m2392initimpl(composer, Integer.valueOf(i), companion.getSetCompositeKeyHash());
    }

    public static final <T extends View> void AndroidView(final Function1<? super Context, ? extends T> function1, Modifier modifier, Function1<? super T, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        final Modifier modifier2;
        final Function1<? super T, Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1783766393);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        } else if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier3 = modifier;
            Function1<? super T, Unit> function4 = i5 != 0 ? NoOpUpdate : function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1783766393, i3, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:104)");
            }
            AndroidView(function1, modifier3, null, NoOpUpdate, function4, composerStartRestartGroup, (i3 & 14) | 3072 | (i3 & 112) | (57344 & (i3 << 6)), 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            function3 = function4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            function3 = function2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i6) {
                    AndroidView_androidKt.AndroidView(function1, modifier2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
