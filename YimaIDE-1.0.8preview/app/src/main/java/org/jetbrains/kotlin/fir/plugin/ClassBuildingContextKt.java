package org.jetbrains.kotlin.fir.plugin;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.plugin.ClassBuildingContext;
import org.jetbrains.kotlin.fir.plugin.ClassBuildingContextKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aE\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0007b\u0002\b\u000e\u001aK\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r\u001a9\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¨\u0006\u0015"}, d2 = {"createTopLevelClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "config", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/plugin/ClassBuildingContext;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "Lorg/jetbrains/kotlin/fir/extensions/ExperimentalTopLevelDeclarationsGenerationApi;", "createNestedClass", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "createCompanionObject", "org.jetbrains.kotlin:plugin-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassBuildingContextKt {
    public static Unit b(ClassBuildingContext classBuildingContext) {
        classBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    public static Unit c(ClassBuildingContext classBuildingContext) {
        classBuildingContext.getClass();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClass createCompanionObject(FirExtension firExtension, final FirClassSymbol<?> firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Function1<? super ClassBuildingContext, Unit> function1) {
        firExtension.getClass();
        firClassSymbol.getClass();
        generatedDeclarationKey.getClass();
        function1.getClass();
        ClassBuildingContext classBuildingContext = new ClassBuildingContext(firExtension.getSession(), generatedDeclarationKey, firClassSymbol, firClassSymbol.getClassId().createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT), ClassKind.OBJECT);
        function1.invoke(classBuildingContext);
        classBuildingContext.setModality(Modality.FINAL);
        if (((FirClassLikeDeclaration) firClassSymbol.getFir()).getIsLocal()) {
            classBuildingContext.setVisibility(Visibilities.Local.INSTANCE);
        }
        classBuildingContext.status(new Function1() { // from class: mt1
            public final Object invoke(Object obj) {
                return ClassBuildingContextKt.createCompanionObject$lambda$1$0(firClassSymbol, (FirResolvedDeclarationStatusImpl) obj);
            }
        });
        FirRegularClass firRegularClassBuild = classBuildingContext.build();
        if (((FirClassLikeDeclaration) firClassSymbol.getFir()).getIsLocal()) {
            ClassMembersKt.setContainingClassForLocalAttr(firRegularClassBuild, firClassSymbol.getLookupTag());
        }
        return firRegularClassBuild;
    }

    public static /* synthetic */ FirRegularClass createCompanionObject$default(FirExtension firExtension, FirClassSymbol firClassSymbol, GeneratedDeclarationKey generatedDeclarationKey, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: lt1
                public final Object invoke(Object obj2) {
                    return ClassBuildingContextKt.c((ClassBuildingContext) obj2);
                }
            };
        }
        return createCompanionObject(firExtension, firClassSymbol, generatedDeclarationKey, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createCompanionObject$lambda$1$0(FirClassSymbol firClassSymbol, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl) {
        firResolvedDeclarationStatusImpl.getClass();
        firResolvedDeclarationStatusImpl.setCompanion(true);
        firResolvedDeclarationStatusImpl.setExpect(firClassSymbol.getRawStatus().isExpect());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClass createNestedClass(FirExtension firExtension, final FirClassSymbol<?> firClassSymbol, Name name, GeneratedDeclarationKey generatedDeclarationKey, ClassKind classKind, Function1<? super ClassBuildingContext, Unit> function1) {
        firExtension.getClass();
        firClassSymbol.getClass();
        name.getClass();
        generatedDeclarationKey.getClass();
        classKind.getClass();
        function1.getClass();
        ClassBuildingContext classBuildingContext = new ClassBuildingContext(firExtension.getSession(), generatedDeclarationKey, firClassSymbol, firClassSymbol.getClassId().createNestedClassId(name), classKind);
        function1.invoke(classBuildingContext);
        if (((FirClassLikeDeclaration) firClassSymbol.getFir()).getIsLocal()) {
            classBuildingContext.setVisibility(Visibilities.Local.INSTANCE);
        }
        classBuildingContext.status(new Function1() { // from class: jt1
            public final Object invoke(Object obj) {
                return ClassBuildingContextKt.createNestedClass$lambda$1$0(firClassSymbol, (FirResolvedDeclarationStatusImpl) obj);
            }
        });
        FirRegularClass firRegularClassBuild = classBuildingContext.build();
        if (((FirClassLikeDeclaration) firClassSymbol.getFir()).getIsLocal()) {
            ClassMembersKt.setContainingClassForLocalAttr(firRegularClassBuild, firClassSymbol.getLookupTag());
        }
        return firRegularClassBuild;
    }

    public static /* synthetic */ FirRegularClass createNestedClass$default(FirExtension firExtension, FirClassSymbol firClassSymbol, Name name, GeneratedDeclarationKey generatedDeclarationKey, ClassKind classKind, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            classKind = ClassKind.CLASS;
        }
        ClassKind classKind2 = classKind;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: kt1
                public final Object invoke(Object obj2) {
                    return ClassBuildingContextKt.d((ClassBuildingContext) obj2);
                }
            };
        }
        return createNestedClass(firExtension, firClassSymbol, name, generatedDeclarationKey, classKind2, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createNestedClass$lambda$1$0(FirClassSymbol firClassSymbol, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl) {
        firResolvedDeclarationStatusImpl.getClass();
        firResolvedDeclarationStatusImpl.setExpect(firClassSymbol.getRawStatus().isExpect());
        return Unit.INSTANCE;
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public static final FirRegularClass createTopLevelClass(FirExtension firExtension, ClassId classId, GeneratedDeclarationKey generatedDeclarationKey, ClassKind classKind, Function1<? super ClassBuildingContext, Unit> function1) {
        firExtension.getClass();
        classId.getClass();
        generatedDeclarationKey.getClass();
        classKind.getClass();
        function1.getClass();
        ClassBuildingContext classBuildingContext = new ClassBuildingContext(firExtension.getSession(), generatedDeclarationKey, null, classId, classKind);
        function1.invoke(classBuildingContext);
        return classBuildingContext.build();
    }

    public static /* synthetic */ FirRegularClass createTopLevelClass$default(FirExtension firExtension, ClassId classId, GeneratedDeclarationKey generatedDeclarationKey, ClassKind classKind, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            classKind = ClassKind.CLASS;
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: it1
                public final Object invoke(Object obj2) {
                    return ClassBuildingContextKt.b((ClassBuildingContext) obj2);
                }
            };
        }
        return createTopLevelClass(firExtension, classId, generatedDeclarationKey, classKind, function1);
    }

    public static Unit d(ClassBuildingContext classBuildingContext) {
        classBuildingContext.getClass();
        return Unit.INSTANCE;
    }
}
