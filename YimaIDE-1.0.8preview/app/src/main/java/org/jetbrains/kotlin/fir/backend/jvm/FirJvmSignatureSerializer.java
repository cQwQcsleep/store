package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.serialization.JvmSignatureSerializer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ClassMapperLite;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmSignatureSerializer;", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSignatureSerializer;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "<init>", "(Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;)V", "requiresFunctionSignature", Argument.Delimiters.none, "descriptor", "desc", Argument.Delimiters.none, "requiresPropertySignature", "mapTypeDefault", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmSignatureSerializer extends JvmSignatureSerializer<FirFunction, FirProperty> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmSignatureSerializer(FirElementAwareStringTable firElementAwareStringTable) {
        super(firElementAwareStringTable);
        firElementAwareStringTable.getClass();
    }

    private final String mapTypeDefault(FirTypeRef typeRef) {
        ClassId classId = ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getConeType(typeRef));
        if (classId == null) {
            return null;
        }
        return ClassMapperLite.mapClass(classId.asString());
    }

    public boolean requiresFunctionSignature(FirFunction descriptor, String desc) {
        descriptor.getClass();
        desc.getClass();
        StringBuilder sb = new StringBuilder("(");
        FirReceiverParameter receiverParameter = descriptor.getReceiverParameter();
        FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
        if (typeRef != null) {
            String strMapTypeDefault = mapTypeDefault(typeRef);
            if (strMapTypeDefault == null) {
                return true;
            }
            sb.append(strMapTypeDefault);
        }
        Iterator<FirValueParameter> it = descriptor.getValueParameters().iterator();
        while (it.hasNext()) {
            String strMapTypeDefault2 = mapTypeDefault(it.next().getReturnTypeRef());
            if (strMapTypeDefault2 == null) {
                return true;
            }
            sb.append(strMapTypeDefault2);
        }
        sb.append(")");
        String strMapTypeDefault3 = mapTypeDefault(descriptor.getReturnTypeRef());
        if (strMapTypeDefault3 == null) {
            return true;
        }
        sb.append(strMapTypeDefault3);
        return !Intrinsics.areEqual(sb.toString(), desc);
    }

    public boolean requiresPropertySignature(FirProperty descriptor, String desc) {
        descriptor.getClass();
        desc.getClass();
        return !Intrinsics.areEqual(desc, mapTypeDefault(descriptor.getReturnTypeRef()));
    }
}
