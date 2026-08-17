package org.jetbrains.kotlin.resolve.descriptorUtil;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0011\u0010\u000e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0011\u0010\u0014\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0003\"\u001c\u0010\u0016\u001a\r\u0012\t\u0012\u00070\u0018¢\u0006\u0002\b\u00190\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"NO_INFER_ANNOTATION_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "getNO_INFER_ANNOTATION_FQ_NAME", "()Lorg/jetbrains/kotlin/name/FqName;", "EXACT_ANNOTATION_FQ_NAME", "getEXACT_ANNOTATION_FQ_NAME", "LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_FQ_NAME", "getLOW_PRIORITY_IN_OVERLOAD_RESOLUTION_FQ_NAME", "HIDES_MEMBERS_ANNOTATION_FQ_NAME", "getHIDES_MEMBERS_ANNOTATION_FQ_NAME", "ONLY_INPUT_TYPES_FQ_NAME", "getONLY_INPUT_TYPES_FQ_NAME", "DYNAMIC_EXTENSION_FQ_NAME", "getDYNAMIC_EXTENSION_FQ_NAME", "BUILDER_INFERENCE_ANNOTATION_FQ_NAME", "getBUILDER_INFERENCE_ANNOTATION_FQ_NAME", "OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getOVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_FQ_NAME", "getOVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_FQ_NAME", "HIDES_MEMBERS_NAME_LIST", "", "Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/internal/EnhancedNullability;", "getHIDES_MEMBERS_NAME_LIST", "()Ljava/util/Set;", "org.jetbrains.kotlin:compiler.common"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class AnnotationsForResolveKt {
    private static final Set<Name> HIDES_MEMBERS_NAME_LIST;
    private static final ClassId OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID;
    private static final FqName OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_FQ_NAME;
    private static final FqName NO_INFER_ANNOTATION_FQ_NAME = new FqName("kotlin.internal.NoInfer");
    private static final FqName EXACT_ANNOTATION_FQ_NAME = new FqName("kotlin.internal.Exact");
    private static final FqName LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_FQ_NAME = new FqName("kotlin.internal.LowPriorityInOverloadResolution");
    private static final FqName HIDES_MEMBERS_ANNOTATION_FQ_NAME = new FqName("kotlin.internal.HidesMembers");
    private static final FqName ONLY_INPUT_TYPES_FQ_NAME = new FqName("kotlin.internal.OnlyInputTypes");
    private static final FqName DYNAMIC_EXTENSION_FQ_NAME = new FqName("kotlin.internal.DynamicExtension");
    private static final FqName BUILDER_INFERENCE_ANNOTATION_FQ_NAME = new FqName("kotlin.BuilderInference");

    static {
        FqName fqName = new FqName("kotlin");
        Name nameIdentifier = Name.identifier("OverloadResolutionByLambdaReturnType");
        nameIdentifier.getClass();
        ClassId classId = new ClassId(fqName, nameIdentifier);
        OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID = classId;
        OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_FQ_NAME = classId.asSingleFqName();
        HIDES_MEMBERS_NAME_LIST = SetsKt.setOf(new Name[]{Name.identifier("forEach"), Name.identifier("addSuppressed")});
    }

    public static final FqName getBUILDER_INFERENCE_ANNOTATION_FQ_NAME() {
        return BUILDER_INFERENCE_ANNOTATION_FQ_NAME;
    }

    public static final FqName getDYNAMIC_EXTENSION_FQ_NAME() {
        return DYNAMIC_EXTENSION_FQ_NAME;
    }

    public static final FqName getEXACT_ANNOTATION_FQ_NAME() {
        return EXACT_ANNOTATION_FQ_NAME;
    }

    public static final FqName getHIDES_MEMBERS_ANNOTATION_FQ_NAME() {
        return HIDES_MEMBERS_ANNOTATION_FQ_NAME;
    }

    public static final Set<Name> getHIDES_MEMBERS_NAME_LIST() {
        return HIDES_MEMBERS_NAME_LIST;
    }

    public static final FqName getLOW_PRIORITY_IN_OVERLOAD_RESOLUTION_FQ_NAME() {
        return LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_FQ_NAME;
    }

    public static final FqName getNO_INFER_ANNOTATION_FQ_NAME() {
        return NO_INFER_ANNOTATION_FQ_NAME;
    }

    public static final FqName getONLY_INPUT_TYPES_FQ_NAME() {
        return ONLY_INPUT_TYPES_FQ_NAME;
    }

    public static final ClassId getOVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID() {
        return OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID;
    }

    public static final FqName getOVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_FQ_NAME() {
        return OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_FQ_NAME;
    }
}
