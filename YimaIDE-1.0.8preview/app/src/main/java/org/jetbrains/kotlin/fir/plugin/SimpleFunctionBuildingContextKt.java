package org.jetbrains.kotlin.fir.plugin;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.plugin.SimpleFunctionBuildingContext;
import org.jetbrains.kotlin.fir.plugin.SimpleFunctionBuildingContextKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001aI\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f\u001a[\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0018\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\n0\f2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f\u001aO\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0007b\u0002\b\u0018\u001aa\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0018\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\n0\f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0007b\u0002\b\u0018¨\u0006\u0019"}, d2 = {"createMemberFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "returnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "config", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/plugin/SimpleFunctionBuildingContext;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "returnTypeProvider", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "createTopLevelFunction", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "containingFileName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/ExperimentalTopLevelDeclarationsGenerationApi;", "org.jetbrains.kotlin:plugin-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SimpleFunctionBuildingContextKt {
    public static Unit a(SimpleFunctionBuildingContext simpleFunctionBuildingContext) {
        simpleFunctionBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static Unit c(SimpleFunctionBuildingContext simpleFunctionBuildingContext) {
        simpleFunctionBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static final FirNamedFunction createMemberFunction(FirExtension firExtension, final FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> function1, Function1<? super SimpleFunctionBuildingContext, Unit> function2) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        name.getClass();
        function1.getClass();
        function2.getClass();
        SimpleFunctionBuildingContext simpleFunctionBuildingContext = new SimpleFunctionBuildingContext(firExtension.getSession(), generatedDeclarationKey, firClassSymbol, new CallableId(firClassSymbol.getClassId(), name), function1, null);
        function2.invoke(simpleFunctionBuildingContext);
        simpleFunctionBuildingContext.status(new Function1() { // from class: ddd
            public final Object invoke(Object obj) {
                return SimpleFunctionBuildingContextKt.createMemberFunction$lambda$3$0(firClassSymbol, (FirResolvedDeclarationStatusImpl) obj);
            }
        });
        return simpleFunctionBuildingContext.build();
    }

    public static /* synthetic */ FirNamedFunction createMemberFunction$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, ConeKotlinType coneKotlinType, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: add
                public final Object invoke(Object obj2) {
                    return SimpleFunctionBuildingContextKt.a((SimpleFunctionBuildingContext) obj2);
                }
            };
        }
        return createMemberFunction(firExtension, (FirClassSymbol<?>) firClassSymbol, generatedDeclarationKey, name, coneKotlinType, (Function1<? super SimpleFunctionBuildingContext, Unit>) function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createMemberFunction$lambda$3$0(FirClassSymbol firClassSymbol, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl) {
        firResolvedDeclarationStatusImpl.getClass();
        firResolvedDeclarationStatusImpl.setExpect(firClassSymbol.getRawStatus().isExpect());
        return Unit.INSTANCE;
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public static final FirNamedFunction createTopLevelFunction(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> function1, String str, Function1<? super SimpleFunctionBuildingContext, Unit> function2) {
        firExtension.getClass();
        generatedDeclarationKey.getClass();
        callableId.getClass();
        function1.getClass();
        function2.getClass();
        if (callableId.getClassId() != null) {
            w01.a("Failed requirement.");
            return null;
        }
        SimpleFunctionBuildingContext simpleFunctionBuildingContext = new SimpleFunctionBuildingContext(firExtension.getSession(), generatedDeclarationKey, null, callableId, function1, str);
        function2.invoke(simpleFunctionBuildingContext);
        return simpleFunctionBuildingContext.build();
    }

    public static /* synthetic */ FirNamedFunction createTopLevelFunction$default(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, ConeKotlinType coneKotlinType, String str, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            str = null;
        }
        String str2 = str;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: ycd
                public final Object invoke(Object obj2) {
                    return SimpleFunctionBuildingContextKt.e((SimpleFunctionBuildingContext) obj2);
                }
            };
        }
        return createTopLevelFunction(firExtension, generatedDeclarationKey, callableId, coneKotlinType, str2, (Function1<? super SimpleFunctionBuildingContext, Unit>) function1);
    }

    public static ConeKotlinType d(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    public static Unit e(SimpleFunctionBuildingContext simpleFunctionBuildingContext) {
        simpleFunctionBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static ConeKotlinType f(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    public static Unit g(SimpleFunctionBuildingContext simpleFunctionBuildingContext) {
        simpleFunctionBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ FirNamedFunction createMemberFunction$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, Function1 function1, Function1 function2, int i, Object obj) {
        if ((i & 16) != 0) {
            function2 = new Function1() { // from class: zcd
                public final Object invoke(Object obj2) {
                    return SimpleFunctionBuildingContextKt.g((SimpleFunctionBuildingContext) obj2);
                }
            };
        }
        return createMemberFunction(firExtension, (FirClassSymbol<?>) firClassSymbol, generatedDeclarationKey, name, (Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType>) function1, (Function1<? super SimpleFunctionBuildingContext, Unit>) function2);
    }

    public static /* synthetic */ FirNamedFunction createTopLevelFunction$default(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, Function1 function1, String str, Function1 function2, int i, Object obj) {
        if ((i & 8) != 0) {
            str = null;
        }
        String str2 = str;
        if ((i & 16) != 0) {
            function2 = new Function1() { // from class: xcd
                public final Object invoke(Object obj2) {
                    return SimpleFunctionBuildingContextKt.c((SimpleFunctionBuildingContext) obj2);
                }
            };
        }
        return createTopLevelFunction(firExtension, generatedDeclarationKey, callableId, (Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType>) function1, str2, (Function1<? super SimpleFunctionBuildingContext, Unit>) function2);
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public static final FirNamedFunction createTopLevelFunction(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, final ConeKotlinType coneKotlinType, String str, Function1<? super SimpleFunctionBuildingContext, Unit> function1) {
        firExtension.getClass();
        generatedDeclarationKey.getClass();
        callableId.getClass();
        coneKotlinType.getClass();
        function1.getClass();
        return createTopLevelFunction(firExtension, generatedDeclarationKey, callableId, (Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType>) new Function1() { // from class: cdd
            public final Object invoke(Object obj) {
                return SimpleFunctionBuildingContextKt.f(coneKotlinType, (List) obj);
            }
        }, str, function1);
    }

    public static final FirNamedFunction createMemberFunction(FirExtension firExtension, FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, final ConeKotlinType coneKotlinType, Function1<? super SimpleFunctionBuildingContext, Unit> function1) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        name.getClass();
        coneKotlinType.getClass();
        function1.getClass();
        return createMemberFunction(firExtension, firClassSymbol, generatedDeclarationKey, name, (Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType>) new Function1() { // from class: bdd
            public final Object invoke(Object obj) {
                return SimpleFunctionBuildingContextKt.d(coneKotlinType, (List) obj);
            }
        }, function1);
    }
}
