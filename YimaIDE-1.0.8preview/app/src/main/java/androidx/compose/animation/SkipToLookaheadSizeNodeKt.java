package androidx.compose.animation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.ContentScale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0003\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"DefaultEnabled", "Lkotlin/Function0;", "", "createContentScaleModifier", "Landroidx/compose/ui/Modifier;", "scaleToBounds", "Landroidx/compose/animation/ScaleToBoundsImpl;", "isEnabled", "animation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SkipToLookaheadSizeNodeKt {
    private static final Function0<Boolean> DefaultEnabled = new Function0<Boolean>() { // from class: androidx.compose.animation.SkipToLookaheadSizeNodeKt$DefaultEnabled$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Boolean m180invoke() {
            return Boolean.TRUE;
        }
    };

    public static final Modifier createContentScaleModifier(Modifier modifier, ScaleToBoundsImpl scaleToBoundsImpl, final Function0<Boolean> function0) {
        return modifier.then(Intrinsics.areEqual(scaleToBoundsImpl.getContentScale(), ContentScale.Companion.getCrop()) ? GraphicsLayerModifierKt.graphicsLayer(Modifier.Companion, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.SkipToLookaheadSizeNodeKt.createContentScaleModifier.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                graphicsLayerScope.setClip(((Boolean) function0.invoke()).booleanValue());
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((GraphicsLayerScope) obj);
                return Unit.INSTANCE;
            }
        }) : Modifier.Companion).then(new SkipToLookaheadSizeElement(scaleToBoundsImpl, function0));
    }
}
