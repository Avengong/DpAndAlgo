package com.tencent.asynctask;

import android.os.*;
import android.os.Process;
import android.support.annotation.WorkerThread;

import java.util.ArrayDeque;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class CopyAsyncTask<Params, Progress, Result> {
    static final ThreadFactory sThreadFactory = new ThreadFactory() {
        final AtomicInteger sCreator = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {

            return new Thread(r, "asyncTask #" + sCreator.getAndIncrement());
        }
    };
    private static final String LOG_TAG = "MyAsyncTask";
    //线程池
    private static final Executor sTHREAD_POOL_EXECUTOR;
    private static final int MESSAGE_POST_RESULT = 0x1;
    private static final int MESSAGE_POST_PROGRESS = 0x2;
    private static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static int CORE_THREADS = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static int MAX_THREADS = CPU_COUNT * 2 + 1;
    private static int KEEP_ALIVE = 30;
    private static LinkedBlockingDeque sWorkQueue = new LinkedBlockingDeque(128);
    //串行执行
    private static Executor SERIAL_EXECUTOR = new SerialExecutor();
    private static volatile Executor sDefaultExecutor = SERIAL_EXECUTOR;
    //主线程handler
    private static Handler sHandler;

    static {
        sTHREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_THREADS, MAX_THREADS, KEEP_ALIVE, TimeUnit.SECONDS,
                sWorkQueue, sThreadFactory);

    }

    private Handler mHandler;
    private WorkRunnable<Params, Result> mWork;
    private FutureTask<Result> mFutureTaskreTask;

    private AtomicBoolean mTaskInvoved = new AtomicBoolean();
    private AtomicBoolean mTaskCanceled = new AtomicBoolean();
    private volatile Status mStatus = Status.PEDDING; //因为在子线程开始而在主线程结束，所以需要volitale修饰，

    public CopyAsyncTask() {

        this(Looper.getMainLooper());
    }


    public CopyAsyncTask(Looper callBackLoop) {

//
//        mHandler = callBackLoop == null || callBackLoop == Looper.getMainLooper() ? getMainHandler() : new Handler(callBackLoop);
        mHandler = getMainHandler();

        mWork = new WorkRunnable<Params, Result>() {
            @Override
            public Result call() throws Exception {
                mTaskInvoved.set(true);
                Result result = null;
                try {

                    Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                    //do task
                    result = doInBackground(mParams);
                    Binder.flushPendingCommands();

                } catch (Throwable e) {
                    mTaskCanceled.set(true);
                    throw e;
                } finally {
                    //send result
                    postResult(result);
                }
                return result;
            }
        };


        mFutureTaskreTask = new FutureTask<Result>(mWork) {
            @Override
            protected void done() {

                try {
                    postResultIfNotInvoked(get());
                } catch (InterruptedException e) {
                    android.util.Log.w(LOG_TAG, e);
                } catch (ExecutionException e) {
                    throw new RuntimeException("An error occurred while executing doInBackground()",
                            e.getCause());
                } catch (CancellationException e) {
                    postResultIfNotInvoked(null);
                }
            }
        };


    }

    public Handler getmHandler() {
        return mHandler;
    }

    public void cancel(boolean mayInterruptIfRunning) {
        mTaskCanceled.set(true);
        mFutureTaskreTask.cancel(mayInterruptIfRunning);
    }

    @WorkerThread
    private void finish(Result result) {
        //不管是否完成或取消，都会执行完毕的
        if (isCanceled()) {
            onCanceled(result);
        } else {
            onPostExecute(result);
        }
        mStatus = Status.FINISH;
    }

    protected void onCanceled(Result result) {

    }

    private boolean isCanceled() {
        return mTaskCanceled.get();
    }

    private void onPostProgress(Progress... progress) {

        onProgressUpdate(progress);
    }

    @WorkerThread
    public void publishProgress(Progress... progress) {
        if (!isCanceled()) {
            Message message = getmHandler().obtainMessage(MESSAGE_POST_PROGRESS, new CopyAsyncTaskResult<Progress>(this, progress));
            message.sendToTarget();
        }

    }

    protected void onPostExecute(Result result) {

    }

    protected void onProgressUpdate(Progress... progress) {

    }

    private synchronized Handler getMainHandler() {
        synchronized (CopyAsyncTask.class) {

            if (sHandler == null) {
                sHandler = new InnerHandler(Looper.getMainLooper());
            }
            return sHandler;
        }
    }

    private void postResultIfNotInvoked(Result result) {
        boolean taskInvoled = mTaskInvoved.get();
        if (!taskInvoled) {
            postResult(result);
        }
    }

    private Result postResult(Result result) {
        //send result , 封装结果返回
        Message message = getmHandler().obtainMessage(MESSAGE_POST_RESULT, new CopyAsyncTaskResult<Result>(this, result));
        message.sendToTarget();
        return result;
    }

    public CopyAsyncTask<Params, Progress, Result> execute(Params... params) {
        return executeOnExecutor(sDefaultExecutor, params);
    }

    private CopyAsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params[] params) {

        if (mStatus != Status.PEDDING) {
            switch (mStatus) {
                case RUNNING:
                    throw new RuntimeException("synctask is running !!");
                case FINISH:
                    throw new RuntimeException("synctask is running !!");
            }
        }

        mStatus = Status.RUNNING;
        onPreExecute();
        //如何把参数封装成可运行的对象？
        mWork.mParams = params;
        executor.execute(mFutureTaskreTask);
        return this;
    }

    protected void onPreExecute() {

    }

    public abstract Result doInBackground(Params... mParams);

    public enum Status {
        PEDDING,
        RUNNING,
        FINISH
    }

    static class SerialExecutor implements Executor {

        ArrayDeque<Runnable> mArrayDeque = new ArrayDeque<Runnable>();
        Runnable mActive;

        @Override
        public synchronized void execute(final Runnable command) {
            mArrayDeque.offer(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        scheDuleNext();
                    }

                }
            });
            if (mActive == null) {
                scheDuleNext();
            }

        }

        private synchronized void scheDuleNext() {
            if ((mActive = mArrayDeque.poll()) != null) {
                sTHREAD_POOL_EXECUTOR.execute(mActive);
            }

        }


    }

    static class InnerHandler extends Handler {
        public InnerHandler(Looper mainLooper) {
            super(mainLooper);
        }

        @Override
        public void handleMessage(Message msg) {
            CopyAsyncTaskResult<?> result = (CopyAsyncTaskResult<?>) msg.obj;
            switch (msg.what) {
                case MESSAGE_POST_RESULT:
                    //only one result
                    result.mAsyncTask.finish(result.mData[0]);
                    break;
                case MESSAGE_POST_PROGRESS:
                    //为什么会奔溃？没有break！！！！！草
//                    Log.d(LOG_TAG," progress:"+result.mData);
                    result.mAsyncTask.onPostProgress(result.mData);
                    break;
            }
        }
    }

    private abstract static class WorkRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;
    }

    private static class CopyAsyncTaskResult<Data> {
        Data[] mData;
        CopyAsyncTask mAsyncTask;

        public CopyAsyncTaskResult(CopyAsyncTask mAsyncTask, Data... data) {
            this.mData = data;
            this.mAsyncTask = mAsyncTask;
        }
    }

}
