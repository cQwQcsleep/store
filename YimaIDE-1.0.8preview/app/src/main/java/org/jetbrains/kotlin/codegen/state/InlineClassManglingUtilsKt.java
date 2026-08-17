package org.jetbrains.kotlin.codegen.state;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InfoForMangling;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.JavaMethodDescriptor;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.InlineClassDescriptorResolver;
import org.jetbrains.kotlin.resolve.InlineClassesUtilsKt;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.jvm.InlineClassManglingRulesKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.typeUtil.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\b\u001a \u0010\n\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\u000e\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER", Argument.Delimiters.none, "collectFunctionSignatureForManglingSuffix", "useOldManglingRules", Argument.Delimiters.none, "requiresFunctionNameManglingForParameterTypes", "fqNamesForMangling", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/state/InfoForMangling;", "returnTypeInfo", "getManglingSuffixBasedOnKotlinSignature", "descriptor", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "shouldMangleByReturnType", "getInfoForMangling", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/KotlinType;", "md5base64", "signatureForMangling", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineClassManglingUtilsKt {
    public static final String NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER = "_";

    public static CharSequence a(boolean z, InfoForMangling infoForMangling) {
        return collectFunctionSignatureForManglingSuffix$getSignatureElementForMangling(z, infoForMangling);
    }

    public static final String collectFunctionSignatureForManglingSuffix(boolean z, boolean z2, List<InfoForMangling> list, InfoForMangling infoForMangling) {
        String str;
        list.getClass();
        if (z) {
            if (z2) {
                return collectFunctionSignatureForManglingSuffix$collectSignatureForMangling(list, z);
            }
            if (infoForMangling == null) {
                return null;
            }
            return ":" + collectFunctionSignatureForManglingSuffix$getSignatureElementForMangling(z, infoForMangling);
        }
        if (!z2 && infoForMangling == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(collectFunctionSignatureForManglingSuffix$collectSignatureForMangling(list, z));
        if (infoForMangling != null) {
            str = ":" + collectFunctionSignatureForManglingSuffix$getSignatureElementForMangling(z, infoForMangling);
        } else {
            str = Argument.Delimiters.none;
        }
        sb.append(str);
        return sb.toString();
    }

    private static final String collectFunctionSignatureForManglingSuffix$collectSignatureForMangling(List<InfoForMangling> list, final boolean z) {
        return CollectionsKt.joinToString$default(list, z ? ", " : Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: up6
            public final Object invoke(Object obj) {
                return InlineClassManglingUtilsKt.a(z, (InfoForMangling) obj);
            }
        }, 30, (Object) null);
    }

    private static final String collectFunctionSignatureForManglingSuffix$getSignatureElementForMangling(boolean z, InfoForMangling infoForMangling) {
        StringBuilder sb = new StringBuilder();
        if (infoForMangling == null) {
            return Argument.Delimiters.none;
        }
        if (z || infoForMangling.getIsValue()) {
            sb.append('L');
            sb.append(infoForMangling.getFqName());
            if (infoForMangling.getIsNullable()) {
                sb.append('?');
            }
            sb.append(';');
        } else {
            sb.append(NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER);
        }
        return sb.toString();
    }

    private static final InfoForMangling getInfoForMangling(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return null;
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            DeclarationDescriptor declarationDescriptor2 = (DeclarationDescriptor) declarationDescriptor;
            return new InfoForMangling(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor2), InlineClassesUtilsKt.isValueClass(declarationDescriptor2), kotlinType.isMarkedNullable());
        }
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            return getInfoForMangling(TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) declarationDescriptor));
        }
        return null;
    }

    public static final String getManglingSuffixBasedOnKotlinSignature(CallableMemberDescriptor callableMemberDescriptor, boolean z, boolean z2) {
        InfoForMangling infoForMangling;
        callableMemberDescriptor.getClass();
        if (!(callableMemberDescriptor instanceof FunctionDescriptor) || (callableMemberDescriptor instanceof ConstructorDescriptor) || (callableMemberDescriptor instanceof JavaMethodDescriptor) || InlineClassDescriptorResolver.isSynthesizedBoxOrUnboxMethod(callableMemberDescriptor) || DescriptorUtils.hasJvmNameAnnotation(callableMemberDescriptor)) {
            return null;
        }
        boolean zRequiresFunctionNameManglingForParameterTypes = InlineClassManglingRulesKt.requiresFunctionNameManglingForParameterTypes(callableMemberDescriptor);
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) callableMemberDescriptor;
        ParameterDescriptor extensionReceiverParameter = functionDescriptor.getExtensionReceiverParameter();
        List listListOfNotNull = CollectionsKt.listOfNotNull(extensionReceiverParameter != null ? extensionReceiverParameter.getType() : null);
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        List<ValueParameterDescriptor> list = valueParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ValueParameterDescriptor) it.next()).getType());
        }
        List listPlus = CollectionsKt.plus(listListOfNotNull, arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        Iterator it2 = listPlus.iterator();
        while (it2.hasNext()) {
            arrayList2.add(getInfoForMangling((KotlinType) it2.next()));
        }
        if (z && InlineClassManglingRulesKt.requiresFunctionNameManglingForReturnType(callableMemberDescriptor)) {
            KotlinType returnType = functionDescriptor.getReturnType();
            returnType.getClass();
            infoForMangling = getInfoForMangling(returnType);
        } else {
            infoForMangling = null;
        }
        String strCollectFunctionSignatureForManglingSuffix = collectFunctionSignatureForManglingSuffix(z2, zRequiresFunctionNameManglingForParameterTypes, arrayList2, infoForMangling);
        if (strCollectFunctionSignatureForManglingSuffix == null) {
            return null;
        }
        return "-" + md5base64(strCollectFunctionSignatureForManglingSuffix);
    }

    public static final String md5base64(String str) throws NoSuchAlgorithmException {
        str.getClass();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        byte[] bArrDigest = messageDigest.digest(bytes);
        bArrDigest.getClass();
        String strEncodeToString = Base64.getUrlEncoder().withoutPadding().encodeToString(ArraysKt.copyOfRange(bArrDigest, 0, 5));
        strEncodeToString.getClass();
        return strEncodeToString;
    }
}
