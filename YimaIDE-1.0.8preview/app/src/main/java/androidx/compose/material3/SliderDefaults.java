package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.SliderDefaults;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ScaleKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006Js\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0012\u0010\u0013J?\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0004\b\"\u0010#JG\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0001¢\u0006\u0004\b&\u0010'J3\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010+J3\u0010(\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010,J\u0093\u0001\u0010(\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0007¢\u0006\u0004\b7\u00108J\u009b\u0001\u0010(\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u00109\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0001¢\u0006\u0004\b:\u0010;J\u009d\u0001\u0010<\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u0002052\b\b\u0002\u00109\u001a\u000205H\u0001¢\u0006\u0004\b=\u0010>J\u009d\u0001\u0010?\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u00109\u001a\u0002052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u001f\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12#\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u0010@\u001a\u00020\u001f2\u0006\u0010A\u001a\u00020\u001fH\u0003¢\u0006\u0004\bB\u0010CJ3\u0010(\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010FJ\u0093\u0001\u0010(\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0007¢\u0006\u0004\b7\u0010GJ\u009b\u0001\u0010(\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\u0006\u00109\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052!\b\u0002\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12%\b\u0002\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205H\u0001¢\u0006\u0004\b:\u0010HJ\u008d\u0001\u0010?\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\u0006\u00109\u001a\u0002052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u001f\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12#\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205H\u0003¢\u0006\u0004\bI\u0010JJï\u0001\u0010K\u001a\u00020\u0019*\u00020/2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020O2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010Q\u001a\u0002052\u0006\u0010R\u001a\u0002052\u0006\u0010S\u001a\u0002052\u0006\u0010T\u001a\u0002052\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00109\u001a\u0002052\u001f\u0010-\u001a\u001b\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0019\u0018\u00010.¢\u0006\u0002\b12#\u00102\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001903¢\u0006\u0002\b12\u0006\u0010U\u001a\u00020\u001f2\b\b\u0002\u0010@\u001a\u00020\u001f2\b\b\u0002\u0010V\u001a\u00020W2\b\b\u0002\u0010A\u001a\u00020\u001fH\u0002¢\u0006\u0004\bX\u0010YJC\u0010Z\u001a\u00020\u0019*\u00020/2\u0006\u0010V\u001a\u00020W2\u0006\u0010[\u001a\u0002002\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020\b2\u0006\u0010_\u001a\u00020O2\u0006\u0010`\u001a\u00020OH\u0002¢\u0006\u0004\ba\u0010bJ)\u0010-\u001a\u00020\u0019*\u00020/2\u0006\u0010[\u001a\u0002002\u0006\u0010\\\u001a\u0002052\u0006\u0010^\u001a\u00020\b¢\u0006\u0004\bc\u0010dR\u0018\u0010\u0014\u001a\u00020\u0005*\u00020\u00158@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010e\u001a\u000205¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010gR\u0013\u0010i\u001a\u000205¢\u0006\n\n\u0002\u0010h\u001a\u0004\bj\u0010gR\u000e\u0010k\u001a\u00020lX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Landroidx/compose/material3/SliderDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/SliderColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SliderColors;", "thumbColor", "Landroidx/compose/ui/graphics/Color;", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SliderColors;", "defaultSliderColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultSliderColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SliderColors;", "Thumb", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "thumbSize", "Landroidx/compose/ui/unit/DpSize;", "Thumb-9LiSoMs", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "sliderState", "Landroidx/compose/material3/SliderState;", "Thumb-HwbPF3A$material3", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "Track", "sliderPositions", "Landroidx/compose/material3/SliderPositions;", "(Landroidx/compose/material3/SliderPositions;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ExtensionFunctionType;", "drawTick", "Lkotlin/Function3;", "thumbTrackGapSize", "Landroidx/compose/ui/unit/Dp;", "trackInsideCornerSize", "Track-4EFweAY", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "trackCornerSize", "Track-mnvyFg4$material3", "(Landroidx/compose/material3/SliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "CenteredTrack", "CenteredTrack-7LSsfP0$material3", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFFLandroidx/compose/runtime/Composer;II)V", "TrackImpl", "enableCornerShrinking", "isCentered", "TrackImpl-VvwgllI", "(Landroidx/compose/material3/SliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFZZLandroidx/compose/runtime/Composer;II)V", "rangeSliderState", "Landroidx/compose/material3/RangeSliderState;", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/RangeSliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "TrackImpl-xlyIBlM", "(Landroidx/compose/material3/RangeSliderState;FLandroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;I)V", "drawTrack", "tickFractions", "", "activeRangeStart", "", "activeRangeEnd", "startThumbWidth", "startThumbHeight", "endThumbWidth", "endThumbHeight", "isRangeSlider", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "drawTrack-GVD57ws", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;[FFFJJJJFFFFFFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;ZZLandroidx/compose/foundation/gestures/Orientation;Z)V", "drawTrackPath", "offset", "size", "Landroidx/compose/ui/geometry/Size;", "color", "startCornerRadius", "endCornerRadius", "drawTrackPath-zXTsYAs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/foundation/gestures/Orientation;JJJFF)V", "drawStopIndicator-x3O1jOs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJ)V", "TrackStopIndicatorSize", "getTrackStopIndicatorSize-D9Ej5fM", "()F", "F", "TickSize", "getTickSize-D9Ej5fM", "trackPath", "Landroidx/compose/ui/graphics/Path;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    private static final float TickSize;
    private static final float TrackStopIndicatorSize;
    private static final Path trackPath;

    static {
        SliderTokens sliderTokens = SliderTokens.INSTANCE;
        TrackStopIndicatorSize = sliderTokens.m2113getStopIndicatorSizeD9Ej5fM();
        TickSize = sliderTokens.m2113getStopIndicatorSizeD9Ej5fM();
        trackPath = AndroidPath_androidKt.Path();
    }

    private SliderDefaults() {
    }

    /* JADX INFO: renamed from: TrackImpl-VvwgllI, reason: not valid java name */
    private final void m896TrackImplVvwgllI(final SliderState sliderState, final float f, final Modifier modifier, final boolean z, final SliderColors sliderColors, final Function2<? super DrawScope, ? super Offset, Unit> function2, final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, final float f2, final float f3, final boolean z2, final boolean z3, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        Composer composer2;
        Modifier modifierFillMaxHeight$default;
        Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(133396521);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(sliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changed(sliderColors) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changed(f2) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(f3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changed(z3) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if (composerStartRestartGroup.shouldExecute(((i3 & 306783379) == 306783378 && (i4 & 3) == 2) ? false : true, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(133396521, i3, i4, "androidx.compose.material3.SliderDefaults.TrackImpl (Slider.kt:1587)");
            }
            int i5 = i3;
            final long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, false);
            final long jM895trackColorWaAFU9c$material4 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
            final long jM894tickColorWaAFU9c$material3 = sliderColors.m894tickColorWaAFU9c$material3(z, false);
            int i6 = i4;
            final long jM894tickColorWaAFU9c$material4 = sliderColors.m894tickColorWaAFU9c$material3(z, true);
            if (sliderState.getOrientation() == Orientation.Vertical) {
                modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.width-3ABfNKs(modifier, SliderKt.getTrackHeight()), 0.0f, 1, (Object) null);
                if (sliderState.getReverseVerticalDirection()) {
                    modifierFillMaxHeight$default = ScaleKt.scale(modifierFillMaxHeight$default, 1.0f, -1.0f);
                }
            } else {
                modifierFillMaxHeight$default = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            int i7 = i5 & 112;
            boolean zChangedInstance = (i7 == 32) | composerStartRestartGroup.changedInstance(sliderState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function3() { // from class: xed
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SliderDefaults.j(f, sliderState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierThen = modifierFillMaxHeight$default.then(LayoutModifierKt.layout(companion, (Function3) objRememberedValue));
            boolean zChangedInstance2 = (i7 == 32) | composerStartRestartGroup.changedInstance(sliderState) | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material4) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material4) | ((i5 & 29360128) == 8388608) | ((i5 & 234881024) == 67108864) | ((i5 & 458752) == 131072) | ((i5 & 3670016) == 1048576) | ((i5 & 1879048192) == 536870912) | ((i6 & 14) == 4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                modifier2 = modifierThen;
                Function1 function1 = new Function1() { // from class: zed
                    public final Object invoke(Object obj) {
                        return SliderDefaults.c(f, sliderState, jM895trackColorWaAFU9c$material3, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, f2, f3, function2, function3, z2, z3, (DrawScope) obj);
                    }
                };
                composer2 = composerStartRestartGroup;
                composer2.updateRememberedValue(function1);
                objRememberedValue2 = function1;
            } else {
                modifier2 = modifierThen;
                composer2 = composerStartRestartGroup;
            }
            CanvasKt.Canvas(modifier2, (Function1) objRememberedValue2, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: afd
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.q(this.b, sliderState, f, modifier, z, sliderColors, function2, function3, f2, f3, z2, z3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TrackImpl-xlyIBlM, reason: not valid java name */
    private final void m897TrackImplxlyIBlM(final RangeSliderState rangeSliderState, final float f, final Modifier modifier, final boolean z, final SliderColors sliderColors, final Function2<? super DrawScope, ? super Offset, Unit> function2, final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, final float f2, final float f3, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1719396904);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(rangeSliderState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(sliderColors) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f2) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i2 |= composerStartRestartGroup.changed(f3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 38347923) != 38347922, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1719396904, i2, -1, "androidx.compose.material3.SliderDefaults.TrackImpl (Slider.kt:1811)");
            }
            final long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, false);
            int i3 = i2;
            final long jM895trackColorWaAFU9c$material4 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
            final long jM894tickColorWaAFU9c$material3 = sliderColors.m894tickColorWaAFU9c$material3(z, false);
            final long jM894tickColorWaAFU9c$material4 = sliderColors.m894tickColorWaAFU9c$material3(z, true);
            Modifier modifier2 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function3() { // from class: hfd
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SliderDefaults.b((MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                composer3 = composerStartRestartGroup;
                composer3.updateRememberedValue(objRememberedValue);
            } else {
                composer3 = composerStartRestartGroup;
            }
            Modifier modifierLayout = LayoutModifierKt.layout(modifier2, (Function3) objRememberedValue);
            boolean zChangedInstance = ((i3 & 112) == 32) | composer3.changedInstance(rangeSliderState) | composer3.changed(jM895trackColorWaAFU9c$material3) | composer3.changed(jM895trackColorWaAFU9c$material4) | composer3.changed(jM894tickColorWaAFU9c$material3) | composer3.changed(jM894tickColorWaAFU9c$material4) | ((i3 & 29360128) == 8388608) | ((i3 & 234881024) == 67108864) | ((i3 & 458752) == 131072) | ((i3 & 3670016) == 1048576);
            Object objRememberedValue2 = composer3.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                composer2 = composer3;
                Function1 function1 = new Function1() { // from class: ifd
                    public final Object invoke(Object obj) {
                        return SliderDefaults.i(f, rangeSliderState, jM895trackColorWaAFU9c$material3, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, f2, f3, function2, function3, (DrawScope) obj);
                    }
                };
                composer2.updateRememberedValue(function1);
                objRememberedValue2 = function1;
            } else {
                composer2 = composer3;
            }
            CanvasKt.Canvas(modifierLayout, (Function1) objRememberedValue2, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jfd
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.n(this.b, rangeSliderState, f, modifier, z, sliderColors, function2, function3, f2, f3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m906TrackmnvyFg4$material3(rangeSliderState, f, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static MeasureResult b(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        return measureScope.layout(placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), MapsKt.mapOf(TuplesKt.to(SliderKt.getCornerSizeAlignmentLine(), Integer.valueOf(placeableMo4605measureBRTryo0.getHeight() / 2))), new Function1() { // from class: ped
            public final Object invoke(Object obj) {
                return SliderDefaults.o(placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        });
    }

    public static Unit c(float f, SliderState sliderState, long j, long j2, long j3, long j4, float f2, float f3, Function2 function2, Function3 function3, boolean z, boolean z2, DrawScope drawScope) {
        float fMo4557toPx0680j_4;
        if (Dp.m6027equalsimpl0(f, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM())) {
            fMo4557toPx0680j_4 = (sliderState.getOrientation() == Orientation.Vertical ? Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) : Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L))) / 2.0f;
        } else {
            fMo4557toPx0680j_4 = drawScope.mo4557toPx0680j_4(f);
        }
        INSTANCE.m898drawTrackGVD57ws(drawScope, sliderState.getTickFractions(), 0.0f, sliderState.getCoercedValueAsFraction(), j, j2, j3, j4, drawScope.mo4554toDpu2uoSUM(0), drawScope.mo4554toDpu2uoSUM(0), drawScope.mo4554toDpu2uoSUM(sliderState.getThumbWidth$material3()), drawScope.mo4554toDpu2uoSUM(sliderState.getThumbHeight$material3()), f2, f3, drawScope.mo4553toDpu2uoSUM(fMo4557toPx0680j_4), function2, function3, false, z, sliderState.getOrientation(), z2);
        return Unit.INSTANCE;
    }

    public static Unit d(SliderDefaults sliderDefaults, SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f, float f2, float f3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m901CenteredTrack7LSsfP0$material3(sliderState, modifier, z, sliderColors, function2, function3, f, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:111:0x0264  */
    /* JADX WARN: Code duplicated, block: B:184:0x0403  */
    /* JADX WARN: Code duplicated, block: B:213:0x0508  */
    /* JADX WARN: Code duplicated, block: B:72:0x0121  */
    /* JADX INFO: renamed from: drawTrack-GVD57ws, reason: not valid java name */
    private final void m898drawTrackGVD57ws(DrawScope drawScope, float[] fArr, float f, float f2, long j, long j2, long j3, long j4, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, boolean z, boolean z2, Orientation orientation, boolean z3) {
        float f10;
        float f11;
        boolean z4;
        long jM2881constructorimpl;
        long jM2949constructorimpl;
        float f12;
        float f13;
        long jM2881constructorimpl2;
        float f14;
        float f15;
        float f16;
        ClosedFloatingPointRange closedFloatingPointRange;
        ClosedFloatingPointRange closedFloatingPointRange2;
        long jM2881constructorimpl3;
        long jM2881constructorimpl4;
        long jM2949constructorimpl2;
        long jM2881constructorimpl5;
        long jM2949constructorimpl3;
        long jM2881constructorimpl6;
        float fMo4557toPx0680j_4;
        float fMo4557toPx0680j_5;
        float fMo4557toPx0680j_6;
        boolean z5 = orientation == Orientation.Vertical;
        boolean z6 = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
        boolean z7 = z6 && !z5;
        float fMo4557toPx0680j_7 = drawScope.mo4557toPx0680j_4(f9);
        long jMo3708getSizeNHjbRc = drawScope.mo3708getSizeNHjbRc();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (z5 ? jMo3708getSizeNHjbRc & 4294967295L : jMo3708getSizeNHjbRc >> 32));
        boolean z8 = Intrinsics.areEqual(f, ArraysKt.firstOrNull(fArr)) || Intrinsics.areEqual(f, ArraysKt.lastOrNull(fArr));
        float f17 = ((fArr.length == 0) || (Intrinsics.areEqual(f2, ArraysKt.firstOrNull(fArr)) || Intrinsics.areEqual(f2, ArraysKt.lastOrNull(fArr)))) ? 0.0f + ((fIntBitsToFloat - 0.0f) * f2) : (((fIntBitsToFloat - 0.0f) - (fMo4557toPx0680j_7 * 2.0f)) * f2) + 0.0f + fMo4557toPx0680j_7;
        float f18 = ((fArr.length == 0) || z8) ? 0.0f + ((fIntBitsToFloat - 0.0f) * f) : (((fIntBitsToFloat - 0.0f) - (fMo4557toPx0680j_7 * 2.0f)) * f) + 0.0f + fMo4557toPx0680j_7;
        float fMo4557toPx0680j_8 = drawScope.mo4557toPx0680j_4(f8);
        if (Dp.m6021compareTo0680j_4(f7, Dp.m6022constructorimpl(0.0f)) > 0) {
            if (z5) {
                fMo4557toPx0680j_4 = (drawScope.mo4557toPx0680j_4(f4) / 2.0f) + drawScope.mo4557toPx0680j_4(f7);
                fMo4557toPx0680j_5 = drawScope.mo4557toPx0680j_4(f6) / 2.0f;
                fMo4557toPx0680j_6 = drawScope.mo4557toPx0680j_4(f7);
            } else {
                fMo4557toPx0680j_4 = (drawScope.mo4557toPx0680j_4(f3) / 2.0f) + drawScope.mo4557toPx0680j_4(f7);
                fMo4557toPx0680j_5 = drawScope.mo4557toPx0680j_4(f5) / 2.0f;
                fMo4557toPx0680j_6 = drawScope.mo4557toPx0680j_4(f7);
            }
            f10 = fMo4557toPx0680j_4;
            f11 = fMo4557toPx0680j_5 + fMo4557toPx0680j_6;
        } else {
            f10 = 0.0f;
            f11 = 0.0f;
        }
        long jMo3707getCenterF1C5BW0 = drawScope.mo3707getCenterF1C5BW0();
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (z5 ? jMo3707getCenterF1C5BW0 & 4294967295L : jMo3707getCenterF1C5BW0 >> 32));
        float f19 = f10 + 0.0f;
        if (z2) {
            if (!(fArr.length == 0)) {
                f19 += fMo4557toPx0680j_7;
            }
        } else {
            f19 += fMo4557toPx0680j_7;
        }
        float fMin = z3 ? Math.min(f17, fIntBitsToFloat2) : f18;
        if ((z3 || z) && fMin > f19) {
            float f20 = z7 ? fMo4557toPx0680j_8 : fMo4557toPx0680j_7;
            float f21 = z7 ? fMo4557toPx0680j_7 : fMo4557toPx0680j_8;
            float f22 = fMin - f10;
            if (z7) {
                z4 = true;
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) - f22)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            } else {
                z4 = true;
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            }
            if (z5) {
                jM2949constructorimpl = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(f22 - 0.0f)) & 4294967295L) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)))) << 32));
            } else {
                jM2949constructorimpl = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)))) & 4294967295L) | (((long) Float.floatToRawIntBits(f22 - 0.0f)) << 32));
            }
            f12 = fIntBitsToFloat2;
            f13 = f17;
            m900drawTrackPathzXTsYAs(drawScope, orientation, jM2881constructorimpl, jM2949constructorimpl, j, f20, f21);
            if (z5) {
                jM2881constructorimpl2 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_7 + 0.0f)) & 4294967295L));
            } else if (z6) {
                jM2881constructorimpl2 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) - 0.0f) - fMo4557toPx0680j_7)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
            } else {
                jM2881constructorimpl2 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMo4557toPx0680j_7 + 0.0f)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
            }
            if (function2 != null) {
                function2.invoke(drawScope, Offset.m2878boximpl(jM2881constructorimpl2));
                Unit unit = Unit.INSTANCE;
            }
        } else {
            f12 = fIntBitsToFloat2;
            f13 = f17;
            z4 = true;
        }
        float f23 = fIntBitsToFloat - f11;
        if (z2) {
            if (!(fArr.length == 0 ? z4 : false)) {
                f23 -= fMo4557toPx0680j_7;
            }
        } else {
            f23 -= fMo4557toPx0680j_7;
        }
        float fMax = z3 ? Math.max(f13, f12) : f13;
        if (fMax < f23) {
            float f24 = z7 ? fMo4557toPx0680j_7 : fMo4557toPx0680j_8;
            float f25 = z7 ? fMo4557toPx0680j_8 : fMo4557toPx0680j_7;
            float f26 = fMax + f11;
            float f27 = fIntBitsToFloat - f26;
            if (z5) {
                jM2881constructorimpl5 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(f26)) & 4294967295L));
            } else if (z6) {
                jM2881constructorimpl5 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            } else {
                jM2881constructorimpl5 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f26)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            }
            if (z5) {
                jM2949constructorimpl3 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(f27)) & 4294967295L));
            } else if (!z6 || z) {
                jM2949constructorimpl3 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(f27)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)))) & 4294967295L));
            } else {
                jM2949constructorimpl3 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) - f26)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)))) & 4294967295L));
            }
            m900drawTrackPathzXTsYAs(drawScope, orientation, jM2881constructorimpl5, jM2949constructorimpl3, j, f24, f25);
            if (z5) {
                jM2881constructorimpl6 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat - fMo4557toPx0680j_7)) & 4294967295L));
            } else if (z6) {
                jM2881constructorimpl6 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMo4557toPx0680j_7)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
            } else {
                jM2881constructorimpl6 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat - fMo4557toPx0680j_7)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
            }
            if (function2 != null) {
                function2.invoke(drawScope, Offset.m2878boximpl(jM2881constructorimpl6));
                Unit unit2 = Unit.INSTANCE;
            }
        }
        if (z3) {
            f14 = fMin + (fMin < f12 ? f10 : 0.0f);
        } else {
            f14 = z ? f18 + f10 : 0.0f;
        }
        if (z3) {
            f15 = fMax - (fMax > f12 ? f11 : 0.0f);
        } else {
            f15 = f13 - f11;
        }
        float f28 = (z7 || z3 || z) ? fMo4557toPx0680j_8 : fMo4557toPx0680j_7;
        float f29 = (!z7 || z3 || z) ? fMo4557toPx0680j_8 : fMo4557toPx0680j_7;
        float f30 = (!z7 || z3 || z) ? f15 - f14 : f15;
        if (z2) {
            if (fArr.length == 0 ? z4 : false) {
                f16 = 0.0f;
            } else {
                f16 = f28;
            }
        } else {
            f16 = f28;
        }
        if (f30 > f16) {
            if (z5) {
                jM2881constructorimpl4 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(f14)) & 4294967295L));
            } else if (z6) {
                jM2881constructorimpl4 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) - f15)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            } else {
                jM2881constructorimpl4 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f14)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
            }
            long j5 = jM2881constructorimpl4;
            if (z5) {
                jM2949constructorimpl2 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(f30)) & 4294967295L));
            } else if (!z6 || z3 || z) {
                jM2949constructorimpl2 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(f30)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)))) & 4294967295L));
            } else {
                jM2949constructorimpl2 = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(f15)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)))) & 4294967295L));
            }
            m900drawTrackPathzXTsYAs(drawScope, orientation, j5, jM2949constructorimpl2, j2, f28, f29);
        }
        float f31 = 0.0f + fMo4557toPx0680j_7;
        float f32 = fIntBitsToFloat - fMo4557toPx0680j_7;
        ClosedFloatingPointRange closedFloatingPointRangeRangeTo = RangesKt.rangeTo(f14, f15);
        ClosedFloatingPointRange closedFloatingPointRangeRangeTo2 = RangesKt.rangeTo(f12 - f11, f12 + f11);
        ClosedFloatingPointRange closedFloatingPointRangeRangeTo3 = RangesKt.rangeTo(f18 - f10, f18 + f10);
        ClosedFloatingPointRange closedFloatingPointRangeRangeTo4 = RangesKt.rangeTo(f13 - f11, f13 + f11);
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            float f33 = fArr[i2];
            int i3 = i + 1;
            if (function2 == null || !(((z3 || z) && i == 0) || i == fArr.length - 1)) {
                float fLerp = MathHelpersKt.lerp(f31, f32, f33);
                if ((z3 && closedFloatingPointRangeRangeTo2.contains(Float.valueOf(fLerp))) || ((z && closedFloatingPointRangeRangeTo3.contains(Float.valueOf(fLerp))) || closedFloatingPointRangeRangeTo4.contains(Float.valueOf(fLerp)))) {
                    closedFloatingPointRange = closedFloatingPointRangeRangeTo2;
                    closedFloatingPointRange2 = closedFloatingPointRangeRangeTo3;
                } else {
                    if (z5) {
                        closedFloatingPointRange = closedFloatingPointRangeRangeTo2;
                        closedFloatingPointRange2 = closedFloatingPointRangeRangeTo3;
                        jM2881constructorimpl3 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() >> 32)))) << 32) | (((long) Float.floatToRawIntBits(fLerp)) & 4294967295L));
                    } else {
                        closedFloatingPointRange = closedFloatingPointRangeRangeTo2;
                        closedFloatingPointRange2 = closedFloatingPointRangeRangeTo3;
                        if (z6) {
                            jM2881constructorimpl3 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)) - fLerp)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
                        } else {
                            jM2881constructorimpl3 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fLerp)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
                        }
                    }
                    function3.invoke(drawScope, Offset.m2878boximpl(jM2881constructorimpl3), Color.m3124boximpl(closedFloatingPointRangeRangeTo.contains(Float.valueOf(fLerp)) ? j4 : j3));
                }
            } else {
                closedFloatingPointRange = closedFloatingPointRangeRangeTo2;
                closedFloatingPointRange2 = closedFloatingPointRangeRangeTo3;
            }
            i2++;
            closedFloatingPointRangeRangeTo2 = closedFloatingPointRange;
            closedFloatingPointRangeRangeTo3 = closedFloatingPointRange2;
            i = i3;
        }
    }

    /* JADX INFO: renamed from: drawTrack-GVD57ws$default, reason: not valid java name */
    public static /* synthetic */ void m899drawTrackGVD57ws$default(SliderDefaults sliderDefaults, DrawScope drawScope, float[] fArr, float f, float f2, long j, long j2, long j3, long j4, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Function2 function2, Function3 function3, boolean z, boolean z2, Orientation orientation, boolean z3, int i, Object obj) {
        sliderDefaults.m898drawTrackGVD57ws(drawScope, fArr, f, f2, j, j2, j3, j4, f3, f4, f5, f6, f7, f8, f9, function2, function3, z, (i & 131072) != 0 ? false : z2, (i & 262144) != 0 ? Orientation.Horizontal : orientation, (i & 524288) != 0 ? false : z3);
    }

    /* JADX INFO: renamed from: drawTrackPath-zXTsYAs, reason: not valid java name */
    private final void m900drawTrackPathzXTsYAs(DrawScope drawScope, Orientation orientation, long j, long j2, long j3, float f, float f2) {
        RoundRect roundRectM2941RoundRectZAM2FJo;
        long jM2843constructorimpl = CornerRadius.m2843constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L));
        long jM2843constructorimpl2 = CornerRadius.m2843constructorimpl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L));
        if (orientation == Orientation.Vertical) {
            float fIntBitsToFloat = Float.intBitsToFloat((int) (j2 >> 32));
            roundRectM2941RoundRectZAM2FJo = RoundRectKt.m2941RoundRectZAM2FJo(RectKt.m2929Recttz77jQw(j, Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j2 & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32))), jM2843constructorimpl, jM2843constructorimpl, jM2843constructorimpl2, jM2843constructorimpl2);
        } else {
            float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j2 >> 32));
            roundRectM2941RoundRectZAM2FJo = RoundRectKt.m2941RoundRectZAM2FJo(RectKt.m2929Recttz77jQw(j, Size.m2949constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j2 & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat2) << 32))), jM2843constructorimpl, jM2843constructorimpl2, jM2843constructorimpl2, jM2843constructorimpl);
        }
        Path path = trackPath;
        Path.addRoundRect$default(path, roundRectM2941RoundRectZAM2FJo, null, 2, null);
        DrawScope.m3698drawPathLG529CI$default(drawScope, path, j3, 0.0f, null, null, 0, 60, null);
        path.rewind();
    }

    public static Unit e(SliderDefaults sliderDefaults, SliderState sliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m907TrackmnvyFg4$material3(sliderState, f, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit f(SliderDefaults sliderDefaults, SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, int i, int i2, Composer composer, int i3) {
        sliderDefaults.Track(sliderState, modifier, sliderColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit g(SliderColors sliderColors, boolean z, DrawScope drawScope, Offset offset) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, offset.m2899unboximpl(), TrackStopIndicatorSize, jM895trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    public static Unit h(SliderDefaults sliderDefaults, MutableInteractionSource mutableInteractionSource, Modifier modifier, SliderColors sliderColors, boolean z, long j, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m902Thumb9LiSoMs(mutableInteractionSource, modifier, sliderColors, z, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit i(float f, RangeSliderState rangeSliderState, long j, long j2, long j3, long j4, float f2, float f3, Function2 function2, Function3 function3, DrawScope drawScope) {
        m899drawTrackGVD57ws$default(INSTANCE, drawScope, rangeSliderState.getTickFractions(), rangeSliderState.getCoercedActiveRangeStartAsFraction$material3(), rangeSliderState.getCoercedActiveRangeEndAsFraction$material3(), j, j2, j3, j4, drawScope.mo4553toDpu2uoSUM(rangeSliderState.getStartThumbWidth$material3()), drawScope.mo4553toDpu2uoSUM(rangeSliderState.getStartThumbHeight$material3()), drawScope.mo4553toDpu2uoSUM(rangeSliderState.getEndThumbWidth$material3()), drawScope.mo4553toDpu2uoSUM(rangeSliderState.getEndThumbHeight$material3()), f2, f3, drawScope.mo4553toDpu2uoSUM(Dp.m6027equalsimpl0(f, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM()) ? Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L)) / 2.0f : drawScope.mo4557toPx0680j_4(f)), function2, function3, true, false, null, false, 917504, null);
        return Unit.INSTANCE;
    }

    public static MeasureResult j(float f, SliderState sliderState, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        int iMo4551roundToPx0680j_4;
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        if (Dp.m6027equalsimpl0(f, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM())) {
            iMo4551roundToPx0680j_4 = sliderState.getOrientation() == Orientation.Vertical ? placeableMo4605measureBRTryo0.getWidth() / 2 : placeableMo4605measureBRTryo0.getHeight() / 2;
        } else {
            iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(f);
        }
        return measureScope.layout(placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), MapsKt.mapOf(TuplesKt.to(SliderKt.getCornerSizeAlignmentLine(), Integer.valueOf(iMo4551roundToPx0680j_4))), new Function1() { // from class: wed
            public final Object invoke(Object obj) {
                return SliderDefaults.p(placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        });
    }

    public static Unit k(long j, SliderPositions sliderPositions, long j2, long j3, long j4, DrawScope drawScope) {
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
        long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L));
        long jM2881constructorimpl2 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32)))) << 32));
        long j5 = jM2881constructorimpl;
        long j6 = z ? jM2881constructorimpl2 : j5;
        if (!z) {
            j5 = jM2881constructorimpl2;
        }
        float fMo4557toPx0680j_4 = drawScope.mo4557toPx0680j_4(TickSize);
        float fMo4557toPx0680j_5 = drawScope.mo4557toPx0680j_4(SliderKt.getTrackHeight());
        StrokeCap.Companion companion = StrokeCap.INSTANCE;
        DrawScope.m3694drawLineNGM6Ib0$default(drawScope, j, j6, j5, fMo4557toPx0680j_5, companion.m3509getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        int i = (int) (j6 >> 32);
        int i2 = (int) (j5 >> 32);
        long j7 = j6;
        long j8 = j5;
        DrawScope.m3694drawLineNGM6Ib0$default(drawScope, j2, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat(i) + ((Float.intBitsToFloat(i2) - Float.intBitsToFloat(i)) * ((Number) sliderPositions.getActiveRange().getStart()).floatValue()))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L)), Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat(i) + ((Float.intBitsToFloat(i2) - Float.intBitsToFloat(i)) * ((Number) sliderPositions.getActiveRange().getEndInclusive()).floatValue()))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L)), fMo4557toPx0680j_5, companion.m3509getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        float[] tickFractions = sliderPositions.getTickFractions();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int length = tickFractions.length;
        for (int i3 = 0; i3 < length; i3++) {
            float f = tickFractions[i3];
            Boolean boolValueOf = Boolean.valueOf(f > ((Number) sliderPositions.getActiveRange().getEndInclusive()).floatValue() || f < ((Number) sliderPositions.getActiveRange().getStart()).floatValue());
            Object arrayList = linkedHashMap.get(boolValueOf);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(boolValueOf, arrayList);
            }
            ((List) arrayList).add(Float.valueOf(f));
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            boolean zBooleanValue = ((Boolean) entry.getKey()).booleanValue();
            List list = (List) entry.getValue();
            boolean z2 = zBooleanValue;
            ArrayList arrayList2 = new ArrayList(list.size());
            int i4 = 0;
            for (int size = list.size(); i4 < size; size = size) {
                arrayList2.add(Offset.m2878boximpl(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.mo3707getCenterF1C5BW0() & 4294967295L)))) & 4294967295L) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (OffsetKt.m2912lerpWko1d7g(j7, j8, ((Number) list.get(i4)).floatValue()) >> 32)))) << 32))));
                i4++;
                z2 = z2;
            }
            j7 = j7;
            j8 = j8;
            DrawScope.m3699drawPointsF8ZwMP8$default(drawScope, arrayList2, PointMode.INSTANCE.m3454getPointsr_lszbg(), z2 ? j3 : j4, fMo4557toPx0680j_4, StrokeCap.INSTANCE.m3509getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        }
        return Unit.INSTANCE;
    }

    public static Unit l(SliderDefaults sliderDefaults, MutableInteractionSource mutableInteractionSource, SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, long j, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m903ThumbHwbPF3A$material3(mutableInteractionSource, sliderState, modifier, sliderColors, z, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit m(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f, float f2, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m904Track4EFweAY(rangeSliderState, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit n(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, int i, Composer composer, int i2) {
        sliderDefaults.m897TrackImplxlyIBlM(rangeSliderState, f, modifier, z, sliderColors, function2, function3, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit o(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit p(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit q(SliderDefaults sliderDefaults, SliderState sliderState, float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f2, float f3, boolean z2, boolean z3, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m896TrackImplVvwgllI(sliderState, f, modifier, z, sliderColors, function2, function3, f2, f3, z2, z3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static Unit r(SliderDefaults sliderDefaults, SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2 function2, Function3 function3, float f, float f2, int i, int i2, Composer composer, int i3) {
        sliderDefaults.m905Track4EFweAY(sliderState, modifier, z, sliderColors, (Function2<? super DrawScope, ? super Offset, Unit>) function2, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) function3, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit s(SliderColors sliderColors, boolean z, DrawScope drawScope, Offset offset) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, offset.m2899unboximpl(), TrackStopIndicatorSize, jM895trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    public static Unit t(SliderColors sliderColors, boolean z, DrawScope drawScope, Offset offset) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, offset.m2899unboximpl(), TrackStopIndicatorSize, jM895trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    public static Unit u(SliderDefaults sliderDefaults, RangeSliderState rangeSliderState, Modifier modifier, SliderColors sliderColors, boolean z, int i, int i2, Composer composer, int i3) {
        sliderDefaults.Track(rangeSliderState, modifier, sliderColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit v(SliderColors sliderColors, boolean z, DrawScope drawScope, Offset offset) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, offset.m2899unboximpl(), TrackStopIndicatorSize, jM895trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    public static Unit w(SliderDefaults sliderDefaults, SliderPositions sliderPositions, Modifier modifier, SliderColors sliderColors, boolean z, int i, int i2, Composer composer, int i3) {
        sliderDefaults.Track(sliderPositions, modifier, sliderColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit x(SliderColors sliderColors, boolean z, DrawScope drawScope, Offset offset) {
        SliderDefaults sliderDefaults = INSTANCE;
        long jM895trackColorWaAFU9c$material3 = sliderColors.m895trackColorWaAFU9c$material3(z, true);
        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, offset.m2899unboximpl(), TrackStopIndicatorSize, jM895trackColorWaAFU9c$material3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x011b  */
    /* JADX WARN: Code duplicated, block: B:101:0x011e  */
    /* JADX WARN: Code duplicated, block: B:103:0x0122  */
    /* JADX WARN: Code duplicated, block: B:105:0x0128  */
    /* JADX WARN: Code duplicated, block: B:106:0x012b  */
    /* JADX WARN: Code duplicated, block: B:110:0x013b  */
    /* JADX WARN: Code duplicated, block: B:111:0x013e  */
    /* JADX WARN: Code duplicated, block: B:114:0x0148  */
    /* JADX WARN: Code duplicated, block: B:116:0x0152  */
    /* JADX WARN: Code duplicated, block: B:126:0x0172 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:127:0x0174  */
    /* JADX WARN: Code duplicated, block: B:129:0x0179  */
    /* JADX WARN: Code duplicated, block: B:132:0x017f  */
    /* JADX WARN: Code duplicated, block: B:135:0x018e  */
    /* JADX WARN: Code duplicated, block: B:137:0x0196  */
    /* JADX WARN: Code duplicated, block: B:139:0x019c  */
    /* JADX WARN: Code duplicated, block: B:145:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:148:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:150:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:153:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:155:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:157:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:159:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:161:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:162:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:164:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:165:0x0202  */
    /* JADX WARN: Code duplicated, block: B:168:0x0212  */
    /* JADX WARN: Code duplicated, block: B:171:0x0257  */
    /* JADX WARN: Code duplicated, block: B:173:0x025c  */
    /* JADX WARN: Code duplicated, block: B:176:0x026f  */
    /* JADX WARN: Code duplicated, block: B:178:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:45:0x007c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:79:0x00db  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:91:0x0100  */
    /* JADX WARN: Code duplicated, block: B:93:0x0104  */
    /* JADX WARN: Code duplicated, block: B:95:0x010e  */
    /* JADX WARN: Code duplicated, block: B:96:0x0111  */
    /* JADX INFO: renamed from: CenteredTrack-7LSsfP0$material3, reason: not valid java name */
    public final void m901CenteredTrack7LSsfP0$material3(final SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f, float f2, float f3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        int i5;
        final SliderColors sliderColors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        int i6;
        int i7;
        int i8;
        final float f4;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z3;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function5;
        final Modifier modifier3;
        final boolean z4;
        final SliderColors sliderColors3;
        final Function2<? super DrawScope, ? super Offset, Unit> function6;
        final float f5;
        final float f6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        float f7;
        int i15;
        float fM6042getUnspecifiedD9Ej5fM;
        Object objRememberedValue;
        boolean z5;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1199441071);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(sliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i16 = i2 & 2;
        if (i16 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        sliderColors2 = sliderColors;
                        int i17 = composerStartRestartGroup.changed(sliderColors2) ? 2048 : 1024;
                        i3 |= i17;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i17;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        function4 = function2;
                        int i18 = composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
                        i3 |= i18;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i18;
                } else {
                    function4 = function2;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                    f4 = f;
                } else {
                    f4 = f;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -7169;
                                sliderColors2 = sliderColorsColors;
                            }
                            if ((i2 & 16) != 0) {
                                z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new Function2() { // from class: bfd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-57345) & i3;
                            }
                            if (i6 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i8 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.TrackInsideCornerSize;
                            } else {
                                f7 = f2;
                            }
                            if (i12 != 0) {
                                function5 = function7;
                                i15 = i3;
                                z4 = z2;
                                f5 = f7;
                                fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                                sliderColors3 = sliderColors2;
                            } else {
                                function5 = function7;
                                i15 = i3;
                                z4 = z2;
                                sliderColors3 = sliderColors2;
                                f5 = f7;
                                fM6042getUnspecifiedD9Ej5fM = f3;
                            }
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            function5 = function3;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                        }
                        int i19 = i15 << 3;
                        modifier3 = modifier2;
                        function6 = function4;
                        m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i19 & 896) | (i19 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i19) | (458752 & i19) | (3670016 & i19) | (29360128 & i19) | (i19 & 234881024), ((i15 >> 24) & 112) | 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = fM6042getUnspecifiedD9Ej5fM;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        function6 = function4;
                        f5 = f2;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors2;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5) {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.TrackInsideCornerSize;
                        } else {
                            f7 = f2;
                        }
                        if (i12 != 0) {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                            sliderColors3 = sliderColors2;
                        } else {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors3;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5) {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.TrackInsideCornerSize;
                        } else {
                            f7 = f2;
                        }
                        if (i12 != 0) {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                            sliderColors3 = sliderColors2;
                        } else {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                    }
                    int i110 = i15 << 3;
                    modifier3 = modifier2;
                    function6 = function4;
                    m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i110 & 896) | (i110 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i110) | (458752 & i110) | (3670016 & i110) | (29360128 & i110) | (i110 & 234881024), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = fM6042getUnspecifiedD9Ej5fM;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    function6 = function4;
                    f5 = f2;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i17;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i17;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i18;
                } else {
                    function4 = function2;
                }
                i3 |= i18;
            } else {
                function4 = function2;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                f4 = f;
            } else {
                f4 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors4;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5) {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.TrackInsideCornerSize;
                        } else {
                            f7 = f2;
                        }
                        if (i12 != 0) {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                            sliderColors3 = sliderColors2;
                        } else {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors5;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5) {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.TrackInsideCornerSize;
                        } else {
                            f7 = f2;
                        }
                        if (i12 != 0) {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                            sliderColors3 = sliderColors2;
                        } else {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                    }
                    int i111 = i15 << 3;
                    modifier3 = modifier2;
                    function6 = function4;
                    m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i111 & 896) | (i111 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i111) | (458752 & i111) | (3670016 & i111) | (29360128 & i111) | (i111 & 234881024), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = fM6042getUnspecifiedD9Ej5fM;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    function6 = function4;
                    f5 = f2;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors6;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.TrackInsideCornerSize;
                    } else {
                        f7 = f2;
                    }
                    if (i12 != 0) {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                        sliderColors3 = sliderColors2;
                    } else {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = f3;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors7;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.TrackInsideCornerSize;
                    } else {
                        f7 = f2;
                    }
                    if (i12 != 0) {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                        sliderColors3 = sliderColors2;
                    } else {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                }
                int i112 = i15 << 3;
                modifier3 = modifier2;
                function6 = function4;
                m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i112 & 896) | (i112 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i112) | (458752 & i112) | (3670016 & i112) | (29360128 & i112) | (i112 & 234881024), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = fM6042getUnspecifiedD9Ej5fM;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function6 = function4;
                f5 = f2;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i17;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i17;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i18;
                } else {
                    function4 = function2;
                }
                i3 |= i18;
            } else {
                function4 = function2;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                f4 = f;
            } else {
                f4 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors8 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors8;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5) {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.TrackInsideCornerSize;
                        } else {
                            f7 = f2;
                        }
                        if (i12 != 0) {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                            sliderColors3 = sliderColors2;
                        } else {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors9 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors9;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5) {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: bfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.TrackInsideCornerSize;
                        } else {
                            f7 = f2;
                        }
                        if (i12 != 0) {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                            sliderColors3 = sliderColors2;
                        } else {
                            function5 = function7;
                            i15 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f7;
                            fM6042getUnspecifiedD9Ej5fM = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                    }
                    int i113 = i15 << 3;
                    modifier3 = modifier2;
                    function6 = function4;
                    m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i113 & 896) | (i113 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i113) | (458752 & i113) | (3670016 & i113) | (29360128 & i113) | (i113 & 234881024), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = fM6042getUnspecifiedD9Ej5fM;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    function6 = function4;
                    f5 = f2;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors10 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors10;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.TrackInsideCornerSize;
                    } else {
                        f7 = f2;
                    }
                    if (i12 != 0) {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                        sliderColors3 = sliderColors2;
                    } else {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = f3;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors11 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors11;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.TrackInsideCornerSize;
                    } else {
                        f7 = f2;
                    }
                    if (i12 != 0) {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                        sliderColors3 = sliderColors2;
                    } else {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                }
                int i114 = i15 << 3;
                modifier3 = modifier2;
                function6 = function4;
                m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i114 & 896) | (i114 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i114) | (458752 & i114) | (3670016 & i114) | (29360128 & i114) | (i114 & 234881024), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = fM6042getUnspecifiedD9Ej5fM;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function6 = function4;
                f5 = f2;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i17;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i17;
        } else {
            sliderColors2 = sliderColors;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                function4 = function2;
                if (composerStartRestartGroup.changedInstance(function4)) {
                }
                i3 |= i18;
            } else {
                function4 = function2;
            }
            i3 |= i18;
        } else {
            function4 = function2;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i7 = 131072;
            } else {
                i7 = 65536;
            }
            i3 |= i7;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
            f4 = f;
        } else {
            f4 = f;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(f4)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(f2)) {
                i11 = 8388608;
            } else {
                i11 = 4194304;
            }
            i3 |= i11;
        }
        i12 = i2 & 256;
        if (i12 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i13 = 33554432;
                }
                i3 |= i13;
            }
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors12 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors12;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.TrackInsideCornerSize;
                    } else {
                        f7 = f2;
                    }
                    if (i12 != 0) {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                        sliderColors3 = sliderColors2;
                    } else {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = f3;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors13 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors13;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: bfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.TrackInsideCornerSize;
                    } else {
                        f7 = f2;
                    }
                    if (i12 != 0) {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                        sliderColors3 = sliderColors2;
                    } else {
                        function5 = function7;
                        i15 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f7;
                        fM6042getUnspecifiedD9Ej5fM = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
                }
                int i115 = i15 << 3;
                modifier3 = modifier2;
                function6 = function4;
                m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i115 & 896) | (i115 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i115) | (458752 & i115) | (3670016 & i115) | (29360128 & i115) | (i115 & 234881024), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = fM6042getUnspecifiedD9Ej5fM;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function6 = function4;
                f5 = f2;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i2 & 512) != 0) {
            i3 |= 805306368;
        } else if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i14 = 536870912;
            } else {
                i14 = 268435456;
            }
            i3 |= i14;
        }
        if ((306783379 & i3) != 306783378) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i16 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors14 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors14;
                }
                if ((i2 & 16) != 0) {
                    z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new Function2() { // from class: bfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: bfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-57345) & i3;
                }
                if (i6 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i8 != 0) {
                    f4 = SliderKt.ThumbTrackGapSize;
                }
                if (i10 != 0) {
                    f7 = SliderKt.TrackInsideCornerSize;
                } else {
                    f7 = f2;
                }
                if (i12 != 0) {
                    function5 = function7;
                    i15 = i3;
                    z4 = z2;
                    f5 = f7;
                    fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                    sliderColors3 = sliderColors2;
                } else {
                    function5 = function7;
                    i15 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f7;
                    fM6042getUnspecifiedD9Ej5fM = f3;
                }
            } else {
                if (i16 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors15 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors15;
                }
                if ((i2 & 16) != 0) {
                    z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new Function2() { // from class: bfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: bfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.v(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-57345) & i3;
                }
                if (i6 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$CenteredTrack$2$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m912invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m912invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i8 != 0) {
                    f4 = SliderKt.ThumbTrackGapSize;
                }
                if (i10 != 0) {
                    f7 = SliderKt.TrackInsideCornerSize;
                } else {
                    f7 = f2;
                }
                if (i12 != 0) {
                    function5 = function7;
                    i15 = i3;
                    z4 = z2;
                    f5 = f7;
                    fM6042getUnspecifiedD9Ej5fM = Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM();
                    sliderColors3 = sliderColors2;
                } else {
                    function5 = function7;
                    i15 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f7;
                    fM6042getUnspecifiedD9Ej5fM = f3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1199441071, i15, -1, "androidx.compose.material3.SliderDefaults.CenteredTrack (Slider.kt:1557)");
            }
            int i116 = i15 << 3;
            modifier3 = modifier2;
            function6 = function4;
            m896TrackImplVvwgllI(sliderState, fM6042getUnspecifiedD9Ej5fM, modifier3, z4, sliderColors3, function6, function5, f4, f5, true, true, composerStartRestartGroup, (i15 & 14) | 805306368 | ((i15 >> 21) & 112) | (i116 & 896) | (i116 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i116) | (458752 & i116) | (3670016 & i116) | (29360128 & i116) | (i116 & 234881024), ((i15 >> 24) & 112) | 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f6 = fM6042getUnspecifiedD9Ej5fM;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function5 = function3;
            modifier3 = modifier2;
            z4 = z2;
            sliderColors3 = sliderColors2;
            function6 = function4;
            f5 = f2;
            f6 = f3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: cfd
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.d(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f4, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x012d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0136  */
    /* JADX WARN: Code duplicated, block: B:105:0x013c  */
    /* JADX WARN: Code duplicated, block: B:108:0x014f  */
    /* JADX WARN: Code duplicated, block: B:109:0x0165  */
    /* JADX WARN: Code duplicated, block: B:112:0x018c  */
    /* JADX WARN: Code duplicated, block: B:115:0x0194  */
    /* JADX WARN: Code duplicated, block: B:118:0x019f  */
    /* JADX WARN: Code duplicated, block: B:120:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0065  */
    /* JADX WARN: Code duplicated, block: B:41:0x0069  */
    /* JADX WARN: Code duplicated, block: B:43:0x0071  */
    /* JADX WARN: Code duplicated, block: B:44:0x0074  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:50:0x0080  */
    /* JADX WARN: Code duplicated, block: B:52:0x0084  */
    /* JADX WARN: Code duplicated, block: B:54:0x008c  */
    /* JADX WARN: Code duplicated, block: B:55:0x008f  */
    /* JADX WARN: Code duplicated, block: B:59:0x0099  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:65:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd A[PHI: r4 r9 r10 r12
      0x00dd: PHI (r4v32 int) = (r4v19 int), (r4v17 int), (r4v33 int) binds: [B:89:0x00f8, B:79:0x00d9, B:80:0x00db] A[DONT_GENERATE, DONT_INLINE]
      0x00dd: PHI (r9v8 androidx.compose.ui.Modifier) = (r9v5 androidx.compose.ui.Modifier), (r9v2 androidx.compose.ui.Modifier), (r9v2 androidx.compose.ui.Modifier) binds: [B:89:0x00f8, B:79:0x00d9, B:80:0x00db] A[DONT_GENERATE, DONT_INLINE]
      0x00dd: PHI (r10v12 androidx.compose.material3.SliderColors) = 
      (r10v9 androidx.compose.material3.SliderColors)
      (r10v6 androidx.compose.material3.SliderColors)
      (r10v6 androidx.compose.material3.SliderColors)
     binds: [B:89:0x00f8, B:79:0x00d9, B:80:0x00db] A[DONT_GENERATE, DONT_INLINE]
      0x00dd: PHI (r12v7 boolean) = (r12v4 boolean), (r12v2 boolean), (r12v2 boolean) binds: [B:89:0x00f8, B:79:0x00d9, B:80:0x00db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:82:0x00e0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:83:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:93:0x0109  */
    /* JADX WARN: Code duplicated, block: B:96:0x011e  */
    /* JADX INFO: renamed from: Thumb-9LiSoMs, reason: not valid java name */
    public final void m902Thumb9LiSoMs(final MutableInteractionSource mutableInteractionSource, Modifier modifier, SliderColors sliderColors, boolean z, long j, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        int i5;
        int i6;
        long j2;
        int i7;
        int i8;
        boolean z3;
        final long j3;
        final SliderColors sliderColors3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long j4;
        Object objRememberedValue;
        Composer.Companion companion;
        SnapshotStateList snapshotStateList;
        boolean z5;
        Object objRememberedValue2;
        long jM6113copyDwJknco$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(-290277409);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(mutableInteractionSource) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    int i10 = composerStartRestartGroup.changed(sliderColors2) ? 256 : 128;
                    i3 |= i10;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i10;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        j2 = j;
                        if (composerStartRestartGroup.changed(j2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    if ((i2 & 32) != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i3 |= i8;
                    }
                    if ((i3 & 74899) != 74898) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i9 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                                i3 &= -897;
                                sliderColors2 = sliderColorsColors;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if (i6 != 0) {
                                j4 = SliderKt.ThumbSize;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            snapshotStateList = (SnapshotStateList) objRememberedValue;
                            int i11 = i3 & 14;
                            z5 = i11 == 4;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5 || objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i11);
                            if (snapshotStateList.isEmpty()) {
                                jM6113copyDwJknco$default = j4;
                            } else {
                                jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                            }
                            SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            j3 = j4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 4) != 0) {
                                i3 &= -897;
                            }
                        }
                        j4 = j2;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt.mutableStateListOf();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        snapshotStateList = (SnapshotStateList) objRememberedValue;
                        int i12 = i3 & 14;
                        if (i12 == 4) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i12);
                        if (snapshotStateList.isEmpty()) {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                        } else {
                            jM6113copyDwJknco$default = j4;
                        }
                        SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j3 = j4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        j3 = j2;
                    }
                    sliderColors3 = sliderColors2;
                    z4 = z2;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier3 = modifier2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.h(this.b, mutableInteractionSource, modifier3, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                j2 = j;
                if ((i2 & 32) != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i3 |= i8;
                }
                if ((i3 & 74899) != 74898) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors2;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            j4 = SliderKt.ThumbSize;
                        } else {
                            j4 = j2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors3;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            j4 = SliderKt.ThumbSize;
                        } else {
                            j4 = j2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateListOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    snapshotStateList = (SnapshotStateList) objRememberedValue;
                    int i13 = i3 & 14;
                    if (i13 == 4) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i13);
                    if (snapshotStateList.isEmpty()) {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                    } else {
                        jM6113copyDwJknco$default = j4;
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j3 = j2;
                }
                sliderColors3 = sliderColors2;
                z4 = z2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.h(this.b, mutableInteractionSource, modifier4, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    j2 = j;
                    if (composerStartRestartGroup.changed(j2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((i2 & 32) != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i3 |= i8;
                }
                if ((i3 & 74899) != 74898) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors4;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            j4 = SliderKt.ThumbSize;
                        } else {
                            j4 = j2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors5;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            j4 = SliderKt.ThumbSize;
                        } else {
                            j4 = j2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateListOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    snapshotStateList = (SnapshotStateList) objRememberedValue;
                    int i14 = i3 & 14;
                    if (i14 == 4) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (snapshotStateList.isEmpty()) {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                    } else {
                        jM6113copyDwJknco$default = j4;
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j3 = j2;
                }
                sliderColors3 = sliderColors2;
                z4 = z2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.h(this.b, mutableInteractionSource, modifier5, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            j2 = j;
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i3 |= i8;
            }
            if ((i3 & 74899) != 74898) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors6;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        j4 = SliderKt.ThumbSize;
                    } else {
                        j4 = j2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors7;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        j4 = SliderKt.ThumbSize;
                    } else {
                        j4 = j2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateListOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                snapshotStateList = (SnapshotStateList) objRememberedValue;
                int i15 = i3 & 14;
                if (i15 == 4) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i15);
                if (snapshotStateList.isEmpty()) {
                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                } else {
                    jM6113copyDwJknco$default = j4;
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j3 = j2;
            }
            sliderColors3 = sliderColors2;
            z4 = z2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.h(this.b, mutableInteractionSource, modifier6, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i10;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i10;
        } else {
            sliderColors2 = sliderColors;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    j2 = j;
                    if (composerStartRestartGroup.changed(j2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((i2 & 32) != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i3 |= i8;
                }
                if ((i3 & 74899) != 74898) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors8 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors8;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            j4 = SliderKt.ThumbSize;
                        } else {
                            j4 = j2;
                        }
                    } else {
                        if (i9 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors9 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors9;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if (i6 != 0) {
                            j4 = SliderKt.ThumbSize;
                        } else {
                            j4 = j2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateListOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    snapshotStateList = (SnapshotStateList) objRememberedValue;
                    int i16 = i3 & 14;
                    if (i16 == 4) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i16);
                    if (snapshotStateList.isEmpty()) {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                    } else {
                        jM6113copyDwJknco$default = j4;
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j3 = j2;
                }
                sliderColors3 = sliderColors2;
                z4 = z2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier7 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.h(this.b, mutableInteractionSource, modifier7, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            j2 = j;
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i3 |= i8;
            }
            if ((i3 & 74899) != 74898) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors10 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors10;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        j4 = SliderKt.ThumbSize;
                    } else {
                        j4 = j2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors11 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors11;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        j4 = SliderKt.ThumbSize;
                    } else {
                        j4 = j2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateListOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                snapshotStateList = (SnapshotStateList) objRememberedValue;
                int i17 = i3 & 14;
                if (i17 == 4) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i17);
                if (snapshotStateList.isEmpty()) {
                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                } else {
                    jM6113copyDwJknco$default = j4;
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j3 = j2;
            }
            sliderColors3 = sliderColors2;
            z4 = z2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier8 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.h(this.b, mutableInteractionSource, modifier8, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                j2 = j;
                if (composerStartRestartGroup.changed(j2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            if ((i2 & 32) != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i3 |= i8;
            }
            if ((i3 & 74899) != 74898) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors12 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors12;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        j4 = SliderKt.ThumbSize;
                    } else {
                        j4 = j2;
                    }
                } else {
                    if (i9 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors13 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors13;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if (i6 != 0) {
                        j4 = SliderKt.ThumbSize;
                    } else {
                        j4 = j2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateListOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                snapshotStateList = (SnapshotStateList) objRememberedValue;
                int i18 = i3 & 14;
                if (i18 == 4) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i18);
                if (snapshotStateList.isEmpty()) {
                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
                } else {
                    jM6113copyDwJknco$default = j4;
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j3 = j2;
            }
            sliderColors3 = sliderColors2;
            z4 = z2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.h(this.b, mutableInteractionSource, modifier9, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        j2 = j;
        if ((i2 & 32) != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i8 = 131072;
            } else {
                i8 = 65536;
            }
            i3 |= i8;
        }
        if ((i3 & 74899) != 74898) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    SliderColors sliderColorsColors14 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                    i3 &= -897;
                    sliderColors2 = sliderColorsColors14;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if (i6 != 0) {
                    j4 = SliderKt.ThumbSize;
                } else {
                    j4 = j2;
                }
            } else {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    SliderColors sliderColorsColors15 = colors(composerStartRestartGroup, (i3 >> 15) & 14);
                    i3 &= -897;
                    sliderColors2 = sliderColorsColors15;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if (i6 != 0) {
                    j4 = SliderKt.ThumbSize;
                } else {
                    j4 = j2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1212)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            snapshotStateList = (SnapshotStateList) objRememberedValue;
            int i19 = i3 & 14;
            if (i19 == 4) {
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i19);
            if (snapshotStateList.isEmpty()) {
                jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j4, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j4) / 2.0f), 0.0f, 2, null);
            } else {
                jM6113copyDwJknco$default = j4;
            }
            SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, jM6113copyDwJknco$default), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j3 = j2;
        }
        sliderColors3 = sliderColors2;
        z4 = z2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier10 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: red
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.h(this.b, mutableInteractionSource, modifier10, sliderColors3, z4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0112  */
    /* JADX WARN: Code duplicated, block: B:103:0x0121  */
    /* JADX WARN: Code duplicated, block: B:106:0x0136  */
    /* JADX WARN: Code duplicated, block: B:110:0x0145  */
    /* JADX WARN: Code duplicated, block: B:113:0x014d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0153  */
    /* JADX WARN: Code duplicated, block: B:118:0x0166  */
    /* JADX WARN: Code duplicated, block: B:120:0x0170  */
    /* JADX WARN: Code duplicated, block: B:122:0x0185  */
    /* JADX WARN: Code duplicated, block: B:123:0x0199  */
    /* JADX WARN: Code duplicated, block: B:126:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:129:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:134:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x004a  */
    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0056  */
    /* JADX WARN: Code duplicated, block: B:33:0x0059  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0064  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:45:0x0075  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:50:0x0080  */
    /* JADX WARN: Code duplicated, block: B:52:0x0084  */
    /* JADX WARN: Code duplicated, block: B:54:0x008c  */
    /* JADX WARN: Code duplicated, block: B:55:0x008f  */
    /* JADX WARN: Code duplicated, block: B:59:0x0098  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00df  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f6 A[PHI: r4 r10 r11 r13
      0x00f6: PHI (r4v27 int) = (r4v21 int), (r4v19 int), (r4v28 int) binds: [B:99:0x0110, B:89:0x00f2, B:90:0x00f4] A[DONT_GENERATE, DONT_INLINE]
      0x00f6: PHI (r10v8 androidx.compose.ui.Modifier) = (r10v5 androidx.compose.ui.Modifier), (r10v2 androidx.compose.ui.Modifier), (r10v2 androidx.compose.ui.Modifier) binds: [B:99:0x0110, B:89:0x00f2, B:90:0x00f4] A[DONT_GENERATE, DONT_INLINE]
      0x00f6: PHI (r11v12 androidx.compose.material3.SliderColors) = 
      (r11v9 androidx.compose.material3.SliderColors)
      (r11v6 androidx.compose.material3.SliderColors)
      (r11v6 androidx.compose.material3.SliderColors)
     binds: [B:99:0x0110, B:89:0x00f2, B:90:0x00f4] A[DONT_GENERATE, DONT_INLINE]
      0x00f6: PHI (r13v7 boolean) = (r13v4 boolean), (r13v2 boolean), (r13v2 boolean) binds: [B:99:0x0110, B:89:0x00f2, B:90:0x00f4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:96:0x0102  */
    /* JADX WARN: Code duplicated, block: B:98:0x010f  */
    /* JADX INFO: renamed from: Thumb-HwbPF3A$material3, reason: not valid java name */
    public final void m903ThumbHwbPF3A$material3(final MutableInteractionSource mutableInteractionSource, final SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, long j, Composer composer, final int i, final int i2) {
        int i3;
        SliderState sliderState2;
        int i4;
        Modifier modifier2;
        int i5;
        SliderColors sliderColors2;
        int i6;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z3;
        final long j2;
        final Modifier modifier3;
        final SliderColors sliderColors3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long j3;
        Object objRememberedValue;
        Composer.Companion companion;
        SnapshotStateList snapshotStateList;
        boolean z5;
        Object objRememberedValue2;
        long j4;
        long jM6113copyDwJknco$default;
        Composer composerStartRestartGroup = composer.startRestartGroup(-889714565);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(mutableInteractionSource) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                sliderState2 = sliderState;
                i3 |= composerStartRestartGroup.changedInstance(sliderState2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        sliderColors2 = sliderColors;
                        int i11 = composerStartRestartGroup.changed(sliderColors2) ? 2048 : 1024;
                        i3 |= i11;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i11;
                } else {
                    sliderColors2 = sliderColors;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((196608 & i) == 0) {
                        if (composerStartRestartGroup.changed(j)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 64) != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 8) != 0) {
                                SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                                i3 &= -7169;
                                sliderColors2 = sliderColorsColors;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if (i8 != 0) {
                                j3 = SliderKt.ThumbSize;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.INSTANCE;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            snapshotStateList = (SnapshotStateList) objRememberedValue;
                            int i12 = i3 & 14;
                            z5 = i12 == 4;
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5 || objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i12);
                            if (snapshotStateList.isEmpty()) {
                                j4 = j3;
                            } else {
                                if (sliderState2.getOrientation() == Orientation.Vertical) {
                                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                                } else {
                                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                                }
                                j4 = jM6113copyDwJknco$default;
                            }
                            SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            j2 = j3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                        }
                        j3 = j;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.INSTANCE;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt.mutableStateListOf();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        snapshotStateList = (SnapshotStateList) objRememberedValue;
                        int i13 = i3 & 14;
                        if (i13 == 4) {
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i13);
                        if (snapshotStateList.isEmpty()) {
                            if (sliderState2.getOrientation() == Orientation.Vertical) {
                                jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                            } else {
                                jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                            }
                            j4 = jM6113copyDwJknco$default;
                        } else {
                            j4 = j3;
                        }
                        SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j2 = j3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        j2 = j;
                    }
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    z4 = z2;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                z2 = z;
                i8 = i2 & 32;
                if (i8 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changed(j)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors2;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if (i8 != 0) {
                            j3 = SliderKt.ThumbSize;
                        } else {
                            j3 = j;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors3;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if (i8 != 0) {
                            j3 = SliderKt.ThumbSize;
                        } else {
                            j3 = j;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateListOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    snapshotStateList = (SnapshotStateList) objRememberedValue;
                    int i14 = i3 & 14;
                    if (i14 == 4) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i14);
                    if (snapshotStateList.isEmpty()) {
                        if (sliderState2.getOrientation() == Orientation.Vertical) {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                        } else {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                        }
                        j4 = jM6113copyDwJknco$default;
                    } else {
                        j4 = j3;
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j2 = j;
                }
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            modifier2 = modifier;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i11;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i11;
            } else {
                sliderColors2 = sliderColors;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changed(j)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors4;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if (i8 != 0) {
                            j3 = SliderKt.ThumbSize;
                        } else {
                            j3 = j;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors5;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if (i8 != 0) {
                            j3 = SliderKt.ThumbSize;
                        } else {
                            j3 = j;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateListOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    snapshotStateList = (SnapshotStateList) objRememberedValue;
                    int i15 = i3 & 14;
                    if (i15 == 4) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i15);
                    if (snapshotStateList.isEmpty()) {
                        if (sliderState2.getOrientation() == Orientation.Vertical) {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                        } else {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                        }
                        j4 = jM6113copyDwJknco$default;
                    } else {
                        j4 = j3;
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j2 = j;
                }
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z2 = z;
            i8 = i2 & 32;
            if (i8 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changed(j)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i3 |= i10;
            }
            if ((i3 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors6;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if (i8 != 0) {
                        j3 = SliderKt.ThumbSize;
                    } else {
                        j3 = j;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors7;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if (i8 != 0) {
                        j3 = SliderKt.ThumbSize;
                    } else {
                        j3 = j;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateListOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                snapshotStateList = (SnapshotStateList) objRememberedValue;
                int i16 = i3 & 14;
                if (i16 == 4) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i16);
                if (snapshotStateList.isEmpty()) {
                    if (sliderState2.getOrientation() == Orientation.Vertical) {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                    } else {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                    }
                    j4 = jM6113copyDwJknco$default;
                } else {
                    j4 = j3;
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j2 = j;
            }
            modifier3 = modifier2;
            sliderColors3 = sliderColors2;
            z4 = z2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        sliderState2 = sliderState;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i11;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i11;
            } else {
                sliderColors2 = sliderColors;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changed(j)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((i2 & 64) != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors8 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors8;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if (i8 != 0) {
                            j3 = SliderKt.ThumbSize;
                        } else {
                            j3 = j;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors9 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors9;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if (i8 != 0) {
                            j3 = SliderKt.ThumbSize;
                        } else {
                            j3 = j;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.INSTANCE;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateListOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    snapshotStateList = (SnapshotStateList) objRememberedValue;
                    int i17 = i3 & 14;
                    if (i17 == 4) {
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i17);
                    if (snapshotStateList.isEmpty()) {
                        if (sliderState2.getOrientation() == Orientation.Vertical) {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                        } else {
                            jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                        }
                        j4 = jM6113copyDwJknco$default;
                    } else {
                        j4 = j3;
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j2 = j3;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    j2 = j;
                }
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z2 = z;
            i8 = i2 & 32;
            if (i8 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changed(j)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i3 |= i10;
            }
            if ((i3 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors10 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors10;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if (i8 != 0) {
                        j3 = SliderKt.ThumbSize;
                    } else {
                        j3 = j;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors11 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors11;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if (i8 != 0) {
                        j3 = SliderKt.ThumbSize;
                    } else {
                        j3 = j;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateListOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                snapshotStateList = (SnapshotStateList) objRememberedValue;
                int i18 = i3 & 14;
                if (i18 == 4) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i18);
                if (snapshotStateList.isEmpty()) {
                    if (sliderState2.getOrientation() == Orientation.Vertical) {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                    } else {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                    }
                    j4 = jM6113copyDwJknco$default;
                } else {
                    j4 = j3;
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j2 = j;
            }
            modifier3 = modifier2;
            sliderColors3 = sliderColors2;
            z4 = z2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i11;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i11;
        } else {
            sliderColors2 = sliderColors;
        }
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changed(j)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            if ((i2 & 64) != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i3 |= i10;
            }
            if ((i3 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors12 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors12;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if (i8 != 0) {
                        j3 = SliderKt.ThumbSize;
                    } else {
                        j3 = j;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors13 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors13;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if (i8 != 0) {
                        j3 = SliderKt.ThumbSize;
                    } else {
                        j3 = j;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateListOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                snapshotStateList = (SnapshotStateList) objRememberedValue;
                int i19 = i3 & 14;
                if (i19 == 4) {
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i19);
                if (snapshotStateList.isEmpty()) {
                    if (sliderState2.getOrientation() == Orientation.Vertical) {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                    } else {
                        jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                    }
                    j4 = jM6113copyDwJknco$default;
                } else {
                    j4 = j3;
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j2 = j3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                j2 = j;
            }
            modifier3 = modifier2;
            sliderColors3 = sliderColors2;
            z4 = z2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z2 = z;
        i8 = i2 & 32;
        if (i8 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & i) == 0) {
            if (composerStartRestartGroup.changed(j)) {
                i9 = 131072;
            } else {
                i9 = 65536;
            }
            i3 |= i9;
        }
        if ((i2 & 64) != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i10 = 1048576;
            } else {
                i10 = 524288;
            }
            i3 |= i10;
        }
        if ((i3 & 599187) != 599186) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors14 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors14;
                }
                if (i6 != 0) {
                    z2 = true;
                }
                if (i8 != 0) {
                    j3 = SliderKt.ThumbSize;
                } else {
                    j3 = j;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors15 = colors(composerStartRestartGroup, (i3 >> 18) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors15;
                }
                if (i6 != 0) {
                    z2 = true;
                }
                if (i8 != 0) {
                    j3 = SliderKt.ThumbSize;
                } else {
                    j3 = j;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-889714565, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:1265)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            snapshotStateList = (SnapshotStateList) objRememberedValue;
            int i110 = i3 & 14;
            if (i110 == 4) {
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new SliderDefaults$Thumb$3$1(mutableInteractionSource, snapshotStateList, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, i110);
            if (snapshotStateList.isEmpty()) {
                if (sliderState2.getOrientation() == Orientation.Vertical) {
                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, 0.0f, Dp.m6022constructorimpl(DpSize.m6118getHeightD9Ej5fM(j3) / 2.0f), 1, null);
                } else {
                    jM6113copyDwJknco$default = DpSize.m6113copyDwJknco$default(j3, Dp.m6022constructorimpl(DpSize.m6120getWidthD9Ej5fM(j3) / 2.0f), 0.0f, 2, null);
                }
                j4 = jM6113copyDwJknco$default;
            } else {
                j4 = j3;
            }
            SpacerKt.Spacer(BackgroundKt.background-bw27NRU(HoverableKt.hoverable$default(SizeKt.size-6HolHcs(modifier2, j4), mutableInteractionSource, false, 2, (Object) null), sliderColors2.m893thumbColorvNxB06k$material3(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6)), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j2 = j3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j2 = j;
        }
        modifier3 = modifier2;
        sliderColors3 = sliderColors2;
        z4 = z2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qed
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.l(this.b, mutableInteractionSource, sliderState, modifier3, sliderColors3, z4, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x016e  */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0047  */
    /* JADX WARN: Code duplicated, block: B:28:0x004b  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:31:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:39:0x0067  */
    /* JADX WARN: Code duplicated, block: B:41:0x006b  */
    /* JADX WARN: Code duplicated, block: B:43:0x0073  */
    /* JADX WARN: Code duplicated, block: B:44:0x0076  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    /* JADX WARN: Code duplicated, block: B:54:0x008d  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:59:0x009a  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:71:0x00be A[PHI: r3 r5 r8
      0x00be: PHI (r3v23 androidx.compose.material3.SliderColors) = (r3v19 androidx.compose.material3.SliderColors), (r3v25 androidx.compose.material3.SliderColors) binds: [B:79:0x00d8, B:70:0x00ba] A[DONT_GENERATE, DONT_INLINE]
      0x00be: PHI (r5v8 androidx.compose.ui.Modifier) = (r5v4 androidx.compose.ui.Modifier), (r5v11 androidx.compose.ui.Modifier) binds: [B:79:0x00d8, B:70:0x00ba] A[DONT_GENERATE, DONT_INLINE]
      0x00be: PHI (r8v13 int) = (r8v9 int), (r8v14 int) binds: [B:79:0x00d8, B:70:0x00ba] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:72:0x00c0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:77:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:80:0x00da  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:86:0x011d  */
    /* JADX WARN: Code duplicated, block: B:87:0x011f  */
    /* JADX WARN: Code duplicated, block: B:90:0x0137  */
    /* JADX WARN: Code duplicated, block: B:92:0x013f  */
    /* JADX WARN: Code duplicated, block: B:97:0x015b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0162  */
    @Deprecated(message = "Use version that supports slider state")
    public final void Track(final SliderPositions sliderPositions, Modifier modifier, SliderColors sliderColors, boolean z, Composer composer, final int i, final int i2) {
        final SliderPositions sliderPositions2;
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        int i5;
        int i6;
        boolean z3;
        final Modifier modifier3;
        final SliderColors sliderColors3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int i7;
        SliderColors sliderColors4;
        boolean z5;
        int i8;
        final long jM895trackColorWaAFU9c$material3;
        final long jM895trackColorWaAFU9c$material4;
        final long jM894tickColorWaAFU9c$material3;
        final long jM894tickColorWaAFU9c$material4;
        boolean z6;
        boolean zChanged;
        Object objRememberedValue;
        boolean z7;
        int i9;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1546713545);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            sliderPositions2 = sliderPositions;
        } else {
            sliderPositions2 = sliderPositions;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(sliderPositions2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    int i11 = composerStartRestartGroup.changed(sliderColors2) ? 256 : 128;
                    i3 |= i11;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i11;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                            i3 &= -897;
                            sliderColors2 = sliderColorsColors;
                        }
                        SliderColors sliderColors5 = sliderColors2;
                        i7 = i3;
                        sliderColors4 = sliderColors5;
                        if (i4 != 0) {
                            z5 = true;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1546713545, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1318)");
                        }
                        i8 = i7;
                        jM895trackColorWaAFU9c$material3 = sliderColors4.m895trackColorWaAFU9c$material3(z5, false);
                        jM895trackColorWaAFU9c$material4 = sliderColors4.m895trackColorWaAFU9c$material3(z5, true);
                        jM894tickColorWaAFU9c$material3 = sliderColors4.m894tickColorWaAFU9c$material3(z5, false);
                        jM894tickColorWaAFU9c$material4 = sliderColors4.m894tickColorWaAFU9c$material3(z5, true);
                        SliderColors sliderColors6 = sliderColors4;
                        modifier3 = modifier4;
                        Modifier modifier5 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
                        boolean zChanged2 = composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material3);
                        if ((i8 & 14) == 4) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        zChanged = zChanged2 | z6 | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material4) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material4);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            z7 = z5;
                            i9 = 0;
                            Function1 function1 = new Function1() { // from class: kfd
                                public final Object invoke(Object obj) {
                                    return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(function1);
                            objRememberedValue = function1;
                        } else {
                            z7 = z5;
                            i9 = 0;
                        }
                        CanvasKt.Canvas(modifier5, (Function1) objRememberedValue, composerStartRestartGroup, i9);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        sliderColors3 = sliderColors6;
                        z4 = z7;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        SliderColors sliderColors7 = sliderColors2;
                        i7 = i3;
                        sliderColors4 = sliderColors7;
                        modifier4 = modifier2;
                    }
                    z5 = z2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1546713545, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1318)");
                    }
                    i8 = i7;
                    jM895trackColorWaAFU9c$material3 = sliderColors4.m895trackColorWaAFU9c$material3(z5, false);
                    jM895trackColorWaAFU9c$material4 = sliderColors4.m895trackColorWaAFU9c$material3(z5, true);
                    jM894tickColorWaAFU9c$material3 = sliderColors4.m894tickColorWaAFU9c$material3(z5, false);
                    jM894tickColorWaAFU9c$material4 = sliderColors4.m894tickColorWaAFU9c$material3(z5, true);
                    SliderColors sliderColors8 = sliderColors4;
                    modifier3 = modifier4;
                    Modifier modifier6 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
                    boolean zChanged3 = composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material3);
                    if ((i8 & 14) == 4) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    zChanged = zChanged3 | z6 | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material4) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material4);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        z7 = z5;
                        i9 = 0;
                        Function1 function2 = new Function1() { // from class: kfd
                            public final Object invoke(Object obj) {
                                return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(function2);
                        objRememberedValue = function2;
                    } else {
                        z7 = z5;
                        i9 = 0;
                        Function1 function3 = new Function1() { // from class: kfd
                            public final Object invoke(Object obj) {
                                return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(function3);
                        objRememberedValue = function3;
                    }
                    CanvasKt.Canvas(modifier6, (Function1) objRememberedValue, composerStartRestartGroup, i9);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    sliderColors3 = sliderColors8;
                    z4 = z7;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oed
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.w(this.b, sliderPositions, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors2;
                    }
                    SliderColors sliderColors9 = sliderColors2;
                    i7 = i3;
                    sliderColors4 = sliderColors9;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors3;
                    }
                    SliderColors sliderColors10 = sliderColors2;
                    i7 = i3;
                    sliderColors4 = sliderColors10;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1546713545, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1318)");
                }
                i8 = i7;
                jM895trackColorWaAFU9c$material3 = sliderColors4.m895trackColorWaAFU9c$material3(z5, false);
                jM895trackColorWaAFU9c$material4 = sliderColors4.m895trackColorWaAFU9c$material3(z5, true);
                jM894tickColorWaAFU9c$material3 = sliderColors4.m894tickColorWaAFU9c$material3(z5, false);
                jM894tickColorWaAFU9c$material4 = sliderColors4.m894tickColorWaAFU9c$material3(z5, true);
                SliderColors sliderColors11 = sliderColors4;
                modifier3 = modifier4;
                Modifier modifier7 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
                boolean zChanged4 = composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material3);
                if ((i8 & 14) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = zChanged4 | z6 | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material4) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material4);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    z7 = z5;
                    i9 = 0;
                    Function1 function4 = new Function1() { // from class: kfd
                        public final Object invoke(Object obj) {
                            return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(function4);
                    objRememberedValue = function4;
                } else {
                    z7 = z5;
                    i9 = 0;
                    Function1 function5 = new Function1() { // from class: kfd
                        public final Object invoke(Object obj) {
                            return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(function5);
                    objRememberedValue = function5;
                }
                CanvasKt.Canvas(modifier7, (Function1) objRememberedValue, composerStartRestartGroup, i9);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sliderColors3 = sliderColors11;
                z4 = z7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.w(this.b, sliderPositions, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i11;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i11;
        } else {
            sliderColors2 = sliderColors;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors4;
                    }
                    SliderColors sliderColors12 = sliderColors2;
                    i7 = i3;
                    sliderColors4 = sliderColors12;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                        sliderColors2 = sliderColorsColors5;
                    }
                    SliderColors sliderColors13 = sliderColors2;
                    i7 = i3;
                    sliderColors4 = sliderColors13;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1546713545, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1318)");
                }
                i8 = i7;
                jM895trackColorWaAFU9c$material3 = sliderColors4.m895trackColorWaAFU9c$material3(z5, false);
                jM895trackColorWaAFU9c$material4 = sliderColors4.m895trackColorWaAFU9c$material3(z5, true);
                jM894tickColorWaAFU9c$material3 = sliderColors4.m894tickColorWaAFU9c$material3(z5, false);
                jM894tickColorWaAFU9c$material4 = sliderColors4.m894tickColorWaAFU9c$material3(z5, true);
                SliderColors sliderColors14 = sliderColors4;
                modifier3 = modifier4;
                Modifier modifier8 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
                boolean zChanged5 = composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material3);
                if ((i8 & 14) == 4) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                zChanged = zChanged5 | z6 | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material4) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material4);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    z7 = z5;
                    i9 = 0;
                    Function1 function6 = new Function1() { // from class: kfd
                        public final Object invoke(Object obj) {
                            return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(function6);
                    objRememberedValue = function6;
                } else {
                    z7 = z5;
                    i9 = 0;
                    Function1 function7 = new Function1() { // from class: kfd
                        public final Object invoke(Object obj) {
                            return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(function7);
                    objRememberedValue = function7;
                }
                CanvasKt.Canvas(modifier8, (Function1) objRememberedValue, composerStartRestartGroup, i9);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sliderColors3 = sliderColors14;
                z4 = z7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.w(this.b, sliderPositions, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i6 = 16384;
            } else {
                i6 = 8192;
            }
            i3 |= i6;
        }
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                    i3 &= -897;
                    sliderColors2 = sliderColorsColors6;
                }
                SliderColors sliderColors15 = sliderColors2;
                i7 = i3;
                sliderColors4 = sliderColors15;
                if (i4 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
            } else {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                    i3 &= -897;
                    sliderColors2 = sliderColorsColors7;
                }
                SliderColors sliderColors16 = sliderColors2;
                i7 = i3;
                sliderColors4 = sliderColors16;
                if (i4 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1546713545, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1318)");
            }
            i8 = i7;
            jM895trackColorWaAFU9c$material3 = sliderColors4.m895trackColorWaAFU9c$material3(z5, false);
            jM895trackColorWaAFU9c$material4 = sliderColors4.m895trackColorWaAFU9c$material3(z5, true);
            jM894tickColorWaAFU9c$material3 = sliderColors4.m894tickColorWaAFU9c$material3(z5, false);
            jM894tickColorWaAFU9c$material4 = sliderColors4.m894tickColorWaAFU9c$material3(z5, true);
            SliderColors sliderColors17 = sliderColors4;
            modifier3 = modifier4;
            Modifier modifier9 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), SliderKt.getTrackHeight());
            boolean zChanged6 = composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material3);
            if ((i8 & 14) == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            zChanged = zChanged6 | z6 | composerStartRestartGroup.changed(jM895trackColorWaAFU9c$material4) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material3) | composerStartRestartGroup.changed(jM894tickColorWaAFU9c$material4);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                z7 = z5;
                i9 = 0;
                Function1 function8 = new Function1() { // from class: kfd
                    public final Object invoke(Object obj) {
                        return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function8);
                objRememberedValue = function8;
            } else {
                z7 = z5;
                i9 = 0;
                Function1 function9 = new Function1() { // from class: kfd
                    public final Object invoke(Object obj) {
                        return SliderDefaults.k(jM895trackColorWaAFU9c$material3, sliderPositions2, jM895trackColorWaAFU9c$material4, jM894tickColorWaAFU9c$material3, jM894tickColorWaAFU9c$material4, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function9);
                objRememberedValue = function9;
            }
            CanvasKt.Canvas(modifier9, (Function1) objRememberedValue, composerStartRestartGroup, i9);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sliderColors3 = sliderColors17;
            z4 = z7;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            sliderColors3 = sliderColors2;
            z4 = z2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: oed
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.w(this.b, sliderPositions, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x011c  */
    /* JADX WARN: Code duplicated, block: B:103:0x0126  */
    /* JADX WARN: Code duplicated, block: B:105:0x0130  */
    /* JADX WARN: Code duplicated, block: B:115:0x0151 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:116:0x0153  */
    /* JADX WARN: Code duplicated, block: B:118:0x0158  */
    /* JADX WARN: Code duplicated, block: B:121:0x015e  */
    /* JADX WARN: Code duplicated, block: B:124:0x016d  */
    /* JADX WARN: Code duplicated, block: B:126:0x0175  */
    /* JADX WARN: Code duplicated, block: B:128:0x017b  */
    /* JADX WARN: Code duplicated, block: B:134:0x018a  */
    /* JADX WARN: Code duplicated, block: B:137:0x0194  */
    /* JADX WARN: Code duplicated, block: B:139:0x019c  */
    /* JADX WARN: Code duplicated, block: B:142:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:144:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:146:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:148:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:150:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:151:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:154:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:157:0x0229  */
    /* JADX WARN: Code duplicated, block: B:158:0x022d  */
    /* JADX WARN: Code duplicated, block: B:161:0x023f  */
    /* JADX WARN: Code duplicated, block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:45:0x007c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:79:0x00db  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:92:0x0100  */
    /* JADX WARN: Code duplicated, block: B:94:0x0106  */
    /* JADX WARN: Code duplicated, block: B:95:0x0109  */
    /* JADX WARN: Code duplicated, block: B:99:0x0119  */
    /* JADX INFO: renamed from: Track-4EFweAY, reason: not valid java name */
    public final void m904Track4EFweAY(final RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f, float f2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        int i5;
        final SliderColors sliderColors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        int i6;
        int i7;
        int i8;
        float f3;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z3;
        final Modifier modifier3;
        final boolean z4;
        final SliderColors sliderColors3;
        final Function2<? super DrawScope, ? super Offset, Unit> function5;
        final float f4;
        final float f5;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        int i13;
        Object objRememberedValue;
        boolean z5;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-541824132);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(rangeSliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i14 = i2 & 2;
        if (i14 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        sliderColors2 = sliderColors;
                        int i15 = composerStartRestartGroup.changed(sliderColors2) ? 2048 : 1024;
                        i3 |= i15;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i15;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        function4 = function2;
                        int i16 = composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
                        i3 |= i16;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i16;
                } else {
                    function4 = function2;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                    f3 = f;
                } else {
                    f3 = f;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i2 & 256) != 0) {
                    i3 |= 100663296;
                } else if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i12 = 33554432;
                    }
                    i3 |= i12;
                }
                if ((38347923 & i3) != 38347922) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new Function2() { // from class: ued
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f3 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            z4 = z2;
                            function5 = function4;
                            f5 = f3;
                            function6 = function7;
                            i13 = i3;
                            modifier3 = modifier2;
                            sliderColors3 = sliderColors2;
                            f4 = SliderKt.TrackInsideCornerSize;
                        } else {
                            z4 = z2;
                            function5 = function4;
                            f5 = f3;
                            function6 = function7;
                            i13 = i3;
                            modifier3 = modifier2;
                            sliderColors3 = sliderColors2;
                            f4 = f2;
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                        }
                        i13 = i3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        function5 = function4;
                        f4 = f2;
                        f5 = f3;
                        function6 = function3;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-541824132, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1734)");
                    }
                    int i17 = (i13 & 14) | 48;
                    int i18 = i13 << 3;
                    m897TrackImplxlyIBlM(rangeSliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function5, function6, f5, f4, composerStartRestartGroup, i17 | (i18 & 896) | (i18 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i18) | (458752 & i18) | (3670016 & i18) | (29360128 & i18) | (234881024 & i18) | (i18 & 1879048192));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    function5 = function4;
                    f4 = f2;
                    f5 = f3;
                    function6 = function3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ved
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.m(this.b, rangeSliderState, modifier3, z4, sliderColors3, function5, function6, f5, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i15;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i15;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i16;
                } else {
                    function4 = function2;
                }
                i3 |= i16;
            } else {
                function4 = function2;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                f3 = f;
            } else {
                f3 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i12 = 33554432;
                }
                i3 |= i12;
            }
            if ((38347923 & i3) != 38347922) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors2;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors3;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-541824132, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1734)");
                }
                int i19 = (i13 & 14) | 48;
                int i110 = i13 << 3;
                m897TrackImplxlyIBlM(rangeSliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function5, function6, f5, f4, composerStartRestartGroup, i19 | (i110 & 896) | (i110 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i110) | (458752 & i110) | (3670016 & i110) | (29360128 & i110) | (234881024 & i110) | (i110 & 1879048192));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function5 = function4;
                f4 = f2;
                f5 = f3;
                function6 = function3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ved
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.m(this.b, rangeSliderState, modifier3, z4, sliderColors3, function5, function6, f5, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i15;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i15;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i16;
                } else {
                    function4 = function2;
                }
                i3 |= i16;
            } else {
                function4 = function2;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                f3 = f;
            } else {
                f3 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i12 = 33554432;
                }
                i3 |= i12;
            }
            if ((38347923 & i3) != 38347922) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors4;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors5;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ued
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        z4 = z2;
                        function5 = function4;
                        f5 = f3;
                        function6 = function7;
                        i13 = i3;
                        modifier3 = modifier2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-541824132, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1734)");
                }
                int i111 = (i13 & 14) | 48;
                int i112 = i13 << 3;
                m897TrackImplxlyIBlM(rangeSliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function5, function6, f5, f4, composerStartRestartGroup, i111 | (i112 & 896) | (i112 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i112) | (458752 & i112) | (3670016 & i112) | (29360128 & i112) | (234881024 & i112) | (i112 & 1879048192));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function5 = function4;
                f4 = f2;
                f5 = f3;
                function6 = function3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ved
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.m(this.b, rangeSliderState, modifier3, z4, sliderColors3, function5, function6, f5, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i15;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i15;
        } else {
            sliderColors2 = sliderColors;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                function4 = function2;
                if (composerStartRestartGroup.changedInstance(function4)) {
                }
                i3 |= i16;
            } else {
                function4 = function2;
            }
            i3 |= i16;
        } else {
            function4 = function2;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i7 = 131072;
            } else {
                i7 = 65536;
            }
            i3 |= i7;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
            f3 = f;
        } else {
            f3 = f;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(f2)) {
                i11 = 8388608;
            } else {
                i11 = 4194304;
            }
            i3 |= i11;
        }
        if ((i2 & 256) != 0) {
            i3 |= 100663296;
        } else if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i12 = 33554432;
            }
            i3 |= i12;
        }
        if ((38347923 & i3) != 38347922) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors6;
                }
                if ((i2 & 16) != 0) {
                    z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new Function2() { // from class: ued
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: ued
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-57345) & i3;
                }
                if (i6 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i8 != 0) {
                    f3 = SliderKt.ThumbTrackGapSize;
                }
                if (i10 != 0) {
                    z4 = z2;
                    function5 = function4;
                    f5 = f3;
                    function6 = function7;
                    i13 = i3;
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    f4 = SliderKt.TrackInsideCornerSize;
                } else {
                    z4 = z2;
                    function5 = function4;
                    f5 = f3;
                    function6 = function7;
                    i13 = i3;
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    f4 = f2;
                }
            } else {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors7;
                }
                if ((i2 & 16) != 0) {
                    z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new Function2() { // from class: ued
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: ued
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.x(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-57345) & i3;
                }
                if (i6 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m913invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m913invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i8 != 0) {
                    f3 = SliderKt.ThumbTrackGapSize;
                }
                if (i10 != 0) {
                    z4 = z2;
                    function5 = function4;
                    f5 = f3;
                    function6 = function7;
                    i13 = i3;
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    f4 = SliderKt.TrackInsideCornerSize;
                } else {
                    z4 = z2;
                    function5 = function4;
                    f5 = f3;
                    function6 = function7;
                    i13 = i3;
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    f4 = f2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-541824132, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1734)");
            }
            int i113 = (i13 & 14) | 48;
            int i114 = i13 << 3;
            m897TrackImplxlyIBlM(rangeSliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function5, function6, f5, f4, composerStartRestartGroup, i113 | (i114 & 896) | (i114 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i114) | (458752 & i114) | (3670016 & i114) | (29360128 & i114) | (234881024 & i114) | (i114 & 1879048192));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z4 = z2;
            sliderColors3 = sliderColors2;
            function5 = function4;
            f4 = f2;
            f5 = f3;
            function6 = function3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ved
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.m(this.b, rangeSliderState, modifier3, z4, sliderColors3, function5, function6, f5, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:102:0x011d  */
    /* JADX WARN: Code duplicated, block: B:104:0x0121  */
    /* JADX WARN: Code duplicated, block: B:106:0x0127  */
    /* JADX WARN: Code duplicated, block: B:107:0x012a  */
    /* JADX WARN: Code duplicated, block: B:111:0x013a  */
    /* JADX WARN: Code duplicated, block: B:112:0x013d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0147  */
    /* JADX WARN: Code duplicated, block: B:117:0x0157  */
    /* JADX WARN: Code duplicated, block: B:128:0x0179 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:129:0x017b  */
    /* JADX WARN: Code duplicated, block: B:131:0x0180  */
    /* JADX WARN: Code duplicated, block: B:134:0x0186  */
    /* JADX WARN: Code duplicated, block: B:137:0x0195  */
    /* JADX WARN: Code duplicated, block: B:139:0x019d  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:147:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:150:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:152:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:155:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:157:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:159:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:161:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:162:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:164:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:165:0x0200  */
    /* JADX WARN: Code duplicated, block: B:168:0x0212  */
    /* JADX WARN: Code duplicated, block: B:171:0x0252  */
    /* JADX WARN: Code duplicated, block: B:173:0x025d  */
    /* JADX WARN: Code duplicated, block: B:176:0x026f  */
    /* JADX WARN: Code duplicated, block: B:178:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006c  */
    /* JADX WARN: Code duplicated, block: B:41:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0078  */
    /* JADX WARN: Code duplicated, block: B:44:0x007b  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:63:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:80:0x00da  */
    /* JADX WARN: Code duplicated, block: B:81:0x00df  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:85:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:92:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:94:0x0103  */
    /* JADX WARN: Code duplicated, block: B:96:0x010d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0110  */
    /* JADX INFO: renamed from: Track-mnvyFg4$material3, reason: not valid java name */
    public final void m907TrackmnvyFg4$material3(final SliderState sliderState, final float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f2, float f3, Composer composer, final int i, final int i2) {
        SliderState sliderState2;
        int i3;
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        final boolean z2;
        int i7;
        final SliderColors sliderColors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        int i8;
        int i9;
        int i10;
        float f4;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z3;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function5;
        final Modifier modifier3;
        final boolean z4;
        final SliderColors sliderColors3;
        final float f5;
        final Function2<? super DrawScope, ? super Offset, Unit> function6;
        final float f6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        float f7;
        int i15;
        boolean z5;
        float f8;
        float f9;
        SliderColors sliderColors4;
        Object objRememberedValue;
        boolean z6;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1691224881);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            sliderState2 = sliderState;
        } else if ((i & 6) == 0) {
            sliderState2 = sliderState;
            i3 = (composerStartRestartGroup.changedInstance(sliderState2) ? 4 : 2) | i;
        } else {
            sliderState2 = sliderState;
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        if ((i2 & 16) == 0) {
                            sliderColors2 = sliderColors;
                            int i16 = composerStartRestartGroup.changed(sliderColors2) ? 16384 : 8192;
                            i3 |= i16;
                        } else {
                            sliderColors2 = sliderColors;
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            function4 = function2;
                            int i17 = composerStartRestartGroup.changedInstance(function4) ? 131072 : 65536;
                            i3 |= i17;
                        } else {
                            function4 = function2;
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                        f4 = f2;
                    } else {
                        f4 = f2;
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(f4)) {
                                i11 = 8388608;
                            } else {
                                i11 = 4194304;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 256;
                    if (i12 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(f3)) {
                                i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i13 = 33554432;
                            }
                            i3 |= i13;
                        }
                        if ((i2 & 512) != 0) {
                            i3 |= 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(this)) {
                                i14 = 536870912;
                            } else {
                                i14 = 268435456;
                            }
                            i3 |= i14;
                        }
                        if ((306783379 & i3) != 306783378) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    z2 = true;
                                }
                                if ((i2 & 16) != 0) {
                                    SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                    i3 &= -57345;
                                    sliderColors2 = sliderColorsColors;
                                }
                                if ((i2 & 32) != 0) {
                                    z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                    if (z6 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = new Function2() { // from class: sed
                                            public final Object invoke(Object obj, Object obj2) {
                                                return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                            }
                                        };
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                    }
                                    i3 &= -458753;
                                    function4 = (Function2) objRememberedValue2;
                                }
                                if (i8 != 0) {
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                                m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                            public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                            }
                                        };
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    function7 = (Function3) objRememberedValue;
                                } else {
                                    function7 = function3;
                                }
                                if (i10 != 0) {
                                    f7 = SliderKt.ThumbTrackGapSize;
                                } else {
                                    f7 = f4;
                                }
                                if (i12 != 0) {
                                    boolean z7 = z2;
                                    i15 = i3;
                                    z5 = z7;
                                    f8 = SliderKt.TrackInsideCornerSize;
                                    sliderColors4 = sliderColors2;
                                    f9 = f7;
                                } else {
                                    boolean z8 = z2;
                                    i15 = i3;
                                    z5 = z8;
                                    f8 = f3;
                                    f9 = f7;
                                    sliderColors4 = sliderColors2;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                boolean z9 = z2;
                                i15 = i3;
                                z5 = z9;
                                function7 = function3;
                                f8 = f3;
                                sliderColors4 = sliderColors2;
                                f9 = f4;
                            }
                            Function2<? super DrawScope, ? super Offset, Unit> function8 = function4;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                            }
                            Modifier modifier4 = modifier2;
                            Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function9 = function7;
                            m896TrackImplVvwgllI(sliderState2, f, modifier4, z5, sliderColors4, function8, function9, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            f6 = f8;
                            f5 = f9;
                            function5 = function9;
                            function6 = function8;
                            sliderColors3 = sliderColors4;
                            z4 = z5;
                            modifier3 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function5 = function3;
                            modifier3 = modifier2;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f4;
                            function6 = function4;
                            f6 = f3;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors2;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z10 = z2;
                                i15 = i3;
                                z5 = z10;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z11 = z2;
                                i15 = i3;
                                z5 = z11;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors3;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z12 = z2;
                                i15 = i3;
                                z5 = z12;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z13 = z2;
                                i15 = i3;
                                z5 = z13;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        }
                        Function2<? super DrawScope, ? super Offset, Unit> function10 = function4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                        }
                        Modifier modifier5 = modifier2;
                        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function11 = function7;
                        m896TrackImplVvwgllI(sliderState2, f, modifier5, z5, sliderColors4, function10, function11, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f9;
                        function5 = function11;
                        function6 = function10;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier5;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                z2 = z;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        sliderColors2 = sliderColors;
                        if (composerStartRestartGroup.changed(sliderColors2)) {
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors4;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z14 = z2;
                                i15 = i3;
                                z5 = z14;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z15 = z2;
                                i15 = i3;
                                z5 = z15;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors5;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z16 = z2;
                                i15 = i3;
                                z5 = z16;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z17 = z2;
                                i15 = i3;
                                z5 = z17;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        }
                        Function2<? super DrawScope, ? super Offset, Unit> function12 = function4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                        }
                        Modifier modifier6 = modifier2;
                        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function13 = function7;
                        m896TrackImplVvwgllI(sliderState2, f, modifier6, z5, sliderColors4, function12, function13, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f9;
                        function5 = function13;
                        function6 = function12;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors6;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z18 = z2;
                            i15 = i3;
                            z5 = z18;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z19 = z2;
                            i15 = i3;
                            z5 = z19;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors7;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z110 = z2;
                            i15 = i3;
                            z5 = z110;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z111 = z2;
                            i15 = i3;
                            z5 = z111;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    }
                    Function2<? super DrawScope, ? super Offset, Unit> function14 = function4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                    }
                    Modifier modifier7 = modifier2;
                    Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function15 = function7;
                    m896TrackImplVvwgllI(sliderState2, f, modifier7, z5, sliderColors4, function14, function15, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f9;
                    function5 = function15;
                    function6 = function14;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier7;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            modifier2 = modifier;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        sliderColors2 = sliderColors;
                        if (composerStartRestartGroup.changed(sliderColors2)) {
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors8 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors8;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z112 = z2;
                                i15 = i3;
                                z5 = z112;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z113 = z2;
                                i15 = i3;
                                z5 = z113;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors9 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors9;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z114 = z2;
                                i15 = i3;
                                z5 = z114;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z115 = z2;
                                i15 = i3;
                                z5 = z115;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        }
                        Function2<? super DrawScope, ? super Offset, Unit> function16 = function4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                        }
                        Modifier modifier8 = modifier2;
                        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function17 = function7;
                        m896TrackImplVvwgllI(sliderState2, f, modifier8, z5, sliderColors4, function16, function17, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f9;
                        function5 = function17;
                        function6 = function16;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors10 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors10;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z116 = z2;
                            i15 = i3;
                            z5 = z116;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z117 = z2;
                            i15 = i3;
                            z5 = z117;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors11 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors11;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z118 = z2;
                            i15 = i3;
                            z5 = z118;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z119 = z2;
                            i15 = i3;
                            z5 = z119;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    }
                    Function2<? super DrawScope, ? super Offset, Unit> function18 = function4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                    }
                    Modifier modifier9 = modifier2;
                    Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function19 = function7;
                    m896TrackImplVvwgllI(sliderState2, f, modifier9, z5, sliderColors4, function18, function19, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f9;
                    function5 = function19;
                    function6 = function18;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier9;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                f4 = f2;
            } else {
                f4 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors12 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors12;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z1110 = z2;
                            i15 = i3;
                            z5 = z1110;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z1111 = z2;
                            i15 = i3;
                            z5 = z1111;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors13 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors13;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z1112 = z2;
                            i15 = i3;
                            z5 = z1112;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z1113 = z2;
                            i15 = i3;
                            z5 = z1113;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    }
                    Function2<? super DrawScope, ? super Offset, Unit> function110 = function4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                    }
                    Modifier modifier10 = modifier2;
                    Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function111 = function7;
                    m896TrackImplVvwgllI(sliderState2, f, modifier10, z5, sliderColors4, function110, function111, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f9;
                    function5 = function111;
                    function6 = function110;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors14 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors14;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z1114 = z2;
                        i15 = i3;
                        z5 = z1114;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z1115 = z2;
                        i15 = i3;
                        z5 = z1115;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors15 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors15;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z1116 = z2;
                        i15 = i3;
                        z5 = z1116;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z1117 = z2;
                        i15 = i3;
                        z5 = z1117;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                }
                Function2<? super DrawScope, ? super Offset, Unit> function112 = function4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                }
                Modifier modifier11 = modifier2;
                Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function113 = function7;
                m896TrackImplVvwgllI(sliderState2, f, modifier11, z5, sliderColors4, function112, function113, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f9;
                function5 = function113;
                function6 = function112;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier11;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        sliderColors2 = sliderColors;
                        if (composerStartRestartGroup.changed(sliderColors2)) {
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors16 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors16;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z1118 = z2;
                                i15 = i3;
                                z5 = z1118;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z1119 = z2;
                                i15 = i3;
                                z5 = z1119;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors17 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors17;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z6) {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: sed
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i3 &= -458753;
                                function4 = (Function2) objRememberedValue2;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f7 = SliderKt.ThumbTrackGapSize;
                            } else {
                                f7 = f4;
                            }
                            if (i12 != 0) {
                                boolean z11110 = z2;
                                i15 = i3;
                                z5 = z11110;
                                f8 = SliderKt.TrackInsideCornerSize;
                                sliderColors4 = sliderColors2;
                                f9 = f7;
                            } else {
                                boolean z11111 = z2;
                                i15 = i3;
                                z5 = z11111;
                                f8 = f3;
                                f9 = f7;
                                sliderColors4 = sliderColors2;
                            }
                        }
                        Function2<? super DrawScope, ? super Offset, Unit> function114 = function4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                        }
                        Modifier modifier12 = modifier2;
                        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function115 = function7;
                        m896TrackImplVvwgllI(sliderState2, f, modifier12, z5, sliderColors4, function114, function115, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f9;
                        function5 = function115;
                        function6 = function114;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier12;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors18 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors18;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z11112 = z2;
                            i15 = i3;
                            z5 = z11112;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z11113 = z2;
                            i15 = i3;
                            z5 = z11113;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors19 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors19;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z11114 = z2;
                            i15 = i3;
                            z5 = z11114;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z11115 = z2;
                            i15 = i3;
                            z5 = z11115;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    }
                    Function2<? super DrawScope, ? super Offset, Unit> function116 = function4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                    }
                    Modifier modifier13 = modifier2;
                    Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function117 = function7;
                    m896TrackImplVvwgllI(sliderState2, f, modifier13, z5, sliderColors4, function116, function117, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f9;
                    function5 = function117;
                    function6 = function116;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier13;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                f4 = f2;
            } else {
                f4 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors110 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors110;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z11116 = z2;
                            i15 = i3;
                            z5 = z11116;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z11117 = z2;
                            i15 = i3;
                            z5 = z11117;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors111 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors111;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z11118 = z2;
                            i15 = i3;
                            z5 = z11118;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z11119 = z2;
                            i15 = i3;
                            z5 = z11119;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    }
                    Function2<? super DrawScope, ? super Offset, Unit> function118 = function4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                    }
                    Modifier modifier14 = modifier2;
                    Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function119 = function7;
                    m896TrackImplVvwgllI(sliderState2, f, modifier14, z5, sliderColors4, function118, function119, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f9;
                    function5 = function119;
                    function6 = function118;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier14;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors112 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors112;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z111110 = z2;
                        i15 = i3;
                        z5 = z111110;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z111111 = z2;
                        i15 = i3;
                        z5 = z111111;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors113 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors113;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z111112 = z2;
                        i15 = i3;
                        z5 = z111112;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z111113 = z2;
                        i15 = i3;
                        z5 = z111113;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                }
                Function2<? super DrawScope, ? super Offset, Unit> function1110 = function4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                }
                Modifier modifier15 = modifier2;
                Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function1111 = function7;
                m896TrackImplVvwgllI(sliderState2, f, modifier15, z5, sliderColors4, function1110, function1111, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f9;
                function5 = function1111;
                function6 = function1110;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier15;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                f4 = f2;
            } else {
                f4 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors114 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors114;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z111114 = z2;
                            i15 = i3;
                            z5 = z111114;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z111115 = z2;
                            i15 = i3;
                            z5 = z111115;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors115 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors115;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z6) {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: sed
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i3 &= -458753;
                            function4 = (Function2) objRememberedValue2;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f7 = SliderKt.ThumbTrackGapSize;
                        } else {
                            f7 = f4;
                        }
                        if (i12 != 0) {
                            boolean z111116 = z2;
                            i15 = i3;
                            z5 = z111116;
                            f8 = SliderKt.TrackInsideCornerSize;
                            sliderColors4 = sliderColors2;
                            f9 = f7;
                        } else {
                            boolean z111117 = z2;
                            i15 = i3;
                            z5 = z111117;
                            f8 = f3;
                            f9 = f7;
                            sliderColors4 = sliderColors2;
                        }
                    }
                    Function2<? super DrawScope, ? super Offset, Unit> function1112 = function4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                    }
                    Modifier modifier16 = modifier2;
                    Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function1113 = function7;
                    m896TrackImplVvwgllI(sliderState2, f, modifier16, z5, sliderColors4, function1112, function1113, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f9;
                    function5 = function1113;
                    function6 = function1112;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier16;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors116 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors116;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z111118 = z2;
                        i15 = i3;
                        z5 = z111118;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z111119 = z2;
                        i15 = i3;
                        z5 = z111119;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors117 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors117;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z1111110 = z2;
                        i15 = i3;
                        z5 = z1111110;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z1111111 = z2;
                        i15 = i3;
                        z5 = z1111111;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                }
                Function2<? super DrawScope, ? super Offset, Unit> function1114 = function4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                }
                Modifier modifier17 = modifier2;
                Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function1115 = function7;
                m896TrackImplVvwgllI(sliderState2, f, modifier17, z5, sliderColors4, function1114, function1115, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f9;
                function5 = function1115;
                function6 = function1114;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier17;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i16;
        } else {
            sliderColors2 = sliderColors;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                function4 = function2;
                if (composerStartRestartGroup.changedInstance(function4)) {
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i3 |= i17;
        } else {
            function4 = function2;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i9 = 1048576;
            } else {
                i9 = 524288;
            }
            i3 |= i9;
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
            f4 = f2;
        } else {
            f4 = f2;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f4)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
        }
        i12 = i2 & 256;
        if (i12 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i13 = 33554432;
                }
                i3 |= i13;
            }
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors118 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors118;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z1111112 = z2;
                        i15 = i3;
                        z5 = z1111112;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z1111113 = z2;
                        i15 = i3;
                        z5 = z1111113;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors119 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors119;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: sed
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i3 &= -458753;
                        function4 = (Function2) objRememberedValue2;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f7 = SliderKt.ThumbTrackGapSize;
                    } else {
                        f7 = f4;
                    }
                    if (i12 != 0) {
                        boolean z1111114 = z2;
                        i15 = i3;
                        z5 = z1111114;
                        f8 = SliderKt.TrackInsideCornerSize;
                        sliderColors4 = sliderColors2;
                        f9 = f7;
                    } else {
                        boolean z1111115 = z2;
                        i15 = i3;
                        z5 = z1111115;
                        f8 = f3;
                        f9 = f7;
                        sliderColors4 = sliderColors2;
                    }
                }
                Function2<? super DrawScope, ? super Offset, Unit> function1116 = function4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
                }
                Modifier modifier18 = modifier2;
                Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function1117 = function7;
                m896TrackImplVvwgllI(sliderState2, f, modifier18, z5, sliderColors4, function1116, function1117, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f9;
                function5 = function1117;
                function6 = function1116;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier18;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i2 & 512) != 0) {
            i3 |= 805306368;
        } else if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i14 = 536870912;
            } else {
                i14 = 268435456;
            }
            i3 |= i14;
        }
        if ((306783379 & i3) != 306783378) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    SliderColors sliderColorsColors1110 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                    i3 &= -57345;
                    sliderColors2 = sliderColorsColors1110;
                }
                if ((i2 & 32) != 0) {
                    z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue2 = new Function2() { // from class: sed
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: sed
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    i3 &= -458753;
                    function4 = (Function2) objRememberedValue2;
                }
                if (i8 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i10 != 0) {
                    f7 = SliderKt.ThumbTrackGapSize;
                } else {
                    f7 = f4;
                }
                if (i12 != 0) {
                    boolean z1111116 = z2;
                    i15 = i3;
                    z5 = z1111116;
                    f8 = SliderKt.TrackInsideCornerSize;
                    sliderColors4 = sliderColors2;
                    f9 = f7;
                } else {
                    boolean z1111117 = z2;
                    i15 = i3;
                    z5 = z1111117;
                    f8 = f3;
                    f9 = f7;
                    sliderColors4 = sliderColors2;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    SliderColors sliderColorsColors1111 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                    i3 &= -57345;
                    sliderColors2 = sliderColorsColors1111;
                }
                if ((i2 & 32) != 0) {
                    z6 = ((((i3 & 57344) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue2 = new Function2() { // from class: sed
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: sed
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.t(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    i3 &= -458753;
                    function4 = (Function2) objRememberedValue2;
                }
                if (i8 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m916invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m916invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i10 != 0) {
                    f7 = SliderKt.ThumbTrackGapSize;
                } else {
                    f7 = f4;
                }
                if (i12 != 0) {
                    boolean z1111118 = z2;
                    i15 = i3;
                    z5 = z1111118;
                    f8 = SliderKt.TrackInsideCornerSize;
                    sliderColors4 = sliderColors2;
                    f9 = f7;
                } else {
                    boolean z1111119 = z2;
                    i15 = i3;
                    z5 = z1111119;
                    f8 = f3;
                    f9 = f7;
                    sliderColors4 = sliderColors2;
                }
            }
            Function2<? super DrawScope, ? super Offset, Unit> function1118 = function4;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1691224881, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1502)");
            }
            Modifier modifier19 = modifier2;
            Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function1119 = function7;
            m896TrackImplVvwgllI(sliderState2, f, modifier19, z5, sliderColors4, function1118, function1119, f9, f8, true, false, composerStartRestartGroup, (57344 & i15) | (i15 & 14) | 805306368 | (i15 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (458752 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ((i15 >> 24) & 112) | 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f6 = f8;
            f5 = f9;
            function5 = function1119;
            function6 = function1118;
            sliderColors3 = sliderColors4;
            z4 = z5;
            modifier3 = modifier19;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function5 = function3;
            modifier3 = modifier2;
            z4 = z2;
            sliderColors3 = sliderColors2;
            f5 = f4;
            function6 = function4;
            f6 = f3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ted
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.e(this.b, sliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final SliderColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1376295968, i, -1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:1107)");
        }
        SliderColors defaultSliderColors$material3 = getDefaultSliderColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultSliderColors$material3;
    }

    /* JADX INFO: renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m908colorsq0g_0yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, Composer composer, int i, int i2, int i3) {
        long jM3170getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        long j11 = jM3170getUnspecified0d7_KjU;
        long jM3170getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j9;
        long jM3170getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885588574, i, i2, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:1149)");
        }
        SliderColors sliderColorsM882copyK518z4 = getDefaultSliderColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m882copyK518z4(j11, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8, jM3170getUnspecified0d7_KjU9, jM3170getUnspecified0d7_KjU10);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return sliderColorsM882copyK518z4;
    }

    /* JADX INFO: renamed from: drawStopIndicator-x3O1jOs, reason: not valid java name */
    public final void m909drawStopIndicatorx3O1jOs(DrawScope drawScope, long j, float f, long j2) {
        DrawScope.m3689drawCircleVaOC9Bg$default(drawScope, j2, drawScope.mo4557toPx0680j_4(f) / 2.0f, j, 0.0f, null, null, 0, 120, null);
    }

    public final SliderColors getDefaultSliderColors$material3(ColorScheme colorScheme) {
        SliderColors defaultSliderColorsCached = colorScheme.getDefaultSliderColorsCached();
        if (defaultSliderColorsCached != null) {
            return defaultSliderColorsCached;
        }
        SliderTokens sliderTokens = SliderTokens.INSTANCE;
        SliderColors sliderColors = new SliderColors(ColorSchemeKt.fromToken(colorScheme, sliderTokens.getHandleColor()), ColorSchemeKt.fromToken(colorScheme, sliderTokens.getActiveTrackColor()), ColorSchemeKt.fromToken(colorScheme, sliderTokens.getInactiveTrackColor()), ColorSchemeKt.fromToken(colorScheme, sliderTokens.getInactiveTrackColor()), ColorSchemeKt.fromToken(colorScheme, sliderTokens.getActiveTrackColor()), ColorKt.m3179compositeOverOWjLjI(Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, sliderTokens.getDisabledHandleColor()), sliderTokens.getDisabledHandleOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, sliderTokens.getDisabledActiveTrackColor()), sliderTokens.getDisabledActiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, sliderTokens.getDisabledInactiveTrackColor()), sliderTokens.getDisabledInactiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, sliderTokens.getDisabledInactiveTrackColor()), sliderTokens.getDisabledInactiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, sliderTokens.getDisabledActiveTrackColor()), sliderTokens.getDisabledActiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultSliderColorsCached$material3(sliderColors);
        return sliderColors;
    }

    /* JADX INFO: renamed from: getTickSize-D9Ej5fM, reason: not valid java name */
    public final float m910getTickSizeD9Ej5fM() {
        return TickSize;
    }

    /* JADX INFO: renamed from: getTrackStopIndicatorSize-D9Ej5fM, reason: not valid java name */
    public final float m911getTrackStopIndicatorSizeD9Ej5fM() {
        return TrackStopIndicatorSize;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0042  */
    /* JADX WARN: Code duplicated, block: B:28:0x0046  */
    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:31:0x0051  */
    /* JADX WARN: Code duplicated, block: B:34:0x0057  */
    /* JADX WARN: Code duplicated, block: B:37:0x005d  */
    /* JADX WARN: Code duplicated, block: B:39:0x0062  */
    /* JADX WARN: Code duplicated, block: B:41:0x0066  */
    /* JADX WARN: Code duplicated, block: B:43:0x006e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0071  */
    /* JADX WARN: Code duplicated, block: B:48:0x0078  */
    /* JADX WARN: Code duplicated, block: B:49:0x007b  */
    /* JADX WARN: Code duplicated, block: B:51:0x007f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0085  */
    /* JADX WARN: Code duplicated, block: B:54:0x0088  */
    /* JADX WARN: Code duplicated, block: B:58:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x0094  */
    /* JADX WARN: Code duplicated, block: B:62:0x009d  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b7 A[PHI: r4 r5 r6
      0x00b7: PHI (r4v8 androidx.compose.ui.Modifier) = (r4v4 androidx.compose.ui.Modifier), (r4v10 androidx.compose.ui.Modifier) binds: [B:80:0x00d0, B:70:0x00b4] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r5v14 androidx.compose.material3.SliderColors) = (r5v7 androidx.compose.material3.SliderColors), (r5v15 androidx.compose.material3.SliderColors) binds: [B:80:0x00d0, B:70:0x00b4] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r6v13 int) = (r6v8 int), (r6v14 int) binds: [B:80:0x00d0, B:70:0x00b4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:72:0x00b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00be  */
    /* JADX WARN: Code duplicated, block: B:77:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:84:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:87:0x0114  */
    /* JADX WARN: Code duplicated, block: B:89:0x011a  */
    /* JADX WARN: Code duplicated, block: B:92:0x0126  */
    /* JADX WARN: Code duplicated, block: B:94:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacySliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(sliderState, modifier, enabled, colors, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        int i5;
        int i6;
        boolean z3;
        final Modifier modifier3;
        final SliderColors sliderColors3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        SliderColors sliderColorsColors;
        int i7;
        boolean z5;
        Composer composerStartRestartGroup = composer.startRestartGroup(593554206);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(sliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i8 = i2 & 2;
        if (i8 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    int i9 = composerStartRestartGroup.changed(sliderColors2) ? 256 : 128;
                    i3 |= i9;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i9;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        modifier4 = modifier2;
                        sliderColorsColors = sliderColors2;
                        i7 = i3;
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                            i3 &= -897;
                        } else {
                            sliderColorsColors = sliderColors2;
                        }
                        i7 = i3;
                        if (i4 != 0) {
                            z5 = true;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(593554206, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1400)");
                        }
                        Modifier modifier5 = modifier4;
                        sliderColors3 = sliderColorsColors;
                        m905Track4EFweAY(sliderState, modifier5, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z5;
                        modifier3 = modifier5;
                    }
                    z5 = z2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(593554206, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1400)");
                    }
                    Modifier modifier6 = modifier4;
                    sliderColors3 = sliderColorsColors;
                    m905Track4EFweAY(sliderState, modifier6, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z5;
                    modifier3 = modifier6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ned
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.f(this.b, sliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(593554206, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1400)");
                }
                Modifier modifier7 = modifier4;
                sliderColors3 = sliderColorsColors;
                m905Track4EFweAY(sliderState, modifier7, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                modifier3 = modifier7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ned
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.f(this.b, sliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i9;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i9;
        } else {
            sliderColors2 = sliderColors;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(593554206, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1400)");
                }
                Modifier modifier8 = modifier4;
                sliderColors3 = sliderColorsColors;
                m905Track4EFweAY(sliderState, modifier8, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                modifier3 = modifier8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ned
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.f(this.b, sliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i6 = 16384;
            } else {
                i6 = 8192;
            }
            i3 |= i6;
        }
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                    i3 &= -897;
                } else {
                    sliderColorsColors = sliderColors2;
                }
                i7 = i3;
                if (i4 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
            } else {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                    i3 &= -897;
                } else {
                    sliderColorsColors = sliderColors2;
                }
                i7 = i3;
                if (i4 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(593554206, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1400)");
            }
            Modifier modifier9 = modifier4;
            sliderColors3 = sliderColorsColors;
            m905Track4EFweAY(sliderState, modifier9, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z5;
            modifier3 = modifier9;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            sliderColors3 = sliderColors2;
            z4 = z2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ned
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.f(this.b, sliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0042  */
    /* JADX WARN: Code duplicated, block: B:28:0x0046  */
    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:31:0x0051  */
    /* JADX WARN: Code duplicated, block: B:34:0x0057  */
    /* JADX WARN: Code duplicated, block: B:37:0x005d  */
    /* JADX WARN: Code duplicated, block: B:39:0x0062  */
    /* JADX WARN: Code duplicated, block: B:41:0x0066  */
    /* JADX WARN: Code duplicated, block: B:43:0x006e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0071  */
    /* JADX WARN: Code duplicated, block: B:48:0x0078  */
    /* JADX WARN: Code duplicated, block: B:49:0x007b  */
    /* JADX WARN: Code duplicated, block: B:51:0x007f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0085  */
    /* JADX WARN: Code duplicated, block: B:54:0x0088  */
    /* JADX WARN: Code duplicated, block: B:58:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x0094  */
    /* JADX WARN: Code duplicated, block: B:62:0x009d  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b7 A[PHI: r4 r5 r6
      0x00b7: PHI (r4v8 androidx.compose.ui.Modifier) = (r4v4 androidx.compose.ui.Modifier), (r4v10 androidx.compose.ui.Modifier) binds: [B:80:0x00d0, B:70:0x00b4] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r5v14 androidx.compose.material3.SliderColors) = (r5v7 androidx.compose.material3.SliderColors), (r5v15 androidx.compose.material3.SliderColors) binds: [B:80:0x00d0, B:70:0x00b4] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r6v13 int) = (r6v8 int), (r6v14 int) binds: [B:80:0x00d0, B:70:0x00b4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:72:0x00b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00be  */
    /* JADX WARN: Code duplicated, block: B:77:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:78:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:84:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:87:0x0114  */
    /* JADX WARN: Code duplicated, block: B:89:0x011a  */
    /* JADX WARN: Code duplicated, block: B:92:0x0126  */
    /* JADX WARN: Code duplicated, block: B:94:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacyRangeSliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(rangeSliderState, modifier, colors, enabled, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    public final /* synthetic */ void Track(final RangeSliderState rangeSliderState, Modifier modifier, SliderColors sliderColors, boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        int i5;
        int i6;
        boolean z3;
        final Modifier modifier3;
        final SliderColors sliderColors3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        SliderColors sliderColorsColors;
        int i7;
        boolean z5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1617869097);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(rangeSliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i8 = i2 & 2;
        if (i8 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    int i9 = composerStartRestartGroup.changed(sliderColors2) ? 256 : 128;
                    i3 |= i9;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i9;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                }
                if ((i3 & 9363) != 9362) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        modifier4 = modifier2;
                        sliderColorsColors = sliderColors2;
                        i7 = i3;
                    } else {
                        if (i8 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 4) != 0) {
                            sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                            i3 &= -897;
                        } else {
                            sliderColorsColors = sliderColors2;
                        }
                        i7 = i3;
                        if (i4 != 0) {
                            z5 = true;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1617869097, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1688)");
                        }
                        Modifier modifier5 = modifier4;
                        sliderColors3 = sliderColorsColors;
                        m904Track4EFweAY(rangeSliderState, modifier5, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z4 = z5;
                        modifier3 = modifier5;
                    }
                    z5 = z2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1617869097, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1688)");
                    }
                    Modifier modifier6 = modifier4;
                    sliderColors3 = sliderColorsColors;
                    m904Track4EFweAY(rangeSliderState, modifier6, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z4 = z5;
                    modifier3 = modifier6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    sliderColors3 = sliderColors2;
                    z4 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: yed
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.u(this.b, rangeSliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1617869097, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1688)");
                }
                Modifier modifier7 = modifier4;
                sliderColors3 = sliderColorsColors;
                m904Track4EFweAY(rangeSliderState, modifier7, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                modifier3 = modifier7;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: yed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.u(this.b, rangeSliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i9;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i9;
        } else {
            sliderColors2 = sliderColors;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i2 & 16) != 0) {
                i3 |= 24576;
            } else if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                } else {
                    if (i8 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 4) != 0) {
                        sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                        i3 &= -897;
                    } else {
                        sliderColorsColors = sliderColors2;
                    }
                    i7 = i3;
                    if (i4 != 0) {
                        z5 = true;
                    } else {
                        z5 = z2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1617869097, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1688)");
                }
                Modifier modifier8 = modifier4;
                sliderColors3 = sliderColorsColors;
                m904Track4EFweAY(rangeSliderState, modifier8, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
                modifier3 = modifier8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                sliderColors3 = sliderColors2;
                z4 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: yed
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.u(this.b, rangeSliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i6 = 16384;
            } else {
                i6 = 8192;
            }
            i3 |= i6;
        }
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                    i3 &= -897;
                } else {
                    sliderColorsColors = sliderColors2;
                }
                i7 = i3;
                if (i4 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
            } else {
                if (i8 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 12) & 14);
                    i3 &= -897;
                } else {
                    sliderColorsColors = sliderColors2;
                }
                i7 = i3;
                if (i4 != 0) {
                    z5 = true;
                } else {
                    z5 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1617869097, i7, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1688)");
            }
            Modifier modifier9 = modifier4;
            sliderColors3 = sliderColorsColors;
            m904Track4EFweAY(rangeSliderState, modifier9, z5, sliderColors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, SliderKt.ThumbTrackGapSize, SliderKt.TrackInsideCornerSize, composerStartRestartGroup, (i7 & 14) | 14155776 | (i7 & 112) | ((i7 >> 3) & 896) | ((i7 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i7 << 12) & 234881024), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z4 = z5;
            modifier3 = modifier9;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            sliderColors3 = sliderColors2;
            z4 = z2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: yed
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.u(this.b, rangeSliderState, modifier3, sliderColors3, z4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x011c  */
    /* JADX WARN: Code duplicated, block: B:103:0x0126  */
    /* JADX WARN: Code duplicated, block: B:105:0x0130  */
    /* JADX WARN: Code duplicated, block: B:116:0x014e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:117:0x0150  */
    /* JADX WARN: Code duplicated, block: B:119:0x0155  */
    /* JADX WARN: Code duplicated, block: B:122:0x015b  */
    /* JADX WARN: Code duplicated, block: B:125:0x016a  */
    /* JADX WARN: Code duplicated, block: B:127:0x0172  */
    /* JADX WARN: Code duplicated, block: B:129:0x0178  */
    /* JADX WARN: Code duplicated, block: B:135:0x0187  */
    /* JADX WARN: Code duplicated, block: B:138:0x0191  */
    /* JADX WARN: Code duplicated, block: B:140:0x0199  */
    /* JADX WARN: Code duplicated, block: B:143:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:145:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:147:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:151:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:152:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:155:0x01de  */
    /* JADX WARN: Code duplicated, block: B:158:0x0226  */
    /* JADX WARN: Code duplicated, block: B:159:0x022a  */
    /* JADX WARN: Code duplicated, block: B:162:0x023b  */
    /* JADX WARN: Code duplicated, block: B:164:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:45:0x007c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:79:0x00db  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:92:0x0100  */
    /* JADX WARN: Code duplicated, block: B:94:0x0106  */
    /* JADX WARN: Code duplicated, block: B:95:0x0109  */
    /* JADX WARN: Code duplicated, block: B:99:0x0119  */
    /* JADX INFO: renamed from: Track-4EFweAY, reason: not valid java name */
    public final void m905Track4EFweAY(final SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f, float f2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        int i5;
        final SliderColors sliderColors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        int i6;
        int i7;
        int i8;
        final float f3;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z3;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function5;
        final Modifier modifier3;
        final boolean z4;
        final SliderColors sliderColors3;
        final Function2<? super DrawScope, ? super Offset, Unit> function6;
        final float f4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        int i13;
        Object objRememberedValue;
        boolean z5;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(49984771);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(sliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i14 = i2 & 2;
        if (i14 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        sliderColors2 = sliderColors;
                        int i15 = composerStartRestartGroup.changed(sliderColors2) ? 2048 : 1024;
                        i3 |= i15;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i15;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        function4 = function2;
                        int i16 = composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
                        i3 |= i16;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i16;
                } else {
                    function4 = function2;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                    f3 = f;
                } else {
                    f3 = f;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
                if ((i2 & 256) != 0) {
                    i3 |= 100663296;
                } else if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i12 = 33554432;
                    }
                    i3 |= i12;
                }
                if ((38347923 & i3) != 38347922) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                        }
                        function5 = function3;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 8) != 0) {
                            SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                            i3 &= -7169;
                            sliderColors2 = sliderColorsColors;
                        }
                        if ((i2 & 16) != 0) {
                            z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = new Function2() { // from class: dfd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-57345) & i3;
                        }
                        if (i6 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i8 != 0) {
                            f3 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i10 != 0) {
                            function5 = function7;
                            i13 = i3;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f4 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function5 = function7;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(49984771, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1446)");
                        }
                        int i17 = i13 << 3;
                        modifier3 = modifier2;
                        function6 = function4;
                        m896TrackImplVvwgllI(sliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function6, function5, f3, f4, false, false, composerStartRestartGroup, (i13 & 14) | 805306416 | (i17 & 896) | (i17 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i17) | (458752 & i17) | (3670016 & i17) | (29360128 & i17) | (i17 & 234881024), ((i13 >> 21) & 112) | 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    i13 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f4 = f2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(49984771, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1446)");
                    }
                    int i18 = i13 << 3;
                    modifier3 = modifier2;
                    function6 = function4;
                    m896TrackImplVvwgllI(sliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function6, function5, f3, f4, false, false, composerStartRestartGroup, (i13 & 14) | 805306416 | (i18 & 896) | (i18 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i18) | (458752 & i18) | (3670016 & i18) | (29360128 & i18) | (i18 & 234881024), ((i13 >> 21) & 112) | 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    function6 = function4;
                    f4 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.r(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f3, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            z2 = z;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i15;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i15;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i16;
                } else {
                    function4 = function2;
                }
                i3 |= i16;
            } else {
                function4 = function2;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                f3 = f;
            } else {
                f3 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i12 = 33554432;
                }
                i3 |= i12;
            }
            if ((38347923 & i3) != 38347922) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors2;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z5) {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors3;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z5) {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(49984771, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1446)");
                }
                int i19 = i13 << 3;
                modifier3 = modifier2;
                function6 = function4;
                m896TrackImplVvwgllI(sliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function6, function5, f3, f4, false, false, composerStartRestartGroup, (i13 & 14) | 805306416 | (i19 & 896) | (i19 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i19) | (458752 & i19) | (3670016 & i19) | (29360128 & i19) | (i19 & 234881024), ((i13 >> 21) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function6 = function4;
                f4 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.r(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f3, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i15;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i15;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i16;
                } else {
                    function4 = function2;
                }
                i3 |= i16;
            } else {
                function4 = function2;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
                f3 = f;
            } else {
                f3 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
            if ((i2 & 256) != 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i12 = 33554432;
                }
                i3 |= i12;
            }
            if ((38347923 & i3) != 38347922) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors4;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z5) {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 8) != 0) {
                        SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                        i3 &= -7169;
                        sliderColors2 = sliderColorsColors5;
                    }
                    if ((i2 & 16) != 0) {
                        z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z5) {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: dfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-57345) & i3;
                    }
                    if (i6 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i8 != 0) {
                        f3 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i10 != 0) {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function5 = function7;
                        i13 = i3;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f4 = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(49984771, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1446)");
                }
                int i110 = i13 << 3;
                modifier3 = modifier2;
                function6 = function4;
                m896TrackImplVvwgllI(sliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function6, function5, f3, f4, false, false, composerStartRestartGroup, (i13 & 14) | 805306416 | (i110 & 896) | (i110 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i110) | (458752 & i110) | (3670016 & i110) | (29360128 & i110) | (i110 & 234881024), ((i13 >> 21) & 112) | 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                function6 = function4;
                f4 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.r(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f3, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i15;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i15;
        } else {
            sliderColors2 = sliderColors;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                function4 = function2;
                if (composerStartRestartGroup.changedInstance(function4)) {
                }
                i3 |= i16;
            } else {
                function4 = function2;
            }
            i3 |= i16;
        } else {
            function4 = function2;
        }
        i6 = i2 & 32;
        if (i6 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i7 = 131072;
            } else {
                i7 = 65536;
            }
            i3 |= i7;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
            f3 = f;
        } else {
            f3 = f;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(f2)) {
                i11 = 8388608;
            } else {
                i11 = 4194304;
            }
            i3 |= i11;
        }
        if ((i2 & 256) != 0) {
            i3 |= 100663296;
        } else if ((i & 100663296) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i12 = 33554432;
            }
            i3 |= i12;
        }
        if ((38347923 & i3) != 38347922) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors6;
                }
                if ((i2 & 16) != 0) {
                    z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = new Function2() { // from class: dfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: dfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-57345) & i3;
                }
                if (i6 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i8 != 0) {
                    f3 = SliderKt.ThumbTrackGapSize;
                }
                if (i10 != 0) {
                    function5 = function7;
                    i13 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f4 = SliderKt.TrackInsideCornerSize;
                } else {
                    function5 = function7;
                    i13 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f4 = f2;
                }
            } else {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z2 = true;
                }
                if ((i2 & 8) != 0) {
                    SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 24) & 14);
                    i3 &= -7169;
                    sliderColors2 = sliderColorsColors7;
                }
                if ((i2 & 16) != 0) {
                    z5 = ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z5) {
                        objRememberedValue2 = new Function2() { // from class: dfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: dfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.g(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-57345) & i3;
                }
                if (i6 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m915invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m915invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i8 != 0) {
                    f3 = SliderKt.ThumbTrackGapSize;
                }
                if (i10 != 0) {
                    function5 = function7;
                    i13 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f4 = SliderKt.TrackInsideCornerSize;
                } else {
                    function5 = function7;
                    i13 = i3;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f4 = f2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(49984771, i13, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1446)");
            }
            int i111 = i13 << 3;
            modifier3 = modifier2;
            function6 = function4;
            m896TrackImplVvwgllI(sliderState, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM(), modifier3, z4, sliderColors3, function6, function5, f3, f4, false, false, composerStartRestartGroup, (i13 & 14) | 805306416 | (i111 & 896) | (i111 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i111) | (458752 & i111) | (3670016 & i111) | (29360128 & i111) | (i111 & 234881024), ((i13 >> 21) & 112) | 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function5 = function3;
            modifier3 = modifier2;
            z4 = z2;
            sliderColors3 = sliderColors2;
            function6 = function4;
            f4 = f2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: efd
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.r(this.b, sliderState, modifier3, z4, sliderColors3, function6, function5, f3, f4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:102:0x011d  */
    /* JADX WARN: Code duplicated, block: B:104:0x0121  */
    /* JADX WARN: Code duplicated, block: B:106:0x0127  */
    /* JADX WARN: Code duplicated, block: B:107:0x012a  */
    /* JADX WARN: Code duplicated, block: B:111:0x013a  */
    /* JADX WARN: Code duplicated, block: B:112:0x013d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0147  */
    /* JADX WARN: Code duplicated, block: B:117:0x0154  */
    /* JADX WARN: Code duplicated, block: B:128:0x0175 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:129:0x0177  */
    /* JADX WARN: Code duplicated, block: B:131:0x017c  */
    /* JADX WARN: Code duplicated, block: B:134:0x0182  */
    /* JADX WARN: Code duplicated, block: B:137:0x0191  */
    /* JADX WARN: Code duplicated, block: B:139:0x019b  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:147:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:150:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:152:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:155:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:157:0x01de  */
    /* JADX WARN: Code duplicated, block: B:159:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:161:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:163:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:164:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:167:0x020a  */
    /* JADX WARN: Code duplicated, block: B:170:0x0225  */
    /* JADX WARN: Code duplicated, block: B:172:0x0231  */
    /* JADX WARN: Code duplicated, block: B:175:0x0244  */
    /* JADX WARN: Code duplicated, block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006c  */
    /* JADX WARN: Code duplicated, block: B:41:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0078  */
    /* JADX WARN: Code duplicated, block: B:44:0x007b  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:63:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:80:0x00da  */
    /* JADX WARN: Code duplicated, block: B:81:0x00df  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:85:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:92:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:94:0x0103  */
    /* JADX WARN: Code duplicated, block: B:96:0x010d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0110  */
    /* JADX INFO: renamed from: Track-mnvyFg4$material3, reason: not valid java name */
    public final void m906TrackmnvyFg4$material3(final RangeSliderState rangeSliderState, final float f, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f2, float f3, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        final boolean z2;
        int i7;
        final SliderColors sliderColors2;
        Function2<? super DrawScope, ? super Offset, Unit> function4;
        int i8;
        int i9;
        int i10;
        float f4;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z3;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function5;
        final Modifier modifier3;
        final boolean z4;
        final SliderColors sliderColors3;
        final float f5;
        final Function2<? super DrawScope, ? super Offset, Unit> function6;
        final float f6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function7;
        Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function8;
        int i15;
        Modifier modifier4;
        boolean z5;
        SliderColors sliderColors4;
        float f7;
        Function2<? super DrawScope, ? super Offset, Unit> function9;
        float f8;
        Object objRememberedValue;
        boolean z6;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1952945688);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(rangeSliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        z2 = z;
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        if ((i2 & 16) == 0) {
                            sliderColors2 = sliderColors;
                            int i16 = composerStartRestartGroup.changed(sliderColors2) ? 16384 : 8192;
                            i3 |= i16;
                        } else {
                            sliderColors2 = sliderColors;
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            function4 = function2;
                            int i17 = composerStartRestartGroup.changedInstance(function4) ? 131072 : 65536;
                            i3 |= i17;
                        } else {
                            function4 = function2;
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        i3 |= 12582912;
                        f4 = f2;
                    } else {
                        f4 = f2;
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(f4)) {
                                i11 = 8388608;
                            } else {
                                i11 = 4194304;
                            }
                            i3 |= i11;
                        }
                    }
                    i12 = i2 & 256;
                    if (i12 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(f3)) {
                                i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i13 = 33554432;
                            }
                            i3 |= i13;
                        }
                        if ((i2 & 512) != 0) {
                            i3 |= 805306368;
                        } else if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(this)) {
                                i14 = 536870912;
                            } else {
                                i14 = 268435456;
                            }
                            i3 |= i14;
                        }
                        if ((306783379 & i3) != 306783378) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                function8 = function3;
                            } else {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    z2 = true;
                                }
                                if ((i2 & 16) != 0) {
                                    SliderColors sliderColorsColors = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                    i3 &= -57345;
                                    sliderColors2 = sliderColorsColors;
                                }
                                if ((i2 & 32) != 0) {
                                    z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                    if (!z6 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = new Function2() { // from class: ffd
                                            public final Object invoke(Object obj, Object obj2) {
                                                return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                            }
                                        };
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                    }
                                    function4 = (Function2) objRememberedValue2;
                                    i3 = (-458753) & i3;
                                }
                                if (i8 != 0) {
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                                m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                            public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                            }
                                        };
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    function7 = (Function3) objRememberedValue;
                                } else {
                                    function7 = function3;
                                }
                                if (i10 != 0) {
                                    f4 = SliderKt.ThumbTrackGapSize;
                                }
                                if (i12 != 0) {
                                    function8 = function7;
                                    i15 = i3;
                                    modifier4 = modifier2;
                                    z5 = z2;
                                    sliderColors4 = sliderColors2;
                                    f7 = f4;
                                    function9 = function4;
                                    f8 = SliderKt.TrackInsideCornerSize;
                                } else {
                                    function8 = function7;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                                }
                                m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                f6 = f8;
                                f5 = f7;
                                function5 = function8;
                                function6 = function9;
                                sliderColors3 = sliderColors4;
                                z4 = z5;
                                modifier3 = modifier4;
                            }
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                            }
                            m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            f6 = f8;
                            f5 = f7;
                            function5 = function8;
                            function6 = function9;
                            sliderColors3 = sliderColors4;
                            z4 = z5;
                            modifier3 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function5 = function3;
                            modifier3 = modifier2;
                            z4 = z2;
                            sliderColors3 = sliderColors2;
                            f5 = f4;
                            function6 = function4;
                            f6 = f3;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors2 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors2;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors3 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors3;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                        }
                        m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f7;
                        function5 = function8;
                        function6 = function9;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                z2 = z;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        sliderColors2 = sliderColors;
                        if (composerStartRestartGroup.changed(sliderColors2)) {
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors4 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors4;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors5 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors5;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                        }
                        m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f7;
                        function5 = function8;
                        function6 = function9;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors6 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors6;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors7 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors7;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                    }
                    m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f7;
                    function5 = function8;
                    function6 = function9;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            modifier2 = modifier;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        sliderColors2 = sliderColors;
                        if (composerStartRestartGroup.changed(sliderColors2)) {
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors8 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors8;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors9 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors9;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                        }
                        m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f7;
                        function5 = function8;
                        function6 = function9;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors10 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors10;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors11 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors11;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                    }
                    m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f7;
                    function5 = function8;
                    function6 = function9;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                f4 = f2;
            } else {
                f4 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors12 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors12;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors13 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors13;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                    }
                    m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f7;
                    function5 = function8;
                    function6 = function9;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors14 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors14;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors15 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors15;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                }
                m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f7;
                function5 = function8;
                function6 = function9;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z2 = z;
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        sliderColors2 = sliderColors;
                        if (composerStartRestartGroup.changed(sliderColors2)) {
                        }
                        i3 |= i16;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                        }
                        i3 |= i17;
                    } else {
                        function4 = function2;
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f4)) {
                            i11 = 8388608;
                        } else {
                            i11 = 4194304;
                        }
                        i3 |= i11;
                    }
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i3 |= i13;
                    }
                    if ((i2 & 512) != 0) {
                        i3 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(this)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i3 |= i14;
                    }
                    if ((306783379 & i3) != 306783378) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors16 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors16;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                SliderColors sliderColorsColors17 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                                i3 &= -57345;
                                sliderColors2 = sliderColorsColors17;
                            }
                            if ((i2 & 32) != 0) {
                                z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (!z6) {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                } else {
                                    objRememberedValue2 = new Function2() { // from class: ffd
                                        public final Object invoke(Object obj, Object obj2) {
                                            return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                function4 = (Function2) objRememberedValue2;
                                i3 = (-458753) & i3;
                            }
                            if (i8 != 0) {
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                            m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                        public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                            SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                            sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                function7 = (Function3) objRememberedValue;
                            } else {
                                function7 = function3;
                            }
                            if (i10 != 0) {
                                f4 = SliderKt.ThumbTrackGapSize;
                            }
                            if (i12 != 0) {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = SliderKt.TrackInsideCornerSize;
                            } else {
                                function8 = function7;
                                i15 = i3;
                                modifier4 = modifier2;
                                z5 = z2;
                                sliderColors4 = sliderColors2;
                                f7 = f4;
                                function9 = function4;
                                f8 = f3;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                        }
                        m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f6 = f8;
                        f5 = f7;
                        function5 = function8;
                        function6 = function9;
                        sliderColors3 = sliderColors4;
                        z4 = z5;
                        modifier3 = modifier4;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function5 = function3;
                        modifier3 = modifier2;
                        z4 = z2;
                        sliderColors3 = sliderColors2;
                        f5 = f4;
                        function6 = function4;
                        f6 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors18 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors18;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors19 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors19;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                    }
                    m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f7;
                    function5 = function8;
                    function6 = function9;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z2 = z;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                f4 = f2;
            } else {
                f4 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors110 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors110;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors111 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors111;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                    }
                    m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f7;
                    function5 = function8;
                    function6 = function9;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors112 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors112;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors113 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors113;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                }
                m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f7;
                function5 = function8;
                function6 = function9;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                z2 = z;
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    sliderColors2 = sliderColors;
                    if (composerStartRestartGroup.changed(sliderColors2)) {
                    }
                    i3 |= i16;
                } else {
                    sliderColors2 = sliderColors;
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                    }
                    i3 |= i17;
                } else {
                    function4 = function2;
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                i3 |= 12582912;
                f4 = f2;
            } else {
                f4 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f4)) {
                        i11 = 8388608;
                    } else {
                        i11 = 4194304;
                    }
                    i3 |= i11;
                }
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i3 |= i13;
                }
                if ((i2 & 512) != 0) {
                    i3 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(this)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i3 |= i14;
                }
                if ((306783379 & i3) != 306783378) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors114 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors114;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z2 = true;
                        }
                        if ((i2 & 16) != 0) {
                            SliderColors sliderColorsColors115 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                            i3 &= -57345;
                            sliderColors2 = sliderColorsColors115;
                        }
                        if ((i2 & 32) != 0) {
                            z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (!z6) {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function2() { // from class: ffd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function4 = (Function2) objRememberedValue2;
                            i3 = (-458753) & i3;
                        }
                        if (i8 != 0) {
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                        sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            function7 = (Function3) objRememberedValue;
                        } else {
                            function7 = function3;
                        }
                        if (i10 != 0) {
                            f4 = SliderKt.ThumbTrackGapSize;
                        }
                        if (i12 != 0) {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = SliderKt.TrackInsideCornerSize;
                        } else {
                            function8 = function7;
                            i15 = i3;
                            modifier4 = modifier2;
                            z5 = z2;
                            sliderColors4 = sliderColors2;
                            f7 = f4;
                            function9 = function4;
                            f8 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                    }
                    m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    f6 = f8;
                    f5 = f7;
                    function5 = function8;
                    function6 = function9;
                    sliderColors3 = sliderColors4;
                    z4 = z5;
                    modifier3 = modifier4;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function5 = function3;
                    modifier3 = modifier2;
                    z4 = z2;
                    sliderColors3 = sliderColors2;
                    f5 = f4;
                    function6 = function4;
                    f6 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors116 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors116;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors117 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors117;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                }
                m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f7;
                function5 = function8;
                function6 = function9;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z2 = z;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                sliderColors2 = sliderColors;
                if (composerStartRestartGroup.changed(sliderColors2)) {
                }
                i3 |= i16;
            } else {
                sliderColors2 = sliderColors;
            }
            i3 |= i16;
        } else {
            sliderColors2 = sliderColors;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                function4 = function2;
                if (composerStartRestartGroup.changedInstance(function4)) {
                }
                i3 |= i17;
            } else {
                function4 = function2;
            }
            i3 |= i17;
        } else {
            function4 = function2;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i9 = 1048576;
            } else {
                i9 = 524288;
            }
            i3 |= i9;
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            i3 |= 12582912;
            f4 = f2;
        } else {
            f4 = f2;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f4)) {
                    i11 = 8388608;
                } else {
                    i11 = 4194304;
                }
                i3 |= i11;
            }
        }
        i12 = i2 & 256;
        if (i12 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i13 = 33554432;
                }
                i3 |= i13;
            }
            if ((i2 & 512) != 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(this)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i3 |= i14;
            }
            if ((306783379 & i3) != 306783378) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors118 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors118;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 16) != 0) {
                        SliderColors sliderColorsColors119 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                        i3 &= -57345;
                        sliderColors2 = sliderColorsColors119;
                    }
                    if ((i2 & 32) != 0) {
                        z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (!z6) {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function2() { // from class: ffd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function4 = (Function2) objRememberedValue2;
                        i3 = (-458753) & i3;
                    }
                    if (i8 != 0) {
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                    m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                    SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                    sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        function7 = (Function3) objRememberedValue;
                    } else {
                        function7 = function3;
                    }
                    if (i10 != 0) {
                        f4 = SliderKt.ThumbTrackGapSize;
                    }
                    if (i12 != 0) {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = SliderKt.TrackInsideCornerSize;
                    } else {
                        function8 = function7;
                        i15 = i3;
                        modifier4 = modifier2;
                        z5 = z2;
                        sliderColors4 = sliderColors2;
                        f7 = f4;
                        function9 = function4;
                        f8 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
                }
                m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                f6 = f8;
                f5 = f7;
                function5 = function8;
                function6 = function9;
                sliderColors3 = sliderColors4;
                z4 = z5;
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = function3;
                modifier3 = modifier2;
                z4 = z2;
                sliderColors3 = sliderColors2;
                f5 = f4;
                function6 = function4;
                f6 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i2 & 512) != 0) {
            i3 |= 805306368;
        } else if ((i & 805306368) == 0) {
            if (composerStartRestartGroup.changed(this)) {
                i14 = 536870912;
            } else {
                i14 = 268435456;
            }
            i3 |= i14;
        }
        if ((306783379 & i3) != 306783378) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    SliderColors sliderColorsColors1110 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                    i3 &= -57345;
                    sliderColors2 = sliderColorsColors1110;
                }
                if ((i2 & 32) != 0) {
                    z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue2 = new Function2() { // from class: ffd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: ffd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-458753) & i3;
                }
                if (i8 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i10 != 0) {
                    f4 = SliderKt.ThumbTrackGapSize;
                }
                if (i12 != 0) {
                    function8 = function7;
                    i15 = i3;
                    modifier4 = modifier2;
                    z5 = z2;
                    sliderColors4 = sliderColors2;
                    f7 = f4;
                    function9 = function4;
                    f8 = SliderKt.TrackInsideCornerSize;
                } else {
                    function8 = function7;
                    i15 = i3;
                    modifier4 = modifier2;
                    z5 = z2;
                    sliderColors4 = sliderColors2;
                    f7 = f4;
                    function9 = function4;
                    f8 = f3;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    z2 = true;
                }
                if ((i2 & 16) != 0) {
                    SliderColors sliderColorsColors1111 = colors(composerStartRestartGroup, (i3 >> 27) & 14);
                    i3 &= -57345;
                    sliderColors2 = sliderColorsColors1111;
                }
                if ((i2 & 32) != 0) {
                    z6 = ((((57344 & i3) ^ 24576) <= 16384 && composerStartRestartGroup.changed(sliderColors2)) || (i3 & 24576) == 16384) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (!z6) {
                        objRememberedValue2 = new Function2() { // from class: ffd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function2() { // from class: ffd
                            public final Object invoke(Object obj, Object obj2) {
                                return SliderDefaults.s(sliderColors2, z2, (DrawScope) obj, (Offset) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function4 = (Function2) objRememberedValue2;
                    i3 = (-458753) & i3;
                }
                if (i8 != 0) {
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$15$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                m914invokewPWG1Vc(drawScope, offset.m2899unboximpl(), color.m3144unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-wPWG1Vc, reason: not valid java name */
                            public final void m914invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                SliderDefaults sliderDefaults = SliderDefaults.INSTANCE;
                                sliderDefaults.m909drawStopIndicatorx3O1jOs(drawScope, j, sliderDefaults.m910getTickSizeD9Ej5fM(), j2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function7 = (Function3) objRememberedValue;
                } else {
                    function7 = function3;
                }
                if (i10 != 0) {
                    f4 = SliderKt.ThumbTrackGapSize;
                }
                if (i12 != 0) {
                    function8 = function7;
                    i15 = i3;
                    modifier4 = modifier2;
                    z5 = z2;
                    sliderColors4 = sliderColors2;
                    f7 = f4;
                    function9 = function4;
                    f8 = SliderKt.TrackInsideCornerSize;
                } else {
                    function8 = function7;
                    i15 = i3;
                    modifier4 = modifier2;
                    z5 = z2;
                    sliderColors4 = sliderColors2;
                    f7 = f4;
                    function9 = function4;
                    f8 = f3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1952945688, i15, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1785)");
            }
            m897TrackImplxlyIBlM(rangeSliderState, f, modifier4, z5, sliderColors4, function9, function8, f7, f8, composerStartRestartGroup, i15 & 2147483646);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f6 = f8;
            f5 = f7;
            function5 = function8;
            function6 = function9;
            sliderColors3 = sliderColors4;
            z4 = z5;
            modifier3 = modifier4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function5 = function3;
            modifier3 = modifier2;
            z4 = z2;
            sliderColors3 = sliderColors2;
            f5 = f4;
            function6 = function4;
            f6 = f3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gfd
                public final Object invoke(Object obj, Object obj2) {
                    return SliderDefaults.a(this.b, rangeSliderState, f, modifier3, z4, sliderColors3, function6, function5, f5, f6, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
