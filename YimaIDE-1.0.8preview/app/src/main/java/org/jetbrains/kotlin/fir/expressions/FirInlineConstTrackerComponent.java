package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0010B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirInlineConstTrackerComponent;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "inlineConstTracker", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "<init>", "(Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;)V", "getInlineConstTracker", "()Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "report", Argument.Delimiters.none, "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "Default", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirInlineConstTrackerComponent implements FirSessionComponent {
    private final InlineConstTracker inlineConstTracker;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirInlineConstTrackerComponent$Default;", "Lorg/jetbrains/kotlin/fir/expressions/FirInlineConstTrackerComponent;", "<init>", "()V", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirInlineConstTrackerComponent {
        public static final Default INSTANCE = new Default();

        private Default() {
            super(null);
        }
    }

    public FirInlineConstTrackerComponent(InlineConstTracker inlineConstTracker) {
        this.inlineConstTracker = inlineConstTracker;
    }

    public final InlineConstTracker getInlineConstTracker() {
        return this.inlineConstTracker;
    }

    public final void report(FirField field, FirFile file, FirEvaluatorResult result) {
        KtSourceFile sourceFile;
        String path;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        ClassId classId;
        String strAsString;
        String strReplace$default;
        String strReplace$default2;
        field.getClass();
        result.getClass();
        if (this.inlineConstTracker == null || !(field.getOrigin() instanceof FirDeclarationOrigin.Java) || file == null || (sourceFile = file.getSourceFile()) == null || (path = sourceFile.getPath()) == null || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(field)) == null || (classId = coneClassLikeLookupTagContainingClassLookupTag.getClassId()) == null || (strAsString = classId.asString()) == null || (strReplace$default = StringsKt.replace$default(strAsString, ".", InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX, false, 4, (Object) null)) == null || (strReplace$default2 = StringsKt.replace$default(strReplace$default, "/", ".", false, 4, (Object) null)) == null || (result instanceof FirEvaluatorResult.CompileTimeException)) {
            return;
        }
        FirLiteralExpression firLiteralExpression = null;
        if (result instanceof FirEvaluatorResult.Evaluated) {
            FirElement result2 = ((FirEvaluatorResult.Evaluated) result).getResult();
            firLiteralExpression = (FirLiteralExpression) (result2 instanceof FirLiteralExpression ? result2 : null);
        }
        if (firLiteralExpression == null) {
            return;
        }
        InlineConstTracker inlineConstTracker = this.inlineConstTracker;
        String strAsString2 = field.getName().asString();
        strAsString2.getClass();
        inlineConstTracker.report(path, strReplace$default2, strAsString2, firLiteralExpression.getKind().getAsString());
    }
}
