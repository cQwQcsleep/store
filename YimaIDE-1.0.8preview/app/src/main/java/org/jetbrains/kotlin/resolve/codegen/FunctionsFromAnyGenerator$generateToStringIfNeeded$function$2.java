package org.jetbrains.kotlin.resolve.codegen;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* synthetic */ class FunctionsFromAnyGenerator$generateToStringIfNeeded$function$2 extends FunctionReferenceImpl implements Function1<List<? extends ValueParameterDescriptor>, Boolean> {
    public static final FunctionsFromAnyGenerator$generateToStringIfNeeded$function$2 INSTANCE = new FunctionsFromAnyGenerator$generateToStringIfNeeded$function$2();

    public FunctionsFromAnyGenerator$generateToStringIfNeeded$function$2() {
        super(1, List.class, "isEmpty", "isEmpty()Z", 0);
    }

    public final Boolean invoke(List<? extends ValueParameterDescriptor> list) {
        list.getClass();
        return Boolean.valueOf(list.isEmpty());
    }
}
