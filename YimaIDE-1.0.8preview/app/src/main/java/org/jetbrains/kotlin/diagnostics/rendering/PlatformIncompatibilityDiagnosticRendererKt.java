package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.diagnostics.rendering.PlatformIncompatibilityDiagnosticRendererKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirPlatformIncompatibilityDiagnosticRendererKt;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\u001e\u0010\u0004\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001aZ\u0010\u000f\u001a\u00020\u0001*\u00060\u0002j\u0002`\u000320\u0010\u0010\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00020\u0007\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u00050\u00120\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\"\u000e\u0010\u0013\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"renderIncompatibilityInformation", Argument.Delimiters.none, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "map", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", Argument.Delimiters.none, "indent", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "mode", "Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;", "renderIncompatibleClassScopes", "unfulfilled", Argument.Delimiters.none, "Lkotlin/Pair;", "INDENTATION_UNIT", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PlatformIncompatibilityDiagnosticRendererKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderIncompatibilityInformation(StringBuilder sb, Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>> map, String str, RenderingContext renderingContext, MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        for (Map.Entry<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>> entry : map.entrySet()) {
            K1ExpectActualCompatibility.Incompatible.ClassScopes classScopes = (K1ExpectActualCompatibility.Incompatible) entry.getKey();
            Collection<? extends MemberDescriptor> value = entry.getValue();
            sb.append(str);
            sb.append("The following declaration");
            sb.append(value.size() == 1 ? " is" : "s are");
            sb.append(" incompatible");
            String reason = classScopes.getReason();
            if (reason != null) {
                sb.append(" because ".concat(reason));
            }
            sb.append(":");
            Collection<? extends MemberDescriptor> collection = value;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
            for (final MemberDescriptor memberDescriptor : collection) {
                final StringBuilder sb2 = sb;
                final String str2 = str;
                final RenderingContext renderingContext2 = renderingContext;
                final MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode2 = multiplatformDiagnosticRenderingMode;
                arrayList.add(new Function0() { // from class: i2b
                    public final Object invoke() {
                        return PlatformIncompatibilityDiagnosticRendererKt.renderIncompatibilityInformation$lambda$1$0(multiplatformDiagnosticRenderingMode2, sb2, memberDescriptor, renderingContext2, str2);
                    }
                });
            }
            StringBuilder sb3 = sb;
            String str3 = str;
            RenderingContext renderingContext3 = renderingContext;
            MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode3 = multiplatformDiagnosticRenderingMode;
            multiplatformDiagnosticRenderingMode3.renderList(sb3, arrayList);
            if (classScopes instanceof K1ExpectActualCompatibility.Incompatible.ClassScopes) {
                sb3.append(str3);
                sb3.append("No actual members are found for expected members listed below:");
                multiplatformDiagnosticRenderingMode3.newLine(sb3);
                renderIncompatibleClassScopes(sb3, classScopes.getUnfulfilled(), str3, renderingContext3, multiplatformDiagnosticRenderingMode3);
            }
            multiplatformDiagnosticRenderingMode = multiplatformDiagnosticRenderingMode3;
            sb = sb3;
            renderingContext = renderingContext3;
            str = str3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderIncompatibilityInformation$lambda$1$0(MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode, StringBuilder sb, MemberDescriptor memberDescriptor, RenderingContext renderingContext, String str) {
        multiplatformDiagnosticRenderingMode.renderDescriptor(sb, memberDescriptor, renderingContext, str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderIncompatibleClassScopes(final StringBuilder sb, final List<? extends Pair<? extends MemberDescriptor, ? extends Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>>> list, final String str, final RenderingContext renderingContext, final MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        IntRange indices = CollectionsKt.getIndices(list);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(indices, 10));
        IntIterator it = indices.iterator();
        while (it.hasNext()) {
            final int iNextInt = it.nextInt();
            arrayList.add(new Function0() { // from class: j2b
                public final Object invoke() {
                    return PlatformIncompatibilityDiagnosticRendererKt.renderIncompatibleClassScopes$lambda$0$0(list, iNextInt, multiplatformDiagnosticRenderingMode, sb, renderingContext, str);
                }
            });
        }
        multiplatformDiagnosticRenderingMode.renderList(sb, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderIncompatibleClassScopes$lambda$0$0(List list, int i, MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode, StringBuilder sb, RenderingContext renderingContext, String str) {
        Pair pair = (Pair) list.get(i);
        MemberDescriptor memberDescriptor = (MemberDescriptor) pair.component1();
        Map map = (Map) pair.component2();
        multiplatformDiagnosticRenderingMode.renderDescriptor(sb, memberDescriptor, renderingContext, str);
        if (!map.isEmpty()) {
            multiplatformDiagnosticRenderingMode.newLine(sb);
            renderIncompatibilityInformation(sb, map, str + FirPlatformIncompatibilityDiagnosticRendererKt.INDENTATION_UNIT, renderingContext, multiplatformDiagnosticRenderingMode);
        }
        if (i != CollectionsKt.getLastIndex(list)) {
            multiplatformDiagnosticRenderingMode.newLine(sb);
        }
        return Unit.INSTANCE;
    }
}
