package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationConstants;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.ArrayFactory;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Function;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ContainerUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static final class Options {
        public static boolean RETURN_REALLY_UNMODIFIABLE_COLLECTION_FROM_METHODS_MARKED_UNMODIFIABLE;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 12:
            case 16:
            case 19:
            case 20:
            case 22:
            case 24:
            case 25:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
            case 35:
            case 37:
            case 40:
            case 44:
            case 49:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 61:
            case 65:
            case 66:
            case 69:
            case 77:
            case 79:
            case 90:
            case 93:
            case 99:
            case 122:
            case 125:
            case 128:
            case 131:
            case 133:
            case 141:
            case 145:
            case 149:
            case 153:
            case 164:
            case 167:
            case 171:
            case 174:
            case 177:
            case 180:
            case 181:
            case 184:
            case 187:
            case 194:
            case 197:
            case 200:
            case 203:
            case 212:
            case 215:
            case 228:
            case 230:
            case 233:
            case 236:
            case 239:
            case 240:
            case 241:
            case 245:
            case 246:
            case 247:
            case 250:
            case 252:
            case 256:
            case 261:
            case 264:
            case 265:
            case 266:
            case 268:
            case 277:
            case 280:
            case 284:
            case 291:
            case 294:
            case 296:
            case 301:
            case 304:
            case 307:
            case 310:
            case 314:
            case 315:
            case 318:
            case 321:
            case 323:
            case 326:
            case 330:
            case 331:
            case 332:
            case 333:
            case 335:
            case 341:
            case 342:
            case 345:
            case 363:
            case 373:
            case 375:
            case 378:
            case 382:
            case 396:
            case 397:
            case 399:
            case 400:
            case 401:
            case 402:
            case 404:
            case 405:
            case 406:
            case 407:
            case 414:
            case 415:
            case 418:
            case 419:
            case 422:
            case 423:
            case 426:
            case 427:
            case 430:
            case 433:
            case 436:
            case 438:
            case 439:
            case 440:
            case 443:
            case 445:
            case 446:
            case 447:
            case 448:
            case 449:
            case 457:
            case 458:
            case 459:
            case 460:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 12:
            case 16:
            case 19:
            case 20:
            case 22:
            case 24:
            case 25:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
            case 35:
            case 37:
            case 40:
            case 44:
            case 49:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 61:
            case 65:
            case 66:
            case 69:
            case 77:
            case 79:
            case 90:
            case 93:
            case 99:
            case 122:
            case 125:
            case 128:
            case 131:
            case 133:
            case 141:
            case 145:
            case 149:
            case 153:
            case 164:
            case 167:
            case 171:
            case 174:
            case 177:
            case 180:
            case 181:
            case 184:
            case 187:
            case 194:
            case 197:
            case 200:
            case 203:
            case 212:
            case 215:
            case 228:
            case 230:
            case 233:
            case 236:
            case 239:
            case 240:
            case 241:
            case 245:
            case 246:
            case 247:
            case 250:
            case 252:
            case 256:
            case 261:
            case 264:
            case 265:
            case 266:
            case 268:
            case 277:
            case 280:
            case 284:
            case 291:
            case 294:
            case 296:
            case 301:
            case 304:
            case 307:
            case 310:
            case 314:
            case 315:
            case 318:
            case 321:
            case 323:
            case 326:
            case 330:
            case 331:
            case 332:
            case 333:
            case 335:
            case 341:
            case 342:
            case 345:
            case 363:
            case 373:
            case 375:
            case 378:
            case 382:
            case 396:
            case 397:
            case 399:
            case 400:
            case 401:
            case 402:
            case 404:
            case 405:
            case 406:
            case 407:
            case 414:
            case 415:
            case 418:
            case 419:
            case 422:
            case 423:
            case 426:
            case 427:
            case 430:
            case 433:
            case 436:
            case 438:
            case 439:
            case 440:
            case 443:
            case 445:
            case 446:
            case 447:
            case 448:
            case 449:
            case 457:
            case 458:
            case 459:
            case 460:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 12:
            case 16:
            case 19:
            case 20:
            case 22:
            case 24:
            case 25:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
            case 35:
            case 37:
            case 40:
            case 44:
            case 49:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 61:
            case 65:
            case 66:
            case 69:
            case 77:
            case 79:
            case 90:
            case 93:
            case 99:
            case 122:
            case 125:
            case 128:
            case 131:
            case 133:
            case 141:
            case 145:
            case 149:
            case 153:
            case 164:
            case 167:
            case 171:
            case 174:
            case 177:
            case 180:
            case 181:
            case 184:
            case 187:
            case 194:
            case 197:
            case 200:
            case 203:
            case 212:
            case 215:
            case 228:
            case 230:
            case 233:
            case 236:
            case 239:
            case 240:
            case 241:
            case 245:
            case 246:
            case 247:
            case 250:
            case 252:
            case 256:
            case 261:
            case 264:
            case 265:
            case 266:
            case 268:
            case 277:
            case 280:
            case 284:
            case 291:
            case 294:
            case 296:
            case 301:
            case 304:
            case 307:
            case 310:
            case 314:
            case 315:
            case 318:
            case 321:
            case 323:
            case 326:
            case 330:
            case 331:
            case 332:
            case 333:
            case 335:
            case 341:
            case 342:
            case 345:
            case 363:
            case 373:
            case 375:
            case 378:
            case 382:
            case 396:
            case 397:
            case 399:
            case 400:
            case 401:
            case 402:
            case 404:
            case 405:
            case 406:
            case 407:
            case 414:
            case 415:
            case 418:
            case 419:
            case 422:
            case 423:
            case 426:
            case 427:
            case 430:
            case 433:
            case 436:
            case 438:
            case 439:
            case 440:
            case 443:
            case 445:
            case 446:
            case 447:
            case 448:
            case 449:
            case 457:
            case 458:
            case 459:
            case 460:
                objArr[0] = "com/intellij/util/containers/ContainerUtil";
                break;
            case 2:
            case 8:
                objArr[0] = "first";
                break;
            case 3:
            case 9:
                objArr[0] = "entries";
                break;
            case 5:
            case 88:
                objArr[0] = "keys";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 91:
            case 95:
            case 232:
            case 235:
                objArr[0] = "values";
                break;
            case 11:
            case 17:
            case 29:
            case 36:
            case 39:
            case 41:
            case 50:
            case 62:
            case 85:
            case 87:
            case 211:
            case 214:
            case 217:
            case 322:
            default:
                objArr[0] = "elements";
                break;
            case 13:
            case 54:
            case 101:
            case 135:
            case 136:
            case 140:
            case 170:
            case 189:
            case 193:
            case 225:
            case 227:
            case 298:
            case 309:
            case 312:
            case 325:
            case 353:
            case 413:
            case 429:
                objArr[0] = "array";
                break;
            case 14:
            case 15:
            case 30:
            case 32:
            case 38:
            case 102:
            case 107:
            case 110:
            case 112:
            case 220:
            case 299:
            case 316:
            case 349:
            case 350:
            case 354:
            case 357:
            case 358:
            case 360:
            case 376:
            case 441:
            case 442:
                objArr[0] = "iterable";
                break;
            case 18:
                objArr[0] = "originalList";
                break;
            case 21:
            case 23:
            case 26:
                objArr[0] = "original";
                break;
            case 33:
            case 83:
            case 97:
            case 108:
            case 114:
            case 116:
            case 202:
            case 204:
            case 206:
            case 222:
            case 271:
            case 273:
            case 302:
                objArr[0] = "iterator";
                break;
            case 42:
            case 94:
            case 162:
            case 395:
                objArr[0] = "map";
                break;
            case 43:
            case PartialGapBuffer.SURROUNDING_SIZE /* 64 */:
            case 68:
                objArr[0] = "map2";
                break;
            case 45:
            case 47:
                objArr[0] = "set";
                break;
            case 46:
            case 48:
                objArr[0] = "set2";
                break;
            case 58:
            case 78:
            case 104:
            case 118:
            case 196:
            case 201:
            case 229:
            case 231:
            case 234:
            case 254:
            case 285:
            case 286:
            case 289:
            case 292:
            case 295:
            case 364:
            case 371:
            case 383:
            case 384:
            case 387:
            case 390:
            case 391:
            case 393:
            case 437:
            case 450:
            case 461:
            case 463:
                objArr[0] = "list";
                break;
            case 59:
            case 80:
            case 82:
            case 84:
            case 86:
            case 121:
            case 123:
            case 126:
            case 130:
            case 132:
            case 142:
            case 146:
            case 151:
            case 155:
            case 156:
            case 159:
            case 160:
            case 165:
            case 168:
            case 173:
            case 175:
            case 179:
            case 182:
            case 186:
            case 190:
            case 192:
            case 208:
            case 210:
            case 213:
            case 216:
            case 218:
            case 305:
            case 319:
            case 410:
            case 416:
            case 420:
            case 424:
            case 428:
            case 434:
                objArr[0] = "collection";
                break;
            case 60:
                objArr[0] = "grouper";
                break;
            case 63:
            case 67:
                objArr[0] = "map1";
                break;
            case 70:
            case 74:
            case 237:
            case 385:
                objArr[0] = "list1";
                break;
            case 71:
            case 75:
            case 238:
            case 386:
                objArr[0] = "list2";
                break;
            case 72:
            case 76:
            case 287:
            case 290:
            case 293:
            case 297:
            case 365:
            case 367:
            case 369:
            case 372:
            case 456:
                objArr[0] = "comparator";
                break;
            case 73:
            case 103:
            case 105:
            case 106:
            case 109:
                objArr[0] = "processor";
                break;
            case 81:
                objArr[0] = "appendix";
                break;
            case 89:
                objArr[0] = "valueConvertor";
                break;
            case 92:
            case 96:
            case 98:
                objArr[0] = "keyConvertor";
                break;
            case 100:
            case 111:
            case 117:
            case 119:
            case 154:
            case 161:
            case 166:
            case 178:
            case 185:
            case 188:
            case 191:
            case 219:
            case 348:
            case 351:
            case 352:
            case 355:
            case 356:
            case 359:
            case 361:
            case 388:
            case 389:
            case 392:
                objArr[0] = "condition";
                break;
            case 113:
            case 115:
                objArr[0] = "equalTo";
                break;
            case 120:
            case 124:
            case 127:
            case 129:
            case 134:
            case 137:
            case 139:
            case 144:
            case 147:
            case 150:
            case 272:
            case 411:
            case 412:
            case 417:
            case 421:
            case 425:
                objArr[0] = "mapper";
                break;
            case 138:
            case 143:
            case 157:
            case 158:
            case 221:
            case 223:
            case 224:
                objArr[0] = "aClass";
                break;
            case 148:
            case 152:
            case 381:
                objArr[0] = "to";
                break;
            case 163:
                objArr[0] = "keyFilter";
                break;
            case 169:
            case 172:
            case 176:
            case 183:
            case 205:
                objArr[0] = "instanceOf";
                break;
            case 195:
            case 209:
            case 444:
                objArr[0] = "enumeration";
                break;
            case 198:
                objArr[0] = "iterable1";
                break;
            case 199:
                objArr[0] = "iterable2";
                break;
            case 207:
                objArr[0] = "predicate";
                break;
            case 226:
            case PartialGapBuffer.BUF_SIZE /* 255 */:
                objArr[0] = "listGenerator";
                break;
            case 242:
                objArr[0] = "it1";
                break;
            case 243:
                objArr[0] = "it2";
                break;
            case 244:
                objArr[0] = "iterables";
                break;
            case 248:
            case 249:
                objArr[0] = "iterators";
                break;
            case 251:
            case 253:
                objArr[0] = "lists";
                break;
            case 257:
            case 259:
            case 262:
                objArr[0] = "collection1";
                break;
            case 258:
            case 260:
            case 263:
                objArr[0] = "collection2";
                break;
            case 267:
            case 269:
            case 270:
            case 334:
            case 362:
                objArr[0] = "items";
                break;
            case 274:
                objArr[0] = "filter";
                break;
            case 275:
            case 380:
                objArr[0] = "from";
                break;
            case 276:
                objArr[0] = "what";
                break;
            case 278:
            case 398:
            case 408:
            case 431:
                objArr[0] = "c";
                break;
            case 279:
            case 283:
            case 347:
            case 435:
                objArr[0] = "factory";
                break;
            case 281:
                objArr[0] = "c1";
                break;
            case 282:
                objArr[0] = "c2";
                break;
            case 288:
                objArr[0] = "a";
                break;
            case AnimationConstants.DefaultDurationMillis /* 300 */:
            case 303:
            case 306:
            case 308:
            case 311:
            case 317:
            case 320:
            case 324:
            case 327:
            case 377:
                objArr[0] = "mapping";
                break;
            case 313:
            case 329:
                objArr[0] = "emptyArray";
                break;
            case 328:
                objArr[0] = "arr";
                break;
            case 336:
            case 337:
            case 338:
            case 339:
            case 343:
            case 346:
            case 409:
                objArr[0] = "result";
                break;
            case 340:
                objArr[0] = "parentDisposable";
                break;
            case 344:
                objArr[0] = "defaultValue";
                break;
            case 366:
            case 368:
                objArr[0] = "x";
                break;
            case 370:
            case 374:
                objArr[0] = "collections";
                break;
            case 379:
                objArr[0] = "fun";
                break;
            case 394:
                objArr[0] = "clazz";
                break;
            case 403:
                objArr[0] = "strategy";
                break;
            case 432:
                objArr[0] = "sample";
                break;
            case 451:
                objArr[0] = "prefix";
                break;
            case 452:
            case 454:
                objArr[0] = "o1";
                break;
            case 453:
            case 455:
                objArr[0] = "o2";
                break;
            case 462:
                objArr[0] = "accumulator";
                break;
        }
        switch (i) {
            case 1:
                objArr[1] = "ar";
                break;
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "newHashMap";
                break;
            case 10:
                objArr[1] = "newLinkedHashMap";
                break;
            case 12:
                objArr[1] = "newLinkedList";
                break;
            case 16:
                objArr[1] = "newArrayList";
                break;
            case 19:
            case 20:
                objArr[1] = "newUnmodifiableList";
                break;
            case 22:
                objArr[1] = "unmodifiableOrEmptyList";
                break;
            case 24:
            case 25:
                objArr[1] = "unmodifiableOrEmptySet";
                break;
            case 27:
            case 28:
                objArr[1] = "unmodifiableOrEmptyMap";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
                objArr[1] = "newHashSet";
                break;
            case 35:
                objArr[1] = "newHashOrEmptySet";
                break;
            case 37:
                objArr[1] = "newLinkedHashSet";
                break;
            case 40:
                objArr[1] = "newConcurrentSet";
                break;
            case 44:
            case 49:
                objArr[1] = "union";
                break;
            case 51:
            case 52:
            case 53:
                objArr[1] = "immutableSet";
                break;
            case 55:
            case 56:
                objArr[1] = "immutableList";
                break;
            case 57:
                objArr[1] = "immutableSingletonList";
                break;
            case 61:
                objArr[1] = "groupBy";
                break;
            case 65:
            case 66:
            case 261:
            case 264:
            case 265:
            case 266:
                objArr[1] = "intersection";
                break;
            case 69:
                objArr[1] = "diff";
                break;
            case 77:
                objArr[1] = "mergeSortedLists";
                break;
            case 79:
                objArr[1] = "subList";
                break;
            case 90:
                objArr[1] = "newMapFromKeys";
                break;
            case 93:
                objArr[1] = "newMapFromValues";
                break;
            case 99:
                objArr[1] = "classify";
                break;
            case 122:
            case 125:
            case 133:
                objArr[1] = "map2Map";
                break;
            case 128:
            case 131:
                objArr[1] = "map2MapNotNull";
                break;
            case 141:
            case 145:
            case 149:
            case 153:
                objArr[1] = "map2Array";
                break;
            case 164:
                objArr[1] = "filter";
                break;
            case 167:
            case 171:
            case 184:
            case 187:
                objArr[1] = "findAll";
                break;
            case 174:
            case 177:
            case 180:
            case 181:
                objArr[1] = "findAllAsArray";
                break;
            case 194:
                objArr[1] = "iterate";
                break;
            case 197:
                objArr[1] = "iterateBackward";
                break;
            case 200:
                objArr[1] = "zip";
                break;
            case 203:
                objArr[1] = "collect";
                break;
            case 212:
                objArr[1] = "addAll";
                break;
            case 215:
                objArr[1] = "addAllNotNull";
                break;
            case 228:
            case 230:
            case 239:
            case 240:
            case 241:
            case 245:
            case 246:
            case 247:
            case 250:
            case 252:
            case 256:
                objArr[1] = "concat";
                break;
            case 233:
                objArr[1] = "append";
                break;
            case 236:
                objArr[1] = "prepend";
                break;
            case 268:
                objArr[1] = "getFirstItems";
                break;
            case 277:
                objArr[1] = "subtract";
                break;
            case 280:
            case 284:
            case 430:
            case 433:
                objArr[1] = "toArray";
                break;
            case 291:
            case 294:
            case 296:
                objArr[1] = "sorted";
                break;
            case 301:
            case 304:
            case 307:
            case 326:
            case 330:
            case 331:
                objArr[1] = "map";
                break;
            case 310:
            case 314:
            case 315:
            case 318:
            case 321:
                objArr[1] = "mapNotNull";
                break;
            case 323:
                objArr[1] = "packNullables";
                break;
            case 332:
            case 333:
            case 335:
                objArr[1] = "set";
                break;
            case 341:
                objArr[1] = "createMaybeSingletonList";
                break;
            case 342:
                objArr[1] = "createMaybeSingletonSet";
                break;
            case 345:
                objArr[1] = "getOrCreate";
                break;
            case 363:
                objArr[1] = "list";
                break;
            case 373:
                objArr[1] = "removeDuplicatesFromSorted";
                break;
            case 375:
                objArr[1] = "flatten";
                break;
            case 378:
                objArr[1] = "flatMap";
                break;
            case 382:
                objArr[1] = "convert";
                break;
            case 396:
                objArr[1] = "reverseMap";
                break;
            case 397:
                objArr[1] = "emptyList";
                break;
            case 399:
                objArr[1] = "createConcurrentWeakValueMap";
                break;
            case 400:
                objArr[1] = "createConcurrentSoftKeySoftValueMap";
                break;
            case 401:
                objArr[1] = "createConcurrentWeakKeySoftValueMap";
                break;
            case 402:
            case 404:
                objArr[1] = "createConcurrentWeakKeyWeakValueMap";
                break;
            case 405:
                objArr[1] = "createConcurrentSoftValueMap";
                break;
            case 406:
                objArr[1] = "createConcurrentSoftMap";
                break;
            case 407:
                objArr[1] = "createConcurrentWeakMap";
                break;
            case 414:
            case 415:
            case 418:
            case 419:
                objArr[1] = "map2Set";
                break;
            case 422:
            case 423:
                objArr[1] = "map2LinkedSet";
                break;
            case 426:
            case 427:
                objArr[1] = "map2SetNotNull";
                break;
            case 436:
                objArr[1] = "copyAndClear";
                break;
            case 438:
            case 439:
            case 440:
                objArr[1] = "copyList";
                break;
            case 443:
                objArr[1] = "toCollection";
                break;
            case 445:
            case 446:
                objArr[1] = "toList";
                break;
            case 447:
            case 448:
            case 449:
                objArr[1] = "notNullize";
                break;
            case 457:
                objArr[1] = "createWeakKeySoftValueMap";
                break;
            case 458:
                objArr[1] = "createWeakKeyWeakValueMap";
                break;
            case 459:
                objArr[1] = "createSoftKeySoftValueMap";
                break;
            case 460:
                objArr[1] = "createSoftMap";
                break;
            default:
                objArr[1] = "com/intellij/util/containers/ContainerUtil";
                break;
        }
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 12:
            case 16:
            case 19:
            case 20:
            case 22:
            case 24:
            case 25:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
            case 35:
            case 37:
            case 40:
            case 44:
            case 49:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 61:
            case 65:
            case 66:
            case 69:
            case 77:
            case 79:
            case 90:
            case 93:
            case 99:
            case 122:
            case 125:
            case 128:
            case 131:
            case 133:
            case 141:
            case 145:
            case 149:
            case 153:
            case 164:
            case 167:
            case 171:
            case 174:
            case 177:
            case 180:
            case 181:
            case 184:
            case 187:
            case 194:
            case 197:
            case 200:
            case 203:
            case 212:
            case 215:
            case 228:
            case 230:
            case 233:
            case 236:
            case 239:
            case 240:
            case 241:
            case 245:
            case 246:
            case 247:
            case 250:
            case 252:
            case 256:
            case 261:
            case 264:
            case 265:
            case 266:
            case 268:
            case 277:
            case 280:
            case 284:
            case 291:
            case 294:
            case 296:
            case 301:
            case 304:
            case 307:
            case 310:
            case 314:
            case 315:
            case 318:
            case 321:
            case 323:
            case 326:
            case 330:
            case 331:
            case 332:
            case 333:
            case 335:
            case 341:
            case 342:
            case 345:
            case 363:
            case 373:
            case 375:
            case 378:
            case 382:
            case 396:
            case 397:
            case 399:
            case 400:
            case 401:
            case 402:
            case 404:
            case 405:
            case 406:
            case 407:
            case 414:
            case 415:
            case 418:
            case 419:
            case 422:
            case 423:
            case 426:
            case 427:
            case 430:
            case 433:
            case 436:
            case 438:
            case 439:
            case 440:
            case 443:
            case 445:
            case 446:
            case 447:
            case 448:
            case 449:
            case 457:
            case 458:
            case 459:
            case 460:
                break;
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "newHashMap";
                break;
            case 8:
            case 9:
                objArr[2] = "newLinkedHashMap";
                break;
            case 11:
                objArr[2] = "newLinkedList";
                break;
            case 13:
            case 14:
            case 15:
                objArr[2] = "newArrayList";
                break;
            case 17:
                objArr[2] = "subArrayAsList";
                break;
            case 18:
                objArr[2] = "newUnmodifiableList";
                break;
            case 21:
                objArr[2] = "unmodifiableOrEmptyList";
                break;
            case 23:
                objArr[2] = "unmodifiableOrEmptySet";
                break;
            case 26:
                objArr[2] = "unmodifiableOrEmptyMap";
                break;
            case 29:
            case 30:
            case 32:
            case 33:
                objArr[2] = "newHashSet";
                break;
            case 36:
            case 38:
            case 39:
                objArr[2] = "newLinkedHashSet";
                break;
            case 41:
                objArr[2] = "reverse";
                break;
            case 42:
            case 43:
            case 45:
            case 46:
            case 47:
            case 48:
                objArr[2] = "union";
                break;
            case 50:
                objArr[2] = "immutableSet";
                break;
            case 54:
            case 58:
                objArr[2] = "immutableList";
                break;
            case 59:
            case 60:
                objArr[2] = "groupBy";
                break;
            case 62:
                objArr[2] = "getOrElse";
                break;
            case 63:
            case PartialGapBuffer.SURROUNDING_SIZE /* 64 */:
            case 259:
            case 260:
            case 262:
            case 263:
                objArr[2] = "intersection";
                break;
            case 67:
            case 68:
                objArr[2] = "diff";
                break;
            case 70:
            case 71:
            case 72:
            case 73:
                objArr[2] = "processSortedListsInOrder";
                break;
            case 74:
            case 75:
            case 76:
                objArr[2] = "mergeSortedLists";
                break;
            case 78:
                objArr[2] = "subList";
                break;
            case 80:
            case 81:
            case 82:
            case 83:
            case 208:
            case 209:
            case 210:
            case 211:
                objArr[2] = "addAll";
                break;
            case 84:
            case 85:
            case 86:
            case 87:
            case 213:
            case 214:
                objArr[2] = "addAllNotNull";
                break;
            case 88:
            case 89:
                objArr[2] = "newMapFromKeys";
                break;
            case 91:
            case 92:
                objArr[2] = "newMapFromValues";
                break;
            case 94:
            case 95:
            case 96:
                objArr[2] = "fillMapWithValues";
                break;
            case 97:
            case 98:
                objArr[2] = "classify";
                break;
            case 100:
            case 101:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
                objArr[2] = "find";
                break;
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                objArr[2] = "process";
                break;
            case 118:
            case 119:
                objArr[2] = "findLast";
                break;
            case 120:
            case 121:
            case 123:
            case 124:
            case 132:
                objArr[2] = "map2Map";
                break;
            case 126:
            case 127:
            case 129:
            case 130:
                objArr[2] = "map2MapNotNull";
                break;
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 142:
            case 143:
            case 144:
            case 146:
            case 147:
            case 148:
            case 150:
            case 151:
            case 152:
                objArr[2] = "map2Array";
                break;
            case 154:
            case 155:
            case 160:
            case 161:
            case 162:
            case 163:
                objArr[2] = "filter";
                break;
            case 156:
            case 157:
            case 158:
            case 159:
                objArr[2] = "filterIsInstance";
                break;
            case 165:
            case 166:
            case 169:
            case 170:
            case 182:
            case 183:
            case 185:
            case 186:
                objArr[2] = "findAll";
                break;
            case 168:
                objArr[2] = "skipNulls";
                break;
            case 172:
            case 173:
            case 175:
            case 176:
            case 178:
            case 179:
                objArr[2] = "findAllAsArray";
                break;
            case 188:
            case 189:
            case 190:
            case 191:
                objArr[2] = "all";
                break;
            case 192:
                objArr[2] = "removeDuplicates";
                break;
            case 193:
            case 195:
                objArr[2] = "iterate";
                break;
            case 196:
                objArr[2] = "iterateBackward";
                break;
            case 198:
            case 199:
                objArr[2] = "zip";
                break;
            case 201:
                objArr[2] = "swapElements";
                break;
            case 202:
            case 204:
            case 205:
            case 206:
            case 207:
                objArr[2] = "collect";
                break;
            case 216:
            case 217:
                objArr[2] = "removeAll";
                break;
            case 218:
            case 219:
                objArr[2] = "retainAll";
                break;
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
                objArr[2] = "findInstance";
                break;
            case 226:
            case 227:
            case 229:
            case 237:
            case 238:
            case 242:
            case 243:
            case 244:
            case 251:
            case 253:
            case 254:
            case PartialGapBuffer.BUF_SIZE /* 255 */:
                objArr[2] = "concat";
                break;
            case 231:
            case 232:
                objArr[2] = "append";
                break;
            case 234:
            case 235:
                objArr[2] = "prepend";
                break;
            case 248:
            case 249:
                objArr[2] = "concatIterators";
                break;
            case 257:
            case 258:
                objArr[2] = "intersects";
                break;
            case 267:
                objArr[2] = "getFirstItems";
                break;
            case 269:
            case 270:
                objArr[2] = "iterateAndGetLastItem";
                break;
            case 271:
            case 272:
                objArr[2] = "mapIterator";
                break;
            case 273:
            case 274:
                objArr[2] = "filterIterator";
                break;
            case 275:
            case 276:
                objArr[2] = "subtract";
                break;
            case 278:
            case 279:
            case 281:
            case 282:
            case 283:
            case 428:
            case 429:
            case 431:
            case 432:
                objArr[2] = "toArray";
                break;
            case 285:
            case 286:
            case 287:
            case 288:
            case 297:
            case 298:
                objArr[2] = "sort";
                break;
            case 289:
            case 290:
            case 292:
            case 293:
            case 295:
                objArr[2] = "sorted";
                break;
            case 299:
            case AnimationConstants.DefaultDurationMillis /* 300 */:
            case 302:
            case 303:
            case 305:
            case 306:
            case 324:
            case 325:
            case 327:
            case 328:
            case 329:
                objArr[2] = "map";
                break;
            case 308:
            case 309:
            case 311:
            case 312:
            case 313:
            case 316:
            case 317:
            case 319:
            case 320:
                objArr[2] = "mapNotNull";
                break;
            case 322:
                objArr[2] = "packNullables";
                break;
            case 334:
                objArr[2] = "set";
                break;
            case 336:
            case 337:
            case 338:
                objArr[2] = "putIfNotNull";
                break;
            case 339:
            case 340:
                objArr[2] = "add";
                break;
            case 343:
            case 344:
            case 346:
            case 347:
                objArr[2] = "getOrCreate";
                break;
            case 348:
            case 349:
            case 350:
            case 351:
                objArr[2] = "and";
                break;
            case 352:
            case 353:
            case 354:
            case 355:
                objArr[2] = "exists";
                break;
            case 356:
            case 357:
            case 358:
            case 359:
                objArr[2] = "or";
                break;
            case 360:
            case 361:
                objArr[2] = "count";
                break;
            case 362:
                objArr[2] = "list";
                break;
            case 364:
            case 365:
            case 366:
            case 367:
                objArr[2] = "quickSort";
                break;
            case 368:
            case 369:
                objArr[2] = "med3";
                break;
            case 370:
            case 374:
                objArr[2] = "flatten";
                break;
            case 371:
            case 372:
                objArr[2] = "removeDuplicatesFromSorted";
                break;
            case 376:
            case 377:
                objArr[2] = "flatMap";
                break;
            case 379:
            case 380:
            case 381:
                objArr[2] = "convert";
                break;
            case 383:
                objArr[2] = "containsIdentity";
                break;
            case 384:
                objArr[2] = "indexOfIdentity";
                break;
            case 385:
            case 386:
                objArr[2] = "equalsIdentity";
                break;
            case 387:
            case 388:
            case 389:
            case 390:
                objArr[2] = "indexOf";
                break;
            case 391:
            case 392:
                objArr[2] = "lastIndexOf";
                break;
            case 393:
            case 394:
                objArr[2] = "findLastInstance";
                break;
            case 395:
                objArr[2] = "reverseMap";
                break;
            case 398:
                objArr[2] = "createLockFreeCopyOnWriteList";
                break;
            case 403:
                objArr[2] = "createConcurrentWeakKeyWeakValueMap";
                break;
            case 408:
                objArr[2] = "createConcurrentList";
                break;
            case 409:
                objArr[2] = "addIfNotNull";
                break;
            case 410:
            case 411:
                objArr[2] = "map2List";
                break;
            case 412:
            case 413:
            case 416:
            case 417:
                objArr[2] = "map2Set";
                break;
            case 420:
            case 421:
                objArr[2] = "map2LinkedSet";
                break;
            case 424:
            case 425:
                objArr[2] = "map2SetNotNull";
                break;
            case 434:
            case 435:
                objArr[2] = "copyAndClear";
                break;
            case 437:
                objArr[2] = "copyList";
                break;
            case 441:
            case 442:
                objArr[2] = "toCollection";
                break;
            case 444:
                objArr[2] = "toList";
                break;
            case 450:
            case 451:
                objArr[2] = "startsWith";
                break;
            case 452:
            case 453:
            case 454:
            case 455:
            case 456:
                objArr[2] = "compareLexicographically";
                break;
            case 461:
            case 462:
                objArr[2] = "reduce";
                break;
            case 463:
                objArr[2] = "splitListToChunks";
                break;
            default:
                objArr[2] = "ar";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 12:
            case 16:
            case 19:
            case 20:
            case 22:
            case 24:
            case 25:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
            case 35:
            case 37:
            case 40:
            case 44:
            case 49:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 61:
            case 65:
            case 66:
            case 69:
            case 77:
            case 79:
            case 90:
            case 93:
            case 99:
            case 122:
            case 125:
            case 128:
            case 131:
            case 133:
            case 141:
            case 145:
            case 149:
            case 153:
            case 164:
            case 167:
            case 171:
            case 174:
            case 177:
            case 180:
            case 181:
            case 184:
            case 187:
            case 194:
            case 197:
            case 200:
            case 203:
            case 212:
            case 215:
            case 228:
            case 230:
            case 233:
            case 236:
            case 239:
            case 240:
            case 241:
            case 245:
            case 246:
            case 247:
            case 250:
            case 252:
            case 256:
            case 261:
            case 264:
            case 265:
            case 266:
            case 268:
            case 277:
            case 280:
            case 284:
            case 291:
            case 294:
            case 296:
            case 301:
            case 304:
            case 307:
            case 310:
            case 314:
            case 315:
            case 318:
            case 321:
            case 323:
            case 326:
            case 330:
            case 331:
            case 332:
            case 333:
            case 335:
            case 341:
            case 342:
            case 345:
            case 363:
            case 373:
            case 375:
            case 378:
            case 382:
            case 396:
            case 397:
            case 399:
            case 400:
            case 401:
            case 402:
            case 404:
            case 405:
            case 406:
            case 407:
            case 414:
            case 415:
            case 418:
            case 419:
            case 422:
            case 423:
            case 426:
            case 427:
            case 430:
            case 433:
            case 436:
            case 438:
            case 439:
            case 440:
            case 443:
            case 445:
            case 446:
            case 447:
            case 448:
            case 449:
            case 457:
            case 458:
            case 459:
            case 460:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static /* synthetic */ Iterator a(Iterable[] iterableArr) {
        Iterator[] itArr = new Iterator[iterableArr.length];
        for (int i = 0; i < iterableArr.length; i++) {
            itArr[i] = iterableArr[i].iterator();
        }
        return concatIterators(itArr);
    }

    @SafeVarargs
    public static <T, C extends Collection<? super T>> C addAll(C c, T... tArr) {
        if (c == null) {
            $$$reportNull$$$0(210);
        }
        if (tArr == null) {
            $$$reportNull$$$0(211);
        }
        for (T t : tArr) {
            c.add(t);
        }
        if (c == null) {
            $$$reportNull$$$0(212);
        }
        return c;
    }

    @SafeVarargs
    public static <T, C extends Collection<T>> C addAllNotNull(C c, T... tArr) {
        if (c == null) {
            $$$reportNull$$$0(213);
        }
        if (tArr == null) {
            $$$reportNull$$$0(214);
        }
        for (T t : tArr) {
            if (t != null) {
                c.add(t);
            }
        }
        if (c == null) {
            $$$reportNull$$$0(215);
        }
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void addIfNotNull(Collection<? super T> collection, T t) {
        if (collection == null) {
            $$$reportNull$$$0(409);
        }
        if (t != 0) {
            collection.add(t);
        }
    }

    public static <T> boolean all(Collection<? extends T> collection, Condition<? super T> condition) {
        if (collection == null) {
            $$$reportNull$$$0(190);
        }
        if (condition == null) {
            $$$reportNull$$$0(191);
        }
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            if (!condition.value(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean and(Iterable<? extends T> iterable, Condition<? super T> condition) {
        if (iterable == null) {
            $$$reportNull$$$0(350);
        }
        if (condition == null) {
            $$$reportNull$$$0(351);
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (!condition.value(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SafeVarargs
    public static <T> List<T> append(final List<? extends T> list, final T... tArr) {
        if (list == 0) {
            $$$reportNull$$$0(231);
        }
        if (tArr == null) {
            $$$reportNull$$$0(232);
        }
        if (tArr.length != 0) {
            return new AbstractList<T>() { // from class: com.intellij.util.containers.ContainerUtil.6
                @Override // java.util.AbstractList, java.util.List
                public T get(int i) {
                    return i < list.size() ? (T) list.get(i) : (T) tArr[i - list.size()];
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return list.size() + tArr.length;
                }
            };
        }
        if (list == 0) {
            $$$reportNull$$$0(233);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> concat(final List<? extends T> list, final List<? extends T> list2) {
        if (list == 0) {
            $$$reportNull$$$0(237);
        }
        if (list2 == 0) {
            $$$reportNull$$$0(238);
        }
        if (list.isEmpty() && list2.isEmpty()) {
            List<T> list3 = Collections.EMPTY_LIST;
            if (list3 == null) {
                $$$reportNull$$$0(239);
            }
            return list3;
        }
        if (list.isEmpty()) {
            if (list2 == 0) {
                $$$reportNull$$$0(240);
            }
            return list2;
        }
        if (list2.isEmpty()) {
            return list;
        }
        final int size = list.size();
        final int size2 = list2.size() + size;
        return new AbstractList<T>() { // from class: com.intellij.util.containers.ContainerUtil.8
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/ContainerUtil$8", "iterator"));
            }

            @Override // java.util.AbstractList, java.util.List
            public T get(int i) {
                List list4;
                int i2 = size;
                if (i < i2) {
                    list4 = list;
                } else {
                    list4 = list2;
                    i -= i2;
                }
                return (T) list4.get(i);
            }

            @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
            public Iterator<T> iterator() {
                Iterator<T> it = ContainerUtil.concat((Iterable) list, (Iterable) list2).iterator();
                if (it == null) {
                    $$$reportNull$$$0(0);
                }
                return it;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return size2;
            }
        };
    }

    @SafeVarargs
    public static <T> Iterator<T> concatIterators(Iterator<? extends T>... itArr) {
        if (itArr == null) {
            $$$reportNull$$$0(248);
        }
        return new SequenceIterator(itArr);
    }

    public static <T> T[] copyAndClear(Collection<T> collection, ArrayFactory<? extends T> arrayFactory, boolean z) {
        if (collection == null) {
            $$$reportNull$$$0(434);
        }
        if (arrayFactory == null) {
            $$$reportNull$$$0(435);
        }
        int size = collection.size();
        Object[] objArrCreate = arrayFactory.create(size);
        if (size > 0) {
            objArrCreate = (T[]) collection.toArray(objArrCreate);
            if (z) {
                collection.clear();
            }
        }
        if (objArrCreate == null) {
            $$$reportNull$$$0(436);
        }
        return objArrCreate;
    }

    @Deprecated
    public static <V> ConcurrentIntObjectMap<V> createConcurrentIntObjectMap() {
        return new ConcurrentIntObjectHashMap();
    }

    public static <T> ConcurrentList<T> createConcurrentList() {
        return new LockFreeCopyOnWriteArrayList();
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentSoftKeySoftValueMap() {
        ConcurrentMap<K, V> concurrentMapCreateConcurrentSoftKeySoftValueMap = CollectionFactory.createConcurrentSoftKeySoftValueMap(100, 0.75f, Runtime.getRuntime().availableProcessors());
        if (concurrentMapCreateConcurrentSoftKeySoftValueMap == null) {
            $$$reportNull$$$0(400);
        }
        return concurrentMapCreateConcurrentSoftKeySoftValueMap;
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentSoftMap() {
        ConcurrentMap<K, V> concurrentMapCreateConcurrentSoftMap = CollectionFactory.createConcurrentSoftMap();
        if (concurrentMapCreateConcurrentSoftMap == null) {
            $$$reportNull$$$0(406);
        }
        return concurrentMapCreateConcurrentSoftMap;
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentSoftValueMap() {
        ConcurrentMap<K, V> concurrentMapCreateConcurrentSoftValueMap = CollectionFactory.createConcurrentSoftValueMap();
        if (concurrentMapCreateConcurrentSoftValueMap == null) {
            $$$reportNull$$$0(405);
        }
        return concurrentMapCreateConcurrentSoftValueMap;
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakKeySoftValueMap() {
        ConcurrentMap<K, V> concurrentMapCreateConcurrentWeakKeySoftValueMap = CollectionFactory.createConcurrentWeakKeySoftValueMap();
        if (concurrentMapCreateConcurrentWeakKeySoftValueMap == null) {
            $$$reportNull$$$0(401);
        }
        return concurrentMapCreateConcurrentWeakKeySoftValueMap;
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakMap() {
        ConcurrentMap<K, V> concurrentMapCreateConcurrentWeakMap = CollectionFactory.createConcurrentWeakMap();
        if (concurrentMapCreateConcurrentWeakMap == null) {
            $$$reportNull$$$0(407);
        }
        return concurrentMapCreateConcurrentWeakMap;
    }

    public static <K, V> ConcurrentMap<K, V> createConcurrentWeakValueMap() {
        ConcurrentMap<K, V> concurrentMapCreateConcurrentWeakValueMap = CollectionFactory.createConcurrentWeakValueMap();
        if (concurrentMapCreateConcurrentWeakValueMap == null) {
            $$$reportNull$$$0(399);
        }
        return concurrentMapCreateConcurrentWeakValueMap;
    }

    public static <T> IntObjectMap<T> createIntKeyWeakValueMap() {
        return new IntKeyWeakValueHashMap();
    }

    public static <T> List<T> createLockFreeCopyOnWriteList() {
        return new LockFreeCopyOnWriteArrayList();
    }

    public static <T> List<T> createMaybeSingletonList(T t) {
        List<T> listSingletonList = t == null ? Collections.EMPTY_LIST : Collections.singletonList(t);
        if (listSingletonList == null) {
            $$$reportNull$$$0(341);
        }
        return listSingletonList;
    }

    public static <K, V> Map<K, V> createSoftValueMap() {
        return new SoftValueHashMap();
    }

    public static <K, V> Map<K, V> createWeakValueMap() {
        return new WeakValueHashMap();
    }

    public static <T> List<T> emptyList() {
        List<T> listEmptyList = ContainerUtilRt.emptyList();
        if (listEmptyList == null) {
            $$$reportNull$$$0(397);
        }
        return listEmptyList;
    }

    private static <T> Set<T> emptyOrUnmodifiableSet(Set<T> set) {
        if (set.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        return Options.RETURN_REALLY_UNMODIFIABLE_COLLECTION_FROM_METHODS_MARKED_UNMODIFIABLE ? Collections.unmodifiableSet(set) : set;
    }

    public static <T> boolean exists(Iterable<? extends T> iterable, Condition<? super T> condition) {
        if (iterable == null) {
            $$$reportNull$$$0(354);
        }
        if (condition == null) {
            $$$reportNull$$$0(355);
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (condition.value(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> List<T> filter(T[] tArr, Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(154);
        }
        if (tArr == null) {
            $$$reportNull$$$0(155);
        }
        return findAll(tArr, condition);
    }

    public static <T> List<T> filterIsInstance(Collection<?> collection, Class<? extends T> cls) {
        if (collection == null) {
            $$$reportNull$$$0(156);
        }
        if (cls == null) {
            $$$reportNull$$$0(157);
        }
        return filter(collection, Conditions.instanceOf(cls));
    }

    public static <T> T find(Iterator<? extends T> it, Condition<? super T> condition) {
        if (it == null) {
            $$$reportNull$$$0(116);
        }
        if (condition == null) {
            $$$reportNull$$$0(117);
        }
        while (it.hasNext()) {
            T next = it.next();
            if (condition.value(next)) {
                return next;
            }
        }
        return null;
    }

    public static <T> List<T> findAll(Collection<? extends T> collection, Condition<? super T> condition) {
        if (collection == null) {
            $$$reportNull$$$0(165);
        }
        if (condition == null) {
            $$$reportNull$$$0(166);
        }
        if (collection.isEmpty()) {
            return emptyList();
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList();
        for (T t : collection) {
            if (condition.value(t)) {
                freezableArrayList.add(t);
            }
        }
        List<T> listEmptyList = freezableArrayList.isEmpty() ? emptyList() : freezableArrayList.freeze();
        if (listEmptyList == null) {
            $$$reportNull$$$0(167);
        }
        return listEmptyList;
    }

    public static <T, V> List<V> flatMap(Iterable<? extends T> iterable, Function<? super T, ? extends List<? extends V>> function) {
        if (iterable == null) {
            $$$reportNull$$$0(376);
        }
        if (function == null) {
            $$$reportNull$$$0(377);
        }
        Iterator<? extends T> it = iterable.iterator();
        List<? extends V> list = null;
        boolean z = true;
        while (it.hasNext()) {
            List<? extends V> listFun = function.fun(it.next());
            if (!listFun.isEmpty()) {
                if (list == null) {
                    list = listFun;
                } else {
                    if (z) {
                        ArrayList arrayList = new ArrayList(Math.max(10, list.size() + listFun.size()));
                        arrayList.addAll(list);
                        z = false;
                        list = arrayList;
                    }
                    list.addAll(listFun);
                }
            }
        }
        List<V> listEmptyList = list == null ? emptyList() : Collections.unmodifiableList(list);
        if (listEmptyList == null) {
            $$$reportNull$$$0(378);
        }
        return listEmptyList;
    }

    public static <T> List<T> flatten(Iterable<? extends Collection<? extends T>> iterable) {
        if (iterable == null) {
            $$$reportNull$$$0(374);
        }
        Iterator<? extends Collection<? extends T>> it = iterable.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().size();
        }
        if (size == 0) {
            return emptyList();
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(size);
        Iterator<? extends Collection<? extends T>> it2 = iterable.iterator();
        while (it2.hasNext()) {
            freezableArrayList.addAll(it2.next());
        }
        List<T> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(375);
        }
        return listEmptyOrFrozen;
    }

    public static <T> T getFirstItem(Collection<? extends T> collection, T t) {
        return (collection == null || collection.isEmpty()) ? t : collection.iterator().next();
    }

    public static <T> T getLastItem(List<? extends T> list, T t) {
        return isEmpty(list) ? t : list.get(list.size() - 1);
    }

    public static <T> T getOnlyItem(Collection<? extends T> collection, T t) {
        return (collection == null || collection.size() != 1) ? t : collection.iterator().next();
    }

    @SafeVarargs
    @Deprecated
    public static <E> Set<E> immutableSet(E... eArr) {
        if (eArr == null) {
            $$$reportNull$$$0(50);
        }
        int length = eArr.length;
        if (length == 0) {
            Set<E> set = Collections.EMPTY_SET;
            if (set == null) {
                $$$reportNull$$$0(51);
            }
            return set;
        }
        if (length != 1) {
            Set<E> setUnmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(eArr)));
            if (setUnmodifiableSet == null) {
                $$$reportNull$$$0(53);
            }
            return setUnmodifiableSet;
        }
        Set<E> setSingleton = Collections.singleton(eArr[0]);
        if (setSingleton == null) {
            $$$reportNull$$$0(52);
        }
        return setSingleton;
    }

    public static <T> int indexOf(List<? extends T> list, Condition<? super T> condition) {
        if (list == null) {
            $$$reportNull$$$0(387);
        }
        if (condition == null) {
            $$$reportNull$$$0(388);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (condition.value(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <T> Collection<T> intersection(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            $$$reportNull$$$0(259);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(260);
        }
        if (collection.isEmpty() || collection2.isEmpty()) {
            return emptyList();
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList();
        for (T t : collection) {
            if (collection2.contains(t)) {
                freezableArrayList.add(t);
            }
        }
        List<T> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(261);
        }
        return listEmptyOrFrozen;
    }

    public static <T> boolean intersects(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            $$$reportNull$$$0(257);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(258);
        }
        if (collection.size() <= collection2.size()) {
            Iterator<? extends T> it = collection.iterator();
            while (it.hasNext()) {
                if (collection2.contains(it.next())) {
                    return true;
                }
            }
            return false;
        }
        Iterator<? extends T> it2 = collection2.iterator();
        while (it2.hasNext()) {
            if (collection.contains(it2.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isEmpty(Collection<? extends T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T, V> List<V> map(Collection<? extends T> collection, Function<? super T, ? extends V> function) {
        if (collection == null) {
            $$$reportNull$$$0(305);
        }
        if (function == null) {
            $$$reportNull$$$0(306);
        }
        if (collection.isEmpty()) {
            return emptyList();
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(collection.size());
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            freezableArrayList.add(function.fun(it.next()));
        }
        List<V> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(307);
        }
        return listEmptyOrFrozen;
    }

    public static <T, V> V[] map2Array(Collection<? extends T> collection, Class<V> cls, Function<? super T, ? extends V> function) {
        if (collection == null) {
            $$$reportNull$$$0(142);
        }
        if (cls == null) {
            $$$reportNull$$$0(143);
        }
        if (function == null) {
            $$$reportNull$$$0(144);
        }
        V[] vArr = (V[]) ArrayUtil.newArray(cls, collection.size());
        Iterator<? extends T> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            vArr[i] = function.fun(it.next());
            i++;
        }
        if (vArr == null) {
            $$$reportNull$$$0(145);
        }
        return vArr;
    }

    public static <T, V> Set<V> map2Set(Collection<? extends T> collection, Function<? super T, ? extends V> function) {
        if (collection == null) {
            $$$reportNull$$$0(416);
        }
        if (function == null) {
            $$$reportNull$$$0(417);
        }
        if (collection.isEmpty()) {
            Set<V> set = Collections.EMPTY_SET;
            if (set == null) {
                $$$reportNull$$$0(418);
            }
            return set;
        }
        HashSet hashSet = new HashSet(collection.size());
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            hashSet.add(function.fun(it.next()));
        }
        Set<V> setEmptyOrUnmodifiableSet = emptyOrUnmodifiableSet(hashSet);
        if (setEmptyOrUnmodifiableSet == null) {
            $$$reportNull$$$0(419);
        }
        return setEmptyOrUnmodifiableSet;
    }

    public static <T, V> Set<V> map2SetNotNull(Collection<? extends T> collection, Function<? super T, ? extends V> function) {
        if (collection == null) {
            $$$reportNull$$$0(424);
        }
        if (function == null) {
            $$$reportNull$$$0(425);
        }
        if (collection.isEmpty()) {
            Set<V> set = Collections.EMPTY_SET;
            if (set == null) {
                $$$reportNull$$$0(426);
            }
            return set;
        }
        HashSet hashSet = new HashSet(collection.size());
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            V vFun = function.fun(it.next());
            if (vFun != null) {
                hashSet.add(vFun);
            }
        }
        Set<V> setEmptyOrUnmodifiableSet = emptyOrUnmodifiableSet(hashSet);
        if (setEmptyOrUnmodifiableSet == null) {
            $$$reportNull$$$0(427);
        }
        return setEmptyOrUnmodifiableSet;
    }

    public static <T, V> List<V> mapNotNull(Collection<? extends T> collection, Function<? super T, ? extends V> function) {
        if (collection == null) {
            $$$reportNull$$$0(319);
        }
        if (function == null) {
            $$$reportNull$$$0(320);
        }
        if (collection.isEmpty()) {
            return emptyList();
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(collection.size());
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            V vFun = function.fun(it.next());
            if (vFun != null) {
                freezableArrayList.add(vFun);
            }
        }
        List<V> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(321);
        }
        return listEmptyOrFrozen;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0057 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0058 A[RETURN] */
    private static <T> int med3(List<? extends T> list, Comparator<? super T> comparator, int i, int i2, int i3) {
        if (list == null) {
            $$$reportNull$$$0(368);
        }
        if (comparator == null) {
            $$$reportNull$$$0(369);
        }
        if (comparator.compare(list.get(i), list.get(i2)) < 0) {
            if (comparator.compare(list.get(i2), list.get(i3)) >= 0) {
                if (comparator.compare(list.get(i), list.get(i3)) < 0) {
                    return i3;
                }
                return i;
            }
            return i2;
        }
        if (comparator.compare(list.get(i3), list.get(i2)) >= 0) {
            if (comparator.compare(list.get(i3), list.get(i)) < 0) {
                return i3;
            }
            return i;
        }
        return i2;
    }

    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        if (iterable == null) {
            $$$reportNull$$$0(15);
        }
        ArrayList<E> arrayList = new ArrayList<>();
        Iterator<? extends E> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static <T> Set<T> newConcurrentSet() {
        ConcurrentHashMap.KeySetView keySetViewNewKeySet = ConcurrentHashMap.newKeySet();
        if (keySetViewNewKeySet == null) {
            $$$reportNull$$$0(40);
        }
        return keySetViewNewKeySet;
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... tArr) {
        if (tArr == null) {
            $$$reportNull$$$0(29);
        }
        return new HashSet<>(Arrays.asList(tArr));
    }

    public static <T> LinkedHashSet<T> newLinkedHashSet(Iterable<? extends T> iterable) {
        if (iterable == null) {
            $$$reportNull$$$0(36);
        }
        LinkedHashSet<T> linkedHashSet = new LinkedHashSet<>();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(it.next());
        }
        return linkedHashSet;
    }

    public static <T> boolean or(Iterable<? extends T> iterable, Condition<? super T> condition) {
        if (iterable == null) {
            $$$reportNull$$$0(358);
        }
        if (condition == null) {
            $$$reportNull$$$0(359);
        }
        return exists(iterable, condition);
    }

    public static <T> boolean process(Iterable<? extends T> iterable, Processor<? super T> processor) {
        if (iterable == null) {
            $$$reportNull$$$0(102);
        }
        if (processor == null) {
            $$$reportNull$$$0(103);
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (!processor.process(it.next())) {
                return false;
            }
        }
        return true;
    }

    private static <T> void quickSort(List<? extends T> list, Comparator<? super T> comparator, int i, int i2) {
        int iMed3;
        if (list == null) {
            $$$reportNull$$$0(366);
        }
        if (comparator == null) {
            $$$reportNull$$$0(367);
        }
        if (i2 < 7) {
            for (int i3 = i; i3 < i2 + i; i3++) {
                for (int i4 = i3; i4 > i; i4--) {
                    int i5 = i4 - 1;
                    if (comparator.compare(list.get(i4), list.get(i5)) >= 0) {
                        break;
                    }
                    swapElements(list, i4, i5);
                }
            }
            return;
        }
        int iMed4 = (i2 >> 1) + i;
        if (i2 > 7) {
            int iMed5 = (i + i2) - 1;
            if (i2 > 40) {
                int i6 = i2 / 8;
                int i7 = i6 * 2;
                iMed3 = med3(list, comparator, i, i + i6, i + i7);
                iMed4 = med3(list, comparator, iMed4 - i6, iMed4, iMed4 + i6);
                iMed5 = med3(list, comparator, iMed5 - i7, iMed5 - i6, iMed5);
            } else {
                iMed3 = i;
            }
            iMed4 = med3(list, comparator, iMed3, iMed4, iMed5);
        }
        T t = list.get(iMed4);
        int i8 = i2 + i;
        int i9 = i8 - 1;
        int i10 = i;
        int i11 = i10;
        int i12 = i9;
        while (true) {
            if (i10 > i9 || comparator.compare(list.get(i10), t) > 0) {
                while (i9 >= i10 && comparator.compare(t, list.get(i9)) <= 0) {
                    if (comparator.compare(list.get(i9), t) == 0) {
                        swapElements(list, i9, i12);
                        i12--;
                    }
                    i9--;
                }
                if (i10 > i9) {
                    break;
                }
                swapElements(list, i10, i9);
                i10++;
                i9--;
            } else {
                if (comparator.compare(list.get(i10), t) == 0) {
                    swapElements(list, i11, i10);
                    i11++;
                }
                i10++;
            }
        }
        int i13 = i11 - i;
        int i14 = i10 - i11;
        int iMin = Math.min(i13, i14);
        vecSwap(list, i, i10 - iMin, iMin);
        int i15 = i12 - i9;
        int iMin2 = Math.min(i15, (i8 - i12) - 1);
        vecSwap(list, i10, i8 - iMin2, iMin2);
        if (i14 > 1) {
            quickSort(list, comparator, i, i14);
        }
        if (i15 > 1) {
            quickSort(list, comparator, i8 - i15, i15);
        }
    }

    public static <E> List<E> reverse(final List<? extends E> list) {
        if (list == null) {
            $$$reportNull$$$0(41);
        }
        return list.isEmpty() ? emptyList() : new AbstractList<E>() { // from class: com.intellij.util.containers.ContainerUtil.2
            @Override // java.util.AbstractList, java.util.List
            public E get(int i) {
                List list2 = list;
                return (E) list2.get((list2.size() - 1) - i);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return list.size();
            }
        };
    }

    public static <T> List<T> skipNulls(Collection<? extends T> collection) {
        if (collection == null) {
            $$$reportNull$$$0(168);
        }
        return findAll(collection, Conditions.notNull());
    }

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list == null) {
            $$$reportNull$$$0(286);
        }
        if (comparator == null) {
            $$$reportNull$$$0(287);
        }
        int size = list.size();
        if (size < 2) {
            return;
        }
        if (size == 2) {
            T t = list.get(0);
            T t2 = list.get(1);
            if (comparator.compare(t, t2) > 0) {
                list.set(0, t2);
                list.set(1, t);
                return;
            }
            return;
        }
        if (size >= 10) {
            list.sort(comparator);
            return;
        }
        for (int i = 0; i < size; i++) {
            for (int i2 = 0; i2 < i; i2++) {
                T t3 = list.get(i);
                T t4 = list.get(i2);
                if (comparator.compare(t3, t4) < 0) {
                    list.set(i, t4);
                    list.set(i2, t3);
                }
            }
        }
    }

    public static <T> List<T> sorted(Collection<? extends T> collection, Comparator<? super T> comparator) {
        if (collection == null) {
            $$$reportNull$$$0(289);
        }
        if (comparator == null) {
            $$$reportNull$$$0(290);
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(collection);
        sort(freezableArrayList, comparator);
        List<T> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(291);
        }
        return listEmptyOrFrozen;
    }

    public static <T> List<T> subArrayAsList(final T[] tArr, final int i, final int i2) {
        if (tArr == null) {
            $$$reportNull$$$0(17);
        }
        if (i >= 0 && i <= i2 && i2 <= tArr.length) {
            return new AbstractList<T>() { // from class: com.intellij.util.containers.ContainerUtil.1
                @Override // java.util.AbstractList, java.util.List
                public T get(int i3) {
                    if (i3 >= 0) {
                        int i4 = i2;
                        int i5 = i;
                        if (i3 < i4 - i5) {
                            return (T) tArr[i5 + i3];
                        }
                    }
                    kac.a("index:", i3, " size:", i2 - i);
                    return null;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return i2 - i;
                }
            };
        }
        fq7.a("start:", i, " end:", i2, " length:", tArr.length);
        return null;
    }

    public static <T> Collection<T> subtract(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            $$$reportNull$$$0(275);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(276);
        }
        HashSet hashSet = new HashSet(collection);
        hashSet.removeAll(collection2);
        Collection<T> collectionEmptyList = hashSet.isEmpty() ? emptyList() : Collections.unmodifiableCollection(hashSet);
        if (collectionEmptyList == null) {
            $$$reportNull$$$0(277);
        }
        return collectionEmptyList;
    }

    public static void swapElements(List<?> list, int i, int i2) {
        if (list == null) {
            $$$reportNull$$$0(201);
        }
        Object obj = list.get(i);
        list.set(i, list.get(i2));
        list.set(i2, obj);
    }

    public static <T> T[] toArray(Collection<T> collection, ArrayFactory<? extends T> arrayFactory) {
        if (collection == null) {
            $$$reportNull$$$0(278);
        }
        if (arrayFactory == null) {
            $$$reportNull$$$0(279);
        }
        T[] tArr = (T[]) collection.toArray(arrayFactory.create(collection.size()));
        if (tArr == null) {
            $$$reportNull$$$0(280);
        }
        return tArr;
    }

    public static <T> Set<T> union(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection == null) {
            $$$reportNull$$$0(47);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(48);
        }
        HashSet hashSet = new HashSet(collection.size() + collection2.size());
        hashSet.addAll(collection);
        hashSet.addAll(collection2);
        Set<T> setEmptyOrUnmodifiableSet = emptyOrUnmodifiableSet(hashSet);
        if (setEmptyOrUnmodifiableSet == null) {
            $$$reportNull$$$0(49);
        }
        return setEmptyOrUnmodifiableSet;
    }

    private static <T> void vecSwap(List<T> list, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swapElements(list, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static <T> T getLastItem(List<? extends T> list) {
        return (T) getLastItem(list, null);
    }

    public static <T> List<T> filter(Collection<? extends T> collection, Condition<? super T> condition) {
        if (collection == null) {
            $$$reportNull$$$0(160);
        }
        if (condition == null) {
            $$$reportNull$$$0(161);
        }
        return findAll(collection, condition);
    }

    public static <T> T getFirstItem(List<? extends T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static <T> T getFirstItem(Collection<? extends T> collection) {
        return (T) getFirstItem(collection, null);
    }

    public static <T> T getOnlyItem(Collection<? extends T> collection) {
        return (T) getOnlyItem(collection, null);
    }

    public static <T> List<T> filterIsInstance(Object[] objArr, Class<? extends T> cls) {
        if (cls == null) {
            $$$reportNull$$$0(158);
        }
        if (objArr == null) {
            $$$reportNull$$$0(159);
        }
        return filter(objArr, Conditions.instanceOf(cls));
    }

    public static <T> T find(Iterable<? extends T> iterable, Condition<? super T> condition) {
        if (iterable == null) {
            $$$reportNull$$$0(110);
        }
        if (condition == null) {
            $$$reportNull$$$0(111);
        }
        return (T) find(iterable.iterator(), condition);
    }

    public static <T> T find(T[] tArr, Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(100);
        }
        if (tArr == null) {
            $$$reportNull$$$0(101);
        }
        for (T t : tArr) {
            if (condition.value(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T extends Comparable<? super T>> List<T> sorted(Collection<? extends T> collection) {
        if (collection == null) {
            $$$reportNull$$$0(295);
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(collection);
        freezableArrayList.sort(null);
        List<T> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(296);
        }
        return listEmptyOrFrozen;
    }

    public static <T> void addAllNotNull(Collection<? super T> collection, Iterator<? extends T> it) {
        if (collection == null) {
            $$$reportNull$$$0(86);
        }
        if (it == null) {
            $$$reportNull$$$0(87);
        }
        while (it.hasNext()) {
            T next = it.next();
            if (next != null) {
                collection.add(next);
            }
        }
    }

    public static <T> int indexOf(T[] tArr, Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(389);
        }
        if (tArr == null) {
            $$$reportNull$$$0(390);
        }
        for (int i = 0; i < tArr.length; i++) {
            if (condition.value(tArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static <T> boolean and(T[] tArr, Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(348);
        }
        if (tArr == null) {
            $$$reportNull$$$0(349);
        }
        for (T t : tArr) {
            if (!condition.value(t)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean exists(T[] tArr, Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(352);
        }
        if (tArr == null) {
            $$$reportNull$$$0(353);
        }
        for (T t : tArr) {
            if (condition.value(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean process(List<? extends T> list, Processor<? super T> processor) {
        if (list == null) {
            $$$reportNull$$$0(104);
        }
        if (processor == null) {
            $$$reportNull$$$0(105);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!processor.process(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static <T> void addAllNotNull(Collection<? super T> collection, Iterable<? extends T> iterable) {
        if (collection == null) {
            $$$reportNull$$$0(84);
        }
        if (iterable == null) {
            $$$reportNull$$$0(85);
        }
        addAllNotNull(collection, iterable.iterator());
    }

    public static <T> boolean process(T[] tArr, Processor<? super T> processor) {
        if (processor == null) {
            $$$reportNull$$$0(106);
        }
        if (tArr == null) {
            $$$reportNull$$$0(107);
        }
        for (T t : tArr) {
            if (!processor.process(t)) {
                return false;
            }
        }
        return true;
    }

    public static <T> Set<T> union(Set<? extends T> set, Set<? extends T> set2) {
        if (set == null) {
            $$$reportNull$$$0(45);
        }
        if (set2 == null) {
            $$$reportNull$$$0(46);
        }
        return union((Collection) set, (Collection) set2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, V> V[] map2Array(T[] tArr, Class<V> cls, Function<? super T, ? extends V> function) {
        if (cls == null) {
            $$$reportNull$$$0(138);
        }
        if (function == null) {
            $$$reportNull$$$0(139);
        }
        if (tArr == null) {
            $$$reportNull$$$0(140);
        }
        V[] vArr = (V[]) ArrayUtil.newArray(cls, tArr.length);
        for (int i = 0; i < tArr.length; i++) {
            vArr[i] = function.fun(tArr[i]);
        }
        if (vArr == null) {
            $$$reportNull$$$0(141);
        }
        return vArr;
    }

    public static <T, V> V[] map2Array(T[] tArr, V[] vArr, Function<? super T, ? extends V> function) {
        if (function == null) {
            $$$reportNull$$$0(150);
        }
        if (tArr == null) {
            $$$reportNull$$$0(151);
        }
        if (vArr == null) {
            $$$reportNull$$$0(152);
        }
        V[] vArr2 = (V[]) map(tArr, function).toArray(vArr);
        if (vArr2 == null) {
            $$$reportNull$$$0(153);
        }
        return vArr2;
    }

    public static <T, V> List<V> map(Iterable<? extends T> iterable, Function<? super T, ? extends V> function) {
        if (iterable == null) {
            $$$reportNull$$$0(299);
        }
        if (function == null) {
            $$$reportNull$$$0(AnimationConstants.DefaultDurationMillis);
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            freezableArrayList.add(function.fun(it.next()));
        }
        List<V> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(301);
        }
        return listEmptyOrFrozen;
    }

    public static <T, V> List<V> mapNotNull(T[] tArr, Function<? super T, ? extends V> function) {
        if (function == null) {
            $$$reportNull$$$0(308);
        }
        if (tArr == null) {
            $$$reportNull$$$0(309);
        }
        if (tArr.length == 0) {
            return emptyList();
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(tArr.length);
        for (T t : tArr) {
            V vFun = function.fun(t);
            if (vFun != null) {
                freezableArrayList.add(vFun);
            }
        }
        List<V> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(310);
        }
        return listEmptyOrFrozen;
    }

    public static <T, V> List<T> concat(V[] vArr, Function<? super V, ? extends Collection<? extends T>> function) {
        if (function == null) {
            $$$reportNull$$$0(226);
        }
        if (vArr == null) {
            $$$reportNull$$$0(227);
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList();
        for (V v : vArr) {
            freezableArrayList.addAll(function.fun(v));
        }
        List<T> listEmptyList = freezableArrayList.isEmpty() ? emptyList() : freezableArrayList.freeze();
        if (listEmptyList == null) {
            $$$reportNull$$$0(228);
        }
        return listEmptyList;
    }

    public static <T, V> List<V> map(T[] tArr, Function<? super T, ? extends V> function) {
        if (function == null) {
            $$$reportNull$$$0(324);
        }
        if (tArr == null) {
            $$$reportNull$$$0(325);
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList(tArr.length);
        for (T t : tArr) {
            freezableArrayList.add(function.fun(t));
        }
        List<V> listEmptyOrFrozen = freezableArrayList.emptyOrFrozen();
        if (listEmptyOrFrozen == null) {
            $$$reportNull$$$0(326);
        }
        return listEmptyOrFrozen;
    }

    public static <T> Iterable<T> concat(final Iterable<? extends T> iterable, final Iterable<? extends T> iterable2) {
        if (iterable == null) {
            $$$reportNull$$$0(242);
        }
        if (iterable2 == null) {
            $$$reportNull$$$0(243);
        }
        return new Iterable<T>() { // from class: com.intellij.util.containers.ContainerUtil.9
            @Override // java.lang.Iterable
            public void forEach(Consumer<? super T> consumer) {
                iterable.forEach(consumer);
                iterable2.forEach(consumer);
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new Iterator<T>() { // from class: com.intellij.util.containers.ContainerUtil.9.1
                    boolean firstFinished;
                    Iterator<? extends T> it;

                    {
                        this.it = iterable.iterator();
                        advance();
                    }

                    private void advance() {
                        if (this.firstFinished || this.it.hasNext()) {
                            return;
                        }
                        this.it = iterable2.iterator();
                        this.firstFinished = true;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return !this.firstFinished || this.it.hasNext();
                    }

                    @Override // java.util.Iterator
                    public T next() {
                        T next = this.it.next();
                        advance();
                        return next;
                    }
                };
            }
        };
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(final Iterable<? extends T>... iterableArr) {
        if (iterableArr == null) {
            $$$reportNull$$$0(244);
        }
        if (iterableArr.length == 0) {
            List list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(245);
            }
            return list;
        }
        if (iterableArr.length == 1) {
            Iterable<T> iterable = (Iterable<T>) iterableArr[0];
            if (iterable == null) {
                $$$reportNull$$$0(246);
            }
            return iterable;
        }
        return new Iterable() { // from class: iu2
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ContainerUtil.a(iterableArr);
            }
        };
    }

    public static <T> List<T> findAll(T[] tArr, Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(185);
        }
        if (tArr == null) {
            $$$reportNull$$$0(186);
        }
        FreezableArrayList freezableArrayList = new FreezableArrayList();
        for (T t : tArr) {
            if (condition.value(t)) {
                freezableArrayList.add(t);
            }
        }
        List<T> listFreeze = freezableArrayList.freeze();
        if (listFreeze == null) {
            $$$reportNull$$$0(187);
        }
        return listFreeze;
    }

    public static <T> void quickSort(List<? extends T> list, Comparator<? super T> comparator) {
        if (list == null) {
            $$$reportNull$$$0(364);
        }
        if (comparator == null) {
            $$$reportNull$$$0(365);
        }
        quickSort(list, comparator, 0, list.size());
    }
}
