package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.BottomSheetScaffoldKt;
import androidx.compose.material3.SheetValue;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00112\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010&\u001a7\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020)2\u0014\b\u0002\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0002\u0010+\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010,\u001a\u008a\u0001\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0013\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0004\b4\u00105\u001aq\u00106\u001a\u00020\u00012\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u00108\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\f\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00162\u0006\u0010;\u001a\u00020$H\u0003¢\u0006\u0002\u0010<\u001a\u0014\u0010=\u001a\u00020\b*\u00020\b2\u0006\u0010.\u001a\u00020$H\u0000\u001a\u0014\u0010>\u001a\u00020\b*\u00020\b2\u0006\u0010.\u001a\u00020$H\u0000¨\u0006?"}, d2 = {"BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material3/BottomSheetScaffoldState;", "sheetPeekHeight", "Landroidx/compose/ui/unit/Dp;", "sheetMaxWidth", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetContainerColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetTonalElevation", "sheetShadowElevation", "sheetDragHandle", "Lkotlin/Function0;", "sheetSwipeEnabled", "", "topBar", "snackbarHost", "Landroidx/compose/material3/SnackbarHostState;", "containerColor", "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-sdMYb0k", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/BottomSheetScaffoldState;FFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "rememberBottomSheetScaffoldState", "bottomSheetState", "Landroidx/compose/material3/SheetState;", "snackbarHostState", "(Landroidx/compose/material3/SheetState;Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomSheetScaffoldState;", "rememberStandardBottomSheetState", "initialValue", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "skipHiddenState", "(Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "StandardBottomSheet", "state", "peekHeight", "shape", "tonalElevation", "shadowElevation", "dragHandle", "StandardBottomSheet-w7I5h1o", "(Landroidx/compose/material3/SheetState;FFZLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffoldLayout", "body", "bottomSheet", "sheetOffset", "", "sheetState", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetState;Landroidx/compose/runtime/Composer;I)V", "verticalScaleUp", "verticalScaleDown", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BottomSheetScaffoldKt {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.Hidden.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SheetValue.Expanded.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0128  */
    /* JADX WARN: Code duplicated, block: B:102:0x012e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0134  */
    /* JADX WARN: Code duplicated, block: B:105:0x0137  */
    /* JADX WARN: Code duplicated, block: B:109:0x013f  */
    /* JADX WARN: Code duplicated, block: B:110:0x0146  */
    /* JADX WARN: Code duplicated, block: B:112:0x014a  */
    /* JADX WARN: Code duplicated, block: B:114:0x0154  */
    /* JADX WARN: Code duplicated, block: B:115:0x0157  */
    /* JADX WARN: Code duplicated, block: B:117:0x015c  */
    /* JADX WARN: Code duplicated, block: B:120:0x0166  */
    /* JADX WARN: Code duplicated, block: B:122:0x016d  */
    /* JADX WARN: Code duplicated, block: B:124:0x0171  */
    /* JADX WARN: Code duplicated, block: B:126:0x017b  */
    /* JADX WARN: Code duplicated, block: B:127:0x017e  */
    /* JADX WARN: Code duplicated, block: B:129:0x0183  */
    /* JADX WARN: Code duplicated, block: B:132:0x018e  */
    /* JADX WARN: Code duplicated, block: B:133:0x0191  */
    /* JADX WARN: Code duplicated, block: B:135:0x0197  */
    /* JADX WARN: Code duplicated, block: B:137:0x019f  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:144:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:146:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:148:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:150:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:154:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:156:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:159:0x01e0 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:161:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:164:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:166:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:169:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:171:0x0200  */
    /* JADX WARN: Code duplicated, block: B:174:0x0206  */
    /* JADX WARN: Code duplicated, block: B:175:0x020b  */
    /* JADX WARN: Code duplicated, block: B:177:0x0211  */
    /* JADX WARN: Code duplicated, block: B:179:0x0217  */
    /* JADX WARN: Code duplicated, block: B:183:0x0226  */
    /* JADX WARN: Code duplicated, block: B:187:0x0233  */
    /* JADX WARN: Code duplicated, block: B:190:0x023c  */
    /* JADX WARN: Code duplicated, block: B:192:0x0252  */
    /* JADX WARN: Code duplicated, block: B:214:0x0297 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:215:0x0299  */
    /* JADX WARN: Code duplicated, block: B:216:0x029c  */
    /* JADX WARN: Code duplicated, block: B:219:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:220:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:222:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:223:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:225:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:228:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:231:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:234:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:236:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:238:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:239:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:241:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:242:0x0302  */
    /* JADX WARN: Code duplicated, block: B:244:0x0306  */
    /* JADX WARN: Code duplicated, block: B:245:0x0309  */
    /* JADX WARN: Code duplicated, block: B:247:0x030d  */
    /* JADX WARN: Code duplicated, block: B:248:0x0310  */
    /* JADX WARN: Code duplicated, block: B:250:0x0314  */
    /* JADX WARN: Code duplicated, block: B:251:0x031b  */
    /* JADX WARN: Code duplicated, block: B:254:0x0321  */
    /* JADX WARN: Code duplicated, block: B:255:0x0331  */
    /* JADX WARN: Code duplicated, block: B:258:0x0339  */
    /* JADX WARN: Code duplicated, block: B:260:0x034a  */
    /* JADX WARN: Code duplicated, block: B:263:0x0356  */
    /* JADX WARN: Code duplicated, block: B:265:0x0365  */
    /* JADX WARN: Code duplicated, block: B:268:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:271:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:272:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:275:0x03d5  */
    /* JADX WARN: Code duplicated, block: B:277:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:280:0x0443  */
    /* JADX WARN: Code duplicated, block: B:282:0x0464  */
    /* JADX WARN: Code duplicated, block: B:285:0x0487  */
    /* JADX WARN: Code duplicated, block: B:287:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0050  */
    /* JADX WARN: Code duplicated, block: B:30:0x0058  */
    /* JADX WARN: Code duplicated, block: B:31:0x005b  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:39:0x0071  */
    /* JADX WARN: Code duplicated, block: B:41:0x0075  */
    /* JADX WARN: Code duplicated, block: B:43:0x007d  */
    /* JADX WARN: Code duplicated, block: B:44:0x0080  */
    /* JADX WARN: Code duplicated, block: B:48:0x008c  */
    /* JADX WARN: Code duplicated, block: B:50:0x0091  */
    /* JADX WARN: Code duplicated, block: B:52:0x0095  */
    /* JADX WARN: Code duplicated, block: B:54:0x009d  */
    /* JADX WARN: Code duplicated, block: B:55:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:64:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:66:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:71:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:74:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:81:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:89:0x0105  */
    /* JADX WARN: Code duplicated, block: B:90:0x010a  */
    /* JADX WARN: Code duplicated, block: B:92:0x0110  */
    /* JADX WARN: Code duplicated, block: B:94:0x0116  */
    /* JADX WARN: Code duplicated, block: B:95:0x0119  */
    /* JADX WARN: Code duplicated, block: B:99:0x0123  */
    /* JADX INFO: renamed from: BottomSheetScaffold-sdMYb0k, reason: not valid java name */
    public static final void m124BottomSheetScaffoldsdMYb0k(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, BottomSheetScaffoldState bottomSheetScaffoldState, float f, float f2, Shape shape, long j, long j2, float f3, float f4, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, Function2<? super Composer, ? super Integer, Unit> function4, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function5, long j3, long j4, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function6, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        BottomSheetScaffoldState bottomSheetScaffoldState2;
        int i5;
        int i6;
        int i7;
        int i8;
        float fM121getSheetMaxWidthD9Ej5fM;
        int i9;
        Shape expandedShape;
        long containerColor;
        long jM278contentColorForek8zF_U;
        int i10;
        float fM6022constructorimpl;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        boolean z2;
        final Shape shape2;
        final long j5;
        final Modifier modifier2;
        final boolean z3;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function7;
        final long j6;
        final long j7;
        final float f5;
        final long j8;
        final BottomSheetScaffoldState bottomSheetScaffoldState3;
        final float f6;
        final float f7;
        final float f8;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        BottomSheetScaffoldState bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
        float fM122getSheetPeekHeightD9Ej5fM;
        float fM119getElevationD9Ej5fM;
        Function2<? super Composer, ? super Integer, Unit> lambda$1392012807$material3;
        boolean z4;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> lambda$1768941633$material3;
        long surface;
        long jM278contentColorForek8zF_U2;
        long j9;
        Modifier modifier4;
        int i26;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        int i27;
        int i28;
        int i29;
        Composer composerStartRestartGroup = composer.startRestartGroup(920075480);
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        int i30 = i3 & 2;
        if (i30 == 0) {
            if ((i & 48) == 0) {
                i4 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    bottomSheetScaffoldState2 = bottomSheetScaffoldState;
                    int i31 = composerStartRestartGroup.changed(bottomSheetScaffoldState2) ? 256 : 128;
                    i4 |= i31;
                } else {
                    bottomSheetScaffoldState2 = bottomSheetScaffoldState;
                }
                i4 |= i31;
            } else {
                bottomSheetScaffoldState2 = bottomSheetScaffoldState;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 16;
                i8 = 8192;
                if (i7 != 0) {
                    if ((i & 24576) == 0) {
                        fM121getSheetMaxWidthD9Ej5fM = f2;
                        if (composerStartRestartGroup.changed(fM121getSheetMaxWidthD9Ej5fM)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i4 |= i9;
                    }
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        expandedShape = shape;
                        if ((i3 & 32) == 0 || !composerStartRestartGroup.changed(expandedShape)) {
                            i29 = 65536;
                        } else {
                            i29 = 131072;
                        }
                        i4 |= i29;
                    } else {
                        expandedShape = shape;
                    }
                    if ((i & 1572864) == 0) {
                        containerColor = j;
                        if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(containerColor)) {
                            i28 = 524288;
                        } else {
                            i28 = 1048576;
                        }
                        i4 |= i28;
                    } else {
                        containerColor = j;
                    }
                    if ((i & 12582912) == 0) {
                        jM278contentColorForek8zF_U = j2;
                        if ((i3 & 128) == 0 || !composerStartRestartGroup.changed(jM278contentColorForek8zF_U)) {
                            i27 = 4194304;
                        } else {
                            i27 = 8388608;
                        }
                        i4 |= i27;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    i10 = i3 & 256;
                    if (i10 != 0) {
                        i4 |= 100663296;
                        fM6022constructorimpl = f3;
                    } else {
                        fM6022constructorimpl = f3;
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i4 |= i11;
                        }
                    }
                    i12 = i3 & 512;
                    if (i12 != 0) {
                        i4 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    i14 = i3 & 1024;
                    if (i14 != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    i17 = i3 & 2048;
                    if (i17 != 0) {
                        i15 |= 48;
                    } else if ((i2 & 48) != 0) {
                        if (composerStartRestartGroup.changed(z)) {
                            i18 = 32;
                        } else {
                            i18 = 16;
                        }
                        i15 |= i18;
                    }
                    i19 = i15;
                    i20 = i3 & 4096;
                    if (i20 != 0) {
                        i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    } else {
                        i21 = i19;
                        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i23 = 256;
                            } else {
                                i23 = 128;
                            }
                            i21 |= i23;
                        }
                        i22 = i21;
                    }
                    i24 = i3 & 8192;
                    if (i24 != 0) {
                        i25 = i22;
                        if ((i2 & 3072) == 0) {
                            i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                        }
                        if ((i2 & 24576) != 0) {
                            if ((i3 & 16384) == 0 && composerStartRestartGroup.changed(j3)) {
                                i8 = 16384;
                            }
                            i25 |= i8;
                        }
                        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                            i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                        }
                        if ((i3 & 65536) != 0) {
                            i25 |= 1572864;
                        } else if ((i2 & 1572864) == 0) {
                            i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                        }
                        if ((306783379 & i4) == 306783378 || (i25 & 599187) != 599186) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i30 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if ((i3 & 4) != 0) {
                                    bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                    i4 &= -897;
                                } else {
                                    bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                                }
                                if (i5 != 0) {
                                    fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                                } else {
                                    fM122getSheetPeekHeightD9Ej5fM = f;
                                }
                                if (i7 != 0) {
                                    fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                    i4 &= -458753;
                                }
                                if ((i3 & 64) != 0) {
                                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                }
                                if (i10 != 0) {
                                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                                }
                                if (i12 != 0) {
                                    fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                                } else {
                                    fM119getElevationD9Ej5fM = f4;
                                }
                                if (i14 != 0) {
                                    lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                                } else {
                                    lambda$1392012807$material3 = function2;
                                }
                                if (i17 != 0) {
                                    z4 = true;
                                } else {
                                    z4 = z;
                                }
                                if (i20 != 0) {
                                    function10 = null;
                                } else {
                                    function10 = function4;
                                }
                                if (i24 != 0) {
                                    lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                                } else {
                                    lambda$1768941633$material3 = function5;
                                }
                                if ((i3 & 16384) != 0) {
                                    surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                    i25 &= -57345;
                                } else {
                                    surface = j3;
                                }
                                if ((i3 & 32768) != 0) {
                                    jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                    i25 &= -458753;
                                } else {
                                    jM278contentColorForek8zF_U2 = j4;
                                }
                                j9 = surface;
                                modifier4 = modifier3;
                                i26 = i25;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 32) != 0) {
                                    i4 &= -458753;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                }
                                if ((i3 & 16384) != 0) {
                                    i25 &= -57345;
                                }
                                if ((i3 & 32768) != 0) {
                                    i25 &= -458753;
                                }
                                modifier4 = modifier;
                                fM119getElevationD9Ej5fM = f4;
                                lambda$1392012807$material3 = function2;
                                z4 = z;
                                function10 = function4;
                                lambda$1768941633$material3 = function5;
                                jM278contentColorForek8zF_U2 = j4;
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                                i26 = i25;
                                fM122getSheetPeekHeightD9Ej5fM = f;
                                j9 = j3;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                            }
                            Modifier modifier5 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                            long j10 = j9;
                            Modifier modifier6 = modifier4;
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier5);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
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
                            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                            f7 = fM122getSheetPeekHeightD9Ej5fM;
                            function8 = lambda$1392012807$material3;
                            function7 = lambda$1768941633$material3;
                            modifier2 = modifier6;
                            z3 = z4;
                            j7 = jM278contentColorForek8zF_U2;
                            j6 = j10;
                            long j11 = containerColor;
                            f8 = fM119getElevationD9Ej5fM;
                            f6 = fM6022constructorimpl;
                            function9 = function10;
                            long j12 = jM278contentColorForek8zF_U;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            shape2 = expandedShape;
                            j8 = j11;
                            j5 = j12;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape2 = expandedShape;
                            j5 = jM278contentColorForek8zF_U;
                            modifier2 = modifier;
                            z3 = z;
                            function7 = function5;
                            j6 = j3;
                            j7 = j4;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            j8 = containerColor;
                            bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                            f6 = fM6022constructorimpl;
                            f7 = f;
                            f8 = f4;
                            function8 = function2;
                            function9 = function4;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                                public final Object invoke(Object obj, Object obj2) {
                                    return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i25 = i22 | 3072;
                    if ((i2 & 24576) != 0) {
                        if ((i3 & 16384) == 0) {
                            i8 = 16384;
                        }
                        i25 |= i8;
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                        i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                    }
                    if ((i3 & 65536) != 0) {
                        i25 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                    }
                    if ((306783379 & i4) == 306783378) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        } else {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                        }
                        Modifier modifier7 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                        long j13 = j9;
                        Modifier modifier8 = modifier4;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier7);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier2, companion2.getSetModifier());
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                        f7 = fM122getSheetPeekHeightD9Ej5fM;
                        function8 = lambda$1392012807$material3;
                        function7 = lambda$1768941633$material3;
                        modifier2 = modifier8;
                        z3 = z4;
                        j7 = jM278contentColorForek8zF_U2;
                        j6 = j13;
                        long j14 = containerColor;
                        f8 = fM119getElevationD9Ej5fM;
                        f6 = fM6022constructorimpl;
                        function9 = function10;
                        long j15 = jM278contentColorForek8zF_U;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        shape2 = expandedShape;
                        j8 = j14;
                        j5 = j15;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = expandedShape;
                        j5 = jM278contentColorForek8zF_U;
                        modifier2 = modifier;
                        z3 = z;
                        function7 = function5;
                        j6 = j3;
                        j7 = j4;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        j8 = containerColor;
                        bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                        f6 = fM6022constructorimpl;
                        f7 = f;
                        f8 = f4;
                        function8 = function2;
                        function9 = function4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                            public final Object invoke(Object obj, Object obj2) {
                                return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                fM121getSheetMaxWidthD9Ej5fM = f2;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    expandedShape = shape;
                    if ((i3 & 32) == 0) {
                        i29 = 65536;
                    } else {
                        i29 = 65536;
                    }
                    i4 |= i29;
                } else {
                    expandedShape = shape;
                }
                if ((i & 1572864) == 0) {
                    containerColor = j;
                    if ((i3 & 64) == 0) {
                        i28 = 524288;
                    } else {
                        i28 = 524288;
                    }
                    i4 |= i28;
                } else {
                    containerColor = j;
                }
                if ((i & 12582912) == 0) {
                    jM278contentColorForek8zF_U = j2;
                    if ((i3 & 128) == 0) {
                        i27 = 4194304;
                    } else {
                        i27 = 4194304;
                    }
                    i4 |= i27;
                } else {
                    jM278contentColorForek8zF_U = j2;
                }
                i10 = i3 & 256;
                if (i10 != 0) {
                    i4 |= 100663296;
                    fM6022constructorimpl = f3;
                } else {
                    fM6022constructorimpl = f3;
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i4 |= i11;
                    }
                }
                i12 = i3 & 512;
                if (i12 != 0) {
                    i4 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                i14 = i3 & 1024;
                if (i14 != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                i17 = i3 & 2048;
                if (i17 != 0) {
                    i15 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(z)) {
                        i18 = 32;
                    } else {
                        i18 = 16;
                    }
                    i15 |= i18;
                }
                i19 = i15;
                i20 = i3 & 4096;
                if (i20 != 0) {
                    i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                } else {
                    i21 = i19;
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i23 = 256;
                        } else {
                            i23 = 128;
                        }
                        i21 |= i23;
                    }
                    i22 = i21;
                }
                i24 = i3 & 8192;
                if (i24 != 0) {
                    i25 = i22;
                    if ((i2 & 3072) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                    }
                    if ((i2 & 24576) != 0) {
                        if ((i3 & 16384) == 0) {
                            i8 = 16384;
                        }
                        i25 |= i8;
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                        i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                    }
                    if ((i3 & 65536) != 0) {
                        i25 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                    }
                    if ((306783379 & i4) == 306783378) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        } else {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                        }
                        Modifier modifier9 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                        long j16 = j9;
                        Modifier modifier10 = modifier4;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier9);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier3, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                        f7 = fM122getSheetPeekHeightD9Ej5fM;
                        function8 = lambda$1392012807$material3;
                        function7 = lambda$1768941633$material3;
                        modifier2 = modifier10;
                        z3 = z4;
                        j7 = jM278contentColorForek8zF_U2;
                        j6 = j16;
                        long j17 = containerColor;
                        f8 = fM119getElevationD9Ej5fM;
                        f6 = fM6022constructorimpl;
                        function9 = function10;
                        long j18 = jM278contentColorForek8zF_U;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        shape2 = expandedShape;
                        j8 = j17;
                        j5 = j18;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = expandedShape;
                        j5 = jM278contentColorForek8zF_U;
                        modifier2 = modifier;
                        z3 = z;
                        function7 = function5;
                        j6 = j3;
                        j7 = j4;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        j8 = containerColor;
                        bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                        f6 = fM6022constructorimpl;
                        f7 = f;
                        f8 = f4;
                        function8 = function2;
                        function9 = function4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                            public final Object invoke(Object obj, Object obj2) {
                                return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i25 = i22 | 3072;
                if ((i2 & 24576) != 0) {
                    if ((i3 & 16384) == 0) {
                        i8 = 16384;
                    }
                    i25 |= i8;
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                    i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                }
                if ((i3 & 65536) != 0) {
                    i25 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                }
                if ((306783379 & i4) == 306783378) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    } else {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                    }
                    Modifier modifier11 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                    long j19 = j9;
                    Modifier modifier12 = modifier4;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11);
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion4.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion4.getSetModifier());
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                    f7 = fM122getSheetPeekHeightD9Ej5fM;
                    function8 = lambda$1392012807$material3;
                    function7 = lambda$1768941633$material3;
                    modifier2 = modifier12;
                    z3 = z4;
                    j7 = jM278contentColorForek8zF_U2;
                    j6 = j19;
                    long j110 = containerColor;
                    f8 = fM119getElevationD9Ej5fM;
                    f6 = fM6022constructorimpl;
                    function9 = function10;
                    long j111 = jM278contentColorForek8zF_U;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    shape2 = expandedShape;
                    j8 = j110;
                    j5 = j111;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = expandedShape;
                    j5 = jM278contentColorForek8zF_U;
                    modifier2 = modifier;
                    z3 = z;
                    function7 = function5;
                    j6 = j3;
                    j7 = j4;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    j8 = containerColor;
                    bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                    f6 = fM6022constructorimpl;
                    f7 = f;
                    f8 = f4;
                    function8 = function2;
                    function9 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            i7 = i3 & 16;
            i8 = 8192;
            if (i7 != 0) {
                if ((i & 24576) == 0) {
                    fM121getSheetMaxWidthD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM121getSheetMaxWidthD9Ej5fM)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    expandedShape = shape;
                    if ((i3 & 32) == 0) {
                        i29 = 65536;
                    } else {
                        i29 = 65536;
                    }
                    i4 |= i29;
                } else {
                    expandedShape = shape;
                }
                if ((i & 1572864) == 0) {
                    containerColor = j;
                    if ((i3 & 64) == 0) {
                        i28 = 524288;
                    } else {
                        i28 = 524288;
                    }
                    i4 |= i28;
                } else {
                    containerColor = j;
                }
                if ((i & 12582912) == 0) {
                    jM278contentColorForek8zF_U = j2;
                    if ((i3 & 128) == 0) {
                        i27 = 4194304;
                    } else {
                        i27 = 4194304;
                    }
                    i4 |= i27;
                } else {
                    jM278contentColorForek8zF_U = j2;
                }
                i10 = i3 & 256;
                if (i10 != 0) {
                    i4 |= 100663296;
                    fM6022constructorimpl = f3;
                } else {
                    fM6022constructorimpl = f3;
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i4 |= i11;
                    }
                }
                i12 = i3 & 512;
                if (i12 != 0) {
                    i4 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                i14 = i3 & 1024;
                if (i14 != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                i17 = i3 & 2048;
                if (i17 != 0) {
                    i15 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(z)) {
                        i18 = 32;
                    } else {
                        i18 = 16;
                    }
                    i15 |= i18;
                }
                i19 = i15;
                i20 = i3 & 4096;
                if (i20 != 0) {
                    i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                } else {
                    i21 = i19;
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i23 = 256;
                        } else {
                            i23 = 128;
                        }
                        i21 |= i23;
                    }
                    i22 = i21;
                }
                i24 = i3 & 8192;
                if (i24 != 0) {
                    i25 = i22;
                    if ((i2 & 3072) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                    }
                    if ((i2 & 24576) != 0) {
                        if ((i3 & 16384) == 0) {
                            i8 = 16384;
                        }
                        i25 |= i8;
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                        i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                    }
                    if ((i3 & 65536) != 0) {
                        i25 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                    }
                    if ((306783379 & i4) == 306783378) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        } else {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                        }
                        Modifier modifier13 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                        long j112 = j9;
                        Modifier modifier14 = modifier4;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier13);
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion5.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier5, companion5.getSetModifier());
                        BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                        f7 = fM122getSheetPeekHeightD9Ej5fM;
                        function8 = lambda$1392012807$material3;
                        function7 = lambda$1768941633$material3;
                        modifier2 = modifier14;
                        z3 = z4;
                        j7 = jM278contentColorForek8zF_U2;
                        j6 = j112;
                        long j113 = containerColor;
                        f8 = fM119getElevationD9Ej5fM;
                        f6 = fM6022constructorimpl;
                        function9 = function10;
                        long j114 = jM278contentColorForek8zF_U;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        shape2 = expandedShape;
                        j8 = j113;
                        j5 = j114;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = expandedShape;
                        j5 = jM278contentColorForek8zF_U;
                        modifier2 = modifier;
                        z3 = z;
                        function7 = function5;
                        j6 = j3;
                        j7 = j4;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        j8 = containerColor;
                        bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                        f6 = fM6022constructorimpl;
                        f7 = f;
                        f8 = f4;
                        function8 = function2;
                        function9 = function4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                            public final Object invoke(Object obj, Object obj2) {
                                return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i25 = i22 | 3072;
                if ((i2 & 24576) != 0) {
                    if ((i3 & 16384) == 0) {
                        i8 = 16384;
                    }
                    i25 |= i8;
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                    i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                }
                if ((i3 & 65536) != 0) {
                    i25 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                }
                if ((306783379 & i4) == 306783378) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    } else {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                    }
                    Modifier modifier15 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                    long j115 = j9;
                    Modifier modifier16 = modifier4;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier15);
                    ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion6.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap6, companion6.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion6.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier6, companion6.getSetModifier());
                    BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                    f7 = fM122getSheetPeekHeightD9Ej5fM;
                    function8 = lambda$1392012807$material3;
                    function7 = lambda$1768941633$material3;
                    modifier2 = modifier16;
                    z3 = z4;
                    j7 = jM278contentColorForek8zF_U2;
                    j6 = j115;
                    long j116 = containerColor;
                    f8 = fM119getElevationD9Ej5fM;
                    f6 = fM6022constructorimpl;
                    function9 = function10;
                    long j117 = jM278contentColorForek8zF_U;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    shape2 = expandedShape;
                    j8 = j116;
                    j5 = j117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = expandedShape;
                    j5 = jM278contentColorForek8zF_U;
                    modifier2 = modifier;
                    z3 = z;
                    function7 = function5;
                    j6 = j3;
                    j7 = j4;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    j8 = containerColor;
                    bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                    f6 = fM6022constructorimpl;
                    f7 = f;
                    f8 = f4;
                    function8 = function2;
                    function9 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            fM121getSheetMaxWidthD9Ej5fM = f2;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                expandedShape = shape;
                if ((i3 & 32) == 0) {
                    i29 = 65536;
                } else {
                    i29 = 65536;
                }
                i4 |= i29;
            } else {
                expandedShape = shape;
            }
            if ((i & 1572864) == 0) {
                containerColor = j;
                if ((i3 & 64) == 0) {
                    i28 = 524288;
                } else {
                    i28 = 524288;
                }
                i4 |= i28;
            } else {
                containerColor = j;
            }
            if ((i & 12582912) == 0) {
                jM278contentColorForek8zF_U = j2;
                if ((i3 & 128) == 0) {
                    i27 = 4194304;
                } else {
                    i27 = 4194304;
                }
                i4 |= i27;
            } else {
                jM278contentColorForek8zF_U = j2;
            }
            i10 = i3 & 256;
            if (i10 != 0) {
                i4 |= 100663296;
                fM6022constructorimpl = f3;
            } else {
                fM6022constructorimpl = f3;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i4 |= i11;
                }
            }
            i12 = i3 & 512;
            if (i12 != 0) {
                i4 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(f4)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i4 |= i13;
            }
            i14 = i3 & 1024;
            if (i14 != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            i17 = i3 & 2048;
            if (i17 != 0) {
                i15 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i18 = 32;
                } else {
                    i18 = 16;
                }
                i15 |= i18;
            }
            i19 = i15;
            i20 = i3 & 4096;
            if (i20 != 0) {
                i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            } else {
                i21 = i19;
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i23 = 256;
                    } else {
                        i23 = 128;
                    }
                    i21 |= i23;
                }
                i22 = i21;
            }
            i24 = i3 & 8192;
            if (i24 != 0) {
                i25 = i22;
                if ((i2 & 3072) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                }
                if ((i2 & 24576) != 0) {
                    if ((i3 & 16384) == 0) {
                        i8 = 16384;
                    }
                    i25 |= i8;
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                    i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                }
                if ((i3 & 65536) != 0) {
                    i25 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                }
                if ((306783379 & i4) == 306783378) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    } else {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                    }
                    Modifier modifier17 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                    long j118 = j9;
                    Modifier modifier18 = modifier4;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier17);
                    ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion7.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion7.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion7.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion7.getSetModifier());
                    BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                    f7 = fM122getSheetPeekHeightD9Ej5fM;
                    function8 = lambda$1392012807$material3;
                    function7 = lambda$1768941633$material3;
                    modifier2 = modifier18;
                    z3 = z4;
                    j7 = jM278contentColorForek8zF_U2;
                    j6 = j118;
                    long j119 = containerColor;
                    f8 = fM119getElevationD9Ej5fM;
                    f6 = fM6022constructorimpl;
                    function9 = function10;
                    long j1110 = jM278contentColorForek8zF_U;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    shape2 = expandedShape;
                    j8 = j119;
                    j5 = j1110;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = expandedShape;
                    j5 = jM278contentColorForek8zF_U;
                    modifier2 = modifier;
                    z3 = z;
                    function7 = function5;
                    j6 = j3;
                    j7 = j4;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    j8 = containerColor;
                    bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                    f6 = fM6022constructorimpl;
                    f7 = f;
                    f8 = f4;
                    function8 = function2;
                    function9 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i25 = i22 | 3072;
            if ((i2 & 24576) != 0) {
                if ((i3 & 16384) == 0) {
                    i8 = 16384;
                }
                i25 |= i8;
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
            }
            if ((i3 & 65536) != 0) {
                i25 |= 1572864;
            } else if ((i2 & 1572864) == 0) {
                i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
            }
            if ((306783379 & i4) == 306783378) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                } else {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                }
                Modifier modifier19 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                long j1111 = j9;
                Modifier modifier110 = modifier4;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier19);
                ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion8.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion8.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier8, companion8.getSetModifier());
                BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                f7 = fM122getSheetPeekHeightD9Ej5fM;
                function8 = lambda$1392012807$material3;
                function7 = lambda$1768941633$material3;
                modifier2 = modifier110;
                z3 = z4;
                j7 = jM278contentColorForek8zF_U2;
                j6 = j1111;
                long j1112 = containerColor;
                f8 = fM119getElevationD9Ej5fM;
                f6 = fM6022constructorimpl;
                function9 = function10;
                long j1113 = jM278contentColorForek8zF_U;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                shape2 = expandedShape;
                j8 = j1112;
                j5 = j1113;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = expandedShape;
                j5 = jM278contentColorForek8zF_U;
                modifier2 = modifier;
                z3 = z;
                function7 = function5;
                j6 = j3;
                j7 = j4;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                j8 = containerColor;
                bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                f6 = fM6022constructorimpl;
                f7 = f;
                f8 = f4;
                function8 = function2;
                function9 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                bottomSheetScaffoldState2 = bottomSheetScaffoldState;
                if (composerStartRestartGroup.changed(bottomSheetScaffoldState2)) {
                }
                i4 |= i31;
            } else {
                bottomSheetScaffoldState2 = bottomSheetScaffoldState;
            }
            i4 |= i31;
        } else {
            bottomSheetScaffoldState2 = bottomSheetScaffoldState;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(f)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            i7 = i3 & 16;
            i8 = 8192;
            if (i7 != 0) {
                if ((i & 24576) == 0) {
                    fM121getSheetMaxWidthD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM121getSheetMaxWidthD9Ej5fM)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    expandedShape = shape;
                    if ((i3 & 32) == 0) {
                        i29 = 65536;
                    } else {
                        i29 = 65536;
                    }
                    i4 |= i29;
                } else {
                    expandedShape = shape;
                }
                if ((i & 1572864) == 0) {
                    containerColor = j;
                    if ((i3 & 64) == 0) {
                        i28 = 524288;
                    } else {
                        i28 = 524288;
                    }
                    i4 |= i28;
                } else {
                    containerColor = j;
                }
                if ((i & 12582912) == 0) {
                    jM278contentColorForek8zF_U = j2;
                    if ((i3 & 128) == 0) {
                        i27 = 4194304;
                    } else {
                        i27 = 4194304;
                    }
                    i4 |= i27;
                } else {
                    jM278contentColorForek8zF_U = j2;
                }
                i10 = i3 & 256;
                if (i10 != 0) {
                    i4 |= 100663296;
                    fM6022constructorimpl = f3;
                } else {
                    fM6022constructorimpl = f3;
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i4 |= i11;
                    }
                }
                i12 = i3 & 512;
                if (i12 != 0) {
                    i4 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                i14 = i3 & 1024;
                if (i14 != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                i17 = i3 & 2048;
                if (i17 != 0) {
                    i15 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(z)) {
                        i18 = 32;
                    } else {
                        i18 = 16;
                    }
                    i15 |= i18;
                }
                i19 = i15;
                i20 = i3 & 4096;
                if (i20 != 0) {
                    i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                } else {
                    i21 = i19;
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i23 = 256;
                        } else {
                            i23 = 128;
                        }
                        i21 |= i23;
                    }
                    i22 = i21;
                }
                i24 = i3 & 8192;
                if (i24 != 0) {
                    i25 = i22;
                    if ((i2 & 3072) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                    }
                    if ((i2 & 24576) != 0) {
                        if ((i3 & 16384) == 0) {
                            i8 = 16384;
                        }
                        i25 |= i8;
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                        i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                    }
                    if ((i3 & 65536) != 0) {
                        i25 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                    }
                    if ((306783379 & i4) == 306783378) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        } else {
                            if (i30 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if ((i3 & 4) != 0) {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                                i4 &= -897;
                            } else {
                                bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                            }
                            if (i5 != 0) {
                                fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                            } else {
                                fM122getSheetPeekHeightD9Ej5fM = f;
                            }
                            if (i7 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            }
                            if (i10 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            }
                            if (i12 != 0) {
                                fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                            } else {
                                fM119getElevationD9Ej5fM = f4;
                            }
                            if (i14 != 0) {
                                lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                            } else {
                                lambda$1392012807$material3 = function2;
                            }
                            if (i17 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i20 != 0) {
                                function10 = null;
                            } else {
                                function10 = function4;
                            }
                            if (i24 != 0) {
                                lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                            } else {
                                lambda$1768941633$material3 = function5;
                            }
                            if ((i3 & 16384) != 0) {
                                surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                                i25 &= -57345;
                            } else {
                                surface = j3;
                            }
                            if ((i3 & 32768) != 0) {
                                jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                                i25 &= -458753;
                            } else {
                                jM278contentColorForek8zF_U2 = j4;
                            }
                            j9 = surface;
                            modifier4 = modifier3;
                            i26 = i25;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                        }
                        Modifier modifier111 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                        long j1114 = j9;
                        Modifier modifier112 = modifier4;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier111);
                        ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
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
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy9, companion9.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap9, companion9.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion9.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting()) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        } else {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier9, companion9.getSetModifier());
                        BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                        f7 = fM122getSheetPeekHeightD9Ej5fM;
                        function8 = lambda$1392012807$material3;
                        function7 = lambda$1768941633$material3;
                        modifier2 = modifier112;
                        z3 = z4;
                        j7 = jM278contentColorForek8zF_U2;
                        j6 = j1114;
                        long j1115 = containerColor;
                        f8 = fM119getElevationD9Ej5fM;
                        f6 = fM6022constructorimpl;
                        function9 = function10;
                        long j1116 = jM278contentColorForek8zF_U;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        shape2 = expandedShape;
                        j8 = j1115;
                        j5 = j1116;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = expandedShape;
                        j5 = jM278contentColorForek8zF_U;
                        modifier2 = modifier;
                        z3 = z;
                        function7 = function5;
                        j6 = j3;
                        j7 = j4;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        j8 = containerColor;
                        bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                        f6 = fM6022constructorimpl;
                        f7 = f;
                        f8 = f4;
                        function8 = function2;
                        function9 = function4;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                            public final Object invoke(Object obj, Object obj2) {
                                return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i25 = i22 | 3072;
                if ((i2 & 24576) != 0) {
                    if ((i3 & 16384) == 0) {
                        i8 = 16384;
                    }
                    i25 |= i8;
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                    i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                }
                if ((i3 & 65536) != 0) {
                    i25 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                }
                if ((306783379 & i4) == 306783378) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    } else {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                    }
                    Modifier modifier113 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                    long j1117 = j9;
                    Modifier modifier114 = modifier4;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy10 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier113);
                    ComposeUiNode.Companion companion10 = ComposeUiNode.INSTANCE;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy10, companion10.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap10, companion10.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion10.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier10, companion10.getSetModifier());
                    BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                    f7 = fM122getSheetPeekHeightD9Ej5fM;
                    function8 = lambda$1392012807$material3;
                    function7 = lambda$1768941633$material3;
                    modifier2 = modifier114;
                    z3 = z4;
                    j7 = jM278contentColorForek8zF_U2;
                    j6 = j1117;
                    long j1118 = containerColor;
                    f8 = fM119getElevationD9Ej5fM;
                    f6 = fM6022constructorimpl;
                    function9 = function10;
                    long j1119 = jM278contentColorForek8zF_U;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    shape2 = expandedShape;
                    j8 = j1118;
                    j5 = j1119;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = expandedShape;
                    j5 = jM278contentColorForek8zF_U;
                    modifier2 = modifier;
                    z3 = z;
                    function7 = function5;
                    j6 = j3;
                    j7 = j4;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    j8 = containerColor;
                    bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                    f6 = fM6022constructorimpl;
                    f7 = f;
                    f8 = f4;
                    function8 = function2;
                    function9 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            fM121getSheetMaxWidthD9Ej5fM = f2;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                expandedShape = shape;
                if ((i3 & 32) == 0) {
                    i29 = 65536;
                } else {
                    i29 = 65536;
                }
                i4 |= i29;
            } else {
                expandedShape = shape;
            }
            if ((i & 1572864) == 0) {
                containerColor = j;
                if ((i3 & 64) == 0) {
                    i28 = 524288;
                } else {
                    i28 = 524288;
                }
                i4 |= i28;
            } else {
                containerColor = j;
            }
            if ((i & 12582912) == 0) {
                jM278contentColorForek8zF_U = j2;
                if ((i3 & 128) == 0) {
                    i27 = 4194304;
                } else {
                    i27 = 4194304;
                }
                i4 |= i27;
            } else {
                jM278contentColorForek8zF_U = j2;
            }
            i10 = i3 & 256;
            if (i10 != 0) {
                i4 |= 100663296;
                fM6022constructorimpl = f3;
            } else {
                fM6022constructorimpl = f3;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i4 |= i11;
                }
            }
            i12 = i3 & 512;
            if (i12 != 0) {
                i4 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(f4)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i4 |= i13;
            }
            i14 = i3 & 1024;
            if (i14 != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            i17 = i3 & 2048;
            if (i17 != 0) {
                i15 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i18 = 32;
                } else {
                    i18 = 16;
                }
                i15 |= i18;
            }
            i19 = i15;
            i20 = i3 & 4096;
            if (i20 != 0) {
                i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            } else {
                i21 = i19;
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i23 = 256;
                    } else {
                        i23 = 128;
                    }
                    i21 |= i23;
                }
                i22 = i21;
            }
            i24 = i3 & 8192;
            if (i24 != 0) {
                i25 = i22;
                if ((i2 & 3072) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                }
                if ((i2 & 24576) != 0) {
                    if ((i3 & 16384) == 0) {
                        i8 = 16384;
                    }
                    i25 |= i8;
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                    i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                }
                if ((i3 & 65536) != 0) {
                    i25 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                }
                if ((306783379 & i4) == 306783378) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    } else {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                    }
                    Modifier modifier115 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                    long j11110 = j9;
                    Modifier modifier116 = modifier4;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy11 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier115);
                    ComposeUiNode.Companion companion11 = ComposeUiNode.INSTANCE;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy11, companion11.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap11, companion11.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion11.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier11, companion11.getSetModifier());
                    BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                    f7 = fM122getSheetPeekHeightD9Ej5fM;
                    function8 = lambda$1392012807$material3;
                    function7 = lambda$1768941633$material3;
                    modifier2 = modifier116;
                    z3 = z4;
                    j7 = jM278contentColorForek8zF_U2;
                    j6 = j11110;
                    long j11111 = containerColor;
                    f8 = fM119getElevationD9Ej5fM;
                    f6 = fM6022constructorimpl;
                    function9 = function10;
                    long j11112 = jM278contentColorForek8zF_U;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    shape2 = expandedShape;
                    j8 = j11111;
                    j5 = j11112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = expandedShape;
                    j5 = jM278contentColorForek8zF_U;
                    modifier2 = modifier;
                    z3 = z;
                    function7 = function5;
                    j6 = j3;
                    j7 = j4;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    j8 = containerColor;
                    bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                    f6 = fM6022constructorimpl;
                    f7 = f;
                    f8 = f4;
                    function8 = function2;
                    function9 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i25 = i22 | 3072;
            if ((i2 & 24576) != 0) {
                if ((i3 & 16384) == 0) {
                    i8 = 16384;
                }
                i25 |= i8;
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
            }
            if ((i3 & 65536) != 0) {
                i25 |= 1572864;
            } else if ((i2 & 1572864) == 0) {
                i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
            }
            if ((306783379 & i4) == 306783378) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                } else {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                }
                Modifier modifier117 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                long j11113 = j9;
                Modifier modifier118 = modifier4;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy12 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier117);
                ComposeUiNode.Companion companion12 = ComposeUiNode.INSTANCE;
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy12, companion12.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap12, companion12.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion12.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier12, companion12.getSetModifier());
                BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                f7 = fM122getSheetPeekHeightD9Ej5fM;
                function8 = lambda$1392012807$material3;
                function7 = lambda$1768941633$material3;
                modifier2 = modifier118;
                z3 = z4;
                j7 = jM278contentColorForek8zF_U2;
                j6 = j11113;
                long j11114 = containerColor;
                f8 = fM119getElevationD9Ej5fM;
                f6 = fM6022constructorimpl;
                function9 = function10;
                long j11115 = jM278contentColorForek8zF_U;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                shape2 = expandedShape;
                j8 = j11114;
                j5 = j11115;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = expandedShape;
                j5 = jM278contentColorForek8zF_U;
                modifier2 = modifier;
                z3 = z;
                function7 = function5;
                j6 = j3;
                j7 = j4;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                j8 = containerColor;
                bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                f6 = fM6022constructorimpl;
                f7 = f;
                f8 = f4;
                function8 = function2;
                function9 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        i7 = i3 & 16;
        i8 = 8192;
        if (i7 != 0) {
            if ((i & 24576) == 0) {
                fM121getSheetMaxWidthD9Ej5fM = f2;
                if (composerStartRestartGroup.changed(fM121getSheetMaxWidthD9Ej5fM)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i4 |= i9;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                expandedShape = shape;
                if ((i3 & 32) == 0) {
                    i29 = 65536;
                } else {
                    i29 = 65536;
                }
                i4 |= i29;
            } else {
                expandedShape = shape;
            }
            if ((i & 1572864) == 0) {
                containerColor = j;
                if ((i3 & 64) == 0) {
                    i28 = 524288;
                } else {
                    i28 = 524288;
                }
                i4 |= i28;
            } else {
                containerColor = j;
            }
            if ((i & 12582912) == 0) {
                jM278contentColorForek8zF_U = j2;
                if ((i3 & 128) == 0) {
                    i27 = 4194304;
                } else {
                    i27 = 4194304;
                }
                i4 |= i27;
            } else {
                jM278contentColorForek8zF_U = j2;
            }
            i10 = i3 & 256;
            if (i10 != 0) {
                i4 |= 100663296;
                fM6022constructorimpl = f3;
            } else {
                fM6022constructorimpl = f3;
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i4 |= i11;
                }
            }
            i12 = i3 & 512;
            if (i12 != 0) {
                i4 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(f4)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i4 |= i13;
            }
            i14 = i3 & 1024;
            if (i14 != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            i17 = i3 & 2048;
            if (i17 != 0) {
                i15 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i18 = 32;
                } else {
                    i18 = 16;
                }
                i15 |= i18;
            }
            i19 = i15;
            i20 = i3 & 4096;
            if (i20 != 0) {
                i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            } else {
                i21 = i19;
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i23 = 256;
                    } else {
                        i23 = 128;
                    }
                    i21 |= i23;
                }
                i22 = i21;
            }
            i24 = i3 & 8192;
            if (i24 != 0) {
                i25 = i22;
                if ((i2 & 3072) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
                }
                if ((i2 & 24576) != 0) {
                    if ((i3 & 16384) == 0) {
                        i8 = 16384;
                    }
                    i25 |= i8;
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                    i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
                }
                if ((i3 & 65536) != 0) {
                    i25 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
                }
                if ((306783379 & i4) == 306783378) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    } else {
                        if (i30 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if ((i3 & 4) != 0) {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                            i4 &= -897;
                        } else {
                            bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                        }
                        if (i5 != 0) {
                            fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                        } else {
                            fM122getSheetPeekHeightD9Ej5fM = f;
                        }
                        if (i7 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        }
                        if (i10 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        }
                        if (i12 != 0) {
                            fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                        } else {
                            fM119getElevationD9Ej5fM = f4;
                        }
                        if (i14 != 0) {
                            lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                        } else {
                            lambda$1392012807$material3 = function2;
                        }
                        if (i17 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i20 != 0) {
                            function10 = null;
                        } else {
                            function10 = function4;
                        }
                        if (i24 != 0) {
                            lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                        } else {
                            lambda$1768941633$material3 = function5;
                        }
                        if ((i3 & 16384) != 0) {
                            surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                            i25 &= -57345;
                        } else {
                            surface = j3;
                        }
                        if ((i3 & 32768) != 0) {
                            jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                            i25 &= -458753;
                        } else {
                            jM278contentColorForek8zF_U2 = j4;
                        }
                        j9 = surface;
                        modifier4 = modifier3;
                        i26 = i25;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                    }
                    Modifier modifier119 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                    long j11116 = j9;
                    Modifier modifier1110 = modifier4;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy13 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier119);
                    ComposeUiNode.Companion companion13 = ComposeUiNode.INSTANCE;
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
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy13, companion13.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap13, companion13.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion13.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier13, companion13.getSetModifier());
                    BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                    f7 = fM122getSheetPeekHeightD9Ej5fM;
                    function8 = lambda$1392012807$material3;
                    function7 = lambda$1768941633$material3;
                    modifier2 = modifier1110;
                    z3 = z4;
                    j7 = jM278contentColorForek8zF_U2;
                    j6 = j11116;
                    long j11117 = containerColor;
                    f8 = fM119getElevationD9Ej5fM;
                    f6 = fM6022constructorimpl;
                    function9 = function10;
                    long j11118 = jM278contentColorForek8zF_U;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    shape2 = expandedShape;
                    j8 = j11117;
                    j5 = j11118;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = expandedShape;
                    j5 = jM278contentColorForek8zF_U;
                    modifier2 = modifier;
                    z3 = z;
                    function7 = function5;
                    j6 = j3;
                    j7 = j4;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    j8 = containerColor;
                    bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                    f6 = fM6022constructorimpl;
                    f7 = f;
                    f8 = f4;
                    function8 = function2;
                    function9 = function4;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                        public final Object invoke(Object obj, Object obj2) {
                            return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i25 = i22 | 3072;
            if ((i2 & 24576) != 0) {
                if ((i3 & 16384) == 0) {
                    i8 = 16384;
                }
                i25 |= i8;
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
            }
            if ((i3 & 65536) != 0) {
                i25 |= 1572864;
            } else if ((i2 & 1572864) == 0) {
                i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
            }
            if ((306783379 & i4) == 306783378) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                } else {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                }
                Modifier modifier1111 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                long j11119 = j9;
                Modifier modifier1112 = modifier4;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy14 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1111);
                ComposeUiNode.Companion companion14 = ComposeUiNode.INSTANCE;
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy14, companion14.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap14, companion14.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion14.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier14, companion14.getSetModifier());
                BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                f7 = fM122getSheetPeekHeightD9Ej5fM;
                function8 = lambda$1392012807$material3;
                function7 = lambda$1768941633$material3;
                modifier2 = modifier1112;
                z3 = z4;
                j7 = jM278contentColorForek8zF_U2;
                j6 = j11119;
                long j111110 = containerColor;
                f8 = fM119getElevationD9Ej5fM;
                f6 = fM6022constructorimpl;
                function9 = function10;
                long j111111 = jM278contentColorForek8zF_U;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                shape2 = expandedShape;
                j8 = j111110;
                j5 = j111111;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = expandedShape;
                j5 = jM278contentColorForek8zF_U;
                modifier2 = modifier;
                z3 = z;
                function7 = function5;
                j6 = j3;
                j7 = j4;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                j8 = containerColor;
                bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                f6 = fM6022constructorimpl;
                f7 = f;
                f8 = f4;
                function8 = function2;
                function9 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        fM121getSheetMaxWidthD9Ej5fM = f2;
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            expandedShape = shape;
            if ((i3 & 32) == 0) {
                i29 = 65536;
            } else {
                i29 = 65536;
            }
            i4 |= i29;
        } else {
            expandedShape = shape;
        }
        if ((i & 1572864) == 0) {
            containerColor = j;
            if ((i3 & 64) == 0) {
                i28 = 524288;
            } else {
                i28 = 524288;
            }
            i4 |= i28;
        } else {
            containerColor = j;
        }
        if ((i & 12582912) == 0) {
            jM278contentColorForek8zF_U = j2;
            if ((i3 & 128) == 0) {
                i27 = 4194304;
            } else {
                i27 = 4194304;
            }
            i4 |= i27;
        } else {
            jM278contentColorForek8zF_U = j2;
        }
        i10 = i3 & 256;
        if (i10 != 0) {
            i4 |= 100663296;
            fM6022constructorimpl = f3;
        } else {
            fM6022constructorimpl = f3;
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(fM6022constructorimpl)) {
                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i11 = 33554432;
                }
                i4 |= i11;
            }
        }
        i12 = i3 & 512;
        if (i12 != 0) {
            i4 |= 805306368;
        } else if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changed(f4)) {
                i13 = 536870912;
            } else {
                i13 = 268435456;
            }
            i4 |= i13;
        }
        i14 = i3 & 1024;
        if (i14 != 0) {
            i15 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i16 = 4;
            } else {
                i16 = 2;
            }
            i15 = i2 | i16;
        } else {
            i15 = i2;
        }
        i17 = i3 & 2048;
        if (i17 != 0) {
            i15 |= 48;
        } else if ((i2 & 48) != 0) {
            if (composerStartRestartGroup.changed(z)) {
                i18 = 32;
            } else {
                i18 = 16;
            }
            i15 |= i18;
        }
        i19 = i15;
        i20 = i3 & 4096;
        if (i20 != 0) {
            i22 = i19 | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        } else {
            i21 = i19;
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i23 = 256;
                } else {
                    i23 = 128;
                }
                i21 |= i23;
            }
            i22 = i21;
        }
        i24 = i3 & 8192;
        if (i24 != 0) {
            i25 = i22;
            if ((i2 & 3072) == 0) {
                i25 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
            }
            if ((i2 & 24576) != 0) {
                if ((i3 & 16384) == 0) {
                    i8 = 16384;
                }
                i25 |= i8;
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
            }
            if ((i3 & 65536) != 0) {
                i25 |= 1572864;
            } else if ((i2 & 1572864) == 0) {
                i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
            }
            if ((306783379 & i4) == 306783378) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                } else {
                    if (i30 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if ((i3 & 4) != 0) {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                        i4 &= -897;
                    } else {
                        bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                    }
                    if (i5 != 0) {
                        fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                    } else {
                        fM122getSheetPeekHeightD9Ej5fM = f;
                    }
                    if (i7 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    }
                    if (i10 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    }
                    if (i12 != 0) {
                        fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                    } else {
                        fM119getElevationD9Ej5fM = f4;
                    }
                    if (i14 != 0) {
                        lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                    } else {
                        lambda$1392012807$material3 = function2;
                    }
                    if (i17 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i20 != 0) {
                        function10 = null;
                    } else {
                        function10 = function4;
                    }
                    if (i24 != 0) {
                        lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                    } else {
                        lambda$1768941633$material3 = function5;
                    }
                    if ((i3 & 16384) != 0) {
                        surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                        i25 &= -57345;
                    } else {
                        surface = j3;
                    }
                    if ((i3 & 32768) != 0) {
                        jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                        i25 &= -458753;
                    } else {
                        jM278contentColorForek8zF_U2 = j4;
                    }
                    j9 = surface;
                    modifier4 = modifier3;
                    i26 = i25;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
                }
                Modifier modifier1113 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
                long j111112 = j9;
                Modifier modifier1114 = modifier4;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy15 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1113);
                ComposeUiNode.Companion companion15 = ComposeUiNode.INSTANCE;
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
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy15, companion15.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap15, companion15.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion15.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier15, companion15.getSetModifier());
                BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
                f7 = fM122getSheetPeekHeightD9Ej5fM;
                function8 = lambda$1392012807$material3;
                function7 = lambda$1768941633$material3;
                modifier2 = modifier1114;
                z3 = z4;
                j7 = jM278contentColorForek8zF_U2;
                j6 = j111112;
                long j111113 = containerColor;
                f8 = fM119getElevationD9Ej5fM;
                f6 = fM6022constructorimpl;
                function9 = function10;
                long j111114 = jM278contentColorForek8zF_U;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                shape2 = expandedShape;
                j8 = j111113;
                j5 = j111114;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = expandedShape;
                j5 = jM278contentColorForek8zF_U;
                modifier2 = modifier;
                z3 = z;
                function7 = function5;
                j6 = j3;
                j7 = j4;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                j8 = containerColor;
                bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                f6 = fM6022constructorimpl;
                f7 = f;
                f8 = f4;
                function8 = function2;
                function9 = function4;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i25 = i22 | 3072;
        if ((i2 & 24576) != 0) {
            if ((i3 & 16384) == 0) {
                i8 = 16384;
            }
            i25 |= i8;
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
            i25 |= ((i3 & 32768) == 0 || !composerStartRestartGroup.changed(j4)) ? 65536 : 131072;
        }
        if ((i3 & 65536) != 0) {
            i25 |= 1572864;
        } else if ((i2 & 1572864) == 0) {
            i25 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
        }
        if ((306783379 & i4) == 306783378) {
            z2 = true;
        } else {
            z2 = true;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i30 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i3 & 4) != 0) {
                    bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                    i4 &= -897;
                } else {
                    bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                }
                if (i5 != 0) {
                    fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                } else {
                    fM122getSheetPeekHeightD9Ej5fM = f;
                }
                if (i7 != 0) {
                    fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                }
                if ((i3 & 32) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -458753;
                }
                if ((i3 & 64) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -3670017;
                }
                if ((i3 & 128) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                    i4 &= -29360129;
                }
                if (i10 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                }
                if (i12 != 0) {
                    fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                } else {
                    fM119getElevationD9Ej5fM = f4;
                }
                if (i14 != 0) {
                    lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                } else {
                    lambda$1392012807$material3 = function2;
                }
                if (i17 != 0) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (i20 != 0) {
                    function10 = null;
                } else {
                    function10 = function4;
                }
                if (i24 != 0) {
                    lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                } else {
                    lambda$1768941633$material3 = function5;
                }
                if ((i3 & 16384) != 0) {
                    surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                    i25 &= -57345;
                } else {
                    surface = j3;
                }
                if ((i3 & 32768) != 0) {
                    jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                    i25 &= -458753;
                } else {
                    jM278contentColorForek8zF_U2 = j4;
                }
                j9 = surface;
                modifier4 = modifier3;
                i26 = i25;
            } else {
                if (i30 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i3 & 4) != 0) {
                    bottomSheetScaffoldStateRememberBottomSheetScaffoldState = rememberBottomSheetScaffoldState(null, null, composerStartRestartGroup, 0, 3);
                    i4 &= -897;
                } else {
                    bottomSheetScaffoldStateRememberBottomSheetScaffoldState = bottomSheetScaffoldState2;
                }
                if (i5 != 0) {
                    fM122getSheetPeekHeightD9Ej5fM = BottomSheetDefaults.INSTANCE.m122getSheetPeekHeightD9Ej5fM();
                } else {
                    fM122getSheetPeekHeightD9Ej5fM = f;
                }
                if (i7 != 0) {
                    fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                }
                if ((i3 & 32) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -458753;
                }
                if ((i3 & 64) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -3670017;
                }
                if ((i3 & 128) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 18) & 14);
                    i4 &= -29360129;
                }
                if (i10 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                }
                if (i12 != 0) {
                    fM119getElevationD9Ej5fM = BottomSheetDefaults.INSTANCE.m119getElevationD9Ej5fM();
                } else {
                    fM119getElevationD9Ej5fM = f4;
                }
                if (i14 != 0) {
                    lambda$1392012807$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3();
                } else {
                    lambda$1392012807$material3 = function2;
                }
                if (i17 != 0) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (i20 != 0) {
                    function10 = null;
                } else {
                    function10 = function4;
                }
                if (i24 != 0) {
                    lambda$1768941633$material3 = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3();
                } else {
                    lambda$1768941633$material3 = function5;
                }
                if ((i3 & 16384) != 0) {
                    surface = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getSurface();
                    i25 &= -57345;
                } else {
                    surface = j3;
                }
                if ((i3 & 32768) != 0) {
                    jM278contentColorForek8zF_U2 = ColorSchemeKt.m278contentColorForek8zF_U(surface, composerStartRestartGroup, (i25 >> 12) & 14);
                    i25 &= -458753;
                } else {
                    jM278contentColorForek8zF_U2 = j4;
                }
                j9 = surface;
                modifier4 = modifier3;
                i26 = i25;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(920075480, i4, i26, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
            }
            Modifier modifier1115 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, (Object) null), j9, (Shape) null, 2, (Object) null);
            long j111115 = j9;
            Modifier modifier1116 = modifier4;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy16 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier1115);
            ComposeUiNode.Companion companion16 = ComposeUiNode.INSTANCE;
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
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy16, companion16.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap16, companion16.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion16.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier16, companion16.getSetModifier());
            BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(jM278contentColorForek8zF_U2)), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(bottomSheetScaffoldStateRememberBottomSheetScaffoldState, function10, function6, fM122getSheetPeekHeightD9Ej5fM, fM121getSheetMaxWidthD9Ej5fM, z4, expandedShape, containerColor, jM278contentColorForek8zF_U, fM6022constructorimpl, fM119getElevationD9Ej5fM, lambda$1392012807$material3, function3, lambda$1768941633$material3), composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            bottomSheetScaffoldState3 = bottomSheetScaffoldStateRememberBottomSheetScaffoldState;
            f7 = fM122getSheetPeekHeightD9Ej5fM;
            function8 = lambda$1392012807$material3;
            function7 = lambda$1768941633$material3;
            modifier2 = modifier1116;
            z3 = z4;
            j7 = jM278contentColorForek8zF_U2;
            j6 = j111115;
            long j111116 = containerColor;
            f8 = fM119getElevationD9Ej5fM;
            f6 = fM6022constructorimpl;
            function9 = function10;
            long j111117 = jM278contentColorForek8zF_U;
            f5 = fM121getSheetMaxWidthD9Ej5fM;
            shape2 = expandedShape;
            j8 = j111116;
            j5 = j111117;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            shape2 = expandedShape;
            j5 = jM278contentColorForek8zF_U;
            modifier2 = modifier;
            z3 = z;
            function7 = function5;
            j6 = j3;
            j7 = j4;
            f5 = fM121getSheetMaxWidthD9Ej5fM;
            j8 = containerColor;
            bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
            f6 = fM6022constructorimpl;
            f7 = f;
            f8 = f4;
            function8 = function2;
            function9 = function4;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kz0
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.d(function3, modifier2, bottomSheetScaffoldState3, f7, f5, shape2, j8, j5, f6, f8, function8, z3, function9, function7, j6, j7, function6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BottomSheetScaffoldLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function0<Float> function0, final SheetState sheetState, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1217723575);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(sheetState) ? 131072 : 65536;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i2) != 74898, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1217723575, i2, -1, "androidx.compose.material3.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:413)");
            }
            List listListOf = CollectionsKt.listOf(new Function2[]{function2 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m312getLambda$788244078$material3() : function2, function3, function4, function5});
            boolean z = ((458752 & i2) == 131072) | ((i2 & 57344) == 16384);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(sheetState, function0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) objRememberedValue;
            Modifier.Companion companion = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
            boolean zChanged = composerStartRestartGroup.changed(multiContentMeasurePolicy);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue2;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
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
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mz0
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.f(function2, function3, function4, function5, function0, sheetState, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: StandardBottomSheet-w7I5h1o, reason: not valid java name */
    public static final void m125StandardBottomSheetw7I5h1o(final SheetState sheetState, final float f, final float f2, final boolean z, final Shape shape, final long j, final long j2, final float f3, final float f4, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        Composer composer2;
        Composer.Companion companion;
        Object obj;
        Modifier modifierNestedScroll$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2108849428);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(sheetState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changed(f2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(j) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(j2) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changed(f3) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(f4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changedInstance(function3) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if (composerStartRestartGroup.shouldExecute(((i3 & 306783379) == 306783378 && (i4 & 3) == 2) ? false : true, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2108849428, i3, i4, "androidx.compose.material3.StandardBottomSheet (BottomSheetScaffold.kt:235)");
            }
            MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.DefaultSpatial;
            final FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            final FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            final FiniteAnimationSpec finiteAnimationSpecValue3 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composerStartRestartGroup, 6);
            int i5 = i3 & 14;
            boolean zChangedInstance = (i5 == 4) | composerStartRestartGroup.changedInstance(finiteAnimationSpecValue2) | composerStartRestartGroup.changedInstance(finiteAnimationSpecValue3) | composerStartRestartGroup.changedInstance(finiteAnimationSpecValue);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ez0
                    public final Object invoke() {
                        return BottomSheetScaffoldKt.c(sheetState, finiteAnimationSpecValue2, finiteAnimationSpecValue3, finiteAnimationSpecValue);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.SideEffect((Function0) objRememberedValue, composerStartRestartGroup, 0);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
            Orientation orientation = Orientation.Vertical;
            final float fMo4557toPx0680j_4 = ((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo4557toPx0680j_4(f);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(2049456610);
                Modifier.Companion companion3 = Modifier.INSTANCE;
                boolean zChanged = composerStartRestartGroup.changed(sheetState.getAnchoredDraggableState$material3());
                companion = companion2;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState, orientation, new Function1() { // from class: fz0
                        public final Object invoke(Object obj2) {
                            return BottomSheetScaffoldKt.i(coroutineScope, sheetState, ((Float) obj2).floatValue());
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                obj = null;
                modifierNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion3, (NestedScrollConnection) objRememberedValue3, null, 2, null);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                companion = companion2;
                obj = null;
                composerStartRestartGroup.startReplaceGroup(2049851798);
                composerStartRestartGroup.endReplaceGroup();
                modifierNestedScroll$default = Modifier.INSTANCE;
            }
            int i6 = i3;
            Modifier modifierThen = SizeKt.requiredHeightIn-VpY3zN4$default(SizeKt.fillMaxWidth$default(SizeKt.widthIn-VpY3zN4$default(Modifier.INSTANCE, 0.0f, f2, 1, obj), 0.0f, 1, obj), f, 0.0f, 2, obj).then(modifierNestedScroll$default);
            AnchoredDraggableState<SheetValue> anchoredDraggableState$material3 = sheetState.getAnchoredDraggableState$material3();
            boolean zChanged2 = (i5 == 4) | composerStartRestartGroup.changed(fMo4557toPx0680j_4);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function2() { // from class: gz0
                    public final Object invoke(Object obj2, Object obj3) {
                        return BottomSheetScaffoldKt.j(sheetState, fMo4557toPx0680j_4, (IntSize) obj2, (Constraints) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Modifier modifierVerticalScaleUp = verticalScaleUp(AnchoredDraggableKt.anchoredDraggable$default(AnchoredDraggableKt.draggableAnchors(modifierThen, anchoredDraggableState$material3, orientation, (Function2) objRememberedValue4), sheetState.getAnchoredDraggableState$material3(), orientation, z, false, null, 24, null), sheetState);
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1508311921, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
                public final void invoke(Composer composer3, int i7) {
                    if (!composer3.shouldExecute((i7 & 3) != 2, i7 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1508311921, i7, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:323)");
                    }
                    Modifier modifierVerticalScaleDown = BottomSheetScaffoldKt.verticalScaleDown(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, (Object) null), sheetState);
                    Function2<Composer, Integer, Unit> function4 = function2;
                    Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                    SheetState sheetState2 = sheetState;
                    CoroutineScope coroutineScope2 = coroutineScope;
                    boolean z2 = z;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierVerticalScaleDown);
                    ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion4.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion4.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    if (function4 != null) {
                        composer3.startReplaceGroup(-1044068159);
                        Strings.Companion companion5 = Strings.INSTANCE;
                        SheetDefaultsKt.DragHandleWithTooltip(columnScopeInstance, ComposableLambdaKt.rememberComposableLambda(-511691176, true, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(sheetState2, coroutineScope2, z2, Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0), Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0), Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0), function4), composer3, 54), composer3, 54);
                        composer3.endReplaceGroup();
                    } else {
                        composer3.startReplaceGroup(-1040260677);
                        composer3.endReplaceGroup();
                    }
                    function5.invoke(columnScopeInstance, composer3, 6);
                    composer3.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            int i7 = i6 >> 9;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m954SurfaceT9BRK9s(modifierVerticalScaleUp, shape, j, j2, f3, f4, null, composableLambdaRememberComposableLambda, composer2, (i7 & 112) | 12582912 | (i7 & 896) | (i7 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i7) | (i7 & 458752), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hz0
                public final Object invoke(Object obj2, Object obj3) {
                    return BottomSheetScaffoldKt.a(sheetState, f, f2, z, shape, j, j2, f3, f4, function2, function3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    public static Unit a(SheetState sheetState, float f, float f2, boolean z, Shape shape, long j, long j2, float f3, float f4, Function2 function2, Function3 function3, int i, int i2, Composer composer, int i3) {
        m125StandardBottomSheetw7I5h1o(sheetState, f, f2, z, shape, j, j2, f3, f4, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static Unit b(SheetState sheetState, GraphicsLayerScope graphicsLayerScope) {
        float offset = sheetState.getAnchoredDraggableState$material3().getOffset();
        float fMinAnchor = sheetState.getAnchoredDraggableState$material3().getAnchors().minAnchor();
        float f = offset < fMinAnchor ? fMinAnchor - offset : 0.0f;
        graphicsLayerScope.setScaleY(f > 0.0f ? (Float.intBitsToFloat((int) (graphicsLayerScope.getSize() & 4294967295L)) + f) / Float.intBitsToFloat((int) (4294967295L & graphicsLayerScope.getSize())) : 1.0f);
        graphicsLayerScope.mo3335setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }

    public static Unit c(SheetState sheetState, FiniteAnimationSpec finiteAnimationSpec, FiniteAnimationSpec finiteAnimationSpec2, FiniteAnimationSpec finiteAnimationSpec3) {
        sheetState.setShowMotionSpec$material3(finiteAnimationSpec);
        sheetState.setHideMotionSpec$material3(finiteAnimationSpec2);
        sheetState.setAnchoredDraggableMotionSpec$material3(finiteAnimationSpec3);
        return Unit.INSTANCE;
    }

    public static Unit d(Function3 function3, Modifier modifier, BottomSheetScaffoldState bottomSheetScaffoldState, float f, float f2, Shape shape, long j, long j2, float f3, float f4, Function2 function2, boolean z, Function2 function4, Function3 function5, long j3, long j4, Function3 function6, int i, int i2, int i3, Composer composer, int i4) {
        m124BottomSheetScaffoldsdMYb0k(function3, modifier, bottomSheetScaffoldState, f, f2, shape, j, j2, f3, f4, function2, z, function4, function5, j3, j4, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static Unit e(SheetState sheetState, GraphicsLayerScope graphicsLayerScope) {
        float offset = sheetState.getAnchoredDraggableState$material3().getOffset();
        float fMinAnchor = sheetState.getAnchoredDraggableState$material3().getAnchors().minAnchor();
        float f = offset < fMinAnchor ? fMinAnchor - offset : 0.0f;
        graphicsLayerScope.setScaleY(f > 0.0f ? 1.0f / ((Float.intBitsToFloat((int) (graphicsLayerScope.getSize() & 4294967295L)) + f) / Float.intBitsToFloat((int) (graphicsLayerScope.getSize() & 4294967295L))) : 1.0f);
        graphicsLayerScope.mo3335setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }

    public static Unit f(Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function0 function0, SheetState sheetState, int i, Composer composer, int i2) {
        BottomSheetScaffoldLayout(function2, function3, function4, function5, function0, sheetState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static boolean g(SheetValue sheetValue) {
        return true;
    }

    public static Unit h(SheetState sheetState, float f, float f2, float f3, DraggableAnchorsConfig draggableAnchorsConfig) {
        if (!sheetState.getSkipPartiallyExpanded()) {
            draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, f - f2);
        }
        if (f3 != f2) {
            draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(f - f3, 0.0f));
        }
        if (!sheetState.getSkipHiddenState()) {
            draggableAnchorsConfig.at(SheetValue.Hidden, f);
        }
        return Unit.INSTANCE;
    }

    public static Unit i(CoroutineScope coroutineScope, SheetState sheetState, float f) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BottomSheetScaffoldKt$StandardBottomSheet$nestedScroll$1$1$1(sheetState, f, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0041 A[PHI: r6
      0x0041: PHI (r6v6 androidx.compose.material3.SheetValue) = 
      (r6v5 androidx.compose.material3.SheetValue)
      (r6v7 androidx.compose.material3.SheetValue)
      (r6v8 androidx.compose.material3.SheetValue)
      (r6v9 androidx.compose.material3.SheetValue)
      (r6v10 androidx.compose.material3.SheetValue)
      (r6v11 androidx.compose.material3.SheetValue)
      (r6v12 androidx.compose.material3.SheetValue)
     binds: [B:29:0x007b, B:20:0x0060, B:23:0x0069, B:26:0x0072, B:9:0x003f, B:12:0x0049, B:15:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    public static Pair j(final SheetState sheetState, final float f, IntSize intSize, Constraints constraints) {
        SheetValue sheetValue;
        final float fM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(constraints.getValue());
        final float fM6197unboximpl = (int) (intSize.m6197unboximpl() & 4294967295L);
        DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: lz0
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.h(sheetState, fM5974getMaxHeightimpl, f, fM6197unboximpl, (DraggableAnchorsConfig) obj);
            }
        });
        SheetValue targetValue = sheetState.getAnchoredDraggableState$material3().getTargetValue();
        int i = WhenMappings.$EnumSwitchMapping$0[targetValue.ordinal()];
        if (i == 1) {
            sheetValue = SheetValue.Hidden;
            if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                targetValue = sheetValue;
            }
        } else if (i == 2) {
            sheetValue = SheetValue.PartiallyExpanded;
            if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                targetValue = sheetValue;
            } else {
                sheetValue = SheetValue.Expanded;
                if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                    targetValue = sheetValue;
                } else {
                    sheetValue = SheetValue.Hidden;
                    if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                        targetValue = sheetValue;
                    }
                }
            }
        } else {
            if (i != 3) {
                bu8.a();
                return null;
            }
            sheetValue = SheetValue.Expanded;
            if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                targetValue = sheetValue;
            } else {
                sheetValue = SheetValue.PartiallyExpanded;
                if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                    targetValue = sheetValue;
                } else {
                    sheetValue = SheetValue.Hidden;
                    if (DraggableAnchors.hasAnchorFor(sheetValue)) {
                        targetValue = sheetValue;
                    }
                }
            }
        }
        return TuplesKt.to(DraggableAnchors, targetValue);
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(SheetState sheetState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        Composer composer2;
        if ((i2 & 1) != 0) {
            composer2 = composer;
            sheetState = rememberStandardBottomSheetState(null, null, false, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SnackbarHostState();
                composer2.updateRememberedValue(objRememberedValue);
            }
            snackbarHostState = (SnackbarHostState) objRememberedValue;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1474606134, i, -1, "androidx.compose.material3.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:191)");
        }
        boolean z = ((((i & 14) ^ 6) > 4 && composer2.changed(sheetState)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer2.changed(snackbarHostState)) || (i & 48) == 32);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new BottomSheetScaffoldState(sheetState, snackbarHostState);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return bottomSheetScaffoldState;
    }

    public static final SheetState rememberStandardBottomSheetState(SheetValue sheetValue, Function1<? super SheetValue, Boolean> function1, boolean z, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            sheetValue = SheetValue.PartiallyExpanded;
        }
        SheetValue sheetValue2 = sheetValue;
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: jz0
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(BottomSheetScaffoldKt.g((SheetValue) obj));
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            function1 = (Function1) objRememberedValue;
        }
        Function1<? super SheetValue, Boolean> function2 = function1;
        if ((i2 & 4) != 0) {
            z = true;
        }
        boolean z2 = z;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(678511581, i, -1, "androidx.compose.material3.rememberStandardBottomSheetState (BottomSheetScaffold.kt:215)");
        }
        SheetState sheetStateM862rememberSheetStateAGcomas = SheetDefaultsKt.m862rememberSheetStateAGcomas(false, function2, sheetValue2, z2, 0.0f, 0.0f, composer, (i & 112) | ((i << 6) & 896) | ((i << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE), 49);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return sheetStateM862rememberSheetStateAGcomas;
    }

    public static final Modifier verticalScaleDown(Modifier modifier, final SheetState sheetState) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new Function1() { // from class: dz0
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.e(sheetState, (GraphicsLayerScope) obj);
            }
        });
    }

    public static final Modifier verticalScaleUp(Modifier modifier, final SheetState sheetState) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new Function1() { // from class: iz0
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.b(sheetState, (GraphicsLayerScope) obj);
            }
        });
    }
}
