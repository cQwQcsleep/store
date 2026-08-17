package org.jetbrains.kotlin.descriptors;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J\u0017\u0010\u0016\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0015\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0017J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR&\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/InlineClassRepresentation;", "Type", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "underlyingPropertyName", "Lorg/jetbrains/kotlin/name/Name;", "underlyingType", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;)V", "getUnderlyingPropertyName", "()Lorg/jetbrains/kotlin/name/Name;", "getUnderlyingType", "()Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "underlyingPropertyNamesToTypes", Argument.Delimiters.none, "Lkotlin/Pair;", "getUnderlyingPropertyNamesToTypes", "()Ljava/util/List;", "containsPropertyWithName", Argument.Delimiters.none, ModuleXmlParser.NAME, "getPropertyTypeByName", "(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineClassRepresentation<Type extends RigidTypeMarker> extends ValueClassRepresentation<Type> {
    private final Name underlyingPropertyName;
    private final Type underlyingType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InlineClassRepresentation(Name name, Type type) {
        super(null);
        name.getClass();
        type.getClass();
        this.underlyingPropertyName = name;
        this.underlyingType = type;
    }

    @Override // org.jetbrains.kotlin.descriptors.ValueClassRepresentation
    public boolean containsPropertyWithName(Name name) {
        name.getClass();
        return Intrinsics.areEqual(this.underlyingPropertyName, name);
    }

    @Override // org.jetbrains.kotlin.descriptors.ValueClassRepresentation
    public Type getPropertyTypeByName(Name name) {
        name.getClass();
        Type type = this.underlyingType;
        if (containsPropertyWithName(name)) {
            return type;
        }
        return null;
    }

    public final Name getUnderlyingPropertyName() {
        return this.underlyingPropertyName;
    }

    @Override // org.jetbrains.kotlin.descriptors.ValueClassRepresentation
    public List<Pair<Name, Type>> getUnderlyingPropertyNamesToTypes() {
        return CollectionsKt.listOf(TuplesKt.to(this.underlyingPropertyName, this.underlyingType));
    }

    public final Type getUnderlyingType() {
        return this.underlyingType;
    }

    public String toString() {
        return "InlineClassRepresentation(underlyingPropertyName=" + this.underlyingPropertyName + ", underlyingType=" + this.underlyingType + ')';
    }
}
