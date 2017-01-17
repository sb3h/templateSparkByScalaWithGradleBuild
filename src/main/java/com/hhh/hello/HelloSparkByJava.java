package com.hhh.hello;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;

/**
 * Created by root on 16-12-20.
 */
public class HelloSparkByJava {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf()
                .setMaster("local[4]")
                .setAppName("3H-javaSpark");

        JavaSparkContext sc = new JavaSparkContext(
                sparkConf
//                "spark://master-node:7077"
//                ,"3H-javaSpark"
        );
//       JavaRDD<String> rdd = sc.textFile("file:///etc/hosts");
//        System.out.println(rdd.first());

       JavaRDD intRdd = sc.parallelize(Arrays.asList(1,2,3,4));
        intRdd.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println("Thread.currentThread().getName():"+
                        Thread.currentThread().getName() +" integer:"+integer);
            }
        });
//        System.out.println("womei:"+intRdd.first());

    }
}
