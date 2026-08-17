package org.jetbrains.kotlin.fir.backend.utils;

import com.intellij.psi.tree.IElementType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.impl.FirPropertyFromParameterResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u0004R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a0\u0010\u0006\u001a\u00020\u0001*\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u000f*\u00020\u0011H\u0000\u001a\n\u0010\u0017\u001a\u00020\u000f*\u00020\u0018\u001a\f\u0010\u0019\u001a\u0004\u0018\u00010\u000f*\u00020\u0018\u001a\u000e\u0010\u001a\u001a\u0004\u0018\u00010\u000e*\u00020\u0018H\u0002\"\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000\"*\u0010\u0015\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00140\u0013j\u0002`\u0016\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001d\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u001d\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000f0\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001e*$\b\u0002\u0010\u0012\"\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00140\u00132\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00140\u0013¨\u0006\""}, d2 = {"irOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "computeIrOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "predefinedOrigin", "parentOrigin", "fakeOverrideOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "nameToOperationConventionOrigin", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "statementOrigin", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "NameWithElementType", "Lkotlin/Pair;", "Lcom/intellij/psi/tree/IElementType;", "PREFIX_POSTFIX_ORIGIN_MAP", "Lorg/jetbrains/kotlin/fir/backend/utils/NameWithElementType;", "getIrAssignmentOrigin", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "getIrPrefixPostfixOriginIfAny", "getCallableNameFromIntClassIfAny", "augmentedAssignSourceKindToIrStatementOrigin", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", "getAugmentedAssignSourceKindToIrStatementOrigin", "()Ljava/util/Map;", "incOrDecSourceKindToIrStatementOrigin", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "getIncOrDecSourceKindToIrStatementOrigin", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OriginUtilsKt {
    private static final Map<Pair<Name, IElementType>, IrStatementOrigin> PREFIX_POSTFIX_ORIGIN_MAP;
    private static final Map<KtFakeSourceElementKind.DesugaredAugmentedAssign, IrStatementOrigin> augmentedAssignSourceKindToIrStatementOrigin;
    private static final Map<KtFakeSourceElementKind, IrStatementOrigin> incOrDecSourceKindToIrStatementOrigin;
    private static final Map<Name, IrStatementOrigin> nameToOperationConventionOrigin;

    static {
        Name name = OperatorNameConventions.PLUS;
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        nameToOperationConventionOrigin = MapsKt.mapOf(new Pair[]{TuplesKt.to(name, companion.getPLUS()), TuplesKt.to(OperatorNameConventions.MINUS, companion.getMINUS()), TuplesKt.to(OperatorNameConventions.TIMES, companion.getMUL()), TuplesKt.to(OperatorNameConventions.DIV, companion.getDIV()), TuplesKt.to(OperatorNameConventions.REM, companion.getPERC()), TuplesKt.to(OperatorNameConventions.RANGE_TO, companion.getRANGE()), TuplesKt.to(OperatorNameConventions.RANGE_UNTIL, companion.getRANGE_UNTIL()), TuplesKt.to(OperatorNameConventions.CONTAINS, companion.getIN())});
        Name name2 = OperatorNameConventions.INC;
        IElementType iElementType = KtNodeTypes.PREFIX_EXPRESSION;
        Pair pair = TuplesKt.to(TuplesKt.to(name2, iElementType), companion.getPREFIX_INCR());
        IElementType iElementType2 = KtNodeTypes.POSTFIX_EXPRESSION;
        Pair pair2 = TuplesKt.to(TuplesKt.to(name2, iElementType2), companion.getPOSTFIX_INCR());
        Name name3 = OperatorNameConventions.DEC;
        PREFIX_POSTFIX_ORIGIN_MAP = MapsKt.hashMapOf(new Pair[]{pair, pair2, TuplesKt.to(TuplesKt.to(name3, iElementType), companion.getPREFIX_DECR()), TuplesKt.to(TuplesKt.to(name3, iElementType2), companion.getPOSTFIX_DECR())});
        augmentedAssignSourceKindToIrStatementOrigin = MapsKt.mapOf(new Pair[]{TuplesKt.to(KtFakeSourceElementKind.DesugaredPlusAssign.INSTANCE, companion.getPLUSEQ()), TuplesKt.to(KtFakeSourceElementKind.DesugaredMinusAssign.INSTANCE, companion.getMINUSEQ()), TuplesKt.to(KtFakeSourceElementKind.DesugaredTimesAssign.INSTANCE, companion.getMULTEQ()), TuplesKt.to(KtFakeSourceElementKind.DesugaredDivAssign.INSTANCE, companion.getDIVEQ()), TuplesKt.to(KtFakeSourceElementKind.DesugaredRemAssign.INSTANCE, companion.getPERCEQ())});
        incOrDecSourceKindToIrStatementOrigin = MapsKt.mapOf(new Pair[]{TuplesKt.to(KtFakeSourceElementKind.DesugaredPrefixInc.INSTANCE, companion.getPREFIX_INCR()), TuplesKt.to(KtFakeSourceElementKind.DesugaredPostfixInc.INSTANCE, companion.getPOSTFIX_INCR()), TuplesKt.to(KtFakeSourceElementKind.DesugaredPrefixDec.INSTANCE, companion.getPREFIX_DECR()), TuplesKt.to(KtFakeSourceElementKind.DesugaredPostfixDec.INSTANCE, companion.getPOSTFIX_DECR()), TuplesKt.to(KtFakeSourceElementKind.DesugaredPrefixIncSecondGetReference.INSTANCE, companion.getPREFIX_INCR()), TuplesKt.to(KtFakeSourceElementKind.DesugaredPrefixDecSecondGetReference.INSTANCE, companion.getPREFIX_DECR())});
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:29:0x0073  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:62:0x00fa  */
    public static final IrDeclarationOrigin computeIrOrigin(FirDeclaration firDeclaration, IrDeclarationOrigin irDeclarationOrigin, IrDeclarationOrigin irDeclarationOrigin2, ConeClassLikeLookupTag coneClassLikeLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrDeclarationOrigin.GeneratedByPlugin fake_override;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        if (firDeclaration == null) {
            if (irDeclarationOrigin == null) {
                return irDeclarationOrigin2 == null ? IrDeclarationOrigin.Companion.getDEFINED() : irDeclarationOrigin2;
            }
            return irDeclarationOrigin;
        }
        FirDeclarationOrigin origin = firDeclaration.getOrigin();
        if (origin instanceof FirDeclarationOrigin.Plugin) {
            fake_override = new IrDeclarationOrigin.GeneratedByPlugin(((FirDeclarationOrigin.Plugin) origin).getKey());
        } else if (origin instanceof FirDeclarationOrigin.Synthetic.ReplContainerClass) {
            fake_override = IrDeclarationOrigin.Companion.getSCRIPT_CLASS();
        } else if (origin instanceof FirDeclarationOrigin.Synthetic.ReplEvalFunction) {
            fake_override = IrDeclarationOrigin.Companion.getREPL_EVAL_FUNCTION();
        } else if (origin instanceof FirDeclarationOrigin.ScriptCustomization.ResultProperty) {
            fake_override = IrDeclarationOrigin.Companion.getSCRIPT_RESULT_PROPERTY();
        } else if (firDeclaration instanceof FirValueParameter) {
            Name name = ((FirValueParameter) firDeclaration).getName();
            if (Intrinsics.areEqual(name, SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
                fake_override = IrDeclarationOrigin.Companion.getUNDERSCORE_PARAMETER();
            } else if (Intrinsics.areEqual(name, SpecialNames.DESTRUCT)) {
                fake_override = IrDeclarationOrigin.Companion.getDESTRUCTURED_OBJECT_PARAMETER();
            } else {
                fake_override = null;
            }
        } else if (!(firDeclaration instanceof FirCallableDeclaration)) {
            fake_override = null;
        } else if (coneClassLikeLookupTag == null || Intrinsics.areEqual(coneClassLikeLookupTag, ClassMembersKt.containingClassLookupTag((FirCallableDeclaration) firDeclaration))) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
            if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration) || Intrinsics.areEqual(DeprecationUtilsKt.isHiddenToOvercomeSignatureClash(firCallableDeclaration), Boolean.TRUE)) {
                fake_override = IrDeclarationOrigin.Companion.getFAKE_OVERRIDE();
            } else {
                IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
                if (Intrinsics.areEqual(irDeclarationOrigin2, companion.getIR_EXTERNAL_DECLARATION_STUB())) {
                    FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
                    FirDeclarationOrigin origin2 = symbol.getOrigin();
                    if (!(origin2 instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin2, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                        FirCallableDeclaration fir = symbol.getFir();
                        FirCallableDeclaration firCallableDeclaration2 = fir instanceof FirCallableDeclaration ? fir : null;
                        if (firCallableDeclaration2 == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration2)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                            if (firCallableDeclaration.getSymbol().getOrigin() instanceof FirDeclarationOrigin.Plugin) {
                                FirDeclarationOrigin origin3 = firCallableDeclaration.getSymbol().getOrigin();
                                origin3.getClass();
                                fake_override = new IrDeclarationOrigin.GeneratedByPlugin(((FirDeclarationOrigin.Plugin) origin3).getKey());
                            } else {
                                fake_override = null;
                            }
                        }
                    }
                    fake_override = companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB();
                } else if (firCallableDeclaration.getSymbol().getOrigin() instanceof FirDeclarationOrigin.Plugin) {
                    FirDeclarationOrigin origin4 = firCallableDeclaration.getSymbol().getOrigin();
                    origin4.getClass();
                    fake_override = new IrDeclarationOrigin.GeneratedByPlugin(((FirDeclarationOrigin.Plugin) origin4).getKey());
                } else {
                    fake_override = null;
                }
            }
        } else {
            fake_override = IrDeclarationOrigin.Companion.getFAKE_OVERRIDE();
        }
        if (fake_override != null) {
            return fake_override;
        }
        if (irDeclarationOrigin == null) {
            return irDeclarationOrigin2 == null ? IrDeclarationOrigin.Companion.getDEFINED() : irDeclarationOrigin2;
        }
        return irDeclarationOrigin;
    }

    public static /* synthetic */ IrDeclarationOrigin computeIrOrigin$default(FirDeclaration firDeclaration, IrDeclarationOrigin irDeclarationOrigin, IrDeclarationOrigin irDeclarationOrigin2, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) {
        if ((i & 1) != 0) {
            irDeclarationOrigin = null;
        }
        if ((i & 2) != 0) {
            irDeclarationOrigin2 = null;
        }
        if ((i & 4) != 0) {
            coneClassLikeLookupTag = null;
        }
        return computeIrOrigin(firDeclaration, irDeclarationOrigin, irDeclarationOrigin2, coneClassLikeLookupTag);
    }

    public static final Map<KtFakeSourceElementKind.DesugaredAugmentedAssign, IrStatementOrigin> getAugmentedAssignSourceKindToIrStatementOrigin() {
        return augmentedAssignSourceKindToIrStatementOrigin;
    }

    private static final Name getCallableNameFromIntClassIfAny(FirVariableAssignment firVariableAssignment) {
        FirCallableSymbol resolvedCallableSymbol$default;
        FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
        if (calleeReference != null && (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null)) != null) {
            FirExpression rValue = firVariableAssignment.getRValue();
            if ((rValue instanceof FirFunctionCall) && resolvedCallableSymbol$default.isLocal()) {
                FirCallableSymbol resolvedCallableSymbol$default2 = FirReferenceUtilsKt.toResolvedCallableSymbol$default(((FirFunctionCall) rValue).getCalleeReference(), false, 1, null);
                CallableId callableId = resolvedCallableSymbol$default2 != null ? resolvedCallableSymbol$default2.getCallableId() : null;
                if (Intrinsics.areEqual(callableId != null ? callableId.getClassId() : null, StandardClassIds.INSTANCE.getInt())) {
                    return callableId.getCallableName();
                }
            }
        }
        return null;
    }

    public static final Map<KtFakeSourceElementKind, IrStatementOrigin> getIncOrDecSourceKindToIrStatementOrigin() {
        return incOrDecSourceKindToIrStatementOrigin;
    }

    public static final IrStatementOrigin getIrAssignmentOrigin(FirVariableAssignment firVariableAssignment) {
        firVariableAssignment.getClass();
        Map<KtFakeSourceElementKind, IrStatementOrigin> map = incOrDecSourceKindToIrStatementOrigin;
        KtSourceElement source = firVariableAssignment.getSource();
        IrStatementOrigin irStatementOrigin = map.get(source != null ? source.getKind() : null);
        if (irStatementOrigin != null) {
            return irStatementOrigin;
        }
        Map<KtFakeSourceElementKind.DesugaredAugmentedAssign, IrStatementOrigin> map2 = augmentedAssignSourceKindToIrStatementOrigin;
        KtSourceElement source2 = firVariableAssignment.getSource();
        IrStatementOrigin irStatementOrigin2 = map2.get(source2 != null ? source2.getKind() : null);
        if (irStatementOrigin2 != null) {
            return irStatementOrigin2;
        }
        Name callableNameFromIntClassIfAny = getCallableNameFromIntClassIfAny(firVariableAssignment);
        if (callableNameFromIntClassIfAny == null) {
            return IrStatementOrigin.Companion.getEQ();
        }
        Map<Pair<Name, IElementType>, IrStatementOrigin> map3 = PREFIX_POSTFIX_ORIGIN_MAP;
        KtSourceElement source3 = firVariableAssignment.getSource();
        IrStatementOrigin irStatementOrigin3 = map3.get(TuplesKt.to(callableNameFromIntClassIfAny, source3 != null ? source3.getElementType() : null));
        if (irStatementOrigin3 != null) {
            return irStatementOrigin3;
        }
        FirExpression rValue = firVariableAssignment.getRValue();
        rValue.getClass();
        KtSourceElement source4 = ((FirFunctionCall) rValue).getSource();
        KtSourceElementKind kind = source4 != null ? source4.getKind() : null;
        if (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DesugaredPrefixInc.INSTANCE) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.DesugaredPostfixInc.INSTANCE)) {
            return IrStatementOrigin.Companion.getPLUSEQ();
        }
        return (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DesugaredPrefixDec.INSTANCE) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.DesugaredPostfixDec.INSTANCE)) ? IrStatementOrigin.Companion.getMINUSEQ() : IrStatementOrigin.Companion.getEQ();
    }

    public static final IrStatementOrigin getIrPrefixPostfixOriginIfAny(FirVariableAssignment firVariableAssignment) {
        firVariableAssignment.getClass();
        Name callableNameFromIntClassIfAny = getCallableNameFromIntClassIfAny(firVariableAssignment);
        if (callableNameFromIntClassIfAny == null) {
            return null;
        }
        Map<Pair<Name, IElementType>, IrStatementOrigin> map = PREFIX_POSTFIX_ORIGIN_MAP;
        KtSourceElement source = firVariableAssignment.getSource();
        return map.get(TuplesKt.to(callableNameFromIntClassIfAny, source != null ? source.getElementType() : null));
    }

    public static final IrDeclarationOrigin irOrigin(Fir2IrComponents fir2IrComponents, FirClass firClass) {
        fir2IrComponents.getClass();
        firClass.getClass();
        if (firClass.getOrigin() instanceof FirDeclarationOrigin.Java) {
            return IrDeclarationOrigin.Companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB();
        }
        if (fir2IrComponents.getFirProvider().getFirClassifierContainerFileIfAny(firClass.getSymbol()) != null) {
            return IrDeclarationOrigin.Companion.getDEFINED();
        }
        FirDeclarationOrigin origin = firClass.getOrigin();
        if (origin instanceof FirDeclarationOrigin.Plugin) {
            return new IrDeclarationOrigin.GeneratedByPlugin(((FirDeclarationOrigin.Plugin) origin).getKey());
        }
        return origin instanceof FirDeclarationOrigin.FromOtherReplSnippet ? IrDeclarationOrigin.Companion.getREPL_FROM_OTHER_SNIPPET() : IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB();
    }

    public static final IrStatementOrigin statementOrigin(FirReference firReference) {
        firReference.getClass();
        if (firReference instanceof FirPropertyFromParameterResolvedNamedReference) {
            return IrStatementOrigin.Companion.getINITIALIZE_PROPERTY_FROM_PARAMETER();
        }
        if (firReference instanceof FirResolvedNamedReference) {
            FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) firReference;
            FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
            if (resolvedSymbol instanceof FirSyntheticPropertySymbol) {
                return IrStatementOrigin.Companion.getGET_PROPERTY();
            }
            if (resolvedSymbol instanceof FirNamedFunctionSymbol) {
                FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) resolvedSymbol;
                if (CallableIdUtilsKt.isInvoke(firNamedFunctionSymbol.getCallableId())) {
                    return IrStatementOrigin.Companion.getINVOKE();
                }
                KtSourceElement source = firResolvedNamedReference.getSource();
                KtSourceElementKind kind = source != null ? source.getKind() : null;
                KtFakeSourceElementKind.DesugaredForLoop desugaredForLoop = KtFakeSourceElementKind.DesugaredForLoop.INSTANCE;
                if (Intrinsics.areEqual(kind, desugaredForLoop) && CallableIdUtilsKt.isIteratorNext(firNamedFunctionSymbol.getCallableId())) {
                    return IrStatementOrigin.Companion.getFOR_LOOP_NEXT();
                }
                KtSourceElement source2 = firResolvedNamedReference.getSource();
                if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, desugaredForLoop) && CallableIdUtilsKt.isIteratorHasNext(firNamedFunctionSymbol.getCallableId())) {
                    return IrStatementOrigin.Companion.getFOR_LOOP_HAS_NEXT();
                }
                KtSourceElement source3 = firResolvedNamedReference.getSource();
                if (Intrinsics.areEqual(source3 != null ? source3.getKind() : null, desugaredForLoop) && CallableIdUtilsKt.isIterator(firNamedFunctionSymbol.getCallableId())) {
                    return IrStatementOrigin.Companion.getFOR_LOOP_ITERATOR();
                }
                KtSourceElement source4 = firResolvedNamedReference.getSource();
                if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.DesugaredInvertedContains.INSTANCE)) {
                    return IrStatementOrigin.Companion.getNOT_IN();
                }
                KtSourceElement source5 = firResolvedNamedReference.getSource();
                if ((source5 != null ? source5.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement) {
                    Map<KtFakeSourceElementKind, IrStatementOrigin> map = incOrDecSourceKindToIrStatementOrigin;
                    KtSourceElement source6 = firResolvedNamedReference.getSource();
                    return map.get(source6 != null ? source6.getKind() : null);
                }
                KtSourceElement source7 = firResolvedNamedReference.getSource();
                if ((source7 != null ? source7.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredPrefixSecondGetReference) {
                    Map<KtFakeSourceElementKind, IrStatementOrigin> map2 = incOrDecSourceKindToIrStatementOrigin;
                    KtSourceElement source8 = firResolvedNamedReference.getSource();
                    return map2.get(source8 != null ? source8.getKind() : null);
                }
                KtSourceElement source9 = firResolvedNamedReference.getSource();
                if (Intrinsics.areEqual(source9 != null ? source9.getElementType() : null, KtNodeTypes.OPERATION_REFERENCE)) {
                    return nameToOperationConventionOrigin.get(firNamedFunctionSymbol.getCallableId().getCallableName());
                }
                KtSourceElement source10 = firResolvedNamedReference.getSource();
                if ((source10 != null ? source10.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredComponentFunctionCall) {
                    IrStatementOrigin.COMPONENT_N.Companion companion = IrStatementOrigin.COMPONENT_N.Companion;
                    String strAsString = firResolvedNamedReference.getName().asString();
                    strAsString.getClass();
                    return companion.withIndex(Integer.parseInt(StringsKt.removePrefix(strAsString, StandardNames.DATA_CLASS_COMPONENT_PREFIX)));
                }
                KtSourceElement source11 = firResolvedNamedReference.getSource();
                if ((source11 != null ? source11.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredAugmentedAssign) {
                    Map<KtFakeSourceElementKind.DesugaredAugmentedAssign, IrStatementOrigin> map3 = augmentedAssignSourceKindToIrStatementOrigin;
                    KtSourceElement source12 = firResolvedNamedReference.getSource();
                    return map3.get(source12 != null ? source12.getKind() : null);
                }
                KtSourceElement source13 = firResolvedNamedReference.getSource();
                if ((source13 != null ? source13.getKind() : null) instanceof KtFakeSourceElementKind.ArrayAccessNameReference) {
                    Name name = firResolvedNamedReference.getName();
                    if (Intrinsics.areEqual(name, OperatorNameConventions.GET)) {
                        return IrStatementOrigin.Companion.getGET_ARRAY_ELEMENT();
                    }
                    if (Intrinsics.areEqual(name, OperatorNameConventions.SET)) {
                        return IrStatementOrigin.Companion.getEQ();
                    }
                }
            }
        }
        return null;
    }
}
