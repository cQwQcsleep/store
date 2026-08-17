package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiTreeUtilKt;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtArrayAccessExpression;
import org.jetbrains.kotlin.psi.KtBackingField;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtBinaryExpressionWithTypeRHS;
import org.jetbrains.kotlin.psi.KtBlockExpression;
import org.jetbrains.kotlin.psi.KtCallElement;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtCallableDeclaration;
import org.jetbrains.kotlin.psi.KtCallableReferenceExpression;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtClassInitializer;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtConstructor;
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression;
import org.jetbrains.kotlin.psi.KtConstructorDelegationCall;
import org.jetbrains.kotlin.psi.KtContainerNode;
import org.jetbrains.kotlin.psi.KtContextParameterList;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtDeclarationWithBody;
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry;
import org.jetbrains.kotlin.psi.KtDestructuringDeclaration;
import org.jetbrains.kotlin.psi.KtDestructuringDeclarationEntry;
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtElementImplStub;
import org.jetbrains.kotlin.psi.KtEnumEntry;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtExpressionWithLabel;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtFunction;
import org.jetbrains.kotlin.psi.KtFunctionLiteral;
import org.jetbrains.kotlin.psi.KtFunctionTypeReceiver;
import org.jetbrains.kotlin.psi.KtIfExpression;
import org.jetbrains.kotlin.psi.KtImportAlias;
import org.jetbrains.kotlin.psi.KtImportDirective;
import org.jetbrains.kotlin.psi.KtLabelReferenceExpression;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.jetbrains.kotlin.psi.KtModifierListOwner;
import org.jetbrains.kotlin.psi.KtNameReferenceExpression;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtNullableType;
import org.jetbrains.kotlin.psi.KtObjectDeclaration;
import org.jetbrains.kotlin.psi.KtObjectLiteralExpression;
import org.jetbrains.kotlin.psi.KtOperationExpression;
import org.jetbrains.kotlin.psi.KtOperationReferenceExpression;
import org.jetbrains.kotlin.psi.KtPackageDirective;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtParameterList;
import org.jetbrains.kotlin.psi.KtParenthesizedExpression;
import org.jetbrains.kotlin.psi.KtProjectionKind;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtPropertyAccessor;
import org.jetbrains.kotlin.psi.KtPropertyDelegate;
import org.jetbrains.kotlin.psi.KtQualifiedExpression;
import org.jetbrains.kotlin.psi.KtReferenceExpression;
import org.jetbrains.kotlin.psi.KtReturnExpression;
import org.jetbrains.kotlin.psi.KtSecondaryConstructor;
import org.jetbrains.kotlin.psi.KtSimpleNameExpression;
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry;
import org.jetbrains.kotlin.psi.KtSuperTypeList;
import org.jetbrains.kotlin.psi.KtTypeAlias;
import org.jetbrains.kotlin.psi.KtTypeArgumentList;
import org.jetbrains.kotlin.psi.KtTypeElement;
import org.jetbrains.kotlin.psi.KtTypeParameterList;
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner;
import org.jetbrains.kotlin.psi.KtTypeProjection;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.KtUnaryExpression;
import org.jetbrains.kotlin.psi.KtUserType;
import org.jetbrains.kotlin.psi.KtValueArgument;
import org.jetbrains.kotlin.psi.KtValueArgumentList;
import org.jetbrains.kotlin.psi.KtWhenConditionInRange;
import org.jetbrains.kotlin.psi.KtWhenEntry;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001:\f¿\u0001À\u0001Á\u0001Â\u0001Ã\u0001Ä\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J+\u0010@\u001a\b\u0012\u0004\u0012\u00020'0\u00052\u0012\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020C0B\"\u00020CH\u0007b\u0002\bE¢\u0006\u0002\u0010DJ\u0012\u0010F\u001a\b\u0012\u0004\u0012\u00020'0\u0005H\u0007b\u0002\bEJ\u0010\u0010\u008c\u0001\u001a\u0004\u0018\u00010q*\u00030\u008d\u0001H\u0002R\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u00070\u000f¢\u0006\u0002\b\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\nR\u001b\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\nR\u001b\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010)\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010*\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010+\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010,\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010-\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010/\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00100\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00102\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00103\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00104\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00105\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00106\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010J\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010K\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010L\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010M\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010N\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010O\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010R\u001a\b\u0012\u0004\u0012\u00020S0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010V\u001a\b\u0012\u0004\u0012\u00020S0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010Z\u001a\b\u0012\u0004\u0012\u00020[0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010^\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010_\u001a\b\u0012\u0004\u0012\u00020`0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010a\u001a\b\u0012\u0004\u0012\u00020b0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010c\u001a\b\u0012\u0004\u0012\u00020`0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010d\u001a\b\u0012\u0004\u0012\u00020e0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010f\u001a\b\u0012\u0004\u0012\u00020g0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010h\u001a\b\u0012\u0004\u0012\u00020i0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010l\u001a\b\u0012\u0004\u0012\u00020m0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010n\u001a\b\u0012\u0004\u0012\u00020k0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010p\u001a\b\u0012\u0004\u0012\u00020q0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010r\u001a\b\u0012\u0004\u0012\u00020q0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010s\u001a\b\u0012\u0004\u0012\u00020U0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010t\u001a\b\u0012\u0004\u0012\u00020q0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010u\u001a\b\u0012\u0004\u0012\u00020q0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010v\u001a\b\u0012\u0004\u0012\u00020w0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010x\u001a\b\u0012\u0004\u0012\u00020'0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010y\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010z\u001a\b\u0012\u0004\u0012\u00020{0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010|\u001a\b\u0012\u0004\u0012\u00020}0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010~\u001a\b\u0012\u0004\u0012\u00020}0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001b\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020}0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001d\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001d\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001d\u0010\u0084\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0019\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020i0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010\nR\u0019\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010\nR\u0019\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010\nR\u0019\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010\nR\u001c\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001c\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0019\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0093\u0001\u0010\nR\u0019\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010\nR\u0019\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020'0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\nR\u0019\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010\nR\u0019\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\nR\u0019\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010\nR\u0019\u0010\u009e\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u009f\u0001\u0010\nR\u001a\u0010 \u0001\u001a\t\u0012\u0005\u0012\u00030¡\u00010\u0005¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\nR\u0019\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b¤\u0001\u0010\nR!\u0010¥\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u0005¢\u0006\u0010\n\u0000\u0012\u0005\b¦\u0001\u0010\u0003\u001a\u0005\b§\u0001\u0010\nR\u0019\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u0005¢\u0006\t\n\u0000\u001a\u0005\b©\u0001\u0010\nR\u0019\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b«\u0001\u0010\nR\u0019\u0010¬\u0001\u001a\b\u0012\u0004\u0012\u00020'0\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u00ad\u0001\u0010\nR\u0019\u0010®\u0001\u001a\b\u0012\u0004\u0012\u00020k0\u0005¢\u0006\t\n\u0000\u001a\u0005\b¯\u0001\u0010\nR\u0019\u0010°\u0001\u001a\b\u0012\u0004\u0012\u00020i0\u0005¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010\nR\u001d\u0010²\u0001\u001a\t\u0012\u0005\u0012\u00030³\u00010\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u001c\u0010´\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0007¢\u0006\u0002\n\u0000R\u0019\u0010µ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b¶\u0001\u0010\nR\u0019\u0010·\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b¸\u0001\u0010\nR\u0019\u0010¹\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u0005¢\u0006\t\n\u0000\u001a\u0005\bº\u0001\u0010\nR\u0019\u0010»\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b¼\u0001\u0010\nR\u0019\u0010½\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\t\n\u0000\u001a\u0005\b¾\u0001\u0010\n¨\u0006Å\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lcom/intellij/psi/PsiElement;", "Lkotlin/jvm/JvmField;", "SYNTAX_ERROR", "getSYNTAX_ERROR", "()Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "SUPERTYPES_LIST", "DECLARATION_RETURN_TYPE", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "classKindTokens", "Lcom/intellij/psi/tree/TokenSet;", "Lorg/jetbrains/annotations/NotNull;", "getClassKindTokens", "()Lcom/intellij/psi/tree/TokenSet;", "DECLARATION_START_TO_NAME", "CONTEXT_KEYWORD", "findStartingPsiElementForDeclarationName", "element", "Lorg/jetbrains/kotlin/psi/KtNamedDeclaration;", "DECLARATION_NAME_WITH_VALIDITY_CHECK", "DECLARATION_NAME", "getDECLARATION_NAME", "DECLARATION_NAME_OR_ACCESSOR", "DECLARATION_NAME_ONLY", "DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK", "DECLARATION_SIGNATURE", "getDECLARATION_SIGNATURE", "CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS", "DECLARATION_SIGNATURE_OR_DEFAULT_WITH_VALIDITY_CHECK", "DECLARATION_SIGNATURE_OR_DEFAULT", "getDECLARATION_SIGNATURE_OR_DEFAULT", "NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT", "TYPE_PARAMETERS_OR_DECLARATION_SIGNATURE", "ABSTRACT_MODIFIER", "Lorg/jetbrains/kotlin/psi/KtModifierListOwner;", "OPEN_MODIFIER", "OVERRIDE_MODIFIER", "PRIVATE_MODIFIER", "LATEINIT_MODIFIER", "VARIANCE_MODIFIER", "CONST_MODIFIER", "FUN_MODIFIER", "SUSPEND_MODIFIER", "DATA_MODIFIER", "OPERATOR_MODIFIER", "INFIX_MODIFIER", "ENUM_MODIFIER", "TAILREC_MODIFIER", "EXTERNAL_MODIFIER", "EXPECT_ACTUAL_MODIFIER", "OBJECT_KEYWORD", "Lorg/jetbrains/kotlin/psi/KtObjectDeclaration;", "FIELD_KEYWORD", "Lorg/jetbrains/kotlin/psi/KtBackingField;", "PROPERTY_DELEGATE", "Lorg/jetbrains/kotlin/psi/KtProperty;", "FOR_REDECLARATION", "FOR_UNRESOLVED_REFERENCE", "Lorg/jetbrains/kotlin/psi/KtReferenceExpression;", "modifierSetPosition", "tokens", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "([Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;)Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lkotlin/jvm/JvmStatic;", "projectionPosition", "ARRAY_ACCESS", "Lorg/jetbrains/kotlin/psi/KtArrayAccessExpression;", "SAFE_ACCESS", "VISIBILITY_MODIFIER", "MODALITY_MODIFIER", "INLINE_OR_VALUE_MODIFIER", "INNER_MODIFIER", "INLINE_PARAMETER_MODIFIER", "INLINE_FUN_MODIFIER", "VARIANCE_IN_PROJECTION", "Lorg/jetbrains/kotlin/psi/KtTypeProjection;", "PARAMETER_DEFAULT_VALUE", "Lorg/jetbrains/kotlin/psi/KtParameter;", "PARAMETERS_WITH_DEFAULT_VALUE", "Lorg/jetbrains/kotlin/psi/KtFunction;", "PARAMETER_VARARG_MODIFIER", "NAME_OF_NAMED_ARGUMENT", "Lorg/jetbrains/kotlin/psi/KtValueArgument;", "CALL_ELEMENT", "CALL_ELEMENT_WITH_DOT", "Lorg/jetbrains/kotlin/psi/KtQualifiedExpression;", "DECLARATION_WITH_BODY", "Lorg/jetbrains/kotlin/psi/KtDeclarationWithBody;", "VAL_OR_VAR_NODE", "ELSE_ENTRY", "Lorg/jetbrains/kotlin/psi/KtWhenEntry;", "WHEN_EXPRESSION", "Lorg/jetbrains/kotlin/psi/KtWhenExpression;", "WHEN_GUARD", "IF_EXPRESSION", "Lorg/jetbrains/kotlin/psi/KtIfExpression;", "WHEN_CONDITION_IN_RANGE", "Lorg/jetbrains/kotlin/psi/KtWhenConditionInRange;", "SPECIAL_CONSTRUCT_TOKEN", "Lorg/jetbrains/kotlin/psi/KtExpression;", "REDUNDANT_NULLABLE", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "NULLABLE_TYPE", "Lorg/jetbrains/kotlin/psi/KtNullableType;", "QUESTION_MARK_BY_TYPE", "CALL_EXPRESSION", "VALUE_ARGUMENTS", "Lorg/jetbrains/kotlin/psi/KtElement;", "VALUE_ARGUMENTS_LIST", "FUNCTION_PARAMETERS", "CUT_CHAR_QUOTES", "LONG_LITERAL_SUFFIX", "AS_TYPE", "Lorg/jetbrains/kotlin/psi/KtBinaryExpressionWithTypeRHS;", "COMPANION_OBJECT", "SECONDARY_CONSTRUCTOR_DELEGATION_CALL", "DELEGATOR_SUPER_CALL", "Lorg/jetbrains/kotlin/psi/KtEnumEntry;", "UNUSED_VALUE", "Lorg/jetbrains/kotlin/psi/KtBinaryExpression;", "USELESS_ELVIS", "USELESS_ELVIS_LEFT", "IMPORT_ALIAS", "Lorg/jetbrains/kotlin/psi/KtImportDirective;", "RETURN_WITH_LABEL", "Lorg/jetbrains/kotlin/psi/KtReturnExpression;", "RECEIVER", "Lorg/jetbrains/kotlin/psi/KtCallableDeclaration;", "OPERATOR", "getOPERATOR", "DOT_BY_QUALIFIED", "getDOT_BY_QUALIFIED", "SELECTOR_BY_QUALIFIED", "getSELECTOR_BY_QUALIFIED", "getReferencedTypeExpression", "Lorg/jetbrains/kotlin/psi/KtTypeElement;", "NAME_IDENTIFIER", "getNAME_IDENTIFIER", "SPREAD_OPERATOR", "FUN_INTERFACE", "REFERENCE_BY_QUALIFIED", "getREFERENCE_BY_QUALIFIED", "REFERENCED_NAME_BY_QUALIFIED", "getREFERENCED_NAME_BY_QUALIFIED", "REIFIED_MODIFIER", "getREIFIED_MODIFIER", "VARIABLE_INITIALIZER", "getVARIABLE_INITIALIZER", "WHOLE_ELEMENT", "getWHOLE_ELEMENT", "TYPE_PARAMETERS_LIST", "getTYPE_PARAMETERS_LIST", "FUNCTION_TYPE_RECEIVER", "getFUNCTION_TYPE_RECEIVER", "ANNOTATION_USE_SITE", "Lorg/jetbrains/kotlin/psi/KtAnnotationEntry;", "getANNOTATION_USE_SITE", "IMPORT_LAST_NAME", "getIMPORT_LAST_NAME", "IMPORT_LAST_BUT_ONE_NAME", "getIMPORT_LAST_BUT_ONE_NAME$annotations", "getIMPORT_LAST_BUT_ONE_NAME", "LABEL", "getLABEL", "COMMAS", "getCOMMAS", "NON_FINAL_MODIFIER_OR_NAME", "getNON_FINAL_MODIFIER_OR_NAME", "DELEGATED_SUPERTYPE_BY_KEYWORD", "getDELEGATED_SUPERTYPE_BY_KEYWORD", "PROPERTY_DELEGATE_BY_KEYWORD", "getPROPERTY_DELEGATE_BY_KEYWORD", "TYPEALIAS_TYPE_REFERENCE", "Lorg/jetbrains/kotlin/psi/KtTypeAlias;", "SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC", "TYPE_ARGUMENT_LIST_OR_SELF", "getTYPE_ARGUMENT_LIST_OR_SELF", "TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER", "getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER", "PACKAGE_DIRECTIVE_NAME_EXPRESSION", "getPACKAGE_DIRECTIVE_NAME_EXPRESSION", "OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS", "getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS", "DEPRECATION", "getDEPRECATION", "DeclarationHeader", "DeclarationName", "DeclarationSignature", "ModifierSetBasedPositioningStrategy", "InlineFunPositioningStrategy", "FindReferencePositioningStrategy", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PositioningStrategies {
    public static final PositioningStrategy<KtModifierListOwner> ABSTRACT_MODIFIER;
    private static final PositioningStrategy<KtAnnotationEntry> ANNOTATION_USE_SITE;
    public static final PositioningStrategy<KtArrayAccessExpression> ARRAY_ACCESS;
    public static final PositioningStrategy<KtBinaryExpressionWithTypeRHS> AS_TYPE;
    public static final PositioningStrategy<PsiElement> CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS;
    public static final PositioningStrategy<PsiElement> CALL_ELEMENT;
    public static final PositioningStrategy<KtQualifiedExpression> CALL_ELEMENT_WITH_DOT;
    public static final PositioningStrategy<PsiElement> CALL_EXPRESSION;
    private static final PositioningStrategy<PsiElement> COMMAS;
    public static final PositioningStrategy<KtModifierListOwner> COMPANION_OBJECT;
    public static final PositioningStrategy<KtModifierListOwner> CONST_MODIFIER;
    public static final PositioningStrategy<PsiElement> CONTEXT_KEYWORD;
    public static final PositioningStrategy<KtElement> CUT_CHAR_QUOTES;
    public static final PositioningStrategy<KtModifierListOwner> DATA_MODIFIER;
    private static final PositioningStrategy<KtDeclaration> DECLARATION_NAME;
    public static final PositioningStrategy<KtNamedDeclaration> DECLARATION_NAME_ONLY;
    public static final PositioningStrategy<KtDeclaration> DECLARATION_NAME_OR_ACCESSOR;
    public static final PositioningStrategy<KtDeclaration> DECLARATION_NAME_WITH_VALIDITY_CHECK;
    private static final PositioningStrategy<KtDeclaration> DECLARATION_SIGNATURE;
    private static final PositioningStrategy<PsiElement> DECLARATION_SIGNATURE_OR_DEFAULT;
    public static final PositioningStrategy<PsiElement> DECLARATION_SIGNATURE_OR_DEFAULT_WITH_VALIDITY_CHECK;
    public static final PositioningStrategy<KtDeclaration> DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK;
    public static final PositioningStrategy<KtDeclaration> DECLARATION_START_TO_NAME;
    public static final PositioningStrategy<KtDeclarationWithBody> DECLARATION_WITH_BODY;
    private static final PositioningStrategy<KtTypeReference> DELEGATED_SUPERTYPE_BY_KEYWORD;
    public static final PositioningStrategy<KtEnumEntry> DELEGATOR_SUPER_CALL;
    private static final PositioningStrategy<PsiElement> DEPRECATION;
    private static final PositioningStrategy<PsiElement> DOT_BY_QUALIFIED;
    public static final PositioningStrategy<KtWhenEntry> ELSE_ENTRY;
    public static final PositioningStrategy<KtModifierListOwner> ENUM_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> EXPECT_ACTUAL_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> EXTERNAL_MODIFIER;
    public static final PositioningStrategy<KtBackingField> FIELD_KEYWORD;
    public static final PositioningStrategy<PsiElement> FOR_REDECLARATION;
    public static final PositioningStrategy<KtReferenceExpression> FOR_UNRESOLVED_REFERENCE;
    public static final PositioningStrategy<KtFunction> FUNCTION_PARAMETERS;
    private static final PositioningStrategy<KtElement> FUNCTION_TYPE_RECEIVER;
    public static final PositioningStrategy<KtDeclaration> FUN_INTERFACE;
    public static final PositioningStrategy<KtModifierListOwner> FUN_MODIFIER;
    public static final PositioningStrategy<KtIfExpression> IF_EXPRESSION;
    public static final PositioningStrategy<KtImportDirective> IMPORT_ALIAS;
    private static final PositioningStrategy<KtImportDirective> IMPORT_LAST_BUT_ONE_NAME;
    private static final PositioningStrategy<PsiElement> IMPORT_LAST_NAME;
    public static final PositioningStrategy<KtModifierListOwner> INFIX_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> INLINE_FUN_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> INLINE_OR_VALUE_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> INLINE_PARAMETER_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> INNER_MODIFIER;
    private static final PositioningStrategy<KtElement> LABEL;
    public static final PositioningStrategy<KtModifierListOwner> LATEINIT_MODIFIER;
    public static final PositioningStrategy<KtElement> LONG_LITERAL_SUFFIX;
    public static final PositioningStrategy<KtModifierListOwner> MODALITY_MODIFIER;
    private static final PositioningStrategy<PsiElement> NAME_IDENTIFIER;
    public static final PositioningStrategy<KtValueArgument> NAME_OF_NAMED_ARGUMENT;
    private static final PositioningStrategy<KtModifierListOwner> NON_FINAL_MODIFIER_OR_NAME;
    public static final PositioningStrategy<KtDeclaration> NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT;
    public static final PositioningStrategy<KtNullableType> NULLABLE_TYPE;
    public static final PositioningStrategy<KtObjectDeclaration> OBJECT_KEYWORD;
    public static final PositioningStrategy<KtModifierListOwner> OPEN_MODIFIER;
    private static final PositioningStrategy<KtExpression> OPERATOR;
    public static final PositioningStrategy<KtModifierListOwner> OPERATOR_MODIFIER;
    private static final PositioningStrategy<PsiElement> OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS;
    public static final PositioningStrategy<KtModifierListOwner> OVERRIDE_MODIFIER;
    private static final PositioningStrategy<KtElement> PACKAGE_DIRECTIVE_NAME_EXPRESSION;
    public static final PositioningStrategy<KtFunction> PARAMETERS_WITH_DEFAULT_VALUE;
    public static final PositioningStrategy<KtParameter> PARAMETER_DEFAULT_VALUE;
    public static final PositioningStrategy<KtParameter> PARAMETER_VARARG_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> PRIVATE_MODIFIER;
    public static final PositioningStrategy<KtProperty> PROPERTY_DELEGATE;
    private static final PositioningStrategy<KtExpression> PROPERTY_DELEGATE_BY_KEYWORD;
    public static final PositioningStrategy<KtTypeReference> QUESTION_MARK_BY_TYPE;
    public static final PositioningStrategy<KtCallableDeclaration> RECEIVER;
    public static final PositioningStrategy<KtTypeReference> REDUNDANT_NULLABLE;
    private static final PositioningStrategy<PsiElement> REFERENCED_NAME_BY_QUALIFIED;
    private static final PositioningStrategy<PsiElement> REFERENCE_BY_QUALIFIED;
    private static final PositioningStrategy<KtModifierListOwner> REIFIED_MODIFIER;
    public static final PositioningStrategy<KtReturnExpression> RETURN_WITH_LABEL;
    public static final PositioningStrategy<PsiElement> SAFE_ACCESS;
    public static final PositioningStrategy<PsiElement> SECONDARY_CONSTRUCTOR_DELEGATION_CALL;
    private static final PositioningStrategy<PsiElement> SELECTOR_BY_QUALIFIED;
    public static final PositioningStrategy<KtExpression> SPECIAL_CONSTRUCT_TOKEN;
    public static final PositioningStrategy<PsiElement> SPREAD_OPERATOR;
    public static final PositioningStrategy<KtElement> SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC;
    public static final PositioningStrategy<KtModifierListOwner> SUSPEND_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> TAILREC_MODIFIER;
    public static final PositioningStrategy<KtTypeAlias> TYPEALIAS_TYPE_REFERENCE;
    private static final PositioningStrategy<PsiElement> TYPE_ARGUMENT_LIST_OR_SELF;
    private static final PositioningStrategy<PsiElement> TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER;
    private static final PositioningStrategy<KtDeclaration> TYPE_PARAMETERS_LIST;
    public static final PositioningStrategy<KtDeclaration> TYPE_PARAMETERS_OR_DECLARATION_SIGNATURE;
    public static final PositioningStrategy<KtBinaryExpression> UNUSED_VALUE;
    public static final PositioningStrategy<KtBinaryExpression> USELESS_ELVIS;
    public static final PositioningStrategy<KtBinaryExpression> USELESS_ELVIS_LEFT;
    public static final PositioningStrategy<KtElement> VALUE_ARGUMENTS;
    public static final PositioningStrategy<KtElement> VALUE_ARGUMENTS_LIST;
    public static final PositioningStrategy<KtDeclaration> VAL_OR_VAR_NODE;
    private static final PositioningStrategy<KtElement> VARIABLE_INITIALIZER;
    public static final PositioningStrategy<KtTypeProjection> VARIANCE_IN_PROJECTION;
    public static final PositioningStrategy<KtModifierListOwner> VARIANCE_MODIFIER;
    public static final PositioningStrategy<KtModifierListOwner> VISIBILITY_MODIFIER;
    public static final PositioningStrategy<KtWhenConditionInRange> WHEN_CONDITION_IN_RANGE;
    public static final PositioningStrategy<KtWhenExpression> WHEN_EXPRESSION;
    public static final PositioningStrategy<KtWhenEntry> WHEN_GUARD;
    private static final PositioningStrategy<KtElement> WHOLE_ELEMENT;
    private static final TokenSet classKindTokens;
    public static final PositioningStrategies INSTANCE = new PositioningStrategies();
    public static final PositioningStrategy<PsiElement> DEFAULT = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DEFAULT$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(PsiElement element) {
            element.getClass();
            if (element instanceof KtObjectLiteralExpression) {
                KtObjectDeclaration objectDeclaration = ((KtObjectLiteralExpression) element).getObjectDeclaration();
                objectDeclaration.getClass();
                PsiElement objectKeyword = objectDeclaration.getObjectKeyword();
                objectKeyword.getClass();
                KtSuperTypeList superTypeList = objectDeclaration.getSuperTypeList();
                return superTypeList == null ? PositioningStrategyKt.markElement(objectKeyword) : PositioningStrategyKt.markRange(objectKeyword, superTypeList);
            }
            if (!(element instanceof KtObjectDeclaration)) {
                return element instanceof KtConstructorDelegationCall ? PositioningStrategies.SECONDARY_CONSTRUCTOR_DELEGATION_CALL.mark(element) : super.mark(element);
            }
            KtObjectDeclaration ktObjectDeclaration = (KtObjectDeclaration) element;
            PsiElement objectKeyword2 = ktObjectDeclaration.getObjectKeyword();
            objectKeyword2.getClass();
            PsiElement nameIdentifier = ktObjectDeclaration.getNameIdentifier();
            if (nameIdentifier == null) {
                nameIdentifier = ktObjectDeclaration.getObjectKeyword();
                nameIdentifier.getClass();
            }
            return PositioningStrategyKt.markRange(objectKeyword2, nameIdentifier);
        }
    };
    private static final PositioningStrategy<PsiElement> SYNTAX_ERROR = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SYNTAX_ERROR$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        @DiagnosticLossRisk
        public boolean isValid(PsiElement element) {
            element.getClass();
            if (element instanceof PsiErrorElement) {
                return true;
            }
            return PositioningStrategies.DEFAULT.isValid(element);
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(PsiElement element) {
            element.getClass();
            return PositioningStrategies.DEFAULT.mark(element);
        }
    };
    public static final PositioningStrategy<PsiElement> SUPERTYPES_LIST = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SUPERTYPES_LIST$1
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(PsiElement element) {
            element.getClass();
            KtClassOrObject ktClassOrObject = element instanceof KtClassOrObject ? (KtClassOrObject) element : null;
            if (ktClassOrObject == null) {
                return PositioningStrategyKt.markElement(element);
            }
            List superTypeListEntries = ktClassOrObject.getSuperTypeListEntries();
            return superTypeListEntries.isEmpty() ? PositioningStrategyKt.markElement(element) : PositioningStrategyKt.markRange((PsiElement) superTypeListEntries.get(0), (PsiElement) CollectionsKt.last(superTypeListEntries));
        }
    };
    public static final PositioningStrategy<KtDeclaration> DECLARATION_RETURN_TYPE = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_RETURN_TYPE$1
        private final PsiElement getElementToMark(KtDeclaration declaration) {
            Pair pair;
            if (declaration instanceof KtCallableDeclaration) {
                KtCallableDeclaration ktCallableDeclaration = (KtCallableDeclaration) declaration;
                pair = new Pair(ktCallableDeclaration.getTypeReference(), ktCallableDeclaration.getNameIdentifier());
            } else if (declaration instanceof KtPropertyAccessor) {
                KtPropertyAccessor ktPropertyAccessor = (KtPropertyAccessor) declaration;
                pair = new Pair(ktPropertyAccessor.getTypeReference(), ktPropertyAccessor.getNamePlaceholder());
            } else {
                pair = new Pair((Object) null, (Object) null);
            }
            KtTypeReference ktTypeReference = (KtTypeReference) pair.component1();
            PsiElement psiElement = (PsiElement) pair.component2();
            if (ktTypeReference != null) {
                return ktTypeReference;
            }
            return psiElement != null ? psiElement : declaration;
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public boolean isValid(KtDeclaration element) {
            element.getClass();
            return !PositioningStrategyKt.hasSyntaxErrors(getElementToMark(element));
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(KtDeclaration element) {
            element.getClass();
            return PositioningStrategyKt.markElement(getElementToMark(element));
        }
    };

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$DeclarationHeader;", "T", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "checkValidity", Argument.Delimiters.none, "<init>", "(Z)V", "isValid", "element", "(Lcom/intellij/psi/PsiElement;)Z", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static class DeclarationHeader<T extends PsiElement> extends PositioningStrategy<T> {
        private final boolean checkValidity;

        public DeclarationHeader(boolean z) {
            this.checkValidity = z;
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public boolean isValid(T element) {
            element.getClass();
            if (!this.checkValidity || !(element instanceof KtNamedDeclaration) || (element instanceof KtObjectDeclaration) || (element instanceof KtSecondaryConstructor) || (element instanceof KtFunction) || ((PsiNameIdentifierOwner) element).getNameIdentifier() != null) {
                return super.isValid(element);
            }
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$DeclarationName;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$DeclarationHeader;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "checkValidity", Argument.Delimiters.none, "<init>", "(Z)V", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DeclarationName extends DeclarationHeader<KtDeclaration> {
        public DeclarationName(boolean z) {
            super(z);
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(KtDeclaration element) {
            element.getClass();
            if (element instanceof KtNamedDeclaration) {
                KtNamedDeclaration ktNamedDeclaration = (KtNamedDeclaration) element;
                PsiElement nameIdentifier = ktNamedDeclaration.getNameIdentifier();
                if (nameIdentifier != null) {
                    return element instanceof KtClassOrObject ? PositioningStrategyKt.markRange(PositioningStrategies.INSTANCE.findStartingPsiElementForDeclarationName(ktNamedDeclaration), nameIdentifier) : PositioningStrategyKt.markElement(nameIdentifier);
                }
                if (element instanceof KtNamedFunction) {
                    return PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.mark(element);
                }
            } else if (element instanceof KtPropertyAccessor) {
                return PositioningStrategyKt.markElement(((KtPropertyAccessor) element).getNamePlaceholder());
            }
            return PositioningStrategies.DEFAULT.mark(element);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$DeclarationSignature;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$DeclarationHeader;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "checkValidity", Argument.Delimiters.none, "<init>", "(Z)V", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DeclarationSignature extends DeclarationHeader<KtDeclaration> {
        public DeclarationSignature(boolean z) {
            super(z);
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(KtDeclaration element) {
            element.getClass();
            if (element instanceof KtConstructor) {
                KtConstructor ktConstructor = (KtConstructor) element;
                KtParameterList constructorKeyword = ktConstructor.getConstructorKeyword();
                if (constructorKeyword == null && (constructorKeyword = ktConstructor.getValueParameterList()) == null) {
                    return PositioningStrategyKt.markElement(element);
                }
                PsiElement valueParameterList = ktConstructor.getValueParameterList();
                return (valueParameterList == null && (valueParameterList = ktConstructor.getConstructorKeyword()) == null) ? PositioningStrategyKt.markElement(element) : PositioningStrategyKt.markRange(constructorKeyword, valueParameterList);
            }
            if (element instanceof KtFunction) {
                KtFunction ktFunction = (KtFunction) element;
                KtDeclaration typeReference = ktFunction.getTypeReference();
                if (typeReference == null && (typeReference = ktFunction.getValueParameterList()) == null && (typeReference = ktFunction.getNameIdentifier()) == null) {
                    typeReference = element;
                }
                if (element instanceof KtFunctionLiteral) {
                    KtFunctionLiteral ktFunctionLiteral = (KtFunctionLiteral) element;
                    KtDeclaration receiverTypeReference = ktFunctionLiteral.getReceiverTypeReference();
                    if (receiverTypeReference != null) {
                        element = receiverTypeReference;
                    } else {
                        KtDeclaration valueParameterList2 = ktFunctionLiteral.getValueParameterList();
                        if (valueParameterList2 != null) {
                            element = valueParameterList2;
                        }
                    }
                }
                return PositioningStrategyKt.markRange(element, typeReference);
            }
            if (element instanceof KtProperty) {
                KtProperty ktProperty = (KtProperty) element;
                KtDeclaration typeReference2 = ktProperty.getTypeReference();
                if (typeReference2 == null && (typeReference2 = ktProperty.getNameIdentifier()) == null) {
                    typeReference2 = element;
                }
                return PositioningStrategyKt.markRange(element, typeReference2);
            }
            if (element instanceof KtPropertyAccessor) {
                KtPropertyAccessor ktPropertyAccessor = (KtPropertyAccessor) element;
                PsiElement typeReference3 = ktPropertyAccessor.getTypeReference();
                if (typeReference3 == null && (typeReference3 = ktPropertyAccessor.getParameterList()) == null) {
                    typeReference3 = ktPropertyAccessor.getNamePlaceholder();
                }
                return PositioningStrategyKt.markRange(element, typeReference3);
            }
            if (element instanceof KtClass) {
                KtClass ktClass = (KtClass) element;
                PsiElement nameIdentifier = ktClass.getNameIdentifier();
                if (nameIdentifier == null) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtParameterList primaryConstructorParameterList = ktClass.getPrimaryConstructorParameterList();
                return primaryConstructorParameterList == null ? PositioningStrategyKt.markElement(nameIdentifier) : PositioningStrategyKt.markRange(nameIdentifier, primaryConstructorParameterList);
            }
            if (element instanceof KtObjectDeclaration) {
                return PositioningStrategies.DECLARATION_NAME_WITH_VALIDITY_CHECK.mark(element);
            }
            if (!(element instanceof KtClassInitializer)) {
                return super.mark(element);
            }
            TextRange textRange = ((KtClassInitializer) element).getInitKeyword().getTextRange();
            textRange.getClass();
            return PositioningStrategyKt.markRange(textRange);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$FindReferencePositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lcom/intellij/psi/PsiElement;", "locateReferencedName", Argument.Delimiters.none, "<init>", "(Z)V", "getLocateReferencedName", "()Z", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FindReferencePositioningStrategy extends PositioningStrategy<PsiElement> {
        private final boolean locateReferencedName;

        public FindReferencePositioningStrategy(boolean z) {
            this.locateReferencedName = z;
        }

        public final boolean getLocateReferencedName() {
            return this.locateReferencedName;
        }

        /* JADX WARN: Code duplicated, block: B:86:0x0123 A[PHI: r0
          0x0123: PHI (r0v39 com.intellij.psi.PsiElement) = 
          (r0v16 com.intellij.psi.PsiElement)
          (r0v19 com.intellij.psi.PsiElement)
          (r0v22 com.intellij.psi.PsiElement)
          (r0v28 com.intellij.psi.PsiElement)
          (r0v32 com.intellij.psi.PsiElement)
          (r0v35 com.intellij.psi.PsiElement)
          (r0v38 com.intellij.psi.PsiElement)
          (r0v44 com.intellij.psi.PsiElement)
          (r0v47 com.intellij.psi.PsiElement)
          (r0v47 com.intellij.psi.PsiElement)
         binds: [B:79:0x0110, B:74:0x0102, B:68:0x00f1, B:62:0x00e0, B:52:0x00c4, B:38:0x0089, B:33:0x0078, B:85:0x0121, B:24:0x0056, B:21:0x0051] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x0123 -> B:80:0x0112). Please report as a decompilation issue!!! */
        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(PsiElement element) {
            PsiElement nameIdentifier;
            PsiElement nameIdentifier2;
            KtExpression left;
            element.getClass();
            if (element instanceof KtBinaryExpression) {
                KtBinaryExpression ktBinaryExpression = (KtBinaryExpression) element;
                if (Intrinsics.areEqual(ktBinaryExpression.getOperationToken(), KtTokens.EQ) && (left = ktBinaryExpression.getLeft()) != null) {
                    return mark(left);
                }
            }
            if ((element instanceof KtClassOrObject) && (nameIdentifier2 = ((KtNamedDeclaration) element).getNameIdentifier()) != null) {
                return PositioningStrategyKt.markRange(PositioningStrategies.INSTANCE.findStartingPsiElementForDeclarationName((KtNamedDeclaration) element), nameIdentifier2);
            }
            if (element instanceof KtQualifiedExpression) {
                nameIdentifier = ((KtQualifiedExpression) element).getSelectorExpression();
                if (nameIdentifier instanceof KtCallExpression) {
                    element = ((KtCallExpression) nameIdentifier).getCalleeExpression();
                    if (element == null) {
                        element = nameIdentifier;
                    }
                } else if (nameIdentifier instanceof KtReferenceExpression) {
                    element = nameIdentifier;
                } else {
                    element = (KtExpression) element;
                }
            } else if (element instanceof KtCallableReferenceExpression) {
                element = ((KtCallableReferenceExpression) element).getCallableReference();
                element.getClass();
            } else if (element instanceof KtCallExpression) {
                nameIdentifier = ((KtCallExpression) element).getCalleeExpression();
                if (nameIdentifier == null) {
                    element = (KtExpression) element;
                } else {
                    element = nameIdentifier;
                }
            } else if (element instanceof KtConstructorDelegationCall) {
                nameIdentifier = ((KtConstructorDelegationCall) element).getCalleeExpression();
                if (nameIdentifier != null) {
                    element = nameIdentifier;
                }
            } else if (element instanceof KtSuperTypeCallEntry) {
                element = ((KtSuperTypeCallEntry) element).getCalleeExpression();
                element.getClass();
            } else if (element instanceof KtOperationExpression) {
                element = ((KtOperationExpression) element).getOperationReference();
                element.getClass();
            } else if (element instanceof KtWhenConditionInRange) {
                element = ((KtWhenConditionInRange) element).getOperationReference();
                element.getClass();
            } else if (element instanceof KtAnnotationEntry) {
                nameIdentifier = ((KtAnnotationEntry) element).getCalleeExpression();
                if (nameIdentifier != null) {
                    element = nameIdentifier;
                }
            } else if (element instanceof KtTypeReference) {
                KtNullableType typeElement = ((KtTypeReference) element).getTypeElement();
                KtNullableType ktNullableType = typeElement instanceof KtNullableType ? typeElement : null;
                if (ktNullableType == null || (nameIdentifier = ktNullableType.getInnerType()) == null) {
                    element = (KtElement) element;
                } else {
                    element = nameIdentifier;
                }
            } else if (element instanceof KtImportDirective) {
                nameIdentifier = ((KtImportDirective) element).getImportedReference();
                if (nameIdentifier != null) {
                    element = nameIdentifier;
                } else {
                    element = (KtElement) element;
                }
            } else if (element instanceof KtImportAlias) {
            }
            if (this.locateReferencedName || !(element instanceof KtParenthesizedExpression) || (nameIdentifier = ((KtParenthesizedExpression) element).getExpression()) == null) {
                return super.mark(element);
            }
            element = nameIdentifier;
            if (this.locateReferencedName) {
            }
            return super.mark(element);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$InlineFunPositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$ModifierSetBasedPositioningStrategy;", "<init>", "()V", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InlineFunPositioningStrategy extends ModifierSetBasedPositioningStrategy {
        /* JADX WARN: Illegal instructions before constructor call */
        public InlineFunPositioningStrategy() {
            IElementType iElementType = KtTokens.INLINE_KEYWORD;
            iElementType.getClass();
            super(iElementType);
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategies.ModifierSetBasedPositioningStrategy, org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(PsiElement element) {
            element.getClass();
            if (!(element instanceof KtProperty)) {
                return super.mark(element);
            }
            KtProperty ktProperty = (KtProperty) element;
            List<TextRange> listMarkModifier = markModifier(ktProperty.getGetter());
            if (listMarkModifier != null) {
                return listMarkModifier;
            }
            List<TextRange> listMarkModifier2 = markModifier(ktProperty.getSetter());
            return listMarkModifier2 == null ? super.mark(element) : listMarkModifier2;
        }
    }

    static {
        TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{KtTokens.CLASS_KEYWORD, KtTokens.OBJECT_KEYWORD, KtTokens.INTERFACE_KEYWORD});
        tokenSetCreate.getClass();
        classKindTokens = tokenSetCreate;
        DECLARATION_START_TO_NAME = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_START_TO_NAME$1
            private final PsiElement firstNonCommentNonAnnotationLeaf(PsiElement psiElement) {
                PsiElement firstChild = psiElement.getFirstChild();
                if (firstChild == null) {
                    return psiElement;
                }
                while (true) {
                    if ((firstChild instanceof PsiComment) || (firstChild instanceof PsiWhiteSpace) || (firstChild instanceof KtAnnotationEntry)) {
                        firstChild = firstChild.getNextSibling();
                    } else {
                        if (firstChild == null) {
                            return null;
                        }
                        PsiElement psiElementFirstNonCommentNonAnnotationLeaf = firstNonCommentNonAnnotationLeaf(firstChild);
                        if (psiElementFirstNonCommentNonAnnotationLeaf != null) {
                            return psiElementFirstNonCommentNonAnnotationLeaf;
                        }
                        firstChild = firstChild.getNextSibling();
                    }
                }
            }

            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                element.getClass();
                KtDeclaration ktDeclarationFirstNonCommentNonAnnotationLeaf = firstNonCommentNonAnnotationLeaf(element);
                if (ktDeclarationFirstNonCommentNonAnnotationLeaf == null) {
                    ktDeclarationFirstNonCommentNonAnnotationLeaf = element;
                }
                KtNamedDeclaration ktNamedDeclaration = element instanceof KtNamedDeclaration ? (KtNamedDeclaration) element : null;
                PsiElement nameIdentifier = ktNamedDeclaration != null ? ktNamedDeclaration.getNameIdentifier() : null;
                if (nameIdentifier != null) {
                    return PositioningStrategyKt.markRange(ktDeclarationFirstNonCommentNonAnnotationLeaf, nameIdentifier);
                }
                if (element instanceof KtConstructor) {
                    KtDeclaration constructorKeyword = ((KtConstructor) element).getConstructorKeyword();
                    if (constructorKeyword != null) {
                        element = constructorKeyword;
                    }
                    return PositioningStrategyKt.markRange(ktDeclarationFirstNonCommentNonAnnotationLeaf, element);
                }
                if (!(element instanceof KtObjectDeclaration)) {
                    return PositioningStrategies.DEFAULT.mark(element);
                }
                KtDeclaration objectKeyword = ((KtObjectDeclaration) element).getObjectKeyword();
                if (objectKeyword != null) {
                    element = objectKeyword;
                }
                return PositioningStrategyKt.markRange(ktDeclarationFirstNonCommentNonAnnotationLeaf, element);
            }
        };
        CONTEXT_KEYWORD = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$CONTEXT_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                PsiElement firstChild;
                TextRange textRange;
                List<TextRange> listMarkRange;
                element.getClass();
                Object obj = null;
                for (Object obj2 : PsiTreeUtilKt.descendants$default(element, false, (Function1) null, 3, (Object) null)) {
                    if (obj2 instanceof KtContextParameterList) {
                        obj = obj2;
                        break;
                    }
                }
                KtContextParameterList ktContextParameterList = (KtContextParameterList) obj;
                return (ktContextParameterList == null || (firstChild = ktContextParameterList.getFirstChild()) == null || (textRange = firstChild.getTextRange()) == null || (listMarkRange = PositioningStrategyKt.markRange(textRange)) == null) ? PositioningStrategies.DEFAULT.mark(element) : listMarkRange;
            }
        };
        DECLARATION_NAME_WITH_VALIDITY_CHECK = new DeclarationName(true);
        DECLARATION_NAME = new DeclarationName(false);
        DECLARATION_NAME_OR_ACCESSOR = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_NAME_OR_ACCESSOR$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                element.getClass();
                if (element instanceof KtPropertyAccessor) {
                    return PositioningStrategyKt.markElement(((KtPropertyAccessor) element).getNamePlaceholder());
                }
                return element instanceof KtNamedDeclaration ? PositioningStrategies.DECLARATION_NAME_WITH_VALIDITY_CHECK.mark(element) : super.mark(element);
            }
        };
        DECLARATION_NAME_ONLY = new PositioningStrategy<KtNamedDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_NAME_ONLY$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtNamedDeclaration element) {
                element.getClass();
                PsiElement nameIdentifier = element.getNameIdentifier();
                if (nameIdentifier != null) {
                    return PositioningStrategyKt.markElement(nameIdentifier);
                }
                return element instanceof KtNamedFunction ? PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.mark(element) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK = new DeclarationSignature(true);
        DECLARATION_SIGNATURE = new DeclarationSignature(false);
        CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$CALLABLE_DECLARATION_SIGNATURE_NO_MODIFIERS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                if (element instanceof KtNamedFunction) {
                    KtNamedFunction ktNamedFunction = (KtNamedFunction) element;
                    PsiElement funKeyword = ktNamedFunction.getFunKeyword();
                    if (funKeyword == null) {
                        funKeyword = element;
                    }
                    PsiElement typeReference = ktNamedFunction.getTypeReference();
                    if (typeReference == null && (typeReference = ktNamedFunction.getValueParameterList()) == null) {
                        PsiElement nameIdentifier = ktNamedFunction.getNameIdentifier();
                        if (nameIdentifier != null) {
                            element = nameIdentifier;
                        }
                    } else {
                        element = typeReference;
                    }
                    return PositioningStrategyKt.markRange(funKeyword, element);
                }
                if (element instanceof KtProperty) {
                    KtProperty ktProperty = (KtProperty) element;
                    PsiElement typeReference2 = ktProperty.getTypeReference();
                    if (typeReference2 != null || (typeReference2 = ktProperty.getNameIdentifier()) != null) {
                        element = typeReference2;
                    }
                    PsiElement valOrVarKeyword = ktProperty.getValOrVarKeyword();
                    valOrVarKeyword.getClass();
                    return PositioningStrategyKt.markRange(valOrVarKeyword, element);
                }
                if (!(element instanceof KtPropertyAccessor)) {
                    return element instanceof KtDeclaration ? PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.mark(element) : PositioningStrategies.DEFAULT.mark(element);
                }
                KtPropertyAccessor ktPropertyAccessor = (KtPropertyAccessor) element;
                PsiElement typeReference3 = ktPropertyAccessor.getTypeReference();
                if (typeReference3 == null && (typeReference3 = ktPropertyAccessor.getParameterList()) == null) {
                    typeReference3 = ktPropertyAccessor.getNamePlaceholder();
                }
                return PositioningStrategyKt.markRange(ktPropertyAccessor.getNamePlaceholder(), typeReference3);
            }
        };
        DECLARATION_SIGNATURE_OR_DEFAULT_WITH_VALIDITY_CHECK = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_SIGNATURE_OR_DEFAULT_WITH_VALIDITY_CHECK$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public boolean isValid(PsiElement element) {
                element.getClass();
                return element instanceof KtDeclaration ? PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.isValid(element) : PositioningStrategies.DEFAULT.isValid(element);
            }

            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                return element instanceof KtDeclaration ? PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.mark(element) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        DECLARATION_SIGNATURE_OR_DEFAULT = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_SIGNATURE_OR_DEFAULT$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                return element instanceof KtDeclaration ? PositioningStrategies.INSTANCE.getDECLARATION_SIGNATURE().mark(element) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$NOT_SUPPORTED_IN_INLINE_MOST_RELEVANT$1
            /* JADX WARN: Code duplicated, block: B:10:0x0019 A[PHI: r0
              0x0019: PHI (r0v2 org.jetbrains.kotlin.psi.KtDeclaration) = (r0v1 org.jetbrains.kotlin.psi.KtDeclaration), (r0v3 org.jetbrains.kotlin.psi.KtDeclaration) binds: [B:16:0x002e, B:5:0x000e] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:9:0x0017 A[PHI: r2
              0x0017: PHI (r2v7 org.jetbrains.kotlin.psi.KtDeclaration) = (r2v5 org.jetbrains.kotlin.psi.KtDeclaration), (r2v10 org.jetbrains.kotlin.psi.KtDeclaration) binds: [B:18:0x0034, B:7:0x0014] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                KtDeclaration funKeyword;
                KtDeclaration modifier;
                element.getClass();
                if (element instanceof KtClassOrObject) {
                    KtClassOrObject ktClassOrObject = (KtClassOrObject) element;
                    modifier = ktClassOrObject.getDeclarationKeyword();
                    if (modifier == null) {
                        funKeyword = ktClassOrObject.getNameIdentifier();
                        if (funKeyword != null) {
                            element = funKeyword;
                        }
                    } else {
                        element = modifier;
                    }
                } else if (element instanceof KtNamedFunction) {
                    KtNamedFunction ktNamedFunction = (KtNamedFunction) element;
                    KtModifierList modifierList = ktNamedFunction.getModifierList();
                    if (modifierList == null || (modifier = modifierList.getModifier(KtTokens.INLINE_KEYWORD)) == null) {
                        funKeyword = ktNamedFunction.getFunKeyword();
                        if (funKeyword != null) {
                            element = funKeyword;
                        }
                    } else {
                        element = modifier;
                    }
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        TYPE_PARAMETERS_OR_DECLARATION_SIGNATURE = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$TYPE_PARAMETERS_OR_DECLARATION_SIGNATURE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                KtTypeParameterList typeParameterList;
                element.getClass();
                return (!(element instanceof KtTypeParameterListOwner) || (typeParameterList = ((KtTypeParameterListOwner) element).getTypeParameterList()) == null) ? PositioningStrategies.DECLARATION_SIGNATURE_WITH_VALIDITY_CHECK.mark(element) : PositioningStrategyKt.markElement(typeParameterList);
            }
        };
        IElementType iElementType = KtTokens.ABSTRACT_KEYWORD;
        iElementType.getClass();
        ABSTRACT_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType);
        IElementType iElementType2 = KtTokens.OPEN_KEYWORD;
        iElementType2.getClass();
        OPEN_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType2);
        IElementType iElementType3 = KtTokens.OVERRIDE_KEYWORD;
        iElementType3.getClass();
        OVERRIDE_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType3);
        IElementType iElementType4 = KtTokens.PRIVATE_KEYWORD;
        iElementType4.getClass();
        PRIVATE_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType4);
        IElementType iElementType5 = KtTokens.LATEINIT_KEYWORD;
        iElementType5.getClass();
        LATEINIT_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType5);
        VARIANCE_MODIFIER = projectionPosition();
        IElementType iElementType6 = KtTokens.CONST_KEYWORD;
        iElementType6.getClass();
        CONST_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType6);
        IElementType iElementType7 = KtTokens.FUN_KEYWORD;
        iElementType7.getClass();
        FUN_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType7);
        IElementType iElementType8 = KtTokens.SUSPEND_KEYWORD;
        iElementType8.getClass();
        SUSPEND_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType8);
        IElementType iElementType9 = KtTokens.DATA_KEYWORD;
        iElementType9.getClass();
        DATA_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType9);
        IElementType iElementType10 = KtTokens.OPERATOR_KEYWORD;
        iElementType10.getClass();
        OPERATOR_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType10);
        IElementType iElementType11 = KtTokens.INFIX_KEYWORD;
        iElementType11.getClass();
        INFIX_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType11);
        IElementType iElementType12 = KtTokens.ENUM_KEYWORD;
        iElementType12.getClass();
        ENUM_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType12);
        IElementType iElementType13 = KtTokens.TAILREC_KEYWORD;
        iElementType13.getClass();
        TAILREC_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType13);
        IElementType iElementType14 = KtTokens.EXTERNAL_KEYWORD;
        iElementType14.getClass();
        EXTERNAL_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType14);
        IElementType iElementType15 = KtTokens.EXPECT_KEYWORD;
        iElementType15.getClass();
        IElementType iElementType16 = KtTokens.ACTUAL_KEYWORD;
        iElementType16.getClass();
        EXPECT_ACTUAL_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType15, iElementType16);
        OBJECT_KEYWORD = new PositioningStrategy<KtObjectDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$OBJECT_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtObjectDeclaration element) {
                element.getClass();
                KtObjectDeclaration objectKeyword = element.getObjectKeyword();
                if (objectKeyword != null) {
                    element = objectKeyword;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        FIELD_KEYWORD = new PositioningStrategy<KtBackingField>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$FIELD_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtBackingField element) {
                element.getClass();
                PsiElement fieldKeyword = element.getFieldKeyword();
                fieldKeyword.getClass();
                return PositioningStrategyKt.markElement(fieldKeyword);
            }
        };
        PROPERTY_DELEGATE = new PositioningStrategy<KtProperty>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$PROPERTY_DELEGATE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtProperty element) {
                element.getClass();
                KtPropertyDelegate delegate = element.getDelegate();
                return delegate != null ? PositioningStrategyKt.markElement(delegate) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        FOR_REDECLARATION = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$FOR_REDECLARATION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                PsiElement nameIdentifier;
                element.getClass();
                if (element instanceof KtNamedDeclaration) {
                    nameIdentifier = ((KtNamedDeclaration) element).getNameIdentifier();
                } else if (element instanceof KtFile) {
                    KtPackageDirective packageDirective = ((KtFile) element).getPackageDirective();
                    packageDirective.getClass();
                    nameIdentifier = packageDirective.getNameIdentifier();
                } else {
                    nameIdentifier = null;
                }
                if (nameIdentifier == null && (element instanceof KtObjectDeclaration)) {
                    return PositioningStrategies.DEFAULT.mark(element);
                }
                if (nameIdentifier != null) {
                    element = nameIdentifier;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        FOR_UNRESOLVED_REFERENCE = new PositioningStrategy<KtReferenceExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$FOR_UNRESOLVED_REFERENCE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtReferenceExpression element) {
                element.getClass();
                if (element instanceof KtArrayAccessExpression) {
                    List<TextRange> bracketRanges = ((KtArrayAccessExpression) element).getBracketRanges();
                    bracketRanges.getClass();
                    if (!bracketRanges.isEmpty()) {
                        return bracketRanges;
                    }
                }
                return CollectionsKt.listOf(element.getTextRange());
            }
        };
        ARRAY_ACCESS = new PositioningStrategy<KtArrayAccessExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$ARRAY_ACCESS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtArrayAccessExpression element) {
                element.getClass();
                KtContainerNode indicesNode = element.getIndicesNode();
                indicesNode.getClass();
                return PositioningStrategyKt.markElement(indicesNode);
            }
        };
        SAFE_ACCESS = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SAFE_ACCESS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                PsiElement psi;
                element.getClass();
                ASTNode aSTNodeFindChildByType = element.getNode().findChildByType(KtTokens.SAFE_ACCESS);
                if (aSTNodeFindChildByType != null && (psi = aSTNodeFindChildByType.getPsi()) != null) {
                    element = psi;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        TokenSet tokenSet = KtTokens.VISIBILITY_MODIFIERS;
        tokenSet.getClass();
        VISIBILITY_MODIFIER = new ModifierSetBasedPositioningStrategy(tokenSet);
        TokenSet tokenSet2 = KtTokens.MODALITY_MODIFIERS;
        tokenSet2.getClass();
        MODALITY_MODIFIER = new ModifierSetBasedPositioningStrategy(tokenSet2);
        IElementType iElementType17 = KtTokens.INLINE_KEYWORD;
        iElementType17.getClass();
        IElementType iElementType18 = KtTokens.VALUE_KEYWORD;
        iElementType18.getClass();
        INLINE_OR_VALUE_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType17, iElementType18);
        IElementType iElementType19 = KtTokens.INNER_KEYWORD;
        iElementType19.getClass();
        INNER_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType19);
        IElementType iElementType20 = KtTokens.NOINLINE_KEYWORD;
        iElementType20.getClass();
        IElementType iElementType21 = KtTokens.CROSSINLINE_KEYWORD;
        iElementType21.getClass();
        INLINE_PARAMETER_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType20, iElementType21);
        INLINE_FUN_MODIFIER = new InlineFunPositioningStrategy();
        VARIANCE_IN_PROJECTION = new PositioningStrategy<KtTypeProjection>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$VARIANCE_IN_PROJECTION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtTypeProjection element) {
                element.getClass();
                PsiElement projectionToken = element.getProjectionToken();
                projectionToken.getClass();
                return PositioningStrategyKt.markElement(projectionToken);
            }
        };
        PARAMETER_DEFAULT_VALUE = new PositioningStrategy<KtParameter>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$PARAMETER_DEFAULT_VALUE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtParameter element) {
                element.getClass();
                KtExpression defaultValue = element.getDefaultValue();
                defaultValue.getClass();
                ASTNode node = defaultValue.getNode();
                node.getClass();
                return PositioningStrategyKt.markNode(node);
            }
        };
        PARAMETERS_WITH_DEFAULT_VALUE = new PositioningStrategy<KtFunction>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$PARAMETERS_WITH_DEFAULT_VALUE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtFunction element) {
                element.getClass();
                List valueParameters = element.getValueParameters();
                valueParameters.getClass();
                ArrayList arrayList = new ArrayList();
                for (Object obj : valueParameters) {
                    if (((KtParameter) obj).hasDefaultValue()) {
                        arrayList.add(obj);
                    }
                }
                List<TextRange> listMarkNode = null;
                if (arrayList.isEmpty()) {
                    arrayList = null;
                }
                if (arrayList != null) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ASTNode node = ((KtParameter) it.next()).getNode();
                        node.getClass();
                        CollectionsKt.addAll(arrayList2, PositioningStrategyKt.markNode(node));
                    }
                    return arrayList2;
                }
                KtParameterList valueParameterList = element.getValueParameterList();
                if (valueParameterList != null) {
                    ASTNode node2 = valueParameterList.getNode();
                    node2.getClass();
                    return PositioningStrategyKt.markNode(node2);
                }
                PsiElement nameIdentifier = element.getNameIdentifier();
                if (nameIdentifier != null) {
                    ASTNode node3 = nameIdentifier.getNode();
                    node3.getClass();
                    listMarkNode = PositioningStrategyKt.markNode(node3);
                }
                if (listMarkNode != null) {
                    return listMarkNode;
                }
                ASTNode node4 = element.getNode();
                node4.getClass();
                return PositioningStrategyKt.markNode(node4);
            }
        };
        PARAMETER_VARARG_MODIFIER = new PositioningStrategy<KtParameter>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$PARAMETER_VARARG_MODIFIER$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtParameter element) {
                element.getClass();
                KtModifierList modifierList = element.getModifierList();
                modifierList.getClass();
                PsiElement modifier = modifierList.getModifier(KtTokens.VARARG_KEYWORD);
                modifier.getClass();
                ASTNode node = modifier.getNode();
                node.getClass();
                return PositioningStrategyKt.markNode(node);
            }
        };
        NAME_OF_NAMED_ARGUMENT = new PositioningStrategy<KtValueArgument>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$NAME_OF_NAMED_ARGUMENT$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtValueArgument element) {
                element.getClass();
                KtValueArgument argumentName = element.getArgumentName();
                if (argumentName != null) {
                    element = argumentName;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        CALL_ELEMENT = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$CALL_ELEMENT$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                PsiElement calleeExpression;
                element.getClass();
                KtCallElement ktCallElement = element instanceof KtCallElement ? (KtCallElement) element : null;
                if (ktCallElement != null && (calleeExpression = ktCallElement.getCalleeExpression()) != null) {
                    element = calleeExpression;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        CALL_ELEMENT_WITH_DOT = new PositioningStrategy<KtQualifiedExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$CALL_ELEMENT_WITH_DOT$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtQualifiedExpression element) {
                element.getClass();
                List<TextRange> listMark = PositioningStrategies.INSTANCE.getSELECTOR_BY_QUALIFIED().mark(element);
                if (listMark.size() != 1) {
                    return listMark;
                }
                TextRange textRange = (TextRange) CollectionsKt.first(listMark);
                List<TextRange> listMark2 = PositioningStrategies.SAFE_ACCESS.mark(element);
                return listMark2.size() == 1 ? CollectionsKt.listOf(new TextRange(((TextRange) CollectionsKt.first(listMark2)).getStartOffset(), textRange.getEndOffset())) : listMark2;
            }
        };
        DECLARATION_WITH_BODY = new PositioningStrategy<KtDeclarationWithBody>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DECLARATION_WITH_BODY$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclarationWithBody element) {
                element.getClass();
                KtBlockExpression bodyBlockExpression = element.getBodyBlockExpression();
                TextRange lastBracketRange = bodyBlockExpression != null ? bodyBlockExpression.getLastBracketRange() : null;
                return lastBracketRange != null ? PositioningStrategyKt.markRange(lastBracketRange) : PositioningStrategyKt.markElement(element);
            }
        };
        VAL_OR_VAR_NODE = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$VAL_OR_VAR_NODE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                element.getClass();
                if (element instanceof KtParameter) {
                    KtDeclaration valOrVarKeyword = ((KtParameter) element).getValOrVarKeyword();
                    if (valOrVarKeyword != null) {
                        element = valOrVarKeyword;
                    }
                    return PositioningStrategyKt.markElement(element);
                }
                if (element instanceof KtProperty) {
                    PsiElement valOrVarKeyword2 = ((KtProperty) element).getValOrVarKeyword();
                    valOrVarKeyword2.getClass();
                    return PositioningStrategyKt.markElement(valOrVarKeyword2);
                }
                if (!(element instanceof KtDestructuringDeclaration)) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtDeclaration valOrVarKeyword3 = ((KtDestructuringDeclaration) element).getValOrVarKeyword();
                if (valOrVarKeyword3 != null) {
                    element = valOrVarKeyword3;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        ELSE_ENTRY = new PositioningStrategy<KtWhenEntry>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$ELSE_ENTRY$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtWhenEntry element) {
                element.getClass();
                PsiElement elseKeyword = element.getElseKeyword();
                elseKeyword.getClass();
                return PositioningStrategyKt.markElement(elseKeyword);
            }
        };
        WHEN_EXPRESSION = new PositioningStrategy<KtWhenExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$WHEN_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtWhenExpression element) {
                element.getClass();
                PsiElement whenKeyword = element.getWhenKeyword();
                whenKeyword.getClass();
                return PositioningStrategyKt.markElement(whenKeyword);
            }
        };
        WHEN_GUARD = new PositioningStrategy<KtWhenEntry>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$WHEN_GUARD$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtWhenEntry element) {
                element.getClass();
                KtWhenEntry guard = element.getGuard();
                if (guard != null) {
                    element = guard;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        IF_EXPRESSION = new PositioningStrategy<KtIfExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$IF_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtIfExpression element) {
                element.getClass();
                PsiElement ifKeyword = element.getIfKeyword();
                ifKeyword.getClass();
                return PositioningStrategyKt.markElement(ifKeyword);
            }
        };
        WHEN_CONDITION_IN_RANGE = new PositioningStrategy<KtWhenConditionInRange>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$WHEN_CONDITION_IN_RANGE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtWhenConditionInRange element) {
                element.getClass();
                KtOperationReferenceExpression operationReference = element.getOperationReference();
                operationReference.getClass();
                return PositioningStrategyKt.markElement(operationReference);
            }
        };
        SPECIAL_CONSTRUCT_TOKEN = new PositioningStrategy<KtExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SPECIAL_CONSTRUCT_TOKEN$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtExpression element) {
                element.getClass();
                if (element instanceof KtWhenExpression) {
                    PsiElement whenKeyword = ((KtWhenExpression) element).getWhenKeyword();
                    whenKeyword.getClass();
                    return PositioningStrategyKt.markElement(whenKeyword);
                }
                if (element instanceof KtIfExpression) {
                    PsiElement ifKeyword = ((KtIfExpression) element).getIfKeyword();
                    ifKeyword.getClass();
                    return PositioningStrategyKt.markElement(ifKeyword);
                }
                if (!(element instanceof KtOperationExpression)) {
                    f2f.a("Expression is not an if, when or operation expression: ", PsiUtilsKt.getElementTextWithContext(element));
                    return null;
                }
                KtSimpleNameExpression operationReference = ((KtOperationExpression) element).getOperationReference();
                operationReference.getClass();
                return PositioningStrategyKt.markElement(operationReference);
            }
        };
        REDUNDANT_NULLABLE = new PositioningStrategy<KtTypeReference>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$REDUNDANT_NULLABLE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtTypeReference element) {
                element.getClass();
                KtTypeElement typeElement = element.getTypeElement();
                ASTNode aSTNode = null;
                ASTNode aSTNode2 = null;
                ASTNode aSTNode3 = null;
                while (typeElement instanceof KtNullableType) {
                    KtNullableType ktNullableType = (KtNullableType) typeElement;
                    ASTNode questionMarkNode = ktNullableType.getQuestionMarkNode();
                    if (aSTNode == null) {
                        aSTNode = questionMarkNode;
                    }
                    typeElement = ktNullableType.getInnerType();
                    aSTNode3 = aSTNode2;
                    aSTNode2 = questionMarkNode;
                }
                if (aSTNode == null) {
                    return super.mark(element);
                }
                if (aSTNode3 == null) {
                    aSTNode3 = aSTNode;
                }
                PsiElement psi = aSTNode3.getPsi();
                psi.getClass();
                PsiElement psi2 = aSTNode.getPsi();
                psi2.getClass();
                return PositioningStrategyKt.markRange(psi, psi2);
            }
        };
        NULLABLE_TYPE = new PositioningStrategy<KtNullableType>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$NULLABLE_TYPE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtNullableType element) {
                element.getClass();
                ASTNode questionMarkNode = element.getQuestionMarkNode();
                questionMarkNode.getClass();
                return PositioningStrategyKt.markNode(questionMarkNode);
            }
        };
        QUESTION_MARK_BY_TYPE = new PositioningStrategy<KtTypeReference>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$QUESTION_MARK_BY_TYPE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtTypeReference element) {
                element.getClass();
                KtNullableType typeElement = element.getTypeElement();
                if (!(typeElement instanceof KtNullableType)) {
                    return super.mark(element);
                }
                ASTNode questionMarkNode = typeElement.getQuestionMarkNode();
                questionMarkNode.getClass();
                return PositioningStrategyKt.markNode(questionMarkNode);
            }
        };
        CALL_EXPRESSION = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$CALL_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                if (!(element instanceof KtCallExpression)) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtCallExpression ktCallExpression = (KtCallExpression) element;
                KtElement typeArgumentList = ktCallExpression.getTypeArgumentList();
                if (typeArgumentList == null && (typeArgumentList = ktCallExpression.getCalleeExpression()) == null) {
                    typeArgumentList = (KtElement) element;
                }
                return PositioningStrategyKt.markRange(element, typeArgumentList);
            }
        };
        VALUE_ARGUMENTS = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$VALUE_ARGUMENTS$1
            /* JADX WARN: Code duplicated, block: B:15:0x0035 A[PHI: r6
              0x0035: PHI (r6v7 org.jetbrains.kotlin.psi.KtElement) = (r6v6 org.jetbrains.kotlin.psi.KtElement), (r6v29 org.jetbrains.kotlin.psi.KtElement) binds: [B:19:0x0042, B:13:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                KtElement superTypeList;
                PsiElement psiElementUnwrapParenthesesLabelsAndAnnotations;
                element.getClass();
                if (element instanceof KtBinaryExpression) {
                    KtBinaryExpression ktBinaryExpression = (KtBinaryExpression) element;
                    if (KtTokens.ALL_ASSIGNMENTS.contains(ktBinaryExpression.getOperationToken()) && (psiElementUnwrapParenthesesLabelsAndAnnotations = PsiUtilsKt.unwrapParenthesesLabelsAndAnnotations(ktBinaryExpression.getLeft())) != null) {
                        return PositioningStrategyKt.markElement(psiElementUnwrapParenthesesLabelsAndAnnotations);
                    }
                }
                if (element instanceof KtQualifiedExpression) {
                    superTypeList = ((KtQualifiedExpression) element).getSelectorExpression();
                    if (superTypeList == null) {
                        element = (KtExpression) element;
                    } else {
                        element = superTypeList;
                    }
                } else if (element instanceof KtClassOrObject) {
                    superTypeList = ((KtClassOrObject) element).getSuperTypeList();
                    if (superTypeList != null) {
                        element = superTypeList;
                    } else {
                        element = (KtElementImplStub) element;
                    }
                }
                PsiElement psiElement = null;
                KtValueArgumentList ktValueArgumentList = element instanceof KtValueArgumentList ? (KtValueArgumentList) element : null;
                if (ktValueArgumentList == null) {
                    ktValueArgumentList = (KtValueArgumentList) PsiTreeUtil.getChildOfType(element, KtValueArgumentList.class);
                }
                if (ktValueArgumentList == null) {
                    if (!(element instanceof KtCallExpression)) {
                        return PositioningStrategyKt.markElement(element);
                    }
                    KtElement ktElement = (KtNameReferenceExpression) PsiTreeUtil.getChildOfType(element, KtNameReferenceExpression.class);
                    if (ktElement != null) {
                        element = ktElement;
                    }
                    return PositioningStrategyKt.markElement(element);
                }
                PsiElement rightParenthesis = ktValueArgumentList.getRightParenthesis();
                if (rightParenthesis == null) {
                    return PositioningStrategyKt.markElement(element);
                }
                PsiElement[] children = ktValueArgumentList.getChildren();
                children.getClass();
                int length = children.length - 1;
                if (length >= 0) {
                    while (true) {
                        int i = length - 1;
                        PsiElement psiElement2 = children[length];
                        if (psiElement2 instanceof KtValueArgument) {
                            psiElement = psiElement2;
                            break;
                        }
                        if (i < 0) {
                            break;
                        }
                        length = i;
                    }
                }
                PsiElement psiElement3 = psiElement;
                if (psiElement3 != null) {
                    return PositioningStrategyKt.markRange(psiElement3, rightParenthesis);
                }
                KtElement leftParenthesis = ktValueArgumentList.getLeftParenthesis();
                if (leftParenthesis != null) {
                    element = leftParenthesis;
                }
                return PositioningStrategyKt.markRange(element, rightParenthesis);
            }
        };
        VALUE_ARGUMENTS_LIST = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$VALUE_ARGUMENTS_LIST$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                element.getClass();
                KtElement ktElement = (KtValueArgumentList) PsiTreeUtil.getChildOfType(element, KtValueArgumentList.class);
                if (ktElement != null) {
                    element = ktElement;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        FUNCTION_PARAMETERS = new PositioningStrategy<KtFunction>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$FUNCTION_PARAMETERS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtFunction element) {
                element.getClass();
                KtParameterList valueParameterList = element.getValueParameterList();
                if (valueParameterList != null) {
                    return PositioningStrategyKt.markElement(valueParameterList);
                }
                if (!(element instanceof KtFunctionLiteral)) {
                    return PositioningStrategies.DECLARATION_SIGNATURE_OR_DEFAULT_WITH_VALIDITY_CHECK.mark(element);
                }
                ASTNode node = ((KtFunctionLiteral) element).getLBrace().getNode();
                node.getClass();
                return PositioningStrategyKt.markNode(node);
            }
        };
        CUT_CHAR_QUOTES = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$CUT_CHAR_QUOTES$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                element.getClass();
                if (element instanceof KtConstantExpression) {
                    KtConstantExpression ktConstantExpression = (KtConstantExpression) element;
                    if (Intrinsics.areEqual(ktConstantExpression.getNode().getElementType(), KtNodeTypes.CHARACTER_CONSTANT)) {
                        TextRange textRange = ktConstantExpression.getTextRange();
                        return CollectionsKt.listOf(TextRange.create(textRange.getStartOffset() + 1, textRange.getEndOffset() - 1));
                    }
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        LONG_LITERAL_SUFFIX = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$LONG_LITERAL_SUFFIX$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                element.getClass();
                if (!(element instanceof KtConstantExpression) || !Intrinsics.areEqual(((KtConstantExpression) element).getNode().getElementType(), KtNodeTypes.INTEGER_CONSTANT)) {
                    return PositioningStrategyKt.markElement(element);
                }
                int endOffset = PsiUtilsKt.getEndOffset(element);
                return CollectionsKt.listOf(TextRange.create(endOffset - 1, endOffset));
            }
        };
        AS_TYPE = new PositioningStrategy<KtBinaryExpressionWithTypeRHS>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$AS_TYPE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtBinaryExpressionWithTypeRHS element) {
                element.getClass();
                KtSimpleNameExpression operationReference = element.getOperationReference();
                operationReference.getClass();
                return PositioningStrategyKt.markRange(operationReference, element);
            }
        };
        IElementType iElementType22 = KtTokens.COMPANION_KEYWORD;
        iElementType22.getClass();
        COMPANION_OBJECT = new ModifierSetBasedPositioningStrategy(iElementType22);
        SECONDARY_CONSTRUCTOR_DELEGATION_CALL = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SECONDARY_CONSTRUCTOR_DELEGATION_CALL$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                if (element instanceof KtSecondaryConstructor) {
                    KtSecondaryConstructor ktSecondaryConstructor = (KtSecondaryConstructor) element;
                    KtParameterList valueParameterList = ktSecondaryConstructor.getValueParameterList();
                    if (valueParameterList == null) {
                        return PositioningStrategyKt.markElement(element);
                    }
                    PsiElement constructorKeyword = ktSecondaryConstructor.getConstructorKeyword();
                    PsiElement lastChild = valueParameterList.getLastChild();
                    lastChild.getClass();
                    return PositioningStrategyKt.markRange(constructorKeyword, lastChild);
                }
                if (!(element instanceof KtConstructorDelegationCall)) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtConstructorDelegationCall ktConstructorDelegationCall = (KtConstructorDelegationCall) element;
                if (!ktConstructorDelegationCall.isImplicit()) {
                    PsiElement calleeExpression = ktConstructorDelegationCall.getCalleeExpression();
                    if (calleeExpression != null) {
                        element = calleeExpression;
                    }
                    return PositioningStrategyKt.markElement(element);
                }
                KtSecondaryConstructor parentOfType = PsiTreeUtil.getParentOfType(element, KtSecondaryConstructor.class, true);
                parentOfType.getClass();
                KtSecondaryConstructor ktSecondaryConstructor2 = parentOfType;
                KtParameterList valueParameterList2 = ktSecondaryConstructor2.getValueParameterList();
                if (valueParameterList2 == null) {
                    return PositioningStrategyKt.markElement(ktSecondaryConstructor2);
                }
                PsiElement constructorKeyword2 = ktSecondaryConstructor2.getConstructorKeyword();
                PsiElement lastChild2 = valueParameterList2.getLastChild();
                lastChild2.getClass();
                return PositioningStrategyKt.markRange(constructorKeyword2, lastChild2);
            }
        };
        DELEGATOR_SUPER_CALL = new PositioningStrategy<KtEnumEntry>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DELEGATOR_SUPER_CALL$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtEnumEntry element) {
                element.getClass();
                List superTypeListEntries = element.getSuperTypeListEntries();
                superTypeListEntries.getClass();
                if (!superTypeListEntries.isEmpty()) {
                    Object obj = superTypeListEntries.get(0);
                    obj.getClass();
                    element = (KtElementImplStub) obj;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        UNUSED_VALUE = new PositioningStrategy<KtBinaryExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$UNUSED_VALUE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtBinaryExpression element) {
                element.getClass();
                KtExpression left = element.getLeft();
                left.getClass();
                int startOffset = PsiUtilsKt.getStartOffset(left);
                KtOperationReferenceExpression operationReference = element.getOperationReference();
                operationReference.getClass();
                return CollectionsKt.listOf(new TextRange(startOffset, PsiUtilsKt.getEndOffset(operationReference)));
            }
        };
        USELESS_ELVIS = new PositioningStrategy<KtBinaryExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$USELESS_ELVIS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtBinaryExpression element) {
                element.getClass();
                KtOperationReferenceExpression operationReference = element.getOperationReference();
                operationReference.getClass();
                return CollectionsKt.listOf(new TextRange(PsiUtilsKt.getStartOffset(operationReference), PsiUtilsKt.getEndOffset(element)));
            }
        };
        USELESS_ELVIS_LEFT = new PositioningStrategy<KtBinaryExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$USELESS_ELVIS_LEFT$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtBinaryExpression element) {
                element.getClass();
                int startOffset = PsiUtilsKt.getStartOffset(element);
                KtOperationReferenceExpression operationReference = element.getOperationReference();
                operationReference.getClass();
                return CollectionsKt.listOf(new TextRange(startOffset, PsiUtilsKt.getEndOffset(operationReference)));
            }
        };
        IMPORT_ALIAS = new PositioningStrategy<KtImportDirective>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$IMPORT_ALIAS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtImportDirective element) {
                KtExpression selectorExpression;
                PsiElement nameIdentifier;
                element.getClass();
                KtImportAlias alias = element.getAlias();
                if (alias != null && (nameIdentifier = alias.getNameIdentifier()) != null) {
                    return PositioningStrategyKt.markElement(nameIdentifier);
                }
                KtQualifiedExpression importedReference = element.getImportedReference();
                if (importedReference != null) {
                    return (!(importedReference instanceof KtQualifiedExpression) || (selectorExpression = importedReference.getSelectorExpression()) == null) ? PositioningStrategyKt.markElement(importedReference) : PositioningStrategyKt.markElement(selectorExpression);
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        RETURN_WITH_LABEL = new PositioningStrategy<KtReturnExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$RETURN_WITH_LABEL$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtReturnExpression element) {
                element.getClass();
                PsiElement labeledExpression = element.getLabeledExpression();
                if (labeledExpression != null) {
                    return PositioningStrategyKt.markRange(element, labeledExpression);
                }
                PsiElement returnKeyword = element.getReturnKeyword();
                returnKeyword.getClass();
                return PositioningStrategyKt.markElement(returnKeyword);
            }
        };
        RECEIVER = new PositioningStrategy<KtCallableDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$RECEIVER$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtCallableDeclaration element) {
                element.getClass();
                KtTypeReference receiverTypeReference = element.getReceiverTypeReference();
                return receiverTypeReference != null ? PositioningStrategyKt.markElement(receiverTypeReference) : PositioningStrategies.DEFAULT.mark(element);
            }
        };
        OPERATOR = new PositioningStrategy<KtExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$OPERATOR$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtExpression element) {
                element.getClass();
                if (element instanceof KtBinaryExpression) {
                    KtOperationReferenceExpression operationReference = ((KtBinaryExpression) element).getOperationReference();
                    operationReference.getClass();
                    return mark((KtExpression) operationReference);
                }
                if (element instanceof KtBinaryExpressionWithTypeRHS) {
                    KtSimpleNameExpression operationReference2 = ((KtBinaryExpressionWithTypeRHS) element).getOperationReference();
                    operationReference2.getClass();
                    return mark((KtExpression) operationReference2);
                }
                if (!(element instanceof KtUnaryExpression)) {
                    return super.mark(element);
                }
                KtSimpleNameExpression operationReference3 = ((KtUnaryExpression) element).getOperationReference();
                operationReference3.getClass();
                return mark((KtExpression) operationReference3);
            }
        };
        DOT_BY_QUALIFIED = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DOT_BY_QUALIFIED$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                KtExpression left;
                element.getClass();
                if (element instanceof KtBinaryExpression) {
                    KtBinaryExpression ktBinaryExpression = (KtBinaryExpression) element;
                    if (KtTokens.ALL_ASSIGNMENTS.contains(ktBinaryExpression.getOperationToken()) && (left = ktBinaryExpression.getLeft()) != null) {
                        PositioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$$inlined$findDescendantOfType$default$1 positioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$$inlined$findDescendantOfType$default$1 = new Function1<KtDotQualifiedExpression, Boolean>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$$inlined$findDescendantOfType$default$1
                            public final Boolean invoke(KtDotQualifiedExpression ktDotQualifiedExpression) {
                                ktDotQualifiedExpression.getClass();
                                return Boolean.TRUE;
                            }
                        };
                        PsiUtilsKt.checkDecompiledText(left);
                        Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        left.accept(new PositioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$.inlined.findDescendantOfType.default.2(positioningStrategies$DOT_BY_QUALIFIED$1$mark$lambda$0$$inlined$findDescendantOfType$default$1, objectRef));
                        KtDotQualifiedExpression ktDotQualifiedExpression = (PsiElement) objectRef.element;
                        if (ktDotQualifiedExpression != null) {
                            return mark(ktDotQualifiedExpression);
                        }
                    }
                }
                if (!(element instanceof KtDotQualifiedExpression)) {
                    return PositioningStrategies.INSTANCE.getREFERENCE_BY_QUALIFIED().mark(element);
                }
                PsiElement psi = ((KtDotQualifiedExpression) element).getOperationTokenNode().getPsi();
                psi.getClass();
                return mark(psi);
            }
        };
        SELECTOR_BY_QUALIFIED = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SELECTOR_BY_QUALIFIED$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                KtTypeElement typeElement;
                KtElement referencedTypeExpression;
                PsiElement nameIdentifier;
                KtExpression selectorExpression;
                KtExpression left;
                element.getClass();
                if (element instanceof KtBinaryExpression) {
                    KtBinaryExpression ktBinaryExpression = (KtBinaryExpression) element;
                    if (KtTokens.ALL_ASSIGNMENTS.contains(ktBinaryExpression.getOperationToken()) && (left = ktBinaryExpression.getLeft()) != null) {
                        return mark(left);
                    }
                }
                if ((element instanceof KtQualifiedExpression) && (selectorExpression = ((KtQualifiedExpression) element).getSelectorExpression()) != null) {
                    return mark(selectorExpression);
                }
                if (element instanceof KtImportDirective) {
                    KtImportDirective ktImportDirective = (KtImportDirective) element;
                    KtImportAlias alias = ktImportDirective.getAlias();
                    if (alias != null && (nameIdentifier = alias.getNameIdentifier()) != null) {
                        return mark(nameIdentifier);
                    }
                    KtExpression importedReference = ktImportDirective.getImportedReference();
                    if (importedReference != null) {
                        return mark(importedReference);
                    }
                }
                return (!(element instanceof KtTypeReference) || (typeElement = ((KtTypeReference) element).getTypeElement()) == null || (referencedTypeExpression = PositioningStrategies.INSTANCE.getReferencedTypeExpression(typeElement)) == null) ? super.mark(element) : mark(referencedTypeExpression);
            }
        };
        NAME_IDENTIFIER = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$NAME_IDENTIFIER$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                KtExpression packageNameExpression;
                element.getClass();
                if (element instanceof PsiNameIdentifierOwner) {
                    PsiElement nameIdentifier = ((PsiNameIdentifierOwner) element).getNameIdentifier();
                    if (nameIdentifier != null) {
                        return super.mark(nameIdentifier);
                    }
                } else {
                    if (element instanceof KtLabelReferenceExpression) {
                        return super.mark(((KtLabelReferenceExpression) element).getReferencedNameElement());
                    }
                    if ((element instanceof KtPackageDirective) && (packageNameExpression = ((KtPackageDirective) element).getPackageNameExpression()) != null) {
                        return super.mark(packageNameExpression);
                    }
                }
                return PositioningStrategies.DEFAULT.mark(element);
            }
        };
        SPREAD_OPERATOR = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SPREAD_OPERATOR$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                LeafPsiElement spreadElement;
                ASTNode node;
                PsiElement psi;
                element.getClass();
                KtValueArgument ktValueArgument = element instanceof KtValueArgument ? (KtValueArgument) element : null;
                if (ktValueArgument != null && (spreadElement = ktValueArgument.getSpreadElement()) != null && (node = spreadElement.getNode()) != null && (psi = node.getPsi()) != null) {
                    element = psi;
                }
                return super.mark(element);
            }
        };
        FUN_INTERFACE = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$FUN_INTERFACE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                element.getClass();
                if (element instanceof KtClass) {
                    return PositioningStrategies.FUN_MODIFIER.mark(element);
                }
                if (element instanceof KtProperty) {
                    PsiElement valOrVarKeyword = ((KtProperty) element).getValOrVarKeyword();
                    valOrVarKeyword.getClass();
                    return PositioningStrategyKt.markElement(valOrVarKeyword);
                }
                if (!(element instanceof KtNamedFunction)) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtNamedFunction ktNamedFunction = (KtNamedFunction) element;
                KtTypeParameterList typeParameterList = ktNamedFunction.getTypeParameterList();
                if (typeParameterList != null) {
                    return PositioningStrategyKt.markElement(typeParameterList);
                }
                if (ktNamedFunction.hasModifier(KtTokens.SUSPEND_KEYWORD)) {
                    return PositioningStrategies.SUSPEND_MODIFIER.mark(element);
                }
                KtDeclaration funKeyword = ktNamedFunction.getFunKeyword();
                if (funKeyword != null) {
                    element = funKeyword;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        REFERENCE_BY_QUALIFIED = new FindReferencePositioningStrategy(false);
        REFERENCED_NAME_BY_QUALIFIED = new FindReferencePositioningStrategy(true);
        IElementType iElementType23 = KtTokens.REIFIED_KEYWORD;
        iElementType23.getClass();
        REIFIED_MODIFIER = new ModifierSetBasedPositioningStrategy(iElementType23);
        VARIABLE_INITIALIZER = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$VARIABLE_INITIALIZER$1
            /* JADX WARN: Code duplicated, block: B:8:0x0016 A[PHI: r1
              0x0016: PHI (r1v15 org.jetbrains.kotlin.psi.KtElement) = 
              (r1v7 org.jetbrains.kotlin.psi.KtElement)
              (r1v10 org.jetbrains.kotlin.psi.KtElement)
              (r1v13 org.jetbrains.kotlin.psi.KtElement)
              (r1v18 org.jetbrains.kotlin.psi.KtElement)
             binds: [B:35:0x0061, B:28:0x004d, B:18:0x0032, B:7:0x0014] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:9:0x0018 A[PHI: r0
              0x0018: PHI (r0v5 org.jetbrains.kotlin.psi.KtElement) = 
              (r0v0 org.jetbrains.kotlin.psi.KtElement)
              (r0v1 org.jetbrains.kotlin.psi.KtElement)
              (r0v2 org.jetbrains.kotlin.psi.KtElement)
              (r0v3 org.jetbrains.kotlin.psi.KtElement)
              (r0v4 org.jetbrains.kotlin.psi.KtElement)
              (r0v6 org.jetbrains.kotlin.psi.KtElement)
             binds: [B:33:0x005b, B:23:0x0040, B:25:0x0046, B:13:0x0025, B:15:0x002b, B:5:0x000e] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                KtElement equalsToken;
                KtElement initializer;
                element.getClass();
                if (element instanceof KtProperty) {
                    KtProperty ktProperty = (KtProperty) element;
                    equalsToken = ktProperty.getEqualsToken();
                    if (equalsToken == null) {
                        initializer = ktProperty.getInitializer();
                        if (initializer != null) {
                            element = initializer;
                        }
                    } else {
                        element = equalsToken;
                    }
                } else if (element instanceof KtParameter) {
                    KtParameter ktParameter = (KtParameter) element;
                    equalsToken = ktParameter.getEqualsToken();
                    if (equalsToken == null && (equalsToken = ktParameter.getDefaultValue()) == null) {
                        initializer = ktParameter.getTypeReference();
                        if (initializer != null) {
                            element = initializer;
                        }
                    } else {
                        element = equalsToken;
                    }
                } else if (element instanceof KtDestructuringDeclarationEntry) {
                    KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry = (KtDestructuringDeclarationEntry) element;
                    equalsToken = ktDestructuringDeclarationEntry.getEqualsToken();
                    if (equalsToken == null && (equalsToken = ktDestructuringDeclarationEntry.getInitializer()) == null) {
                        initializer = ktDestructuringDeclarationEntry.getTypeReference();
                        if (initializer != null) {
                            element = initializer;
                        }
                    } else {
                        element = equalsToken;
                    }
                } else if (element instanceof KtBackingField) {
                    KtBackingField ktBackingField = (KtBackingField) element;
                    equalsToken = ktBackingField.getEqualsToken();
                    if (equalsToken == null) {
                        initializer = ktBackingField.getInitializer();
                        if (initializer != null) {
                            element = initializer;
                        }
                    } else {
                        element = equalsToken;
                    }
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        WHOLE_ELEMENT = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$WHOLE_ELEMENT$1
        };
        TYPE_PARAMETERS_LIST = new PositioningStrategy<KtDeclaration>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$TYPE_PARAMETERS_LIST$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtDeclaration element) {
                element.getClass();
                if (!(element instanceof KtTypeParameterListOwner)) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtDeclaration typeParameterList = ((KtTypeParameterListOwner) element).getTypeParameterList();
                if (typeParameterList != null) {
                    element = typeParameterList;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        FUNCTION_TYPE_RECEIVER = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$FUNCTION_TYPE_RECEIVER$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                KtTypeReference childOfType;
                PsiElement[] children;
                PsiElement psiElement;
                KtElement ktElement;
                element.getClass();
                KtElement ktElement2 = Intrinsics.areEqual(PsiTreeUtilKt.getElementType(element), KtNodeTypes.VALUE_PARAMETER) ? element : null;
                if (ktElement2 != null && (childOfType = PsiTreeUtil.getChildOfType(ktElement2, KtTypeReference.class)) != null && (children = childOfType.getChildren()) != null && (psiElement = (PsiElement) ArraysKt.firstOrNull(children)) != null) {
                    PsiElement psiElement2 = Intrinsics.areEqual(PsiTreeUtilKt.getElementType(psiElement), KtNodeTypes.FUNCTION_TYPE) ? psiElement : null;
                    if (psiElement2 != null && (ktElement = (KtFunctionTypeReceiver) PsiTreeUtil.getChildOfType(psiElement2, KtFunctionTypeReceiver.class)) != null) {
                        element = ktElement;
                    }
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        ANNOTATION_USE_SITE = new PositioningStrategy<KtAnnotationEntry>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$ANNOTATION_USE_SITE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtAnnotationEntry element) {
                element.getClass();
                KtAnnotationEntry useSiteTarget = element.getUseSiteTarget();
                if (useSiteTarget != null) {
                    element = useSiteTarget;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        IMPORT_LAST_NAME = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$IMPORT_LAST_NAME$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                KtExpression selectorExpression;
                element.getClass();
                if (!(element instanceof KtImportDirective)) {
                    return super.mark(element);
                }
                KtImportDirective ktImportDirective = (KtImportDirective) element;
                KtDotQualifiedExpression importedReference = ktImportDirective.getImportedReference();
                if ((importedReference instanceof KtDotQualifiedExpression) && (selectorExpression = importedReference.getSelectorExpression()) != null) {
                    return super.mark(selectorExpression);
                }
                KtElement importedReference2 = ktImportDirective.getImportedReference();
                if (importedReference2 == null) {
                    importedReference2 = (KtElement) element;
                }
                return super.mark(importedReference2);
            }
        };
        IMPORT_LAST_BUT_ONE_NAME = new PositioningStrategy<KtImportDirective>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$IMPORT_LAST_BUT_ONE_NAME$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtImportDirective element) {
                KtDotQualifiedExpression receiverExpression;
                KtExpression selectorExpression;
                element.getClass();
                KtExpression importedReference = element.getImportedReference();
                if (importedReference != null) {
                    if (!(importedReference instanceof KtDotQualifiedExpression)) {
                        importedReference = null;
                    }
                    KtDotQualifiedExpression ktDotQualifiedExpression = (KtDotQualifiedExpression) importedReference;
                    if (ktDotQualifiedExpression != null && (receiverExpression = ktDotQualifiedExpression.getReceiverExpression()) != null) {
                        KtDotQualifiedExpression ktDotQualifiedExpression2 = receiverExpression instanceof KtDotQualifiedExpression ? receiverExpression : null;
                        if (ktDotQualifiedExpression2 != null && (selectorExpression = ktDotQualifiedExpression2.getSelectorExpression()) != null) {
                            return PositioningStrategyKt.markElement(selectorExpression);
                        }
                    }
                }
                return super.mark(element);
            }
        };
        LABEL = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$LABEL$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                KtElement labelQualifier;
                element.getClass();
                KtExpressionWithLabel ktExpressionWithLabel = element instanceof KtExpressionWithLabel ? (KtExpressionWithLabel) element : null;
                if (ktExpressionWithLabel != null && (labelQualifier = ktExpressionWithLabel.getLabelQualifier()) != null) {
                    element = labelQualifier;
                }
                return super.mark(element);
            }
        };
        COMMAS = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$COMMAS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public boolean isValid(PsiElement element) {
                element.getClass();
                return true;
            }

            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                for (PsiElement psiElement : PsiUtilsKt.getAllChildren(element)) {
                    if (Intrinsics.areEqual(psiElement.getNode().getElementType(), KtTokens.COMMA)) {
                        listCreateListBuilder.add(PositioningStrategyKt.markSingleElement(psiElement));
                    }
                }
                return CollectionsKt.build(listCreateListBuilder);
            }
        };
        iElementType.getClass();
        iElementType2.getClass();
        IElementType iElementType24 = KtTokens.SEALED_KEYWORD;
        iElementType24.getClass();
        NON_FINAL_MODIFIER_OR_NAME = new ModifierSetBasedPositioningStrategy(iElementType, iElementType2, iElementType24);
        DELEGATED_SUPERTYPE_BY_KEYWORD = new PositioningStrategy<KtTypeReference>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DELEGATED_SUPERTYPE_BY_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtTypeReference element) {
                element.getClass();
                KtDelegatedSuperTypeEntry parent = element.getParent();
                KtDelegatedSuperTypeEntry ktDelegatedSuperTypeEntry = parent instanceof KtDelegatedSuperTypeEntry ? parent : null;
                if (ktDelegatedSuperTypeEntry == null) {
                    return super.mark(element);
                }
                KtTypeReference psi = ktDelegatedSuperTypeEntry.getByKeywordNode().getPsi();
                if (psi != null) {
                    element = psi;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        PROPERTY_DELEGATE_BY_KEYWORD = new PositioningStrategy<KtExpression>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$PROPERTY_DELEGATE_BY_KEYWORD$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtExpression element) {
                ASTNode byKeywordNode;
                KtExpression psi;
                element.getClass();
                KtPropertyDelegate parent = element.getParent();
                KtPropertyDelegate ktPropertyDelegate = parent instanceof KtPropertyDelegate ? parent : null;
                if (ktPropertyDelegate != null && (byKeywordNode = ktPropertyDelegate.getByKeywordNode()) != null && (psi = byKeywordNode.getPsi()) != null) {
                    element = psi;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        TYPEALIAS_TYPE_REFERENCE = new PositioningStrategy<KtTypeAlias>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$TYPEALIAS_TYPE_REFERENCE$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtTypeAlias element) {
                element.getClass();
                KtTypeAlias typeReference = element.getTypeReference();
                if (typeReference != null) {
                    element = typeReference;
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS_DIAGNOSTIC$1
            /* JADX WARN: Code duplicated, block: B:6:0x0010 A[PHI: r2
              0x0010: PHI (r2v14 org.jetbrains.kotlin.psi.KtElement) = (r2v10 org.jetbrains.kotlin.psi.KtElement), (r2v17 org.jetbrains.kotlin.psi.KtElement) binds: [B:22:0x003e, B:5:0x000e] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                KtElement valueArgumentList;
                element.getClass();
                if (element instanceof KtEnumEntry) {
                    valueArgumentList = ((KtEnumEntry) element).getInitializerList();
                    if (valueArgumentList != null) {
                        element = valueArgumentList;
                    } else {
                        element = (KtElementImplStub) element;
                    }
                } else if (element instanceof KtTypeReference) {
                    KtConstructorCalleeExpression parent = ((KtTypeReference) element).getParent();
                    KtConstructorCalleeExpression ktConstructorCalleeExpression = parent instanceof KtConstructorCalleeExpression ? parent : null;
                    PsiElement parent2 = ktConstructorCalleeExpression != null ? ktConstructorCalleeExpression.getParent() : null;
                    KtSuperTypeCallEntry ktSuperTypeCallEntry = parent2 instanceof KtSuperTypeCallEntry ? (KtSuperTypeCallEntry) parent2 : null;
                    if (ktSuperTypeCallEntry == null || (valueArgumentList = ktSuperTypeCallEntry.getValueArgumentList()) == null) {
                        element = (KtElementImplStub) element;
                    } else {
                        element = valueArgumentList;
                    }
                }
                return PositioningStrategyKt.markElement(element);
            }
        };
        TYPE_ARGUMENT_LIST_OR_SELF = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$TYPE_ARGUMENT_LIST_OR_SELF$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                PsiElement selectorExpression;
                element.getClass();
                KtQualifiedExpression ktQualifiedExpression = element instanceof KtQualifiedExpression ? (KtQualifiedExpression) element : null;
                if (ktQualifiedExpression == null || (selectorExpression = ktQualifiedExpression.getSelectorExpression()) == null) {
                    selectorExpression = element;
                }
                KtTypeArgumentList childOfType = PsiTreeUtil.getChildOfType(selectorExpression, KtTypeArgumentList.class);
                return childOfType != null ? PositioningStrategyKt.markElement(childOfType) : super.mark(element);
            }
        };
        TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                KtQualifiedExpression ktQualifiedExpression = element instanceof KtQualifiedExpression ? (KtQualifiedExpression) element : null;
                PsiElement selectorExpression = ktQualifiedExpression != null ? ktQualifiedExpression.getSelectorExpression() : null;
                KtTypeArgumentList childOfType = PsiTreeUtil.getChildOfType(selectorExpression != null ? selectorExpression : element, KtTypeArgumentList.class);
                if (childOfType != null) {
                    return PositioningStrategyKt.markElement(childOfType);
                }
                return selectorExpression != null ? PositioningStrategyKt.markElement(selectorExpression) : super.mark(element);
            }
        };
        PACKAGE_DIRECTIVE_NAME_EXPRESSION = new PositioningStrategy<KtElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$PACKAGE_DIRECTIVE_NAME_EXPRESSION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtElement element) {
                element.getClass();
                KtPackageDirective ktPackageDirective = element instanceof KtPackageDirective ? (KtPackageDirective) element : null;
                KtElement packageNameExpression = ktPackageDirective != null ? ktPackageDirective.getPackageNameExpression() : null;
                if (packageNameExpression != null) {
                    element = packageNameExpression;
                }
                return super.mark(element);
            }
        };
        OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                element.getClass();
                PsiElement assignmentLhsIfUnwrappable = PsiUtilsKt.getAssignmentLhsIfUnwrappable(element);
                return assignmentLhsIfUnwrappable == null ? super.mark(element) : super.mark(assignmentLhsIfUnwrappable);
            }
        };
        DEPRECATION = new PositioningStrategy<PsiElement>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies$DEPRECATION$1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(PsiElement element) {
                KtTypeReference typeReference;
                element.getClass();
                if (!(element instanceof KtConstructorCalleeExpression) || (typeReference = ((KtConstructorCalleeExpression) element).getTypeReference()) == null) {
                    return element instanceof KtTypeReference ? PositioningStrategies.INSTANCE.getSELECTOR_BY_QUALIFIED().mark(element) : PositioningStrategies.INSTANCE.getREFERENCED_NAME_BY_QUALIFIED().mark(element);
                }
                return mark(typeReference);
            }
        };
    }

    private PositioningStrategies() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PsiElement findStartingPsiElementForDeclarationName(KtNamedDeclaration element) {
        PsiElement modifier;
        KtModifierList modifierList = element.getModifierList();
        if (modifierList != null && (modifier = modifierList.getModifier(KtTokens.ENUM_KEYWORD)) != null) {
            return modifier;
        }
        ASTNode aSTNodeFindChildByType = element.getNode().findChildByType(TokenSet.create(new IElementType[]{KtTokens.CLASS_KEYWORD, KtTokens.OBJECT_KEYWORD}));
        PsiElement psi = aSTNodeFindChildByType != null ? aSTNodeFindChildByType.getPsi() : null;
        return psi == null ? element : psi;
    }

    public static /* synthetic */ void getIMPORT_LAST_BUT_ONE_NAME$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KtElement getReferencedTypeExpression(KtTypeElement ktTypeElement) {
        KtTypeElement innerType;
        if (ktTypeElement instanceof KtUserType) {
            return ((KtUserType) ktTypeElement).getReferenceExpression();
        }
        if (!(ktTypeElement instanceof KtNullableType) || (innerType = ((KtNullableType) ktTypeElement).getInnerType()) == null) {
            return null;
        }
        return getReferencedTypeExpression(innerType);
    }

    @JvmStatic
    public static final PositioningStrategy<KtModifierListOwner> modifierSetPosition(final KtModifierKeywordToken... tokens) {
        tokens.getClass();
        return new PositioningStrategy<KtModifierListOwner>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies.modifierSetPosition.1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtModifierListOwner element) {
                element.getClass();
                KtModifierList modifierList = element.getModifierList();
                if (modifierList == null) {
                    return PositioningStrategies.DEFAULT.mark(element);
                }
                for (KtModifierKeywordToken ktModifierKeywordToken : tokens) {
                    PsiElement modifier = modifierList.getModifier(ktModifierKeywordToken);
                    if (modifier != null) {
                        return PositioningStrategyKt.markElement(modifier);
                    }
                }
                return PositioningStrategies.DEFAULT.mark(element);
            }
        };
    }

    @JvmStatic
    public static final PositioningStrategy<KtModifierListOwner> projectionPosition() {
        return new PositioningStrategy<KtModifierListOwner>() { // from class: org.jetbrains.kotlin.diagnostics.PositioningStrategies.projectionPosition.1
            @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
            public List<TextRange> mark(KtModifierListOwner element) {
                element.getClass();
                if ((element instanceof KtTypeProjection) && ((KtTypeProjection) element).getProjectionKind() == KtProjectionKind.STAR) {
                    return PositioningStrategyKt.markElement(element);
                }
                KtModifierList modifierList = element.getModifierList();
                if (modifierList == null) {
                    x01.a("No modifier list, but modifier has been found by the analyzer");
                    return null;
                }
                PsiElement modifier = modifierList.getModifier(KtTokens.IN_KEYWORD);
                if (modifier != null) {
                    return PositioningStrategyKt.markElement(modifier);
                }
                PsiElement modifier2 = modifierList.getModifier(KtTokens.OUT_KEYWORD);
                if (modifier2 != null) {
                    return PositioningStrategyKt.markElement(modifier2);
                }
                k2d.a("None of the modifiers is found: in, out");
                return null;
            }
        };
    }

    public final PositioningStrategy<KtAnnotationEntry> getANNOTATION_USE_SITE() {
        return ANNOTATION_USE_SITE;
    }

    public final PositioningStrategy<PsiElement> getCOMMAS() {
        return COMMAS;
    }

    public final TokenSet getClassKindTokens() {
        return classKindTokens;
    }

    public final PositioningStrategy<KtDeclaration> getDECLARATION_NAME() {
        return DECLARATION_NAME;
    }

    public final PositioningStrategy<KtDeclaration> getDECLARATION_SIGNATURE() {
        return DECLARATION_SIGNATURE;
    }

    public final PositioningStrategy<PsiElement> getDECLARATION_SIGNATURE_OR_DEFAULT() {
        return DECLARATION_SIGNATURE_OR_DEFAULT;
    }

    public final PositioningStrategy<KtTypeReference> getDELEGATED_SUPERTYPE_BY_KEYWORD() {
        return DELEGATED_SUPERTYPE_BY_KEYWORD;
    }

    public final PositioningStrategy<PsiElement> getDEPRECATION() {
        return DEPRECATION;
    }

    public final PositioningStrategy<PsiElement> getDOT_BY_QUALIFIED() {
        return DOT_BY_QUALIFIED;
    }

    public final PositioningStrategy<KtElement> getFUNCTION_TYPE_RECEIVER() {
        return FUNCTION_TYPE_RECEIVER;
    }

    public final PositioningStrategy<KtImportDirective> getIMPORT_LAST_BUT_ONE_NAME() {
        return IMPORT_LAST_BUT_ONE_NAME;
    }

    public final PositioningStrategy<PsiElement> getIMPORT_LAST_NAME() {
        return IMPORT_LAST_NAME;
    }

    public final PositioningStrategy<KtElement> getLABEL() {
        return LABEL;
    }

    public final PositioningStrategy<PsiElement> getNAME_IDENTIFIER() {
        return NAME_IDENTIFIER;
    }

    public final PositioningStrategy<KtModifierListOwner> getNON_FINAL_MODIFIER_OR_NAME() {
        return NON_FINAL_MODIFIER_OR_NAME;
    }

    public final PositioningStrategy<KtExpression> getOPERATOR() {
        return OPERATOR;
    }

    public final PositioningStrategy<PsiElement> getOUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS() {
        return OUTERMOST_PARENTHESES_IN_ASSIGNMENT_LHS;
    }

    public final PositioningStrategy<KtElement> getPACKAGE_DIRECTIVE_NAME_EXPRESSION() {
        return PACKAGE_DIRECTIVE_NAME_EXPRESSION;
    }

    public final PositioningStrategy<KtExpression> getPROPERTY_DELEGATE_BY_KEYWORD() {
        return PROPERTY_DELEGATE_BY_KEYWORD;
    }

    public final PositioningStrategy<PsiElement> getREFERENCED_NAME_BY_QUALIFIED() {
        return REFERENCED_NAME_BY_QUALIFIED;
    }

    public final PositioningStrategy<PsiElement> getREFERENCE_BY_QUALIFIED() {
        return REFERENCE_BY_QUALIFIED;
    }

    public final PositioningStrategy<KtModifierListOwner> getREIFIED_MODIFIER() {
        return REIFIED_MODIFIER;
    }

    public final PositioningStrategy<PsiElement> getSELECTOR_BY_QUALIFIED() {
        return SELECTOR_BY_QUALIFIED;
    }

    public final PositioningStrategy<PsiElement> getSYNTAX_ERROR() {
        return SYNTAX_ERROR;
    }

    public final PositioningStrategy<PsiElement> getTYPE_ARGUMENT_LIST_OR_SELF() {
        return TYPE_ARGUMENT_LIST_OR_SELF;
    }

    public final PositioningStrategy<PsiElement> getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER() {
        return TYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER;
    }

    public final PositioningStrategy<KtDeclaration> getTYPE_PARAMETERS_LIST() {
        return TYPE_PARAMETERS_LIST;
    }

    public final PositioningStrategy<KtElement> getVARIABLE_INITIALIZER() {
        return VARIABLE_INITIALIZER;
    }

    public final PositioningStrategy<KtElement> getWHOLE_ELEMENT() {
        return WHOLE_ELEMENT;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0004\b\u0005\u0010\nJ\u001a\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0004J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategies$ModifierSetBasedPositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lcom/intellij/psi/PsiElement;", "modifierSet", "Lcom/intellij/psi/tree/TokenSet;", "<init>", "(Lcom/intellij/psi/tree/TokenSet;)V", "tokens", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "([Lcom/intellij/psi/tree/IElementType;)V", "markModifier", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "Lorg/jetbrains/kotlin/psi/KtModifierListOwner;", "mark", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static class ModifierSetBasedPositioningStrategy extends PositioningStrategy<PsiElement> {
        private final TokenSet modifierSet;

        /* JADX WARN: Illegal instructions before constructor call */
        public ModifierSetBasedPositioningStrategy(IElementType... iElementTypeArr) {
            iElementTypeArr.getClass();
            TokenSet tokenSetCreate = TokenSet.create((IElementType[]) Arrays.copyOf(iElementTypeArr, iElementTypeArr.length));
            tokenSetCreate.getClass();
            this(tokenSetCreate);
        }

        @Override // org.jetbrains.kotlin.diagnostics.PositioningStrategy
        public List<TextRange> mark(PsiElement element) {
            PsiElement nameIdentifier;
            List<TextRange> listMarkModifier;
            element.getClass();
            if ((element instanceof KtModifierListOwner) && (listMarkModifier = markModifier((KtModifierListOwner) element)) != null) {
                return listMarkModifier;
            }
            if ((element instanceof PsiNameIdentifierOwner) && (nameIdentifier = ((PsiNameIdentifierOwner) element).getNameIdentifier()) != null) {
                return PositioningStrategyKt.markElement(nameIdentifier);
            }
            if (element instanceof KtObjectDeclaration) {
                element = ((KtObjectDeclaration) element).getObjectKeyword();
                element.getClass();
            } else if (element instanceof KtPropertyAccessor) {
                element = ((KtPropertyAccessor) element).getNamePlaceholder();
            }
            return PositioningStrategyKt.markElement(element);
        }

        public final List<TextRange> markModifier(KtModifierListOwner element) {
            KtModifierList modifierList;
            KtModifierKeywordToken[] types = this.modifierSet.getTypes();
            types.getClass();
            ArrayList arrayList = new ArrayList();
            int length = types.length;
            int i = 0;
            while (true) {
                TextRange textRange = null;
                if (i >= length) {
                    break;
                }
                KtModifierKeywordToken ktModifierKeywordToken = types[i];
                if (element != null && (modifierList = element.getModifierList()) != null) {
                    ktModifierKeywordToken.getClass();
                    PsiElement modifier = modifierList.getModifier(ktModifierKeywordToken);
                    if (modifier != null) {
                        textRange = modifier.getTextRange();
                    }
                }
                if (textRange != null) {
                    arrayList.add(textRange);
                }
                i++;
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }

        public ModifierSetBasedPositioningStrategy(TokenSet tokenSet) {
            tokenSet.getClass();
            this.modifierSet = tokenSet;
        }
    }
}
