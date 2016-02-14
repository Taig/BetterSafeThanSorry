package io.taig.bsts.android.ops

import android.view.View
import io.taig.android.viewvalue.Extraction
import io.taig.bsts._
import io.taig.bsts.android.syntax.tags._
import io.taig.bsts.android.{ Event, Feedback }
import io.taig.bsts.data.{ NonEmptyList, Validated }
import io.taig.bsts.report.Report

import scala.language.experimental.macros

final class validation[V <: View]( view: V ) {
    /**
     * Attach a policy to this view
     */
    def obeys[I]: Builder1[I] = new Builder1[I]

    class Builder1[I] {
        def apply[O]( validation: Validation[I, O] )(
            implicit
            ev: Event[V],
            ex: Extraction[V, I],
            f:  Feedback[V],
            r:  Report.Aux[validation.R, String, Validated[NonEmptyList[String], O]]
        ): V = {
            ev.onAttach( view )
            view.feedback = f
            view.validation = () ⇒ r.report( validation.validate( ex.extract( view ) ) )
            view
        }
    }

    /**
     * Remove all validation rules from this view
     */
    def reset()( implicit ev: Event[V] ): Unit = {
        ev.onDetach( view )
        view.feedback = null
        view.validation = null
    }
}