package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 ~*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0002}~B1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fB)\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b¢\u0006\u0004\b\u000b\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0002J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0002J\r\u0010\u0015\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0015\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001fJ\u0015\u0010 \u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00028\u00012\u0006\u0010!\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\"J!\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u0005H\u0000¢\u0006\u0002\b%J1\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010(J9\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010*\u001a\u00020\nH\u0002¢\u0006\u0002\u0010+J)\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010-J=\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u00101J8\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J8\u00104\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010*\u001a\u00020\nH\u0002J&\u00105\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J.\u00106\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002JO\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010<JI\u0010=\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010>JQ\u0010?\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002¢\u0006\u0002\u0010@J[\u0010A\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00028\u00002\u0006\u0010D\u001a\u00028\u00012\u0006\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00028\u00002\u0006\u0010G\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010HJ&\u0010I\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J:\u0010J\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\u001e\u0010K\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010L\u001a\u00020\u0005H\u0002J2\u0010M\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010L\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\u0015\u0010N\u001a\u00020\u00182\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010OJ\u0017\u0010P\u001a\u0004\u0018\u00018\u00012\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010QJ+\u0010R\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010SJ=\u0010T\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010UJ#\u0010V\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010WJ7\u0010X\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010YJ+\u0010V\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010ZJ?\u0010X\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010UJ8\u0010[\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010]\u001a\u00020^2\u0006\u0010*\u001a\u00020\nH\u0002JT\u0010_\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\b\u0010`\u001a\u00020\u0005H\u0002J\u001c\u0010a\u001a\u00020\u00182\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J#\u0010b\u001a\u00020\u00182\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010dJ%\u0010e\u001a\u0004\u0018\u00018\u00012\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010fJJ\u0010g\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010;\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100J9\u0010h\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010iJK\u0010j\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010kJ1\u0010l\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010mJP\u0010n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002JE\u0010p\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010qJX\u0010r\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002J9\u0010l\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010sJM\u0010p\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010kJ\u0091\u0001\u0010t\u001a\u00020u2\u0081\u0001\u0010v\u001a}\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b({\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020u0wH\u0000¢\u0006\u0002\b|J\u009c\u0001\u0010t\u001a\u00020u2\u0081\u0001\u0010v\u001a}\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b({\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020u0w2\u0006\u0010{\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u007f"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "K", "V", "", "dataMap", "", "nodeMap", "buffer", "", "ownedBy", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "<init>", "(II[Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)V", "(II[Ljava/lang/Object;)V", "asInsertResult", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "asUpdateResult", "value", "getBuffer$runtime", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "entryCount", "entryCount$runtime", "hasEntryAt", "", "positionMask", "hasEntryAt$runtime", "hasNodeAt", "entryKeyIndex", "entryKeyIndex$runtime", "nodeIndex", "nodeIndex$runtime", "keyAtIndex", "keyIndex", "(I)Ljava/lang/Object;", "valueAtKeyIndex", "nodeAtIndex", "nodeAtIndex$runtime", "insertEntryAt", "key", "(ILjava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableInsertEntryAt", "owner", "(ILjava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "updateValueAtIndex", "(ILjava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableUpdateValueAtIndex", "mutator", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", "(ILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "updateNodeAtIndex", "newNode", "mutableUpdateNodeAtIndex", "removeNodeAtIndex", "mutableRemoveNodeAtIndex", "bufferMoveEntryToNode", "newKeyHash", "newKey", "newValue", "shift", "(IIILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)[Ljava/lang/Object;", "moveEntryToNode", "(IIILjava/lang/Object;Ljava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableMoveEntryToNode", "(IIILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "makeNode", "keyHash1", "key1", "value1", "keyHash2", "key2", "value2", "(ILjava/lang/Object;Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "removeEntryAtIndex", "mutableRemoveEntryAtIndex", "collisionRemoveEntryAtIndex", "i", "mutableCollisionRemoveEntryAtIndex", "collisionContainsKey", "(Ljava/lang/Object;)Z", "collisionGet", "(Ljava/lang/Object;)Ljava/lang/Object;", "collisionPut", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "mutableCollisionPut", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "collisionRemove", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableCollisionRemove", "(Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableCollisionPutAll", "otherNode", "intersectionCounter", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/DeltaCounter;", "mutablePutAllFromOtherNodeCell", "calculateSize", "elementsIdentityEquals", "containsKey", "keyHash", "(ILjava/lang/Object;I)Z", "get", "(ILjava/lang/Object;I)Ljava/lang/Object;", "mutablePutAll", "put", "(ILjava/lang/Object;Ljava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "mutablePut", "(ILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "remove", "(ILjava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "replaceNode", "targetNode", "mutableRemove", "(ILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableReplaceNode", "(ILjava/lang/Object;Ljava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "accept", "", "visitor", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "node", "hash", "accept$runtime", "ModificationResult", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TrieNode<K, V> {
    private Object[] buffer;
    private int dataMap;
    private int nodeMap;
    private final MutabilityOwnership ownedBy;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0]);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B#\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJA\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002*\u0010\u0011\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00050\u0012H\u0086\bR&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "K", "V", "", "node", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "sizeDelta", "", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;I)V", "getNode", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "setNode", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;)V", "getSizeDelta", "()I", "replaceNode", "operation", "Lkotlin/Function1;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ModificationResult<K, V> {
        public static final int $stable = 8;
        private TrieNode<K, V> node;
        private final int sizeDelta;

        public ModificationResult(TrieNode<K, V> trieNode, int i) {
            this.node = trieNode;
            this.sizeDelta = i;
        }

        public final TrieNode<K, V> getNode() {
            return this.node;
        }

        public final int getSizeDelta() {
            return this.sizeDelta;
        }

        public final ModificationResult<K, V> replaceNode(Function1<? super TrieNode<K, V>, TrieNode<K, V>> operation) {
            setNode((TrieNode) operation.invoke(getNode()));
            return this;
        }

        public final void setNode(TrieNode<K, V> trieNode) {
            this.node = trieNode;
        }
    }

    public TrieNode(int i, int i2, Object[] objArr, MutabilityOwnership mutabilityOwnership) {
        this.dataMap = i;
        this.nodeMap = i2;
        this.ownedBy = mutabilityOwnership;
        this.buffer = objArr;
    }

    private final void accept(Function5<? super TrieNode<K, V>, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> visitor, int hash, int shift) {
        visitor.invoke(this, Integer.valueOf(shift), Integer.valueOf(hash), Integer.valueOf(this.dataMap), Integer.valueOf(this.nodeMap));
        int i = this.nodeMap;
        while (i != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i);
            nodeAtIndex$runtime(nodeIndex$runtime(iLowestOneBit)).accept(visitor, (Integer.numberOfTrailingZeros(iLowestOneBit) << shift) + hash, shift + 5);
            i -= iLowestOneBit;
        }
    }

    private final ModificationResult<K, V> asInsertResult() {
        return new ModificationResult<>(this, 1);
    }

    private final ModificationResult<K, V> asUpdateResult() {
        return new ModificationResult<>(this, 0);
    }

    private final Object[] bufferMoveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift, MutabilityOwnership owner) {
        K kKeyAtIndex = keyAtIndex(keyIndex);
        return TrieNodeKt.replaceEntryWithNode(this.buffer, keyIndex, nodeIndex$runtime(positionMask) + 1, makeNode(kKeyAtIndex != null ? kKeyAtIndex.hashCode() : 0, kKeyAtIndex, valueAtKeyIndex(keyIndex), newKeyHash, newKey, newValue, shift + 5, owner));
    }

    private final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int iBitCount = Integer.bitCount(this.dataMap);
        int length = this.buffer.length;
        for (int i = iBitCount * 2; i < length; i++) {
            iBitCount += nodeAtIndex$runtime(i).calculateSize();
        }
        return iBitCount;
    }

    private final boolean collisionContainsKey(K key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(key, this.buffer[first])) {
                if (first != last) {
                    first += step;
                }
            }
            return true;
        }
        return false;
    }

    private final V collisionGet(K key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return null;
        }
        while (!Intrinsics.areEqual(key, keyAtIndex(first))) {
            if (first == last) {
                return null;
            }
            first += step;
        }
        return valueAtKeyIndex(first);
    }

    private final ModificationResult<K, V> collisionPut(K key, V value) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            if (value == valueAtKeyIndex(first)) {
                return null;
            }
            Object[] objArr = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            objArrCopyOf[first + 1] = value;
            return new TrieNode(0, 0, objArrCopyOf).asUpdateResult();
        }
        return new TrieNode(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, key, value)).asInsertResult();
    }

    private final TrieNode<K, V> collisionRemove(K key, V value) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                if (Intrinsics.areEqual(key, keyAtIndex(first)) && Intrinsics.areEqual(value, valueAtKeyIndex(first))) {
                    return collisionRemoveEntryAtIndex(first);
                }
                if (first != last) {
                    first += step;
                }
            }
        }
        return this;
    }

    private final TrieNode<K, V> collisionRemoveEntryAtIndex(int i) {
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        return new TrieNode<>(0, 0, TrieNodeKt.removeEntryAtIndex(objArr, i));
    }

    private final boolean elementsIdentityEquals(TrieNode<K, V> otherNode) {
        if (this == otherNode) {
            return true;
        }
        if (this.nodeMap != otherNode.nodeMap || this.dataMap != otherNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != otherNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    private final boolean hasNodeAt(int positionMask) {
        return (this.nodeMap & positionMask) != 0;
    }

    private final TrieNode<K, V> insertEntryAt(int positionMask, K key, V value) {
        return new TrieNode<>(positionMask | this.dataMap, this.nodeMap, TrieNodeKt.insertEntryAtIndex(this.buffer, entryKeyIndex$runtime(positionMask), key, value));
    }

    private final K keyAtIndex(int keyIndex) {
        return (K) this.buffer[keyIndex];
    }

    private final TrieNode<K, V> makeNode(int keyHash1, K key1, V value1, int keyHash2, K key2, V value2, int shift, MutabilityOwnership owner) {
        if (shift > 30) {
            return new TrieNode<>(0, 0, new Object[]{key1, value1, key2, value2}, owner);
        }
        int iIndexSegment = TrieNodeKt.indexSegment(keyHash1, shift);
        int iIndexSegment2 = TrieNodeKt.indexSegment(keyHash2, shift);
        if (iIndexSegment != iIndexSegment2) {
            return new TrieNode<>((1 << iIndexSegment) | (1 << iIndexSegment2), 0, iIndexSegment < iIndexSegment2 ? new Object[]{key1, value1, key2, value2} : new Object[]{key2, value2, key1, value1}, owner);
        }
        return new TrieNode<>(0, 1 << iIndexSegment, new Object[]{makeNode(keyHash1, key1, value1, keyHash2, key2, value2, shift + 5, owner)}, owner);
    }

    private final TrieNode<K, V> moveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift) {
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap | positionMask, bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, null));
    }

    private final TrieNode<K, V> mutableCollisionPut(K key, V value, PersistentHashMapBuilder<K, V> mutator) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            mutator.setOperationResult$runtime(valueAtKeyIndex(first));
            if (this.ownedBy == mutator.getOwnership()) {
                this.buffer[first + 1] = value;
                return this;
            }
            mutator.setModCount$runtime(mutator.getModCount() + 1);
            Object[] objArr = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            objArrCopyOf[first + 1] = value;
            return new TrieNode<>(0, 0, objArrCopyOf, mutator.getOwnership());
        }
        mutator.setSize(mutator.size() + 1);
        return new TrieNode<>(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, key, value), mutator.getOwnership());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrieNode<K, V> mutableCollisionPutAll(TrieNode<K, V> otherNode, DeltaCounter intersectionCounter, MutabilityOwnership owner) {
        CommonFunctionsKt.m2511assert(this.nodeMap == 0);
        CommonFunctionsKt.m2511assert(this.dataMap == 0);
        CommonFunctionsKt.m2511assert(otherNode.nodeMap == 0);
        CommonFunctionsKt.m2511assert(otherNode.dataMap == 0);
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + otherNode.buffer.length);
        int length = this.buffer.length;
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, otherNode.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                if (collisionContainsKey(otherNode.buffer[first])) {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                } else {
                    Object[] objArr2 = otherNode.buffer;
                    objArrCopyOf[length] = objArr2[first];
                    objArrCopyOf[length + 1] = objArr2[first + 1];
                    length += 2;
                }
                if (first == last) {
                    break;
                }
                first += step;
            }
        }
        if (length == this.buffer.length) {
            return this;
        }
        if (length == otherNode.buffer.length) {
            return otherNode;
        }
        return length == objArrCopyOf.length ? new TrieNode<>(0, 0, objArrCopyOf, owner) : new TrieNode<>(0, 0, Arrays.copyOf(objArrCopyOf, length), owner);
    }

    private final TrieNode<K, V> mutableCollisionRemove(K key, V value, PersistentHashMapBuilder<K, V> mutator) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                if (Intrinsics.areEqual(key, keyAtIndex(first)) && Intrinsics.areEqual(value, valueAtKeyIndex(first))) {
                    return mutableCollisionRemoveEntryAtIndex(first, mutator);
                }
                if (first != last) {
                    first += step;
                }
            }
        }
        return this;
    }

    private final TrieNode<K, V> mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder<K, V> mutator) {
        mutator.setSize(mutator.size() - 1);
        mutator.setOperationResult$runtime(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership != ownership) {
            return new TrieNode<>(0, 0, TrieNodeKt.removeEntryAtIndex(objArr, i), mutator.getOwnership());
        }
        this.buffer = TrieNodeKt.removeEntryAtIndex(objArr, i);
        return this;
    }

    private final TrieNode<K, V> mutableInsertEntryAt(int positionMask, K key, V value, MutabilityOwnership owner) {
        int iEntryKeyIndex$runtime = entryKeyIndex$runtime(positionMask);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership != owner) {
            return new TrieNode<>(positionMask | this.dataMap, this.nodeMap, TrieNodeKt.insertEntryAtIndex(objArr, iEntryKeyIndex$runtime, key, value), owner);
        }
        this.buffer = TrieNodeKt.insertEntryAtIndex(objArr, iEntryKeyIndex$runtime, key, value);
        this.dataMap = positionMask | this.dataMap;
        return this;
    }

    private final TrieNode<K, V> mutableMoveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift, MutabilityOwnership owner) {
        if (this.ownedBy != owner) {
            return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap | positionMask, bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, owner), owner);
        }
        this.buffer = bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, owner);
        this.dataMap ^= positionMask;
        this.nodeMap |= positionMask;
        return this;
    }

    private final TrieNode<K, V> mutablePutAllFromOtherNodeCell(TrieNode<K, V> otherNode, int positionMask, int shift, DeltaCounter intersectionCounter, PersistentHashMapBuilder<K, V> mutator) {
        if (hasNodeAt(positionMask)) {
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex$runtime(positionMask));
            if (otherNode.hasNodeAt(positionMask)) {
                return trieNodeNodeAtIndex$runtime.mutablePutAll(otherNode.nodeAtIndex$runtime(otherNode.nodeIndex$runtime(positionMask)), shift + 5, intersectionCounter, mutator);
            }
            if (!otherNode.hasEntryAt$runtime(positionMask)) {
                return trieNodeNodeAtIndex$runtime;
            }
            int iEntryKeyIndex$runtime = otherNode.entryKeyIndex$runtime(positionMask);
            K kKeyAtIndex = otherNode.keyAtIndex(iEntryKeyIndex$runtime);
            V vValueAtKeyIndex = otherNode.valueAtKeyIndex(iEntryKeyIndex$runtime);
            int size = mutator.size();
            TrieNode<K, V> trieNodeMutablePut = trieNodeNodeAtIndex$runtime.mutablePut(kKeyAtIndex != null ? kKeyAtIndex.hashCode() : 0, kKeyAtIndex, vValueAtKeyIndex, shift + 5, mutator);
            if (mutator.size() == size) {
                intersectionCounter.setCount(intersectionCounter.getCount() + 1);
            }
            return trieNodeMutablePut;
        }
        if (!otherNode.hasNodeAt(positionMask)) {
            int iEntryKeyIndex$runtime2 = entryKeyIndex$runtime(positionMask);
            K kKeyAtIndex2 = keyAtIndex(iEntryKeyIndex$runtime2);
            V vValueAtKeyIndex2 = valueAtKeyIndex(iEntryKeyIndex$runtime2);
            int iEntryKeyIndex$runtime3 = otherNode.entryKeyIndex$runtime(positionMask);
            K kKeyAtIndex3 = otherNode.keyAtIndex(iEntryKeyIndex$runtime3);
            return makeNode(kKeyAtIndex2 != null ? kKeyAtIndex2.hashCode() : 0, kKeyAtIndex2, vValueAtKeyIndex2, kKeyAtIndex3 != null ? kKeyAtIndex3.hashCode() : 0, kKeyAtIndex3, otherNode.valueAtKeyIndex(iEntryKeyIndex$runtime3), shift + 5, mutator.getOwnership());
        }
        TrieNode<K, V> trieNodeNodeAtIndex$runtime2 = otherNode.nodeAtIndex$runtime(otherNode.nodeIndex$runtime(positionMask));
        if (!hasEntryAt$runtime(positionMask)) {
            return trieNodeNodeAtIndex$runtime2;
        }
        int iEntryKeyIndex$runtime4 = entryKeyIndex$runtime(positionMask);
        K kKeyAtIndex4 = keyAtIndex(iEntryKeyIndex$runtime4);
        int i = shift + 5;
        if (!trieNodeNodeAtIndex$runtime2.containsKey(kKeyAtIndex4 != null ? kKeyAtIndex4.hashCode() : 0, kKeyAtIndex4, i)) {
            return trieNodeNodeAtIndex$runtime2.mutablePut(kKeyAtIndex4 != null ? kKeyAtIndex4.hashCode() : 0, kKeyAtIndex4, valueAtKeyIndex(iEntryKeyIndex$runtime4), i, mutator);
        }
        intersectionCounter.setCount(intersectionCounter.getCount() + 1);
        return trieNodeNodeAtIndex$runtime2;
    }

    private final TrieNode<K, V> mutableRemoveEntryAtIndex(int keyIndex, int positionMask, PersistentHashMapBuilder<K, V> mutator) {
        mutator.setSize(mutator.size() - 1);
        mutator.setOperationResult$runtime(valueAtKeyIndex(keyIndex));
        if (this.buffer.length == 2) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership != ownership) {
            return new TrieNode<>(positionMask ^ this.dataMap, this.nodeMap, TrieNodeKt.removeEntryAtIndex(objArr, keyIndex), mutator.getOwnership());
        }
        this.buffer = TrieNodeKt.removeEntryAtIndex(objArr, keyIndex);
        this.dataMap ^= positionMask;
        return this;
    }

    private final TrieNode<K, V> mutableRemoveNodeAtIndex(int nodeIndex, int positionMask, MutabilityOwnership owner) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        if (this.ownedBy != owner) {
            return new TrieNode<>(this.dataMap, this.nodeMap ^ positionMask, TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex), owner);
        }
        this.buffer = TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex);
        this.nodeMap ^= positionMask;
        return this;
    }

    private final TrieNode<K, V> mutableReplaceNode(TrieNode<K, V> targetNode, TrieNode<K, V> newNode, int nodeIndex, int positionMask, MutabilityOwnership owner) {
        if (newNode == null) {
            return mutableRemoveNodeAtIndex(nodeIndex, positionMask, owner);
        }
        return (this.ownedBy == owner || targetNode != newNode) ? mutableUpdateNodeAtIndex(nodeIndex, newNode, owner) : this;
    }

    private final TrieNode<K, V> mutableUpdateNodeAtIndex(int nodeIndex, TrieNode<K, V> newNode, MutabilityOwnership owner) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1 && newNode.buffer.length == 2 && newNode.nodeMap == 0) {
            newNode.dataMap = this.nodeMap;
            return newNode;
        }
        if (this.ownedBy == owner) {
            objArr[nodeIndex] = newNode;
            return this;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        objArrCopyOf[nodeIndex] = newNode;
        return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf, owner);
    }

    private final TrieNode<K, V> mutableUpdateValueAtIndex(int keyIndex, V value, PersistentHashMapBuilder<K, V> mutator) {
        if (this.ownedBy == mutator.getOwnership()) {
            this.buffer[keyIndex + 1] = value;
            return this;
        }
        mutator.setModCount$runtime(mutator.getModCount() + 1);
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        objArrCopyOf[keyIndex + 1] = value;
        return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf, mutator.getOwnership());
    }

    private final TrieNode<K, V> removeEntryAtIndex(int keyIndex, int positionMask) {
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        return new TrieNode<>(positionMask ^ this.dataMap, this.nodeMap, TrieNodeKt.removeEntryAtIndex(objArr, keyIndex));
    }

    private final TrieNode<K, V> removeNodeAtIndex(int nodeIndex, int positionMask) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        return new TrieNode<>(this.dataMap, this.nodeMap ^ positionMask, TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex));
    }

    private final TrieNode<K, V> replaceNode(TrieNode<K, V> targetNode, TrieNode<K, V> newNode, int nodeIndex, int positionMask) {
        if (newNode == null) {
            return removeNodeAtIndex(nodeIndex, positionMask);
        }
        return targetNode != newNode ? updateNodeAtIndex(nodeIndex, positionMask, newNode) : this;
    }

    private final TrieNode<K, V> updateNodeAtIndex(int nodeIndex, int positionMask, TrieNode<K, V> newNode) {
        Object[] objArr = newNode.buffer;
        if (objArr.length != 2 || newNode.nodeMap != 0) {
            Object[] objArr2 = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length);
            objArrCopyOf[nodeIndex] = newNode;
            return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf);
        }
        if (this.buffer.length == 1) {
            newNode.dataMap = this.nodeMap;
            return newNode;
        }
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap ^ positionMask, TrieNodeKt.replaceNodeWithEntry(this.buffer, nodeIndex, entryKeyIndex$runtime(positionMask), objArr[0], objArr[1]));
    }

    private final TrieNode<K, V> updateValueAtIndex(int keyIndex, V value) {
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        objArrCopyOf[keyIndex + 1] = value;
        return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf);
    }

    private final V valueAtKeyIndex(int keyIndex) {
        return (V) this.buffer[keyIndex + 1];
    }

    public final void accept$runtime(Function5<? super TrieNode<K, V>, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> visitor) {
        accept(visitor, 0, 0);
    }

    public final boolean containsKey(int keyHash, K key, int shift) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            return Intrinsics.areEqual(key, keyAtIndex(entryKeyIndex$runtime(iIndexSegment)));
        }
        if (!hasNodeAt(iIndexSegment)) {
            return false;
        }
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex$runtime(iIndexSegment));
        return shift == 30 ? trieNodeNodeAtIndex$runtime.collisionContainsKey(key) : trieNodeNodeAtIndex$runtime.containsKey(keyHash, key, shift + 5);
    }

    public final int entryCount$runtime() {
        return Integer.bitCount(this.dataMap);
    }

    public final int entryKeyIndex$runtime(int positionMask) {
        return Integer.bitCount(this.dataMap & (positionMask - 1)) * 2;
    }

    public final V get(int keyHash, K key, int shift) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            if (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime))) {
                return valueAtKeyIndex(iEntryKeyIndex$runtime);
            }
            return null;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return null;
        }
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex$runtime(iIndexSegment));
        return shift == 30 ? trieNodeNodeAtIndex$runtime.collisionGet(key) : trieNodeNodeAtIndex$runtime.get(keyHash, key, shift + 5);
    }

    /* JADX INFO: renamed from: getBuffer$runtime, reason: from getter */
    public final Object[] getBuffer() {
        return this.buffer;
    }

    public final boolean hasEntryAt$runtime(int positionMask) {
        return (this.dataMap & positionMask) != 0;
    }

    public final TrieNode<K, V> mutablePut(int keyHash, K key, V value, int shift, PersistentHashMapBuilder<K, V> mutator) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            if (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime))) {
                mutator.setOperationResult$runtime(valueAtKeyIndex(iEntryKeyIndex$runtime));
                return valueAtKeyIndex(iEntryKeyIndex$runtime) == value ? this : mutableUpdateValueAtIndex(iEntryKeyIndex$runtime, value, mutator);
            }
            mutator.setSize(mutator.size() + 1);
            return mutableMoveEntryToNode(iEntryKeyIndex$runtime, iIndexSegment, keyHash, key, value, shift, mutator.getOwnership());
        }
        if (!hasNodeAt(iIndexSegment)) {
            mutator.setSize(mutator.size() + 1);
            return mutableInsertEntryAt(iIndexSegment, key, value, mutator.getOwnership());
        }
        int iNodeIndex$runtime = nodeIndex$runtime(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(iNodeIndex$runtime);
        TrieNode<K, V> trieNodeMutableCollisionPut = shift == 30 ? trieNodeNodeAtIndex$runtime.mutableCollisionPut(key, value, mutator) : trieNodeNodeAtIndex$runtime.mutablePut(keyHash, key, value, shift + 5, mutator);
        return trieNodeNodeAtIndex$runtime == trieNodeMutableCollisionPut ? this : mutableUpdateNodeAtIndex(iNodeIndex$runtime, trieNodeMutableCollisionPut, mutator.getOwnership());
    }

    public final TrieNode<K, V> mutablePutAll(TrieNode<K, V> otherNode, int shift, DeltaCounter intersectionCounter, PersistentHashMapBuilder<K, V> mutator) {
        if (this == otherNode) {
            intersectionCounter.plusAssign(calculateSize());
            return this;
        }
        int i = shift;
        if (i > 30) {
            return mutableCollisionPutAll(otherNode, intersectionCounter, mutator.getOwnership());
        }
        int i2 = this.nodeMap | otherNode.nodeMap;
        int i3 = this.dataMap;
        int i4 = otherNode.dataMap;
        int i5 = (i3 ^ i4) & (~i2);
        int i6 = i3 & i4;
        while (i6 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i6);
            if (Intrinsics.areEqual(keyAtIndex(entryKeyIndex$runtime(iLowestOneBit)), otherNode.keyAtIndex(otherNode.entryKeyIndex$runtime(iLowestOneBit)))) {
                i5 |= iLowestOneBit;
            } else {
                i2 |= iLowestOneBit;
            }
            i6 ^= iLowestOneBit;
        }
        int i7 = 0;
        if (!((i2 & i5) == 0)) {
            PreconditionsKt.throwIllegalStateException("Check failed.");
        }
        TrieNode<K, V> trieNode = (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership()) && this.dataMap == i5 && this.nodeMap == i2) ? this : new TrieNode<>(i5, i2, new Object[(Integer.bitCount(i5) * 2) + Integer.bitCount(i2)]);
        int i8 = i2;
        int i9 = 0;
        while (i8 != 0) {
            int iLowestOneBit2 = Integer.lowestOneBit(i8);
            Object[] objArr = trieNode.buffer;
            objArr[(objArr.length - 1) - i9] = mutablePutAllFromOtherNodeCell(otherNode, iLowestOneBit2, i, intersectionCounter, mutator);
            i9++;
            i8 ^= iLowestOneBit2;
            i = shift;
        }
        while (i5 != 0) {
            int iLowestOneBit3 = Integer.lowestOneBit(i5);
            int i10 = i7 * 2;
            if (otherNode.hasEntryAt$runtime(iLowestOneBit3)) {
                int iEntryKeyIndex$runtime = otherNode.entryKeyIndex$runtime(iLowestOneBit3);
                trieNode.buffer[i10] = otherNode.keyAtIndex(iEntryKeyIndex$runtime);
                trieNode.buffer[i10 + 1] = otherNode.valueAtKeyIndex(iEntryKeyIndex$runtime);
                if (hasEntryAt$runtime(iLowestOneBit3)) {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                }
            } else {
                int iEntryKeyIndex$runtime2 = entryKeyIndex$runtime(iLowestOneBit3);
                trieNode.buffer[i10] = keyAtIndex(iEntryKeyIndex$runtime2);
                trieNode.buffer[i10 + 1] = valueAtKeyIndex(iEntryKeyIndex$runtime2);
            }
            i7++;
            i5 ^= iLowestOneBit3;
        }
        if (elementsIdentityEquals(trieNode)) {
            return this;
        }
        return otherNode.elementsIdentityEquals(trieNode) ? otherNode : trieNode;
    }

    public final TrieNode<K, V> mutableRemove(int keyHash, K key, V value, int shift, PersistentHashMapBuilder<K, V> mutator) {
        PersistentHashMapBuilder<K, V> persistentHashMapBuilder;
        TrieNode<K, V> trieNodeMutableRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            return (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime)) && Intrinsics.areEqual(value, valueAtKeyIndex(iEntryKeyIndex$runtime))) ? mutableRemoveEntryAtIndex(iEntryKeyIndex$runtime, iIndexSegment, mutator) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$runtime = nodeIndex$runtime(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(iNodeIndex$runtime);
        if (shift == 30) {
            trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableCollisionRemove(key, value, mutator);
            persistentHashMapBuilder = mutator;
        } else {
            persistentHashMapBuilder = mutator;
            trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableRemove(keyHash, key, value, shift + 5, persistentHashMapBuilder);
        }
        return mutableReplaceNode(trieNodeNodeAtIndex$runtime, trieNodeMutableRemove, iNodeIndex$runtime, iIndexSegment, persistentHashMapBuilder.getOwnership());
    }

    public final TrieNode<K, V> nodeAtIndex$runtime(int nodeIndex) {
        Object obj = this.buffer[nodeIndex];
        obj.getClass();
        return (TrieNode) obj;
    }

    public final int nodeIndex$runtime(int positionMask) {
        return (this.buffer.length - 1) - Integer.bitCount(this.nodeMap & (positionMask - 1));
    }

    public final ModificationResult<K, V> put(int keyHash, K key, V value, int shift) {
        ModificationResult<K, V> modificationResultPut;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            if (!Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime))) {
                return moveEntryToNode(iEntryKeyIndex$runtime, iIndexSegment, keyHash, key, value, shift).asInsertResult();
            }
            if (valueAtKeyIndex(iEntryKeyIndex$runtime) == value) {
                return null;
            }
            return updateValueAtIndex(iEntryKeyIndex$runtime, value).asUpdateResult();
        }
        if (!hasNodeAt(iIndexSegment)) {
            return insertEntryAt(iIndexSegment, key, value).asInsertResult();
        }
        int iNodeIndex$runtime = nodeIndex$runtime(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(iNodeIndex$runtime);
        if (shift == 30) {
            modificationResultPut = trieNodeNodeAtIndex$runtime.collisionPut(key, value);
            if (modificationResultPut == null) {
                return null;
            }
        } else {
            modificationResultPut = trieNodeNodeAtIndex$runtime.put(keyHash, key, value, shift + 5);
            if (modificationResultPut == null) {
                return null;
            }
        }
        modificationResultPut.setNode(updateNodeAtIndex(iNodeIndex$runtime, iIndexSegment, modificationResultPut.getNode()));
        return modificationResultPut;
    }

    public final TrieNode<K, V> remove(int keyHash, K key, V value, int shift) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            return (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime)) && Intrinsics.areEqual(value, valueAtKeyIndex(iEntryKeyIndex$runtime))) ? removeEntryAtIndex(iEntryKeyIndex$runtime, iIndexSegment) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$runtime = nodeIndex$runtime(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(iNodeIndex$runtime);
        return replaceNode(trieNodeNodeAtIndex$runtime, shift == 30 ? trieNodeNodeAtIndex$runtime.collisionRemove(key, value) : trieNodeNodeAtIndex$runtime.remove(keyHash, key, value, shift + 5), iNodeIndex$runtime, iIndexSegment);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "", "getEMPTY$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TrieNode getEMPTY$runtime() {
            return TrieNode.EMPTY;
        }

        private Companion() {
        }
    }

    public TrieNode(int i, int i2, Object[] objArr) {
        this(i, i2, objArr, null);
    }

    private final TrieNode<K, V> collisionRemove(K key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            return collisionRemoveEntryAtIndex(first);
        }
        return this;
    }

    private final TrieNode<K, V> mutableCollisionRemove(K key, PersistentHashMapBuilder<K, V> mutator) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            return mutableCollisionRemoveEntryAtIndex(first, mutator);
        }
        return this;
    }

    public final TrieNode<K, V> remove(int keyHash, K key, int shift) {
        TrieNode<K, V> trieNodeRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            return Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime)) ? removeEntryAtIndex(iEntryKeyIndex$runtime, iIndexSegment) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$runtime = nodeIndex$runtime(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(iNodeIndex$runtime);
        if (shift == 30) {
            trieNodeRemove = trieNodeNodeAtIndex$runtime.collisionRemove(key);
        } else {
            trieNodeRemove = trieNodeNodeAtIndex$runtime.remove(keyHash, key, shift + 5);
        }
        return replaceNode(trieNodeNodeAtIndex$runtime, trieNodeRemove, iNodeIndex$runtime, iIndexSegment);
    }

    public final TrieNode<K, V> mutableRemove(int keyHash, K key, int shift, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNodeMutableRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(iIndexSegment)) {
            int iEntryKeyIndex$runtime = entryKeyIndex$runtime(iIndexSegment);
            if (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$runtime))) {
                return mutableRemoveEntryAtIndex(iEntryKeyIndex$runtime, iIndexSegment, mutator);
            }
        } else if (hasNodeAt(iIndexSegment)) {
            int iNodeIndex$runtime = nodeIndex$runtime(iIndexSegment);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(iNodeIndex$runtime);
            if (shift == 30) {
                trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableCollisionRemove(key, mutator);
            } else {
                trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableRemove(keyHash, key, shift + 5, mutator);
            }
            return mutableReplaceNode(trieNodeNodeAtIndex$runtime, trieNodeMutableRemove, iNodeIndex$runtime, iIndexSegment, mutator.getOwnership());
        }
        return this;
    }
}
