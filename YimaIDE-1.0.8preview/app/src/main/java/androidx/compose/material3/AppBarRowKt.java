package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material3.AppBarRowKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001aS\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u0084\u0002"}, d2 = {"AppBarRow", "", "overflowIndicator", "Lkotlin/Function1;", "Landroidx/compose/material3/AppBarMenuState;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "maxItemCount", "", "content", "Landroidx/compose/material3/AppBarRowScope;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "material3", "scope", "Landroidx/compose/material3/AppBarRowScopeImpl;"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AppBarRowKt {
    /* JADX WARN: Code duplicated, block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x0065  */
    /* JADX WARN: Code duplicated, block: B:40:0x0069  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:43:0x0072  */
    /* JADX WARN: Code duplicated, block: B:47:0x007d  */
    /* JADX WARN: Code duplicated, block: B:48:0x007f  */
    /* JADX WARN: Code duplicated, block: B:51:0x0088 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:55:0x0090  */
    /* JADX WARN: Code duplicated, block: B:58:0x009a  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:64:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:68:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:73:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:76:0x0130  */
    /* JADX WARN: Code duplicated, block: B:78:0x0136  */
    /* JADX WARN: Code duplicated, block: B:81:0x0159  */
    /* JADX WARN: Code duplicated, block: B:84:0x0165  */
    /* JADX WARN: Code duplicated, block: B:85:0x0169  */
    /* JADX WARN: Code duplicated, block: B:88:0x0188  */
    /* JADX WARN: Code duplicated, block: B:90:0x0196  */
    /* JADX WARN: Code duplicated, block: B:93:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:95:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:98:0x01cc  */
    public static final void AppBarRow(final Function3<? super AppBarMenuState, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, int i, final Function1<? super AppBarRowScope, Unit> function1, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z;
        Modifier modifier3;
        final int i9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final State stateRememberUpdatedState;
        Object objRememberedValue;
        Composer.Companion companion;
        Object objRememberedValue2;
        AppBarOverflowState appBarOverflowStateRememberAppBarOverflowState;
        boolean z2;
        boolean z3;
        Object objRememberedValue3;
        OverflowMeasurePolicy overflowMeasurePolicy;
        boolean zChanged;
        Object objRememberedValue4;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composerStartRestartGroup = composer.startRestartGroup(1891322548);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function3) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i10 = i3 & 2;
        if (i10 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i5 = i3 & 4;
            if (i5 != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i4 |= i7;
                }
                if ((i3 & 8) != 0) {
                    i4 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                if ((i4 & 1171) != 1170) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    if (i10 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i5 != 0) {
                        i6 = Integer.MAX_VALUE;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1891322548, i4, -1, "androidx.compose.material3.AppBarRow (AppBarRow.kt:56)");
                    }
                    stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composerStartRestartGroup, (i4 >> 9) & 14);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: jd0
                            public final Object invoke() {
                                return AppBarRowKt.b(stateRememberUpdatedState);
                            }
                        });
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    final State state = (State) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new AppBarMenuState();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    AppBarMenuState appBarMenuState = (AppBarMenuState) objRememberedValue2;
                    appBarOverflowStateRememberAppBarOverflowState = AppBarDslKt.rememberAppBarOverflowState(composerStartRestartGroup, 0);
                    boolean zChanged2 = composerStartRestartGroup.changed(appBarOverflowStateRememberAppBarOverflowState);
                    if ((i4 & 896) == 256) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    z3 = z2 | zChanged2;
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z3 || objRememberedValue3 == companion.getEmpty()) {
                        objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    overflowMeasurePolicy = (OverflowMeasurePolicy) objRememberedValue3;
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(CollectionsKt.listOf(new Function2[]{ComposableLambdaKt.rememberComposableLambda(68955781, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt.AppBarRow.1
                        public final void invoke(Composer composer2, int i11) {
                            if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(68955781, i11, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:71)");
                            }
                            List<AppBarItem> items = AppBarRowKt.AppBarRow$lambda$2(state).getItems();
                            int size = items.size();
                            for (int i12 = 0; i12 < size; i12++) {
                                items.get(i12).AppbarContent(composer2, 0);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-815780026, true, new AnonymousClass2(function3, appBarMenuState, appBarOverflowStateRememberAppBarOverflowState, state), composerStartRestartGroup, 54)}));
                    zChanged = composerStartRestartGroup.changed(overflowMeasurePolicy);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue4 == companion.getEmpty()) {
                        objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue4;
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                i9 = i6;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = modifier3;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kd0
                        public final Object invoke(Object obj, Object obj2) {
                            return AppBarRowKt.a(function3, modifier4, i9, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            i6 = i;
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 2048;
                } else {
                    i8 = 1024;
                }
                i4 |= i8;
            }
            if ((i4 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    i6 = Integer.MAX_VALUE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1891322548, i4, -1, "androidx.compose.material3.AppBarRow (AppBarRow.kt:56)");
                }
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composerStartRestartGroup, (i4 >> 9) & 14);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: jd0
                        public final Object invoke() {
                            return AppBarRowKt.b(stateRememberUpdatedState);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final State<AppBarRowScopeImpl> state2 = (State) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new AppBarMenuState();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                AppBarMenuState appBarMenuState2 = (AppBarMenuState) objRememberedValue2;
                appBarOverflowStateRememberAppBarOverflowState = AppBarDslKt.rememberAppBarOverflowState(composerStartRestartGroup, 0);
                boolean zChanged3 = composerStartRestartGroup.changed(appBarOverflowStateRememberAppBarOverflowState);
                if ((i4 & 896) == 256) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | zChanged3;
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                overflowMeasurePolicy = (OverflowMeasurePolicy) objRememberedValue3;
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts(CollectionsKt.listOf(new Function2[]{ComposableLambdaKt.rememberComposableLambda(68955781, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt.AppBarRow.1
                    public final void invoke(Composer composer2, int i11) {
                        if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(68955781, i11, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:71)");
                        }
                        List<AppBarItem> items = AppBarRowKt.AppBarRow$lambda$2(state2).getItems();
                        int size = items.size();
                        for (int i12 = 0; i12 < size; i12++) {
                            items.get(i12).AppbarContent(composer2, 0);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-815780026, true, new AnonymousClass2(function3, appBarMenuState2, appBarOverflowStateRememberAppBarOverflowState, state2), composerStartRestartGroup, 54)}));
                zChanged = composerStartRestartGroup.changed(overflowMeasurePolicy);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue4;
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy2, companion3.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion3.getSetModifier());
                function2CombineAsVirtualLayouts2.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            i9 = i6;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kd0
                    public final Object invoke(Object obj, Object obj2) {
                        return AppBarRowKt.a(function3, modifier5, i9, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        i5 = i3 & 4;
        if (i5 != 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i4 |= i7;
            }
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i8 = 2048;
                } else {
                    i8 = 1024;
                }
                i4 |= i8;
            }
            if ((i4 & 1171) != 1170) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                if (i10 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    i6 = Integer.MAX_VALUE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1891322548, i4, -1, "androidx.compose.material3.AppBarRow (AppBarRow.kt:56)");
                }
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composerStartRestartGroup, (i4 >> 9) & 14);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: jd0
                        public final Object invoke() {
                            return AppBarRowKt.b(stateRememberUpdatedState);
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final State<AppBarRowScopeImpl> state3 = (State) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new AppBarMenuState();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                AppBarMenuState appBarMenuState3 = (AppBarMenuState) objRememberedValue2;
                appBarOverflowStateRememberAppBarOverflowState = AppBarDslKt.rememberAppBarOverflowState(composerStartRestartGroup, 0);
                boolean zChanged4 = composerStartRestartGroup.changed(appBarOverflowStateRememberAppBarOverflowState);
                if ((i4 & 896) == 256) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z3 = z2 | zChanged4;
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                overflowMeasurePolicy = (OverflowMeasurePolicy) objRememberedValue3;
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts3 = LayoutKt.combineAsVirtualLayouts(CollectionsKt.listOf(new Function2[]{ComposableLambdaKt.rememberComposableLambda(68955781, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt.AppBarRow.1
                    public final void invoke(Composer composer2, int i11) {
                        if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(68955781, i11, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:71)");
                        }
                        List<AppBarItem> items = AppBarRowKt.AppBarRow$lambda$2(state3).getItems();
                        int size = items.size();
                        for (int i12 = 0; i12 < size; i12++) {
                            items.get(i12).AppbarContent(composer2, 0);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-815780026, true, new AnonymousClass2(function3, appBarMenuState3, appBarOverflowStateRememberAppBarOverflowState, state3), composerStartRestartGroup, 54)}));
                zChanged = composerStartRestartGroup.changed(overflowMeasurePolicy);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue4;
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy3, companion4.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion4.getSetModifier());
                function2CombineAsVirtualLayouts3.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            i9 = i6;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kd0
                    public final Object invoke(Object obj, Object obj2) {
                        return AppBarRowKt.a(function3, modifier6, i9, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        i6 = i;
        if ((i3 & 8) != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i8 = 2048;
            } else {
                i8 = 1024;
            }
            i4 |= i8;
        }
        if ((i4 & 1171) != 1170) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            if (i10 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i5 != 0) {
                i6 = Integer.MAX_VALUE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1891322548, i4, -1, "androidx.compose.material3.AppBarRow (AppBarRow.kt:56)");
            }
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composerStartRestartGroup, (i4 >> 9) & 14);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: jd0
                    public final Object invoke() {
                        return AppBarRowKt.b(stateRememberUpdatedState);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final State<AppBarRowScopeImpl> state4 = (State) objRememberedValue;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new AppBarMenuState();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            AppBarMenuState appBarMenuState4 = (AppBarMenuState) objRememberedValue2;
            appBarOverflowStateRememberAppBarOverflowState = AppBarDslKt.rememberAppBarOverflowState(composerStartRestartGroup, 0);
            boolean zChanged5 = composerStartRestartGroup.changed(appBarOverflowStateRememberAppBarOverflowState);
            if ((i4 & 896) == 256) {
                z2 = true;
            } else {
                z2 = false;
            }
            z3 = z2 | zChanged5;
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z3) {
                objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new OverflowMeasurePolicy(appBarOverflowStateRememberAppBarOverflowState, i6, false);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            overflowMeasurePolicy = (OverflowMeasurePolicy) objRememberedValue3;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts4 = LayoutKt.combineAsVirtualLayouts(CollectionsKt.listOf(new Function2[]{ComposableLambdaKt.rememberComposableLambda(68955781, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt.AppBarRow.1
                public final void invoke(Composer composer2, int i11) {
                    if (!composer2.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(68955781, i11, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:71)");
                    }
                    List<AppBarItem> items = AppBarRowKt.AppBarRow$lambda$2(state4).getItems();
                    int size = items.size();
                    for (int i12 = 0; i12 < size; i12++) {
                        items.get(i12).AppbarContent(composer2, 0);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-815780026, true, new AnonymousClass2(function3, appBarMenuState4, appBarOverflowStateRememberAppBarOverflowState, state4), composerStartRestartGroup, 54)}));
            zChanged = composerStartRestartGroup.changed(overflowMeasurePolicy);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = MultiContentMeasurePolicyKt.createMeasurePolicy(overflowMeasurePolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue4;
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
            ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
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
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy4, companion5.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion5.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion5.getSetModifier());
            function2CombineAsVirtualLayouts4.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        i9 = i6;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier7 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kd0
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarRowKt.a(function3, modifier7, i9, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppBarRowScopeImpl AppBarRow$lambda$2(State<AppBarRowScopeImpl> state) {
        return state.getValue();
    }

    public static Unit a(Function3 function3, Modifier modifier, int i, Function1 function1, int i2, int i3, Composer composer, int i4) {
        AppBarRow(function3, modifier, i, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static AppBarRowScopeImpl b(State state) {
        AppBarRowScopeImpl appBarRowScopeImpl = new AppBarRowScopeImpl(new AppBarScopeImpl());
        ((Function1) state.getValue()).invoke(appBarRowScopeImpl);
        return appBarRowScopeImpl;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarRowKt$AppBarRow$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ AppBarMenuState $menuState;
        final /* synthetic */ Function3<AppBarMenuState, Composer, Integer, Unit> $overflowIndicator;
        final /* synthetic */ AppBarOverflowState $overflowState;
        final /* synthetic */ State<AppBarRowScopeImpl> $scope$delegate;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function3<? super AppBarMenuState, ? super Composer, ? super Integer, Unit> function3, AppBarMenuState appBarMenuState, AppBarOverflowState appBarOverflowState, State<AppBarRowScopeImpl> state) {
            this.$overflowIndicator = function3;
            this.$menuState = appBarMenuState;
            this.$overflowState = appBarOverflowState;
            this.$scope$delegate = state;
        }

        public static Unit a(AppBarMenuState appBarMenuState) {
            appBarMenuState.dismiss();
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-815780026, i, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:73)");
            }
            Function3<AppBarMenuState, Composer, Integer, Unit> function3 = this.$overflowIndicator;
            final AppBarMenuState appBarMenuState = this.$menuState;
            final AppBarOverflowState appBarOverflowState = this.$overflowState;
            final State<AppBarRowScopeImpl> state = this.$scope$delegate;
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
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
            function3.invoke(appBarMenuState, composer, 6);
            boolean zIsExpanded = appBarMenuState.isExpanded();
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: androidx.compose.material3.e
                    public final Object invoke() {
                        return AppBarRowKt.AnonymousClass2.a(appBarMenuState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            AndroidMenu_androidKt.m80DropdownMenuIlH_yew(zIsExpanded, (Function0) objRememberedValue, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1786124721, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt$AppBarRow$2$1$2
                public final void invoke(ColumnScope columnScope, Composer composer2, int i2) {
                    if (!composer2.shouldExecute((i2 & 17) != 16, i2 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1786124721, i2, -1, "androidx.compose.material3.AppBarRow.<anonymous>.<anonymous>.<anonymous> (AppBarRow.kt:79)");
                    }
                    List<AppBarItem> listSubList = AppBarRowKt.AppBarRow$lambda$2(state).getItems().subList(appBarOverflowState.getVisibleItemCount(), appBarOverflowState.getTotalItemCount());
                    AppBarMenuState appBarMenuState2 = appBarMenuState;
                    int size = listSubList.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        listSubList.get(i3).MenuContent(appBarMenuState2, composer2, 6);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer2, Integer num) {
                    invoke(columnScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }
            }, composer, 54), composer, 48, 48, 2044);
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
}
