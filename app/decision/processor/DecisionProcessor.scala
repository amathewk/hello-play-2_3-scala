package decision.processor

import akka.actor.{ActorSystem, ActorRef}
import akka.routing.RoundRobinPool
import akka.util.Timeout
import decision.actor.{Request, Result, DecisionActor}
import decision.domain.OrgAssets
import scala.concurrent.{ExecutionContext, Await, Future}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}
import akka.pattern.{ ask, pipe }

/**
 * Created by amathew on 3/8/15.
 */
object DecisionProcessor {

  val decisionActor:ActorRef = ActorSystem().actorOf(RoundRobinPool(10).props(DecisionActor.props(OrgAssets.rootNode)), "Channel")
//  val decisionActor:ActorRef = ActorSystem().actorOf(DecisionActor.props(OrgAssets.rootNode), "Channel")



  def runDecision() = {

    implicit val timeout = Timeout(5 seconds)

    val f:Future[Result] = (decisionActor ? Request("1")).mapTo[Result]
    val resultMessage = Await.result(f, timeout.duration).asInstanceOf[Result]

    resultMessage.count
  }
}