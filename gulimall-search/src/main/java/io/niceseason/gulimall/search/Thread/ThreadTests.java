package io.niceseason.gulimall.search.Thread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ThreadTests {
    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
//        runAsync();
//        CompletableFuture<Long> future = supplyAsync();
//        whenComplete();
//        thenApply();
//        handle();
//        thenAccept();
//        thenRun();
//        thenCombine();
//        thenAcceptBoth();
//        applyToEither();
        acceptEither();
        System.out.println("主线程执行完成");
    }


    //无返回值
    public static CompletableFuture<Void> runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ..."+Thread.currentThread().getId());
        },service);
//        future.get();
        return future;
    }

    //有返回值
    public static CompletableFuture<Long> supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        return future;
    }

    public static void whenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
                Long i = 1000L / 0;
            } catch (InterruptedException e) {
            }
            return 1L;
        },service).whenCompleteAsync((res,exception)->{
            System.out.println("结果完成"+res);
            System.out.println("出现异常:"+exception);
        }).exceptionally(t->{
            System.out.println(t.getMessage());
            return 10L;
        });
        System.out.println("run end ..." +future.get());
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
                Long i = 1000L / 3;
            } catch (InterruptedException e) {
            }
            return 1L;
        }, service).thenApply(res -> res * 2);
        System.out.println(future.get());
    }

    private static void handle() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
                Long i = 1000L / 0;
                return 1L;
            } catch (InterruptedException e) {
                return 2L;
            }
        }, service).handle((t,u)->{
            System.out.println(u.getMessage());
            return t * 2;
        });
        System.out.println(future.get());
    }

    private static void thenAccept() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
                Long i = 1000L / 3;
                return 1L;
            } catch (InterruptedException e) {
                return 2L;
            }
        }, service).thenAccept(res -> {
            System.out.println(res);
        });
//        System.out.println(future.get());
    }

    private static void thenRun() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
                Long i = 1000L / 0;
                return 1L;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -1L;
            }
        }, service).thenRun(() -> {
            System.out.println("thenRun方法执行了，，");
        });
        System.out.println(future.get());
    }

    private static void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello2");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t+" "+u);
        System.out.println(result.get());
    }

    private static void thenAcceptBoth() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        },service);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        },service);
    }

    private static void applyToEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        },service);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        },service);

        CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
            System.out.println("applyEither:"+t);
            return t * 2;
        });

    }

    private static void acceptEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        },service);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        },service);

        CompletableFuture<Void> result = f1.acceptEither(f2, t -> {
            System.out.println("acceptEither:"+t);
        });

    }
}
