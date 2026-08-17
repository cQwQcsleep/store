package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000®\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B'\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ \u0010g\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ \u0010i\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ \u0010j\u001a\u00020k2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010lJ \u0010m\u001a\u00020k2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010lJ\u001d\u0010n\u001a\u00020\\2\u000e\u0010o\u001a\n\u0012\u0004\u0012\u00020q\u0018\u00010pH\u0000¢\u0006\u0002\brJ \u0010s\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010aJ(\u0010t\u001a\u00020k2\u0006\u0010u\u001a\u00020<2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010vJ \u0010w\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010aJ\b\u0010x\u001a\u00020\\H\u0002J\b\u0010y\u001a\u00020<H\u0002J\u0010\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}H\u0016J\u000e\u0010~\u001a\u00020\\2\u0006\u0010\u007f\u001a\u00020HJ\t\u0010\u0080\u0001\u001a\u00020\\H\u0002J\t\u0010\u0081\u0001\u001a\u00020\\H\u0002J\t\u0010\u0082\u0001\u001a\u00020\\H\u0002J!\u0010\u0083\u0001\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ\u000f\u0010\u0084\u0001\u001a\u00020\\H\u0000¢\u0006\u0003\b\u0085\u0001J\t\u0010\u0086\u0001\u001a\u00020\\H\u0016J\u0018\u0010\u0089\u0001\u001a\u00020\\2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(H\u0016J\u0018\u0010\u008b\u0001\u001a\u00020<2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(H\u0016J\u0018\u0010\u008c\u0001\u001a\u00020\\2\r\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0016J:\u0010\u008e\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130\u008f\u0001032\u0015\u0010\u0090\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0092\u0001\u0012\u0004\u0012\u00020<0\u0091\u0001H\u0080\b¢\u0006\u0003\b\u0093\u0001J\u001b\u0010\u0094\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u00132\u0007\u0010\u0096\u0001\u001a\u00020<H\u0002J!\u0010\u0094\u0001\u001a\u00020\\2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(2\u0007\u0010\u0096\u0001\u001a\u00020<H\u0002J\t\u0010\u0097\u0001\u001a\u00020\\H\u0002J\u0012\u0010\u0098\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0016J\u0012\u0010\u0099\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u009a\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0016J\t\u0010\u009b\u0001\u001a\u00020<H\u0016J)\u0010\u009c\u0001\u001a\u00020\\2\u001e\u0010\u009d\u0001\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u009e\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u009e\u00010\u008f\u000103H\u0016J\u0012\u0010\u009f\u0001\u001a\u00020\\2\u0007\u0010W\u001a\u00030 \u0001H\u0016J\u0011\u0010¡\u0001\u001a\u00020\\2\u0006\u00106\u001a\u000207H\u0002J\t\u0010¢\u0001\u001a\u00020\\H\u0016J\t\u0010£\u0001\u001a\u00020\\H\u0016J\t\u0010¤\u0001\u001a\u00020\\H\u0016JL\u0010¥\u0001\u001a\u0003H¦\u0001\"\u0005\b\u0000\u0010¦\u000122\u0010\u008d\u0001\u001a-\u0012!\u0012\u001f\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$¢\u0006\u000e\b§\u0001\u0012\t\b¨\u0001\u0012\u0004\b\b(6\u0012\u0005\u0012\u0003H¦\u00010\u0091\u0001H\u0082\b¢\u0006\u0003\u0010©\u0001J(\u0010ª\u0001\u001a\u0003H¦\u0001\"\u0005\b\u0000\u0010¦\u00012\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H¦\u00010[H\u0082\b¢\u0006\u0003\u0010«\u0001J\t\u0010¬\u0001\u001a\u00020\\H\u0016J\t\u0010\u00ad\u0001\u001a\u00020\\H\u0016J\t\u0010®\u0001\u001a\u00020\\H\u0016J;\u0010¯\u0001\u001a\u0003H°\u0001\"\u0005\b\u0000\u0010°\u00012\t\u0010±\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010²\u0001\u001a\u00020H2\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H°\u00010[H\u0016¢\u0006\u0003\u0010³\u0001J\u0015\u0010´\u0001\u001a\u0004\u0018\u00010C2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u001e\u0010µ\u0001\u001a\u00030¶\u00012\u0007\u0010·\u0001\u001a\u00020%2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010¹\u0001\u001a\u00020\\2\u0007\u0010·\u0001\u001a\u00020%H\u0016J)\u0010º\u0001\u001a\u0005\u0018\u0001H¦\u0001\"\u0005\b\u0000\u0010¦\u00012\u000e\u0010\u007f\u001a\n\u0012\u0005\u0012\u0003H¦\u00010»\u0001H\u0016¢\u0006\u0003\u0010¼\u0001J\u001d\u0010½\u0001\u001a\u00020<2\u0007\u0010·\u0001\u001a\u00020%2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0002J(\u0010¾\u0001\u001a\u00030¶\u00012\u0007\u0010·\u0001\u001a\u00020%2\b\u0010¿\u0001\u001a\u00030\u0092\u00012\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0002J!\u0010À\u0001\u001a\u00020\\2\u0007\u0010¸\u0001\u001a\u00020\u00132\u0007\u0010·\u0001\u001a\u00020%H\u0000¢\u0006\u0003\bÁ\u0001J\u001b\u0010Â\u0001\u001a\u00020\\2\n\u0010W\u001a\u0006\u0012\u0002\b\u00030/H\u0000¢\u0006\u0003\bÃ\u0001J\u001e\u0010Ä\u0001\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$H\u0002¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001J\u0011\u0010Ç\u0001\u001a\u00020\\2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J(\u0010È\u0001\u001a\u0003H¦\u0001\"\u0005\b\u0000\u0010¦\u00012\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H¦\u00010[H\u0082\b¢\u0006\u0003\u0010«\u0001J\n\u0010|\u001a\u0004\u0018\u00010}H\u0002J\t\u0010É\u0001\u001a\u00020\\H\u0016J\u000f\u0010Ê\u0001\u001a\u00020HH\u0000¢\u0006\u0003\bË\u0001R\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0013`\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u0016\u001a\u00060\u0013j\u0002`\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130(8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020%0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020%0,X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030/0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\u00130(8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b1\u0010*R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020%038AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001c\u0010:\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R \u0010;\u001a\u00020<X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b=\u0010\u001d\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020JX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u000e\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\u00020PX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0010\u0010S\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0011\u0010V\u001a\u00020<¢\u0006\b\n\u0000\u001a\u0004\bV\u0010?R\u000e\u0010W\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020<8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bY\u0010?R'\u0010Z\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]X\u0086\u000e¢\u0006\u0010\n\u0002\u0010b\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010?R\u0014\u0010d\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010?R\u0014\u0010e\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bf\u0010?R\u0016\u0010\u0087\u0001\u001a\u00020<8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010?¨\u0006Ì\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "Landroidx/compose/runtime/PausableComposition;", "Landroidx/compose/runtime/tooling/ObservableComposition;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "getParent", "()Landroidx/compose/runtime/CompositionContext;", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "getAbandonSet$annotations", "()V", "slotTable", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime$annotations", "getSlotTable$runtime", "()Landroidx/compose/runtime/SlotTable;", "observations", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/collection/MutableScatterMap;", "observedObjects", "", "getObservedObjects$runtime", "()Ljava/util/Set;", "invalidatedScopes", "Landroidx/collection/MutableScatterSet;", "conditionallyInvalidatedScopes", "derivedStates", "Landroidx/compose/runtime/DerivedState;", "derivedStateDependencies", "getDerivedStateDependencies$runtime", "conditionalScopes", "", "getConditionalScopes$runtime", "()Ljava/util/List;", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "observationsProcessed", "invalidations", "pendingInvalidScopes", "", "getPendingInvalidScopes$runtime$annotations", "getPendingInvalidScopes$runtime", "()Z", "setPendingInvalidScopes$runtime", "(Z)V", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "pendingPausedComposition", "Landroidx/compose/runtime/PausedCompositionImpl;", "invalidationDelegate", "invalidationDelegateGroup", "", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "rememberManager", "Landroidx/compose/runtime/internal/RememberEventDispatcher;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "getComposer$runtime", "()Landroidx/compose/runtime/ComposerImpl;", "_recomposeContext", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "isRoot", "state", "areChildrenComposing", "getAreChildrenComposing", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "isComposing", "isDisposed", "hasPendingChanges", "getHasPendingChanges", "setContent", "content", "setContentWithReuse", "setPausableContent", "Landroidx/compose/runtime/PausedComposition;", "(Lkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "setPausableContentWithReuse", "pausedCompositionFinished", "ignoreSet", "Landroidx/collection/ScatterSet;", "Landroidx/compose/runtime/RememberObserverHolder;", "pausedCompositionFinished$runtime", "composeInitial", "composeInitialPaused", "reusable", "(ZLkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "composeInitialWithReuse", "ensureRunning", "clearDeactivated", "setObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "invalidateGroupsWithKey", "key", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "drainPendingModificationsOutOfBandLocked", "composeContent", "updateMovingInvalidations", "updateMovingInvalidations$runtime", "dispose", "hasInvalidations", "getHasInvalidations", "recordModificationsOf", "values", "observesAnyOf", "prepareCompose", "block", "extractInvalidationsOfGroup", "Lkotlin/Pair;", "inGroup", "Lkotlin/Function1;", "Landroidx/compose/runtime/Anchor;", "extractInvalidationsOfGroup$runtime", "addPendingInvalidationsLocked", "value", "forgetConditionalScopes", "cleanUpDerivedStateObservations", "recordReadOf", "invalidateScopeOfLocked", "recordWriteOf", "recompose", "insertMovableContent", "references", "Landroidx/compose/runtime/MovableContentStateReference;", "disposeUnusedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "applyChangesInLocked", "applyChanges", "applyLateChanges", "changesApplied", "guardInvalidationsLocked", "T", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "guardChanges", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "abandonChanges", "invalidateAll", "verifyConsistent", "delegateInvalidations", "R", "to", "groupIndex", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAndSetShouldPauseCallback", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "recomposeScopeReleased", "getCompositionService", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "tryImminentInvalidation", "invalidateChecked", "anchor", "removeObservation", "removeObservation$runtime", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime", "takeInvalidations", "takeInvalidations-afanTW4", "()Landroidx/collection/MutableScatterMap;", "validateRecomposeScopeAnchors", "trackAbandonedValues", "deactivate", "composerStacksSizes", "composerStacksSizes$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices, PausableComposition, ObservableComposition {
    public static final int $stable = 8;
    private final CoroutineContext _recomposeContext;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ChangeList changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final MutableScatterSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final MutableScatterMap<Object, Object> derivedStates;
    private final MutableScatterSet<RecomposeScopeImpl> invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private MutableScatterMap<Object, Object> invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final MutableScatterMap<Object, Object> observations;
    private final MutableScatterMap<Object, Object> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private PausedCompositionImpl pendingPausedComposition;
    private final RememberEventDispatcher rememberManager;
    private ShouldPauseCallback shouldPause;
    private final SlotTable slotTable;
    private int state;

    public CompositionImpl(CompositionContext compositionContext, Applier<?> applier, CoroutineContext coroutineContext) {
        this.parent = compositionContext;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        Set<RememberObserver> setAsMutableSet = new MutableScatterSet(0, 1, (DefaultConstructorMarker) null).asMutableSet();
        this.abandonSet = setAsMutableSet;
        SlotTable slotTable = new SlotTable();
        if (compositionContext.getCollectingCallByInformation$runtime()) {
            slotTable.collectCalledByInformation();
        }
        if (compositionContext.getCollectingSourceInformation()) {
            slotTable.collectSourceInformation();
        }
        this.slotTable = slotTable;
        this.observations = ScopeMap.m2490constructorimpl$default(null, 1, null);
        this.invalidatedScopes = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
        this.conditionallyInvalidatedScopes = new MutableScatterSet<>(0, 1, (DefaultConstructorMarker) null);
        this.derivedStates = ScopeMap.m2490constructorimpl$default(null, 1, null);
        ChangeList changeList = new ChangeList();
        this.changes = changeList;
        ChangeList changeList2 = new ChangeList();
        this.lateChanges = changeList2;
        this.observationsProcessed = ScopeMap.m2490constructorimpl$default(null, 1, null);
        this.invalidations = ScopeMap.m2490constructorimpl$default(null, 1, null);
        CompositionObserverHolder compositionObserverHolder = new CompositionObserverHolder(null, false, compositionContext, 3, null);
        this.observerHolder = compositionObserverHolder;
        this.rememberManager = new RememberEventDispatcher();
        ComposerImpl composerImpl = new ComposerImpl(applier, compositionContext, slotTable, setAsMutableSet, changeList, changeList2, compositionObserverHolder, this);
        compositionContext.registerComposer$runtime(composerImpl);
        this.composer = composerImpl;
        this._recomposeContext = coroutineContext;
        this.isRoot = compositionContext instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$954879418$runtime();
    }

    /* JADX WARN: Code duplicated, block: B:190:0x00c8 A[EDGE_INSN: B:190:0x00c8->B:37:0x00c8 BREAK  A[LOOP:2: B:23:0x0077->B:34:0x00b3], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:213:0x0187 A[EDGE_INSN: B:213:0x0187->B:74:0x0187 BREAK  A[LOOP:13: B:61:0x014b->B:72:0x017f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x00b1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x00b3 A[LOOP:2: B:23:0x0077->B:34:0x00b3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:71:0x017d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x017f A[LOOP:13: B:61:0x014b->B:72:0x017f, LOOP_END] */
    private final void addPendingInvalidationsLocked(Set<? extends Object> values, boolean forgetConditionalScopes) {
        char c;
        long j;
        long j2;
        long j3;
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        int i3;
        long j4;
        boolean zContains;
        long[] jArr3;
        long[] jArr4;
        long[] jArr5;
        long j5;
        boolean zIsEmpty;
        int i4;
        long j6;
        long j7;
        char c2;
        long j8;
        int i5;
        int i6;
        Object obj = null;
        char c3 = 7;
        long j9 = -9187201950435737472L;
        int i7 = 8;
        if (values instanceof ScatterSetWrapper) {
            ScatterSet set$runtime = ((ScatterSetWrapper) values).getSet$runtime();
            Object[] objArr = set$runtime.elements;
            long[] jArr6 = set$runtime.metadata;
            int length = jArr6.length - 2;
            if (length >= 0) {
                int i8 = 0;
                j2 = 128;
                while (true) {
                    long j10 = jArr6[i8];
                    j3 = 255;
                    if ((((~j10) << c3) & j10 & j9) != j9) {
                        int i9 = 8 - ((~(i8 - length)) >>> 31);
                        int i10 = 0;
                        while (i10 < i9) {
                            if ((j10 & 255) < 128) {
                                c2 = c3;
                                Object obj2 = objArr[(i8 << 3) + i10];
                                j8 = j9;
                                if (obj2 instanceof RecomposeScopeImpl) {
                                    ((RecomposeScopeImpl) obj2).invalidateForResult(obj);
                                    j7 = j10;
                                    i5 = length;
                                } else {
                                    addPendingInvalidationsLocked(obj2, forgetConditionalScopes);
                                    Object obj3 = this.derivedStates.get(obj2);
                                    if (obj3 == null) {
                                        j7 = j10;
                                        i5 = length;
                                    } else if (obj3 instanceof MutableScatterSet) {
                                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj3;
                                        Object[] objArr2 = ((ScatterSet) mutableScatterSet).elements;
                                        long[] jArr7 = ((ScatterSet) mutableScatterSet).metadata;
                                        int length2 = jArr7.length - 2;
                                        if (length2 >= 0) {
                                            j7 = j10;
                                            int i11 = 0;
                                            while (true) {
                                                long j11 = jArr7[i11];
                                                int i12 = i7;
                                                i5 = length;
                                                if ((((~j11) << c2) & j11 & j8) != j8) {
                                                    int i13 = 8 - ((~(i11 - length2)) >>> 31);
                                                    int i14 = 0;
                                                    while (i14 < i13) {
                                                        if ((j11 & 255) < 128) {
                                                            addPendingInvalidationsLocked((DerivedState) objArr2[(i11 << 3) + i14], forgetConditionalScopes);
                                                        }
                                                        j11 >>= i12;
                                                        i14++;
                                                        i12 = i12;
                                                    }
                                                    if (i13 != i12) {
                                                        break;
                                                    }
                                                    if (i11 != length2) {
                                                        break;
                                                    }
                                                    i11++;
                                                    length = i5;
                                                    i7 = 8;
                                                } else if (i11 != length2) {
                                                    break;
                                                    break;
                                                } else {
                                                    i11++;
                                                    length = i5;
                                                    i7 = 8;
                                                }
                                            }
                                        } else {
                                            j7 = j10;
                                            i5 = length;
                                        }
                                    } else {
                                        j7 = j10;
                                        i5 = length;
                                        addPendingInvalidationsLocked((DerivedState) obj3, forgetConditionalScopes);
                                    }
                                    Unit unit = Unit.INSTANCE;
                                }
                                i6 = 8;
                            } else {
                                j7 = j10;
                                c2 = c3;
                                j8 = j9;
                                i5 = length;
                                i6 = i7;
                            }
                            i10++;
                            length = i5;
                            i7 = i6;
                            c3 = c2;
                            j9 = j8;
                            j10 = j7 >> i6;
                            obj = null;
                        }
                        c = c3;
                        j = j9;
                        int i15 = length;
                        if (i9 != i7) {
                            break;
                        } else {
                            length = i15;
                        }
                    } else {
                        c = c3;
                        j = j9;
                    }
                    if (i8 == length) {
                        break;
                    }
                    i8++;
                    c3 = c;
                    j9 = j;
                    obj = null;
                    i7 = 8;
                }
            } else {
                c = 7;
                j = -9187201950435737472L;
                j2 = 128;
                j3 = 255;
            }
        } else {
            c = 7;
            j = -9187201950435737472L;
            j2 = 128;
            j3 = 255;
            for (Object obj4 : values) {
                if (obj4 instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj4).invalidateForResult(null);
                } else {
                    addPendingInvalidationsLocked(obj4, forgetConditionalScopes);
                    Object obj5 = this.derivedStates.get(obj4);
                    if (obj5 != null) {
                        if (obj5 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet2 = (MutableScatterSet) obj5;
                            Object[] objArr3 = ((ScatterSet) mutableScatterSet2).elements;
                            long[] jArr8 = ((ScatterSet) mutableScatterSet2).metadata;
                            int length3 = jArr8.length - 2;
                            if (length3 >= 0) {
                                int i16 = 0;
                                while (true) {
                                    long j12 = jArr8[i16];
                                    if ((((~j12) << 7) & j12 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i16 != length3) {
                                            break;
                                            break;
                                        }
                                        i16++;
                                    } else {
                                        int i17 = 8 - ((~(i16 - length3)) >>> 31);
                                        for (int i18 = 0; i18 < i17; i18++) {
                                            if ((j12 & 255) < 128) {
                                                addPendingInvalidationsLocked((DerivedState) objArr3[(i16 << 3) + i18], forgetConditionalScopes);
                                            }
                                            j12 >>= 8;
                                        }
                                        if (i17 != 8) {
                                            break;
                                        } else if (i16 != length3) {
                                            break;
                                        } else {
                                            i16++;
                                        }
                                    }
                                }
                            }
                        } else {
                            addPendingInvalidationsLocked((DerivedState) obj5, forgetConditionalScopes);
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet3 = this.conditionallyInvalidatedScopes;
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet4 = this.invalidatedScopes;
        if (forgetConditionalScopes && mutableScatterSet3.isNotEmpty()) {
            MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
            long[] jArr9 = ((ScatterMap) mutableScatterMap).metadata;
            int length4 = jArr9.length - 2;
            if (length4 >= 0) {
                int i19 = 0;
                while (true) {
                    long j13 = jArr9[i19];
                    if ((((~j13) << c) & j13 & j) != j) {
                        int i20 = 8 - ((~(i19 - length4)) >>> 31);
                        int i21 = 0;
                        while (i21 < i20) {
                            if ((j13 & j3) < j2) {
                                int i22 = (i19 << 3) + i21;
                                Object obj6 = ((ScatterMap) mutableScatterMap).keys[i22];
                                Object obj7 = ((ScatterMap) mutableScatterMap).values[i22];
                                if (obj7 instanceof MutableScatterSet) {
                                    MutableScatterSet mutableScatterSet5 = (MutableScatterSet) obj7;
                                    Object[] objArr4 = ((ScatterSet) mutableScatterSet5).elements;
                                    long[] jArr10 = ((ScatterSet) mutableScatterSet5).metadata;
                                    int length5 = jArr10.length - 2;
                                    jArr5 = jArr9;
                                    j5 = j13;
                                    if (length5 >= 0) {
                                        int i23 = 0;
                                        while (true) {
                                            long j14 = jArr10[i23];
                                            Object[] objArr5 = objArr4;
                                            long[] jArr11 = jArr10;
                                            if ((((~j14) << c) & j14 & j) != j) {
                                                int i24 = 8 - ((~(i23 - length5)) >>> 31);
                                                for (int i25 = 0; i25 < i24; i25 = i4 + 1) {
                                                    if ((j14 & j3) < j2) {
                                                        i4 = i25;
                                                        int i26 = (i23 << 3) + i4;
                                                        j6 = j14;
                                                        RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr5[i26];
                                                        if (mutableScatterSet3.contains(recomposeScopeImpl) || mutableScatterSet4.contains(recomposeScopeImpl)) {
                                                            mutableScatterSet5.removeElementAt(i26);
                                                        }
                                                    } else {
                                                        i4 = i25;
                                                        j6 = j14;
                                                    }
                                                    j14 = j6 >> 8;
                                                }
                                                if (i24 != 8) {
                                                    break;
                                                }
                                            }
                                            if (i23 == length5) {
                                                break;
                                            }
                                            i23++;
                                            length5 = length5;
                                            objArr4 = objArr5;
                                            jArr10 = jArr11;
                                        }
                                    }
                                    zIsEmpty = mutableScatterSet5.isEmpty();
                                } else {
                                    jArr5 = jArr9;
                                    j5 = j13;
                                    obj7.getClass();
                                    RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj7;
                                    zIsEmpty = mutableScatterSet3.contains(recomposeScopeImpl2) || mutableScatterSet4.contains(recomposeScopeImpl2);
                                }
                                if (zIsEmpty) {
                                    mutableScatterMap.removeValueAt(i22);
                                }
                            } else {
                                jArr5 = jArr9;
                                j5 = j13;
                            }
                            j13 = j5 >> 8;
                            i21++;
                            jArr9 = jArr5;
                        }
                        jArr4 = jArr9;
                        if (i20 != 8) {
                            break;
                        }
                    } else {
                        jArr4 = jArr9;
                    }
                    if (i19 == length4) {
                        break;
                    }
                    i19++;
                    jArr9 = jArr4;
                }
            }
            mutableScatterSet3.clear();
            cleanUpDerivedStateObservations();
            return;
        }
        if (mutableScatterSet4.isNotEmpty()) {
            MutableScatterMap<Object, Object> mutableScatterMap2 = this.observations;
            long[] jArr12 = ((ScatterMap) mutableScatterMap2).metadata;
            int length6 = jArr12.length - 2;
            if (length6 >= 0) {
                int i27 = 0;
                while (true) {
                    long j15 = jArr12[i27];
                    if ((((~j15) << c) & j15 & j) != j) {
                        int i28 = 8 - ((~(i27 - length6)) >>> 31);
                        int i29 = 0;
                        while (i29 < i28) {
                            if ((j15 & j3) < j2) {
                                int i30 = (i27 << 3) + i29;
                                Object obj8 = ((ScatterMap) mutableScatterMap2).keys[i30];
                                Object obj9 = ((ScatterMap) mutableScatterMap2).values[i30];
                                if (obj9 instanceof MutableScatterSet) {
                                    MutableScatterSet mutableScatterSet6 = (MutableScatterSet) obj9;
                                    Object[] objArr6 = ((ScatterSet) mutableScatterSet6).elements;
                                    long[] jArr13 = ((ScatterSet) mutableScatterSet6).metadata;
                                    int length7 = jArr13.length - 2;
                                    if (length7 >= 0) {
                                        j4 = j15;
                                        int i31 = 0;
                                        while (true) {
                                            long j16 = jArr13[i31];
                                            i2 = length6;
                                            i3 = i27;
                                            if ((((~j16) << c) & j16 & j) != j) {
                                                int i32 = 8 - ((~(i31 - length7)) >>> 31);
                                                int i33 = 0;
                                                while (i33 < i32) {
                                                    if ((j16 & j3) < j2) {
                                                        int i34 = (i31 << 3) + i33;
                                                        jArr3 = jArr12;
                                                        if (mutableScatterSet4.contains((RecomposeScopeImpl) objArr6[i34])) {
                                                            mutableScatterSet6.removeElementAt(i34);
                                                        }
                                                    } else {
                                                        jArr3 = jArr12;
                                                    }
                                                    j16 >>= 8;
                                                    i33++;
                                                    jArr12 = jArr3;
                                                }
                                                jArr2 = jArr12;
                                                if (i32 != 8) {
                                                    break;
                                                }
                                            } else {
                                                jArr2 = jArr12;
                                            }
                                            if (i31 == length7) {
                                                break;
                                            }
                                            i31++;
                                            length6 = i2;
                                            i27 = i3;
                                            jArr12 = jArr2;
                                        }
                                    } else {
                                        jArr2 = jArr12;
                                        i2 = length6;
                                        i3 = i27;
                                        j4 = j15;
                                    }
                                    zContains = mutableScatterSet6.isEmpty();
                                } else {
                                    jArr2 = jArr12;
                                    i2 = length6;
                                    i3 = i27;
                                    j4 = j15;
                                    obj9.getClass();
                                    zContains = mutableScatterSet4.contains((RecomposeScopeImpl) obj9);
                                }
                                if (zContains) {
                                    mutableScatterMap2.removeValueAt(i30);
                                }
                            } else {
                                jArr2 = jArr12;
                                i2 = length6;
                                i3 = i27;
                                j4 = j15;
                            }
                            j15 = j4 >> 8;
                            i29++;
                            length6 = i2;
                            i27 = i3;
                            jArr12 = jArr2;
                        }
                        jArr = jArr12;
                        int i35 = length6;
                        int i36 = i27;
                        if (i28 != 8) {
                            break;
                        }
                        length6 = i35;
                        i = i36;
                    } else {
                        jArr = jArr12;
                        i = i27;
                    }
                    if (i == length6) {
                        break;
                    }
                    i27 = i + 1;
                    jArr12 = jArr;
                }
            }
            cleanUpDerivedStateObservations();
            mutableScatterSet4.clear();
        }
    }

    private final void applyChangesInLocked(ChangeList changes) {
        Applier<?> pausableApplier$runtime;
        RememberEventDispatcher rememberManager$runtime;
        long[] jArr;
        long[] jArr2;
        long j;
        char c;
        long j2;
        int i;
        boolean zIsEmpty;
        long[] jArr3;
        this.rememberManager.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
        try {
            if (changes.isEmpty()) {
                try {
                    if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                        this.rememberManager.dispatchAbandons();
                    }
                } finally {
                    this.rememberManager.clear();
                }
            } else {
                PausedCompositionImpl pausedCompositionImpl = this.pendingPausedComposition;
                if (pausedCompositionImpl == null || (pausableApplier$runtime = pausedCompositionImpl.getPausableApplier$runtime()) == null) {
                    pausableApplier$runtime = this.applier;
                }
                PausedCompositionImpl pausedCompositionImpl2 = this.pendingPausedComposition;
                String str = Intrinsics.areEqual(pausableApplier$runtime, pausedCompositionImpl2 != null ? pausedCompositionImpl2.getPausableApplier$runtime() : null) ? "Compose:recordChanges" : "Compose:applyChanges";
                Trace trace = Trace.INSTANCE;
                Object objBeginSection = trace.beginSection(str);
                try {
                    PausedCompositionImpl pausedCompositionImpl3 = this.pendingPausedComposition;
                    if (pausedCompositionImpl3 == null || (rememberManager$runtime = pausedCompositionImpl3.getRememberManager()) == null) {
                        rememberManager$runtime = this.rememberManager;
                    }
                    pausableApplier$runtime.onBeginChanges();
                    SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                    int i2 = 0;
                    try {
                        changes.executeAndFlushAllPendingChanges(pausableApplier$runtime, slotWriterOpenWriter, rememberManager$runtime, this.composer.getErrorContext$runtime());
                        Unit unit = Unit.INSTANCE;
                        slotWriterOpenWriter.close(true);
                        pausableApplier$runtime.onEndChanges();
                        trace.endSection(objBeginSection);
                        this.rememberManager.dispatchRememberObservers();
                        this.rememberManager.dispatchSideEffects();
                        if (this.pendingInvalidScopes) {
                            Object objBeginSection2 = trace.beginSection("Compose:unobserve");
                            try {
                                this.pendingInvalidScopes = false;
                                MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
                                long[] jArr4 = ((ScatterMap) mutableScatterMap).metadata;
                                int length = jArr4.length - 2;
                                if (length >= 0) {
                                    int i3 = 0;
                                    while (true) {
                                        long j3 = jArr4[i3];
                                        char c2 = 7;
                                        long j4 = -9187201950435737472L;
                                        if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i4 = 8;
                                            int i5 = 8 - ((~(i3 - length)) >>> 31);
                                            int i6 = i2;
                                            while (i6 < i5) {
                                                if ((j3 & 255) < 128) {
                                                    int i7 = (i3 << 3) + i6;
                                                    c = c2;
                                                    Object obj = ((ScatterMap) mutableScatterMap).keys[i7];
                                                    Object obj2 = ((ScatterMap) mutableScatterMap).values[i7];
                                                    j2 = j4;
                                                    if (obj2 instanceof MutableScatterSet) {
                                                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                                        Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                                                        long[] jArr5 = ((ScatterSet) mutableScatterSet).metadata;
                                                        int length2 = jArr5.length - 2;
                                                        if (length2 >= 0) {
                                                            j = j3;
                                                            int i8 = i4;
                                                            int i9 = 0;
                                                            while (true) {
                                                                long j5 = jArr5[i9];
                                                                Object[] objArr2 = objArr;
                                                                long[] jArr6 = jArr5;
                                                                if ((((~j5) << c) & j5 & j2) != j2) {
                                                                    int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                                                    int i11 = 0;
                                                                    while (i11 < i10) {
                                                                        if ((j5 & 255) < 128) {
                                                                            jArr3 = jArr4;
                                                                            int i12 = (i9 << 3) + i11;
                                                                            if (!((RecomposeScopeImpl) objArr2[i12]).getValid()) {
                                                                                mutableScatterSet.removeElementAt(i12);
                                                                            }
                                                                        } else {
                                                                            jArr3 = jArr4;
                                                                        }
                                                                        j5 >>= i8;
                                                                        i11++;
                                                                        jArr4 = jArr3;
                                                                    }
                                                                    jArr2 = jArr4;
                                                                    if (i10 != i8) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    jArr2 = jArr4;
                                                                }
                                                                if (i9 == length2) {
                                                                    break;
                                                                }
                                                                i9++;
                                                                objArr = objArr2;
                                                                jArr5 = jArr6;
                                                                jArr4 = jArr2;
                                                                i8 = 8;
                                                            }
                                                        } else {
                                                            jArr2 = jArr4;
                                                            j = j3;
                                                        }
                                                        zIsEmpty = mutableScatterSet.isEmpty();
                                                    } else {
                                                        jArr2 = jArr4;
                                                        j = j3;
                                                        obj2.getClass();
                                                        zIsEmpty = !((RecomposeScopeImpl) obj2).getValid();
                                                    }
                                                    if (zIsEmpty) {
                                                        mutableScatterMap.removeValueAt(i7);
                                                    }
                                                    i = 8;
                                                } else {
                                                    jArr2 = jArr4;
                                                    j = j3;
                                                    c = c2;
                                                    j2 = j4;
                                                    i = i4;
                                                }
                                                j3 = j >> i;
                                                i6++;
                                                i4 = i;
                                                c2 = c;
                                                j4 = j2;
                                                jArr4 = jArr2;
                                            }
                                            jArr = jArr4;
                                            if (i5 != i4) {
                                                break;
                                            }
                                        } else {
                                            jArr = jArr4;
                                        }
                                        if (i3 == length) {
                                            break;
                                        }
                                        i3++;
                                        jArr4 = jArr;
                                        i2 = 0;
                                    }
                                }
                                cleanUpDerivedStateObservations();
                                Unit unit2 = Unit.INSTANCE;
                                Trace.INSTANCE.endSection(objBeginSection2);
                            } catch (Throwable th) {
                                Trace.INSTANCE.endSection(objBeginSection2);
                                throw th;
                            }
                        }
                        try {
                            if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                                this.rememberManager.dispatchAbandons();
                            }
                        } finally {
                            this.rememberManager.clear();
                        }
                    } catch (Throwable th2) {
                        slotWriterOpenWriter.close(false);
                        throw th2;
                    }
                } catch (Throwable th3) {
                    Trace.INSTANCE.endSection(objBeginSection);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            try {
                if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                    this.rememberManager.dispatchAbandons();
                }
                throw th4;
            } finally {
                this.rememberManager.clear();
            }
        }
    }

    private final void cleanUpDerivedStateObservations() {
        long j;
        char c;
        long j2;
        long j3;
        long[] jArr;
        long[] jArr2;
        int i;
        long j4;
        char c2;
        long j5;
        int i2;
        boolean zIsEmpty;
        long[] jArr3;
        int i3;
        int i4;
        MutableScatterMap<Object, Object> mutableScatterMap = this.derivedStates;
        long[] jArr4 = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr4.length - 2;
        long j6 = 255;
        char c3 = 7;
        long j7 = -9187201950435737472L;
        int i5 = 8;
        if (length >= 0) {
            int i6 = 0;
            while (true) {
                long j8 = jArr4[i6];
                j3 = 128;
                if ((((~j8) << c3) & j8 & j7) != j7) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j8 & j6) < 128) {
                            j4 = j6;
                            int i9 = (i6 << 3) + i8;
                            Object obj = ((ScatterMap) mutableScatterMap).keys[i9];
                            Object obj2 = ((ScatterMap) mutableScatterMap).values[i9];
                            c2 = c3;
                            if (obj2 instanceof MutableScatterSet) {
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                                j5 = j7;
                                long[] jArr5 = ((ScatterSet) mutableScatterSet).metadata;
                                int length2 = jArr5.length - 2;
                                if (length2 >= 0) {
                                    int i10 = i5;
                                    int i11 = 0;
                                    while (true) {
                                        long j9 = jArr5[i11];
                                        Object[] objArr2 = objArr;
                                        long[] jArr6 = jArr5;
                                        if ((((~j9) << c2) & j9 & j5) != j5) {
                                            int i12 = 8 - ((~(i11 - length2)) >>> 31);
                                            int i13 = 0;
                                            while (i13 < i12) {
                                                if ((j9 & j4) < 128) {
                                                    jArr3 = jArr4;
                                                    int i14 = (i11 << 3) + i13;
                                                    i3 = i8;
                                                    i4 = i13;
                                                    if (!ScopeMap.m2491containsimpl(this.observations, (DerivedState) objArr2[i14])) {
                                                        mutableScatterSet.removeElementAt(i14);
                                                    }
                                                } else {
                                                    jArr3 = jArr4;
                                                    i3 = i8;
                                                    i4 = i13;
                                                }
                                                j9 >>= i10;
                                                i13 = i4 + 1;
                                                jArr4 = jArr3;
                                                i8 = i3;
                                            }
                                            jArr2 = jArr4;
                                            i = i8;
                                            if (i12 != i10) {
                                                break;
                                            }
                                        } else {
                                            jArr2 = jArr4;
                                            i = i8;
                                        }
                                        int i15 = i11;
                                        if (i15 == length2) {
                                            break;
                                        }
                                        i11 = i15 + 1;
                                        objArr = objArr2;
                                        jArr5 = jArr6;
                                        jArr4 = jArr2;
                                        i8 = i;
                                        i10 = 8;
                                    }
                                } else {
                                    jArr2 = jArr4;
                                    i = i8;
                                }
                                zIsEmpty = mutableScatterSet.isEmpty();
                            } else {
                                jArr2 = jArr4;
                                i = i8;
                                j5 = j7;
                                obj2.getClass();
                                zIsEmpty = !ScopeMap.m2491containsimpl(this.observations, (DerivedState) obj2);
                            }
                            if (zIsEmpty) {
                                mutableScatterMap.removeValueAt(i9);
                            }
                            i2 = 8;
                        } else {
                            jArr2 = jArr4;
                            i = i8;
                            j4 = j6;
                            c2 = c3;
                            j5 = j7;
                            i2 = i5;
                        }
                        j8 >>= i2;
                        i8 = i + 1;
                        i5 = i2;
                        c3 = c2;
                        j6 = j4;
                        j7 = j5;
                        jArr4 = jArr2;
                    }
                    jArr = jArr4;
                    j = j6;
                    c = c3;
                    j2 = j7;
                    if (i7 != i5) {
                        break;
                    }
                } else {
                    jArr = jArr4;
                    j = j6;
                    c = c3;
                    j2 = j7;
                }
                if (i6 == length) {
                    break;
                }
                i6++;
                c3 = c;
                j6 = j;
                j7 = j2;
                jArr4 = jArr;
                i5 = 8;
            }
        } else {
            j = 255;
            c = 7;
            j2 = -9187201950435737472L;
            j3 = 128;
        }
        if (!this.conditionallyInvalidatedScopes.isNotEmpty()) {
            return;
        }
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet2 = this.conditionallyInvalidatedScopes;
        Object[] objArr3 = ((ScatterSet) mutableScatterSet2).elements;
        long[] jArr7 = ((ScatterSet) mutableScatterSet2).metadata;
        int length3 = jArr7.length - 2;
        if (length3 < 0) {
            return;
        }
        int i16 = 0;
        while (true) {
            long j10 = jArr7[i16];
            if ((((~j10) << c) & j10 & j2) != j2) {
                int i17 = 8 - ((~(i16 - length3)) >>> 31);
                for (int i18 = 0; i18 < i17; i18++) {
                    if ((j10 & j) < j3) {
                        int i19 = (i16 << 3) + i18;
                        if (!((RecomposeScopeImpl) objArr3[i19]).isConditional()) {
                            mutableScatterSet2.removeElementAt(i19);
                        }
                    }
                    j10 >>= 8;
                }
                if (i17 != 8) {
                    return;
                }
            }
            if (i16 == length3) {
                return;
            } else {
                i16++;
            }
        }
    }

    private final boolean clearDeactivated() {
        boolean z;
        synchronized (this.lock) {
            z = true;
            if (this.state != 1) {
                z = false;
            }
            if (z) {
                this.state = 0;
            }
        }
        return z;
    }

    private final void composeInitial(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composable = content;
        this.parent.composeInitial$runtime(this, content);
    }

    private final PausedComposition composeInitialPaused(boolean reusable, Function2<? super Composer, ? super Integer, Unit> content) {
        if (!(this.pendingPausedComposition == null)) {
            PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
        }
        PausedCompositionImpl pausedCompositionImpl = new PausedCompositionImpl(this, this.parent, this.composer, this.abandonSet, content, reusable, this.applier, this.lock);
        this.pendingPausedComposition = pausedCompositionImpl;
        return pausedCompositionImpl;
    }

    private final void composeInitialWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composer.startReuseFromRoot();
        composeInitial(content);
        this.composer.endReuseFromRoot();
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object andSet = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (andSet != null) {
            if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                wq6.a();
                return;
            }
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                wq6.a();
                return;
            }
            for (Set<? extends Object> set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, true);
            }
        }
    }

    private final void drainPendingModificationsLocked() {
        Object andSet = this.pendingModifications.getAndSet(null);
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set<? extends Object>) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set<? extends Object> set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        if (andSet == null) {
            if (this.pendingPausedComposition == null) {
                ComposerKt.composeImmediateRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
            }
        } else {
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            wq6.a();
        }
    }

    private final void drainPendingModificationsOutOfBandLocked() {
        Object andSet = this.pendingModifications.getAndSet(SetsKt.emptySet());
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications) || andSet == null) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set<? extends Object>) andSet, false);
            return;
        }
        if (!(andSet instanceof Object[])) {
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            wq6.a();
            return;
        }
        for (Set<? extends Object> set : (Set[]) andSet) {
            addPendingInvalidationsLocked(set, false);
        }
    }

    private final void ensureRunning() {
        String str;
        int i = this.state;
        if (!(i == 0)) {
            if (i == 1) {
                str = "The composition should be activated before setting content.";
            } else if (i != 2) {
                str = i != 3 ? "" : "The composition is disposed";
            } else {
                str = "A previous pausable composition for this composition was cancelled. This composition must be disposed.";
            }
            PreconditionsKt.throwIllegalStateException(str);
        }
        if (this.pendingPausedComposition == null) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
    }

    private static /* synthetic */ void getAbandonSet$annotations() {
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime();
    }

    public static /* synthetic */ void getPendingInvalidScopes$runtime$annotations() {
    }

    public static /* synthetic */ void getSlotTable$runtime$annotations() {
    }

    private final <T> T guardChanges(Function0<? extends T> block) {
        try {
            try {
                T t = (T) block.invoke();
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                return t;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                        InlineMarker.finallyStart(1);
                        rememberEventDispatcher.clear();
                        InlineMarker.finallyEnd(1);
                    } catch (Throwable th2) {
                        InlineMarker.finallyStart(1);
                        rememberEventDispatcher.clear();
                        InlineMarker.finallyEnd(1);
                        throw th2;
                    }
                }
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } catch (Throwable th3) {
            abandonChanges();
            throw th3;
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super ScopeMap<RecomposeScopeImpl, Object>, ? extends T> block) {
        MutableScatterMap<Object, Object> mutableScatterMapM2331takeInvalidationsafanTW4 = m2331takeInvalidationsafanTW4();
        try {
            return (T) block.invoke(ScopeMap.m2487boximpl(mutableScatterMapM2331takeInvalidationsafanTW4));
        } catch (Throwable th) {
            this.invalidations = mutableScatterMapM2331takeInvalidationsafanTW4;
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0099 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x009b A[Catch: all -> 0x001e, LOOP:0: B:32:0x005a->B:46:0x009b, LOOP_END, TryCatch #0 {all -> 0x001e, blocks: (B:4:0x000b, B:6:0x0010, B:14:0x0023, B:16:0x0029, B:20:0x002f, B:21:0x0038, B:24:0x003e, B:25:0x0044, B:27:0x004a, B:29:0x004e, B:32:0x005a, B:34:0x006a, B:36:0x0076, B:38:0x0080, B:42:0x008f, B:46:0x009b, B:47:0x009e, B:50:0x00a3), top: B:63:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00a3 A[Catch: all -> 0x001e, EDGE_INSN: B:50:0x00a3->B:51:0x00a8 BREAK  A[LOOP:0: B:32:0x005a->B:46:0x009b], TRY_LEAVE, TryCatch #0 {all -> 0x001e, blocks: (B:4:0x000b, B:6:0x0010, B:14:0x0023, B:16:0x0029, B:20:0x002f, B:21:0x0038, B:24:0x003e, B:25:0x0044, B:27:0x004a, B:29:0x004e, B:32:0x005a, B:34:0x006a, B:36:0x0076, B:38:0x0080, B:42:0x008f, B:46:0x009b, B:47:0x009e, B:50:0x00a3), top: B:63:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:66:0x00a3 A[SYNTHETIC] */
    private final InvalidationResult invalidateChecked(RecomposeScopeImpl scope, Anchor anchor, Object instance) {
        synchronized (this.lock) {
            try {
                CompositionImpl compositionImpl = this.invalidationDelegate;
                CompositionImpl compositionImpl2 = null;
                if (compositionImpl != null) {
                    if (!this.slotTable.groupContainsAnchor(this.invalidationDelegateGroup, anchor)) {
                        compositionImpl = null;
                    }
                    compositionImpl2 = compositionImpl;
                }
                if (compositionImpl2 == null) {
                    if (!tryImminentInvalidation(scope, instance)) {
                        if (instance != null) {
                            boolean z = instance instanceof DerivedState;
                            MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
                            if (z) {
                                Object obj = mutableScatterMap.get(scope);
                                if (obj != null) {
                                    if (!(obj instanceof MutableScatterSet)) {
                                        if (obj != ScopeInvalidated.INSTANCE) {
                                            ScopeMap.m2484addimpl(this.invalidations, scope, instance);
                                            break;
                                        }
                                    } else {
                                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
                                        Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                                        long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
                                        int length = jArr.length - 2;
                                        if (length < 0) {
                                            ScopeMap.m2484addimpl(this.invalidations, scope, instance);
                                            break;
                                        }
                                        int i = 0;
                                        loop0: while (true) {
                                            long j = jArr[i];
                                            if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                                                if (i == length) {
                                                    ScopeMap.m2484addimpl(this.invalidations, scope, instance);
                                                    break;
                                                }
                                                i++;
                                            } else {
                                                int i2 = 8;
                                                int i3 = 8 - ((~(i - length)) >>> 31);
                                                int i4 = 0;
                                                while (i4 < i3) {
                                                    if ((j & 255) < 128 && objArr[(i << 3) + i4] == ScopeInvalidated.INSTANCE) {
                                                        break loop0;
                                                    }
                                                    j >>= i2;
                                                    i4++;
                                                    i2 = i2;
                                                }
                                                if (i3 == i2) {
                                                    if (i == length) {
                                                        i++;
                                                    }
                                                }
                                                ScopeMap.m2484addimpl(this.invalidations, scope, instance);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    ScopeMap.m2484addimpl(this.invalidations, scope, instance);
                                    break;
                                }
                            } else {
                                ScopeMap.m2501setimpl(mutableScatterMap, scope, ScopeInvalidated.INSTANCE);
                            }
                        } else {
                            ScopeMap.m2501setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                        }
                    } else {
                        return InvalidationResult.IMMINENT;
                    }
                }
                if (compositionImpl2 != null) {
                    return compositionImpl2.invalidateChecked(scope, anchor, instance);
                }
                this.parent.invalidate$runtime(this);
                return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        Object obj = this.observations.get(value);
        if (obj == null) {
            return;
        }
        if (!(obj instanceof MutableScatterSet)) {
            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj;
            if (recomposeScopeImpl.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                ScopeMap.m2484addimpl(this.observationsProcessed, value, recomposeScopeImpl);
                return;
            }
            return;
        }
        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
        Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
        long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                        if (recomposeScopeImpl2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                            ScopeMap.m2484addimpl(this.observationsProcessed, value, recomposeScopeImpl2);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final CompositionObserver observer() {
        return this.observerHolder.current();
    }

    /* JADX INFO: renamed from: takeInvalidations-afanTW4, reason: not valid java name */
    private final MutableScatterMap<Object, Object> m2331takeInvalidationsafanTW4() {
        MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
        this.invalidations = ScopeMap.m2490constructorimpl$default(null, 1, null);
        return mutableScatterMap;
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            T t = (T) block.invoke();
            InlineMarker.finallyStart(1);
            InlineMarker.finallyEnd(1);
            return t;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (!this.abandonSet.isEmpty()) {
                RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                try {
                    rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                    rememberEventDispatcher.dispatchAbandons();
                    InlineMarker.finallyStart(1);
                    rememberEventDispatcher.clear();
                    InlineMarker.finallyEnd(1);
                } catch (Throwable th2) {
                    InlineMarker.finallyStart(1);
                    rememberEventDispatcher.clear();
                    InlineMarker.finallyEnd(1);
                    throw th2;
                }
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime(scope, instance);
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] slots = slotTable.getSlots();
        ArrayList arrayList = new ArrayList();
        for (Object obj : slots) {
            RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
            if (recomposeScopeImpl != null) {
                arrayList.add(recomposeScopeImpl);
            }
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) arrayList.get(i);
            Anchor anchor = recomposeScopeImpl2.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime(anchor.toIndexFor(slotTable)).contains(recomposeScopeImpl2)) {
                PreconditionsKt.throwIllegalStateException("Misaligned anchor " + anchor + " in scope " + recomposeScopeImpl2 + " encountered, scope found at " + ArraysKt.indexOf(slotTable.getSlots(), recomposeScopeImpl2));
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (this.abandonSet.isEmpty()) {
            return;
        }
        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
        try {
            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
            rememberEventDispatcher.dispatchAbandons();
        } finally {
            rememberEventDispatcher.clear();
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher.clear();
                        }
                    }
                    throw th;
                } catch (Throwable th2) {
                    abandonChanges();
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher.clear();
                        }
                    }
                    throw th;
                } catch (Throwable th2) {
                    abandonChanges();
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        synchronized (this.lock) {
            try {
                this.composer.changesApplied$runtime();
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                        rememberEventDispatcher.clear();
                    } catch (Throwable th) {
                        rememberEventDispatcher.clear();
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th2) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher2 = this.rememberManager;
                        try {
                            rememberEventDispatcher2.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher2.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher2.clear();
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    abandonChanges();
                    throw th3;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                MutableScatterMap<Object, Object> mutableScatterMapM2331takeInvalidationsafanTW4 = m2331takeInvalidationsafanTW4();
                try {
                    this.composer.m2327composeContentZbOJvo$runtime(mutableScatterMapM2331takeInvalidationsafanTW4, content, this.shouldPause);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    this.invalidations = mutableScatterMapM2331takeInvalidationsafanTW4;
                    throw th;
                }
            }
        } catch (Throwable th2) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                    } finally {
                        rememberEventDispatcher.clear();
                    }
                }
                throw th2;
            } catch (Throwable th3) {
                abandonChanges();
                throw th3;
            }
        }
    }

    public final int composerStacksSizes$runtime() {
        return this.composer.stacksSize$runtime();
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        synchronized (this.lock) {
            try {
                if (!(this.pendingPausedComposition == null)) {
                    PreconditionsKt.throwIllegalStateException("Deactivate is not supported while pausable composition is in progress");
                }
                boolean z = this.slotTable.getGroupsSize() > 0;
                if (z || !this.abandonSet.isEmpty()) {
                    Trace trace = Trace.INSTANCE;
                    Object objBeginSection = trace.beginSection("Compose:deactivate");
                    try {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            if (z) {
                                this.applier.onBeginChanges();
                                SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                                try {
                                    ComposerImplKt.deactivateCurrentGroup(slotWriterOpenWriter, this.rememberManager);
                                    Unit unit = Unit.INSTANCE;
                                    slotWriterOpenWriter.close(true);
                                    this.applier.onEndChanges();
                                    rememberEventDispatcher.dispatchRememberObservers();
                                } catch (Throwable th) {
                                    slotWriterOpenWriter.close(false);
                                    throw th;
                                }
                            }
                            rememberEventDispatcher.dispatchAbandons();
                            rememberEventDispatcher.clear();
                            Unit unit2 = Unit.INSTANCE;
                            trace.endSection(objBeginSection);
                        } catch (Throwable th2) {
                            rememberEventDispatcher.clear();
                            throw th2;
                        }
                    } catch (Throwable th3) {
                        Trace.INSTANCE.endSection(objBeginSection);
                        throw th3;
                    }
                }
                ScopeMap.m2488clearimpl(this.observations);
                ScopeMap.m2488clearimpl(this.derivedStates);
                ScopeMap.m2488clearimpl(this.invalidations);
                this.changes.clear();
                this.lateChanges.clear();
                this.composer.deactivate$runtime();
                this.state = 1;
                Unit unit3 = Unit.INSTANCE;
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        if (to == null || Intrinsics.areEqual(to, this) || groupIndex < 0) {
            return (R) block.invoke();
        }
        this.invalidationDelegate = (CompositionImpl) to;
        this.invalidationDelegateGroup = groupIndex;
        try {
            return (R) block.invoke();
        } finally {
            this.invalidationDelegate = null;
            this.invalidationDelegateGroup = 0;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        synchronized (this.lock) {
            try {
                if (this.composer.getIsComposing()) {
                    PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                }
                if (this.state != 3) {
                    this.state = 3;
                    this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$1918065384$runtime();
                    ChangeList deferredChanges$runtime = this.composer.getDeferredChanges();
                    if (deferredChanges$runtime != null) {
                        applyChangesInLocked(deferredChanges$runtime);
                    }
                    boolean z = this.slotTable.getGroupsSize() > 0;
                    if (z || !this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            if (z) {
                                this.applier.onBeginChanges();
                                SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                                try {
                                    ComposerKt.removeCurrentGroup(slotWriterOpenWriter, this.rememberManager);
                                    Unit unit = Unit.INSTANCE;
                                    slotWriterOpenWriter.close(true);
                                    this.applier.clear();
                                    this.applier.onEndChanges();
                                    rememberEventDispatcher.dispatchRememberObservers();
                                } catch (Throwable th) {
                                    slotWriterOpenWriter.close(false);
                                    throw th;
                                }
                            }
                            rememberEventDispatcher.dispatchAbandons();
                            rememberEventDispatcher.clear();
                        } catch (Throwable th2) {
                            rememberEventDispatcher.clear();
                            throw th2;
                        }
                    }
                    this.composer.dispose$runtime();
                }
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th3) {
                throw th3;
            }
        }
        this.parent.unregisterComposition$runtime(this);
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
        try {
            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
            SlotWriter slotWriterOpenWriter = state.getSlotTable().openWriter();
            try {
                ComposerKt.removeCurrentGroup(slotWriterOpenWriter, this.rememberManager);
                Unit unit = Unit.INSTANCE;
                slotWriterOpenWriter.close(true);
                rememberEventDispatcher.dispatchRememberObservers();
                rememberEventDispatcher.clear();
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        } catch (Throwable th2) {
            rememberEventDispatcher.clear();
            throw th2;
        }
    }

    public final List<Pair<RecomposeScopeImpl, Object>> extractInvalidationsOfGroup$runtime(Function1<? super Anchor, Boolean> inGroup) {
        long[] jArr;
        long[] jArr2;
        long j;
        char c;
        long j2;
        int i;
        int i2;
        boolean zIsEmpty;
        Object obj;
        long j3;
        Object obj2;
        int i3;
        if (ScopeMap.m2495getSizeimpl(this.invalidations) <= 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        MutableScatterMap mutableScatterMap = this.invalidations;
        long[] jArr3 = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i4 = 0;
            while (true) {
                long j4 = jArr3[i4];
                char c2 = 7;
                long j5 = -9187201950435737472L;
                if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8;
                    int i6 = 8 - ((~(i4 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((j4 & 255) < 128) {
                            int i8 = (i4 << 3) + i7;
                            c = c2;
                            Object obj3 = ((ScatterMap) mutableScatterMap).keys[i8];
                            j2 = j5;
                            Object obj4 = ((ScatterMap) mutableScatterMap).values[i8];
                            obj3.getClass();
                            if (obj4 instanceof MutableScatterSet) {
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj4;
                                Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                                long[] jArr4 = ((ScatterSet) mutableScatterSet).metadata;
                                int i9 = i5;
                                int length2 = jArr4.length - 2;
                                jArr2 = jArr3;
                                j = j4;
                                if (length2 >= 0) {
                                    int i10 = 0;
                                    while (true) {
                                        long j6 = jArr4[i10];
                                        Object[] objArr2 = objArr;
                                        i = i7;
                                        if ((((~j6) << c) & j6 & j2) != j2) {
                                            int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                            int i12 = 0;
                                            while (i12 < i11) {
                                                if ((j6 & 255) < 128) {
                                                    j3 = j6;
                                                    int i13 = (i10 << 3) + i12;
                                                    Object obj5 = objArr2[i13];
                                                    obj2 = obj3;
                                                    RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj2;
                                                    i3 = i12;
                                                    Anchor anchor = recomposeScopeImpl.getAnchor();
                                                    if (anchor != null && ((Boolean) inGroup.invoke(anchor)).booleanValue()) {
                                                        arrayList.add(TuplesKt.to(recomposeScopeImpl, obj5));
                                                        mutableScatterSet.removeElementAt(i13);
                                                    }
                                                } else {
                                                    j3 = j6;
                                                    obj2 = obj3;
                                                    i3 = i12;
                                                }
                                                j6 = j3 >> i9;
                                                i12 = i3 + 1;
                                                obj3 = obj2;
                                            }
                                            obj = obj3;
                                            if (i11 != i9) {
                                                break;
                                            }
                                        } else {
                                            obj = obj3;
                                        }
                                        if (i10 == length2) {
                                            break;
                                        }
                                        i10++;
                                        i7 = i;
                                        objArr = objArr2;
                                        obj3 = obj;
                                        i9 = 8;
                                    }
                                } else {
                                    i = i7;
                                }
                                zIsEmpty = mutableScatterSet.isEmpty();
                            } else {
                                jArr2 = jArr3;
                                j = j4;
                                i = i7;
                                obj4.getClass();
                                RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj3;
                                Anchor anchor2 = recomposeScopeImpl2.getAnchor();
                                if (anchor2 == null || !((Boolean) inGroup.invoke(anchor2)).booleanValue()) {
                                    zIsEmpty = false;
                                } else {
                                    arrayList.add(TuplesKt.to(recomposeScopeImpl2, obj4));
                                    zIsEmpty = true;
                                }
                            }
                            if (zIsEmpty) {
                                mutableScatterMap.removeValueAt(i8);
                            }
                            i2 = 8;
                        } else {
                            jArr2 = jArr3;
                            j = j4;
                            c = c2;
                            j2 = j5;
                            i = i7;
                            i2 = i5;
                        }
                        i7 = i + 1;
                        i5 = i2;
                        j4 = j >> i2;
                        c2 = c;
                        jArr3 = jArr2;
                        j5 = j2;
                    }
                    jArr = jArr3;
                    if (i6 != i5) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                }
                if (i4 == length) {
                    break;
                }
                i4++;
                jArr3 = jArr;
            }
        }
        return arrayList;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public ShouldPauseCallback getAndSetShouldPauseCallback(ShouldPauseCallback shouldPause) {
        ShouldPauseCallback shouldPauseCallback = this.shouldPause;
        this.shouldPause = shouldPause;
        return shouldPauseCallback;
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    /* JADX INFO: renamed from: getComposer$runtime, reason: from getter */
    public final ComposerImpl getComposer() {
        return this.composer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (Intrinsics.areEqual(key, CompositionKt.getObservableCompositionServiceKey())) {
            return this;
        }
        return null;
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes.asSet());
    }

    public final Set<Object> getDerivedStateDependencies$runtime() {
        return this.derivedStates.asMap().keySet();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        synchronized (this.lock) {
            z = ScopeMap.m2495getSizeimpl(this.invalidations) > 0;
        }
        return z;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime;
        synchronized (this.lock) {
            hasPendingChanges$runtime = this.composer.getHasPendingChanges$runtime();
        }
        return hasPendingChanges$runtime;
    }

    public final Set<Object> getObservedObjects$runtime() {
        return this.observations.asMap().keySet();
    }

    /* JADX INFO: renamed from: getObserverHolder$runtime, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    public final CompositionContext getParent() {
        return this.parent;
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime() : coroutineContext;
    }

    /* JADX INFO: renamed from: getSlotTable$runtime, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        int size = references.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else if (!Intrinsics.areEqual(((MovableContentStateReference) references.get(i).getFirst()).getComposition(), this)) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                    try {
                        rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                        rememberEventDispatcher.dispatchAbandons();
                    } finally {
                        rememberEventDispatcher.clear();
                    }
                }
                throw th;
            } catch (Throwable th2) {
                abandonChanges();
                throw th2;
            }
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        CompositionObserver compositionObserverObserver;
        CompositionImpl compositionImpl;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotTable.ownsAnchor(anchor)) {
            synchronized (this.lock) {
                compositionImpl = this.invalidationDelegate;
            }
            return (compositionImpl == null || !compositionImpl.tryImminentInvalidation(scope, instance)) ? InvalidationResult.IGNORED : InvalidationResult.IMMINENT;
        }
        if (!scope.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        InvalidationResult invalidationResultInvalidateChecked = invalidateChecked(scope, anchor, instance);
        if (invalidationResultInvalidateChecked != InvalidationResult.IGNORED && (compositionObserverObserver = observer()) != null) {
            compositionObserverObserver.onScopeInvalidated(scope, instance);
        }
        return invalidationResultInvalidateChecked;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        synchronized (this.lock) {
            try {
                for (Object obj : this.slotTable.getSlots()) {
                    RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                    if (recomposeScopeImpl != null) {
                        recomposeScopeImpl.invalidate();
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void invalidateGroupsWithKey(int key) {
        List<RecomposeScopeImpl> listInvalidateGroupsWithKey$runtime;
        synchronized (this.lock) {
            listInvalidateGroupsWithKey$runtime = this.slotTable.invalidateGroupsWithKey$runtime(key);
        }
        if (listInvalidateGroupsWithKey$runtime != null) {
            int size = listInvalidateGroupsWithKey$runtime.size();
            for (int i = 0; i < size; i++) {
                if (listInvalidateGroupsWithKey$runtime.get(i).invalidateForResult(null) != InvalidationResult.IGNORED) {
                }
            }
            return;
        }
        if (this.composer.forceRecomposeScopes$runtime()) {
            this.parent.invalidate$runtime(this);
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean isDisposed() {
        return this.state == 3;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0057 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0059 A[LOOP:0: B:7:0x0016->B:21:0x0059, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x007d A[SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        if (values instanceof ScatterSetWrapper) {
            ScatterSet set$runtime = ((ScatterSetWrapper) values).getSet$runtime();
            Object[] objArr = set$runtime.elements;
            long[] jArr = set$runtime.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                loop0: while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                Object obj = objArr[(i << 3) + i3];
                                if (ScopeMap.m2491containsimpl(this.observations, obj) || ScopeMap.m2491containsimpl(this.derivedStates, obj)) {
                                    break loop0;
                                }
                            }
                            j >>= 8;
                        }
                        if (i2 == 8) {
                            if (i != length) {
                                i++;
                            }
                        }
                    } else if (i != length) {
                        i++;
                    }
                }
                return true;
            }
        } else {
            for (Object obj2 : values) {
                if (ScopeMap.m2491containsimpl(this.observations, obj2) || ScopeMap.m2491containsimpl(this.derivedStates, obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void pausedCompositionFinished$runtime(ScatterSet<RememberObserverHolder> ignoreSet) {
        this.pendingPausedComposition = null;
        if (ignoreSet != null) {
            this.rememberManager.ignoreForgotten(ignoreSet);
            this.state = 2;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        this.composer.prepareCompose$runtime(block);
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        synchronized (this.lock) {
            PausedCompositionImpl pausedCompositionImpl = this.pendingPausedComposition;
            if (pausedCompositionImpl != null && !pausedCompositionImpl.isRecomposing$runtime()) {
                pausedCompositionImpl.markIncomplete$runtime();
                pausedCompositionImpl.getPausableApplier$runtime().markRecomposePending();
                return false;
            }
            drainPendingModificationsForCompositionLocked();
            try {
                MutableScatterMap<Object, Object> mutableScatterMapM2331takeInvalidationsafanTW4 = m2331takeInvalidationsafanTW4();
                try {
                    boolean zM2328recomposeaFTiNEg$runtime = this.composer.m2328recomposeaFTiNEg$runtime(mutableScatterMapM2331takeInvalidationsafanTW4, this.shouldPause);
                    if (!zM2328recomposeaFTiNEg$runtime) {
                        drainPendingModificationsLocked();
                    }
                    return zM2328recomposeaFTiNEg$runtime;
                } catch (Throwable th) {
                    this.invalidations = mutableScatterMapM2331takeInvalidationsafanTW4;
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = this.rememberManager;
                        try {
                            rememberEventDispatcher.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
                            rememberEventDispatcher.dispatchAbandons();
                        } finally {
                            rememberEventDispatcher.clear();
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    abandonChanges();
                    throw th3;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
        CompositionObserver compositionObserverObserver = observer();
        if (compositionObserverObserver != null) {
            compositionObserverObserver.onScopeDisposed(scope);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object objPlus;
        do {
            obj = this.pendingModifications.get();
            if (obj == null || Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = values;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    f2f.a("corrupt pendingModifications: ", this.pendingModifications);
                    return;
                }
                objPlus = ArraysKt.plus((Set[]) obj, values);
            }
        } while (!this.pendingModifications.compareAndSet(obj, objPlus));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl currentRecomposeScope$runtime;
        int i;
        int i2;
        if (getAreChildrenComposing() || (currentRecomposeScope$runtime = this.composer.getCurrentRecomposeScope$runtime()) == null) {
            return;
        }
        int i3 = 1;
        currentRecomposeScope$runtime.setUsed(true);
        boolean zRecordRead = currentRecomposeScope$runtime.recordRead(value);
        CompositionObserver compositionObserverObserver = observer();
        if (compositionObserverObserver != null) {
            compositionObserverObserver.onReadInScope(currentRecomposeScope$runtime, value);
        }
        if (zRecordRead) {
            return;
        }
        if (value instanceof StateObjectImpl) {
            ((StateObjectImpl) value).m2585recordReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(1));
        }
        ScopeMap.m2484addimpl(this.observations, value, currentRecomposeScope$runtime);
        if (value instanceof DerivedState) {
            DerivedState<?> derivedState = (DerivedState) value;
            DerivedState.Record<?> currentRecord = derivedState.getCurrentRecord();
            ScopeMap.m2499removeScopeimpl(this.derivedStates, value);
            ObjectIntMap<StateObject> dependencies = currentRecord.getDependencies();
            Object[] objArr = dependencies.keys;
            long[] jArr = dependencies.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i4 = 0;
                while (true) {
                    long j = jArr[i4];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i5 = 8;
                        int i6 = 8 - ((~(i4 - length)) >>> 31);
                        int i7 = 0;
                        while (i7 < i6) {
                            if ((j & 255) < 128) {
                                i2 = i3;
                                StateObject stateObject = (StateObject) objArr[(i4 << 3) + i7];
                                if (stateObject instanceof StateObjectImpl) {
                                    ((StateObjectImpl) stateObject).m2585recordReadInh_f27i8$runtime(ReaderKind.m2571constructorimpl(i2));
                                }
                                ScopeMap.m2484addimpl(this.derivedStates, stateObject, value);
                            } else {
                                i2 = i3;
                                i5 = i5;
                            }
                            j >>= i5;
                            i7++;
                            i3 = i2;
                            i5 = i5;
                        }
                        i = i3;
                        if (i6 != i5) {
                            break;
                        }
                    } else {
                        i = i3;
                    }
                    if (i4 == length) {
                        break;
                    }
                    i4++;
                    i3 = i;
                }
            }
            currentRecomposeScope$runtime.recordDerivedStateValue(derivedState, currentRecord.getCurrentValue());
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0057 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0059 A[Catch: all -> 0x004f, LOOP:0: B:11:0x001f->B:23:0x0059, LOOP_END, TryCatch #0 {all -> 0x004f, blocks: (B:4:0x0003, B:6:0x000e, B:8:0x0012, B:11:0x001f, B:13:0x002f, B:15:0x003b, B:17:0x0044, B:20:0x0051, B:23:0x0059, B:24:0x005c, B:25:0x0061), top: B:30:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0061 A[EDGE_INSN: B:33:0x0061->B:25:0x0061 BREAK  A[LOOP:0: B:11:0x001f->B:23:0x0059], SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        synchronized (this.lock) {
            try {
                invalidateScopeOfLocked(value);
                Object obj = this.derivedStates.get(value);
                if (obj != null) {
                    if (obj instanceof MutableScatterSet) {
                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
                        Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                        long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
                        int length = jArr.length - 2;
                        if (length >= 0) {
                            int i = 0;
                            while (true) {
                                long j = jArr[i];
                                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                                    if (i != length) {
                                        break;
                                        break;
                                    }
                                    i++;
                                } else {
                                    int i2 = 8 - ((~(i - length)) >>> 31);
                                    for (int i3 = 0; i3 < i2; i3++) {
                                        if ((255 & j) < 128) {
                                            invalidateScopeOfLocked((DerivedState) objArr[(i << 3) + i3]);
                                        }
                                        j >>= 8;
                                    }
                                    if (i2 != 8) {
                                        break;
                                    } else if (i != length) {
                                        break;
                                    } else {
                                        i++;
                                    }
                                }
                            }
                        }
                    } else {
                        invalidateScopeOfLocked((DerivedState) obj);
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void removeDerivedStateObservation$runtime(DerivedState<?> state) {
        if (ScopeMap.m2491containsimpl(this.observations, state)) {
            return;
        }
        ScopeMap.m2499removeScopeimpl(this.derivedStates, state);
    }

    public final void removeObservation$runtime(Object instance, RecomposeScopeImpl scope) {
        ScopeMap.m2497removeimpl(this.observations, instance, scope);
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean zClearDeactivated = clearDeactivated();
        ensureRunning();
        if (zClearDeactivated) {
            composeInitialWithReuse(content);
        } else {
            composeInitial(content);
        }
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        composeInitialWithReuse(content);
    }

    @Override // androidx.compose.runtime.tooling.ObservableComposition
    public CompositionObserverHandle setObserver(final CompositionObserver observer) {
        synchronized (this.lock) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl.setObserver.2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object obj = CompositionImpl.this.lock;
                CompositionImpl compositionImpl = CompositionImpl.this;
                CompositionObserver compositionObserver = observer;
                synchronized (obj) {
                    try {
                        if (Intrinsics.areEqual(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                            compositionImpl.getObserverHolder().setObserver(null);
                            compositionImpl.getObserverHolder().setRoot(false);
                        }
                        Unit unit2 = Unit.INSTANCE;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContent(Function2<? super Composer, ? super Integer, Unit> content) {
        return composeInitialPaused(clearDeactivated(), content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        return composeInitialPaused(true, content);
    }

    public final void setPendingInvalidScopes$runtime(boolean z) {
        this.pendingInvalidScopes = z;
    }

    public final void updateMovingInvalidations$runtime() {
        synchronized (this.lock) {
            drainPendingModificationsOutOfBandLocked();
            MutableScatterMap<Object, Object> mutableScatterMapM2331takeInvalidationsafanTW4 = m2331takeInvalidationsafanTW4();
            try {
                this.composer.m2329updateComposerInvalidationsRY85e9Y(mutableScatterMapM2331takeInvalidationsafanTW4);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                this.invalidations = mutableScatterMapM2331takeInvalidationsafanTW4;
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        synchronized (this.lock) {
            try {
                if (!isComposing()) {
                    this.composer.verifyConsistent$runtime();
                    this.slotTable.verifyWellFormed();
                    validateRecomposeScopeAnchors(this.slotTable);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    private final void addPendingInvalidationsLocked(Object value, boolean forgetConditionalScopes) {
        Object obj = this.observations.get(value);
        if (obj == null) {
            return;
        }
        if (obj instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
            long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                            if (!ScopeMap.m2497removeimpl(this.observationsProcessed, value, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(value) != InvalidationResult.IGNORED) {
                                if (recomposeScopeImpl.isConditional() && !forgetConditionalScopes) {
                                    this.conditionallyInvalidatedScopes.add(recomposeScopeImpl);
                                } else {
                                    this.invalidatedScopes.add(recomposeScopeImpl);
                                }
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        } else {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj;
            if (ScopeMap.m2497removeimpl(this.observationsProcessed, value, recomposeScopeImpl2) || recomposeScopeImpl2.invalidateForResult(value) == InvalidationResult.IGNORED) {
                return;
            }
            if (recomposeScopeImpl2.isConditional() && !forgetConditionalScopes) {
                this.conditionallyInvalidatedScopes.add(recomposeScopeImpl2);
            } else {
                this.invalidatedScopes.add(recomposeScopeImpl2);
            }
        }
    }
}
