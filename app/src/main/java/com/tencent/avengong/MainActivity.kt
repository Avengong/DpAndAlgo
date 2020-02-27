package com.tencent.avengong

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tencent.avengong.designpattern.author.ApiRequest
import com.tencent.avengong.designpattern.author.AuthorExecutorImpl
import com.tencent.avengong.designpattern.author.AuthorToken
import com.tencent.avengong.designpattern.author.CredentialStorage
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


    }


}
