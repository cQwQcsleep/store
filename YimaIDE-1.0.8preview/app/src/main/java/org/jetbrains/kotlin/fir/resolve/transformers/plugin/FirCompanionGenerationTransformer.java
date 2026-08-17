package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirCompanionGenerationTransformer;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\u0006\u0010\u000e\u001a\u0002H\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirCompanionGenerationTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "generatedDeclarationProvider", "Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "generateAndUpdateCompanion", Argument.Delimiters.none, "generateCompanion", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompanionGenerationTransformer extends FirTransformer {
    private final FirSwitchableExtensionDeclarationsSymbolProvider generatedDeclarationProvider;
    private final FirSession session;

    public FirCompanionGenerationTransformer(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.generatedDeclarationProvider = FirSwitchableExtensionDeclarationsSymbolProviderKt.getGeneratedDeclarationsSymbolProvider(firSession);
    }

    public static Unit b(Ref.ObjectRef objectRef, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if ((firClassifierSymbol instanceof FirClassLikeSymbol) && ((FirClassLikeSymbol) firClassifierSymbol).getOrigin().getGenerated()) {
            objectRef.element = firClassifierSymbol;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final FirRegularClassSymbol generateCompanion(FirRegularClass regularClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        if (this.generatedDeclarationProvider == null) {
            return null;
        }
        if (regularClass.getIsLocal()) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(this.session, regularClass);
            if (firNestedClassifierScopeNestedClassifierScope != null) {
                Name name = SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT;
                final Function1 function1 = new Function1() { // from class: rz4
                    public final Object invoke(Object obj) {
                        return FirCompanionGenerationTransformer.b(objectRef, (FirClassifierSymbol) obj);
                    }
                };
                firNestedClassifierScopeNestedClassifierScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirCompanionGenerationTransformer$generateCompanion$$inlined$processClassifiersByName$1
                    public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                        firClassifierSymbol.getClass();
                        coneSubstitutor.getClass();
                        function1.invoke(firClassifierSymbol);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                        return Unit.INSTANCE;
                    }
                });
            }
            classLikeSymbolByClassId = (FirClassLikeSymbol) objectRef.element;
        } else {
            classLikeSymbolByClassId = this.generatedDeclarationProvider.getClassLikeSymbolByClassId(FirDeclarationUtilKt.getClassId(regularClass).createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT));
            if (classLikeSymbolByClassId == null || !classLikeSymbolByClassId.getOrigin().getGenerated()) {
                classLikeSymbolByClassId = null;
            }
        }
        if (classLikeSymbolByClassId == null) {
            return null;
        }
        if (!(classLikeSymbolByClassId instanceof FirRegularClassSymbol)) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Plugin generated non regular class as companion object: " + Reflection.getOrCreateKotlinClass(classLikeSymbolByClassId.getOrigin().getClass()).getSimpleName(), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            exceptionAttachmentBuilder.withEntry("origin", classLikeSymbolByClassId.getOrigin().toString());
            FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "generatedCompanion", classLikeSymbolByClassId);
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "regularClass", regularClass);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        if (regularClass.getCompanionObjectSymbol() == null) {
            return (FirRegularClassSymbol) classLikeSymbolByClassId;
        }
        StringBuilder sb = new StringBuilder("Plugin generated duplicated companion object: ");
        FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) classLikeSymbolByClassId;
        sb.append(Reflection.getOrCreateKotlinClass(firRegularClassSymbol.getOrigin().getClass()).getSimpleName());
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments2 = new KotlinIllegalArgumentExceptionWithAttachments(sb.toString(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
        exceptionAttachmentBuilder2.withEntry("origin", firRegularClassSymbol.getOrigin().toString());
        FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder2, "generatedCompanion", classLikeSymbolByClassId);
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder2, "regularClass", regularClass);
        kotlinIllegalArgumentExceptionWithAttachments2.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void generateAndUpdateCompanion(FirRegularClass regularClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        regularClass.getClass();
        FirRegularClassSymbol firRegularClassSymbolGenerateCompanion = generateCompanion(regularClass);
        if (firRegularClassSymbolGenerateCompanion != null) {
            regularClass.replaceCompanionObjectSymbol(firRegularClassSymbolGenerateCompanion);
        }
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Void data) {
        file.getClass();
        if (this.generatedDeclarationProvider == null) {
            return file;
        }
        try {
            return file.transformDeclarations(this, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, Void data) throws KotlinIllegalArgumentExceptionWithAttachments {
        regularClass.getClass();
        generateAndUpdateCompanion(regularClass);
        return regularClass.transformDeclarations((FirTransformer<? super Void>) this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Void data) {
        element.getClass();
        return element;
    }
}
