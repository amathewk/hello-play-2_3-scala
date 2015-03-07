package decision.actor

import akka.actor.{ActorLogging, Props, ActorRef, Actor}
import akka.actor.Actor.Receive
import akka.event.{LoggingReceive, Logging}
import decision.domain.DecisionNode
import scala.collection.mutable
import scala.collection.mutable.{Map, ArrayBuffer, Buffer}

/**
 * Created by amathew on 2/22/15.
 */
class DecisionActor(val decisionNode:DecisionNode) extends Actor with ActorLogging {

  val requestContexts = Map[String, RequestContext]()

  def receive = LoggingReceive {
    case request:Request => {
      if(context.children.size == 0) {
        sender ! Result(request, count = 1)
      } else {
        requestContexts(request.id) = RequestContext(request, sender(), new ArrayBuffer(initialSize = context.children.size))
        for(child <- context.children) {
          child ! request
        }
      }

    }

    case Result(req, count) => {
      val results: ArrayBuffer[Result] = requestContexts(req.id).results
      results += Result(req, count)
      if(results.size == context.children.size) {
        requestContexts(req.id).requestSender ! Result(req, results)
      }

    }

    case _ => log.error("Received unexpected message")
  }

  override def preStart(): Unit = {

    for(childNode <- decisionNode.children) {
      context.actorOf(DecisionActor.props(childNode), childNode.id)
    }
    super.preStart()
  }
}

object DecisionActor {

  def props(node:DecisionNode):Props = Props(new DecisionActor(node))
}


case class Request(val id:String)

case class Result(val req:Request, val count:Int = 1) {

  def +(result:Result):Result = {
    Result(req, this.count + result.count)
  }
}

object Result {

  def apply(req:Request, results:Buffer[Result]): Result = {
    results.par.fold(Result(req, 0)) { (tot, result) =>
      tot + result
    }
  }

}

case class RequestContext(val request: Request, var requestSender:ActorRef, var results:ArrayBuffer[Result] = ArrayBuffer[Result]())