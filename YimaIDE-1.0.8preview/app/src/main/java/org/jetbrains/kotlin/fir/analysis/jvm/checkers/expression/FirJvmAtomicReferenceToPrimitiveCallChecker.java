package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractAtomicReferenceToPrimitiveCallChecker;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmAtomicReferenceToPrimitiveCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractAtomicReferenceToPrimitiveCallChecker;", "<init>", "()V", "isDangerousAtomicCallParameterNameWithin", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmAtomicReferenceToPrimitiveCallChecker extends AbstractAtomicReferenceToPrimitiveCallChecker {
    public static final FirJvmAtomicReferenceToPrimitiveCallChecker INSTANCE = new FirJvmAtomicReferenceToPrimitiveCallChecker();

    /* JADX WARN: Illegal instructions before constructor call */
    private FirJvmAtomicReferenceToPrimitiveCallChecker() {
        Map<ClassId, ClassId> map = JvmStandardClassIds.atomicByPrimitive;
        MppCheckerKind mppCheckerKind = MppCheckerKind.Platform;
        JvmStandardClassIds.Callables callables = JvmStandardClassIds.Callables.INSTANCE;
        super(map, mppCheckerKind, callables.getAtomicReferenceCompareAndSet(), callables.getAtomicReferenceWeakCompareAndSet(), callables.getAtomicReferenceWeakCompareAndSetAcquire(), callables.getAtomicReferenceWeakCompareAndSetRelease(), callables.getAtomicReferenceWeakCompareAndSetPlain(), callables.getAtomicReferenceWeakCompareAndSetVolatile(), callables.getAtomicReferenceCompareAndExchange(), callables.getAtomicReferenceCompareAndExchangeAcquire(), callables.getAtomicReferenceCompareAndExchangeRelease());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractAtomicReferenceToPrimitiveCallChecker
    public boolean isDangerousAtomicCallParameterNameWithin(FirFunctionSymbol<?> function, Name name) {
        function.getClass();
        name.getClass();
        return super.isDangerousAtomicCallParameterNameWithin(function, name) || Intrinsics.areEqual(name, Name.identifier("p0")) || Intrinsics.areEqual(name, Name.identifier("p1"));
    }
}
