package org.jetbrains.kotlin.type;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.type.MapPsiToAsmDesc;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u0005H\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0018\u0010\u0011\u001a\n \u0012*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0013\u0010\u0015\u001a\u00070\u0016¢\u0006\u0002\b\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/type/MapPsiToAsmDesc;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "typeDesc", "", "type", "Lcom/intellij/psi/PsiType;", "classDesc", "psiClass", "Lcom/intellij/psi/PsiClass;", "methodDesc", "psiMethod", "Lcom/intellij/psi/PsiMethod;", "unknownSignature", CompilerOptions.ERROR, "message", "primitive", "kotlin.jvm.PlatformType", "asmType", "Lorg/jetbrains/org/objectweb/asm/Type;", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MapPsiToAsmDesc {
    public static final MapPsiToAsmDesc INSTANCE;
    private static final Logger LOG;

    static {
        MapPsiToAsmDesc mapPsiToAsmDesc = new MapPsiToAsmDesc();
        INSTANCE = mapPsiToAsmDesc;
        Logger logger = Logger.getInstance(mapPsiToAsmDesc.getClass());
        logger.getClass();
        LOG = logger;
    }

    private MapPsiToAsmDesc() {
    }

    private final String classDesc(PsiClass psiClass) {
        StringBuilder sb = new StringBuilder("L");
        List listReversed = CollectionsKt.reversed(SequencesKt.toList(SequencesKt.generateSequence(psiClass, new Function1() { // from class: eu9
            public final Object invoke(Object obj) {
                return MapPsiToAsmDesc.classDesc$lambda$0$0((PsiClass) obj);
            }
        })));
        String qualifiedName = ((PsiClass) CollectionsKt.first(listReversed)).getQualifiedName();
        qualifiedName.getClass();
        sb.append(StringsKt.replace$default(qualifiedName, ".", "/", false, 4, (Object) null));
        for (PsiClass psiClass2 : CollectionsKt.drop(listReversed, 1)) {
            sb.append(AsmUtil.CAPTURED_PREFIX);
            String name = psiClass2.getName();
            name.getClass();
            sb.append(name);
        }
        sb.append(";");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PsiClass classDesc$lambda$0$0(PsiClass psiClass) {
        psiClass.getClass();
        return psiClass.getContainingClass();
    }

    private final String error(String message) {
        LOG.error(message);
        return unknownSignature();
    }

    private final String primitive(Type asmType) {
        return asmType.getDescriptor();
    }

    private final String unknownSignature() {
        return "";
    }

    public final String methodDesc(PsiMethod psiMethod) {
        String strTypeDesc;
        psiMethod.getClass();
        StringBuilder sb = new StringBuilder("(");
        PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        parameters.getClass();
        for (PsiParameter psiParameter : parameters) {
            MapPsiToAsmDesc mapPsiToAsmDesc = INSTANCE;
            PsiType type = psiParameter.getType();
            type.getClass();
            sb.append(mapPsiToAsmDesc.typeDesc(type));
        }
        sb.append(")");
        PsiType returnType = psiMethod.getReturnType();
        if (returnType == null || (strTypeDesc = INSTANCE.typeDesc(returnType)) == null) {
            strTypeDesc = "V";
        }
        sb.append(strTypeDesc);
        return sb.toString();
    }

    public final String typeDesc(PsiType type) {
        String strTypeDesc;
        type.getClass();
        if (Intrinsics.areEqual(type, PsiTypes.voidType())) {
            Type type2 = Type.VOID_TYPE;
            type2.getClass();
            String strPrimitive = primitive(type2);
            strPrimitive.getClass();
            return strPrimitive;
        }
        if (Intrinsics.areEqual(type, PsiTypes.booleanType())) {
            Type type3 = Type.BOOLEAN_TYPE;
            type3.getClass();
            String strPrimitive2 = primitive(type3);
            strPrimitive2.getClass();
            return strPrimitive2;
        }
        if (Intrinsics.areEqual(type, PsiTypes.charType())) {
            Type type4 = Type.CHAR_TYPE;
            type4.getClass();
            String strPrimitive3 = primitive(type4);
            strPrimitive3.getClass();
            return strPrimitive3;
        }
        if (Intrinsics.areEqual(type, PsiTypes.intType())) {
            Type type5 = Type.INT_TYPE;
            type5.getClass();
            String strPrimitive4 = primitive(type5);
            strPrimitive4.getClass();
            return strPrimitive4;
        }
        if (Intrinsics.areEqual(type, PsiTypes.byteType())) {
            Type type6 = Type.BYTE_TYPE;
            type6.getClass();
            String strPrimitive5 = primitive(type6);
            strPrimitive5.getClass();
            return strPrimitive5;
        }
        if (Intrinsics.areEqual(type, PsiTypes.shortType())) {
            Type type7 = Type.SHORT_TYPE;
            type7.getClass();
            String strPrimitive6 = primitive(type7);
            strPrimitive6.getClass();
            return strPrimitive6;
        }
        if (Intrinsics.areEqual(type, PsiTypes.longType())) {
            Type type8 = Type.LONG_TYPE;
            type8.getClass();
            String strPrimitive7 = primitive(type8);
            strPrimitive7.getClass();
            return strPrimitive7;
        }
        if (Intrinsics.areEqual(type, PsiTypes.floatType())) {
            Type type9 = Type.FLOAT_TYPE;
            type9.getClass();
            String strPrimitive8 = primitive(type9);
            strPrimitive8.getClass();
            return strPrimitive8;
        }
        if (Intrinsics.areEqual(type, PsiTypes.doubleType())) {
            Type type10 = Type.DOUBLE_TYPE;
            type10.getClass();
            String strPrimitive9 = primitive(type10);
            strPrimitive9.getClass();
            return strPrimitive9;
        }
        if (type instanceof PsiArrayType) {
            StringBuilder sb = new StringBuilder("[");
            PsiType componentType = ((PsiArrayType) type).getComponentType();
            componentType.getClass();
            sb.append(typeDesc(componentType));
            return sb.toString();
        }
        if (!(type instanceof PsiClassType)) {
            return error("Unexpected type " + type + " of class " + type.getClass());
        }
        PsiTypeParameter psiTypeParameterResolve = ((PsiClassType) type).resolve();
        if (!(psiTypeParameterResolve instanceof PsiTypeParameter)) {
            return psiTypeParameterResolve != null ? classDesc(psiTypeParameterResolve) : unknownSignature();
        }
        PsiClassType[] superTypes = psiTypeParameterResolve.getSuperTypes();
        superTypes.getClass();
        PsiType psiType = (PsiClassType) ArraysKt.firstOrNull(superTypes);
        return (psiType == null || (strTypeDesc = INSTANCE.typeDesc(psiType)) == null) ? "Ljava/lang/Object;" : strTypeDesc;
    }
}
