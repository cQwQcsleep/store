package org.jetbrains.kotlin.resolve.jvm.kotlinSignature;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0006"}, d2 = {"containsVarargs", "", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "containsAnyNotTrivialSignature", "", "hasNotTrivialSignature", "org.jetbrains.kotlin:frontend.java"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class SignaturePropagationUtilKt {
    public static final boolean containsAnyNotTrivialSignature(Collection<? extends FunctionDescriptor> collection) {
        collection.getClass();
        Collection<? extends FunctionDescriptor> collection2 = collection;
        if (collection2.isEmpty()) {
            return false;
        }
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            if (hasNotTrivialSignature((FunctionDescriptor) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsVarargs(FunctionDescriptor functionDescriptor) {
        functionDescriptor.getClass();
        List valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        List list = valueParameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ValueParameterDescriptor) it.next()).getVarargElementType() != null) {
                return true;
            }
        }
        return false;
    }

    private static final boolean hasNotTrivialSignature(FunctionDescriptor functionDescriptor) {
        if (functionDescriptor.getExtensionReceiverParameter() == null && !functionDescriptor.hasStableParameterNames()) {
            return containsVarargs(functionDescriptor);
        }
        return true;
    }
}
