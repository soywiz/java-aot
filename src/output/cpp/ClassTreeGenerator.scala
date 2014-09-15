package output.cpp

import output.SootUtils
import soot.{Scene, SootClass}

import scala.collection.mutable

class ClassTreeGenerator {
  private val processedList = new mutable.HashSet[SootClass]
  private val toProcessList = new mutable.Queue[SootClass]

  def enqueue(className:String):Unit = {
    //enqueue(Scene.v.loadClass(className, SootClass.HIERARCHY))
    enqueue(Scene.v.loadClassAndSupport(className))
  }
  
  def enqueue(clazz:SootClass):Unit = {
    if (!processedList.contains(clazz)) toProcessList.enqueue(clazz)
  }

  def run() = {
    while (toProcessList.length > 0) {
      //SootUtils.init()
      val item = toProcessList.dequeue()
      processedList.add(item)
      println("Processing class: " + item.getName)
      val result = new ClassGenerator(item).doClass()

      //println(result.declaration)
      println(result.definition)

      for (referencedClass <- result.referencedClasses) enqueue(referencedClass)
    }
    println("Processed classes: " + processedList.size)
  }
}
