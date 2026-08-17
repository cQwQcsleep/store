package org.jetbrains.kotlin.descriptors;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\u0017\u0010\u0011\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0010\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\u0012J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004R&\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/MultiFieldValueClassRepresentation;", "Type", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "underlyingPropertyNamesToTypes", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Ljava/util/List;)V", "getUnderlyingPropertyNamesToTypes", "()Ljava/util/List;", "map", Argument.Delimiters.none, "containsPropertyWithName", Argument.Delimiters.none, ModuleXmlParser.NAME, "getPropertyTypeByName", "(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MultiFieldValueClassRepresentation<Type extends RigidTypeMarker> extends ValueClassRepresentation<Type> {
    private final Map<Name, Type> map;
    private final List<Pair<Name, Type>> underlyingPropertyNamesToTypes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiFieldValueClassRepresentation(List<? extends Pair<Name, ? extends Type>> list) {
        super(null);
        list.getClass();
        this.underlyingPropertyNamesToTypes = list;
        this.map = MapsKt.toMap(getUnderlyingPropertyNamesToTypes());
    }

    @Override // org.jetbrains.kotlin.descriptors.ValueClassRepresentation
    public boolean containsPropertyWithName(Name name) {
        name.getClass();
        return this.map.containsKey(name);
    }

    @Override // org.jetbrains.kotlin.descriptors.ValueClassRepresentation
    public Type getPropertyTypeByName(Name name) {
        name.getClass();
        return this.map.get(name);
    }

    @Override // org.jetbrains.kotlin.descriptors.ValueClassRepresentation
    public List<Pair<Name, Type>> getUnderlyingPropertyNamesToTypes() {
        return this.underlyingPropertyNamesToTypes;
    }

    public String toString() {
        return "MultiFieldValueClassRepresentation(underlyingPropertyNamesToTypes=" + getUnderlyingPropertyNamesToTypes() + ')';
    }
}
