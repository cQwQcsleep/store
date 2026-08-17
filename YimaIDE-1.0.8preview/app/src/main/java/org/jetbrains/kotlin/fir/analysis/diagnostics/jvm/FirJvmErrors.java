package org.jetbrains.kotlin.fir.analysis.diagnostics.jvm;

import com.intellij.psi.PsiElement;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtCallableDeclaration;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtTypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u001e\n\u0002\b:\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010ù\u0001\u001a\u00030ú\u0001H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R%\u00100\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u000302\u0012\b\u0012\u0006\u0012\u0002\b\u00030201¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R#\u00105\u001a\u0014\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020706¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001d\u0010;\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002070<¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0011\u0010?\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0007R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020C0B¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010F\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0007R\u001d\u0010H\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I01¢\u0006\b\n\u0000\u001a\u0004\bJ\u00104R#\u0010K\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020806¢\u0006\b\n\u0000\u001a\u0004\bL\u0010:R\u001d\u0010M\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020N01¢\u0006\b\n\u0000\u001a\u0004\bO\u00104R#\u0010P\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020806¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010:R#\u0010R\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020806¢\u0006\b\n\u0000\u001a\u0004\bS\u0010:R\u001d\u0010T\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I01¢\u0006\b\n\u0000\u001a\u0004\bU\u00104R\u001d\u0010V\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I01¢\u0006\b\n\u0000\u001a\u0004\bW\u00104R\u0017\u0010X\u001a\b\u0012\u0004\u0012\u00020I0B¢\u0006\b\n\u0000\u001a\u0004\bY\u0010ER\u0011\u0010Z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0007R#\u0010\\\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I06¢\u0006\b\n\u0000\u001a\u0004\b]\u0010:R#\u0010^\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I06¢\u0006\b\n\u0000\u001a\u0004\b_\u0010:R\u0011\u0010`\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\u0007R\u0011\u0010b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bc\u0010\u0007R\u0011\u0010d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\be\u0010\u0007R\u0011\u0010f\u001a\u00020g¢\u0006\b\n\u0000\u001a\u0004\bh\u0010iR\u0011\u0010j\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bk\u0010\u0007R\u0011\u0010l\u001a\u00020g¢\u0006\b\n\u0000\u001a\u0004\bm\u0010iR\u0011\u0010n\u001a\u00020g¢\u0006\b\n\u0000\u001a\u0004\bo\u0010iR\u0011\u0010p\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\u0007R\u0011\u0010r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bs\u0010\u0007R\u0011\u0010t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bu\u0010\u0007R\u0011\u0010v\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\u0007R\u0011\u0010x\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\by\u0010\u0007R\u0011\u0010z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b{\u0010\u0007R\u0017\u0010|\u001a\b\u0012\u0004\u0012\u00020}0B¢\u0006\b\n\u0000\u001a\u0004\b~\u0010ER\u0012\u0010\u007f\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0007R\u0013\u0010\u0081\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0007R\u0013\u0010\u0083\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0007R\u0013\u0010\u0085\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\u0007R\u001f\u0010\u0087\u0001\u001a\u000e\u0012\u0004\u0012\u00020}\u0012\u0004\u0012\u00020}01¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u00104R\u0013\u0010\u0089\u0001\u001a\u00020g¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010iR\u0013\u0010\u008b\u0001\u001a\u00020g¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010iR-\u0010\u008d\u0001\u001a\u001c\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002080\u008e\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002080\u008e\u000101¢\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u00104R\u0013\u0010\u0090\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0007R\u0013\u0010\u0092\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0093\u0001\u0010\u0007R\u001f\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020I01¢\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u00104R\u0013\u0010\u0096\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0007R\u0013\u0010\u0098\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0007R\u0013\u0010\u009a\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\u0007R\u0013\u0010\u009c\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010\u0007R\u0013\u0010\u009e\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009f\u0001\u0010\u0007R\u0013\u0010 \u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¡\u0001\u0010\u0007R\u0013\u0010¢\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b£\u0001\u0010\u0007R\u0013\u0010¤\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¥\u0001\u0010\u0007R\u0013\u0010¦\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b§\u0001\u0010\u0007R\u0013\u0010¨\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b©\u0001\u0010\u0007R\u0019\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00020I0B¢\u0006\t\n\u0000\u001a\u0005\b«\u0001\u0010ER\u0013\u0010¬\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u00ad\u0001\u0010\u0007R\u0013\u0010®\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¯\u0001\u0010\u0007R\u0019\u0010°\u0001\u001a\b\u0012\u0004\u0012\u0002080B¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010ER\u0013\u0010²\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b³\u0001\u0010\u0007R\u001f\u0010´\u0001\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020801¢\u0006\t\n\u0000\u001a\u0005\bµ\u0001\u00104R\u0013\u0010¶\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b·\u0001\u0010\u0007R\u0013\u0010¸\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¹\u0001\u0010\u0007R\u0013\u0010º\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b»\u0001\u0010\u0007R\u0013\u0010¼\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b½\u0001\u0010\u0007R\u0013\u0010¾\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b¿\u0001\u0010\u0007R\u0013\u0010À\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÁ\u0001\u0010\u0007R\u0013\u0010Â\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÃ\u0001\u0010\u0007R\u001f\u0010Ä\u0001\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020N01¢\u0006\t\n\u0000\u001a\u0005\bÅ\u0001\u00104R\u001f\u0010Æ\u0001\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020N01¢\u0006\t\n\u0000\u001a\u0005\bÇ\u0001\u00104R \u0010È\u0001\u001a\u000f\u0012\u0004\u0012\u00020N\u0012\u0005\u0012\u00030É\u000101¢\u0006\t\n\u0000\u001a\u0005\bÊ\u0001\u00104R-\u0010Ë\u0001\u001a\u001b\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u0002080Ì\u0001¢\u0006\n\n\u0000\u001a\u0006\bÍ\u0001\u0010Î\u0001R\u001f\u0010Ï\u0001\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020N01¢\u0006\t\n\u0000\u001a\u0005\bÐ\u0001\u00104R\u0013\u0010Ñ\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bÒ\u0001\u0010\u0007R\u001d\u0010Ó\u0001\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003020B¢\u0006\t\n\u0000\u001a\u0005\bÔ\u0001\u0010ER\u001f\u0010Õ\u0001\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020801¢\u0006\t\n\u0000\u001a\u0005\bÖ\u0001\u00104R\u0019\u0010×\u0001\u001a\b\u0012\u0004\u0012\u0002080B¢\u0006\t\n\u0000\u001a\u0005\bØ\u0001\u0010ER\u0019\u0010Ù\u0001\u001a\b\u0012\u0004\u0012\u0002080B¢\u0006\t\n\u0000\u001a\u0005\bÚ\u0001\u0010ER\u0019\u0010Û\u0001\u001a\b\u0012\u0004\u0012\u00020I0B¢\u0006\t\n\u0000\u001a\u0005\bÜ\u0001\u0010ER\u0019\u0010Ý\u0001\u001a\b\u0012\u0004\u0012\u00020I0B¢\u0006\t\n\u0000\u001a\u0005\bÞ\u0001\u0010ER\u001b\u0010ß\u0001\u001a\t\u0012\u0004\u0012\u00020I0à\u0001¢\u0006\n\n\u0000\u001a\u0006\bá\u0001\u0010â\u0001R\u0013\u0010ã\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bä\u0001\u0010\u0007R\u0013\u0010å\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bæ\u0001\u0010\u0007R\u0013\u0010ç\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bè\u0001\u0010\u0007R\u0013\u0010é\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bê\u0001\u0010\u0007R\u0013\u0010ë\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bì\u0001\u0010\u0007R\u0013\u0010í\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\bî\u0001\u0010\u0007R \u0010ï\u0001\u001a\u000f\u0012\u0004\u0012\u000207\u0012\u0005\u0012\u00030É\u000101¢\u0006\t\n\u0000\u001a\u0005\bð\u0001\u00104R\u001a\u0010ñ\u0001\u001a\t\u0012\u0005\u0012\u00030ò\u00010B¢\u0006\t\n\u0000\u001a\u0005\bó\u0001\u0010ER\u001e\u0010ô\u0001\u001a\r\u0012\t\u0012\u0007\u0012\u0002\b\u00030õ\u00010B¢\u0006\t\n\u0000\u001a\u0005\bö\u0001\u0010ER\u0019\u0010÷\u0001\u001a\b\u0012\u0004\u0012\u0002080B¢\u0006\t\n\u0000\u001a\u0005\bø\u0001\u0010E¨\u0006û\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/jvm/FirJvmErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "OVERRIDE_CANNOT_BE_STATIC", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getOVERRIDE_CANNOT_BE_STATIC", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "JVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION", "getJVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION", "JVM_STATIC_NOT_IN_OBJECT_OR_COMPANION", "getJVM_STATIC_NOT_IN_OBJECT_OR_COMPANION", "JVM_STATIC_ON_NON_PUBLIC_MEMBER", "getJVM_STATIC_ON_NON_PUBLIC_MEMBER", "JVM_STATIC_ON_CONST_OR_JVM_FIELD", "getJVM_STATIC_ON_CONST_OR_JVM_FIELD", "JVM_STATIC_ON_EXTERNAL_IN_INTERFACE", "getJVM_STATIC_ON_EXTERNAL_IN_INTERFACE", "INAPPLICABLE_JVM_NAME", "getINAPPLICABLE_JVM_NAME", "ILLEGAL_JVM_NAME", "getILLEGAL_JVM_NAME", "FUNCTION_DELEGATE_MEMBER_NAME_CLASH", "getFUNCTION_DELEGATE_MEMBER_NAME_CLASH", "VALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION", "getVALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION", "JVM_INLINE_WITHOUT_VALUE_CLASS", "getJVM_INLINE_WITHOUT_VALUE_CLASS", "INAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME", "getINAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME", "USELESS_JVM_EXPOSE_BOXED", "getUSELESS_JVM_EXPOSE_BOXED", "JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND", "getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND", "JVM_EXPOSE_BOXED_REQUIRES_NAME", "getJVM_EXPOSE_BOXED_REQUIRES_NAME", "JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME", "getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME", "JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME", "getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME", "JVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT", "getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT", "JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC", "getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC", "JVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS", "getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS", "JVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED", "getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED", "WRONG_TYPE_FOR_JAVA_OVERRIDE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getWRONG_TYPE_FOR_JAVA_OVERRIDE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "ACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", Argument.Delimiters.none, "getACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "IMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;", "getIMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;", "NOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION", "getNOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION", "PROPERTY_HIDES_JAVA_FIELD", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "getPROPERTY_HIDES_JAVA_FIELD", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "CONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION", "getCONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION", "JAVA_TYPE_MISMATCH", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getJAVA_TYPE_MISMATCH", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "getRECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "Lorg/jetbrains/kotlin/name/ClassId;", "getRECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA", "getNULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA", "TYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES", "getTYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES", "JAVA_CLASS_ON_COMPANION", "getJAVA_CLASS_ON_COMPANION", "UNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS", "getUNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS", "UPPER_BOUND_CANNOT_BE_ARRAY", "getUPPER_BOUND_CANNOT_BE_ARRAY", "UPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS", "getUPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS", "UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS", "getUPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS", "STRICTFP_ON_CLASS", "getSTRICTFP_ON_CLASS", "SYNCHRONIZED_ON_ABSTRACT", "getSYNCHRONIZED_ON_ABSTRACT", "SYNCHRONIZED_IN_INTERFACE", "getSYNCHRONIZED_IN_INTERFACE", "SYNCHRONIZED_IN_ANNOTATION", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation0;", "getSYNCHRONIZED_IN_ANNOTATION", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation0;", "SYNCHRONIZED_ON_INLINE", "getSYNCHRONIZED_ON_INLINE", "SYNCHRONIZED_ON_VALUE_CLASS", "getSYNCHRONIZED_ON_VALUE_CLASS", "SYNCHRONIZED_ON_SUSPEND", "getSYNCHRONIZED_ON_SUSPEND", "OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS", "getOVERLOADS_WITHOUT_DEFAULT_ARGUMENTS", "OVERLOADS_ABSTRACT", "getOVERLOADS_ABSTRACT", "OVERLOADS_INTERFACE", "getOVERLOADS_INTERFACE", "OVERLOADS_LOCAL", "getOVERLOADS_LOCAL", "OVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR", "getOVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR", "OVERLOADS_PRIVATE", "getOVERLOADS_PRIVATE", "DEPRECATED_JAVA_ANNOTATION", "Lorg/jetbrains/kotlin/name/FqName;", "getDEPRECATED_JAVA_ANNOTATION", "JVM_PACKAGE_NAME_CANNOT_BE_EMPTY", "getJVM_PACKAGE_NAME_CANNOT_BE_EMPTY", "JVM_PACKAGE_NAME_MUST_BE_VALID_NAME", "getJVM_PACKAGE_NAME_MUST_BE_VALID_NAME", "JVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES", "getJVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES", "POSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION", "getPOSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION", "REDUNDANT_REPEATABLE_ANNOTATION", "getREDUNDANT_REPEATABLE_ANNOTATION", "THROWS_IN_ANNOTATION", "getTHROWS_IN_ANNOTATION", "JVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS", "getJVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS", "INCOMPATIBLE_ANNOTATION_TARGETS", Argument.Delimiters.none, "getINCOMPATIBLE_ANNOTATION_TARGETS", "ANNOTATION_TARGETS_ONLY_IN_JAVA", "getANNOTATION_TARGETS_ONLY_IN_JAVA", "INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER", "getINTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER", "JAVA_CLASS_INHERITS_KT_PRIVATE_CLASS", "getJAVA_CLASS_INHERITS_KT_PRIVATE_CLASS", "LOCAL_JVM_RECORD", "getLOCAL_JVM_RECORD", "NON_FINAL_JVM_RECORD", "getNON_FINAL_JVM_RECORD", "ENUM_JVM_RECORD", "getENUM_JVM_RECORD", "JVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS", "getJVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS", "NON_DATA_CLASS_JVM_RECORD", "getNON_DATA_CLASS_JVM_RECORD", "JVM_RECORD_NOT_VAL_PARAMETER", "getJVM_RECORD_NOT_VAL_PARAMETER", "JVM_RECORD_NOT_LAST_VARARG_PARAMETER", "getJVM_RECORD_NOT_LAST_VARARG_PARAMETER", "INNER_JVM_RECORD", "getINNER_JVM_RECORD", "FIELD_IN_JVM_RECORD", "getFIELD_IN_JVM_RECORD", "DELEGATION_BY_IN_JVM_RECORD", "getDELEGATION_BY_IN_JVM_RECORD", "JVM_RECORD_EXTENDS_CLASS", "getJVM_RECORD_EXTENDS_CLASS", "ILLEGAL_JAVA_LANG_RECORD_SUPERTYPE", "getILLEGAL_JAVA_LANG_RECORD_SUPERTYPE", "JVM_RECORDS_ILLEGAL_BYTECODE_TARGET", "getJVM_RECORDS_ILLEGAL_BYTECODE_TARGET", "JAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE", "getJAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE", "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE", "getJAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE", "JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE", "getJAVA_MODULE_DOES_NOT_EXPORT_PACKAGE", "JVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE", "getJVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE", "JVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE", "getJVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE", "EXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT", "getEXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT", "EXTERNAL_DECLARATION_CANNOT_HAVE_BODY", "getEXTERNAL_DECLARATION_CANNOT_HAVE_BODY", "EXTERNAL_DECLARATION_IN_INTERFACE", "getEXTERNAL_DECLARATION_IN_INTERFACE", "EXTERNAL_DECLARATION_CANNOT_BE_INLINED", "getEXTERNAL_DECLARATION_CANNOT_BE_INLINED", "NON_SOURCE_REPEATED_ANNOTATION", "getNON_SOURCE_REPEATED_ANNOTATION", "REPEATED_ANNOTATION_WITH_CONTAINER", "getREPEATED_ANNOTATION_WITH_CONTAINER", "REPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR", "getREPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR", "REPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR", "Lorg/jetbrains/kotlin/name/Name;", "getREPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR", "REPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "getREPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "REPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR", "getREPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR", "REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR", "getREPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR", "SUSPENSION_POINT_INSIDE_CRITICAL_SECTION", "getSUSPENSION_POINT_INSIDE_CRITICAL_SECTION", "INLINE_FROM_HIGHER_PLATFORM", "getINLINE_FROM_HIGHER_PLATFORM", "INAPPLICABLE_JVM_FIELD", "getINAPPLICABLE_JVM_FIELD", "INAPPLICABLE_JVM_FIELD_WARNING", "getINAPPLICABLE_JVM_FIELD_WARNING", "IDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE", "getIDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE", "SYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS", "getSYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS", "SYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "getSYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "JVM_SYNTHETIC_ON_DELEGATE", "getJVM_SYNTHETIC_ON_DELEGATE", "SUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC", "getSUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC", "CONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR", "getCONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR", "SPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR", "getSPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR", "JAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE", "getJAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE", "NO_REFLECTION_IN_CLASS_PATH", "getNO_REFLECTION_IN_CLASS_PATH", "SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN", "getSYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN", "JAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getJAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY", "MISSING_BUILT_IN_DECLARATION", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getMISSING_BUILT_IN_DECLARATION", "DANGEROUS_CHARACTERS", "getDANGEROUS_CHARACTERS", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmErrors extends KtDiagnosticsContainer {
    private static final KtDiagnosticFactory3<FirNamedFunctionSymbol, String, FirNamedFunctionSymbol> ACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE;
    private static final KtDiagnosticFactory0 ANNOTATION_TARGETS_ONLY_IN_JAVA;
    private static final KtDiagnosticFactory0 CONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR;
    private static final KtDiagnosticFactory0 CONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION;
    private static final KtDiagnosticFactory1<String> DANGEROUS_CHARACTERS;
    private static final KtDiagnosticFactory0 DELEGATION_BY_IN_JVM_RECORD;
    private static final KtDiagnosticFactory1<FqName> DEPRECATED_JAVA_ANNOTATION;
    private static final KtDiagnosticFactory0 ENUM_JVM_RECORD;
    private static final KtDiagnosticFactory0 EXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT;
    private static final KtDiagnosticFactory0 EXTERNAL_DECLARATION_CANNOT_BE_INLINED;
    private static final KtDiagnosticFactory0 EXTERNAL_DECLARATION_CANNOT_HAVE_BODY;
    private static final KtDiagnosticFactory0 EXTERNAL_DECLARATION_IN_INTERFACE;
    private static final KtDiagnosticFactory0 FIELD_IN_JVM_RECORD;
    private static final KtDiagnosticFactory0 FUNCTION_DELEGATE_MEMBER_NAME_CLASH;
    private static final KtDiagnosticFactory1<ConeKotlinType> IDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE;
    private static final KtDiagnosticFactory0 ILLEGAL_JAVA_LANG_RECORD_SUPERTYPE;
    private static final KtDiagnosticFactory0 ILLEGAL_JVM_NAME;
    private static final KtDiagnosticFactoryForDeprecation2<FirNamedFunctionSymbol, FirNamedFunctionSymbol> IMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE;
    private static final KtDiagnosticFactory0 INAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME;
    private static final KtDiagnosticFactory1<String> INAPPLICABLE_JVM_FIELD;
    private static final KtDiagnosticFactory1<String> INAPPLICABLE_JVM_FIELD_WARNING;
    private static final KtDiagnosticFactory0 INAPPLICABLE_JVM_NAME;
    private static final KtDiagnosticFactory2<Collection<String>, Collection<String>> INCOMPATIBLE_ANNOTATION_TARGETS;
    private static final KtDiagnosticFactory2<String, String> INLINE_FROM_HIGHER_PLATFORM;
    private static final KtDiagnosticFactory0 INNER_JVM_RECORD;
    public static final FirJvmErrors INSTANCE;
    private static final KtDiagnosticFactory0 INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER;
    private static final KtDiagnosticFactory2<ClassId, ConeKotlinType> JAVA_CLASS_INHERITS_KT_PRIVATE_CLASS;
    private static final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> JAVA_CLASS_ON_COMPANION;
    private static final KtDiagnosticFactory1<FirPropertySymbol> JAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY;
    private static final KtDiagnosticFactory1<String> JAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE;
    private static final KtDiagnosticFactory2<String, String> JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE;
    private static final KtDiagnosticFactory0 JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE;
    private static final KtDiagnosticFactory0 JAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE;
    private static final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> JAVA_TYPE_MISMATCH;
    private static final KtDiagnosticFactory0 JVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE;
    private static final KtDiagnosticFactory0 JVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC;
    private static final KtDiagnosticFactory0 JVM_EXPOSE_BOXED_REQUIRES_NAME;
    private static final KtDiagnosticFactory0 JVM_INLINE_WITHOUT_VALUE_CLASS;
    private static final KtDiagnosticFactory0 JVM_PACKAGE_NAME_CANNOT_BE_EMPTY;
    private static final KtDiagnosticFactory0 JVM_PACKAGE_NAME_MUST_BE_VALID_NAME;
    private static final KtDiagnosticFactory0 JVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES;
    private static final KtDiagnosticFactory0 JVM_RECORDS_ILLEGAL_BYTECODE_TARGET;
    private static final KtDiagnosticFactory1<ConeKotlinType> JVM_RECORD_EXTENDS_CLASS;
    private static final KtDiagnosticFactory0 JVM_RECORD_NOT_LAST_VARARG_PARAMETER;
    private static final KtDiagnosticFactory0 JVM_RECORD_NOT_VAL_PARAMETER;
    private static final KtDiagnosticFactory0 JVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS;
    private static final KtDiagnosticFactoryForDeprecation0 JVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS;
    private static final KtDiagnosticFactory0 JVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION;
    private static final KtDiagnosticFactory0 JVM_STATIC_NOT_IN_OBJECT_OR_COMPANION;
    private static final KtDiagnosticFactory0 JVM_STATIC_ON_CONST_OR_JVM_FIELD;
    private static final KtDiagnosticFactory0 JVM_STATIC_ON_EXTERNAL_IN_INTERFACE;
    private static final KtDiagnosticFactory0 JVM_STATIC_ON_NON_PUBLIC_MEMBER;
    private static final KtDiagnosticFactory0 JVM_SYNTHETIC_ON_DELEGATE;
    private static final KtDiagnosticFactory0 LOCAL_JVM_RECORD;
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> MISSING_BUILT_IN_DECLARATION;
    private static final KtDiagnosticFactory0 NON_DATA_CLASS_JVM_RECORD;
    private static final KtDiagnosticFactory0 NON_FINAL_JVM_RECORD;
    private static final KtDiagnosticFactory0 NON_SOURCE_REPEATED_ANNOTATION;
    private static final KtDiagnosticFactory0 NOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION;
    private static final KtDiagnosticFactory0 NO_REFLECTION_IN_CLASS_PATH;
    private static final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> NULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA;
    private static final KtDiagnosticFactory0 OVERLOADS_ABSTRACT;
    private static final KtDiagnosticFactory0 OVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR;
    private static final KtDiagnosticFactory0 OVERLOADS_INTERFACE;
    private static final KtDiagnosticFactory0 OVERLOADS_LOCAL;
    private static final KtDiagnosticFactory0 OVERLOADS_PRIVATE;
    private static final KtDiagnosticFactory0 OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS;
    private static final KtDiagnosticFactory0 OVERRIDE_CANNOT_BE_STATIC;
    private static final KtDiagnosticFactory0 POSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION;
    private static final KtDiagnosticFactory1<FirFieldSymbol> PROPERTY_HIDES_JAVA_FIELD;
    private static final KtDiagnosticFactory2<ConeKotlinType, ClassId> RECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS;
    private static final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS;
    private static final KtDiagnosticFactory2<FqName, FqName> REDUNDANT_REPEATABLE_ANNOTATION;
    private static final KtDiagnosticFactory0 REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR;
    private static final KtDiagnosticFactory2<ClassId, Name> REPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR;
    private static final KtDiagnosticFactory4<ClassId, String, ClassId, String> REPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR;
    private static final KtDiagnosticFactory2<ClassId, ClassId> REPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR;
    private static final KtDiagnosticFactory2<ClassId, ClassId> REPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR;
    private static final KtDiagnosticFactory2<ClassId, ClassId> REPEATED_ANNOTATION_WITH_CONTAINER;
    private static final KtDiagnosticFactory0 SPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR;
    private static final KtDiagnosticFactory0 STRICTFP_ON_CLASS;
    private static final KtDiagnosticFactory0 SUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC;
    private static final KtDiagnosticFactory1<FirCallableSymbol<?>> SUSPENSION_POINT_INSIDE_CRITICAL_SECTION;
    private static final KtDiagnosticFactory1<ConeKotlinType> SYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS;
    private static final KtDiagnosticFactoryForDeprecation1<ConeKotlinType> SYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE;
    private static final KtDiagnosticFactoryForDeprecation0 SYNCHRONIZED_IN_ANNOTATION;
    private static final KtDiagnosticFactory0 SYNCHRONIZED_IN_INTERFACE;
    private static final KtDiagnosticFactory0 SYNCHRONIZED_ON_ABSTRACT;
    private static final KtDiagnosticFactory0 SYNCHRONIZED_ON_INLINE;
    private static final KtDiagnosticFactoryForDeprecation0 SYNCHRONIZED_ON_SUSPEND;
    private static final KtDiagnosticFactoryForDeprecation0 SYNCHRONIZED_ON_VALUE_CLASS;
    private static final KtDiagnosticFactory2<FirNamedFunctionSymbol, Name> SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN;
    private static final KtDiagnosticFactoryForDeprecation0 THROWS_IN_ANNOTATION;
    private static final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS;
    private static final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> TYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES;
    private static final KtDiagnosticFactory1<ConeKotlinType> UNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS;
    private static final KtDiagnosticFactory0 UPPER_BOUND_CANNOT_BE_ARRAY;
    private static final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> UPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS;
    private static final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS;
    private static final KtDiagnosticFactory0 USELESS_JVM_EXPOSE_BOXED;
    private static final KtDiagnosticFactory0 VALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION;
    private static final KtDiagnosticFactory2<FirCallableSymbol<?>, FirCallableSymbol<?>> WRONG_TYPE_FOR_JAVA_OVERRIDE;

    static {
        FirJvmErrors firJvmErrors = new FirJvmErrors();
        INSTANCE = firJvmErrors;
        Severity severity = Severity.ERROR;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        OVERRIDE_CANNOT_BE_STATIC = new KtDiagnosticFactory0("OVERRIDE_CANNOT_BE_STATIC", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION = new KtDiagnosticFactory0("JVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_STATIC_NOT_IN_OBJECT_OR_COMPANION = new KtDiagnosticFactory0("JVM_STATIC_NOT_IN_OBJECT_OR_COMPANION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_STATIC_ON_NON_PUBLIC_MEMBER = new KtDiagnosticFactory0("JVM_STATIC_ON_NON_PUBLIC_MEMBER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_STATIC_ON_CONST_OR_JVM_FIELD = new KtDiagnosticFactory0("JVM_STATIC_ON_CONST_OR_JVM_FIELD", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_STATIC_ON_EXTERNAL_IN_INTERFACE = new KtDiagnosticFactory0("JVM_STATIC_ON_EXTERNAL_IN_INTERFACE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        INAPPLICABLE_JVM_NAME = new KtDiagnosticFactory0("INAPPLICABLE_JVM_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        ILLEGAL_JVM_NAME = new KtDiagnosticFactory0("ILLEGAL_JVM_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        FUNCTION_DELEGATE_MEMBER_NAME_CLASH = new KtDiagnosticFactory0("FUNCTION_DELEGATE_MEMBER_NAME_CLASH", severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        VALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION = new KtDiagnosticFactory0("VALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_INLINE_WITHOUT_VALUE_CLASS = new KtDiagnosticFactory0("JVM_INLINE_WITHOUT_VALUE_CLASS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        INAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME = new KtDiagnosticFactory0("INAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        Severity severity2 = Severity.WARNING;
        USELESS_JVM_EXPOSE_BOXED = new KtDiagnosticFactory0("USELESS_JVM_EXPOSE_BOXED", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_REQUIRES_NAME = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_REQUIRES_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED = new KtDiagnosticFactory0("JVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        WRONG_TYPE_FOR_JAVA_OVERRIDE = new KtDiagnosticFactory2<>("WRONG_TYPE_FOR_JAVA_OVERRIDE", severity2, sourceElementPositioningStrategies.getOVERRIDE_MODIFIER(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        ACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE = new KtDiagnosticFactory3<>("ACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE", severity, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(KtNamedFunction.class), firJvmErrors.getRendererFactory());
        IMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE = new KtDiagnosticFactoryForDeprecation2<>("IMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE", LanguageFeature.ForbidImplementationByDelegationWithDifferentGenericSignature, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtTypeReference.class), firJvmErrors.getRendererFactory());
        NOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION = new KtDiagnosticFactory0("NOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION", severity, sourceElementPositioningStrategies.getNOT_SUPPORTED_IN_INLINE_MOST_RELEVANT(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJvmErrors.getRendererFactory());
        PROPERTY_HIDES_JAVA_FIELD = new KtDiagnosticFactory1<>("PROPERTY_HIDES_JAVA_FIELD", severity2, sourceElementPositioningStrategies.getDECLARATION_NAME(), Reflection.getOrCreateKotlinClass(KtCallableDeclaration.class), firJvmErrors.getRendererFactory());
        CONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION = new KtDiagnosticFactory0("CONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_TYPE_MISMATCH = new KtDiagnosticFactory2<>("JAVA_TYPE_MISMATCH", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firJvmErrors.getRendererFactory());
        RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS = new KtDiagnosticFactory3<>("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        RECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS = new KtDiagnosticFactory2<>("RECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS = new KtDiagnosticFactory3<>("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        NULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA = new KtDiagnosticFactory3<>("NULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        TYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES = new KtDiagnosticFactory2<>("TYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_CLASS_ON_COMPANION = new KtDiagnosticFactory2<>("JAVA_CLASS_ON_COMPANION", severity2, sourceElementPositioningStrategies.getSELECTOR_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        UNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS = new KtDiagnosticFactory1<>("UNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS", severity2, sourceElementPositioningStrategies.getWHEN_EXPRESSION(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        UPPER_BOUND_CANNOT_BE_ARRAY = new KtDiagnosticFactory0("UPPER_BOUND_CANNOT_BE_ARRAY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        UPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS = new KtDiagnosticFactory3<>("UPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS = new KtDiagnosticFactory3<>("UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        STRICTFP_ON_CLASS = new KtDiagnosticFactory0("STRICTFP_ON_CLASS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        SYNCHRONIZED_ON_ABSTRACT = new KtDiagnosticFactory0("SYNCHRONIZED_ON_ABSTRACT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        SYNCHRONIZED_IN_INTERFACE = new KtDiagnosticFactory0("SYNCHRONIZED_IN_INTERFACE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        LanguageFeature languageFeature = LanguageFeature.ForbidJvmAnnotationsOnAnnotationParameters;
        SYNCHRONIZED_IN_ANNOTATION = new KtDiagnosticFactoryForDeprecation0("SYNCHRONIZED_IN_ANNOTATION", languageFeature, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        SYNCHRONIZED_ON_INLINE = new KtDiagnosticFactory0("SYNCHRONIZED_ON_INLINE", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        LanguageFeature languageFeature2 = LanguageFeature.ProhibitSynchronizationByValueClassesAndPrimitives;
        SYNCHRONIZED_ON_VALUE_CLASS = new KtDiagnosticFactoryForDeprecation0("SYNCHRONIZED_ON_VALUE_CLASS", languageFeature2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        SYNCHRONIZED_ON_SUSPEND = new KtDiagnosticFactoryForDeprecation0("SYNCHRONIZED_ON_SUSPEND", LanguageFeature.SynchronizedSuspendError, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS = new KtDiagnosticFactory0("OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        OVERLOADS_ABSTRACT = new KtDiagnosticFactory0("OVERLOADS_ABSTRACT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        OVERLOADS_INTERFACE = new KtDiagnosticFactory0("OVERLOADS_INTERFACE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        OVERLOADS_LOCAL = new KtDiagnosticFactory0("OVERLOADS_LOCAL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        OVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR = new KtDiagnosticFactory0("OVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        OVERLOADS_PRIVATE = new KtDiagnosticFactory0("OVERLOADS_PRIVATE", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        DEPRECATED_JAVA_ANNOTATION = new KtDiagnosticFactory1<>("DEPRECATED_JAVA_ANNOTATION", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        JVM_PACKAGE_NAME_CANNOT_BE_EMPTY = new KtDiagnosticFactory0("JVM_PACKAGE_NAME_CANNOT_BE_EMPTY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        JVM_PACKAGE_NAME_MUST_BE_VALID_NAME = new KtDiagnosticFactory0("JVM_PACKAGE_NAME_MUST_BE_VALID_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        JVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES = new KtDiagnosticFactory0("JVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        POSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION = new KtDiagnosticFactory0("POSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firJvmErrors.getRendererFactory());
        REDUNDANT_REPEATABLE_ANNOTATION = new KtDiagnosticFactory2<>("REDUNDANT_REPEATABLE_ANNOTATION", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        THROWS_IN_ANNOTATION = new KtDiagnosticFactoryForDeprecation0("THROWS_IN_ANNOTATION", languageFeature, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        JVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS = new KtDiagnosticFactoryForDeprecation0("JVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS", LanguageFeature.ForbidJvmSerializableLambdaOnInlinedFunctionLiterals, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        INCOMPATIBLE_ANNOTATION_TARGETS = new KtDiagnosticFactory2<>("INCOMPATIBLE_ANNOTATION_TARGETS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        ANNOTATION_TARGETS_ONLY_IN_JAVA = new KtDiagnosticFactory0("ANNOTATION_TARGETS_ONLY_IN_JAVA", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER = new KtDiagnosticFactory0("INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER", severity, sourceElementPositioningStrategies.getREFERENCE_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_CLASS_INHERITS_KT_PRIVATE_CLASS = new KtDiagnosticFactory2<>("JAVA_CLASS_INHERITS_KT_PRIVATE_CLASS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJvmErrors.getRendererFactory());
        LOCAL_JVM_RECORD = new KtDiagnosticFactory0("LOCAL_JVM_RECORD", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        NON_FINAL_JVM_RECORD = new KtDiagnosticFactory0("NON_FINAL_JVM_RECORD", severity, sourceElementPositioningStrategies.getNON_FINAL_MODIFIER_OR_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        ENUM_JVM_RECORD = new KtDiagnosticFactory0("ENUM_JVM_RECORD", severity, sourceElementPositioningStrategies.getENUM_MODIFIER(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS = new KtDiagnosticFactory0("JVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        NON_DATA_CLASS_JVM_RECORD = new KtDiagnosticFactory0("NON_DATA_CLASS_JVM_RECORD", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_RECORD_NOT_VAL_PARAMETER = new KtDiagnosticFactory0("JVM_RECORD_NOT_VAL_PARAMETER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_RECORD_NOT_LAST_VARARG_PARAMETER = new KtDiagnosticFactory0("JVM_RECORD_NOT_LAST_VARARG_PARAMETER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        INNER_JVM_RECORD = new KtDiagnosticFactory0("INNER_JVM_RECORD", severity, sourceElementPositioningStrategies.getINNER_MODIFIER(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        FIELD_IN_JVM_RECORD = new KtDiagnosticFactory0("FIELD_IN_JVM_RECORD", severity, sourceElementPositioningStrategies.getCALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        DELEGATION_BY_IN_JVM_RECORD = new KtDiagnosticFactory0("DELEGATION_BY_IN_JVM_RECORD", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_RECORD_EXTENDS_CLASS = new KtDiagnosticFactory1<>("JVM_RECORD_EXTENDS_CLASS", severity, sourceElementPositioningStrategies.getACTUAL_DECLARATION_NAME(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        ILLEGAL_JAVA_LANG_RECORD_SUPERTYPE = new KtDiagnosticFactory0("ILLEGAL_JAVA_LANG_RECORD_SUPERTYPE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_RECORDS_ILLEGAL_BYTECODE_TARGET = new KtDiagnosticFactory0("JVM_RECORDS_ILLEGAL_BYTECODE_TARGET", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE = new KtDiagnosticFactory1<>("JAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE = new KtDiagnosticFactory0("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE = new KtDiagnosticFactory2<>("JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE = new KtDiagnosticFactory0("JVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJvmErrors.getRendererFactory());
        JVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE = new KtDiagnosticFactory0("JVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJvmErrors.getRendererFactory());
        EXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT = new KtDiagnosticFactory0("EXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT", severity, sourceElementPositioningStrategies.getABSTRACT_MODIFIER(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJvmErrors.getRendererFactory());
        EXTERNAL_DECLARATION_CANNOT_HAVE_BODY = new KtDiagnosticFactory0("EXTERNAL_DECLARATION_CANNOT_HAVE_BODY", severity, sourceElementPositioningStrategies.getEXTERNAL_MODIFIER(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJvmErrors.getRendererFactory());
        EXTERNAL_DECLARATION_IN_INTERFACE = new KtDiagnosticFactory0("EXTERNAL_DECLARATION_IN_INTERFACE", severity, sourceElementPositioningStrategies.getEXTERNAL_MODIFIER(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJvmErrors.getRendererFactory());
        EXTERNAL_DECLARATION_CANNOT_BE_INLINED = new KtDiagnosticFactory0("EXTERNAL_DECLARATION_CANNOT_BE_INLINED", severity, sourceElementPositioningStrategies.getEXTERNAL_MODIFIER(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJvmErrors.getRendererFactory());
        NON_SOURCE_REPEATED_ANNOTATION = new KtDiagnosticFactory0("NON_SOURCE_REPEATED_ANNOTATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        REPEATED_ANNOTATION_WITH_CONTAINER = new KtDiagnosticFactory2<>("REPEATED_ANNOTATION_WITH_CONTAINER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        REPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR = new KtDiagnosticFactory2<>("REPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        REPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR = new KtDiagnosticFactory2<>("REPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        REPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR = new KtDiagnosticFactory4<>("REPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        REPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR = new KtDiagnosticFactory2<>("REPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR = new KtDiagnosticFactory0("REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        SUSPENSION_POINT_INSIDE_CRITICAL_SECTION = new KtDiagnosticFactory1<>("SUSPENSION_POINT_INSIDE_CRITICAL_SECTION", severity, sourceElementPositioningStrategies.getREFERENCE_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        INLINE_FROM_HIGHER_PLATFORM = new KtDiagnosticFactory2<>("INLINE_FROM_HIGHER_PLATFORM", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        INAPPLICABLE_JVM_FIELD = new KtDiagnosticFactory1<>("INAPPLICABLE_JVM_FIELD", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        INAPPLICABLE_JVM_FIELD_WARNING = new KtDiagnosticFactory1<>("INAPPLICABLE_JVM_FIELD_WARNING", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        IDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE = new KtDiagnosticFactory1<>("IDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJvmErrors.getRendererFactory());
        SYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS = new KtDiagnosticFactory1<>("SYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJvmErrors.getRendererFactory());
        SYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE = new KtDiagnosticFactoryForDeprecation1<>("SYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE", languageFeature2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JVM_SYNTHETIC_ON_DELEGATE = new KtDiagnosticFactory0("JVM_SYNTHETIC_ON_DELEGATE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnnotationEntry.class), firJvmErrors.getRendererFactory());
        SUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC = new KtDiagnosticFactory0("SUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC", severity, sourceElementPositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        CONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR = new KtDiagnosticFactory0("CONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        SPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR = new KtDiagnosticFactory0("SPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR", severity, sourceElementPositioningStrategies.getSPREAD_OPERATOR(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE = new KtDiagnosticFactory0("JAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        NO_REFLECTION_IN_CLASS_PATH = new KtDiagnosticFactory0("NO_REFLECTION_IN_CLASS_PATH", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN = new KtDiagnosticFactory2<>("SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN", severity2, sourceElementPositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        JAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY = new KtDiagnosticFactory1<>("JAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY", severity, sourceElementPositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        MISSING_BUILT_IN_DECLARATION = new KtDiagnosticFactory1<>("MISSING_BUILT_IN_DECLARATION", severity, sourceElementPositioningStrategies.getREFERENCED_NAME_BY_QUALIFIED(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJvmErrors.getRendererFactory());
        DANGEROUS_CHARACTERS = new KtDiagnosticFactory1<>("DANGEROUS_CHARACTERS", severity2, sourceElementPositioningStrategies.getNAME_IDENTIFIER(), Reflection.getOrCreateKotlinClass(KtNamedDeclaration.class), firJvmErrors.getRendererFactory());
    }

    private FirJvmErrors() {
    }

    public final KtDiagnosticFactory3<FirNamedFunctionSymbol, String, FirNamedFunctionSymbol> getACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE() {
        return ACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE;
    }

    public final KtDiagnosticFactory0 getANNOTATION_TARGETS_ONLY_IN_JAVA() {
        return ANNOTATION_TARGETS_ONLY_IN_JAVA;
    }

    public final KtDiagnosticFactory0 getCONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR() {
        return CONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR;
    }

    public final KtDiagnosticFactory0 getCONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION() {
        return CONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION;
    }

    public final KtDiagnosticFactory1<String> getDANGEROUS_CHARACTERS() {
        return DANGEROUS_CHARACTERS;
    }

    public final KtDiagnosticFactory0 getDELEGATION_BY_IN_JVM_RECORD() {
        return DELEGATION_BY_IN_JVM_RECORD;
    }

    public final KtDiagnosticFactory1<FqName> getDEPRECATED_JAVA_ANNOTATION() {
        return DEPRECATED_JAVA_ANNOTATION;
    }

    public final KtDiagnosticFactory0 getENUM_JVM_RECORD() {
        return ENUM_JVM_RECORD;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT() {
        return EXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DECLARATION_CANNOT_BE_INLINED() {
        return EXTERNAL_DECLARATION_CANNOT_BE_INLINED;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DECLARATION_CANNOT_HAVE_BODY() {
        return EXTERNAL_DECLARATION_CANNOT_HAVE_BODY;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DECLARATION_IN_INTERFACE() {
        return EXTERNAL_DECLARATION_IN_INTERFACE;
    }

    public final KtDiagnosticFactory0 getFIELD_IN_JVM_RECORD() {
        return FIELD_IN_JVM_RECORD;
    }

    public final KtDiagnosticFactory0 getFUNCTION_DELEGATE_MEMBER_NAME_CLASH() {
        return FUNCTION_DELEGATE_MEMBER_NAME_CLASH;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getIDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE() {
        return IDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE;
    }

    public final KtDiagnosticFactory0 getILLEGAL_JAVA_LANG_RECORD_SUPERTYPE() {
        return ILLEGAL_JAVA_LANG_RECORD_SUPERTYPE;
    }

    public final KtDiagnosticFactory0 getILLEGAL_JVM_NAME() {
        return ILLEGAL_JVM_NAME;
    }

    public final KtDiagnosticFactoryForDeprecation2<FirNamedFunctionSymbol, FirNamedFunctionSymbol> getIMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE() {
        return IMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME() {
        return INAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME;
    }

    public final KtDiagnosticFactory1<String> getINAPPLICABLE_JVM_FIELD() {
        return INAPPLICABLE_JVM_FIELD;
    }

    public final KtDiagnosticFactory1<String> getINAPPLICABLE_JVM_FIELD_WARNING() {
        return INAPPLICABLE_JVM_FIELD_WARNING;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_JVM_NAME() {
        return INAPPLICABLE_JVM_NAME;
    }

    public final KtDiagnosticFactory2<Collection<String>, Collection<String>> getINCOMPATIBLE_ANNOTATION_TARGETS() {
        return INCOMPATIBLE_ANNOTATION_TARGETS;
    }

    public final KtDiagnosticFactory2<String, String> getINLINE_FROM_HIGHER_PLATFORM() {
        return INLINE_FROM_HIGHER_PLATFORM;
    }

    public final KtDiagnosticFactory0 getINNER_JVM_RECORD() {
        return INNER_JVM_RECORD;
    }

    public final KtDiagnosticFactory0 getINTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER() {
        return INTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER;
    }

    public final KtDiagnosticFactory2<ClassId, ConeKotlinType> getJAVA_CLASS_INHERITS_KT_PRIVATE_CLASS() {
        return JAVA_CLASS_INHERITS_KT_PRIVATE_CLASS;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getJAVA_CLASS_ON_COMPANION() {
        return JAVA_CLASS_ON_COMPANION;
    }

    public final KtDiagnosticFactory1<FirPropertySymbol> getJAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY() {
        return JAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY;
    }

    public final KtDiagnosticFactory1<String> getJAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE() {
        return JAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE;
    }

    public final KtDiagnosticFactory2<String, String> getJAVA_MODULE_DOES_NOT_EXPORT_PACKAGE() {
        return JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE;
    }

    public final KtDiagnosticFactory0 getJAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE() {
        return JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE;
    }

    public final KtDiagnosticFactory0 getJAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE() {
        return JAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getJAVA_TYPE_MISMATCH() {
        return JAVA_TYPE_MISMATCH;
    }

    public final KtDiagnosticFactory0 getJVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE() {
        return JVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE;
    }

    public final KtDiagnosticFactory0 getJVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE() {
        return JVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME() {
        return JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME() {
        return JVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS() {
        return JVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT() {
        return JVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED() {
        return JVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND() {
        return JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC() {
        return JVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC;
    }

    public final KtDiagnosticFactory0 getJVM_EXPOSE_BOXED_REQUIRES_NAME() {
        return JVM_EXPOSE_BOXED_REQUIRES_NAME;
    }

    public final KtDiagnosticFactory0 getJVM_INLINE_WITHOUT_VALUE_CLASS() {
        return JVM_INLINE_WITHOUT_VALUE_CLASS;
    }

    public final KtDiagnosticFactory0 getJVM_PACKAGE_NAME_CANNOT_BE_EMPTY() {
        return JVM_PACKAGE_NAME_CANNOT_BE_EMPTY;
    }

    public final KtDiagnosticFactory0 getJVM_PACKAGE_NAME_MUST_BE_VALID_NAME() {
        return JVM_PACKAGE_NAME_MUST_BE_VALID_NAME;
    }

    public final KtDiagnosticFactory0 getJVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES() {
        return JVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES;
    }

    public final KtDiagnosticFactory0 getJVM_RECORDS_ILLEGAL_BYTECODE_TARGET() {
        return JVM_RECORDS_ILLEGAL_BYTECODE_TARGET;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getJVM_RECORD_EXTENDS_CLASS() {
        return JVM_RECORD_EXTENDS_CLASS;
    }

    public final KtDiagnosticFactory0 getJVM_RECORD_NOT_LAST_VARARG_PARAMETER() {
        return JVM_RECORD_NOT_LAST_VARARG_PARAMETER;
    }

    public final KtDiagnosticFactory0 getJVM_RECORD_NOT_VAL_PARAMETER() {
        return JVM_RECORD_NOT_VAL_PARAMETER;
    }

    public final KtDiagnosticFactory0 getJVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS() {
        return JVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS;
    }

    public final KtDiagnosticFactoryForDeprecation0 getJVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS() {
        return JVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS;
    }

    public final KtDiagnosticFactory0 getJVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION() {
        return JVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION;
    }

    public final KtDiagnosticFactory0 getJVM_STATIC_NOT_IN_OBJECT_OR_COMPANION() {
        return JVM_STATIC_NOT_IN_OBJECT_OR_COMPANION;
    }

    public final KtDiagnosticFactory0 getJVM_STATIC_ON_CONST_OR_JVM_FIELD() {
        return JVM_STATIC_ON_CONST_OR_JVM_FIELD;
    }

    public final KtDiagnosticFactory0 getJVM_STATIC_ON_EXTERNAL_IN_INTERFACE() {
        return JVM_STATIC_ON_EXTERNAL_IN_INTERFACE;
    }

    public final KtDiagnosticFactory0 getJVM_STATIC_ON_NON_PUBLIC_MEMBER() {
        return JVM_STATIC_ON_NON_PUBLIC_MEMBER;
    }

    public final KtDiagnosticFactory0 getJVM_SYNTHETIC_ON_DELEGATE() {
        return JVM_SYNTHETIC_ON_DELEGATE;
    }

    public final KtDiagnosticFactory0 getLOCAL_JVM_RECORD() {
        return LOCAL_JVM_RECORD;
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getMISSING_BUILT_IN_DECLARATION() {
        return MISSING_BUILT_IN_DECLARATION;
    }

    public final KtDiagnosticFactory0 getNON_DATA_CLASS_JVM_RECORD() {
        return NON_DATA_CLASS_JVM_RECORD;
    }

    public final KtDiagnosticFactory0 getNON_FINAL_JVM_RECORD() {
        return NON_FINAL_JVM_RECORD;
    }

    public final KtDiagnosticFactory0 getNON_SOURCE_REPEATED_ANNOTATION() {
        return NON_SOURCE_REPEATED_ANNOTATION;
    }

    public final KtDiagnosticFactory0 getNOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION() {
        return NOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION;
    }

    public final KtDiagnosticFactory0 getNO_REFLECTION_IN_CLASS_PATH() {
        return NO_REFLECTION_IN_CLASS_PATH;
    }

    public final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> getNULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA() {
        return NULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA;
    }

    public final KtDiagnosticFactory0 getOVERLOADS_ABSTRACT() {
        return OVERLOADS_ABSTRACT;
    }

    public final KtDiagnosticFactory0 getOVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR() {
        return OVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR;
    }

    public final KtDiagnosticFactory0 getOVERLOADS_INTERFACE() {
        return OVERLOADS_INTERFACE;
    }

    public final KtDiagnosticFactory0 getOVERLOADS_LOCAL() {
        return OVERLOADS_LOCAL;
    }

    public final KtDiagnosticFactory0 getOVERLOADS_PRIVATE() {
        return OVERLOADS_PRIVATE;
    }

    public final KtDiagnosticFactory0 getOVERLOADS_WITHOUT_DEFAULT_ARGUMENTS() {
        return OVERLOADS_WITHOUT_DEFAULT_ARGUMENTS;
    }

    public final KtDiagnosticFactory0 getOVERRIDE_CANNOT_BE_STATIC() {
        return OVERRIDE_CANNOT_BE_STATIC;
    }

    public final KtDiagnosticFactory0 getPOSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION() {
        return POSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION;
    }

    public final KtDiagnosticFactory1<FirFieldSymbol> getPROPERTY_HIDES_JAVA_FIELD() {
        return PROPERTY_HIDES_JAVA_FIELD;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, ClassId> getRECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS() {
        return RECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS;
    }

    public final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> getRECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS() {
        return RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS;
    }

    public final KtDiagnosticFactory2<FqName, FqName> getREDUNDANT_REPEATABLE_ANNOTATION() {
        return REDUNDANT_REPEATABLE_ANNOTATION;
    }

    public final KtDiagnosticFactory0 getREPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR() {
        return REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR;
    }

    public final KtDiagnosticFactory2<ClassId, Name> getREPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR() {
        return REPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR;
    }

    public final KtDiagnosticFactory4<ClassId, String, ClassId, String> getREPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR() {
        return REPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR;
    }

    public final KtDiagnosticFactory2<ClassId, ClassId> getREPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR() {
        return REPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR;
    }

    public final KtDiagnosticFactory2<ClassId, ClassId> getREPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR() {
        return REPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR;
    }

    public final KtDiagnosticFactory2<ClassId, ClassId> getREPEATED_ANNOTATION_WITH_CONTAINER() {
        return REPEATED_ANNOTATION_WITH_CONTAINER;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return FirJvmErrorsDefaultMessages.INSTANCE;
    }

    public final KtDiagnosticFactory0 getSPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR() {
        return SPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR;
    }

    public final KtDiagnosticFactory0 getSTRICTFP_ON_CLASS() {
        return STRICTFP_ON_CLASS;
    }

    public final KtDiagnosticFactory0 getSUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC() {
        return SUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC;
    }

    public final KtDiagnosticFactory1<FirCallableSymbol<?>> getSUSPENSION_POINT_INSIDE_CRITICAL_SECTION() {
        return SUSPENSION_POINT_INSIDE_CRITICAL_SECTION;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getSYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS() {
        return SYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS;
    }

    public final KtDiagnosticFactoryForDeprecation1<ConeKotlinType> getSYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE() {
        return SYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE;
    }

    public final KtDiagnosticFactoryForDeprecation0 getSYNCHRONIZED_IN_ANNOTATION() {
        return SYNCHRONIZED_IN_ANNOTATION;
    }

    public final KtDiagnosticFactory0 getSYNCHRONIZED_IN_INTERFACE() {
        return SYNCHRONIZED_IN_INTERFACE;
    }

    public final KtDiagnosticFactory0 getSYNCHRONIZED_ON_ABSTRACT() {
        return SYNCHRONIZED_ON_ABSTRACT;
    }

    public final KtDiagnosticFactory0 getSYNCHRONIZED_ON_INLINE() {
        return SYNCHRONIZED_ON_INLINE;
    }

    public final KtDiagnosticFactoryForDeprecation0 getSYNCHRONIZED_ON_SUSPEND() {
        return SYNCHRONIZED_ON_SUSPEND;
    }

    public final KtDiagnosticFactoryForDeprecation0 getSYNCHRONIZED_ON_VALUE_CLASS() {
        return SYNCHRONIZED_ON_VALUE_CLASS;
    }

    public final KtDiagnosticFactory2<FirNamedFunctionSymbol, Name> getSYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN() {
        return SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN;
    }

    public final KtDiagnosticFactoryForDeprecation0 getTHROWS_IN_ANNOTATION() {
        return THROWS_IN_ANNOTATION;
    }

    public final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS() {
        return TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getTYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES() {
        return TYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getUNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS() {
        return UNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS;
    }

    public final KtDiagnosticFactory0 getUPPER_BOUND_CANNOT_BE_ARRAY() {
        return UPPER_BOUND_CANNOT_BE_ARRAY;
    }

    public final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getUPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS() {
        return UPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS;
    }

    public final KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, ConeKotlinType> getUPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS() {
        return UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS;
    }

    public final KtDiagnosticFactory0 getUSELESS_JVM_EXPOSE_BOXED() {
        return USELESS_JVM_EXPOSE_BOXED;
    }

    public final KtDiagnosticFactory0 getVALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION() {
        return VALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION;
    }

    public final KtDiagnosticFactory2<FirCallableSymbol<?>, FirCallableSymbol<?>> getWRONG_TYPE_FOR_JAVA_OVERRIDE() {
        return WRONG_TYPE_FOR_JAVA_OVERRIDE;
    }
}
