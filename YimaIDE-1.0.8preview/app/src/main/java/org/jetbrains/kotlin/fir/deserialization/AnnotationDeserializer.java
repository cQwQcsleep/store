package org.jetbrains.kotlin.fir.deserialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001>B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0000H&J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rH&J0\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H&J:\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H&J0\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H&J0\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H&J8\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H&J8\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020 H&J0\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H&JR\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020 H&J&\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010\f\u001a\u00020\rH&J8\u00103\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010'\u001a\u00020(2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,H&J4\u00104\u001a\u0004\u0018\u0001052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u00106\u001a\u0002072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H&J\u001e\u00108\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u00109\u001a\u00020:2\u0006\u0010\f\u001a\u00020\rH&J\u001e\u0010;\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010<\u001a\u00020=2\u0006\u0010\f\u001a\u00020\rH&¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", Argument.Delimiters.none, "<init>", "()V", "inheritAnnotationInfo", Argument.Delimiters.none, "parent", "loadClassAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadTypeAliasAnnotations", "aliasProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;", "loadFunctionAnnotations", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "functionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "loadPropertyAnnotations", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "containingClassProto", "loadPropertyBackingFieldAnnotations", "loadPropertyDelegatedFieldAnnotations", "loadPropertyGetterAnnotations", "getterFlags", Argument.Delimiters.none, "loadPropertySetterAnnotations", "setterFlags", "loadConstructorAnnotations", "constructorProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "loadValueParameterAnnotations", "callableProto", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "valueParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "kind", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer$CallableKind;", "parameterIndex", "loadEnumEntryAnnotations", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry;", "loadExtensionReceiverParameterAnnotations", "loadAnnotationPropertyDefaultValue", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "expectedPropertyType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "loadTypeAnnotations", "typeProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "loadTypeParameterAnnotations", "typeParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "CallableKind", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AnnotationDeserializer {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer$CallableKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "PROPERTY", "PROPERTY_GETTER", "PROPERTY_SETTER", "OTHERS", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum CallableKind {
        PROPERTY,
        PROPERTY_GETTER,
        PROPERTY_SETTER,
        OTHERS;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<CallableKind> getEntries() {
            return $ENTRIES;
        }
    }

    public abstract void inheritAnnotationInfo(AnnotationDeserializer parent);

    public abstract FirExpression loadAnnotationPropertyDefaultValue(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, FirTypeRef expectedPropertyType, NameResolver nameResolver, TypeTable typeTable);

    public abstract List<FirAnnotation> loadClassAnnotations(ProtoBuf.Class classProto, NameResolver nameResolver);

    public abstract List<FirAnnotation> loadConstructorAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Constructor constructorProto, NameResolver nameResolver, TypeTable typeTable);

    public abstract List<FirAnnotation> loadEnumEntryAnnotations(ClassId classId, ProtoBuf.EnumEntry enumEntryProto, NameResolver nameResolver);

    public abstract List<FirAnnotation> loadExtensionReceiverParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, NameResolver nameResolver, TypeTable typeTable, CallableKind kind);

    public abstract List<FirAnnotation> loadFunctionAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Function functionProto, NameResolver nameResolver, TypeTable typeTable);

    public abstract List<FirAnnotation> loadPropertyAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, ProtoBuf.Class containingClassProto, NameResolver nameResolver, TypeTable typeTable);

    public abstract List<FirAnnotation> loadPropertyBackingFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable);

    public abstract List<FirAnnotation> loadPropertyDelegatedFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable);

    public abstract List<FirAnnotation> loadPropertyGetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int getterFlags);

    public abstract List<FirAnnotation> loadPropertySetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int setterFlags);

    public abstract List<FirAnnotation> loadTypeAliasAnnotations(ProtoBuf.TypeAlias aliasProto, NameResolver nameResolver);

    public abstract List<FirAnnotation> loadTypeAnnotations(ProtoBuf.Type typeProto, NameResolver nameResolver);

    public abstract List<FirAnnotation> loadTypeParameterAnnotations(ProtoBuf.TypeParameter typeParameterProto, NameResolver nameResolver);

    public abstract List<FirAnnotation> loadValueParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, ProtoBuf.ValueParameter valueParameterProto, ProtoBuf.Class classProto, NameResolver nameResolver, TypeTable typeTable, CallableKind kind, int parameterIndex);
}
