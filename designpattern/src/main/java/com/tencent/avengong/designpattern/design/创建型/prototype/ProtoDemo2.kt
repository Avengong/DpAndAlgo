package com.tencent.avengong.designpattern.design.创建型.prototype

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

class ProtoDemo2 {

    var mCurrentSearchWords = HashMap<String, SearchWord>()

    var mLastUpdateTime: Long = 0

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val protoDemo2 = ProtoDemo2()

            protoDemo2.refresh()


        }
    }

    //单纯更新
    public fun refresh() {
        val searchWordFromDb = getSearchWordFromDb(mLastUpdateTime)

        var maxUpdateTime = mLastUpdateTime
        for (i in 0 until searchWordFromDb.size) {

            val searchWord = searchWordFromDb[i]
            if (searchWord.lastUpateTime > maxUpdateTime) {
                maxUpdateTime = searchWord.lastUpateTime
            }

            if (mCurrentSearchWords.contains(searchWord.keyword)) {
                mCurrentSearchWords.put(searchWord.keyword, searchWord)
            } else {

                mCurrentSearchWords.put(searchWord.keyword, searchWord)
            }

        }
        mLastUpdateTime = maxUpdateTime

    }


    //不停机更新
    public fun refreshWithoutStop() {
        val searchWordFromDb = getSearchWordFromDb()
        val mBackupSearchWords = HashMap<String, SearchWord>()
        for (i in 0 until searchWordFromDb.size) {
            val searchWord = searchWordFromDb[i]
            mBackupSearchWords[searchWord.keyword] = searchWord
        }

        mCurrentSearchWords = mBackupSearchWords

    }

    //不停机更新， clone 优化版--> 深浅拷贝优化版本
    public fun optRefreshWithoutStop() {
        val searchWordFromDb = getSearchWordFromDb()

        val mBackupSearchWords = mCurrentSearchWords.clone() as HashMap<String, SearchWord>

        var maxUpdateTime = mLastUpdateTime
        for (i in 0 until searchWordFromDb.size) {

            val searchWord = searchWordFromDb[i]
            if (searchWord.lastUpateTime > maxUpdateTime) {
                maxUpdateTime = searchWord.lastUpateTime
            }
//
//            if (mBackupSearchWords.contains(searchWord.keyword)) {
//                /**
//                 * 这里是关键，不要在此插入而是直接更新，时间复杂度为o（1）
//                 */
//                val old = mBackupSearchWords.get(searchWord.keyword)
//                old!!.keyword=searchWord.keyword
//                old.lastUpateTime=searchWord.lastUpateTime
//
//            } else {
//
//                mBackupSearchWords.put(searchWord.keyword, searchWord)
//            }

            /**
             * 深拷贝加浅拷贝优化版
             *
             */

            if (mBackupSearchWords.containsKey(searchWord.keyword)) {
                mBackupSearchWords.remove(searchWord.keyword)
            }

            mBackupSearchWords.put(searchWord.keyword, searchWord)


        }

        mLastUpdateTime = maxUpdateTime


        mCurrentSearchWords = mBackupSearchWords

    }


    private fun getSearchWordFromDb(): ArrayList<SearchWord> {
        //todo return the whole version data
        return arrayListOf()
    }


    private fun getSearchWordFromDb(mLastUpdateTime: Long): ArrayList<SearchWord> {
        //todo ...
        return arrayListOf()


    }

    /**
     * 深拷贝的两种方式
     * 方式一：序列化对象
     */

    fun deepCopy(ob: Any): Any {

        var bout = ByteArrayOutputStream()
        var objout = ObjectOutputStream(bout)
        objout.writeObject(ob)

        var bin = ByteArrayInputStream(bout.toByteArray())
        var obOut = ObjectInputStream(bin)

        return obOut.readObject()

    }


}