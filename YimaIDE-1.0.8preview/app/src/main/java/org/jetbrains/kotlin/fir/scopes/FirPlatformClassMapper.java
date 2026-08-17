package org.jetbrains.kotlin.fir.scopes;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0014\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007H\u0017b\u0002\b\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0014\u0010\t\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH&J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH&R\u001e\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "createComposed", "Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "getCorrespondingPlatformClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getCorrespondingKotlinClass", "classTypealiasesThatDontCauseAmbiguity", Argument.Delimiters.none, "getClassTypealiasesThatDontCauseAmbiguity", "()Ljava/util/Map;", "Default", "Composed", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPlatformClassMapper implements FirComposableSessionComponent<FirPlatformClassMapper> {
    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirPlatformClassMapper> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract Map<ClassId, ClassId> getClassTypealiasesThatDontCauseAmbiguity();

    public abstract ClassId getCorrespondingKotlinClass(ClassId classId);

    public abstract FirRegularClass getCorrespondingPlatformClass(FirClassLikeDeclaration declaration);

    public abstract ClassId getCorrespondingPlatformClass(ClassId classId);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\u0014\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper$Default;", "Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper;", "<init>", "()V", "getCorrespondingPlatformClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getCorrespondingKotlinClass", "classTypealiasesThatDontCauseAmbiguity", Argument.Delimiters.none, "getClassTypealiasesThatDontCauseAmbiguity", "()Ljava/util/Map;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirPlatformClassMapper {
        public static final Default INSTANCE = new Default();
        private static final Map<ClassId, ClassId> classTypealiasesThatDontCauseAmbiguity = MapsKt.emptyMap();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public Map<ClassId, ClassId> getClassTypealiasesThatDontCauseAmbiguity() {
            return classTypealiasesThatDontCauseAmbiguity;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public ClassId getCorrespondingKotlinClass(ClassId classId) {
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public FirRegularClass getCorrespondingPlatformClass(FirClassLikeDeclaration declaration) {
            declaration.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public ClassId getCorrespondingPlatformClass(ClassId classId) {
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirPlatformClassMapper>) list);
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\t\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR'\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00118VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper$Composed;", "Lorg/jetbrains/kotlin/fir/scopes/FirPlatformClassMapper;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "getCorrespondingPlatformClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getCorrespondingKotlinClass", "classTypealiasesThatDontCauseAmbiguity", Argument.Delimiters.none, "getClassTypealiasesThatDontCauseAmbiguity", "()Ljava/util/Map;", "classTypealiasesThatDontCauseAmbiguity$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirPlatformClassMapper implements FirComposableSessionComponent.Composed<FirPlatformClassMapper> {

        /* JADX INFO: renamed from: classTypealiasesThatDontCauseAmbiguity$delegate, reason: from kotlin metadata */
        private final Lazy classTypealiasesThatDontCauseAmbiguity;
        private final List<FirPlatformClassMapper> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirPlatformClassMapper> list) {
            list.getClass();
            this.components = list;
            this.classTypealiasesThatDontCauseAmbiguity = LazyKt.lazy(new Function0() { // from class: wb5
                public final Object invoke() {
                    return FirPlatformClassMapper.Composed.a(this.b);
                }
            });
        }

        public static Map a(Composed composed) {
            Map mapCreateMapBuilder = MapsKt.createMapBuilder();
            Iterator<T> it = composed.getComponents().iterator();
            while (it.hasNext()) {
                mapCreateMapBuilder.putAll(((FirPlatformClassMapper) it.next()).getClassTypealiasesThatDontCauseAmbiguity());
            }
            return MapsKt.build(mapCreateMapBuilder);
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public Map<ClassId, ClassId> getClassTypealiasesThatDontCauseAmbiguity() {
            return (Map) this.classTypealiasesThatDontCauseAmbiguity.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirPlatformClassMapper> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public ClassId getCorrespondingKotlinClass(ClassId classId) {
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                ClassId correspondingKotlinClass = ((FirPlatformClassMapper) it.next()).getCorrespondingKotlinClass(classId);
                if (correspondingKotlinClass != null) {
                    return correspondingKotlinClass;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public FirRegularClass getCorrespondingPlatformClass(FirClassLikeDeclaration declaration) {
            declaration.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                FirRegularClass correspondingPlatformClass = ((FirPlatformClassMapper) it.next()).getCorrespondingPlatformClass(declaration);
                if (correspondingPlatformClass != null) {
                    return correspondingPlatformClass;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapper
        public ClassId getCorrespondingPlatformClass(ClassId classId) {
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                ClassId correspondingPlatformClass = ((FirPlatformClassMapper) it.next()).getCorrespondingPlatformClass(classId);
                if (correspondingPlatformClass != null) {
                    return correspondingPlatformClass;
                }
            }
            return null;
        }
    }
}
