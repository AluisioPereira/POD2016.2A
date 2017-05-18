package br.edu.ifpb.pod

/**
  * Created by ajp on 13/05/2017.
  */
class Op1(var x: Int, var y: Int) extends Serializable {

  def getX(): Int = {
    return x;
  }

  def setX(x: Int): Unit = {
    return this.x = x;
  }


  def getY(): Int = {
    return y;
  }

  def setY(y: Int): Unit = {
    return this.y = y;
  }


  override def finalize(): Unit = super.finalize()

  override def clone(): AnyRef = super.clone()

  override def toString = s"Op1($x, $y)"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Op1]

  override def equals(other: Any): Boolean = other match {
    case that: Op1 =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
