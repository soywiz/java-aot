package target

import util.RuntimeProvider
import vfs.VfsNode

import scala.collection.mutable.ListBuffer

class BaseProjectContext(val classNames:Seq[String], val mainClass:String, val runtimeProvider:RuntimeProvider, val outputPath:VfsNode) {
  val classes = new ListBuffer[BaseClassContext]

  //def getClassesWithStaticConstructor
}