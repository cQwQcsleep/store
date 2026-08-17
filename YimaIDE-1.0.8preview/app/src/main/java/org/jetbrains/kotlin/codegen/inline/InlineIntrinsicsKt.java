package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorUtilKt;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.resolve.calls.checkers.CoroutineCallCheckerKt;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0002\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0000¨\u0006\u000b"}, d2 = {"isBuiltInSuspendCoroutineUninterceptedOrReturn", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "generateInlineIntrinsicForIr", "Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "descriptor", "getSpecialEnumFunDescriptor", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "isValueOf", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineIntrinsicsKt {
    public static final SMAPAndMethodNode generateInlineIntrinsicForIr(FunctionDescriptor functionDescriptor) {
        MethodNode methodNodeCreateMethodNodeForSuspendCoroutineUninterceptedOrReturn;
        functionDescriptor.getClass();
        if (CoroutineCallCheckerKt.isBuiltInCoroutineContext(functionDescriptor)) {
            methodNodeCreateMethodNodeForSuspendCoroutineUninterceptedOrReturn = CoroutineCodegenUtilKt.createMethodNodeForCoroutineContext(functionDescriptor);
        } else {
            methodNodeCreateMethodNodeForSuspendCoroutineUninterceptedOrReturn = isBuiltInSuspendCoroutineUninterceptedOrReturn(functionDescriptor) ? CoroutineCodegenUtilKt.createMethodNodeForSuspendCoroutineUninterceptedOrReturn() : null;
        }
        if (methodNodeCreateMethodNodeForSuspendCoroutineUninterceptedOrReturn != null) {
            return new SMAPAndMethodNode(methodNodeCreateMethodNodeForSuspendCoroutineUninterceptedOrReturn, new SMAP(CollectionsKt.emptyList()));
        }
        return null;
    }

    public static final String getSpecialEnumFunDescriptor(Type type, boolean z) {
        type.getClass();
        if (z) {
            String methodDescriptor = Type.getMethodDescriptor(type, new Type[]{AsmTypes.JAVA_STRING_TYPE});
            methodDescriptor.getClass();
            return methodDescriptor;
        }
        String methodDescriptor2 = Type.getMethodDescriptor(AsmUtil.getArrayType(type), new Type[0]);
        methodDescriptor2.getClass();
        return methodDescriptor2;
    }

    private static final boolean isBuiltInSuspendCoroutineUninterceptedOrReturn(FunctionDescriptor functionDescriptor) {
        return DescriptorUtilKt.isTopLevelInPackage(functionDescriptor, "suspendCoroutineUninterceptedOrReturn", StandardNames.COROUTINES_INTRINSICS_PACKAGE_FQ_NAME.asString());
    }
}
