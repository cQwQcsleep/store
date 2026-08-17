package org.jetbrains.kotlin.cli.pipeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.FrontendFilesForPluginsGenerationPipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.builder.FirFileBuilder;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProviderKt;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.CallableIdKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \t*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\tB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/FrontendFilesForPluginsGenerationPipelinePhase;", "A", "Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "<init>", "()V", "executePhase", "input", "(Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;)Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FrontendFilesForPluginsGenerationPipelinePhase<A extends FrontendPipelineArtifact> extends PipelinePhase<A, A> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public FrontendFilesForPluginsGenerationPipelinePhase() {
        super("FrontendFilesForPluginsGenerationPipelinePhase", null, null, 6, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public A executePhase(A input) {
        input.getClass();
        return (A) PipelineArtifactsKt.m33withNewFrontendOutputYuMBOKc(input, INSTANCE.m30createFilesWithGeneratedDeclarationsrAV7gdU(input.mo31getFrontendOutputQYgrGdg()));
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\f\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/FrontendFilesForPluginsGenerationPipelinePhase$Companion;", Argument.Delimiters.none, "<init>", "()V", "createFilesWithGeneratedDeclarations", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "allModulesOutput", "createFilesWithGeneratedDeclarations-rAV7gdU", "(Ljava/util/List;)Ljava/util/List;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "toPath", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit createFilesWithGeneratedDeclarations$lambda$0$2(FirClassLikeDeclaration firClassLikeDeclaration, FirFileBuilder firFileBuilder) {
            firFileBuilder.getClass();
            firFileBuilder.getDeclarations().add(firClassLikeDeclaration);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit createFilesWithGeneratedDeclarations$lambda$0$7(List list, FirFileBuilder firFileBuilder) {
            firFileBuilder.getClass();
            List<FirDeclaration> declarations = firFileBuilder.getDeclarations();
            List list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add((FirCallableDeclaration) ((FirCallableSymbol) it.next()).getFir());
            }
            CollectionsKt.addAll(declarations, arrayList);
            return Unit.INSTANCE;
        }

        private final String toPath(FqName fqName) {
            return StringsKt.replace$default(fqName.asString(), '.', '/', false, 4, (Object) null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final List<FirFile> createFilesWithGeneratedDeclarations(FirSession session) {
            session.getClass();
            FirSwitchableExtensionDeclarationsSymbolProvider generatedDeclarationsSymbolProvider = FirSwitchableExtensionDeclarationsSymbolProviderKt.getGeneratedDeclarationsSymbolProvider(session);
            if (generatedDeclarationsSymbolProvider == null) {
                return CollectionsKt.emptyList();
            }
            List<FirDeclarationGenerationExtension> declarationGenerators = FirDeclarationGenerationExtensionKt.getDeclarationGenerators(FirExtensionServiceKt.getExtensionService(session));
            FirModuleData moduleData = FirModuleDataKt.getModuleData(session);
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            List<FirDeclarationGenerationExtension> list = declarationGenerators;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((FirDeclarationGenerationExtension) it.next()).getTopLevelClassIdsCache().getValue());
            }
            ArrayList<FirClassLikeDeclaration> arrayList2 = new ArrayList();
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                FirClassLikeSymbol<?> classLikeSymbolByClassId = generatedDeclarationsSymbolProvider.getClassLikeSymbolByClassId((ClassId) it2.next());
                FirClassLikeDeclaration firClassLikeDeclaration = classLikeSymbolByClassId != null ? (FirClassLikeDeclaration) classLikeSymbolByClassId.getFir() : null;
                if (firClassLikeDeclaration != null) {
                    arrayList2.add(firClassLikeDeclaration);
                }
            }
            for (final FirClassLikeDeclaration firClassLikeDeclaration2 : arrayList2) {
                ClassId classId = firClassLikeDeclaration2.getSymbol().getClassId();
                listCreateListBuilder.add(IrElementsCreationUtilsKt.createSyntheticFirFileForFir2Ir(FrontendFilesForPluginsGenerationPipelinePhase.INSTANCE.toPath(classId.getPackageFqName()) + '/' + classId.getRelativeClassName().asString() + ".kt", classId.getPackageFqName(), moduleData, FirDeclarationOrigin.Synthetic.PluginFile.INSTANCE, new Function1() { // from class: mk5
                    public final Object invoke(Object obj) {
                        return FrontendFilesForPluginsGenerationPipelinePhase.Companion.createFilesWithGeneratedDeclarations$lambda$0$2(firClassLikeDeclaration2, (FirFileBuilder) obj);
                    }
                }));
            }
            ArrayList<CallableId> arrayList3 = new ArrayList();
            Iterator<T> it3 = list.iterator();
            while (it3.hasNext()) {
                CollectionsKt.addAll(arrayList3, ((FirDeclarationGenerationExtension) it3.next()).getTopLevelCallableIdsCache().getValue());
            }
            ArrayList arrayList4 = new ArrayList();
            for (CallableId callableId : arrayList3) {
                CollectionsKt.addAll(arrayList4, generatedDeclarationsSymbolProvider.getTopLevelCallableSymbols(callableId.getPackageName(), callableId.getCallableName()));
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : arrayList4) {
                FqName packageName = CallableIdKt.getPackageName(((FirCallableSymbol) obj).getCallableId());
                Object arrayList5 = linkedHashMap.get(packageName);
                if (arrayList5 == null) {
                    arrayList5 = new ArrayList();
                    linkedHashMap.put(packageName, arrayList5);
                }
                ((List) arrayList5).add(obj);
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                FqName fqName = (FqName) entry.getKey();
                List list2 = (List) entry.getValue();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Object obj2 : list2) {
                    String fileNameForPluginGeneratedCallable = DeclarationAttributesKt.getFileNameForPluginGeneratedCallable((FirCallableDeclaration) ((FirCallableSymbol) obj2).getFir());
                    if (fileNameForPluginGeneratedCallable == null) {
                        fileNameForPluginGeneratedCallable = "__GENERATED__CALLABLES__.kt";
                    }
                    if (!StringsKt.endsWith$default(fileNameForPluginGeneratedCallable, ".kt", false, 2, (Object) null)) {
                        fileNameForPluginGeneratedCallable = fileNameForPluginGeneratedCallable.concat(".kt");
                    }
                    Object arrayList6 = linkedHashMap2.get(fileNameForPluginGeneratedCallable);
                    if (arrayList6 == null) {
                        arrayList6 = new ArrayList();
                        linkedHashMap2.put(fileNameForPluginGeneratedCallable, arrayList6);
                    }
                    ((List) arrayList6).add(obj2);
                }
                for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                    String str = (String) entry2.getKey();
                    final List list3 = (List) entry2.getValue();
                    listCreateListBuilder.add(IrElementsCreationUtilsKt.createSyntheticFirFileForFir2Ir(FrontendFilesForPluginsGenerationPipelinePhase.INSTANCE.toPath(fqName) + '/' + str, fqName, moduleData, FirDeclarationOrigin.Synthetic.PluginFile.INSTANCE, new Function1() { // from class: nk5
                        public final Object invoke(Object obj3) {
                            return FrontendFilesForPluginsGenerationPipelinePhase.Companion.createFilesWithGeneratedDeclarations$lambda$0$7(list3, (FirFileBuilder) obj3);
                        }
                    }));
                }
            }
            return CollectionsKt.build(listCreateListBuilder);
        }

        /* JADX INFO: renamed from: createFilesWithGeneratedDeclarations-rAV7gdU, reason: not valid java name */
        public final List<? extends SingleModuleFrontendOutput> m30createFilesWithGeneratedDeclarationsrAV7gdU(List<? extends SingleModuleFrontendOutput> allModulesOutput) {
            allModulesOutput.getClass();
            List<? extends SingleModuleFrontendOutput> list = allModulesOutput;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (SingleModuleFrontendOutput singleModuleFrontendOutputCopy$default : list) {
                List<FirFile> listCreateFilesWithGeneratedDeclarations = FrontendFilesForPluginsGenerationPipelinePhase.INSTANCE.createFilesWithGeneratedDeclarations(singleModuleFrontendOutputCopy$default.getSession());
                if (!listCreateFilesWithGeneratedDeclarations.isEmpty()) {
                    singleModuleFrontendOutputCopy$default = SingleModuleFrontendOutput.copy$default(singleModuleFrontendOutputCopy$default, null, null, CollectionsKt.plus(singleModuleFrontendOutputCopy$default.getFir(), listCreateFilesWithGeneratedDeclarations), 3, null);
                }
                arrayList.add(singleModuleFrontendOutputCopy$default);
            }
            return AllModulesFrontendOutput.m573constructorimpl(arrayList);
        }

        private Companion() {
        }
    }
}
