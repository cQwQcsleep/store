package org.jetbrains.kotlin.fir.backend;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.actualizer.ClassActualizationInfo;
import org.jetbrains.kotlin.backend.common.actualizer.SpecialFakeOverrideSymbolsResolver;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrDelegatedMembersGenerationStrategy;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionsKt;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrScriptSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol;
import org.jetbrains.kotlin.ir.types.AbstractIrTypeSubstitutor;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypeSubstitutor;
import org.jetbrains.kotlin.ir.types.IrTypeSubstitutorKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrFakeOverrideUtilsKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u000278BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u0016H\u0002J\u0006\u0010\u001a\u001a\u00020\u0014J4\u0010\u001b\u001a\u00020\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J0\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J8\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u0002002\u0006\u0010*\u001a\u0002002\u0006\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00101\u001a\u000202H\u0002J8\u00103\u001a\u0002042\u0006\u0010+\u001a\u00020\n2\u0006\u0010*\u001a\u0002002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00101\u001a\u0002022\u0006\u0010/\u001a\u0002002\u0006\u0010,\u001a\u00020-H\u0002J\f\u00105\u001a\u000206*\u000206H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDelegatedMembersGenerationStrategy;", Argument.Delimiters.none, "irFactory", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "irBuiltins", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "fir2IrExtensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "delegatedClassesInfo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "classActualizationInfo", "Lorg/jetbrains/kotlin/backend/common/actualizer/ClassActualizationInfo;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrFactory;Lorg/jetbrains/kotlin/ir/IrBuiltIns;Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;Ljava/util/Map;Lorg/jetbrains/kotlin/backend/common/actualizer/ClassActualizationInfo;)V", "delegatedInfos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/DelegatedMemberInfo;", "convertFakeOverrideToDelegateIfNeeded", Argument.Delimiters.none, "overridableMember", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "updateDeclarationHeader", "generateDelegatedBodies", "updateMetadataSources", "delegatedClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "symbolResolver", "Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;", "generateDelegatedPropertyBody", "delegatedProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "delegateTargetFromBaseType", "classSymbolOfDelegateField", "delegateField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "generateDelegatedFunctionBody", "delegatedFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "kind", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDelegatedMembersGenerationStrategy$Kind;", "extractDelegatedFunctionBodyInfo", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDelegatedMembersGenerationStrategy$DelegatedFunctionBodyInfo;", "unwrapTypeParameterType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "Kind", "DelegatedFunctionBodyInfo", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrDelegatedMembersGenerationStrategy {
    private final Map<IrClassSymbol, Map<IrClassSymbol, IrFieldSymbol>> delegatedClassesInfo;
    private final List<DelegatedMemberInfo> delegatedInfos;
    private final Fir2IrExtensions fir2IrExtensions;
    private final IrBuiltIns irBuiltins;
    private final IrFactory irFactory;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDelegatedMembersGenerationStrategy$DelegatedFunctionBodyInfo;", Argument.Delimiters.none, "delegateTargetFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "substitutor", "Lorg/jetbrains/kotlin/ir/types/AbstractIrTypeSubstitutor;", "delegatingToMethodOfSupertype", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/types/AbstractIrTypeSubstitutor;Z)V", "getDelegateTargetFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getSubstitutor", "()Lorg/jetbrains/kotlin/ir/types/AbstractIrTypeSubstitutor;", "getDelegatingToMethodOfSupertype", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DelegatedFunctionBodyInfo {
        private final IrSimpleFunction delegateTargetFunction;
        private final boolean delegatingToMethodOfSupertype;
        private final AbstractIrTypeSubstitutor substitutor;

        public DelegatedFunctionBodyInfo(IrSimpleFunction irSimpleFunction, AbstractIrTypeSubstitutor abstractIrTypeSubstitutor, boolean z) {
            irSimpleFunction.getClass();
            abstractIrTypeSubstitutor.getClass();
            this.delegateTargetFunction = irSimpleFunction;
            this.substitutor = abstractIrTypeSubstitutor;
            this.delegatingToMethodOfSupertype = z;
        }

        public static /* synthetic */ DelegatedFunctionBodyInfo copy$default(DelegatedFunctionBodyInfo delegatedFunctionBodyInfo, IrSimpleFunction irSimpleFunction, AbstractIrTypeSubstitutor abstractIrTypeSubstitutor, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                irSimpleFunction = delegatedFunctionBodyInfo.delegateTargetFunction;
            }
            if ((i & 2) != 0) {
                abstractIrTypeSubstitutor = delegatedFunctionBodyInfo.substitutor;
            }
            if ((i & 4) != 0) {
                z = delegatedFunctionBodyInfo.delegatingToMethodOfSupertype;
            }
            return delegatedFunctionBodyInfo.copy(irSimpleFunction, abstractIrTypeSubstitutor, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrSimpleFunction getDelegateTargetFunction() {
            return this.delegateTargetFunction;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AbstractIrTypeSubstitutor getSubstitutor() {
            return this.substitutor;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getDelegatingToMethodOfSupertype() {
            return this.delegatingToMethodOfSupertype;
        }

        public final DelegatedFunctionBodyInfo copy(IrSimpleFunction delegateTargetFunction, AbstractIrTypeSubstitutor substitutor, boolean delegatingToMethodOfSupertype) {
            delegateTargetFunction.getClass();
            substitutor.getClass();
            return new DelegatedFunctionBodyInfo(delegateTargetFunction, substitutor, delegatingToMethodOfSupertype);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DelegatedFunctionBodyInfo)) {
                return false;
            }
            DelegatedFunctionBodyInfo delegatedFunctionBodyInfo = (DelegatedFunctionBodyInfo) other;
            return Intrinsics.areEqual(this.delegateTargetFunction, delegatedFunctionBodyInfo.delegateTargetFunction) && Intrinsics.areEqual(this.substitutor, delegatedFunctionBodyInfo.substitutor) && this.delegatingToMethodOfSupertype == delegatedFunctionBodyInfo.delegatingToMethodOfSupertype;
        }

        public final IrSimpleFunction getDelegateTargetFunction() {
            return this.delegateTargetFunction;
        }

        public final boolean getDelegatingToMethodOfSupertype() {
            return this.delegatingToMethodOfSupertype;
        }

        public final AbstractIrTypeSubstitutor getSubstitutor() {
            return this.substitutor;
        }

        public int hashCode() {
            return (((this.delegateTargetFunction.hashCode() * 31) + this.substitutor.hashCode()) * 31) + Boolean.hashCode(this.delegatingToMethodOfSupertype);
        }

        public String toString() {
            return "DelegatedFunctionBodyInfo(delegateTargetFunction=" + this.delegateTargetFunction + ", substitutor=" + this.substitutor + ", delegatingToMethodOfSupertype=" + this.delegatingToMethodOfSupertype + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDelegatedMembersGenerationStrategy$Kind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Function", "Getter", "Setter", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Kind {
        Function,
        Getter,
        Setter;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Kind.values().length];
            try {
                iArr[Kind.Function.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Kind.Getter.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Kind.Setter.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Fir2IrDelegatedMembersGenerationStrategy(IrFactory irFactory, IrBuiltIns irBuiltIns, Fir2IrExtensions fir2IrExtensions, Map<IrClassSymbol, ? extends Map<IrClassSymbol, ? extends IrFieldSymbol>> map, ClassActualizationInfo classActualizationInfo) {
        IrSymbol actualWithoutExpansion;
        IrClass owner;
        irFactory.getClass();
        irBuiltIns.getClass();
        fir2IrExtensions.getClass();
        map.getClass();
        this.irFactory = irFactory;
        this.irBuiltins = irBuiltIns;
        this.fir2IrExtensions = fir2IrExtensions;
        this.delegatedInfos = new ArrayList();
        Map map2 = map;
        if (classActualizationInfo != null) {
            Map linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Map map3 = (Map) entry.getValue();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map3.size()));
                for (Map.Entry entry2 : map3.entrySet()) {
                    IrClassSymbol classOrFail = (IrClassSymbol) entry2.getKey();
                    ClassId classId = AdditionalIrUtilsKt.getClassId(classOrFail.getOwner());
                    if (classId != null && (actualWithoutExpansion = classActualizationInfo.getActualWithoutExpansion(classId)) != null && (owner = actualWithoutExpansion.getOwner()) != null) {
                        if (owner instanceof IrClass) {
                            classOrFail = owner.getSymbol();
                        } else {
                            if (!(owner instanceof IrTypeAlias)) {
                                f2f.a("Unexpected declaration: ", RenderIrElementKt.render$default((IrElement) owner, (DumpIrTreeOptions) null, 1, (Object) null));
                                throw null;
                            }
                            classOrFail = IrTypesKt.getClassOrFail(((IrTypeAlias) owner).getExpandedType());
                        }
                    }
                    linkedHashMap2.put(classOrFail, entry2.getValue());
                }
                linkedHashMap.put(key, linkedHashMap2);
            }
            map2 = linkedHashMap;
        }
        this.delegatedClassesInfo = map2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static Unit a(ConeClassLikeLookupTag coneClassLikeLookupTag, Fir2IrDeclarationStorage fir2IrDeclarationStorage, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver, FirVariableSymbol firVariableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrSimpleFunction setter;
        IrSimpleFunction getter;
        firVariableSymbol.getClass();
        if (ClassMembersKt.isDelegated(firVariableSymbol) && Intrinsics.areEqual(ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firVariableSymbol), coneClassLikeLookupTag) && (firVariableSymbol instanceof FirPropertySymbol)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firVariableSymbol.getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                        originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                    }
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol");
                return null;
            }
            IrPropertySymbol irPropertySymbol = fir2IrDeclarationStorage.getIrPropertySymbol((FirPropertySymbol) symbol, coneClassLikeLookupTag);
            if (irPropertySymbol == null) {
                return Unit.INSTANCE;
            }
            IrProperty owner = specialFakeOverrideSymbolsResolver.getReferencedProperty(irPropertySymbol).getOwner();
            FirProperty firProperty = (FirProperty) ((FirPropertySymbol) firVariableSymbol).getFir();
            owner.setMetadata(new FirMetadataSource.Property(firProperty));
            FirPropertyAccessor getter2 = firProperty.getGetter();
            if (getter2 != null && (getter = owner.getGetter()) != null) {
                getter.setMetadata(new FirMetadataSource.Function(getter2));
            }
            FirPropertyAccessor setter2 = firProperty.getSetter();
            if (setter2 != null && (setter = owner.getSetter()) != null) {
                setter.setMetadata(new FirMetadataSource.Function(setter2));
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(ConeClassLikeLookupTag coneClassLikeLookupTag, Fir2IrDeclarationStorage fir2IrDeclarationStorage, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (ClassMembersKt.isDelegated(firNamedFunctionSymbol) && Intrinsics.areEqual(ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firNamedFunctionSymbol), coneClassLikeLookupTag)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol.getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                        originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                    }
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol != null) {
                specialFakeOverrideSymbolsResolver.getReferencedFunction(Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrDeclarationStorage, (FirNamedFunctionSymbol) symbol, coneClassLikeLookupTag, false, 4, null)).getOwner().setMetadata(new FirMetadataSource.Function((FirFunction) firNamedFunctionSymbol.getFir()));
                return Unit.INSTANCE;
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit convertFakeOverrideToDelegateIfNeeded$lambda$2$0$0(IrFieldSymbol irFieldSymbol, IrOverridableDeclaration irOverridableDeclaration, ExceptionAttachmentBuilder exceptionAttachmentBuilder) {
        exceptionAttachmentBuilder.getClass();
        exceptionAttachmentBuilder.withEntry("delegate field", RenderIrElementKt.render$default(irFieldSymbol.getOwner(), (DumpIrTreeOptions) null, 1, (Object) null));
        exceptionAttachmentBuilder.withEntry("supertype member", RenderIrElementKt.render$default(irOverridableDeclaration, (DumpIrTreeOptions) null, 1, (Object) null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0060 A[PHI: r1
      0x0060: PHI (r1v10 org.jetbrains.kotlin.ir.declarations.IrSimpleFunction) = 
      (r1v6 org.jetbrains.kotlin.ir.declarations.IrSimpleFunction)
      (r1v13 org.jetbrains.kotlin.ir.declarations.IrSimpleFunction)
      (r1v17 org.jetbrains.kotlin.ir.declarations.IrSimpleFunction)
     binds: [B:33:0x005d, B:27:0x0052, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:64:0x0066 A[SYNTHETIC] */
    private final DelegatedFunctionBodyInfo extractDelegatedFunctionBodyInfo(IrClassSymbol classSymbolOfDelegateField, IrSimpleFunction delegateTargetFromBaseType, IrClass parent, Kind kind, IrSimpleFunction delegatedFunction, IrField delegateField) {
        IrSimpleFunction getter;
        List typeParameters;
        Iterator it = classSymbolOfDelegateField.getOwner().getDeclarations().iterator();
        do {
            if (!it.hasNext()) {
                getter = null;
                break;
            }
            IrProperty irProperty = (IrDeclaration) it.next();
            int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            if (i == 1) {
                getter = irProperty instanceof IrSimpleFunction ? (IrSimpleFunction) irProperty : null;
                if (getter != null) {
                    if (AdditionalIrUtilsKt.overrides(getter, delegateTargetFromBaseType)) {
                    }
                }
            } else if (i == 2) {
                IrProperty irProperty2 = irProperty instanceof IrProperty ? irProperty : null;
                if (irProperty2 != null && (getter = irProperty2.getGetter()) != null) {
                    if (AdditionalIrUtilsKt.overrides(getter, delegateTargetFromBaseType)) {
                    }
                }
            } else {
                if (i != 3) {
                    bu8.a();
                    return null;
                }
                IrProperty irProperty3 = irProperty instanceof IrProperty ? irProperty : null;
                if (irProperty3 != null && (getter = irProperty3.getSetter()) != null) {
                    if (AdditionalIrUtilsKt.overrides(getter, delegateTargetFromBaseType)) {
                    }
                }
            }
            getter = null;
        } while (getter == null);
        if (getter == null || (typeParameters = getter.getTypeParameters()) == null || typeParameters.size() != delegatedFunction.getTypeParameters().size()) {
            AbstractIrTypeSubstitutor abstractIrTypeSubstitutorForSuperClass = AbstractIrTypeSubstitutor.Companion.forSuperClass(IrUtilsKt.getParentAsClass(delegateTargetFromBaseType).getSymbol(), parent.getSymbol());
            if (abstractIrTypeSubstitutorForSuperClass != null) {
                return new DelegatedFunctionBodyInfo(delegateTargetFromBaseType, abstractIrTypeSubstitutorForSuperClass, true);
            }
            i37.a(IrUtilsKt.getParentAsClass(delegateTargetFromBaseType), " is not super-class of ", parent);
            return null;
        }
        if (getter == null) {
            w01.a("Required value was null.");
            return null;
        }
        if (!Intrinsics.areEqual(classSymbolOfDelegateField, IrUtilsKt.getParentAsClass(getter).getSymbol())) {
            w01.a("Failed requirement.");
            return null;
        }
        IrSimpleType irSimpleTypeUnwrapTypeParameterType = unwrapTypeParameterType(delegateField.getType());
        irSimpleTypeUnwrapTypeParameterType.getClass();
        return new DelegatedFunctionBodyInfo(getter, AbstractIrTypeSubstitutor.Companion.forType(irSimpleTypeUnwrapTypeParameterType), false);
    }

    private final void generateDelegatedFunctionBody(IrSimpleFunction delegatedFunction, IrSimpleFunction delegateTargetFromBaseType, IrClassSymbol classSymbolOfDelegateField, IrField delegateField, IrClass parent, Kind kind) {
        IrTypeSubstitutor irTypeSubstitutor;
        DelegatedFunctionBodyInfo delegatedFunctionBodyInfoExtractDelegatedFunctionBodyInfo = extractDelegatedFunctionBodyInfo(classSymbolOfDelegateField, delegateTargetFromBaseType, parent, kind, delegatedFunction, delegateField);
        IrSimpleFunction delegateTargetFunction = delegatedFunctionBodyInfoExtractDelegatedFunctionBodyInfo.getDelegateTargetFunction();
        AbstractIrTypeSubstitutor substitutor = delegatedFunctionBodyInfoExtractDelegatedFunctionBodyInfo.getSubstitutor();
        boolean delegatingToMethodOfSupertype = delegatedFunctionBodyInfoExtractDelegatedFunctionBodyInfo.getDelegatingToMethodOfSupertype();
        if (delegatedFunction.getTypeParameters().isEmpty()) {
            irTypeSubstitutor = null;
        } else {
            List typeParameters = delegateTargetFunction.getTypeParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
            Iterator it = typeParameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((IrTypeParameter) it.next()).getSymbol());
            }
            List typeParameters2 = delegatedFunction.getTypeParameters();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters2, 10));
            Iterator it2 = typeParameters2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(IrTypesKt.getDefaultType((IrTypeParameter) it2.next()));
            }
            irTypeSubstitutor = new IrTypeSubstitutor(arrayList, arrayList2, true);
        }
        IrTypeOperatorCall irTypeOperatorCallIrCallImpl$default = BuildersKt.IrCallImpl$default(-2, -2, IrTypeSubstitutorKt.chainedWith(substitutor, irTypeSubstitutor).substitute(delegateTargetFunction.getReturnType()), delegateTargetFunction.getSymbol(), delegatedFunction.getTypeParameters().size(), (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        IrValueParameter dispatchReceiverParameter = delegatedFunction.getDispatchReceiverParameter();
        dispatchReceiverParameter.getClass();
        IrExpression irExpressionIrGetFieldImpl$default = BuildersKt.IrGetFieldImpl$default(-2, -2, delegateField.getSymbol(), delegateField.getType(), BuildersKt.IrGetValueImpl$default(-2, -2, dispatchReceiverParameter.getType(), dispatchReceiverParameter.getSymbol(), (IrStatementOrigin) null, 16, (Object) null), (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        if (delegatingToMethodOfSupertype) {
            IrType irTypeSubstitute = substitutor.substitute(IrUtilsKt.getDefaultType(IrUtilsKt.getParentAsClass(delegateTargetFromBaseType)));
            if (!IrTypeUtilsKt.isSubtypeOfClass(delegateField.getType(), IrTypesKt.getClassOrFail(irTypeSubstitute))) {
                irExpressionIrGetFieldImpl$default = IrExpressionsKt.implicitCastTo(irExpressionIrGetFieldImpl$default, irTypeSubstitute);
            }
        }
        IrMemberAccessExpression.ValueArgumentsList arguments = irTypeOperatorCallIrCallImpl$default.getArguments();
        List listListOf = CollectionsKt.listOf(irExpressionIrGetFieldImpl$default);
        List<IrValueParameter> nonDispatchParameters = IrUtilsKt.getNonDispatchParameters(delegatedFunction);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(nonDispatchParameters, 10));
        for (IrValueParameter irValueParameter : nonDispatchParameters) {
            arrayList3.add(BuildersKt.IrGetValueImpl$default(-2, -2, irValueParameter.getType(), irValueParameter.getSymbol(), (IrStatementOrigin) null, 16, (Object) null));
        }
        AddToStdlibKt.assignFrom(arguments, CollectionsKt.plus(listListOf, arrayList3));
        int size = delegatedFunction.getTypeParameters().size();
        for (int i = 0; i < size; i++) {
            irTypeOperatorCallIrCallImpl$default.getTypeArguments().set(i, IrSimpleTypeImplKt.IrSimpleTypeImpl(((IrTypeParameter) delegatedFunction.getTypeParameters().get(i)).getSymbol(), false, CollectionsKt.emptyList(), CollectionsKt.emptyList()));
        }
        IrType returnType = delegateTargetFunction.getReturnType();
        StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
        if ((IrUtilsKt.hasAnnotation(returnType, standardClassIds$Annotations.getFlexibleNullability()) || IrUtilsKt.hasAnnotation(returnType, standardClassIds$Annotations.getEnhancedNullability())) && !IrTypePredicatesKt.canBeNull(delegatedFunction.getReturnType())) {
            irTypeOperatorCallIrCallImpl$default = Fir2IrImplicitCastInserter.INSTANCE.implicitNotNullCast(irTypeOperatorCallIrCallImpl$default);
        }
        IrTypeOperatorCall irTypeOperatorCall = irTypeOperatorCallIrCallImpl$default;
        IrBlockBody irBlockBodyCreateBlockBody = this.irFactory.createBlockBody(-2, -2);
        if (AdditionalIrUtilsKt.isSetter(delegatedFunction) || IrTypePredicatesKt.isUnit(delegatedFunction.getReturnType()) || IrTypePredicatesKt.isNothing(delegatedFunction.getReturnType())) {
            irBlockBodyCreateBlockBody.getStatements().add(irTypeOperatorCall);
        } else {
            irBlockBodyCreateBlockBody.getStatements().add(new IrReturnImpl(-2, -2, this.irBuiltins.getNothingType(), delegatedFunction.getSymbol(), irTypeOperatorCall));
        }
        delegatedFunction.setBody(irBlockBodyCreateBlockBody);
    }

    private final void generateDelegatedPropertyBody(IrProperty delegatedProperty, IrProperty delegateTargetFromBaseType, IrClassSymbol classSymbolOfDelegateField, IrField delegateField, IrClass parent) {
        delegatedProperty.setAnnotations(CollectionsKt.emptyList());
        IrSimpleFunction getter = delegatedProperty.getGetter();
        if (getter != null) {
            IrSimpleFunction getter2 = delegateTargetFromBaseType.getGetter();
            getter2.getClass();
            generateDelegatedFunctionBody(getter, getter2, classSymbolOfDelegateField, delegateField, parent, Kind.Getter);
        }
        IrSimpleFunction setter = delegatedProperty.getSetter();
        if (setter != null) {
            IrSimpleFunction setter2 = delegateTargetFromBaseType.getSetter();
            setter2.getClass();
            generateDelegatedFunctionBody(setter, setter2, classSymbolOfDelegateField, delegateField, parent, Kind.Setter);
        }
    }

    private final IrType unwrapTypeParameterType(IrType irType) {
        IrTypeParameterSymbol classifierOrFail = IrTypesKt.getClassifierOrFail(irType);
        if (classifierOrFail instanceof IrClassSymbol) {
            return irType;
        }
        if (classifierOrFail instanceof IrTypeParameterSymbol) {
            return unwrapTypeParameterType((IrType) CollectionsKt.first(classifierOrFail.getOwner().getSuperTypes()));
        }
        if (!(classifierOrFail instanceof IrScriptSymbol)) {
            bu8.a();
            return null;
        }
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        wq6.a();
        return null;
    }

    private final void updateDeclarationHeader(IrOverridableDeclaration<?> irOverridableDeclaration) {
        irOverridableDeclaration.setStartOffset(-2);
        irOverridableDeclaration.setEndOffset(-2);
        irOverridableDeclaration.setFakeOverride(false);
        irOverridableDeclaration.setOrigin(IrDeclarationOrigin.Companion.getDELEGATED_MEMBER());
        irOverridableDeclaration.setModality(Modality.OPEN);
        if (irOverridableDeclaration instanceof IrSimpleFunction) {
            IrFunction irFunction = (IrFunction) irOverridableDeclaration;
            ((IrSimpleFunction) irOverridableDeclaration).setParameters(CollectionsKt.plus(CollectionsKt.listOf(IrUtilsKt.createDispatchReceiverParameterWithClassParent$default(irFunction, (IrDeclarationOrigin) null, 1, (Object) null)), IrUtilsKt.getNonDispatchParameters(irFunction)));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void convertFakeOverrideToDelegateIfNeeded(IrOverridableDeclaration<?> overridableMember, IrClass parent) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFieldSymbol irFieldSymbol;
        overridableMember.getClass();
        parent.getClass();
        Map<IrClassSymbol, IrFieldSymbol> map = this.delegatedClassesInfo.get(parent.getSymbol());
        if (map == null) {
            return;
        }
        List<IrFunction> listAllOverridden$default = IrUtilsKt.allOverridden$default(overridableMember, false, 1, (Object) null);
        ArrayList<Pair> arrayList = new ArrayList();
        for (IrFunction irFunction : listAllOverridden$default) {
            Pair pair = (((irFunction instanceof IrSimpleFunction) && IrUtilsKt.isFakeOverriddenFromAny(irFunction)) || (irFieldSymbol = map.get(IrUtilsKt.getParentAsClass(irFunction).getSymbol())) == null) ? null : TuplesKt.to(irFunction, irFieldSymbol);
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        if (arrayList.size() > 1) {
            HashSet hashSet = new HashSet();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (hashSet.add(IrFakeOverrideUtilsKt.resolveFakeOverride$default((IrOverridableDeclaration) ((Pair) obj).component1(), (Function1) null, 1, (Object) null))) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        }
        int size = arrayList.size();
        if (size != 0) {
            if (size != 1) {
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Too many suitable delegated supertypes for single delegated declaration", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                exceptionAttachmentBuilder.withEntry("delegated declaration", RenderIrElementKt.render$default(overridableMember, (DumpIrTreeOptions) null, 1, (Object) null));
                for (Pair pair2 : arrayList) {
                    final IrOverridableDeclaration irOverridableDeclaration = (IrOverridableDeclaration) pair2.component1();
                    final IrFieldSymbol irFieldSymbol2 = (IrFieldSymbol) pair2.component2();
                    exceptionAttachmentBuilder.withEntryGroup("matched delegate", new Function1() { // from class: nv4
                        public final Object invoke(Object obj2) {
                            return Fir2IrDelegatedMembersGenerationStrategy.convertFakeOverrideToDelegateIfNeeded$lambda$2$0$0(irFieldSymbol2, irOverridableDeclaration, (ExceptionAttachmentBuilder) obj2);
                        }
                    });
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            Pair pair3 = (Pair) CollectionsKt.single(arrayList);
            IrOverridableDeclaration<?> irOverridableDeclaration2 = (IrOverridableDeclaration) pair3.component1();
            IrFieldSymbol irFieldSymbol3 = (IrFieldSymbol) pair3.component2();
            if (this.fir2IrExtensions.shouldGenerateDelegatedMember(irOverridableDeclaration2)) {
                if (overridableMember instanceof IrSimpleFunction) {
                    updateDeclarationHeader(overridableMember);
                } else {
                    if (!(overridableMember instanceof IrProperty)) {
                        bu8.a();
                        return;
                    }
                    updateDeclarationHeader(overridableMember);
                    IrProperty irProperty = (IrProperty) overridableMember;
                    IrSimpleFunction getter = irProperty.getGetter();
                    if (getter != null) {
                        updateDeclarationHeader(getter);
                    }
                    IrSimpleFunction setter = irProperty.getSetter();
                    if (setter != null) {
                        updateDeclarationHeader(setter);
                    }
                    irProperty.setLateinit(false);
                }
                this.delegatedInfos.add(new DelegatedMemberInfo(overridableMember, irOverridableDeclaration2, irFieldSymbol3.getOwner(), parent));
            }
        }
    }

    public final void generateDelegatedBodies() {
        for (DelegatedMemberInfo delegatedMemberInfo : this.delegatedInfos) {
            IrOverridableDeclaration<?> irOverridableDeclarationComponent1 = delegatedMemberInfo.component1();
            IrOverridableDeclaration<?> irOverridableDeclarationComponent2 = delegatedMemberInfo.component2();
            IrField delegateField = delegatedMemberInfo.getDelegateField();
            IrClass parent = delegatedMemberInfo.getParent();
            IrClassSymbol classOrFail = IrTypesKt.getClassOrFail(this.unwrapTypeParameterType(delegateField.getType()));
            if (irOverridableDeclarationComponent1 instanceof IrSimpleFunction) {
                irOverridableDeclarationComponent2.getClass();
                this.generateDelegatedFunctionBody((IrSimpleFunction) irOverridableDeclarationComponent1, (IrSimpleFunction) irOverridableDeclarationComponent2, classOrFail, delegateField, parent, Kind.Function);
            } else {
                Fir2IrDelegatedMembersGenerationStrategy fir2IrDelegatedMembersGenerationStrategy = this;
                if (!(irOverridableDeclarationComponent1 instanceof IrProperty)) {
                    bu8.a();
                    return;
                } else {
                    irOverridableDeclarationComponent2.getClass();
                    fir2IrDelegatedMembersGenerationStrategy.generateDelegatedPropertyBody((IrProperty) irOverridableDeclarationComponent1, (IrProperty) irOverridableDeclarationComponent2, classOrFail, delegateField, parent);
                    this = fir2IrDelegatedMembersGenerationStrategy;
                }
            }
        }
    }

    public final void updateMetadataSources(Collection<? extends FirClass> delegatedClasses, FirSession session, ScopeSession scopeSession, final Fir2IrDeclarationStorage declarationStorage, final SpecialFakeOverrideSymbolsResolver symbolResolver) {
        delegatedClasses.getClass();
        session.getClass();
        scopeSession.getClass();
        declarationStorage.getClass();
        symbolResolver.getClass();
        for (FirClass firClass : delegatedClasses) {
            final ConeClassLikeLookupTag lookupTag = firClass.getSymbol().getLookupTag();
            FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope(firClass, session, scopeSession, true, (FirResolvePhase) null);
            FirContainingNamesAwareScopeKt.processAllFunctions(firTypeScopeUnsubstitutedScope, new Function1() { // from class: lv4
                public final Object invoke(Object obj) {
                    return Fir2IrDelegatedMembersGenerationStrategy.c(lookupTag, declarationStorage, symbolResolver, (FirNamedFunctionSymbol) obj);
                }
            });
            FirContainingNamesAwareScopeKt.processAllProperties(firTypeScopeUnsubstitutedScope, new Function1() { // from class: mv4
                public final Object invoke(Object obj) {
                    return Fir2IrDelegatedMembersGenerationStrategy.a(lookupTag, declarationStorage, symbolResolver, (FirVariableSymbol) obj);
                }
            });
        }
    }
}
