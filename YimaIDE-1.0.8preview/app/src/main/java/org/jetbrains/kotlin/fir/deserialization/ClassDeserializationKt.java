package org.jetbrains.kotlin.fir.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentation;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.EnumClassUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.declarations.FirVersionRequirementsTableKeyKt;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.declarations.ValueClassesUtilsKt;
import org.jetbrains.kotlin.fir.declarations.builder.FirEnumEntryBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirOuterClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusWithLazyEffectiveVisibility;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.deserialization.ClassDeserializationKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.SerializationPluginMetadataExtensions;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.serialization.deserialization.ValueClassUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aº\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001a\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001f2\u001a\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u00010\"0\u001f\u001a$\u0010'\u001a\u00020\u0001*\u00020(2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010+\u001a\b\u0012\u0004\u0012\u00020,0&*\u00020-H\u0002\u001a.\u0010.\u001a\b\u0012\u0004\u0012\u0002000/*\u00020-2\f\u00101\u001a\b\u0012\u0004\u0012\u0002020/2\f\u00103\u001a\b\u0012\u0004\u0012\u00020,0&H\u0002\"\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020$0&X\u0082\u0004¢\u0006\u0002\n\u0000\"!\u00104\u001a\u0004\u0018\u000105*\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b6\u00107\"3\u0010<\u001a\u0004\u0018\u00010;*\u00020=2\b\u0010:\u001a\u0004\u0018\u00010;8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A\"\u0015\u0010<\u001a\u00020;*\u00020D8F¢\u0006\u0006\u001a\u0004\b>\u0010E¨\u0006F"}, d2 = {"deserializeClassToSymbol", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "defaultAnnotationDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "kdocDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "serializerExtensionProtocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "parentContext", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "deserializeNestedClass", "Lkotlin/Function2;", "deserializeNestedTypeAlias", "Lorg/jetbrains/kotlin/fir/deserialization/FirNestedTypeAliasDeserializationContext;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "ARRAY", "Lorg/jetbrains/kotlin/name/Name;", "ARRAY_CLASSES", Argument.Delimiters.none, "addCloneForArrayIfNeeded", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getPropertyOrderFromMetadataExtension", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ClassOrBuilder;", "propertiesInOrder", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "versionRequirements", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "orderFromExtension", "deserializationExtension", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension;", "getDeserializationExtension", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension;", "deserializationExtension$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "<set-?>", Argument.Delimiters.none, "registeredInSerializationPluginMetadataExtension", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getRegisteredInSerializationPluginMetadataExtension", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "setRegisteredInSerializationPluginMetadataExtension", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Ljava/lang/Boolean;)V", "registeredInSerializationPluginMetadataExtension$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassDeserializationKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ClassDeserializationKt.class, "deserializationExtension", "getDeserializationExtension(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension;", 1), new MutablePropertyReference1Impl<>(ClassDeserializationKt.class, "registeredInSerializationPluginMetadataExtension", "getRegisteredInSerializationPluginMetadataExtension(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1)};
    private static final Name ARRAY;
    private static final Set<Name> ARRAY_CLASSES;
    private static final NullableArrayMapAccessor deserializationExtension$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor registeredInSerializationPluginMetadataExtension$delegate;

    static {
        Name nameIdentifier = Name.identifier("Array");
        nameIdentifier.getClass();
        ARRAY = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("ByteArray");
        nameIdentifier2.getClass();
        Name nameIdentifier3 = Name.identifier("CharArray");
        nameIdentifier3.getClass();
        Name nameIdentifier4 = Name.identifier("ShortArray");
        nameIdentifier4.getClass();
        Name nameIdentifier5 = Name.identifier("IntArray");
        nameIdentifier5.getClass();
        Name nameIdentifier6 = Name.identifier("LongArray");
        nameIdentifier6.getClass();
        Name nameIdentifier7 = Name.identifier("FloatArray");
        nameIdentifier7.getClass();
        Name nameIdentifier8 = Name.identifier("DoubleArray");
        nameIdentifier8.getClass();
        Name nameIdentifier9 = Name.identifier("BooleanArray");
        nameIdentifier9.getClass();
        ARRAY_CLASSES = SetsKt.setOf(new Name[]{nameIdentifier, nameIdentifier2, nameIdentifier3, nameIdentifier4, nameIdentifier5, nameIdentifier6, nameIdentifier7, nameIdentifier8, nameIdentifier9});
        deserializationExtension$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirDeserializationExtension.class));
        registeredInSerializationPluginMetadataExtension$delegate = FirDeclarationDataRegistry.INSTANCE.data(RegisteredInSerializationPluginMetadataExtensionKey.INSTANCE);
    }

    public static final void addCloneForArrayIfNeeded(FirRegularClassBuilder firRegularClassBuilder, ClassId classId, ConeClassLikeType coneClassLikeType, FirSession firSession) {
        firRegularClassBuilder.getClass();
        classId.getClass();
        firSession.getClass();
        FqName packageFqName = classId.getPackageFqName();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(packageFqName, standardClassIds.getBASE_KOTLIN_PACKAGE()) && ARRAY_CLASSES.contains(classId.getShortClassName()) && FirSymbolProviderKt.getRegularClassSymbolByClassId(firSession, standardClassIds.getCloneable()) != null) {
            List<FirTypeRef> superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(standardClassIds.getCloneable()), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null));
            superTypeRefs.add(firResolvedTypeRefBuilder.build());
            List<FirDeclaration> declarations = firRegularClassBuilder.getDeclarations();
            FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
            firNamedFunctionBuilder.setModuleData(firRegularClassBuilder.getModuleData());
            firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
            firNamedFunctionBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder2.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(classId), Intrinsics.areEqual(classId.getShortClassName(), ARRAY) ? new ConeTypeParameterTypeImpl[]{new ConeTypeParameterTypeImpl(new ConeTypeParameterLookupTag(((FirTypeParameterRef) CollectionsKt.first(firRegularClassBuilder.getTypeParameters())).getSymbol()), false, null, 4, null)} : new ConeTypeParameterTypeImpl[0], false, null, 8, null));
            firNamedFunctionBuilder.setReturnTypeRef(firResolvedTypeRefBuilder2.build());
            FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE);
            firResolvedDeclarationStatusImpl.setOverride(true);
            firNamedFunctionBuilder.setStatus(firResolvedDeclarationStatusImpl);
            firNamedFunctionBuilder.setLocal(false);
            firNamedFunctionBuilder.setName(StandardClassIds.Callables.INSTANCE.getClone().getCallableName());
            firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(classId, firNamedFunctionBuilder.getName())));
            coneClassLikeType.getClass();
            firNamedFunctionBuilder.setDispatchReceiverType(coneClassLikeType);
            declarations.add(firNamedFunctionBuilder.mo288build());
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0403 A[LOOP:8: B:99:0x03fd->B:101:0x0403, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:104:0x04ac  */
    /* JADX WARN: Code duplicated, block: B:105:0x04de  */
    /* JADX WARN: Code duplicated, block: B:108:0x04f4  */
    /* JADX WARN: Code duplicated, block: B:112:0x0509  */
    /* JADX WARN: Code duplicated, block: B:120:0x0526  */
    /* JADX WARN: Code duplicated, block: B:121:0x052b  */
    /* JADX WARN: Code duplicated, block: B:124:0x054d  */
    /* JADX WARN: Code duplicated, block: B:127:0x056b A[LOOP:10: B:125:0x0565->B:127:0x056b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:129:0x058f  */
    /* JADX WARN: Code duplicated, block: B:137:0x05c7  */
    /* JADX WARN: Code duplicated, block: B:145:0x0609  */
    /* JADX WARN: Code duplicated, block: B:149:0x061c  */
    /* JADX WARN: Code duplicated, block: B:151:0x061f  */
    /* JADX WARN: Code duplicated, block: B:154:0x0640 A[LOOP:11: B:152:0x063a->B:154:0x0640, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:164:0x0328 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x039e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:0x0373 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:171:0x03e4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x03b7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:178:0x0521 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:45:0x020c A[LOOP:0: B:43:0x0206->B:45:0x020c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:48:0x0225  */
    /* JADX WARN: Code duplicated, block: B:57:0x0260  */
    /* JADX WARN: Code duplicated, block: B:59:0x0263  */
    /* JADX WARN: Code duplicated, block: B:64:0x028e A[LOOP:2: B:62:0x0288->B:64:0x028e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:68:0x02b8 A[LOOP:3: B:66:0x02b2->B:68:0x02b8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:72:0x030b  */
    /* JADX WARN: Code duplicated, block: B:74:0x0323  */
    /* JADX WARN: Code duplicated, block: B:79:0x034d A[LOOP:5: B:77:0x0347->B:79:0x034d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x0379  */
    /* JADX WARN: Code duplicated, block: B:85:0x0394  */
    /* JADX WARN: Code duplicated, block: B:86:0x039b  */
    /* JADX WARN: Code duplicated, block: B:92:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:94:0x03da  */
    /* JADX WARN: Code duplicated, block: B:95:0x03e1  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void deserializeClassToSymbol(ClassId classId, ProtoBuf.Class r31, FirRegularClassSymbol firRegularClassSymbol, NameResolver nameResolver, FirSession firSession, FirModuleData firModuleData, AnnotationDeserializer annotationDeserializer, FirKDocDeserializer firKDocDeserializer, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, FirScopeProvider firScopeProvider, SerializerExtensionProtocol serializerExtensionProtocol, FirDeserializationContext firDeserializationContext, DeserializedContainerSource deserializedContainerSource, FirDeclarationOrigin firDeclarationOrigin, Function2<? super ClassId, ? super FirDeserializationContext, FirRegularClassSymbol> function2, Function2<? super ClassId, ? super FirNestedTypeAliasDeserializationContext, FirTypeAliasSymbol> function3) {
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility;
        int i;
        ProtoEnumFlags protoEnumFlags;
        AnnotationDeserializer annotationDeserializer2;
        ProtoBuf.Class r2;
        ProtoBuf.Class.Kind kind;
        NameResolver nameResolver2;
        FirRegularClassSymbol firRegularClassSymbol2;
        DeserializedContainerSource deserializedContainerSource2;
        ClassId classId2;
        FirModuleData firModuleData2;
        FirDeserializationContext firDeserializationContext2;
        FirRegularClassBuilder firRegularClassBuilder;
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility2;
        ArrayList arrayList;
        Iterator<T> it;
        FirTypeDeserializer typeDeserializer;
        FirMemberDeserializer memberDeserializer;
        List<FirTypeRef> superTypeRefs;
        Iterator it2;
        ArrayList arrayList2;
        FirRegularClassBuilder firRegularClassBuilder2;
        ProtoBuf.Class r0;
        FirRegularClassSymbol firRegularClassSymbol3;
        FirMemberDeserializer firMemberDeserializer;
        Set<Integer> propertyOrderFromMetadataExtension;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        ArrayList arrayList7;
        Iterator it3;
        FirDeclarationOrigin firDeclarationOrigin2;
        FirRegularClassSymbol firRegularClassSymbol4;
        FirRegularClassBuilder firRegularClassBuilder3;
        FirMemberDeserializer firMemberDeserializer2;
        FirDeserializationExtension deserializationExtension;
        Iterator<T> it4;
        Object next;
        FirRegularClass firRegularClass;
        FirRegularClassSymbol symbol;
        final FirRegularClass firRegularClassBuild;
        boolean z;
        List list;
        FirDeserializationExtension deserializationExtension2;
        ValueClassRepresentation<ConeRigidType> valueClassRepresentationLoadValueClassRepresentation;
        FirDeserializationExtension deserializationExtension3;
        List compilerPluginDataList;
        LinkedHashMap linkedHashMap;
        String strLoadModuleName;
        ArrayList arrayList8;
        FirDeclaration firDeclaration;
        FirTypeAliasSymbol firTypeAliasSymbol;
        FirTypeAlias firTypeAlias;
        FirRegularClassSymbol firRegularClassSymbol5;
        FirRegularClass firRegularClass2;
        FirProperty firPropertyLoadProperty;
        List listEmptyList;
        List<FirTypeParameterSymbol> allTypeParameters;
        classId.getClass();
        r31.getClass();
        firRegularClassSymbol.getClass();
        nameResolver.getClass();
        firSession.getClass();
        firModuleData.getClass();
        firKDocDeserializer.getClass();
        flexibleTypeFactory.getClass();
        firScopeProvider.getClass();
        serializerExtensionProtocol.getClass();
        firDeclarationOrigin.getClass();
        function2.getClass();
        function3.getClass();
        int flags = r31.getFlags();
        ProtoBuf.Class.Kind kind2 = (ProtoBuf.Class.Kind) Flags.CLASS_KIND.get(flags);
        ProtoEnumFlags protoEnumFlags2 = ProtoEnumFlags.INSTANCE;
        Modality modality = protoEnumFlags2.modality((ProtoBuf.Modality) Flags.MODALITY.get(flags));
        Visibility visibility = protoEnumFlags2.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(flags));
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility3 = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, modality, FirMemberDeserializerKt.toLazyEffectiveVisibility(visibility, firDeserializationContext != null ? firDeserializationContext.getOuterClassSymbol() : null, firSession, true));
        Boolean bool = Flags.IS_EXPECT_CLASS.get(flags);
        bool.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setExpect(bool.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setActual(false);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setCompanion(kind2 == ProtoBuf.Class.Kind.COMPANION_OBJECT);
        Boolean bool2 = Flags.IS_INNER.get(flags);
        bool2.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setInner(bool2.booleanValue());
        Boolean bool3 = Flags.IS_DATA.get(r31.getFlags());
        bool3.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setData(bool3.booleanValue());
        Boolean bool4 = Flags.IS_VALUE_CLASS.get(r31.getFlags());
        bool4.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setValue(bool4.booleanValue());
        Boolean bool5 = Flags.IS_EXTERNAL_CLASS.get(r31.getFlags());
        bool5.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setExternal(bool5.booleanValue());
        Boolean bool6 = Flags.IS_FUN_INTERFACE.get(r31.getFlags());
        bool6.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility3.setFun(bool6.booleanValue());
        boolean z2 = modality == Modality.SEALED;
        AnnotationDeserializer firBuiltinAnnotationDeserializer = annotationDeserializer == null ? new FirBuiltinAnnotationDeserializer(firSession) : annotationDeserializer;
        FirDeserializationExtension deserializationExtension4 = getDeserializationExtension(firSession);
        FirConstDeserializer firConstDeserializerCreateConstDeserializer = deserializationExtension4 != null ? deserializationExtension4.createConstDeserializer(deserializedContainerSource, firSession, serializerExtensionProtocol) : null;
        FirConstDeserializer firConstDeserializer = firConstDeserializerCreateConstDeserializer == null ? new FirConstDeserializer(serializerExtensionProtocol) : firConstDeserializerCreateConstDeserializer;
        if (firDeserializationContext != null) {
            List<ProtoBuf.TypeParameter> typeParameterList = r31.getTypeParameterList();
            typeParameterList.getClass();
            ProtoBuf.TypeTable typeTable = r31.getTypeTable();
            typeTable.getClass();
            annotationDeserializer2 = firBuiltinAnnotationDeserializer;
            i = flags;
            firResolvedDeclarationStatusWithLazyEffectiveVisibility = firResolvedDeclarationStatusWithLazyEffectiveVisibility3;
            protoEnumFlags = protoEnumFlags2;
            FirDeserializationContext firDeserializationContextChildContext = firDeserializationContext.childContext(typeParameterList, firRegularClassSymbol, nameResolver, new TypeTable(typeTable), classId.getRelativeClassName(), deserializedContainerSource, firRegularClassSymbol, annotationDeserializer2, (firResolvedDeclarationStatusWithLazyEffectiveVisibility3.isCompanion() || firConstDeserializerCreateConstDeserializer == null) ? firDeserializationContext.getConstDeserializer() : firConstDeserializer, firResolvedDeclarationStatusWithLazyEffectiveVisibility3.isInner());
            if (firDeserializationContextChildContext != null) {
                classId2 = classId;
                r2 = r31;
                firRegularClassSymbol2 = firRegularClassSymbol;
                nameResolver2 = nameResolver;
                firModuleData2 = firModuleData;
                deserializedContainerSource2 = deserializedContainerSource;
                kind = kind2;
                firDeserializationContext2 = firDeserializationContextChildContext;
            }
            if (firResolvedDeclarationStatusWithLazyEffectiveVisibility.isCompanion() && firDeserializationContext != null) {
                firDeserializationContext2.getAnnotationDeserializer().inheritAnnotationInfo(firDeserializationContext.getAnnotationDeserializer());
                Unit unit = Unit.INSTANCE;
            }
            List<VersionRequirement> listCreate = FirDeserializationUtilsKt.create(VersionRequirement.Companion, r2, firDeserializationContext2);
            firRegularClassBuilder = new FirRegularClassBuilder();
            firRegularClassBuilder.setModuleData(firModuleData2);
            firRegularClassBuilder.setOrigin(firDeclarationOrigin);
            firRegularClassBuilder.setName(classId2.getShortClassName());
            firResolvedDeclarationStatusWithLazyEffectiveVisibility2 = firResolvedDeclarationStatusWithLazyEffectiveVisibility;
            firRegularClassBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility2);
            firRegularClassBuilder.setClassKind(protoEnumFlags.classKind(kind));
            firRegularClassBuilder.setScopeProvider(firScopeProvider);
            firRegularClassBuilder.setSymbol(firRegularClassSymbol2);
            firRegularClassBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            List<FirTypeParameterRef> typeParameters = firRegularClassBuilder.getTypeParameters();
            List<FirTypeParameterSymbol> ownTypeParameters = firDeserializationContext2.getTypeDeserializer().getOwnTypeParameters();
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(ownTypeParameters, 10));
            it = ownTypeParameters.iterator();
            while (it.hasNext()) {
                arrayList.add((FirTypeParameter) ((FirTypeParameterSymbol) it.next()).getFir());
            }
            CollectionsKt.addAll(typeParameters, arrayList);
            if (firResolvedDeclarationStatusWithLazyEffectiveVisibility2.isInner()) {
                List<FirTypeParameterRef> typeParameters2 = firRegularClassBuilder.getTypeParameters();
                if (firDeserializationContext != null || (allTypeParameters = firDeserializationContext.getAllTypeParameters()) == null) {
                    listEmptyList = null;
                } else {
                    List<FirTypeParameterSymbol> list2 = allTypeParameters;
                    ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (FirTypeParameterSymbol firTypeParameterSymbol : list2) {
                        FirOuterClassTypeParameterRefBuilder firOuterClassTypeParameterRefBuilder = new FirOuterClassTypeParameterRefBuilder();
                        firOuterClassTypeParameterRefBuilder.setSymbol(firTypeParameterSymbol);
                        arrayList9.add(firOuterClassTypeParameterRefBuilder.build());
                    }
                    listEmptyList = arrayList9;
                }
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                CollectionsKt.addAll(typeParameters2, listEmptyList);
            }
            typeDeserializer = firDeserializationContext2.getTypeDeserializer();
            memberDeserializer = firDeserializationContext2.getMemberDeserializer();
            List listSupertypes = ProtoTypeTableUtilKt.supertypes(r2, firDeserializationContext2.getTypeTable());
            superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
            it2 = listSupertypes.iterator();
            while (it2.hasNext()) {
                superTypeRefs.add(typeDeserializer.typeRef((ProtoBuf.Type) it2.next()));
            }
            List functionList = r2.getFunctionList();
            functionList.getClass();
            List<ProtoBuf.Function> list3 = functionList;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for (ProtoBuf.Function function : list3) {
                function.getClass();
                FirMemberDeserializer firMemberDeserializer3 = memberDeserializer;
                arrayList2.add(FirMemberDeserializer.loadFunction$default(firMemberDeserializer3, function, r2, firRegularClassSymbol2, null, 8, null));
                memberDeserializer = firMemberDeserializer3;
                firRegularClassBuilder = firRegularClassBuilder;
                firRegularClassSymbol2 = firRegularClassSymbol2;
                r2 = r2;
            }
            firRegularClassBuilder2 = firRegularClassBuilder;
            r0 = r2;
            firRegularClassSymbol3 = firRegularClassSymbol2;
            firMemberDeserializer = memberDeserializer;
            FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList2);
            propertyOrderFromMetadataExtension = getPropertyOrderFromMetadataExtension(r0);
            List<ProtoBuf.Property> listPropertiesInOrder = propertiesInOrder(r0, listCreate, propertyOrderFromMetadataExtension);
            arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPropertiesInOrder, 10));
            for (ProtoBuf.Property property : listPropertiesInOrder) {
                firPropertyLoadProperty = firMemberDeserializer.loadProperty(property, r0, firRegularClassSymbol3);
                if (propertyOrderFromMetadataExtension.contains(Integer.valueOf(property.getName()))) {
                    setRegisteredInSerializationPluginMetadataExtension(firPropertyLoadProperty, Boolean.TRUE);
                }
                arrayList3.add(firPropertyLoadProperty);
            }
            FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList3);
            List constructorList = r0.getConstructorList();
            constructorList.getClass();
            List<ProtoBuf.Constructor> list4 = constructorList;
            arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            for (ProtoBuf.Constructor constructor : list4) {
                constructor.getClass();
                arrayList4.add(firMemberDeserializer.loadConstructor(constructor, r0, firRegularClassBuilder2));
            }
            FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList4);
            List<Integer> nestedClassNameList = r0.getNestedClassNameList();
            nestedClassNameList.getClass();
            arrayList5 = new ArrayList();
            for (Integer num : nestedClassNameList) {
                num.getClass();
                firRegularClassSymbol5 = (FirRegularClassSymbol) function2.invoke(deserializeClassToSymbol$lambda$2$createNestedClassId(classId2, nameResolver2, num.intValue()), firDeserializationContext2);
                if (firRegularClassSymbol5 != null) {
                    firRegularClass2 = (FirRegularClass) firRegularClassSymbol5.getFir();
                } else {
                    firRegularClass2 = null;
                }
                if (firRegularClass2 != null) {
                    arrayList5.add(firRegularClass2);
                }
            }
            FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList5);
            List<ProtoBuf.TypeAlias> typeAliasList = r0.getTypeAliasList();
            typeAliasList.getClass();
            arrayList6 = new ArrayList();
            for (ProtoBuf.TypeAlias typeAlias : typeAliasList) {
                firTypeAliasSymbol = (FirTypeAliasSymbol) function3.invoke(deserializeClassToSymbol$lambda$2$createNestedClassId(classId2, nameResolver2, typeAlias.getName()), new FirNestedTypeAliasDeserializationContext(firMemberDeserializer, typeAlias, firScopeProvider));
                if (firTypeAliasSymbol != null) {
                    firTypeAlias = (FirTypeAlias) firTypeAliasSymbol.getFir();
                } else {
                    firTypeAlias = null;
                }
                if (firTypeAlias != null) {
                    arrayList6.add(firTypeAlias);
                }
            }
            FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList6);
            List enumEntryList = r0.getEnumEntryList();
            enumEntryList.getClass();
            arrayList7 = new ArrayList();
            it3 = enumEntryList.iterator();
            while (it3.hasNext()) {
                ProtoBuf.EnumEntry enumEntry = (ProtoBuf.EnumEntry) it3.next();
                Name name = NameResolverUtilKt.getName(nameResolver2, enumEntry.getName());
                ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(firRegularClassSymbol3.getLookupTag(), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null);
                FirEnumEntryBuilder firEnumEntryBuilder = new FirEnumEntryBuilder();
                firEnumEntryBuilder.setModuleData(firModuleData2);
                firEnumEntryBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setConeType(coneClassLikeTypeImpl);
                firEnumEntryBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
                firEnumEntryBuilder.setName(name);
                firEnumEntryBuilder.setSymbol(new FirEnumEntrySymbol(new CallableId(classId2, name)));
                Iterator it5 = it3;
                FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE);
                firResolvedDeclarationStatusImpl.setStatic(true);
                firEnumEntryBuilder.setStatus(firResolvedDeclarationStatusImpl);
                firEnumEntryBuilder.setLocal(false);
                firEnumEntryBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
                FirEnumEntry firEnumEntryBuild = firEnumEntryBuilder.mo288build();
                ConeClassLikeType dispatchReceiver = firDeserializationContext2.getDispatchReceiver();
                dispatchReceiver.getClass();
                ClassMembersKt.setContainingClassForStaticMemberAttr(firEnumEntryBuild, dispatchReceiver.getLookupTag());
                firEnumEntryBuild.replaceAnnotations(firDeserializationContext2.getAnnotationDeserializer().loadEnumEntryAnnotations(classId2, enumEntry, firDeserializationContext2.getNameResolver()));
                arrayList7.add(firEnumEntryBuild);
                it3 = it5;
            }
            FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList7);
            if (firRegularClassBuilder2.getClassKind() == ClassKind.ENUM_CLASS) {
                firMemberDeserializer2 = firMemberDeserializer;
                firRegularClassBuilder3 = firRegularClassBuilder2;
                firRegularClassSymbol4 = firRegularClassSymbol;
                firDeclarationOrigin2 = firDeclarationOrigin;
                EnumClassUtilsKt.generateValuesFunction$default(firRegularClassBuilder3, firModuleData, classId2.getPackageFqName(), classId2.getRelativeClassName(), false, firDeclarationOrigin2, 8, null);
                EnumClassUtilsKt.generateValueOfFunction$default(firRegularClassBuilder3, firModuleData, classId2.getPackageFqName(), classId2.getRelativeClassName(), false, firDeclarationOrigin2, 8, null);
                EnumClassUtilsKt.generateEntriesGetter$default(firRegularClassBuilder3, firModuleData, classId2.getPackageFqName(), classId2.getRelativeClassName(), false, firDeclarationOrigin2, 8, null);
            } else {
                firDeclarationOrigin2 = firDeclarationOrigin;
                firRegularClassSymbol4 = firRegularClassSymbol3;
                firRegularClassBuilder3 = firRegularClassBuilder2;
                firMemberDeserializer2 = firMemberDeserializer;
            }
            addCloneForArrayIfNeeded(firRegularClassBuilder3, classId2, firDeserializationContext2.getDispatchReceiver(), firSession);
            deserializationExtension = getDeserializationExtension(firSession);
            if (deserializationExtension != null) {
                deserializationExtension.configureDeserializedClass(firRegularClassBuilder3, classId2);
                Unit unit2 = Unit.INSTANCE;
            }
            it4 = firRegularClassBuilder3.getDeclarations().iterator();
            while (true) {
                if (it4.hasNext()) {
                    next = null;
                    break;
                }
                next = it4.next();
                firDeclaration = (FirDeclaration) next;
                if (!(firDeclaration instanceof FirRegularClass) && ((FirRegularClass) firDeclaration).getStatus().isCompanion()) {
                    break;
                }
            }
            firRegularClass = (FirRegularClass) next;
            if (firRegularClass != null) {
                symbol = firRegularClass.getSymbol();
            } else {
                symbol = null;
            }
            firRegularClassBuilder3.setCompanionObjectSymbol(symbol);
            firRegularClassBuilder3.getContextParameters().addAll(firMemberDeserializer2.createContextParametersForClass$org_jetbrains_kotlin_fir_deserialization(r31, firDeclarationOrigin2, firRegularClassSymbol4));
            FirKDocDeserializerKt.applyKDoc(firRegularClassBuilder3, firDeserializationContext2.getKdocDeserializer().loadClassKDoc(r31));
            firRegularClassBuild = firRegularClassBuilder3.mo288build();
            if (z2) {
                List sealedSubclassFqNameList = r31.getSealedSubclassFqNameList();
                sealedSubclassFqNameList.getClass();
                List<Integer> list5 = sealedSubclassFqNameList;
                arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
                for (Integer num2 : list5) {
                    ClassId.Companion companion = ClassId.Companion;
                    num2.getClass();
                    arrayList8.add(ClassId.Companion.fromString$default(companion, nameResolver2.getQualifiedClassName(num2.intValue()), false, 2, (Object) null));
                }
                z = false;
                list = null;
                SealedClassInheritorsKt.setSealedClassInheritors(firRegularClassBuild, arrayList8);
            } else {
                z = false;
                list = null;
            }
            deserializationExtension2 = getDeserializationExtension(firSession);
            if (deserializationExtension2 != null && deserializationExtension2.isMaybeMultiFieldValueClass(deserializedContainerSource2)) {
                z = true;
            }
            valueClassRepresentationLoadValueClassRepresentation = ValueClassUtilKt.loadValueClassRepresentation(r31, z, firDeserializationContext2.getNameResolver(), firDeserializationContext2.getTypeTable(), new ClassDeserializationKt$deserializeClassToSymbol$3$1(firDeserializationContext2.getTypeDeserializer()), new Function1() { // from class: xt1
                public final Object invoke(Object obj) {
                    return ClassDeserializationKt.deserializeClassToSymbol$lambda$3$1(firRegularClassBuild, (Name) obj);
                }
            });
            if (valueClassRepresentationLoadValueClassRepresentation == null) {
                valueClassRepresentationLoadValueClassRepresentation = ValueClassesUtilsKt.computeValueClassRepresentation(firRegularClassBuild, firSession);
            }
            FirValueClassRepresentationKt.setValueClassRepresentation(firRegularClassBuild, valueClassRepresentationLoadValueClassRepresentation);
            firRegularClassBuild.replaceAnnotations(firDeserializationContext2.getAnnotationDeserializer().loadClassAnnotations(r31, firDeserializationContext2.getNameResolver()));
            FirVersionRequirementsTableKeyKt.setVersionRequirements(firRegularClassBuild, listCreate);
            DeclarationAttributesKt.setSourceElement(firRegularClassBuild, deserializedContainerSource2);
            firRegularClassBuild.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProvider(firRegularClassBuild, firSession));
            deserializationExtension3 = getDeserializationExtension(firSession);
            if (deserializationExtension3 != null && (strLoadModuleName = deserializationExtension3.loadModuleName(r31, nameResolver2)) != null) {
                DeclarationAttributesKt.setModuleName(firRegularClassBuild, strLoadModuleName);
                Unit unit3 = Unit.INSTANCE;
            }
            if (!Flags.HAS_ENUM_ENTRIES.get(i).booleanValue()) {
                ClassMembersKt.setHasNoEnumEntriesAttr(firRegularClassBuild, Boolean.TRUE);
            }
            compilerPluginDataList = r31.getCompilerPluginDataList();
            if (compilerPluginDataList.isEmpty()) {
                compilerPluginDataList = list;
            }
            if (compilerPluginDataList != null) {
                List<ProtoBuf.CompilerPluginData> list6 = compilerPluginDataList;
                linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list6, 10)), 16));
                for (ProtoBuf.CompilerPluginData compilerPluginData : list6) {
                    linkedHashMap.put(firDeserializationContext2.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
                }
                DeclarationAttributesKt.setCompilerPluginMetadata(firRegularClassBuild, linkedHashMap);
                Unit unit4 = Unit.INSTANCE;
            }
            PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(firRegularClassBuild, firSession);
        }
        firResolvedDeclarationStatusWithLazyEffectiveVisibility = firResolvedDeclarationStatusWithLazyEffectiveVisibility3;
        i = flags;
        protoEnumFlags = protoEnumFlags2;
        annotationDeserializer2 = firBuiltinAnnotationDeserializer;
        r2 = r31;
        kind = kind2;
        FirDeserializationContext firDeserializationContextCreateForClass = FirDeserializationContext.INSTANCE.createForClass(classId, r2, nameResolver, firModuleData, annotationDeserializer2, flexibleTypeFactory, firConstDeserializer, firKDocDeserializer, deserializedContainerSource, firRegularClassSymbol, firResolvedDeclarationStatusWithLazyEffectiveVisibility.getEffectiveVisibility());
        nameResolver2 = nameResolver;
        firRegularClassSymbol2 = firRegularClassSymbol;
        deserializedContainerSource2 = deserializedContainerSource;
        classId2 = classId;
        firModuleData2 = firModuleData;
        firDeserializationContext2 = firDeserializationContextCreateForClass;
        if (firResolvedDeclarationStatusWithLazyEffectiveVisibility.isCompanion()) {
            firDeserializationContext2.getAnnotationDeserializer().inheritAnnotationInfo(firDeserializationContext.getAnnotationDeserializer());
            Unit unit5 = Unit.INSTANCE;
        }
        List<VersionRequirement> listCreate2 = FirDeserializationUtilsKt.create(VersionRequirement.Companion, r2, firDeserializationContext2);
        firRegularClassBuilder = new FirRegularClassBuilder();
        firRegularClassBuilder.setModuleData(firModuleData2);
        firRegularClassBuilder.setOrigin(firDeclarationOrigin);
        firRegularClassBuilder.setName(classId2.getShortClassName());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility2 = firResolvedDeclarationStatusWithLazyEffectiveVisibility;
        firRegularClassBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility2);
        firRegularClassBuilder.setClassKind(protoEnumFlags.classKind(kind));
        firRegularClassBuilder.setScopeProvider(firScopeProvider);
        firRegularClassBuilder.setSymbol(firRegularClassSymbol2);
        firRegularClassBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeParameterRef> typeParameters3 = firRegularClassBuilder.getTypeParameters();
        List<FirTypeParameterSymbol> ownTypeParameters2 = firDeserializationContext2.getTypeDeserializer().getOwnTypeParameters();
        arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(ownTypeParameters2, 10));
        it = ownTypeParameters2.iterator();
        while (it.hasNext()) {
            arrayList.add((FirTypeParameter) ((FirTypeParameterSymbol) it.next()).getFir());
        }
        CollectionsKt.addAll(typeParameters3, arrayList);
        if (firResolvedDeclarationStatusWithLazyEffectiveVisibility2.isInner()) {
            List<FirTypeParameterRef> typeParameters4 = firRegularClassBuilder.getTypeParameters();
            if (firDeserializationContext != null) {
                listEmptyList = null;
            } else {
                listEmptyList = null;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(typeParameters4, listEmptyList);
        }
        typeDeserializer = firDeserializationContext2.getTypeDeserializer();
        memberDeserializer = firDeserializationContext2.getMemberDeserializer();
        List listSupertypes2 = ProtoTypeTableUtilKt.supertypes(r2, firDeserializationContext2.getTypeTable());
        superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
        it2 = listSupertypes2.iterator();
        while (it2.hasNext()) {
            superTypeRefs.add(typeDeserializer.typeRef((ProtoBuf.Type) it2.next()));
        }
        List functionList2 = r2.getFunctionList();
        functionList2.getClass();
        List<ProtoBuf.Function> list7 = functionList2;
        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
        while (r17.hasNext()) {
            function.getClass();
            FirMemberDeserializer firMemberDeserializer4 = memberDeserializer;
            arrayList2.add(FirMemberDeserializer.loadFunction$default(firMemberDeserializer4, function, r2, firRegularClassSymbol2, null, 8, null));
            memberDeserializer = firMemberDeserializer4;
            firRegularClassBuilder = firRegularClassBuilder;
            firRegularClassSymbol2 = firRegularClassSymbol2;
            r2 = r2;
        }
        firRegularClassBuilder2 = firRegularClassBuilder;
        r0 = r2;
        firRegularClassSymbol3 = firRegularClassSymbol2;
        firMemberDeserializer = memberDeserializer;
        FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList2);
        propertyOrderFromMetadataExtension = getPropertyOrderFromMetadataExtension(r0);
        List<ProtoBuf.Property> listPropertiesInOrder2 = propertiesInOrder(r0, listCreate2, propertyOrderFromMetadataExtension);
        arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPropertiesInOrder2, 10));
        while (r4.hasNext()) {
            firPropertyLoadProperty = firMemberDeserializer.loadProperty(property, r0, firRegularClassSymbol3);
            if (propertyOrderFromMetadataExtension.contains(Integer.valueOf(property.getName()))) {
                setRegisteredInSerializationPluginMetadataExtension(firPropertyLoadProperty, Boolean.TRUE);
            }
            arrayList3.add(firPropertyLoadProperty);
        }
        FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList3);
        List constructorList2 = r0.getConstructorList();
        constructorList2.getClass();
        List<ProtoBuf.Constructor> list8 = constructorList2;
        arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list8, 10));
        while (r1.hasNext()) {
            constructor.getClass();
            arrayList4.add(firMemberDeserializer.loadConstructor(constructor, r0, firRegularClassBuilder2));
        }
        FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList4);
        List<Integer> nestedClassNameList2 = r0.getNestedClassNameList();
        nestedClassNameList2.getClass();
        arrayList5 = new ArrayList();
        while (r1.hasNext()) {
            num.getClass();
            firRegularClassSymbol5 = (FirRegularClassSymbol) function2.invoke(deserializeClassToSymbol$lambda$2$createNestedClassId(classId2, nameResolver2, num.intValue()), firDeserializationContext2);
            if (firRegularClassSymbol5 != null) {
                firRegularClass2 = (FirRegularClass) firRegularClassSymbol5.getFir();
            } else {
                firRegularClass2 = null;
            }
            if (firRegularClass2 != null) {
                arrayList5.add(firRegularClass2);
            }
        }
        FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList5);
        List<ProtoBuf.TypeAlias> typeAliasList2 = r0.getTypeAliasList();
        typeAliasList2.getClass();
        arrayList6 = new ArrayList();
        while (r1.hasNext()) {
            firTypeAliasSymbol = (FirTypeAliasSymbol) function3.invoke(deserializeClassToSymbol$lambda$2$createNestedClassId(classId2, nameResolver2, typeAlias.getName()), new FirNestedTypeAliasDeserializationContext(firMemberDeserializer, typeAlias, firScopeProvider));
            if (firTypeAliasSymbol != null) {
                firTypeAlias = (FirTypeAlias) firTypeAliasSymbol.getFir();
            } else {
                firTypeAlias = null;
            }
            if (firTypeAlias != null) {
                arrayList6.add(firTypeAlias);
            }
        }
        FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList6);
        List enumEntryList2 = r0.getEnumEntryList();
        enumEntryList2.getClass();
        arrayList7 = new ArrayList();
        it3 = enumEntryList2.iterator();
        while (it3.hasNext()) {
            ProtoBuf.EnumEntry enumEntry2 = (ProtoBuf.EnumEntry) it3.next();
            Name name2 = NameResolverUtilKt.getName(nameResolver2, enumEntry2.getName());
            ConeClassLikeTypeImpl coneClassLikeTypeImpl2 = new ConeClassLikeTypeImpl(firRegularClassSymbol3.getLookupTag(), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null);
            FirEnumEntryBuilder firEnumEntryBuilder2 = new FirEnumEntryBuilder();
            firEnumEntryBuilder2.setModuleData(firModuleData2);
            firEnumEntryBuilder2.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder2.setConeType(coneClassLikeTypeImpl2);
            firEnumEntryBuilder2.setReturnTypeRef(firResolvedTypeRefBuilder2.build());
            firEnumEntryBuilder2.setName(name2);
            firEnumEntryBuilder2.setSymbol(new FirEnumEntrySymbol(new CallableId(classId2, name2)));
            Iterator it6 = it3;
            FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl2 = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE);
            firResolvedDeclarationStatusImpl2.setStatic(true);
            firEnumEntryBuilder2.setStatus(firResolvedDeclarationStatusImpl2);
            firEnumEntryBuilder2.setLocal(false);
            firEnumEntryBuilder2.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            FirEnumEntry firEnumEntryBuild2 = firEnumEntryBuilder2.mo288build();
            ConeClassLikeType dispatchReceiver2 = firDeserializationContext2.getDispatchReceiver();
            dispatchReceiver2.getClass();
            ClassMembersKt.setContainingClassForStaticMemberAttr(firEnumEntryBuild2, dispatchReceiver2.getLookupTag());
            firEnumEntryBuild2.replaceAnnotations(firDeserializationContext2.getAnnotationDeserializer().loadEnumEntryAnnotations(classId2, enumEntry2, firDeserializationContext2.getNameResolver()));
            arrayList7.add(firEnumEntryBuild2);
            it3 = it6;
        }
        FirDeclarationBuildingUtilsKt.addDeclarations(firRegularClassBuilder2, arrayList7);
        if (firRegularClassBuilder2.getClassKind() == ClassKind.ENUM_CLASS) {
            firMemberDeserializer2 = firMemberDeserializer;
            firRegularClassBuilder3 = firRegularClassBuilder2;
            firRegularClassSymbol4 = firRegularClassSymbol;
            firDeclarationOrigin2 = firDeclarationOrigin;
            EnumClassUtilsKt.generateValuesFunction$default(firRegularClassBuilder3, firModuleData, classId2.getPackageFqName(), classId2.getRelativeClassName(), false, firDeclarationOrigin2, 8, null);
            EnumClassUtilsKt.generateValueOfFunction$default(firRegularClassBuilder3, firModuleData, classId2.getPackageFqName(), classId2.getRelativeClassName(), false, firDeclarationOrigin2, 8, null);
            EnumClassUtilsKt.generateEntriesGetter$default(firRegularClassBuilder3, firModuleData, classId2.getPackageFqName(), classId2.getRelativeClassName(), false, firDeclarationOrigin2, 8, null);
        } else {
            firDeclarationOrigin2 = firDeclarationOrigin;
            firRegularClassSymbol4 = firRegularClassSymbol3;
            firRegularClassBuilder3 = firRegularClassBuilder2;
            firMemberDeserializer2 = firMemberDeserializer;
        }
        addCloneForArrayIfNeeded(firRegularClassBuilder3, classId2, firDeserializationContext2.getDispatchReceiver(), firSession);
        deserializationExtension = getDeserializationExtension(firSession);
        if (deserializationExtension != null) {
            deserializationExtension.configureDeserializedClass(firRegularClassBuilder3, classId2);
            Unit unit6 = Unit.INSTANCE;
        }
        it4 = firRegularClassBuilder3.getDeclarations().iterator();
        while (true) {
            if (it4.hasNext()) {
                next = null;
                break;
            } else {
                next = it4.next();
                firDeclaration = (FirDeclaration) next;
                if (!(firDeclaration instanceof FirRegularClass)) {
                }
            }
        }
        firRegularClass = (FirRegularClass) next;
        if (firRegularClass != null) {
            symbol = firRegularClass.getSymbol();
        } else {
            symbol = null;
        }
        firRegularClassBuilder3.setCompanionObjectSymbol(symbol);
        firRegularClassBuilder3.getContextParameters().addAll(firMemberDeserializer2.createContextParametersForClass$org_jetbrains_kotlin_fir_deserialization(r31, firDeclarationOrigin2, firRegularClassSymbol4));
        FirKDocDeserializerKt.applyKDoc(firRegularClassBuilder3, firDeserializationContext2.getKdocDeserializer().loadClassKDoc(r31));
        firRegularClassBuild = firRegularClassBuilder3.mo288build();
        if (z2) {
            List sealedSubclassFqNameList2 = r31.getSealedSubclassFqNameList();
            sealedSubclassFqNameList2.getClass();
            List<Integer> list9 = sealedSubclassFqNameList2;
            arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list9, 10));
            while (r1.hasNext()) {
                ClassId.Companion companion2 = ClassId.Companion;
                num2.getClass();
                arrayList8.add(ClassId.Companion.fromString$default(companion2, nameResolver2.getQualifiedClassName(num2.intValue()), false, 2, (Object) null));
            }
            z = false;
            list = null;
            SealedClassInheritorsKt.setSealedClassInheritors(firRegularClassBuild, arrayList8);
        } else {
            z = false;
            list = null;
        }
        deserializationExtension2 = getDeserializationExtension(firSession);
        if (deserializationExtension2 != null) {
            z = true;
        }
        valueClassRepresentationLoadValueClassRepresentation = ValueClassUtilKt.loadValueClassRepresentation(r31, z, firDeserializationContext2.getNameResolver(), firDeserializationContext2.getTypeTable(), new ClassDeserializationKt$deserializeClassToSymbol$3$1(firDeserializationContext2.getTypeDeserializer()), new Function1() { // from class: xt1
            public final Object invoke(Object obj) {
                return ClassDeserializationKt.deserializeClassToSymbol$lambda$3$1(firRegularClassBuild, (Name) obj);
            }
        });
        if (valueClassRepresentationLoadValueClassRepresentation == null) {
            valueClassRepresentationLoadValueClassRepresentation = ValueClassesUtilsKt.computeValueClassRepresentation(firRegularClassBuild, firSession);
        }
        FirValueClassRepresentationKt.setValueClassRepresentation(firRegularClassBuild, valueClassRepresentationLoadValueClassRepresentation);
        firRegularClassBuild.replaceAnnotations(firDeserializationContext2.getAnnotationDeserializer().loadClassAnnotations(r31, firDeserializationContext2.getNameResolver()));
        FirVersionRequirementsTableKeyKt.setVersionRequirements(firRegularClassBuild, listCreate2);
        DeclarationAttributesKt.setSourceElement(firRegularClassBuild, deserializedContainerSource2);
        firRegularClassBuild.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProvider(firRegularClassBuild, firSession));
        deserializationExtension3 = getDeserializationExtension(firSession);
        if (deserializationExtension3 != null) {
            DeclarationAttributesKt.setModuleName(firRegularClassBuild, strLoadModuleName);
            Unit unit7 = Unit.INSTANCE;
        }
        if (!Flags.HAS_ENUM_ENTRIES.get(i).booleanValue()) {
            ClassMembersKt.setHasNoEnumEntriesAttr(firRegularClassBuild, Boolean.TRUE);
        }
        compilerPluginDataList = r31.getCompilerPluginDataList();
        if (compilerPluginDataList.isEmpty()) {
            compilerPluginDataList = list;
        }
        if (compilerPluginDataList != null) {
            List<ProtoBuf.CompilerPluginData> list10 = compilerPluginDataList;
            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list10, 10)), 16));
            while (r1.hasNext()) {
                linkedHashMap.put(firDeserializationContext2.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
            }
            DeclarationAttributesKt.setCompilerPluginMetadata(firRegularClassBuild, linkedHashMap);
            Unit unit8 = Unit.INSTANCE;
        }
        PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(firRegularClassBuild, firSession);
    }

    private static final ClassId deserializeClassToSymbol$lambda$2$createNestedClassId(ClassId classId, NameResolver nameResolver, int i) {
        Name nameIdentifier = Name.identifier(nameResolver.getString(i));
        nameIdentifier.getClass();
        return classId.createNestedClassId(nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeRigidType deserializeClassToSymbol$lambda$3$1(FirRegularClass firRegularClass, Name name) {
        FirTypeRef returnTypeRef;
        name.getClass();
        Iterator<T> it = firRegularClass.getDeclarations().iterator();
        ConeKotlinType coneType = null;
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            FirDeclaration firDeclaration = (FirDeclaration) next;
            if (firDeclaration instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                if (firProperty.getReceiverParameter() == null && Intrinsics.areEqual(firProperty.getName(), name)) {
                    if (!z) {
                        z = true;
                        obj = next;
                    }
                }
            }
            obj = null;
            break;
        }
        FirProperty firProperty2 = (FirProperty) ((FirDeclaration) obj);
        if (firProperty2 != null && (returnTypeRef = firProperty2.getReturnTypeRef()) != null) {
            coneType = FirTypeUtilsKt.getConeType(returnTypeRef);
        }
        coneType.getClass();
        return (ConeRigidType) coneType;
    }

    public static final FirDeserializationExtension getDeserializationExtension(FirSession firSession) {
        firSession.getClass();
        return (FirDeserializationExtension) deserializationExtension$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    private static final Set<Integer> getPropertyOrderFromMetadataExtension(ProtoBuf.ClassOrBuilder classOrBuilder) {
        Object extension = classOrBuilder.getExtension(SerializationPluginMetadataExtensions.propertiesNamesInProgramOrder);
        extension.getClass();
        return CollectionsKt.toSet((Iterable) extension);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getRegisteredInSerializationPluginMetadataExtension(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        Boolean registeredInSerializationPluginMetadataExtension = getRegisteredInSerializationPluginMetadataExtension((FirProperty) firPropertySymbol.getFir());
        if (registeredInSerializationPluginMetadataExtension != null) {
            return registeredInSerializationPluginMetadataExtension.booleanValue();
        }
        return false;
    }

    private static final List<ProtoBuf.Property> propertiesInOrder(ProtoBuf.ClassOrBuilder classOrBuilder, List<VersionRequirement> list, Set<Integer> set) {
        List<ProtoBuf.Property> propertyList = classOrBuilder.getPropertyList();
        List<VersionRequirement> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                if (((VersionRequirement) it.next()).getVersion().getMajor() >= 2) {
                    propertyList.getClass();
                    return propertyList;
                }
            }
        }
        if (set.isEmpty()) {
            propertyList.getClass();
            return propertyList;
        }
        propertyList.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : propertyList) {
            Integer numValueOf = Integer.valueOf(((ProtoBuf.Property) obj).getName());
            Object arrayList = linkedHashMap.get(numValueOf);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(numValueOf, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = set.iterator();
        while (it2.hasNext()) {
            List listEmptyList = (List) linkedHashMap.get(Integer.valueOf(((Number) it2.next()).intValue()));
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList2, listEmptyList);
        }
        if (arrayList2.size() == propertyList.size()) {
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : propertyList) {
            if (!set.contains(Integer.valueOf(((ProtoBuf.Property) obj2).getName()))) {
                arrayList3.add(obj2);
            }
        }
        return CollectionsKt.plus(arrayList2, arrayList3);
    }

    private static final void setRegisteredInSerializationPluginMetadataExtension(FirProperty firProperty, Boolean bool) {
        registeredInSerializationPluginMetadataExtension$delegate.setValue(firProperty, $$delegatedProperties[1], bool);
    }

    private static final Boolean getRegisteredInSerializationPluginMetadataExtension(FirProperty firProperty) {
        return (Boolean) registeredInSerializationPluginMetadataExtension$delegate.getValue(firProperty, $$delegatedProperties[1]);
    }
}
