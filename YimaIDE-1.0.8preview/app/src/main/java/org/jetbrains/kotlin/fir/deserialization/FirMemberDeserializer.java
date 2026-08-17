package org.jetbrains.kotlin.fir.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVersionRequirementsTableKeyKt;
import org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructedClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyAccessorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeAliasBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusWithLazyEffectiveVisibility;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitUnitTypeRef;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000þ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014JF\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0002JR\u0010\"\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00172\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\f\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0002J*\u0010%\u001a\u00020&2\u0006\u0010\r\u001a\u00020\u00172\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0019J`\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\n\u0010/\u001a\u0006\u0012\u0002\b\u0003002\u0006\u0010\r\u001a\u0002012\u0006\u00102\u001a\u0002032\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u0002070*H\u0002J\"\u00108\u001a\u0002072\u0006\u0010\r\u001a\u00020.2\u0006\u00109\u001a\u0002052\n\u0010:\u001a\u0006\u0012\u0002\b\u000300J/\u0010;\u001a\b\u0012\u0004\u0012\u0002070-2\u0006\u0010#\u001a\u00020$2\u0006\u00109\u001a\u0002052\n\u0010:\u001a\u0006\u0012\u0002\b\u000300H\u0000¢\u0006\u0002\b<J4\u0010=\u001a\u00020>2\u0006\u0010\r\u001a\u00020?2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\b\b\u0002\u00104\u001a\u000205J\u001e\u0010@\u001a\u00020A2\u0006\u0010\r\u001a\u00020B2\u0006\u0010#\u001a\u00020$2\u0006\u0010C\u001a\u00020DJ(\u0010E\u001a\u0004\u0018\u00010F*\u00020+2\u0006\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010D2\u0006\u0010J\u001a\u00020KH\u0002Jf\u0010L\u001a\u00020(2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020+0-2\n\u0010:\u001a\u0006\u0012\u0002\b\u0003002\b\u0010I\u001a\u0004\u0018\u00010D2\u0006\u0010N\u001a\u0002012\u0006\u00102\u001a\u0002032\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010O\u001a\u00020P2\b\b\u0002\u0010Q\u001a\u00020K2\f\u00106\u001a\b\u0012\u0004\u0012\u0002070*H\u0002J\u0014\u0010R\u001a\u00020S*\u00020.2\u0006\u0010T\u001a\u00020\u0003H\u0002J \u0010U\u001a\b\u0012\u0004\u0012\u00020W0V*\u00020X2\f\u0010Y\u001a\b\u0012\u0002\b\u0003\u0018\u00010ZH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", Argument.Delimiters.none, "c", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;)V", "contractDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirContractDeserializer;", "loadOldFlags", Argument.Delimiters.none, "oldFlags", "loadTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "preComputedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "loadPropertyGetter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "defaultAccessorFlags", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "local", "propertyModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "loadPropertySetter", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "loadProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "loadContextParametersTo", Argument.Delimiters.none, "contextParameterList", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "legacyContextReceiverTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "callableKind", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer$CallableKind;", "deserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "destination", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "loadLegacyContextReceiver", "origin", "containingDeclarationSymbol", "createContextParametersForClass", "createContextParametersForClass$org_jetbrains_kotlin_fir_deserialization", "loadFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "loadConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "classBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "loadDefaultValue", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "parameterName", "Lorg/jetbrains/kotlin/name/Name;", "classBuilderForAnnotationConstructor", "forcefullyCreateDefaultValue", Argument.Delimiters.none, "addValueParametersTo", "valueParameters", "callableProto", "kind", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameterKind;", "addDefaultValue", "toTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "context", "toLazyEffectiveVisibility", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMemberDeserializer {
    private final FirDeserializationContext c;
    private final FirContractDeserializer contractDeserializer;

    public FirMemberDeserializer(FirDeserializationContext firDeserializationContext) {
        firDeserializationContext.getClass();
        this.c = firDeserializationContext;
        this.contractDeserializer = new FirContractDeserializer(firDeserializationContext);
    }

    private final void addValueParametersTo(List<ProtoBuf.ValueParameter> valueParameters, FirBasedSymbol<?> containingDeclarationSymbol, FirRegularClassBuilder classBuilderForAnnotationConstructor, MessageLite callableProto, AnnotationDeserializer.CallableKind callableKind, ProtoBuf.Class classProto, FirValueParameterKind kind, boolean addDefaultValue, List<FirValueParameter> destination) {
        List<FirValueParameter> list = destination;
        int i = 0;
        for (Object obj : valueParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ProtoBuf.ValueParameter valueParameter = (ProtoBuf.ValueParameter) obj;
            int flags = valueParameter.hasFlags() ? valueParameter.getFlags() : 0;
            Name name = (kind != FirValueParameterKind.ContextParameter || valueParameter.hasName()) ? NameResolverUtilKt.getName(this.c.getNameResolver(), valueParameter.getName()) : SpecialNames.UNDERSCORE_FOR_UNUSED_VAR;
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setModuleData(this.c.getModuleData());
            firValueParameterBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
            firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
            firValueParameterBuilder.setReturnTypeRef(toTypeRef(ProtoTypeTableUtilKt.type(valueParameter, this.c.getTypeTable()), this.c));
            firValueParameterBuilder.setName(name);
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            firValueParameterBuilder.setDefaultValue(loadDefaultValue(valueParameter, name, classBuilderForAnnotationConstructor, addDefaultValue));
            Boolean bool = Flags.IS_CROSSINLINE.get(flags);
            bool.getClass();
            firValueParameterBuilder.setCrossinline(bool.booleanValue());
            Boolean bool2 = Flags.IS_NOINLINE.get(flags);
            bool2.getClass();
            firValueParameterBuilder.setNoinline(bool2.booleanValue());
            firValueParameterBuilder.setVararg(ProtoTypeTableUtilKt.varargElementType(valueParameter, this.c.getTypeTable()) != null);
            CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), this.c.getAnnotationDeserializer().loadValueParameterAnnotations(this.c.getContainerSource(), callableProto, valueParameter, classProto, this.c.getNameResolver(), this.c.getTypeTable(), callableKind, i));
            firValueParameterBuilder.setValueParameterKind(kind);
            list.add(firValueParameterBuilder.mo288build());
            i = i2;
        }
    }

    public static /* synthetic */ void addValueParametersTo$default(FirMemberDeserializer firMemberDeserializer, List list, FirBasedSymbol firBasedSymbol, FirRegularClassBuilder firRegularClassBuilder, MessageLite messageLite, AnnotationDeserializer.CallableKind callableKind, ProtoBuf.Class r17, FirValueParameterKind firValueParameterKind, boolean z, List list2, int i, Object obj) {
        firMemberDeserializer.addValueParametersTo(list, firBasedSymbol, firRegularClassBuilder, messageLite, callableKind, r17, firValueParameterKind, (i & 128) != 0 ? false : z, list2);
    }

    private final void loadContextParametersTo(List<ProtoBuf.ValueParameter> contextParameterList, List<ProtoBuf.Type> legacyContextReceiverTypes, FirBasedSymbol<?> symbol, MessageLite proto, AnnotationDeserializer.CallableKind callableKind, ProtoBuf.Class classProto, FirDeclarationOrigin deserializationOrigin, List<FirValueParameter> destination) {
        if (!contextParameterList.isEmpty()) {
            addValueParametersTo$default(this, contextParameterList, symbol, null, proto, callableKind, classProto, FirValueParameterKind.ContextParameter, false, destination, 128, null);
            return;
        }
        List<FirValueParameter> list = destination;
        Iterator<T> it = legacyContextReceiverTypes.iterator();
        while (it.hasNext()) {
            list.add(loadLegacyContextReceiver((ProtoBuf.Type) it.next(), deserializationOrigin, symbol));
        }
    }

    private final FirExpression loadDefaultValue(ProtoBuf.ValueParameter valueParameter, Name name, FirRegularClassBuilder firRegularClassBuilder, boolean z) {
        FirExpression initializer;
        int flags = valueParameter.hasFlags() ? valueParameter.getFlags() : 0;
        Object obj = null;
        if (!z && !Flags.DECLARES_DEFAULT_VALUE.get(flags).booleanValue()) {
            return null;
        }
        if (valueParameter.hasAnnotationParameterDefaultValue()) {
            ProtoBuf.Annotation.Argument.Value annotationParameterDefaultValue = valueParameter.getAnnotationParameterDefaultValue();
            annotationParameterDefaultValue.getClass();
            return AnnotationDeserializationUtilKt.toFirExpression(annotationParameterDefaultValue, this.c.getSession(), this.c.getNameResolver());
        }
        if ((firRegularClassBuilder != null ? firRegularClassBuilder.getClassKind() : null) != ClassKind.ANNOTATION_CLASS) {
            return new FirExpressionStubBuilder().mo288build();
        }
        List<FirDeclaration> declarations = firRegularClassBuilder.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : declarations) {
            if (obj2 instanceof FirProperty) {
                arrayList.add(obj2);
            }
        }
        for (Object obj3 : arrayList) {
            if (Intrinsics.areEqual(((FirProperty) obj3).getName(), name)) {
                obj = obj3;
                break;
            }
        }
        FirProperty firProperty = (FirProperty) obj;
        return (firProperty == null || (initializer = firProperty.getInitializer()) == null) ? new FirExpressionStubBuilder().mo288build() : initializer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirNamedFunction loadFunction$default(FirMemberDeserializer firMemberDeserializer, ProtoBuf.Function function, ProtoBuf.Class r3, FirClassSymbol firClassSymbol, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            r3 = null;
        }
        if ((i & 4) != 0) {
            firClassSymbol = null;
        }
        if ((i & 8) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Library.INSTANCE;
        }
        return firMemberDeserializer.loadFunction(function, r3, firClassSymbol, firDeclarationOrigin);
    }

    private final int loadOldFlags(int oldFlags) {
        return (oldFlags & 63) + ((oldFlags >> 8) << 6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirProperty loadProperty$default(FirMemberDeserializer firMemberDeserializer, ProtoBuf.Property property, ProtoBuf.Class r3, FirClassSymbol firClassSymbol, int i, Object obj) {
        if ((i & 2) != 0) {
            r3 = null;
        }
        if ((i & 4) != 0) {
            firClassSymbol = null;
        }
        return firMemberDeserializer.loadProperty(property, r3, firClassSymbol);
    }

    private final FirPropertyAccessor loadPropertyGetter(ProtoBuf.Property proto, FirClassSymbol<?> classSymbol, int defaultAccessorFlags, FirTypeRef returnTypeRef, FirPropertySymbol propertySymbol, FirDeserializationContext local, Modality propertyModality) {
        ProtoBuf.Property property;
        FirPropertyAccessor firDefaultPropertyGetter;
        int getterFlags = proto.hasGetterFlags() ? proto.getGetterFlags() : defaultAccessorFlags;
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Visibility visibility = protoEnumFlags.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(getterFlags));
        Modality modality = protoEnumFlags.modality((ProtoBuf.Modality) Flags.MODALITY.get(getterFlags));
        Lazy<EffectiveVisibility> lazyEffectiveVisibility = toLazyEffectiveVisibility(visibility, classSymbol);
        Boolean bool = Flags.IS_STATIC_PROPERTY.get(proto.getFlags());
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        if (Flags.IS_NOT_DEFAULT.get(getterFlags).booleanValue()) {
            FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
            firPropertyAccessorBuilder.setModuleData(this.c.getModuleData());
            firPropertyAccessorBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
            firPropertyAccessorBuilder.setReturnTypeRef(returnTypeRef);
            firPropertyAccessorBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            firPropertyAccessorBuilder.setGetter(true);
            FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, modality, lazyEffectiveVisibility);
            Boolean bool2 = Flags.IS_INLINE_ACCESSOR.get(getterFlags);
            bool2.getClass();
            firResolvedDeclarationStatusWithLazyEffectiveVisibility.setInline(bool2.booleanValue());
            Boolean bool3 = Flags.IS_EXTERNAL_ACCESSOR.get(getterFlags);
            bool3.getClass();
            firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExternal(bool3.booleanValue());
            firResolvedDeclarationStatusWithLazyEffectiveVisibility.setStatic(zBooleanValue);
            firPropertyAccessorBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility);
            firPropertyAccessorBuilder.setSymbol(new FirPropertyAccessorSymbol());
            firPropertyAccessorBuilder.setDispatchReceiverType(zBooleanValue ? null : this.c.getDispatchReceiver());
            firPropertyAccessorBuilder.setPropertySymbol(propertySymbol);
            firDefaultPropertyGetter = firPropertyAccessorBuilder.mo288build();
            property = proto;
            FirVersionRequirementsTableKeyKt.setVersionRequirements(firDefaultPropertyGetter, FirDeserializationUtilsKt.create(VersionRequirement.Companion, property, this.c));
        } else {
            property = proto;
            FirModuleData moduleData = this.c.getModuleData();
            FirDeclarationOrigin.Library library = FirDeclarationOrigin.Library.INSTANCE;
            FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility2 = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, propertyModality, lazyEffectiveVisibility);
            firResolvedDeclarationStatusWithLazyEffectiveVisibility2.setStatic(zBooleanValue);
            Unit unit = Unit.INSTANCE;
            firDefaultPropertyGetter = new FirDefaultPropertyGetter(null, moduleData, library, returnTypeRef, propertySymbol, firResolvedDeclarationStatusWithLazyEffectiveVisibility2, null, FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES(), null, 320, null);
        }
        firDefaultPropertyGetter.replaceAnnotations(this.c.getAnnotationDeserializer().loadPropertyGetterAnnotations(this.c.getContainerSource(), property, local.getNameResolver(), local.getTypeTable(), getterFlags));
        ConeClassLikeType dispatchReceiver = this.c.getDispatchReceiver();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firDefaultPropertyGetter, dispatchReceiver != null ? dispatchReceiver.getLookupTag() : null);
        return firDefaultPropertyGetter;
    }

    private final FirPropertyAccessor loadPropertySetter(ProtoBuf.Property proto, ProtoBuf.Class classProto, FirClassSymbol<?> classSymbol, int defaultAccessorFlags, FirTypeRef returnTypeRef, FirPropertySymbol propertySymbol, FirDeserializationContext local, Modality propertyModality) {
        ProtoBuf.Property property;
        FirPropertyAccessor firDefaultPropertySetter;
        int setterFlags = proto.hasSetterFlags() ? proto.getSetterFlags() : defaultAccessorFlags;
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Visibility visibility = protoEnumFlags.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(setterFlags));
        Modality modality = protoEnumFlags.modality((ProtoBuf.Modality) Flags.MODALITY.get(setterFlags));
        Lazy<EffectiveVisibility> lazyEffectiveVisibility = toLazyEffectiveVisibility(visibility, classSymbol);
        Boolean bool = Flags.IS_STATIC_PROPERTY.get(proto.getFlags());
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        if (Flags.IS_NOT_DEFAULT.get(setterFlags).booleanValue()) {
            FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
            firPropertyAccessorBuilder.setModuleData(this.c.getModuleData());
            firPropertyAccessorBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
            firPropertyAccessorBuilder.setReturnTypeRef(new FirImplicitUnitTypeRef(firPropertyAccessorBuilder.getSource()));
            firPropertyAccessorBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            firPropertyAccessorBuilder.setGetter(false);
            FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, modality, lazyEffectiveVisibility);
            Boolean bool2 = Flags.IS_INLINE_ACCESSOR.get(setterFlags);
            bool2.getClass();
            firResolvedDeclarationStatusWithLazyEffectiveVisibility.setInline(bool2.booleanValue());
            Boolean bool3 = Flags.IS_EXTERNAL_ACCESSOR.get(setterFlags);
            bool3.getClass();
            firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExternal(bool3.booleanValue());
            firResolvedDeclarationStatusWithLazyEffectiveVisibility.setStatic(zBooleanValue);
            firPropertyAccessorBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility);
            firPropertyAccessorBuilder.setSymbol(new FirPropertyAccessorSymbol());
            firPropertyAccessorBuilder.setDispatchReceiverType(zBooleanValue ? null : this.c.getDispatchReceiver());
            addValueParametersTo$default(local.getMemberDeserializer(), CollectionsKt.listOf(proto.getSetterValueParameter()), firPropertyAccessorBuilder.getSymbol(), null, proto, AnnotationDeserializer.CallableKind.PROPERTY_SETTER, classProto, FirValueParameterKind.Regular, false, firPropertyAccessorBuilder.getValueParameters(), 128, null);
            firPropertyAccessorBuilder.setPropertySymbol(propertySymbol);
            firDefaultPropertySetter = firPropertyAccessorBuilder.mo288build();
            property = proto;
            FirVersionRequirementsTableKeyKt.setVersionRequirements(firDefaultPropertySetter, FirDeserializationUtilsKt.create(VersionRequirement.Companion, property, this.c));
        } else {
            property = proto;
            FirModuleData moduleData = this.c.getModuleData();
            FirDeclarationOrigin.Library library = FirDeclarationOrigin.Library.INSTANCE;
            FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility2 = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, propertyModality, lazyEffectiveVisibility);
            firResolvedDeclarationStatusWithLazyEffectiveVisibility2.setStatic(zBooleanValue);
            Unit unit = Unit.INSTANCE;
            firDefaultPropertySetter = new FirDefaultPropertySetter(null, moduleData, library, returnTypeRef, propertySymbol, firResolvedDeclarationStatusWithLazyEffectiveVisibility2, null, null, null, FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES(), null, 1472, null);
        }
        firDefaultPropertySetter.replaceAnnotations(this.c.getAnnotationDeserializer().loadPropertySetterAnnotations(this.c.getContainerSource(), property, local.getNameResolver(), local.getTypeTable(), setterFlags));
        ConeClassLikeType dispatchReceiver = this.c.getDispatchReceiver();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firDefaultPropertySetter, dispatchReceiver != null ? dispatchReceiver.getLookupTag() : null);
        return firDefaultPropertySetter;
    }

    public static /* synthetic */ FirTypeAlias loadTypeAlias$default(FirMemberDeserializer firMemberDeserializer, ProtoBuf.TypeAlias typeAlias, ClassId classId, FirScopeProvider firScopeProvider, FirTypeAliasSymbol firTypeAliasSymbol, int i, Object obj) {
        if ((i & 8) != 0) {
            firTypeAliasSymbol = null;
        }
        return firMemberDeserializer.loadTypeAlias(typeAlias, classId, firScopeProvider, firTypeAliasSymbol);
    }

    private final Lazy<EffectiveVisibility> toLazyEffectiveVisibility(Visibility visibility, FirClassLikeSymbol<?> firClassLikeSymbol) {
        return FirMemberDeserializerKt.toLazyEffectiveVisibility(visibility, firClassLikeSymbol, this.c.getSession(), false);
    }

    private final FirResolvedTypeRef toTypeRef(ProtoBuf.Type type, FirDeserializationContext firDeserializationContext) {
        return firDeserializationContext.getTypeDeserializer().typeRef(type);
    }

    public final List<FirValueParameter> createContextParametersForClass$org_jetbrains_kotlin_fir_deserialization(ProtoBuf.Class classProto, FirDeclarationOrigin origin, FirBasedSymbol<?> containingDeclarationSymbol) {
        classProto.getClass();
        origin.getClass();
        containingDeclarationSymbol.getClass();
        List listContextReceiverTypes = ProtoTypeTableUtilKt.contextReceiverTypes(classProto, this.c.getTypeTable());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listContextReceiverTypes, 10));
        Iterator it = listContextReceiverTypes.iterator();
        while (it.hasNext()) {
            arrayList.add(loadLegacyContextReceiver((ProtoBuf.Type) it.next(), origin, containingDeclarationSymbol));
        }
        return arrayList;
    }

    public final FirConstructor loadConstructor(ProtoBuf.Constructor proto, ProtoBuf.Class classProto, FirRegularClassBuilder classBuilder) {
        ConeSimpleKotlinType coneSimpleKotlinTypeDefaultType;
        proto.getClass();
        classProto.getClass();
        classBuilder.getClass();
        int flags = proto.getFlags();
        FqName relativeClassName = this.c.getRelativeClassName();
        relativeClassName.getClass();
        FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(new CallableId(this.c.getPackageFqName(), relativeClassName, relativeClassName.shortName()));
        FirDeserializationContext firDeserializationContextChildContext$default = FirDeserializationContext.childContext$default(this.c, CollectionsKt.emptyList(), firConstructorSymbol, null, null, null, null, null, null, null, false, 1020, null);
        boolean zBooleanValue = Flags.IS_SECONDARY.get(flags).booleanValue();
        List<FirTypeParameterRef> typeParameters = classBuilder.getTypeParameters();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        ConeClassLikeLookupTag lookupTag = classBuilder.getSymbol().getLookupTag();
        List<FirTypeParameterRef> list = typeParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConeTypeParameterTypeImpl(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag(), false, null, 4, null));
        }
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeParameterTypeImpl[0]), false, null, 8, null));
        FirTypeRef firTypeRefBuild = firResolvedTypeRefBuilder.build();
        FirAbstractConstructorBuilder firPrimaryConstructorBuilder = !zBooleanValue ? new FirPrimaryConstructorBuilder() : new FirConstructorBuilder();
        firPrimaryConstructorBuilder.setModuleData(this.c.getModuleData());
        firPrimaryConstructorBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
        firPrimaryConstructorBuilder.setReturnTypeRef(firTypeRefBuild);
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Visibility visibility = protoEnumFlags.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(flags));
        boolean zIsInner = classBuilder.getStatus().isInner();
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, Modality.FINAL, toLazyEffectiveVisibility(visibility, classBuilder.getSymbol()));
        Boolean bool = Flags.IS_EXPECT_CLASS.get(classProto.getFlags());
        bool.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExpect(bool.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setHasStableParameterNames(!Flags.IS_CONSTRUCTOR_WITH_NON_STABLE_PARAMETER_NAMES.get(flags).booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setActual(false);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setOverride(false);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setInner(zIsInner);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setReturnValueStatus(protoEnumFlags.returnValueStatus((ProtoBuf.ReturnValueStatus) Flags.RETURN_VALUE_STATUS_CTOR.get(flags)));
        firPrimaryConstructorBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility);
        firPrimaryConstructorBuilder.setLocal(false);
        firPrimaryConstructorBuilder.setSymbol(firConstructorSymbol);
        if (zIsInner) {
            FirDeserializationContext firDeserializationContext = this.c;
            coneSimpleKotlinTypeDefaultType = ScopeUtilsKt.defaultType(new ClassId(firDeserializationContext.getPackageFqName(), relativeClassName.parent(), false), firDeserializationContext.getOuterTypeParameters());
        } else {
            coneSimpleKotlinTypeDefaultType = null;
        }
        firPrimaryConstructorBuilder.setDispatchReceiverType(coneSimpleKotlinTypeDefaultType);
        firPrimaryConstructorBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeParameterRef> typeParameters2 = firPrimaryConstructorBuilder.getTypeParameters();
        ArrayList<FirTypeParameter> arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof FirTypeParameter) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (FirTypeParameter firTypeParameter : arrayList2) {
            FirConstructedClassTypeParameterRefBuilder firConstructedClassTypeParameterRefBuilder = new FirConstructedClassTypeParameterRefBuilder();
            firConstructedClassTypeParameterRefBuilder.setSymbol(firTypeParameter.getSymbol());
            arrayList3.add(firConstructedClassTypeParameterRefBuilder.build());
        }
        CollectionsKt.addAll(typeParameters2, arrayList3);
        FirMemberDeserializer memberDeserializer = firDeserializationContextChildContext$default.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
        valueParameterList.getClass();
        memberDeserializer.addValueParametersTo(valueParameterList, firConstructorSymbol, classBuilder, proto, AnnotationDeserializer.CallableKind.OTHERS, classProto, FirValueParameterKind.Regular, Intrinsics.areEqual(classBuilder.getSymbol().getClassId(), StandardClassIds.INSTANCE.getEnum()), firPrimaryConstructorBuilder.getValueParameters());
        CollectionsKt.addAll(firPrimaryConstructorBuilder.getAnnotations(), this.c.getAnnotationDeserializer().loadConstructorAnnotations(this.c.getContainerSource(), proto, firDeserializationContextChildContext$default.getNameResolver(), firDeserializationContextChildContext$default.getTypeTable()));
        firPrimaryConstructorBuilder.setContainerSource(this.c.getContainerSource());
        firPrimaryConstructorBuilder.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAnnotations$default(firPrimaryConstructorBuilder.getAnnotations(), this.c.getSession(), false, null, 4, null));
        List listContextReceiverTypes = ProtoTypeTableUtilKt.contextReceiverTypes(classProto, this.c.getTypeTable());
        List<FirValueParameter> contextParameters = firPrimaryConstructorBuilder.getContextParameters();
        Iterator it2 = listContextReceiverTypes.iterator();
        while (it2.hasNext()) {
            contextParameters.add(loadLegacyContextReceiver((ProtoBuf.Type) it2.next(), FirDeclarationOrigin.Library.INSTANCE, firConstructorSymbol));
        }
        FirKDocDeserializerKt.applyKDoc(firPrimaryConstructorBuilder, this.c.getKdocDeserializer().loadConstructorKDoc(proto));
        FirConstructor firConstructorBuild = firPrimaryConstructorBuilder.mo288build();
        ConeClassLikeType dispatchReceiver = this.c.getDispatchReceiver();
        dispatchReceiver.getClass();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorBuild, dispatchReceiver.getLookupTag());
        FirVersionRequirementsTableKeyKt.setVersionRequirements(firConstructorBuild, FirDeserializationUtilsKt.create(VersionRequirement.Companion, proto, this.c));
        FirDeserializationContext firDeserializationContext2 = this.c;
        List compilerPluginDataList = proto.getCompilerPluginDataList();
        List list2 = compilerPluginDataList.isEmpty() ? null : compilerPluginDataList;
        if (list2 != null) {
            List<ProtoBuf.CompilerPluginData> list3 = list2;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list3, 10)), 16));
            for (ProtoBuf.CompilerPluginData compilerPluginData : list3) {
                linkedHashMap.put(firDeserializationContext2.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
            }
            DeclarationAttributesKt.setCompilerPluginMetadata(firConstructorBuild, linkedHashMap);
        }
        PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(firConstructorBuild, this.c.getSession());
        return firConstructorBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunction loadFunction(ProtoBuf.Function proto, ProtoBuf.Class classProto, FirClassSymbol<?> classSymbol, FirDeclarationOrigin deserializationOrigin) {
        ProtoBuf.Function function;
        List<FirAnnotation> listEmptyList;
        FirReceiverParameter firReceiverParameterBuild;
        FirTypeRef typeRef;
        proto.getClass();
        deserializationOrigin.getClass();
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        if (ProtoTypeTableUtilKt.hasReceiver(proto)) {
            listEmptyList = this.c.getAnnotationDeserializer().loadExtensionReceiverParameterAnnotations(this.c.getContainerSource(), proto, this.c.getNameResolver(), this.c.getTypeTable(), AnnotationDeserializer.CallableKind.OTHERS);
            function = proto;
        } else {
            function = proto;
            listEmptyList = CollectionsKt.emptyList();
        }
        Name name = NameResolverUtilKt.getName(this.c.getNameResolver(), function.getName());
        FirNamedFunctionSymbol firNamedFunctionSymbol = new FirNamedFunctionSymbol(new CallableId(this.c.getPackageFqName(), this.c.getRelativeClassName(), name));
        FirDeserializationContext firDeserializationContext = this.c;
        List typeParameterList = function.getTypeParameterList();
        typeParameterList.getClass();
        FirDeserializationContext firDeserializationContextChildContext$default = FirDeserializationContext.childContext$default(firDeserializationContext, typeParameterList, firNamedFunctionSymbol, null, null, null, null, null, null, null, false, 1020, null);
        List<VersionRequirement> listCreate = FirDeserializationUtilsKt.create(VersionRequirement.Companion, function, this.c);
        Boolean bool = Flags.IS_STATIC_FUNCTION.get(flags);
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setModuleData(this.c.getModuleData());
        firNamedFunctionBuilder.setOrigin(deserializationOrigin);
        firNamedFunctionBuilder.setReturnTypeRef(toTypeRef(ProtoTypeTableUtilKt.returnType(function, firDeserializationContextChildContext$default.getTypeTable()), firDeserializationContextChildContext$default));
        ProtoBuf.Type typeReceiverType = ProtoTypeTableUtilKt.receiverType(function, firDeserializationContextChildContext$default.getTypeTable());
        if (typeReceiverType == null || (typeRef = toTypeRef(typeReceiverType, firDeserializationContextChildContext$default)) == null) {
            firReceiverParameterBuild = null;
        } else {
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setTypeRef(typeRef);
            CollectionsKt.addAll(firReceiverParameterBuilder.getAnnotations(), listEmptyList);
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setModuleData(this.c.getModuleData());
            firReceiverParameterBuilder.setOrigin(deserializationOrigin);
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionSymbol);
            firReceiverParameterBuild = firReceiverParameterBuilder.mo288build();
        }
        firNamedFunctionBuilder.setReceiverParameter(firReceiverParameterBuild);
        firNamedFunctionBuilder.setName(name);
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Visibility visibility = protoEnumFlags.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(flags));
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, protoEnumFlags.modality((ProtoBuf.Modality) Flags.MODALITY.get(flags)), toLazyEffectiveVisibility(visibility, classSymbol));
        Boolean bool2 = Flags.IS_EXPECT_FUNCTION.get(flags);
        bool2.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExpect(bool2.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setActual(false);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setOverride(false);
        Boolean bool3 = Flags.IS_OPERATOR.get(flags);
        bool3.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setOperator(bool3.booleanValue());
        Boolean bool4 = Flags.IS_INFIX.get(flags);
        bool4.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setInfix(bool4.booleanValue());
        Boolean bool5 = Flags.IS_INLINE.get(flags);
        bool5.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setInline(bool5.booleanValue());
        Boolean bool6 = Flags.IS_TAILREC.get(flags);
        bool6.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setTailRec(bool6.booleanValue());
        Boolean bool7 = Flags.IS_EXTERNAL_FUNCTION.get(flags);
        bool7.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExternal(bool7.booleanValue());
        Boolean bool8 = Flags.IS_SUSPEND.get(flags);
        bool8.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setSuspend(bool8.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setStatic(zBooleanValue);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setHasStableParameterNames(!Flags.IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES.get(flags).booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setReturnValueStatus(protoEnumFlags.returnValueStatus((ProtoBuf.ReturnValueStatus) Flags.RETURN_VALUE_STATUS_FUNCTION.get(flags)));
        firNamedFunctionBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility);
        firNamedFunctionBuilder.setLocal(false);
        firNamedFunctionBuilder.setSymbol(firNamedFunctionSymbol);
        firNamedFunctionBuilder.setDispatchReceiverType(zBooleanValue ? null : this.c.getDispatchReceiver());
        firNamedFunctionBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeParameter> typeParameters = firNamedFunctionBuilder.getTypeParameters();
        List<FirTypeParameterSymbol> ownTypeParameters = firDeserializationContextChildContext$default.getTypeDeserializer().getOwnTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(ownTypeParameters, 10));
        Iterator<T> it = ownTypeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add((FirTypeParameter) ((FirTypeParameterSymbol) it.next()).getFir());
        }
        CollectionsKt.addAll(typeParameters, arrayList);
        FirMemberDeserializer memberDeserializer = firDeserializationContextChildContext$default.getMemberDeserializer();
        List valueParameterList = function.getValueParameterList();
        valueParameterList.getClass();
        AnnotationDeserializer.CallableKind callableKind = AnnotationDeserializer.CallableKind.OTHERS;
        addValueParametersTo$default(memberDeserializer, valueParameterList, firNamedFunctionSymbol, null, function, callableKind, classProto, FirValueParameterKind.Regular, false, firNamedFunctionBuilder.getValueParameters(), 128, null);
        CollectionsKt.addAll(firNamedFunctionBuilder.getAnnotations(), this.c.getAnnotationDeserializer().loadFunctionAnnotations(this.c.getContainerSource(), function, firDeserializationContextChildContext$default.getNameResolver(), firDeserializationContextChildContext$default.getTypeTable()));
        firNamedFunctionBuilder.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAnnotations(firNamedFunctionBuilder.getAnnotations(), this.c.getSession(), false, listCreate));
        firNamedFunctionBuilder.setContainerSource(this.c.getContainerSource());
        FirMemberDeserializer memberDeserializer2 = firDeserializationContextChildContext$default.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> contextParameterList = function.getContextParameterList();
        contextParameterList.getClass();
        memberDeserializer2.loadContextParametersTo(contextParameterList, ProtoTypeTableUtilKt.contextReceiverTypes(function, this.c.getTypeTable()), firNamedFunctionSymbol, function, callableKind, classProto, deserializationOrigin, firNamedFunctionBuilder.getContextParameters());
        FirKDocDeserializerKt.applyKDoc(firNamedFunctionBuilder, this.c.getKdocDeserializer().loadFunctionKDoc(function));
        FirNamedFunction firNamedFunctionBuild = firNamedFunctionBuilder.mo288build();
        FirVersionRequirementsTableKeyKt.setVersionRequirements(firNamedFunctionBuild, listCreate);
        PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(firNamedFunctionBuild, this.c.getSession());
        FirDeserializationContext firDeserializationContext2 = this.c;
        List compilerPluginDataList = function.getCompilerPluginDataList();
        List list = compilerPluginDataList.isEmpty() ? null : compilerPluginDataList;
        if (list != null) {
            List<ProtoBuf.CompilerPluginData> list2 = list;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
            for (ProtoBuf.CompilerPluginData compilerPluginData : list2) {
                linkedHashMap.put(firDeserializationContext2.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
            }
            DeclarationAttributesKt.setCompilerPluginMetadata(firNamedFunctionBuild, linkedHashMap);
        }
        if (function.hasContract()) {
            FirContractDeserializer firContractDeserializer = function.getTypeParameterList().isEmpty() ? this.contractDeserializer : new FirContractDeserializer(firDeserializationContextChildContext$default);
            ProtoBuf.Contract contract = function.getContract();
            contract.getClass();
            FirContractDescription firContractDescriptionLoadContract = firContractDeserializer.loadContract(contract, firNamedFunctionBuild);
            if (firContractDescriptionLoadContract != null) {
                firNamedFunctionBuild.replaceContractDescription(firContractDescriptionLoadContract);
            }
        }
        return firNamedFunctionBuild;
    }

    public final FirValueParameter loadLegacyContextReceiver(ProtoBuf.Type proto, FirDeclarationOrigin origin, FirBasedSymbol<?> containingDeclarationSymbol) {
        proto.getClass();
        origin.getClass();
        containingDeclarationSymbol.getClass();
        FirTypeRef typeRef = toTypeRef(proto, this.c);
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setModuleData(this.c.getModuleData());
        firValueParameterBuilder.setOrigin(origin);
        firValueParameterBuilder.setName(SpecialNames.UNDERSCORE_FOR_UNUSED_VAR);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setReturnTypeRef(typeRef);
        firValueParameterBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
        firValueParameterBuilder.setValueParameterKind(FirValueParameterKind.LegacyContextReceiver);
        firValueParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        return firValueParameterBuilder.mo288build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirProperty loadProperty(ProtoBuf.Property proto, ProtoBuf.Class classProto, FirClassSymbol<?> classSymbol) {
        ProtoBuf.Property property;
        Flags.FlagField flagField;
        List<FirAnnotation> listEmptyList;
        FirReceiverParameter firReceiverParameterBuild;
        FirPropertyBuilder firPropertyBuilder;
        ProtoBuf.Property property2;
        int i;
        FirMemberDeserializer firMemberDeserializer;
        ProtoBuf.Property property3;
        FirRegularPropertySymbol firRegularPropertySymbol;
        FirExpression firExpressionLoadAnnotationPropertyDefaultValue;
        Boolean boolLoadHasBackingFieldFlag;
        FirDeserializationExtension deserializationExtension;
        FirTypeRef typeRef;
        proto.getClass();
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        Name name = NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName());
        FirRegularPropertySymbol firRegularPropertySymbol2 = new FirRegularPropertySymbol(new CallableId(this.c.getPackageFqName(), this.c.getRelativeClassName(), name));
        FirDeserializationContext firDeserializationContext = this.c;
        List typeParameterList = proto.getTypeParameterList();
        typeParameterList.getClass();
        FirDeserializationContext firDeserializationContextChildContext$default = FirDeserializationContext.childContext$default(firDeserializationContext, typeParameterList, firRegularPropertySymbol2, null, null, null, null, null, null, null, false, 1020, null);
        Boolean bool = Flags.HAS_ANNOTATIONS.get(flags);
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        Flags.FlagField flagField2 = Flags.VISIBILITY;
        ProtoBuf.Visibility visibility = (ProtoBuf.Visibility) flagField2.get(flags);
        Flags.FlagField flagField3 = Flags.MODALITY;
        int accessorFlags = Flags.getAccessorFlags(zBooleanValue, visibility, (ProtoBuf.Modality) flagField3.get(flags), false, false, false);
        FirTypeRef typeRef2 = toTypeRef(ProtoTypeTableUtilKt.returnType(proto, this.c.getTypeTable()), firDeserializationContextChildContext$default);
        Boolean bool2 = Flags.HAS_GETTER.get(flags);
        bool2.getClass();
        boolean zBooleanValue2 = bool2.booleanValue();
        if (zBooleanValue2 && ProtoTypeTableUtilKt.hasReceiver(proto)) {
            flagField = flagField3;
            property = proto;
            listEmptyList = this.c.getAnnotationDeserializer().loadExtensionReceiverParameterAnnotations(this.c.getContainerSource(), property, firDeserializationContextChildContext$default.getNameResolver(), firDeserializationContextChildContext$default.getTypeTable(), AnnotationDeserializer.CallableKind.PROPERTY_GETTER);
        } else {
            property = proto;
            flagField = flagField3;
            listEmptyList = CollectionsKt.emptyList();
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Modality modality = protoEnumFlags.modality((ProtoBuf.Modality) flagField.get(flags));
        Boolean bool3 = Flags.IS_VAR.get(flags);
        bool3.getClass();
        boolean zBooleanValue3 = bool3.booleanValue();
        List<VersionRequirement> listCreate = FirDeserializationUtilsKt.create(VersionRequirement.Companion, property, this.c);
        boolean z = classProto != null && Flags.CLASS_KIND.get(classProto.getFlags()) == ProtoBuf.Class.Kind.ANNOTATION_CLASS;
        FirPropertyBuilder firPropertyBuilder2 = new FirPropertyBuilder();
        firPropertyBuilder2.setModuleData(this.c.getModuleData());
        FirDeclarationOrigin.Library library = FirDeclarationOrigin.Library.INSTANCE;
        firPropertyBuilder2.setOrigin(library);
        firPropertyBuilder2.setReturnTypeRef(typeRef2);
        ProtoBuf.Type typeReceiverType = ProtoTypeTableUtilKt.receiverType(property, this.c.getTypeTable());
        if (typeReceiverType == null || (typeRef = toTypeRef(typeReceiverType, firDeserializationContextChildContext$default)) == null) {
            firReceiverParameterBuild = null;
        } else {
            List<FirAnnotation> list = listEmptyList;
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setTypeRef(typeRef);
            CollectionsKt.addAll(firReceiverParameterBuilder.getAnnotations(), list);
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setModuleData(this.c.getModuleData());
            firReceiverParameterBuilder.setOrigin(library);
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firRegularPropertySymbol2);
            firReceiverParameterBuild = firReceiverParameterBuilder.mo288build();
        }
        firPropertyBuilder2.setReceiverParameter(firReceiverParameterBuild);
        firPropertyBuilder2.setName(name);
        firPropertyBuilder2.setVar(zBooleanValue3);
        firPropertyBuilder2.setSymbol(firRegularPropertySymbol2);
        Boolean bool4 = Flags.IS_STATIC_PROPERTY.get(flags);
        bool4.getClass();
        boolean zBooleanValue4 = bool4.booleanValue();
        firPropertyBuilder2.setDispatchReceiverType(zBooleanValue4 ? null : this.c.getDispatchReceiver());
        Visibility visibility2 = protoEnumFlags.visibility((ProtoBuf.Visibility) flagField2.get(flags));
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility2, modality, toLazyEffectiveVisibility(visibility2, classSymbol));
        Boolean bool5 = Flags.IS_EXPECT_PROPERTY.get(flags);
        bool5.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExpect(bool5.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setActual(false);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setOverride(false);
        Boolean bool6 = Flags.IS_CONST.get(flags);
        bool6.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setConst(bool6.booleanValue());
        Boolean bool7 = Flags.IS_LATEINIT.get(flags);
        bool7.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setLateInit(bool7.booleanValue());
        Boolean bool8 = Flags.IS_EXTERNAL_PROPERTY.get(flags);
        bool8.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExternal(bool8.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setStatic(zBooleanValue4);
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setReturnValueStatus(protoEnumFlags.returnValueStatus((ProtoBuf.ReturnValueStatus) Flags.RETURN_VALUE_STATUS_PROPERTY.get(flags)));
        firPropertyBuilder2.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility);
        firPropertyBuilder2.setLocal(false);
        firPropertyBuilder2.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeParameter> typeParameters = firPropertyBuilder2.getTypeParameters();
        List<FirTypeParameterSymbol> ownTypeParameters = firDeserializationContextChildContext$default.getTypeDeserializer().getOwnTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(ownTypeParameters, 10));
        Iterator<T> it = ownTypeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add((FirTypeParameter) ((FirTypeParameterSymbol) it.next()).getFir());
        }
        CollectionsKt.addAll(typeParameters, arrayList);
        if (!z || (deserializationExtension = ClassDeserializationKt.getDeserializationExtension(this.c.getSession())) == null || deserializationExtension.isLoadingOfAnnotationsOnAnnotationPropertiesEnabled()) {
            firPropertyBuilder = firPropertyBuilder2;
            property2 = proto;
            CollectionsKt.addAll(firPropertyBuilder2.getAnnotations(), this.c.getAnnotationDeserializer().loadPropertyAnnotations(this.c.getContainerSource(), property2, classProto, firDeserializationContextChildContext$default.getNameResolver(), firDeserializationContextChildContext$default.getTypeTable()));
        } else {
            property2 = proto;
            firPropertyBuilder = firPropertyBuilder2;
        }
        ArrayList arrayList2 = new ArrayList();
        CollectionsKt.addAll(arrayList2, this.c.getAnnotationDeserializer().loadPropertyBackingFieldAnnotations(this.c.getContainerSource(), property2, firDeserializationContextChildContext$default.getNameResolver(), firDeserializationContextChildContext$default.getTypeTable()));
        CollectionsKt.addAll(arrayList2, this.c.getAnnotationDeserializer().loadPropertyDelegatedFieldAnnotations(this.c.getContainerSource(), property2, firDeserializationContextChildContext$default.getNameResolver(), firDeserializationContextChildContext$default.getTypeTable()));
        FirDeserializationContext firDeserializationContext2 = firDeserializationContextChildContext$default;
        FirModuleData moduleData = this.c.getModuleData();
        FirDeclarationOrigin.Library library2 = FirDeclarationOrigin.Library.INSTANCE;
        Modality modality2 = modality;
        FirPropertyBuilder firPropertyBuilder3 = firPropertyBuilder;
        firPropertyBuilder3.setBackingField(new FirDefaultPropertyBackingField(moduleData, library2, null, arrayList2, typeRef2, zBooleanValue3, firRegularPropertySymbol2, firPropertyBuilder.getStatus(), null, 256, null));
        if (zBooleanValue2) {
            ProtoBuf.Property property4 = property2;
            i = accessorFlags;
            FirPropertyAccessor firPropertyAccessorLoadPropertyGetter = loadPropertyGetter(property4, classSymbol, i, typeRef2, firRegularPropertySymbol2, firDeserializationContext2, modality2);
            modality2 = modality2;
            firPropertyBuilder3.setGetter(firPropertyAccessorLoadPropertyGetter);
        } else {
            i = accessorFlags;
        }
        if (Flags.HAS_SETTER.get(flags).booleanValue()) {
            property3 = proto;
            FirPropertyAccessor firPropertyAccessorLoadPropertySetter = loadPropertySetter(property3, classProto, classSymbol, i, typeRef2, firRegularPropertySymbol2, firDeserializationContext2, modality2);
            firMemberDeserializer = this;
            firRegularPropertySymbol = firRegularPropertySymbol2;
            firDeserializationContext2 = firDeserializationContext2;
            firPropertyBuilder3.setSetter(firPropertyAccessorLoadPropertySetter);
        } else {
            firMemberDeserializer = this;
            property3 = proto;
            firRegularPropertySymbol = firRegularPropertySymbol2;
        }
        firPropertyBuilder3.setContainerSource(firMemberDeserializer.c.getContainerSource());
        if (Flags.HAS_CONSTANT.get(property3.getFlags()).booleanValue()) {
            firExpressionLoadAnnotationPropertyDefaultValue = firMemberDeserializer.c.getConstDeserializer().loadConstant(property3, firRegularPropertySymbol.getCallableId(), firMemberDeserializer.c.getNameResolver(), ConeBuiltinTypeUtilsKt.isUnsignedTypeOrNullableUnsignedType(typeRef2.getConeType()));
        } else if (z) {
            ProtoBuf.Property property5 = property3;
            firExpressionLoadAnnotationPropertyDefaultValue = firMemberDeserializer.c.getAnnotationDeserializer().loadAnnotationPropertyDefaultValue(firMemberDeserializer.c.getContainerSource(), property5, typeRef2, firDeserializationContext2.getNameResolver(), firDeserializationContext2.getTypeTable());
            property3 = property5;
        } else {
            firExpressionLoadAnnotationPropertyDefaultValue = null;
        }
        firPropertyBuilder3.setInitializer(firExpressionLoadAnnotationPropertyDefaultValue);
        FirMemberDeserializer memberDeserializer = firDeserializationContext2.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> contextParameterList = property3.getContextParameterList();
        contextParameterList.getClass();
        ProtoBuf.Property property6 = property3;
        memberDeserializer.loadContextParametersTo(contextParameterList, ProtoTypeTableUtilKt.contextReceiverTypes(property3, firMemberDeserializer.c.getTypeTable()), firRegularPropertySymbol, property6, AnnotationDeserializer.CallableKind.PROPERTY_GETTER, classProto, library2, firPropertyBuilder3.getContextParameters());
        FirKDocDeserializerKt.applyKDoc(firPropertyBuilder3, firMemberDeserializer.c.getKdocDeserializer().loadPropertyKDoc(property6));
        FirProperty firPropertyBuild = firPropertyBuilder3.mo288build();
        FirExpression initializer = firPropertyBuild.getInitializer();
        if (initializer instanceof FirAnnotation) {
            FirAnnotation firAnnotation = (FirAnnotation) initializer;
            firAnnotation.replaceAnnotationTypeRef(TypeUtilsKt.withReplacedReturnType(firAnnotation.getAnnotationTypeRef(), typeRef2.getConeType()));
            Unit unit = Unit.INSTANCE;
        } else if (initializer != null) {
            initializer.replaceConeTypeOrNull(typeRef2.getConeType());
            Unit unit2 = Unit.INSTANCE;
        }
        FirVersionRequirementsTableKeyKt.setVersionRequirements(firPropertyBuild, listCreate);
        FirDeserializationExtension deserializationExtension2 = ClassDeserializationKt.getDeserializationExtension(firMemberDeserializer.c.getSession());
        if (deserializationExtension2 != null && (boolLoadHasBackingFieldFlag = deserializationExtension2.loadHasBackingFieldFlag(property6)) != null) {
            DeclarationAttributesKt.setHasBackingFieldAttr(firPropertyBuild, boolLoadHasBackingFieldFlag);
            Unit unit3 = Unit.INSTANCE;
        }
        if (Flags.IS_DELEGATED.get(property6.getFlags()).booleanValue()) {
            DeclarationAttributesKt.setDelegatedPropertyAttr(firPropertyBuild, Boolean.TRUE);
        }
        if (z) {
            DeclarationAttributesKt.setDeserializedPropertyFromAnnotation(firPropertyBuild, Boolean.TRUE);
        }
        firPropertyBuild.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProvider(firPropertyBuild, firMemberDeserializer.c.getSession()));
        FirDeserializationContext firDeserializationContext3 = firMemberDeserializer.c;
        List compilerPluginDataList = property6.getCompilerPluginDataList();
        List list2 = compilerPluginDataList.isEmpty() ? null : compilerPluginDataList;
        if (list2 != null) {
            List<ProtoBuf.CompilerPluginData> list3 = list2;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list3, 10)), 16));
            for (ProtoBuf.CompilerPluginData compilerPluginData : list3) {
                linkedHashMap.put(firDeserializationContext3.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
            }
            DeclarationAttributesKt.setCompilerPluginMetadata(firPropertyBuild, linkedHashMap);
            Unit unit4 = Unit.INSTANCE;
        }
        PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(firPropertyBuild, firMemberDeserializer.c.getSession());
        FirPropertyAccessor getter = firPropertyBuild.getGetter();
        if (getter != null) {
            PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(getter, firPropertyBuild.getAnnotations(), firPropertyBuild, firMemberDeserializer.c.getSession());
            Unit unit5 = Unit.INSTANCE;
        }
        FirPropertyAccessor setter = firPropertyBuild.getSetter();
        if (setter != null) {
            PublishedApiEffectiveVisibilityKt.setLazyPublishedVisibility(setter, firPropertyBuild.getAnnotations(), firPropertyBuild, firMemberDeserializer.c.getSession());
            Unit unit6 = Unit.INSTANCE;
        }
        FirPropertyAccessor getter2 = firPropertyBuild.getGetter();
        if (getter2 != null) {
            if (property6.hasGetterContract()) {
                FirContractDeserializer firContractDeserializer = firMemberDeserializer.contractDeserializer;
                ProtoBuf.Contract getterContract = property6.getGetterContract();
                getterContract.getClass();
                FirContractDescription firContractDescriptionLoadContract = firContractDeserializer.loadContract(getterContract, getter2);
                if (firContractDescriptionLoadContract != null) {
                    getter2.replaceContractDescription(firContractDescriptionLoadContract);
                    Unit unit7 = Unit.INSTANCE;
                }
            }
            Unit unit8 = Unit.INSTANCE;
        }
        FirPropertyAccessor setter2 = firPropertyBuild.getSetter();
        if (setter2 != null) {
            if (property6.hasSetterContract()) {
                FirContractDeserializer firContractDeserializer2 = firMemberDeserializer.contractDeserializer;
                ProtoBuf.Contract setterContract = property6.getSetterContract();
                setterContract.getClass();
                FirContractDescription firContractDescriptionLoadContract2 = firContractDeserializer2.loadContract(setterContract, setter2);
                if (firContractDescriptionLoadContract2 != null) {
                    setter2.replaceContractDescription(firContractDescriptionLoadContract2);
                    Unit unit9 = Unit.INSTANCE;
                }
            }
            Unit unit10 = Unit.INSTANCE;
        }
        return firPropertyBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirTypeAlias loadTypeAlias(ProtoBuf.TypeAlias proto, ClassId classId, FirScopeProvider scopeProvider, FirTypeAliasSymbol preComputedSymbol) {
        ClassId classId2;
        FirTypeAliasSymbol firTypeAliasSymbol;
        proto.getClass();
        classId.getClass();
        scopeProvider.getClass();
        int flags = proto.getFlags();
        if (preComputedSymbol == null) {
            classId2 = classId;
            firTypeAliasSymbol = new FirTypeAliasSymbol(classId2);
        } else {
            classId2 = classId;
            firTypeAliasSymbol = preComputedSymbol;
        }
        FirDeserializationContext firDeserializationContext = this.c;
        List typeParameterList = proto.getTypeParameterList();
        typeParameterList.getClass();
        FirDeserializationContext firDeserializationContextChildContext$default = FirDeserializationContext.childContext$default(firDeserializationContext, typeParameterList, firTypeAliasSymbol, null, null, null, null, null, null, null, false, 1020, null);
        List<VersionRequirement> listCreate = FirDeserializationUtilsKt.create(VersionRequirement.Companion, proto, this.c);
        FirTypeAliasBuilder firTypeAliasBuilder = new FirTypeAliasBuilder();
        firTypeAliasBuilder.setModuleData(this.c.getModuleData());
        firTypeAliasBuilder.setOrigin(FirDeclarationOrigin.Library.INSTANCE);
        firTypeAliasBuilder.setScopeProvider(scopeProvider);
        firTypeAliasBuilder.setName(classId2.getShortClassName());
        Visibility visibility = ProtoEnumFlags.INSTANCE.visibility((ProtoBuf.Visibility) Flags.VISIBILITY.get(flags));
        FirResolvedDeclarationStatusWithLazyEffectiveVisibility firResolvedDeclarationStatusWithLazyEffectiveVisibility = new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(visibility, Modality.FINAL, toLazyEffectiveVisibility(visibility, null));
        Boolean bool = Flags.IS_EXPECT_CLASS.get(flags);
        bool.getClass();
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setExpect(bool.booleanValue());
        firResolvedDeclarationStatusWithLazyEffectiveVisibility.setActual(false);
        firTypeAliasBuilder.setStatus(firResolvedDeclarationStatusWithLazyEffectiveVisibility);
        CollectionsKt.addAll(firTypeAliasBuilder.getAnnotations(), this.c.getAnnotationDeserializer().loadTypeAliasAnnotations(proto, firDeserializationContextChildContext$default.getNameResolver()));
        firTypeAliasBuilder.setSymbol(firTypeAliasSymbol);
        firTypeAliasBuilder.setExpandedTypeRef(toTypeRef(ProtoTypeTableUtilKt.expandedType(proto, this.c.getTypeTable()), firDeserializationContextChildContext$default));
        firTypeAliasBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeParameterRef> typeParameters = firTypeAliasBuilder.getTypeParameters();
        List<FirTypeParameterSymbol> ownTypeParameters = firDeserializationContextChildContext$default.getTypeDeserializer().getOwnTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(ownTypeParameters, 10));
        Iterator<T> it = ownTypeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add((FirTypeParameter) ((FirTypeParameterSymbol) it.next()).getFir());
        }
        CollectionsKt.addAll(typeParameters, arrayList);
        firTypeAliasBuilder.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAnnotations(firTypeAliasBuilder.getAnnotations(), this.c.getSession(), false, listCreate));
        FirTypeAlias firTypeAliasBuild = firTypeAliasBuilder.mo288build();
        FirVersionRequirementsTableKeyKt.setVersionRequirements(firTypeAliasBuild, listCreate);
        DeclarationAttributesKt.setSourceElement(firTypeAliasBuild, this.c.getContainerSource());
        FirDeserializationContext firDeserializationContext2 = this.c;
        List compilerPluginDataList = proto.getCompilerPluginDataList();
        List list = compilerPluginDataList.isEmpty() ? null : compilerPluginDataList;
        if (list != null) {
            List<ProtoBuf.CompilerPluginData> list2 = list;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
            for (ProtoBuf.CompilerPluginData compilerPluginData : list2) {
                linkedHashMap.put(firDeserializationContext2.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
            }
            DeclarationAttributesKt.setCompilerPluginMetadata(firTypeAliasBuild, linkedHashMap);
        }
        return firTypeAliasBuild;
    }
}
