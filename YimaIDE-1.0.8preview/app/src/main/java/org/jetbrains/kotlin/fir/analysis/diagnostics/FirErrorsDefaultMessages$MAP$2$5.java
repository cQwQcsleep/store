package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrorsDefaultMessages$MAP$2$5;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"org/jetbrains/kotlin/fir/analysis/diagnostics/FirErrorsDefaultMessages$MAP$2$5", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "contextParametersKey", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", Argument.Delimiters.none, Argument.Delimiters.none, "getContextParametersKey", "()Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", "render", "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorsDefaultMessages$MAP$2$5 implements DiagnosticParameterRenderer<Boolean> {
    private final RenderingContext.Key<List<String>> contextParametersKey = new RenderingContext.Key<List<? extends String>>() { // from class: org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrorsDefaultMessages$MAP$2$5$contextParametersKey$1
        @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext.Key
        /* JADX INFO: renamed from: compute, reason: avoid collision after fix types in other method */
        public List<? extends String> compute2(Collection<? extends Object> objectsToRender, DiagnosticBaseContext diagnosticContext) {
            String identifier;
            objectsToRender.getClass();
            diagnosticContext.getClass();
            Object objLast = CollectionsKt.last(objectsToRender);
            List list = objLast instanceof List ? (List) objLast : null;
            if (list == null) {
                return CollectionsKt.emptyList();
            }
            List<FirValueParameterSymbol> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (FirValueParameterSymbol firValueParameterSymbol : list2) {
                if (firValueParameterSymbol.getName().isSpecial()) {
                    identifier = "contextOf<" + ConeTypeUtilsKt.renderReadable(firValueParameterSymbol.getResolvedReturnType()) + ">()";
                } else {
                    identifier = firValueParameterSymbol.getName().getIdentifier();
                    identifier.getClass();
                }
                arrayList.add(identifier);
            }
            return CollectionsKt.distinct(arrayList);
        }

        @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext.Key
        public /* bridge */ /* synthetic */ List<? extends String> compute(Collection collection, DiagnosticBaseContext diagnosticBaseContext) {
            return compute2((Collection<? extends Object>) collection, diagnosticBaseContext);
        }
    };

    public static CharSequence a(String str) {
        str.getClass();
        return "'" + str + '\'';
    }

    public static CharSequence b(String str) {
        str.getClass();
        return "'with(" + str + ") { ... }'";
    }

    public final RenderingContext.Key<List<String>> getContextParametersKey() {
        return this.contextParametersKey;
    }

    public String render(boolean obj, RenderingContext renderingContext) {
        renderingContext.getClass();
        List list = (List) renderingContext.get(this.contextParametersKey);
        if (obj) {
            return "Disambiguate the receiver by wrapping the call in 'with(this) { ... }' or " + CollectionsKt.joinToString$default(list, " / ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: t55
                public final Object invoke(Object obj2) {
                    return FirErrorsDefaultMessages$MAP$2$5.b((String) obj2);
                }
            }, 30, (Object) null) + '.';
        }
        return "Make the receiver explicit using 'this' or " + CollectionsKt.joinToString$default(list, " / ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: u55
            public final Object invoke(Object obj2) {
                return FirErrorsDefaultMessages$MAP$2$5.a((String) obj2);
            }
        }, 30, (Object) null) + '.';
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public /* bridge */ /* synthetic */ String render(Boolean bool, RenderingContext renderingContext) {
        return render(bool.booleanValue(), renderingContext);
    }
}
