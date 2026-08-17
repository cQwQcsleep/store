package androidx.compose.ui.text.font;

import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\fR\u0016\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/text/font/AndroidFontLoader;", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "loadBlocking", "Landroid/graphics/Typeface;", "font", "Landroidx/compose/ui/text/font/Font;", "awaitLoad", "(Landroidx/compose/ui/text/font/Font;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cacheKey", "", "getCacheKey", "()Ljava/lang/Object;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidFontLoader implements PlatformFontLoader {
    public static final int $stable = 8;
    private final Object cacheKey;
    private final Context context;

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.AndroidFontLoader$awaitLoad$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.text.font.AndroidFontLoader", f = "AndroidFontLoader.android.kt", i = {1}, l = {55, 57}, m = "awaitLoad", n = {"font"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidFontLoader.this.awaitLoad(null, this);
        }
    }

    public AndroidFontLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0065, code lost:
    
        if (r8 == r1) goto L27;
     */
    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object awaitLoad(Font font, Continuation<? super android.graphics.Typeface> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objLoadAsync = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objLoadAsync);
            if (font instanceof AndroidFont) {
                AndroidFont androidFont = (AndroidFont) font;
                AndroidFont.TypefaceLoader typefaceLoader = androidFont.getTypefaceLoader();
                Context context = this.context;
                anonymousClass1.label = 1;
                Object objAwaitLoad = typefaceLoader.awaitLoad(context, androidFont, anonymousClass1);
                if (objAwaitLoad != coroutine_suspended) {
                    return objAwaitLoad;
                }
            } else {
                if (!(font instanceof ResourceFont)) {
                    aca.a("Unknown font type: ", font);
                    return null;
                }
                Context context2 = this.context;
                anonymousClass1.L$0 = font;
                anonymousClass1.label = 2;
                objLoadAsync = AndroidFontLoader_androidKt.loadAsync((ResourceFont) font, context2, anonymousClass1);
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            ResultKt.throwOnFailure(objLoadAsync);
            return objLoadAsync;
        }
        if (i2 != 2) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        font = (Font) anonymousClass1.L$0;
        ResultKt.throwOnFailure(objLoadAsync);
        return PlatformTypefaces_androidKt.setFontVariationSettings((android.graphics.Typeface) objLoadAsync, ((ResourceFont) font).getVariationSettings(), this.context);
    }

    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public Object getCacheKey() {
        return this.cacheKey;
    }

    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public android.graphics.Typeface loadBlocking(Font font) {
        Object obj;
        android.graphics.Typeface typefaceLoad;
        if (font instanceof AndroidFont) {
            AndroidFont androidFont = (AndroidFont) font;
            return androidFont.getTypefaceLoader().loadBlocking(this.context, androidFont);
        }
        if (font instanceof ResourceFont) {
            ResourceFont resourceFont = (ResourceFont) font;
            int loadingStrategy = resourceFont.getLoadingStrategy();
            FontLoadingStrategy.Companion companion = FontLoadingStrategy.INSTANCE;
            if (FontLoadingStrategy.m5578equalsimpl0(loadingStrategy, companion.m5583getBlockingPKNRLFQ())) {
                typefaceLoad = AndroidFontLoader_androidKt.load(resourceFont, this.context);
            } else if (FontLoadingStrategy.m5578equalsimpl0(loadingStrategy, companion.m5584getOptionalLocalPKNRLFQ())) {
                try {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(AndroidFontLoader_androidKt.load((ResourceFont) font, this.context));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                typefaceLoad = (android.graphics.Typeface) (Result.isFailure-impl(obj) ? null : obj);
            } else {
                if (FontLoadingStrategy.m5578equalsimpl0(loadingStrategy, companion.m5582getAsyncPKNRLFQ())) {
                    c41.a("Unsupported Async font load path");
                    return null;
                }
                z01.a("Unknown loading type ", FontLoadingStrategy.m5580toStringimpl(resourceFont.getLoadingStrategy()));
            }
            return PlatformTypefaces_androidKt.setFontVariationSettings(typefaceLoad, resourceFont.getVariationSettings(), this.context);
        }
        return null;
    }
}
