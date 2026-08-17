package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.DestructuringContext;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dBQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001aR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001aR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "initializerSource", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "initializerName", "isVar", Argument.Delimiters.none, "isFullForm", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/name/Name;ZZLjava/util/List;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getInitializerSource", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getInitializerName", "()Z", "getAnnotations", "()Ljava/util/List;", "Companion", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DestructuringEntry {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<FirAnnotationCall> annotations;
    private final Name initializerName;
    private final KtSourceElement initializerSource;
    private final boolean isFullForm;
    private final boolean isVar;
    private final Name name;
    private final FirTypeRef returnTypeRef;
    private final KtSourceElement source;

    /* JADX WARN: Multi-variable type inference failed */
    public DestructuringEntry(KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirTypeRef firTypeRef, Name name, Name name2, boolean z, boolean z2, List<? extends FirAnnotationCall> list) {
        ktSourceElement.getClass();
        firTypeRef.getClass();
        name.getClass();
        list.getClass();
        this.source = ktSourceElement;
        this.initializerSource = ktSourceElement2;
        this.returnTypeRef = firTypeRef;
        this.name = name;
        this.initializerName = name2;
        this.isVar = z;
        this.isFullForm = z2;
        this.annotations = list;
    }

    public final List<FirAnnotationCall> getAnnotations() {
        return this.annotations;
    }

    public final Name getInitializerName() {
        return this.initializerName;
    }

    public final KtSourceElement getInitializerSource() {
        return this.initializerSource;
    }

    public final Name getName() {
        return this.name;
    }

    public final FirTypeRef getReturnTypeRef() {
        return this.returnTypeRef;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: isFullForm, reason: from getter */
    public final boolean getIsFullForm() {
        return this.isFullForm;
    }

    /* JADX INFO: renamed from: isVar, reason: from getter */
    public final boolean getIsVar() {
        return this.isVar;
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J \u0010\u0018\u001a\u00020\u0019*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0016R\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u00020\n*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u0004\u0018\u00010\n*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0018\u0010\u000f\u001a\u00020\u0010*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u0013*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry$Companion;", "Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;", "Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;", "<init>", "()V", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "(Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;)Lorg/jetbrains/kotlin/name/Name;", "initializerName", "getInitializerName", "isVar", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;)Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "(Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;)Lorg/jetbrains/kotlin/KtSourceElement;", "initializerSource", "getInitializerSource", "extractAnnotationsTo", Argument.Delimiters.none, "target", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "containerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion implements DestructuringContext<DestructuringEntry> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: extractAnnotationsTo, reason: avoid collision after fix types in other method */
        public void extractAnnotationsTo2(DestructuringEntry destructuringEntry, FirAnnotationContainerBuilder firAnnotationContainerBuilder, FirBasedSymbol<?> firBasedSymbol) {
            destructuringEntry.getClass();
            firAnnotationContainerBuilder.getClass();
            firBasedSymbol.getClass();
            List<FirAnnotation> annotations = firAnnotationContainerBuilder.getAnnotations();
            List<FirAnnotationCall> annotations2 = destructuringEntry.getAnnotations();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations2, 10));
            for (FirAnnotationCall firAnnotationCall : annotations2) {
                FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
                firAnnotationCallBuilder.setSource(firAnnotationCall.getSource());
                firAnnotationCallBuilder.setUseSiteTarget(firAnnotationCall.getUseSiteTarget());
                firAnnotationCallBuilder.setAnnotationTypeRef(firAnnotationCall.getAnnotationTypeRef());
                firAnnotationCallBuilder.getTypeArguments().addAll(firAnnotationCall.getTypeArguments());
                firAnnotationCallBuilder.setArgumentList(firAnnotationCall.getArgumentList());
                firAnnotationCallBuilder.setCalleeReference(firAnnotationCall.getCalleeReference());
                firAnnotationCallBuilder.setArgumentMapping(firAnnotationCall.getArgumentMapping());
                firAnnotationCallBuilder.setAnnotationResolvePhase(firAnnotationCall.getAnnotationResolvePhase());
                firAnnotationCallBuilder.setContainingDeclarationSymbol(firAnnotationCall.getContainingDeclarationSymbol());
                firAnnotationCallBuilder.setContainingDeclarationSymbol(firBasedSymbol);
                arrayList.add(firAnnotationCallBuilder.mo289build());
            }
            CollectionsKt.addAll(annotations, arrayList);
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public Name getInitializerName(DestructuringEntry destructuringEntry) {
            destructuringEntry.getClass();
            return destructuringEntry.getInitializerName();
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public KtSourceElement getInitializerSource(DestructuringEntry destructuringEntry) {
            destructuringEntry.getClass();
            return destructuringEntry.getInitializerSource();
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public Name getName(DestructuringEntry destructuringEntry) {
            destructuringEntry.getClass();
            return destructuringEntry.getName();
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public FirTypeRef getReturnTypeRef(DestructuringEntry destructuringEntry) {
            destructuringEntry.getClass();
            return destructuringEntry.getReturnTypeRef();
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public KtSourceElement getSource(DestructuringEntry destructuringEntry) {
            destructuringEntry.getClass();
            return destructuringEntry.getSource();
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public boolean isVar(DestructuringEntry destructuringEntry) {
            destructuringEntry.getClass();
            return destructuringEntry.getIsVar();
        }

        private Companion() {
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public /* bridge */ /* synthetic */ void extractAnnotationsTo(DestructuringEntry destructuringEntry, FirAnnotationContainerBuilder firAnnotationContainerBuilder, FirBasedSymbol firBasedSymbol) {
            extractAnnotationsTo2(destructuringEntry, firAnnotationContainerBuilder, (FirBasedSymbol<?>) firBasedSymbol);
        }
    }
}
