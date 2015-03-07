package decision

import scala.concurrent.{ExecutionContext, Await, Future}
import ExecutionContext.Implicits.global
import akka.actor.{Inbox, ActorRef, Props, ActorSystem}
import akka.util.Timeout
import decision.actor.{Result, Request, DecisionActor}
import org.scalatest.FlatSpec

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import akka.pattern.{ ask, pipe }

/**
 * Created by amathew on 2/22/15.
 */
class DecisionActorSpec extends FlatSpec {

  "The DecisionActor" should "return the correct result count for the tree" in {
    val node = TreeBuilder.buildTree(7,20)
    assert(node.id == "C1")

    assert(node.children.size == 7)
    assert(node.children(0).children.size == 20)
    assert(node.children(3).children.size == 20)
    assert(node.children(6).children.size == 20)

    val actorSystem = ActorSystem()
    val decisionActor:ActorRef = actorSystem.actorOf(DecisionActor.props(node), "Channel")
    implicit val timeout = Timeout(5 seconds)

    val f:Future[Result] = (decisionActor ? Request("1")).mapTo[Result]
    val resultMessage = Await.result(f, timeout.duration).asInstanceOf[Result]
    assert(resultMessage.count == 140)

  }

}