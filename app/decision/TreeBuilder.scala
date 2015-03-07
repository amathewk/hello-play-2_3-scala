package decision

import decision.domain.DecisionNode

/**
 * Created by amathew on 2/18/15.
 */
object TreeBuilder {

  def buildTree(numIPs:Int, numMGsPerIP:Int): DecisionNode = {

    var ips = IndexedSeq[DecisionNode]()
    for(a <- 1 to numIPs) {
      ips = ips :+ buildIP(s"IP_${a}", numMGsPerIP)
    }

    DecisionNode("C1", IndexedSeq("r1"), ips)
  }

  def buildIP(name:String, numMGs:Int):DecisionNode = {
    var mgs = IndexedSeq[DecisionNode]()
    for(a <- 1 to numMGs) {
      mgs = mgs :+ buildMG(s"MG${name}_${a}")
    }
    DecisionNode(name, IndexedSeq(), mgs)
  }

  def buildMG(name:String):DecisionNode = {
    DecisionNode(name)
  }
}
