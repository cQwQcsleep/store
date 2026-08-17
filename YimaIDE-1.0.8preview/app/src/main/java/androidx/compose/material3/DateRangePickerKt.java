package androidx.compose.material3;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.material3.DateRangePickerKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarModel_androidKt;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001aQ\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a[\u0010 \u001a\u00020\u00032\n\u0010!\u001a\u00060\"j\u0002`#2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b$\u0010%\u001aÄ\u0001\u0010&\u001a\u00020\u00012\b\u0010'\u001a\u0004\u0018\u00010\u00152\b\u0010(\u001a\u0004\u0018\u00010\u00152\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u001b2:\u0010+\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00010,2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0001022\u0006\u00104\u001a\u0002052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0003¢\u0006\u0004\b6\u00107\u001a°\u0001\u00108\u001a\u00020\u00012\b\u0010'\u001a\u0004\u0018\u00010\u00152\b\u0010(\u001a\u0004\u0018\u00010\u00152\u0006\u0010)\u001a\u00020\u00152:\u0010+\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00010,2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0001022\u0006\u00104\u001a\u0002052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u00109\u001a°\u0001\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020<2\b\u0010'\u001a\u0004\u0018\u00010\u00152\b\u0010(\u001a\u0004\u0018\u00010\u00152:\u0010+\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00010,2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0001022\u0006\u00104\u001a\u0002052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\tH\u0003¢\u0006\u0002\u0010=\u001ae\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u00152\b\u0010@\u001a\u0004\u0018\u00010\u00152\b\u0010A\u001a\u0004\u0018\u00010\u00152:\u0010+\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00010,H\u0002¢\u0006\u0002\u0010B\u001a#\u0010G\u001a\u00020\u0001*\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0000¢\u0006\u0004\bM\u0010N\u001a.\u0010O\u001a\b\u0012\u0004\u0012\u00020Q0P2\u0006\u0010\u0002\u001a\u00020<2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020UH\u0002\"\u0014\u0010C\u001a\u00020DX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010F\"\u000e\u0010W\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010X\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010Y\u001a\u00020ZX\u0082\u0004¢\u0006\u0004\n\u0002\u0010[¨\u0006\\"}, d2 = {"DateRangePicker", "", "state", "Landroidx/compose/material3/DateRangePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "colors", "Landroidx/compose/material3/DatePickerColors;", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "headline", "showModeToggle", "", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Landroidx/compose/material3/DateRangePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/DatePickerColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "rememberDateRangePickerState", "initialSelectedStartDateMillis", "", "initialSelectedEndDateMillis", "initialDisplayedMonthMillis", "yearRange", "Lkotlin/ranges/IntRange;", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "rememberDateRangePickerState-IlFM19s", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DateRangePickerState;", "DateRangePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateRangePickerState-HVP43zI", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DateRangePickerState;", "SwitchableDateEntryContent", "selectedStartDateMillis", "selectedEndDateMillis", "displayedMonthMillis", "displayMode", "onDatesSelectionChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "startDateMillis", "endDateMillis", "onDisplayedMonthChange", "Lkotlin/Function1;", "monthInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "SwitchableDateEntryContent-eVtQiho", "(Ljava/lang/Long;Ljava/lang/Long;JILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "DateRangePickerContent", "(Ljava/lang/Long;Ljava/lang/Long;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalMonthsList", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "updateDateSelection", "dateInMillis", "currentStartDateMillis", "currentEndDateMillis", "(JLjava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;)V", "CalendarMonthSubheadPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getCalendarMonthSubheadPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "drawRangeBackground", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "selectedRangeInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "color", "Landroidx/compose/ui/graphics/Color;", "drawRangeBackground-mxwnekA", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;Landroidx/compose/material3/SelectedRangeInfo;J)V", "customScrollActions", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "scrollUpLabel", "", "scrollDownLabel", "DateRangePickerTitlePadding", "DateRangePickerHeadlinePadding", "HeaderHeightOffset", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateRangePickerKt {
    private static final PaddingValues CalendarMonthSubheadPadding = PaddingKt.PaddingValues-a9UjIt4$default(Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(20.0f), 0.0f, Dp.m6022constructorimpl(8.0f), 4, (Object) null);
    private static final PaddingValues DateRangePickerTitlePadding = PaddingKt.PaddingValues-a9UjIt4$default(Dp.m6022constructorimpl(64.0f), 0.0f, Dp.m6022constructorimpl(12.0f), 0.0f, 10, (Object) null);
    private static final PaddingValues DateRangePickerHeadlinePadding = PaddingKt.PaddingValues-a9UjIt4$default(Dp.m6022constructorimpl(64.0f), 0.0f, Dp.m6022constructorimpl(12.0f), Dp.m6022constructorimpl(12.0f), 2, (Object) null);
    private static final float HeaderHeightOffset = Dp.m6022constructorimpl(60.0f);

    /* JADX WARN: Code duplicated, block: B:108:0x012d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x012f  */
    /* JADX WARN: Code duplicated, block: B:112:0x0136  */
    /* JADX WARN: Code duplicated, block: B:114:0x0142  */
    /* JADX WARN: Code duplicated, block: B:116:0x015a  */
    /* JADX WARN: Code duplicated, block: B:119:0x0160  */
    /* JADX WARN: Code duplicated, block: B:120:0x0169  */
    /* JADX WARN: Code duplicated, block: B:122:0x016c  */
    /* JADX WARN: Code duplicated, block: B:123:0x0181  */
    /* JADX WARN: Code duplicated, block: B:125:0x0186  */
    /* JADX WARN: Code duplicated, block: B:126:0x0193  */
    /* JADX WARN: Code duplicated, block: B:128:0x0196  */
    /* JADX WARN: Code duplicated, block: B:130:0x0199  */
    /* JADX WARN: Code duplicated, block: B:132:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:134:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:142:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:144:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:146:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:150:0x0205  */
    /* JADX WARN: Code duplicated, block: B:152:0x0221  */
    /* JADX WARN: Code duplicated, block: B:155:0x0285  */
    /* JADX WARN: Code duplicated, block: B:158:0x0290  */
    /* JADX WARN: Code duplicated, block: B:161:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0047  */
    /* JADX WARN: Code duplicated, block: B:28:0x004b  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:33:0x005a  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d  */
    /* JADX WARN: Code duplicated, block: B:38:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x0068  */
    /* JADX WARN: Code duplicated, block: B:42:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0073  */
    /* JADX WARN: Code duplicated, block: B:46:0x0079  */
    /* JADX WARN: Code duplicated, block: B:49:0x007f  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x0088  */
    /* JADX WARN: Code duplicated, block: B:55:0x0090  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:75:0x00be  */
    /* JADX WARN: Code duplicated, block: B:77:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:83:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:85:0x00de  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:88:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:96:0x0104  */
    /* JADX WARN: Code duplicated, block: B:98:0x010c  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void DateRangePicker(final DateRangePickerState dateRangePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean z, FocusRequester focusRequester, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        DatePickerColors datePickerColors2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        int i5;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i7;
        int i8;
        boolean z2;
        int i9;
        int i10;
        int i11;
        boolean z3;
        Composer composer2;
        final DatePickerFormatter datePickerFormatter2;
        final FocusRequester focusRequester2;
        final Modifier modifier3;
        final DatePickerColors datePickerColors3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final boolean z4;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final DatePickerFormatter datePickerFormatter3;
        final DatePickerColors datePickerColorsColors;
        boolean z5;
        int i12;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean z6;
        DatePickerColors datePickerColors4;
        Modifier modifier4;
        int i13;
        FocusRequester focusRequester3;
        Object objRememberedValue;
        Object objRememberedValue2;
        boolean zChanged;
        Object objRememberedValue3;
        CalendarModel calendarModelCreateCalendarModel;
        ComposableLambda composableLambdaRememberComposableLambda;
        int i14;
        boolean zChangedInstance;
        Composer composerStartRestartGroup = composer.startRestartGroup(1969726368);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(dateRangePickerState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i15 = i2 & 2;
        if (i15 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) != 0) {
                    i14 = 128;
                } else {
                    if ((i & 512) == 0) {
                        zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                    } else {
                        zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                    }
                    if (zChangedInstance) {
                        i14 = 256;
                    } else {
                        i14 = 128;
                    }
                }
                i3 |= i14;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    datePickerColors2 = datePickerColors;
                    int i16 = composerStartRestartGroup.changed(datePickerColors2) ? 2048 : 1024;
                    i3 |= i16;
                } else {
                    datePickerColors2 = datePickerColors;
                }
                i3 |= i16;
            } else {
                datePickerColors2 = datePickerColors;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    function2RememberComposableLambda = function2;
                    if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        function4 = function3;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        if ((1572864 & i) == 0) {
                            z2 = z;
                            if (composerStartRestartGroup.changed(z2)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                        i10 = i2 & 128;
                        if (i10 != 0) {
                            i3 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(focusRequester)) {
                                i11 = 8388608;
                            } else {
                                i11 = 4194304;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 4793491) != 4793490) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i15 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i2 & 4) != 0) {
                                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                    }
                                    datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                    i3 &= -897;
                                } else {
                                    datePickerFormatter3 = datePickerFormatter;
                                }
                                if ((i2 & 8) != 0) {
                                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    i3 &= -7169;
                                } else {
                                    datePickerColorsColors = datePickerColors2;
                                }
                                if (i4 != 0) {
                                    z5 = true;
                                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                        public final void invoke(Composer composer3, int i17) {
                                            if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-803011924, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, 54);
                                    i12 = 54;
                                } else {
                                    z5 = true;
                                    i12 = 54;
                                }
                                if (i6 != 0) {
                                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                        public final void invoke(Composer composer3, int i17) {
                                            if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-331385278, i17, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                            }
                                            DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, i12);
                                } else {
                                    function2RememberComposableLambda2 = function4;
                                }
                                if (i8 != 0) {
                                    z2 = true;
                                }
                                if (i10 != 0) {
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = new FocusRequester();
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    int i17 = i3;
                                    focusRequester3 = (FocusRequester) objRememberedValue;
                                    z6 = z2;
                                    datePickerColors4 = datePickerColorsColors;
                                    i13 = i17;
                                    function4 = function2RememberComposableLambda2;
                                    function7 = function2RememberComposableLambda;
                                    modifier4 = modifier2;
                                } else {
                                    function4 = function2RememberComposableLambda2;
                                    function7 = function2RememberComposableLambda;
                                    z6 = z2;
                                    datePickerColors4 = datePickerColorsColors;
                                    modifier4 = modifier2;
                                    i13 = i3;
                                    focusRequester3 = focusRequester;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 4) != 0) {
                                    i3 &= -897;
                                }
                                if ((i2 & 8) != 0) {
                                    i3 &= -7169;
                                }
                                datePickerFormatter3 = datePickerFormatter;
                                i13 = i3;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                focusRequester3 = focusRequester;
                                modifier4 = modifier2;
                                datePickerColors4 = datePickerColors2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                            }
                            zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                    calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                                } else {
                                    calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                                }
                                objRememberedValue3 = calendarModelCreateCalendarModel;
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CalendarModel calendarModel = (CalendarModel) objRememberedValue3;
                            if (z6) {
                                composerStartRestartGroup.startReplaceGroup(-2018438858);
                                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(-2018051234);
                                composerStartRestartGroup.endReplaceGroup();
                                composableLambdaRememberComposableLambda = null;
                            }
                            ComposableLambda composableLambda = composableLambdaRememberComposableLambda;
                            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
                            TextStyle value = TypographyKt.getValue(datePickerModalTokens.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                            float fM6022constructorimpl = Dp.m6022constructorimpl(datePickerModalTokens.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                            FocusRequester focusRequester4 = focusRequester3;
                            AnonymousClass6 anonymousClass6 = new AnonymousClass6(dateRangePickerState, calendarModel, datePickerFormatter3, datePickerColors4, focusRequester4);
                            DatePickerFormatter datePickerFormatter4 = datePickerFormatter3;
                            int i18 = i13 >> 9;
                            composer2 = composerStartRestartGroup;
                            DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda, datePickerColors4, value, fM6022constructorimpl, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass6, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i18 & 112) | (i18 & 896) | ((i13 << 3) & 57344));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            datePickerFormatter2 = datePickerFormatter4;
                            focusRequester2 = focusRequester4;
                            z4 = z6;
                            modifier3 = modifier4;
                            function5 = function7;
                            datePickerColors3 = datePickerColors4;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            datePickerFormatter2 = datePickerFormatter;
                            focusRequester2 = focusRequester;
                            modifier3 = modifier2;
                            datePickerColors3 = datePickerColors2;
                            function5 = function2RememberComposableLambda;
                            z4 = z2;
                        }
                        function6 = function4;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                                public final Object invoke(Object obj, Object obj2) {
                                    return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    z2 = z;
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i19) {
                                        if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i19) {
                                        if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i19, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i19 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i19;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i110) {
                                        if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i110) {
                                        if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i110 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i110;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                        }
                        zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel2 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-2018438858);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-2018051234);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda2 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens2 = DatePickerModalTokens.INSTANCE;
                        TextStyle value2 = TypographyKt.getValue(datePickerModalTokens2.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM6022constructorimpl2 = Dp.m6022constructorimpl(datePickerModalTokens2.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                        FocusRequester focusRequester5 = focusRequester3;
                        AnonymousClass6 anonymousClass7 = new AnonymousClass6(dateRangePickerState, calendarModel2, datePickerFormatter3, datePickerColors4, focusRequester5);
                        DatePickerFormatter datePickerFormatter5 = datePickerFormatter3;
                        int i111 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda2, datePickerColors4, value2, fM6022constructorimpl2, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass7, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111 & 112) | (i111 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter5;
                        focusRequester2 = focusRequester5;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function4 = function3;
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i112) {
                                        if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i112) {
                                        if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i112 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i112;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i113) {
                                        if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i113) {
                                        if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i113 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i113;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                        }
                        zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel3 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-2018438858);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-2018051234);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda3 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens3 = DatePickerModalTokens.INSTANCE;
                        TextStyle value3 = TypographyKt.getValue(datePickerModalTokens3.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM6022constructorimpl3 = Dp.m6022constructorimpl(datePickerModalTokens3.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                        FocusRequester focusRequester6 = focusRequester3;
                        AnonymousClass6 anonymousClass8 = new AnonymousClass6(dateRangePickerState, calendarModel3, datePickerFormatter3, datePickerColors4, focusRequester6);
                        DatePickerFormatter datePickerFormatter6 = datePickerFormatter3;
                        int i114 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda3, datePickerColors4, value3, fM6022constructorimpl3, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass8, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i114 & 112) | (i114 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter6;
                        focusRequester2 = focusRequester6;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z2 = z;
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i115) {
                                    if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i115) {
                                    if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i115 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i115;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i116) {
                                    if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i116) {
                                    if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i116 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i116;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                    }
                    zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel4 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-2018438858);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2018051234);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda4 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens4 = DatePickerModalTokens.INSTANCE;
                    TextStyle value4 = TypographyKt.getValue(datePickerModalTokens4.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM6022constructorimpl4 = Dp.m6022constructorimpl(datePickerModalTokens4.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                    FocusRequester focusRequester7 = focusRequester3;
                    AnonymousClass6 anonymousClass9 = new AnonymousClass6(dateRangePickerState, calendarModel4, datePickerFormatter3, datePickerColors4, focusRequester7);
                    DatePickerFormatter datePickerFormatter7 = datePickerFormatter3;
                    int i117 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda4, datePickerColors4, value4, fM6022constructorimpl4, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass9, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i117 & 112) | (i117 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter7;
                    focusRequester2 = focusRequester7;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function2RememberComposableLambda = function2;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i118) {
                                        if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i118) {
                                        if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i118 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i118;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i119) {
                                        if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i119) {
                                        if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i119 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i119;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                        }
                        zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel5 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-2018438858);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-2018051234);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda5 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens5 = DatePickerModalTokens.INSTANCE;
                        TextStyle value5 = TypographyKt.getValue(datePickerModalTokens5.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM6022constructorimpl5 = Dp.m6022constructorimpl(datePickerModalTokens5.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                        FocusRequester focusRequester8 = focusRequester3;
                        AnonymousClass6 anonymousClass10 = new AnonymousClass6(dateRangePickerState, calendarModel5, datePickerFormatter3, datePickerColors4, focusRequester8);
                        DatePickerFormatter datePickerFormatter8 = datePickerFormatter3;
                        int i1110 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda5, datePickerColors4, value5, fM6022constructorimpl5, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass10, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1110 & 112) | (i1110 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter8;
                        focusRequester2 = focusRequester8;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z2 = z;
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i1111) {
                                    if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i1111) {
                                    if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i1111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1111 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1111;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i1112) {
                                    if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i1112) {
                                    if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i1112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1112 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1112;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                    }
                    zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel6 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-2018438858);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2018051234);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda6 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens6 = DatePickerModalTokens.INSTANCE;
                    TextStyle value6 = TypographyKt.getValue(datePickerModalTokens6.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM6022constructorimpl6 = Dp.m6022constructorimpl(datePickerModalTokens6.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                    FocusRequester focusRequester9 = focusRequester3;
                    AnonymousClass6 anonymousClass11 = new AnonymousClass6(dateRangePickerState, calendarModel6, datePickerFormatter3, datePickerColors4, focusRequester9);
                    DatePickerFormatter datePickerFormatter9 = datePickerFormatter3;
                    int i1113 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda6, datePickerColors4, value6, fM6022constructorimpl6, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass11, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1113 & 112) | (i1113 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter9;
                    focusRequester2 = focusRequester9;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i1114) {
                                    if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i1114) {
                                    if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i1114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1114 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1114;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i1115) {
                                    if (!composer3.shouldExecute((i1115 & 3) != 2, i1115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i1115) {
                                    if (!composer3.shouldExecute((i1115 & 3) != 2, i1115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i1115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i1115 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i1115;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                    }
                    zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel7 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-2018438858);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2018051234);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda7 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens7 = DatePickerModalTokens.INSTANCE;
                    TextStyle value7 = TypographyKt.getValue(datePickerModalTokens7.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM6022constructorimpl7 = Dp.m6022constructorimpl(datePickerModalTokens7.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                    FocusRequester focusRequester10 = focusRequester3;
                    AnonymousClass6 anonymousClass12 = new AnonymousClass6(dateRangePickerState, calendarModel7, datePickerFormatter3, datePickerColors4, focusRequester10);
                    DatePickerFormatter datePickerFormatter10 = datePickerFormatter3;
                    int i1116 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda7, datePickerColors4, value7, fM6022constructorimpl7, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass12, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1116 & 112) | (i1116 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter10;
                    focusRequester2 = focusRequester10;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z2 = z;
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i1117) {
                                if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i1117) {
                                if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i1117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i1117 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i1117;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i1118) {
                                if (!composer3.shouldExecute((i1118 & 3) != 2, i1118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i1118) {
                                if (!composer3.shouldExecute((i1118 & 3) != 2, i1118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i1118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i1118 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i1118;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                }
                zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel8 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-2018438858);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-2018051234);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda8 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens8 = DatePickerModalTokens.INSTANCE;
                TextStyle value8 = TypographyKt.getValue(datePickerModalTokens8.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM6022constructorimpl8 = Dp.m6022constructorimpl(datePickerModalTokens8.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                FocusRequester focusRequester11 = focusRequester3;
                AnonymousClass6 anonymousClass13 = new AnonymousClass6(dateRangePickerState, calendarModel8, datePickerFormatter3, datePickerColors4, focusRequester11);
                DatePickerFormatter datePickerFormatter11 = datePickerFormatter3;
                int i1119 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda8, datePickerColors4, value8, fM6022constructorimpl8, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass13, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1119 & 112) | (i1119 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter11;
                focusRequester2 = focusRequester11;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) != 0) {
                i14 = 128;
            } else {
                if ((i & 512) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i14 = 256;
                } else {
                    i14 = 128;
                }
            }
            i3 |= i14;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                datePickerColors2 = datePickerColors;
                if (composerStartRestartGroup.changed(datePickerColors2)) {
                }
                i3 |= i16;
            } else {
                datePickerColors2 = datePickerColors;
            }
            i3 |= i16;
        } else {
            datePickerColors2 = datePickerColors;
        }
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                function2RememberComposableLambda = function2;
                if (composerStartRestartGroup.changedInstance(function2RememberComposableLambda)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(focusRequester)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 4793491) != 4793490) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i11110) {
                                        if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i11110) {
                                        if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i11110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i11110 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i11110;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        } else {
                            if (i15 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                                i3 &= -897;
                            } else {
                                datePickerFormatter3 = datePickerFormatter;
                            }
                            if ((i2 & 8) != 0) {
                                datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                i3 &= -7169;
                            } else {
                                datePickerColorsColors = datePickerColors2;
                            }
                            if (i4 != 0) {
                                z5 = true;
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                    public final void invoke(Composer composer3, int i11111) {
                                        if (!composer3.shouldExecute((i11111 & 3) != 2, i11111 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-803011924, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                                i12 = 54;
                            } else {
                                z5 = true;
                                i12 = 54;
                            }
                            if (i6 != 0) {
                                function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                    public final void invoke(Composer composer3, int i11111) {
                                        if (!composer3.shouldExecute((i11111 & 3) != 2, i11111 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-331385278, i11111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                        }
                                        DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, i12);
                            } else {
                                function2RememberComposableLambda2 = function4;
                            }
                            if (i8 != 0) {
                                z2 = true;
                            }
                            if (i10 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new FocusRequester();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                int i11111 = i3;
                                focusRequester3 = (FocusRequester) objRememberedValue;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                i13 = i11111;
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                modifier4 = modifier2;
                            } else {
                                function4 = function2RememberComposableLambda2;
                                function7 = function2RememberComposableLambda;
                                z6 = z2;
                                datePickerColors4 = datePickerColorsColors;
                                modifier4 = modifier2;
                                i13 = i3;
                                focusRequester3 = focusRequester;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                        }
                        zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                                calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                            } else {
                                calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                            }
                            objRememberedValue3 = calendarModelCreateCalendarModel;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CalendarModel calendarModel9 = (CalendarModel) objRememberedValue3;
                        if (z6) {
                            composerStartRestartGroup.startReplaceGroup(-2018438858);
                            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-2018051234);
                            composerStartRestartGroup.endReplaceGroup();
                            composableLambdaRememberComposableLambda = null;
                        }
                        ComposableLambda composableLambda9 = composableLambdaRememberComposableLambda;
                        DatePickerModalTokens datePickerModalTokens9 = DatePickerModalTokens.INSTANCE;
                        TextStyle value9 = TypographyKt.getValue(datePickerModalTokens9.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                        float fM6022constructorimpl9 = Dp.m6022constructorimpl(datePickerModalTokens9.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                        FocusRequester focusRequester12 = focusRequester3;
                        AnonymousClass6 anonymousClass14 = new AnonymousClass6(dateRangePickerState, calendarModel9, datePickerFormatter3, datePickerColors4, focusRequester12);
                        DatePickerFormatter datePickerFormatter12 = datePickerFormatter3;
                        int i11112 = i13 >> 9;
                        composer2 = composerStartRestartGroup;
                        DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda9, datePickerColors4, value9, fM6022constructorimpl9, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass14, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i11112 & 112) | (i11112 & 896) | ((i13 << 3) & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        datePickerFormatter2 = datePickerFormatter12;
                        focusRequester2 = focusRequester12;
                        z4 = z6;
                        modifier3 = modifier4;
                        function5 = function7;
                        datePickerColors3 = datePickerColors4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        datePickerFormatter2 = datePickerFormatter;
                        focusRequester2 = focusRequester;
                        modifier3 = modifier2;
                        datePickerColors3 = datePickerColors2;
                        function5 = function2RememberComposableLambda;
                        z4 = z2;
                    }
                    function6 = function4;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                z2 = z;
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i11113) {
                                    if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i11113) {
                                    if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i11113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11113 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11113;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i11114) {
                                    if (!composer3.shouldExecute((i11114 & 3) != 2, i11114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i11114) {
                                    if (!composer3.shouldExecute((i11114 & 3) != 2, i11114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i11114, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11114 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11114;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                    }
                    zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel10 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-2018438858);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2018051234);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda10 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens10 = DatePickerModalTokens.INSTANCE;
                    TextStyle value10 = TypographyKt.getValue(datePickerModalTokens10.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM6022constructorimpl10 = Dp.m6022constructorimpl(datePickerModalTokens10.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                    FocusRequester focusRequester13 = focusRequester3;
                    AnonymousClass6 anonymousClass15 = new AnonymousClass6(dateRangePickerState, calendarModel10, datePickerFormatter3, datePickerColors4, focusRequester13);
                    DatePickerFormatter datePickerFormatter13 = datePickerFormatter3;
                    int i11115 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda10, datePickerColors4, value10, fM6022constructorimpl10, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass15, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i11115 & 112) | (i11115 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter13;
                    focusRequester2 = focusRequester13;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function3;
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i11116) {
                                    if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i11116) {
                                    if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i11116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11116 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11116;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i11117) {
                                    if (!composer3.shouldExecute((i11117 & 3) != 2, i11117 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i11117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i11117) {
                                    if (!composer3.shouldExecute((i11117 & 3) != 2, i11117 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i11117, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i11117 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i11117;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                    }
                    zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel11 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-2018438858);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2018051234);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda11 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens11 = DatePickerModalTokens.INSTANCE;
                    TextStyle value11 = TypographyKt.getValue(datePickerModalTokens11.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM6022constructorimpl11 = Dp.m6022constructorimpl(datePickerModalTokens11.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                    FocusRequester focusRequester14 = focusRequester3;
                    AnonymousClass6 anonymousClass16 = new AnonymousClass6(dateRangePickerState, calendarModel11, datePickerFormatter3, datePickerColors4, focusRequester14);
                    DatePickerFormatter datePickerFormatter14 = datePickerFormatter3;
                    int i11118 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda11, datePickerColors4, value11, fM6022constructorimpl11, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass16, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i11118 & 112) | (i11118 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter14;
                    focusRequester2 = focusRequester14;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z2 = z;
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i11119) {
                                if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i11119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i11119) {
                                if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i11119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i11119 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i11119;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i111110) {
                                if (!composer3.shouldExecute((i111110 & 3) != 2, i111110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i111110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i111110) {
                                if (!composer3.shouldExecute((i111110 & 3) != 2, i111110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i111110, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111110 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111110;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                }
                zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel12 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-2018438858);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-2018051234);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda12 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens12 = DatePickerModalTokens.INSTANCE;
                TextStyle value12 = TypographyKt.getValue(datePickerModalTokens12.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM6022constructorimpl12 = Dp.m6022constructorimpl(datePickerModalTokens12.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                FocusRequester focusRequester15 = focusRequester3;
                AnonymousClass6 anonymousClass17 = new AnonymousClass6(dateRangePickerState, calendarModel12, datePickerFormatter3, datePickerColors4, focusRequester15);
                DatePickerFormatter datePickerFormatter15 = datePickerFormatter3;
                int i111111 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda12, datePickerColors4, value12, fM6022constructorimpl12, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass17, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111111 & 112) | (i111111 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter15;
                focusRequester2 = focusRequester15;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function2RememberComposableLambda = function2;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                function4 = function3;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(focusRequester)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i3 & 4793491) != 4793490) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i111112) {
                                    if (!composer3.shouldExecute((i111112 & 3) != 2, i111112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i111112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i111112) {
                                    if (!composer3.shouldExecute((i111112 & 3) != 2, i111112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i111112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i111112 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i111112;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    } else {
                        if (i15 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                            i3 &= -897;
                        } else {
                            datePickerFormatter3 = datePickerFormatter;
                        }
                        if ((i2 & 8) != 0) {
                            datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            i3 &= -7169;
                        } else {
                            datePickerColorsColors = datePickerColors2;
                        }
                        if (i4 != 0) {
                            z5 = true;
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                                public final void invoke(Composer composer3, int i111113) {
                                    if (!composer3.shouldExecute((i111113 & 3) != 2, i111113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-803011924, i111113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            i12 = 54;
                        } else {
                            z5 = true;
                            i12 = 54;
                        }
                        if (i6 != 0) {
                            function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                                public final void invoke(Composer composer3, int i111113) {
                                    if (!composer3.shouldExecute((i111113 & 3) != 2, i111113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-331385278, i111113, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                    }
                                    DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, i12);
                        } else {
                            function2RememberComposableLambda2 = function4;
                        }
                        if (i8 != 0) {
                            z2 = true;
                        }
                        if (i10 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new FocusRequester();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            int i111113 = i3;
                            focusRequester3 = (FocusRequester) objRememberedValue;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            i13 = i111113;
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            modifier4 = modifier2;
                        } else {
                            function4 = function2RememberComposableLambda2;
                            function7 = function2RememberComposableLambda;
                            z6 = z2;
                            datePickerColors4 = datePickerColorsColors;
                            modifier4 = modifier2;
                            i13 = i3;
                            focusRequester3 = focusRequester;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                    }
                    zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                            calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                        } else {
                            calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                        }
                        objRememberedValue3 = calendarModelCreateCalendarModel;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CalendarModel calendarModel13 = (CalendarModel) objRememberedValue3;
                    if (z6) {
                        composerStartRestartGroup.startReplaceGroup(-2018438858);
                        composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-2018051234);
                        composerStartRestartGroup.endReplaceGroup();
                        composableLambdaRememberComposableLambda = null;
                    }
                    ComposableLambda composableLambda13 = composableLambdaRememberComposableLambda;
                    DatePickerModalTokens datePickerModalTokens13 = DatePickerModalTokens.INSTANCE;
                    TextStyle value13 = TypographyKt.getValue(datePickerModalTokens13.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                    float fM6022constructorimpl13 = Dp.m6022constructorimpl(datePickerModalTokens13.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                    FocusRequester focusRequester16 = focusRequester3;
                    AnonymousClass6 anonymousClass18 = new AnonymousClass6(dateRangePickerState, calendarModel13, datePickerFormatter3, datePickerColors4, focusRequester16);
                    DatePickerFormatter datePickerFormatter16 = datePickerFormatter3;
                    int i111114 = i13 >> 9;
                    composer2 = composerStartRestartGroup;
                    DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda13, datePickerColors4, value13, fM6022constructorimpl13, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass18, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111114 & 112) | (i111114 & 896) | ((i13 << 3) & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    datePickerFormatter2 = datePickerFormatter16;
                    focusRequester2 = focusRequester16;
                    z4 = z6;
                    modifier3 = modifier4;
                    function5 = function7;
                    datePickerColors3 = datePickerColors4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    datePickerFormatter2 = datePickerFormatter;
                    focusRequester2 = focusRequester;
                    modifier3 = modifier2;
                    datePickerColors3 = datePickerColors2;
                    function5 = function2RememberComposableLambda;
                    z4 = z2;
                }
                function6 = function4;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            z2 = z;
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i111115) {
                                if (!composer3.shouldExecute((i111115 & 3) != 2, i111115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i111115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i111115) {
                                if (!composer3.shouldExecute((i111115 & 3) != 2, i111115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i111115, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111115 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111115;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i111116) {
                                if (!composer3.shouldExecute((i111116 & 3) != 2, i111116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i111116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i111116) {
                                if (!composer3.shouldExecute((i111116 & 3) != 2, i111116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i111116, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111116 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111116;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                }
                zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel14 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-2018438858);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-2018051234);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda14 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens14 = DatePickerModalTokens.INSTANCE;
                TextStyle value14 = TypographyKt.getValue(datePickerModalTokens14.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM6022constructorimpl14 = Dp.m6022constructorimpl(datePickerModalTokens14.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                FocusRequester focusRequester17 = focusRequester3;
                AnonymousClass6 anonymousClass19 = new AnonymousClass6(dateRangePickerState, calendarModel14, datePickerFormatter3, datePickerColors4, focusRequester17);
                DatePickerFormatter datePickerFormatter17 = datePickerFormatter3;
                int i111117 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda14, datePickerColors4, value14, fM6022constructorimpl14, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass19, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i111117 & 112) | (i111117 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter17;
                focusRequester2 = focusRequester17;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function4 = function3;
        i8 = i2 & 64;
        if (i8 != 0) {
            if ((1572864 & i) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(focusRequester)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i3 & 4793491) != 4793490) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i111118) {
                                if (!composer3.shouldExecute((i111118 & 3) != 2, i111118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i111118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i111118) {
                                if (!composer3.shouldExecute((i111118 & 3) != 2, i111118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i111118, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111118 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111118;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                } else {
                    if (i15 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                        i3 &= -897;
                    } else {
                        datePickerFormatter3 = datePickerFormatter;
                    }
                    if ((i2 & 8) != 0) {
                        datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i3 &= -7169;
                    } else {
                        datePickerColorsColors = datePickerColors2;
                    }
                    if (i4 != 0) {
                        z5 = true;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                            public final void invoke(Composer composer3, int i111119) {
                                if (!composer3.shouldExecute((i111119 & 3) != 2, i111119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-803011924, i111119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                                }
                                DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        i12 = 54;
                    } else {
                        z5 = true;
                        i12 = 54;
                    }
                    if (i6 != 0) {
                        function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                            public final void invoke(Composer composer3, int i111119) {
                                if (!composer3.shouldExecute((i111119 & 3) != 2, i111119 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-331385278, i111119, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                                }
                                DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, i12);
                    } else {
                        function2RememberComposableLambda2 = function4;
                    }
                    if (i8 != 0) {
                        z2 = true;
                    }
                    if (i10 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new FocusRequester();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        int i111119 = i3;
                        focusRequester3 = (FocusRequester) objRememberedValue;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        i13 = i111119;
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        modifier4 = modifier2;
                    } else {
                        function4 = function2RememberComposableLambda2;
                        function7 = function2RememberComposableLambda;
                        z6 = z2;
                        datePickerColors4 = datePickerColorsColors;
                        modifier4 = modifier2;
                        i13 = i3;
                        focusRequester3 = focusRequester;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
                }
                zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                        calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                    } else {
                        calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                    }
                    objRememberedValue3 = calendarModelCreateCalendarModel;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                CalendarModel calendarModel15 = (CalendarModel) objRememberedValue3;
                if (z6) {
                    composerStartRestartGroup.startReplaceGroup(-2018438858);
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-2018051234);
                    composerStartRestartGroup.endReplaceGroup();
                    composableLambdaRememberComposableLambda = null;
                }
                ComposableLambda composableLambda15 = composableLambdaRememberComposableLambda;
                DatePickerModalTokens datePickerModalTokens15 = DatePickerModalTokens.INSTANCE;
                TextStyle value15 = TypographyKt.getValue(datePickerModalTokens15.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
                float fM6022constructorimpl15 = Dp.m6022constructorimpl(datePickerModalTokens15.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
                FocusRequester focusRequester18 = focusRequester3;
                AnonymousClass6 anonymousClass110 = new AnonymousClass6(dateRangePickerState, calendarModel15, datePickerFormatter3, datePickerColors4, focusRequester18);
                DatePickerFormatter datePickerFormatter18 = datePickerFormatter3;
                int i1111110 = i13 >> 9;
                composer2 = composerStartRestartGroup;
                DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda15, datePickerColors4, value15, fM6022constructorimpl15, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass110, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1111110 & 112) | (i1111110 & 896) | ((i13 << 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                datePickerFormatter2 = datePickerFormatter18;
                focusRequester2 = focusRequester18;
                z4 = z6;
                modifier3 = modifier4;
                function5 = function7;
                datePickerColors3 = datePickerColors4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                datePickerFormatter2 = datePickerFormatter;
                focusRequester2 = focusRequester;
                modifier3 = modifier2;
                datePickerColors3 = datePickerColors2;
                function5 = function2RememberComposableLambda;
                z4 = z2;
            }
            function6 = function4;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        z2 = z;
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(focusRequester)) {
                i11 = 8388608;
            } else {
                i11 = 4194304;
            }
            i3 |= i11;
        }
        if ((i3 & 4793491) != 4793490) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i15 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                    i3 &= -897;
                } else {
                    datePickerFormatter3 = datePickerFormatter;
                }
                if ((i2 & 8) != 0) {
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    datePickerColorsColors = datePickerColors2;
                }
                if (i4 != 0) {
                    z5 = true;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                        public final void invoke(Composer composer3, int i1111111) {
                            if (!composer3.shouldExecute((i1111111 & 3) != 2, i1111111 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-803011924, i1111111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                            }
                            DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    i12 = 54;
                } else {
                    z5 = true;
                    i12 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                        public final void invoke(Composer composer3, int i1111111) {
                            if (!composer3.shouldExecute((i1111111 & 3) != 2, i1111111 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-331385278, i1111111, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                            }
                            DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, i12);
                } else {
                    function2RememberComposableLambda2 = function4;
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    int i1111111 = i3;
                    focusRequester3 = (FocusRequester) objRememberedValue;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    i13 = i1111111;
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    modifier4 = modifier2;
                } else {
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    modifier4 = modifier2;
                    i13 = i3;
                    focusRequester3 = focusRequester;
                }
            } else {
                if (i15 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    datePickerFormatter3 = (DatePickerFormatter) objRememberedValue2;
                    i3 &= -897;
                } else {
                    datePickerFormatter3 = datePickerFormatter;
                }
                if ((i2 & 8) != 0) {
                    datePickerColorsColors = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i3 &= -7169;
                } else {
                    datePickerColorsColors = datePickerColors2;
                }
                if (i4 != 0) {
                    z5 = true;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-803011924, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.2
                        public final void invoke(Composer composer3, int i1111112) {
                            if (!composer3.shouldExecute((i1111112 & 3) != 2, i1111112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-803011924, i1111112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:105)");
                            }
                            DateRangePickerDefaults.INSTANCE.m382DateRangePickerTitleFNtVw6o(dateRangePickerState.mo390getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerTitlePadding), datePickerColorsColors.getTitleContentColor(), composer3, 3120, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    i12 = 54;
                } else {
                    z5 = true;
                    i12 = 54;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-331385278, z5, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt.DateRangePicker.3
                        public final void invoke(Composer composer3, int i1111112) {
                            if (!composer3.shouldExecute((i1111112 & 3) != 2, i1111112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-331385278, i1111112, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:112)");
                            }
                            DateRangePickerDefaults.INSTANCE.m381DateRangePickerHeadlineqS89cEg(dateRangePickerState.getSelectedStartDateMillis(), dateRangePickerState.getSelectedEndDateMillis(), dateRangePickerState.mo390getDisplayModejFl4v0(), datePickerFormatter3, PaddingKt.padding(Modifier.INSTANCE, DateRangePickerKt.DateRangePickerHeadlinePadding), datePickerColorsColors.getHeadlineContentColor(), composer3, 1597440, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, i12);
                } else {
                    function2RememberComposableLambda2 = function4;
                }
                if (i8 != 0) {
                    z2 = true;
                }
                if (i10 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new FocusRequester();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    int i1111112 = i3;
                    focusRequester3 = (FocusRequester) objRememberedValue;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    i13 = i1111112;
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    modifier4 = modifier2;
                } else {
                    function4 = function2RememberComposableLambda2;
                    function7 = function2RememberComposableLambda;
                    z6 = z2;
                    datePickerColors4 = datePickerColorsColors;
                    modifier4 = modifier2;
                    i13 = i3;
                    focusRequester3 = focusRequester;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1969726368, i13, -1, "androidx.compose.material3.DateRangePicker (DateRangePicker.kt:123)");
            }
            zChanged = composerStartRestartGroup.changed(dateRangePickerState.getLocale());
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                    calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                } else {
                    calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                }
                objRememberedValue3 = calendarModelCreateCalendarModel;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                if (dateRangePickerState instanceof BaseDatePickerStateImpl) {
                    calendarModelCreateCalendarModel = ((BaseDatePickerStateImpl) dateRangePickerState).getCalendarModel();
                } else {
                    calendarModelCreateCalendarModel = CalendarModel_androidKt.createCalendarModel(dateRangePickerState.getLocale());
                }
                objRememberedValue3 = calendarModelCreateCalendarModel;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            CalendarModel calendarModel16 = (CalendarModel) objRememberedValue3;
            if (z6) {
                composerStartRestartGroup.startReplaceGroup(-2018438858);
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1343236786, true, new AnonymousClass5(dateRangePickerState, datePickerColors4), composerStartRestartGroup, 54);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-2018051234);
                composerStartRestartGroup.endReplaceGroup();
                composableLambdaRememberComposableLambda = null;
            }
            ComposableLambda composableLambda16 = composableLambdaRememberComposableLambda;
            DatePickerModalTokens datePickerModalTokens16 = DatePickerModalTokens.INSTANCE;
            TextStyle value16 = TypographyKt.getValue(datePickerModalTokens16.getRangeSelectionHeaderHeadlineFont(), composerStartRestartGroup, 6);
            float fM6022constructorimpl16 = Dp.m6022constructorimpl(datePickerModalTokens16.m1707getRangeSelectionHeaderContainerHeightD9Ej5fM() - HeaderHeightOffset);
            FocusRequester focusRequester19 = focusRequester3;
            AnonymousClass6 anonymousClass111 = new AnonymousClass6(dateRangePickerState, calendarModel16, datePickerFormatter3, datePickerColors4, focusRequester19);
            DatePickerFormatter datePickerFormatter19 = datePickerFormatter3;
            int i1111113 = i13 >> 9;
            composer2 = composerStartRestartGroup;
            DatePickerKt.m363DateEntryContainerau3_HiA(modifier4, function7, function4, composableLambda16, datePickerColors4, value16, fM6022constructorimpl16, ComposableLambdaKt.rememberComposableLambda(684885105, true, anonymousClass111, composerStartRestartGroup, 54), composer2, ((i13 >> 3) & 14) | 14155776 | (i1111113 & 112) | (i1111113 & 896) | ((i13 << 3) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            datePickerFormatter2 = datePickerFormatter19;
            focusRequester2 = focusRequester19;
            z4 = z6;
            modifier3 = modifier4;
            function5 = function7;
            datePickerColors3 = datePickerColors4;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            datePickerFormatter2 = datePickerFormatter;
            focusRequester2 = focusRequester;
            modifier3 = modifier2;
            datePickerColors3 = datePickerColors2;
            function5 = function2RememberComposableLambda;
            z4 = z2;
        }
        function6 = function4;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: za3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerKt.e(dateRangePickerState, modifier3, datePickerFormatter2, datePickerColors3, function5, function6, z4, focusRequester2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DateRangePickerContent(final Long l, final Long l2, final long j, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Long l3;
        Function2<? super Long, ? super Long, Unit> function3;
        Function1<? super Long, Unit> function4;
        SelectableDates selectableDates2;
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(-787063721);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            l3 = l2;
            i2 |= composerStartRestartGroup.changed(l3) ? 32 : 16;
        } else {
            l3 = l2;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            function3 = function2;
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        } else {
            function3 = function2;
        }
        if ((i & 24576) == 0) {
            function4 = function1;
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function1;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= (16777216 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            selectableDates2 = selectableDates;
            i2 |= composerStartRestartGroup.changed(selectableDates2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((i & 805306368) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 536870912 : 268435456;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 306783379) != 306783378, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-787063721, i2, -1, "androidx.compose.material3.DateRangePickerContent (DateRangePicker.kt:764)");
            }
            int iCoerceAtLeast = RangesKt.coerceAtLeast(calendarModel.getMonth(j).indexIn(intRange), 0);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(iCoerceAtLeast, 0, composerStartRestartGroup, 0, 2);
            Integer numValueOf = Integer.valueOf(iCoerceAtLeast);
            boolean zChanged = composerStartRestartGroup.changed(lazyListStateRememberLazyListState) | composerStartRestartGroup.changed(iCoerceAtLeast);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = null;
                objRememberedValue = new DateRangePickerKt$DateRangePickerContent$1$1(lazyListStateRememberLazyListState, iCoerceAtLeast, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                obj = null;
            }
            EffectsKt.LaunchedEffect(numValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, composerStartRestartGroup, 0);
            Modifier modifier = PaddingKt.padding-VpY3zN4$default(Modifier.INSTANCE, DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, obj);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion.getConstructor();
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            DatePickerKt.WeekDays(datePickerColors, calendarModel, composerStartRestartGroup, ((i2 >> 27) & 14) | ((i2 >> 12) & 112));
            VerticalMonthsList(lazyListStateRememberLazyListState, l, l3, function3, function4, calendarModel, intRange, datePickerFormatter, selectableDates2, datePickerColors, composerStartRestartGroup, ((i2 << 3) & PointerIconCompat.TYPE_TEXT) | (i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i2) | (458752 & i2) | (3670016 & i2) | (29360128 & i2) | (234881024 & i2) | (1879048192 & i2));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xa3
                public final Object invoke(Object obj2, Object obj3) {
                    return DateRangePickerKt.a(l, l2, j, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI, reason: not valid java name */
    public static final DateRangePickerState m383DateRangePickerStateHVP43zI(Locale locale, Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates) {
        return new DateRangePickerStateImpl(l, l2, l3, intRange, i, selectableDates, locale, null);
    }

    /* JADX INFO: renamed from: DateRangePickerState-HVP43zI$default, reason: not valid java name */
    public static /* synthetic */ DateRangePickerState m384DateRangePickerStateHVP43zI$default(Locale locale, Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            l = null;
        }
        if ((i2 & 4) != 0) {
            l2 = null;
        }
        if ((i2 & 8) != 0) {
            l3 = l;
        }
        if ((i2 & 16) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i2 & 32) != 0) {
            i = DisplayMode.INSTANCE.m412getPickerjFl4v0();
        }
        if ((i2 & 64) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        return m383DateRangePickerStateHVP43zI(locale, l, l2, l3, intRange, i, selectableDates);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-eVtQiho, reason: not valid java name */
    public static final void m385SwitchableDateEntryContenteVtQiho(final Long l, final Long l2, final long j, final int i, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, final FocusRequester focusRequester, Composer composer, final int i2, final int i3) {
        int i4;
        Long l3;
        Function2<? super Long, ? super Long, Unit> function3;
        Function1<? super Long, Unit> function4;
        int i5;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(621028059);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            l3 = l2;
            i4 |= composerStartRestartGroup.changed(l3) ? 32 : 16;
        } else {
            l3 = l2;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            function3 = function2;
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        } else {
            function3 = function2;
        }
        if ((196608 & i2) == 0) {
            function4 = function1;
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function1;
        }
        if ((i2 & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(calendarModel) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(intRange) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= (i2 & 134217728) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changed(selectableDates) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(focusRequester) ? 32 : 16;
        }
        int i6 = i5;
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (i6 & 19) == 18) ? false : true, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(621028059, i4, i6, "androidx.compose.material3.SwitchableDateEntryContent (DateRangePicker.kt:708)");
            }
            FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composerStartRestartGroup, 6);
            Modifier.Companion companion = Modifier.INSTANCE;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: sa3
                    public final Object invoke(Object obj) {
                        return DateRangePickerKt.g((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final Long l4 = l3;
            final Function2<? super Long, ? super Long, Unit> function5 = function3;
            final Function1<? super Long, Unit> function6 = function4;
            composer2 = composerStartRestartGroup;
            CrossfadeKt.Crossfade(DisplayMode.m404boximpl(i), SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null), finiteAnimationSpecValue, (String) null, ComposableLambdaKt.rememberComposableLambda(-773828161, true, new Function3<DisplayMode, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode, Composer composer3, Integer num) {
                    m389invokeQujVXRc(displayMode.getValue(), composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-QujVXRc, reason: not valid java name */
                public final void m389invokeQujVXRc(int i7, Composer composer3, int i8) {
                    int i9;
                    if ((i8 & 6) == 0) {
                        i9 = i8 | (composer3.changed(i7) ? 4 : 2);
                    } else {
                        i9 = i8;
                    }
                    if (!composer3.shouldExecute((i9 & 19) != 18, i9 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-773828161, i9, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DateRangePicker.kt:721)");
                    }
                    DisplayMode.Companion companion2 = DisplayMode.INSTANCE;
                    if (DisplayMode.m407equalsimpl0(i7, companion2.m412getPickerjFl4v0())) {
                        composer3.startReplaceGroup(-619517270);
                        DateRangePickerKt.DateRangePickerContent(l, l4, j, function5, function6, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer3, 0);
                        composer3.endReplaceGroup();
                    } else if (DisplayMode.m407equalsimpl0(i7, companion2.m411getInputjFl4v0())) {
                        composer3.startReplaceGroup(-619495944);
                        DateRangeInputKt.DateRangeInputContent(l, l4, function5, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer3, 0);
                        composer3.endReplaceGroup();
                    } else {
                        composer3.startReplaceGroup(-2023979101);
                        composer3.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composer2, ((i4 >> 9) & 14) | 24576, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ta3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerKt.f(l, l2, j, i, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalMonthsList(LazyListState lazyListState, final Long l, final Long l2, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, Composer composer, final int i) {
        int i2;
        Long l3;
        Long l4;
        Object month;
        Object dateRangePickerKt$VerticalMonthsList$2$1;
        final LazyListState lazyListState2 = lazyListState;
        Composer composerStartRestartGroup = composer.startRestartGroup(1257365001);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(lazyListState2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            l3 = l;
            i2 |= composerStartRestartGroup.changed(l3) ? 32 : 16;
        } else {
            l3 = l;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            l4 = l2;
            i2 |= composerStartRestartGroup.changed(l4) ? 256 : 128;
        } else {
            l4 = l2;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(intRange) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= (16777216 & i) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 536870912 : 268435456;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 306783379) != 306783378, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1257365001, i2, -1, "androidx.compose.material3.VerticalMonthsList (DateRangePicker.kt:812)");
            }
            CalendarDate today = calendarModel.getToday();
            boolean zChanged = composerStartRestartGroup.changed(intRange);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                month = calendarModel.getMonth(intRange.getFirst(), 1);
                composerStartRestartGroup.updateRememberedValue(month);
            } else {
                month = objRememberedValue;
            }
            int i3 = i2;
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getDateLabelTextFont(), composerStartRestartGroup, 6), ComposableLambdaKt.rememberComposableLambda(1090773432, true, new AnonymousClass1(l3, l4, function2, lazyListState2, intRange, calendarModel, (CalendarMonth) month, datePickerFormatter, datePickerColors, today, selectableDates), composerStartRestartGroup, 54), composerStartRestartGroup, 48);
            int i4 = i3 & 14;
            boolean zChangedInstance = (i4 == 4) | ((i3 & 57344) == 16384) | composerStartRestartGroup.changedInstance(calendarModel) | composerStartRestartGroup.changedInstance(intRange);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                lazyListState2 = lazyListState;
                dateRangePickerKt$VerticalMonthsList$2$1 = new DateRangePickerKt$VerticalMonthsList$2$1(lazyListState2, function1, calendarModel, intRange, null);
                composerStartRestartGroup.updateRememberedValue(dateRangePickerKt$VerticalMonthsList$2$1);
            } else {
                dateRangePickerKt$VerticalMonthsList$2$1 = objRememberedValue2;
                lazyListState2 = lazyListState;
            }
            EffectsKt.LaunchedEffect(lazyListState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) dateRangePickerKt$VerticalMonthsList$2$1, composerStartRestartGroup, i4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ya3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerKt.b(lazyListState2, l, l2, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Long l, Long l2, long j, Function2 function2, Function1 function1, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        DateRangePickerContent(l, l2, j, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(LazyListState lazyListState, Long l, Long l2, Function2 function2, Function1 function1, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i, Composer composer, int i2) {
        VerticalMonthsList(lazyListState, l, l2, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static boolean c(LazyListState lazyListState, CoroutineScope coroutineScope) {
        if (!lazyListState.getCanScrollBackward()) {
            return false;
        }
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DateRangePickerKt$customScrollActions$scrollUpAction$1$1(lazyListState, null), 3, (Object) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomAccessibilityAction> customScrollActions(final LazyListState lazyListState, final CoroutineScope coroutineScope, String str, String str2) {
        return CollectionsKt.listOf(new CustomAccessibilityAction[]{new CustomAccessibilityAction(str, new Function0() { // from class: ua3
            public final Object invoke() {
                return Boolean.valueOf(DateRangePickerKt.c(lazyListState, coroutineScope));
            }
        }), new CustomAccessibilityAction(str2, new Function0() { // from class: va3
            public final Object invoke() {
                return Boolean.valueOf(DateRangePickerKt.h(lazyListState, coroutineScope));
            }
        })});
    }

    public static DateRangePickerStateImpl d(Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, Locale locale) {
        return new DateRangePickerStateImpl(l, l2, l3, intRange, i, selectableDates, locale, null);
    }

    /* JADX INFO: renamed from: drawRangeBackground-mxwnekA, reason: not valid java name */
    public static final void m387drawRangeBackgroundmxwnekA(ContentDrawScope contentDrawScope, SelectedRangeInfo selectedRangeInfo, long j) {
        float fMo4557toPx0680j_4 = contentDrawScope.mo4557toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float fMo4557toPx0680j_5 = contentDrawScope.mo4557toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float fMo4557toPx0680j_6 = contentDrawScope.mo4557toPx0680j_4(DatePickerModalTokens.INSTANCE.m1700getDateStateLayerHeightD9Ej5fM());
        float f = (fMo4557toPx0680j_5 - fMo4557toPx0680j_6) / 2.0f;
        float fIntBitsToFloat = (Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32)) - (7.0f * fMo4557toPx0680j_4)) / 7.0f;
        long gridStartCoordinates = selectedRangeInfo.getGridStartCoordinates();
        int iM6150getXimpl = IntOffset.m6150getXimpl(gridStartCoordinates);
        int iM6151getYimpl = IntOffset.m6151getYimpl(gridStartCoordinates);
        long gridEndCoordinates = selectedRangeInfo.getGridEndCoordinates();
        int iM6150getXimpl2 = IntOffset.m6150getXimpl(gridEndCoordinates);
        int iM6151getYimpl2 = IntOffset.m6151getYimpl(gridEndCoordinates);
        float f2 = fMo4557toPx0680j_4 + fIntBitsToFloat;
        float f3 = fIntBitsToFloat / 2.0f;
        float fIntBitsToFloat2 = (iM6150getXimpl * f2) + (selectedRangeInfo.getFirstIsSelectionStart() ? fMo4557toPx0680j_4 / 2.0f : 0.0f) + f3;
        float f4 = (iM6151getYimpl * fMo4557toPx0680j_5) + f;
        float f5 = iM6150getXimpl2 * f2;
        if (selectedRangeInfo.getLastIsSelectionEnd()) {
            fMo4557toPx0680j_4 /= 2.0f;
        }
        float fIntBitsToFloat3 = f5 + fMo4557toPx0680j_4 + f3;
        float f6 = (iM6151getYimpl2 * fMo4557toPx0680j_5) + f;
        boolean z = contentDrawScope.getLayoutDirection() == LayoutDirection.Rtl;
        if (z) {
            fIntBitsToFloat2 = Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32)) - fIntBitsToFloat2;
            fIntBitsToFloat3 = Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32)) - fIntBitsToFloat3;
        }
        float fIntBitsToFloat4 = fIntBitsToFloat3;
        DrawScope.m3702drawRectnJ9OG0$default(contentDrawScope, j, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat2)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), Size.m2949constructorimpl((((long) Float.floatToRawIntBits(iM6151getYimpl == iM6151getYimpl2 ? fIntBitsToFloat4 - fIntBitsToFloat2 : z ? -fIntBitsToFloat2 : Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32)) - fIntBitsToFloat2)) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_6)) & 4294967295L)), 0.0f, null, null, 0, 120, null);
        if (iM6151getYimpl != iM6151getYimpl2) {
            for (int i = (iM6151getYimpl2 - iM6151getYimpl) - 1; i > 0; i--) {
                DrawScope.m3702drawRectnJ9OG0$default(contentDrawScope, j, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(f4 + (i * fMo4557toPx0680j_5))) & 4294967295L)), Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_6)) & 4294967295L)), 0.0f, null, null, 0, 120, null);
            }
            long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(contentDrawScope.getLayoutDirection() != LayoutDirection.Ltr ? Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32)) : 0.0f)) << 32) | (((long) Float.floatToRawIntBits(f6)) & 4294967295L));
            if (z) {
                fIntBitsToFloat4 -= Float.intBitsToFloat((int) (contentDrawScope.mo3708getSizeNHjbRc() >> 32));
            }
            DrawScope.m3702drawRectnJ9OG0$default(contentDrawScope, j, jM2881constructorimpl, Size.m2949constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat4)) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_6)) & 4294967295L)), 0.0f, null, null, 0, 120, null);
        }
    }

    public static Unit e(DateRangePickerState dateRangePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, Function2 function2, Function2 function3, boolean z, FocusRequester focusRequester, int i, int i2, Composer composer, int i3) {
        DateRangePicker(dateRangePickerState, modifier, datePickerFormatter, datePickerColors, function2, function3, z, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit f(Long l, Long l2, long j, int i, Function2 function2, Function1 function1, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i2, int i3, Composer composer, int i4) {
        m385SwitchableDateEntryContenteVtQiho(l, l2, j, i, function2, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    public static Unit g(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContainer(semanticsPropertyReceiver, true);
        return Unit.INSTANCE;
    }

    public static final PaddingValues getCalendarMonthSubheadPadding() {
        return CalendarMonthSubheadPadding;
    }

    public static boolean h(LazyListState lazyListState, CoroutineScope coroutineScope) {
        if (!lazyListState.getCanScrollForward()) {
            return false;
        }
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DateRangePickerKt$customScrollActions$scrollDownAction$1$1(lazyListState, null), 3, (Object) null);
        return true;
    }

    /* JADX INFO: renamed from: rememberDateRangePickerState-IlFM19s, reason: not valid java name */
    public static final DateRangePickerState m388rememberDateRangePickerStateIlFM19s(Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, Composer composer, int i2, int i3) {
        final Long l4 = (i3 & 1) != 0 ? null : l;
        final Long l5 = (i3 & 2) != 0 ? null : l2;
        final Long l6 = (i3 & 4) != 0 ? l4 : l3;
        final IntRange yearRange = (i3 & 8) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : intRange;
        final int iM412getPickerjFl4v0 = (i3 & 16) != 0 ? DisplayMode.INSTANCE.m412getPickerjFl4v0() : i;
        final SelectableDates allDates = (i3 & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2012087461, i2, -1, "androidx.compose.material3.rememberDateRangePickerState (DateRangePicker.kt:283)");
        }
        final Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composer, 0);
        Object[] objArr = new Object[0];
        Saver<DateRangePickerStateImpl, Object> Saver = DateRangePickerStateImpl.INSTANCE.Saver(allDates, localeDefaultLocale);
        boolean z = true;
        boolean zChangedInstance = ((((i2 & 112) ^ 48) > 32 && composer.changed(l5)) || (i2 & 48) == 32) | ((((i2 & 14) ^ 6) > 4 && composer.changed(l4)) || (i2 & 6) == 4) | ((((i2 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 && composer.changed(l6)) || (i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 256) | composer.changedInstance(yearRange) | ((((57344 & i2) ^ 24576) > 16384 && composer.changed(iM412getPickerjFl4v0)) || (i2 & 24576) == 16384);
        if ((((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 || !composer.changed(allDates)) && (i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
            z = false;
        }
        boolean zChangedInstance2 = zChangedInstance | z | composer.changedInstance(localeDefaultLocale);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: wa3
                public final Object invoke() {
                    return DateRangePickerKt.d(l4, l5, l6, yearRange, iM412getPickerjFl4v0, allDates, localeDefaultLocale);
                }
            };
            composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        DateRangePickerStateImpl dateRangePickerStateImpl = (DateRangePickerStateImpl) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue, composer, 0);
        dateRangePickerStateImpl.setSelectableDates(allDates);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return dateRangePickerStateImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDateSelection(long j, Long l, Long l2, Function2<? super Long, ? super Long, Unit> function2) {
        if ((l == null && l2 == null) || (l != null && l2 != null)) {
            function2.invoke(Long.valueOf(j), (Object) null);
        } else if (l == null || j < l.longValue()) {
            function2.invoke(Long.valueOf(j), (Object) null);
        } else {
            function2.invoke(l, Long.valueOf(j));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$5, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass5 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DateRangePickerState $state;

        public AnonymousClass5(DateRangePickerState dateRangePickerState, DatePickerColors datePickerColors) {
            this.$state = dateRangePickerState;
            this.$colors = datePickerColors;
        }

        public static Unit a(DateRangePickerState dateRangePickerState, DisplayMode displayMode) {
            dateRangePickerState.mo391setDisplayModevCnGnXg(displayMode.getValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1343236786, i, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:139)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
            int iMo390getDisplayModejFl4v0 = this.$state.mo390getDisplayModejFl4v0();
            boolean zChanged = composer.changed(this.$state);
            final DateRangePickerState dateRangePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.t0
                    public final Object invoke(Object obj) {
                        return DateRangePickerKt.AnonymousClass5.a(dateRangePickerState, (DisplayMode) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            DatePickerKt.m367DisplayModeToggleButtoniUJLfQg(modifierPadding, iMo390getDisplayModejFl4v0, (Function1) objRememberedValue, this.$colors, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$DateRangePicker$6, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass6 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ FocusRequester $focusRequester;
        final /* synthetic */ DateRangePickerState $state;

        public AnonymousClass6(DateRangePickerState dateRangePickerState, CalendarModel calendarModel, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, FocusRequester focusRequester) {
            this.$state = dateRangePickerState;
            this.$calendarModel = calendarModel;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
            this.$focusRequester = focusRequester;
        }

        public static Unit a(DateRangePickerState dateRangePickerState, Long l, Long l2) {
            try {
                dateRangePickerState.setSelection(l, l2);
            } catch (IllegalArgumentException unused) {
            }
            return Unit.INSTANCE;
        }

        public static Unit b(DateRangePickerState dateRangePickerState, long j) {
            dateRangePickerState.setDisplayedMonthMillis(j);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(684885105, i, -1, "androidx.compose.material3.DateRangePicker.<anonymous> (DateRangePicker.kt:154)");
            }
            Long selectedStartDateMillis = this.$state.getSelectedStartDateMillis();
            Long selectedEndDateMillis = this.$state.getSelectedEndDateMillis();
            long displayedMonthMillis = this.$state.getDisplayedMonthMillis();
            int iMo390getDisplayModejFl4v0 = this.$state.mo390getDisplayModejFl4v0();
            boolean zChanged = composer.changed(this.$state);
            final DateRangePickerState dateRangePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function2() { // from class: androidx.compose.material3.u0
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerKt.AnonymousClass6.a(dateRangePickerState, (Long) obj, (Long) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function2 function2 = (Function2) objRememberedValue;
            boolean zChanged2 = composer.changed(this.$state);
            final DateRangePickerState dateRangePickerState2 = this.$state;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.v0
                    public final Object invoke(Object obj) {
                        return DateRangePickerKt.AnonymousClass6.b(dateRangePickerState2, ((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            DateRangePickerKt.m385SwitchableDateEntryContenteVtQiho(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, iMo390getDisplayModejFl4v0, function2, (Function1) objRememberedValue2, this.$calendarModel, this.$state.getYearRange(), this.$dateFormatter, this.$state.getSelectableDates(), this.$colors, this.$focusRequester, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ CalendarMonth $firstMonth;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ Function2<Long, Long, Unit> $onDatesSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedEndDateMillis;
        final /* synthetic */ Long $selectedStartDateMillis;
        final /* synthetic */ CalendarDate $today;
        final /* synthetic */ IntRange $yearRange;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Long l, Long l2, Function2<? super Long, ? super Long, Unit> function2, LazyListState lazyListState, IntRange intRange, CalendarModel calendarModel, CalendarMonth calendarMonth, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors, CalendarDate calendarDate, SelectableDates selectableDates) {
            this.$selectedStartDateMillis = l;
            this.$selectedEndDateMillis = l2;
            this.$onDatesSelectionChange = function2;
            this.$lazyListState = lazyListState;
            this.$yearRange = intRange;
            this.$calendarModel = calendarModel;
            this.$firstMonth = calendarMonth;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
            this.$today = calendarDate;
            this.$selectableDates = selectableDates;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, new ScrollAxisRange(new Function0() { // from class: androidx.compose.material3.z0
                public final Object invoke() {
                    return Float.valueOf(DateRangePickerKt.AnonymousClass1.c());
                }
            }, new Function0() { // from class: androidx.compose.material3.a1
                public final Object invoke() {
                    return Float.valueOf(DateRangePickerKt.AnonymousClass1.b());
                }
            }, false, 4, null));
            return Unit.INSTANCE;
        }

        public static float b() {
            return 0.0f;
        }

        public static float c() {
            return 0.0f;
        }

        public static Unit d(Long l, Long l2, Function2 function2, long j) {
            DateRangePickerKt.updateDateSelection(j, l, l2, function2);
            return Unit.INSTANCE;
        }

        public static Unit e(IntRange intRange, final CalendarModel calendarModel, final CalendarMonth calendarMonth, final Long l, final Long l2, final Function1 function1, final CalendarDate calendarDate, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, final List list, LazyListScope lazyListScope) {
            LazyListScope.items$default(lazyListScope, DatePickerKt.numberOfMonthsInRange(intRange), (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(682334170, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1$2$1$1
                public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                    int i3;
                    SelectedRangeInfo selectedRangeInfo;
                    if ((i2 & 6) == 0) {
                        i3 = i2 | (composer.changed(lazyItemScope) ? 4 : 2);
                    } else {
                        i3 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        i3 |= composer.changed(i) ? 32 : 16;
                    }
                    if (!composer.shouldExecute((i3 & 147) != 146, i3 & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(682334170, i3, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DateRangePicker.kt:855)");
                    }
                    CalendarMonth calendarMonthPlusMonths = calendarModel.plusMonths(calendarMonth, i);
                    Modifier modifierFillParentMaxWidth$default = LazyItemScope.fillParentMaxWidth$default(lazyItemScope, Modifier.INSTANCE, 0.0f, 1, (Object) null);
                    Long l3 = l;
                    Long l4 = l2;
                    Function1<Long, Unit> function2 = function1;
                    CalendarDate calendarDate2 = calendarDate;
                    DatePickerFormatter datePickerFormatter2 = datePickerFormatter;
                    SelectableDates selectableDates2 = selectableDates;
                    DatePickerColors datePickerColors2 = datePickerColors;
                    CalendarModel calendarModel2 = calendarModel;
                    List<CustomAccessibilityAction> list2 = list;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierFillParentMaxWidth$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion.getConstructor();
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
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getRangeSelectionMonthSubheadFont(), composer, 6), ComposableLambdaKt.rememberComposableLambda(-577031469, true, new DateRangePickerKt$VerticalMonthsList$1$2$1$1$1$1(datePickerFormatter2, calendarMonthPlusMonths, calendarModel2, list2, datePickerColors2), composer, 54), composer, 48);
                    if (l3 == null || l4 == null) {
                        composer.startReplaceGroup(186488258);
                        composer.endReplaceGroup();
                        selectedRangeInfo = null;
                    } else {
                        composer.startReplaceGroup(185956701);
                        boolean zChanged = composer.changed(l3) | composer.changed(l4);
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = SelectedRangeInfo.INSTANCE.calculateRangeInfo(calendarMonthPlusMonths, calendarModel2.getCanonicalDate(l3.longValue()), calendarModel2.getCanonicalDate(l4.longValue()));
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        selectedRangeInfo = (SelectedRangeInfo) objRememberedValue;
                        composer.endReplaceGroup();
                    }
                    DatePickerKt.Month(calendarMonthPlusMonths, function2, calendarDate2.getUtcTimeMillis(), l3, l4, selectedRangeInfo, datePickerFormatter2, selectableDates2, datePickerColors2, calendarModel2.getLocale(), composer, 0);
                    composer.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
                    return Unit.INSTANCE;
                }
            }), 6, (Object) null);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1090773432, i, -1, "androidx.compose.material3.VerticalMonthsList.<anonymous> (DateRangePicker.kt:822)");
            }
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer);
                composer.updateRememberedValue(objRememberedValue);
            }
            CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_scroll_to_previous_month), composer, 0);
            String strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_scroll_to_next_month), composer, 0);
            boolean zChanged = composer.changed(this.$selectedStartDateMillis) | composer.changed(this.$selectedEndDateMillis) | composer.changed(this.$onDatesSelectionChange);
            final Long l = this.$selectedStartDateMillis;
            final Long l2 = this.$selectedEndDateMillis;
            final Function2<Long, Long, Unit> function2 = this.$onDatesSelectionChange;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.w0
                    public final Object invoke(Object obj) {
                        return DateRangePickerKt.AnonymousClass1.d(l, l2, function2, ((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            final Function1 function1 = (Function1) objRememberedValue2;
            final List listCustomScrollActions = DateRangePickerKt.customScrollActions(this.$lazyListState, coroutineScope, strM1471getString2EP1pXo, strM1471getString2EP1pXo2);
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: androidx.compose.material3.x0
                    public final Object invoke(Object obj) {
                        return DateRangePickerKt.AnonymousClass1.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion3, false, (Function1) objRememberedValue3, 1, null);
            LazyListState lazyListState = this.$lazyListState;
            boolean zChangedInstance = composer.changedInstance(this.$yearRange) | composer.changedInstance(this.$calendarModel) | composer.changed(this.$firstMonth) | composer.changedInstance(this.$dateFormatter) | composer.changedInstance(listCustomScrollActions) | composer.changed(this.$colors) | composer.changed(this.$selectedStartDateMillis) | composer.changed(this.$selectedEndDateMillis) | composer.changed(function1) | composer.changed(this.$today) | composer.changed(this.$selectableDates);
            final IntRange intRange = this.$yearRange;
            final CalendarModel calendarModel = this.$calendarModel;
            final CalendarMonth calendarMonth = this.$firstMonth;
            final Long l3 = this.$selectedStartDateMillis;
            final Long l4 = this.$selectedEndDateMillis;
            final CalendarDate calendarDate = this.$today;
            final DatePickerFormatter datePickerFormatter = this.$dateFormatter;
            final SelectableDates selectableDates = this.$selectableDates;
            final DatePickerColors datePickerColors = this.$colors;
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
                Object obj = new Function1() { // from class: androidx.compose.material3.y0
                    public final Object invoke(Object obj2) {
                        return DateRangePickerKt.AnonymousClass1.e(intRange, calendarModel, calendarMonth, l3, l4, function1, calendarDate, datePickerFormatter, selectableDates, datePickerColors, listCustomScrollActions, (LazyListScope) obj2);
                    }
                };
                composer.updateRememberedValue(obj);
                objRememberedValue4 = obj;
            }
            LazyDslKt.LazyColumn(modifierSemantics$default, lazyListState, (PaddingValues) null, false, (Arrangement.Vertical) null, (Alignment.Horizontal) null, (FlingBehavior) null, false, (OverscrollEffect) null, (Function1) objRememberedValue4, composer, 0, 508);
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
