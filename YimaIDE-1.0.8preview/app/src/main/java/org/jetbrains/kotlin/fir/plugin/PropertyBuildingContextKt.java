package org.jetbrains.kotlin.fir.plugin;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.plugin.PropertyBuildingContext;
import org.jetbrains.kotlin.fir.plugin.PropertyBuildingContextKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a]\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012\u001ao\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0018\u0010\u0013\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\n0\u000f2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012\u001ac\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0007b\u0002\b\u001b\u001au\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0018\u0010\u0013\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\n0\u000f2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0007b\u0002\b\u001b¨\u0006\u001c"}, d2 = {"createMemberProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "returnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isVal", Argument.Delimiters.none, "hasBackingField", "config", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/plugin/PropertyBuildingContext;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "returnTypeProvider", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "createTopLevelProperty", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "containingFileName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/ExperimentalTopLevelDeclarationsGenerationApi;", "org.jetbrains.kotlin:plugin-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertyBuildingContextKt {
    public static ConeKotlinType a(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    public static Unit c(PropertyBuildingContext propertyBuildingContext) {
        propertyBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static final FirProperty createMemberProperty(FirExtension firExtension, final FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> function1, boolean z, boolean z2, Function1<? super PropertyBuildingContext, Unit> function2) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        name.getClass();
        function1.getClass();
        function2.getClass();
        PropertyBuildingContext propertyBuildingContext = new PropertyBuildingContext(firExtension.getSession(), generatedDeclarationKey, firClassSymbol, new CallableId(firClassSymbol.getClassId(), name), function1, z, z2, null);
        function2.invoke(propertyBuildingContext);
        propertyBuildingContext.status(new Function1() { // from class: egb
            public final Object invoke(Object obj) {
                return PropertyBuildingContextKt.createMemberProperty$lambda$3$0(firClassSymbol, (FirResolvedDeclarationStatusImpl) obj);
            }
        });
        return propertyBuildingContext.build();
    }

    public static /* synthetic */ FirProperty createMemberProperty$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, ConeKotlinType coneKotlinType, boolean z, boolean z2, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            z = true;
        }
        if ((i & 32) != 0) {
            z2 = true;
        }
        if ((i & 64) != 0) {
            function1 = new Function1() { // from class: jgb
                public final Object invoke(Object obj2) {
                    return PropertyBuildingContextKt.c((PropertyBuildingContext) obj2);
                }
            };
        }
        return createMemberProperty(firExtension, (FirClassSymbol<?>) firClassSymbol, generatedDeclarationKey, name, coneKotlinType, z, z2, (Function1<? super PropertyBuildingContext, Unit>) function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createMemberProperty$lambda$3$0(FirClassSymbol firClassSymbol, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl) {
        firResolvedDeclarationStatusImpl.getClass();
        firResolvedDeclarationStatusImpl.setExpect(firClassSymbol.getRawStatus().isExpect());
        return Unit.INSTANCE;
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public static final FirProperty createTopLevelProperty(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> function1, boolean z, boolean z2, String str, Function1<? super PropertyBuildingContext, Unit> function2) {
        firExtension.getClass();
        generatedDeclarationKey.getClass();
        callableId.getClass();
        function1.getClass();
        function2.getClass();
        if (callableId.getClassId() != null) {
            w01.a("Failed requirement.");
            return null;
        }
        PropertyBuildingContext propertyBuildingContext = new PropertyBuildingContext(firExtension.getSession(), generatedDeclarationKey, null, callableId, function1, z, z2, str);
        function2.invoke(propertyBuildingContext);
        return propertyBuildingContext.build();
    }

    public static /* synthetic */ FirProperty createTopLevelProperty$default(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, ConeKotlinType coneKotlinType, boolean z, boolean z2, String str, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        if ((i & 32) != 0) {
            str = null;
        }
        if ((i & 64) != 0) {
            function1 = new Function1() { // from class: ggb
                public final Object invoke(Object obj2) {
                    return PropertyBuildingContextKt.e((PropertyBuildingContext) obj2);
                }
            };
        }
        return createTopLevelProperty(firExtension, generatedDeclarationKey, callableId, coneKotlinType, z, z2, str, (Function1<? super PropertyBuildingContext, Unit>) function1);
    }

    public static ConeKotlinType d(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    public static Unit e(PropertyBuildingContext propertyBuildingContext) {
        propertyBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static Unit f(PropertyBuildingContext propertyBuildingContext) {
        propertyBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static Unit g(PropertyBuildingContext propertyBuildingContext) {
        propertyBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ FirProperty createMemberProperty$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, Function1 function1, boolean z, boolean z2, Function1 function2, int i, Object obj) {
        if ((i & 16) != 0) {
            z = true;
        }
        if ((i & 32) != 0) {
            z2 = true;
        }
        if ((i & 64) != 0) {
            function2 = new Function1() { // from class: fgb
                public final Object invoke(Object obj2) {
                    return PropertyBuildingContextKt.f((PropertyBuildingContext) obj2);
                }
            };
        }
        return createMemberProperty(firExtension, (FirClassSymbol<?>) firClassSymbol, generatedDeclarationKey, name, (Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>) function1, z, z2, (Function1<? super PropertyBuildingContext, Unit>) function2);
    }

    public static /* synthetic */ FirProperty createTopLevelProperty$default(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, Function1 function1, boolean z, boolean z2, String str, Function1 function2, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        if ((i & 32) != 0) {
            str = null;
        }
        if ((i & 64) != 0) {
            function2 = new Function1() { // from class: dgb
                public final Object invoke(Object obj2) {
                    return PropertyBuildingContextKt.g((PropertyBuildingContext) obj2);
                }
            };
        }
        return createTopLevelProperty(firExtension, generatedDeclarationKey, callableId, (Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>) function1, z, z2, str, (Function1<? super PropertyBuildingContext, Unit>) function2);
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public static final FirProperty createTopLevelProperty(FirExtension firExtension, GeneratedDeclarationKey generatedDeclarationKey, CallableId callableId, final ConeKotlinType coneKotlinType, boolean z, boolean z2, String str, Function1<? super PropertyBuildingContext, Unit> function1) {
        firExtension.getClass();
        generatedDeclarationKey.getClass();
        callableId.getClass();
        coneKotlinType.getClass();
        function1.getClass();
        return createTopLevelProperty(firExtension, generatedDeclarationKey, callableId, (Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>) new Function1() { // from class: igb
            public final Object invoke(Object obj) {
                return PropertyBuildingContextKt.a(coneKotlinType, (List) obj);
            }
        }, z, z2, str, function1);
    }

    public static final FirProperty createMemberProperty(FirExtension firExtension, FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Name name, final ConeKotlinType coneKotlinType, boolean z, boolean z2, Function1<? super PropertyBuildingContext, Unit> function1) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        name.getClass();
        coneKotlinType.getClass();
        function1.getClass();
        return createMemberProperty(firExtension, firClassSymbol, generatedDeclarationKey, name, (Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType>) new Function1() { // from class: hgb
            public final Object invoke(Object obj) {
                return PropertyBuildingContextKt.d(coneKotlinType, (List) obj);
            }
        }, z, z2, function1);
    }
}
