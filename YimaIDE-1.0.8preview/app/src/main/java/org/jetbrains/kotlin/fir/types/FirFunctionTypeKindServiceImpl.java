package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindExtractor;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirFunctionTypeKindExtension;
import org.jetbrains.kotlin.fir.extensions.FirFunctionTypeKindExtensionKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\r\u001a\u0004\u0018\u00010\b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0011H\u0016JZ\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0011\"\u0004\b\u0000\u0010\u001b2\u0006\u0010\u001c\u001a\u0002H\u001b2\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\u0002\b 2\u001d\u0010\u0018\u001a\u0019\u0012\u0004\u0012\u0002H\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00110\u001e¢\u0006\u0002\b H\u0082\b¢\u0006\u0002\u0010!J \u0010\"\u001a\u00020#*\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeKindServiceImpl;", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeKindService;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "nonReflectKindsFromExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "extractor", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKindExtractor;", "getExtractor", "()Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKindExtractor;", "extractSingleSpecialKindForFunction", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "extractAllSpecialKindsForFunction", Argument.Delimiters.none, "extractAllSpecialKindsForFunctionTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "extractSingleExtensionKindForDeserializedConeType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "extractSpecialKindsImpl", "T", "source", "isSuspend", "Lkotlin/Function1;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "extractKindsFromAnnotations", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFunctionTypeKindServiceImpl extends FirFunctionTypeKindService {
    private final FunctionTypeKindExtractor extractor;
    private final List<FunctionTypeKind> nonReflectKindsFromExtensions;
    private final FirSession session;

    public FirFunctionTypeKindServiceImpl(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.nonReflectKindsFromExtensions = new ArrayList();
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(FunctionTypeKind.Function.INSTANCE);
        listCreateListBuilder.add(FunctionTypeKind.SuspendFunction.INSTANCE);
        listCreateListBuilder.add(FunctionTypeKind.KFunction.INSTANCE);
        listCreateListBuilder.add(FunctionTypeKind.KSuspendFunction.INSTANCE);
        FirFunctionTypeKindExtension.FunctionTypeKindRegistrar functionTypeKindRegistrar = new FirFunctionTypeKindExtension.FunctionTypeKindRegistrar() { // from class: org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceImpl$extractor$1$kinds$1$registrar$1
            public void registerKind(FunctionTypeKind nonReflectKind, FunctionTypeKind reflectKind) {
                nonReflectKind.getClass();
                reflectKind.getClass();
                if (!Intrinsics.areEqual(nonReflectKind.reflectKind(), reflectKind)) {
                    w01.a("Failed requirement.");
                } else {
                    if (!Intrinsics.areEqual(reflectKind.nonReflectKind(), nonReflectKind)) {
                        w01.a("Failed requirement.");
                        return;
                    }
                    listCreateListBuilder.add(nonReflectKind);
                    listCreateListBuilder.add(reflectKind);
                    this.nonReflectKindsFromExtensions.add(nonReflectKind);
                }
            }
        };
        Iterator<FirFunctionTypeKindExtension> it = FirFunctionTypeKindExtensionKt.getFunctionTypeKindExtensions(FirExtensionServiceKt.getExtensionService(firSession)).iterator();
        while (it.hasNext()) {
            it.next().registerKinds(functionTypeKindRegistrar);
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        List<FunctionTypeKind> list = listBuild;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (FunctionTypeKind functionTypeKind : list) {
            arrayList.add(functionTypeKind.getPackageFqName() + '.' + functionTypeKind.getClassNamePrefix());
        }
        if (!Intrinsics.areEqual(CollectionsKt.distinct(arrayList), arrayList)) {
            dt1.a("There are clashing functional type kinds: ", arrayList);
            throw null;
        }
        this.extractor = new FunctionTypeKindExtractor(listBuild);
    }

    private final void extractKindsFromAnnotations(List<FunctionTypeKind> list, List<ClassId> list2) {
        for (ClassId classId : list2) {
            for (FunctionTypeKind functionTypeKind : this.nonReflectKindsFromExtensions) {
                if (Intrinsics.areEqual(functionTypeKind.getAnnotationOnInvokeClassId(), classId)) {
                    list.add(functionTypeKind);
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService
    public List<FunctionTypeKind> extractAllSpecialKindsForFunction(FirFunctionSymbol<?> functionSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<ClassId> resolvedAnnotationClassIds;
        functionSymbol.getClass();
        List<FunctionTypeKind> listCreateListBuilder = CollectionsKt.createListBuilder();
        if (functionSymbol.getRawStatus().isSuspend()) {
            listCreateListBuilder.add(FunctionTypeKind.SuspendFunction.INSTANCE);
        }
        if (!this.nonReflectKindsFromExtensions.isEmpty()) {
            if (functionSymbol instanceof FirAnonymousFunctionSymbol) {
                List<FirAnnotation> annotations = ((FirAnonymousFunctionSymbol) functionSymbol).getAnnotations();
                resolvedAnnotationClassIds = new ArrayList<>();
                Iterator<T> it = annotations.iterator();
                while (it.hasNext()) {
                    ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId((FirAnnotation) it.next(), this.session);
                    if (annotationClassId != null) {
                        resolvedAnnotationClassIds.add(annotationClassId);
                    }
                }
            } else {
                resolvedAnnotationClassIds = functionSymbol.getResolvedAnnotationClassIds();
            }
            extractKindsFromAnnotations(listCreateListBuilder, resolvedAnnotationClassIds);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService
    public List<FunctionTypeKind> extractAllSpecialKindsForFunctionTypeRef(FirFunctionTypeRef typeRef) {
        typeRef.getClass();
        List<FunctionTypeKind> listCreateListBuilder = CollectionsKt.createListBuilder();
        if (typeRef.isSuspend()) {
            listCreateListBuilder.add(FunctionTypeKind.SuspendFunction.INSTANCE);
        }
        if (!this.nonReflectKindsFromExtensions.isEmpty()) {
            List annotations = typeRef.getAnnotations();
            ArrayList arrayList = new ArrayList();
            Iterator it = annotations.iterator();
            while (it.hasNext()) {
                ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId((FirAnnotation) it.next(), this.session);
                if (annotationClassId != null) {
                    arrayList.add(annotationClassId);
                }
            }
            extractKindsFromAnnotations(listCreateListBuilder, arrayList);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService
    public FunctionTypeKind extractSingleExtensionKindForDeserializedConeType(ClassId classId, List<? extends FirAnnotation> annotations) {
        classId.getClass();
        annotations.getClass();
        if (!this.nonReflectKindsFromExtensions.isEmpty() && !annotations.isEmpty()) {
            FunctionTypeKindExtractor extractor = getExtractor();
            FqName packageFqName = classId.getPackageFqName();
            String strAsString = classId.getShortClassName().asString();
            strAsString.getClass();
            FunctionTypeKind functionalClassKind = extractor.getFunctionalClassKind(packageFqName, strAsString);
            if (functionalClassKind == null || !Intrinsics.areEqual(functionalClassKind.nonReflectKind(), FunctionTypeKind.Function.INSTANCE)) {
                return null;
            }
            List<FunctionTypeKind> listCreateListBuilder = CollectionsKt.createListBuilder();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = annotations.iterator();
            while (it.hasNext()) {
                ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId((FirAnnotation) it.next(), this.session);
                if (annotationClassId != null) {
                    arrayList.add(annotationClassId);
                }
            }
            extractKindsFromAnnotations(listCreateListBuilder, arrayList);
            FunctionTypeKind functionTypeKind = (FunctionTypeKind) CollectionsKt.singleOrNull(CollectionsKt.build(listCreateListBuilder));
            if (functionTypeKind == null) {
                return null;
            }
            boolean zIsReflectType = functionalClassKind.isReflectType();
            if (!zIsReflectType) {
                return functionTypeKind;
            }
            if (zIsReflectType) {
                return functionTypeKind.reflectKind();
            }
            bu8.a();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService
    public FunctionTypeKind extractSingleSpecialKindForFunction(FirFunctionSymbol<?> functionSymbol) {
        functionSymbol.getClass();
        if (!this.nonReflectKindsFromExtensions.isEmpty()) {
            return (FunctionTypeKind) CollectionsKt.singleOrNull(extractAllSpecialKindsForFunction(functionSymbol));
        }
        FunctionTypeKind.SuspendFunction suspendFunction = FunctionTypeKind.SuspendFunction.INSTANCE;
        if (functionSymbol.getRawStatus().isSuspend()) {
            return suspendFunction;
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService
    public FunctionTypeKindExtractor getExtractor() {
        return this.extractor;
    }
}
