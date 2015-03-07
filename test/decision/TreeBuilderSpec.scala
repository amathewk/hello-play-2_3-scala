package decision

import org.scalatest.FlatSpec

/**
 * Created by amathew on 2/18/15.
 */
class TreeBuilderSpec extends FlatSpec {

  "The TreeBuilder" should "return a DecisionNode with the right number of nodes" in {
    val node = TreeBuilder.buildTree(7,20)
    assert(node.id == "C1")

    assert(node.children.size == 7)
    assert(node.children(0).children.size == 20)
    assert(node.children(3).children.size == 20)
    assert(node.children(6).children.size == 20)

  }

}
