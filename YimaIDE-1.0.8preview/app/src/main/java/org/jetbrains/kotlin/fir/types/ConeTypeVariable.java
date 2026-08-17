package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u0010\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", ModuleXmlParser.NAME, Argument.Delimiters.none, "originalTypeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;)V", "typeConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "getTypeConstructor", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "defaultType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "getDefaultType", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "toString", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeTypeVariable implements TypeVariableMarker {
    private final ConeTypeVariableType defaultType;
    private final ConeTypeVariableTypeConstructor typeConstructor;

    public ConeTypeVariable(String str, TypeParameterMarker typeParameterMarker) {
        str.getClass();
        ConeTypeVariableTypeConstructor coneTypeVariableTypeConstructor = new ConeTypeVariableTypeConstructor(str, typeParameterMarker);
        this.typeConstructor = coneTypeVariableTypeConstructor;
        this.defaultType = new ConeTypeVariableType(false, coneTypeVariableTypeConstructor, null, 4, null);
    }

    public final ConeTypeVariableType getDefaultType() {
        return this.defaultType;
    }

    public final ConeTypeVariableTypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    public String toString() {
        return this.defaultType.toString();
    }

    public /* synthetic */ ConeTypeVariable(String str, TypeParameterMarker typeParameterMarker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : typeParameterMarker);
    }
}
