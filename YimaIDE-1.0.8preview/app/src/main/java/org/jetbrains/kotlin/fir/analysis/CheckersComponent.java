package org.jetbrains.kotlin.fir.analysis;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.analysis.checkers.LanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.config.ComposedLanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.ComposedDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ComposedExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.type.ComposedTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0005H\u0007b\u0002\b!J\u0014\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\fH\u0007b\u0002\b!J\u0014\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0013H\u0007b\u0002\b!J\u0014\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001aH\u0007b\u0002\b!J\u0014\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\"H\u0007b\u0002\b!R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\f¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0010\u001a\u00020\f¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\u0012\u001a\u00020\u0013¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0017\u001a\u00020\u0013¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\u0019\u001a\u00020\u001a¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001c¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/CheckersComponent;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "commonDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "getCommonDeclarationCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/ComposedDeclarationCheckers;", "platformDeclarationCheckers", "getPlatformDeclarationCheckers", "commonExpressionCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "getCommonExpressionCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ComposedExpressionCheckers;", "platformExpressionCheckers", "getPlatformExpressionCheckers", "commonTypeCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "getCommonTypeCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/ComposedTypeCheckers;", "platformTypeCheckers", "getPlatformTypeCheckers", "languageVersionSettingsCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;", "getLanguageVersionSettingsCheckers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/config/ComposedLanguageVersionSettingsCheckers;", "register", Argument.Delimiters.none, "checkers", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Lorg/jetbrains/kotlin/fir/analysis/extensions/FirAdditionalCheckersExtension;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckersComponent implements FirSessionComponent {
    private final ComposedDeclarationCheckers commonDeclarationCheckers;
    private final ComposedExpressionCheckers commonExpressionCheckers;
    private final ComposedTypeCheckers commonTypeCheckers;
    private final ComposedLanguageVersionSettingsCheckers languageVersionSettingsCheckers;
    private final ComposedDeclarationCheckers platformDeclarationCheckers;
    private final ComposedExpressionCheckers platformExpressionCheckers;
    private final ComposedTypeCheckers platformTypeCheckers;

    public CheckersComponent() {
        MppCheckerKind mppCheckerKind = MppCheckerKind.Common;
        this.commonDeclarationCheckers = new ComposedDeclarationCheckers(mppCheckerKind);
        MppCheckerKind mppCheckerKind2 = MppCheckerKind.Platform;
        this.platformDeclarationCheckers = new ComposedDeclarationCheckers(mppCheckerKind2);
        this.commonExpressionCheckers = new ComposedExpressionCheckers(mppCheckerKind);
        this.platformExpressionCheckers = new ComposedExpressionCheckers(mppCheckerKind2);
        this.commonTypeCheckers = new ComposedTypeCheckers(mppCheckerKind);
        this.platformTypeCheckers = new ComposedTypeCheckers(mppCheckerKind2);
        this.languageVersionSettingsCheckers = new ComposedLanguageVersionSettingsCheckers();
    }

    public final DeclarationCheckers getCommonDeclarationCheckers() {
        return this.commonDeclarationCheckers;
    }

    public final ExpressionCheckers getCommonExpressionCheckers() {
        return this.commonExpressionCheckers;
    }

    public final TypeCheckers getCommonTypeCheckers() {
        return this.commonTypeCheckers;
    }

    public final LanguageVersionSettingsCheckers getLanguageVersionSettingsCheckers() {
        return this.languageVersionSettingsCheckers;
    }

    public final DeclarationCheckers getPlatformDeclarationCheckers() {
        return this.platformDeclarationCheckers;
    }

    public final ExpressionCheckers getPlatformExpressionCheckers() {
        return this.platformExpressionCheckers;
    }

    public final TypeCheckers getPlatformTypeCheckers() {
        return this.platformTypeCheckers;
    }

    @SessionConfiguration
    public final void register(FirAdditionalCheckersExtension checkers) {
        checkers.getClass();
        register(checkers.getDeclarationCheckers());
        register(checkers.getExpressionCheckers());
        register(checkers.getTypeCheckers());
        register(checkers.getLanguageVersionSettingsCheckers());
    }

    @SessionConfiguration
    public final void register(ExpressionCheckers checkers) {
        checkers.getClass();
        this.commonExpressionCheckers.register(checkers);
        this.platformExpressionCheckers.register(checkers);
    }

    @SessionConfiguration
    public final void register(TypeCheckers checkers) {
        checkers.getClass();
        this.commonTypeCheckers.register(checkers);
        this.platformTypeCheckers.register(checkers);
    }

    @SessionConfiguration
    public final void register(LanguageVersionSettingsCheckers checkers) {
        checkers.getClass();
        this.languageVersionSettingsCheckers.register(checkers);
    }

    @SessionConfiguration
    public final void register(DeclarationCheckers checkers) {
        checkers.getClass();
        this.commonDeclarationCheckers.register(checkers);
        this.platformDeclarationCheckers.register(checkers);
    }
}
