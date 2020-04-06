package com.tencent.avengong.designpattern.samples.idgenarate;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class RandomIdGenerator implements LogTraceIdGenerator {


    public static void main(String[] agrs) {
        RandomIdGenerator randomIdGenerator = new RandomIdGenerator();
        String generate = randomIdGenerator.generate();
        System.out.println(" id: " + generate);
    }

    private static String generateRandomChar(int length) {
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isNumber = randomAscii >= '0' && randomAscii <= '9';
            boolean isLowerLetter = randomAscii >= 'a' && randomAscii <= 'z';
            boolean isUperLetter = randomAscii >= 'A' && randomAscii <= 'Z';

            if (isNumber || isLowerLetter || isUperLetter) {
                randomChars[count] = (char) randomAscii;

                count++;
            }

//      if (randomAscii >= 48 && randomAscii <= 57) {
//        randomChars[count] = (char)('0' + (randomAscii - 48));
//        count++;
//      } else if (randomAscii >= 65 && randomAscii <= 90) {
//        randomChars[count] = (char)('A' + (randomAscii - 65));
//        count++;
//      } else if (randomAscii >= 97 && randomAscii <= 122) {
//        randomChars[count] = (char)('a' + (randomAscii - 97));
//        count++;
//      }
        }
        return new String(randomChars);
    }

    private static String getLastFieldHostName() {
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
        } catch (UnknownHostException e) {
            Log.w("Failed", e.toString());
        }

        return hostName;
    }

    public String generate() {
        String id = "";

        String hostName = getLastFieldHostName();
        String randomChars = generateRandomChar(8);
        id = String.format("%s-%d-%s", hostName,
                System.currentTimeMillis(), new String(randomChars));

        return id;
    }


    /**
     * 大方面：
     * 1，不满足扩展，如果要修改算法，那么还是要修改这里的代码
     * 2，，如果要添加新功能，是否方便？
     *代码是否可以服用，封装成库或者lib
     *
     *
     * 小方面：
     *异常边界的处理
     * 多线程
     * 日志是否方便debug排查
     * 性能
     *
     *
     *
     *
     */

}