package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDanglingModifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u0002\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0003\u001a\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u0002\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003\u001a\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u0002\u001a\f\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u00020\u0003\u001a\f\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\b\u001a\f\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u00020\t\u001a\f\u0010\n\u001a\u0004\u0018\u00010\u0005*\u00020\u000b\u001a\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\r\u001a \u0010R\u001a\u0004\u0018\u0001HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a \u0010S\u001a\u0004\u0018\u0001HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a$\u0010R\u001a\u0004\u0018\u0001HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HNH\u0086\b¢\u0006\u0002\u0010O\u001a\u001e\u0010T\u001a\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a\"\u0010T\u001a\u0002HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HNH\u0086\b¢\u0006\u0002\u0010O\u001a\u001e\u0010U\u001a\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a\u001e\u0010V\u001a\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a\u001e\u0010W\u001a\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a\"\u0010W\u001a\u0002HK\"\u000e\b\u0000\u0010K\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HKH\u0086\b¢\u0006\u0002\u0010O\u001a\u001e\u0010X\u001a\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a\u001e\u0010Y\u001a\u0002HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HKH\u0086\b¢\u0006\u0002\u0010M\u001a\"\u0010Y\u001a\u0002HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HNH\u0086\b¢\u0006\u0002\u0010O\u001a\"\u0010U\u001a\u0002HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HNH\u0086\b¢\u0006\u0002\u0010O\u001a\"\u0010V\u001a\u0002HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HNH\u0086\b¢\u0006\u0002\u0010O\u001a\"\u0010X\u001a\u0002HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HNH\u0086\b¢\u0006\u0002\u0010O\"3\u0010\u000f\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"3\u0010\u0016\u001a\u0004\u0018\u00010\u0005*\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0015\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\"3\u0010\u001c\u001a\u0004\u0018\u00010\u0005*\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u0015\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \"3\u0010#\u001a\u0004\u0018\u00010\"*\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\"8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u0015\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'\"3\u0010*\u001a\u0004\u0018\u00010)*\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010)8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u0015\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\"3\u00101\u001a\u0004\u0018\u000100*\u0002022\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b7\u0010\u0015\u001a\u0004\b3\u00104\"\u0004\b5\u00106\"\u0015\u00108\u001a\u000200*\u0002028F¢\u0006\u0006\u001a\u0004\b9\u0010:\"3\u0010;\u001a\u0004\u0018\u000100*\u00020<2\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010\u0015\u001a\u0004\b;\u0010=\"\u0004\b>\u0010?\"\u0015\u0010A\u001a\u000200*\u00020\u00038F¢\u0006\u0006\u001a\u0004\bA\u0010B\"\u0015\u0010C\u001a\u000200*\u00020\u00038F¢\u0006\u0006\u001a\u0004\bC\u0010B\"\u0015\u0010D\u001a\u000200*\u00020\u00038F¢\u0006\u0006\u001a\u0004\bD\u0010B\"\u0015\u0010E\u001a\u000200*\u00020\u00038F¢\u0006\u0006\u001a\u0004\bE\u0010B\"\u0015\u0010F\u001a\u000200*\u00020\u00038F¢\u0006\u0006\u001a\u0004\bF\u0010B\"\u0015\u0010G\u001a\u000200*\u00020\u00038F¢\u0006\u0006\u001a\u0004\bH\u0010B\"\u0019\u0010A\u001a\u000200*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\bA\u0010I\"\u0019\u0010C\u001a\u000200*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\bC\u0010I\"\u0019\u0010D\u001a\u000200*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\bD\u0010I\"\u0019\u0010E\u001a\u000200*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\bE\u0010I\"\u0019\u0010F\u001a\u000200*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\bF\u0010I\"$\u0010J\u001a\u0004\u0018\u0001HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HK8Æ\u0002¢\u0006\u0006\u001a\u0004\bL\u0010M\"(\u0010J\u001a\u0004\u0018\u0001HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HN8Æ\u0002¢\u0006\u0006\u001a\u0004\bL\u0010O\"$\u0010P\u001a\u0004\u0018\u0001HK\"\n\b\u0000\u0010K\u0018\u0001*\u00020\u0003*\u0002HK8Æ\u0002¢\u0006\u0006\u001a\u0004\bQ\u0010M\"(\u0010P\u001a\u0004\u0018\u0001HN\"\u000e\b\u0000\u0010N\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u0002HN8Æ\u0002¢\u0006\u0006\u001a\u0004\bQ\u0010O\"=\u0010Z\u001a\u0004\u0018\u0001HK\"\b\b\u0000\u0010K*\u00020\u0003*\u0002HK2\b\u0010\u000e\u001a\u0004\u0018\u00018\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b^\u0010\u0015\u001a\u0004\b[\u0010M\"\u0004\b\\\u0010]\"=\u0010_\u001a\u0004\u0018\u0001HK\"\b\b\u0000\u0010K*\u00020\u0003*\u0002HK2\b\u0010\u000e\u001a\u0004\u0018\u00018\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bb\u0010\u0015\u001a\u0004\b`\u0010M\"\u0004\ba\u0010]\"3\u0010d\u001a\u0004\u0018\u00010c*\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010\u0015\u001a\u0004\be\u0010f\"\u0004\bg\u0010h\"=\u0010k\u001a\u0004\u0018\u00010j\"\b\b\u0000\u0010K*\u00020l*\u0002HK2\b\u0010\u000e\u001a\u0004\u0018\u00010j8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bq\u0010\u0015\u001a\u0004\bm\u0010n\"\u0004\bo\u0010p\"3\u0010s\u001a\u0004\u0018\u00010r*\u00020t2\b\u0010\u000e\u001a\u0004\u0018\u00010r8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\by\u0010\u0015\u001a\u0004\bu\u0010v\"\u0004\bw\u0010x\"\u0015\u0010z\u001a\u00020\u0003*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b{\u0010M\"\u0015\u0010|\u001a\u00020\u0003*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b}\u0010M\"4\u0010~\u001a\u0004\u0018\u000100*\u00020<2\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0013\n\u0005\b\u0080\u0001\u0010\u0015\u001a\u0004\b~\u0010=\"\u0004\b\u007f\u0010?\":\u0010\u0081\u0001\u001a\u0004\u0018\u000100*\u00030\u0082\u00012\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0086\u0001\u0010\u0015\u001a\u0006\b\u0081\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001\"7\u0010\u0087\u0001\u001a\u0004\u0018\u000100*\u00020<2\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0089\u0001\u0010\u0015\u001a\u0005\b\u0087\u0001\u0010=\"\u0005\b\u0088\u0001\u0010?\"9\u0010\u008a\u0001\u001a\u0004\u0018\u000100*\u00020r2\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u008e\u0001\u0010\u0015\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001\"9\u0010\u008f\u0001\u001a\u0004\u0018\u000100*\u00020r2\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0091\u0001\u0010\u0015\u001a\u0006\b\u008f\u0001\u0010\u008b\u0001\"\u0006\b\u0090\u0001\u0010\u008d\u0001\"Q\u0010\u0093\u0001\u001a\u000b\u0012\u0004\u0012\u0002HK\u0018\u00010\u0092\u0001\"\b\b\u0000\u0010K*\u00020\u0003*\u0002HK2\u000f\u0010\u000e\u001a\u000b\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0092\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0098\u0001\u0010\u0015\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001\"1\u0010\u0093\u0001\u001a\u000b\u0012\u0004\u0012\u0002HK\u0018\u00010\u0092\u0001\"\b\b\u0000\u0010K*\u00020\u0003*\b\u0012\u0004\u0012\u0002HK0\u00028F¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0099\u0001\";\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009a\u0001*\u00020t2\t\u0010\u000e\u001a\u0005\u0018\u00010\u009a\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b \u0001\u0010\u0015\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001\"\u001c\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009a\u0001*\u00030¡\u00018F¢\u0006\b\u001a\u0006\b\u009c\u0001\u0010¢\u0001\";\u0010¤\u0001\u001a\u0005\u0018\u00010£\u0001*\u0002022\t\u0010\u000e\u001a\u0005\u0018\u00010£\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b©\u0001\u0010\u0015\u001a\u0006\b¥\u0001\u0010¦\u0001\"\u0006\b§\u0001\u0010¨\u0001\" \u0010¤\u0001\u001a\u0005\u0018\u00010£\u0001*\u0007\u0012\u0002\b\u00030ª\u00018F¢\u0006\b\u001a\u0006\b¥\u0001\u0010«\u0001\";\u0010\u00ad\u0001\u001a\u0005\u0018\u00010¬\u0001*\u0002022\t\u0010\u000e\u001a\u0005\u0018\u00010¬\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b²\u0001\u0010\u0015\u001a\u0006\b®\u0001\u0010¯\u0001\"\u0006\b°\u0001\u0010±\u0001\":\u0010³\u0001\u001a\u0004\u0018\u000100*\u00030´\u00012\b\u0010\u000e\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b¸\u0001\u0010\u0015\u001a\u0006\b³\u0001\u0010µ\u0001\"\u0006\b¶\u0001\u0010·\u0001¨\u0006¹\u0001"}, d2 = {"dispatchReceiverClassTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "dispatchReceiverClassLookupTagOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "containingClassLookupTag", "containingClassForLocal", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDanglingModifierSymbol;", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "getContainingClassLookupTag", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "<set-?>", "containingClassForStaticMemberAttr", "getContainingClassForStaticMemberAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "setContainingClassForStaticMemberAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)V", "containingClassForStaticMemberAttr$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "containingClassForLocalAttr", "getContainingClassForLocalAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "setContainingClassForLocalAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)V", "containingClassForLocalAttr$delegate", "containingClassAttr", "getContainingClassAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "setContainingClassAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)V", "containingClassAttr$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "containingScriptSymbolAttr", "getContainingScriptSymbolAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "setContainingScriptSymbolAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;)V", "containingScriptSymbolAttr$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "containingReplSymbolAttr", "getContainingReplSymbolAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "setContainingReplSymbolAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;)V", "containingReplSymbolAttr$delegate", Argument.Delimiters.none, "hasNoEnumEntriesAttr", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getHasNoEnumEntriesAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Ljava/lang/Boolean;", "setHasNoEnumEntriesAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Ljava/lang/Boolean;)V", "hasNoEnumEntriesAttr$delegate", "hasEnumEntries", "getHasEnumEntries", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Z", "isNewPlaceForBodyGeneration", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/Boolean;", "setNewPlaceForBodyGeneration", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Ljava/lang/Boolean;)V", "isNewPlaceForBodyGeneration$delegate", "isIntersectionOverride", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "isSubstitutionOverride", "isSubstitutionOrIntersectionOverride", "isDelegated", "isCopyCreatedInScope", "canHaveDeferredReturnTypeCalculation", "getCanHaveDeferredReturnTypeCalculation", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "originalForSubstitutionOverride", "D", "getOriginalForSubstitutionOverride", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "S", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "baseForIntersectionOverride", "getBaseForIntersectionOverride", "originalIfFakeOverride", "originalIfFakeOverrideOrDelegated", "originalOrSelf", "unwrapFakeOverrides", "unwrapFakeOverridesAccountingForExplicitBackingFields", "unwrapFakeOverridesOrDelegated", "unwrapSubstitutionOverrides", "unwrapUseSiteSubstitutionOverrides", "originalForSubstitutionOverrideAttr", "getOriginalForSubstitutionOverrideAttr", "setOriginalForSubstitutionOverrideAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "originalForSubstitutionOverrideAttr$delegate", "originalForIntersectionOverrideAttr", "getOriginalForIntersectionOverrideAttr", "setOriginalForIntersectionOverrideAttr", "originalForIntersectionOverrideAttr$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "initialSignatureAttr", "getInitialSignatureAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "setInitialSignatureAttr", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "initialSignatureAttr$delegate", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "matchingParameterFunctionType", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getMatchingParameterFunctionType", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setMatchingParameterFunctionType", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "matchingParameterFunctionType$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "correspondingProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getCorrespondingProperty", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "setCorrespondingProperty", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "correspondingProperty$delegate", "propertyIfAccessor", "getPropertyIfAccessor", "propertyIfBackingField", "getPropertyIfBackingField", "isJavaRecord", "setJavaRecord", "isJavaRecord$delegate", "isJavaRecordComponent", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Ljava/lang/Boolean;", "setJavaRecordComponent", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Ljava/lang/Boolean;)V", "isJavaRecordComponent$delegate", "isJavaNonAbstractSealed", "setJavaNonAbstractSealed", "isJavaNonAbstractSealed$delegate", "isCatchParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "setCatchParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Ljava/lang/Boolean;)V", "isCatchParameter$delegate", "isForLoopParameter", "setForLoopParameter", "isForLoopParameter$delegate", "Lorg/jetbrains/kotlin/fir/DelegatedWrapperData;", "delegatedWrapperData", "getDelegatedWrapperData", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/DelegatedWrapperData;", "setDelegatedWrapperData", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/DelegatedWrapperData;)V", "delegatedWrapperData$delegate", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/DelegatedWrapperData;", "Lorg/jetbrains/kotlin/name/Name;", "generatedContextParameterName", "getGeneratedContextParameterName", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/name/Name;", "setGeneratedContextParameterName", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/name/Name;)V", "generatedContextParameterName$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;)Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/name/FqName;", "localClassJvmType", "getLocalClassJvmType", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/name/FqName;", "setLocalClassJvmType", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/name/FqName;)V", "localClassJvmType$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/CompanionBlockInfo;", "companionBlocks", "getCompanionBlocks", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/fir/CompanionBlockInfo;", "setCompanionBlocks", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/CompanionBlockInfo;)V", "companionBlocks$delegate", "isIllegalCompanionBlockMember", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "setIllegalCompanionBlockMember", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/lang/Boolean;)V", "isIllegalCompanionBlockMember$delegate", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassMembersKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(ClassMembersKt.class, "containingClassForStaticMemberAttr", "getContainingClassForStaticMemberAttr(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "containingClassForLocalAttr", "getContainingClassForLocalAttr(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "containingClassAttr", "getContainingClassAttr(Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "containingScriptSymbolAttr", "getContainingScriptSymbolAttr(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "containingReplSymbolAttr", "getContainingReplSymbolAttr(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "hasNoEnumEntriesAttr", "getHasNoEnumEntriesAttr(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isNewPlaceForBodyGeneration", "isNewPlaceForBodyGeneration(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "originalForSubstitutionOverrideAttr", "getOriginalForSubstitutionOverrideAttr(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "originalForIntersectionOverrideAttr", "getOriginalForIntersectionOverrideAttr(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "initialSignatureAttr", "getInitialSignatureAttr(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "matchingParameterFunctionType", "getMatchingParameterFunctionType(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "correspondingProperty", "getCorrespondingProperty(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isJavaRecord", "isJavaRecord(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isJavaRecordComponent", "isJavaRecordComponent(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isJavaNonAbstractSealed", "isJavaNonAbstractSealed(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isCatchParameter", "isCatchParameter(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isForLoopParameter", "isForLoopParameter(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "delegatedWrapperData", "getDelegatedWrapperData(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/DelegatedWrapperData;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "generatedContextParameterName", "getGeneratedContextParameterName(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Lorg/jetbrains/kotlin/name/Name;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "localClassJvmType", "getLocalClassJvmType(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/name/FqName;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "companionBlocks", "getCompanionBlocks(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/fir/CompanionBlockInfo;", 1), new MutablePropertyReference1Impl<>(ClassMembersKt.class, "isIllegalCompanionBlockMember", "isIllegalCompanionBlockMember(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor companionBlocks$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor containingClassAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor containingClassForLocalAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor containingClassForStaticMemberAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor containingReplSymbolAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor containingScriptSymbolAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor correspondingProperty$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor delegatedWrapperData$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor generatedContextParameterName$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor hasNoEnumEntriesAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor initialSignatureAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isCatchParameter$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isForLoopParameter$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isIllegalCompanionBlockMember$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isJavaNonAbstractSealed$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isJavaRecord$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isJavaRecordComponent$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isNewPlaceForBodyGeneration$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor localClassJvmType$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor matchingParameterFunctionType$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor originalForIntersectionOverrideAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor originalForSubstitutionOverrideAttr$delegate;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        ContainingClassKey containingClassKey = ContainingClassKey.INSTANCE;
        containingClassForStaticMemberAttr$delegate = firDeclarationDataRegistry.data(containingClassKey);
        containingClassForLocalAttr$delegate = firDeclarationDataRegistry.data(containingClassKey);
        containingClassAttr$delegate = firDeclarationDataRegistry.data(containingClassKey);
        containingScriptSymbolAttr$delegate = firDeclarationDataRegistry.data(ContainingScriptKey.INSTANCE);
        containingReplSymbolAttr$delegate = firDeclarationDataRegistry.data(ContainingReplKey.INSTANCE);
        hasNoEnumEntriesAttr$delegate = firDeclarationDataRegistry.data(HasNoEnumEntriesKey.INSTANCE);
        isNewPlaceForBodyGeneration$delegate = firDeclarationDataRegistry.data(IsNewPlaceForBodyGeneration.INSTANCE);
        originalForSubstitutionOverrideAttr$delegate = firDeclarationDataRegistry.data(SubstitutedOverrideOriginalKey.INSTANCE);
        originalForIntersectionOverrideAttr$delegate = firDeclarationDataRegistry.data(IntersectionOverrideOriginalKey.INSTANCE);
        initialSignatureAttr$delegate = firDeclarationDataRegistry.data(InitialSignatureKey.INSTANCE);
        matchingParameterFunctionType$delegate = firDeclarationDataRegistry.data(MatchingParameterFunctionTypeKey.INSTANCE);
        correspondingProperty$delegate = firDeclarationDataRegistry.data(CorrespondingProperty.INSTANCE);
        isJavaRecord$delegate = firDeclarationDataRegistry.data(IsJavaRecordKey.INSTANCE);
        isJavaRecordComponent$delegate = firDeclarationDataRegistry.data(IsJavaRecordComponentKey.INSTANCE);
        isJavaNonAbstractSealed$delegate = firDeclarationDataRegistry.data(IsJavaNonAbstractSealed.INSTANCE);
        isCatchParameter$delegate = firDeclarationDataRegistry.data(IsCatchParameterProperty.INSTANCE);
        isForLoopParameter$delegate = firDeclarationDataRegistry.data(IsForLoopParameterProperty.INSTANCE);
        delegatedWrapperData$delegate = firDeclarationDataRegistry.data(DelegatedWrapperDataKey.INSTANCE);
        generatedContextParameterName$delegate = firDeclarationDataRegistry.data(UnnamedContextParameterNameKey.INSTANCE);
        localClassJvmType$delegate = firDeclarationDataRegistry.data(LocalClassJvmTypeKey.INSTANCE);
        companionBlocks$delegate = firDeclarationDataRegistry.data(FirCompanionBlockDataKey.INSTANCE);
        isIllegalCompanionBlockMember$delegate = firDeclarationDataRegistry.data(FirIllegalCompanionBlockMemberKey.INSTANCE);
    }

    public static final ConeClassLikeLookupTag containingClass(FirDanglingModifierList firDanglingModifierList) {
        firDanglingModifierList.getClass();
        return getContainingClassAttr(firDanglingModifierList);
    }

    public static final ConeClassLikeLookupTag containingClassForLocal(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        if (firClassLikeDeclaration.getIsLocal()) {
            return getContainingClassForLocalAttr(firClassLikeDeclaration);
        }
        return null;
    }

    public static final ConeClassLikeLookupTag containingClassLookupTag(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        ConeClassLikeLookupTag containingClassForStaticMemberAttr = getContainingClassForStaticMemberAttr(firCallableDeclaration);
        return containingClassForStaticMemberAttr == null ? dispatchReceiverClassLookupTagOrNull(firCallableDeclaration) : containingClassForStaticMemberAttr;
    }

    public static final ConeClassLikeLookupTag dispatchReceiverClassLookupTagOrNull(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        ConeClassLikeType coneClassLikeTypeDispatchReceiverClassTypeOrNull = dispatchReceiverClassTypeOrNull(firCallableDeclaration);
        if (coneClassLikeTypeDispatchReceiverClassTypeOrNull != null) {
            return coneClassLikeTypeDispatchReceiverClassTypeOrNull.getLookupTag();
        }
        return null;
    }

    public static final ConeClassLikeType dispatchReceiverClassTypeOrNull(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        if ((firCallableDeclaration.getDispatchReceiverType() instanceof ConeIntersectionType) && isIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForIntersectionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            originalForIntersectionOverrideAttr.getClass();
            return dispatchReceiverClassTypeOrNull(originalForIntersectionOverrideAttr);
        }
        ConeSimpleKotlinType dispatchReceiverType = firCallableDeclaration.getDispatchReceiverType();
        if (dispatchReceiverType instanceof ConeClassLikeType) {
            return (ConeClassLikeType) dispatchReceiverType;
        }
        return null;
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S getBaseForIntersectionOverride(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        FirCallableDeclaration originalForIntersectionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
        FirCallableSymbol<FirCallableDeclaration> symbol = originalForIntersectionOverrideAttr != null ? originalForIntersectionOverrideAttr.getSymbol() : null;
        Intrinsics.reifiedOperationMarker(1, "S?");
        return symbol;
    }

    public static final boolean getCanHaveDeferredReturnTypeCalculation(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return isCopyCreatedInScope(firCallableDeclaration) || Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE) || Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.Synthetic.JavaProperty.INSTANCE);
    }

    public static final CompanionBlockInfo getCompanionBlocks(FirClass firClass) {
        firClass.getClass();
        return (CompanionBlockInfo) companionBlocks$delegate.getValue(firClass, $$delegatedProperties[20]);
    }

    public static final ConeClassLikeLookupTag getContainingClassAttr(FirDanglingModifierList firDanglingModifierList) {
        firDanglingModifierList.getClass();
        return (ConeClassLikeLookupTag) containingClassAttr$delegate.getValue(firDanglingModifierList, $$delegatedProperties[2]);
    }

    public static final ConeClassLikeLookupTag getContainingClassForLocalAttr(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        return (ConeClassLikeLookupTag) containingClassForLocalAttr$delegate.getValue(firClassLikeDeclaration, $$delegatedProperties[1]);
    }

    public static final ConeClassLikeLookupTag getContainingClassForStaticMemberAttr(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (ConeClassLikeLookupTag) containingClassForStaticMemberAttr$delegate.getValue(firCallableDeclaration, $$delegatedProperties[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeClassLikeLookupTag getContainingClassLookupTag(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        if (((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal()) {
            return containingClassForLocal((FirClassLikeDeclaration) firClassLikeSymbol.getFir());
        }
        ClassId outerClassId = firClassLikeSymbol.getClassId().getOuterClassId();
        if (outerClassId != null) {
            return TypeConstructionUtilsKt.toLookupTag(outerClassId);
        }
        return null;
    }

    public static final FirReplSnippetSymbol getContainingReplSymbolAttr(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        return (FirReplSnippetSymbol) containingReplSymbolAttr$delegate.getValue(firClassLikeDeclaration, $$delegatedProperties[4]);
    }

    public static final FirScriptSymbol getContainingScriptSymbolAttr(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        return (FirScriptSymbol) containingScriptSymbolAttr$delegate.getValue(firClassLikeDeclaration, $$delegatedProperties[3]);
    }

    public static final FirProperty getCorrespondingProperty(FirValueParameter firValueParameter) {
        firValueParameter.getClass();
        return (FirProperty) correspondingProperty$delegate.getValue(firValueParameter, $$delegatedProperties[11]);
    }

    public static final <D extends FirCallableDeclaration> DelegatedWrapperData<D> getDelegatedWrapperData(D d) {
        d.getClass();
        return (DelegatedWrapperData) delegatedWrapperData$delegate.getValue(d, $$delegatedProperties[17]);
    }

    public static final Name getGeneratedContextParameterName(FirValueParameter firValueParameter) {
        firValueParameter.getClass();
        return (Name) generatedContextParameterName$delegate.getValue(firValueParameter, $$delegatedProperties[18]);
    }

    public static final boolean getHasEnumEntries(FirClass firClass) {
        firClass.getClass();
        return !Intrinsics.areEqual(getHasNoEnumEntriesAttr(firClass), Boolean.TRUE);
    }

    public static final Boolean getHasNoEnumEntriesAttr(FirClass firClass) {
        firClass.getClass();
        return (Boolean) hasNoEnumEntriesAttr$delegate.getValue(firClass, $$delegatedProperties[5]);
    }

    public static final FirNamedFunctionSymbol getInitialSignatureAttr(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (FirNamedFunctionSymbol) initialSignatureAttr$delegate.getValue(firCallableDeclaration, $$delegatedProperties[9]);
    }

    public static final FqName getLocalClassJvmType(FirClass firClass) {
        firClass.getClass();
        return (FqName) localClassJvmType$delegate.getValue(firClass, $$delegatedProperties[19]);
    }

    public static final <D extends FirAnonymousFunction> ConeKotlinType getMatchingParameterFunctionType(D d) {
        d.getClass();
        return (ConeKotlinType) matchingParameterFunctionType$delegate.getValue(d, $$delegatedProperties[10]);
    }

    public static final <D extends FirCallableDeclaration> D getOriginalForIntersectionOverrideAttr(D d) {
        d.getClass();
        return (D) originalForIntersectionOverrideAttr$delegate.getValue(d, $$delegatedProperties[8]);
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S getOriginalForSubstitutionOverride(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        FirCallableSymbol<FirCallableDeclaration> symbol = originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null;
        Intrinsics.reifiedOperationMarker(1, "S?");
        return symbol;
    }

    public static final <D extends FirCallableDeclaration> D getOriginalForSubstitutionOverrideAttr(D d) {
        d.getClass();
        return (D) originalForSubstitutionOverrideAttr$delegate.getValue(d, $$delegatedProperties[7]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirCallableDeclaration getPropertyIfAccessor(FirCallableDeclaration firCallableDeclaration) {
        FirPropertySymbol propertySymbol;
        FirProperty firProperty;
        firCallableDeclaration.getClass();
        FirPropertyAccessor firPropertyAccessor = firCallableDeclaration instanceof FirPropertyAccessor ? (FirPropertyAccessor) firCallableDeclaration : null;
        return (firPropertyAccessor == null || (propertySymbol = firPropertyAccessor.getPropertySymbol()) == null || (firProperty = (FirProperty) propertySymbol.getFir()) == null) ? firCallableDeclaration : firProperty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirCallableDeclaration getPropertyIfBackingField(FirCallableDeclaration firCallableDeclaration) {
        FirPropertySymbol propertySymbol;
        FirProperty firProperty;
        firCallableDeclaration.getClass();
        FirBackingField firBackingField = firCallableDeclaration instanceof FirBackingField ? (FirBackingField) firCallableDeclaration : null;
        return (firBackingField == null || (propertySymbol = firBackingField.getPropertySymbol()) == null || (firProperty = (FirProperty) propertySymbol.getFir()) == null) ? firCallableDeclaration : firProperty;
    }

    public static final Boolean isCatchParameter(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isCatchParameter$delegate.getValue(firProperty, $$delegatedProperties[15]);
    }

    public static final boolean isCopyCreatedInScope(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return isSubstitutionOrIntersectionOverride(firCallableDeclaration) || isDelegated(firCallableDeclaration);
    }

    public static final boolean isDelegated(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE);
    }

    public static final Boolean isForLoopParameter(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isForLoopParameter$delegate.getValue(firProperty, $$delegatedProperties[16]);
    }

    public static final Boolean isIllegalCompanionBlockMember(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (Boolean) isIllegalCompanionBlockMember$delegate.getValue(firDeclaration, $$delegatedProperties[21]);
    }

    public static final boolean isIntersectionOverride(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.IntersectionOverride.INSTANCE);
    }

    public static final Boolean isJavaNonAbstractSealed(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return (Boolean) isJavaNonAbstractSealed$delegate.getValue(firRegularClass, $$delegatedProperties[14]);
    }

    public static final Boolean isJavaRecord(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return (Boolean) isJavaRecord$delegate.getValue(firRegularClass, $$delegatedProperties[12]);
    }

    public static final Boolean isJavaRecordComponent(FirFunction firFunction) {
        firFunction.getClass();
        return (Boolean) isJavaRecordComponent$delegate.getValue(firFunction, $$delegatedProperties[13]);
    }

    public static final Boolean isNewPlaceForBodyGeneration(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return (Boolean) isNewPlaceForBodyGeneration$delegate.getValue(firRegularClass, $$delegatedProperties[6]);
    }

    public static final boolean isSubstitutionOrIntersectionOverride(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return isSubstitutionOverride(firCallableDeclaration) || isIntersectionOverride(firCallableDeclaration);
    }

    public static final boolean isSubstitutionOverride(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.SubstitutionOverride;
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S originalIfFakeOverride(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        if (originalForSubstitutionOverrideAttr == null) {
            originalForSubstitutionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null;
        Intrinsics.reifiedOperationMarker(1, "S?");
        return symbol;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D originalIfFakeOverrideOrDelegated(D d) {
        d.getClass();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : (D) null;
        if (originalForSubstitutionOverrideAttr == null) {
            originalForSubstitutionOverrideAttr = isIntersectionOverride(d) ? (D) getOriginalForIntersectionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                DelegatedWrapperData delegatedWrapperData = getDelegatedWrapperData(d);
                if (delegatedWrapperData != null) {
                    return (D) delegatedWrapperData.getWrapped();
                }
                return null;
            }
        }
        return (D) originalForSubstitutionOverrideAttr;
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S originalOrSelf(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        while (isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        Intrinsics.reifiedOperationMarker(1, "S");
        return symbol;
    }

    public static final void setCatchParameter(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isCatchParameter$delegate.setValue(firProperty, $$delegatedProperties[15], bool);
    }

    public static final void setCompanionBlocks(FirClass firClass, CompanionBlockInfo companionBlockInfo) {
        firClass.getClass();
        companionBlocks$delegate.setValue(firClass, $$delegatedProperties[20], companionBlockInfo);
    }

    public static final void setContainingClassAttr(FirDanglingModifierList firDanglingModifierList, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        firDanglingModifierList.getClass();
        containingClassAttr$delegate.setValue(firDanglingModifierList, $$delegatedProperties[2], coneClassLikeLookupTag);
    }

    public static final void setContainingClassForLocalAttr(FirClassLikeDeclaration firClassLikeDeclaration, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        firClassLikeDeclaration.getClass();
        containingClassForLocalAttr$delegate.setValue(firClassLikeDeclaration, $$delegatedProperties[1], coneClassLikeLookupTag);
    }

    public static final void setContainingClassForStaticMemberAttr(FirCallableDeclaration firCallableDeclaration, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        firCallableDeclaration.getClass();
        containingClassForStaticMemberAttr$delegate.setValue(firCallableDeclaration, $$delegatedProperties[0], coneClassLikeLookupTag);
    }

    public static final void setContainingReplSymbolAttr(FirClassLikeDeclaration firClassLikeDeclaration, FirReplSnippetSymbol firReplSnippetSymbol) {
        firClassLikeDeclaration.getClass();
        containingReplSymbolAttr$delegate.setValue(firClassLikeDeclaration, $$delegatedProperties[4], firReplSnippetSymbol);
    }

    public static final void setContainingScriptSymbolAttr(FirClassLikeDeclaration firClassLikeDeclaration, FirScriptSymbol firScriptSymbol) {
        firClassLikeDeclaration.getClass();
        containingScriptSymbolAttr$delegate.setValue(firClassLikeDeclaration, $$delegatedProperties[3], firScriptSymbol);
    }

    public static final void setCorrespondingProperty(FirValueParameter firValueParameter, FirProperty firProperty) {
        firValueParameter.getClass();
        correspondingProperty$delegate.setValue(firValueParameter, $$delegatedProperties[11], firProperty);
    }

    public static final <D extends FirCallableDeclaration> void setDelegatedWrapperData(D d, DelegatedWrapperData<D> delegatedWrapperData) {
        d.getClass();
        delegatedWrapperData$delegate.setValue(d, $$delegatedProperties[17], delegatedWrapperData);
    }

    public static final void setForLoopParameter(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isForLoopParameter$delegate.setValue(firProperty, $$delegatedProperties[16], bool);
    }

    public static final void setGeneratedContextParameterName(FirValueParameter firValueParameter, Name name) {
        firValueParameter.getClass();
        generatedContextParameterName$delegate.setValue(firValueParameter, $$delegatedProperties[18], name);
    }

    public static final void setHasNoEnumEntriesAttr(FirClass firClass, Boolean bool) {
        firClass.getClass();
        hasNoEnumEntriesAttr$delegate.setValue(firClass, $$delegatedProperties[5], bool);
    }

    public static final void setIllegalCompanionBlockMember(FirDeclaration firDeclaration, Boolean bool) {
        firDeclaration.getClass();
        isIllegalCompanionBlockMember$delegate.setValue(firDeclaration, $$delegatedProperties[21], bool);
    }

    public static final void setInitialSignatureAttr(FirCallableDeclaration firCallableDeclaration, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firCallableDeclaration.getClass();
        initialSignatureAttr$delegate.setValue(firCallableDeclaration, $$delegatedProperties[9], firNamedFunctionSymbol);
    }

    public static final void setJavaNonAbstractSealed(FirRegularClass firRegularClass, Boolean bool) {
        firRegularClass.getClass();
        isJavaNonAbstractSealed$delegate.setValue(firRegularClass, $$delegatedProperties[14], bool);
    }

    public static final void setJavaRecord(FirRegularClass firRegularClass, Boolean bool) {
        firRegularClass.getClass();
        isJavaRecord$delegate.setValue(firRegularClass, $$delegatedProperties[12], bool);
    }

    public static final void setJavaRecordComponent(FirFunction firFunction, Boolean bool) {
        firFunction.getClass();
        isJavaRecordComponent$delegate.setValue(firFunction, $$delegatedProperties[13], bool);
    }

    public static final void setLocalClassJvmType(FirClass firClass, FqName fqName) {
        firClass.getClass();
        localClassJvmType$delegate.setValue(firClass, $$delegatedProperties[19], fqName);
    }

    public static final <D extends FirAnonymousFunction> void setMatchingParameterFunctionType(D d, ConeKotlinType coneKotlinType) {
        d.getClass();
        matchingParameterFunctionType$delegate.setValue(d, $$delegatedProperties[10], coneKotlinType);
    }

    public static final void setNewPlaceForBodyGeneration(FirRegularClass firRegularClass, Boolean bool) {
        firRegularClass.getClass();
        isNewPlaceForBodyGeneration$delegate.setValue(firRegularClass, $$delegatedProperties[6], bool);
    }

    public static final <D extends FirCallableDeclaration> void setOriginalForIntersectionOverrideAttr(D d, D d2) {
        d.getClass();
        originalForIntersectionOverrideAttr$delegate.setValue(d, $$delegatedProperties[8], d2);
    }

    public static final <D extends FirCallableDeclaration> void setOriginalForSubstitutionOverrideAttr(D d, D d2) {
        d.getClass();
        originalForSubstitutionOverrideAttr$delegate.setValue(d, $$delegatedProperties[7], d2);
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S unwrapFakeOverrides(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
                Intrinsics.reifiedOperationMarker(1, "S");
                return symbol;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S unwrapFakeOverridesAccountingForExplicitBackingFields(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        while (!isIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        Intrinsics.reifiedOperationMarker(1, "S");
        return symbol;
    }

    public static final /* synthetic */ <D extends FirCallableSymbol<?>> D unwrapFakeOverridesOrDelegated(D d) {
        d.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) d.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(firCallableDeclaration) ? getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = getDelegatedWrapperData(firCallableDeclaration);
                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                }
            }
            if (originalForSubstitutionOverrideAttr == null) {
                FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
                Intrinsics.reifiedOperationMarker(1, "D");
                return symbol;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S unwrapSubstitutionOverrides(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
                Intrinsics.reifiedOperationMarker(1, "S");
                return symbol;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
    }

    public static final /* synthetic */ <S extends FirCallableSymbol<?>> S unwrapUseSiteSubstitutionOverrides(S s) {
        s.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) s.getFir();
        while (Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        Intrinsics.reifiedOperationMarker(1, "S");
        return symbol;
    }

    public static final boolean isSubstitutionOverride(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getOrigin() instanceof FirDeclarationOrigin.SubstitutionOverride;
    }

    public static final boolean isDelegated(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE);
    }

    public static final boolean isIntersectionOverride(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.IntersectionOverride.INSTANCE);
    }

    public static final ConeClassLikeLookupTag containingClassLookupTag(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return containingClassLookupTag((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final ConeClassLikeLookupTag containingClassLookupTag(FirDanglingModifierSymbol firDanglingModifierSymbol) {
        firDanglingModifierSymbol.getClass();
        return containingClass(firDanglingModifierSymbol.getFir());
    }

    public static final ConeClassLikeLookupTag dispatchReceiverClassLookupTagOrNull(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return dispatchReceiverClassLookupTagOrNull((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final <D extends FirCallableDeclaration> DelegatedWrapperData<D> getDelegatedWrapperData(FirCallableSymbol<? extends D> firCallableSymbol) {
        firCallableSymbol.getClass();
        return getDelegatedWrapperData(firCallableSymbol.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Name getGeneratedContextParameterName(FirValueParameterSymbol firValueParameterSymbol) {
        firValueParameterSymbol.getClass();
        return getGeneratedContextParameterName((FirValueParameter) firValueParameterSymbol.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FqName getLocalClassJvmType(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return getLocalClassJvmType((FirClass) firClassSymbol.getFir());
    }

    public static final boolean isCopyCreatedInScope(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return isSubstitutionOrIntersectionOverride(firCallableSymbol) || isDelegated(firCallableSymbol);
    }

    public static final boolean isSubstitutionOrIntersectionOverride(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return isSubstitutionOverride(firCallableSymbol) || isIntersectionOverride(firCallableSymbol);
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D getBaseForIntersectionOverride(D d) {
        d.getClass();
        if (isIntersectionOverride(d)) {
            return (D) getOriginalForIntersectionOverrideAttr(d);
        }
        return null;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D getOriginalForSubstitutionOverride(D d) {
        d.getClass();
        if (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
            return (D) getOriginalForSubstitutionOverrideAttr(d);
        }
        return null;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D unwrapSubstitutionOverrides(D d) {
        d.getClass();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                return d;
            }
            d = (D) originalForSubstitutionOverrideAttr;
        }
    }

    public static final ConeClassLikeType dispatchReceiverClassTypeOrNull(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return dispatchReceiverClassTypeOrNull((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D unwrapUseSiteSubstitutionOverrides(D d) {
        d.getClass();
        while (Intrinsics.areEqual(d.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            d = (D) originalForSubstitutionOverrideAttr;
        }
        return d;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D originalIfFakeOverride(D d) {
        d.getClass();
        D d2 = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? (D) getOriginalForSubstitutionOverrideAttr(d) : null;
        if (d2 != null) {
            return d2;
        }
        if (isIntersectionOverride(d)) {
            return (D) getOriginalForIntersectionOverrideAttr(d);
        }
        return null;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D unwrapFakeOverrides(D d) {
        d.getClass();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(d) ? getOriginalForIntersectionOverrideAttr(d) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                return d;
            }
            d = (D) originalForSubstitutionOverrideAttr;
        }
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D originalOrSelf(D d) {
        d.getClass();
        while (isSubstitutionOrIntersectionOverride(d)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(d) ? getOriginalForIntersectionOverrideAttr(d) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            d = (D) originalForSubstitutionOverrideAttr;
        }
        return d;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D unwrapFakeOverridesAccountingForExplicitBackingFields(D d) {
        d.getClass();
        while (!isIntersectionOverride(d)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(d) ? getOriginalForIntersectionOverrideAttr(d) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            d = (D) originalForSubstitutionOverrideAttr;
        }
        return d;
    }

    public static final /* synthetic */ <D extends FirCallableDeclaration> D unwrapFakeOverridesOrDelegated(D d) {
        d.getClass();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (isSubstitutionOverride(d) || (d.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? getOriginalForSubstitutionOverrideAttr(d) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = isIntersectionOverride(d) ? getOriginalForIntersectionOverrideAttr(d) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = getDelegatedWrapperData(d);
                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                }
            }
            if (originalForSubstitutionOverrideAttr == null) {
                return d;
            }
            d = (D) originalForSubstitutionOverrideAttr;
        }
    }
}
