package com.tencent.avengong

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.tencent.asynctask.CopyAsyncTask
import com.tencent.avengong.designpattern.samples.apiauthor.ExecuteEntrance
import com.tencent.avengong.designpattern.samples.apiauthor.ServerApiAuthor
import com.tencent.avengong.designpattern.samples.apiauthor.ServerAppKeyStorage
import com.tencent.avengong.designpattern.samples.apistatistics.MemoryMetricStorage
import com.tencent.avengong.designpattern.samples.apistatistics.MetricsCollect
import com.tencent.avengong.designpattern.samples.apistatistics.UserAthor
import com.tencent.avengong.designpattern.samples.apistatistics.UserInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val copyOnWriteArrayList = CopyOnWriteArrayList<String>()
    public lateinit var tvDesc: TextView
    val blockingDeque = LinkedBlockingDeque<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_author.setOnClickListener {
            doAuthor()
        }

        blockQueue.setOnClickListener {
            doBlock()
        }


        toast.setOnClickListener {
            doToastTest()
        }
        tvDesc = findViewById<TextView>(R.id.tv_desc)
        asynctask.setOnClickListener { doAsyncTask() }

    }

    private fun doAsyncTask() {

        Log.d("MyAsyncTask", "doAsyncTask   thread:${Thread.currentThread().name}")
//        Thread (Runnable {
//            Log.d("MyAsyncTask", "start   thread:${Thread.currentThread().keyword}")
//            for (i in 1 ..3){
//                var masynTask= MyAsyncTask()
//                masynTask.execute("www.bai.com","www.bai.com$i")
//            }
//        } ).start()

//        thread (start=true){
//            Log.d("MyAsyncTask", "start   thread:${Thread.currentThread().keyword}")
//            for (i in 1 ..3){
//                var masynTask= MyAsyncTask()
//                masynTask.execute("www.bai.com","www.bai.com$i")
//            }
//        }

//        Thread {
//            Log.d("MyAsyncTask", "start   thread:${Thread.currentThread().keyword}")
//            for (i in 1 ..3){
//                var masynTask= MyAsyncTask()
//                masynTask.execute("www.bai.com","www.bai.com$i")
//            }
//        }.start()

//        object :Thread(){
//            override fun run() {
//
//                Log.d("MyAsyncTask", "start   thread:${Thread.currentThread().keyword}")
//                for (i in 1 ..3){
//                    var masynTask= MyAsyncTask()
//                    masynTask.execute("www.bai.com","www.bai.com$i")
//                }
//            }
//        }.start()
        Log.d("MyAsyncTask", "start   thread:${Thread.currentThread().name}")

        val tasklist = arrayListOf<MyAsyncTask>()
        for (i in 1..2) {
            var masynTask = MyAsyncTask("asynctask$i")


            masynTask.execute("www.bai.com", "www.bai.com$i")
            tasklist.add(masynTask)
        }


        TimeUnit.MILLISECONDS.sleep(80)
        for (task in tasklist) {
            task.cancel(true)
        }


    }

    inner class MyAsyncTask(var s: String) : CopyAsyncTask<String, Int, String>() {
        override fun doInBackground(vararg params: String?): String? {


            for (str in params) {
                Log.d("MyAsyncTask", "doInBackground  param:$str , thread:${Thread.currentThread().name}")

            }
            for (i in 0..100) {

                publishProgress(i)
                TimeUnit.MILLISECONDS.sleep(10)
            }

            return "task is done!!"
        }

        override fun onPreExecute() {

            Log.d(
                "MyAsyncTask",
                "onPreExecute keyword: $s , pool:${AsyncTask.THREAD_POOL_EXECUTOR}, thread：${Thread.currentThread().name}"
            )

        }

        override fun onPostExecute(result: String?) {
            Log.d("MyAsyncTask", "onPostExecute : $result ")
        }

        override fun onProgressUpdate(vararg values: Int?) {

            tvDesc.text = "${values[0]}"
            Log.d("MyAsyncTask", "onProgressUpdate :  ${values[0]}")

        }

        override fun onCanceled(result: String?) {
            Log.d("MyAsyncTask", "onCanceled : $result ")

        }


    }


    private fun doToastTest() {

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()

        val newFixedThreadPool = Executors.newFixedThreadPool(1)


    }

    private fun doBlock() {
        //目的是测试 生产者与消费者的阻塞（挂起）情形：
        //1，生产者挂起 --队列满了

        //2. 消费者挂起 队列空了

        val take = blockingDeque.take()
        Log.d(TAG, "take:$take")


    }

    private fun doAuthor() {

//       calculateFlowers()

        for (i in 0 until 100) {
            copyOnWriteArrayList.add("i:$i")
        }

        Log.d("testcopy", "size:${copyOnWriteArrayList.size} ")
        copyOnWriteArrayList.removeAt(0)
        Log.d("testcopy", "size:${copyOnWriteArrayList.size} ")


        val userInfo = UserInfo("", 11)
        val userAthor = UserAthor(MetricsCollect(MemoryMetricStorage()))
        userAthor.register(userInfo = userInfo)
        userAthor.login("", "")


        val executeEntry = ExecuteEntrance(ServerApiAuthor(ServerAppKeyStorage()))
        executeEntry.startClientRequest()


    }

    private fun calculateFlowers() {

        // 水仙花数
        val a = StringBuilder()
        val end = 1000000
        for (i in 0 until end) {
            val len = end.toString().length
            var num: Int
            var left: Int = i
            val nums = mutableListOf<Int>()
            var total = 0
            for (k in 0 until len) {
                if (left > 0) {
                    num = left % 10
                    left /= 10
                    nums.add(num)
                    total += Math.pow(num.toDouble(), 3.toDouble()).toInt()
                }
            }
            if (i == total) {
                a.append(i).append("   ")
            }

        }
        Log.d("水仙花数", " sb: ${a}")
    }


}
