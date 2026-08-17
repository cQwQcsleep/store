package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0010*\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u001d\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u001eJ\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J\b\u0010 \u001a\u00020\u000bH\u0002J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0002J\b\u0010!\u001a\u00020\u000bH\u0002J\u001d\u0010\"\u001a\u00020#2\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010%J'\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010'J-\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010)\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010*J\u001f\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\b\u0010,\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010-J\u0015\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010\u0018J\u0015\u0010/\u001a\u00020#2\u0006\u0010,\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00100J?\u00101\u001a\u0002022\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u00105J?\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u00107\u001a\u00020\u000bH\u0002¢\u0006\u0002\u00108J\u0016\u00109\u001a\u00020#2\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;H\u0016J=\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010=\u001a\u00020\u000b2\u000e\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0?H\u0002¢\u0006\u0002\u0010@JE\u0010A\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010 \u001a\u00020\u000b2\u0014\u0010B\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0007H\u0002¢\u0006\u0002\u0010CJM\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010 \u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0014\u0010E\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070?H\u0002¢\u0006\u0002\u0010FJ\u001d\u0010/\u001a\u0002022\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010,\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010HJ/\u0010I\u001a\u0002022\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010,\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010JJG\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u00107\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020\u000b2\b\u0010,\u001a\u0004\u0018\u00010\b2\u0006\u0010L\u001a\u00020MH\u0002¢\u0006\u0002\u0010NJ\u001e\u00109\u001a\u00020#2\u0006\u0010G\u001a\u00020\u000b2\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;H\u0016J[\u0010K\u001a\u0002022\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;2\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010O\u001a\u00020\u000b2\u0016\u0010B\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00072\u0006\u0010P\u001a\u00020\u000b2\u000e\u0010Q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010RJU\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010T\u001a\u00020\u000b2\u0006\u0010O\u001a\u00020\u000b2\u0016\u0010B\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00072\u0006\u0010P\u001a\u00020\u000b2\u000e\u0010Q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010UJk\u0010V\u001a\u0002022\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;2\u0006\u0010G\u001a\u00020\u000b2\u000e\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010X\u001a\u00020\u000b2\u0016\u0010B\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00072\u0006\u0010P\u001a\u00020\u000b2\u000e\u0010Q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002¢\u0006\u0002\u0010YJ\u0016\u0010Z\u001a\u00028\u00002\u0006\u0010G\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0002\u0010[J\u001d\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010G\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010]J\u0015\u0010^\u001a\u00028\u00002\u0006\u0010G\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010[J9\u0010_\u001a\u0004\u0018\u00010\b2\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010 \u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010`J=\u0010a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u00107\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010b\u001a\u00020MH\u0002¢\u0006\u0002\u0010cJ/\u0010d\u001a\u0002022\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0006\u0010 \u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010eJ?\u0010f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u00107\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010b\u001a\u00020MH\u0002¢\u0006\u0002\u0010cJ\u0016\u0010g\u001a\u00020#2\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;H\u0016J\u001a\u0010h\u001a\u00020#2\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020#0jJ\u001c\u0010g\u001a\u00020#2\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020#0jH\u0002J/\u0010k\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\u001b\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010*J5\u0010l\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010G\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010mJ,\u0010n\u001a\u00020\u000b2\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020#0j2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010o\u001a\u00020MH\u0002JA\u0010g\u001a\u00020\u000b2\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020#0j2\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010p\u001a\u00020\u000b2\u0006\u0010o\u001a\u00020MH\u0002¢\u0006\u0002\u0010qJu\u0010r\u001a\u00020\u000b2\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020#0j2\u000e\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010p\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020\u000b2\u0006\u0010o\u001a\u00020M2\u0014\u0010t\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070u2\u0014\u0010B\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070uH\u0002¢\u0006\u0002\u0010vJ\u001e\u0010w\u001a\u00028\u00002\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010,\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010xJE\u0010y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u00107\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010z\u001a\u00028\u00002\u0006\u0010{\u001a\u00020MH\u0002¢\u0006\u0002\u0010NJ\u000f\u0010|\u001a\b\u0012\u0004\u0012\u00028\u00000}H\u0096\u0002J\u000e\u0010~\u001a\b\u0012\u0004\u0012\u00028\u00000\u007fH\u0016J\u0016\u0010~\u001a\b\u0012\u0004\u0012\u00028\u00000\u007f2\u0006\u0010G\u001a\u00020\u000bH\u0016J \u0010\u0080\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0081\u00012\u0006\u0010G\u001a\u00020\u000bH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u001a\u0010\n\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R4\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00072\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0017\u0010\u0018R0\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u001a\u0010\u0018R\u001e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010¨\u0006\u0082\u0001"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVectorBuilder;", "E", "Lkotlin/collections/AbstractMutableList;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList$Builder;", "vector", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "vectorRoot", "", "", "vectorTail", "rootShift", "", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;[Ljava/lang/Object;[Ljava/lang/Object;I)V", "[Ljava/lang/Object;", "getRootShift$runtime", "()I", "setRootShift$runtime", "(I)V", "ownership", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "value", "root", "getRoot$runtime", "()[Ljava/lang/Object;", "tail", "getTail$runtime", "size", "getSize", "getModCount", "getModCount$runtime", "build", "rootSize", "tailSize", "isMutable", "", "buffer", "([Ljava/lang/Object;)Z", "makeMutable", "([Ljava/lang/Object;)[Ljava/lang/Object;", "makeMutableShiftingRight", "distance", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "mutableBufferWith", "element", "(Ljava/lang/Object;)[Ljava/lang/Object;", "mutableBuffer", "add", "(Ljava/lang/Object;)Z", "pushFilledTail", "", "filledTail", "newTail", "([Ljava/lang/Object;[Ljava/lang/Object;[Ljava/lang/Object;)V", "pushTail", "shift", "([Ljava/lang/Object;[Ljava/lang/Object;I)[Ljava/lang/Object;", "addAll", PathTree.NAME_elements, "", "copyToBuffer", "bufferIndex", "sourceIterator", "", "([Ljava/lang/Object;ILjava/util/Iterator;)[Ljava/lang/Object;", "pushBuffersIncreasingHeightIfNeeded", "buffers", "([Ljava/lang/Object;I[[Ljava/lang/Object;)[Ljava/lang/Object;", "pushBuffers", "buffersIterator", "([Ljava/lang/Object;IILjava/util/Iterator;)[Ljava/lang/Object;", "index", "(ILjava/lang/Object;)V", "insertIntoTail", "([Ljava/lang/Object;ILjava/lang/Object;)V", "insertIntoRoot", "elementCarry", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;", "([Ljava/lang/Object;IILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "rightShift", "nullBuffers", "nextBuffer", "(Ljava/util/Collection;II[[Ljava/lang/Object;I[Ljava/lang/Object;)V", "shiftLeafBuffers", "startLeafIndex", "(II[[Ljava/lang/Object;I[Ljava/lang/Object;)[Ljava/lang/Object;", "splitToBuffers", "startBuffer", "startBufferSize", "(Ljava/util/Collection;I[Ljava/lang/Object;I[[Ljava/lang/Object;I[Ljava/lang/Object;)V", "get", "(I)Ljava/lang/Object;", "bufferFor", "(I)[Ljava/lang/Object;", "removeAt", "removeFromTailAt", "([Ljava/lang/Object;III)Ljava/lang/Object;", "removeFromRootAt", "tailCarry", "([Ljava/lang/Object;IILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "pullLastBufferFromRoot", "([Ljava/lang/Object;II)V", "pullLastBuffer", "removeAll", "removeAllWithPredicate", "predicate", "Lkotlin/Function1;", "retainFirst", "nullifyAfter", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "removeAllFromTail", "bufferRef", "bufferSize", "(Lkotlin/jvm/functions/Function1;[Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)I", "recyclableRemoveAll", "toBufferSize", "recyclableBuffers", "", "(Lkotlin/jvm/functions/Function1;[Ljava/lang/Object;IILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;Ljava/util/List;Ljava/util/List;)I", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "setInRoot", "e", "oldElementCarry", "iterator", "", "listIterator", "", "leafBufferIterator", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PersistentVectorBuilder<E> extends AbstractMutableList<E> implements PersistentList.Builder<E> {
    public static final int $stable = 8;
    private MutabilityOwnership ownership = new MutabilityOwnership();
    private Object[] root;
    private int rootShift;
    private int size;
    private Object[] tail;
    private PersistentList<? extends E> vector;
    private Object[] vectorRoot;
    private Object[] vectorTail;

    public PersistentVectorBuilder(PersistentList<? extends E> persistentList, Object[] objArr, Object[] objArr2, int i) {
        this.vector = persistentList;
        this.vectorRoot = objArr;
        this.vectorTail = objArr2;
        this.rootShift = i;
        this.root = this.vectorRoot;
        this.tail = this.vectorTail;
        this.size = this.vector.size();
    }

    public static boolean b(Collection collection, Object obj) {
        return collection.contains(obj);
    }

    private final Object[] bufferFor(int index) {
        if (rootSize() <= index) {
            return this.tail;
        }
        Object[] objArr = this.root;
        objArr.getClass();
        for (int i = this.rootShift; i > 0; i -= 5) {
            Object[] objArr2 = objArr[UtilsKt.indexSegment(index, i)];
            objArr2.getClass();
            objArr = objArr2;
        }
        return objArr;
    }

    private final Object[] copyToBuffer(Object[] buffer, int bufferIndex, Iterator<? extends Object> sourceIterator) {
        while (bufferIndex < 32 && sourceIterator.hasNext()) {
            buffer[bufferIndex] = sourceIterator.next();
            bufferIndex++;
        }
        return buffer;
    }

    private final Object[] insertIntoRoot(Object[] root, int shift, int index, Object element, ObjectRef elementCarry) {
        Object obj;
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            elementCarry.setValue(root[31]);
            Object[] objArrCopyInto = ArraysKt.copyInto(root, makeMutable(root), iIndexSegment + 1, iIndexSegment, 31);
            objArrCopyInto[iIndexSegment] = element;
            return objArrCopyInto;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        int i = shift - 5;
        Object obj2 = objArrMakeMutable[iIndexSegment];
        obj2.getClass();
        objArrMakeMutable[iIndexSegment] = insertIntoRoot((Object[]) obj2, i, index, element, elementCarry);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || (obj = objArrMakeMutable[iIndexSegment]) == null) {
                break;
            }
            obj.getClass();
            objArrMakeMutable[iIndexSegment] = insertIntoRoot((Object[]) obj, i, 0, elementCarry.getValue(), elementCarry);
        }
        return objArrMakeMutable;
    }

    private final void insertIntoTail(Object[] root, int index, E element) {
        int iTailSize = tailSize();
        Object[] objArrMakeMutable = makeMutable(this.tail);
        Object[] objArr = this.tail;
        if (iTailSize >= 32) {
            Object obj = objArr[31];
            ArraysKt.copyInto(objArr, objArrMakeMutable, index + 1, index, 31);
            objArrMakeMutable[index] = element;
            pushFilledTail(root, objArrMakeMutable, mutableBufferWith(obj));
            return;
        }
        ArraysKt.copyInto(objArr, objArrMakeMutable, index + 1, index, iTailSize);
        objArrMakeMutable[index] = element;
        this.root = root;
        this.tail = objArrMakeMutable;
        this.size = size() + 1;
    }

    private final boolean isMutable(Object[] buffer) {
        return buffer.length == 33 && buffer[32] == this.ownership;
    }

    private final ListIterator<Object[]> leafBufferIterator(int index) {
        Object[] objArr = this.root;
        if (objArr == null) {
            k2d.a("Invalid root");
            return null;
        }
        int iRootSize = rootSize() >> 5;
        ListImplementation.checkPositionIndex$runtime(index, iRootSize);
        int i = this.rootShift;
        return i == 0 ? new SingleElementListIterator(objArr, index) : new TrieIterator(objArr, index, iRootSize, i / 5);
    }

    private final Object[] makeMutable(Object[] buffer) {
        if (buffer == null) {
            return mutableBuffer();
        }
        return isMutable(buffer) ? buffer : ArraysKt.copyInto$default(buffer, mutableBuffer(), 0, 0, RangesKt.coerceAtMost(buffer.length, 32), 6, (Object) null);
    }

    private final Object[] makeMutableShiftingRight(Object[] buffer, int distance) {
        return isMutable(buffer) ? ArraysKt.copyInto(buffer, buffer, distance, 0, 32 - distance) : ArraysKt.copyInto(buffer, mutableBuffer(), distance, 0, 32 - distance);
    }

    private final Object[] mutableBuffer() {
        Object[] objArr = new Object[33];
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] mutableBufferWith(Object element) {
        Object[] objArr = new Object[33];
        objArr[0] = element;
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] nullifyAfter(Object[] root, int index, int shift) {
        if (!(shift >= 0)) {
            PreconditionsKt.throwIllegalArgumentException("shift should be positive");
        }
        if (shift == 0) {
            return root;
        }
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object obj = root[iIndexSegment];
        obj.getClass();
        Object objNullifyAfter = nullifyAfter((Object[]) obj, index, shift - 5);
        if (iIndexSegment < 31) {
            int i = iIndexSegment + 1;
            if (root[i] != null) {
                if (isMutable(root)) {
                    ArraysKt.fill(root, (Object) null, i, 32);
                }
                root = ArraysKt.copyInto(root, mutableBuffer(), 0, 0, i);
            }
        }
        if (objNullifyAfter == root[iIndexSegment]) {
            return root;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        objArrMakeMutable[iIndexSegment] = objNullifyAfter;
        return objArrMakeMutable;
    }

    private final Object[] pullLastBuffer(Object[] root, int shift, int rootSize, ObjectRef tailCarry) {
        Object[] objArrPullLastBuffer;
        int iIndexSegment = UtilsKt.indexSegment(rootSize - 1, shift);
        if (shift == 5) {
            tailCarry.setValue(root[iIndexSegment]);
            objArrPullLastBuffer = null;
        } else {
            Object obj = root[iIndexSegment];
            obj.getClass();
            objArrPullLastBuffer = pullLastBuffer((Object[]) obj, shift - 5, rootSize, tailCarry);
        }
        if (objArrPullLastBuffer == null && iIndexSegment == 0) {
            return null;
        }
        Object[] objArrMakeMutable = makeMutable(root);
        objArrMakeMutable[iIndexSegment] = objArrPullLastBuffer;
        return objArrMakeMutable;
    }

    private final void pullLastBufferFromRoot(Object[] root, int rootSize, int shift) {
        if (shift == 0) {
            this.root = null;
            if (root == null) {
                root = new Object[0];
            }
            this.tail = root;
            this.size = rootSize;
            this.rootShift = shift;
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        root.getClass();
        Object[] objArrPullLastBuffer = pullLastBuffer(root, shift, rootSize, objectRef);
        objArrPullLastBuffer.getClass();
        Object value = objectRef.getValue();
        value.getClass();
        this.tail = (Object[]) value;
        this.size = rootSize;
        if (objArrPullLastBuffer[1] == null) {
            this.root = (Object[]) objArrPullLastBuffer[0];
            this.rootShift = shift - 5;
        } else {
            this.root = objArrPullLastBuffer;
            this.rootShift = shift;
        }
    }

    private final Object[] pushBuffers(Object[] root, int rootSize, int shift, Iterator<Object[]> buffersIterator) {
        if (!buffersIterator.hasNext()) {
            PreconditionsKt.throwIllegalArgumentException("invalid buffersIterator");
        }
        if (!(shift >= 0)) {
            PreconditionsKt.throwIllegalArgumentException("negative shift");
        }
        if (shift == 0) {
            return buffersIterator.next();
        }
        Object[] objArrMakeMutable = makeMutable(root);
        int iIndexSegment = UtilsKt.indexSegment(rootSize, shift);
        int i = shift - 5;
        objArrMakeMutable[iIndexSegment] = pushBuffers((Object[]) objArrMakeMutable[iIndexSegment], rootSize, i, buffersIterator);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || !buffersIterator.hasNext()) {
                break;
            }
            objArrMakeMutable[iIndexSegment] = pushBuffers((Object[]) objArrMakeMutable[iIndexSegment], 0, i, buffersIterator);
        }
        return objArrMakeMutable;
    }

    private final Object[] pushBuffersIncreasingHeightIfNeeded(Object[] root, int rootSize, Object[][] buffers) {
        Iterator<Object[]> it = ArrayIteratorKt.iterator(buffers);
        int i = rootSize >> 5;
        int i2 = this.rootShift;
        Object[] objArrPushBuffers = i < (1 << i2) ? pushBuffers(root, rootSize, i2, it) : makeMutable(root);
        while (it.hasNext()) {
            this.rootShift += 5;
            objArrPushBuffers = mutableBufferWith(objArrPushBuffers);
            int i3 = this.rootShift;
            pushBuffers(objArrPushBuffers, 1 << i3, i3, it);
        }
        return objArrPushBuffers;
    }

    private final void pushFilledTail(Object[] root, Object[] filledTail, Object[] newTail) {
        int size = size() >> 5;
        int i = this.rootShift;
        if (size > (1 << i)) {
            this.root = pushTail(mutableBufferWith(root), filledTail, this.rootShift + 5);
            this.tail = newTail;
            this.rootShift += 5;
            this.size = size() + 1;
            return;
        }
        if (root == null) {
            this.root = filledTail;
            this.tail = newTail;
            this.size = size() + 1;
        } else {
            this.root = pushTail(root, filledTail, i);
            this.tail = newTail;
            this.size = size() + 1;
        }
    }

    private final Object[] pushTail(Object[] root, Object[] tail, int shift) {
        int iIndexSegment = UtilsKt.indexSegment(size() - 1, shift);
        Object[] objArrMakeMutable = makeMutable(root);
        if (shift == 5) {
            objArrMakeMutable[iIndexSegment] = tail;
            return objArrMakeMutable;
        }
        objArrMakeMutable[iIndexSegment] = pushTail((Object[]) objArrMakeMutable[iIndexSegment], tail, shift - 5);
        return objArrMakeMutable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int recyclableRemoveAll(Function1<? super E, Boolean> predicate, Object[] buffer, int bufferSize, int toBufferSize, ObjectRef bufferRef, List<Object[]> recyclableBuffers, List<Object[]> buffers) {
        if (isMutable(buffer)) {
            recyclableBuffers.add(buffer);
        }
        Object value = bufferRef.getValue();
        value.getClass();
        Object[] objArr = (Object[]) value;
        Object[] objArrRemove = objArr;
        for (int i = 0; i < bufferSize; i++) {
            Object obj = buffer[i];
            if (!((Boolean) predicate.invoke(obj)).booleanValue()) {
                if (toBufferSize == 32) {
                    objArrRemove = !recyclableBuffers.isEmpty() ? recyclableBuffers.remove(recyclableBuffers.size() - 1) : mutableBuffer();
                    toBufferSize = 0;
                }
                objArrRemove[toBufferSize] = obj;
                toBufferSize++;
            }
        }
        bufferRef.setValue(objArrRemove);
        if (objArr != bufferRef.getValue()) {
            buffers.add(objArr);
        }
        return toBufferSize;
    }

    private final boolean removeAll(Function1<? super E, Boolean> predicate) {
        int iTailSize = tailSize();
        ObjectRef objectRef = new ObjectRef(null);
        if (this.root == null) {
            return removeAllFromTail(predicate, iTailSize, objectRef) != iTailSize;
        }
        ListIterator<Object[]> listIteratorLeafBufferIterator = leafBufferIterator(0);
        int iRemoveAll = 32;
        while (iRemoveAll == 32 && listIteratorLeafBufferIterator.hasNext()) {
            iRemoveAll = removeAll(predicate, listIteratorLeafBufferIterator.next(), 32, objectRef);
        }
        if (iRemoveAll == 32) {
            CommonFunctionsKt.m2511assert(!listIteratorLeafBufferIterator.hasNext());
            int iRemoveAllFromTail = removeAllFromTail(predicate, iTailSize, objectRef);
            if (iRemoveAllFromTail == 0) {
                pullLastBufferFromRoot(this.root, size(), this.rootShift);
            }
            return iRemoveAllFromTail != iTailSize;
        }
        int iPreviousIndex = listIteratorLeafBufferIterator.previousIndex() << 5;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iRecyclableRemoveAll = iRemoveAll;
        while (listIteratorLeafBufferIterator.hasNext()) {
            iRecyclableRemoveAll = recyclableRemoveAll(predicate, listIteratorLeafBufferIterator.next(), 32, iRecyclableRemoveAll, objectRef, arrayList2, arrayList);
        }
        int iRecyclableRemoveAll2 = recyclableRemoveAll(predicate, this.tail, iTailSize, iRecyclableRemoveAll, objectRef, arrayList2, arrayList);
        Object value = objectRef.getValue();
        value.getClass();
        Object[] objArr = (Object[]) value;
        ArraysKt.fill(objArr, (Object) null, iRecyclableRemoveAll2, 32);
        boolean zIsEmpty = arrayList.isEmpty();
        Object[] objArrPushBuffers = this.root;
        if (zIsEmpty) {
            objArrPushBuffers.getClass();
        } else {
            objArrPushBuffers = pushBuffers(objArrPushBuffers, iPreviousIndex, this.rootShift, arrayList.iterator());
        }
        int size = iPreviousIndex + (arrayList.size() << 5);
        this.root = retainFirst(objArrPushBuffers, size);
        this.tail = objArr;
        this.size = size + iRecyclableRemoveAll2;
        return true;
    }

    private final int removeAllFromTail(Function1<? super E, Boolean> predicate, int tailSize, ObjectRef bufferRef) {
        int iRemoveAll = removeAll(predicate, this.tail, tailSize, bufferRef);
        if (iRemoveAll == tailSize) {
            CommonFunctionsKt.m2511assert(bufferRef.getValue() == this.tail);
            return tailSize;
        }
        Object value = bufferRef.getValue();
        value.getClass();
        Object[] objArr = (Object[]) value;
        ArraysKt.fill(objArr, (Object) null, iRemoveAll, tailSize);
        this.tail = objArr;
        this.size = size() - (tailSize - iRemoveAll);
        return iRemoveAll;
    }

    private final Object[] removeFromRootAt(Object[] root, int shift, int index, ObjectRef tailCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            Object obj = root[iIndexSegment];
            Object[] objArrCopyInto = ArraysKt.copyInto(root, makeMutable(root), iIndexSegment, iIndexSegment + 1, 32);
            objArrCopyInto[31] = tailCarry.getValue();
            tailCarry.setValue(obj);
            return objArrCopyInto;
        }
        int iIndexSegment2 = root[31] == null ? UtilsKt.indexSegment(rootSize() - 1, shift) : 31;
        Object[] objArrMakeMutable = makeMutable(root);
        int i = shift - 5;
        int i2 = iIndexSegment + 1;
        if (i2 <= iIndexSegment2) {
            while (true) {
                Object obj2 = objArrMakeMutable[iIndexSegment2];
                obj2.getClass();
                objArrMakeMutable[iIndexSegment2] = removeFromRootAt((Object[]) obj2, i, 0, tailCarry);
                if (iIndexSegment2 == i2) {
                    break;
                }
                iIndexSegment2--;
            }
        }
        Object obj3 = objArrMakeMutable[iIndexSegment];
        obj3.getClass();
        objArrMakeMutable[iIndexSegment] = removeFromRootAt((Object[]) obj3, i, index, tailCarry);
        return objArrMakeMutable;
    }

    private final Object removeFromTailAt(Object[] root, int rootSize, int shift, int index) {
        int size = size() - rootSize;
        CommonFunctionsKt.m2511assert(index < size);
        Object[] objArr = this.tail;
        if (size == 1) {
            Object obj = objArr[0];
            pullLastBufferFromRoot(root, rootSize, shift);
            return obj;
        }
        Object obj2 = objArr[index];
        Object[] objArrCopyInto = ArraysKt.copyInto(objArr, makeMutable(objArr), index, index + 1, size);
        objArrCopyInto[size - 1] = null;
        this.root = root;
        this.tail = objArrCopyInto;
        this.size = (rootSize + size) - 1;
        this.rootShift = shift;
        return obj2;
    }

    private final Object[] retainFirst(Object[] root, int size) {
        if (!((size & 31) == 0)) {
            PreconditionsKt.throwIllegalArgumentException("invalid size");
        }
        if (size == 0) {
            this.rootShift = 0;
            return null;
        }
        int i = size - 1;
        while (true) {
            int i2 = this.rootShift;
            if ((i >> i2) != 0) {
                return nullifyAfter(root, i, i2);
            }
            this.rootShift = i2 - 5;
            Object[] objArr = root[0];
            objArr.getClass();
            root = objArr;
        }
    }

    private final int rootSize() {
        if (size() <= 32) {
            return 0;
        }
        return UtilsKt.rootSize(size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object[] setInRoot(Object[] root, int shift, int index, E e, ObjectRef oldElementCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object[] objArrMakeMutable = makeMutable(root);
        if (shift != 0) {
            Object obj = objArrMakeMutable[iIndexSegment];
            obj.getClass();
            objArrMakeMutable[iIndexSegment] = setInRoot((Object[]) obj, shift - 5, index, e, oldElementCarry);
            return objArrMakeMutable;
        }
        if (objArrMakeMutable != root) {
            ((AbstractList) this).modCount++;
        }
        oldElementCarry.setValue(objArrMakeMutable[iIndexSegment]);
        objArrMakeMutable[iIndexSegment] = e;
        return objArrMakeMutable;
    }

    private final Object[] shiftLeafBuffers(int startLeafIndex, int rightShift, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        if (this.root == null) {
            k2d.a("root is null");
            return null;
        }
        ListIterator<Object[]> listIteratorLeafBufferIterator = leafBufferIterator(rootSize() >> 5);
        while (listIteratorLeafBufferIterator.previousIndex() != startLeafIndex) {
            Object[] objArrPrevious = listIteratorLeafBufferIterator.previous();
            ArraysKt.copyInto(objArrPrevious, nextBuffer, 0, 32 - rightShift, 32);
            nextBuffer = makeMutableShiftingRight(objArrPrevious, rightShift);
            nullBuffers--;
            buffers[nullBuffers] = nextBuffer;
        }
        return listIteratorLeafBufferIterator.previous();
    }

    private final void splitToBuffers(Collection<? extends E> elements, int index, Object[] startBuffer, int startBufferSize, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        Object[] objArrMutableBuffer;
        if (!(nullBuffers >= 1)) {
            PreconditionsKt.throwIllegalArgumentException("requires at least one nullBuffer");
        }
        Object[] objArrMakeMutable = makeMutable(startBuffer);
        buffers[0] = objArrMakeMutable;
        int i = index & 31;
        int size = ((index + elements.size()) - 1) & 31;
        int i2 = (startBufferSize - i) + size;
        if (i2 < 32) {
            ArraysKt.copyInto(objArrMakeMutable, nextBuffer, size + 1, i, startBufferSize);
        } else {
            int i3 = i2 - 31;
            if (nullBuffers == 1) {
                objArrMutableBuffer = objArrMakeMutable;
            } else {
                objArrMutableBuffer = mutableBuffer();
                nullBuffers--;
                buffers[nullBuffers] = objArrMutableBuffer;
            }
            int i4 = startBufferSize - i3;
            ArraysKt.copyInto(objArrMakeMutable, nextBuffer, 0, i4, startBufferSize);
            ArraysKt.copyInto(objArrMakeMutable, objArrMutableBuffer, size + 1, i, i4);
            nextBuffer = objArrMutableBuffer;
        }
        Iterator<? extends E> it = elements.iterator();
        copyToBuffer(objArrMakeMutable, i, it);
        for (int i5 = 1; i5 < nullBuffers; i5++) {
            buffers[i5] = copyToBuffer(mutableBuffer(), 0, it);
        }
        copyToBuffer(nextBuffer, 0, it);
    }

    private final int tailSize(int size) {
        return size <= 32 ? size : size - UtilsKt.rootSize(size);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public void add(int index, E element) {
        ListImplementation.checkPositionIndex$runtime(index, size());
        if (index == size()) {
            add(element);
            return;
        }
        ((AbstractList) this).modCount++;
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            insertIntoTail(this.root, index - iRootSize, element);
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArr = this.root;
        objArr.getClass();
        insertIntoTail(insertIntoRoot(objArr, this.rootShift, index, element, objectRef), 0, objectRef.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends E> elements) {
        Collection<? extends E> collection;
        Object[] objArrCopyInto;
        Object[][] objArr;
        PersistentVectorBuilder persistentVectorBuilder;
        ListImplementation.checkPositionIndex$runtime(index, size());
        if (index == size()) {
            return addAll(elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i = (index >> 5) << 5;
        int size = (((size() - i) + elements.size()) - 1) / 32;
        if (size == 0) {
            CommonFunctionsKt.m2511assert(index >= rootSize());
            int i2 = index & 31;
            int size2 = ((index + elements.size()) - 1) & 31;
            Object[] objArr2 = this.tail;
            Object[] objArrCopyInto2 = ArraysKt.copyInto(objArr2, makeMutable(objArr2), size2 + 1, i2, tailSize());
            copyToBuffer(objArrCopyInto2, i2, elements.iterator());
            this.tail = objArrCopyInto2;
            this.size = size() + elements.size();
            return true;
        }
        Object[][] objArr3 = new Object[size][];
        int iTailSize = tailSize();
        int iTailSize2 = tailSize(size() + elements.size());
        if (index >= rootSize()) {
            objArrCopyInto = mutableBuffer();
            objArr = objArr3;
            PersistentVectorBuilder persistentVectorBuilder2 = this;
            collection = elements;
            persistentVectorBuilder2.splitToBuffers(collection, index, this.tail, iTailSize, objArr, size, objArrCopyInto);
            persistentVectorBuilder = persistentVectorBuilder2;
        } else {
            PersistentVectorBuilder persistentVectorBuilder3 = this;
            collection = elements;
            Object[] objArr4 = persistentVectorBuilder3.tail;
            if (iTailSize2 > iTailSize) {
                int i3 = iTailSize2 - iTailSize;
                Object[] objArrMakeMutableShiftingRight = persistentVectorBuilder3.makeMutableShiftingRight(objArr4, i3);
                persistentVectorBuilder3.insertIntoRoot(collection, index, i3, objArr3, size, objArrMakeMutableShiftingRight);
                objArr = objArr3;
                objArrCopyInto = objArrMakeMutableShiftingRight;
                persistentVectorBuilder = persistentVectorBuilder3;
            } else {
                int i4 = iTailSize - iTailSize2;
                objArrCopyInto = ArraysKt.copyInto(objArr4, persistentVectorBuilder3.mutableBuffer(), 0, i4, iTailSize);
                int i5 = 32 - i4;
                Object[] objArrMakeMutableShiftingRight2 = persistentVectorBuilder3.makeMutableShiftingRight(persistentVectorBuilder3.tail, i5);
                int i6 = size - 1;
                objArr3[i6] = objArrMakeMutableShiftingRight2;
                persistentVectorBuilder3.insertIntoRoot(collection, index, i5, objArr3, i6, objArrMakeMutableShiftingRight2);
                collection = collection;
                objArr = objArr3;
                persistentVectorBuilder = persistentVectorBuilder3;
            }
        }
        persistentVectorBuilder.root = persistentVectorBuilder.pushBuffersIncreasingHeightIfNeeded(persistentVectorBuilder.root, i, objArr);
        persistentVectorBuilder.tail = objArrCopyInto;
        persistentVectorBuilder.size = persistentVectorBuilder.size() + collection.size();
        return true;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection.Builder
    public PersistentList<E> build() {
        PersistentVector persistentVector;
        if (this.root == this.vectorRoot && this.tail == this.vectorTail) {
            persistentVector = this.vector;
        } else {
            this.ownership = new MutabilityOwnership();
            Object[] objArr = this.root;
            this.vectorRoot = objArr;
            Object[] objArr2 = this.tail;
            this.vectorTail = objArr2;
            if (objArr == null) {
                persistentVector = objArr2.length == 0 ? UtilsKt.persistentVectorOf() : new SmallPersistentVector(Arrays.copyOf(this.tail, size()));
            } else {
                Object[] objArr3 = this.root;
                objArr3.getClass();
                persistentVector = new PersistentVector(objArr3, this.tail, size(), this.rootShift);
            }
        }
        this.vector = persistentVector;
        return (PersistentList<E>) persistentVector;
    }

    @Override // java.util.List
    public E get(int index) {
        ListImplementation.checkElementIndex$runtime(index, size());
        return (E) bufferFor(index)[index & 31];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getModCount$runtime() {
        return ((AbstractList) this).modCount;
    }

    /* JADX INFO: renamed from: getRoot$runtime, reason: from getter */
    public final Object[] getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: getRootShift$runtime, reason: from getter */
    public final int getRootShift() {
        return this.rootShift;
    }

    public int getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: getTail$runtime, reason: from getter */
    public final Object[] getTail() {
        return this.tail;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        ListImplementation.checkPositionIndex$runtime(index, size());
        return new PersistentVectorMutableIterator(this, index);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean removeAllWithPredicate(Function1<? super E, Boolean> predicate) {
        boolean zRemoveAll = removeAll(predicate);
        if (zRemoveAll) {
            ((AbstractList) this).modCount++;
        }
        return zRemoveAll;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public E removeAt(int index) {
        ListImplementation.checkElementIndex$runtime(index, size());
        ((AbstractList) this).modCount++;
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            return (E) removeFromTailAt(this.root, iRootSize, this.rootShift, index - iRootSize);
        }
        ObjectRef objectRef = new ObjectRef(this.tail[0]);
        Object[] objArr = this.root;
        objArr.getClass();
        removeFromTailAt(removeFromRootAt(objArr, this.rootShift, index, objectRef), iRootSize, this.rootShift, 0);
        return (E) objectRef.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public E set(int index, E element) {
        ListImplementation.checkElementIndex$runtime(index, size());
        if (rootSize() > index) {
            ObjectRef objectRef = new ObjectRef(null);
            Object[] objArr = this.root;
            objArr.getClass();
            this.root = setInRoot(objArr, this.rootShift, index, element, objectRef);
            return (E) objectRef.getValue();
        }
        Object[] objArrMakeMutable = makeMutable(this.tail);
        if (objArrMakeMutable != this.tail) {
            ((AbstractList) this).modCount++;
        }
        int i = index & 31;
        E e = (E) objArrMakeMutable[i];
        objArrMakeMutable[i] = element;
        this.tail = objArrMakeMutable;
        return e;
    }

    public final void setRootShift$runtime(int i) {
        this.rootShift = i;
    }

    private final int tailSize() {
        return tailSize(size());
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public boolean add(E element) {
        ((AbstractList) this).modCount++;
        int iTailSize = tailSize();
        if (iTailSize < 32) {
            Object[] objArrMakeMutable = makeMutable(this.tail);
            objArrMakeMutable[iTailSize] = element;
            this.tail = objArrMakeMutable;
            this.size = size() + 1;
        } else {
            pushFilledTail(this.root, this.tail, mutableBufferWith(element));
        }
        return true;
    }

    private final void insertIntoRoot(Collection<? extends E> elements, int index, int rightShift, Object[][] buffers, int nullBuffers, Object[] nextBuffer) {
        Object[] objArr;
        if (this.root != null) {
            int i = index >> 5;
            Object[] objArrShiftLeafBuffers = shiftLeafBuffers(i, rightShift, buffers, nullBuffers, nextBuffer);
            int iRootSize = nullBuffers - (((rootSize() >> 5) - 1) - i);
            if (iRootSize < nullBuffers) {
                Object[] objArr2 = buffers[iRootSize];
                objArr2.getClass();
                objArr = objArr2;
            } else {
                objArr = nextBuffer;
            }
            splitToBuffers(elements, index, objArrShiftLeafBuffers, 32, buffers, iRootSize, objArr);
            return;
        }
        k2d.a("root is null");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(final Collection<?> elements) {
        return removeAllWithPredicate(new Function1() { // from class: r1b
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PersistentVectorBuilder.b(elements, obj));
            }
        });
    }

    private final int removeAll(Function1<? super E, Boolean> predicate, Object[] buffer, int bufferSize, ObjectRef bufferRef) {
        Object[] objArrMakeMutable = buffer;
        int i = bufferSize;
        boolean z = false;
        for (int i2 = 0; i2 < bufferSize; i2++) {
            Object obj = buffer[i2];
            if (((Boolean) predicate.invoke(obj)).booleanValue()) {
                if (!z) {
                    objArrMakeMutable = makeMutable(buffer);
                    z = true;
                    i = i2;
                }
            } else if (z) {
                objArrMakeMutable[i] = obj;
                i++;
            }
        }
        bufferRef.setValue(objArrMakeMutable);
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int iTailSize = tailSize();
        Iterator<? extends E> it = elements.iterator();
        if (32 - iTailSize >= elements.size()) {
            this.tail = copyToBuffer(makeMutable(this.tail), iTailSize, it);
            this.size = size() + elements.size();
        } else {
            int size = ((elements.size() + iTailSize) - 1) / 32;
            Object[][] objArr = new Object[size][];
            objArr[0] = copyToBuffer(makeMutable(this.tail), iTailSize, it);
            for (int i = 1; i < size; i++) {
                objArr[i] = copyToBuffer(mutableBuffer(), 0, it);
            }
            this.root = pushBuffersIncreasingHeightIfNeeded(this.root, rootSize(), objArr);
            this.tail = copyToBuffer(mutableBuffer(), 0, it);
            this.size = size() + elements.size();
        }
        return true;
    }
}
