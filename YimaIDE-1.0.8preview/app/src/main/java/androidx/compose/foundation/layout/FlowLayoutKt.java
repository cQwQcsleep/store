package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.foundation.layout.FlowLayoutKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import com.intellij.util.io.IOUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aq\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001ag\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0015\u001aq\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u001a2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u001c\u001ag\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u001d\u001a%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u000bH\u0001¢\u0006\u0002\u0010!\u001a=\u0010\"\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010&\u001a%\u0010'\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u000bH\u0001¢\u0006\u0002\u0010(\u001a=\u0010)\u001a\u00020#2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010*\u001aT\u0010+\u001a\u00020\u000b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2#\u0010/\u001a\u001f\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b00¢\u0006\u0002\b\u00132\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000bH\u0082\b\u001a\u0091\u0001\u00103\u001a\u00020\u000b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2#\u0010/\u001a\u001f\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b00¢\u0006\u0002\b\u00132#\u00104\u001a\u001f\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b00¢\u0006\u0002\b\u00132\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020%H\u0083\b\u001a[\u00106\u001a\u0002072\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020%H\u0002¢\u0006\u0002\u0010<\u001a\u0096\u0001\u00106\u001a\u0002072\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2#\u0010/\u001a\u001f\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b00¢\u0006\u0002\b\u00132#\u00104\u001a\u001f\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b00¢\u0006\u0002\b\u00132\u0006\u0010;\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020%H\u0082\b¢\u0006\u0002\u0010=\u001aY\u0010>\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020J2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020%H\u0000¢\u0006\u0004\bK\u0010L\u001a\u001e\u0010M\u001a\u0004\u0018\u00010E*\b\u0012\u0004\u0012\u00020E0D2\b\u0010N\u001a\u0004\u0018\u00010OH\u0002\u001a\u001c\u0010P\u001a\u00020\u000b*\u00020.2\u0006\u0010Q\u001a\u00020R2\u0006\u00104\u001a\u00020\u000bH\u0000\u001a\u001c\u0010S\u001a\u00020\u000b*\u00020.2\u0006\u0010Q\u001a\u00020R2\u0006\u0010/\u001a\u00020\u000bH\u0000\u001a9\u0010Z\u001a\u000207*\u00020E2\u0006\u0010A\u001a\u00020B2\u0006\u0010I\u001a\u00020[2\u0014\u0010\\\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010]\u0012\u0004\u0012\u00020\u00010\u0010H\u0000¢\u0006\u0004\b^\u0010_\u001aQ\u0010`\u001a\u00020?*\u00020@2\u0006\u0010I\u001a\u00020J2\u0006\u0010a\u001a\u00020\u000b2\u0006\u0010b\u001a\u00020\u000b2\u0006\u0010:\u001a\u0002092\f\u0010c\u001a\b\u0012\u0004\u0012\u00020?0d2\u0006\u0010e\u001a\u00020B2\u0006\u0010f\u001a\u000209H\u0000¢\u0006\u0004\bg\u0010h\"\u0014\u0010T\u001a\u00020UX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010W\"\u0014\u0010X\u001a\u00020UX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010W¨\u0006i"}, d2 = {"FlowRow", "", "modifier", "Landroidx/compose/ui/Modifier;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "itemVerticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "maxItemsInEachRow", "", "maxLines", "overflow", "Landroidx/compose/foundation/layout/FlowRowOverflow;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowRowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;IILandroidx/compose/foundation/layout/FlowRowOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;IILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FlowColumn", "itemHorizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "maxItemsInEachColumn", "Landroidx/compose/foundation/layout/FlowColumnOverflow;", "Landroidx/compose/foundation/layout/FlowColumnScope;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Horizontal;IILandroidx/compose/foundation/layout/FlowColumnOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Horizontal;IILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "rowMeasurementHelper", "Landroidx/compose/ui/layout/MeasurePolicy;", "maxItemsInMainAxis", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "rowMeasurementMultiContentHelper", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "columnMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "columnMeasurementMultiContentHelper", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "maxIntrinsicMainAxisSize", "children", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "mainAxisSize", "Lkotlin/Function3;", "crossAxisAvailable", "mainAxisSpacing", "minIntrinsicMainAxisSize", "crossAxisSize", "crossAxisSpacing", "intrinsicCrossAxisSize", "Landroidx/collection/IntIntPair;", "mainAxisSizes", "", "crossAxisSizes", "mainAxisAvailable", "(Ljava/util/List;[I[IIIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "breakDownItems", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurePolicy", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "measurablesIterator", "", "Landroidx/compose/ui/layout/Measurable;", "mainAxisSpacingDp", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacingDp", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "breakDownItems-di9J0FM", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;Ljava/util/Iterator;FFJIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)Landroidx/compose/ui/layout/MeasureResult;", "safeNext", "info", "Landroidx/compose/foundation/layout/FlowLineInfo;", "mainAxisMin", "isHorizontal", "", "crossAxisMin", "CROSS_AXIS_ALIGNMENT_TOP", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCROSS_AXIS_ALIGNMENT_TOP", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "CROSS_AXIS_ALIGNMENT_START", "getCROSS_AXIS_ALIGNMENT_START", "measureAndCache", "Landroidx/compose/ui/unit/Constraints;", "storePlaceable", "Landroidx/compose/ui/layout/Placeable;", "measureAndCache-rqJ1uqs", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;JLkotlin/jvm/functions/Function1;)J", "placeHelper", "mainAxisTotalSize", "crossAxisTotalSize", "items", "Landroidx/compose/runtime/collection/MutableVector;", "measureHelper", "outPosition", "placeHelper-BmaY500", "(Landroidx/compose/ui/layout/MeasureScope;JII[ILandroidx/compose/runtime/collection/MutableVector;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;[I)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FlowLayoutKt {
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_START;
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_TOP;

    static {
        CrossAxisAlignment.Companion companion = CrossAxisAlignment.INSTANCE;
        Alignment.Companion companion2 = Alignment.Companion;
        CROSS_AXIS_ALIGNMENT_TOP = companion.vertical$foundation_layout(companion2.getTop());
        CROSS_AXIS_ALIGNMENT_START = companion.horizontal$foundation_layout(companion2.getStart());
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0122  */
    /* JADX WARN: Code duplicated, block: B:103:0x012e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0130  */
    /* JADX WARN: Code duplicated, block: B:106:0x0133  */
    /* JADX WARN: Code duplicated, block: B:107:0x0135  */
    /* JADX WARN: Code duplicated, block: B:109:0x0139  */
    /* JADX WARN: Code duplicated, block: B:110:0x0140  */
    /* JADX WARN: Code duplicated, block: B:113:0x0148  */
    /* JADX WARN: Code duplicated, block: B:116:0x0158  */
    /* JADX WARN: Code duplicated, block: B:117:0x015a  */
    /* JADX WARN: Code duplicated, block: B:120:0x0161  */
    /* JADX WARN: Code duplicated, block: B:122:0x0169  */
    /* JADX WARN: Code duplicated, block: B:125:0x0186  */
    /* JADX WARN: Code duplicated, block: B:126:0x0188  */
    /* JADX WARN: Code duplicated, block: B:129:0x0190  */
    /* JADX WARN: Code duplicated, block: B:130:0x0192  */
    /* JADX WARN: Code duplicated, block: B:133:0x019b  */
    /* JADX WARN: Code duplicated, block: B:134:0x019d  */
    /* JADX WARN: Code duplicated, block: B:137:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:139:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:142:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:144:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:147:0x0208  */
    /* JADX WARN: Code duplicated, block: B:150:0x0214  */
    /* JADX WARN: Code duplicated, block: B:151:0x0218  */
    /* JADX WARN: Code duplicated, block: B:154:0x025a  */
    /* JADX WARN: Code duplicated, block: B:157:0x0268  */
    /* JADX WARN: Code duplicated, block: B:160:0x027f  */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x005b  */
    /* JADX WARN: Code duplicated, block: B:33:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:55:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:81:0x00df  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:91:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:92:0x0102  */
    /* JADX WARN: Code duplicated, block: B:94:0x0106  */
    /* JADX WARN: Code duplicated, block: B:95:0x010e  */
    /* JADX WARN: Code duplicated, block: B:97:0x0111  */
    /* JADX WARN: Code duplicated, block: B:98:0x011d  */
    @Deprecated(message = "The overflow parameter has been deprecated")
    public static final void FlowColumn(Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, FlowColumnOverflow flowColumnOverflow, final Function3<? super FlowColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        int i5;
        Arrangement.Vertical vertical2;
        int i6;
        int i7;
        int i8;
        Alignment.Horizontal start;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z;
        final Modifier modifier2;
        final Arrangement.Horizontal horizontal3;
        final Arrangement.Vertical vertical3;
        Composer composer2;
        final int i17;
        final int i18;
        final FlowColumnOverflow flowColumnOverflow2;
        final Alignment.Horizontal horizontal4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        Arrangement.Vertical top;
        int i19;
        Arrangement.Horizontal start2;
        int i20;
        int i21;
        FlowColumnOverflow clip;
        int i22;
        boolean z2;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        MultiContentMeasurePolicy multiContentMeasurePolicyColumnMeasurementMultiContentHelper;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Object objRememberedValue2;
        Object obj;
        boolean zChanged;
        Object objRememberedValue3;
        Function0 constructor;
        int i23;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1944405121);
        int i24 = i4 & 1;
        if (i24 != 0) {
            i5 = i3 | 6;
        } else if ((i3 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i3;
        } else {
            i5 = i3;
        }
        int i25 = i4 & 2;
        if (i25 == 0) {
            if ((i3 & 48) == 0) {
                vertical2 = vertical;
                i5 |= composerStartRestartGroup.changed(vertical2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & 384) == 0) {
                    if (composerStartRestartGroup.changed(horizontal)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        start = horizontal2;
                        if (composerStartRestartGroup.changed(start)) {
                            i9 = 2048;
                        } else {
                            i9 = 1024;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 16;
                    if (i10 != 0) {
                        if ((i3 & 24576) == 0) {
                            i11 = i;
                            if (composerStartRestartGroup.changed(i11)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i5 |= i12;
                        }
                        i13 = i4 & 32;
                        if (i13 != 0) {
                            i5 |= 196608;
                        } else if ((i3 & 196608) == 0) {
                            if (composerStartRestartGroup.changed(i2)) {
                                i14 = 131072;
                            } else {
                                i14 = 65536;
                            }
                            i5 |= i14;
                        }
                        i15 = i4 & 64;
                        if (i15 != 0) {
                            i5 |= 1572864;
                        } else if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                                i16 = IOUtil.MiB;
                            } else {
                                i16 = 524288;
                            }
                            i5 |= i16;
                        }
                        if ((i3 & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i23 = 8388608;
                            } else {
                                i23 = 4194304;
                            }
                            i5 |= i23;
                        }
                        if ((i5 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i24 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i25 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start2 = Arrangement.INSTANCE.getStart();
                                i19 = i8;
                            } else {
                                i19 = i8;
                                start2 = horizontal;
                            }
                            if (i19 != 0) {
                                start = Alignment.Companion.getStart();
                            }
                            if (i10 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i11;
                            }
                            if (i13 != 0) {
                                i21 = Integer.MAX_VALUE;
                            } else {
                                i21 = i2;
                            }
                            if (i15 != 0) {
                                clip = FlowColumnOverflow.INSTANCE.getClip();
                            } else {
                                clip = flowColumnOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                            }
                            i22 = 3670016 & i5;
                            if (i22 == 1048576) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                            if (i22 == 1048576) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if ((29360128 & i5) == 8388608) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            boolean z7 = z4 | z3;
                            if ((i5 & 458752) == 131072) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            z6 = z7 | z5;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                    public final Object invoke(Object obj2, Object obj3) {
                                        return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                    }
                                }));
                                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            Function2 function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts((List) obj);
                            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged || objRememberedValue3 == Composer.Companion.getEmpty()) {
                                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue3;
                            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                            Modifier modifier4 = modifier3;
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
                            Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                            Updater.set-impl(composer3, measurePolicy, companion.getSetMeasurePolicy());
                            Updater.set-impl(composer3, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer3, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer3, companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer3, modifierMaterializeModifier, companion.getSetModifier());
                            function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            horizontal3 = start2;
                            i17 = i20;
                            i18 = i21;
                            modifier2 = modifier4;
                            composer2 = composerStartRestartGroup;
                            flowColumnOverflow2 = clip;
                            vertical3 = top;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier2 = modifier;
                            horizontal3 = horizontal;
                            vertical3 = vertical2;
                            composer2 = composerStartRestartGroup;
                            i17 = i11;
                            i18 = i2;
                            flowColumnOverflow2 = flowColumnOverflow;
                        }
                        horizontal4 = start;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i11 = i;
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            start2 = horizontal;
                        }
                        if (i19 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z8 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z8 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                        Modifier modifier5 = modifier3;
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
                        Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer4, measurePolicy2, companion2.getSetMeasurePolicy());
                        Updater.set-impl(composer4, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer4, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer4, companion2.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer4, modifierMaterializeModifier2, companion2.getSetModifier());
                        function2CombineAsVirtualLayouts2.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        horizontal3 = start2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier5;
                        composer2 = composerStartRestartGroup;
                        flowColumnOverflow2 = clip;
                        vertical3 = top;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        horizontal3 = horizontal;
                        vertical3 = vertical2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowColumnOverflow2 = flowColumnOverflow;
                    }
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 3072;
                start = horizontal2;
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            start2 = horizontal;
                        }
                        if (i19 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z9 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z9 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            arrayList4.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts3 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                        Modifier modifier6 = modifier3;
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
                        Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer5, measurePolicy3, companion3.getSetMeasurePolicy());
                        Updater.set-impl(composer5, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer5, Integer.valueOf(iHashCode3), companion3.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer5, companion3.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer5, modifierMaterializeModifier3, companion3.getSetModifier());
                        function2CombineAsVirtualLayouts3.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        horizontal3 = start2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier6;
                        composer2 = composerStartRestartGroup;
                        flowColumnOverflow2 = clip;
                        vertical3 = top;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        horizontal3 = horizontal;
                        vertical3 = vertical2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowColumnOverflow2 = flowColumnOverflow;
                    }
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        start2 = horizontal;
                    }
                    if (i19 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z10 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z10 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList6 = new ArrayList();
                        arrayList6.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList6);
                        composerStartRestartGroup.updateRememberedValue(arrayList6);
                        obj = arrayList6;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList7 = new ArrayList();
                        arrayList7.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList7);
                        composerStartRestartGroup.updateRememberedValue(arrayList7);
                        obj = arrayList7;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts4 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
                    Modifier modifier7 = modifier3;
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
                    Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer6, measurePolicy4, companion4.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode4), companion4.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion4.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier4, companion4.getSetModifier());
                    function2CombineAsVirtualLayouts4.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    horizontal3 = start2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier7;
                    composer2 = composerStartRestartGroup;
                    flowColumnOverflow2 = clip;
                    vertical3 = top;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    horizontal3 = horizontal;
                    vertical3 = vertical2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowColumnOverflow2 = flowColumnOverflow;
                }
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 384;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    start = horizontal2;
                    if (composerStartRestartGroup.changed(start)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            start2 = horizontal;
                        }
                        if (i19 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z11 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z11 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            arrayList8.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            arrayList9.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts5 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy5 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
                        Modifier modifier8 = modifier3;
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
                        Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer7, measurePolicy5, companion5.getSetMeasurePolicy());
                        Updater.set-impl(composer7, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer7, Integer.valueOf(iHashCode5), companion5.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer7, companion5.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer7, modifierMaterializeModifier5, companion5.getSetModifier());
                        function2CombineAsVirtualLayouts5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        horizontal3 = start2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier8;
                        composer2 = composerStartRestartGroup;
                        flowColumnOverflow2 = clip;
                        vertical3 = top;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        horizontal3 = horizontal;
                        vertical3 = vertical2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowColumnOverflow2 = flowColumnOverflow;
                    }
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        start2 = horizontal;
                    }
                    if (i19 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z12 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z12 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        arrayList10.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        arrayList11.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts6 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy6 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion6 = ComposeUiNode.Companion;
                    Modifier modifier9 = modifier3;
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
                    Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer8, measurePolicy6, companion6.getSetMeasurePolicy());
                    Updater.set-impl(composer8, currentCompositionLocalMap6, companion6.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer8, Integer.valueOf(iHashCode6), companion6.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer8, companion6.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer8, modifierMaterializeModifier6, companion6.getSetModifier());
                    function2CombineAsVirtualLayouts6.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    horizontal3 = start2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier9;
                    composer2 = composerStartRestartGroup;
                    flowColumnOverflow2 = clip;
                    vertical3 = top;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    horizontal3 = horizontal;
                    vertical3 = vertical2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowColumnOverflow2 = flowColumnOverflow;
                }
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            start = horizontal2;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        start2 = horizontal;
                    }
                    if (i19 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z13 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z13 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        arrayList12.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        arrayList13.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts7 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy7 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion7 = ComposeUiNode.Companion;
                    Modifier modifier10 = modifier3;
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
                    Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer9, measurePolicy7, companion7.getSetMeasurePolicy());
                    Updater.set-impl(composer9, currentCompositionLocalMap7, companion7.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer9, Integer.valueOf(iHashCode7), companion7.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer9, companion7.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer9, modifierMaterializeModifier7, companion7.getSetModifier());
                    function2CombineAsVirtualLayouts7.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    horizontal3 = start2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier10;
                    composer2 = composerStartRestartGroup;
                    flowColumnOverflow2 = clip;
                    vertical3 = top;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    horizontal3 = horizontal;
                    vertical3 = vertical2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowColumnOverflow2 = flowColumnOverflow;
                }
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i19 = i8;
                } else {
                    i19 = i8;
                    start2 = horizontal;
                }
                if (i19 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z14 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z14 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList14 = new ArrayList();
                    arrayList14.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList14);
                    composerStartRestartGroup.updateRememberedValue(arrayList14);
                    obj = arrayList14;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList15 = new ArrayList();
                    arrayList15.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList15);
                    composerStartRestartGroup.updateRememberedValue(arrayList15);
                    obj = arrayList15;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts8 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy8 = (MeasurePolicy) objRememberedValue3;
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion8 = ComposeUiNode.Companion;
                Modifier modifier11 = modifier3;
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
                Composer composer10 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer10, measurePolicy8, companion8.getSetMeasurePolicy());
                Updater.set-impl(composer10, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
                Updater.init-impl(composer10, Integer.valueOf(iHashCode8), companion8.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer10, companion8.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer10, modifierMaterializeModifier8, companion8.getSetModifier());
                function2CombineAsVirtualLayouts8.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                horizontal3 = start2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier11;
                composer2 = composerStartRestartGroup;
                flowColumnOverflow2 = clip;
                vertical3 = top;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                horizontal3 = horizontal;
                vertical3 = vertical2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowColumnOverflow2 = flowColumnOverflow;
            }
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 48;
        vertical2 = vertical;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & 384) == 0) {
                if (composerStartRestartGroup.changed(horizontal)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    start = horizontal2;
                    if (composerStartRestartGroup.changed(start)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            start2 = horizontal;
                        }
                        if (i19 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z15 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z15 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            arrayList16.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            arrayList17.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts9 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy9 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion9 = ComposeUiNode.Companion;
                        Modifier modifier12 = modifier3;
                        constructor = companion9.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer11 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer11, measurePolicy9, companion9.getSetMeasurePolicy());
                        Updater.set-impl(composer11, currentCompositionLocalMap9, companion9.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer11, Integer.valueOf(iHashCode9), companion9.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer11, companion9.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer11, modifierMaterializeModifier9, companion9.getSetModifier());
                        function2CombineAsVirtualLayouts9.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        horizontal3 = start2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier12;
                        composer2 = composerStartRestartGroup;
                        flowColumnOverflow2 = clip;
                        vertical3 = top;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        horizontal3 = horizontal;
                        vertical3 = vertical2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowColumnOverflow2 = flowColumnOverflow;
                    }
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        start2 = horizontal;
                    }
                    if (i19 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z16 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z16 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        arrayList18.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        arrayList19.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts10 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy10 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion10 = ComposeUiNode.Companion;
                    Modifier modifier13 = modifier3;
                    constructor = companion10.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer12 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer12, measurePolicy10, companion10.getSetMeasurePolicy());
                    Updater.set-impl(composer12, currentCompositionLocalMap10, companion10.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer12, Integer.valueOf(iHashCode10), companion10.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer12, companion10.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer12, modifierMaterializeModifier10, companion10.getSetModifier());
                    function2CombineAsVirtualLayouts10.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    horizontal3 = start2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier13;
                    composer2 = composerStartRestartGroup;
                    flowColumnOverflow2 = clip;
                    vertical3 = top;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    horizontal3 = horizontal;
                    vertical3 = vertical2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowColumnOverflow2 = flowColumnOverflow;
                }
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            start = horizontal2;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        start2 = horizontal;
                    }
                    if (i19 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z17 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z17 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        arrayList110.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        arrayList111.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts11 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy11 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion11 = ComposeUiNode.Companion;
                    Modifier modifier14 = modifier3;
                    constructor = companion11.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer13 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer13, measurePolicy11, companion11.getSetMeasurePolicy());
                    Updater.set-impl(composer13, currentCompositionLocalMap11, companion11.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer13, Integer.valueOf(iHashCode11), companion11.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer13, companion11.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer13, modifierMaterializeModifier11, companion11.getSetModifier());
                    function2CombineAsVirtualLayouts11.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    horizontal3 = start2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier14;
                    composer2 = composerStartRestartGroup;
                    flowColumnOverflow2 = clip;
                    vertical3 = top;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    horizontal3 = horizontal;
                    vertical3 = vertical2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowColumnOverflow2 = flowColumnOverflow;
                }
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i19 = i8;
                } else {
                    i19 = i8;
                    start2 = horizontal;
                }
                if (i19 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z18 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z18 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList112 = new ArrayList();
                    arrayList112.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList112);
                    composerStartRestartGroup.updateRememberedValue(arrayList112);
                    obj = arrayList112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList113 = new ArrayList();
                    arrayList113.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList113);
                    composerStartRestartGroup.updateRememberedValue(arrayList113);
                    obj = arrayList113;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts12 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy12 = (MeasurePolicy) objRememberedValue3;
                int iHashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion12 = ComposeUiNode.Companion;
                Modifier modifier15 = modifier3;
                constructor = companion12.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer14 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer14, measurePolicy12, companion12.getSetMeasurePolicy());
                Updater.set-impl(composer14, currentCompositionLocalMap12, companion12.getSetResolvedCompositionLocals());
                Updater.init-impl(composer14, Integer.valueOf(iHashCode12), companion12.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer14, companion12.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer14, modifierMaterializeModifier12, companion12.getSetModifier());
                function2CombineAsVirtualLayouts12.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                horizontal3 = start2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier15;
                composer2 = composerStartRestartGroup;
                flowColumnOverflow2 = clip;
                vertical3 = top;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                horizontal3 = horizontal;
                vertical3 = vertical2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowColumnOverflow2 = flowColumnOverflow;
            }
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 384;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                start = horizontal2;
                if (composerStartRestartGroup.changed(start)) {
                    i9 = 2048;
                } else {
                    i9 = 1024;
                }
                i5 |= i9;
            }
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        start2 = horizontal;
                    }
                    if (i19 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z19 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z19 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        arrayList114.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        arrayList115.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts13 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy13 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion13 = ComposeUiNode.Companion;
                    Modifier modifier16 = modifier3;
                    constructor = companion13.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer15 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer15, measurePolicy13, companion13.getSetMeasurePolicy());
                    Updater.set-impl(composer15, currentCompositionLocalMap13, companion13.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer15, Integer.valueOf(iHashCode13), companion13.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer15, companion13.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer15, modifierMaterializeModifier13, companion13.getSetModifier());
                    function2CombineAsVirtualLayouts13.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    horizontal3 = start2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier16;
                    composer2 = composerStartRestartGroup;
                    flowColumnOverflow2 = clip;
                    vertical3 = top;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    horizontal3 = horizontal;
                    vertical3 = vertical2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowColumnOverflow2 = flowColumnOverflow;
                }
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i19 = i8;
                } else {
                    i19 = i8;
                    start2 = horizontal;
                }
                if (i19 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z110 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z110 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    arrayList116.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    arrayList117.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts14 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy14 = (MeasurePolicy) objRememberedValue3;
                int iHashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion14 = ComposeUiNode.Companion;
                Modifier modifier17 = modifier3;
                constructor = companion14.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer16 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer16, measurePolicy14, companion14.getSetMeasurePolicy());
                Updater.set-impl(composer16, currentCompositionLocalMap14, companion14.getSetResolvedCompositionLocals());
                Updater.init-impl(composer16, Integer.valueOf(iHashCode14), companion14.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer16, companion14.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer16, modifierMaterializeModifier14, companion14.getSetModifier());
                function2CombineAsVirtualLayouts14.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                horizontal3 = start2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier17;
                composer2 = composerStartRestartGroup;
                flowColumnOverflow2 = clip;
                vertical3 = top;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                horizontal3 = horizontal;
                vertical3 = vertical2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowColumnOverflow2 = flowColumnOverflow;
            }
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 3072;
        start = horizontal2;
        i10 = i4 & 16;
        if (i10 != 0) {
            if ((i3 & 24576) == 0) {
                i11 = i;
                if (composerStartRestartGroup.changed(i11)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i5 |= i12;
            }
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i6 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i19 = i8;
                } else {
                    i19 = i8;
                    start2 = horizontal;
                }
                if (i19 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = flowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z111 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z111 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    arrayList118.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    arrayList119.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts15 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy15 = (MeasurePolicy) objRememberedValue3;
                int iHashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion15 = ComposeUiNode.Companion;
                Modifier modifier18 = modifier3;
                constructor = companion15.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer17 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer17, measurePolicy15, companion15.getSetMeasurePolicy());
                Updater.set-impl(composer17, currentCompositionLocalMap15, companion15.getSetResolvedCompositionLocals());
                Updater.init-impl(composer17, Integer.valueOf(iHashCode15), companion15.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer17, companion15.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer17, modifierMaterializeModifier15, companion15.getSetModifier());
                function2CombineAsVirtualLayouts15.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                horizontal3 = start2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier18;
                composer2 = composerStartRestartGroup;
                flowColumnOverflow2 = clip;
                vertical3 = top;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                horizontal3 = horizontal;
                vertical3 = vertical2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowColumnOverflow2 = flowColumnOverflow;
            }
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 24576;
        i11 = i;
        i13 = i4 & 32;
        if (i13 != 0) {
            i5 |= 196608;
        } else if ((i3 & 196608) == 0) {
            if (composerStartRestartGroup.changed(i2)) {
                i14 = 131072;
            } else {
                i14 = 65536;
            }
            i5 |= i14;
        }
        i15 = i4 & 64;
        if (i15 != 0) {
            i5 |= 1572864;
        } else if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(flowColumnOverflow)) {
                i16 = IOUtil.MiB;
            } else {
                i16 = 524288;
            }
            i5 |= i16;
        }
        if ((i3 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i23 = 8388608;
            } else {
                i23 = 4194304;
            }
            i5 |= i23;
        }
        if ((i5 & 4793491) != 4793490) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
            if (i24 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier;
            }
            if (i25 != 0) {
                top = Arrangement.INSTANCE.getTop();
            } else {
                top = vertical2;
            }
            if (i6 != 0) {
                start2 = Arrangement.INSTANCE.getStart();
                i19 = i8;
            } else {
                i19 = i8;
                start2 = horizontal;
            }
            if (i19 != 0) {
                start = Alignment.Companion.getStart();
            }
            if (i10 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i11;
            }
            if (i13 != 0) {
                i21 = Integer.MAX_VALUE;
            } else {
                i21 = i2;
            }
            if (i15 != 0) {
                clip = FlowColumnOverflow.INSTANCE.getClip();
            } else {
                clip = flowColumnOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1944405121, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:213)");
            }
            i22 = 3670016 & i5;
            if (i22 == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            multiContentMeasurePolicyColumnMeasurementMultiContentHelper = columnMeasurementMultiContentHelper(top, start2, start, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
            if (i22 == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            if ((29360128 & i5) == 8388608) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z112 = z4 | z3;
            if ((i5 & 458752) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            z6 = z112 | z5;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                obj = objRememberedValue2;
                ArrayList arrayList1110 = new ArrayList();
                arrayList1110.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }));
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1110);
                composerStartRestartGroup.updateRememberedValue(arrayList1110);
                obj = arrayList1110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111 = new ArrayList();
                arrayList1111.add(ComposableLambdaKt.composableLambdaInstance(-1720407857, true, new Function2() { // from class: aj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.FlowColumn$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }));
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111);
                obj = arrayList1111;
            }
            obj = objRememberedValue2;
            Function2 function2CombineAsVirtualLayouts16 = LayoutKt.combineAsVirtualLayouts((List) obj);
            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyColumnMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MeasurePolicy measurePolicy16 = (MeasurePolicy) objRememberedValue3;
            int iHashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
            ComposeUiNode.Companion companion16 = ComposeUiNode.Companion;
            Modifier modifier19 = modifier3;
            constructor = companion16.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer18 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer18, measurePolicy16, companion16.getSetMeasurePolicy());
            Updater.set-impl(composer18, currentCompositionLocalMap16, companion16.getSetResolvedCompositionLocals());
            Updater.init-impl(composer18, Integer.valueOf(iHashCode16), companion16.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer18, companion16.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer18, modifierMaterializeModifier16, companion16.getSetModifier());
            function2CombineAsVirtualLayouts16.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            horizontal3 = start2;
            i17 = i20;
            i18 = i21;
            modifier2 = modifier19;
            composer2 = composerStartRestartGroup;
            flowColumnOverflow2 = clip;
            vertical3 = top;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            horizontal3 = horizontal;
            vertical3 = vertical2;
            composer2 = composerStartRestartGroup;
            i17 = i11;
            i18 = i2;
            flowColumnOverflow2 = flowColumnOverflow;
        }
        horizontal4 = start;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bj5
                public final Object invoke(Object obj2, Object obj3) {
                    return FlowLayoutKt.c(modifier2, vertical3, horizontal3, horizontal4, i17, i18, flowColumnOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FlowColumn$lambda$1$0(Function3 function3, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1720407857, i, -1, "androidx.compose.foundation.layout.FlowColumn.<anonymous>.<anonymous> (FlowLayout.kt:227)");
            }
            function3.invoke(FlowColumnScopeInstance.INSTANCE, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0122  */
    /* JADX WARN: Code duplicated, block: B:103:0x012e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0130  */
    /* JADX WARN: Code duplicated, block: B:106:0x0133  */
    /* JADX WARN: Code duplicated, block: B:107:0x0135  */
    /* JADX WARN: Code duplicated, block: B:109:0x0139  */
    /* JADX WARN: Code duplicated, block: B:110:0x0140  */
    /* JADX WARN: Code duplicated, block: B:113:0x0148  */
    /* JADX WARN: Code duplicated, block: B:116:0x0158  */
    /* JADX WARN: Code duplicated, block: B:117:0x015a  */
    /* JADX WARN: Code duplicated, block: B:120:0x0161  */
    /* JADX WARN: Code duplicated, block: B:122:0x0169  */
    /* JADX WARN: Code duplicated, block: B:125:0x0186  */
    /* JADX WARN: Code duplicated, block: B:126:0x0188  */
    /* JADX WARN: Code duplicated, block: B:129:0x0190  */
    /* JADX WARN: Code duplicated, block: B:130:0x0192  */
    /* JADX WARN: Code duplicated, block: B:133:0x019b  */
    /* JADX WARN: Code duplicated, block: B:134:0x019d  */
    /* JADX WARN: Code duplicated, block: B:137:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:139:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:142:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:144:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:147:0x0208  */
    /* JADX WARN: Code duplicated, block: B:150:0x0214  */
    /* JADX WARN: Code duplicated, block: B:151:0x0218  */
    /* JADX WARN: Code duplicated, block: B:154:0x025a  */
    /* JADX WARN: Code duplicated, block: B:157:0x0268  */
    /* JADX WARN: Code duplicated, block: B:160:0x027f  */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x005b  */
    /* JADX WARN: Code duplicated, block: B:33:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:55:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:81:0x00df  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:91:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:92:0x0102  */
    /* JADX WARN: Code duplicated, block: B:94:0x0106  */
    /* JADX WARN: Code duplicated, block: B:95:0x010e  */
    /* JADX WARN: Code duplicated, block: B:97:0x0111  */
    /* JADX WARN: Code duplicated, block: B:98:0x011d  */
    @Deprecated(message = "The overflow parameter has been deprecated")
    public static final void FlowRow(Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i, int i2, FlowRowOverflow flowRowOverflow, final Function3<? super FlowRowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        int i5;
        Arrangement.Horizontal horizontal2;
        int i6;
        int i7;
        int i8;
        Alignment.Vertical top;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z;
        final Modifier modifier2;
        final Arrangement.Vertical vertical3;
        final Arrangement.Horizontal horizontal3;
        Composer composer2;
        final int i17;
        final int i18;
        final FlowRowOverflow flowRowOverflow2;
        final Alignment.Vertical vertical4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        Arrangement.Horizontal start;
        int i19;
        Arrangement.Vertical top2;
        int i20;
        int i21;
        FlowRowOverflow clip;
        int i22;
        boolean z2;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        MultiContentMeasurePolicy multiContentMeasurePolicyRowMeasurementMultiContentHelper;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Object objRememberedValue2;
        Object obj;
        boolean zChanged;
        Object objRememberedValue3;
        Function0 constructor;
        int i23;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1956591841);
        int i24 = i4 & 1;
        if (i24 != 0) {
            i5 = i3 | 6;
        } else if ((i3 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i3;
        } else {
            i5 = i3;
        }
        int i25 = i4 & 2;
        if (i25 == 0) {
            if ((i3 & 48) == 0) {
                horizontal2 = horizontal;
                i5 |= composerStartRestartGroup.changed(horizontal2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & 384) == 0) {
                    if (composerStartRestartGroup.changed(vertical)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        top = vertical2;
                        if (composerStartRestartGroup.changed(top)) {
                            i9 = 2048;
                        } else {
                            i9 = 1024;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 16;
                    if (i10 != 0) {
                        if ((i3 & 24576) == 0) {
                            i11 = i;
                            if (composerStartRestartGroup.changed(i11)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i5 |= i12;
                        }
                        i13 = i4 & 32;
                        if (i13 != 0) {
                            i5 |= 196608;
                        } else if ((i3 & 196608) == 0) {
                            if (composerStartRestartGroup.changed(i2)) {
                                i14 = 131072;
                            } else {
                                i14 = 65536;
                            }
                            i5 |= i14;
                        }
                        i15 = i4 & 64;
                        if (i15 != 0) {
                            i5 |= 1572864;
                        } else if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(flowRowOverflow)) {
                                i16 = IOUtil.MiB;
                            } else {
                                i16 = 524288;
                            }
                            i5 |= i16;
                        }
                        if ((i3 & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i23 = 8388608;
                            } else {
                                i23 = 4194304;
                            }
                            i5 |= i23;
                        }
                        if ((i5 & 4793491) != 4793490) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i24 != 0) {
                                modifier3 = Modifier.Companion;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i25 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top2 = Arrangement.INSTANCE.getTop();
                                i19 = i8;
                            } else {
                                i19 = i8;
                                top2 = vertical;
                            }
                            if (i19 != 0) {
                                top = Alignment.Companion.getTop();
                            }
                            if (i10 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i11;
                            }
                            if (i13 != 0) {
                                i21 = Integer.MAX_VALUE;
                            } else {
                                i21 = i2;
                            }
                            if (i15 != 0) {
                                clip = FlowRowOverflow.INSTANCE.getClip();
                            } else {
                                clip = flowRowOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                            }
                            i22 = 3670016 & i5;
                            if (i22 == 1048576) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                            if (i22 == 1048576) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if ((29360128 & i5) == 8388608) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            boolean z7 = z4 | z3;
                            if ((i5 & 458752) == 131072) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            z6 = z7 | z5;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                    public final Object invoke(Object obj2, Object obj3) {
                                        return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                    }
                                }));
                                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            Function2 function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts((List) obj);
                            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged || objRememberedValue3 == Composer.Companion.getEmpty()) {
                                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue3;
                            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                            Modifier modifier4 = modifier3;
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
                            Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                            Updater.set-impl(composer3, measurePolicy, companion.getSetMeasurePolicy());
                            Updater.set-impl(composer3, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer3, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer3, companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer3, modifierMaterializeModifier, companion.getSetModifier());
                            function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            vertical3 = top2;
                            i17 = i20;
                            i18 = i21;
                            modifier2 = modifier4;
                            composer2 = composerStartRestartGroup;
                            flowRowOverflow2 = clip;
                            horizontal3 = start;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier2 = modifier;
                            vertical3 = vertical;
                            horizontal3 = horizontal2;
                            composer2 = composerStartRestartGroup;
                            i17 = i11;
                            i18 = i2;
                            flowRowOverflow2 = flowRowOverflow;
                        }
                        vertical4 = top;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i11 = i;
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            top2 = vertical;
                        }
                        if (i19 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z8 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z8 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                        Modifier modifier5 = modifier3;
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
                        Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer4, measurePolicy2, companion2.getSetMeasurePolicy());
                        Updater.set-impl(composer4, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer4, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer4, companion2.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer4, modifierMaterializeModifier2, companion2.getSetModifier());
                        function2CombineAsVirtualLayouts2.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        vertical3 = top2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier5;
                        composer2 = composerStartRestartGroup;
                        flowRowOverflow2 = clip;
                        horizontal3 = start;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        vertical3 = vertical;
                        horizontal3 = horizontal2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowRowOverflow2 = flowRowOverflow;
                    }
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 3072;
                top = vertical2;
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            top2 = vertical;
                        }
                        if (i19 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z9 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z9 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            arrayList4.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts3 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                        Modifier modifier6 = modifier3;
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
                        Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer5, measurePolicy3, companion3.getSetMeasurePolicy());
                        Updater.set-impl(composer5, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer5, Integer.valueOf(iHashCode3), companion3.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer5, companion3.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer5, modifierMaterializeModifier3, companion3.getSetModifier());
                        function2CombineAsVirtualLayouts3.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        vertical3 = top2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier6;
                        composer2 = composerStartRestartGroup;
                        flowRowOverflow2 = clip;
                        horizontal3 = start;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        vertical3 = vertical;
                        horizontal3 = horizontal2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowRowOverflow2 = flowRowOverflow;
                    }
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        top2 = vertical;
                    }
                    if (i19 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z10 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z10 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList6 = new ArrayList();
                        arrayList6.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList6);
                        composerStartRestartGroup.updateRememberedValue(arrayList6);
                        obj = arrayList6;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList7 = new ArrayList();
                        arrayList7.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList7);
                        composerStartRestartGroup.updateRememberedValue(arrayList7);
                        obj = arrayList7;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts4 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
                    Modifier modifier7 = modifier3;
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
                    Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer6, measurePolicy4, companion4.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode4), companion4.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion4.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier4, companion4.getSetModifier());
                    function2CombineAsVirtualLayouts4.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    vertical3 = top2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier7;
                    composer2 = composerStartRestartGroup;
                    flowRowOverflow2 = clip;
                    horizontal3 = start;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    vertical3 = vertical;
                    horizontal3 = horizontal2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowRowOverflow2 = flowRowOverflow;
                }
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 384;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    top = vertical2;
                    if (composerStartRestartGroup.changed(top)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            top2 = vertical;
                        }
                        if (i19 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z11 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z11 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            arrayList8.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            arrayList9.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts5 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy5 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
                        Modifier modifier8 = modifier3;
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
                        Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer7, measurePolicy5, companion5.getSetMeasurePolicy());
                        Updater.set-impl(composer7, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer7, Integer.valueOf(iHashCode5), companion5.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer7, companion5.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer7, modifierMaterializeModifier5, companion5.getSetModifier());
                        function2CombineAsVirtualLayouts5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        vertical3 = top2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier8;
                        composer2 = composerStartRestartGroup;
                        flowRowOverflow2 = clip;
                        horizontal3 = start;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        vertical3 = vertical;
                        horizontal3 = horizontal2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowRowOverflow2 = flowRowOverflow;
                    }
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        top2 = vertical;
                    }
                    if (i19 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z12 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z12 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        arrayList10.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        arrayList11.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts6 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy6 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion6 = ComposeUiNode.Companion;
                    Modifier modifier9 = modifier3;
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
                    Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer8, measurePolicy6, companion6.getSetMeasurePolicy());
                    Updater.set-impl(composer8, currentCompositionLocalMap6, companion6.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer8, Integer.valueOf(iHashCode6), companion6.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer8, companion6.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer8, modifierMaterializeModifier6, companion6.getSetModifier());
                    function2CombineAsVirtualLayouts6.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    vertical3 = top2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier9;
                    composer2 = composerStartRestartGroup;
                    flowRowOverflow2 = clip;
                    horizontal3 = start;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    vertical3 = vertical;
                    horizontal3 = horizontal2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowRowOverflow2 = flowRowOverflow;
                }
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            top = vertical2;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        top2 = vertical;
                    }
                    if (i19 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z13 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z13 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        arrayList12.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        arrayList13.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts7 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy7 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion7 = ComposeUiNode.Companion;
                    Modifier modifier10 = modifier3;
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
                    Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer9, measurePolicy7, companion7.getSetMeasurePolicy());
                    Updater.set-impl(composer9, currentCompositionLocalMap7, companion7.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer9, Integer.valueOf(iHashCode7), companion7.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer9, companion7.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer9, modifierMaterializeModifier7, companion7.getSetModifier());
                    function2CombineAsVirtualLayouts7.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    vertical3 = top2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier10;
                    composer2 = composerStartRestartGroup;
                    flowRowOverflow2 = clip;
                    horizontal3 = start;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    vertical3 = vertical;
                    horizontal3 = horizontal2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowRowOverflow2 = flowRowOverflow;
                }
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i19 = i8;
                } else {
                    i19 = i8;
                    top2 = vertical;
                }
                if (i19 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z14 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z14 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList14 = new ArrayList();
                    arrayList14.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList14);
                    composerStartRestartGroup.updateRememberedValue(arrayList14);
                    obj = arrayList14;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList15 = new ArrayList();
                    arrayList15.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList15);
                    composerStartRestartGroup.updateRememberedValue(arrayList15);
                    obj = arrayList15;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts8 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy8 = (MeasurePolicy) objRememberedValue3;
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion8 = ComposeUiNode.Companion;
                Modifier modifier11 = modifier3;
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
                Composer composer10 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer10, measurePolicy8, companion8.getSetMeasurePolicy());
                Updater.set-impl(composer10, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
                Updater.init-impl(composer10, Integer.valueOf(iHashCode8), companion8.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer10, companion8.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer10, modifierMaterializeModifier8, companion8.getSetModifier());
                function2CombineAsVirtualLayouts8.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                vertical3 = top2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier11;
                composer2 = composerStartRestartGroup;
                flowRowOverflow2 = clip;
                horizontal3 = start;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                vertical3 = vertical;
                horizontal3 = horizontal2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowRowOverflow2 = flowRowOverflow;
            }
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 48;
        horizontal2 = horizontal;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & 384) == 0) {
                if (composerStartRestartGroup.changed(vertical)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    top = vertical2;
                    if (composerStartRestartGroup.changed(top)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(flowRowOverflow)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i23 = 8388608;
                        } else {
                            i23 = 4194304;
                        }
                        i5 |= i23;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i24 != 0) {
                            modifier3 = Modifier.Companion;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i25 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i19 = i8;
                        } else {
                            i19 = i8;
                            top2 = vertical;
                        }
                        if (i19 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i10 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i11;
                        }
                        if (i13 != 0) {
                            i21 = Integer.MAX_VALUE;
                        } else {
                            i21 = i2;
                        }
                        if (i15 != 0) {
                            clip = FlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = flowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                        }
                        i22 = 3670016 & i5;
                        if (i22 == 1048576) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                        if (i22 == 1048576) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if ((29360128 & i5) == 8388608) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        boolean z15 = z4 | z3;
                        if ((i5 & 458752) == 131072) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        z6 = z15 | z5;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            arrayList16.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            arrayList17.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                                public final Object invoke(Object obj2, Object obj3) {
                                    return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            }));
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        Function2 function2CombineAsVirtualLayouts9 = LayoutKt.combineAsVirtualLayouts((List) obj);
                        zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        MeasurePolicy measurePolicy9 = (MeasurePolicy) objRememberedValue3;
                        int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                        ComposeUiNode.Companion companion9 = ComposeUiNode.Companion;
                        Modifier modifier12 = modifier3;
                        constructor = companion9.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer11 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer11, measurePolicy9, companion9.getSetMeasurePolicy());
                        Updater.set-impl(composer11, currentCompositionLocalMap9, companion9.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer11, Integer.valueOf(iHashCode9), companion9.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer11, companion9.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer11, modifierMaterializeModifier9, companion9.getSetModifier());
                        function2CombineAsVirtualLayouts9.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        vertical3 = top2;
                        i17 = i20;
                        i18 = i21;
                        modifier2 = modifier12;
                        composer2 = composerStartRestartGroup;
                        flowRowOverflow2 = clip;
                        horizontal3 = start;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier2 = modifier;
                        vertical3 = vertical;
                        horizontal3 = horizontal2;
                        composer2 = composerStartRestartGroup;
                        i17 = i11;
                        i18 = i2;
                        flowRowOverflow2 = flowRowOverflow;
                    }
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        top2 = vertical;
                    }
                    if (i19 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z16 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z16 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        arrayList18.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        arrayList19.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts10 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy10 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion10 = ComposeUiNode.Companion;
                    Modifier modifier13 = modifier3;
                    constructor = companion10.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer12 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer12, measurePolicy10, companion10.getSetMeasurePolicy());
                    Updater.set-impl(composer12, currentCompositionLocalMap10, companion10.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer12, Integer.valueOf(iHashCode10), companion10.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer12, companion10.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer12, modifierMaterializeModifier10, companion10.getSetModifier());
                    function2CombineAsVirtualLayouts10.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    vertical3 = top2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier13;
                    composer2 = composerStartRestartGroup;
                    flowRowOverflow2 = clip;
                    horizontal3 = start;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    vertical3 = vertical;
                    horizontal3 = horizontal2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowRowOverflow2 = flowRowOverflow;
                }
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            top = vertical2;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        top2 = vertical;
                    }
                    if (i19 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z17 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z17 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        arrayList110.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        arrayList111.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts11 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy11 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion11 = ComposeUiNode.Companion;
                    Modifier modifier14 = modifier3;
                    constructor = companion11.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer13 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer13, measurePolicy11, companion11.getSetMeasurePolicy());
                    Updater.set-impl(composer13, currentCompositionLocalMap11, companion11.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer13, Integer.valueOf(iHashCode11), companion11.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer13, companion11.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer13, modifierMaterializeModifier11, companion11.getSetModifier());
                    function2CombineAsVirtualLayouts11.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    vertical3 = top2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier14;
                    composer2 = composerStartRestartGroup;
                    flowRowOverflow2 = clip;
                    horizontal3 = start;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    vertical3 = vertical;
                    horizontal3 = horizontal2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowRowOverflow2 = flowRowOverflow;
                }
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i19 = i8;
                } else {
                    i19 = i8;
                    top2 = vertical;
                }
                if (i19 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z18 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z18 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList112 = new ArrayList();
                    arrayList112.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList112);
                    composerStartRestartGroup.updateRememberedValue(arrayList112);
                    obj = arrayList112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList113 = new ArrayList();
                    arrayList113.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList113);
                    composerStartRestartGroup.updateRememberedValue(arrayList113);
                    obj = arrayList113;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts12 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy12 = (MeasurePolicy) objRememberedValue3;
                int iHashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion12 = ComposeUiNode.Companion;
                Modifier modifier15 = modifier3;
                constructor = companion12.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer14 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer14, measurePolicy12, companion12.getSetMeasurePolicy());
                Updater.set-impl(composer14, currentCompositionLocalMap12, companion12.getSetResolvedCompositionLocals());
                Updater.init-impl(composer14, Integer.valueOf(iHashCode12), companion12.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer14, companion12.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer14, modifierMaterializeModifier12, companion12.getSetModifier());
                function2CombineAsVirtualLayouts12.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                vertical3 = top2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier15;
                composer2 = composerStartRestartGroup;
                flowRowOverflow2 = clip;
                horizontal3 = start;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                vertical3 = vertical;
                horizontal3 = horizontal2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowRowOverflow2 = flowRowOverflow;
            }
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 384;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                top = vertical2;
                if (composerStartRestartGroup.changed(top)) {
                    i9 = 2048;
                } else {
                    i9 = 1024;
                }
                i5 |= i9;
            }
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(flowRowOverflow)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i23 = 8388608;
                    } else {
                        i23 = 4194304;
                    }
                    i5 |= i23;
                }
                if ((i5 & 4793491) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i24 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i25 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i19 = i8;
                    } else {
                        i19 = i8;
                        top2 = vertical;
                    }
                    if (i19 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i10 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i11;
                    }
                    if (i13 != 0) {
                        i21 = Integer.MAX_VALUE;
                    } else {
                        i21 = i2;
                    }
                    if (i15 != 0) {
                        clip = FlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = flowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                    }
                    i22 = 3670016 & i5;
                    if (i22 == 1048576) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                    if (i22 == 1048576) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((29360128 & i5) == 8388608) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    boolean z19 = z4 | z3;
                    if ((i5 & 458752) == 131072) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    z6 = z19 | z5;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        arrayList114.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        arrayList115.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                            public final Object invoke(Object obj2, Object obj3) {
                                return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }));
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    Function2 function2CombineAsVirtualLayouts13 = LayoutKt.combineAsVirtualLayouts((List) obj);
                    zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    MeasurePolicy measurePolicy13 = (MeasurePolicy) objRememberedValue3;
                    int iHashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion13 = ComposeUiNode.Companion;
                    Modifier modifier16 = modifier3;
                    constructor = companion13.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer15 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer15, measurePolicy13, companion13.getSetMeasurePolicy());
                    Updater.set-impl(composer15, currentCompositionLocalMap13, companion13.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer15, Integer.valueOf(iHashCode13), companion13.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer15, companion13.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer15, modifierMaterializeModifier13, companion13.getSetModifier());
                    function2CombineAsVirtualLayouts13.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    vertical3 = top2;
                    i17 = i20;
                    i18 = i21;
                    modifier2 = modifier16;
                    composer2 = composerStartRestartGroup;
                    flowRowOverflow2 = clip;
                    horizontal3 = start;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier2 = modifier;
                    vertical3 = vertical;
                    horizontal3 = horizontal2;
                    composer2 = composerStartRestartGroup;
                    i17 = i11;
                    i18 = i2;
                    flowRowOverflow2 = flowRowOverflow;
                }
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i19 = i8;
                } else {
                    i19 = i8;
                    top2 = vertical;
                }
                if (i19 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z110 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z110 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    arrayList116.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    arrayList117.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts14 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy14 = (MeasurePolicy) objRememberedValue3;
                int iHashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion14 = ComposeUiNode.Companion;
                Modifier modifier17 = modifier3;
                constructor = companion14.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer16 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer16, measurePolicy14, companion14.getSetMeasurePolicy());
                Updater.set-impl(composer16, currentCompositionLocalMap14, companion14.getSetResolvedCompositionLocals());
                Updater.init-impl(composer16, Integer.valueOf(iHashCode14), companion14.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer16, companion14.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer16, modifierMaterializeModifier14, companion14.getSetModifier());
                function2CombineAsVirtualLayouts14.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                vertical3 = top2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier17;
                composer2 = composerStartRestartGroup;
                flowRowOverflow2 = clip;
                horizontal3 = start;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                vertical3 = vertical;
                horizontal3 = horizontal2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowRowOverflow2 = flowRowOverflow;
            }
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 3072;
        top = vertical2;
        i10 = i4 & 16;
        if (i10 != 0) {
            if ((i3 & 24576) == 0) {
                i11 = i;
                if (composerStartRestartGroup.changed(i11)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i5 |= i12;
            }
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(flowRowOverflow)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i23 = 8388608;
                } else {
                    i23 = 4194304;
                }
                i5 |= i23;
            }
            if ((i5 & 4793491) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i24 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (i25 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i19 = i8;
                } else {
                    i19 = i8;
                    top2 = vertical;
                }
                if (i19 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i10 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i11;
                }
                if (i13 != 0) {
                    i21 = Integer.MAX_VALUE;
                } else {
                    i21 = i2;
                }
                if (i15 != 0) {
                    clip = FlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = flowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
                }
                i22 = 3670016 & i5;
                if (i22 == 1048576) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
                if (i22 == 1048576) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((29360128 & i5) == 8388608) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z111 = z4 | z3;
                if ((i5 & 458752) == 131072) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                z6 = z111 | z5;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    arrayList118.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    arrayList119.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                        public final Object invoke(Object obj2, Object obj3) {
                            return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }));
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                Function2 function2CombineAsVirtualLayouts15 = LayoutKt.combineAsVirtualLayouts((List) obj);
                zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                MeasurePolicy measurePolicy15 = (MeasurePolicy) objRememberedValue3;
                int iHashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                ComposeUiNode.Companion companion15 = ComposeUiNode.Companion;
                Modifier modifier18 = modifier3;
                constructor = companion15.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer17 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer17, measurePolicy15, companion15.getSetMeasurePolicy());
                Updater.set-impl(composer17, currentCompositionLocalMap15, companion15.getSetResolvedCompositionLocals());
                Updater.init-impl(composer17, Integer.valueOf(iHashCode15), companion15.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer17, companion15.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer17, modifierMaterializeModifier15, companion15.getSetModifier());
                function2CombineAsVirtualLayouts15.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                vertical3 = top2;
                i17 = i20;
                i18 = i21;
                modifier2 = modifier18;
                composer2 = composerStartRestartGroup;
                flowRowOverflow2 = clip;
                horizontal3 = start;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier2 = modifier;
                vertical3 = vertical;
                horizontal3 = horizontal2;
                composer2 = composerStartRestartGroup;
                i17 = i11;
                i18 = i2;
                flowRowOverflow2 = flowRowOverflow;
            }
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i5 |= 24576;
        i11 = i;
        i13 = i4 & 32;
        if (i13 != 0) {
            i5 |= 196608;
        } else if ((i3 & 196608) == 0) {
            if (composerStartRestartGroup.changed(i2)) {
                i14 = 131072;
            } else {
                i14 = 65536;
            }
            i5 |= i14;
        }
        i15 = i4 & 64;
        if (i15 != 0) {
            i5 |= 1572864;
        } else if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(flowRowOverflow)) {
                i16 = IOUtil.MiB;
            } else {
                i16 = 524288;
            }
            i5 |= i16;
        }
        if ((i3 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i23 = 8388608;
            } else {
                i23 = 4194304;
            }
            i5 |= i23;
        }
        if ((i5 & 4793491) != 4793490) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
            if (i24 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier;
            }
            if (i25 != 0) {
                start = Arrangement.INSTANCE.getStart();
            } else {
                start = horizontal2;
            }
            if (i6 != 0) {
                top2 = Arrangement.INSTANCE.getTop();
                i19 = i8;
            } else {
                i19 = i8;
                top2 = vertical;
            }
            if (i19 != 0) {
                top = Alignment.Companion.getTop();
            }
            if (i10 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i11;
            }
            if (i13 != 0) {
                i21 = Integer.MAX_VALUE;
            } else {
                i21 = i2;
            }
            if (i15 != 0) {
                clip = FlowRowOverflow.INSTANCE.getClip();
            } else {
                clip = flowRowOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1956591841, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:99)");
            }
            i22 = 3670016 & i5;
            if (i22 == 1048576) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            multiContentMeasurePolicyRowMeasurementMultiContentHelper = rowMeasurementMultiContentHelper(start, top2, top, i20, i21, flowLayoutOverflowState, composerStartRestartGroup, (i5 >> 3) & 65534);
            if (i22 == 1048576) {
                z3 = true;
            } else {
                z3 = false;
            }
            if ((29360128 & i5) == 8388608) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z112 = z4 | z3;
            if ((i5 & 458752) == 131072) {
                z5 = true;
            } else {
                z5 = false;
            }
            z6 = z112 | z5;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                obj = objRememberedValue2;
                ArrayList arrayList1110 = new ArrayList();
                arrayList1110.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }));
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1110);
                composerStartRestartGroup.updateRememberedValue(arrayList1110);
                obj = arrayList1110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111 = new ArrayList();
                arrayList1111.add(ComposableLambdaKt.composableLambdaInstance(-1192950673, true, new Function2() { // from class: gj5
                    public final Object invoke(Object obj2, Object obj3) {
                        return FlowLayoutKt.FlowRow$lambda$1$0(function3, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }));
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111);
                obj = arrayList1111;
            }
            obj = objRememberedValue2;
            Function2 function2CombineAsVirtualLayouts16 = LayoutKt.combineAsVirtualLayouts((List) obj);
            zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicyRowMeasurementMultiContentHelper);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MeasurePolicy measurePolicy16 = (MeasurePolicy) objRememberedValue3;
            int iHashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
            ComposeUiNode.Companion companion16 = ComposeUiNode.Companion;
            Modifier modifier19 = modifier3;
            constructor = companion16.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer18 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer18, measurePolicy16, companion16.getSetMeasurePolicy());
            Updater.set-impl(composer18, currentCompositionLocalMap16, companion16.getSetResolvedCompositionLocals());
            Updater.init-impl(composer18, Integer.valueOf(iHashCode16), companion16.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer18, companion16.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer18, modifierMaterializeModifier16, companion16.getSetModifier());
            function2CombineAsVirtualLayouts16.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            vertical3 = top2;
            i17 = i20;
            i18 = i21;
            modifier2 = modifier19;
            composer2 = composerStartRestartGroup;
            flowRowOverflow2 = clip;
            horizontal3 = start;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            vertical3 = vertical;
            horizontal3 = horizontal2;
            composer2 = composerStartRestartGroup;
            i17 = i11;
            i18 = i2;
            flowRowOverflow2 = flowRowOverflow;
        }
        vertical4 = top;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hj5
                public final Object invoke(Object obj2, Object obj3) {
                    return FlowLayoutKt.i(modifier2, horizontal3, vertical3, vertical4, i17, i18, flowRowOverflow2, function3, i3, i4, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FlowRow$lambda$1$0(Function3 function3, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1192950673, i, -1, "androidx.compose.foundation.layout.FlowRow.<anonymous>.<anonymous> (FlowLayout.kt:113)");
            }
            function3.invoke(FlowRowScopeInstance.INSTANCE, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: breakDownItems-di9J0FM, reason: not valid java name */
    public static final MeasureResult m839breakDownItemsdi9J0FM(MeasureScope measureScope, FlowLineMeasurePolicy flowLineMeasurePolicy, Iterator<? extends Measurable> it, float f, float f2, long j, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i3;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo;
        MutableIntObjectMap mutableIntObjectMap;
        int i4;
        int i5;
        int height;
        int width;
        MutableIntObjectMap mutableIntObjectMap2;
        IntIntPair intIntPairM21boximpl;
        int i6;
        MutableIntList mutableIntList;
        MutableIntList mutableIntList2;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo2;
        int i7;
        int i8;
        FlowLayoutData flowLayoutData;
        MeasureScope measureScope2 = measureScope;
        FlowLineMeasurePolicy flowLineMeasurePolicy2 = flowLineMeasurePolicy;
        Iterator<? extends Measurable> it2 = it;
        MutableVector mutableVector = new MutableVector(new MeasureResult[16], 0);
        int i9 = Constraints.getMaxWidth-impl(j);
        int i10 = Constraints.getMinWidth-impl(j);
        int i11 = Constraints.getMaxHeight-impl(j);
        MutableIntObjectMap mutableIntObjectMapMutableIntObjectMapOf = IntObjectMapKt.mutableIntObjectMapOf();
        ArrayList arrayList = new ArrayList();
        int iCeil = (int) Math.ceil(measureScope2.toPx-0680j_4(f));
        int iCeil2 = (int) Math.ceil(measureScope2.toPx-0680j_4(f2));
        long jM896constructorimpl = OrientationIndependentConstraints.m896constructorimpl(0, i9, 0, i11);
        long jM911toBoxConstraintsOenEA2s = OrientationIndependentConstraints.m911toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m900copyyUG9Ft0$default(jM896constructorimpl, 0, 0, 0, 0, 14, null), flowLineMeasurePolicy2.isHorizontal() ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FlowLineInfo flowLineInfo = it2 instanceof ContextualFlowItemIterator ? new FlowLineInfo(0, 0, measureScope2.toDp-u2uoSUM(i9), measureScope2.toDp-u2uoSUM(i11), null) : null;
        Measurable measurableSafeNext = !it2.hasNext() ? null : safeNext(it2, flowLineInfo);
        IntIntPair intIntPairM21boximpl2 = measurableSafeNext != null ? IntIntPair.m21boximpl(m840measureAndCacherqJ1uqs(measurableSafeNext, flowLineMeasurePolicy2, jM911toBoxConstraintsOenEA2s, new Function1() { // from class: ej5
            public final Object invoke(Object obj) {
                return FlowLayoutKt.breakDownItems_di9J0FM$lambda$0$0(objectRef, (Placeable) obj);
            }
        })) : null;
        Integer numValueOf = intIntPairM21boximpl2 != null ? Integer.valueOf(IntIntPair.m28getFirstimpl(intIntPairM21boximpl2.getPackedValue())) : null;
        Integer numValueOf2 = intIntPairM21boximpl2 != null ? Integer.valueOf(IntIntPair.m29getSecondimpl(intIntPairM21boximpl2.getPackedValue())) : null;
        Integer num = numValueOf;
        Measurable measurable = measurableSafeNext;
        MutableIntList mutableIntList3 = new MutableIntList(0, 1, null);
        MutableIntList mutableIntList4 = new MutableIntList(0, 1, null);
        MutableIntSet mutableIntSetMutableIntSetOf = IntSetKt.mutableIntSetOf();
        FlowLineInfo flowLineInfo2 = flowLineInfo;
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i, flowLayoutOverflowState, j, i2, iCeil, iCeil2, null);
        int i12 = iCeil;
        FlowLayoutBuildingBlocks.WrapInfo wrapInfoM837getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(it2.hasNext(), 0, IntIntPair.m24constructorimpl(i9, i11), intIntPairM21boximpl2, 0, 0, 0, false, false);
        if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInContainer()) {
            wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM837getWrapInfoOpUlnko, intIntPairM21boximpl2 != null, -1, 0, i9, 0);
            i3 = i9;
        } else {
            i3 = i9;
            wrapEllipsisInfo = null;
        }
        Integer numValueOf3 = num;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo3 = wrapEllipsisInfo;
        MutableIntList mutableIntList5 = mutableIntList3;
        int i13 = 0;
        int i14 = 0;
        boolean z = false;
        int i15 = 0;
        FlowLayoutBuildingBlocks.WrapInfo wrapInfo = wrapInfoM837getWrapInfoOpUlnko;
        Measurable measurableSafeNext2 = measurable;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = i3;
        int i20 = i10;
        MutableIntSet mutableIntSet = mutableIntSetMutableIntSetOf;
        int i21 = i11;
        while (!wrapInfo.getIsLastItemInContainer() && measurableSafeNext2 != null) {
            numValueOf3.getClass();
            int iIntValue = numValueOf3.intValue();
            numValueOf2.getClass();
            MutableIntList mutableIntList6 = mutableIntList4;
            int i22 = i3;
            int i23 = i14 + iIntValue;
            int iMax = Math.max(i17, numValueOf2.intValue());
            int i24 = i19 - iIntValue;
            int i25 = i13 + 1;
            int i26 = i20;
            flowLayoutOverflowState.setItemShown$foundation_layout(i25);
            arrayList.add(measurableSafeNext2);
            mutableIntObjectMapMutableIntObjectMapOf.set(i13, objectRef.element);
            Object parentData = measurableSafeNext2.getParentData();
            RowColumnParentData rowColumnParentData = parentData instanceof RowColumnParentData ? (RowColumnParentData) parentData : null;
            if (((rowColumnParentData == null || (flowLayoutData = rowColumnParentData.getFlowLayoutData()) == null) ? null : Float.valueOf(flowLayoutData.getFillCrossAxisFraction())) != null) {
                z = true;
            }
            int i27 = i25 - i18;
            boolean z2 = i27 < i;
            if (flowLineInfo2 != null) {
                int i28 = z2 ? i16 : i16 + 1;
                int i29 = z2 ? i27 : 0;
                if (z2) {
                    int i30 = i24 - i12;
                    i7 = i30 < 0 ? 0 : i30;
                } else {
                    i7 = i22;
                }
                float f3 = measureScope2.toDp-u2uoSUM(i7);
                if (z2) {
                    mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
                    i8 = i21;
                } else {
                    int i31 = (i21 - iMax) - iCeil2;
                    mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
                    i8 = i31 < 0 ? 0 : i31;
                }
                flowLineInfo2.m852update4j6BHR0$foundation_layout(i28, i29, f3, measureScope2.toDp-u2uoSUM(i8));
                Unit unit = Unit.INSTANCE;
            } else {
                i27 = i27;
                mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
            }
            measurableSafeNext2 = !it2.hasNext() ? null : safeNext(it2, flowLineInfo2);
            objectRef.element = null;
            IntIntPair intIntPairM21boximpl3 = measurableSafeNext2 != null ? IntIntPair.m21boximpl(m840measureAndCacherqJ1uqs(measurableSafeNext2, flowLineMeasurePolicy2, jM911toBoxConstraintsOenEA2s, new Function1() { // from class: fj5
                public final Object invoke(Object obj) {
                    return FlowLayoutKt.breakDownItems_di9J0FM$lambda$2$0(objectRef, (Placeable) obj);
                }
            })) : null;
            Integer numValueOf4 = intIntPairM21boximpl3 != null ? Integer.valueOf(IntIntPair.m28getFirstimpl(intIntPairM21boximpl3.getPackedValue()) + i12) : null;
            numValueOf2 = intIntPairM21boximpl3 != null ? Integer.valueOf(IntIntPair.m29getSecondimpl(intIntPairM21boximpl3.getPackedValue())) : null;
            boolean zHasNext = it2.hasNext();
            int i32 = i16;
            long jM24constructorimpl = IntIntPair.m24constructorimpl(i24, i21);
            if (intIntPairM21boximpl3 == null) {
                intIntPairM21boximpl = null;
            } else {
                numValueOf4.getClass();
                int iIntValue2 = numValueOf4.intValue();
                numValueOf2.getClass();
                intIntPairM21boximpl = IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iIntValue2, numValueOf2.intValue()));
            }
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM837getWrapInfoOpUlnko2 = flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(zHasNext, i27, jM24constructorimpl, intIntPairM21boximpl, i32, i15, iMax, false, false);
            if (wrapInfoM837getWrapInfoOpUlnko2.getIsLastItemInLine()) {
                int iMin = Math.min(Math.max(i26, i23), i22);
                int i33 = i15 + iMax;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo4 = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM837getWrapInfoOpUlnko2, intIntPairM21boximpl3 != null, i32, i33, i24, i27);
                mutableIntList = mutableIntList6;
                mutableIntList.add(iMax);
                MutableIntSet mutableIntSet2 = mutableIntSet;
                if (z) {
                    mutableIntSet2.plusAssign(i32);
                }
                int i34 = (i11 - i33) - iCeil2;
                mutableIntSet = mutableIntSet2;
                mutableIntList2 = mutableIntList5;
                mutableIntList2.add(i25);
                i16 = i32 + 1;
                i15 = i33 + iCeil2;
                i22 = i22;
                i18 = i25;
                numValueOf3 = numValueOf4 != null ? Integer.valueOf(numValueOf4.intValue() - i12) : null;
                i23 = 0;
                z = false;
                i6 = 0;
                i20 = iMin;
                wrapEllipsisInfo2 = wrapEllipsisInfo4;
                i21 = i34;
                i19 = i22;
            } else {
                i6 = iMax;
                mutableIntList = mutableIntList6;
                mutableIntList2 = mutableIntList5;
                numValueOf3 = numValueOf4;
                i19 = i24;
                i16 = i32;
                i20 = i26;
                wrapEllipsisInfo2 = wrapEllipsisInfo3;
            }
            mutableIntList5 = mutableIntList2;
            wrapEllipsisInfo3 = wrapEllipsisInfo2;
            mutableIntSet = mutableIntSet;
            i13 = i25;
            wrapInfo = wrapInfoM837getWrapInfoOpUlnko2;
            i17 = i6;
            it2 = it;
            mutableIntList4 = mutableIntList;
            mutableIntObjectMapMutableIntObjectMapOf = mutableIntObjectMap2;
            i14 = i23;
            i3 = i22;
        }
        MutableIntObjectMap mutableIntObjectMap3 = mutableIntObjectMapMutableIntObjectMapOf;
        MutableIntList mutableIntList7 = mutableIntList4;
        int i35 = i20;
        MutableIntList mutableIntList8 = mutableIntList5;
        MutableIntSet mutableIntSet3 = mutableIntSet;
        if (wrapEllipsisInfo3 != null) {
            arrayList.add(wrapEllipsisInfo3.getEllipsis());
            mutableIntObjectMap = mutableIntObjectMap3;
            mutableIntObjectMap.set(arrayList.size() - 1, wrapEllipsisInfo3.getPlaceable());
            int i36 = mutableIntList8._size - 1;
            if (wrapEllipsisInfo3.getPlaceEllipsisOnLastContentLine()) {
                int i37 = mutableIntList8._size - 1;
                mutableIntList7.set(i36, Math.max(mutableIntList7.get(i36), IntIntPair.m29getSecondimpl(wrapEllipsisInfo3.getEllipsisSize())));
                mutableIntList8.set(i37, mutableIntList8.last() + 1);
                Unit unit2 = Unit.INSTANCE;
            } else {
                mutableIntList7.add(IntIntPair.m29getSecondimpl(wrapEllipsisInfo3.getEllipsisSize()));
                mutableIntList8.add(mutableIntList8.last() + 1);
            }
        } else {
            mutableIntObjectMap = mutableIntObjectMap3;
        }
        int size = arrayList.size();
        Placeable[] placeableArr = new Placeable[size];
        for (int i38 = 0; i38 < size; i38++) {
            placeableArr[i38] = mutableIntObjectMap.get(i38);
        }
        int i39 = mutableIntList8._size;
        int[] iArr = new int[i39];
        int[] iArr2 = new int[i39];
        int[] iArr3 = mutableIntList8.content;
        int iMax2 = i35;
        int i40 = 0;
        int i41 = 0;
        int i42 = 0;
        while (i41 < i39) {
            int i43 = iArr3[i41];
            int i44 = mutableIntList7.get(i41);
            if (!mutableIntSet3.contains(i41)) {
                i44 = Constraints.getMaxHeight-impl(jM896constructorimpl) == Integer.MAX_VALUE ? Integer.MAX_VALUE : Constraints.getMaxHeight-impl(jM896constructorimpl) - i42;
            }
            MutableIntSet mutableIntSet4 = mutableIntSet3;
            MutableIntList mutableIntList9 = mutableIntList7;
            int i45 = i44;
            FlowLineMeasurePolicy flowLineMeasurePolicy3 = flowLineMeasurePolicy2;
            ArrayList arrayList2 = arrayList;
            int i46 = i12;
            MeasureResult measureResultMeasure = RowColumnMeasurePolicyKt.measure(flowLineMeasurePolicy3, iMax2, Constraints.getMinHeight-impl(jM896constructorimpl), Constraints.getMaxWidth-impl(jM896constructorimpl), i45, i46, measureScope2, arrayList2, placeableArr, i40, i43, iArr, i41);
            if (flowLineMeasurePolicy.isHorizontal()) {
                height = measureResultMeasure.getWidth();
                width = measureResultMeasure.getHeight();
            } else {
                height = measureResultMeasure.getHeight();
                width = measureResultMeasure.getWidth();
            }
            iArr2[i41] = width;
            i42 += width;
            iMax2 = Math.max(iMax2, height);
            mutableVector.add(measureResultMeasure);
            i41++;
            arrayList = arrayList2;
            i40 = i43;
            mutableIntList7 = mutableIntList9;
            i12 = i46;
            mutableIntSet3 = mutableIntSet4;
            measureScope2 = measureScope;
            flowLineMeasurePolicy2 = flowLineMeasurePolicy;
        }
        if (mutableVector.getSize() == 0) {
            i4 = 0;
            i5 = 0;
        } else {
            i4 = iMax2;
            i5 = i42;
        }
        return m841placeHelperBmaY500(measureScope, j, i4, i5, iArr2, mutableVector, flowLineMeasurePolicy, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit breakDownItems_di9J0FM$lambda$0$0(Ref.ObjectRef objectRef, Placeable placeable) {
        objectRef.element = placeable;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit breakDownItems_di9J0FM$lambda$2$0(Ref.ObjectRef objectRef, Placeable placeable) {
        objectRef.element = placeable;
        return Unit.INSTANCE;
    }

    public static Unit c(Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, FlowColumnOverflow flowColumnOverflow, Function3 function3, int i3, int i4, Composer composer, int i5) {
        FlowColumn(modifier, vertical, horizontal, horizontal2, i, i2, flowColumnOverflow, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    public static final MeasurePolicy columnMeasurementHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2013098357, i2, -1, "androidx.compose.foundation.layout.columnMeasurementHelper (FlowLayout.kt:500)");
        }
        boolean z = ((((i2 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(i)) || (i2 & 384) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            final FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(false, horizontal, vertical, vertical.getSpacing(), CROSS_AXIS_ALIGNMENT_START, horizontal.getSpacing(), i, Integer.MAX_VALUE, FlowRowOverflow.INSTANCE.getVisible().createOverflowState$foundation_layout(), null);
            objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$columnMeasurementHelper$1$1
                /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
                public final MeasureResult m842measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                    return flowMeasurePolicy.m862measure3p2s80s(measureScope, CollectionsKt.listOf(list), j);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return measurePolicy;
    }

    public static final MultiContentMeasurePolicy columnMeasurementMultiContentHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, Composer composer, int i3) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-308635847, i3, -1, "androidx.compose.foundation.layout.columnMeasurementMultiContentHelper (FlowLayout.kt:528)");
        }
        boolean zChanged = ((((i3 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i3 & 48) == 32) | ((((i3 & 896) ^ 384) > 256 && composer.changed(horizontal2)) || (i3 & 384) == 256) | ((((i3 & 7168) ^ 3072) > 2048 && composer.changed(i)) || (i3 & 3072) == 2048) | ((((57344 & i3) ^ 24576) > 16384 && composer.changed(i2)) || (i3 & 24576) == 16384) | composer.changed(flowLayoutOverflowState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(false, horizontal, vertical, vertical.getSpacing(), CrossAxisAlignment.INSTANCE.horizontal$foundation_layout(horizontal2), horizontal.getSpacing(), i, i2, flowLayoutOverflowState, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return flowMeasurePolicy2;
    }

    public static final int crossAxisMin(IntrinsicMeasurable intrinsicMeasurable, boolean z, int i) {
        return z ? intrinsicMeasurable.minIntrinsicHeight(i) : intrinsicMeasurable.minIntrinsicWidth(i);
    }

    public static Unit e(MutableVector mutableVector, Placeable.PlacementScope placementScope) {
        Object[] objArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            ((MeasureResult) objArr[i]).placeChildren();
        }
        return Unit.INSTANCE;
    }

    public static Unit g(Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i, int i2, Function3 function3, int i3, int i4, Composer composer, int i5) {
        FlowRow(modifier, horizontal, vertical, vertical2, i, i2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_START() {
        return CROSS_AXIS_ALIGNMENT_START;
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_TOP() {
        return CROSS_AXIS_ALIGNMENT_TOP;
    }

    public static Unit h(Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, Function3 function3, int i3, int i4, Composer composer, int i5) {
        FlowColumn(modifier, vertical, horizontal, horizontal2, i, i2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    public static Unit i(Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i, int i2, FlowRowOverflow flowRowOverflow, Function3 function3, int i3, int i4, Composer composer, int i5) {
        FlowRow(modifier, horizontal, vertical, vertical2, i, i2, flowRowOverflow, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    private static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function4, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i6;
        if (list.isEmpty()) {
            return IntIntPair.m24constructorimpl(0, 0);
        }
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i4, flowLayoutOverflowState, OrientationIndependentConstraints.m896constructorimpl(0, i, 0, Integer.MAX_VALUE), i5, i2, i3, null);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, 0);
        int iIntValue = intrinsicMeasurable != null ? ((Number) function4.invoke(intrinsicMeasurable, 0, Integer.valueOf(i))).intValue() : 0;
        int iIntValue2 = intrinsicMeasurable != null ? ((Number) function3.invoke(intrinsicMeasurable, 0, Integer.valueOf(iIntValue))).intValue() : 0;
        int i7 = 0;
        int iMax = 0;
        if (flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(list.size() > 1, 0, IntIntPair.m24constructorimpl(i, Integer.MAX_VALUE), intrinsicMeasurable == null ? null : IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iIntValue2, iIntValue)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
            IntIntPair intIntPairM844ellipsisSizeF35zmw$foundation_layout = flowLayoutOverflowState.m844ellipsisSizeF35zmw$foundation_layout(intrinsicMeasurable != null, 0, 0);
            return IntIntPair.m24constructorimpl(intIntPairM844ellipsisSizeF35zmw$foundation_layout != null ? IntIntPair.m29getSecondimpl(intIntPairM844ellipsisSizeF35zmw$foundation_layout.getPackedValue()) : 0, 0);
        }
        int size = list.size();
        int i8 = i;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int i13 = iMax;
            if (i9 >= size) {
                i6 = i10;
                break;
            }
            int i14 = i8 - iIntValue2;
            int i15 = i9 + 1;
            iMax = Math.max(i13, iIntValue);
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, i15);
            int iIntValue3 = intrinsicMeasurable2 != null ? ((Number) function4.invoke(intrinsicMeasurable2, Integer.valueOf(i15), Integer.valueOf(i))).intValue() : 0;
            int iIntValue4 = intrinsicMeasurable2 != null ? ((Number) function3.invoke(intrinsicMeasurable2, Integer.valueOf(i15), Integer.valueOf(iIntValue3))).intValue() + i2 : 0;
            int i16 = i15 - i11;
            i6 = i15;
            int i17 = i12;
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM837getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(i9 + 2 < list.size(), i16, IntIntPair.m24constructorimpl(i14, Integer.MAX_VALUE), intrinsicMeasurable2 == null ? null : IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iIntValue4, iIntValue3)), i17, i7, iMax, false, false);
            if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInLine()) {
                int iM29getSecondimpl = i7 + iMax + i3;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM837getWrapInfoOpUlnko, intrinsicMeasurable2 != null, i17, iM29getSecondimpl, i14, i16);
                iIntValue4 -= i2;
                i12 = i17 + 1;
                if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInContainer()) {
                    if (wrapEllipsisInfo != null) {
                        long ellipsisSize = wrapEllipsisInfo.getEllipsisSize();
                        if (!wrapEllipsisInfo.getPlaceEllipsisOnLastContentLine()) {
                            iM29getSecondimpl += IntIntPair.m29getSecondimpl(ellipsisSize) + i3;
                        }
                    }
                    i7 = iM29getSecondimpl;
                    break;
                }
                i8 = i;
                i11 = i6;
                i7 = iM29getSecondimpl;
                iMax = 0;
            } else {
                i8 = i14;
                i12 = i17;
            }
            iIntValue2 = iIntValue4;
            iIntValue = iIntValue3;
            i9 = i6;
            i10 = i9;
        }
        return IntIntPair.m24constructorimpl(i7 - i3, i6);
    }

    public static final int mainAxisMin(IntrinsicMeasurable intrinsicMeasurable, boolean z, int i) {
        return z ? intrinsicMeasurable.minIntrinsicWidth(i) : intrinsicMeasurable.minIntrinsicHeight(i);
    }

    private static final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, int i, int i2, int i3) {
        int size = list.size();
        int i4 = 0;
        int iMax = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < size) {
            int iIntValue = ((Number) function3.invoke(list.get(i4), Integer.valueOf(i4), Integer.valueOf(i))).intValue() + i2;
            int i7 = i4 + 1;
            if (i7 - i5 == i3 || i7 == list.size()) {
                iMax = Math.max(iMax, (i6 + iIntValue) - i2);
                i6 = 0;
                i5 = i4;
            } else {
                i6 += iIntValue;
            }
            i4 = i7;
        }
        return iMax;
    }

    /* JADX INFO: renamed from: measureAndCache-rqJ1uqs, reason: not valid java name */
    public static final long m840measureAndCacherqJ1uqs(Measurable measurable, FlowLineMeasurePolicy flowLineMeasurePolicy, long j, Function1<? super Placeable, Unit> function1) {
        FlowLayoutData flowLayoutData;
        if (RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData((IntrinsicMeasurable) measurable)) == 0.0f) {
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData((IntrinsicMeasurable) measurable);
            if (((rowColumnParentData == null || (flowLayoutData = rowColumnParentData.getFlowLayoutData()) == null) ? null : Float.valueOf(flowLayoutData.getFillCrossAxisFraction())) == null) {
                Placeable placeable = measurable.measure-BRTryo0(j);
                function1.invoke(placeable);
                return IntIntPair.m24constructorimpl(flowLineMeasurePolicy.mainAxisSize(placeable), flowLineMeasurePolicy.crossAxisSize(placeable));
            }
        }
        int iMainAxisMin = mainAxisMin(measurable, flowLineMeasurePolicy.isHorizontal(), Integer.MAX_VALUE);
        return IntIntPair.m24constructorimpl(iMainAxisMin, crossAxisMin(measurable, flowLineMeasurePolicy.isHorizontal(), iMainAxisMin));
    }

    private static final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function4, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        List<? extends IntrinsicMeasurable> list2 = list;
        int i6 = i4;
        int i7 = i5;
        if (list2.isEmpty()) {
            return 0;
        }
        int size = list2.size();
        int[] iArr = new int[size];
        int size2 = list2.size();
        int[] iArr2 = new int[size2];
        int size3 = list2.size();
        for (int i8 = 0; i8 < size3; i8++) {
            IntrinsicMeasurable intrinsicMeasurable = list2.get(i8);
            int iIntValue = ((Number) function3.invoke(intrinsicMeasurable, Integer.valueOf(i8), Integer.valueOf(i))).intValue();
            iArr[i8] = iIntValue;
            iArr2[i8] = ((Number) function4.invoke(intrinsicMeasurable, Integer.valueOf(i8), Integer.valueOf(iIntValue))).intValue();
        }
        int i9 = Integer.MAX_VALUE;
        if (i7 != Integer.MAX_VALUE && i6 != Integer.MAX_VALUE) {
            i9 = i6 * i7;
        }
        int i10 = 1;
        int iMin = Math.min(i9 - (((i9 >= list2.size() || !(flowLayoutOverflowState.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandIndicator || flowLayoutOverflowState.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) && (i9 < list2.size() || i7 < flowLayoutOverflowState.getMinLinesToShowCollapse$foundation_layout() || flowLayoutOverflowState.getType$foundation_layout() != FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) ? 0 : 1), list2.size());
        int iSum = ArraysKt.sum(iArr) + ((list2.size() - 1) * i2);
        if (size2 == 0) {
            z0e.a();
            return 0;
        }
        int iM28getFirstimpl = iArr2[0];
        int lastIndex = ArraysKt.getLastIndex(iArr2);
        if (1 <= lastIndex) {
            int i11 = 1;
            while (true) {
                int i12 = iArr2[i11];
                if (iM28getFirstimpl < i12) {
                    iM28getFirstimpl = i12;
                }
                if (i11 == lastIndex) {
                    break;
                }
                i11++;
            }
        }
        if (size == 0) {
            z0e.a();
            return 0;
        }
        int i13 = iArr[0];
        int lastIndex2 = ArraysKt.getLastIndex(iArr);
        if (1 <= lastIndex2) {
            while (true) {
                int i14 = iArr[i10];
                if (i13 < i14) {
                    i13 = i14;
                }
                if (i10 == lastIndex2) {
                    break;
                }
                i10++;
            }
        }
        int i15 = i13;
        int i16 = iSum;
        while (i15 <= i16 && iM28getFirstimpl != i) {
            int i17 = (i15 + i16) / 2;
            long jIntrinsicCrossAxisSize = intrinsicCrossAxisSize(list2, iArr, iArr2, i17, i2, i3, i6, i7, flowLayoutOverflowState);
            iM28getFirstimpl = IntIntPair.m28getFirstimpl(jIntrinsicCrossAxisSize);
            int iM29getSecondimpl = IntIntPair.m29getSecondimpl(jIntrinsicCrossAxisSize);
            if (iM28getFirstimpl > i || iM29getSecondimpl < iMin) {
                i15 = i17 + 1;
                if (i15 > i16) {
                    return i15;
                }
            } else {
                if (iM28getFirstimpl >= i) {
                    return i17;
                }
                i16 = i17 - 1;
            }
            list2 = list;
            i6 = i4;
            i7 = i5;
            iSum = i17;
        }
        return iSum;
    }

    /* JADX INFO: renamed from: placeHelper-BmaY500, reason: not valid java name */
    public static final MeasureResult m841placeHelperBmaY500(MeasureScope measureScope, long j, int i, int i2, int[] iArr, final MutableVector<MeasureResult> mutableVector, FlowLineMeasurePolicy flowLineMeasurePolicy, int[] iArr2) {
        int i3;
        int i4;
        int i5;
        boolean zIsHorizontal = flowLineMeasurePolicy.isHorizontal();
        Arrangement.Vertical verticalArrangement = flowLineMeasurePolicy.getVerticalArrangement();
        Arrangement.Horizontal horizontalArrangement = flowLineMeasurePolicy.getHorizontalArrangement();
        if (zIsHorizontal) {
            int i6 = i2 + (measureScope.roundToPx-0680j_4(verticalArrangement.getSpacing()) * (mutableVector.getSize() - 1));
            int i7 = Constraints.getMinHeight-impl(j);
            i3 = Constraints.getMaxHeight-impl(j);
            if (i6 < i7) {
                i6 = i7;
            }
            if (i6 <= i3) {
                i3 = i6;
            }
            verticalArrangement.arrange(measureScope, i3, iArr, iArr2);
        } else {
            int i8 = i2 + (measureScope.roundToPx-0680j_4(horizontalArrangement.getSpacing()) * (mutableVector.getSize() - 1));
            int i9 = Constraints.getMinHeight-impl(j);
            int i10 = Constraints.getMaxHeight-impl(j);
            if (i8 < i9) {
                i8 = i9;
            }
            int i11 = i8 > i10 ? i10 : i8;
            horizontalArrangement.arrange(measureScope, i11, iArr, measureScope.getLayoutDirection(), iArr2);
            i3 = i11;
        }
        int i12 = Constraints.getMinWidth-impl(j);
        int i13 = Constraints.getMaxWidth-impl(j);
        if (i < i12) {
            i = i12;
        }
        if (i <= i13) {
            i13 = i;
        }
        if (zIsHorizontal) {
            i5 = i13;
            i4 = i3;
        } else {
            i4 = i13;
            i5 = i3;
        }
        return MeasureScope.layout$default(measureScope, i5, i4, (Map) null, new Function1() { // from class: dj5
            public final Object invoke(Object obj) {
                return FlowLayoutKt.e(mutableVector, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public static final MeasurePolicy rowMeasurementHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1479255111, i2, -1, "androidx.compose.foundation.layout.rowMeasurementHelper (FlowLayout.kt:439)");
        }
        boolean z = ((((i2 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(i)) || (i2 & 384) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            final FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(true, horizontal, vertical, horizontal.getSpacing(), CROSS_AXIS_ALIGNMENT_TOP, vertical.getSpacing(), i, Integer.MAX_VALUE, FlowRowOverflow.INSTANCE.getVisible().createOverflowState$foundation_layout(), null);
            objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$rowMeasurementHelper$1$1
                /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
                public final MeasureResult m843measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                    return flowMeasurePolicy.measure-3p2s80s(measureScope, CollectionsKt.listOf(list), j);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return measurePolicy;
    }

    public static final MultiContentMeasurePolicy rowMeasurementMultiContentHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, Composer composer, int i3) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2010142641, i3, -1, "androidx.compose.foundation.layout.rowMeasurementMultiContentHelper (FlowLayout.kt:470)");
        }
        boolean zChanged = ((((i3 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i3 & 48) == 32) | ((((i3 & 896) ^ 384) > 256 && composer.changed(vertical2)) || (i3 & 384) == 256) | ((((i3 & 7168) ^ 3072) > 2048 && composer.changed(i)) || (i3 & 3072) == 2048) | ((((57344 & i3) ^ 24576) > 16384 && composer.changed(i2)) || (i3 & 24576) == 16384) | composer.changed(flowLayoutOverflowState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(true, horizontal, vertical, horizontal.getSpacing(), CrossAxisAlignment.INSTANCE.vertical$foundation_layout(vertical2), vertical.getSpacing(), i, i2, flowLayoutOverflowState, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return flowMeasurePolicy2;
    }

    private static final Measurable safeNext(Iterator<? extends Measurable> it, FlowLineInfo flowLineInfo) {
        try {
            if (!(it instanceof ContextualFlowItemIterator)) {
                return it.next();
            }
            flowLineInfo.getClass();
            return ((ContextualFlowItemIterator) it).getNext$foundation_layout(flowLineInfo);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        if (list.isEmpty()) {
            return IntIntPair.m24constructorimpl(0, 0);
        }
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i4, flowLayoutOverflowState, OrientationIndependentConstraints.m896constructorimpl(0, i, 0, Integer.MAX_VALUE), i5, i2, i3, null);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, 0);
        int i6 = intrinsicMeasurable != null ? iArr2[0] : 0;
        int i7 = intrinsicMeasurable != null ? iArr[0] : 0;
        int i8 = 0;
        if (flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(list.size() > 1, 0, IntIntPair.m24constructorimpl(i, Integer.MAX_VALUE), intrinsicMeasurable == null ? null : IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(i7, i6)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
            IntIntPair intIntPairM844ellipsisSizeF35zmw$foundation_layout = flowLayoutOverflowState.m844ellipsisSizeF35zmw$foundation_layout(intrinsicMeasurable != null, 0, 0);
            return IntIntPair.m24constructorimpl(intIntPairM844ellipsisSizeF35zmw$foundation_layout != null ? IntIntPair.m29getSecondimpl(intIntPairM844ellipsisSizeF35zmw$foundation_layout.getPackedValue()) : 0, 0);
        }
        int size = list.size();
        int i9 = i;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i10 < size) {
            int i15 = i9 - i7;
            int i16 = i10 + 1;
            int iMax = Math.max(i14, i6);
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, i16);
            int i17 = intrinsicMeasurable2 != null ? iArr2[i16] : 0;
            int i18 = intrinsicMeasurable2 != null ? iArr[i16] + i2 : 0;
            int i19 = i16 - i12;
            int i20 = i13;
            int i21 = i17;
            int i22 = i18;
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM837getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(i10 + 2 < list.size(), i19, IntIntPair.m24constructorimpl(i15, Integer.MAX_VALUE), intrinsicMeasurable2 == null ? null : IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(i18, i17)), i20, i8, iMax, false, false);
            if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInLine()) {
                int iM29getSecondimpl = i8 + iMax + i3;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM837getWrapInfoOpUlnko, intrinsicMeasurable2 != null, i20, iM29getSecondimpl, i15, i19);
                int i23 = i22 - i2;
                i13 = i20 + 1;
                if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInContainer()) {
                    if (wrapEllipsisInfo != null) {
                        long ellipsisSize = wrapEllipsisInfo.getEllipsisSize();
                        if (!wrapEllipsisInfo.getPlaceEllipsisOnLastContentLine()) {
                            iM29getSecondimpl += IntIntPair.m29getSecondimpl(ellipsisSize) + i3;
                        }
                    }
                    i8 = iM29getSecondimpl;
                    i11 = i16;
                    break;
                }
                i14 = 0;
                i8 = iM29getSecondimpl;
                i7 = i23;
                i12 = i16;
                i9 = i;
            } else {
                i9 = i15;
                i13 = i20;
                i14 = iMax;
                i7 = i22;
            }
            i10 = i16;
            i11 = i10;
            i6 = i21;
        }
        return IntIntPair.m24constructorimpl(i8 - i3, i11);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0122  */
    /* JADX WARN: Code duplicated, block: B:104:0x015d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0167  */
    /* JADX WARN: Code duplicated, block: B:110:0x017a  */
    /* JADX WARN: Code duplicated, block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:82:0x00df  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:91:0x0102  */
    /* JADX WARN: Code duplicated, block: B:92:0x010e  */
    /* JADX WARN: Code duplicated, block: B:95:0x0116  */
    /* JADX WARN: Code duplicated, block: B:97:0x0119  */
    /* JADX WARN: Code duplicated, block: B:98:0x011b  */
    public static final void FlowColumn(Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, final Function3<? super FlowColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        Modifier modifier2;
        int i5;
        Arrangement.Vertical vertical2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean z;
        Composer composer2;
        final Alignment.Horizontal horizontal3;
        final Modifier modifier3;
        final Arrangement.Vertical vertical3;
        final int i16;
        final Arrangement.Horizontal horizontal4;
        final int i17;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i18;
        Arrangement.Vertical top;
        Arrangement.Horizontal start;
        int i19;
        Alignment.Horizontal start2;
        int i20;
        int i21;
        Composer composerStartRestartGroup = composer.startRestartGroup(1371845627);
        int i22 = i4 & 1;
        if (i22 != 0) {
            i5 = i3 | 6;
            modifier2 = modifier;
        } else if ((i3 & 6) == 0) {
            modifier2 = modifier;
            i5 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i3;
        } else {
            modifier2 = modifier;
            i5 = i3;
        }
        int i23 = i4 & 2;
        if (i23 == 0) {
            if ((i3 & 48) == 0) {
                vertical2 = vertical;
                i5 |= composerStartRestartGroup.changed(vertical2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & 384) == 0) {
                    if (composerStartRestartGroup.changed(horizontal)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        if (composerStartRestartGroup.changed(horizontal2)) {
                            i9 = 2048;
                        } else {
                            i9 = 1024;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 16;
                    if (i10 != 0) {
                        if ((i3 & 24576) == 0) {
                            i11 = i;
                            if (composerStartRestartGroup.changed(i11)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i5 |= i12;
                        }
                        i13 = i4 & 32;
                        if (i13 != 0) {
                            if ((196608 & i3) == 0) {
                                i14 = i2;
                                if (composerStartRestartGroup.changed(i14)) {
                                    i15 = 131072;
                                } else {
                                    i15 = 65536;
                                }
                                i5 |= i15;
                            }
                            if ((i3 & 1572864) == 0) {
                                if (composerStartRestartGroup.changedInstance(function3)) {
                                    i21 = IOUtil.MiB;
                                } else {
                                    i21 = 524288;
                                }
                                i5 |= i21;
                            }
                            if ((i5 & 599187) != 599186) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                                if (i22 != 0) {
                                    modifier4 = Modifier.Companion;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i23 != 0) {
                                    top = Arrangement.INSTANCE.getTop();
                                    i18 = i8;
                                } else {
                                    i18 = i8;
                                    top = vertical2;
                                }
                                if (i6 != 0) {
                                    start = Arrangement.INSTANCE.getStart();
                                } else {
                                    start = horizontal;
                                }
                                if (i18 != 0) {
                                    start2 = Alignment.Companion.getStart();
                                    i19 = i10;
                                } else {
                                    i19 = i10;
                                    start2 = horizontal2;
                                }
                                if (i19 != 0) {
                                    i11 = Integer.MAX_VALUE;
                                }
                                if (i13 != 0) {
                                    i20 = Integer.MAX_VALUE;
                                } else {
                                    i20 = i14;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                                }
                                composer2 = composerStartRestartGroup;
                                FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                vertical3 = top;
                                horizontal4 = start;
                                horizontal3 = start2;
                                i16 = i20;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                horizontal3 = horizontal2;
                                modifier3 = modifier2;
                                vertical3 = vertical2;
                                i16 = i14;
                                horizontal4 = horizontal;
                            }
                            i17 = i11;
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                                    public final Object invoke(Object obj, Object obj2) {
                                        return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i5 |= 196608;
                        i14 = i2;
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal;
                            }
                            if (i18 != 0) {
                                start2 = Alignment.Companion.getStart();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                start2 = horizontal2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            vertical3 = top;
                            horizontal4 = start;
                            horizontal3 = start2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            horizontal3 = horizontal2;
                            modifier3 = modifier2;
                            vertical3 = vertical2;
                            i16 = i14;
                            horizontal4 = horizontal;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i11 = i;
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal;
                            }
                            if (i18 != 0) {
                                start2 = Alignment.Companion.getStart();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                start2 = horizontal2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            vertical3 = top;
                            horizontal4 = start;
                            horizontal3 = start2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            horizontal3 = horizontal2;
                            modifier3 = modifier2;
                            vertical3 = vertical2;
                            i16 = i14;
                            horizontal4 = horizontal;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 3072;
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal;
                            }
                            if (i18 != 0) {
                                start2 = Alignment.Companion.getStart();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                start2 = horizontal2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            vertical3 = top;
                            horizontal4 = start;
                            horizontal3 = start2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            horizontal3 = horizontal2;
                            modifier3 = modifier2;
                            vertical3 = vertical2;
                            i16 = i14;
                            horizontal4 = horizontal;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 384;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal;
                            }
                            if (i18 != 0) {
                                start2 = Alignment.Companion.getStart();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                start2 = horizontal2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            vertical3 = top;
                            horizontal4 = start;
                            horizontal3 = start2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            horizontal3 = horizontal2;
                            modifier3 = modifier2;
                            vertical3 = vertical2;
                            i16 = i14;
                            horizontal4 = horizontal;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                    i18 = i8;
                } else {
                    i18 = i8;
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal;
                }
                if (i18 != 0) {
                    start2 = Alignment.Companion.getStart();
                    i19 = i10;
                } else {
                    i19 = i10;
                    start2 = horizontal2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                }
                composer2 = composerStartRestartGroup;
                FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                vertical3 = top;
                horizontal4 = start;
                horizontal3 = start2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                horizontal3 = horizontal2;
                modifier3 = modifier2;
                vertical3 = vertical2;
                i16 = i14;
                horizontal4 = horizontal;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 48;
        vertical2 = vertical;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & 384) == 0) {
                if (composerStartRestartGroup.changed(horizontal)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                top = vertical2;
                            }
                            if (i6 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal;
                            }
                            if (i18 != 0) {
                                start2 = Alignment.Companion.getStart();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                start2 = horizontal2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            vertical3 = top;
                            horizontal4 = start;
                            horizontal3 = start2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            horizontal3 = horizontal2;
                            modifier3 = modifier2;
                            vertical3 = vertical2;
                            i16 = i14;
                            horizontal4 = horizontal;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                    i18 = i8;
                } else {
                    i18 = i8;
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal;
                }
                if (i18 != 0) {
                    start2 = Alignment.Companion.getStart();
                    i19 = i10;
                } else {
                    i19 = i10;
                    start2 = horizontal2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                }
                composer2 = composerStartRestartGroup;
                FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                vertical3 = top;
                horizontal4 = start;
                horizontal3 = start2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                horizontal3 = horizontal2;
                modifier3 = modifier2;
                vertical3 = vertical2;
                i16 = i14;
                horizontal4 = horizontal;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 384;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                if (composerStartRestartGroup.changed(horizontal2)) {
                    i9 = 2048;
                } else {
                    i9 = 1024;
                }
                i5 |= i9;
            }
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            top = vertical2;
                        }
                        if (i6 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal;
                        }
                        if (i18 != 0) {
                            start2 = Alignment.Companion.getStart();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            start2 = horizontal2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        vertical3 = top;
                        horizontal4 = start;
                        horizontal3 = start2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal2;
                        modifier3 = modifier2;
                        vertical3 = vertical2;
                        i16 = i14;
                        horizontal4 = horizontal;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                    i18 = i8;
                } else {
                    i18 = i8;
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal;
                }
                if (i18 != 0) {
                    start2 = Alignment.Companion.getStart();
                    i19 = i10;
                } else {
                    i19 = i10;
                    start2 = horizontal2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                }
                composer2 = composerStartRestartGroup;
                FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                vertical3 = top;
                horizontal4 = start;
                horizontal3 = start2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                horizontal3 = horizontal2;
                modifier3 = modifier2;
                vertical3 = vertical2;
                i16 = i14;
                horizontal4 = horizontal;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 3072;
        i10 = i4 & 16;
        if (i10 != 0) {
            if ((i3 & 24576) == 0) {
                i11 = i;
                if (composerStartRestartGroup.changed(i11)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i5 |= i12;
            }
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        top = vertical2;
                    }
                    if (i6 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal;
                    }
                    if (i18 != 0) {
                        start2 = Alignment.Companion.getStart();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        start2 = horizontal2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    vertical3 = top;
                    horizontal4 = start;
                    horizontal3 = start2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    horizontal3 = horizontal2;
                    modifier3 = modifier2;
                    vertical3 = vertical2;
                    i16 = i14;
                    horizontal4 = horizontal;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                    i18 = i8;
                } else {
                    i18 = i8;
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal;
                }
                if (i18 != 0) {
                    start2 = Alignment.Companion.getStart();
                    i19 = i10;
                } else {
                    i19 = i10;
                    start2 = horizontal2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                }
                composer2 = composerStartRestartGroup;
                FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                vertical3 = top;
                horizontal4 = start;
                horizontal3 = start2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                horizontal3 = horizontal2;
                modifier3 = modifier2;
                vertical3 = vertical2;
                i16 = i14;
                horizontal4 = horizontal;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 24576;
        i11 = i;
        i13 = i4 & 32;
        if (i13 != 0) {
            if ((196608 & i3) == 0) {
                i14 = i2;
                if (composerStartRestartGroup.changed(i14)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                    i18 = i8;
                } else {
                    i18 = i8;
                    top = vertical2;
                }
                if (i6 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal;
                }
                if (i18 != 0) {
                    start2 = Alignment.Companion.getStart();
                    i19 = i10;
                } else {
                    i19 = i10;
                    start2 = horizontal2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
                }
                composer2 = composerStartRestartGroup;
                FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                vertical3 = top;
                horizontal4 = start;
                horizontal3 = start2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                horizontal3 = horizontal2;
                modifier3 = modifier2;
                vertical3 = vertical2;
                i16 = i14;
                horizontal4 = horizontal;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 196608;
        i14 = i2;
        if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i21 = IOUtil.MiB;
            } else {
                i21 = 524288;
            }
            i5 |= i21;
        }
        if ((i5 & 599187) != 599186) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
            if (i22 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (i23 != 0) {
                top = Arrangement.INSTANCE.getTop();
                i18 = i8;
            } else {
                i18 = i8;
                top = vertical2;
            }
            if (i6 != 0) {
                start = Arrangement.INSTANCE.getStart();
            } else {
                start = horizontal;
            }
            if (i18 != 0) {
                start2 = Alignment.Companion.getStart();
                i19 = i10;
            } else {
                i19 = i10;
                start2 = horizontal2;
            }
            if (i19 != 0) {
                i11 = Integer.MAX_VALUE;
            }
            if (i13 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i14;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1371845627, i5, -1, "androidx.compose.foundation.layout.FlowColumn (FlowLayout.kt:271)");
            }
            composer2 = composerStartRestartGroup;
            FlowColumn(modifier4, top, start, start2, i11, i20, FlowColumnOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            vertical3 = top;
            horizontal4 = start;
            horizontal3 = start2;
            i16 = i20;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            horizontal3 = horizontal2;
            modifier3 = modifier2;
            vertical3 = vertical2;
            i16 = i14;
            horizontal4 = horizontal;
        }
        i17 = i11;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cj5
                public final Object invoke(Object obj, Object obj2) {
                    return FlowLayoutKt.h(modifier3, vertical3, horizontal4, horizontal3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0122  */
    /* JADX WARN: Code duplicated, block: B:104:0x015d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0167  */
    /* JADX WARN: Code duplicated, block: B:110:0x017a  */
    /* JADX WARN: Code duplicated, block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:82:0x00df  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:91:0x0102  */
    /* JADX WARN: Code duplicated, block: B:92:0x010e  */
    /* JADX WARN: Code duplicated, block: B:95:0x0116  */
    /* JADX WARN: Code duplicated, block: B:97:0x0119  */
    /* JADX WARN: Code duplicated, block: B:98:0x011b  */
    public static final void FlowRow(Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i, int i2, final Function3<? super FlowRowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4) {
        Modifier modifier2;
        int i5;
        Arrangement.Horizontal horizontal2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean z;
        Composer composer2;
        final Alignment.Vertical vertical3;
        final Modifier modifier3;
        final Arrangement.Horizontal horizontal3;
        final int i16;
        final Arrangement.Vertical vertical4;
        final int i17;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i18;
        Arrangement.Horizontal start;
        Arrangement.Vertical top;
        int i19;
        Alignment.Vertical top2;
        int i20;
        int i21;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1303174015);
        int i22 = i4 & 1;
        if (i22 != 0) {
            i5 = i3 | 6;
            modifier2 = modifier;
        } else if ((i3 & 6) == 0) {
            modifier2 = modifier;
            i5 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i3;
        } else {
            modifier2 = modifier;
            i5 = i3;
        }
        int i23 = i4 & 2;
        if (i23 == 0) {
            if ((i3 & 48) == 0) {
                horizontal2 = horizontal;
                i5 |= composerStartRestartGroup.changed(horizontal2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & 384) == 0) {
                    if (composerStartRestartGroup.changed(vertical)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        if (composerStartRestartGroup.changed(vertical2)) {
                            i9 = 2048;
                        } else {
                            i9 = 1024;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 16;
                    if (i10 != 0) {
                        if ((i3 & 24576) == 0) {
                            i11 = i;
                            if (composerStartRestartGroup.changed(i11)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i5 |= i12;
                        }
                        i13 = i4 & 32;
                        if (i13 != 0) {
                            if ((196608 & i3) == 0) {
                                i14 = i2;
                                if (composerStartRestartGroup.changed(i14)) {
                                    i15 = 131072;
                                } else {
                                    i15 = 65536;
                                }
                                i5 |= i15;
                            }
                            if ((i3 & 1572864) == 0) {
                                if (composerStartRestartGroup.changedInstance(function3)) {
                                    i21 = IOUtil.MiB;
                                } else {
                                    i21 = 524288;
                                }
                                i5 |= i21;
                            }
                            if ((i5 & 599187) != 599186) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                                if (i22 != 0) {
                                    modifier4 = Modifier.Companion;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i23 != 0) {
                                    start = Arrangement.INSTANCE.getStart();
                                    i18 = i8;
                                } else {
                                    i18 = i8;
                                    start = horizontal2;
                                }
                                if (i6 != 0) {
                                    top = Arrangement.INSTANCE.getTop();
                                } else {
                                    top = vertical;
                                }
                                if (i18 != 0) {
                                    top2 = Alignment.Companion.getTop();
                                    i19 = i10;
                                } else {
                                    i19 = i10;
                                    top2 = vertical2;
                                }
                                if (i19 != 0) {
                                    i11 = Integer.MAX_VALUE;
                                }
                                if (i13 != 0) {
                                    i20 = Integer.MAX_VALUE;
                                } else {
                                    i20 = i14;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                                }
                                composer2 = composerStartRestartGroup;
                                FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                horizontal3 = start;
                                vertical4 = top;
                                vertical3 = top2;
                                i16 = i20;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                vertical3 = vertical2;
                                modifier3 = modifier2;
                                horizontal3 = horizontal2;
                                i16 = i14;
                                vertical4 = vertical;
                            }
                            i17 = i11;
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                                    public final Object invoke(Object obj, Object obj2) {
                                        return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i5 |= 196608;
                        i14 = i2;
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical;
                            }
                            if (i18 != 0) {
                                top2 = Alignment.Companion.getTop();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                top2 = vertical2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            horizontal3 = start;
                            vertical4 = top;
                            vertical3 = top2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            vertical3 = vertical2;
                            modifier3 = modifier2;
                            horizontal3 = horizontal2;
                            i16 = i14;
                            vertical4 = vertical;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i11 = i;
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical;
                            }
                            if (i18 != 0) {
                                top2 = Alignment.Companion.getTop();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                top2 = vertical2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            horizontal3 = start;
                            vertical4 = top;
                            vertical3 = top2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            vertical3 = vertical2;
                            modifier3 = modifier2;
                            horizontal3 = horizontal2;
                            i16 = i14;
                            vertical4 = vertical;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 3072;
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical;
                            }
                            if (i18 != 0) {
                                top2 = Alignment.Companion.getTop();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                top2 = vertical2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            horizontal3 = start;
                            vertical4 = top;
                            vertical3 = top2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            vertical3 = vertical2;
                            modifier3 = modifier2;
                            horizontal3 = horizontal2;
                            i16 = i14;
                            vertical4 = vertical;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 384;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical;
                            }
                            if (i18 != 0) {
                                top2 = Alignment.Companion.getTop();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                top2 = vertical2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            horizontal3 = start;
                            vertical4 = top;
                            vertical3 = top2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            vertical3 = vertical2;
                            modifier3 = modifier2;
                            horizontal3 = horizontal2;
                            i16 = i14;
                            vertical4 = vertical;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                    i18 = i8;
                } else {
                    i18 = i8;
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical;
                }
                if (i18 != 0) {
                    top2 = Alignment.Companion.getTop();
                    i19 = i10;
                } else {
                    i19 = i10;
                    top2 = vertical2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                }
                composer2 = composerStartRestartGroup;
                FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                horizontal3 = start;
                vertical4 = top;
                vertical3 = top2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                vertical3 = vertical2;
                modifier3 = modifier2;
                horizontal3 = horizontal2;
                i16 = i14;
                vertical4 = vertical;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 48;
        horizontal2 = horizontal;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & 384) == 0) {
                if (composerStartRestartGroup.changed(vertical)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        if ((196608 & i3) == 0) {
                            i14 = i2;
                            if (composerStartRestartGroup.changed(i14)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i5 |= i15;
                        }
                        if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i21 = IOUtil.MiB;
                            } else {
                                i21 = 524288;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                            if (i22 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i23 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                                i18 = i8;
                            } else {
                                i18 = i8;
                                start = horizontal2;
                            }
                            if (i6 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical;
                            }
                            if (i18 != 0) {
                                top2 = Alignment.Companion.getTop();
                                i19 = i10;
                            } else {
                                i19 = i10;
                                top2 = vertical2;
                            }
                            if (i19 != 0) {
                                i11 = Integer.MAX_VALUE;
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                            } else {
                                i20 = i14;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                            }
                            composer2 = composerStartRestartGroup;
                            FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            horizontal3 = start;
                            vertical4 = top;
                            vertical3 = top2;
                            i16 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            vertical3 = vertical2;
                            modifier3 = modifier2;
                            horizontal3 = horizontal2;
                            i16 = i14;
                            vertical4 = vertical;
                        }
                        i17 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                                public final Object invoke(Object obj, Object obj2) {
                                    return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 196608;
                    i14 = i2;
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                    i18 = i8;
                } else {
                    i18 = i8;
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical;
                }
                if (i18 != 0) {
                    top2 = Alignment.Companion.getTop();
                    i19 = i10;
                } else {
                    i19 = i10;
                    top2 = vertical2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                }
                composer2 = composerStartRestartGroup;
                FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                horizontal3 = start;
                vertical4 = top;
                vertical3 = top2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                vertical3 = vertical2;
                modifier3 = modifier2;
                horizontal3 = horizontal2;
                i16 = i14;
                vertical4 = vertical;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 384;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                if (composerStartRestartGroup.changed(vertical2)) {
                    i9 = 2048;
                } else {
                    i9 = 1024;
                }
                i5 |= i9;
            }
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    if ((196608 & i3) == 0) {
                        i14 = i2;
                        if (composerStartRestartGroup.changed(i14)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i5 |= i15;
                    }
                    if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i21 = IOUtil.MiB;
                        } else {
                            i21 = 524288;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                        if (i22 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i23 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                            i18 = i8;
                        } else {
                            i18 = i8;
                            start = horizontal2;
                        }
                        if (i6 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical;
                        }
                        if (i18 != 0) {
                            top2 = Alignment.Companion.getTop();
                            i19 = i10;
                        } else {
                            i19 = i10;
                            top2 = vertical2;
                        }
                        if (i19 != 0) {
                            i11 = Integer.MAX_VALUE;
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                        } else {
                            i20 = i14;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                        }
                        composer2 = composerStartRestartGroup;
                        FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        horizontal3 = start;
                        vertical4 = top;
                        vertical3 = top2;
                        i16 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        vertical3 = vertical2;
                        modifier3 = modifier2;
                        horizontal3 = horizontal2;
                        i16 = i14;
                        vertical4 = vertical;
                    }
                    i17 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                            public final Object invoke(Object obj, Object obj2) {
                                return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 196608;
                i14 = i2;
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                    i18 = i8;
                } else {
                    i18 = i8;
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical;
                }
                if (i18 != 0) {
                    top2 = Alignment.Companion.getTop();
                    i19 = i10;
                } else {
                    i19 = i10;
                    top2 = vertical2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                }
                composer2 = composerStartRestartGroup;
                FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                horizontal3 = start;
                vertical4 = top;
                vertical3 = top2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                vertical3 = vertical2;
                modifier3 = modifier2;
                horizontal3 = horizontal2;
                i16 = i14;
                vertical4 = vertical;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 3072;
        i10 = i4 & 16;
        if (i10 != 0) {
            if ((i3 & 24576) == 0) {
                i11 = i;
                if (composerStartRestartGroup.changed(i11)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i5 |= i12;
            }
            i13 = i4 & 32;
            if (i13 != 0) {
                if ((196608 & i3) == 0) {
                    i14 = i2;
                    if (composerStartRestartGroup.changed(i14)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i5 |= i15;
                }
                if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i21 = IOUtil.MiB;
                    } else {
                        i21 = 524288;
                    }
                    i5 |= i21;
                }
                if ((i5 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                    if (i22 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i23 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                        i18 = i8;
                    } else {
                        i18 = i8;
                        start = horizontal2;
                    }
                    if (i6 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical;
                    }
                    if (i18 != 0) {
                        top2 = Alignment.Companion.getTop();
                        i19 = i10;
                    } else {
                        i19 = i10;
                        top2 = vertical2;
                    }
                    if (i19 != 0) {
                        i11 = Integer.MAX_VALUE;
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                    } else {
                        i20 = i14;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                    }
                    composer2 = composerStartRestartGroup;
                    FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    horizontal3 = start;
                    vertical4 = top;
                    vertical3 = top2;
                    i16 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    vertical3 = vertical2;
                    modifier3 = modifier2;
                    horizontal3 = horizontal2;
                    i16 = i14;
                    vertical4 = vertical;
                }
                i17 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                        public final Object invoke(Object obj, Object obj2) {
                            return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 196608;
            i14 = i2;
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                    i18 = i8;
                } else {
                    i18 = i8;
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical;
                }
                if (i18 != 0) {
                    top2 = Alignment.Companion.getTop();
                    i19 = i10;
                } else {
                    i19 = i10;
                    top2 = vertical2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                }
                composer2 = composerStartRestartGroup;
                FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                horizontal3 = start;
                vertical4 = top;
                vertical3 = top2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                vertical3 = vertical2;
                modifier3 = modifier2;
                horizontal3 = horizontal2;
                i16 = i14;
                vertical4 = vertical;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 24576;
        i11 = i;
        i13 = i4 & 32;
        if (i13 != 0) {
            if ((196608 & i3) == 0) {
                i14 = i2;
                if (composerStartRestartGroup.changed(i14)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i5 |= i15;
            }
            if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i21 = IOUtil.MiB;
                } else {
                    i21 = 524288;
                }
                i5 |= i21;
            }
            if ((i5 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
                if (i22 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i23 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                    i18 = i8;
                } else {
                    i18 = i8;
                    start = horizontal2;
                }
                if (i6 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical;
                }
                if (i18 != 0) {
                    top2 = Alignment.Companion.getTop();
                    i19 = i10;
                } else {
                    i19 = i10;
                    top2 = vertical2;
                }
                if (i19 != 0) {
                    i11 = Integer.MAX_VALUE;
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                } else {
                    i20 = i14;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
                }
                composer2 = composerStartRestartGroup;
                FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                horizontal3 = start;
                vertical4 = top;
                vertical3 = top2;
                i16 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                vertical3 = vertical2;
                modifier3 = modifier2;
                horizontal3 = horizontal2;
                i16 = i14;
                vertical4 = vertical;
            }
            i17 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                    public final Object invoke(Object obj, Object obj2) {
                        return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 196608;
        i14 = i2;
        if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i21 = IOUtil.MiB;
            } else {
                i21 = 524288;
            }
            i5 |= i21;
        }
        if ((i5 & 599187) != 599186) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i5 & 1)) {
            if (i22 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (i23 != 0) {
                start = Arrangement.INSTANCE.getStart();
                i18 = i8;
            } else {
                i18 = i8;
                start = horizontal2;
            }
            if (i6 != 0) {
                top = Arrangement.INSTANCE.getTop();
            } else {
                top = vertical;
            }
            if (i18 != 0) {
                top2 = Alignment.Companion.getTop();
                i19 = i10;
            } else {
                i19 = i10;
                top2 = vertical2;
            }
            if (i19 != 0) {
                i11 = Integer.MAX_VALUE;
            }
            if (i13 != 0) {
                i20 = Integer.MAX_VALUE;
            } else {
                i20 = i14;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1303174015, i5, -1, "androidx.compose.foundation.layout.FlowRow (FlowLayout.kt:162)");
            }
            composer2 = composerStartRestartGroup;
            FlowRow(modifier4, start, top, top2, i11, i20, FlowRowOverflow.INSTANCE.getClip(), function3, composer2, (i5 & 14) | 1572864 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | ((i5 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            horizontal3 = start;
            vertical4 = top;
            vertical3 = top2;
            i16 = i20;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            vertical3 = vertical2;
            modifier3 = modifier2;
            horizontal3 = horizontal2;
            i16 = i14;
            vertical4 = vertical;
        }
        i17 = i11;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ij5
                public final Object invoke(Object obj, Object obj2) {
                    return FlowLayoutKt.g(modifier3, horizontal3, vertical4, vertical3, i17, i16, function3, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
