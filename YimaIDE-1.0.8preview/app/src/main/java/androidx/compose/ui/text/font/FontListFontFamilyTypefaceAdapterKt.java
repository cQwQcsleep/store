package androidx.compose.ui.text.font;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00040\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\fH\u0002¨\u0006\r"}, d2 = {"firstImmediatelyAvailable", "Lkotlin/Pair;", "", "Landroidx/compose/ui/text/font/Font;", "", "typefaceRequest", "Landroidx/compose/ui/text/font/TypefaceRequest;", "asyncTypefaceCache", "Landroidx/compose/ui/text/font/AsyncTypefaceCache;", "platformFontLoader", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "createDefaultTypeface", "Lkotlin/Function1;", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FontListFontFamilyTypefaceAdapterKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<List<Font>, Object> firstImmediatelyAvailable(List<? extends Font> list, TypefaceRequest typefaceRequest, AsyncTypefaceCache asyncTypefaceCache, PlatformFontLoader platformFontLoader, Function1<? super TypefaceRequest, ? extends Object> function1) {
        Object objInvoke;
        Object objInvoke2;
        Object obj;
        Object objM5553unboximpl;
        int size = list.size();
        List listMutableListOf = null;
        for (int i = 0; i < size; i++) {
            Font font = list.get(i);
            int loadingStrategy = font.getLoadingStrategy();
            FontLoadingStrategy.Companion companion = FontLoadingStrategy.INSTANCE;
            if (FontLoadingStrategy.m5578equalsimpl0(loadingStrategy, companion.m5583getBlockingPKNRLFQ())) {
                synchronized (asyncTypefaceCache.cacheLock) {
                    try {
                        AsyncTypefaceCache.Key key = new AsyncTypefaceCache.Key(font, platformFontLoader.getCacheKey());
                        AsyncTypefaceCache.AsyncTypefaceResult asyncTypefaceResult = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.resultCache.get(key);
                        if (asyncTypefaceResult == null) {
                            asyncTypefaceResult = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.permanentCache.get(key);
                        }
                        if (asyncTypefaceResult != null) {
                            objInvoke2 = asyncTypefaceResult.m5553unboximpl();
                        } else {
                            Unit unit = Unit.INSTANCE;
                            try {
                                objInvoke = platformFontLoader.loadBlocking(font);
                            } catch (Exception unused) {
                                objInvoke = function1.invoke(typefaceRequest);
                            }
                            Object obj2 = objInvoke;
                            AsyncTypefaceCache.put$default(asyncTypefaceCache, font, platformFontLoader, obj2, false, 8, null);
                            objInvoke2 = obj2;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (objInvoke2 == null) {
                    objInvoke2 = function1.invoke(typefaceRequest);
                }
                return TuplesKt.to(listMutableListOf, FontSynthesis_androidKt.m5613synthesizeTypefaceFxwP2eA(typefaceRequest.m5638getFontSynthesisGVVA2EU(), objInvoke2, font, typefaceRequest.getFontWeight(), typefaceRequest.m5637getFontStyle_LCdwA()));
            }
            if (FontLoadingStrategy.m5578equalsimpl0(loadingStrategy, companion.m5584getOptionalLocalPKNRLFQ())) {
                synchronized (asyncTypefaceCache.cacheLock) {
                    try {
                        AsyncTypefaceCache.Key key2 = new AsyncTypefaceCache.Key(font, platformFontLoader.getCacheKey());
                        AsyncTypefaceCache.AsyncTypefaceResult asyncTypefaceResult2 = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.resultCache.get(key2);
                        if (asyncTypefaceResult2 == null) {
                            asyncTypefaceResult2 = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.permanentCache.get(key2);
                        }
                        if (asyncTypefaceResult2 != null) {
                            objM5553unboximpl = asyncTypefaceResult2.m5553unboximpl();
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                            try {
                                Result.Companion companion2 = Result.Companion;
                                obj = Result.constructor-impl(platformFontLoader.loadBlocking(font));
                            } catch (Throwable th2) {
                                Result.Companion companion3 = Result.Companion;
                                obj = Result.constructor-impl(ResultKt.createFailure(th2));
                            }
                            Object obj3 = Result.isFailure-impl(obj) ? null : obj;
                            AsyncTypefaceCache.put$default(asyncTypefaceCache, font, platformFontLoader, obj3, false, 8, null);
                            objM5553unboximpl = obj3;
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                }
                if (objM5553unboximpl != null) {
                    return TuplesKt.to(listMutableListOf, FontSynthesis_androidKt.m5613synthesizeTypefaceFxwP2eA(typefaceRequest.m5638getFontSynthesisGVVA2EU(), objM5553unboximpl, font, typefaceRequest.getFontWeight(), typefaceRequest.m5637getFontStyle_LCdwA()));
                }
            } else {
                if (!FontLoadingStrategy.m5578equalsimpl0(loadingStrategy, companion.m5582getAsyncPKNRLFQ())) {
                    qu7.a("Unknown font type ", font);
                    return null;
                }
                AsyncTypefaceCache.AsyncTypefaceResult asyncTypefaceResultM5545get1ASDuI8 = asyncTypefaceCache.m5545get1ASDuI8(font, platformFontLoader);
                if (asyncTypefaceResultM5545get1ASDuI8 != null) {
                    if (!AsyncTypefaceCache.AsyncTypefaceResult.m5551isPermanentFailureimpl(asyncTypefaceResultM5545get1ASDuI8.m5553unboximpl()) && asyncTypefaceResultM5545get1ASDuI8.m5553unboximpl() != null) {
                        return TuplesKt.to(listMutableListOf, FontSynthesis_androidKt.m5613synthesizeTypefaceFxwP2eA(typefaceRequest.m5638getFontSynthesisGVVA2EU(), asyncTypefaceResultM5545get1ASDuI8.m5553unboximpl(), font, typefaceRequest.getFontWeight(), typefaceRequest.m5637getFontStyle_LCdwA()));
                    }
                } else if (listMutableListOf == null) {
                    listMutableListOf = CollectionsKt.mutableListOf(new Font[]{font});
                } else {
                    listMutableListOf.add(font);
                }
            }
        }
        return TuplesKt.to(listMutableListOf, function1.invoke(typefaceRequest));
    }
}
