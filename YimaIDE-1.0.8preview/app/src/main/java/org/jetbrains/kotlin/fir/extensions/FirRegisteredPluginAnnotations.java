package org.jetbrains.kotlin.fir.extensions;

import java.util.Collection;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00112\n\u0010\u0012\u001a\u00060\u0006j\u0002`\u0007H&J\"\u0010\u0013\u001a\u00020\u00142\n\u0010\u0012\u001a\u00060\u0006j\u0002`\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011H&J\u001a\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00052\u0006\u0010\u0018\u001a\u00020\u0019H&J\f\u0010\u001a\u001a\u00020\u0014H'b\u0002\b\u001bR\u001c\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirRegisteredPluginAnnotations;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "metaAnnotations", "getMetaAnnotations", "hasRegisteredAnnotations", Argument.Delimiters.none, "getHasRegisteredAnnotations", "()Z", "getAnnotationsWithMetaAnnotation", Argument.Delimiters.none, "metaAnnotation", "registerUserDefinedAnnotation", Argument.Delimiters.none, "annotationClasses", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getAnnotationsForPredicate", "predicate", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", "initialize", "Lorg/jetbrains/kotlin/fir/extensions/PluginServicesInitialization;", "Empty", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirRegisteredPluginAnnotations implements FirSessionComponent {

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\f\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\r2\n\u0010\u000e\u001a\u00060\u0006j\u0002`\u0007H\u0016J\"\u0010\u000f\u001a\u00020\u00102\n\u0010\u000e\u001a\u00060\u0006j\u0002`\u00072\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\u0016J\u001a\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\f\u0010\u0016\u001a\u00020\u0010H\u0017b\u0002\b\u0017R\u001e\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirRegisteredPluginAnnotations$Empty;", "Lorg/jetbrains/kotlin/fir/extensions/FirRegisteredPluginAnnotations;", "<init>", "()V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "metaAnnotations", "getMetaAnnotations", "getAnnotationsWithMetaAnnotation", Argument.Delimiters.none, "metaAnnotation", "registerUserDefinedAnnotation", Argument.Delimiters.none, "annotationClasses", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getAnnotationsForPredicate", "predicate", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", "initialize", "Lorg/jetbrains/kotlin/fir/extensions/PluginServicesInitialization;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Empty extends FirRegisteredPluginAnnotations {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
        public Set<FqName> getAnnotations() {
            return SetsKt.emptySet();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
        public Set<FqName> getAnnotationsForPredicate(DeclarationPredicate predicate) {
            predicate.getClass();
            return SetsKt.emptySet();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
        public Collection<FqName> getAnnotationsWithMetaAnnotation(FqName metaAnnotation) {
            metaAnnotation.getClass();
            return CollectionsKt.emptyList();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
        public Set<FqName> getMetaAnnotations() {
            return SetsKt.emptySet();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
        @PluginServicesInitialization
        public void initialize() throws KotlinNothingValueException {
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            throw new KotlinNothingValueException();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        @Override // org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotations
        public void registerUserDefinedAnnotation(FqName metaAnnotation, Collection<? extends FirRegularClass> annotationClasses) throws KotlinNothingValueException {
            metaAnnotation.getClass();
            annotationClasses.getClass();
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    public abstract Set<FqName> getAnnotations();

    public abstract Set<FqName> getAnnotationsForPredicate(DeclarationPredicate predicate);

    public abstract Collection<FqName> getAnnotationsWithMetaAnnotation(FqName metaAnnotation);

    public final boolean getHasRegisteredAnnotations() {
        return (getAnnotations().isEmpty() && getMetaAnnotations().isEmpty()) ? false : true;
    }

    public abstract Set<FqName> getMetaAnnotations();

    @PluginServicesInitialization
    public abstract void initialize();

    public abstract void registerUserDefinedAnnotation(FqName metaAnnotation, Collection<? extends FirRegularClass> annotationClasses);
}
