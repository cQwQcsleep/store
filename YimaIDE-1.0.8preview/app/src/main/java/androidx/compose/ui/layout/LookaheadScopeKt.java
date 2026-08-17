package androidx.compose.ui.layout;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\b\u001a \u0001\u0010\t\u001a\u00020\n*\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u00032.\b\u0002\u0010\u0011\u001a(\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\u0002\b\u00072A\u0010\u0016\u001a=\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0017¢\u0006\u0002\b\u0007\u001a\u0012\u0010\u001f\u001a\u00020\u0014*\u00020\u00042\u0006\u0010 \u001a\u00020\u0014\u001a3\u0010!\u001a\u00020\"*\u00020\u00042\u0006\u0010#\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00142\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0010H\u0000¢\u0006\u0004\b&\u0010'\"4\u0010\u001e\u001a(\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"LookaheadScope", "", "content", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LookaheadScope;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "approachLayout", "Landroidx/compose/ui/Modifier;", "isMeasurementApproachInProgress", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", "name", "lookaheadSize", "", "isPlacementApproachInProgress", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "lookaheadCoordinates", "approachMeasure", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "defaultPlacementApproachInProgress", "lookaheadScopeCoordinates", "sourceCoordinates", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "coordinates", "relativeToSource", "includeMotionFrameOfReference", "localLookaheadPositionOf-Fgt4K4Q", "(Landroidx/compose/ui/layout/LookaheadScope;Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LookaheadScopeKt {
    private static final Function2<Placeable.PlacementScope, LayoutCoordinates, Boolean> defaultPlacementApproachInProgress = new Function2<Placeable.PlacementScope, LayoutCoordinates, Boolean>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$defaultPlacementApproachInProgress$1
        public final Boolean invoke(Placeable.PlacementScope placementScope, LayoutCoordinates layoutCoordinates) {
            return Boolean.FALSE;
        }
    };

    public static final void LookaheadScope(final Function3<? super LookaheadScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(441837433);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        int i3 = 1;
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(441837433, i2, -1, "androidx.compose.ui.layout.LookaheadScope (LookaheadScope.kt:49)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                Function0 function0 = null;
                objRememberedValue = new LookaheadScopeImpl(function0, i3, function0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            LookaheadScopeImpl lookaheadScopeImpl = (LookaheadScopeImpl) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function0<LayoutNode>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$1$1
                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                    public final LayoutNode m4637invoke() {
                        return new LayoutNode(true, 0, 2, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function0 function1 = (Function0) objRememberedValue2;
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(function1);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2393initimpl(composerM2388constructorimpl, new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((LayoutNode) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(LayoutNode layoutNode) {
                    layoutNode.setVirtualLookaheadRoot$ui(true);
                }
            });
            Updater.m2396setimpl(composerM2388constructorimpl, lookaheadScopeImpl, new Function2<LayoutNode, LookaheadScopeImpl, Unit>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((LayoutNode) obj, (LookaheadScopeImpl) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(final LayoutNode layoutNode, LookaheadScopeImpl lookaheadScopeImpl2) {
                    lookaheadScopeImpl2.setScopeCoordinates(new Function0<LayoutCoordinates>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2.1
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                        public final LayoutCoordinates m4638invoke() {
                            LayoutNode parent$ui = layoutNode.getParent$ui();
                            parent$ui.getClass();
                            return parent$ui.getInnerCoordinator$ui().getCoordinates();
                        }
                    });
                }
            });
            function3.invoke(lookaheadScopeImpl, composerStartRestartGroup, Integer.valueOf((i2 << 3) & 112));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt.LookaheadScope.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    LookaheadScopeKt.LookaheadScope(function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final Modifier approachLayout(Modifier modifier, Function1<? super IntSize, Boolean> function1, Function2<? super Placeable.PlacementScope, ? super LayoutCoordinates, Boolean> function2, Function3<? super ApproachMeasureScope, ? super Measurable, ? super Constraints, ? extends MeasureResult> function3) {
        return modifier.then(new ApproachLayoutElement(function3, function1, function2));
    }

    public static /* synthetic */ Modifier approachLayout$default(Modifier modifier, Function1 function1, Function2 function2, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = defaultPlacementApproachInProgress;
        }
        return approachLayout(modifier, function1, function2, function3);
    }

    /* JADX INFO: renamed from: localLookaheadPositionOf-Fgt4K4Q, reason: not valid java name */
    public static final long m4636localLookaheadPositionOfFgt4K4Q(LookaheadScope lookaheadScope, LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, long j, boolean z) {
        LayoutCoordinates lookaheadCoordinates = lookaheadScope.toLookaheadCoordinates(layoutCoordinates);
        LayoutCoordinates lookaheadCoordinates2 = lookaheadScope.toLookaheadCoordinates(layoutCoordinates2);
        if (lookaheadCoordinates instanceof LookaheadLayoutCoordinates) {
            return ((LookaheadLayoutCoordinates) lookaheadCoordinates).mo4615localPositionOfS_NoaFU(lookaheadCoordinates2, j, z);
        }
        return lookaheadCoordinates2 instanceof LookaheadLayoutCoordinates ? Offset.m2881constructorimpl(((LookaheadLayoutCoordinates) lookaheadCoordinates2).mo4615localPositionOfS_NoaFU(lookaheadCoordinates, j, z) ^ (-9223372034707292160L)) : lookaheadCoordinates.mo4615localPositionOfS_NoaFU(lookaheadCoordinates, j, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutCoordinates lookaheadScopeCoordinates(LookaheadScope lookaheadScope, LayoutCoordinates layoutCoordinates) {
        if (layoutCoordinates instanceof LookaheadCapablePlaceable) {
            return lookaheadScope.getLookaheadScopeCoordinates(((LookaheadCapablePlaceable) layoutCoordinates).getPlacementScope());
        }
        dt1.a("Invalid LayoutCoordinates: ", layoutCoordinates);
        return null;
    }
}
