package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", ModuleXmlParser.NAME, Argument.Delimiters.none, "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "defaultPositioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "psiType", "Lkotlin/reflect/KClass;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactoryForDeprecation1<A> extends KtDiagnosticFactoryForDeprecation<KtDiagnosticFactory1<A>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtDiagnosticFactoryForDeprecation1(String str, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, KClass<?> kClass, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        super(str, languageFeature, new KtDiagnosticFactory1(str + "_WARNING", Severity.WARNING, abstractSourceElementPositioningStrategy, kClass, baseDiagnosticRendererFactory), new KtDiagnosticFactory1(str + "_ERROR", Severity.ERROR, abstractSourceElementPositioningStrategy, kClass, baseDiagnosticRendererFactory), null);
        str.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        kClass.getClass();
        baseDiagnosticRendererFactory.getClass();
    }
}
