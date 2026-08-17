package org.jetbrains.kotlin.fir.plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThrowExpressionBuilder;
import org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 W*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003UVWB'\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u001e\u001a\u00020\u001f2\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001f0!¢\u0006\u0002\b#JI\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0019\b\u0002\u0010+\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u001f0!¢\u0006\u0002\b#H\u0016J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000201H\u0016J\"\u00102\u001a\u00020\u001f2\u0018\u00104\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/\u0012\u0004\u0012\u0002010!H\u0016J8\u00105\u001a\u00020\u001f2\f\u00106\u001a\b\u0012\u0004\u0012\u0002070.2\f\u00108\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u00109\u001a\u00020:2\n\u0010;\u001a\u0006\u0012\u0002\b\u00030<H\u0004J\n\u0010I\u001a\u0004\u0018\u00010BH\u0004J\r\u0010J\u001a\u00028\u0000H&¢\u0006\u0002\u0010KJ\b\u0010L\u001a\u00020\"H\u0004J\u001c\u0010M\u001a\u00020N2\u0006\u0010$\u001a\u00020=2\n\u0010;\u001a\u0006\u0012\u0002\b\u00030OH\u0004J$\u0010P\u001a\u00020\u001f2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002000/2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020N0/H\u0004J\b\u0010S\u001a\u00020TH\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR&\u0010-\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/\u0012\u0004\u0012\u0002010!0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020=0.X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R%\u0010@\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001f0!¢\u0006\u0002\b#0.X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010C\u001a\u0004\u0018\u00010B2\b\u0010A\u001a\u0004\u0018\u00010B8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0010\u0010H\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0001\u0003XYZ¨\u0006["}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext;", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getKey", "()Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "getOwner", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/Visibility;)V", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "setModality", "(Lorg/jetbrains/kotlin/descriptors/Modality;)V", "status", Argument.Delimiters.none, "statusConfig", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "Lkotlin/ExtensionFunctionType;", "typeParameter", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "isReified", Argument.Delimiters.none, "config", "Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext$TypeParameterBuildingContext;", "contextReceiverTypeProviders", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "contextReceiver", ModuleXmlParser.TYPE, "typeProvider", "produceContextReceiversTo", "destination", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "typeParameters", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext$TypeParameterData;", "getTypeParameters", "()Ljava/util/List;", "statusConfigs", "value", "Lorg/jetbrains/kotlin/KtSourceElement;", "source", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "_source", "getSourceForFirDeclaration", "build", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "generateStatus", "generateTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "initTypeParameterBounds", "allParameters", "ownTypeParameters", "generateExpressionStub", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "TypeParameterBuildingContext", "TypeParameterData", "Companion", "Lorg/jetbrains/kotlin/fir/plugin/ClassBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/FunctionBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/PropertyBuildingContext;", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DeclarationBuildingContext<T extends FirDeclaration> {
    private static final Companion Companion = new Companion(null);
    private static final Object DEFAULT_SOURCE_ELEMENT_STUB = new Object();
    private Object _source;
    private final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> contextReceiverTypeProviders;
    private final GeneratedDeclarationKey key;
    private Modality modality;
    private final FirClassSymbol<?> owner;
    private final FirSession session;
    private final List<Function1<FirResolvedDeclarationStatusImpl, Unit>> statusConfigs;
    private final List<TypeParameterData> typeParameters;
    private Visibility visibility;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0084\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001e\u0010\b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0004\u0012\u00020\f0\n0\t\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J!\u0010\u001d\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0004\u0012\u00020\f0\n0\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000eHÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072 \b\u0002\u0010\b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0004\u0012\u00020\f0\n0\t2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0014\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0015R)\u0010\b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0004\u0012\u00020\f0\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext$TypeParameterData;", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "isReified", Argument.Delimiters.none, "boundProviders", Argument.Delimiters.none, "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/types/Variance;ZLjava/util/List;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "()Z", "getBoundProviders", "()Ljava/util/List;", "getKey", "()Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class TypeParameterData {
        private final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> boundProviders;
        private final boolean isReified;
        private final GeneratedDeclarationKey key;
        private final Name name;
        private final Variance variance;

        public TypeParameterData(Name name, Variance variance, boolean z, List<? extends Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>> list, GeneratedDeclarationKey generatedDeclarationKey) {
            name.getClass();
            variance.getClass();
            list.getClass();
            generatedDeclarationKey.getClass();
            this.name = name;
            this.variance = variance;
            this.isReified = z;
            this.boundProviders = list;
            this.key = generatedDeclarationKey;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TypeParameterData copy$default(TypeParameterData typeParameterData, Name name, Variance variance, boolean z, List list, GeneratedDeclarationKey generatedDeclarationKey, int i, Object obj) {
            if ((i & 1) != 0) {
                name = typeParameterData.name;
            }
            if ((i & 2) != 0) {
                variance = typeParameterData.variance;
            }
            if ((i & 4) != 0) {
                z = typeParameterData.isReified;
            }
            if ((i & 8) != 0) {
                list = typeParameterData.boundProviders;
            }
            if ((i & 16) != 0) {
                generatedDeclarationKey = typeParameterData.key;
            }
            GeneratedDeclarationKey generatedDeclarationKey2 = generatedDeclarationKey;
            boolean z2 = z;
            return typeParameterData.copy(name, variance, z2, list, generatedDeclarationKey2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Name getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Variance getVariance() {
            return this.variance;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReified() {
            return this.isReified;
        }

        public final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> component4() {
            return this.boundProviders;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final GeneratedDeclarationKey getKey() {
            return this.key;
        }

        public final TypeParameterData copy(Name name, Variance variance, boolean isReified, List<? extends Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>> boundProviders, GeneratedDeclarationKey key) {
            name.getClass();
            variance.getClass();
            boundProviders.getClass();
            key.getClass();
            return new TypeParameterData(name, variance, isReified, boundProviders, key);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeParameterData)) {
                return false;
            }
            TypeParameterData typeParameterData = (TypeParameterData) other;
            return Intrinsics.areEqual(this.name, typeParameterData.name) && this.variance == typeParameterData.variance && this.isReified == typeParameterData.isReified && Intrinsics.areEqual(this.boundProviders, typeParameterData.boundProviders) && Intrinsics.areEqual(this.key, typeParameterData.key);
        }

        public final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> getBoundProviders() {
            return this.boundProviders;
        }

        public final GeneratedDeclarationKey getKey() {
            return this.key;
        }

        public final Name getName() {
            return this.name;
        }

        public final Variance getVariance() {
            return this.variance;
        }

        public int hashCode() {
            return (((((((this.name.hashCode() * 31) + this.variance.hashCode()) * 31) + Boolean.hashCode(this.isReified)) * 31) + this.boundProviders.hashCode()) * 31) + this.key.hashCode();
        }

        public final boolean isReified() {
            return this.isReified;
        }

        public String toString() {
            return "TypeParameterData(name=" + this.name + ", variance=" + this.variance + ", isReified=" + this.isReified + ", boundProviders=" + this.boundProviders + ", key=" + this.key + ')';
        }
    }

    private DeclarationBuildingContext(FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol<?> firClassSymbol) {
        this.session = firSession;
        this.key = generatedDeclarationKey;
        this.owner = firClassSymbol;
        this.visibility = Visibilities.Public.INSTANCE;
        this.modality = Modality.FINAL;
        this.contextReceiverTypeProviders = new ArrayList();
        this.typeParameters = new ArrayList();
        this.statusConfigs = new ArrayList();
        this._source = DEFAULT_SOURCE_ELEMENT_STUB;
    }

    public static Unit a(TypeParameterBuildingContext typeParameterBuildingContext) {
        typeParameterBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static ConeKotlinType b(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void typeParameter$default(DeclarationBuildingContext declarationBuildingContext, Name name, Variance variance, boolean z, GeneratedDeclarationKey generatedDeclarationKey, Function1 function1, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: typeParameter");
            return;
        }
        if ((i & 2) != 0) {
            variance = Variance.INVARIANT;
        }
        Variance variance2 = variance;
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            generatedDeclarationKey = declarationBuildingContext.key;
        }
        GeneratedDeclarationKey generatedDeclarationKey2 = generatedDeclarationKey;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: tc3
                public final Object invoke(Object obj2) {
                    return DeclarationBuildingContext.a((DeclarationBuildingContext.TypeParameterBuildingContext) obj2);
                }
            };
        }
        declarationBuildingContext.typeParameter(name, variance2, z2, generatedDeclarationKey2, function1);
    }

    public abstract T build();

    public void contextReceiver(final ConeKotlinType type) {
        type.getClass();
        contextReceiver(new Function1() { // from class: sc3
            public final Object invoke(Object obj) {
                return DeclarationBuildingContext.b(type, (List) obj);
            }
        });
    }

    public final FirExpression generateExpressionStub() {
        FirThrowExpressionBuilder firThrowExpressionBuilder = new FirThrowExpressionBuilder();
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        ConeClassLikeType coneType = this.session.getBuiltinTypes().getThrowableType().getConeType();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneType, this.session);
        regularClassSymbol.getClass();
        for (FirConstructorSymbol firConstructorSymbol : DeclarationUtilsKt.constructors(regularClassSymbol, this.session)) {
            if (firConstructorSymbol.getValueParameterSymbols().size() == 1 && ConeBuiltinTypeUtilsKt.isNullableString(((FirValueParameterSymbol) CollectionsKt.first(firConstructorSymbol.getValueParameterSymbols())).getResolvedReturnTypeRef().getConeType())) {
                firFunctionCallBuilder.setConeTypeOrNull(coneType);
                FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
                firResolvedNamedReferenceBuilder.setName(firConstructorSymbol.getName());
                firResolvedNamedReferenceBuilder.setResolvedSymbol(firConstructorSymbol);
                firFunctionCallBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
                FirLiteralExpression firLiteralExpressionBuildLiteralExpression$default = FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.String.INSTANCE, "Stub for declaration generated by " + FirDeclarationOriginKt.getOrigin(this.key), null, true, null, 40, null);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(firLiteralExpressionBuildLiteralExpression$default, ((FirValueParameterSymbol) CollectionsKt.first(firConstructorSymbol.getValueParameterSymbols())).getFir());
                Unit unit = Unit.INSTANCE;
                firFunctionCallBuilder.setArgumentList(FirArgumentUtilKt.buildResolvedArgumentList(null, linkedHashMap));
                firThrowExpressionBuilder.setException(firFunctionCallBuilder.mo288build());
                return firThrowExpressionBuilder.mo288build();
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    public final FirResolvedDeclarationStatusImpl generateStatus() {
        Visibility visibility = this.visibility;
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(visibility, this.modality, EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility, (FirClassLikeSymbol) this.owner, true, false, 4, (Object) null));
        Iterator<Function1<FirResolvedDeclarationStatusImpl, Unit>> it = this.statusConfigs.iterator();
        while (it.hasNext()) {
            it.next().invoke(firResolvedDeclarationStatusImpl);
        }
        return firResolvedDeclarationStatusImpl;
    }

    public final FirTypeParameter generateTypeParameter(TypeParameterData typeParameter, FirBasedSymbol<?> containingDeclarationSymbol) {
        typeParameter.getClass();
        containingDeclarationSymbol.getClass();
        FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
        firTypeParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firTypeParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        firTypeParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(typeParameter.getKey()));
        firTypeParameterBuilder.setName(typeParameter.getName());
        firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
        firTypeParameterBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
        firTypeParameterBuilder.setVariance(typeParameter.getVariance());
        firTypeParameterBuilder.setReified(typeParameter.isReified());
        return firTypeParameterBuilder.mo288build();
    }

    public final GeneratedDeclarationKey getKey() {
        return this.key;
    }

    public final Modality getModality() {
        return this.modality;
    }

    public final FirClassSymbol<?> getOwner() {
        return this.owner;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final KtSourceElement getSource() {
        Object obj = this._source;
        if (obj instanceof KtSourceElement) {
            return (KtSourceElement) obj;
        }
        return null;
    }

    public final KtSourceElement getSourceForFirDeclaration() {
        KtSourceElement source;
        Object obj = this._source;
        if (obj != DEFAULT_SOURCE_ELEMENT_STUB) {
            return (KtSourceElement) obj;
        }
        FirClassSymbol<?> firClassSymbol = this.owner;
        if (firClassSymbol == null || (source = firClassSymbol.getSource()) == null) {
            return null;
        }
        return KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.PluginGenerated.INSTANCE, null, 2, null);
    }

    public final List<TypeParameterData> getTypeParameters() {
        return this.typeParameters;
    }

    public final Visibility getVisibility() {
        return this.visibility;
    }

    public final void initTypeParameterBounds(List<? extends FirTypeParameterRef> allParameters, List<? extends FirTypeParameter> ownTypeParameters) {
        List<? extends FirTypeRef> arrayList;
        allParameters.getClass();
        ownTypeParameters.getClass();
        for (Pair pair : CollectionsKt.zip(ownTypeParameters, this.typeParameters)) {
            FirTypeParameter firTypeParameter = (FirTypeParameter) pair.component1();
            List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> boundProviders = ((TypeParameterData) pair.component2()).getBoundProviders();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(boundProviders, 10));
            Iterator<T> it = boundProviders.iterator();
            while (it.hasNext()) {
                arrayList2.add((ConeKotlinType) ((Function1) it.next()).invoke(allParameters));
            }
            if (arrayList2.isEmpty()) {
                arrayList = CollectionsKt.listOf(this.session.getBuiltinTypes().getNullableAnyType());
            } else {
                arrayList = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    arrayList.add(UtilsKt.toFirResolvedTypeRef$default((ConeKotlinType) it2.next(), null, null, 3, null));
                }
            }
            firTypeParameter.replaceBounds(arrayList);
        }
    }

    public final void produceContextReceiversTo(List<FirValueParameter> destination, List<? extends FirTypeParameterRef> typeParameters, FirDeclarationOrigin origin, FirCallableSymbol<?> containingDeclarationSymbol) {
        destination.getClass();
        typeParameters.getClass();
        origin.getClass();
        containingDeclarationSymbol.getClass();
        List<FirValueParameter> list = destination;
        Iterator<T> it = this.contextReceiverTypeProviders.iterator();
        while (it.hasNext()) {
            Function1 function1 = (Function1) it.next();
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
            firValueParameterBuilder.setOrigin(origin);
            firValueParameterBuilder.setName(SpecialNames.UNDERSCORE_FOR_UNUSED_VAR);
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default((ConeKotlinType) function1.invoke(typeParameters), null, null, 3, null));
            firValueParameterBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
            firValueParameterBuilder.setValueParameterKind(FirValueParameterKind.ContextParameter);
            list.add(firValueParameterBuilder.mo288build());
        }
    }

    public final void setModality(Modality modality) {
        modality.getClass();
        this.modality = modality;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this._source = ktSourceElement;
    }

    public final void setVisibility(Visibility visibility) {
        visibility.getClass();
        this.visibility = visibility;
    }

    public final void status(Function1<? super FirResolvedDeclarationStatusImpl, Unit> statusConfig) {
        statusConfig.getClass();
        this.statusConfigs.add(statusConfig);
    }

    public void typeParameter(Name name, Variance variance, boolean isReified, GeneratedDeclarationKey key, Function1<? super TypeParameterBuildingContext, Unit> config) {
        name.getClass();
        variance.getClass();
        key.getClass();
        config.getClass();
        List<TypeParameterData> list = this.typeParameters;
        TypeParameterBuildingContext typeParameterBuildingContext = new TypeParameterBuildingContext();
        config.invoke(typeParameterBuildingContext);
        list.add(new TypeParameterData(name, variance, isReified, typeParameterBuildingContext.getBoundProviders$org_jetbrains_kotlin_plugin_utils(), key));
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT_SOURCE_ELEMENT_STUB", "getDEFAULT_SOURCE_ELEMENT_STUB", "()Ljava/lang/Object;", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object getDEFAULT_SOURCE_ELEMENT_STUB() {
            return DeclarationBuildingContext.DEFAULT_SOURCE_ELEMENT_STUB;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J \u0010\u0004\u001a\u00020\u00052\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u00070\tR,\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u00070\t0\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext$TypeParameterBuildingContext;", Argument.Delimiters.none, "<init>", "()V", "bound", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeProvider", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "boundProviders", Argument.Delimiters.none, "getBoundProviders$org_jetbrains_kotlin_plugin_utils", "()Ljava/util/List;", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TypeParameterBuildingContext {
        private final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> boundProviders = new ArrayList();

        public static ConeKotlinType a(ConeKotlinType coneKotlinType, List list) {
            list.getClass();
            return coneKotlinType;
        }

        public final void bound(final ConeKotlinType type) {
            type.getClass();
            bound(new Function1() { // from class: uc3
                public final Object invoke(Object obj) {
                    return DeclarationBuildingContext.TypeParameterBuildingContext.a(type, (List) obj);
                }
            });
        }

        public final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> getBoundProviders$org_jetbrains_kotlin_plugin_utils() {
            return this.boundProviders;
        }

        public final void bound(Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> typeProvider) {
            typeProvider.getClass();
            this.boundProviders.add(typeProvider);
        }
    }

    public void contextReceiver(Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> typeProvider) {
        typeProvider.getClass();
        this.contextReceiverTypeProviders.add(typeProvider);
    }

    public /* synthetic */ DeclarationBuildingContext(FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol firClassSymbol, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, generatedDeclarationKey, firClassSymbol);
    }
}
