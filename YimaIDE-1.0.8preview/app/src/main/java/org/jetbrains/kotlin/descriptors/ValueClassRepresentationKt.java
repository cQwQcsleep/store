package org.jetbrains.kotlin.descriptors;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u00020\u00070\u0006\u001a8\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00042\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u00020\u00070\u0006¨\u0006\f"}, d2 = {"valueClassLoweringKind", "Lorg/jetbrains/kotlin/descriptors/ValueClassKind;", "Type", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "fields", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/Name;", "createValueClassRepresentation", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "context", "org.jetbrains.kotlin:compiler.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ValueClassRepresentationKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ValueClassKind.values().length];
            try {
                iArr[ValueClassKind.Inline.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ValueClassKind.MultiField.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final <Type extends RigidTypeMarker> ValueClassRepresentation<Type> createValueClassRepresentation(TypeSystemCommonBackendContext typeSystemCommonBackendContext, List<? extends Pair<Name, ? extends Type>> list) {
        typeSystemCommonBackendContext.getClass();
        list.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[valueClassLoweringKind(typeSystemCommonBackendContext, list).ordinal()];
        if (i == 1) {
            return new InlineClassRepresentation((Name) list.get(0).getFirst(), (RigidTypeMarker) list.get(0).getSecond());
        }
        if (i == 2) {
            return new MultiFieldValueClassRepresentation(list);
        }
        bu8.a();
        return null;
    }

    public static final <Type extends RigidTypeMarker> ValueClassKind valueClassLoweringKind(TypeSystemCommonBackendContext typeSystemCommonBackendContext, List<? extends Pair<Name, ? extends Type>> list) {
        typeSystemCommonBackendContext.getClass();
        list.getClass();
        if (list.size() > 1) {
            return ValueClassKind.MultiField;
        }
        if (list.isEmpty()) {
            k2d.a("Value classes cannot have 0 fields");
            return null;
        }
        RigidTypeMarker rigidTypeMarker = (RigidTypeMarker) ((Pair) CollectionsKt.single(list)).getSecond();
        if (!typeSystemCommonBackendContext.isNullableType(rigidTypeMarker) && typeSystemCommonBackendContext.isMultiFieldValueClass(typeSystemCommonBackendContext.typeConstructor(rigidTypeMarker))) {
            return ValueClassKind.MultiField;
        }
        return ValueClassKind.Inline;
    }
}
