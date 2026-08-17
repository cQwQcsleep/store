package org.jetbrains.kotlin.fir.deserialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDynamicUnsupported;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001DBU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000\u0012\f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u001d\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020'2\u0006\u0010$\u001a\u00020%H\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010$\u001a\u00020%J\u000e\u0010*\u001a\u00020+2\u0006\u0010$\u001a\u00020%J\u0018\u0010(\u001a\u00020)2\u0006\u0010$\u001a\u00020%2\u0006\u0010,\u001a\u00020'H\u0002J\u0012\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020\u0016H\u0002J\f\u00100\u001a\u000201*\u000202H\u0002J\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u00170\r*\u0006\u0012\u0002\b\u000304J\u001a\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010$\u001a\u00020%2\u0006\u0010,\u001a\u00020'H\u0002J5\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020 2\f\u00108\u001a\b\u0012\u0004\u0012\u00020:092\u0006\u0010;\u001a\u00020<2\u0006\u0010,\u001a\u00020'H\u0002¢\u0006\u0002\u0010=J3\u0010>\u001a\u0002062\u0006\u00107\u001a\u00020 2\f\u00108\u001a\b\u0012\u0004\u0012\u00020:092\u0006\u0010;\u001a\u00020<2\u0006\u0010,\u001a\u00020'H\u0002¢\u0006\u0002\u0010=J\u0012\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010A\u001a\u00020:2\u0006\u0010B\u001a\u00020CH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\r8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006E"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer;", Argument.Delimiters.none, "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "annotationDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "typeParameterProtos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "parent", "containingSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;Ljava/util/List;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "typeParameterDescriptors", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "typeParameterNames", Argument.Delimiters.none, "ownTypeParameters", "getOwnTypeParameters", "()Ljava/util/List;", "forChildContext", "containingDeclarationSymbol", "computeClassifier", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "fqNameIndex", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "attributesFromAnnotations", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "rigidType", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "attributes", "typeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "typeParameterId", "convertVariance", "Lorg/jetbrains/kotlin/types/Variance;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Variance;", "typeParameters", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "createSuspendFunctionTypeForBasicCase", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "functionTypeConstructor", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isNullable", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "createSuspendFunctionType", "typeSymbol", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "typeArgument", "typeArgumentProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Argument;", "FlexibleTypeFactory", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeDeserializer {
    private final AnnotationDeserializer annotationDeserializer;
    private final FirBasedSymbol<?> containingSymbol;
    private final FlexibleTypeFactory flexibleTypeFactory;
    private final FirModuleData moduleData;
    private final NameResolver nameResolver;
    private final FirTypeDeserializer parent;
    private final Map<Integer, FirTypeParameterSymbol> typeParameterDescriptors;
    private final Map<String, FirTypeParameterSymbol> typeParameterNames;
    private final TypeTable typeTable;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u000bJ \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J \u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", Argument.Delimiters.none, "createFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "createDynamicType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Default", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface FlexibleTypeFactory {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory$Default;", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "<init>", "()V", "createFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "createDynamicType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Default implements FlexibleTypeFactory {
            public static final Default INSTANCE = new Default();

            private Default() {
            }

            @Override // org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer.FlexibleTypeFactory
            /* JADX INFO: renamed from: createDynamicType */
            public ConeKotlinType mo620createDynamicType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound) {
                proto.getClass();
                lowerBound.getClass();
                upperBound.getClass();
                return new ConeErrorType(ConeDynamicUnsupported.INSTANCE, false, null, null, null, null, null, 126, null);
            }

            @Override // org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer.FlexibleTypeFactory
            public ConeFlexibleType createFlexibleType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound) {
                proto.getClass();
                lowerBound.getClass();
                upperBound.getClass();
                return new ConeFlexibleType(lowerBound, upperBound, false);
            }
        }

        /* JADX INFO: renamed from: createDynamicType */
        ConeKotlinType mo620createDynamicType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound);

        ConeFlexibleType createFlexibleType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound);
    }

    public FirTypeDeserializer(FirModuleData firModuleData, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer annotationDeserializer, FlexibleTypeFactory flexibleTypeFactory, List<ProtoBuf.TypeParameter> list, FirTypeDeserializer firTypeDeserializer, FirBasedSymbol<?> firBasedSymbol) {
        firModuleData.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        annotationDeserializer.getClass();
        flexibleTypeFactory.getClass();
        list.getClass();
        this.moduleData = firModuleData;
        this.nameResolver = nameResolver;
        this.typeTable = typeTable;
        this.annotationDeserializer = annotationDeserializer;
        this.flexibleTypeFactory = flexibleTypeFactory;
        this.parent = firTypeDeserializer;
        this.containingSymbol = firBasedSymbol;
        List<ProtoBuf.TypeParameter> list2 = list;
        Map<Integer, FirTypeParameterSymbol> linkedHashMap = !list2.isEmpty() ? new LinkedHashMap<>() : MapsKt.emptyMap();
        this.typeParameterDescriptors = linkedHashMap;
        if (list2.isEmpty()) {
            this.typeParameterNames = MapsKt.emptyMap();
            return;
        }
        this.typeParameterNames = new HashMap();
        linkedHashMap.getClass();
        LinkedHashMap linkedHashMap2 = (LinkedHashMap) linkedHashMap;
        ArrayList arrayList = new ArrayList();
        for (ProtoBuf.TypeParameter typeParameter : list) {
            if (typeParameter.hasId()) {
                Name name = NameResolverUtilKt.getName(this.nameResolver, typeParameter.getName());
                FirTypeParameterSymbol firTypeParameterSymbol = new FirTypeParameterSymbol();
                this.typeParameterNames.put(name.asString(), firTypeParameterSymbol);
                FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
                firTypeParameterBuilder.setModuleData(this.moduleData);
                firTypeParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
                firTypeParameterBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
                firTypeParameterBuilder.setName(name);
                firTypeParameterBuilder.setSymbol(firTypeParameterSymbol);
                FirBasedSymbol<?> firBasedSymbol2 = this.containingSymbol;
                if (firBasedSymbol2 == null) {
                    k2d.a("Top-level type parameter ???");
                    throw null;
                }
                firTypeParameterBuilder.setContainingDeclarationSymbol(firBasedSymbol2);
                ProtoBuf.TypeParameter.Variance variance = typeParameter.getVariance();
                variance.getClass();
                firTypeParameterBuilder.setVariance(convertVariance(variance));
                firTypeParameterBuilder.setReified(typeParameter.getReified());
                CollectionsKt.addAll(firTypeParameterBuilder.getAnnotations(), this.annotationDeserializer.loadTypeParameterAnnotations(typeParameter, this.nameResolver));
                arrayList.add(firTypeParameterBuilder);
                linkedHashMap2.put(Integer.valueOf(typeParameter.getId()), firTypeParameterSymbol);
            }
        }
        int i = 0;
        for (ProtoBuf.TypeParameter typeParameter2 : list) {
            int i2 = i + 1;
            FirTypeParameterBuilder firTypeParameterBuilder2 = (FirTypeParameterBuilder) arrayList.get(i);
            List<ProtoBuf.Type> listUpperBounds = ProtoTypeTableUtilKt.upperBounds(typeParameter2, this.typeTable);
            List<FirTypeRef> bounds = firTypeParameterBuilder2.getBounds();
            for (ProtoBuf.Type type : listUpperBounds) {
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setConeType(type(type));
                bounds.add(firResolvedTypeRefBuilder.build());
            }
            FirDeclarationBuildingUtilsKt.addDefaultBoundIfNecessary(firTypeParameterBuilder2);
            firTypeParameterBuilder2.mo288build();
            i = i2;
        }
    }

    private final ConeAttributes attributesFromAnnotations(ProtoBuf.Type proto) {
        return CopyUtilsKt.computeTypeAttributes$default(this.annotationDeserializer.loadTypeAnnotations(proto, this.nameResolver), this.moduleData.getSession(), null, false, false, 6, null);
    }

    private final ConeClassLikeLookupTag computeClassifier(int fqNameIndex) {
        try {
            ClassId classId = NameResolverUtilKt.getClassId(this.nameResolver, fqNameIndex);
            if (classId.isLocal()) {
                classId = null;
            }
            if (classId == null) {
                classId = StandardClassIds.INSTANCE.getAny();
            }
            return TypeConstructionUtilsKt.toLookupTag(classId);
        } catch (Throwable th) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            eyf.a("Looking up for ", NameResolverUtilKt.getClassId(this.nameResolver, fqNameIndex), th);
            return null;
        }
    }

    private final Variance convertVariance(ProtoBuf.TypeParameter.Variance variance) {
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return Variance.IN_VARIANCE;
        }
        if (i == 2) {
            return Variance.OUT_VARIANCE;
        }
        if (i == 3) {
            return Variance.INVARIANT;
        }
        bu8.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeClassLikeType createSuspendFunctionType(ConeClassLikeLookupTag functionTypeConstructor, ConeTypeProjection[] arguments, boolean isNullable, ConeAttributes attributes) {
        int length;
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(functionTypeConstructor, this.moduleData.getSession());
        ConeClassLikeType coneClassLikeTypeCreateSuspendFunctionTypeForBasicCase = null;
        FirClassLikeDeclaration firClassLikeDeclaration = symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null;
        firClassLikeDeclaration.getClass();
        int size = firClassLikeDeclaration.getTypeParameters().size() - arguments.length;
        if (size == 0) {
            coneClassLikeTypeCreateSuspendFunctionTypeForBasicCase = createSuspendFunctionTypeForBasicCase(functionTypeConstructor, arguments, isNullable, attributes);
        } else if (size == 1 && (length = arguments.length - 1) >= 0) {
            FunctionTypeKind.SuspendFunction suspendFunction = FunctionTypeKind.SuspendFunction.INSTANCE;
            coneClassLikeTypeCreateSuspendFunctionTypeForBasicCase = new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(new ClassId(suspendFunction.getPackageFqName(), suspendFunction.numberedClassName(length))), arguments, isNullable, attributes);
        }
        if (coneClassLikeTypeCreateSuspendFunctionTypeForBasicCase != null) {
            return coneClassLikeTypeCreateSuspendFunctionTypeForBasicCase;
        }
        return new ConeErrorType(new ConeSimpleDiagnostic("Bad suspend function in metadata with constructor: " + functionTypeConstructor, DiagnosticKind.DeserializationError), false, null, null, null, null, null, 126, null);
    }

    private final ConeClassLikeType createSuspendFunctionTypeForBasicCase(ConeClassLikeLookupTag functionTypeConstructor, ConeTypeProjection[] arguments, boolean isNullable, ConeAttributes attributes) {
        ConeKotlinType type;
        ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.getOrNull(arguments, ArraysKt.getLastIndex(arguments) - 1);
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = (coneTypeProjection == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null) ? null : ConeTypeUtilsKt.lowerBoundIfFlexible(type);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null) {
            return null;
        }
        if (!createSuspendFunctionTypeForBasicCase$isContinuation(coneClassLikeType)) {
            return new ConeClassLikeTypeImpl(functionTypeConstructor, arguments, isNullable, attributes);
        }
        ConeTypeProjection coneTypeProjection2 = (ConeTypeProjection) ArraysKt.single(coneClassLikeType.getTypeArguments());
        List listDropLast = ArraysKt.dropLast(arguments, 2);
        FunctionTypeKind.SuspendFunction suspendFunction = FunctionTypeKind.SuspendFunction.INSTANCE;
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(new ClassId(suspendFunction.getPackageFqName(), suspendFunction.numberedClassName(listDropLast.size()))), (ConeTypeProjection[]) CollectionsKt.plus(listDropLast, coneTypeProjection2).toArray(new ConeTypeProjection[0]), isNullable, attributes);
    }

    private static final boolean createSuspendFunctionTypeForBasicCase$isContinuation(ConeClassLikeType coneClassLikeType) {
        return coneClassLikeType.getTypeArguments().length == 1 && Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getContinuation());
    }

    private final ConeRigidType rigidType(ProtoBuf.Type proto, ConeAttributes attributes) {
        ConeClassLikeType coneClassLikeTypeCreateSuspendFunctionType;
        ConeRigidType coneRigidTypeRigidType;
        ConeDefinitelyNotNullType coneDefinitelyNotNullTypeCreate;
        ConeClassifierLookupTag coneClassifierLookupTagTypeSymbol = typeSymbol(proto);
        if (coneClassifierLookupTagTypeSymbol == null) {
            return null;
        }
        if (coneClassifierLookupTagTypeSymbol instanceof ConeTypeParameterLookupTag) {
            ConeTypeParameterTypeImpl coneTypeParameterTypeImpl = new ConeTypeParameterTypeImpl((ConeTypeParameterLookupTag) coneClassifierLookupTagTypeSymbol, proto.getNullable(), attributes);
            return (!Flags.DEFINITELY_NOT_NULL_TYPE.get(proto.getFlags()).booleanValue() || (coneDefinitelyNotNullTypeCreate = TypeUtilsKt.create(ConeDefinitelyNotNullType.INSTANCE, coneTypeParameterTypeImpl, TypeComponentsKt.getTypeContext(this.moduleData.getSession()), true)) == null) ? coneTypeParameterTypeImpl : coneDefinitelyNotNullTypeCreate;
        }
        if (!(coneClassifierLookupTagTypeSymbol instanceof ConeClassLikeLookupTag)) {
            return null;
        }
        List<ProtoBuf.Type.Argument> listRigidType$collectAllArguments = rigidType$collectAllArguments(proto, this);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listRigidType$collectAllArguments, 10));
        Iterator<T> it = listRigidType$collectAllArguments.iterator();
        while (it.hasNext()) {
            arrayList.add(typeArgument((ProtoBuf.Type.Argument) it.next()));
        }
        ConeTypeProjection[] coneTypeProjectionArr = (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]);
        ConeClassLikeLookupTag coneClassLikeLookupTag = (ConeClassLikeLookupTag) coneClassifierLookupTagTypeSymbol;
        FunctionTypeKind functionTypeKindExtractSingleExtensionKindForDeserializedConeType = FirFunctionTypeKindServiceKt.getFunctionTypeService(this.moduleData.getSession()).extractSingleExtensionKindForDeserializedConeType(coneClassLikeLookupTag.getClassId(), CustomAnnotationTypeAttributeKt.getCustomAnnotations(attributes));
        if (functionTypeKindExtractSingleExtensionKindForDeserializedConeType != null) {
            if (coneTypeProjectionArr.length == 0) {
                return new ConeErrorType(new ConeSimpleDiagnostic("Illegal number of arguments for extension functional type " + functionTypeKindExtractSingleExtensionKindForDeserializedConeType, null, 2, null), false, null, coneTypeProjectionArr, attributes, null, null, 102, null);
            }
            coneClassLikeTypeCreateSuspendFunctionType = new ConeClassLikeTypeImpl(new ConeClassLikeLookupTagImpl(functionTypeKindExtractSingleExtensionKindForDeserializedConeType.numberedClassId(coneTypeProjectionArr.length - 1)), coneTypeProjectionArr, proto.getNullable(), attributes);
        } else {
            coneClassLikeTypeCreateSuspendFunctionType = Flags.SUSPEND_TYPE.get(proto.getFlags()).booleanValue() ? createSuspendFunctionType(coneClassLikeLookupTag, coneTypeProjectionArr, proto.getNullable(), attributes) : new ConeClassLikeTypeImpl(coneClassLikeLookupTag, coneTypeProjectionArr, proto.getNullable(), attributes);
        }
        ProtoBuf.Type typeAbbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(proto, this.typeTable);
        return (typeAbbreviatedType == null || (coneRigidTypeRigidType = rigidType(typeAbbreviatedType, attributes)) == null) ? coneClassLikeTypeCreateSuspendFunctionType : (ConeRigidType) TypeUtilsKt.withAttributes(coneClassLikeTypeCreateSuspendFunctionType, coneClassLikeTypeCreateSuspendFunctionType.getAttributes().add(new AbbreviatedTypeAttribute(coneRigidTypeRigidType)));
    }

    private static final List<ProtoBuf.Type.Argument> rigidType$collectAllArguments(ProtoBuf.Type type, FirTypeDeserializer firTypeDeserializer) {
        List argumentList = type.getArgumentList();
        argumentList.getClass();
        List list = argumentList;
        ProtoBuf.Type typeOuterType = ProtoTypeTableUtilKt.outerType(type, firTypeDeserializer.typeTable);
        List<ProtoBuf.Type.Argument> listRigidType$collectAllArguments = typeOuterType != null ? rigidType$collectAllArguments(typeOuterType, firTypeDeserializer) : null;
        if (listRigidType$collectAllArguments == null) {
            listRigidType$collectAllArguments = CollectionsKt.emptyList();
        }
        return CollectionsKt.plus(list, listRigidType$collectAllArguments);
    }

    private final ConeKotlinType type(ProtoBuf.Type proto, ConeAttributes attributes) {
        if (!proto.hasFlexibleTypeCapabilitiesId()) {
            ConeRigidType coneRigidTypeRigidType = rigidType(proto, attributes);
            if (coneRigidTypeRigidType != null) {
                return coneRigidTypeRigidType;
            }
            return new ConeErrorType(new ConeSimpleDiagnostic("?!id:0", DiagnosticKind.DeserializationError), false, null, null, null, null, null, 126, null);
        }
        ConeRigidType coneRigidTypeRigidType2 = rigidType(proto, attributes);
        ProtoBuf.Type typeFlexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(proto, this.typeTable);
        typeFlexibleUpperBound.getClass();
        ConeRigidType coneRigidTypeRigidType3 = rigidType(typeFlexibleUpperBound, attributes);
        ClassId classId = coneRigidTypeRigidType2 != null ? ConeTypeUtilsKt.getClassId(coneRigidTypeRigidType2) : null;
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getNothing())) {
            if (Intrinsics.areEqual(coneRigidTypeRigidType3 != null ? ConeTypeUtilsKt.getClassId(coneRigidTypeRigidType3) : null, standardClassIds.getAny())) {
                return this.flexibleTypeFactory.mo620createDynamicType(proto, coneRigidTypeRigidType2, coneRigidTypeRigidType3);
            }
        }
        FlexibleTypeFactory flexibleTypeFactory = this.flexibleTypeFactory;
        coneRigidTypeRigidType2.getClass();
        coneRigidTypeRigidType3.getClass();
        return flexibleTypeFactory.createFlexibleType(proto, coneRigidTypeRigidType2, coneRigidTypeRigidType3);
    }

    private final ConeTypeProjection typeArgument(ProtoBuf.Type.Argument typeArgumentProto) {
        if (typeArgumentProto.getProjection() == ProtoBuf.Type.Argument.Projection.STAR) {
            return ConeStarProjection.INSTANCE;
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Type.Argument.Projection projection = typeArgumentProto.getProjection();
        projection.getClass();
        Variance variance = protoEnumFlags.variance(projection);
        ProtoBuf.Type type = ProtoTypeTableUtilKt.type(typeArgumentProto, this.typeTable);
        if (type != null) {
            return ConeTypeUtilsKt.toTypeProjection(type(type), variance);
        }
        return new ConeErrorType(new ConeSimpleDiagnostic("No type recorded", DiagnosticKind.DeserializationError), false, null, null, null, null, null, 126, null);
    }

    private final ConeTypeParameterLookupTag typeParameterSymbol(int typeParameterId) {
        ConeTypeParameterLookupTag lookupTag;
        FirTypeParameterSymbol firTypeParameterSymbol = this.typeParameterDescriptors.get(Integer.valueOf(typeParameterId));
        if (firTypeParameterSymbol != null && (lookupTag = firTypeParameterSymbol.getLookupTag()) != null) {
            return lookupTag;
        }
        FirTypeDeserializer firTypeDeserializer = this.parent;
        if (firTypeDeserializer != null) {
            return firTypeDeserializer.typeParameterSymbol(typeParameterId);
        }
        return null;
    }

    private final ConeClassifierLookupTag typeSymbol(ProtoBuf.Type proto) {
        if (proto.hasClassName()) {
            return computeClassifier(proto.getClassName());
        }
        if (proto.hasTypeAliasName()) {
            return computeClassifier(proto.getTypeAliasName());
        }
        if (proto.hasTypeParameter()) {
            return typeParameterSymbol(proto.getTypeParameter());
        }
        if (proto.hasTypeParameterName()) {
            FirTypeParameterSymbol firTypeParameterSymbol = this.typeParameterNames.get(this.nameResolver.getString(proto.getTypeParameterName()));
            if (firTypeParameterSymbol != null) {
                return firTypeParameterSymbol.getLookupTag();
            }
        }
        return null;
    }

    public final FirTypeDeserializer forChildContext(List<ProtoBuf.TypeParameter> typeParameterProtos, FirBasedSymbol<?> containingDeclarationSymbol, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer annotationDeserializer) {
        typeParameterProtos.getClass();
        containingDeclarationSymbol.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        annotationDeserializer.getClass();
        return new FirTypeDeserializer(this.moduleData, nameResolver, typeTable, annotationDeserializer, this.flexibleTypeFactory, typeParameterProtos, this, containingDeclarationSymbol);
    }

    public final List<FirTypeParameterSymbol> getOwnTypeParameters() {
        return CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final List<FirTypeParameterSymbol> typeParameters(FirClassLikeSymbol<?> firClassLikeSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<FirTypeParameterRef> typeParameters;
        firClassLikeSymbol.getClass();
        E fir = firClassLikeSymbol.getFir();
        ArrayList arrayList = null;
        FirTypeParameterRefsOwner firTypeParameterRefsOwner = fir instanceof FirTypeParameterRefsOwner ? (FirTypeParameterRefsOwner) fir : null;
        if (firTypeParameterRefsOwner != null && (typeParameters = firTypeParameterRefsOwner.getTypeParameters()) != null) {
            List<FirTypeParameterRef> list = typeParameters;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirTypeParameterRef) it.next()).getSymbol());
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    public final FirResolvedTypeRef typeRef(ProtoBuf.Type proto) {
        proto.getClass();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), this.annotationDeserializer.loadTypeAnnotations(proto, this.nameResolver));
        firResolvedTypeRefBuilder.setConeType(type(proto, CopyUtilsKt.computeTypeAttributes$default(firResolvedTypeRefBuilder.getAnnotations(), this.moduleData.getSession(), null, false, false, 6, null)));
        return firResolvedTypeRefBuilder.build();
    }

    public final ConeKotlinType type(ProtoBuf.Type proto) {
        proto.getClass();
        return type(proto, attributesFromAnnotations(proto));
    }

    public final ConeRigidType rigidType(ProtoBuf.Type proto) {
        proto.getClass();
        ConeRigidType coneRigidTypeRigidType = rigidType(proto, attributesFromAnnotations(proto));
        if (coneRigidTypeRigidType != null) {
            return coneRigidTypeRigidType;
        }
        return new ConeErrorType(new ConeSimpleDiagnostic("?!id:0", DiagnosticKind.DeserializationError), false, null, null, null, null, null, 126, null);
    }
}
