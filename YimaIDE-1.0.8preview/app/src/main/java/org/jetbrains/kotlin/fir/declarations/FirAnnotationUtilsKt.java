package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\n\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012*\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0018\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0014\u001a\u00020\u0015*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a \u0010\u0018\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a0\u0010\u001a\u001a\u0004\u0018\u00010\u0002\"\f\b\u0000\u0010\u001b*\u00020\u0013*\u00020\u0016*\b\u0012\u0004\u0012\u0002H\u001b0\u00192\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a.\u0010\u001c\u001a\u00020\u0015\"\f\b\u0000\u0010\u001b*\u00020\u0013*\u00020\u0016*\b\u0012\u0004\u0012\u0002H\u001b0\u00192\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a0\u0010\u001d\u001a\u0004\u0018\u00010\u0002\"\f\b\u0000\u0010\u001b*\u00020\u0013*\u00020\u0016*\b\u0012\u0004\u0012\u0002H\u001b0\u00192\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0002*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\"\u0010\u001d\u001a\u0004\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a&\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u001f\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a&\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a(\u0010!\u001a\u0004\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00020\u00122\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0#2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010$\u001a\u0004\u0018\u00010%*\u00020\u00022\u0006\u0010&\u001a\u00020\u00102\b\b\u0002\u0010'\u001a\u00020\u0015\u001a[\u0010(\u001a\u0004\u0018\u00010\u0015*\u00020\u00022\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0007b6\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u001c\b-\u0012\u0018\b\u000bB\u0014\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0006\b1\u0012\u0002\b\f\u0012\n\b2\u0012\u0006\b\n0384¢\u0006\u0002\u0010)\u001a\u0019\u0010(\u001a\u0004\u0018\u00010\u0015*\u00020\u00022\u0006\u0010&\u001a\u00020\u0010¢\u0006\u0002\u00105\u001aV\u00106\u001a\u0004\u0018\u000107*\u00020\u00022\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0007b6\b*\u0012\b\b+\u0012\u0004\b\b(8\u0012\u001c\b-\u0012\u0018\b\u000bB\u0014\b.\u0012\b\b/\u0012\u0004\b\b(9\u0012\u0006\b1\u0012\u0002\b\f\u0012\n\b2\u0012\u0006\b\n0384\u001a\u0014\u00106\u001a\u0004\u0018\u000107*\u00020\u00022\u0006\u0010&\u001a\u00020\u0010\u001a$\u0010:\u001a\u0004\u0018\u0001H;\"\u0006\b\u0000\u0010;\u0018\u0001*\u00020\u00022\u0006\u0010&\u001a\u00020\u0010H\u0082\b¢\u0006\u0002\u0010<\u001a\\\u0010=\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u0012*\u00020\u00022\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0007b6\b*\u0012\b\b+\u0012\u0004\b\b(>\u0012\u001c\b-\u0012\u0018\b\u000bB\u0014\b.\u0012\b\b/\u0012\u0004\b\b(?\u0012\u0006\b1\u0012\u0002\b\f\u0012\n\b2\u0012\u0006\b\n0384\u001a\u001a\u0010=\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u0012*\u00020\u00022\u0006\u0010&\u001a\u00020\u0010\u001aV\u0010@\u001a\u0004\u0018\u00010A*\u00020\u00022\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0007b6\b*\u0012\b\b+\u0012\u0004\b\b(B\u0012\u001c\b-\u0012\u0018\b\u000bB\u0014\b.\u0012\b\b/\u0012\u0004\b\b(C\u0012\u0006\b1\u0012\u0002\b\f\u0012\n\b2\u0012\u0006\b\n0384\u001a\u0014\u0010@\u001a\u0004\u0018\u00010A*\u00020\u00022\u0006\u0010&\u001a\u00020\u0010\u001a\f\u0010D\u001a\u0004\u0018\u00010A*\u00020E\u001a\f\u0010F\u001a\u0004\u0018\u00010G*\u00020%\u001a-\u0010H\u001a\t\u0018\u0001H;¢\u0006\u0002\bI\"\n\b\u0000\u0010;\u0018\u0001*\u00020J*\u00020%2\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010K\u001a\u0010\u0010L\u001a\b\u0012\u0004\u0012\u00020%0\u0012*\u00020%\u001a\u0014\u0010Q\u001a\u00020\u00152\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010M\u001a\u00020\u0015*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bN\u0010O\"\u000e\u0010P\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"toAnnotationClassLikeType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "toAnnotationLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "toAnnotationLookupTagSafe", "toAnnotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "toAnnotationClassIdSafe", "toAnnotationClassLikeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "toAnnotationClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "sourceName", "Lorg/jetbrains/kotlin/name/Name;", "nonSourceAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "hasAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "classId", "hasAnnotationSafe", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getAnnotationWithResolvedArgumentsByClassId", "D", "hasAnnotationWithClassId", "getAnnotationByClassId", "getAnnotationsByClassId", "doesMatchesClassId", "filterOutAnnotationsByClassId", "getAnnotationByClassIds", "classIds", Argument.Delimiters.none, "findArgumentByName", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", ModuleXmlParser.NAME, "returnFirstWhenNotFound", "getBooleanArgument", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "Lkotlin/Deprecated;", "message", "Use getBooleanArgument overload without session parameter", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "getBooleanArgument(name)", "imports", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/name/Name;)Ljava/lang/Boolean;", "getStringArgument", Argument.Delimiters.none, "Use getStringArgument overload without session parameter", "getStringArgument(name)", "getPrimitiveArgumentValue", "T", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/name/Name;)Ljava/lang/Object;", "getStringArrayArgument", "Use getStringArrayArgument overload without session parameter", "getStringArrayArgument(name)", "getKClassArgument", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Use getKClassArgument overload without session parameter", "getKClassArgument(name)", "getTargetType", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "extractEnumValueArgumentInfo", "Lorg/jetbrains/kotlin/fir/declarations/EnumValueArgumentInfo;", "evaluateAs", "Lkotlin/internal/NoInfer;", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirElement;", "unwrapVarargValue", "resolved", "getResolved", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Z", "LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_CLASS_ID", "hasLowPriorityAnnotation", "annotations", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationUtilsKt {
    private static final ClassId LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_CLASS_ID;
    private static final Name sourceName;

    static {
        Name nameIdentifier = Name.identifier("SOURCE");
        nameIdentifier.getClass();
        sourceName = nameIdentifier;
        FqName fqName = new FqName("kotlin.internal");
        Name nameIdentifier2 = Name.identifier("LowPriorityInOverloadResolution");
        nameIdentifier2.getClass();
        LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_CLASS_ID = new ClassId(fqName, nameIdentifier2);
    }

    private static final boolean doesMatchesClassId(FirAnnotation firAnnotation, ClassId classId, FirSession firSession) {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default;
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        ClassId classId2 = null;
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (!(coneType instanceof ConeClassLikeType)) {
            coneType = null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
        if (coneClassLikeType != null && (coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null)) != null && (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneClassLikeTypeFullyExpandedType$default)) != null) {
            classId2 = classLikeLookupTagIfAny.getClassId();
        }
        return Intrinsics.areEqual(classId2, classId);
    }

    public static final /* synthetic */ <T extends FirElement> T evaluateAs(FirExpression firExpression, FirSession firSession) {
        firExpression.getClass();
        firSession.getClass();
        FirEvaluatorResult firEvaluatorResultEvaluateExpression = FirExpressionEvaluator.INSTANCE.evaluateExpression(firExpression, firSession);
        if (firEvaluatorResultEvaluateExpression != null) {
            if (firEvaluatorResultEvaluateExpression instanceof FirEvaluatorResult.CompileTimeException) {
                return null;
            }
            if (firEvaluatorResultEvaluateExpression instanceof FirEvaluatorResult.Evaluated) {
                T t = (T) ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluateExpression).getResult();
                Intrinsics.reifiedOperationMarker(2, "T");
                return t;
            }
        }
        return null;
    }

    public static final EnumValueArgumentInfo extractEnumValueArgumentInfo(FirExpression firExpression) {
        firExpression.getClass();
        if (!(firExpression instanceof FirPropertyAccessExpression)) {
            if (!(firExpression instanceof FirEnumEntryDeserializedAccessExpression)) {
                return null;
            }
            FirEnumEntryDeserializedAccessExpression firEnumEntryDeserializedAccessExpression = (FirEnumEntryDeserializedAccessExpression) firExpression;
            return new EnumValueArgumentInfo(firEnumEntryDeserializedAccessExpression.getEnumClassId(), firEnumEntryDeserializedAccessExpression.getEnumEntryName());
        }
        if (!FirTypeUtilsKt.getHasResolvedType(firExpression)) {
            return new EnumValueArgumentInfo(null, ((FirPropertyAccessExpression) firExpression).getCalleeReference().getName());
        }
        FirEnumEntrySymbol resolvedEnumEntrySymbol$default = FirReferenceUtilsKt.toResolvedEnumEntrySymbol$default(((FirPropertyAccessExpression) firExpression).getCalleeReference(), false, 1, null);
        if (resolvedEnumEntrySymbol$default == null) {
            return null;
        }
        ClassId classId = resolvedEnumEntrySymbol$default.getCallableId().getClassId();
        classId.getClass();
        return new EnumValueArgumentInfo(classId, resolvedEnumEntrySymbol$default.getCallableId().getCallableName());
    }

    public static final List<FirAnnotation> filterOutAnnotationsByClassId(List<? extends FirAnnotation> list, ClassId classId, FirSession firSession) {
        list.getClass();
        classId.getClass();
        firSession.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!doesMatchesClassId((FirAnnotation) obj, classId, firSession)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirExpression findArgumentByName(FirAnnotation firAnnotation, Name name, boolean z) {
        firAnnotation.getClass();
        name.getClass();
        FirExpression firExpression = firAnnotation.getArgumentMapping().getMapping().get(name);
        if (firExpression != null) {
            return firExpression;
        }
        if (!(firAnnotation instanceof FirAnnotationCall)) {
            return null;
        }
        FirCall firCall = (FirCall) firAnnotation;
        for (FirExpression firExpression2 : firCall.getArgumentList().getArguments()) {
            if (firExpression2 instanceof FirNamedArgumentExpression) {
                FirNamedArgumentExpression firNamedArgumentExpression = (FirNamedArgumentExpression) firExpression2;
                if (Intrinsics.areEqual(firNamedArgumentExpression.getName(), name)) {
                    return firNamedArgumentExpression.getExpression();
                }
            }
        }
        if (getResolved(firAnnotation) || !z) {
            return null;
        }
        return (FirExpression) CollectionsKt.firstOrNull(firCall.getArgumentList().getArguments());
    }

    public static /* synthetic */ FirExpression findArgumentByName$default(FirAnnotation firAnnotation, Name name, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return findArgumentByName(firAnnotation, name, z);
    }

    public static final FirAnnotation getAnnotationByClassId(List<? extends FirAnnotation> list, ClassId classId, FirSession firSession) {
        list.getClass();
        classId.getClass();
        firSession.getClass();
        return (FirAnnotation) CollectionsKt.firstOrNull(getAnnotationsByClassId(list, classId, firSession));
    }

    public static final FirAnnotation getAnnotationByClassIds(List<? extends FirAnnotation> list, Collection<ClassId> collection, FirSession firSession) {
        Object classId;
        Object next;
        Collection<ClassId> collection2;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default;
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        list.getClass();
        collection.getClass();
        firSession.getClass();
        Iterator<T> it = list.iterator();
        do {
            classId = null;
            if (it.hasNext()) {
                next = it.next();
                collection2 = collection;
                FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) next).getAnnotationTypeRef();
                FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
                ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                if (coneType == null) {
                    coneType = null;
                }
                if (coneType != null && (coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneType, firSession, (Function1) null, 2, (Object) null)) != null && (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinTypeFullyExpandedType$default)) != null) {
                    classId = classLikeLookupTagIfAny.getClassId();
                }
            }
            return (FirAnnotation) classId;
        } while (!CollectionsKt.contains(collection2, classId));
        classId = next;
        return (FirAnnotation) classId;
    }

    public static final <D extends FirDeclaration & FirAnnotationContainer> FirAnnotation getAnnotationWithResolvedArgumentsByClassId(FirBasedSymbol<? extends D> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        return getAnnotationByClassId(firBasedSymbol.getResolvedAnnotationsWithArguments(), classId, firSession);
    }

    public static final List<FirAnnotation> getAnnotationsByClassId(List<? extends FirAnnotation> list, ClassId classId, FirSession firSession) {
        list.getClass();
        classId.getClass();
        firSession.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (doesMatchesClassId((FirAnnotation) obj, classId, firSession)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Boolean getBooleanArgument(FirAnnotation firAnnotation, Name name) {
        firAnnotation.getClass();
        name.getClass();
        FirExpression firExpressionFindArgumentByName$default = findArgumentByName$default(firAnnotation, name, false, 2, null);
        if (firExpressionFindArgumentByName$default == null) {
            return null;
        }
        FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
        if (firLiteralExpression == null) {
            return null;
        }
        Object value = firLiteralExpression.getValue();
        return (Boolean) (value instanceof Boolean ? value : null);
    }

    public static final ConeKotlinType getKClassArgument(FirAnnotation firAnnotation, Name name) {
        firAnnotation.getClass();
        name.getClass();
        FirExpression firExpressionFindArgumentByName$default = findArgumentByName$default(firAnnotation, name, false, 2, null);
        if (firExpressionFindArgumentByName$default == null) {
            return null;
        }
        FirGetClassCall firGetClassCall = firExpressionFindArgumentByName$default instanceof FirGetClassCall ? (FirGetClassCall) firExpressionFindArgumentByName$default : null;
        if (firGetClassCall == null) {
            return null;
        }
        return getTargetType(firGetClassCall);
    }

    public static final boolean getResolved(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        if (!(firAnnotation.getAnnotationTypeRef() instanceof FirResolvedTypeRef)) {
            return false;
        }
        if (!(firAnnotation instanceof FirAnnotationCall)) {
            return true;
        }
        FirAnnotationCall firAnnotationCall = (FirAnnotationCall) firAnnotation;
        return (firAnnotationCall.getCalleeReference() instanceof FirResolvedNamedReference) || (firAnnotationCall.getCalleeReference() instanceof FirErrorNamedReference);
    }

    public static final String getStringArgument(FirAnnotation firAnnotation, Name name) {
        firAnnotation.getClass();
        name.getClass();
        FirExpression firExpressionFindArgumentByName$default = findArgumentByName$default(firAnnotation, name, false, 2, null);
        if (firExpressionFindArgumentByName$default == null) {
            return null;
        }
        FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
        if (firLiteralExpression == null) {
            return null;
        }
        Object value = firLiteralExpression.getValue();
        return (String) (value instanceof String ? value : null);
    }

    public static final List<String> getStringArrayArgument(FirAnnotation firAnnotation, Name name) {
        firAnnotation.getClass();
        name.getClass();
        FirExpression firExpressionFindArgumentByName$default = findArgumentByName$default(firAnnotation, name, false, 2, null);
        if (firExpressionFindArgumentByName$default == null) {
            return null;
        }
        FirCollectionLiteral firCollectionLiteral = firExpressionFindArgumentByName$default instanceof FirCollectionLiteral ? (FirCollectionLiteral) firExpressionFindArgumentByName$default : null;
        if (firCollectionLiteral == null) {
            return null;
        }
        List<FirExpression> arguments = firCollectionLiteral.getArgumentList().getArguments();
        ArrayList arrayList = new ArrayList();
        for (FirExpression firExpression : arguments) {
            FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            String str = value instanceof String ? (String) value : null;
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static final ConeKotlinType getTargetType(FirGetClassCall firGetClassCall) {
        firGetClassCall.getClass();
        ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.getOrNull(FirTypeUtilsKt.getResolvedType(firGetClassCall).getTypeArguments(), 0);
        if (coneTypeProjection != null) {
            return ConeTypeProjectionKt.getType(coneTypeProjection);
        }
        return null;
    }

    public static final boolean hasAnnotation(List<? extends FirAnnotation> list, ClassId classId, FirSession firSession) {
        list.getClass();
        classId.getClass();
        firSession.getClass();
        List<? extends FirAnnotation> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(toAnnotationClassId((FirAnnotation) it.next(), firSession), classId)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean hasAnnotationSafe(List<? extends FirAnnotation> list, ClassId classId, FirSession firSession) {
        list.getClass();
        classId.getClass();
        firSession.getClass();
        List<? extends FirAnnotation> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(toAnnotationClassIdSafe((FirAnnotation) it.next(), firSession), classId)) {
                return true;
            }
        }
        return false;
    }

    public static final <D extends FirDeclaration & FirAnnotationContainer> boolean hasAnnotationWithClassId(FirBasedSymbol<? extends D> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        return getAnnotationByClassId(firBasedSymbol.getResolvedAnnotationsWithClassIds(), classId, firSession) != null;
    }

    public static final boolean hasLowPriorityAnnotation(List<? extends FirAnnotation> list) {
        list.getClass();
        List<? extends FirAnnotation> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(((FirAnnotation) it.next()).getAnnotationTypeRef()));
            if (classLikeLookupTagIfAny == null ? false : Intrinsics.areEqual(classLikeLookupTagIfAny.getClassId(), LOW_PRIORITY_IN_OVERLOAD_RESOLUTION_CLASS_ID)) {
                return true;
            }
        }
        return false;
    }

    public static final List<FirAnnotation> nonSourceAnnotations(List<? extends FirAnnotation> list, FirSession firSession) {
        EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo;
        list.getClass();
        firSession.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            FirRegularClass annotationClass = toAnnotationClass((FirAnnotation) obj, firSession);
            if (annotationClass != null) {
                List<FirAnnotation> resolvedAnnotationsWithClassIds = annotationClass.getSymbol().getResolvedAnnotationsWithClassIds();
                if (!(resolvedAnnotationsWithClassIds instanceof Collection) || !resolvedAnnotationsWithClassIds.isEmpty()) {
                    Iterator<T> it = resolvedAnnotationsWithClassIds.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            FirAnnotation firAnnotation = (FirAnnotation) it.next();
                            if (Intrinsics.areEqual(toAnnotationClassId(firAnnotation, firSession), StandardClassIds$Annotations.INSTANCE.getRetention())) {
                                Name enumEntryName = null;
                                FirExpression firExpressionFindArgumentByName$default = findArgumentByName$default(firAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getRetentionValue(), false, 2, null);
                                if (firExpressionFindArgumentByName$default != null && (enumValueArgumentInfoExtractEnumValueArgumentInfo = extractEnumValueArgumentInfo(firExpressionFindArgumentByName$default)) != null) {
                                    enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName();
                                }
                                if (Intrinsics.areEqual(enumEntryName, sourceName)) {
                                    break;
                                }
                            }
                        }
                    }
                }
                arrayList.add(obj);
                break;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClass toAnnotationClass(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> annotationClassLikeSymbol = toAnnotationClassLikeSymbol(firAnnotation, firSession);
        FirClassLikeDeclaration firClassLikeDeclaration = annotationClassLikeSymbol != null ? (FirClassLikeDeclaration) annotationClassLikeSymbol.getFir() : null;
        if (firClassLikeDeclaration instanceof FirRegularClass) {
            return (FirRegularClass) firClassLikeDeclaration;
        }
        return null;
    }

    public static final ClassId toAnnotationClassId(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        ConeClassLikeLookupTag annotationLookupTag = toAnnotationLookupTag(firAnnotation, firSession);
        if (annotationLookupTag != null) {
            return annotationLookupTag.getClassId();
        }
        return null;
    }

    public static final ClassId toAnnotationClassIdSafe(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        ConeClassLikeLookupTag annotationLookupTagSafe = toAnnotationLookupTagSafe(firAnnotation, firSession);
        if (annotationLookupTagSafe != null) {
            return annotationLookupTagSafe.getClassId();
        }
        return null;
    }

    public static final FirClassLikeSymbol<?> toAnnotationClassLikeSymbol(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        ConeClassLikeLookupTag annotationLookupTag = toAnnotationLookupTag(firAnnotation, firSession);
        if (annotationLookupTag != null) {
            return ToSymbolUtilsKt.toSymbol(annotationLookupTag, firSession);
        }
        return null;
    }

    public static final ConeClassLikeType toAnnotationClassLikeType(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType != null) {
            return TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null);
        }
        return null;
    }

    private static final ConeClassLikeLookupTag toAnnotationLookupTag(FirAnnotation firAnnotation, FirSession firSession) {
        ConeClassLikeType annotationClassLikeType = toAnnotationClassLikeType(firAnnotation, firSession);
        if (annotationClassLikeType != null) {
            return annotationClassLikeType.getLookupTag();
        }
        return null;
    }

    private static final ConeClassLikeLookupTag toAnnotationLookupTagSafe(FirAnnotation firAnnotation, FirSession firSession) {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default;
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (!(coneType instanceof ConeClassLikeType)) {
            coneType = null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
        if (coneClassLikeType == null || (coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null)) == null) {
            return null;
        }
        return coneClassLikeTypeFullyExpandedType$default.getLookupTag();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<FirExpression> unwrapVarargValue(FirExpression firExpression) {
        firExpression.getClass();
        if (!(firExpression instanceof FirVarargArgumentsExpression)) {
            return firExpression instanceof FirCollectionLiteral ? ((FirCall) firExpression).getArgumentList().getArguments() : CollectionsKt.listOf(firExpression);
        }
        FirVarargArgumentsExpression firVarargArgumentsExpression = (FirVarargArgumentsExpression) firExpression;
        FirExpression firExpression2 = (FirExpression) CollectionsKt.firstOrNull(firVarargArgumentsExpression.getArguments());
        return firExpression2 instanceof FirWrappedArgumentExpression ? unwrapVarargValue(((FirWrappedArgumentExpression) firExpression2).getExpression()) : firVarargArgumentsExpression.getArguments();
    }

    public static final FirAnnotation getAnnotationByClassId(FirAnnotationContainer firAnnotationContainer, ClassId classId, FirSession firSession) {
        firAnnotationContainer.getClass();
        classId.getClass();
        firSession.getClass();
        return getAnnotationByClassId(firAnnotationContainer.getAnnotations(), classId, firSession);
    }

    public static final <D extends FirDeclaration & FirAnnotationContainer> FirAnnotation getAnnotationByClassId(FirBasedSymbol<? extends D> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        return getAnnotationByClassId(firBasedSymbol.getResolvedAnnotationsWithClassIds(), classId, firSession);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use getKClassArgument overload without session parameter", replaceWith = @ReplaceWith(expression = "getKClassArgument(name)", imports = {}))
    public static final /* synthetic */ ConeKotlinType getKClassArgument(FirAnnotation firAnnotation, Name name, FirSession firSession) {
        firAnnotation.getClass();
        name.getClass();
        firSession.getClass();
        return getKClassArgument(firAnnotation, name);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use getBooleanArgument overload without session parameter", replaceWith = @ReplaceWith(expression = "getBooleanArgument(name)", imports = {}))
    public static final /* synthetic */ Boolean getBooleanArgument(FirAnnotation firAnnotation, Name name, FirSession firSession) {
        firAnnotation.getClass();
        name.getClass();
        firSession.getClass();
        return getBooleanArgument(firAnnotation, name);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use getStringArgument overload without session parameter", replaceWith = @ReplaceWith(expression = "getStringArgument(name)", imports = {}))
    public static final /* synthetic */ String getStringArgument(FirAnnotation firAnnotation, Name name, FirSession firSession) {
        firAnnotation.getClass();
        name.getClass();
        firSession.getClass();
        return getStringArgument(firAnnotation, name);
    }

    public static final List<FirAnnotation> getAnnotationsByClassId(FirAnnotationContainer firAnnotationContainer, ClassId classId, FirSession firSession) {
        firAnnotationContainer.getClass();
        classId.getClass();
        firSession.getClass();
        return getAnnotationsByClassId(firAnnotationContainer.getAnnotations(), classId, firSession);
    }

    public static final boolean hasAnnotation(FirBasedSymbol<?> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        return hasAnnotation(firBasedSymbol.getResolvedAnnotationsWithClassIds(), classId, firSession);
    }

    public static final boolean hasAnnotationSafe(FirDeclaration firDeclaration, ClassId classId, FirSession firSession) {
        firDeclaration.getClass();
        classId.getClass();
        firSession.getClass();
        return hasAnnotationSafe(firDeclaration.getAnnotations(), classId, firSession);
    }

    public static final boolean hasAnnotation(FirAnnotationContainer firAnnotationContainer, ClassId classId, FirSession firSession) {
        firAnnotationContainer.getClass();
        classId.getClass();
        firSession.getClass();
        return hasAnnotation(firAnnotationContainer.getAnnotations(), classId, firSession);
    }

    public static final boolean hasAnnotation(FirDeclaration firDeclaration, ClassId classId, FirSession firSession) {
        firDeclaration.getClass();
        classId.getClass();
        firSession.getClass();
        return hasAnnotation(firDeclaration.getAnnotations(), classId, firSession);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use getStringArrayArgument overload without session parameter", replaceWith = @ReplaceWith(expression = "getStringArrayArgument(name)", imports = {}))
    public static final /* synthetic */ List getStringArrayArgument(FirAnnotation firAnnotation, Name name, FirSession firSession) {
        firAnnotation.getClass();
        name.getClass();
        firSession.getClass();
        return getStringArrayArgument(firAnnotation, name);
    }

    public static final List<FirAnnotation> nonSourceAnnotations(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        return nonSourceAnnotations(firAnnotationContainer.getAnnotations(), firSession);
    }
}
