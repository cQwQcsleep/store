package org.jetbrains.kotlin.codegen.inline;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\"\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "<init>", "()V", "lambdaClassType", "Lorg/jetbrains/org/objectweb/asm/Type;", "getLambdaClassType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "invokeMethod", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "getInvokeMethod", "()Lorg/jetbrains/org/objectweb/asm/commons/Method;", "invokeMethodParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "getInvokeMethodParameters", "()Ljava/util/List;", "invokeMethodReturnType", "getInvokeMethodReturnType", "()Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "capturedVars", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "getCapturedVars", "returnLabels", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Label;", "getReturnLabels", "()Ljava/util/Map;", "node", "Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "getNode", "()Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "setNode", "(Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;)V", "reifiedTypeParametersUsages", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "getReifiedTypeParametersUsages", "()Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "hasDispatchReceiver", Argument.Delimiters.none, "getHasDispatchReceiver", "()Z", "addAllParameters", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "remapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class LambdaInfo implements FunctionalArgument {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public SMAPAndMethodNode node;
    private final ReifiedTypeParametersUsages reifiedTypeParametersUsages = new ReifiedTypeParametersUsages();

    public final Parameters addAllParameters(FieldRemapper remapper) {
        remapper.getClass();
        ParametersBuilder parametersBuilderNewBuilder = ParametersBuilder.INSTANCE.newBuilder();
        if (getHasDispatchReceiver()) {
            parametersBuilderNewBuilder.addThis(getLambdaClassType(), true).setFunctionalArgument(this);
        }
        Type[] argumentTypes = Type.getArgumentTypes(getInvokeMethod().getDescriptor());
        argumentTypes.getClass();
        for (Type type : argumentTypes) {
            type.getClass();
            ParametersBuilder.addNextParameter$default(parametersBuilderNewBuilder, type, false, null, 4, null);
        }
        for (CapturedParamDesc capturedParamDesc : getCapturedVars()) {
            CapturedParamInfo capturedParamInfoFindField$default = FieldRemapper.findField$default(remapper, new FieldInsnNode(0, capturedParamDesc.getContainingLambdaName(), capturedParamDesc.getFieldName(), Argument.Delimiters.none), null, 2, null);
            if (capturedParamInfoFindField$default == null) {
                throw new IllegalStateException(("Captured field not found: " + capturedParamDesc.getContainingLambdaName() + '.' + capturedParamDesc.getFieldName()).toString());
            }
            CapturedParamInfo capturedParamInfoAddCapturedParam = parametersBuilderNewBuilder.addCapturedParam(capturedParamInfoFindField$default, capturedParamDesc.getFieldName());
            if (capturedParamDesc.getIsSuspend()) {
                capturedParamInfoAddCapturedParam.setFunctionalArgument(NonInlineArgumentForInlineSuspendParameter.INLINE_LAMBDA_AS_VARIABLE);
            }
        }
        return parametersBuilderNewBuilder.buildParameters();
    }

    public abstract List<CapturedParamDesc> getCapturedVars();

    public boolean getHasDispatchReceiver() {
        return true;
    }

    public abstract Method getInvokeMethod();

    public abstract List<KotlinTypeMarker> getInvokeMethodParameters();

    /* JADX INFO: renamed from: getInvokeMethodReturnType */
    public abstract KotlinTypeMarker getNullableAnyType();

    public abstract Type getLambdaClassType();

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final SMAPAndMethodNode getNode() throws UninitializedPropertyAccessException {
        SMAPAndMethodNode sMAPAndMethodNode = this.node;
        if (sMAPAndMethodNode != null) {
            return sMAPAndMethodNode;
        }
        Intrinsics.throwUninitializedPropertyAccessException("node");
        return null;
    }

    public final ReifiedTypeParametersUsages getReifiedTypeParametersUsages() {
        return this.reifiedTypeParametersUsages;
    }

    public Map<String, Label> getReturnLabels() {
        return MapsKt.emptyMap();
    }

    public final void setNode(SMAPAndMethodNode sMAPAndMethodNode) {
        sMAPAndMethodNode.getClass();
        this.node = sMAPAndMethodNode;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo$Companion;", Argument.Delimiters.none, "<init>", "()V", "capturedParamDesc", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "fieldName", Argument.Delimiters.none, "fieldType", "Lorg/jetbrains/org/objectweb/asm/Type;", "isSuspend", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CapturedParamDesc capturedParamDesc(LambdaInfo lambdaInfo, String str, Type type, boolean z) {
            lambdaInfo.getClass();
            str.getClass();
            type.getClass();
            return new CapturedParamDesc(lambdaInfo.getLambdaClassType(), str, type, z);
        }

        private Companion() {
        }
    }
}
