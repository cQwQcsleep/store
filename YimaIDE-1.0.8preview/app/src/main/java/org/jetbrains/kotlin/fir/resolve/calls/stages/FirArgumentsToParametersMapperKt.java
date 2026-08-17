package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a:\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"EmptyArgumentMapping", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ArgumentMapping;", "mapArguments", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "originScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "callSiteIsOperatorCall", Argument.Delimiters.none, "lookInContextParameters", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirArgumentsToParametersMapperKt {
    private static final ArgumentMapping EmptyArgumentMapping = new ArgumentMapping(new LinkedHashMap(), CollectionsKt.emptyList());

    /* JADX WARN: Code duplicated, block: B:30:0x007d  */
    public static final ArgumentMapping mapArguments(BodyResolveComponents bodyResolveComponents, List<? extends ConeResolutionAtom> list, FirFunction firFunction, FirScope firScope, boolean z, boolean z2) {
        boolean z3;
        bodyResolveComponents.getClass();
        list.getClass();
        firFunction.getClass();
        if (list.isEmpty() && firFunction.getValueParameters().isEmpty()) {
            return EmptyArgumentMapping;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ConeResolutionAtom coneResolutionAtom = null;
        for (ConeResolutionAtom coneResolutionAtom2 : list) {
            FirExpression expression = coneResolutionAtom2.getExpression();
            if (!(expression instanceof FirAnonymousFunctionExpression) || !((FirAnonymousFunctionExpression) expression).getIsTrailingLambda()) {
                arrayList.add(coneResolutionAtom2);
            } else if (coneResolutionAtom == null) {
                coneResolutionAtom = coneResolutionAtom2;
            } else {
                arrayList2.add(coneResolutionAtom2);
            }
        }
        if (z && (firFunction instanceof FirNamedFunction) && firFunction.getStatus().isOperator()) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firFunction;
            if (!Intrinsics.areEqual(firNamedFunction.getName(), OperatorNameConventions.SET) || (firNamedFunction.getOrigin() instanceof FirDeclarationOrigin.DynamicScope)) {
                z3 = false;
            } else {
                z3 = true;
            }
        } else {
            z3 = false;
        }
        FirCallArgumentsProcessor firCallArgumentsProcessor = new FirCallArgumentsProcessor(bodyResolveComponents.getSession(), firFunction, bodyResolveComponents, firScope, z3, z2);
        firCallArgumentsProcessor.processNonLambdaArguments(arrayList);
        if (coneResolutionAtom != null) {
            firCallArgumentsProcessor.processExternalArgument(coneResolutionAtom);
        }
        firCallArgumentsProcessor.processExcessLambdaArguments(arrayList2);
        firCallArgumentsProcessor.processDefaultsAndRunChecks();
        LinkedHashMap<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> result = firCallArgumentsProcessor.getResult();
        List<ResolutionDiagnostic> diagnostics = firCallArgumentsProcessor.getDiagnostics();
        if (diagnostics == null) {
            diagnostics = CollectionsKt.emptyList();
        }
        return new ArgumentMapping(result, diagnostics);
    }
}
