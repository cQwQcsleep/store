package org.jetbrains.kotlin.backend.common.linkage;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0011J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H\u0017J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/IrDeserializer;", "Lorg/jetbrains/kotlin/ir/IrProvider;", "init", "", "moduleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "resolveBySignatureInModule", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "kind", "Lorg/jetbrains/kotlin/backend/common/linkage/IrDeserializer$TopLevelSymbolKind;", "moduleName", "Lorg/jetbrains/kotlin/name/Name;", "postProcess", "inOrAfterLinkageStep", "", "TopLevelSymbolKind", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface IrDeserializer extends IrProvider {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/IrDeserializer$TopLevelSymbolKind;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "FUNCTION_SYMBOL", "CLASS_SYMBOL", "PROPERTY_SYMBOL", "TYPEALIAS_SYMBOL", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum TopLevelSymbolKind {
        FUNCTION_SYMBOL,
        CLASS_SYMBOL,
        PROPERTY_SYMBOL,
        TYPEALIAS_SYMBOL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<TopLevelSymbolKind> getEntries() {
            return $ENTRIES;
        }
    }

    void init(IrModuleFragment moduleFragment);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use postProcess(inOrAfterLinkageStep) instead", replaceWith = @ReplaceWith(expression = "postProcess(inOrAfterLinkageStep = true)", imports = {}))
    default void postProcess() {
        postProcess(true);
    }

    void postProcess(boolean inOrAfterLinkageStep);

    IrSymbol resolveBySignatureInModule(IdSignature signature, TopLevelSymbolKind kind, Name moduleName);
}
