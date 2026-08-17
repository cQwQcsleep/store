package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.DateRangePickerDefaults;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\rJG\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0098\u0001\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\u0002\b\u001b2\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\u0002\b\u001b2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\u0002\b\u001b2\n\u0010\u001e\u001a\u00060\u001fj\u0002` H\u0003¢\u0006\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/material3/DateRangePickerDefaults;", "", "<init>", "()V", "DateRangePickerTitle", "", "displayMode", "Landroidx/compose/material3/DisplayMode;", "modifier", "Landroidx/compose/ui/Modifier;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "DateRangePickerTitle-FNtVw6o", "(ILandroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "DateRangePickerHeadline", "selectedStartDateMillis", "", "selectedEndDateMillis", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "DateRangePickerHeadline-qS89cEg", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "startDateText", "", "endDateText", "startDatePlaceholder", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "endDatePlaceholder", "datesDelimiter", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateRangePickerHeadline-nZrIstQ", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;JLjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Ljava/util/Locale;Landroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateRangePickerDefaults {
    public static final int $stable = 0;
    public static final DateRangePickerDefaults INSTANCE = new DateRangePickerDefaults();

    private DateRangePickerDefaults() {
    }

    /* JADX INFO: renamed from: DateRangePickerHeadline-nZrIstQ, reason: not valid java name */
    private final void m380DateRangePickerHeadlinenZrIstQ(Long l, final Long l2, final int i, final DatePickerFormatter datePickerFormatter, final Modifier modifier, final long j, final String str, final String str2, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Locale locale, Composer composer, final int i2, final int i3) {
        int i4;
        int i5;
        Long l3;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1381313200);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(l2) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= (i2 & 4096) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changed(modifier) ? 16384 : 8192;
        }
        int i6 = i4;
        if ((196608 & i2) == 0) {
            i6 |= composerStartRestartGroup.changed(j) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i6 |= composerStartRestartGroup.changed(str) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i6 |= composerStartRestartGroup.changed(str2) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function3) ? 536870912 : 268435456;
        }
        int i7 = i6;
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changedInstance(function4) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(locale) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute(((i7 & 306783379) == 306783378 && (i5 & 19) == 18) ? false : true, i7 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1381313200, i7, i5, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:468)");
            }
            int i8 = i5;
            String date$default = DatePickerFormatter.formatDate$default(datePickerFormatter, l, locale, false, 4, null);
            l3 = l;
            String date$default2 = DatePickerFormatter.formatDate$default(datePickerFormatter, l2, locale, false, 4, null);
            String date = datePickerFormatter.formatDate(l3, locale, true);
            String strM1471getString2EP1pXo = "";
            if (date == null) {
                composerStartRestartGroup.startReplaceGroup(620891895);
                DisplayMode.Companion companion = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(297125251);
                    Strings.Companion companion2 = Strings.INSTANCE;
                    String strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_no_selection_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    date = strM1471getString2EP1pXo2;
                } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(297128222);
                    Strings.Companion companion3 = Strings.INSTANCE;
                    date = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_no_input_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(621113326);
                    composerStartRestartGroup.endReplaceGroup();
                    date = "";
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(297117483);
                composerStartRestartGroup.endReplaceGroup();
            }
            String date2 = datePickerFormatter.formatDate(l2, locale, true);
            if (date2 == null) {
                composerStartRestartGroup.startReplaceGroup(621382935);
                DisplayMode.Companion companion4 = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion4.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(297141091);
                    Strings.Companion companion5 = Strings.INSTANCE;
                    strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_picker_no_selection_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (DisplayMode.m407equalsimpl0(i, companion4.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(297144062);
                    Strings.Companion companion6 = Strings.INSTANCE;
                    strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_no_input_description), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(621604366);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                date2 = strM1471getString2EP1pXo;
            } else {
                composerStartRestartGroup.startReplaceGroup(297133385);
                composerStartRestartGroup.endReplaceGroup();
            }
            final String str3 = str + ": " + date;
            final String str4 = str2 + ": " + date2;
            boolean zChanged = composerStartRestartGroup.changed(str3) | composerStartRestartGroup.changed(str4);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: oa3
                    public final Object invoke(Object obj) {
                        return DateRangePickerDefaults.d(str3, str4, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(modifier, (Function1) objRememberedValue);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.spacedBy-0680j_4(Dp.m6022constructorimpl(4.0f)), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierClearAndSetSemantics);
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion7.getConstructor();
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion7.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion7.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion7.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion7.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            if (date$default != null) {
                composerStartRestartGroup.startReplaceGroup(-177386503);
                TextKt.m1097TextNvy7gAk(date$default, null, j, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, (i7 >> 9) & 896, 0, 262138);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            } else {
                composer2 = composerStartRestartGroup;
                composer2.startReplaceGroup(-177297192);
                function2.invoke(composer2, Integer.valueOf((i7 >> 24) & 14));
                composer2.endReplaceGroup();
            }
            function4.invoke(composer2, Integer.valueOf(i8 & 14));
            if (date$default2 != 0) {
                composer2.startReplaceGroup(-177171301);
                TextKt.m1097TextNvy7gAk(date$default2, null, j, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, (i7 >> 9) & 896, 0, 262138);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-177083974);
                function3.invoke(composer2, Integer.valueOf((i7 >> 27) & 14));
                composer2.endReplaceGroup();
            }
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            l3 = l;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Long l4 = l3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pa3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerDefaults.b(this.b, l4, l2, i, datePickerFormatter, modifier, j, str, str2, function2, function3, function4, locale, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(DateRangePickerDefaults dateRangePickerDefaults, int i, Modifier modifier, long j, int i2, int i3, Composer composer, int i4) {
        dateRangePickerDefaults.m382DateRangePickerTitleFNtVw6o(i, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit b(DateRangePickerDefaults dateRangePickerDefaults, Long l, Long l2, int i, DatePickerFormatter datePickerFormatter, Modifier modifier, long j, String str, String str2, Function2 function2, Function2 function3, Function2 function4, Locale locale, int i2, int i3, Composer composer, int i4) {
        dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l, l2, i, datePickerFormatter, modifier, j, str, str2, function2, function3, function4, locale, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    public static Unit c(DateRangePickerDefaults dateRangePickerDefaults, Long l, Long l2, int i, DatePickerFormatter datePickerFormatter, Modifier modifier, long j, int i2, int i3, Composer composer, int i4) {
        dateRangePickerDefaults.m381DateRangePickerHeadlineqS89cEg(l, l2, i, datePickerFormatter, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit d(String str, String str2, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.m5263setLiveRegionhR3wRGc(semanticsPropertyReceiver, LiveRegionMode.INSTANCE.m5237getPolite0phEisY());
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str + ", " + str2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0110  */
    /* JADX WARN: Code duplicated, block: B:104:0x0126  */
    /* JADX WARN: Code duplicated, block: B:107:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:109:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:112:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x005b  */
    /* JADX WARN: Code duplicated, block: B:33:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:38:0x0068  */
    /* JADX WARN: Code duplicated, block: B:40:0x006c  */
    /* JADX WARN: Code duplicated, block: B:42:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:45:0x007b  */
    /* JADX WARN: Code duplicated, block: B:46:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x008a  */
    /* JADX WARN: Code duplicated, block: B:54:0x008e  */
    /* JADX WARN: Code duplicated, block: B:56:0x0096  */
    /* JADX WARN: Code duplicated, block: B:57:0x0099  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:72:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00de  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:96:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0108  */
    /* JADX WARN: Code duplicated, block: B:98:0x010b  */
    /* JADX INFO: renamed from: DateRangePickerHeadline-qS89cEg, reason: not valid java name */
    public final void m381DateRangePickerHeadlineqS89cEg(final Long l, final Long l2, final int i, final DatePickerFormatter datePickerFormatter, Modifier modifier, long j, Composer composer, final int i2, final int i3) {
        Long l3;
        int i4;
        Long l4;
        int i5;
        int i6;
        boolean zChangedInstance;
        int i7;
        int i8;
        Modifier modifier2;
        int i9;
        final long headlineContentColor;
        DateRangePickerDefaults dateRangePickerDefaults;
        int i10;
        boolean z;
        final Modifier modifier3;
        final long j2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(1655228151);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            l3 = l;
        } else if ((i2 & 6) == 0) {
            l3 = l;
            i4 = (composerStartRestartGroup.changed(l3) ? 4 : 2) | i2;
        } else {
            l3 = l;
            i4 = i2;
        }
        if ((i3 & 2) == 0) {
            if ((i2 & 48) == 0) {
                l4 = l2;
                i4 |= composerStartRestartGroup.changed(l4) ? 32 : 16;
            }
            if ((i3 & 4) != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    i5 = i;
                    if (composerStartRestartGroup.changed(i5)) {
                        i6 = 256;
                    } else {
                        i6 = 128;
                    }
                    i4 |= i6;
                }
                if ((i3 & 8) != 0) {
                    i4 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    if ((i2 & 4096) == 0) {
                        zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                    } else {
                        zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                    }
                    if (zChangedInstance) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i4 |= i7;
                }
                i8 = i3 & 16;
                if (i8 != 0) {
                    if ((i2 & 24576) == 0) {
                        modifier2 = modifier;
                        if (composerStartRestartGroup.changed(modifier2)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i4 |= i9;
                    }
                    if ((196608 & i2) == 0) {
                        if ((i3 & 32) == 0) {
                            headlineContentColor = j;
                            int i11 = composerStartRestartGroup.changed(headlineContentColor) ? 131072 : 65536;
                            i4 |= i11;
                        } else {
                            headlineContentColor = j;
                        }
                        i4 |= i11;
                    } else {
                        headlineContentColor = j;
                    }
                    if ((i3 & 64) != 0) {
                        if ((i2 & 1572864) == 0) {
                            dateRangePickerDefaults = this;
                            if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                                i10 = 1048576;
                            } else {
                                i10 = 524288;
                            }
                            i4 |= i10;
                        }
                        if ((599187 & i4) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i8 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if ((i3 & 32) != 0) {
                                    i4 &= -458753;
                                    headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i3 & 32) != 0) {
                                    i4 &= -458753;
                                }
                                modifier4 = modifier2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                            }
                            Strings.Companion companion = Strings.INSTANCE;
                            final String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                            final String strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                                public final void invoke(Composer composer2, int i12) {
                                    if (!composer2.shouldExecute((i12 & 3) != 2, i12 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(850203865, i12, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                                    }
                                    TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                                public final void invoke(Composer composer2, int i12) {
                                    if (!composer2.shouldExecute((i12 & 3) != 2, i12 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(282231642, i12, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                                    }
                                    TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo2, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            ComposableLambda composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                                public final void invoke(Composer composer2, int i12) {
                                    if (!composer2.shouldExecute((i12 & 3) != 2, i12 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-320655704, i12, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                                    }
                                    TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                            Locale localeDefaultLocale = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                            int i12 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                            int i13 = ((i4 >> 12) & 896) | 6;
                            int i14 = i5;
                            Modifier modifier5 = modifier4;
                            long j3 = headlineContentColor;
                            dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i14, datePickerFormatter, modifier5, j3, strM1471getString2EP1pXo, strM1471getString2EP1pXo2, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, localeDefaultLocale, composerStartRestartGroup, i12, i13);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            j2 = j3;
                            modifier3 = modifier5;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier3 = modifier2;
                            j2 = headlineContentColor;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                                public final Object invoke(Object obj, Object obj2) {
                                    return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 1572864;
                    dateRangePickerDefaults = this;
                    if ((599187 & i4) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        } else {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                        }
                        Strings.Companion companion2 = Strings.INSTANCE;
                        final String strM1471getString2EP1pXo3 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        final String strM1471getString2EP1pXo4 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        ComposableLambda composableLambdaRememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            public final void invoke(Composer composer2, int i15) {
                                if (!composer2.shouldExecute((i15 & 3) != 2, i15 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(850203865, i15, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo3, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            public final void invoke(Composer composer2, int i15) {
                                if (!composer2.shouldExecute((i15 & 3) != 2, i15 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(282231642, i15, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo4, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda6 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            public final void invoke(Composer composer2, int i15) {
                                if (!composer2.shouldExecute((i15 & 3) != 2, i15 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-320655704, i15, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                                }
                                TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        Locale localeDefaultLocale2 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        int i15 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                        int i16 = ((i4 >> 12) & 896) | 6;
                        int i17 = i5;
                        Modifier modifier6 = modifier4;
                        long j4 = headlineContentColor;
                        dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i17, datePickerFormatter, modifier6, j4, strM1471getString2EP1pXo3, strM1471getString2EP1pXo4, composableLambdaRememberComposableLambda4, composableLambdaRememberComposableLambda5, composableLambdaRememberComposableLambda6, localeDefaultLocale2, composerStartRestartGroup, i15, i16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j2 = j4;
                        modifier3 = modifier6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j2 = headlineContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                modifier2 = modifier;
                if ((196608 & i2) == 0) {
                    if ((i3 & 32) == 0) {
                        headlineContentColor = j;
                        if (composerStartRestartGroup.changed(headlineContentColor)) {
                        }
                        i4 |= i11;
                    } else {
                        headlineContentColor = j;
                    }
                    i4 |= i11;
                } else {
                    headlineContentColor = j;
                }
                if ((i3 & 64) != 0) {
                    if ((i2 & 1572864) == 0) {
                        dateRangePickerDefaults = this;
                        if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                    if ((599187 & i4) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        } else {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                        }
                        Strings.Companion companion3 = Strings.INSTANCE;
                        final String strM1471getString2EP1pXo5 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        final String strM1471getString2EP1pXo6 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        ComposableLambda composableLambdaRememberComposableLambda7 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            public final void invoke(Composer composer2, int i18) {
                                if (!composer2.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(850203865, i18, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo5, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda8 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            public final void invoke(Composer composer2, int i18) {
                                if (!composer2.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(282231642, i18, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo6, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda9 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            public final void invoke(Composer composer2, int i18) {
                                if (!composer2.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-320655704, i18, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                                }
                                TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        Locale localeDefaultLocale3 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        int i18 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                        int i19 = ((i4 >> 12) & 896) | 6;
                        int i110 = i5;
                        Modifier modifier7 = modifier4;
                        long j5 = headlineContentColor;
                        dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i110, datePickerFormatter, modifier7, j5, strM1471getString2EP1pXo5, strM1471getString2EP1pXo6, composableLambdaRememberComposableLambda7, composableLambdaRememberComposableLambda8, composableLambdaRememberComposableLambda9, localeDefaultLocale3, composerStartRestartGroup, i18, i19);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j2 = j5;
                        modifier3 = modifier7;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j2 = headlineContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                dateRangePickerDefaults = this;
                if ((599187 & i4) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                    }
                    Strings.Companion companion4 = Strings.INSTANCE;
                    final String strM1471getString2EP1pXo7 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    final String strM1471getString2EP1pXo8 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    ComposableLambda composableLambdaRememberComposableLambda10 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        public final void invoke(Composer composer2, int i111) {
                            if (!composer2.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(850203865, i111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo7, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda11 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        public final void invoke(Composer composer2, int i111) {
                            if (!composer2.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(282231642, i111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo8, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda12 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        public final void invoke(Composer composer2, int i111) {
                            if (!composer2.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-320655704, i111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                            }
                            TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    Locale localeDefaultLocale4 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    int i111 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                    int i112 = ((i4 >> 12) & 896) | 6;
                    int i113 = i5;
                    Modifier modifier8 = modifier4;
                    long j6 = headlineContentColor;
                    dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i113, datePickerFormatter, modifier8, j6, strM1471getString2EP1pXo7, strM1471getString2EP1pXo8, composableLambdaRememberComposableLambda10, composableLambdaRememberComposableLambda11, composableLambdaRememberComposableLambda12, localeDefaultLocale4, composerStartRestartGroup, i111, i112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j6;
                    modifier3 = modifier8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = headlineContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            i5 = i;
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if ((i2 & 4096) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i4 |= i7;
            }
            i8 = i3 & 16;
            if (i8 != 0) {
                if ((i2 & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                if ((196608 & i2) == 0) {
                    if ((i3 & 32) == 0) {
                        headlineContentColor = j;
                        if (composerStartRestartGroup.changed(headlineContentColor)) {
                        }
                        i4 |= i11;
                    } else {
                        headlineContentColor = j;
                    }
                    i4 |= i11;
                } else {
                    headlineContentColor = j;
                }
                if ((i3 & 64) != 0) {
                    if ((i2 & 1572864) == 0) {
                        dateRangePickerDefaults = this;
                        if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                    if ((599187 & i4) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        } else {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                        }
                        Strings.Companion companion5 = Strings.INSTANCE;
                        final String strM1471getString2EP1pXo9 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        final String strM1471getString2EP1pXo10 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        ComposableLambda composableLambdaRememberComposableLambda13 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            public final void invoke(Composer composer2, int i114) {
                                if (!composer2.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(850203865, i114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo9, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda14 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            public final void invoke(Composer composer2, int i114) {
                                if (!composer2.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(282231642, i114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo10, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda15 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            public final void invoke(Composer composer2, int i114) {
                                if (!composer2.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-320655704, i114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                                }
                                TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        Locale localeDefaultLocale5 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        int i114 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                        int i115 = ((i4 >> 12) & 896) | 6;
                        int i116 = i5;
                        Modifier modifier9 = modifier4;
                        long j7 = headlineContentColor;
                        dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i116, datePickerFormatter, modifier9, j7, strM1471getString2EP1pXo9, strM1471getString2EP1pXo10, composableLambdaRememberComposableLambda13, composableLambdaRememberComposableLambda14, composableLambdaRememberComposableLambda15, localeDefaultLocale5, composerStartRestartGroup, i114, i115);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j2 = j7;
                        modifier3 = modifier9;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j2 = headlineContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                dateRangePickerDefaults = this;
                if ((599187 & i4) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                    }
                    Strings.Companion companion6 = Strings.INSTANCE;
                    final String strM1471getString2EP1pXo11 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    final String strM1471getString2EP1pXo12 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    ComposableLambda composableLambdaRememberComposableLambda16 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        public final void invoke(Composer composer2, int i117) {
                            if (!composer2.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(850203865, i117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo11, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda17 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        public final void invoke(Composer composer2, int i117) {
                            if (!composer2.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(282231642, i117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo12, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda18 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        public final void invoke(Composer composer2, int i117) {
                            if (!composer2.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-320655704, i117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                            }
                            TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    Locale localeDefaultLocale6 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    int i117 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                    int i118 = ((i4 >> 12) & 896) | 6;
                    int i119 = i5;
                    Modifier modifier10 = modifier4;
                    long j8 = headlineContentColor;
                    dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i119, datePickerFormatter, modifier10, j8, strM1471getString2EP1pXo11, strM1471getString2EP1pXo12, composableLambdaRememberComposableLambda16, composableLambdaRememberComposableLambda17, composableLambdaRememberComposableLambda18, localeDefaultLocale6, composerStartRestartGroup, i117, i118);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j8;
                    modifier3 = modifier10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = headlineContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            modifier2 = modifier;
            if ((196608 & i2) == 0) {
                if ((i3 & 32) == 0) {
                    headlineContentColor = j;
                    if (composerStartRestartGroup.changed(headlineContentColor)) {
                    }
                    i4 |= i11;
                } else {
                    headlineContentColor = j;
                }
                i4 |= i11;
            } else {
                headlineContentColor = j;
            }
            if ((i3 & 64) != 0) {
                if ((i2 & 1572864) == 0) {
                    dateRangePickerDefaults = this;
                    if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
                if ((599187 & i4) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                    }
                    Strings.Companion companion7 = Strings.INSTANCE;
                    final String strM1471getString2EP1pXo13 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    final String strM1471getString2EP1pXo14 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    ComposableLambda composableLambdaRememberComposableLambda19 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        public final void invoke(Composer composer2, int i1110) {
                            if (!composer2.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(850203865, i1110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo13, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda110 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        public final void invoke(Composer composer2, int i1110) {
                            if (!composer2.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(282231642, i1110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo14, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda111 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        public final void invoke(Composer composer2, int i1110) {
                            if (!composer2.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-320655704, i1110, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                            }
                            TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    Locale localeDefaultLocale7 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    int i1110 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                    int i1111 = ((i4 >> 12) & 896) | 6;
                    int i1112 = i5;
                    Modifier modifier11 = modifier4;
                    long j9 = headlineContentColor;
                    dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i1112, datePickerFormatter, modifier11, j9, strM1471getString2EP1pXo13, strM1471getString2EP1pXo14, composableLambdaRememberComposableLambda19, composableLambdaRememberComposableLambda110, composableLambdaRememberComposableLambda111, localeDefaultLocale7, composerStartRestartGroup, i1110, i1111);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j9;
                    modifier3 = modifier11;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = headlineContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            dateRangePickerDefaults = this;
            if ((599187 & i4) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                }
                Strings.Companion companion8 = Strings.INSTANCE;
                final String strM1471getString2EP1pXo15 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                final String strM1471getString2EP1pXo16 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                ComposableLambda composableLambdaRememberComposableLambda112 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    public final void invoke(Composer composer2, int i1113) {
                        if (!composer2.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(850203865, i1113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo15, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda113 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    public final void invoke(Composer composer2, int i1113) {
                        if (!composer2.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(282231642, i1113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo16, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda114 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    public final void invoke(Composer composer2, int i1113) {
                        if (!composer2.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-320655704, i1113, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                        }
                        TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                Locale localeDefaultLocale8 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                int i1113 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                int i1114 = ((i4 >> 12) & 896) | 6;
                int i1115 = i5;
                Modifier modifier12 = modifier4;
                long j10 = headlineContentColor;
                dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i1115, datePickerFormatter, modifier12, j10, strM1471getString2EP1pXo15, strM1471getString2EP1pXo16, composableLambdaRememberComposableLambda112, composableLambdaRememberComposableLambda113, composableLambdaRememberComposableLambda114, localeDefaultLocale8, composerStartRestartGroup, i1113, i1114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j10;
                modifier3 = modifier12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = headlineContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        l4 = l2;
        if ((i3 & 4) != 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i5 = i;
                if (composerStartRestartGroup.changed(i5)) {
                    i6 = 256;
                } else {
                    i6 = 128;
                }
                i4 |= i6;
            }
            if ((i3 & 8) != 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                if ((i2 & 4096) == 0) {
                    zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
                } else {
                    zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
                }
                if (zChangedInstance) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i4 |= i7;
            }
            i8 = i3 & 16;
            if (i8 != 0) {
                if ((i2 & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i4 |= i9;
                }
                if ((196608 & i2) == 0) {
                    if ((i3 & 32) == 0) {
                        headlineContentColor = j;
                        if (composerStartRestartGroup.changed(headlineContentColor)) {
                        }
                        i4 |= i11;
                    } else {
                        headlineContentColor = j;
                    }
                    i4 |= i11;
                } else {
                    headlineContentColor = j;
                }
                if ((i3 & 64) != 0) {
                    if ((i2 & 1572864) == 0) {
                        dateRangePickerDefaults = this;
                        if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                    if ((599187 & i4) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        } else {
                            if (i8 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i3 & 32) != 0) {
                                i4 &= -458753;
                                headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                        }
                        Strings.Companion companion9 = Strings.INSTANCE;
                        final String strM1471getString2EP1pXo17 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                        final String strM1471getString2EP1pXo18 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                        ComposableLambda composableLambdaRememberComposableLambda115 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                            public final void invoke(Composer composer2, int i1116) {
                                if (!composer2.shouldExecute((i1116 & 3) != 2, i1116 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(850203865, i1116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo17, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda116 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                            public final void invoke(Composer composer2, int i1116) {
                                if (!composer2.shouldExecute((i1116 & 3) != 2, i1116 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(282231642, i1116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                                }
                                TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo18, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        ComposableLambda composableLambdaRememberComposableLambda117 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                            public final void invoke(Composer composer2, int i1116) {
                                if (!composer2.shouldExecute((i1116 & 3) != 2, i1116 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-320655704, i1116, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                                }
                                TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                        Locale localeDefaultLocale9 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                        int i1116 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                        int i1117 = ((i4 >> 12) & 896) | 6;
                        int i1118 = i5;
                        Modifier modifier13 = modifier4;
                        long j11 = headlineContentColor;
                        dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i1118, datePickerFormatter, modifier13, j11, strM1471getString2EP1pXo17, strM1471getString2EP1pXo18, composableLambdaRememberComposableLambda115, composableLambdaRememberComposableLambda116, composableLambdaRememberComposableLambda117, localeDefaultLocale9, composerStartRestartGroup, i1116, i1117);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j2 = j11;
                        modifier3 = modifier13;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j2 = headlineContentColor;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                            public final Object invoke(Object obj, Object obj2) {
                                return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                dateRangePickerDefaults = this;
                if ((599187 & i4) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                    }
                    Strings.Companion companion10 = Strings.INSTANCE;
                    final String strM1471getString2EP1pXo19 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    final String strM1471getString2EP1pXo110 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    ComposableLambda composableLambdaRememberComposableLambda118 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        public final void invoke(Composer composer2, int i1119) {
                            if (!composer2.shouldExecute((i1119 & 3) != 2, i1119 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(850203865, i1119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo19, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda119 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        public final void invoke(Composer composer2, int i1119) {
                            if (!composer2.shouldExecute((i1119 & 3) != 2, i1119 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(282231642, i1119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo110, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1110 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        public final void invoke(Composer composer2, int i1119) {
                            if (!composer2.shouldExecute((i1119 & 3) != 2, i1119 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-320655704, i1119, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                            }
                            TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    Locale localeDefaultLocale10 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    int i1119 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                    int i11110 = ((i4 >> 12) & 896) | 6;
                    int i11111 = i5;
                    Modifier modifier14 = modifier4;
                    long j12 = headlineContentColor;
                    dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i11111, datePickerFormatter, modifier14, j12, strM1471getString2EP1pXo19, strM1471getString2EP1pXo110, composableLambdaRememberComposableLambda118, composableLambdaRememberComposableLambda119, composableLambdaRememberComposableLambda1110, localeDefaultLocale10, composerStartRestartGroup, i1119, i11110);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j12;
                    modifier3 = modifier14;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = headlineContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            modifier2 = modifier;
            if ((196608 & i2) == 0) {
                if ((i3 & 32) == 0) {
                    headlineContentColor = j;
                    if (composerStartRestartGroup.changed(headlineContentColor)) {
                    }
                    i4 |= i11;
                } else {
                    headlineContentColor = j;
                }
                i4 |= i11;
            } else {
                headlineContentColor = j;
            }
            if ((i3 & 64) != 0) {
                if ((i2 & 1572864) == 0) {
                    dateRangePickerDefaults = this;
                    if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
                if ((599187 & i4) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                    }
                    Strings.Companion companion11 = Strings.INSTANCE;
                    final String strM1471getString2EP1pXo111 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    final String strM1471getString2EP1pXo112 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    ComposableLambda composableLambdaRememberComposableLambda1111 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        public final void invoke(Composer composer2, int i11112) {
                            if (!composer2.shouldExecute((i11112 & 3) != 2, i11112 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(850203865, i11112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo111, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1112 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        public final void invoke(Composer composer2, int i11112) {
                            if (!composer2.shouldExecute((i11112 & 3) != 2, i11112 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(282231642, i11112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo112, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1113 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        public final void invoke(Composer composer2, int i11112) {
                            if (!composer2.shouldExecute((i11112 & 3) != 2, i11112 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-320655704, i11112, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                            }
                            TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    Locale localeDefaultLocale11 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    int i11112 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                    int i11113 = ((i4 >> 12) & 896) | 6;
                    int i11114 = i5;
                    Modifier modifier15 = modifier4;
                    long j13 = headlineContentColor;
                    dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i11114, datePickerFormatter, modifier15, j13, strM1471getString2EP1pXo111, strM1471getString2EP1pXo112, composableLambdaRememberComposableLambda1111, composableLambdaRememberComposableLambda1112, composableLambdaRememberComposableLambda1113, localeDefaultLocale11, composerStartRestartGroup, i11112, i11113);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j13;
                    modifier3 = modifier15;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = headlineContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            dateRangePickerDefaults = this;
            if ((599187 & i4) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                }
                Strings.Companion companion12 = Strings.INSTANCE;
                final String strM1471getString2EP1pXo113 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                final String strM1471getString2EP1pXo114 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                ComposableLambda composableLambdaRememberComposableLambda1114 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    public final void invoke(Composer composer2, int i11115) {
                        if (!composer2.shouldExecute((i11115 & 3) != 2, i11115 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(850203865, i11115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo113, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda1115 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    public final void invoke(Composer composer2, int i11115) {
                        if (!composer2.shouldExecute((i11115 & 3) != 2, i11115 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(282231642, i11115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo114, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda1116 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    public final void invoke(Composer composer2, int i11115) {
                        if (!composer2.shouldExecute((i11115 & 3) != 2, i11115 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-320655704, i11115, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                        }
                        TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                Locale localeDefaultLocale12 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                int i11115 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                int i11116 = ((i4 >> 12) & 896) | 6;
                int i11117 = i5;
                Modifier modifier16 = modifier4;
                long j14 = headlineContentColor;
                dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i11117, datePickerFormatter, modifier16, j14, strM1471getString2EP1pXo113, strM1471getString2EP1pXo114, composableLambdaRememberComposableLambda1114, composableLambdaRememberComposableLambda1115, composableLambdaRememberComposableLambda1116, localeDefaultLocale12, composerStartRestartGroup, i11115, i11116);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j14;
                modifier3 = modifier16;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = headlineContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        i5 = i;
        if ((i3 & 8) != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            if ((i2 & 4096) == 0) {
                zChangedInstance = composerStartRestartGroup.changed(datePickerFormatter);
            } else {
                zChangedInstance = composerStartRestartGroup.changedInstance(datePickerFormatter);
            }
            if (zChangedInstance) {
                i7 = 2048;
            } else {
                i7 = 1024;
            }
            i4 |= i7;
        }
        i8 = i3 & 16;
        if (i8 != 0) {
            if ((i2 & 24576) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i4 |= i9;
            }
            if ((196608 & i2) == 0) {
                if ((i3 & 32) == 0) {
                    headlineContentColor = j;
                    if (composerStartRestartGroup.changed(headlineContentColor)) {
                    }
                    i4 |= i11;
                } else {
                    headlineContentColor = j;
                }
                i4 |= i11;
            } else {
                headlineContentColor = j;
            }
            if ((i3 & 64) != 0) {
                if ((i2 & 1572864) == 0) {
                    dateRangePickerDefaults = this;
                    if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
                if ((599187 & i4) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i3 & 32) != 0) {
                            i4 &= -458753;
                            headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                    }
                    Strings.Companion companion13 = Strings.INSTANCE;
                    final String strM1471getString2EP1pXo115 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                    final String strM1471getString2EP1pXo116 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                    ComposableLambda composableLambdaRememberComposableLambda1117 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                        public final void invoke(Composer composer2, int i11118) {
                            if (!composer2.shouldExecute((i11118 & 3) != 2, i11118 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(850203865, i11118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo115, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1118 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                        public final void invoke(Composer composer2, int i11118) {
                            if (!composer2.shouldExecute((i11118 & 3) != 2, i11118 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(282231642, i11118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                            }
                            TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo116, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    ComposableLambda composableLambdaRememberComposableLambda1119 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                        public final void invoke(Composer composer2, int i11118) {
                            if (!composer2.shouldExecute((i11118 & 3) != 2, i11118 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-320655704, i11118, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                            }
                            TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                    Locale localeDefaultLocale13 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                    int i11118 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                    int i11119 = ((i4 >> 12) & 896) | 6;
                    int i111110 = i5;
                    Modifier modifier17 = modifier4;
                    long j15 = headlineContentColor;
                    dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i111110, datePickerFormatter, modifier17, j15, strM1471getString2EP1pXo115, strM1471getString2EP1pXo116, composableLambdaRememberComposableLambda1117, composableLambdaRememberComposableLambda1118, composableLambdaRememberComposableLambda1119, localeDefaultLocale13, composerStartRestartGroup, i11118, i11119);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j15;
                    modifier3 = modifier17;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j2 = headlineContentColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                        public final Object invoke(Object obj, Object obj2) {
                            return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            dateRangePickerDefaults = this;
            if ((599187 & i4) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                }
                Strings.Companion companion14 = Strings.INSTANCE;
                final String strM1471getString2EP1pXo117 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                final String strM1471getString2EP1pXo118 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                ComposableLambda composableLambdaRememberComposableLambda11110 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    public final void invoke(Composer composer2, int i111111) {
                        if (!composer2.shouldExecute((i111111 & 3) != 2, i111111 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(850203865, i111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo117, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11111 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    public final void invoke(Composer composer2, int i111111) {
                        if (!composer2.shouldExecute((i111111 & 3) != 2, i111111 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(282231642, i111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo118, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11112 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    public final void invoke(Composer composer2, int i111111) {
                        if (!composer2.shouldExecute((i111111 & 3) != 2, i111111 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-320655704, i111111, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                        }
                        TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                Locale localeDefaultLocale14 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                int i111111 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                int i111112 = ((i4 >> 12) & 896) | 6;
                int i111113 = i5;
                Modifier modifier18 = modifier4;
                long j16 = headlineContentColor;
                dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i111113, datePickerFormatter, modifier18, j16, strM1471getString2EP1pXo117, strM1471getString2EP1pXo118, composableLambdaRememberComposableLambda11110, composableLambdaRememberComposableLambda11111, composableLambdaRememberComposableLambda11112, localeDefaultLocale14, composerStartRestartGroup, i111111, i111112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j16;
                modifier3 = modifier18;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = headlineContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        modifier2 = modifier;
        if ((196608 & i2) == 0) {
            if ((i3 & 32) == 0) {
                headlineContentColor = j;
                if (composerStartRestartGroup.changed(headlineContentColor)) {
                }
                i4 |= i11;
            } else {
                headlineContentColor = j;
            }
            i4 |= i11;
        } else {
            headlineContentColor = j;
        }
        if ((i3 & 64) != 0) {
            if ((i2 & 1572864) == 0) {
                dateRangePickerDefaults = this;
                if (composerStartRestartGroup.changed(dateRangePickerDefaults)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i4 |= i10;
            }
            if ((599187 & i4) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                        headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
                }
                Strings.Companion companion15 = Strings.INSTANCE;
                final String strM1471getString2EP1pXo119 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
                final String strM1471getString2EP1pXo1110 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
                ComposableLambda composableLambdaRememberComposableLambda11113 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                    public final void invoke(Composer composer2, int i111114) {
                        if (!composer2.shouldExecute((i111114 & 3) != 2, i111114 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(850203865, i111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo119, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11114 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                    public final void invoke(Composer composer2, int i111114) {
                        if (!composer2.shouldExecute((i111114 & 3) != 2, i111114 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(282231642, i111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                        }
                        TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo1110, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                ComposableLambda composableLambdaRememberComposableLambda11115 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                    public final void invoke(Composer composer2, int i111114) {
                        if (!composer2.shouldExecute((i111114 & 3) != 2, i111114 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-320655704, i111114, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                        }
                        TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
                Locale localeDefaultLocale15 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
                int i111114 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
                int i111115 = ((i4 >> 12) & 896) | 6;
                int i111116 = i5;
                Modifier modifier19 = modifier4;
                long j17 = headlineContentColor;
                dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i111116, datePickerFormatter, modifier19, j17, strM1471getString2EP1pXo119, strM1471getString2EP1pXo1110, composableLambdaRememberComposableLambda11113, composableLambdaRememberComposableLambda11114, composableLambdaRememberComposableLambda11115, localeDefaultLocale15, composerStartRestartGroup, i111114, i111115);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j17;
                modifier3 = modifier19;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = headlineContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 1572864;
        dateRangePickerDefaults = this;
        if ((599187 & i4) != 599186) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 32) != 0) {
                    i4 &= -458753;
                    headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                }
            } else {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 32) != 0) {
                    i4 &= -458753;
                    headlineContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getHeadlineContentColor();
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1655228151, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
            }
            Strings.Companion companion16 = Strings.INSTANCE;
            final String strM1471getString2EP1pXo1111 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
            final String strM1471getString2EP1pXo1112 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composerStartRestartGroup, 0);
            ComposableLambda composableLambdaRememberComposableLambda11116 = ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                public final void invoke(Composer composer2, int i111117) {
                    if (!composer2.shouldExecute((i111117 & 3) != 2, i111117 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(850203865, i111117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                    }
                    TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo1111, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            ComposableLambda composableLambdaRememberComposableLambda11117 = ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                public final void invoke(Composer composer2, int i111117) {
                    if (!composer2.shouldExecute((i111117 & 3) != 2, i111117 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(282231642, i111117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                    }
                    TextKt.m1097TextNvy7gAk(strM1471getString2EP1pXo1112, null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 262138);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            ComposableLambda composableLambdaRememberComposableLambda11118 = ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                public final void invoke(Composer composer2, int i111117) {
                    if (!composer2.shouldExecute((i111117 & 3) != 2, i111117 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-320655704, i111117, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                    }
                    TextKt.m1097TextNvy7gAk("-", null, headlineContentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 6, 0, 262138);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54);
            Locale localeDefaultLocale16 = CalendarLocale_androidKt.defaultLocale(composerStartRestartGroup, 0);
            int i111117 = (458752 & i4) | (i4 & 14) | 905969664 | (i4 & 112) | (i4 & 896) | (i4 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i4);
            int i111118 = ((i4 >> 12) & 896) | 6;
            int i111119 = i5;
            Modifier modifier110 = modifier4;
            long j18 = headlineContentColor;
            dateRangePickerDefaults.m380DateRangePickerHeadlinenZrIstQ(l3, l4, i111119, datePickerFormatter, modifier110, j18, strM1471getString2EP1pXo1111, strM1471getString2EP1pXo1112, composableLambdaRememberComposableLambda11116, composableLambdaRememberComposableLambda11117, composableLambdaRememberComposableLambda11118, localeDefaultLocale16, composerStartRestartGroup, i111117, i111118);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j2 = j18;
            modifier3 = modifier110;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j2 = headlineContentColor;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ra3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerDefaults.c(this.b, l, l2, i, datePickerFormatter, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0043  */
    /* JADX WARN: Code duplicated, block: B:28:0x0047  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0058  */
    /* JADX WARN: Code duplicated, block: B:37:0x0061  */
    /* JADX WARN: Code duplicated, block: B:38:0x0063  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x0088  */
    /* JADX WARN: Code duplicated, block: B:52:0x008b  */
    /* JADX WARN: Code duplicated, block: B:55:0x0090  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:63:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:65:0x0103  */
    /* JADX WARN: Code duplicated, block: B:66:0x0142  */
    /* JADX WARN: Code duplicated, block: B:69:0x0151  */
    /* JADX WARN: Code duplicated, block: B:71:0x0157  */
    /* JADX WARN: Code duplicated, block: B:74:0x0162  */
    /* JADX WARN: Code duplicated, block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DateRangePickerTitle-FNtVw6o, reason: not valid java name */
    public final void m382DateRangePickerTitleFNtVw6o(final int i, Modifier modifier, long j, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long titleContentColor;
        boolean z;
        final Modifier modifier3;
        final long j2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long j3;
        Modifier modifier5;
        DisplayMode.Companion companion;
        Composer composerStartRestartGroup = composer.startRestartGroup(694693107);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i5 = i3 & 2;
        if (i5 == 0) {
            if ((i2 & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    titleContentColor = j;
                    int i6 = composerStartRestartGroup.changed(titleContentColor) ? 256 : 128;
                    i4 |= i6;
                } else {
                    titleContentColor = j;
                }
                i4 |= i6;
            } else {
                titleContentColor = j;
            }
            if ((i4 & 147) != 146) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i5 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 4) != 0) {
                        titleContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getTitleContentColor();
                        i4 &= -897;
                    }
                    j3 = titleContentColor;
                    modifier5 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    j3 = titleContentColor;
                    modifier5 = modifier2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(694693107, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerTitle (DateRangePicker.kt:371)");
                }
                companion = DisplayMode.INSTANCE;
                if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(1880154051);
                    Strings.Companion companion2 = Strings.INSTANCE;
                    TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_title), composerStartRestartGroup, 0), modifier5, j3, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                    composerStartRestartGroup.startReplaceGroup(1880161282);
                    Strings.Companion companion3 = Strings.INSTANCE;
                    TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_input_title), composerStartRestartGroup, 0), modifier5, j3, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1844364305);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j2 = j3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j2 = titleContentColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qa3
                    public final Object invoke(Object obj, Object obj2) {
                        return DateRangePickerDefaults.a(this.b, i, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                titleContentColor = j;
                if (composerStartRestartGroup.changed(titleContentColor)) {
                }
                i4 |= i6;
            } else {
                titleContentColor = j;
            }
            i4 |= i6;
        } else {
            titleContentColor = j;
        }
        if ((i4 & 147) != 146) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    titleContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getTitleContentColor();
                    i4 &= -897;
                }
                j3 = titleContentColor;
                modifier5 = modifier4;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 4) != 0) {
                    titleContentColor = DatePickerDefaults.INSTANCE.colors(composerStartRestartGroup, 6).getTitleContentColor();
                    i4 &= -897;
                }
                j3 = titleContentColor;
                modifier5 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(694693107, i4, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerTitle (DateRangePicker.kt:371)");
            }
            companion = DisplayMode.INSTANCE;
            if (DisplayMode.m407equalsimpl0(i, companion.m412getPickerjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(1880154051);
                Strings.Companion companion4 = Strings.INSTANCE;
                TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_title), composerStartRestartGroup, 0), modifier5, j3, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                composerStartRestartGroup.endReplaceGroup();
            } else if (DisplayMode.m407equalsimpl0(i, companion.m411getInputjFl4v0())) {
                composerStartRestartGroup.startReplaceGroup(1880161282);
                Strings.Companion companion5 = Strings.INSTANCE;
                TextKt.m1097TextNvy7gAk(Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_input_title), composerStartRestartGroup, 0), modifier5, j3, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, i4 & PointerIconCompat.TYPE_TEXT, 0, 262136);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1844364305);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j2 = j3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j2 = titleContentColor;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qa3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerDefaults.a(this.b, i, modifier3, j2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
