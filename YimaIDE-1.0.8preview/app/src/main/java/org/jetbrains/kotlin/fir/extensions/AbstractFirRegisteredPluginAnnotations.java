package org.jetbrains.kotlin.fir.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0013\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\u00112\u0006\u0010\u0014\u001a\u00020\u0010J\u001a\u0010\u0015\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\u00112\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\f\u0010\u0016\u001a\u00020\u0017H\u0007b\u0002\b\u0018J\u001a\u0010\u0019\u001a\u00020\u00172\u0010\u0010\u001a\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\u001bH$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR,\u0010\u000e\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u000e\u0012\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/AbstractFirRegisteredPluginAnnotations;", "Lorg/jetbrains/kotlin/fir/extensions/FirRegisteredPluginAnnotations;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "metaAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getMetaAnnotations", "()Ljava/util/Set;", "annotationsForPredicateCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", Argument.Delimiters.none, Argument.Delimiters.none, "getAnnotationsForPredicate", "predicate", "collectAnnotations", "initialize", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/PluginServicesInitialization;", "saveAnnotationsFromPlugin", "annotations", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirRegisteredPluginAnnotations extends FirRegisteredPluginAnnotations {
    private final FirCache annotationsForPredicateCache;
    private final Set<FqName> metaAnnotations;
    private final FirSession session;

    public AbstractFirRegisteredPluginAnnotations(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.metaAnnotations = new LinkedHashSet();
        this.annotationsForPredicateCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.extensions.AbstractFirRegisteredPluginAnnotations$special$$inlined$createCache$1
            public final Set<? extends FqName> invoke(DeclarationPredicate declarationPredicate, Void r2) {
                declarationPredicate.getClass();
                return this.this$0.collectAnnotations(declarationPredicate);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((DeclarationPredicate) obj, (Void) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<FqName> collectAnnotations(DeclarationPredicate predicate) {
        Set<FqName> metaAnnotations = predicate.getMetaAnnotations();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = metaAnnotations.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, getAnnotationsWithMetaAnnotation((FqName) it.next()));
        }
        if (linkedHashSet.isEmpty()) {
            return predicate.getAnnotations();
        }
        CollectionsKt.addAll(linkedHashSet, predicate.getAnnotations());
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
    public final Set<FqName> getAnnotationsForPredicate(DeclarationPredicate predicate) {
        predicate.getClass();
        return (Set) this.annotationsForPredicateCache.getValue(predicate, null);
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
    public final Set<FqName> getMetaAnnotations() {
        return this.metaAnnotations;
    }

    public final FirSession getSession() {
        return this.session;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.jetbrains.kotlin.fir.extensions.AbstractFirRegisteredPluginAnnotations$initialize$registrar$1, org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar] */
    @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
    @PluginServicesInitialization
    public final void initialize() {
        ?? r0 = new FirDeclarationPredicateRegistrar() { // from class: org.jetbrains.kotlin.fir.extensions.AbstractFirRegisteredPluginAnnotations$initialize$registrar$1
            private final List<AbstractPredicate<?>> predicates = new ArrayList();

            public final List<AbstractPredicate<?>> getPredicates() {
                return this.predicates;
            }

            @Override // org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
            public void register(Collection<? extends AbstractPredicate<?>> predicates) {
                predicates.getClass();
                CollectionsKt.addAll(this.predicates, predicates);
            }

            @Override // org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
            public void register(AbstractPredicate<?>... predicates) {
                predicates.getClass();
                CollectionsKt.addAll(this.predicates, predicates);
            }
        };
        Iterator<FirExtension> it = FirExtensionServiceKt.getExtensionService(this.session).getAllExtensions().iterator();
        while (it.hasNext()) {
            it.next().registerPredicates(r0);
        }
        for (AbstractPredicate<?> abstractPredicate : r0.getPredicates()) {
            saveAnnotationsFromPlugin(abstractPredicate.getAnnotations());
            CollectionsKt.addAll(this.metaAnnotations, abstractPredicate.getMetaAnnotations());
        }
    }

    public abstract void saveAnnotationsFromPlugin(Collection<FqName> annotations);
}
