package org.jetbrains.kotlin.codegen;

import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.FunctionInvokeDescriptor;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.kotlin.descriptors.impl.AnonymousFunctionDescriptor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class JvmCodegenUtil {
    /* JADX WARN: Code duplicated, block: B:19:0x002b  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 4) ? 2 : 3];
        if (i == 1) {
            objArr[0] = ModuleXmlParser.NAME;
        } else if (i == 2) {
            objArr[0] = "org/jetbrains/kotlin/codegen/JvmCodegenUtil";
        } else if (i == 3) {
            objArr[0] = "moduleName";
        } else if (i != 4) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "org/jetbrains/kotlin/codegen/JvmCodegenUtil";
        }
        if (i == 2) {
            objArr[1] = "prepareModuleName";
        } else if (i != 4) {
            objArr[1] = "org/jetbrains/kotlin/codegen/JvmCodegenUtil";
        } else {
            objArr[1] = "getMappingFileName";
        }
        if (i == 1) {
            objArr[2] = "prepareModuleName";
        } else if (i != 2) {
            if (i == 3) {
                objArr[2] = "getMappingFileName";
            } else if (i != 4) {
                objArr[2] = "getDispatchReceiverParameterForConstructorCall";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private JvmCodegenUtil() {
    }

    public static ClassDescriptor getDispatchReceiverParameterForConstructorCall(ConstructorDescriptor constructorDescriptor) {
        if (constructorDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        ParameterDescriptor dispatchReceiverParameter = constructorDescriptor.getDispatchReceiverParameter();
        if (dispatchReceiverParameter == null) {
            return null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) dispatchReceiverParameter.getDeclarationDescriptor();
        if (classDescriptor.getKind().isSingleton()) {
            return null;
        }
        return classDescriptor;
    }

    public static String getMappingFileName(String str) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        return "META-INF/" + str.replaceAll("[<>:\"/\\\\|?*%\\x00-\\x1F]", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER) + ".kotlin_module";
    }

    public static String getModuleName(ModuleDescriptor moduleDescriptor) {
        Name stableName = moduleDescriptor.getStableName();
        return stableName == null ? prepareModuleName(moduleDescriptor.getName()) : prepareModuleName(stableName);
    }

    public static boolean isDeclarationOfBigArityCreateCoroutineMethod(DeclarationDescriptor declarationDescriptor) {
        return (declarationDescriptor instanceof SimpleFunctionDescriptor) && declarationDescriptor.getName().asString().equals(CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME) && ((SimpleFunctionDescriptor) declarationDescriptor).getValueParameters().size() >= 22 && (declarationDescriptor.getContainingDeclaration() instanceof AnonymousFunctionDescriptor) && declarationDescriptor.getContainingDeclaration().isSuspend();
    }

    public static boolean isDeclarationOfBigArityFunctionInvoke(DeclarationDescriptor declarationDescriptor) {
        return (declarationDescriptor instanceof FunctionInvokeDescriptor) && ((FunctionInvokeDescriptor) declarationDescriptor).hasBigArity();
    }

    public static boolean isJvmInterface(DeclarationDescriptor declarationDescriptor) {
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            return false;
        }
        ClassKind kind = ((ClassDescriptor) declarationDescriptor).getKind();
        return kind == ClassKind.INTERFACE || kind == ClassKind.ANNOTATION_CLASS;
    }

    public static String prepareModuleName(Name name) {
        if (name == null) {
            $$$reportNull$$$0(1);
        }
        String strRemoveSurrounding = StringsKt.removeSurrounding(name.asString(), "<", ">");
        if (strRemoveSurrounding == null) {
            $$$reportNull$$$0(2);
        }
        return strRemoveSurrounding;
    }

    public static boolean isJvmInterface(KotlinType kotlinType) {
        return isJvmInterface((DeclarationDescriptor) kotlinType.getConstructor().getDeclarationDescriptor());
    }
}
