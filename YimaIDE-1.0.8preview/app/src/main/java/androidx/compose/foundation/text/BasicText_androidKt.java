package androidx.compose.foundation.text;

import android.os.Trace;
import androidx.compose.foundation.text.BasicText_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u001a%\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\u0010\r\u001a;\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0014\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u0010H\u0001¢\u0006\u0002\u0010\u0013\u001a\u0010\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0015H\u0000\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001a\"\u001a\u0010\u001b\u001a\u00020\u00198@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"LocalBackgroundTextMeasurementExecutor", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Ljava/util/concurrent/Executor;", "getLocalBackgroundTextMeasurementExecutor", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "BackgroundTextMeasurement", "", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/text/AnnotatedString;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "PrefetchTextMinimumCoreCount", "", "MinTextLengthThreshold", "MaxTextLengthThreshold", "backingCoreCountSatisfactory", "", "Ljava/lang/Boolean;", "coreCountSatisfactory", "getCoreCountSatisfactory$annotations", "()V", "getCoreCountSatisfactory", "()Z", "shouldPrefetch", "textLength", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BasicText_androidKt {
    private static final ProvidableCompositionLocal<Executor> LocalBackgroundTextMeasurementExecutor = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: ns0
        public final Object invoke() {
            return BasicText_androidKt.b();
        }
    });
    private static final int MaxTextLengthThreshold = 1000;
    private static final int MinTextLengthThreshold = 8;
    private static final int PrefetchTextMinimumCoreCount = 4;
    private static Boolean backingCoreCountSatisfactory;

    /* JADX WARN: Code duplicated, block: B:13:0x004f A[Catch: RejectedExecutionException -> 0x00a2, TryCatch #0 {RejectedExecutionException -> 0x00a2, blocks: (B:11:0x0049, B:17:0x0056, B:19:0x006b, B:24:0x0076, B:26:0x0088, B:29:0x009d, B:28:0x0090, B:21:0x0071, B:13:0x004f), top: B:37:0x0049 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0053  */
    /* JADX WARN: Code duplicated, block: B:16:0x0055  */
    /* JADX WARN: Code duplicated, block: B:28:0x0090 A[Catch: RejectedExecutionException -> 0x00a2, TryCatch #0 {RejectedExecutionException -> 0x00a2, blocks: (B:11:0x0049, B:17:0x0056, B:19:0x006b, B:24:0x0076, B:26:0x0088, B:29:0x009d, B:28:0x0090, B:21:0x0071, B:13:0x004f), top: B:37:0x0049 }] */
    public static final void BackgroundTextMeasurement(final AnnotatedString annotatedString, final TextStyle textStyle, final FontFamily.Resolver resolver, final List<AnnotatedString.Range<Placeholder>> list, Composer composer, int i) {
        boolean z;
        boolean zChanged;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-650368117, i, -1, "androidx.compose.foundation.text.BackgroundTextMeasurement (BasicText.android.kt:102)");
        }
        Executor executor = (Executor) composer.consume(LocalBackgroundTextMeasurementExecutor);
        if (executor == null || !shouldPrefetch(annotatedString.length())) {
            composer.startReplaceGroup(-523310345);
        } else {
            composer.startReplaceGroup(-518761746);
            final LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
            final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
            if (((i & 112) ^ 48) > 32) {
                try {
                    if (composer.changed(textStyle)) {
                        z = true;
                    } else if ((i & 48) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zChanged = z | composer.changed(layoutDirection.ordinal()) | composer.changedInstance(list) | ((((i & 14) ^ 6) <= 4 && composer.changed(annotatedString)) || (i & 6) == 4) | composer.changed(density) | composer.changedInstance(resolver);
                    objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                        Runnable runnable = new Runnable() { // from class: ms0
                            @Override // java.lang.Runnable
                            public final void run() {
                                BasicText_androidKt.BackgroundTextMeasurement$lambda$1$0(textStyle, layoutDirection, list, annotatedString, density, resolver);
                            }
                        };
                        composer.updateRememberedValue(runnable);
                        objRememberedValue = runnable;
                    }
                    executor.execute((Runnable) objRememberedValue);
                } catch (RejectedExecutionException unused) {
                }
            } else {
                if ((i & 48) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                zChanged = z | composer.changed(layoutDirection.ordinal()) | composer.changedInstance(list) | ((((i & 14) ^ 6) <= 4 && composer.changed(annotatedString)) || (i & 6) == 4) | composer.changed(density) | composer.changedInstance(resolver);
                objRememberedValue = composer.rememberedValue();
                if (zChanged) {
                    Runnable runnable2 = new Runnable() { // from class: ms0
                        @Override // java.lang.Runnable
                        public final void run() {
                            BasicText_androidKt.BackgroundTextMeasurement$lambda$1$0(textStyle, layoutDirection, list, annotatedString, density, resolver);
                        }
                    };
                    composer.updateRememberedValue(runnable2);
                    objRememberedValue = runnable2;
                } else {
                    Runnable runnable3 = new Runnable() { // from class: ms0
                        @Override // java.lang.Runnable
                        public final void run() {
                            BasicText_androidKt.BackgroundTextMeasurement$lambda$1$0(textStyle, layoutDirection, list, annotatedString, density, resolver);
                        }
                    };
                    composer.updateRememberedValue(runnable3);
                    objRememberedValue = runnable3;
                }
                executor.execute((Runnable) objRememberedValue);
            }
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BackgroundTextMeasurement$lambda$0$0(TextStyle textStyle, LayoutDirection layoutDirection, String str, Density density, FontFamily.Resolver resolver) {
        Trace.beginSection("BackgroundTextMeasurement");
        try {
            MutableSnapshot mutableSnapshotTakeMutableSnapshot$default = Snapshot.Companion.takeMutableSnapshot$default(Snapshot.Companion, (Function1) null, (Function1) null, 3, (Object) null);
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot$default.makeCurrent();
                try {
                    ParagraphIntrinsicsKt.ParagraphIntrinsics$default(str, TextStyleKt.resolveDefaults(textStyle, layoutDirection), CollectionsKt.emptyList(), density, resolver, (List) null, 32, (Object) null).getMaxIntrinsicWidth();
                    Unit unit = Unit.INSTANCE;
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    mutableSnapshotTakeMutableSnapshot$default.apply().check();
                    mutableSnapshotTakeMutableSnapshot$default.dispose();
                    Trace.endSection();
                } catch (Throwable th) {
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    mutableSnapshotTakeMutableSnapshot$default.dispose();
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Trace.endSection();
            throw th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BackgroundTextMeasurement$lambda$1$0(TextStyle textStyle, LayoutDirection layoutDirection, List list, AnnotatedString annotatedString, Density density, FontFamily.Resolver resolver) {
        Trace.beginSection("BackgroundTextMeasurement");
        try {
            MutableSnapshot mutableSnapshotTakeMutableSnapshot$default = Snapshot.Companion.takeMutableSnapshot$default(Snapshot.Companion, (Function1) null, (Function1) null, 3, (Object) null);
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot$default.makeCurrent();
                try {
                    TextStyle textStyleResolveDefaults = TextStyleKt.resolveDefaults(textStyle, layoutDirection);
                    if (list == null) {
                        list = CollectionsKt.emptyList();
                    }
                    new MultiParagraphIntrinsics(annotatedString, textStyleResolveDefaults, list, density, resolver).getMaxIntrinsicWidth();
                    Unit unit = Unit.INSTANCE;
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    mutableSnapshotTakeMutableSnapshot$default.apply().check();
                    mutableSnapshotTakeMutableSnapshot$default.dispose();
                    Trace.endSection();
                } catch (Throwable th) {
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    mutableSnapshotTakeMutableSnapshot$default.dispose();
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Trace.endSection();
            throw th4;
        }
    }

    public static Executor b() {
        return null;
    }

    public static final boolean getCoreCountSatisfactory() {
        if (backingCoreCountSatisfactory == null) {
            backingCoreCountSatisfactory = Boolean.valueOf(Runtime.getRuntime().availableProcessors() >= 4);
        }
        Boolean bool = backingCoreCountSatisfactory;
        bool.getClass();
        return bool.booleanValue();
    }

    public static /* synthetic */ void getCoreCountSatisfactory$annotations() {
    }

    public static final ProvidableCompositionLocal<Executor> getLocalBackgroundTextMeasurementExecutor() {
        return LocalBackgroundTextMeasurementExecutor;
    }

    public static final boolean shouldPrefetch(int i) {
        return i >= 8 && i < MaxTextLengthThreshold && getCoreCountSatisfactory();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x004f A[Catch: RejectedExecutionException -> 0x009c, TryCatch #0 {RejectedExecutionException -> 0x009c, blocks: (B:11:0x0049, B:17:0x0056, B:19:0x0066, B:24:0x0071, B:26:0x0083, B:29:0x0097, B:28:0x008b, B:21:0x006c, B:13:0x004f), top: B:37:0x0049 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0053  */
    /* JADX WARN: Code duplicated, block: B:16:0x0055  */
    /* JADX WARN: Code duplicated, block: B:28:0x008b A[Catch: RejectedExecutionException -> 0x009c, TryCatch #0 {RejectedExecutionException -> 0x009c, blocks: (B:11:0x0049, B:17:0x0056, B:19:0x0066, B:24:0x0071, B:26:0x0083, B:29:0x0097, B:28:0x008b, B:21:0x006c, B:13:0x004f), top: B:37:0x0049 }] */
    public static final void BackgroundTextMeasurement(final String str, final TextStyle textStyle, final FontFamily.Resolver resolver, Composer composer, int i) {
        boolean z;
        boolean zChanged;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1589371739, i, -1, "androidx.compose.foundation.text.BackgroundTextMeasurement (BasicText.android.kt:68)");
        }
        Executor executor = (Executor) composer.consume(LocalBackgroundTextMeasurementExecutor);
        if (executor == null || !shouldPrefetch(str.length())) {
            composer.startReplaceGroup(1250991751);
        } else {
            composer.startReplaceGroup(1254274527);
            final LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
            final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
            if (((i & 112) ^ 48) > 32) {
                try {
                    if (composer.changed(textStyle)) {
                        z = true;
                    } else if ((i & 48) == 32) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zChanged = z | composer.changed(layoutDirection.ordinal()) | ((((i & 14) ^ 6) <= 4 && composer.changed(str)) || (i & 6) == 4) | composer.changed(density) | composer.changedInstance(resolver);
                    objRememberedValue = composer.rememberedValue();
                    if (!zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                        Runnable runnable = new Runnable() { // from class: os0
                            @Override // java.lang.Runnable
                            public final void run() {
                                BasicText_androidKt.BackgroundTextMeasurement$lambda$0$0(textStyle, layoutDirection, str, density, resolver);
                            }
                        };
                        composer.updateRememberedValue(runnable);
                        objRememberedValue = runnable;
                    }
                    executor.execute((Runnable) objRememberedValue);
                } catch (RejectedExecutionException unused) {
                }
            } else {
                if ((i & 48) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                zChanged = z | composer.changed(layoutDirection.ordinal()) | ((((i & 14) ^ 6) <= 4 && composer.changed(str)) || (i & 6) == 4) | composer.changed(density) | composer.changedInstance(resolver);
                objRememberedValue = composer.rememberedValue();
                if (!zChanged) {
                    Runnable runnable2 = new Runnable() { // from class: os0
                        @Override // java.lang.Runnable
                        public final void run() {
                            BasicText_androidKt.BackgroundTextMeasurement$lambda$0$0(textStyle, layoutDirection, str, density, resolver);
                        }
                    };
                    composer.updateRememberedValue(runnable2);
                    objRememberedValue = runnable2;
                } else {
                    Runnable runnable3 = new Runnable() { // from class: os0
                        @Override // java.lang.Runnable
                        public final void run() {
                            BasicText_androidKt.BackgroundTextMeasurement$lambda$0$0(textStyle, layoutDirection, str, density, resolver);
                        }
                    };
                    composer.updateRememberedValue(runnable3);
                    objRememberedValue = runnable3;
                }
                executor.execute((Runnable) objRememberedValue);
            }
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
