package target.cpp

import soot._
import target.base.BaseMangler

object CppMangler extends BaseMangler {
  override def mangle(clazz:SootClass): String = mangleClassName(clazz.getName)
  override def mangle(field:SootField): String = field.getName
  override def mangleClassName(name:String):String = name.replace('.', '_')
  override def mangleFullClassName(name:String):String = name.replace('.', '_')

  override def visibility(member:ClassMember):String = if (member.isPublic) "public" else if (member.isProtected) "protected" else "private"
  override def staticity(member:ClassMember):String = if (member.isStatic) "static" else ""

  override def typeToCppRef(kind:Type): String = {
    kind match {
      case r:RefType => typeToCppNoRef(kind) + "*"
      case r:ArrayType => typeToCppNoRef(kind) + "*"
      case _ => typeToCppNoRef(kind)
    }
  }

  override def typeToCppNoRef(kind:Type): String = {
    kind match {
      case v:VoidType => "void"
      case v:NullType => "Null"
      case prim:PrimType =>
        prim match {
          case v:BooleanType => "bool"
          case v:ByteType => "int8"
          case v:CharType => "wchar_t"
          case v:ShortType => "int16"
          case v:IntType => "int32"
          case v:LongType => "int64"
          case v:FloatType => "float32"
          case v:DoubleType => "float64"
        }
      case r:ArrayType => "Array<" + typeToCppRef(r.getElementType) + ">"
      case r:RefType => mangleClassName(r.getClassName)
    }
  }
}
