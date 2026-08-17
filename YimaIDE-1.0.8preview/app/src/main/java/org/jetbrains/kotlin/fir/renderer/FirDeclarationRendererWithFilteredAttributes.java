package org.jetbrains.kotlin.fir.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005H\u0014¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRendererWithFilteredAttributes;", "Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRendererWithAttributes;", "<init>", "()V", "attributeTypesToIds", Argument.Delimiters.none, "Lkotlin/Pair;", Argument.Delimiters.none, Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationRendererWithFilteredAttributes extends FirDeclarationRendererWithAttributes {
    private static final Companion Companion = new Companion(null);
    private static final Set<String> IGNORED_ATTRIBUTES = SetsKt.hashSetOf(new String[]{"FirVersionRequirementsTableKey", "SourceElementKey", "KlibSourceFile", "KlibFileAnnotationsKey"});

    @Override // org.jetbrains.kotlin.fir.renderer.FirDeclarationRendererWithAttributes
    public List<Pair<String, Integer>> attributeTypesToIds() {
        List<Pair<String, Integer>> listAttributeTypesToIds = super.attributeTypesToIds();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listAttributeTypesToIds) {
            if (!IGNORED_ATTRIBUTES.contains(((Pair) obj).getFirst())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRendererWithFilteredAttributes$Companion;", Argument.Delimiters.none, "<init>", "()V", "IGNORED_ATTRIBUTES", Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
