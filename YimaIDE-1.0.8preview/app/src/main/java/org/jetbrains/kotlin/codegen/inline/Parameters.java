package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.Parameters;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0002J\u000e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u000eJ\u0010\u0010 \u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u000eH\u0002J\u0010\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\"H\u0096\u0082\u0004R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0015\u0010\u0011R!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u001a\u0010\bR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010\b¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/Parameters;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "parameters", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getParameters", "()Ljava/util/List;", "actualDeclShifts", Argument.Delimiters.none, "[Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "paramToDeclByteCodeIndex", "Ljava/util/HashMap;", Argument.Delimiters.none, "argsSizeOnStack", "getArgsSizeOnStack", "()I", "realParametersSizeOnStack", "getRealParametersSizeOnStack", "capturedParametersSizeOnStack", "getCapturedParametersSizeOnStack", "capturedParametersSizeOnStack$delegate", "Lkotlin/Lazy;", "captured", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "getCaptured", "captured$delegate", "getDeclarationSlot", "info", "getParameterByDeclarationSlot", "index", "get", "iterator", Argument.Delimiters.none, "capturedTypes", "Lorg/jetbrains/org/objectweb/asm/Type;", "getCapturedTypes", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Parameters implements Iterable<ParameterInfo>, KMappedMarker {
    private final ParameterInfo[] actualDeclShifts;
    private final int argsSizeOnStack;

    /* JADX INFO: renamed from: captured$delegate, reason: from kotlin metadata */
    private final Lazy captured;

    /* JADX INFO: renamed from: capturedParametersSizeOnStack$delegate, reason: from kotlin metadata */
    private final Lazy capturedParametersSizeOnStack;
    private final HashMap<ParameterInfo, Integer> paramToDeclByteCodeIndex;
    private final List<ParameterInfo> parameters;

    /* JADX WARN: Multi-variable type inference failed */
    public Parameters(List<? extends ParameterInfo> list) {
        list.getClass();
        this.parameters = list;
        this.paramToDeclByteCodeIndex = new HashMap<>();
        Iterator it = list.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((ParameterInfo) it.next()).getType().getSize();
        }
        this.argsSizeOnStack = size;
        this.capturedParametersSizeOnStack = LazyKt.lazy(new Function0() { // from class: dya
            public final Object invoke() {
                return Integer.valueOf(Parameters.b(this.b));
            }
        });
        this.captured = LazyKt.lazy(new Function0() { // from class: eya
            public final Object invoke() {
                return Parameters.a(this.b);
            }
        });
        Integer[] numArr = new Integer[size];
        for (IndexedValue indexedValue : CollectionsKt.withIndex(this)) {
            numArr[((ParameterInfo) indexedValue.getValue()).getDeclarationIndex()] = Integer.valueOf(indexedValue.getIndex());
        }
        this.actualDeclShifts = new ParameterInfo[this.argsSizeOnStack];
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            Integer num = numArr[i];
            if (num != null) {
                ParameterInfo parameterInfo = get(num.intValue());
                this.actualDeclShifts[size2] = parameterInfo;
                this.paramToDeclByteCodeIndex.put(parameterInfo, Integer.valueOf(size2));
                size2 += parameterInfo.getType().getSize();
            }
        }
    }

    public static List a(Parameters parameters) {
        List<ParameterInfo> list = parameters.parameters;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof CapturedParamInfo) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static int b(Parameters parameters) {
        Iterator<T> it = parameters.getCaptured().iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((CapturedParamInfo) it.next()).getType().getSize();
        }
        return size;
    }

    private final ParameterInfo get(int index) {
        return this.parameters.get(index);
    }

    public final int getArgsSizeOnStack() {
        return this.argsSizeOnStack;
    }

    public final List<CapturedParamInfo> getCaptured() {
        return (List) this.captured.getValue();
    }

    public final int getCapturedParametersSizeOnStack() {
        return ((Number) this.capturedParametersSizeOnStack.getValue()).intValue();
    }

    public final List<Type> getCapturedTypes() {
        List<CapturedParamInfo> captured = getCaptured();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(captured, 10));
        Iterator<T> it = captured.iterator();
        while (it.hasNext()) {
            arrayList.add(((CapturedParamInfo) it.next()).getType());
        }
        return arrayList;
    }

    public final int getDeclarationSlot(ParameterInfo info) {
        info.getClass();
        Integer num = this.paramToDeclByteCodeIndex.get(info);
        num.getClass();
        return num.intValue();
    }

    public final ParameterInfo getParameterByDeclarationSlot(int index) {
        ParameterInfo parameterInfo = this.actualDeclShifts[index];
        parameterInfo.getClass();
        return parameterInfo;
    }

    public final List<ParameterInfo> getParameters() {
        return this.parameters;
    }

    public final int getRealParametersSizeOnStack() {
        return this.argsSizeOnStack - getCapturedParametersSizeOnStack();
    }

    @Override // java.lang.Iterable
    public Iterator<ParameterInfo> iterator() {
        return this.parameters.iterator();
    }
}
