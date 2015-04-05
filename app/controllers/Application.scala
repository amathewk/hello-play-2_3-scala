package controllers

import decision.processor.DecisionProcessor
import play.api.mvc.{Action, Controller}

object Application extends Controller {

  def index = Action {
//    Ok(views.html.index("Hello Play Framework"))
//    Ok(JSON ("Hello Play Framework"))


    val count:Int = DecisionProcessor.runDecision()
    Ok(s"count is $count")
  }
}