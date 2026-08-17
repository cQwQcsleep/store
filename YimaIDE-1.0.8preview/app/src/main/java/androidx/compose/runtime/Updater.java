package androidx.compose.runtime;

import androidx.compose.runtime.Updater;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006JH\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2.\b\b\u0010\r\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\u0002\b\u0011H\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013JI\u0010\t\u001a\u00020\n\"\u0004\b\u0001\u0010\u00142\u0006\u0010\u000b\u001a\u0002H\u00142,\u0010\r\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u0011H\u0014¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\u0002\b\u0011¢\u0006\u0004\b\u0012\u0010\u0015JH\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2.\b\b\u0010\r\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\u0002\b\u0011H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0013JI\u0010\u0016\u001a\u00020\n\"\u0004\b\u0001\u0010\u00142\u0006\u0010\u000b\u001a\u0002H\u00142,\u0010\r\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u0011H\u0014¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\u0002\b\u0011¢\u0006\u0004\b\u0017\u0010\u0015J&\u0010\u0018\u001a\u00020\n2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\u0019¢\u0006\u0002\b\u0011¢\u0006\u0004\b\u001a\u0010\u001bJ:\u0010\u0018\u001a\u00020\n\"\u0004\b\u0001\u0010\u00142\u0006\u0010\u000b\u001a\u0002H\u00142\u001d\u0010\r\u001a\u0019\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\n0\u000e¢\u0006\u0002\b\u0011¢\u0006\u0004\b\u001a\u0010\u0015J&\u0010\u001c\u001a\u00020\n2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\u0019¢\u0006\u0002\b\u0011¢\u0006\u0004\b\u001d\u0010\u001bJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010!\u001a\u00020\fHÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0016\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\b\u0088\u0001\u0003\u0092\u0001\u00020\u0004¨\u0006$"}, d2 = {"Landroidx/compose/runtime/Updater;", "T", "", "composer", "Landroidx/compose/runtime/Composer;", "constructor-impl", "(Landroidx/compose/runtime/Composer;)Landroidx/compose/runtime/Composer;", "getComposer$annotations", "()V", "set", "", "value", "", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lkotlin/ExtensionFunctionType;", "set-impl", "(Landroidx/compose/runtime/Composer;ILkotlin/jvm/functions/Function2;)V", "V", "(Landroidx/compose/runtime/Composer;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "update", "update-impl", "init", "Lkotlin/Function1;", "init-impl", "(Landroidx/compose/runtime/Composer;Lkotlin/jvm/functions/Function1;)V", "reconcile", "reconcile-impl", "equals", "", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class Updater<T> {
    private final Composer composer;

    private /* synthetic */ Updater(Composer composer) {
        this.composer = composer;
    }

    public static Unit a(Function1 function1, Object obj, Unit unit) {
        function1.invoke(obj);
        return Unit.INSTANCE;
    }

    public static Unit b(Function1 function1, Object obj, Unit unit) {
        function1.invoke(obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Updater m2387boximpl(Composer composer) {
        return new Updater(composer);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> Composer m2388constructorimpl(Composer composer) {
        return composer;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2389equalsimpl(Composer composer, Object obj) {
        return (obj instanceof Updater) && Intrinsics.areEqual(composer, ((Updater) obj).getComposer());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2390equalsimpl0(Composer composer, Composer composer2) {
        return Intrinsics.areEqual(composer, composer2);
    }

    public static /* synthetic */ void getComposer$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2391hashCodeimpl(Composer composer) {
        return composer.hashCode();
    }

    /* JADX INFO: renamed from: init-impl, reason: not valid java name */
    public static final void m2393initimpl(Composer composer, final Function1<? super T, Unit> function1) {
        if (composer.getInserting()) {
            composer.apply(Unit.INSTANCE, new Function2() { // from class: i1f
                public final Object invoke(Object obj, Object obj2) {
                    return Updater.b(function1, obj, (Unit) obj2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: reconcile-impl, reason: not valid java name */
    public static final void m2394reconcileimpl(Composer composer, final Function1<? super T, Unit> function1) {
        composer.apply(Unit.INSTANCE, new Function2() { // from class: j1f
            public final Object invoke(Object obj, Object obj2) {
                return Updater.a(function1, obj, (Unit) obj2);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Boxes more than than the generic overload")
    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final /* synthetic */ void m2395setimpl(Composer composer, int i, Function2<? super T, ? super Integer, Unit> function2) {
        if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(i))) {
            composer.updateRememberedValue(Integer.valueOf(i));
            composer.apply(Integer.valueOf(i), function2);
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2397toStringimpl(Composer composer) {
        return "Updater(composer=" + composer + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Boxes more than the generic overload")
    /* JADX INFO: renamed from: update-impl, reason: not valid java name */
    public static final /* synthetic */ void m2398updateimpl(Composer composer, int i, Function2<? super T, ? super Integer, Unit> function2) {
        boolean inserting = composer.getInserting();
        if (inserting || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(i))) {
            composer.updateRememberedValue(Integer.valueOf(i));
            if (inserting) {
                return;
            }
            composer.apply(Integer.valueOf(i), function2);
        }
    }

    public boolean equals(Object other) {
        return m2389equalsimpl(this.composer, other);
    }

    public int hashCode() {
        return m2391hashCodeimpl(this.composer);
    }

    public String toString() {
        return m2397toStringimpl(this.composer);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Composer getComposer() {
        return this.composer;
    }

    /* JADX INFO: renamed from: init-impl, reason: not valid java name */
    public static final <V> void m2392initimpl(Composer composer, V v, Function2<? super T, ? super V, Unit> function2) {
        if (composer.getInserting()) {
            composer.apply(v, function2);
        }
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final <V> void m2396setimpl(Composer composer, V v, Function2<? super T, ? super V, Unit> function2) {
        if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), v)) {
            composer.updateRememberedValue(v);
            composer.apply(v, function2);
        }
    }

    /* JADX INFO: renamed from: update-impl, reason: not valid java name */
    public static final <V> void m2399updateimpl(Composer composer, V v, Function2<? super T, ? super V, Unit> function2) {
        boolean inserting = composer.getInserting();
        if (inserting || !Intrinsics.areEqual(composer.rememberedValue(), v)) {
            composer.updateRememberedValue(v);
            if (inserting) {
                return;
            }
            composer.apply(v, function2);
        }
    }
}
