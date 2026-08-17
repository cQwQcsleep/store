package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class IrFileSerializer$requireAbiAtLeast$1 implements Function1 {
    public static final IrFileSerializer$requireAbiAtLeast$1 INSTANCE = new IrFileSerializer$requireAbiAtLeast$1();

    public final String invoke(IrElement irElement) {
        irElement.getClass();
        String simpleName = Reflection.getOrCreateKotlinClass(irElement.getClass()).getSimpleName();
        return simpleName == null ? "IrElement" : simpleName;
    }
}
