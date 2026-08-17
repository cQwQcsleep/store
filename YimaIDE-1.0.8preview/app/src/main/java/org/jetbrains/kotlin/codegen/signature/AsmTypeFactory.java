package org.jetbrains.kotlin.codegen.signature;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.JvmTypeFactory;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0011\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/signature/AsmTypeFactory;", "Lorg/jetbrains/kotlin/load/kotlin/JvmTypeFactory;", "Lorg/jetbrains/org/objectweb/asm/Type;", "<init>", "()V", "boxType", "possiblyPrimitiveType", "createFromString", "representation", Argument.Delimiters.none, "createPrimitiveType", "primitiveType", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "createObjectType", "internalName", "toString", ModuleXmlParser.TYPE, "javaLangClassType", "getJavaLangClassType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AsmTypeFactory implements JvmTypeFactory<Type> {
    public static final AsmTypeFactory INSTANCE = new AsmTypeFactory();

    private AsmTypeFactory() {
    }

    public Type boxType(Type possiblyPrimitiveType) {
        possiblyPrimitiveType.getClass();
        Type typeBoxType = AsmUtil.boxType(possiblyPrimitiveType);
        typeBoxType.getClass();
        return typeBoxType;
    }

    public Type createFromString(String representation) {
        representation.getClass();
        Type type = Type.getType(representation);
        type.getClass();
        return type;
    }

    public Type createObjectType(String internalName) {
        internalName.getClass();
        Type objectType = Type.getObjectType(internalName);
        objectType.getClass();
        return objectType;
    }

    public Type createPrimitiveType(PrimitiveType primitiveType) {
        primitiveType.getClass();
        Type typeValueTypeForPrimitive = AsmTypes.valueTypeForPrimitive(primitiveType);
        typeValueTypeForPrimitive.getClass();
        return typeValueTypeForPrimitive;
    }

    public Type getJavaLangClassType() {
        Type type = AsmTypes.JAVA_CLASS_TYPE;
        type.getClass();
        return type;
    }

    public String toString(Type type) {
        type.getClass();
        String descriptor = type.getDescriptor();
        descriptor.getClass();
        return descriptor;
    }
}
