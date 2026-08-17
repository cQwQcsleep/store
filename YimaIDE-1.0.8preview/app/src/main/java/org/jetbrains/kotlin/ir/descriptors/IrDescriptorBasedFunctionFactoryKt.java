package org.jetbrains.kotlin.ir.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.functions.FunctionClassDescriptor;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\f\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\r\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u000e\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000f"}, d2 = {"reflectFunctionClassFqn", "Lorg/jetbrains/kotlin/name/FqName;", "shortName", "Lorg/jetbrains/kotlin/name/Name;", "reflectionFunctionClassName", "isSuspend", "", "arity", "", "functionClassDescriptor", "Lorg/jetbrains/kotlin/builtins/functions/FunctionClassDescriptor;", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "suspendFunctionClassDescriptor", "kFunctionClassDescriptor", "kSuspendFunctionClassDescriptor", "org.jetbrains.kotlin:ir.psi2ir"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrDescriptorBasedFunctionFactoryKt {
    public static final FunctionClassDescriptor functionClassDescriptor(KotlinBuiltIns kotlinBuiltIns, int i) {
        kotlinBuiltIns.getClass();
        FunctionClassDescriptor function = kotlinBuiltIns.getFunction(i);
        function.getClass();
        return function;
    }

    public static final FunctionClassDescriptor kFunctionClassDescriptor(KotlinBuiltIns kotlinBuiltIns, int i) {
        kotlinBuiltIns.getClass();
        FunctionClassDescriptor builtInClassByFqName = kotlinBuiltIns.getBuiltInClassByFqName(reflectFunctionClassFqn(reflectionFunctionClassName(false, i)));
        builtInClassByFqName.getClass();
        return builtInClassByFqName;
    }

    public static final FunctionClassDescriptor kSuspendFunctionClassDescriptor(KotlinBuiltIns kotlinBuiltIns, int i) {
        kotlinBuiltIns.getClass();
        FunctionClassDescriptor builtInClassByFqName = kotlinBuiltIns.getBuiltInClassByFqName(reflectFunctionClassFqn(reflectionFunctionClassName(true, i)));
        builtInClassByFqName.getClass();
        return builtInClassByFqName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName reflectFunctionClassFqn(Name name) {
        return StandardNames.KOTLIN_REFLECT_FQ_NAME.child(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Name reflectionFunctionClassName(boolean z, int i) {
        StringBuilder sb = new StringBuilder("K");
        sb.append(z ? "Suspend" : "");
        sb.append("Function");
        sb.append(i);
        Name nameIdentifier = Name.identifier(sb.toString());
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    public static final FunctionClassDescriptor suspendFunctionClassDescriptor(KotlinBuiltIns kotlinBuiltIns, int i) {
        kotlinBuiltIns.getClass();
        FunctionClassDescriptor suspendFunction = kotlinBuiltIns.getSuspendFunction(i);
        suspendFunction.getClass();
        return suspendFunction;
    }
}
