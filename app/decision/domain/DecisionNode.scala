package decision.domain

/**
 * Created by amathew on 2/17/15.
 */
class DecisionNode(val id:String, val ruleIds:IndexedSeq[String] = IndexedSeq(), val children:IndexedSeq[DecisionNode] = IndexedSeq()) {


}

object DecisionNode {

  def apply(id:String, ruleIds:IndexedSeq[String], children:IndexedSeq[DecisionNode]) = {
    new DecisionNode(id, ruleIds, children)
  }

  def apply(id:String) = {
    new DecisionNode(id)
  }

}
