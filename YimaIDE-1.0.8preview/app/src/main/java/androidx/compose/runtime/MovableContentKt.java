package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MovableContentKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a+\u0010\u0000\u001a\r\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a=\u0010\u0000\u001a\u0013\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\b\u0003\"\u0004\b\u0000\u0010\u00072\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0002\u0010\b\u001aO\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0002\b\u0003\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b2\u001d\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0002\u0010\f\u001aa\u0010\u0000\u001a\u001f\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0002\b\u0003\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\"\u0004\b\u0002\u0010\u000e2#\u0010\u0004\u001a\u001f\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001as\u0010\u0000\u001a%\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0002\b\u0003\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\"\u0004\b\u0002\u0010\u000e\"\u0004\b\u0003\u0010\u00112)\u0010\u0004\u001a%\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0002\u0010\u0012\u001aG\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015\"\u0004\b\u0000\u0010\u00142\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\b\u001aY\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u00072\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\f\u001ak\u0010\u0013\u001a$\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b2(\u0010\u0004\u001a$\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u000f\u001a}\u0010\u0013\u001a*\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b\"\u0004\b\u0003\u0010\u000e2.\u0010\u0004\u001a*\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0012\"\u000e\u0010\u0016\u001a\u00020\u0017X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"movableContentOf", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "content", "(Lkotlin/jvm/functions/Function2;)Lkotlin/jvm/functions/Function2;", "Lkotlin/Function1;", "P", "(Lkotlin/jvm/functions/Function3;)Lkotlin/jvm/functions/Function3;", "Lkotlin/Function2;", "P1", "P2", "(Lkotlin/jvm/functions/Function4;)Lkotlin/jvm/functions/Function4;", "Lkotlin/Function3;", "P3", "(Lkotlin/jvm/functions/Function5;)Lkotlin/jvm/functions/Function5;", "Lkotlin/Function4;", "P4", "(Lkotlin/jvm/functions/Function6;)Lkotlin/jvm/functions/Function6;", "movableContentWithReceiverOf", "R", "Lkotlin/ExtensionFunctionType;", "movableContentKey", "", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MovableContentKt {
    public static final int movableContentKey = 126665345;

    public static Unit a(Function4 function4, Pair pair, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(pair) : composer.changedInstance(pair) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-95003188, i, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:196)");
            }
            function4.invoke(pair.getFirst(), pair.getSecond(), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(MovableContent movableContent, Object obj, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1032736913, i, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:63)");
            }
            composer.insertMovableContent(movableContent, obj);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(MovableContent movableContent, Object obj, Object obj2, Object obj3, Object obj4, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composer.changed(obj2) : composer.changedInstance(obj2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= (i & 512) == 0 ? composer.changed(obj3) : composer.changedInstance(obj3) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= (i & 4096) == 0 ? composer.changed(obj4) : composer.changedInstance(obj4) ? 2048 : 1024;
        }
        if (composer.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1304803723, i2, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:146)");
            }
            composer.insertMovableContent(movableContent, new Object[]{obj, obj2, obj3, obj4});
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(Function6 function6, Object[] objArr, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-346590649, i, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:248)");
        }
        function6.invoke(objArr[0], objArr[1], objArr[2], objArr[3], composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(MovableContent movableContent, Object obj, Object obj2, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composer.changed(obj2) : composer.changedInstance(obj2) ? 32 : 16;
        }
        if (composer.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1349529900, i2, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:197)");
            }
            composer.insertMovableContent(movableContent, TuplesKt.to(obj, obj2));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit f(Function5 function5, Triple triple, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(triple) : composer.changedInstance(triple) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-963896566, i, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:221)");
            }
            function5.invoke(triple.getFirst(), triple.getSecond(), triple.getThird(), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit g(MovableContent movableContent, Object obj, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1655274392, i, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:172)");
            }
            composer.insertMovableContent(movableContent, obj);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit h(Function3 function3, Object obj, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(668517896, i, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:171)");
            }
            function3.invoke(obj, composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit i(Function5 function5, Triple triple, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(triple) : composer.changedInstance(triple) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-846116651, i, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:113)");
            }
            function5.invoke(triple.getFirst(), triple.getSecond(), triple.getThird(), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit j(MovableContent movableContent, Object obj, Object obj2, Object obj3, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composer.changed(obj2) : composer.changedInstance(obj2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= (i & 512) == 0 ? composer.changed(obj3) : composer.changedInstance(obj3) ? 256 : 128;
        }
        if (composer.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1477386365, i2, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:115)");
            }
            composer.insertMovableContent(movableContent, new Triple(obj, obj2, obj3));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit k(Function4 function4, Pair pair, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= (i & 8) == 0 ? composer.changed(pair) : composer.changedInstance(pair) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(561467799, i, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:87)");
            }
            function4.invoke(pair.getFirst(), pair.getSecond(), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit l(MovableContent movableContent, Object obj, Object obj2, Object obj3, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composer.changed(obj2) : composer.changedInstance(obj2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= (i & 512) == 0 ? composer.changed(obj3) : composer.changedInstance(obj3) ? 256 : 128;
        }
        if (composer.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2121535275, i2, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:222)");
            }
            composer.insertMovableContent(movableContent, new Triple(obj, obj2, obj3));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit m(MovableContent movableContent, Object obj, Object obj2, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composer.changed(obj2) : composer.changedInstance(obj2) ? 32 : 16;
        }
        if (composer.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-525773808, i2, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:88)");
            }
            composer.insertMovableContent(movableContent, TuplesKt.to(obj, obj2));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final Function2<Composer, Integer, Unit> movableContentOf(final Function2<? super Composer, ? super Integer, Unit> function2) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(-703201834, true, new Function3() { // from class: androidx.compose.runtime.MovableContentKt$movableContentOf$movableContent$1
            public final void invoke(Void r3, Composer composer, int i) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-703201834, i, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:39)");
                }
                function2.invoke(composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                invoke((Void) obj, (Composer) obj2, ((Number) obj3).intValue());
                return Unit.INSTANCE;
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(-328108779, true, new Function2() { // from class: k7a
            public final Object invoke(Object obj, Object obj2) {
                return MovableContentKt.p(movableContent, (Composer) obj, ((Integer) obj2).intValue());
            }
        });
    }

    public static final <R> Function3<R, Composer, Integer, Unit> movableContentWithReceiverOf(final Function3<? super R, ? super Composer, ? super Integer, Unit> function3) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(668517896, true, new Function3() { // from class: t7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.h(function3, obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(-1655274392, true, new Function3() { // from class: u7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.g(movableContent, obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    public static Unit n(Function6 function6, Object[] objArr, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(743481490, i, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:142)");
        }
        function6.invoke(objArr[0], objArr[1], objArr[2], objArr[3], composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit o(MovableContent movableContent, Object obj, Object obj2, Object obj3, Object obj4, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composer.changed(obj) : composer.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composer.changed(obj2) : composer.changedInstance(obj2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= (i & 512) == 0 ? composer.changed(obj3) : composer.changedInstance(obj3) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= (i & 4096) == 0 ? composer.changed(obj4) : composer.changedInstance(obj4) ? 2048 : 1024;
        }
        if (composer.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(572330192, i2, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:252)");
            }
            composer.insertMovableContent(movableContent, new Object[]{obj, obj2, obj3, obj4});
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit p(MovableContent movableContent, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-328108779, i, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:40)");
            }
            composer.insertMovableContent(movableContent, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final <P> Function3<P, Composer, Integer, Unit> movableContentOf(Function3<? super P, ? super Composer, ? super Integer, Unit> function3) {
        final MovableContent movableContent = new MovableContent(function3);
        return ComposableLambdaKt.composableLambdaInstance(1032736913, true, new Function3() { // from class: n7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.b(movableContent, obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    public static final <R, P> Function4<R, P, Composer, Integer, Unit> movableContentWithReceiverOf(final Function4<? super R, ? super P, ? super Composer, ? super Integer, Unit> function4) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(-95003188, true, new Function3() { // from class: v7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.a(function4, (Pair) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(1349529900, true, new Function4() { // from class: w7a
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MovableContentKt.e(movableContent, obj, obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        });
    }

    public static final <P1, P2> Function4<P1, P2, Composer, Integer, Unit> movableContentOf(final Function4<? super P1, ? super P2, ? super Composer, ? super Integer, Unit> function4) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(561467799, true, new Function3() { // from class: o7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.k(function4, (Pair) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(-525773808, true, new Function4() { // from class: p7a
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MovableContentKt.m(movableContent, obj, obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        });
    }

    public static final <R, P1, P2> Function5<R, P1, P2, Composer, Integer, Unit> movableContentWithReceiverOf(final Function5<? super R, ? super P1, ? super P2, ? super Composer, ? super Integer, Unit> function5) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(-963896566, true, new Function3() { // from class: x7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.f(function5, (Triple) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(2121535275, true, new Function5() { // from class: y7a
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return MovableContentKt.l(movableContent, obj, obj2, obj3, (Composer) obj4, ((Integer) obj5).intValue());
            }
        });
    }

    public static final <P1, P2, P3> Function5<P1, P2, P3, Composer, Integer, Unit> movableContentOf(final Function5<? super P1, ? super P2, ? super P3, ? super Composer, ? super Integer, Unit> function5) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(-846116651, true, new Function3() { // from class: j7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.i(function5, (Triple) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(1477386365, true, new Function5() { // from class: q7a
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return MovableContentKt.j(movableContent, obj, obj2, obj3, (Composer) obj4, ((Integer) obj5).intValue());
            }
        });
    }

    public static final <R, P1, P2, P3> Function6<R, P1, P2, P3, Composer, Integer, Unit> movableContentWithReceiverOf(final Function6<? super R, ? super P1, ? super P2, ? super P3, ? super Composer, ? super Integer, Unit> function6) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(-346590649, true, new Function3() { // from class: l7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.d(function6, (Object[]) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(572330192, true, new Function6() { // from class: m7a
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                return MovableContentKt.o(movableContent, obj, obj2, obj3, obj4, (Composer) obj5, ((Integer) obj6).intValue());
            }
        });
    }

    public static final <P1, P2, P3, P4> Function6<P1, P2, P3, P4, Composer, Integer, Unit> movableContentOf(final Function6<? super P1, ? super P2, ? super P3, ? super P4, ? super Composer, ? super Integer, Unit> function6) {
        final MovableContent movableContent = new MovableContent(ComposableLambdaKt.composableLambdaInstance(743481490, true, new Function3() { // from class: r7a
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MovableContentKt.n(function6, (Object[]) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
        return ComposableLambdaKt.composableLambdaInstance(-1304803723, true, new Function6() { // from class: s7a
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                return MovableContentKt.c(movableContent, obj, obj2, obj3, obj4, (Composer) obj5, ((Integer) obj6).intValue());
            }
        });
    }
}
