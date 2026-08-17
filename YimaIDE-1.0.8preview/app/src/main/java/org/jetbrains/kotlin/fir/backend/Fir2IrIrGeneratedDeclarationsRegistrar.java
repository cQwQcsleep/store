package org.jetbrains.kotlin.fir.backend;

import defpackage.f2f;
import defpackage.pv4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructedClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.deserialization.FirEnumEntryDeserializerAccessUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirClassReferenceExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVarargArgumentsExpressionBuilder;
import org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider;
import org.jetbrains.kotlin.fir.serialization.FirProvidedDeclarationsForMetadataServiceKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrMetadataSourceOwner;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrClassReference;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrStarProjection;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0004?@ABB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u001bH\u0016J\u001e\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#H\u0016J \u0010$\u001a\u00020\u0019*\u00060%R\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020(H\u0002J\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u0004\u0018\u00010,*\u00020-H\u0002J\u0012\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u001b*\u000200H\u0002J\f\u00102\u001a\u000203*\u000204H\u0002J\f\u00105\u001a\u00020/*\u000206H\u0002J \u0010:\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u0002082\u0006\u0010=\u001a\u000209H\u0016J\u001a\u0010>\u001a\u0004\u0018\u0001092\u0006\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u000208H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0013\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u00101\u001a\u00060%R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R&\u00107\u001a\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u0002090\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "Lorg/jetbrains/kotlin/backend/common/extensions/IrGeneratedDeclarationsRegistrar;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "implicitType", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "getImplicitType", "()Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "annotationsStorage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotationsOnParametersStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind;", "getMetadataVisibleAnnotationsForElement", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "addMetadataVisibleAnnotationsToElement", Argument.Delimiters.none, "annotations", Argument.Delimiters.none, "findFirDeclaration", "Lkotlin/Pair;", "registerFunctionAsMetadataVisible", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "registerConstructorAsMetadataVisible", "irConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "updateFunctionCommon", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$TypeConverter;", "firFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "createAdditionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "toFirClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "convertAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", "emptyTypeConverter", "toFirExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "toFirAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "metadataExtensionsForDeclarations", Argument.Delimiters.none, Argument.Delimiters.none, "addCustomMetadataExtension", "irDeclaration", "pluginId", "data", "getCustomMetadataExtension", "ChildDeclarationKind", "TypeConverter", "GeneratedForMetadata", "Provider", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrIrGeneratedDeclarationsRegistrar extends IrGeneratedDeclarationsRegistrar {
    private final Map<FirDeclaration, Map<ChildDeclarationKind, List<IrAnnotation>>> annotationsOnParametersStorage;
    private final Map<FirDeclaration, List<IrAnnotation>> annotationsStorage;
    private final Fir2IrComponents components;
    private final TypeConverter emptyTypeConverter;
    private final Map<FirDeclaration, Map<String, byte[]>> metadataExtensionsForDeclarations;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÂ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$GeneratedForMetadata;", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class GeneratedForMetadata extends GeneratedDeclarationKey {
        public static final GeneratedForMetadata INSTANCE = new GeneratedForMetadata();

        private GeneratedForMetadata() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof GeneratedForMetadata);
        }

        public int hashCode() {
            return -1314509095;
        }

        public String toString() {
            return "GeneratedForMetadata";
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$Provider;", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;)V", "findGeneratedAnnotationsFor", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "hasGeneratedAnnotationsFor", Argument.Delimiters.none, "extractGeneratedIrDeclarations", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "findMetadataExtensionsFor", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Provider extends FirAdditionalMetadataProvider {
        public Provider() {
        }

        private final List<IrConstructorCall> extractGeneratedIrDeclarations(FirDeclaration declaration) {
            Pair pair;
            FirDeclarationOrigin origin = declaration.getOrigin();
            if ((origin instanceof FirDeclarationOrigin.Synthetic) || (origin instanceof FirDeclarationOrigin.Delegated)) {
                return CollectionsKt.emptyList();
            }
            if (declaration instanceof FirValueParameter) {
                FirValueParameter firValueParameter = (FirValueParameter) declaration;
                pair = TuplesKt.to(firValueParameter.getContainingDeclarationSymbol().getFir(), new ChildDeclarationKind.ValueParameter(firValueParameter.getName()));
            } else if (declaration instanceof FirTypeParameter) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) declaration;
                pair = TuplesKt.to(firTypeParameter.getContainingDeclarationSymbol().getFir(), new ChildDeclarationKind.TypeParameter(firTypeParameter.getName()));
            } else {
                pair = TuplesKt.to(declaration, null);
            }
            FirDeclaration firDeclaration = (FirDeclaration) pair.component1();
            ChildDeclarationKind childDeclarationKind = (ChildDeclarationKind) pair.component2();
            Fir2IrIrGeneratedDeclarationsRegistrar fir2IrIrGeneratedDeclarationsRegistrar = Fir2IrIrGeneratedDeclarationsRegistrar.this;
            if (childDeclarationKind == null) {
                List<IrConstructorCall> list = (List) fir2IrIrGeneratedDeclarationsRegistrar.annotationsStorage.get(firDeclaration);
                return list == null ? CollectionsKt.emptyList() : list;
            }
            Map mapEmptyMap = (Map) fir2IrIrGeneratedDeclarationsRegistrar.annotationsOnParametersStorage.get(firDeclaration);
            if (mapEmptyMap == null) {
                mapEmptyMap = MapsKt.emptyMap();
            }
            List<IrConstructorCall> list2 = (List) mapEmptyMap.get(childDeclarationKind);
            return list2 == null ? CollectionsKt.emptyList() : list2;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider
        public List<FirAnnotation> findGeneratedAnnotationsFor(FirDeclaration declaration) {
            declaration.getClass();
            List<IrConstructorCall> listExtractGeneratedIrDeclarations = extractGeneratedIrDeclarations(declaration);
            if (listExtractGeneratedIrDeclarations.isEmpty()) {
                listExtractGeneratedIrDeclarations = null;
            }
            if (listExtractGeneratedIrDeclarations == null) {
                return CollectionsKt.emptyList();
            }
            List<IrConstructorCall> list = listExtractGeneratedIrDeclarations;
            Fir2IrIrGeneratedDeclarationsRegistrar fir2IrIrGeneratedDeclarationsRegistrar = Fir2IrIrGeneratedDeclarationsRegistrar.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(fir2IrIrGeneratedDeclarationsRegistrar.toFirAnnotation((IrConstructorCall) it.next()));
            }
            return arrayList;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider
        public Map<String, byte[]> findMetadataExtensionsFor(FirDeclaration declaration) {
            declaration.getClass();
            Map<String, byte[]> map = (Map) Fir2IrIrGeneratedDeclarationsRegistrar.this.metadataExtensionsForDeclarations.get(declaration);
            return map == null ? MapsKt.emptyMap() : map;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider
        public boolean hasGeneratedAnnotationsFor(FirDeclaration declaration) {
            declaration.getClass();
            return !extractGeneratedIrDeclarations(declaration).isEmpty();
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0092\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\r*\u00020\u000eJ\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\u0002J\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$TypeConverter;", Argument.Delimiters.none, "originalFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "convertedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "getOriginalFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getConvertedFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "toConeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/ir/types/IrType;", "toConeTypeProjection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "toLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public class TypeConverter {
        private final FirFunction convertedFunction;
        private final IrFunction originalFunction;

        public TypeConverter(IrFunction irFunction, FirFunction firFunction) {
            this.originalFunction = irFunction;
            this.convertedFunction = firFunction;
            if (irFunction == null || firFunction != null) {
                return;
            }
            k2d.a("Conversion with null `convertedFunction`is unsupported");
            throw null;
        }

        private final ConeTypeProjection toConeTypeProjection(IrTypeArgument irTypeArgument) {
            if (irTypeArgument instanceof IrStarProjection) {
                return ConeStarProjection.INSTANCE;
            }
            if (irTypeArgument instanceof IrTypeProjection) {
                IrTypeProjection irTypeProjection = (IrTypeProjection) irTypeArgument;
                return ConeTypeUtilsKt.toTypeProjection(toConeType(irTypeProjection.getType()), irTypeProjection.getVariance());
            }
            bu8.a();
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final ConeClassifierLookupTag toLookupTag(IrClassifierSymbol irClassifierSymbol) {
            FirRegularClass firRegularClass;
            FirTypeParameterRef firTypeParameterRef;
            IrClass owner = irClassifierSymbol.getOwner();
            if (owner instanceof IrClass) {
                return TypeConstructionUtilsKt.toLookupTag(AdditionalIrUtilsKt.getClassIdOrFail(owner));
            }
            if (!(owner instanceof IrTypeParameter)) {
                f2f.a("Unsupported IR classifier: ", RenderIrElementKt.render$default((IrElement) owner, (DumpIrTreeOptions) null, 1, (Object) null));
                return null;
            }
            IrTypeParameter irTypeParameter = (IrTypeParameter) owner;
            IrClass parent = irTypeParameter.getParent();
            if (Intrinsics.areEqual(parent, this.originalFunction)) {
                FirFunction firFunction = this.convertedFunction;
                firFunction.getClass();
                firTypeParameterRef = firFunction.getTypeParameters().get(irTypeParameter.getIndex());
            } else {
                if (!(parent instanceof IrClass)) {
                    f2f.a("Unsupported type parameter container: ", RenderIrElementKt.render$default(parent, (DumpIrTreeOptions) null, 1, (Object) null));
                    return null;
                }
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((ConeClassLikeLookupTag) TypeConstructionUtilsKt.toLookupTag(AdditionalIrUtilsKt.getClassIdOrFail(parent)), Fir2IrIrGeneratedDeclarationsRegistrar.this.getSession());
                if (regularClassSymbol == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null) {
                    b88.a("Fir class for ", RenderIrElementKt.render$default(parent, (DumpIrTreeOptions) null, 1, (Object) null), " not found");
                    return null;
                }
                firTypeParameterRef = firRegularClass.getTypeParameters().get(irTypeParameter.getIndex());
            }
            return firTypeParameterRef.getSymbol().getLookupTag();
        }

        public final FirFunction getConvertedFunction() {
            return this.convertedFunction;
        }

        public final IrFunction getOriginalFunction() {
            return this.originalFunction;
        }

        public final ConeKotlinType toConeType(IrType irType) {
            irType.getClass();
            if (!(irType instanceof IrSimpleType)) {
                if (irType instanceof IrDynamicType) {
                    return TypeUtilsKt.create$default(ConeDynamicType.Companion, Fir2IrIrGeneratedDeclarationsRegistrar.this.getSession(), null, 2, null);
                }
                w04.a("Unsupported IR type: ", irType);
                return null;
            }
            IrSimpleType irSimpleType = (IrSimpleType) irType;
            ConeClassifierLookupTag lookupTag = toLookupTag(irSimpleType.getClassifier());
            List arguments = irSimpleType.getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
            Iterator it = arguments.iterator();
            while (it.hasNext()) {
                arrayList.add(toConeTypeProjection((IrTypeArgument) it.next()));
            }
            return TypeConstructionUtilsKt.constructType$default(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]), IrTypePredicatesKt.isMarkedNullable(irSimpleType), (ConeAttributes) null, 4, (Object) null);
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IrParameterKind.values().length];
            try {
                iArr[IrParameterKind.DispatchReceiver.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IrParameterKind.ExtensionReceiver.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IrParameterKind.Regular.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[IrParameterKind.Context.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Fir2IrIrGeneratedDeclarationsRegistrar(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.components = fir2IrComponents;
        this.annotationsStorage = new LinkedHashMap();
        this.annotationsOnParametersStorage = new LinkedHashMap();
        this.emptyTypeConverter = new TypeConverter(null, null);
        this.metadataExtensionsForDeclarations = new LinkedHashMap();
    }

    private final List<FirAnnotation> convertAnnotations(IrAnnotationContainer irAnnotationContainer) {
        List<IrAnnotation> annotations = irAnnotationContainer.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        Iterator<T> it = annotations.iterator();
        while (it.hasNext()) {
            arrayList.add(toFirAnnotation((IrAnnotation) it.next()));
        }
        return arrayList;
    }

    private final Pair<FirDeclaration, ChildDeclarationKind> findFirDeclaration(IrDeclaration declaration) {
        FirDeclaration fir;
        if (declaration instanceof IrMetadataSourceOwner) {
            MetadataSource metadata = ((IrMetadataSourceOwner) declaration).getMetadata();
            FirMetadataSource firMetadataSource = metadata instanceof FirMetadataSource ? (FirMetadataSource) metadata : null;
            if (firMetadataSource != null && (fir = firMetadataSource.getFir()) != null) {
                return TuplesKt.to(fir, null);
            }
            f2f.a("Fir declaration is not found for ", RenderIrElementKt.render$default((IrElement) declaration, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        if (declaration instanceof IrValueParameter) {
            IrValueParameter irValueParameter = (IrValueParameter) declaration;
            IrDeclarationParent parent = irValueParameter.getParent();
            parent.getClass();
            return TuplesKt.to(findFirDeclaration((IrDeclaration) parent).getFirst(), new ChildDeclarationKind.ValueParameter(irValueParameter.getName()));
        }
        if (!(declaration instanceof IrTypeParameter)) {
            f2f.a("Declaration with annotations should be `IrMetadataSourceOwner`, `IrValueParameter` or `IrTypeParameter`, but got ", RenderIrElementKt.render$default((IrElement) declaration, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        IrTypeParameter irTypeParameter = (IrTypeParameter) declaration;
        IrDeclarationParent parent2 = irTypeParameter.getParent();
        parent2.getClass();
        return TuplesKt.to(findFirDeclaration((IrDeclaration) parent2).getFirst(), new ChildDeclarationKind.TypeParameter(irTypeParameter.getName()));
    }

    private final FirImplicitTypeRef getImplicitType() {
        return FirImplicitTypeRefImplWithoutSource.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirSession getSession() {
        return this.components.getSession();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirAnnotation toFirAnnotation(IrConstructorCall irConstructorCall) {
        ClassId classId = AdditionalIrUtilsKt.getClassId(AdditionalIrUtilsKt.getConstructedClass(irConstructorCall.getSymbol().getOwner()));
        classId.getClass();
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        firAnnotationBuilder.setAnnotationTypeRef(UtilsKt.toFirResolvedTypeRef$default(TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(classId), null, false, null, 7, null), null, null, 3, null));
        FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
        int i = 0;
        for (IrExpression irExpression : irConstructorCall.getArguments()) {
            int i2 = i + 1;
            if (irExpression != null) {
                firAnnotationArgumentMappingBuilder.getMapping().put(((IrValueParameter) irConstructorCall.getSymbol().getOwner().getParameters().get(i)).getName(), toFirExpression(irExpression));
            }
            i = i2;
        }
        firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuilder.build());
        return firAnnotationBuilder.mo288build();
    }

    private final FirRegularClass toFirClass(IrDeclarationParent irDeclarationParent) {
        IrClass irClass = irDeclarationParent instanceof IrClass ? (IrClass) irDeclarationParent : null;
        MetadataSource metadata = irClass != null ? irClass.getMetadata() : null;
        FirMetadataSource.Class r1 = metadata instanceof FirMetadataSource.Class ? (FirMetadataSource.Class) metadata : null;
        FirClass fir = r1 != null ? r1.getFir() : null;
        if (fir instanceof FirRegularClass) {
            return (FirRegularClass) fir;
        }
        return null;
    }

    private final FirExpression toFirExpression(IrExpression irExpression) {
        FirEnumEntrySymbol firEnumEntrySymbol;
        List<FirBasedSymbol<?>> declarationSymbols;
        Object next;
        if (irExpression instanceof IrConst) {
            IrConst irConst = (IrConst) irExpression;
            IrConstKind kind = irConst.getKind();
            if (Intrinsics.areEqual(kind, IrConstKind.Boolean.INSTANCE)) {
                ConstantValueKind.Boolean r3 = ConstantValueKind.Boolean.INSTANCE;
                Object value = irConst.getValue();
                value.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r3, (Boolean) value, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Byte.INSTANCE)) {
                ConstantValueKind.Byte r4 = ConstantValueKind.Byte.INSTANCE;
                Object value2 = irConst.getValue();
                value2.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r4, (Byte) value2, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Char.INSTANCE)) {
                ConstantValueKind.Char r5 = ConstantValueKind.Char.INSTANCE;
                Object value3 = irConst.getValue();
                value3.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r5, (Character) value3, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Double.INSTANCE)) {
                ConstantValueKind.Double r6 = ConstantValueKind.Double.INSTANCE;
                Object value4 = irConst.getValue();
                value4.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r6, (Double) value4, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Float.INSTANCE)) {
                ConstantValueKind.Float r7 = ConstantValueKind.Float.INSTANCE;
                Object value5 = irConst.getValue();
                value5.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r7, (Float) value5, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Int.INSTANCE)) {
                ConstantValueKind.Int r8 = ConstantValueKind.Int.INSTANCE;
                Object value6 = irConst.getValue();
                value6.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r8, (Integer) value6, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Long.INSTANCE)) {
                ConstantValueKind.Long r9 = ConstantValueKind.Long.INSTANCE;
                Object value7 = irConst.getValue();
                value7.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r9, (Long) value7, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Null.INSTANCE)) {
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Null.INSTANCE, null, null, true, null, 40, null);
            }
            if (Intrinsics.areEqual(kind, IrConstKind.Short.INSTANCE)) {
                ConstantValueKind.Short r10 = ConstantValueKind.Short.INSTANCE;
                Object value8 = irConst.getValue();
                value8.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, r10, (Short) value8, null, true, null, 40, null);
            }
            if (!Intrinsics.areEqual(kind, IrConstKind.String.INSTANCE)) {
                bu8.a();
                return null;
            }
            ConstantValueKind.String string = ConstantValueKind.String.INSTANCE;
            Object value9 = irConst.getValue();
            value9.getClass();
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, string, (String) value9, null, true, null, 40, null);
        }
        if (!(irExpression instanceof IrGetEnumValue)) {
            if (irExpression instanceof IrConstructorCall) {
                return toFirAnnotation((IrConstructorCall) irExpression);
            }
            if (!(irExpression instanceof IrVararg)) {
                if (!(irExpression instanceof IrClassReference)) {
                    f2f.a("Unsupported ir type: ", RenderIrElementKt.render$default(irExpression, (DumpIrTreeOptions) null, 1, (Object) null));
                    return null;
                }
                FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
                TypeConverter typeConverter = this.emptyTypeConverter;
                ConeKotlinType coneType = typeConverter.toConeType(irExpression.getType());
                firGetClassCallBuilder.setConeTypeOrNull(coneType);
                FirClassReferenceExpressionBuilder firClassReferenceExpressionBuilder = new FirClassReferenceExpressionBuilder();
                firClassReferenceExpressionBuilder.setConeTypeOrNull(typeConverter.toConeType(((IrClassReference) irExpression).getClassType()));
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setConeType(coneType);
                firClassReferenceExpressionBuilder.setClassTypeRef(firResolvedTypeRefBuilder.build());
                firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firClassReferenceExpressionBuilder.mo288build()));
                return firGetClassCallBuilder.mo288build();
            }
            IrVararg irVararg = (IrVararg) irExpression;
            List<IrVarargElement> elements = irVararg.getElements();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(elements, 10));
            for (IrVarargElement irVarargElement : elements) {
                if (!(irVarargElement instanceof IrExpression)) {
                    f2f.a("Unsupported ir type: ", RenderIrElementKt.render$default(irVarargElement, (DumpIrTreeOptions) null, 1, (Object) null));
                    return null;
                }
                arrayList.add(toFirExpression((IrExpression) irVarargElement));
            }
            TypeConverter typeConverter2 = this.emptyTypeConverter;
            ConeKotlinType coneType2 = typeConverter2.toConeType(irExpression.getType());
            ConeKotlinType coneType3 = typeConverter2.toConeType(irVararg.getVarargElementType());
            FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
            firVarargArgumentsExpressionBuilder.getArguments().addAll(arrayList);
            firVarargArgumentsExpressionBuilder.setConeTypeOrNull(coneType2);
            firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(coneType3);
            return firVarargArgumentsExpressionBuilder.mo288build();
        }
        IrGetEnumValue irGetEnumValue = (IrGetEnumValue) irExpression;
        IrClass parent = irGetEnumValue.getSymbol().getOwner().getParent();
        parent.getClass();
        ClassId classId = AdditionalIrUtilsKt.getClassId(parent);
        classId.getClass();
        Name name = irGetEnumValue.getSymbol().getOwner().getName();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(getSession()).getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId != null) {
            FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
            if (firRegularClassSymbol == null || (declarationSymbols = firRegularClassSymbol.getDeclarationSymbols()) == null) {
                firEnumEntrySymbol = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : declarationSymbols) {
                    if (obj instanceof FirEnumEntrySymbol) {
                        arrayList2.add(obj);
                    }
                }
                Iterator it = arrayList2.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((FirEnumEntrySymbol) next).getName(), name));
                firEnumEntrySymbol = (FirEnumEntrySymbol) next;
            }
            if (firEnumEntrySymbol != null) {
                FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
                FirResolvedQualifier resolvedQualifier = FirEnumEntryDeserializerAccessUtilKt.toResolvedQualifier(classId, getSession());
                firPropertyAccessExpressionBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(resolvedQualifier));
                FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
                firResolvedNamedReferenceBuilder.setName(name);
                firResolvedNamedReferenceBuilder.setResolvedSymbol(firEnumEntrySymbol);
                firPropertyAccessExpressionBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
                firPropertyAccessExpressionBuilder.setExplicitReceiver(resolvedQualifier);
                firPropertyAccessExpressionBuilder.setDispatchReceiver(resolvedQualifier);
                return firPropertyAccessExpressionBuilder.mo288build();
            }
        }
        pv4.a("Could not resolve FirEnumEntry for ", classId, 46, name);
        return null;
    }

    private final void updateFunctionCommon(TypeConverter typeConverter, FirFunction firFunction, IrFunction irFunction) {
        firFunction.replaceReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(typeConverter.toConeType(irFunction.getReturnType()), null, null, 3, null));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (IrValueParameter irValueParameter : irFunction.getParameters()) {
            int i = WhenMappings.$EnumSwitchMapping$0[irValueParameter.getKind().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
                    firReceiverParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
                    firReceiverParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(GeneratedForMetadata.INSTANCE));
                    firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
                    firReceiverParameterBuilder.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(typeConverter.toConeType(irValueParameter.getType()), null, null, 3, null));
                    firReceiverParameterBuilder.setContainingDeclarationSymbol(firFunction.getSymbol());
                    firReceiverParameterBuilder.getAnnotations().addAll(convertAnnotations(irValueParameter));
                    firFunction.replaceReceiverParameter(firReceiverParameterBuilder.mo288build());
                } else {
                    if (i != 3 && i != 4) {
                        bu8.a();
                        return;
                    }
                    boolean z = irValueParameter.getKind() == IrParameterKind.Context;
                    FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                    firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
                    firValueParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(GeneratedForMetadata.INSTANCE));
                    firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(typeConverter.toConeType(irValueParameter.getType()), null, null, 3, null));
                    firValueParameterBuilder.setName(irValueParameter.getName());
                    firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                    if (irValueParameter.getDefaultValue() != null) {
                        FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                        firExpressionStubBuilder.setConeTypeOrNull(FirTypeUtilsKt.getConeType(firValueParameterBuilder.getReturnTypeRef()));
                        firValueParameterBuilder.setDefaultValue(firExpressionStubBuilder.mo288build());
                    }
                    firValueParameterBuilder.setContainingDeclarationSymbol(firFunction.getSymbol());
                    firValueParameterBuilder.setCrossinline(irValueParameter.isCrossinline());
                    firValueParameterBuilder.setNoinline(irValueParameter.isNoinline());
                    firValueParameterBuilder.setVararg(AdditionalIrUtilsKt.isVararg(irValueParameter));
                    firValueParameterBuilder.getAnnotations().addAll(convertAnnotations(irValueParameter));
                    firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
                    firValueParameterBuilder.setValueParameterKind(z ? FirValueParameterKind.ContextParameter : FirValueParameterKind.Regular);
                    FirValueParameter firValueParameterMo288build = firValueParameterBuilder.mo288build();
                    if (z) {
                        arrayList.add(firValueParameterMo288build);
                    } else {
                        arrayList2.add(firValueParameterMo288build);
                    }
                }
            }
        }
        firFunction.replaceValueParameters(arrayList2);
        firFunction.replaceContextParameters(arrayList);
        firFunction.replaceAnnotations(convertAnnotations(irFunction));
    }

    public void addCustomMetadataExtension(IrDeclaration irDeclaration, String pluginId, byte[] data) {
        MetadataSource metadata;
        FirDeclaration fir;
        irDeclaration.getClass();
        pluginId.getClass();
        data.getClass();
        IrMetadataSourceOwner irMetadataSourceOwner = irDeclaration instanceof IrMetadataSourceOwner ? (IrMetadataSourceOwner) irDeclaration : null;
        if (irMetadataSourceOwner == null || (metadata = irMetadataSourceOwner.getMetadata()) == null) {
            f2f.a("No metadata source found for ", RenderIrElementKt.render$default((IrElement) irDeclaration, (DumpIrTreeOptions) null, 1, (Object) null));
            return;
        }
        FirMetadataSource firMetadataSource = metadata instanceof FirMetadataSource ? (FirMetadataSource) metadata : null;
        if (firMetadataSource == null || (fir = firMetadataSource.getFir()) == null) {
            f2f.a("No FIR declaration found for ", RenderIrElementKt.render$default((IrElement) irDeclaration, (DumpIrTreeOptions) null, 1, (Object) null));
            return;
        }
        Map<FirDeclaration, Map<String, byte[]>> map = this.metadataExtensionsForDeclarations;
        Map<String, byte[]> linkedHashMap = map.get(fir);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            map.put(fir, linkedHashMap);
        }
        if (linkedHashMap.put(pluginId, data) == null) {
            return;
        }
        rza.a("There is already metadata value for plugin ", pluginId, " and ", RenderIrElementKt.render$default((IrElement) irDeclaration, (DumpIrTreeOptions) null, 1, (Object) null));
    }

    public void addMetadataVisibleAnnotationsToElement(IrDeclaration declaration, List<? extends IrAnnotation> annotations) {
        declaration.getClass();
        annotations.getClass();
        List<? extends IrAnnotation> list = annotations;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((IrAnnotation) it.next()).getTypeArguments().isEmpty()) {
                    dt1.a("Saving annotations with type arguments from IR to metadata is not supported: ", RenderIrElementKt.render$default((IrElement) declaration, (DumpIrTreeOptions) null, 1, (Object) null));
                    return;
                }
            }
        }
        for (IrAnnotation irAnnotation : list) {
            if (!IrUtilsKt.isAnnotationClass(AdditionalIrUtilsKt.getConstructedClass(irAnnotation.getSymbol().getOwner()))) {
                z1f.a(RenderIrElementKt.render$default(irAnnotation, (DumpIrTreeOptions) null, 1, (Object) null), " is not an annotation constructor call");
                return;
            }
        }
        CollectionsKt.addAll(getMetadataVisibleAnnotationsForElement(declaration), list);
        declaration.setAnnotations(CollectionsKt.plus(declaration.getAnnotations(), list));
    }

    public final FirAdditionalMetadataProvider createAdditionalMetadataProvider() {
        return new Provider();
    }

    public byte[] getCustomMetadataExtension(IrDeclaration irDeclaration, String pluginId) {
        FirDeclaration fir;
        Map<String, byte[]> compilerPluginMetadata;
        irDeclaration.getClass();
        pluginId.getClass();
        AbstractFir2IrLazyDeclaration abstractFir2IrLazyDeclaration = irDeclaration instanceof AbstractFir2IrLazyDeclaration ? (AbstractFir2IrLazyDeclaration) irDeclaration : null;
        if (abstractFir2IrLazyDeclaration == null || (fir = abstractFir2IrLazyDeclaration.getFir()) == null || (compilerPluginMetadata = DeclarationAttributesKt.getCompilerPluginMetadata(fir)) == null) {
            return null;
        }
        return compilerPluginMetadata.get(pluginId);
    }

    public List<IrAnnotation> getMetadataVisibleAnnotationsForElement(IrDeclaration declaration) {
        declaration.getClass();
        if (Intrinsics.areEqual(declaration.getOrigin(), IrDeclarationOrigin.Companion.getFAKE_OVERRIDE())) {
            dt1.a("FAKE_OVERRIDE declarations are not preserved in metadata and should not be marked with annotations: ", RenderIrElementKt.render$default((IrElement) declaration, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        Pair<FirDeclaration, ChildDeclarationKind> pairFindFirDeclaration = findFirDeclaration(declaration);
        FirDeclaration firDeclaration = (FirDeclaration) pairFindFirDeclaration.component1();
        ChildDeclarationKind childDeclarationKind = (ChildDeclarationKind) pairFindFirDeclaration.component2();
        if (childDeclarationKind == null) {
            Map<FirDeclaration, List<IrAnnotation>> map = this.annotationsStorage;
            List<IrAnnotation> arrayList = map.get(firDeclaration);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(firDeclaration, arrayList);
            }
            return arrayList;
        }
        Map<FirDeclaration, Map<ChildDeclarationKind, List<IrAnnotation>>> map2 = this.annotationsOnParametersStorage;
        Map<ChildDeclarationKind, List<IrAnnotation>> linkedHashMap = map2.get(firDeclaration);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            map2.put(firDeclaration, linkedHashMap);
        }
        Map<ChildDeclarationKind, List<IrAnnotation>> map3 = linkedHashMap;
        List<IrAnnotation> arrayList2 = map3.get(childDeclarationKind);
        if (arrayList2 == null) {
            arrayList2 = new ArrayList<>();
            map3.put(childDeclarationKind, arrayList2);
        }
        return arrayList2;
    }

    public void registerConstructorAsMetadataVisible(IrConstructor irConstructor) {
        irConstructor.getClass();
        if (AdditionalIrUtilsKt.isLocal(irConstructor) || AdditionalIrUtilsKt.isLocal(IrUtilsKt.getParentAsClass(irConstructor))) {
            return;
        }
        FirRegularClass firClass = toFirClass(irConstructor.getParent());
        if (firClass == null) {
            b88.a("Fir class for constructor ", RenderIrElementKt.render$default(irConstructor, (DumpIrTreeOptions) null, 1, (Object) null), " not found");
            return;
        }
        FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
        firConstructorBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firConstructorBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(GeneratedForMetadata.INSTANCE));
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(irConstructor.getVisibility().getDelegate(), Modality.FINAL, EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(irConstructor.getVisibility().getDelegate(), (ConeClassLikeLookupTag) null, false, false, 6, (Object) null));
        firResolvedDeclarationStatusImpl.setExpect(irConstructor.isExpect());
        firResolvedDeclarationStatusImpl.setActual(false);
        firConstructorBuilder.setStatus(firResolvedDeclarationStatusImpl);
        firConstructorBuilder.setLocal(firClass.getIsLocal());
        firConstructorBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firConstructorBuilder.setReturnTypeRef(getImplicitType());
        firConstructorBuilder.setSymbol(new FirConstructorSymbol(FirDeclarationUtilKt.getClassId(firClass)));
        List<FirTypeParameterRef> typeParameters = firClass.getTypeParameters();
        List<FirTypeParameterRef> typeParameters2 = firConstructorBuilder.getTypeParameters();
        for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
            FirConstructedClassTypeParameterRefBuilder firConstructedClassTypeParameterRefBuilder = new FirConstructedClassTypeParameterRefBuilder();
            firConstructedClassTypeParameterRefBuilder.setSymbol(firTypeParameterRef.getSymbol());
            typeParameters2.add(firConstructedClassTypeParameterRefBuilder.build());
        }
        FirConstructor firConstructorMo288build = firConstructorBuilder.mo288build();
        updateFunctionCommon(new TypeConverter(irConstructor, firConstructorMo288build), firConstructorMo288build, irConstructor);
        ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorMo288build, firClass.getSymbol().getLookupTag());
        FirProvidedDeclarationsForMetadataServiceKt.getProvidedDeclarationsForMetadataService(getSession()).registerDeclaration(firConstructorMo288build);
        irConstructor.setMetadata(new FirMetadataSource.Function(firConstructorMo288build));
    }

    public void registerFunctionAsMetadataVisible(IrSimpleFunction irFunction) {
        irFunction.getClass();
        if (AdditionalIrUtilsKt.isLocal(irFunction)) {
            return;
        }
        IrClass parentClassOrNull = IrUtilsKt.getParentClassOrNull(irFunction);
        if (parentClassOrNull == null || !AdditionalIrUtilsKt.isLocal(parentClassOrNull)) {
            FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
            firNamedFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
            firNamedFunctionBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(GeneratedForMetadata.INSTANCE));
            FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(irFunction.getVisibility().getDelegate(), irFunction.getModality(), EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(irFunction.getVisibility().getDelegate(), (ConeClassLikeLookupTag) null, false, false, 6, (Object) null));
            firResolvedDeclarationStatusImpl.setExpect(irFunction.isExpect());
            firResolvedDeclarationStatusImpl.setActual(false);
            firResolvedDeclarationStatusImpl.setOverride(true ^ irFunction.getOverriddenSymbols().isEmpty());
            firResolvedDeclarationStatusImpl.setInfix(irFunction.isInfix());
            firResolvedDeclarationStatusImpl.setInline(irFunction.isInline());
            firResolvedDeclarationStatusImpl.setTailRec(irFunction.isTailrec());
            firResolvedDeclarationStatusImpl.setSuspend(irFunction.isSuspend());
            firNamedFunctionBuilder.setStatus(firResolvedDeclarationStatusImpl);
            firNamedFunctionBuilder.setLocal(false);
            firNamedFunctionBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
            firNamedFunctionBuilder.setReturnTypeRef(getImplicitType());
            FirRegularClass firClass = toFirClass(irFunction.getParent());
            firNamedFunctionBuilder.setDispatchReceiverType(firClass != null ? ScopeUtilsKt.defaultType(firClass) : null);
            firNamedFunctionBuilder.setName(irFunction.getName());
            firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(AdditionalIrUtilsKt.getCallableId(irFunction)));
            List<IrTypeParameter> typeParameters = irFunction.getTypeParameters();
            List<FirTypeParameter> typeParameters2 = firNamedFunctionBuilder.getTypeParameters();
            for (IrTypeParameter irTypeParameter : typeParameters) {
                FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
                firTypeParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
                firTypeParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(GeneratedForMetadata.INSTANCE));
                firTypeParameterBuilder.setName(irTypeParameter.getName());
                firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
                firTypeParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
                firTypeParameterBuilder.setVariance(irTypeParameter.getVariance());
                firTypeParameterBuilder.setReified(irTypeParameter.isReified());
                firTypeParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
                typeParameters2.add(firTypeParameterBuilder.mo288build());
            }
            FirNamedFunction firNamedFunctionMo288build = firNamedFunctionBuilder.mo288build();
            TypeConverter typeConverter = new TypeConverter(irFunction, firNamedFunctionMo288build);
            updateFunctionCommon(typeConverter, firNamedFunctionMo288build, irFunction);
            for (Pair pair : CollectionsKt.zip(firNamedFunctionMo288build.getTypeParameters(), irFunction.getTypeParameters())) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) pair.component1();
                IrAnnotationContainer irAnnotationContainer = (IrTypeParameter) pair.component2();
                List superTypes = irAnnotationContainer.getSuperTypes();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypes, 10));
                Iterator it = superTypes.iterator();
                while (it.hasNext()) {
                    arrayList.add(UtilsKt.toFirResolvedTypeRef$default(typeConverter.toConeType((IrType) it.next()), null, null, 3, null));
                }
                firTypeParameter.replaceBounds(arrayList);
                firTypeParameter.replaceAnnotations(convertAnnotations(irAnnotationContainer));
            }
            FirProvidedDeclarationsForMetadataServiceKt.getProvidedDeclarationsForMetadataService(getSession()).registerDeclaration(firNamedFunctionMo288build);
            irFunction.setMetadata(new FirMetadataSource.Function(firNamedFunctionMo288build));
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind;", Argument.Delimiters.none, "<init>", "()V", "ValueParameter", "TypeParameter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind$TypeParameter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind$ValueParameter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ChildDeclarationKind {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind$TypeParameter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class TypeParameter extends ChildDeclarationKind {
            private final Name name;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public TypeParameter(Name name) {
                super(null);
                name.getClass();
                this.name = name;
            }

            public static /* synthetic */ TypeParameter copy$default(TypeParameter typeParameter, Name name, int i, Object obj) {
                if ((i & 1) != 0) {
                    name = typeParameter.name;
                }
                return typeParameter.copy(name);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Name getName() {
                return this.name;
            }

            public final TypeParameter copy(Name name) {
                name.getClass();
                return new TypeParameter(name);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof TypeParameter) && Intrinsics.areEqual(this.name, ((TypeParameter) other).name);
            }

            public final Name getName() {
                return this.name;
            }

            public int hashCode() {
                return this.name.hashCode();
            }

            public String toString() {
                return "TypeParameter(name=" + this.name + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind$ValueParameter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar$ChildDeclarationKind;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class ValueParameter extends ChildDeclarationKind {
            private final Name name;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ValueParameter(Name name) {
                super(null);
                name.getClass();
                this.name = name;
            }

            public static /* synthetic */ ValueParameter copy$default(ValueParameter valueParameter, Name name, int i, Object obj) {
                if ((i & 1) != 0) {
                    name = valueParameter.name;
                }
                return valueParameter.copy(name);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Name getName() {
                return this.name;
            }

            public final ValueParameter copy(Name name) {
                name.getClass();
                return new ValueParameter(name);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ValueParameter) && Intrinsics.areEqual(this.name, ((ValueParameter) other).name);
            }

            public final Name getName() {
                return this.name;
            }

            public int hashCode() {
                return this.name.hashCode();
            }

            public String toString() {
                return "ValueParameter(name=" + this.name + ')';
            }
        }

        public /* synthetic */ ChildDeclarationKind(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ChildDeclarationKind() {
        }
    }
}
