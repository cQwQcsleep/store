package org.jetbrains.kotlin.backend.konan.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.serialization.mangle.PlatformSpecificFunctionNameMangleComputer;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.native.interop.ObjCMethodInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\n\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u000bH&J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u000bH&J\u0015\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0013\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/ObjCFunctionNameMangleComputer;", "ValueParameter", "", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/PlatformSpecificFunctionNameMangleComputer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getObjCMethodInfo", "Lorg/jetbrains/kotlin/native/interop/ObjCMethodInfo;", "getExtensionReceiverClassName", "Lorg/jetbrains/kotlin/name/Name;", "isObjCConstructor", "", "isPropertyAccessor", "hasObjCMethodAnnotation", "hasObjCFactoryAnnotation", "isObjCClassMethod", "getValueParameterName", "valueParameter", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/name/Name;", "computePlatformSpecificFunctionName", "", "computePlatformSpecificValueParameterPrefix", "(Ljava/lang/Object;)Ljava/lang/String;", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ObjCFunctionNameMangleComputer<ValueParameter> implements PlatformSpecificFunctionNameMangleComputer<ValueParameter> {
    @Override // org.jetbrains.kotlin.backend.common.serialization.mangle.PlatformSpecificFunctionNameMangleComputer
    public final String computePlatformSpecificFunctionName() {
        ObjCMethodInfo objCMethodInfo = getObjCMethodInfo();
        if (objCMethodInfo == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Name extensionReceiverClassName = getExtensionReceiverClassName();
        if (extensionReceiverClassName != null) {
            sb.append(extensionReceiverClassName);
            sb.append('.');
        }
        sb.append("objc:");
        sb.append(objCMethodInfo.getSelector());
        if (isObjCConstructor()) {
            sb.append("#Constructor");
        }
        if (isPropertyAccessor()) {
            sb.append("#Accessor");
        }
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.mangle.PlatformSpecificFunctionNameMangleComputer
    public final String computePlatformSpecificValueParameterPrefix(ValueParameter valueParameter) {
        valueParameter.getClass();
        if (!hasObjCMethodAnnotation() && !hasObjCFactoryAnnotation() && !isObjCClassMethod()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getValueParameterName(valueParameter));
        sb.append(':');
        return sb.toString();
    }

    public abstract Name getExtensionReceiverClassName();

    public abstract ObjCMethodInfo getObjCMethodInfo();

    public abstract Name getValueParameterName(ValueParameter valueParameter);

    public abstract boolean hasObjCFactoryAnnotation();

    public abstract boolean hasObjCMethodAnnotation();

    public abstract boolean isObjCClassMethod();

    public abstract boolean isObjCConstructor();

    public abstract boolean isPropertyAccessor();
}
