package com.tencent.avengong

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tencent.avengong.designpattern.author.ApiRequest
import com.tencent.avengong.designpattern.author.AuthorExecutorImpl
import com.tencent.avengong.designpattern.author.AuthorToken
import com.tencent.avengong.designpattern.author.CredentialStorage
import com.tencent.avengong.designpattern.ocp.AlertClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_author.setOnClickListener {
            doAuthor()
        }
    }

    private fun doAuthor() {

//       calculateFlowers()

        val url = "http://www.baidu.com"
        val appid = "4444"
        val appkey = "key"
        val currentTimeMillis = System.currentTimeMillis();
        val createAuthorToken =
            AuthorToken.createAuthorToken(url, currentTimeMillis, mutableMapOf(appid to appkey))
        val apiRequest = ApiRequest(url, appid, createAuthorToken.getToken(), currentTimeMillis)
        val createFullUrl = apiRequest.createFullUrl()

        val authorExecutorImpl = AuthorExecutorImpl(CredentialStorage())
        authorExecutorImpl.author(createFullUrl)

        AlertClient().check()


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
