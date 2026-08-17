package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.DesignationState;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B'\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState;", Argument.Delimiters.none, "firstDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "designation", Argument.Delimiters.none, "targetClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/Iterator;Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)V", "getFirstDeclaration", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getTargetClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "currentElement", "value", Argument.Delimiters.none, "classLocated", "getClassLocated", "()Z", "shouldSkipClass", "declaration", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DesignationState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean classLocated;
    private FirDeclaration currentElement;
    private final Iterator<FirDeclaration> designation;
    private final FirDeclaration firstDeclaration;
    private final FirClassLikeDeclaration targetClass;

    /* JADX WARN: Multi-variable type inference failed */
    private DesignationState(FirDeclaration firDeclaration, Iterator<? extends FirDeclaration> it, FirClassLikeDeclaration firClassLikeDeclaration) {
        this.firstDeclaration = firDeclaration;
        this.designation = it;
        this.targetClass = firClassLikeDeclaration;
    }

    public final boolean getClassLocated() {
        return this.classLocated;
    }

    public final FirDeclaration getFirstDeclaration() {
        return this.firstDeclaration;
    }

    public final FirClassLikeDeclaration getTargetClass() {
        return this.targetClass;
    }

    public final boolean shouldSkipClass(FirDeclaration declaration) {
        declaration.getClass();
        if (this.classLocated) {
            return !Intrinsics.areEqual(declaration, this.targetClass);
        }
        if (this.currentElement == null && this.designation.hasNext()) {
            this.currentElement = this.designation.next();
        }
        boolean zAreEqual = Intrinsics.areEqual(this.currentElement, declaration);
        if (zAreEqual) {
            if (Intrinsics.areEqual(this.currentElement, this.targetClass)) {
                this.classLocated = true;
            }
            this.currentElement = null;
        }
        return !zAreEqual;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "designationMapForLocalClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "includeFile", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClassId create$lambda$1$0(ClassId classId) {
            classId.getClass();
            return classId.getOuterClassId();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final DesignationState create(FirRegularClassSymbol symbol, Map<FirClassLikeDeclaration, ? extends FirClassLikeDeclaration> designationMapForLocalClasses, boolean includeFile) {
            List listBuild;
            symbol.getClass();
            designationMapForLocalClasses.getClass();
            FirRegularClass firRegularClass = (FirRegularClass) symbol.getFir();
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (firRegularClass.getIsLocal()) {
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                FirClassLikeDeclaration firClassLikeDeclaration = firRegularClass;
                do {
                    listCreateListBuilder.add(firClassLikeDeclaration);
                    firClassLikeDeclaration = designationMapForLocalClasses.get(firClassLikeDeclaration);
                    if (firClassLikeDeclaration == null) {
                        break;
                    }
                    if (firClassLikeDeclaration instanceof FirAnonymousObject) {
                        firClassLikeDeclaration = null;
                    }
                } while (firClassLikeDeclaration != null);
                CollectionsKt.reverse(listCreateListBuilder);
                listBuild = CollectionsKt.build(listCreateListBuilder);
            } else {
                List listCreateListBuilder2 = CollectionsKt.createListBuilder();
                FirProvider firProvider = FirProviderKt.getFirProvider(firRegularClass.getModuleData().getSession());
                Sequence<ClassId> sequenceGenerateSequence = SequencesKt.generateSequence(symbol.getClassId(), new Function1() { // from class: zo3
                    public final Object invoke(Object obj) {
                        return DesignationState.Companion.create$lambda$1$0((ClassId) obj);
                    }
                });
                ArrayList arrayList = new ArrayList();
                for (ClassId classId : sequenceGenerateSequence) {
                    arrayList.add(Intrinsics.areEqual(classId, symbol.getClassId()) ? firRegularClass : firProvider.getFirClassifierByFqName(classId));
                }
                FirFile firClassifierContainerFileIfAny = firProvider.getFirClassifierContainerFileIfAny(firRegularClass.getSymbol());
                if (firClassifierContainerFileIfAny == null) {
                    dt1.a("Containing file was not found for\n", UtilsKt.render(firRegularClass));
                    return null;
                }
                if (includeFile) {
                    listCreateListBuilder2.add(firClassifierContainerFileIfAny);
                }
                CollectionsKt.addAll(listCreateListBuilder2, CollectionsKt.asReversed(CollectionsKt.filterNotNull(arrayList)));
                listBuild = CollectionsKt.build(listCreateListBuilder2);
            }
            if (listBuild.isEmpty()) {
                return null;
            }
            return new DesignationState((FirDeclaration) CollectionsKt.first(listBuild), listBuild.iterator(), firRegularClass, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ DesignationState(FirDeclaration firDeclaration, Iterator it, FirClassLikeDeclaration firClassLikeDeclaration, DefaultConstructorMarker defaultConstructorMarker) {
        this(firDeclaration, it, firClassLikeDeclaration);
    }
}
