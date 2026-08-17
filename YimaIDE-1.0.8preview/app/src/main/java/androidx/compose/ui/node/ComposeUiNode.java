package androidx.compose.ui.node;

import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\ba\u0018\u0000 ,2\u00020\u0001:\u0001,R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u00020\u001bX¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u00020!X¦\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0018\u0010&\u001a\u00020'X¦\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006-À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/ComposeUiNode;", "", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "setMeasurePolicy", "(Landroidx/compose/ui/layout/MeasurePolicy;)V", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "setViewConfiguration", "(Landroidx/compose/ui/platform/ViewConfiguration;)V", "compositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "setCompositionLocalMap", "(Landroidx/compose/runtime/CompositionLocalMap;)V", "compositeKeyHash", "", "getCompositeKeyHash", "()I", "setCompositeKeyHash", "(I)V", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface ComposeUiNode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR(\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R(\u0010\u0012\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R(\u0010\u0015\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R(\u0010\u0018\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R(\u0010\u001b\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011R(\u0010\u001e\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0011R(\u0010!\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0011R\"\u0010$\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0%¢\u0006\u0002\b\u000f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Landroidx/compose/ui/node/ComposeUiNode$Companion;", "", "<init>", "()V", "Constructor", "Lkotlin/Function0;", "Landroidx/compose/ui/node/ComposeUiNode;", "getConstructor", "()Lkotlin/jvm/functions/Function0;", "VirtualConstructor", "getVirtualConstructor", "SetModifier", "Lkotlin/Function2;", "Landroidx/compose/ui/Modifier;", "", "Lkotlin/ExtensionFunctionType;", "getSetModifier", "()Lkotlin/jvm/functions/Function2;", "SetDensity", "Landroidx/compose/ui/unit/Density;", "getSetDensity", "SetResolvedCompositionLocals", "Landroidx/compose/runtime/CompositionLocalMap;", "getSetResolvedCompositionLocals", "SetMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getSetMeasurePolicy", "SetLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getSetLayoutDirection", "SetViewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getSetViewConfiguration", "SetCompositeKeyHash", "", "getSetCompositeKeyHash", "ApplyOnDeactivatedNodeAssertion", "Lkotlin/Function1;", "getApplyOnDeactivatedNodeAssertion", "()Lkotlin/jvm/functions/Function1;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Function0<ComposeUiNode> Constructor = LayoutNode.INSTANCE.getConstructor$ui();
        private static final Function0<ComposeUiNode> VirtualConstructor = new Function0<LayoutNode>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$VirtualConstructor$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final LayoutNode m4780invoke() {
                return new LayoutNode(true, 0, 2, null);
            }
        };
        private static final Function2<ComposeUiNode, Modifier, Unit> SetModifier = new Function2<ComposeUiNode, Modifier, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetModifier$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, (Modifier) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, Modifier modifier) {
                composeUiNode.setModifier(modifier);
            }
        };
        private static final Function2<ComposeUiNode, Density, Unit> SetDensity = new Function2<ComposeUiNode, Density, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, (Density) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, Density density) {
                composeUiNode.setDensity(density);
            }
        };
        private static final Function2<ComposeUiNode, CompositionLocalMap, Unit> SetResolvedCompositionLocals = new Function2<ComposeUiNode, CompositionLocalMap, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetResolvedCompositionLocals$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, (CompositionLocalMap) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, CompositionLocalMap compositionLocalMap) {
                composeUiNode.setCompositionLocalMap(compositionLocalMap);
            }
        };
        private static final Function2<ComposeUiNode, MeasurePolicy, Unit> SetMeasurePolicy = new Function2<ComposeUiNode, MeasurePolicy, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetMeasurePolicy$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, (MeasurePolicy) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, MeasurePolicy measurePolicy) {
                composeUiNode.setMeasurePolicy(measurePolicy);
            }
        };
        private static final Function2<ComposeUiNode, LayoutDirection, Unit> SetLayoutDirection = new Function2<ComposeUiNode, LayoutDirection, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetLayoutDirection$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, (LayoutDirection) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, LayoutDirection layoutDirection) {
                composeUiNode.setLayoutDirection(layoutDirection);
            }
        };
        private static final Function2<ComposeUiNode, ViewConfiguration, Unit> SetViewConfiguration = new Function2<ComposeUiNode, ViewConfiguration, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetViewConfiguration$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, (ViewConfiguration) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, ViewConfiguration viewConfiguration) {
                composeUiNode.setViewConfiguration(viewConfiguration);
            }
        };
        private static final Function2<ComposeUiNode, Integer, Unit> SetCompositeKeyHash = new Function2<ComposeUiNode, Integer, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetCompositeKeyHash$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((ComposeUiNode) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ComposeUiNode composeUiNode, int i) {
                composeUiNode.setCompositeKeyHash(i);
            }
        };
        private static final Function1<ComposeUiNode, Unit> ApplyOnDeactivatedNodeAssertion = new Function1<ComposeUiNode, Unit>() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$ApplyOnDeactivatedNodeAssertion$1
            public final void invoke(ComposeUiNode composeUiNode) {
                LayoutNode layoutNode = composeUiNode instanceof LayoutNode ? (LayoutNode) composeUiNode : null;
                if (layoutNode == null || !layoutNode.getIsDeactivated()) {
                    return;
                }
                qu7.a("Apply is called on deactivated node ", composeUiNode);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ComposeUiNode) obj);
                return Unit.INSTANCE;
            }
        };

        private Companion() {
        }

        public final Function1<ComposeUiNode, Unit> getApplyOnDeactivatedNodeAssertion() {
            return ApplyOnDeactivatedNodeAssertion;
        }

        public final Function0<ComposeUiNode> getConstructor() {
            return Constructor;
        }

        public final Function2<ComposeUiNode, Integer, Unit> getSetCompositeKeyHash() {
            return SetCompositeKeyHash;
        }

        public final Function2<ComposeUiNode, Density, Unit> getSetDensity() {
            return SetDensity;
        }

        public final Function2<ComposeUiNode, LayoutDirection, Unit> getSetLayoutDirection() {
            return SetLayoutDirection;
        }

        public final Function2<ComposeUiNode, MeasurePolicy, Unit> getSetMeasurePolicy() {
            return SetMeasurePolicy;
        }

        public final Function2<ComposeUiNode, Modifier, Unit> getSetModifier() {
            return SetModifier;
        }

        public final Function2<ComposeUiNode, CompositionLocalMap, Unit> getSetResolvedCompositionLocals() {
            return SetResolvedCompositionLocals;
        }

        public final Function2<ComposeUiNode, ViewConfiguration, Unit> getSetViewConfiguration() {
            return SetViewConfiguration;
        }

        public final Function0<ComposeUiNode> getVirtualConstructor() {
            return VirtualConstructor;
        }
    }

    int getCompositeKeyHash();

    CompositionLocalMap getCompositionLocalMap();

    Density getDensity();

    LayoutDirection getLayoutDirection();

    MeasurePolicy getMeasurePolicy();

    /* JADX INFO: renamed from: getModifier */
    Modifier get_modifier();

    ViewConfiguration getViewConfiguration();

    void setCompositeKeyHash(int i);

    void setCompositionLocalMap(CompositionLocalMap compositionLocalMap);

    void setDensity(Density density);

    void setLayoutDirection(LayoutDirection layoutDirection);

    void setMeasurePolicy(MeasurePolicy measurePolicy);

    void setModifier(Modifier modifier);

    void setViewConfiguration(ViewConfiguration viewConfiguration);
}
