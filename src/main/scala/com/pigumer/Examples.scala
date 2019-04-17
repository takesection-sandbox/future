package com.pigumer

import java.util.concurrent.{CompletableFuture, CompletionStage, Executors}

import scala.compat.java8.FutureConverters._
import scala.concurrent.ExecutionContext

object Examples extends App {

  val es = Executors.newCachedThreadPool()
  implicit val ec = ExecutionContext.fromExecutorService(es)

  val future = scala.concurrent.Future {
    Thread.sleep(5000)
    println("running")
    ()
  }
  println("end")

  future.onComplete(_ => es.shutdown())
}

object SampleConverterToScala {

  def convert[T](cf: CompletableFuture[T]): scala.concurrent.Future[T] =
    cf.toScala

}

object SampleConverterToJava {

  def convert[T](cf: scala.concurrent.Future[T]): CompletionStage[T] =
    cf.toJava

}

object SampleJavaFuture {

  def process(jf: java.util.concurrent.Future[Int]): Int = {
    jf.get()
  }

}

object SampleCompletableFuture {

  def process(cf: CompletableFuture[Int]) = {
    cf.thenApply(x => x * 2)
      .thenAccept(x => println(x))
  }
}

object SampleScalaFuture {

  implicit val ec = ExecutionContext.Implicits.global

  def process(sf: scala.concurrent.Future[Int]) = {
    sf.map(x => x * 2)
      .map(x => x.toString)
      .foreach(str => println(str))
  }
}

