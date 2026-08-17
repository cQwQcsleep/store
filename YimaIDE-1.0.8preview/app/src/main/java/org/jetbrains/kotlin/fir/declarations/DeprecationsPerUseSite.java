package org.jetbrains.kotlin.fir.declarations;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\r\u001a\u0004\u0018\u00010\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000f\"\u00020\u0006¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/DeprecationsPerUseSite;", Argument.Delimiters.none, "all", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "bySpecificSite", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;Ljava/util/Map;)V", "getAll", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "getBySpecificSite", "()Ljava/util/Map;", "forUseSite", "sites", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "isEmpty", Argument.Delimiters.none, "isNotEmpty", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeprecationsPerUseSite {
    private final FirDeprecationInfo all;
    private final Map<AnnotationUseSiteTarget, FirDeprecationInfo> bySpecificSite;

    /* JADX WARN: Multi-variable type inference failed */
    public DeprecationsPerUseSite(FirDeprecationInfo firDeprecationInfo, Map<AnnotationUseSiteTarget, ? extends FirDeprecationInfo> map) {
        this.all = firDeprecationInfo;
        this.bySpecificSite = map;
    }

    public final FirDeprecationInfo forUseSite(AnnotationUseSiteTarget... sites) {
        sites.getClass();
        if (this.bySpecificSite != null) {
            for (AnnotationUseSiteTarget annotationUseSiteTarget : sites) {
                FirDeprecationInfo firDeprecationInfo = this.bySpecificSite.get(annotationUseSiteTarget);
                if (firDeprecationInfo != null) {
                    return firDeprecationInfo;
                }
            }
        }
        return this.all;
    }

    public final FirDeprecationInfo getAll() {
        return this.all;
    }

    public final Map<AnnotationUseSiteTarget, FirDeprecationInfo> getBySpecificSite() {
        return this.bySpecificSite;
    }

    public final boolean isEmpty() {
        return this.all == null && this.bySpecificSite == null;
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public String toString() {
        if (isEmpty()) {
            return "NoDeprecation";
        }
        return "org.jetbrains.kotlin.fir.declarations.DeprecationInfoForUseSites(all=" + this.all + ", bySpecificSite=" + this.bySpecificSite + ')';
    }
}
