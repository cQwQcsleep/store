package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSealedClassInheritorsProcessor;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSealedClassInheritorsProcessor;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirGlobalResolveProcessor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "process", Argument.Delimiters.none, "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "InheritorsCollector", "InheritorsTransformer", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSealedClassInheritorsProcessor extends FirGlobalResolveProcessor {

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B!\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004¢\u0006\u0004\b\b\u0010\tJ)\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u0002H\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u000fJ\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSealedClassInheritorsProcessor$InheritorsTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "inheritorsMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Ljava/util/Map;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "regularClass", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InheritorsTransformer extends FirTransformer<Object> {
        private final Map<FirRegularClass, Set<ClassId>> inheritorsMap;

        public InheritorsTransformer(Map<FirRegularClass, Set<ClassId>> map) {
            map.getClass();
            this.inheritorsMap = map;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public <E extends FirElement> E transformElement(E element, Object data) {
            element.getClass();
            return element;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirFile transformFile(FirFile file, Object data) {
            file.getClass();
            try {
                FirElement firElementTransformChildren = file.transformChildren(this, data);
                firElementTransformChildren.getClass();
                return (FirFile) firElementTransformChildren;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
                wq6.a();
                return null;
            }
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformRegularClass(FirRegularClass regularClass, Object data) {
            Set<ClassId> setRemove;
            regularClass.getClass();
            if (regularClass.getStatus().getModality() == Modality.SEALED && (setRemove = this.inheritorsMap.remove(regularClass)) != null) {
                SealedClassInheritorsKt.setSealedClassInheritors(regularClass, (List<ClassId>) CollectionsKt.toList(setRemove));
            }
            if (this.inheritorsMap.isEmpty()) {
                return regularClass;
            }
            FirElement firElementTransformChildren = regularClass.transformChildren(this, data);
            firElementTransformChildren.getClass();
            return (FirRegularClass) firElementTransformChildren;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSealedClassInheritorsProcessor(FirSession firSession, ScopeSession scopeSession) {
        super(firSession, scopeSession, FirResolvePhase.SEALED_CLASS_INHERITORS);
        firSession.getClass();
        scopeSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirGlobalResolveProcessor
    public void process(Collection<? extends FirFile> files) {
        files.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        InheritorsCollector inheritorsCollector = new InheritorsCollector(getSession());
        Collection<? extends FirFile> collection = files;
        for (FirFile firFile : collection) {
            try {
                firFile.accept(inheritorsCollector, linkedHashMap);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
                wq6.a();
                return;
            }
        }
        for (FirFile firFile2 : collection) {
            try {
                FirTransformerUtilKt.transformSingle(firFile2, new InheritorsTransformer(linkedHashMap), null);
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th2) {
                UtilsKt.getExceptionHandler(firFile2.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile2, th2);
                wq6.a();
                return;
            }
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00030\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ*\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u0016J*\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u0016J*\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00042\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u0016J*\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u0016J\u001e\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSealedClassInheritorsProcessor$InheritorsCollector;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitRegularClass", "regularClass", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "collectInheritorsOfCorrespondingExpectSealedClass", "expectClassId", "inheritors", "extractClassFromTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InheritorsCollector extends FirDefaultVisitor<Unit, Map<FirRegularClass, Set<ClassId>>> {
        private final FirSession session;

        public InheritorsCollector(FirSession firSession) {
            firSession.getClass();
            this.session = firSession;
        }

        public static Set c(FirRegularClass firRegularClass) {
            firRegularClass.getClass();
            return new LinkedHashSet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final void collectInheritorsOfCorrespondingExpectSealedClass(ClassId expectClassId, Set<ClassId> inheritors) {
            FirRegularClassSymbol regularClassSymbolByClassIdFromDependencies;
            FirRegularClass firRegularClass;
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).supportsFeature(LanguageFeature.MultiPlatformProjects) && (regularClassSymbolByClassIdFromDependencies = FirSymbolProviderKt.getRegularClassSymbolByClassIdFromDependencies(this.session, expectClassId)) != null && (firRegularClass = (FirRegularClass) regularClassSymbolByClassIdFromDependencies.getFir()) != null && firRegularClass.getStatus().isExpect() && firRegularClass.getStatus().getModality() == Modality.SEALED) {
                inheritors.addAll(SealedClassInheritorsKt.getSealedClassInheritors(firRegularClass, firRegularClass.getModuleData().getSession()));
            }
        }

        public static Set e(Function1 function1, Object obj) {
            return (Set) function1.invoke(obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final FirRegularClass extractClassFromTypeRef(FirTypeRef typeRef) {
            FirClassifierSymbol<?> symbol;
            ConeClassifierLookupTag lookupTagIfAny = ConeTypeUtilsKt.getLookupTagIfAny(FirTypeUtilsKt.getConeType(typeRef));
            if (lookupTagIfAny == null || (symbol = ToSymbolUtilsKt.toSymbol(lookupTagIfAny, this.session)) == null) {
                return null;
            }
            if (symbol instanceof FirRegularClassSymbol) {
                return (FirRegularClass) ((FirRegularClassSymbol) symbol).getFir();
            }
            if (!(symbol instanceof FirTypeAliasSymbol)) {
                return null;
            }
            FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, FirResolvePhase.SUPER_TYPES);
            return extractClassFromTypeRef(((FirTypeAlias) ((FirTypeAliasSymbol) symbol).getFir()).getExpandedTypeRef());
        }

        public static Set f(FirRegularClass firRegularClass) {
            firRegularClass.getClass();
            return new LinkedHashSet();
        }

        public static Set g(Function1 function1, Object obj) {
            return (Set) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Set visitRegularClass$lambda$1$0(FirRegularClass firRegularClass) {
            firRegularClass.getClass();
            return new LinkedHashSet();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Set visitRegularClass$lambda$1$1(Function1 function1, Object obj) {
            return (Set) function1.invoke(obj);
        }

        public final FirSession getSession() {
            return this.session;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
            visitElement(firElement, (Map<FirRegularClass, Set<ClassId>>) obj);
            return Unit.INSTANCE;
        }

        public void visitFile(FirFile file, Map<FirRegularClass, Set<ClassId>> data) {
            file.getClass();
            data.getClass();
            Iterator<T> it = file.getDeclarations().iterator();
            while (it.hasNext()) {
                ((FirDeclaration) it.next()).accept(this, data);
            }
        }

        public void visitRegularClass(FirRegularClass regularClass, Map<FirRegularClass, Set<ClassId>> data) {
            Set<ClassId> setComputeIfAbsent;
            regularClass.getClass();
            data.getClass();
            Iterator<T> it = regularClass.getDeclarations().iterator();
            while (it.hasNext()) {
                ((FirDeclaration) it.next()).accept(this, data);
            }
            if (regularClass.getStatus().getModality() == Modality.SEALED) {
                final Function1 function1 = new Function1() { // from class: wc5
                    public final Object invoke(Object obj) {
                        return FirSealedClassInheritorsProcessor.InheritorsCollector.visitRegularClass$lambda$1$0((FirRegularClass) obj);
                    }
                };
                setComputeIfAbsent = data.computeIfAbsent(regularClass, new Function() { // from class: xc5
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return FirSealedClassInheritorsProcessor.InheritorsCollector.visitRegularClass$lambda$1$1(function1, obj);
                    }
                });
            } else {
                setComputeIfAbsent = null;
            }
            Iterator<FirTypeRef> it2 = regularClass.getSuperTypeRefs().iterator();
            while (it2.hasNext()) {
                FirRegularClass firRegularClassExtractClassFromTypeRef = extractClassFromTypeRef(it2.next());
                if ((firRegularClassExtractClassFromTypeRef != null ? firRegularClassExtractClassFromTypeRef.getStatus().getModality() : null) != Modality.SEALED) {
                    firRegularClassExtractClassFromTypeRef = null;
                }
                if (firRegularClassExtractClassFromTypeRef != null && Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firRegularClassExtractClassFromTypeRef).getPackageFqName(), FirDeclarationUtilKt.getClassId(regularClass).getPackageFqName())) {
                    final Function1 function2 = new Function1() { // from class: yc5
                        public final Object invoke(Object obj) {
                            return FirSealedClassInheritorsProcessor.InheritorsCollector.c((FirRegularClass) obj);
                        }
                    };
                    Set<ClassId> setComputeIfAbsent2 = data.computeIfAbsent(firRegularClassExtractClassFromTypeRef, new Function() { // from class: zc5
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return FirSealedClassInheritorsProcessor.InheritorsCollector.e(function2, obj);
                        }
                    });
                    setComputeIfAbsent2.getClass();
                    setComputeIfAbsent2.add(regularClass.getSymbol().getClassId());
                }
            }
            if (setComputeIfAbsent != null) {
                collectInheritorsOfCorrespondingExpectSealedClass(FirDeclarationUtilKt.getClassId(regularClass), setComputeIfAbsent);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitTypeAlias(FirTypeAlias typeAlias, Map<FirRegularClass, Set<ClassId>> data) {
            FirRegularClassSymbol regularClassSymbol;
            FirRegularClass firRegularClass;
            typeAlias.getClass();
            data.getClass();
            if (typeAlias.getStatus().isActual() && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(FirTypeUtilsKt.getConeType(typeAlias.getExpandedTypeRef()), this.session)) != null && (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) != null && firRegularClass.getStatus().getModality() == Modality.SEALED) {
                ClassId classId = FirDeclarationUtilKt.getClassId(typeAlias);
                final Function1 function1 = new Function1() { // from class: ad5
                    public final Object invoke(Object obj) {
                        return FirSealedClassInheritorsProcessor.InheritorsCollector.f((FirRegularClass) obj);
                    }
                };
                Set<ClassId> setComputeIfAbsent = data.computeIfAbsent(firRegularClass, new Function() { // from class: bd5
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return FirSealedClassInheritorsProcessor.InheritorsCollector.g(function1, obj);
                    }
                });
                setComputeIfAbsent.getClass();
                collectInheritorsOfCorrespondingExpectSealedClass(classId, setComputeIfAbsent);
            }
        }

        public void visitElement(FirElement element, Map<FirRegularClass, Set<ClassId>> data) {
            element.getClass();
            data.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitFile(FirFile firFile, Object obj) {
            visitFile(firFile, (Map<FirRegularClass, Set<ClassId>>) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitTypeAlias(FirTypeAlias firTypeAlias, Object obj) {
            visitTypeAlias(firTypeAlias, (Map<FirRegularClass, Set<ClassId>>) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitRegularClass(FirRegularClass firRegularClass, Object obj) {
            visitRegularClass(firRegularClass, (Map<FirRegularClass, Set<ClassId>>) obj);
            return Unit.INSTANCE;
        }
    }
}
