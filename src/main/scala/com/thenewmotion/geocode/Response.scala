package com.thenewmotion.geocode


case class Address(
  long_name: String,
  short_name: String,
  types: List[String]
)

case class Result(
  address_components: List[Address],
  formatted_address: String,
  types: List[String]
)

case class Response(
  results: List[Result],
  status: Status
) {
  def allResults = status match {
    case Ok => Right(results)
    case e: Error => Left(e)
  }
}

/** @see https://developers.google.com/maps/documentation/geocoding/#StatusCodes */
sealed trait Status

case object Ok extends Status

sealed trait Error extends Status
case object ZeroResults    extends Error
case object OverQuotaLimit extends Error
case object Denied         extends Error
case object InvalidRequest extends Error
case class OtherError(description: String) extends Error
